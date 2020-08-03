package org.lanter.lan4gate.Implementation.Messages.Requests.Operations.ServiceOperations;

import org.lanter.lan4gate.Messages.Request.RequestFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Implementation.Messages.Requests.Request;

public class SetCurrentPrinter extends Request {
    public SetCurrentPrinter() {
        setOperationCode(OperationsList.SetCurrentPrinter);
        addMandatoryFields(RequestFieldsList.AdditionalInfo);
    }
}
