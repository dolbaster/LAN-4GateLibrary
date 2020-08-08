package org.lanter.lan4gate.Implementation.MessageProcessor.Builder;

import org.json.JSONObject;
import org.lanter.lan4gate.MessageProcessor.Builder.IMessageBuilder;
import org.lanter.lan4gate.Messages.Bridge.IBridge;
import org.lanter.lan4gate.Messages.Notification.INotification;
import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Messages.Response.IResponse;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.RootFields;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.ClassFieldValuesList;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class JSONMessageBuilder implements IMessageBuilder {

    @Override
    public ByteBuffer buildMessage(IRequest request) {
        ByteBuffer result = null;
        if(request != null && request.checkMandatoryFields()) {
            JSONObject root = new JSONObject();
            if(createClassField(root, ClassFieldValuesList.Request) &&  createObjectField(root, request)) {
                result = convertToByteBuffer(root.toString());
            }
        }
        return result;
    }

    @Override
    public ByteBuffer buildMessage(IResponse response) {
        ByteBuffer result = null;
        /*if(response != null && response.checkMandatoryFields()) {
            JSONObject root = new JSONObject();
            if(createClassField(root, ClassFieldValuesList.Response) &&  createObjectField(root, response)) {
                result = root.toString();
            }
        }*/
        return result;
    }

    @Override
    public ByteBuffer buildMessage(INotification notification) {
        return null;
    }

    @Override
    public ByteBuffer buildMessage(IBridge bridge) {
        ByteBuffer result = null;
        if(bridge != null && bridge.checkMandatoryFields()) {
            JSONObject root = new JSONObject();
            if(createClassField(root, ClassFieldValuesList.Bridge) &&  createObjectField(root, bridge)) {
                result = convertToByteBuffer(root.toString());
            }
        }
        return result;
    }

    private boolean createClassField(JSONObject root, final ClassFieldValuesList type) {
        root.put(RootFields.CLASS, type.getString());
        return !root.isEmpty();
    }

    private boolean createObjectField(JSONObject root, IRequest request) {
        return JSONRequestBuilder.createObject(root, request);
    }


    private boolean createObjectField(JSONObject root, IResponse response) {
        //JSONReBuilder.createRequestObject(root, request);
        return false;
    }

    private boolean createObjectField(JSONObject root, IBridge bridge) {
        return JSONBridgeBuilder.createObject(root, bridge);
    }

    private ByteBuffer convertToByteBuffer(String message){
        return StandardCharsets.UTF_8.encode(message).slice();
    }
}
