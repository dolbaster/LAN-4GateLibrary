package org.lanter.lan4gate.Managers;

import org.lanter.lan4gate.Communication.ICommunication;
import org.lanter.lan4gate.Messages.Bridge.IBridge;

import java.io.IOException;

public interface IBridgeManager{

    void newMessage(IBridge message);
    void start() throws IOException;

    void closeAllConnections();

    void doManager();

    void setConnection(ICommunication connection);
}
