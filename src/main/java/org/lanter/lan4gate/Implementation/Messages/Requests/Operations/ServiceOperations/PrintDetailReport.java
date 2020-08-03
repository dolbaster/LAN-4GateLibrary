package org.lanter.lan4gate.Implementation.Messages.Requests.Operations.ServiceOperations;

import org.lanter.lan4gate.Messages.Request.RequestFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Implementation.Messages.Requests.Request;

public class PrintDetailReport extends Request {
    public PrintDetailReport() {
        setOperationCode(OperationsList.PrintDetailReport);
        addOptionalFields(RequestFieldsList.EcrMerchantNumber);
    }
}
