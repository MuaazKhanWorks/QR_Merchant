package com.mfs.merchantQR.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.service.MerchantQrService;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2response.getResponseCode()2
 * Time: 11:54 AM
 * Project : jcash
 */


@RestControllerAdvice
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(Constants.requestMapping)
public class LoginPostApi extends AbstarctApi {



    @Autowired
    private MerchantQrService merchantQrService;


    //Login
//    @RequestMapping(value = Constants.loginEndPoint, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> accountLogin(@Valid @RequestBody String loginRequest1, HttpServletRequest request) throws Exception {
//
//        String className = this.getClass().getSimpleName();
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        String pakageName = this.getClass().getPackageName();
//        TblResponseMessage tblResponseMessage = null;
//        LoginResponse loginResponse = new LoginResponse();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Request jsonRequest = convertStringToRequestObject(loginRequest1);
//        LoginRequest loginRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), LoginRequest.class);
//        logs(Constants.loginEndPoint, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
////        TblUser tblUser = usrMngtNonFinService.checkUserPassword(loginRequest.getUserName(), loginRequest.getUserPassword());
//        TblUser tblUser = usrMngtNonFinService.checkUserNameAndPassWord(loginRequest.getUserName(), loginRequest.getUserPassword());
////        if(tblUser!=null) {
////            TblAppUser tblAppUser = usrMngtNonFinService.findByPassword(loginRequest.getUserPassword());
//        if (tblUser != null && tblUser.getUserId() > 0) {
//
//            ////// GET ALL MENU AGIANST ROLE  //////////////////////////////////////////////////
//            List<ModuleDtoForQuery> tblUserRole = usrMngtNonFinService.getRoleAgainstUser(tblUser.getUserId());
//            List<Menu> menus = new ArrayList<>();
//            if (tblUserRole != null && tblUserRole.size() > 0) {
//                for (ModuleDtoForQuery moduleDtoForQuery : tblUserRole) {
//                    Menu menu = null;
//                    List<TblModule> tblModules = usrMngtNonFinService.getRoleWiseModules(tblUser.getUserId(), moduleDtoForQuery.getModuleId());
//                    if (tblModules != null && tblModules.size() > 0) {
//
//                        for (TblModule tblModule : tblModules) {
//                            List<Menu> childMenus = new ArrayList<>();
//                            Menu childMenu = null;
////                            List<TblRoleRight> tblPages = usrMngtNonFinService.getAllRoleRightsbyRoleId("1");
////                            if (tblPages != null && tblPages.size() > 0) {
//                            for (TblMenu tblPage : tblModule.getTblMenus()) {
//
//                                childMenu = new Menu();
//                                childMenu.setLabel(tblPage.getMenuDescr());
//                                childMenu.setTo(tblPage.getMenuUrl());
//                                childMenu.setUpdateAllowed(tblPage.getUpdateAllowed());
//                                childMenu.setInsertAllowed(tblPage.getInserAllowed());
//                                childMenu.setIcon(tblPage.getIconPath());
//
//                                childMenus.add(childMenu);
//                            }
//
////                            }
//
//                            menu = new Menu();
//                            menu.setLabel(tblModule.getModuleDescr());
//                            menu.setItems(childMenus);
//                            menu.setIcon("pi-angle-right");
//
//                            menus.add(menu);
//                        }
//                    }
//                }
//
//
//                TblUserLoginHistory tblUserLoginHistory = new TblUserLoginHistory();
//
//                tblUser.setUserId(tblUser.getUserId());
//                tblUserLoginHistory.setTblUser(tblUser);
//                tblUserLoginHistory.setLoginDate(new Date());
//
//                tblUserLoginHistory = usrMngtNonFinService.saveUserLoginHistory(tblUserLoginHistory);
//
//                JWTSecurity jwtSecurity = new JWTSecurity();
//                Map<String, Object> claims = new HashMap<>();
//                AESencryption aeSencryption = new AESencryption();
//
//                claims.put(Constants.userIdVar, String.valueOf(tblUser.getUserId()));
//                claims.put(Constants.loginVar, String.valueOf(tblUserLoginHistory.getUserLoginHistoryId()));
//
//                String jwt = jwtSecurity.createJWTWithClaims(Constants.jsCashUserVar, claims);
//
//
//                loginResponse.setLogin(true);
//                loginResponse.setMenu(menus);
//                loginResponse.setTblUser(tblUser);
//                loginResponse.setToken("Bearer " +aeSencryption.encryptwith256(jwt));
//
//
//                Response response = new Response();
//                response.setPayLoad(loginResponse);
//                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.successVar);
//                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//                response.setMessage(Constants.successVar);
//                return convertStringToResponseObject(response, response.getResponseCode());
//
//
//            } else {
//                Response response = new Response();
//                response.setPayLoad(loginResponse);
//                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.noRolesAttached);
//                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//                response.setMessage(Constants.noRolesAttached);
//                logs(Constants.loginEndPoint, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, response);
//                return convertStringToResponseObject(response, response.getResponseCode());
//            }
//
//            ////////////////////////////////////////////////////////////////
//        } else {
//            Response response = new Response();
//            response.setPayLoad(loginResponse);
//            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.usernamePasswordInvalid);
//            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//            response.setMessage(Constants.usernamePasswordInvalid);
//            logs(Constants.loginEndPoint, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, response);
//            return convertStringToResponseObject(response, response.getResponseCode());
//        }
//
//    }
//
//    //Logout
//    @RequestMapping(value = Constants.logOutEndPoint, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> logout(HttpServletRequest request) {
//        String className = this.getClass().getSimpleName();
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        String pakageName = this.getClass().getPackageName();
//        logs(Constants.logOutEndPoint, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
//        TblResponseMessage tblResponseMessage = null;
//        TokenData loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
//        if (loggedUserDetail != null) {
//            TblUserLoginHistory tblUserLoginHistory = usrMngtNonFinService.logoutUser(loggedUserDetail.getUserId());
//            if (tblUserLoginHistory != null) {
//                Response response = new Response();
//                response.setPayLoad(null);
//                tblResponseMessage = usrMngtNonFinService.findByResponseMessageDescr(Constants.loggedOutVar);
//                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//                response.setMessage(Constants.loggedOutVar);
//                logs(Constants.logOutEndPoint, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                return convertStringToResponseObject(response, response.getResponseCode());
//            } else {
//                Response response = new Response();
//                response.setPayLoad(null);
//                tblResponseMessage = usrMngtNonFinService.findByResponseMessageDescr(Constants.errorWhileLoggingOff);
//                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//                response.setMessage(Constants.errorWhileLoggingOff);
//                logs(Constants.logOutEndPoint, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                return convertStringToResponseObject(response, response.getResponseCode());
//            }
//        } else {
//            Response response = new Response();
//            response.setPayLoad(null);
//            tblResponseMessage = usrMngtNonFinService.findByResponseMessageDescr(Constants.usernamePasswordInvalid);
//            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//            response.setMessage(Constants.usernamePasswordInvalid);
//            logs(Constants.logOutEndPoint, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//            return convertStringToResponseObject(response, response.getResponseCode());
//        }
//
//    }
//
//
//    @RequestMapping(value = Constants.generateOtp, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> generateOtp(@RequestBody String data, HttpServletRequest request) throws JsonProcessingException {
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Request jsonRequest = convertStringToRequestObject(data);
//        Response response = new Response();
//        TblResponseMessage tblResponseMessage;
//        logs(Constants.generateOtp, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
//
//        Response generateOtpResp = getResponseFromPostAPIData(createHeaderMapBackOffice(request.getHeader(Constants.authorization)), createPostParamBackOffice(jsonRequest), generateOtpApiUrl);
//        GenerateEmailOtpResponse generateOtpResponse = objectMapper.readValue(convertObjecttoJson(generateOtpResp.getPayLoad()), GenerateEmailOtpResponse.class);
//        generateOtpResponse.setOtpin(Constants.empty);
//        generateOtpResp.setPayLoad(generateOtpResponse);
//        if (generateOtpResp != null) {
//            tblResponseMessage = usrMngtNonFinService.findByResponseMessageDescr(generateOtpResp.getMessage());
//            response = generateOtpResp;
//            response.setPayLoad(generateOtpResp.getPayLoad());
//            response.setErrors(generateOtpResp.getErrors());
//            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//            response.setMessage(tblResponseMessage.getResponseMessageDescr());
//            logs(Constants.generateOtp, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//        } else {
//            tblResponseMessage = usrMngtNonFinService.findByResponseMessageDescr(Constants.recordNotFound);
//            response.setPayLoad(null);
//            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//            response.setErrors(generateOtpResp.getErrors());
//            response.setMessage(tblResponseMessage.getResponseMessageDescr());
//            logs(Constants.generateOtp, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//        }
//
//
//        return convertStringToResponseObject(response, response.getResponseCode());
//    }
//
//    @RequestMapping(value = Constants.verifyOtp, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> verifyOtp(@RequestBody String data, HttpServletRequest request) throws JsonProcessingException {
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Request jsonRequest = convertStringToRequestObject(data);
//        Response response = new Response();
//        TblResponseMessage tblResponseMessage;
//        logs(Constants.verifyOtp, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
//        VerifyOtpRequest verifyOtpRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), VerifyOtpRequest.class);
//        jsonRequest.setPayLoad(verifyOtpRequest);
//        Response verifyOtpResp = getResponseFromPostAPIData(createHeaderMapBackOffice(request.getHeader(Constants.authorization)), createPostParamBackOffice(jsonRequest), verifyOtpUrl);
//
//        return convertStringToResponseObject(verifyOtpResp, verifyOtpResp.getResponseCode());
//    }
//
//    @RequestMapping(value = Constants.resetPassword, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> resetPassword(@Valid @RequestBody String loginRequest1, HttpServletRequest request) throws JsonProcessingException {
//
//        String className = this.getClass().getSimpleName();
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        String pakageName = this.getClass().getPackageName();
//        TblResponseMessage tblResponseMessage = null;
//        Response response = new Response();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Request jsonRequest = convertStringToRequestObject(loginRequest1);
//        logs(Constants.resetPassword, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
//        LoginRequest loginRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), LoginRequest.class);
//
//        TblUser tblUser = usrMngtNonFinService.updateUserByEmail(loginRequest);
//
//        if (tblUser != null) {
//            tblResponseMessage = usrMngtNonFinService.findByResponseMessageDescr(Constants.successVar);
//            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//            response.setMessage(Constants.successVar);
//        } else {
//            tblResponseMessage = usrMngtNonFinService.findByResponseMessageDescr(Constants.recordNotUpdated);
//            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
//            response.setMessage(Constants.recordNotUpdated);
//        }
//
//        return convertStringToResponseObject(response, response.getResponseCode());
//    }


