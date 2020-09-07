package org.lanter.lan4gate;

import org.lanter.lan4gate.Messages.Request.IRequest;


public interface IRequestCallback {
    void newRequestMessage(IRequest request, Lan4Gate initiator);
}
