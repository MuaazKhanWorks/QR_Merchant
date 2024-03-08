/*Author Name:ahmad.raza
Project Name: QR_merchant
Package Name:com.mfs.merchantQR.dto
Class Name: StaticQrResponse
Date and Time:3/1/2024 11:02 AM
Version:1.0*/
package com.mfs.merchantQR.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StaticQrResponse {

    private String QR;
    private String UUID;
    private String UETR;
    private String Amount;
    private String Expiry;
    @JsonProperty("CreatedDate")
    private String createdDate;

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUETR() {
        return UETR;
    }

    public void setUETR(String UETR) {
        this.UETR = UETR;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getExpiry() {
        return Expiry;
    }

    public void setExpiry(String expiry) {
        Expiry = expiry;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}