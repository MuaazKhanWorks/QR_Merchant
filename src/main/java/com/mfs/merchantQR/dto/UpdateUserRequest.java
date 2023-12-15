/*Author Name:ahmad.raza
Project Name: merchantQR
Package Name:com.mfs.merchantQR.dto
Class Name: TblUserRequest
Date and Time:12/14/2023 12:26 PM
Version:1.0*/
package com.mfs.merchantQR.dto;

public class UpdateUserRequest {

    private int userId;
    private String email;
    private String name;
    private String password;
    private int roleId;
    private int statusId;
    private String isActive;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}