/*Author Name:ahmad.raza
Project Name: Configurations
Package Name:com.mfs.configurations.controller.validators
Class Name: ValidatorsPostApi
Date and Time:3/16/2023 10:21 AM
Version:1.0*/
package com.mfs.merchantQR.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.PrintQr.QRCodeGenerator;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.repo.TblMerchantRepo;
import com.mfs.merchantQR.service.*;
import com.mfs.merchantQR.utils.Constants;
import com.mfs.merchantQR.utils.FieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = Constants.s1, allowedHeaders = Constants.s1)
@RestControllerAdvice
@RequestMapping(Constants.requestMapping)
public class QrPostApi extends AbstarctApi {

    @Autowired
    private MerchantQrService merchantQrService;
    @Autowired
    private Environment env;
    @Autowired
    private TblMerchantRepo tblMerchantRepo;
    @Autowired
    private CommonService commonService;

//
//    //Save User
//    @RequestMapping(value = Constants.SAVE_USER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> saveUser(@Valid @RequestBody String saveUserRequest, HttpServletRequest request) throws JsonProcessingException, ParseException {
//        String className = this.getClass().getSimpleName();
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        String pakageName = this.getClass().getPackageName();
//        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
//        TblResponseMessage tblResponseMessage = null;
//        Response response = new Response();
//        TblUser saveUser = new TblUser();
//        TokenData loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader(Constants.AUTHORIZATION));
//        if (loggedUserDetail != null) {
//            logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
//            ObjectMapper objectMapper = new ObjectMapper();
//            Request jsonRequest = convertStringToRequestObject(saveUserRequest);
//            TblUserRequest tblModuleRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), TblUserRequest.class);
//            List<Error> validations = FieldsValidator.saveUserValidator(tblModuleRequest);
//            if (validations.size() <= 0) {
//                TblUser existUser = merchantQrService.checkUserExistance(tblModuleRequest.getName());
//                if (existUser != null) {
//                    String checkMcApplicability = checkMcApplicability(jsonRequest, request.getHeader(Constants.AUTHORIZATION), Constants.TABLE_NAME_USER, Constants.FORM_NAME_USER, Constants.requestTypeSave, moduleId);
//                    response = commonService.makerChecker(Constants.TBL_API_SUBSCRIBE_TABLE_NAME, Constants.UPDATE_SUBSCRIBE_API_FORM_NAME, Constants.REQUEST_TYPE_UPDATE, Constants.REQUEST_TYPE_UPDATE, String.valueOf(tblApiSubscribe.getApiSubscribeId()), updateJson, BigDecimal.valueOf(loggedUserDetail.getUserId()));
//                    if (checkMcApplicability != null) {
//                        Response response1 = objectMapper.readValue(checkMcApplicability, Response.class);
//                        ProcedureResponse procedureResponse = objectMapper.readValue(convertObjecttoJson(response1.getPayLoad()), ProcedureResponse.class);
//                        if (procedureResponse != null && procedureResponse.getMcApplicability() > 0 && procedureResponse.getStatus() == 1) {
//                            tblModuleRequest.setIsActive(Constants.setIsActiveNo);
//                            tblModuleRequest.setStatusId(1);
//                            saveUser = merchantQrService.saveUser(tblModuleRequest, (int) loggedUserDetail.getUserId());
//                            if (saveUser != null) {
//                                String resultMcRequest = mcRequest(jsonRequest, request.getHeader(Constants.AUTHORIZATION), Constants.FORM_NAME_USER, BigDecimal.valueOf(loggedUserDetail.getUserId()), Constants.empty, Constants.empty, Constants.TABLE_NAME_USER, Constants.requestTypeSave, Constants.empty, Constants.empty, saveUser.getUserId(), moduleId);
//                                if (resultMcRequest != null) {
//                                    Response response2 = objectMapper.readValue(resultMcRequest, Response.class);
//                                    McResponse mcRequestResponse = objectMapper.readValue(convertObjecttoJson(response2.getPayLoad()), McResponse.class);
//                                    if (mcRequestResponse != null && mcRequestResponse.getStatus() == 1) {
//                                        response = new Response();
//                                        tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.successVar);
//                                        response.setPayLoad(saveUser);
//                                        response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                                        response.setMessage(mcRequestResponse.getStatusDecsr());
//                                        logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                        return convertStringToResponseObject(response, response.getResponseCode());
//                                    } else {
//                                        response = new Response();
//                                        tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotSaved);
//                                        response.setPayLoad(saveUser);
//                                        response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                                        response.setMessage(mcRequestResponse.getStatusDecsr());
//                                        logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                        return convertStringToResponseObject(response, response.getResponseCode());
//                                    }
//                                } else {
//                                    response = new Response();
//                                    response.setPayLoad(saveUser);
//                                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.WORK_FLOW_ERROR);
//                                    response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                                    response.setMessage(tblResponseMessage.getResponseMessageDescr());
//                                    logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                                    return convertStringToResponseObject(response, response.getResponseCode());
//                                }
//                            } else {
//                                response.setPayLoad(saveUser);
//                                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotSaved);
//                                response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                                response.setMessage(Constants.recordNotSaved);
//                                logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                                return convertStringToResponseObject(response, response.getResponseCode());
//                            }
//                        } else {
//                            tblModuleRequest.setIsActive(tblModuleRequest.getIsActive());
//                            tblModuleRequest.setStatusId(2);
//                            saveUser = merchantQrService.saveUser(tblModuleRequest, (int) loggedUserDetail.getUserId());
//                            if (saveUser != null) {
//                                response.setPayLoad(saveUser);
//                                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.successVar);
//                                response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                                response.setMessage(Constants.successVar);
//                                logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                                return convertStringToResponseObject(response, response.getResponseCode());
//                            } else {
//                                response.setPayLoad(saveUser);
//                                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotSaved);
//                                response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                                response.setMessage(Constants.recordNotSaved);
//                                logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                                return convertStringToResponseObject(response, response.getResponseCode());
//                            }
//                        }
//                    } else {
//                        response = new Response();
//                        response.setPayLoad(saveUser);
//                        tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.WORK_FLOW_ERROR);
//                        response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                        response.setMessage(tblResponseMessage.getResponseMessageDescr());
//                        logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                        return convertStringToResponseObject(response, response.getResponseCode());
//                    }
//                } else {
//                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.USER_ALREADY_EXIST);
//                    response.setPayLoad(saveUser);
//                    response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//                    response.setMessage(tblResponseMessage.getResponseMessageDescr());
//                    logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//                    return convertStringToResponseObject(response, response.getResponseCode());
//                }
//            } else {
//                response.setResponseCode(Constants.fieldValidationCode);
//                response.setErrors(validations);
//                response.setMessage(Constants.validationFailed);
//                return convertStringToResponseObject(response, response.getResponseCode());
//            }
//        } else {
//            response.setPayLoad(null);
//            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sessionExpired);
//            response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//            response.setMessage(Constants.sessionExpired);
//            logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//            return convertStringToResponseObject(response, response.getResponseCode());
//        }
//    }


