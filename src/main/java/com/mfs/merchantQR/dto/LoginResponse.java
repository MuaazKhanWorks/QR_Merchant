package com.mfs.merchantQR.dto;


import com.mfs.merchantQR.model.TblUser;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:10 PM
 * Project : jcash
 */
public class LoginResponse {

    private TblUser tblUser;

    private String token;

//    private List<Menu> menu;

    private boolean login = false;

    private String sessionTimeOut;

    public TblUser getTblUser() {
        return tblUser;
    }

    public void setTblUser(TblUser tblUser) {
        this.tblUser = tblUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public List<Menu> getMenu() {
//        return menu;
//    }
//
//    public void setMenu(List<Menu> menu) {
//        this.menu = menu;
//    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(String sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }
}
