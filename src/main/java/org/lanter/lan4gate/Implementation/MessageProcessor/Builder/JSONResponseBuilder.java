package org.lanter.lan4gate.Implementation.MessageProcessor.Builder;

import org.json.JSONException;
import org.json.JSONObject;
import org.lanter.lan4gate.Implementation.MessageProcessor.Fields.RootFields;
import org.lanter.lan4gate.Messages.Response.IResponse;
import org.lanter.lan4gate.Messages.Response.ResponseFieldsList;

import java.util.Set;

public class JSONResponseBuilder {
    public static boolean createObject(JSONObject root, IResponse response) throws JSONException {
        JSONObject object = new JSONObject();
        addObjectFields(object, response);

        boolean result = object.length() > 0;
        if(result) {
            root.put(RootFields.OBJECT, object);
        }
        return result;
    }

    private static void addObjectFields(JSONObject object, IResponse response) throws JSONException {
        if(response != null) {
            addFields(object, response.getMandatoryFields(), response);
            addFields(object, response.getOptionalFields(), response);
        }
    }
    private static void addFields(JSONObject object, final Set<ResponseFieldsList> fields, IResponse response) throws JSONException {
        if(fields != null && object != null) {
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
                    case OriginalOperationCode:
                        object.put(field.getString(), response.getOriginalOperationCode().getNumber());
                        break;
                    case TotalAmount:
                        object.put(field.getString(), response.getTotalAmount());
                        break;
                    case PartialAmount:
                        object.put(field.getString(), response.getPartialAmount());
                        break;
                    case AcquirerFeeAmount:
                        object.put(field.getString(), response.getAcquirerFeeAmount());
                        break;
                    case TerminalFeeAmount:
                        object.put(field.getString(), response.getTerminalFeeAmount());
                        break;
                    case TipsAmount:
                        object.put(field.getString(), response.getTipsAmount());
                        break;
                    case CurrencyCode:
                        object.put(field.getString(), response.getCurrencyCode());
                        break;
                    case ReceiptReference:
                        object.put(field.getString(), response.getReceiptReference());
                        break;
                    case RRN:
                        object.put(field.getString(), response.getRRN());
                        break;
                    case Status:
                        object.put(field.getString(), response.getStatus().getNumber());
                        break;
                    case OriginalOperationStatus:
                        object.put(field.getString(), response.getOriginalOperationStatus().getNumber());
                        break;
                    case TransDateTime:
                        object.put(field.getString(), response.getTransDateTime());
                        break;
                    case TerminalDateTime:
                        object.put(field.getString(), response.getTerminalDateTime());
                        break;
                    case CardPAN:
                        object.put(field.getString(), response.getCardPAN());
                        break;
                    case ExpireDate:
                        object.put(field.getString(), response.getExpireDate());
                        break;
                    case CardholderName:
                        object.put(field.getString(), response.getCardholderName());
                        break;
                    case CardholderAuthMethod:
                        object.put(field.getString(), response.getCardholderAuthMethod().getNumber());
                        break;
                    case AuthCode:
                        object.put(field.getString(), response.getAuthCode());
                        break;
                    case ResponseCode:
                        object.put(field.getString(), response.getResponseCode());
                        break;
                    case ResponseText:
                        object.put(field.getString(), response.getResponseText());
                        break;
                    case STAN:object.put(field.getString(), response.getSTAN());
                        break;
                    case TransactionID:
                        object.put(field.getString(), response.getTransactionID());
                        break;
                    case TerminalID:
                        object.put(field.getString(), response.getTerminalID());
                        break;
                    case CardEmvAid:
                        object.put(field.getString(), response.getCardEmvAid());
                        break;
                    case CardAppName:
                        object.put(field.getString(), response.getCardAppName());
                        break;
                    case CardInputMethod:
                        object.put(field.getString(), response.getCardInputMethod().getNumber());
                        break;
                    case IssuerName:
                        object.put(field.getString(), response.getIssuerName());
                        break;
                    case AdditionalInfo:
                        object.put(field.getString(), response.getAdditionalInfo());
                        break;
                    case CardData:
                        object.put(field.getString(), response.getCardData());
                        break;
                    case CardDataEnc:
                        object.put(field.getString(), response.getCardDataEnc());
                        break;
                    case MerchantId:
                        object.put(field.getString(), response.getMerchantId());
                        break;
                    case TVR:
                        object.put(field.getString(), response.getTVR());
                        break;
                    case TSI:
                        object.put(field.getString(), response.getTSI());
                        break;
                    case TC:
                        object.put(field.getString(), response.getTC());
                        break;
                    case CID:
                        object.put(field.getString(), response.getCID());
                        break;
                    case KVR:
                        object.put(field.getString(), response.getKVR());
                        break;
                    case CDAResult:
                        object.put(field.getString(), response.getCDAResult());
                        break;
                    case SalesCount:
                        object.put(field.getString(), response.getSalesCount());
                        break;
                    case VoidCount:
                        object.put(field.getString(), response.getVoidCount());
                        break;
                    case RefundCount:
                        object.put(field.getString(), response.getRefundCount());
                        break;
                    case SalesArray:
                        break;
                    case VoidArray:
                        break;
                    case RefundArray:
                        break;
                    case CardPANHash:
                        object.put(field.getString(), response.getCardPANHash());
                        break;
                    case ReceiptLine1:
                        object.put(field.getString(), response.getReceiptLine1());
                        break;
                    case ReceiptLine2:
                        object.put(field.getString(), response.getReceiptLine2());
                        break;
                    case ReceiptLine3:
                        object.put(field.getString(), response.getReceiptLine3());
                        break;
                    case ReceiptLine4:
                        object.put(field.getString(), response.getReceiptLine4());
                        break;
                    case ReceiptLine5:
                        object.put(field.getString(), response.getReceiptLine5());
                        break;
                }
            }
        }


    }
}
