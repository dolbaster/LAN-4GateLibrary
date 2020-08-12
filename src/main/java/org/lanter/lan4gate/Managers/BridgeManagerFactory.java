package org.lanter.lan4gate.Managers;

import org.lanter.lan4gate.Implementation.Managers.BridgeManager;

public class BridgeManagerFactory {
    public static IBridgeManager getManager() {
        return new BridgeManager();
    }
}
