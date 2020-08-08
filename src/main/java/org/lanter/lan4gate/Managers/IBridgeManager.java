package org.lanter.lan4gate.Managers;

import java.nio.ByteBuffer;

public interface IBridgeManager extends Runnable{
    void closeAllConnections();
    void stop();
    ByteBuffer getMessage();
    int messageCount();
    void addListener();
}
