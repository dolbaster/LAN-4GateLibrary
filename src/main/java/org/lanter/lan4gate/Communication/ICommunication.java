package org.lanter.lan4gate.Communication;

import java.io.IOException;
import java.nio.ByteBuffer;

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
