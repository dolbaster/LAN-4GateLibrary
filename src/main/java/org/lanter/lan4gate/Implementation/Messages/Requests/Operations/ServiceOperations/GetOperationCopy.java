package org.lanter.lan4gate.Implementation.Messages.Requests.Operations.ServiceOperations;

import org.lanter.lan4gate.Implementation.Messages.Requests.Request;
import org.lanter.lan4gate.Messages.Request.RequestFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;

public class GetOperationCopy extends Request {
    public GetOperationCopy() {
        setOperationCode(OperationsList.GetOperationCopy);
        addOptionalFields(RequestFieldsList.ReceiptReference);
        addOptionalFields(RequestFieldsList.EcrMerchantNumber);
    }
}