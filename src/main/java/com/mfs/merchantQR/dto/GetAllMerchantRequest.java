/*Author Name:ahmad.raza
Project Name: QR_merchant
Package Name:com.mfs.merchantQR.dto
Class Name: GetAllUsersRequest
Date and Time:12/18/2023 11:34 AM
Version:1.0*/
package com.mfs.merchantQR.dto;

public class GetAllMerchantRequest {

    private String merchantName;
    private String merchantAccountNo;
    private String merchantCreationDate;
    private String createdBy;
    private String status;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAccountNo() {
        return merchantAccountNo;
    }

    public void setMerchantAccountNo(String merchantAccountNo) {
        this.merchantAccountNo = merchantAccountNo;
    }

    public String getMerchantCreationDate() {
        return merchantCreationDate;
    }

    public void setMerchantCreationDate(String merchantCreationDate) {
        this.merchantCreationDate = merchantCreationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}