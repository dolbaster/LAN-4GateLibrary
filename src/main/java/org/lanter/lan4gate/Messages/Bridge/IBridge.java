package org.lanter.lan4gate.Messages.Bridge;

import java.nio.ByteBuffer;

/**
 * The interface, provides communication redirection for LAN-4Tap software.
 * If LAN-4Tap is running on isolated hardware,  
 */
public interface IBridge {
    /**
     * Gets command.
     *
     * @return the command
     */
    BridgeCommand getCommand();

    /**
     * Sets command.
     *
     * @param command the command
     */
    void setCommand(BridgeCommand command);

    /**
     * Gets link.
     *
     * @return the link
     */
    int getLink();

    /**
     * Sets link.
     *
     * @param linkID the link id
     */
    void setLink(int linkID);

    /**
     * Gets ip.
     *
     * @return the ip
     */
    String getIP();

    /**
     * Sets ip.
     *
     * @param ip the ip
     */
    void setIP(String ip);

    /**
     * Gets port.
     *
     * @return the port
     */
    int getPort();

    /**
     * Sets port.
     *
     * @param port the port
     */
    void setPort(int port);

    /**
     * Gets data.
     *
     * @return the data
     */
    ByteBuffer getData();

    /**
     * Sets data.
     *
     * @param data the data
     */
    void setData(ByteBuffer data);


}
