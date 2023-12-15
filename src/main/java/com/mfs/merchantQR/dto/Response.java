/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.dto

Class Name: Response

Date and Time:3/13/2023 12:34 PM

Version:1.0
*/
package com.mfs.merchantQR.dto;

import java.util.List;

public class Response {

    private String responseCode;
    private String message;
    private String channel;
    private String terminal;
    private String transactionDate;
    private String reterivalReferenceNumber;
    private Object payLoad;
    private List<Error> errors;
    private String checkSum;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getReterivalReferenceNumber() {
        return reterivalReferenceNumber;
    }

    public void setReterivalReferenceNumber(String reterivalReferenceNumber) {
        this.reterivalReferenceNumber = reterivalReferenceNumber;
    }

    public Object getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(Object payLoad) {
        this.payLoad = payLoad;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
}
