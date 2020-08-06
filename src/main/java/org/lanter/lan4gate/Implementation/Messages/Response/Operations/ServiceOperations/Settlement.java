package org.lanter.lan4gate.Implementation.Messages.Response.Operations.ServiceOperations;

import org.lanter.lan4gate.Messages.Response.ResponseFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Implementation.Messages.Response.Response;
import org.lanter.lan4gate.Implementation.Messages.Response.ResponseBuilder;

public class Settlement extends Response {
    public Settlement() {
        setOperationCode(OperationsList.Settlement);
        addOptionalFields(ResponseFieldsList.EcrMerchantNumber);

        ResponseBuilder builder = new ResponseBuilder();
        Response operation = builder.prepareResponse(OperationsList.PrintDetailReport);
        if(operation != null) {
            addMandatoryFieldsGroup(operation.getMandatoryFields());
            addOptionalFieldsGroup(operation.getOptionalFields());
        }
    }
}
