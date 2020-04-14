package org.lanter.lan4gate.Messages.Responses.Operations.ServiceOperations;

import org.lanter.lan4gate.Messages.Fields.ResponseFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Messages.Responses.Response;

public class GetCurrentPrinter extends Response {
    public GetCurrentPrinter() {
        setOperationCode(OperationsList.GetCurrentPrinter);
        addMandatoryFields(ResponseFieldsList.AdditionalInfo);
    }
}