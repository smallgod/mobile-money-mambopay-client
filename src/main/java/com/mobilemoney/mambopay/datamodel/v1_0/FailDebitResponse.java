package com.mobilemoney.mambopay.datamodel.v1_0;

import com.google.gson.annotations.SerializedName;

public class FailDebitResponse {

    /*

    JSON Response sample for a failed payment:

    { 
        "statusCode": 401, 
        "message": "Access denied due to missing subscription key. Make sure to include subscription key when making requests to an API." 
    }
    
     */
    @SerializedName(value = "statusCode")
    private String statusCode;

    @SerializedName(value = "message")
    private String statusMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
