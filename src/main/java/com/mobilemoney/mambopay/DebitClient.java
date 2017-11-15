/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.mambopay;

import com.library.utilities.GeneralUtils;
import com.library.utilities.MamboPayStatusConverter;
import com.mobilemoney.mambopay.datamodel.v1_0.FailDebitResponse;
import com.mobilemoney.mambopay.datamodel.v1_0.HTTPMethod;
import com.mobilemoney.mambopay.datamodel.v1_0.MamboPayPaymentRequest;
import com.mobilemoney.mambopay.datamodel.v1_0.MamboPayPaymentResponse;
import com.mobilemoney.mambopay.datamodel.v1_0.SuccessDebitResponse;
import com.mobilemoney.mambopay.datamodel.v1_0.TransactionStatus;
import com.mobilemoney.mambopay.httpconnmanager.HttpClientPool;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author smallgod
 */
public class DebitClient {

    private final HttpClientPool clientPool;

    public DebitClient(HttpClientPool clientPool) {
        this.clientPool = clientPool;
    }

    /**
     * Debit customer accounts with the given details below
     *
     * @param paymentsList
     * @param callBackUrlParam
     * @param callBackUrlValue
     * @param mamboPaySubscriptionKeyParam
     * @param mamboPaySubscriptionKeyValue
     * @param mamboPayDebitUrl
     * @return
     *
     */
    public Set<MamboPayPaymentResponse> debitClientViaMamboPay(Iterator<MamboPayPaymentRequest> paymentsList, String callBackUrlParam, String callBackUrlValue, String mamboPayDebitUrl, String mamboPaySubscriptionKeyParam, String mamboPaySubscriptionKeyValue) {

        Set<MamboPayPaymentResponse> paymentResponseList = new HashSet<>();

        while (paymentsList.hasNext()) {

            MamboPayPaymentResponse payResponse = new MamboPayPaymentResponse();

            MamboPayPaymentRequest payment = paymentsList.next();

            String momoAccount = payment.getAccountToDebit().getAccountToDebitValue();
            String transactionId = String.valueOf(payment.getTransactionId().getTransactionIdValue());
            int amount = GeneralUtils.roundUpToNext100(payment.getAmountToDebit().getAmountToDebitValue().getAmount());

            Map<String, Object> paramPairs = new HashMap<>();

            paramPairs.put(payment.getAccountToDebit().getAccountToDebitParam(), momoAccount);
            paramPairs.put(payment.getAmountToDebit().getAmountToDebitParam(), amount);
            paramPairs.put(payment.getTransactionId().getTransactionIdParam(), transactionId);
            paramPairs.put(callBackUrlParam, callBackUrlValue);

            paramPairs.put(mamboPaySubscriptionKeyParam, mamboPaySubscriptionKeyValue);

            String response = clientPool.sendRemoteRequest("", mamboPayDebitUrl, paramPairs, HTTPMethod.POST);

            TransactionStatus status;
            String statusCode;
            String statusDescription;
            String reference = "";

            SuccessDebitResponse debitResponse;
            FailDebitResponse debitResponseFail;

            if (!(response == null || response.isEmpty())) {

                debitResponse = GeneralUtils.convertFromJson(response, SuccessDebitResponse.class);

                if (debitResponse != null) {

                    statusCode = debitResponse.getStatusCode();
                    statusDescription = debitResponse.getStatusDescription();
                    reference = debitResponse.getReference() != null ? debitResponse.getReference() : "";

                    if (statusCode == null || statusCode.isEmpty()) {

                        debitResponseFail = GeneralUtils.convertFromJson(response, FailDebitResponse.class);
                        statusCode = debitResponseFail.getStatusCode() != null ? debitResponseFail.getStatusCode() : "";
                        statusDescription = debitResponseFail.getStatusMessage();

                    }

                } else {

                    debitResponseFail = GeneralUtils.convertFromJson(response, DebitAccountMamboPayResponseFail.class);
                    statusCode = debitResponseFail.getStatusCode() != null ? debitResponseFail.getStatusCode() : "";
                    statusDescription = debitResponseFail.getStatusMessage();

                }
                MamboPayStatusConverter.ConvertedStatus transStatus = MamboPayStatusConverter.convert(statusCode, statusDescription);
                status = transStatus.getStatus();
                statusDescription = transStatus.getStatusDescription();

            } else {
                status = TransactionAggregatorStatus.UNKNOWN;
                statusDescription = "Status is unkown, decide if to re-process or not";
            }

            payResponse.setMamboPayReference(reference);
            payResponse.setStatus(status.getValue());
            payResponse.setStatusDescription(statusDescription);

            paymentResponseList.add(payResponse);

        }

        return paymentResponseList;
    }

