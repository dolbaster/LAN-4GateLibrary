package org.lanter.lan4gate.Implementation.MessageProcessor.Builder;

import org.json.JSONObject;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.RootFields;
import org.lanter.lan4gate.Messages.Response.IResponse;
import org.lanter.lan4gate.Messages.Response.ResponseFieldsList;

import java.util.Set;

public class JSONResponseBuilder {
    public static boolean createObject(JSONObject root, IResponse response) {
        JSONObject object = new JSONObject();
        addObjectFields(object, response);

        boolean result = !object.isEmpty();
        if(result) {
            root.put(RootFields.OBJECT, object);
        }
        return result;
    }

    private static void addObjectFields(JSONObject object, IResponse response) {
        if(response != null) {
            addFields(object, response.getMandatoryFields(), response);
            addFields(object, response.getOptionalFields(), response);
        }
    }
    private static void addFields(JSONObject object, final Set<ResponseFieldsList> fields, IResponse response) {
        /*if(fields != null && object != null) {
            for (ResponseFieldsList field : fields) {
                switch (field)
                {
                    case EcrNumber:{
                        object.put(field.getString(), response.getEcrNumber());
                        break;
                    }
                    case EcrMerchantNumber:{
                        object.put(field.getString(), response.getEcrMerchantNumber());
                        break;
                    }
                    case OperationCode:{
                        object.put(field.getString(), response.getOperationCode().getNumber());
                        break;
                    }
                    case Amount:{
                        object.put(field.getString(), response.getAmount());
                        break;
                    }
                    case PartialAmount: {
                        object.put(field.getString(), response.getPartialAmount());
                        break;
                    }
                    case TipsAmount:{
                        object.put(field.getString(), response.getTipsAmount());
                        break;
                    }
                    case CashbackAmount:{
                        object.put(field.getString(), response.getCashbackAmount());
                        break;
                    }
                    case CurrencyCode:{
                        object.put(field.getString(), String.valueOf(response.getCurrencyCode()));
                        break;
                    }
                    case RRN:{
                        object.put(field.getString(), response.getRRN());
                        break;
                    }
                    case AuthCode:{
                        object.put(field.getString(), response.getAuthCode());
                        break;
                    }
                    case ReceiptReference:{
                        object.put(field.getString(), response.getReceiptReference());
                        break;
                    }
                    case TransactionID:{
                        object.put(field.getString(), response.getTransactionID());
                        break;
                    }
                    case CardDataEnc:{
                        object.put(field.getString(), response.getCardDataEnc());
                        break;
                    }
                    case OpenTags:{
                        object.put(field.getString(), response.getOpenTags());
                        break;
                    }
                    case EncTags:{
                        object.put(field.getString(), response.getEncTags());
                        break;
                    }
                    case ProviderCode:{
                        object.put(field.getString(), response.getProviderCode());
                        break;
                    }
                    case AdditionalInfo:{
                        object.put(field.getString(), response.getAdditionalInfo());
                        break;
                    }
                }
            }
        }*/
    }
}
