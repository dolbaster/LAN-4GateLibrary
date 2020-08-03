package org.lanter.lan4gate.MessageProcessor.Builder;

import org.json.JSONObject;
import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Messages.Response.IResponse;
import org.lanter.lan4gate.Implementation.Messages.Fields.RootFields;
import org.lanter.lan4gate.Implementation.Messages.Fields.ClassFieldValuesList;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

class JSONMessageBuilder implements IMessageBuilder {

    @Override
    public ByteBuffer buildMessage(IRequest request) {
        ByteBuffer result = null;
        if(request != null && request.checkMandatoryFields()) {
            JSONObject root = new JSONObject();
            if(createClassField(root, ClassFieldValuesList.Request) &&  createObjectField(root, request)) {

                result = StandardCharsets.UTF_8.encode(root.toString());
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

    private boolean createClassField(JSONObject root, final ClassFieldValuesList type) {
        root.put(RootFields.CLASS, type.getString());
        return !root.isEmpty();
    }

    private boolean createObjectField(JSONObject root, IRequest request) {
        return JSONRequestBuilder.createRequestObject(root, request);
    }

    private boolean createObjectField(JSONObject root, IResponse response) {
        //JSONReBuilder.createRequestObject(root, request);
        return false;
    }
}
