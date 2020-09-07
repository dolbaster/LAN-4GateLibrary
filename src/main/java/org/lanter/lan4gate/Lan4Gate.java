package org.lanter.lan4gate;

import org.lanter.lan4gate.Communication.ICommunication;
import org.lanter.lan4gate.Implementation.Communication.SingleConnectionTCPServer;
import org.lanter.lan4gate.Managers.BridgeManagerFactory;
import org.lanter.lan4gate.Managers.IBridgeManager;
import org.lanter.lan4gate.MessageProcessor.Builder.IMessageBuilder;
import org.lanter.lan4gate.MessageProcessor.Builder.MessageBuilderFactory;
import org.lanter.lan4gate.MessageProcessor.Parser.IMessageParser;
import org.lanter.lan4gate.MessageProcessor.Parser.MessageParserFactory;
import org.lanter.lan4gate.Messages.Bridge.IBridge;
import org.lanter.lan4gate.Messages.Notification.INotification;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Implementation.Messages.Requests.Request;
import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Messages.Request.RequestFactory;
import org.lanter.lan4gate.Messages.Response.IResponse;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * This class encapsulates ECR protocol LAN-4Gate
 */
public class Lan4Gate implements Runnable {
    private final Set<IRequestCallback> mRequestListeners = new HashSet<>();
    private final Set<IResponseCallback> mResponseListeners = new HashSet<>();
    private final Set<INotificationCallback> mNotificationListeners = new HashSet<>();
    private final Set<ICommunicationCallback> mCommunicationListeners = new HashSet<>();
    private final Set<IErrorCallback> mErrorListeners = new HashSet<>();

    private final int mEcrNumber;

    private int mPort = 20501;

    private ICommunication mCommunication = new SingleConnectionTCPServer();

    private Thread mThread = null;

    private IBridgeManager mBridge;
    /**
     * Creates Lan4Gate object with ecrNumber
     *
     * @param ecrNumber Logical ECR number, used for communication with terminal. Value in range [1,999]
     */
    public Lan4Gate(int ecrNumber) {
        mEcrNumber = ecrNumber;
    }

    public void setBridgeManager(IBridgeManager bridge) throws RuntimeException {
        if(!isStarted()) {
            mBridge = bridge;
        } else {
            throw new RuntimeException("Stop Lan4Gate before enable bridge");
        }
    }

    public void setCommunication(ICommunication communication) throws RuntimeException {
        if(!isStarted()) {
            mCommunication = communication;
        } else {
            throw new RuntimeException("Stop Lan4Gate before change communication");
        }

    }
    /**
     * Добавляет слушателя {@link IRequestCallback}
     * йцукенгшщзхъфывапролджэячсмитьбю
     * @param callback Объект, реализующий интерфейс {@link IRequestCallback}
     */
    public void addRequestCallback(IRequestCallback callback) {
        mRequestListeners.add(callback);
    }
    /**
     * Add listener for {@link IResponseCallback}
     *
     * @param callback Object, implements interface {@link IResponseCallback}
     */
    public void addResponseCallback(IResponseCallback callback) {
        mResponseListeners.add(callback);
    }

    /**
     * Remove registered {@link IResponseCallback} listener
     *
     * @param callback Registered {@link IResponseCallback} object
     */
    public void removeResponseCallback(IResponseCallback callback) {
        mResponseListeners.remove(callback);
    }

    /**
     * Add listener for {@link ICommunicationCallback}
     *
     * @param callback Object, implements interface {@link ICommunicationCallback}
     */
    public void addCommunicationCallback(ICommunicationCallback callback) {
        mCommunicationListeners.add(callback);
    }

    /**
     * Remove registered {@link ICommunicationCallback} listener
     *
     * @param callback Registered {@link ICommunicationCallback} object
     */
    public void removeCommunicationCallback(ICommunicationCallback callback) {
        mCommunicationListeners.remove(callback);
    }

    /**
     * Add listener for {@link INotificationCallback}
     *
     * @param callback Object, implements interface {@link INotificationCallback}
     */
    public void addNotificationCallback(INotificationCallback callback) {
        mNotificationListeners.add(callback);
    }

    /**
     * Remove registered {@link INotificationCallback} listener
     *
     * @param callback Registered {@link INotificationCallback} object
     */
    public void removeNotificationCallback(INotificationCallback callback) {
        mNotificationListeners.remove(callback);
    }

    /**
     * Add listener for {@link IErrorCallback}
     *
     * @param callback Object, implements interface {@link IErrorCallback}
     */
    public void addErrorCallback(IErrorCallback callback) {
        mErrorListeners.add(callback);
    }

    /**
     * Remove registered {@link IErrorCallback} listener
     *
     * @param callback Registered {@link IErrorCallback} object
     */
    public void removeErrorCallback(IErrorCallback callback) {
        mErrorListeners.remove(callback);
    }

    /**
     * Sets working TCP port
     *
     * @param port Value in range [0, 65535]
     */
    @Deprecated
    public void setPort(int port) {
        mPort = port;
        if(mCommunication instanceof SingleConnectionTCPServer) {
            ((SingleConnectionTCPServer) mCommunication).setPort(mPort);
        }
    }

    /**
     * Returns using TCP port
     *
     * @return Value in range [0, 65535]
     */
    @Deprecated
    public int getPort() { return mPort; }

