package org.lanter.lan4gate.Implementation.Messages.Bridge.Operations;

import org.lanter.lan4gate.Implementation.Messages.Bridge.Bridge;
import org.lanter.lan4gate.Messages.Bridge.BridgeCommand;
import org.lanter.lan4gate.Messages.Bridge.BridgeFieldsList;

public class CheckStatus extends Bridge {
    public CheckStatus() {
        setCommand(BridgeCommand.CheckStatus);
        addOptionalFields(BridgeFieldsList.Status);
    }
}
