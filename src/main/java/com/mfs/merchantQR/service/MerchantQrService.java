package com.mfs.merchantQR.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.merchantQR.dto.Response;
import com.mfs.merchantQR.dto.TblUserRequest;
import com.mfs.merchantQR.dto.TokenData;
import com.mfs.merchantQR.dto.UpdateUserRequest;
import com.mfs.merchantQR.model.TblResponseMessage;
import com.mfs.merchantQR.model.TblRole;
import com.mfs.merchantQR.model.TblUser;

import java.text.ParseException;

public interface MerchantQrService {

    TblResponseMessage findByResponseMessageDescr(String success);

    TblUser saveUser(TblUserRequest tblUserRequest, int userId) throws ParseException;

    TblUser checkUserExistance(String userName);

    Response saveUserRequest(TokenData loggedUserDetail, TblUserRequest tblUserRequest) throws JsonProcessingException, ParseException;

    Response updateUserRequest(TokenData loggedUserDetail, UpdateUserRequest updateUserRequest) throws JsonProcessingException;


}
