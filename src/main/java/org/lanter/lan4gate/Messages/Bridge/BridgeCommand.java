package org.lanter.lan4gate.Messages.Bridge;

import java.util.HashMap;
import java.util.Map;

public enum BridgeCommand {
    /**
     * Request for open communication at Address/Port
     */
    OpenConnection (1),
    /**
     * Close connection with ID
     */
    CloseConnection (2),
    /**
     * Exchange data with LAN-4Tap
     */
    DataExchange  (3),
    /**
     * Check link status
     */
    CheckStatus (4);

    private final int mOperationValue;
    private final static Map<Integer, BridgeCommand> mStaticValuesMap = new HashMap<>();

    static {
        for(BridgeCommand operation : BridgeCommand.values()) {
            mStaticValuesMap.put(operation.mOperationValue, operation);
        }
    }

    BridgeCommand(int value) {
        mOperationValue = value;
    }
    /**
     * Returns enum value which corresponds integer view
     *
     * @param value Card input method value
     *
     * @return Enum value, if value is correct. Null elsewhere
     */
    public static BridgeCommand getValue(int value) {
        return mStaticValuesMap.get(value);
    }
    /**
     * Returns integer value which corresponds current enum value
     *
     * @return Integer value of enum
     */
    public int getNumber() {
        return mOperationValue;
    }
}
