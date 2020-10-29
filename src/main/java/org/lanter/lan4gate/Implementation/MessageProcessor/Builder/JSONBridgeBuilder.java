package org.lanter.lan4gate.Implementation.MessageProcessor.Builder;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.RootFields;
import org.lanter.lan4gate.Messages.Bridge.IBridge;
import org.lanter.lan4gate.Messages.Bridge.BridgeFieldsList;

import java.util.Set;

public class JSONBridgeBuilder {
    public static boolean createObject(JSONObject root, IBridge bridge) throws JSONException {
        JSONObject object = new JSONObject();
        addObjectFields(object, bridge);

        boolean result = object.length() > 0;
        if(result) {
            root.put(RootFields.OBJECT, object);
        }
        return result;
    }

    private static void addObjectFields(JSONObject object, IBridge bridge) throws JSONException {
        if(bridge != null) {
            addFields(object, bridge.getMandatoryFields(), bridge);
            addFields(object, bridge.getOptionalFields(), bridge);
        }
    }
    private static void addFields(JSONObject object, final Set<BridgeFieldsList> fields, IBridge bridge) throws JSONException {
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
                        String encoded = Base64.encodeToString(bridge.getData(), Base64.NO_WRAP);
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
