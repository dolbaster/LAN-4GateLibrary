package org.lanter.lan4gate.Implementation.MessageProcessor.Parser;

import android.util.Base64;

import org.json.JSONObject;
import org.lanter.lan4gate.Messages.Bridge.*;

import java.util.Set;

public class JSONBridgeParser {
    public static IBridge parse(JSONObject objectField) {
        IBridge result = null;
        IBridge bridge = createBridge(getCommand(objectField));
        if(bridge != null) {
            getFields(bridge.getMandatoryFields(), objectField, bridge);
            getFields(bridge.getOptionalFields(), objectField, bridge);

            if(bridge.checkMandatoryFields()) {
                result = bridge;
            }
        }
        return result;
    }
    private static IBridge createBridge(int operationCode) {
        BridgeFactory builder = new BridgeFactory();
        return builder.getBridge(BridgeCommand.getValue(operationCode));
    }

    private static int getCommand(JSONObject objectField) {
        return objectField.optInt(BridgeFieldsList.Command.getString(), 0);
    }

    private static void getFields(Set<BridgeFieldsList> fields, JSONObject objectField, IBridge bridge) {
        for (BridgeFieldsList field : fields) {
            if (objectField.has(field.getString())) {
                switch (field) {
                    case LinkID:
                        getLinkID(objectField, bridge);
                        break;
                    case Data:
                        getData(objectField, bridge);
                        break;
                    case IP:
                        getIP(objectField, bridge);
                        break;
                    case Port:
                        getPort(objectField, bridge);
                        break;
                    case Status:
                        getStatus(objectField, bridge);
                        break;
                }
            }
        }
    }

    private static void getLinkID(JSONObject object, IBridge bridge) {
        if(object != null && bridge != null) {
            int linkID = object.optInt(BridgeFieldsList.LinkID.getString(), -1);
            if(linkID != -1) {
                bridge.setLinkID(linkID);
            }
        }
    }

    private static void getIP(JSONObject object, IBridge bridge) {
        if(object != null && bridge != null) {
            String ip = object.optString(BridgeFieldsList.IP.getString());
            if(!ip.isEmpty()) {
                bridge.setIP(ip);
            }
        }
    }

    private static void getPort(JSONObject object, IBridge bridge) {
        if(object != null && bridge != null) {
            int port = object.optInt(BridgeFieldsList.Port.getString(), -1);
            if(port != -1) {
                bridge.setPort(port);
            }
        }
    }

    private static void getStatus(JSONObject object, IBridge bridge) {
        if(object != null && bridge != null) {
            int linkID = object.optInt(BridgeFieldsList.Status.getString(), -1);
            if(linkID != -1) {
                bridge.setStatus(BridgeStatus.getValue(linkID));
            }
        }
    }

    private static void getData(JSONObject object, IBridge bridge) {
        if(object != null && bridge != null) {
            String base64String = object.optString(BridgeFieldsList.Data.getString());
            if(!base64String.isEmpty()) {
                //In Java 7 Base64 encoder/decoder ia unavailable.
                //This is not uses only in Android.
                //Use apache codecs
                byte[] decodedData = Base64.decode(base64String, Base64.NO_WRAP);

                bridge.setData(decodedData);
            }
        }
    }
}
