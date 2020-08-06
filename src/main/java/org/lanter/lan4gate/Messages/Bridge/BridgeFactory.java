package org.lanter.lan4gate.Messages.Bridge;

import org.lanter.lan4gate.Implementation.Messages.Bridge.Bridge;

public class BridgeFactory {
    public static IBridge getBridge(BridgeCommand command) {
        return new Bridge();
    }
}
