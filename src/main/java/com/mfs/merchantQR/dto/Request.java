/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.dto

Class Name: Request

Date and Time:3/13/2023 12:34 PM

Version:1.0
*/
package com.mfs.merchantQR.dto;

import java.util.List;

public class Request {

    private Security security;
    private Account account;
    private String channel;
    private String terminal;
    private String reterivalReferenceNumber;
    private Object payLoad;
    private List<AdditionalInformation> additionalInformation;
    public String checkSum;

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public List<AdditionalInformation> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<AdditionalInformation> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
}
