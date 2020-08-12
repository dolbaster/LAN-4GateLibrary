package org.lanter.lan4gate.Messages.Bridge;

import org.lanter.lan4gate.Implementation.Messages.Bridge.Bridge;
import org.lanter.lan4gate.Implementation.Messages.Bridge.Operations.CheckStatus;
import org.lanter.lan4gate.Implementation.Messages.Bridge.Operations.CloseConnection;
import org.lanter.lan4gate.Implementation.Messages.Bridge.Operations.DataExchange;
import org.lanter.lan4gate.Implementation.Messages.Bridge.Operations.OpenConnection;
import org.lanter.lan4gate.Implementation.Messages.Requests.Request;

public class BridgeFactory {
    public static IBridge getBridge(BridgeCommand command) {
        IBridge result = null;
        switch (command) {
            case OpenConnection:
                result = new OpenConnection();
                break;
            case CloseConnection:
                result = new CloseConnection();
                break;
            case DataExchange:
                result = new DataExchange();
                break;
            case CheckStatus:
                result = new CheckStatus();
                break;
        }
        return result;
    }
}
