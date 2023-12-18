/*Author Name:ahmad.raza
Project Name: QR_merchant
Package Name:com.mfs.merchantQR.dto
Class Name: GetAllUsersRequest
Date and Time:12/18/2023 11:34 AM
Version:1.0*/
package com.mfs.merchantQR.dto;

public class GetAllUsersRequest {

    private String name;
    private String role;
    private String user;
    private String date;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}