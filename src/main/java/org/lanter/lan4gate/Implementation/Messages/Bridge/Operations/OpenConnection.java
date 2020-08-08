package org.lanter.lan4gate.Implementation.Messages.Bridge.Operations;

import org.lanter.lan4gate.Implementation.Messages.Bridge.Bridge;
import org.lanter.lan4gate.Messages.Bridge.BridgeFieldsList;

public class OpenConnection extends Bridge {
    public OpenConnection() {
        addMandatoryFields(BridgeFieldsList.IP);
        addMandatoryFields(BridgeFieldsList.Port);
    }
}