    @PostMapping(value = Constants.SAVE_USER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createuser(@RequestBody String data, HttpServletRequest request) throws JsonProcessingException, ParseException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        String moduleId = env.getProperty(Constants.moduleIdKey);
        Request jsonRequest = convertStringToRequestObject(data);
        TblResponseMessage tblResponseMessage;
        Response response = new Response();
        TblUser saveUser = new TblUser();
        logs(Constants.SAVE_USER, Constants.LOG_INFO, getClass().getSimpleName(), methodName, getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
        TokenData loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader(Constants.AUTHORIZATION));
//        if (loggedUserDetail != null) {
            logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
            TblUserRequest tblModuleRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), TblUserRequest.class);
            List<Error> validations = FieldsValidator.saveUserValidator(tblModuleRequest);
            if (validations.size() <= 0) {
                TblUser existUser = merchantQrService.checkUserExistance(tblModuleRequest.getName());
                if (existUser == null) {
                   response  = merchantQrService.saveUserRequest( loggedUserDetail,tblModuleRequest);
                } else {
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.USER_ALREADY_EXIST);
                    response.setPayLoad(response.getPayLoad());
                    response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage.getResponseMessageDescr());
                    logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
                    return convertStringToResponseObject(response, response.getResponseCode());
                }
            } else {
                response.setResponseCode(Constants.fieldValidationCode);
                response.setErrors(validations);
                response.setMessage(Constants.validationFailed);
                return convertStringToResponseObject(response, response.getResponseCode());
            }
