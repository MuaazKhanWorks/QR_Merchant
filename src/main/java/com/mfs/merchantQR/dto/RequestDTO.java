package com.mfs.merchantQR.dto;

import java.io.Serializable;

/*
Author Name: romail.ahmed

Project Name: integration

Package Name:com.mfs.integration.dto

Class Name: RequestDTO

Date and Time:2/6/2023 4:26 PM

Version:1.0
*/
public class RequestDTO implements Serializable {
    private Security security;
    private PayloadDTO payload;
    private String indexName;


    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public PayloadDTO getPayload() {
        return payload;
    }

    public void setPayload(PayloadDTO payload) {
        this.payload = payload;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}

