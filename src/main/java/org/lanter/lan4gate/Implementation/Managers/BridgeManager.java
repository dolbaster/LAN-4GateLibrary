package org.lanter.lan4gate.Implementation.Managers;

import android.util.Base64;
import android.util.Log;

import org.lanter.lan4gate.Communication.ICommunication;
import org.lanter.lan4gate.Managers.IBridgeManager;
import org.lanter.lan4gate.MessageProcessor.Builder.IMessageBuilder;
import org.lanter.lan4gate.MessageProcessor.Builder.MessageBuilderFactory;
import org.lanter.lan4gate.Messages.Bridge.BridgeCommand;
import org.lanter.lan4gate.Messages.Bridge.BridgeFactory;
import org.lanter.lan4gate.Messages.Bridge.BridgeStatus;
import org.lanter.lan4gate.Messages.Bridge.IBridge;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BridgeManager implements IBridgeManager {

    //uses as payload for selectors
    private static class LinkConfiguration
    {
        public LinkConfiguration(String ip, int port, int linkId)
        {
            this.IP = ip;
            this.port = port;
            this.linkId = linkId;
        }
        public String getIP() {return IP;}

        public int getPort()
        {
            return port;
        }
        public int getLinkId()
        {
            return linkId;
        }
        private String IP;
        private int port;
        private int linkId;
    }

    private ICommunication mConnection;
    private Selector m_ConnectionSelector;
    private Map<Integer, SelectionKey> m_ConnectionMap = new HashMap<>();
    private Map<Integer, ConcurrentLinkedQueue<ByteBuffer>> m_DataForSend = new HashMap<>();

    @Override
    public void start() throws IOException {
        m_ConnectionSelector = Selector.open();
    }

    @Override
    public void closeAllConnections() {
        for (SelectionKey selectionKey : m_ConnectionMap.values()) {
            try {
                selectionKey.channel().close();
                selectionKey.cancel();
            } catch (Exception ignored) {
            }
        }
        try
        {
            m_ConnectionSelector.close();
        }catch (Exception ignored) {}
    }

    @Override
    public void doManager() {
        try {
            int count = m_ConnectionSelector.selectNow();

            if (count == 0) {
                return;
            }

            Set<SelectionKey> keys = m_ConnectionSelector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                try
                {
                    if (key.isReadable()) {
                        readData(key);
                    }
                    if (key.isWritable()) {
                        sendData(key, extractData(key));
                    }
                    if (key.isConnectable()) {
                        SocketChannel channel = (SocketChannel) key.channel();

                        if (!channel.isConnected() && !channel.isConnectionPending()) {
                            LinkConfiguration info = (LinkConfiguration) key.attachment();
                            channel.connect(new InetSocketAddress(info.getIP(), info.getPort()));
                        } else {
                            channel.finishConnect();
                            registerConnection(key);
                        }
                    }
                } catch (Exception ignored) {
                }
                iterator.remove();
            }
        } catch (IOException ignored) { }
    }

    @Override
    public void setConnection(ICommunication connection) {
        mConnection = connection;
    }

    @Override
    public void newMessage(IBridge result) {
        try {
            switch (result.getCommand()) {
                case OpenConnection: {
                    Log.d("LAN-4Gate", "Open connection");
                    openConnection(result);
                    break;
                }
                case CloseConnection: {
                    Log.d("LAN-4Gate", "Close connection");
                    closeClientConnection(getConnection(result.getLinkID()));
                    break;
                }
                case CheckStatus: {
                    Log.d("LAN-4Gate", "Check status");
                    sendConnectionStatus(result.getLinkID());
                    break;
                }
                case DataExchange: {
                    Log.d("LAN-4Gate", "Data exchange");
                    addData(result);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            Log.d("LAN-4Gate", "New command exception. Reason: " + e.getMessage());
        }
    }
    private void addData(IBridge result) {
        if(m_DataForSend.containsKey(result.getLinkID())) {
            ByteBuffer buf = result.getData();
            if(buf != null) {
                m_DataForSend.get(result.getLinkID()).offer(buf);
            }
        }
    }

    private void sendConnectionStatus(int linkId) throws IOException {
        SelectionKey key = getConnection(linkId);


        IBridge result = BridgeFactory.getBridge(BridgeCommand.CheckStatus);

        result.setLinkID(linkId);

        if(key != null && ((SocketChannel)key.channel()).isConnected()) {
            result.setStatus(BridgeStatus.Success);

        } else {
            result.setStatus(BridgeStatus.Error);
        }

        IMessageBuilder builder = MessageBuilderFactory.getBuilder();
        mConnection.sendData(builder.buildMessage(result));
    }
    private void readData(SelectionKey key) throws IOException {
        SocketChannel channel1 = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(2048);
        int bytes = channel1.read(buf);
        if(bytes > 0) {
            buf.flip();
            ByteBuffer result = ByteBuffer.wrap(buf.array(), 0, bytes).slice();
            sendDataExchange(result, (int)key.attachment());
        } else if (bytes < 0) {
            closeClientConnection(key);
        }
        buf.clear();
    }
    private void sendConnectionClosed(int linkID, BridgeStatus status) throws IOException{
        if(mConnection != null)
        {
            IBridge result = BridgeFactory.getBridge(BridgeCommand.CloseConnection);
            result.setLinkID(linkID);
            result.setStatus(status);

            IMessageBuilder builder = MessageBuilderFactory.getBuilder();

            mConnection.sendData(builder.buildMessage(result));
        }
    }
    private void sendDataExchange(ByteBuffer buffer, int linkID) throws IOException {
        if(mConnection != null)
        {
            IBridge result = BridgeFactory.getBridge(BridgeCommand.DataExchange);
            result.setLinkID(linkID);
            result.setData(buffer.slice());

            IMessageBuilder builder = MessageBuilderFactory.getBuilder();

            mConnection.sendData(builder.buildMessage(result));
        }
    }
    private void openConnection(IBridge command) throws IOException {
        
        if(!m_ConnectionMap.containsKey(command.getLinkID())) {
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            SelectionKey key = channel.register(m_ConnectionSelector, SelectionKey.OP_CONNECT);
            key.attach(new LinkConfiguration(command.getIP(), command.getPort(), command.getLinkID()));
        } else {
            if(mConnection != null) {
                IBridge result = BridgeFactory.getBridge(BridgeCommand.OpenConnection);
                result.setLinkID(command.getLinkID());

                if(((SocketChannel)getConnection(command.getLinkID()).channel()).isConnected()) {
                    result.setStatus(BridgeStatus.Success);
                } else {
                    result.setStatus(BridgeStatus.Error);
                }

                IMessageBuilder builder = MessageBuilderFactory.getBuilder();

                mConnection.sendData(builder.buildMessage(result));
            }
        }
    }
    private void closeClientConnection(SelectionKey key) throws IOException {
        if(key != null) {
            if (key.attachment() != null && key.attachment() instanceof Integer) {
                m_DataForSend.remove(key.attachment());
                m_ConnectionMap.remove(key.attachment());
                sendConnectionClosed((int) key.attachment(), BridgeStatus.Success);
            } else {
                sendConnectionClosed((int) key.attachment(), BridgeStatus.Error);
            }
            if (key.channel().isRegistered()) {
                key.cancel();
            }
            key.channel().close();
        }
    }
    private void registerConnection(SelectionKey key) throws IOException{
        LinkConfiguration info = (LinkConfiguration) key.attachment();
        key.attach(info.getLinkId());

        setRWInterest(key);
        m_ConnectionMap.put(info.getLinkId(), key);
        m_DataForSend.put(info.getLinkId(), new ConcurrentLinkedQueue<ByteBuffer>());
        if(mConnection != null)
        {
            IBridge result = BridgeFactory.getBridge(BridgeCommand.OpenConnection);
            result.setLinkID(info.getLinkId());
            result.setStatus(BridgeStatus.Success);

            IMessageBuilder builder = MessageBuilderFactory.getBuilder();

            mConnection.sendData(builder.buildMessage(result));
        }
    }
    private void setRWInterest(SelectionKey key) {
        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }
    private String encodeBase64(ByteBuffer buffer) {
        return Base64.encodeToString(buffer.array(), Base64.NO_WRAP);
    }
    private ByteBuffer decodeBase64(String data) {
        return ByteBuffer.wrap(Base64.decode(data, Base64.NO_WRAP));
    }
    private void sendData(SelectionKey key, ByteBuffer message) throws IOException {
        if(key != null && message != null)
        {
            SocketChannel channel = (SocketChannel)key.channel();
            channel.write(message);
        }
    }
    private ByteBuffer extractData(SelectionKey key) throws IOException {
        if(key.attachment() != null && key.attachment() instanceof Integer)
        {
            Integer ecrNumber = (Integer)key.attachment();
            if(m_DataForSend.containsKey(ecrNumber))
            {
                ConcurrentLinkedQueue<ByteBuffer> data = m_DataForSend.get(ecrNumber);
                if(data != null && !data.isEmpty())
                {
                    ByteBuffer buffer = data.peek();
                    data.poll();
                    return buffer;
                }
            }
        }
        return null;
    }
    private SelectionKey getConnection(int linkId) {
        if(m_ConnectionMap.containsKey(linkId))
        {
            return m_ConnectionMap.get(linkId);
        }
        return null;
    }
}
