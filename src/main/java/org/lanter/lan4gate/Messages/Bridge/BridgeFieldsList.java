package org.lanter.lan4gate.Messages.Bridge;

import org.lanter.lan4gate.Messages.Request.RequestFieldsList;

import java.util.HashMap;
import java.util.Map;

public enum BridgeFieldsList {
    Command("Cmd"),
	LinkID("Lnk"),
	Data("Dt"),
	IP("IP"),
	Port("Port");

    private final String mValue;
    private static final Map<String, BridgeFieldsList> mStaticValuesMap = new HashMap<>();

    static {
        for(BridgeFieldsList field : BridgeFieldsList.values()) {
            mStaticValuesMap.put(field.mValue, field);
        }
    }

    BridgeFieldsList(String value) {
        mValue = value;
    }

    public String getString() { return mValue; }
    public static BridgeFieldsList getValue (String value) { return mStaticValuesMap.get(value); }
}
