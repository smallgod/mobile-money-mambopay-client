package com.mobilemoney.mambopay.datamodel.v1_0;

public class AmountType {

    private int amount;
    private String currencyCode;

    /**
     * Gets the value of the amount property.
     *
     * @return 
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     *
     * @param value
     */
    public void setAmount(int value) {
        this.amount = value;
    }

    /**
     * Gets the value of the currencyCode property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
