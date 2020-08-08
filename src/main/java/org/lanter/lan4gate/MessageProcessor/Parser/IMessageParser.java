package org.lanter.lan4gate.MessageProcessor.Parser;

import org.lanter.lan4gate.Messages.Bridge.IBridge;
import org.lanter.lan4gate.Messages.Notification.INotification;
import org.lanter.lan4gate.Messages.Response.IResponse;

/**
 * The interface Message parser.
 */
public interface IMessageParser {

    /**
     * Gets response.
     *
     * @return the response
     */
    IResponse getResponse();

    /**
     * Gets response count.
     *
     * @return the response count
     */
    int getResponseCount();

    /**
     * Gets notification.
     *
     * @return the notification
     */
    INotification getNotification();

    /**
     * Gets notification count.
     *
     * @return the notification count
     */
    int getNotificationCount();

    /**
     * Gets bridge.
     *
     * @return the bridge
     */
    IBridge getBridge();

    /**
     * Gets bridge count.
     *
     * @return the bridge count
     */
    int getBridgeCount();

    /**
     * Parse message type.
     *
     * @param json the json
     * @return the message type
     */
    MessageType parse(String json);
}
