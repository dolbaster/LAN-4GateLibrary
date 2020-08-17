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
                    case EcrNumber:
                        break;
                    case EcrMerchantNumber:
                        break;
                    case OperationCode:
                        break;
                    case OriginalOperationCode:
                        break;
                    case TotalAmount:
                        break;
                    case PartialAmount:
                        break;
                    case AcquirerFeeAmount:
                        break;
                    case TerminalFeeAmount:
                        break;
                    case TipsAmount:
                        break;
                    case CurrencyCode:
                        break;
                    case ReceiptReference:
                        break;
                    case RRN:
                        break;
                    case Status:
                        break;
                    case OriginalOperationStatus:
                        break;
                    case TransDateTime:
                        break;
                    case TerminalDateTime:
                        break;
                    case CardPAN:
                        break;
                    case ExpireDate:
                        break;
                    case CardholderName:
                        break;
                    case CardholderAuthMethod:
                        break;
                    case AuthCode:
                        break;
                    case ResponseCode:
                        break;
                    case ResponseText:
                        break;
                    case STAN:
                        break;
                    case TransactionID:
                        break;
                    case TerminalID:
                        break;
                    case CardEmvAid:
                        break;
                    case CardAppName:
                        break;
                    case CardInputMethod:
                        break;
                    case IssuerName:
                        break;
                    case AdditionalInfo:
                        break;
                    case CardData:
                        break;
                    case CardDataEnc:
                        break;
                    case MerchantId:
                        break;
                    case TVR:
                        break;
                    case TSI:
                        break;
                    case TC:
                        break;
                    case CID:
                        break;
                    case KVR:
                        break;
                    case CDAResult:
                        break;
                    case SalesCount:
                        break;
                    case VoidCount:
                        break;
                    case RefundCount:
                        break;
                    case SalesArray:
                        break;
                    case VoidArray:
                        break;
                    case RefundArray:
                        break;
                    case CardPANHash:
                        break;
                    case ReceiptLine1:
                        break;
                    case ReceiptLine2:
                        break;
                    case ReceiptLine3:
                        break;
                    case ReceiptLine4:
                        break;
                    case ReceiptLine5:
                        break;
                }
            }
        }


    }
}
