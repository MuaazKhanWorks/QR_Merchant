/*Author Name:ahmad.raza
Project Name: merchantQR
Package Name:com.mfs.merchantQR.dto
Class Name: TblUserRequest
Date and Time:12/14/2023 12:26 PM
Version:1.0*/
package com.mfs.merchantQR.dto;

public class UpdateDownloadStatusRequest {

    private Integer merchantId;
    private String downloadStatus;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
}