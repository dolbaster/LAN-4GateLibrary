package org.lanter.lan4gate.Communication;

import java.nio.ByteBuffer;

public interface ICommunicationListener {

    void newData(ByteBuffer data);

    void communicationStarted();

    void communicationStopped();

    void connected();

    void disconnected();

    void errorMessage(String error);

    void errorException(Exception exception);
}
