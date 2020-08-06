package org.lanter.lan4gate.Messages.Bridge;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * The interface, provides communication redirection for LAN-4Tap software.
 * If LAN-4Tap is running on isolated hardware,
 */
public interface IBridge {

    /**
     * Gets mandatory fields.
     *
     * @return the mandatory fields
     */
    Set<BridgeFieldsList> getMandatoryFields();

    /**
     * Gets optional fields.
     *
     * @return the optional fields
     */
    Set<BridgeFieldsList> getOptionalFields();

    /**
     * Gets the list of inserted fields.
     *
     * @return The list of current fields
     */
    Set<BridgeFieldsList> getCurrentFields();

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
    int getLinkID();

    /**
     * Sets link.
     *
     * @param linkID the link id
     */
    void setLinkID(int linkID);

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

    /**
     * Gets status.
     *
     * @return the status
     */
    BridgeStatus getStatus();

    /**
     * Sets status.
     *
     * @param status the status
     */
    void setStatus(BridgeStatus status);
}
