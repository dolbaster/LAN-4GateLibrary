package org.lanter.lan4gate.MessageProcessor.Builder;

import org.json.JSONObject;
import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Implementation.Messages.Fields.RootFields;
import org.lanter.lan4gate.Messages.Request.RequestFieldsList;

import java.util.Set;

class JSONRequestBuilder {
    public static boolean createRequestObject(JSONObject root, IRequest request) {
        JSONObject object = new JSONObject();
        addObjectFields(object, request);

        boolean result = !object.isEmpty();
        if(result) {
            root.put(RootFields.OBJECT, object);
        }
        return result;
    }

    private static void addObjectFields(JSONObject object, IRequest request) {
        if(request != null) {
            addFields(object, request.getMandatoryFields(), request);
            addFields(object, request.getOptionalFields(), request);
        }
    }
    private static void addFields(JSONObject object, final Set<RequestFieldsList> fields, IRequest request) {
        if(fields != null && object != null) {
            for (RequestFieldsList field : fields) {
                switch (field)
                {
                    case EcrNumber:{
                        object.put(field.getString(), request.getEcrNumber());
                        break;
                    }
                    case EcrMerchantNumber:{
                        object.put(field.getString(), request.getEcrMerchantNumber());
                        break;
                    }
                    case OperationCode:{
                        object.put(field.getString(), request.getOperationCode().getNumber());
                        break;
                    }
                    case Amount:{
                        object.put(field.getString(), request.getAmount());
                        break;
                    }
                    case PartialAmount: {
                        object.put(field.getString(), request.getPartialAmount());
                        break;
                    }
                    case TipsAmount:{
                        object.put(field.getString(), request.getTipsAmount());
                        break;
                    }
                    case CashbackAmount:{
                        object.put(field.getString(), request.getCashbackAmount());
                        break;
                    }
                    case CurrencyCode:{
                        object.put(field.getString(), String.valueOf(request.getCurrencyCode()));
                        break;
                    }
                    case RRN:{
                        object.put(field.getString(), request.getRRN());
                        break;
                    }
                    case AuthCode:{
                        object.put(field.getString(), request.getAuthCode());
                        break;
                    }
                    case ReceiptReference:{
                        object.put(field.getString(), request.getReceiptReference());
                        break;
                    }
                    case TransactionID:{
                        object.put(field.getString(), request.getTransactionID());
                        break;
                    }
                    case CardDataEnc:{
                        object.put(field.getString(), request.getCardDataEnc());
                        break;
                    }
                    case OpenTags:{
                        object.put(field.getString(), request.getOpenTags());
                        break;
                    }
                    case EncTags:{
                        object.put(field.getString(), request.getEncTags());
                        break;
                    }
                    case ProviderCode:{
                        object.put(field.getString(), request.getProviderCode());
                        break;
                    }
                    case AdditionalInfo:{
                        object.put(field.getString(), request.getAdditionalInfo());
                        break;
                    }
                }
            }
        }
    }
}
