package org.lanter.lan4gate.Communication;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * The interface Communication.
 */
public interface ICommunication extends Runnable {
    /**
     * Add communication listener.
     *
     * @param listener the listener
     */
    void addCommunicationListener(ICommunicationListener listener);

    /**
     * Remove communication listener.
     *
     * @param listener the listener
     */
    void removeCommunicationListener(ICommunicationListener listener);

    /**
     * Open communication.
     */
    void openCommunication() throws IOException;

    /**
     * Is open boolean.
     *
     * @return the boolean
     */
    boolean isOpen ();

    /**
     * Is connected boolean.
     *
     * @return the boolean
     */
    boolean isConnected();

    /**
     * Start monitoring.
     */
    void startMonitoring() throws IOException;

    /**
     * Stop monitoring.
     */
    void stopMonitoring();

    /**
     * Is started boolean.
     *
     * @return the boolean
     */
    boolean isStarted() ;


    /**
     * Send data.
     *
     * @param data the data
     */
    void sendData(ByteBuffer data) throws IOException;

    /**
     * Gets data.
     *
     * @return the data
     */
    ByteBuffer getData() throws IOException;

    /**
     * Do communication.
     */
    void doCommunication() throws IOException;
}
