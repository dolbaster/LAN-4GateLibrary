package org.lanter.lan4gate.Implementation.Messages.Requests.Assembler;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.lanter.lan4gate.Messages.Fields.RequestFieldsList;
import org.lanter.lan4gate.Implementation.Messages.Fields.RootFields;
import org.lanter.lan4gate.Implementation.Messages.Fields.ClassFieldValuesList;
import org.lanter.lan4gate.Implementation.Messages.Requests.Request;
import org.lanter.lan4gate.STUB_LoggingTAG;

import java.util.Set;

public class JSONAssembler {
    Request mRequest;
    String mJsonString;
    public boolean assemble(Request request) {
        Log.i(STUB_LoggingTAG.getTag(), "Request assembler. Assemble");
        if(request != null && request.checkMandatoryFields()) {
            mRequest = request;
            JSONObject root = new JSONObject();
            createClassField(root);
            createObjectField(root);
            mJsonString = root.toString();
            Log.i(STUB_LoggingTAG.getTag(), "Request assembler. Assembled message = " + mJsonString);
            return !mJsonString.isEmpty();
        }
        return false;
    }
    public String getJson() {
        return mJsonString;
    }
    private void createClassField(JSONObject root) {

        try {
            root.put(RootFields.CLASS, ClassFieldValuesList.Request.getString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void createObjectField(JSONObject root) {
        JSONObject object = new JSONObject();
        addObjectFields(object);
        try {
            root.put(RootFields.OBJECT, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void addObjectFields(JSONObject object) {
        if(mRequest != null) {
            try {
                addFields(object, mRequest.getMandatoryFields());
                addFields(object, mRequest.getOptionalFields());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void addFields(JSONObject object, Set<RequestFieldsList> fields) throws JSONException {
        if(fields != null && object != null) {
            for (RequestFieldsList field : fields) {
                switch (field)
                {
                    case EcrNumber:{
                        object.put(field.getString(), mRequest.getEcrNumber());
                        break;
                    }
                    case EcrMerchantNumber:{
                        object.put(field.getString(), mRequest.getEcrMerchantNumber());
                        break;
                    }
                    case OperationCode:{
                        object.put(field.getString(), mRequest.getOperationCode().getNumber());
                        break;
                    }
                    case Amount:{
                        object.put(field.getString(), mRequest.getAmount());
                        break;
                    }
                    case PartialAmount: {
                        object.put(field.getString(), mRequest.getPartialAmount());
                        break;
                    }
                    case TipsAmount:{
                        object.put(field.getString(), mRequest.getTipsAmount());
                        break;
                    }
                    case CashbackAmount:{
                        object.put(field.getString(), mRequest.getCashbackAmount());
                        break;
                    }
                    case CurrencyCode:{
                        object.put(field.getString(), String.valueOf(mRequest.getCurrencyCode()));
                        break;
                    }
                    case RRN:{
                        object.put(field.getString(), mRequest.getRRN());
                        break;
                    }
                    case AuthCode:{
                        object.put(field.getString(), mRequest.getAuthCode());
                        break;
                    }
                    case ReceiptReference:{
                        object.put(field.getString(), mRequest.getReceiptReference());
                        break;
                    }
                    case TransactionID:{
                        object.put(field.getString(), mRequest.getTransactionID());
                        break;
                    }
                    case CardDataEnc:{
                        object.put(field.getString(), mRequest.getCardDataEnc());
                        break;
                    }
                    case OpenTags:{
                        object.put(field.getString(), mRequest.getOpenTags());
                        break;
                    }
                    case EncTags:{
                        object.put(field.getString(), mRequest.getEncTags());
                        break;
                    }
                    case ProviderCode:{
                        object.put(field.getString(), mRequest.getProviderCode());
                        break;
                    }
                    case AdditionalInfo:{
                        object.put(field.getString(), mRequest.getAdditionalInfo());
                        break;
                    }
                }
            }
        }
    }
}
