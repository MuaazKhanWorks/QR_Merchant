package com.mfs.merchantQR.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.model.TblResponseMessage;
import com.mfs.merchantQR.model.TblRole;
import com.mfs.merchantQR.model.TblUser;

import java.text.ParseException;
import java.util.List;

public interface MerchantQrService {

    TblResponseMessage findByResponseMessageDescr(String success);

    TblUser saveUser(TblUserRequest tblUserRequest, int userId) throws ParseException;

    TblUser checkUserExistance(String userName);

    Response saveUserRequest(TokenData loggedUserDetail, TblUserRequest tblUserRequest) throws JsonProcessingException, ParseException;

    Response updateUserRequest(TokenData loggedUserDetail, UpdateUserRequest updateUserRequest) throws JsonProcessingException;

    List<TblUser> getAllUsersBySearch(GetAllUsersRequest getAllUsersRequest);
}
