package org.lanter.lan4gate.Implementation.Messages.Responses.Operations.ServiceOperations;

import org.lanter.lan4gate.Messages.Response.ResponseFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Implementation.Messages.Responses.Response;

public class PrintCommsInfo extends Response {
    public PrintCommsInfo() {
        setOperationCode(OperationsList.PrintCommsInfo);
        addOptionalFields(ResponseFieldsList.AdditionalInfo);
    }
}
