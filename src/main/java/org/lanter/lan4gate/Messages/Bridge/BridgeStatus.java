package org.lanter.lan4gate.Messages.Bridge;

import org.lanter.lan4gate.Messages.Response.StatusList;

import java.util.HashMap;
import java.util.Map;

public enum BridgeStatus {
    UnknownError(-1),

    Success(1),

    Error(2);

    private final int mOperationValue;
    private final static Map<Integer, BridgeStatus> mStaticValuesMap = new HashMap<>();

    static {
        for(BridgeStatus operation : BridgeStatus.values()) {
            mStaticValuesMap.put(operation.mOperationValue, operation);
        }
    }

    BridgeStatus(int value) {
        mOperationValue = value;
    }

    public static BridgeStatus getValue(int operationValue) {
        BridgeStatus value;
        if(mStaticValuesMap.containsKey(operationValue)) {
            value = mStaticValuesMap.get(operationValue);
        }
        else {
            value = UnknownError;
        }
        return value;
    }

    public int getNumber() {
        return mOperationValue;
    }
}
