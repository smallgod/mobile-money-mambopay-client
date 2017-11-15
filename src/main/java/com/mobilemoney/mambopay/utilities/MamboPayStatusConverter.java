package com.mobilemoney.mambopay.utilities;

import com.mobilemoney.mambopay.datamodel.v1_0.TransactionStatus;

/**
 *
 * @author smallgod
 */
public class MamboPayStatusConverter {

    public static final class ConvertedStatus {

        private TransactionStatus status;
        private String statusDescription;

        public ConvertedStatus(TransactionStatus status, String statusDescription) {
            this.status = status;
            this.statusDescription = statusDescription;
        }

        public TransactionStatus getStatus() {
            return status;
        }

        public void setStatus(TransactionStatus status) {
            this.status = status;
        }

        public String getStatusDescription() {
            return statusDescription;
        }

        public void setStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
        }

    }

    /**
     * Convert a mambo pay status to an appropriate internal status code &
     * description
     *
     * @param mamboPayStatusCode
     * @param mamboPayStatusDescription
     * @return
     */
    public static ConvertedStatus convert(String mamboPayStatusCode, String mamboPayStatusDescription) {

        TransactionStatus status = TransactionStatus.UNKNOWN;
        String statusDescription = mamboPayStatusDescription;

        if (!(mamboPayStatusCode == null || mamboPayStatusCode.isEmpty())) {

            switch (mamboPayStatusCode) {

                case NamedConstants.MAMBOPAY_DEBIT_PROCESSING:
                    status = TransactionStatus.PROCESSING;
                    statusDescription = "Processing: " + mamboPayStatusDescription;
                    break;

                case NamedConstants.MAMBOPAY_DEBIT_DUPLICATE:
                    status = TransactionStatus.DUPLICATE;
                    statusDescription = "Duplicate payment: " + mamboPayStatusDescription;
                    break;

                case NamedConstants.MAMBOPAY_DEBIT_MISSING_SUBSCRIPTION_KEY_2:
                    status = TransactionStatus.FAILED;
                    statusDescription = "Missing subscription key: " + mamboPayStatusDescription;
                    break;

                default:
                    status = TransactionStatus.UNKNOWN;
                    statusDescription = "Failed to interprete status code: " + mamboPayStatusDescription;
                    break;
            }
        }

        return new ConvertedStatus(status, statusDescription);
    }

    /**
     * Convert a mambo pay status to an appropriate internal status code &
     * description
     *
     * @param mamboPayStatusCode
     * @param mamboPayStatusDescription
     * @return
     */
    public static ConvertedStatus convert(int mamboPayStatusCode, String mamboPayStatusDescription) {

        TransactionAggregatorStatus status;
        String statusDescription;

        switch (mamboPayStatusCode) {

            case NamedConstants.MAMBOPAY_DEBIT_ACCOUNT_UNREGISTERED:
                status = TransactionAggregatorStatus.FAILED;
                statusDescription = "Processing: " + mamboPayStatusDescription;
                break;

            case NamedConstants.MAMBOPAY_DEBIT_BELOW_THRESHOLD:
                status = TransactionAggregatorStatus.FAILED;
                statusDescription = "Payment below threshold: " + mamboPayStatusDescription;
                break;

            case NamedConstants.MAMBOPAY_DEBIT_PAYMENT_EXPIRED:
                status = TransactionAggregatorStatus.FAILED;
                statusDescription = "Failed to approve payment in 6 minutes: " + mamboPayStatusDescription;
                break;

            case NamedConstants.MAMBOPAY_DEBIT_INSUFFICIENT_FUNDS:
                status = TransactionAggregatorStatus.FAILED;
                statusDescription = "Insufficient funds: " + mamboPayStatusDescription;
                break;

            case NamedConstants.MAMBOPAY_DEBIT_SUCCESS:
                status = TransactionAggregatorStatus.SUCCESSFUL;
                statusDescription = mamboPayStatusDescription;
                break;

            case NamedConstants.MAMBOPAY_DEBIT_MISSING_SUBSCRIPTION_KEY:
                status = TransactionAggregatorStatus.FAILED;
                statusDescription = "Missing subscription key: " + mamboPayStatusDescription;
                break;

            default:
                status = TransactionAggregatorStatus.UNKNOWN;
                statusDescription = "Failed to interprete status code: " + mamboPayStatusDescription;

                break;
        }

        return new ConvertedStatus(status, statusDescription);
    }

}
