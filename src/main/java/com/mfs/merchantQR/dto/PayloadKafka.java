/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.dto

Class Name: PayloadKafka

Date and Time:3/13/2023 12:33 PM

Version:1.0
*/
package com.mfs.merchantQR.dto;

public class PayloadKafka {

    private String serviceName;
    private String endpoint;
    private String dateTime;
    private String payloadService;
    private String message;
    private String loggingLevel;
    private String className;
    private String methodName;
    private String packageName;
    private String loggerID;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPayloadService() {
        return payloadService;
    }

    public void setPayloadService(String payloadService) {
        this.payloadService = payloadService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel(String loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLoggerID() {
        return loggerID;
    }

    public void setLoggerID(String loggerID) {
        this.loggerID = loggerID;
    }
}
