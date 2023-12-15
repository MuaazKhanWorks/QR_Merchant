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
}

