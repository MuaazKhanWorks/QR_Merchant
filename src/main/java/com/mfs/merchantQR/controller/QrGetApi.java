/*Author Name:ahmad.raza
Project Name: account
Package Name:com.mfs.account.controller.Segment
Class Name: SegmentGetApi
Date and Time:7/19/2023 12:11 PM
Version:1.0*/
package com.mfs.merchantQR.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.controller.*;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.service.*;
import com.mfs.merchantQR.utils.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RestControllerAdvice
@RequestMapping(Constants.requestMapping)
public class QrGetApi extends AbstarctApi {

    @Autowired
    private Environment env;
    
    @Autowired
    private MerchantQrService merchantQrService;


    @RequestMapping(value = Constants.getUserById, method = RequestMethod.GET)
    public ResponseEntity<Response> getUserById(@PathVariable int userId, HttpServletRequest request) throws JsonProcessingException {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Response response = new Response();
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        logs(Constants.getUserById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo, response);
        List<Error> validations = FieldsValidator.getValidatorId(userId);
        TblResponseMessage tblResponseMessage;
        if (validations.size() <= 0) {
            TblUser tblUser = merchantQrService.getUserById(userId);
            if (tblUser != null) {
                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
                response.setPayLoad(tblUser);
                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                logs(Constants.getUserById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
            } else {
                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotFound);
                response.setPayLoad(tblUser);
                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                logs(Constants.getUserById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
            }
        } else {
            response.setResponseCode(Constants.fieldValidationCode);
            response.setErrors(validations);
            response.setMessage(Constants.validationFailed);
        }
        return convertStringToResponseObject(response, response.getResponseCode());
    }

    @RequestMapping(value = Constants.getUserUpdateCheckerById, method = RequestMethod.GET)
    public ResponseEntity<Response> getUserUpdateCheckerById(@PathVariable int mcRequestId, HttpServletRequest request) throws JsonProcessingException {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Response response = new Response();
        TblResponseMessage tblResponseMessage=null;
        String moduleId = env.getProperty(Constants.moduleIdKey);
        logs(Constants.getUserUpdateCheckerById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo, response);
        List<Error> validations = FieldsValidator.getValidatorId(mcRequestId);
        if (validations.size() <= 0) {

            TblMcRequest tblMcRequest = merchantQrService.getUserUpdateCheckerById(mcRequestId);
            TblUser tblUser =new TblUser();
            UpdateUserRequest updateUserRequest = new UpdateUserRequest();
            ObjectMapper objectMapper = new ObjectMapper();
            updateUserRequest=objectMapper.readValue(tblMcRequest.getUpdateJson(), UpdateUserRequest.class);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(updateUserRequest, tblUser);

            if (tblUser != null) {
                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
                response.setPayLoad(tblUser);
                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                logs(Constants.getUserUpdateCheckerById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
            } else {
                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotFound);
                response.setPayLoad(tblUser);
                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
                logs(Constants.getUserUpdateCheckerById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
            }
        } else {
            response.setResponseCode(Constants.fieldValidationCode);
            response.setErrors(validations);
            response.setMessage(Constants.validationFailed);
        }
        return convertStringToResponseObject(response, response.getResponseCode());
    }


}