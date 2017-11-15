package com.mobilemoney.mambopay.datamodel.v1_0;

/**
 *
 * @author SmallGod - Davies Mugume A.
 */
public enum TransactionStatus {

    /**
     * Payment NOT logged (to the database) for some reason other than
     * 'duplicate', etc
     */
    NOT_LOGGED("NOT_LOGGED"),
    /**
     * Payment logged (to the database)
     */
    LOGGED("LOGGED"),
    /**
     * Payment fetched for processing
     */
    PROCESSING("PROCESSING"),
    SUCCESSFUL("SUCCESSFUL"),
    FAILED("FAILED"),
    REVERSED("REVERSED"),
    DUPLICATE("DUPLICATE"),
    EXPIRED("EXPIRED"),
    UNKNOWN("UNKNOWN"),
    ALL("ALL");

    private final String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return this.status;
    }

    public static TransactionStatus convertToEnum(String value) {

        if (value != null) {

            for (TransactionStatus availableValue : TransactionStatus.values()) {

                if (value.equalsIgnoreCase(availableValue.getValue())) {
                    return availableValue;
                }
            }
        }
        throw new IllegalArgumentException("Unsupported TransactionStatus value: " + value);
    }
}
