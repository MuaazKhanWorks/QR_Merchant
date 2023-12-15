package com.mfs.merchantQR.dto;
/*
Author Name: romail.ahmed

Project Name: integration

Package Name:com.mfs.integration.dto

Class Name: PayloadDTO

Date and Time:2/6/2023 4:26 PM

Version:1.0
*/

import java.io.Serializable;

public class PayloadDTO implements Serializable {
    private String serviceName;
    private String endpoint;
    private String dateTime;
    private String payloadService;
    private String message;
    private String loggingLevel;
    private String packageName;
    private String loggerID;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName( String serviceName ) {
        this.serviceName = serviceName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint( String endpoint ) {
        this.endpoint = endpoint;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime( String dateTime ) {
        this.dateTime = dateTime;
    }

    public String getPayloadService() {
        return payloadService;
    }

    public void setPayloadService( String payloadService ) {
        this.payloadService = payloadService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public String getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel( String loggingLevel ) {
        this.loggingLevel = loggingLevel;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName( String packageName ) {
        this.packageName = packageName;
    }

    public String getLoggerID() {
        return loggerID;
    }

    public void setLoggerID( String loggerID ) {
        this.loggerID = loggerID;
    }

    @Override
    public String toString() {
        return "payload{" +
                "serviceName='" + serviceName + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", payloadService='" + payloadService + '\'' +
                ", message='" + message + '\'' +
                ", loggingLevel='" + loggingLevel + '\'' +
                ", packageName='" + packageName + '\'' +
                ", loggerID='" + loggerID + '\'' +
                '}';
    }
}