    /**
     * Debit customer accounts with the given details below
     *
     * @param payment
     * @param callBackUrlParam
     * @param callBackUrlValue
     * @param mamboPaySubscriptionKeyParam
     * @param mamboPaySubscriptionKeyValue
     * @param mamboPayDebitUrl
     * @return
     * @throws com.library.customexception.MyCustomException
     */
    public MamboPayPaymentResponse debitClientViaMamboPay(MamboPayPaymentRequest payment, String callBackUrlParam, String callBackUrlValue, String mamboPayDebitUrl, String mamboPaySubscriptionKeyParam, String mamboPaySubscriptionKeyValue) throws MyCustomException {

        MamboPayPaymentResponse payResponse = new MamboPayPaymentResponse();

        if (payment != null) {

            String momoAccount = payment.getAccountToDebit().getAccountToDebitValue();
            String transactionId = String.valueOf(payment.getTransactionId().getTransactionIdValue());
            int amount = GeneralUtils.roundUpToNext100(payment.getAmountToDebit().getAmountToDebitValue().getAmount());

            Map<String, Object> paramPairs = new HashMap<>();

            paramPairs.put(payment.getAccountToDebit().getAccountToDebitParam(), momoAccount);
            paramPairs.put(payment.getAmountToDebit().getAmountToDebitParam(), amount);
            paramPairs.put(payment.getTransactionId().getTransactionIdParam(), transactionId);
            paramPairs.put(callBackUrlParam, callBackUrlValue);
            paramPairs.put(mamboPaySubscriptionKeyParam, mamboPaySubscriptionKeyValue);

            String response = clientPool.sendRemoteRequest("", mamboPayDebitUrl, paramPairs, HTTPMethod.POST);
            //String response = "";
            logger.info("Response from MamboPay Server: " + response);

            TransactionAggregatorStatus status;
            String statusCode;
            String statusDescription;
            String reference = "";

            DebitAccountMamboPayResponseSuccess debitResponse;
            DebitAccountMamboPayResponseFail debitResponseFail;

            if (!(response == null || response.isEmpty())) {

                debitResponse = GeneralUtils.convertFromJson(response, DebitAccountMamboPayResponseSuccess.class);

                if (debitResponse != null) {

                    statusCode = debitResponse.getStatusCode();
                    statusDescription = debitResponse.getStatusDescription();
                    reference = debitResponse.getReference() != null ? debitResponse.getReference() : "";
//                    
//                    statusCode = NamedConstants.MAMBOPAY_DEBIT_PROCESSING;
//                    statusDescription = "Recieved & Processing";
//                    reference = "90894994";

                    if (statusCode == null || statusCode.isEmpty()) {

                        debitResponseFail = GeneralUtils.convertFromJson(response, DebitAccountMamboPayResponseFail.class);
                        statusCode = debitResponseFail.getStatusCode() != null ? debitResponseFail.getStatusCode() : "";
                        statusDescription = debitResponseFail.getStatusMessage();

                    }

                } else {

                    debitResponseFail = GeneralUtils.convertFromJson(response, DebitAccountMamboPayResponseFail.class);
                    statusCode = debitResponseFail.getStatusCode() != null ? debitResponseFail.getStatusCode() : "";
                    statusDescription = debitResponseFail.getStatusMessage();

                }
                MamboPayStatusConverter.ConvertedStatus transStatus = MamboPayStatusConverter.convert(statusCode, statusDescription);
                status = transStatus.getStatus();
                statusDescription = transStatus.getStatusDescription();

            } else {
                status = TransactionAggregatorStatus.UNKNOWN;
                statusDescription = "Status is unkown, decide if to re-process or not";
            }

            payResponse.setMamboPayReference(reference);
            payResponse.setStatus(status.getValue());
            payResponse.setStatusDescription(statusDescription);

        } else {
            logger.info(">>>> NO Pending Payments Found!!! <<<<<");
        }

        return payResponse;
    }

}
