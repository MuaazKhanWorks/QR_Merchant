/*Author Name:ahmad.raza
Project Name: Configurations
Package Name:com.mfs.configurations.controller.validators
Class Name: ValidatorsPostApi
Date and Time:3/16/2023 10:21 AM
Version:1.0*/
package com.mfs.merchantQR.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.controller.AbstarctApi;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.service.*;
import com.mfs.merchantQR.utils.Constants;
import com.mfs.merchantQR.utils.FieldsValidator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestControllerAdvice
@RequestMapping(Constants.requestMapping)
public class QrUpdateApi extends AbstarctApi {

    @Autowired
    private MerchantQrService merchantQrService;
    @Autowired
    private Environment env;

    @PostMapping(value = Constants.UPDATE_USER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updatesubscription(@RequestBody String data, HttpServletRequest request) throws JsonProcessingException, ParseException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        String moduleId = env.getProperty(Constants.moduleIdKey);
        Request jsonRequest = convertStringToRequestObject(data);
        TblResponseMessage tblResponseMessage = null;
        Response response = new Response();
        TblUser saveUser = new TblUser();
        logs(Constants.UPDATE_SUBSCRIPTION, Constants.LOG_INFO, getClass().getSimpleName(), methodName, getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
        TokenData loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader(Constants.AUTHORIZATION));
        if (loggedUserDetail != null) {
            logs(Constants.UPDATE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo + methodName, new Response());
            UpdateUserRequest updateUserRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), UpdateUserRequest.class);
            List<Error> validations = FieldsValidator.updateUserValidator(updateUserRequest);
            if (validations.size() <= 0) {
                    response  = merchantQrService.updateUserRequest( loggedUserDetail,updateUserRequest);
            } else {
                response.setResponseCode(Constants.fieldValidationCode);
                response.setErrors(validations);
                response.setMessage(Constants.validationFailed);
                return convertStringToResponseObject(response, response.getResponseCode());
            }
        } else {
            response.setPayLoad(null);
            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sessionExpired);
            response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
            response.setMessage(Constants.sessionExpired);
            logs(Constants.UPDATE_USER, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo + methodName, new Response());
            return convertStringToResponseObject(response, response.getResponseCode());
        }
        logs(Constants.UPDATE_CATEGORY, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }





}