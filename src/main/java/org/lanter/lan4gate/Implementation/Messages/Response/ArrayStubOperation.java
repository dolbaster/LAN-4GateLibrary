package org.lanter.lan4gate.Implementation.Messages.Response;

import org.lanter.lan4gate.Messages.Response.IResponse;
import org.lanter.lan4gate.Messages.Response.ResponseFieldsList;

public class ArrayStubOperation extends Response {
    public ArrayStubOperation() {
        clearMandatoryFields();
        clearOptionalFields();

        addMandatoryFields(ResponseFieldsList.TotalAmount);
        addMandatoryFields(ResponseFieldsList.CurrencyCode);
        addMandatoryFields(ResponseFieldsList.RRN);
        addMandatoryFields(ResponseFieldsList.ReceiptReference);
        addMandatoryFields(ResponseFieldsList.ResponseCode);
        addMandatoryFields(ResponseFieldsList.TransactionID);
        addMandatoryFields(ResponseFieldsList.TransDateTime);
        addMandatoryFields(ResponseFieldsList.CardPAN);
    }

    public ArrayStubOperation(IResponse response) {
        this();
        if(response != null) {
            for (ResponseFieldsList field : response.getCurrentFieldsList()) {
                if(getMandatoryFields().contains(field) || getOptionalFields().contains(field)) {
                    switch (field) {
                        case EcrNumber:
                            setEcrNumber(response.getEcrNumber());
                            break;
                        case EcrMerchantNumber:
                            setEcrMerchantNumber(response.getEcrMerchantNumber());
                            break;
                        case OperationCode:
                            setOperationCode(response.getOperationCode());
                            break;
                        case OriginalOperationCode:
                            setOriginalOperationCode(response.getOriginalOperationCode());
                            break;
                        case TotalAmount:
                            setTotalAmount(response.getTotalAmount());
                            break;
                        case PartialAmount:
                            setPartialAmount(response.getPartialAmount());
                            break;
                        case AcquirerFeeAmount:
                            setAcquirerFeeAmount(response.getAcquirerFeeAmount());
                            break;
                        case TerminalFeeAmount:
                            setTerminalFeeAmount(response.getTerminalFeeAmount());
                            break;
                        case TipsAmount:
                            setTipsAmount(response.getTipsAmount());
                            break;
                        case CurrencyCode:
                            setCurrencyCode(response.getCurrencyCode());
                            break;
                        case ReceiptReference:
                            setReceiptReference(response.getReceiptReference());
                            break;
                        case RRN:
                            setRRN(response.getRRN());
                            break;
                        case Status:
                            setStatus(response.getStatus());
                            break;
                        case OriginalOperationStatus:
                            setOriginalOperationStatus(response.getOriginalOperationStatus());
                            break;
                        case TransDateTime:
                            setTransDateTime(response.getTransDateTime());
                            break;
                        case TerminalDateTime:
                            setTerminalDateTime(response.getTerminalDateTime());
                            break;
                        case CardPAN:
                            setCardPAN(response.getCardPAN());
                            break;
                        case ExpireDate:
                            setExpireDate(response.getExpireDate());
                            break;
                        case CardholderName:
                            setCardholderName(response.getCardholderName());
                            break;
                        case CardholderAuthMethod:
                            setCardholderAuthMethod(response.getCardholderAuthMethod());
                            break;
                        case AuthCode:
                            setAuthCode(response.getAuthCode());
                            break;
                        case ResponseCode:
                            setResponseCode(response.getResponseCode());
                            break;
                        case ResponseText:
                            setResponseText(response.getResponseText());
                            break;
                        case STAN:
                            setSTAN(response.getSTAN());
                            break;
                        case TransactionID:
                            setTransactionID(response.getTransactionID());
                            break;
                        case TerminalID:
                            setTerminalID(response.getTerminalID());
                            break;
                        case CardEmvAid:
                            setCardEmvAid(response.getCardEmvAid());
                            break;
                        case CardAppName:
                            setCardAppName(response.getCardAppName());
                            break;
                        case CardInputMethod:
                            setCardInputMethod(response.getCardInputMethod());
                            break;
                        case IssuerName:
                            setIssuerName(response.getIssuerName());
                            break;
                        case AdditionalInfo:
                            setAdditionalInfo(response.getAdditionalInfo());
                            break;
                        case CardData:
                            setCardData(response.getCardData());
                            break;
                        case CardDataEnc:
                            setCardDataEnc(response.getCardDataEnc());
                            break;
                        case MerchantId:
                            setMerchantId(response.getMerchantId());
                            break;
                        case TVR:
                            setTVR(response.getTVR());
                            break;
                        case TSI:
                            setTSI(response.getTSI());
                            break;
                        case TC:
                            setTC(response.getTC());
                            break;
                        case CID:
                            setCID(response.getCID());
                            break;
                        case KVR:
                            setKVR(response.getKVR());
                            break;
                        case CDAResult:
                            setCDAResult(response.getCDAResult());
                            break;
                        case SalesCount:
                            setSalesCount(response.getSalesCount());
                            break;
                        case VoidCount:
                            setVoidCount(response.getVoidCount());
                            break;
                        case RefundCount:
                            setRefundCount(response.getRefundCount());
                            break;
                        case SalesArray:
                            setSalesArray(response.getSalesArray());
                            break;
                        case VoidArray:
                            setVoidArray(response.getVoidArray());
                            break;
                        case RefundArray:
                            setRefundArray(response.getRefundArray());
                            break;
                        case CardPANHash:
                            setCardPANHash(response.getCardPANHash());
                            break;
                        case ReceiptLine1:
                            setReceiptLine1(response.getReceiptLine1());
                            break;
                        case ReceiptLine2:
                            setReceiptLine2(response.getReceiptLine2());
                            break;
                        case ReceiptLine3:
                            setReceiptLine3(response.getReceiptLine3());
                            break;
                        case ReceiptLine4:
                            setReceiptLine4(response.getReceiptLine4());
                            break;
                        case ReceiptLine5:
                            setReceiptLine5(response.getReceiptLine5());
                            break;
                    }
                }
            }
        }
    }
}
