package org.lanter.lan4gate.MessageProcessor.Builder;

import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Messages.Response.IResponse;

import java.nio.ByteBuffer;

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
    ByteBuffer buildMessage(IRequest request);

    /**
     * Assemble message from {@link IResponse} to byte array
     *
     * @param request Filled object {@link IResponse}
     * @return byte array, contains message. In error - array is empty
     */
    ByteBuffer buildMessage(IResponse request);
}
