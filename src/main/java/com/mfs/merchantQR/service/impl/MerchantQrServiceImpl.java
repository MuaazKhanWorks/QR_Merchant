/*Author Name:ahmad.raza
Project Name: merchantQR
Package Name:com.mfs.merchantQR.service.impl
Class Name: MerchantQrServiceImpl
Date and Time:12/13/2023 11:26 AM
Version:1.0*/
package com.mfs.merchantQR.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.controller.AbstarctApi;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.repo.TblResponseMessageRepo;
import com.mfs.merchantQR.repo.TblRoleRepo;
import com.mfs.merchantQR.repo.TblUserRepo;
import com.mfs.merchantQR.repo.TblUserRoleRepo;
import com.mfs.merchantQR.service.CommonService;
import com.mfs.merchantQR.service.MerchantQrService;
import com.mfs.merchantQR.utils.Constants;
import com.mfs.merchantQR.utils.FieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.instrument.classloading.WeavingTransformer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MerchantQrServiceImpl extends AbstarctApi implements MerchantQrService {

    @Autowired
    private TblResponseMessageRepo tblResponseMessageRepo;

    @Autowired
    private TblUserRepo tblUserRepo;

    @Autowired
    private TblUserRoleRepo tblUserRoleRepo;

    @Autowired
    private TblRoleRepo tblRoleRepo;

    @Autowired
    private CommonService commonService;


    @Override
    public TblResponseMessage findByResponseMessageDescr(String success) {
        return tblResponseMessageRepo.findByResponseMessageDescr(success);
    }

    @Override
    public TblUser saveUser(TblUserRequest tblUserRequest, int userId) throws ParseException {
        TblUser tblUser = new TblUser();
        tblUser.setName(tblUserRequest.getName());
        tblUser.setPassword(tblUserRequest.getPassword());
        if (tblUserRequest.getEmail() != null && !tblUserRequest.getEmail().equalsIgnoreCase("")) {
            tblUser.setEmail(tblUserRequest.getEmail());
        }
        tblUser.setIsActive("N");
        tblUser.setCreatedate(new Date());
        tblUser.setCreateuser(userId);
        LkpStatus lkpStatus = new LkpStatus();
        lkpStatus.setStatusId(tblUserRequest.getStatusId());
        tblUser.setStatusId(lkpStatus.getStatusId());
        tblUser = tblUserRepo.saveAndFlush(tblUser);
        if (tblUser != null && tblUser.getUserId() > 0) {
            if (Long.valueOf(tblUserRequest.getRoleId()) > 0) {
                TblUserRole tblUserRole = new TblUserRole();
                TblRole tblRole1 = tblRoleRepo.findById(Long.valueOf(tblUserRequest.getRoleId())).orElse(null);
                tblRole1.setRoleId(tblRole1.getRoleId());
                tblUserRole.setTblUser(tblUser);
                tblUserRole.setTblRole(tblRole1);
                tblUserRole.setIsActive("Y");
                tblUserRole.setCreateuser(tblUser.getCreateuser());
                tblUserRole.setCreatedate(new Date());

                tblUserRoleRepo.save(tblUserRole);
            }

//            }
            return tblUser;

        } else {
            return tblUser;
        }

    }


    public Response saveUserRequest(TokenData loggedUserDetail, TblUserRequest tblUserRequest) throws JsonProcessingException, ParseException {
        Response response = new Response();
        response = commonService.makerChecker(Constants.TBL_USER, Constants.CREATE_USER_FORM_NAME, Constants.REQUEST_TYPE_SAVE, Constants.REQUEST_TYPE_SAVE, String.valueOf(loggedUserDetail.getUserId()), Constants.EMPTY, BigDecimal.valueOf(loggedUserDetail.getUserId()));
        if (response.getResponseCode() != null && response.getResponseCode().equals(Constants.MAKER_CHECKER_NOT_APPLICABLE_CODE)) {
            tblUserRequest.setIsActive(Constants.SET_YES);
            TblUser tblUser = saveUser(tblUserRequest, (int) loggedUserDetail.getUserId());
            if (tblUser != null) {
                setResponse(response, Constants.SUCCESS, tblUser);
            } else {
                setResponse(response, Constants.RECORD_NOT_SAVED, Constants.EMPTY);
            }
        }
        return response;
    }


    @Override
    public TblUser checkUserExistance(String userName) {
        return tblUserRepo.findByName(userName);
    }

    public void setResponse(Response response, String type, Object s) {
        TblResponseMessage tblResponseMessage;
        tblResponseMessage = commonService.getResponseMessageByResponseDescr(type);
        response.setPayLoad(s);
        response.setResponseCode(tblResponseMessage.getResponseMessageCode());
        response.setMessage(tblResponseMessage.getResponseMessageDescr());
    }


    @Override
    public Response updateUserRequest(TokenData loggedUserDetail, UpdateUserRequest updateUserRequest) throws JsonProcessingException {
        Response response = new Response();
        ObjectMapper objectMapper = new ObjectMapper();
        TblUser tblUser = tblUserRepo.findById(updateUserRequest.getUserId()).orElse(null);
        if (tblUser != null) {
            String updateJson = objectMapper.writeValueAsString(updateUserRequest);
            response = commonService.makerChecker(Constants.TBL_USER, Constants.UPDATE_USER_FORM_NAME, Constants.REQUEST_TYPE_UPDATE, Constants.REQUEST_TYPE_UPDATE, String.valueOf(tblUser.getUserId()), updateJson, BigDecimal.valueOf(loggedUserDetail.getUserId()));
            if (response.getResponseCode() != null && response.getResponseCode().equals(Constants.MAKER_CHECKER_NOT_APPLICABLE_CODE)) {
                tblUser = updateUser(updateUserRequest, (int) loggedUserDetail.getUserId());

                if (tblUser != null) {
                    setResponse(response, Constants.SUCCESS, tblUser);
                } else {
                    setResponse(response, Constants.RECORD_NOT_UPDATED, Constants.EMPTY);
                }
            }

        } else {
            setResponse(response, Constants.RECORD_NOT_FOUND, Constants.EMPTY);
        }

        return response;
    }

    private TblUser updateUser(UpdateUserRequest updateUserRequest, int valueOf) {
        TblUser tblUser = new TblUser();
        tblUser.setName(updateUserRequest.getName());
        tblUser.setPassword(updateUserRequest.getPassword());
        if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().equalsIgnoreCase("")) {
            tblUser.setEmail(updateUserRequest.getEmail());
        }
        tblUser.setIsActive("N");
        tblUser.setCreatedate(new Date());
        tblUser.setCreateuser(valueOf);
        LkpStatus lkpStatus = new LkpStatus();
        lkpStatus.setStatusId(updateUserRequest.getStatusId());
        tblUser.setStatusId(lkpStatus.getStatusId());
        tblUser.setLastupdatedate(new Date());
        tblUser.setLastupdateuser(valueOf);
        tblUser.setUpdateindex(tblUser.getUpdateindex() != 0 ? tblUser.getUpdateindex() + 1 : 1);
        tblUser = tblUserRepo.saveAndFlush(tblUser);
        if (tblUser != null && tblUser.getUserId() > 0) {
            if (Long.valueOf(updateUserRequest.getRoleId()) > 0) {
                TblUserRole tblUserRole = new TblUserRole();
                TblRole tblRole1 = tblRoleRepo.findById(Long.valueOf(updateUserRequest.getRoleId())).orElse(null);
                tblRole1.setRoleId(tblRole1.getRoleId());
                tblUserRole.setTblUser(tblUser);
                tblUserRole.setTblRole(tblRole1);
                tblUserRole.setIsActive("Y");
                tblUserRole.setCreateuser(tblUser.getCreateuser());
                tblUserRole.setCreatedate(new Date());

                tblUserRoleRepo.save(tblUserRole);
            }

//            }
            return tblUser;

        } else {
            return tblUser;
        }
    }
}