    @PostMapping(value = Constants.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> loginMember(@RequestBody String data, HttpServletRequest request) throws Exception {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        Request jsonRequest = convertStringToRequestObject(data);
        Response response = new Response();
        AESencryption aes256Encryption=new AESencryption();
        logs(Constants.LOGIN, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
        LoginRequest loginRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), LoginRequest.class);
        List<Error> validator = FieldsValidator.getLoginValidator(loginRequest);
        if (validator.size() <= 0) {
            TblUser user = merchantQrService.loginMember(loginRequest.getUserName(), loginRequest.getUserPassword());
            if (user != null) {
                JWTSecurityToken jwtSecurityToken = new JWTSecurityToken();
                Map<String, Object> claims = new HashMap<>();

                claims.put(Constants.USER_ID_VAR, String.valueOf(user.getUserId()));
                user.setToken(Constants.BEARER + aes256Encryption.encryptwith256(jwtSecurityToken.createJWTWithClaims(Constants.MFS, claims)));
                TblResponseMessage tblResponseMessage;
                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
                response.setPayLoad(user);
                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage.getResponseMessageDescr());

            } else {
                setResponse(response, Constants.RECORD_NOT_FOUND, "");

            }
        } else {
            TblResponseMessage tblResponseMessage;
            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.validationFailed);
            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
            response.setMessage(tblResponseMessage.getResponseMessageDescr());

        }
        logs(Constants.LOGIN, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }


}
