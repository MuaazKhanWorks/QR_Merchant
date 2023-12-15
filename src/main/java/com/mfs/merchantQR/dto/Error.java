/*
Author Name: romail.ahmed

Project Name: integration

Package Name:org.mfs.integration.dto

Class Name: Error

Date and Time:2/6/2023 4:30 PM

Version:1.0
*/
package com.mfs.merchantQR.dto;

public class Error {
    private String errorCode;
    private String errorDescr;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescr() {
        return errorDescr;
    }

    public void setErrorDescr(String errorDescr) {
        this.errorDescr = errorDescr;
    }
}
