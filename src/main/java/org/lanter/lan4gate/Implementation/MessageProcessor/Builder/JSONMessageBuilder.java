package org.lanter.lan4gate.Implementation.MessageProcessor.Builder;

import org.json.JSONException;
import org.json.JSONObject;
import org.lanter.lan4gate.MessageProcessor.Builder.IMessageBuilder;
import org.lanter.lan4gate.Messages.Bridge.IBridge;
import org.lanter.lan4gate.Messages.Notification.INotification;
import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Messages.Response.IResponse;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.RootFields;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.ClassFieldValuesList;

import java.nio.charset.StandardCharsets;

public class JSONMessageBuilder implements IMessageBuilder {

    @Override
    public byte[] buildMessage(IRequest request) {
        byte[] result = null;
        try {
            if (request != null && request.checkMandatoryFields()) {
                JSONObject root = new JSONObject();
                if (createClassField(root, ClassFieldValuesList.Request) && createObjectField(root, request)) {
                    result = convertToByteArray(root.toString());
                }
            }
        } catch (JSONException ignored) {
            //TODO add reaction
        }
        return result;
    }

    @Override
    public byte[] buildMessage(IResponse response) {
        byte[] result = null;
        try {
            if (response != null && response.checkMandatoryFields()) {
                JSONObject root = new JSONObject();
                if (createClassField(root, ClassFieldValuesList.Response) && createObjectField(root, response)) {
                    result = convertToByteArray(root.toString());
                }
            }
        } catch (JSONException ignored) {}
        return result;
    }

    @Override
    public byte[] buildMessage(INotification notification) {
        return null;
    }

    @Override
    public byte[] buildMessage(IBridge bridge) {
        byte[] result = null;
        try {
            if (bridge != null && bridge.checkMandatoryFields()) {
                JSONObject root = new JSONObject();
                if (createClassField(root, ClassFieldValuesList.Bridge) && createObjectField(root, bridge)) {
                    result = convertToByteArray(root.toString());
                }
            }
        } catch (JSONException ignored) {
            //TODO add reaction
        }
        return result;
    }

    private boolean createClassField(JSONObject root, final ClassFieldValuesList type) throws JSONException {
        root.put(RootFields.CLASS, type.getString());

        return root.length() > 0;
    }

    private boolean createObjectField(JSONObject root, IRequest request) throws JSONException {
        return JSONRequestBuilder.createObject(root, request);
    }


    private boolean createObjectField(JSONObject root, IResponse response) throws JSONException {
        return JSONResponseBuilder.createObject(root, response);
    }

    private boolean createObjectField(JSONObject root, IBridge bridge) throws JSONException {
        return JSONBridgeBuilder.createObject(root, bridge);
    }

    private byte[] convertToByteArray(String message) {
        return message.getBytes(StandardCharsets.UTF_8);
    }
}
