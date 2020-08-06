package org.lanter.lan4gate.Implementation.Messages.Response.Operations.ServiceOperations;

import org.lanter.lan4gate.Messages.Response.ResponseFieldsList;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Implementation.Messages.Response.Response;
import org.lanter.lan4gate.Implementation.Messages.Response.ResponseBuilder;

public class GetLastOperation extends Response {
   public GetLastOperation(OperationsList operationCode) {
       setOperationCode(OperationsList.GetLastOperation);
       addOptionalFields(ResponseFieldsList.EcrMerchantNumber);
       addOptionalFields(ResponseFieldsList.OriginalOperationCode);
       addOptionalFields(ResponseFieldsList.OriginalOperationStatus);

       //Так как неизвестна операция, то необходимо забрать поля из используемой
       if (!operationCode.equals(OperationsList.GetLastOperation)) {
           ResponseBuilder builder = new ResponseBuilder();
           Response operation = builder.prepareResponse(operationCode);
           if (operation != null) {
               addMandatoryFieldsGroup(operation.getMandatoryFields());
               addOptionalFieldsGroup(operation.getOptionalFields());
           }
       }
   }
}
