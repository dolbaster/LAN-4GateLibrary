package org.lanter.lan4gate.Communication;

import java.io.IOException;

/**
 * The interface Communication.
 */
public interface ICommunication {
    /**
     * Open communication.
     */
    void openCommunication() throws IOException;

    void closeCommunication() throws IOException;
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
     * Send data.
     *
     * @param data the data
     */
    void sendData(byte[] data) throws IOException;

    /**
     * Gets data.
     *
     * @return the data
     */
    byte[] getData() throws IOException;

    /**
     * Do communication.
     */
    void doCommunication() throws IOException;
}
