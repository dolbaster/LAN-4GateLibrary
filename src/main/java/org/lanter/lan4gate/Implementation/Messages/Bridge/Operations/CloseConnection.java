package org.lanter.lan4gate.Implementation.Messages.Bridge.Operations;

import org.lanter.lan4gate.Implementation.Messages.Bridge.Bridge;
import org.lanter.lan4gate.Messages.Bridge.BridgeCommand;
import org.lanter.lan4gate.Messages.Bridge.BridgeFieldsList;

public class CloseConnection extends Bridge {
    public CloseConnection() {
        setCommand(BridgeCommand.CloseConnection);
        addOptionalFields(BridgeFieldsList.Status);
    }
}
