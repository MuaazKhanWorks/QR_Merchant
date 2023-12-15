/*
Author Name: romail.ahmed

Project Name: integration

Package Name:org.mfs.integration.dto

Class Name: Account

Date and Time:2/6/2023 4:26 PM

Version:1.0
*/
package com.mfs.merchantQR.dto;

public class Account {
    private String msidn;
    private String iban;
    private String bban;
    private String pan;
    private String currency;

    public String getMsidn() {
        return msidn;
    }

    public void setMsidn(String msidn) {
        this.msidn = msidn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBban() {
        return bban;
    }

    public void setBban(String bban) {
        this.bban = bban;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
