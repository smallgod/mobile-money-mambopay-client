package com.mobilemoney.mambopay.datamodel.v1_0;

/**
 *
 * @author SmallGod - Davies Mugume A.
 */
public class MamboPayPaymentResponse {

    private String status;
    private String statusDescription;
    private String mamboPayReference;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the statusDescription
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getMamboPayReference() {
        return mamboPayReference;
    }

    public void setMamboPayReference(String mamboPayReference) {
        this.mamboPayReference = mamboPayReference;
    }

}
