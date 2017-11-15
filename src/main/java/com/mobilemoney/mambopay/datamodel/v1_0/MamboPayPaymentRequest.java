package com.mobilemoney.mambopay.datamodel.v1_0;

/**
 *
 * @author SmallGod - Davies Mugume A.
 */
public class MamboPayPaymentRequest {

    private AccountToDebit accountToDebit;
    private AmountToDebit amountToDebit;
    private TransactionId transactionId;

    public AccountToDebit getAccountToDebit() {
        return accountToDebit;
    }

    public void setAccountToDebit(AccountToDebit accountToDebit) {
        this.accountToDebit = accountToDebit;
    }

    public AmountToDebit getAmountToDebit() {
        return amountToDebit;
    }

    public void setAmountToDebit(AmountToDebit amountToDebit) {
        this.amountToDebit = amountToDebit;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionId transactionId) {
        this.transactionId = transactionId;
    }

    public class AccountToDebit {

        private String accountToDebitParam;
        private String accountToDebitValue;

        public AccountToDebit(String accountToDebitParam, String accountToDebitValue) {

            this.accountToDebitParam = accountToDebitParam;
            this.accountToDebitValue = accountToDebitValue;
        }

        /**
         * @return the accountToDebitValue
         */
        public String getAccountToDebitValue() {
            return accountToDebitValue;
        }

        /**
         * @param accountToDebitValue the accountToDebitValue to set
         */
        public void setAccountToDebitValue(String accountToDebitValue) {
            this.accountToDebitValue = accountToDebitValue;
        }

        public String getAccountToDebitParam() {
            return accountToDebitParam;
        }

        public void setAccountToDebitParam(String accountToDebitParam) {
            this.accountToDebitParam = accountToDebitParam;
        }

    }

    public class AmountToDebit {

        private String amountToDebitParam;
        private AmountType amountToDebitValue;

        public AmountToDebit(String amountToDebitParam, AmountType amountToDebitValue) {
            this.amountToDebitParam = amountToDebitParam;
            this.amountToDebitValue = amountToDebitValue;
        }

        public AmountType getAmountToDebitValue() {
            return amountToDebitValue;
        }

        public void setAmountToDebitValue(AmountType amountToDebitValue) {
            this.amountToDebitValue = amountToDebitValue;
        }

        public String getAmountToDebitParam() {
            return amountToDebitParam;
        }

        public void setAmountToDebitParam(String amountToDebitParam) {
            this.amountToDebitParam = amountToDebitParam;
        }
    }

    public class TransactionId {

        private String transactionIdParam;
        private String transactionIdValue;

        public TransactionId(String transactionIdParam, String transactionIdValue) {
            this.transactionIdParam = transactionIdParam;
            this.transactionIdValue = transactionIdValue;
        }

        /**
         * @return the transactionIdValue
         */
        public String getTransactionIdValue() {
            return transactionIdValue;
        }

        /**
         * @param transactionIdValue the transactionIdValue to set
         */
        public void setTransactionIdValue(String transactionIdValue) {
            this.transactionIdValue = transactionIdValue;
        }

        public String getTransactionIdParam() {
            return transactionIdParam;
        }

        public void setTransactionIdParam(String transactionIdParam) {
            this.transactionIdParam = transactionIdParam;
        }
    }

}
