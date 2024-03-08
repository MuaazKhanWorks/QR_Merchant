/*Author Name:ahmad.raza
Project Name: QR_merchant
Package Name:com.mfs.merchantQR.dto
Class Name: GetAllMerchantFromMicrobank
Date and Time:3/6/2024 3:52 PM
Version:1.0*/
package com.mfs.merchantQR.dto;

import java.util.Date;

public class GetAllMerchantFromMicrobank {

    String mobileNo;
    String businessName;
    String createdOn;
    long createdBy;
    long registrationStateId;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public long getRegistrationStateId() {
        return registrationStateId;
    }

    public void setRegistrationStateId(long registrationStateId) {
        this.registrationStateId = registrationStateId;
    }
}