    /**
     * Sets IP for connection in TCP Client mode
     * Currently is STUB
     * @param ip String in format "127.0.0.1"
     */
    @Deprecated
    public void setIP(String ip) { }

    /**
     * Returns using IP
     *
     * @return String in format "127.0.0.1"
     */
    @Deprecated
    public String getIP() { return "127.0.0.1"; }

    /**
     * Start protocol communication. For TCP Server mode (default state) it starts thread for {@link java.nio.channels.Selector}
     * After correct start {@link ICommunicationCallback}.communicationStarted() will be called
     */
    public void start() {
        if(mThread == null) {
            mThread = new Thread(this);
            mThread.start();
        }
    }

    /**
     * Returns start state of protocol communication
     *
     * @return True, if already and correct started
     */
    public boolean isStarted() {
        return mThread != null && mThread.isAlive();
    }

    /**
     * Returns state of link opening. For TCP Server mode it`s returns status of server socket opening.
     * For TCP Client mode it`s returns status of client link opening
     *
     * @return True, if client link opened
     */
    public boolean linkIsOpen() {
        return mCommunication != null && mCommunication.isOpen();
    }

    /**
     * Returns state of link connection. For TCP Server mode it`s returns status remote client connection.
     * For TCP Client mode it`s returns connection status to remote host.
     * @return True, if client is connected.
     */
    public boolean linkIsConnected() {
        return mCommunication != null && mCommunication.isConnected();
    }

    /**
     * Stop protocol communication.
     * After correct start {@link ICommunicationCallback}.communicationStopped() will be called
     */
    public void stop() {
        if(mThread != null) {
            mThread.interrupt();
            mThread = null;
        }
    }

    /**
     * Prepare and returns {@link IRequest} object.
     * Automatically fill ecrNumber, Type and mandatory fields
     *
     * @param operation One of {@link OperationsList} enum values
     *
     * @return Prepared object, implements {@link IRequest}
     */
    public IRequest getPreparedRequest(OperationsList operation) {
        return RequestFactory.getRequest(operation, mEcrNumber);
    }

    /**
     * Send new request to terminal
     *
     * @param request Prepared object, implements {@link IRequest}
     */
    public void sendRequest(IRequest request){
        IMessageBuilder builder = MessageBuilderFactory.getBuilder();
        ByteBuffer result = builder.buildMessage((Request) request);
        if(result != null) {
            try {
                mCommunication.sendData(result);
            } catch (Exception ignored) {}
        }
    }

    private void newData(ByteBuffer data) {
        if(data != null) {
            String convertedData = StandardCharsets.UTF_8.decode(data).toString();

            IMessageParser parser = MessageParserFactory.getParser();

            switch (parser.parse(convertedData)) {
                case Response:
                    IResponse response = parser.getResponse();
                    for (IResponseCallback callback : mResponseListeners) {
                        callback.newResponseMessage(response, this);
                    }
                    break;
                case Notification:
                    INotification notification = parser.getNotification();
                    for (INotificationCallback callback : mNotificationListeners) {
                        callback.newNotificationMessage(notification, this);
                    }
                    break;
                case Bridge:
                    IBridge bridge = parser.getBridge();
                    if(mBridge != null) {
                        mBridge.newMessage(bridge);
                    }
                    break;
            }
        }
    }

    private void communicationStarted() {
        for(ICommunicationCallback callback : mCommunicationListeners) {
            callback.communicationStarted(this);
        }
    }

    private void communicationStopped() {
        for(ICommunicationCallback callback : mCommunicationListeners) {
            callback.communicationStopped(this);
        }
    }

    private void connected() {
        for(ICommunicationCallback callback : mCommunicationListeners) {
            callback.connected(this);
        }
    }

    private void disconnected() {
        for(ICommunicationCallback callback : mCommunicationListeners) {
            callback.disconnected(this);
        }
    }

    private void errorMessage(String error) {
        for(IErrorCallback callback : mErrorListeners) {
            callback.errorMessage(error,this);
        }
    }

    private void errorException(Exception exception) {
        for(IErrorCallback callback : mErrorListeners) {
            callback.errorException(exception, this);
        }
    }

    public void run() {
        if(mCommunication == null){
            mCommunication = new SingleConnectionTCPServer();
        }

        if(mBridge != null) {
            try {
                mBridge.setConnection(mCommunication);
                mBridge.start();
            } catch (IOException ignored) {
            }
        }
        boolean isConnected = false;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if(mBridge != null) {
                    mBridge.doManager();
                }

                if(mCommunication != null) {
                    if(!mCommunication.isOpen()) {
                        mCommunication.openCommunication();
                        communicationStarted();
                    }
                    mCommunication.doCommunication();
                    if(isConnected != mCommunication.isConnected()) {
                        isConnected = mCommunication.isConnected();

                        if(isConnected){
                            connected();
                        } else {
                            disconnected();
                        }
                    }

                    if(isConnected) {
                        newData(mCommunication.getData());
                    }
                }
            } catch (Exception e) {
                errorException(e);
            }
        }

        if(mBridge != null) {
            mBridge.closeAllConnections();
        }
        if(mCommunication != null) {
            try {
                mCommunication.closeCommunication();
            } catch (IOException e) {
                errorException(e);
            }
        }
        communicationStopped();
    }
}
