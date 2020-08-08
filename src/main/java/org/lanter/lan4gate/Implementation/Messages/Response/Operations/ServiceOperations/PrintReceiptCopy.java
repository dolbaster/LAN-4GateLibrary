package org.lanter.lan4gate.Implementation.Messages.Response.Operations.ServiceOperations;

import org.lanter.lan4gate.Messages.Response.IResponse;
import org.lanter.lan4gate.Messages.Response.ResponseFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Implementation.Messages.Response.Response;
import org.lanter.lan4gate.Messages.Response.ResponseFactory;

public class PrintReceiptCopy extends Response {
    public PrintReceiptCopy(OperationsList operationCode) {
        setOperationCode(OperationsList.PrintReceiptCopy);
        addOptionalFields(ResponseFieldsList.EcrMerchantNumber);
        addOptionalFields(ResponseFieldsList.OriginalOperationCode);
        addOptionalFields(ResponseFieldsList.OriginalOperationStatus);

        //Так как неизвестна операция, то необходимо забрать поля из используемой
        if(!operationCode.equals(OperationsList.PrintReceiptCopy)) {
            ResponseFactory builder = new ResponseFactory();
            IResponse operation = builder.getResponse(operationCode);
            if(operation != null) {
                addMandatoryFieldsGroup(operation.getMandatoryFields());
                addOptionalFieldsGroup(operation.getOptionalFields());
            }
        }
    }
}
