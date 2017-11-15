package com.mobilemoney.mambopay.httpconnmanager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author smallgod
 */
public class HttpConnect {
    
     /**
     * Send a request to a remote server and get a string response
     *
     * @param requestPayloadString
     * @param urlToCall
     * @param paramPairs
     * @param method
     * @return string response
     */
    public String sendRemoteRequest(String requestPayloadString, String urlToCall, Map<String, Object> paramPairs, HTTPMethod method) throws MyCustomException {

        CloseableHttpResponse response = sendHttpRequest(requestPayloadString, urlToCall, paramPairs, method);
        String responsePayload = readResponse(response);

        return responsePayload;

    }
    
    
    /**
     * Send a request to a remote server
     *
     * @param requestPayloadString
     * @param urlToCall
     * @param contentType
     * @param username
     * @param password
     * @return CloseableHttpResponse contains a response that needs to be read
     * and decoded into a string
     */
    private CloseableHttpResponse sendHttpRequest(String requestPayloadString, String urlToCall, Map<String, Object> paramPairs, HTTPMethod method) throws MyCustomException {

        List<NameValuePair> httpParams = GeneralUtils.convertToNameValuePair(paramPairs);

        HttpRequestBase request;
        String errorDetails;
        ErrorCode errorCode = ErrorCode.COMMUNICATION_ERR;

        try {

            switch (method) {

                case POST:
                    request = setHttpPostRequest(requestPayloadString, urlToCall, httpParams);
                    break;

                case GET:
                    request = setHttpGetRequest(requestPayloadString, urlToCall, httpParams);
                    break;

                default:
                    request = setHttpPostRequest(requestPayloadString, urlToCall, httpParams);
                    break;
            }

            //request = Security.setBasicEncoding(request, username, password);
            CloseableHttpResponse response = HttpClientPool.getHttpClient().execute(request);

            return response;

        } catch (UnknownHostException uhe) {

            errorDetails = "UnknownHostException error sending request to: " + urlToCall + " - " + uhe.getMessage();

        } catch (ConnectTimeoutException ex) {

            errorDetails = "ConnectTimeoutException error sending request to: " + urlToCall + " - " + ex.getMessage();
            errorCode = ErrorCode.CONNECTION_TIMEOUT_ERR;

        } catch (SocketTimeoutException ex) {

            errorDetails = "SocketTimeoutException error sending request to: " + urlToCall + " - " + ex.getMessage();
            errorCode = ErrorCode.CONNECTION_TIMEOUT_ERR;

        } catch (UnsupportedEncodingException ex) {

            errorDetails = "UnsupportedEncodingException error sending request to: " + urlToCall + " - " + ex.getMessage();

        } catch (IOException ex) {

            errorDetails = "IOException error sending request to: " + urlToCall + " - " + ex.getMessage();

        } catch (Exception ex) {

            errorDetails = "General Exception error sending request to: " + urlToCall + " - " + ex.getMessage();

        }

        String errorDescription = "Error occurred sending requests to another system";
        MyCustomException error = GeneralUtils.getSingleError(errorCode, errorDescription, errorDetails);
        throw error;

    }

    
}
