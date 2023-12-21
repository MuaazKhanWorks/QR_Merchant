package com.mfs.merchantQR.utils;


import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FieldsValidator {

    public static List<Error> saveUserValidator(TblUserRequest tblModuleRequest) {
        List<Error> fieldError = new ArrayList<>();
        if (tblModuleRequest.getName() == null || tblModuleRequest.getName().equals("")) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_NAME);
            fieldError.add(error);
        }
        if (tblModuleRequest.getPassword() == null || tblModuleRequest.getPassword().equals("")) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_PASSWORD);
            fieldError.add(error);
        }
        if (tblModuleRequest.getRoleId()==0) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_ROLE);
            fieldError.add(error);
        }
        return fieldError;
    }

    public static List<Error> updateUserValidator(UpdateUserRequest updateUserRequest) {

        List<Error> fieldError = new ArrayList<>();
        if (updateUserRequest.getUserId()==0) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_ROLE);
            fieldError.add(error);
        }
        return fieldError;
    }

    public static List<Error> getAllUserValidator(GetAllUsersRequest getAllUsersRequest) {

        List<Error> fieldError = new ArrayList<>();
        if (getAllUsersRequest.getName()==null||getAllUsersRequest.getRole()==null||getAllUsersRequest.getUser()==null||getAllUsersRequest.getStatus()==null||getAllUsersRequest.getDate()==null||getAllUsersRequest.getRole()==null) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_USERS);
            fieldError.add(error);
        }
        return fieldError;
    }

    public static List<Error> getAllMerchantValidator(GetAllMerchantRequest getAllMerchantRequest) {
        List<Error> fieldError = new ArrayList<>();
        if (getAllMerchantRequest.getMerchantName()==null||getAllMerchantRequest.getMerchantAccountNo()==null||getAllMerchantRequest.getMerchantCreationDate()==null||getAllMerchantRequest.getCreatedBy()==null||getAllMerchantRequest.getStatus()==null) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_USERS);
            fieldError.add(error);
        }
        return fieldError;

    }

    public static List<Error> getValidatorId(int userId) {
        List<Error> fieldError = new ArrayList<>();
        if (userId!=0) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_USERS);
            fieldError.add(error);
        }
        return fieldError;
    }

    public static List<Error> getLoginValidator(LoginRequest loginRequest) {
        List<Error> fieldError = new ArrayList<>();
        if (loginRequest.getUserName()==null && loginRequest.getUserPassword()==null) {
            Error error = new Error();
            error.setErrorCode(Constants.fieldValidationCode);
            error.setErrorDescr(Constants.GET_USERS);
            fieldError.add(error);
        }
        return fieldError;

    }
}