//        } else {
//            response.setPayLoad(null);
//            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sessionExpired);
//            response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
//            response.setMessage(Constants.sessionExpired);
//            logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
//            return convertStringToResponseObject(response, response.getResponseCode());
//        }
        logs(Constants.SAVE_USER, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }

    @PostMapping(value = Constants.GET_ALL_USERS, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getalluser(@RequestBody String data, HttpServletRequest request) throws JsonProcessingException, ParseException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        String moduleId = env.getProperty(Constants.moduleIdKey);
        Request jsonRequest = convertStringToRequestObject(data);
        TblResponseMessage tblResponseMessage;
        Response response = new Response();
        logs(Constants.GET_ALL_USERS, Constants.LOG_INFO, getClass().getSimpleName(), methodName, getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
        TokenData loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader(Constants.AUTHORIZATION));
        loggedUserDetail=new TokenData();
        loggedUserDetail.setUserId(1);
        if (loggedUserDetail != null) {
            logs(Constants.GET_ALL_USERS, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
            GetAllUsersRequest getAllUsersRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), GetAllUsersRequest.class);
            List<Error> validations = FieldsValidator.getAllUserValidator(getAllUsersRequest);
            if (validations.size() <= 0) {
                    List<TblUser> tblUsers  = merchantQrService.getAllUsersBySearch(getAllUsersRequest);
                if (tblUsers != null && tblUsers.size() > 0) {
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
                    response.setPayLoad(tblUsers);
                    response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                    logs(Constants.GET_ALL_USERS, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                } else {
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotFound);
                    response.setPayLoad(tblUsers);
                    response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                    logs(Constants.GET_ALL_USERS, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                }
            } else {
                response.setResponseCode(Constants.fieldValidationCode);
                response.setErrors(validations);
                response.setMessage(Constants.validationFailed);
                return convertStringToResponseObject(response, response.getResponseCode());
            }
        } else {
            response.setPayLoad(null);
            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sessionExpired);
            response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
            response.setMessage(Constants.sessionExpired);
            logs(Constants.GET_ALL_USERS, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
            return convertStringToResponseObject(response, response.getResponseCode());
        }
        logs(Constants.GET_ALL_USERS, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }


      @PostMapping(value = Constants.GET_ALL_MERCHANT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getallmerchant(@RequestBody String data, HttpServletRequest request) throws JsonProcessingException, ParseException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        String moduleId = env.getProperty(Constants.moduleIdKey);
        Request jsonRequest = convertStringToRequestObject(data);
        TblResponseMessage tblResponseMessage = null;
        Response response = new Response();
        logs(Constants.GET_ALL_MERCHANT, Constants.LOG_INFO, getClass().getSimpleName(), methodName, getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
        TokenData loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader(Constants.AUTHORIZATION));
        if (loggedUserDetail != null) {
            logs(Constants.GET_ALL_MERCHANT, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
            GetAllMerchantRequest getAllMerchantRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), GetAllMerchantRequest.class);
            List<Error> validations = FieldsValidator.getAllMerchantValidator(getAllMerchantRequest);
            if (validations.size() <= 0) {
                List<TblMerchant> tblMerchants  = merchantQrService.getAllMerchantBySearch(getAllMerchantRequest);
                if (tblMerchants != null && tblMerchants.size() > 0) {
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
                    response.setPayLoad(tblMerchants);
                    response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                    logs(Constants.GET_ALL_MERCHANT, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                } else {
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotFound);
                    response.setPayLoad(tblMerchants);
                    response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                    logs(Constants.GET_ALL_MERCHANT, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                }
            } else {
                response.setResponseCode(Constants.fieldValidationCode);
                response.setErrors(validations);
                response.setMessage(Constants.validationFailed);
                return convertStringToResponseObject(response, response.getResponseCode());
            }
        } else {
            response.setPayLoad(null);
            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sessionExpired);
            response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
            response.setMessage(Constants.sessionExpired);
            logs(Constants.GET_ALL_MERCHANT, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
            return convertStringToResponseObject(response, response.getResponseCode());
        }
        logs(Constants.GET_ALL_MERCHANT, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }

    @PostMapping(value = Constants.UPDATE_DOWNLAOD_STATUS, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateDownloadStatus(@RequestBody String data, HttpServletRequest request) throws JsonProcessingException, ParseException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        String moduleId = env.getProperty(Constants.moduleIdKey);
        Request jsonRequest = convertStringToRequestObject(data);
        TblResponseMessage tblResponseMessage;
        Response response = new Response();
        logs(Constants.UPDATE_DOWNLAOD_STATUS, Constants.LOG_INFO, getClass().getSimpleName(), methodName, getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
        TokenData loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader(Constants.AUTHORIZATION));
        if (loggedUserDetail != null) {
        logs(Constants.UPDATE_DOWNLAOD_STATUS, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
        UpdateDownloadStatusRequest updateDownloadStatusRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), UpdateDownloadStatusRequest.class);
        List<Error> validations = FieldsValidator.updateDownloadStatusValidator(updateDownloadStatusRequest);
        if (validations.size() <= 0) {
                TblMerchant tblMerchant  = merchantQrService.updateDownloadStatus( loggedUserDetail,updateDownloadStatusRequest);
                if(tblMerchant!=null){
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
                    response.setPayLoad(tblMerchant);
                    response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                    logs(Constants.UPDATE_DOWNLAOD_STATUS, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                } else {
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotSaved);
                    response.setPayLoad(tblMerchant);
                    response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                    logs(Constants.UPDATE_DOWNLAOD_STATUS, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                }
        } else {
            response.setResponseCode(Constants.fieldValidationCode);
            response.setErrors(validations);
            response.setMessage(Constants.validationFailed);
            return convertStringToResponseObject(response, response.getResponseCode());
        }
        } else {
            response.setPayLoad(null);
            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sessionExpired);
            response.setResponseCode(moduleId + tblResponseMessage.getResponseMessageCode());
            response.setMessage(Constants.sessionExpired);
            logs(Constants.SAVE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
            return convertStringToResponseObject(response, response.getResponseCode());
        }
        logs(Constants.UPDATE_DOWNLAOD_STATUS, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }



    @RequestMapping(value = Constants.QR_CODE, method = RequestMethod.GET)
    public ResponseEntity<String> generateBarcodeImage(@PathVariable Integer merchantId) {

        TblMerchant tblMerchant = tblMerchantRepo.findById(merchantId).orElse(null);

        if (tblMerchant != null) {

            // Generate QR code image as Base64 using the QRCodeGenerator class
            String qrCodeBase64 = QRCodeGenerator.generateQRCode(tblMerchant.getQrCode());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);

            return new ResponseEntity<>(qrCodeBase64, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}