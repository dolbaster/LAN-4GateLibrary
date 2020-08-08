package org.lanter.lan4gate.Implementation.Communication;

import org.lanter.lan4gate.Communication.ICommunication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SingleConnectionTCPServer implements ICommunication {
    private ServerSocketChannel mServerChannel;
    private Selector mConnectionSelector;
    private SelectionKey mRegisteredConnection = null;
    private final Queue<ByteBuffer> mDataForSend = new ConcurrentLinkedQueue<>();
    private final Queue<ByteBuffer> mReceivedData = new ConcurrentLinkedQueue<>();
    private int mPort = 20501;

    @Override
    public void openCommunication() throws IOException{
        createSelector();
        createTcpServer();
        runServer();
    }

    @Override
    public void closeCommunication() throws IOException {
        stopSelector();
    }

    public void setPort(int port) { mPort = port; }
    public int getPort() { return mPort; }

    @Override
    public void sendData(ByteBuffer data) {
        mDataForSend.add(data);
    }

    @Override
    public ByteBuffer getData() {
        return mReceivedData.poll();
    }

    @Override
    public void doCommunication() throws IOException{
        int count = mConnectionSelector.selectNow();

        if (count == 0) {
            return;
        }

        Set<SelectionKey> keys = mConnectionSelector.selectedKeys();
        Iterator<SelectionKey> iterator = keys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            try {
                if(key.isAcceptable()) {
                    addConnection(registerChannelFromServer(getClientChannel(key)));
                }
                if (key.isReadable())
                {
                    readData(key);
                }
                if (key.isWritable())
                {
                    sendData(key, extractData(key));
                }
            } catch (Exception ignored) { }
            iterator.remove();
        }
    }

    public boolean isOpen () {
        return mConnectionSelector != null && mConnectionSelector.isOpen();
    }

    public boolean isConnected() {
        return mRegisteredConnection != null;
    }

    private void createSelector() throws IOException {
        if(mConnectionSelector == null)
        {
            mConnectionSelector = Selector.open();
        }
    }
    private void createTcpServer() throws IOException {
        if (mServerChannel == null)
        {
            //каналы платформозависимы, необходимо использовать фабрику
            mServerChannel = ServerSocketChannel.open();
            //в селекторах канал может быть только в неблокирующем состоянии
            mServerChannel.configureBlocking(false);
            //серверный канал регистрируется только на принятие соединения
            mServerChannel.register(mConnectionSelector, SelectionKey.OP_ACCEPT);
        }
    }
    private void runServer() throws IOException {
        mServerChannel.socket().bind(new InetSocketAddress(mPort));
    }
    private void stopSelector() throws IOException {
        try
        {
            for (SelectionKey selectionKey : mConnectionSelector.keys()) {
                try {
                    SelectableChannel channel = selectionKey.channel();

                    if (channel instanceof SocketChannel) {
                        SocketChannel socketChannel = (SocketChannel) channel;
                        socketChannel.close();
                        selectionKey.cancel();
                    } else if (channel instanceof ServerSocketChannel) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) channel;
                        serverSocketChannel.close();
                        selectionKey.cancel();
                    }
                } catch (IOException ignored) {

                }
            }
            mConnectionSelector.close();
        }
        catch (Exception ignored)
        {

        }
    }
    private void closeClientConnection(SelectionKey key) throws IOException {
        closeConnection(key);
        mDataForSend.clear();
        mReceivedData.clear();
        mRegisteredConnection = null;
    }
    private void closeConnection(SelectionKey key) throws IOException{
        if (key.channel().isRegistered())
        {
            key.cancel();
        }
        key.channel().close();
    }
    private void addConnection(SelectionKey key) throws IOException {
        if(allowRegisterConnection()) {
            mRegisteredConnection = key;
        } else {
            closeConnection(key);
        }
    }
    private void sendData(SelectionKey key, ByteBuffer buffer) throws IOException {
        if(buffer != null && key != null)
        {
            ((SocketChannel) key.channel()).write(buffer);
        }
    }
    private ByteBuffer extractData(SelectionKey key) throws IOException {
        ByteBuffer buffer = mDataForSend.peek();
        mDataForSend.poll();
        return buffer;
    }
    private SelectionKey registerChannelFromServer(SocketChannel channel) throws IOException {

        if(channel != null)
        {
            if(allowRegisterConnection()) {
                channel.configureBlocking(false);
                return channel.register(mConnectionSelector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            } else {
                channel.close();
            }
        }

        return null;
    }
    private SocketChannel getClientChannel(SelectionKey key) throws IOException {
        if (key != null) {
            if (key.channel() instanceof ServerSocketChannel) {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                return serverChannel.accept();
            }
        }
        return null;
    }
    private void readData(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ByteBuffer buf = ByteBuffer.allocate(2048);

        int bytes = channel.read(buf);

        while (bytes > 0)
        {
            buf.flip();
            outputStream.write(buf.array());
            buf.clear();
            bytes = channel.read(buf);
        }
        if(bytes != -1)
        {
            mReceivedData.offer(ByteBuffer.wrap(outputStream.toByteArray()));
        }
        else
        {
            closeClientConnection(key);
        }
    }
    public boolean allowRegisterConnection() {
        return mRegisteredConnection == null;
    }
}