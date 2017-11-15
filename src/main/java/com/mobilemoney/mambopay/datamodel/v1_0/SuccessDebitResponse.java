package com.mobilemoney.mambopay.datamodel.v1_0;

import com.google.gson.annotations.SerializedName;

public class SuccessDebitResponse {

    /*

    JSON Request sample:

    SUCCESS
    {
    
        "reference":745115924,
        "transaction_id":"33322345",
        "status_code":"01",
        "status_description":"Transaction Queued for processing"
    }

     */
    @SerializedName(value = "reference")
    private String reference;

    @SerializedName(value = "transaction_id")
    private String transactionId;

    @SerializedName(value = "status_code")
    private String statusCode;

    @SerializedName(value = "status_message") 
    private String statusDescription;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

}
