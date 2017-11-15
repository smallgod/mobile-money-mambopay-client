package com.mobilemoney.mambopay.datamodel.v1_0;

/**
 *
 * @author SmallGod - Davies Mugume A.
 */
public enum HTTPMethod {

    GET("GET"),
    POST("POST");

    private final String method;

    HTTPMethod(String method) {
        this.method = method;
    }

    public String getValue() {
        return this.method;
    }

    public static HTTPMethod convertToEnum(String methodName) {

        if (methodName != null) {

            for (HTTPMethod availableMethodName : HTTPMethod.values()) {

                if (methodName.equalsIgnoreCase(availableMethodName.getValue())) {
                    return availableMethodName;
                }
            }
        }
        throw new IllegalArgumentException("Unsupported HTTPMethod value: " + methodName);
    }
}
