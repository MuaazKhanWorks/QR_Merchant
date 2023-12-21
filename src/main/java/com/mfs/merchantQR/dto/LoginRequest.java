package com.mfs.merchantQR.dto;

import com.mfs.merchantQR.utils.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class LoginRequest {


    private String userName;

    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
