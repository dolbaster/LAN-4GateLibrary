package org.lanter.lan4gate.MessageProcessor.Builder;

import org.lanter.lan4gate.Messages.Bridge.IBridge;
import org.lanter.lan4gate.Messages.Notification.INotification;
import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Messages.Response.IResponse;

/**
 * The interface for building messages
 */
public interface IMessageBuilder {
    /**
     * Assemble message from {@link IRequest} to byte array
     *
     * @param request Filled object {@link IRequest}
     * @return byte array, contains message. In error - array is empty
     */
    byte[] buildMessage(IRequest request);

    /**
     * Assemble message from {@link IResponse} to byte array
     *
     * @param response Filled object {@link IResponse}
     * @return byte array, contains message. In error - array is empty
     */
    byte[] buildMessage(IResponse response);

    /**
     * Assemble message from {@link INotification} to byte array
     *
     * @param notification Filled object {@link INotification}
     * @return byte array, contains message. In error - array is empty
     */
    byte[] buildMessage(INotification notification);

    /**
     * Assemble message from {@link IBridge} to byte array
     *
     * @param bridge Filled object {@link IBridge}
     * @return byte array, contains message. In error - array is empty
     */
    byte[] buildMessage(IBridge bridge);
}
