package org.lanter.lan4gate.Implementation.MessageProcessor.Parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.lanter.lan4gate.Messages.OperationsList;
import org.lanter.lan4gate.Messages.Request.IRequest;
import org.lanter.lan4gate.Messages.Request.RequestFactory;
import org.lanter.lan4gate.Messages.Request.RequestFieldsList;

import java.util.Set;

public class JSONRequestParser {
    public static IRequest parse(JSONObject objectField) {
        IRequest result = null;
        IRequest request = createRequest(getOperationCode(objectField));
        if(request != null) {
            getFields(request.getMandatoryFields(), objectField, request);
            getFields(request.getOptionalFields(), objectField, request);

            if(request.checkMandatoryFields()) {
                result = request;
            }
        }
        return result;
    }
    private static IRequest createRequest(int operationCode) {
        return RequestFactory.getRequest(OperationsList.getValue(operationCode));
    }

    private static int getOperationCode(JSONObject objectField) {
        return objectField.optInt(RequestFieldsList.OperationCode.getString(), 0);
    }
    private static void getFields(Set<RequestFieldsList> fields, JSONObject objectField, IRequest requestObject) {
        for(RequestFieldsList field : fields ) {
            if(objectField.has(field.getString())) {
                switch (field) {
                    case EcrNumber: {
                        getFieldEcrNumber(objectField, requestObject);
                        break;
                    }
                    case EcrMerchantNumber: {
                        getFieldEcrMerchantNumber(objectField, requestObject);
                        break;
                    }
                    case Amount:
                        getFieldAmount(objectField, requestObject);
                        break;
                    case PartialAmount: {
                        getFieldPartialAmount(objectField, requestObject);
                        break;
                    }
                    case TipsAmount: {
                        getFieldTipsAmount(objectField, requestObject);
                        break;
                    }
                    case CashbackAmount:
                        break;
                    case CurrencyCode: {
                        getFieldCurrencyCode(objectField, requestObject);
                        break;
                    }
                    case ReceiptReference: {
                        getFieldReceiptReference(objectField, requestObject);
                        break;
                    }
                    case RRN : {
                        getFieldRRN (objectField, requestObject);
                        break;
                    }
                    case AuthCode: {
                        getFieldAuthCode(objectField, requestObject);
                        break;
                    }
                    case TransactionID: {
                        getFieldTransactionID(objectField, requestObject);
                        break;
                    }
                    case OpenTags:
                        getFieldOpenTags(objectField, requestObject);
                        break;
                    case EncTags:
                        getFieldEncTags(objectField, requestObject);
                        break;
                    case ProviderCode:
                        getFieldProviderCode(objectField, requestObject);
                        break;
                    case AdditionalInfo: {
                        getFieldAdditionalInfo(objectField, requestObject);
                        break;
                    }
                    case CardDataEnc: {
                        getFieldCardDataEnc(objectField, requestObject);
                        break;
                    }
                }//switch
            }//object has field
        }//foreach
    }//getFields
    private static void getFieldEcrNumber(JSONObject objectField, IRequest requestObject) {
        int ecrNumber = objectField.optInt(RequestFieldsList.EcrNumber.getString(), -1);
        if(ecrNumber != -1)
        {
            requestObject.setEcrNumber(ecrNumber);
        }
    }
    private static void getFieldEcrMerchantNumber(JSONObject objectField, IRequest requestObject) {
        int ecrMerchantNumber = objectField.optInt(RequestFieldsList.EcrMerchantNumber.getString(), -1);
        if(ecrMerchantNumber != -1)
        {
            requestObject.setEcrMerchantNumber(ecrMerchantNumber);
        }
    }
    private static void getFieldAmount(JSONObject objectField, IRequest requestObject) {
        long amount = objectField.optLong(RequestFieldsList.Amount.getString(), -1);
        if(amount != -1)
        {
            requestObject.setAmount(amount);
        }
    }
    private static void getFieldPartialAmount(JSONObject objectField, IRequest requestObject) {
        long partialAmount = objectField.optLong(RequestFieldsList.PartialAmount.getString(), -1);
        if(partialAmount != -1)
        {
            requestObject.setPartialAmount(partialAmount);
        }
    }
    private static void getFieldTipsAmount(JSONObject objectField, IRequest requestObject) {
        long tipsAmount = objectField.optLong(RequestFieldsList.TipsAmount.getString(), -1);
        if(tipsAmount != -1)
        {
            requestObject.setTipsAmount(tipsAmount);
        }
    }
    private static void getFieldCurrencyCode(JSONObject objectField, IRequest requestObject) {
        int currencyCode = objectField.optInt(RequestFieldsList.CurrencyCode.getString(), -1);
        if(currencyCode != -1)
        {
            requestObject.setCurrencyCode(currencyCode);
        }
    }
    private static void getFieldReceiptReference(JSONObject objectField, IRequest requestObject) {
        String receiptReference = objectField.optString(RequestFieldsList.ReceiptReference.getString());
        if(!receiptReference.isEmpty())
        {
            requestObject.setReceiptReference(receiptReference);
        }
    }
    private static void getFieldRRN (JSONObject objectField, IRequest requestObject) {
        String rrn = objectField.optString(RequestFieldsList.RRN.getString());
        if(!rrn.isEmpty())
        {
            requestObject.setRRN(rrn);
        }
    }
    private static void getFieldAuthCode(JSONObject objectField, IRequest requestObject) {
        String authCode = objectField.optString(RequestFieldsList.AuthCode.getString());
        if(!authCode.isEmpty())
        {
            requestObject.setAuthCode(authCode);
        }
    }
    private static void getFieldTransactionID(JSONObject objectField, IRequest requestObject) {
        String transactionID = objectField.optString(RequestFieldsList.TransactionID.getString());
        if(!transactionID.isEmpty())
        {
            requestObject.setTransactionID(transactionID);
        }
    }

    private static void getFieldOpenTags(JSONObject objectField, IRequest requestObject) {
        String openTags = objectField.optString(RequestFieldsList.OpenTags.getString());
        if(!openTags.isEmpty())
        {
            requestObject.setOpenTags(openTags);
        }
    }

    private static void getFieldEncTags(JSONObject objectField, IRequest requestObject) {
        String encTags = objectField.optString(RequestFieldsList.EncTags.getString());
        if(!encTags.isEmpty())
        {
            requestObject.setEncTags(encTags);
        }
    }

    private static void getFieldProviderCode(JSONObject objectField, IRequest requestObject) {
        String providerCode = objectField.optString(RequestFieldsList.ProviderCode.getString());
        if(!providerCode.isEmpty())
        {
            requestObject.setProviderCode(providerCode);
        }
    }

    private static void getFieldAdditionalInfo(JSONObject objectField, IRequest requestObject) {
        String additionalInfo = objectField.optString(RequestFieldsList.AdditionalInfo.getString());
        if(!additionalInfo.isEmpty())
        {
            requestObject.setAdditionalInfo(additionalInfo);
        }
    }
    private static void getFieldCardDataEnc(JSONObject objectField, IRequest requestObject) {
        String cardDataEnc = objectField.optString(RequestFieldsList.CardDataEnc.getString());
        if(!cardDataEnc.isEmpty())
        {
            requestObject.setCardDataEnc(cardDataEnc);
        }
    }
}
