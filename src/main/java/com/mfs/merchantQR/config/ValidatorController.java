package com.mfs.merchantQR.config;/*
Author Name: ahmad.raza

Project Name: usermanagement

Package Name: org.mfs.usermanagement.config

Class Name: ValidatorController

Date and Time:2/10/2023 5:43 PM

Version:1.0
*/


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.merchantQR.controller.AbstarctApi;
import com.mfs.merchantQR.dto.Response;
import com.mfs.merchantQR.model.TblResponseMessage;
import com.mfs.merchantQR.service.MerchantQrService;
import com.mfs.merchantQR.utils.Constants;
//import org.primefaces.shaded.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.rmi.ConnectIOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;

@RestControllerAdvice
public class ValidatorController extends AbstarctApi {
    

    @Autowired
    private MerchantQrService merchantQrService;
    
    @Autowired
    private Environment env;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.methodArgumentNotValidException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchAlgorithmException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleValidationException(NoSuchAlgorithmException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.methodArgumentNotValidException);
        Response response = new Response();
        response.setResponseCode(tblResponseMessage != null ? moduleId+tblResponseMessage.getResponseMessageCode() : moduleId+Constants.generalProcessingCode);
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe, this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

    }
        @ExceptionHandler(value = DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleDataAccessException(DataAccessException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.dataAccessException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleException(Exception ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.exception);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());

        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SocketTimeoutException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseEntity<Response> handleSocketTimeoutException(SocketTimeoutException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.socketTimeoutException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(value = JsonProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleJsonProcessingException(JsonProcessingException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.jsonProcessingException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleNullPointerException(NullPointerException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.nullPointerException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ConnectIOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleConnectIOException(ConnectIOException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.connectIOException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleIOException(IOException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.IOException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.arrayIndexOutOfBoundsException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SQLDataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleSQLDataException(SQLDataException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sqlDataException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = JsonParseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleJsonParseException(JsonParseException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.jsonParseException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleClassNotFoundException(ClassNotFoundException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.classNotFoundException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleNumberFormatException(NumberFormatException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.numberFormatException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleRuntimeException(RuntimeException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.runtimeException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleSQLException(SQLException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sqlException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(value = JSONException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Response> handleJSONException(JSONException ex) {
//        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
//        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.jsonException);
//        Response response = new Response();
//        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
//
//        response.setMessage(ex.getMessage());
////        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
//        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(value = SQLRecoverableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleSQLRecoverableException(SQLRecoverableException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.sqlRecoverableException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.illegalArgumentException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleClassCastException(ClassCastException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.classCastException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SocketException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleSocketException(SocketException ex) {
        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
        TblResponseMessage tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.socketException);
        Response response = new Response();
        response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
        
        response.setMessage(ex.getMessage());
//        logs(Constants.exceptionEndPoint, Constants.logLevelExe,  this.getClass().getSimpleName(), Constants.empty, this.getClass().getPackageName(), new Request(), Constants.endingMethod,response);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(value = RestClientException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Response> handleRestClientException(RestClientException ex) throws HttpClientErrorException, JsonProcessingException {
//        if (ex instanceof HttpStatusCodeException) {
//            String moduleId = env.getProperty(Constants.moduleIdKey).toString();
//            String errorResponse = ((HttpStatusCodeException) ex).getResponseBodyAsString();
//            ObjectMapper objectMapper = new ObjectMapper();
//            Response readValueToString = objectMapper.readValue(errorResponse, Response.class);
//            return convertStringToResponseObject(readValueToString, moduleId+readValueToString.getResponseCode());
//        } else {
//            String moduleId = env.getProperty(Constants.moduleIdKey).toString();
//            ObjectMapper objectMapper = new ObjectMapper();
//            Response readValueToString = objectMapper.readValue(Constants.empty, Response.class);
//            return convertStringToResponseObject(readValueToString, moduleId+readValueToString.getResponseCode());
//        }
//    }

}