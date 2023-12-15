package com.mfs.merchantQR.dto;

public class McResponse {

    private int status;
    private String statusDecsr;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDecsr() {
        return statusDecsr;
    }

    public void setStatusDecsr(String statusDecsr) {
        this.statusDecsr = statusDecsr;
    }
}
