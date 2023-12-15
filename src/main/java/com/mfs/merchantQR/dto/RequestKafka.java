/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.dto

Class Name: RequestKafka

Date and Time:3/13/2023 12:34 PM

Version:1.0
*/
package com.mfs.merchantQR.dto;

public class RequestKafka {

    private Security security;
    private PayloadKafka payload;
    private String indexName;

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public PayloadKafka getPayload() {
        return payload;
    }

    public void setPayload(PayloadKafka payload) {
        this.payload = payload;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}
