/*Author Name:ahmad.raza
Project Name: QR_merchant
Package Name:com.mfs.merchantQR.dto
Class Name: MerchantAccountDTO
Date and Time:2/19/2024 1:54 PM
Version:1.0*/
package com.mfs.merchantQR.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantAccountDTO {

    private String qrImages;
    private String mobileNo;
    private String cnic;
    private String businessName;
    private String city;
    private String businessAddress;
    private String typeOfBusiness;
    private String downloadStatus;
    @Temporal(TemporalType.DATE)
    private Date createdOn;
    private BigDecimal createdBy;
    private String registrationStatus;


    public String getQrImages() {
        return qrImages;
    }

    public void setQrImages(String qrImages) {
        this.qrImages = qrImages;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }


    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}