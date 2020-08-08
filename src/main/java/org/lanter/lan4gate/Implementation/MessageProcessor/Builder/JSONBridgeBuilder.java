package org.lanter.lan4gate.Implementation.MessageProcessor.Builder;

import org.json.JSONObject;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.RootFields;
import org.lanter.lan4gate.Messages.Bridge.IBridge;
import org.lanter.lan4gate.Messages.Bridge.BridgeFieldsList;

import java.util.Set;
import org.apache.commons.codec.binary.Base64;

public class JSONBridgeBuilder {
    public static boolean createObject(JSONObject root, IBridge bridge) {
        JSONObject object = new JSONObject();
        addObjectFields(object, bridge);

        boolean result = !object.isEmpty();
        if(result) {
            root.put(RootFields.OBJECT, object);
        }
        return result;
    }

    private static void addObjectFields(JSONObject object, IBridge bridge) {
        if(bridge != null) {
            addFields(object, bridge.getMandatoryFields(), bridge);
            addFields(object, bridge.getOptionalFields(), bridge);
        }
    }
    private static void addFields(JSONObject object, final Set<BridgeFieldsList> fields, IBridge bridge) {
        if(fields != null && object != null) {
            for (BridgeFieldsList field : fields) {
                switch (field)
                {
                    case Command:
                        object.put(field.getString(), bridge.getCommand().getNumber());
                        break;
                    case LinkID:
                        object.put(field.getString(), bridge.getLinkID());
                        break;
                    case Data:
                        //In Java 7 Base64 encoder/decoder ia unavailable.
                        //This is not uses only in Android.
                        //Use apache codecs
                        String encoded = org.apache.commons.codec.binary.Base64.encodeBase64String(bridge.getData().array());
                        object.put(field.getString(), encoded);
                        break;
                    case IP:
                        object.put(field.getString(), bridge.getIP());
                        break;
                    case Port:
                        object.put(field.getString(), bridge.getPort());
                        break;
                    case Status:
                        object.put(field.getString(), bridge.getStatus().getNumber());
                        break;
                }
            }
        }
    }
}
