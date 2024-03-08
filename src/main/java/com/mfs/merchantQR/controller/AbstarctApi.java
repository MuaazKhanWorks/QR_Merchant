package com.mfs.merchantQR.controller;/*
Author Name: ahmad.raza

Project Name: usermanagement

Package Name: org.mfs.usermanagement.controller.AbstarctApi

Class Name: AbstarctApi

Date and Time:2/7/2023 10:03 AM

Version:1.0
*/


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mfs.merchantQR.dto.McActionRequest;
import com.mfs.merchantQR.dto.Request;
import com.mfs.merchantQR.dto.Response;
import com.mfs.merchantQR.dto.TokenData;
import com.mfs.merchantQR.model.TblResponseMessage;
import com.mfs.merchantQR.service.MerchantQrService;
import com.mfs.merchantQR.service.impl.MerchantQrServiceImpl;
import com.mfs.merchantQR.utils.Constants;
import com.mfs.merchantQR.utils.JWTSecurity;
import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

@RestControllerAdvice
public class AbstarctApi {

    @Autowired
    private Environment env;




//    @Autowired
//    private L0Services l0Services;

    //this method is used to call external post api using rest template
//    public String getResponseFromPostAPI(Map headerMap, Map postParam, String url) throws HttpClientErrorException {
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        headers.setAll(headerMap);
//        HttpEntity<?> request = new HttpEntity<>(postParam, headers);
//        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
//        ResponseEntity<?> response = restTemplate.postForEntity(url, request, String.class);
//        String entityResponse = (String) response.getBody();
//        HttpStatus entitystatus = (HttpStatus) response.getStatusCode();
//        if (entitystatus.value() == 200) {
//            System.out.println(entityResponse);
//            if (!entityResponse.isEmpty()) {
//                return entityResponse;
//            } else {
//                return null;
//            }
//        } else {
//            return entityResponse;
//        }
//    }

    public String getResponseFromPostAPI(Map headerMap, Map postParam, String url) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.setAll(headerMap);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(postParam, headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ResponseEntity<String> response;

        try {
            response = restTemplate.postForEntity(url, request, String.class);
        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx) here
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx) here
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }

        HttpStatus entityStatus = response.getStatusCode();
        String entityResponse = response.getBody();

        if (entityStatus.is2xxSuccessful()) {
            if (entityResponse != null && !entityResponse.isEmpty()) {
                return entityResponse;
            } else {
                return Constants.unhandleException;
            }
        } else {
            return entityResponse;
        }
    }


    public Request convertStringToRequestObjectData(String data) throws JsonProcessingException, JSONException {
        JSONObject jsonData = new JSONObject(data);
        ObjectMapper objectMapper = new ObjectMapper();
        Request readValueToString = objectMapper.readValue(jsonData.get(Constants.requestData).toString(), Request.class);
        return readValueToString;
    }

    public Response getResponseFromPostAPIData(Map headerMap, Map postParam, String url) throws HttpClientErrorException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.setAll(headerMap);
        HttpEntity<?> request = new HttpEntity<>(postParam, headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ResponseEntity<?> response = restTemplate.postForEntity(url, request, String.class);
        String entityResponse = (String) response.getBody();
        HttpStatus entitystatus = (HttpStatus) response.getStatusCode();
        if (entitystatus.value() == 200) {
            if (!entityResponse.isEmpty()) {
                return new Gson().fromJson(entityResponse, Response.class);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Response formDataPostRequest(String url, HttpHeaders headers, MultiValueMap<String, Object> formData) {
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> response = restTemplate.postForEntity(url, requestEntity, String.class);

        String entityResponse = (String) response.getBody();
        HttpStatus entitystatus = (HttpStatus) response.getStatusCode();
        if (entitystatus.value() == 200) {
            if (!entityResponse.isEmpty()) {
                return new Gson().fromJson(entityResponse, Response.class);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    //this method is used to set connection time and read time when call external apis
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(60000);
        clientHttpRequestFactory.setReadTimeout(60000);
        return clientHttpRequestFactory;
    }

    //this method is used to set convert string to request object
    public Request convertStringToRequestObject(String data) throws JsonProcessingException, JSONException {
        JSONObject jsonData = new JSONObject(data);
        ObjectMapper objectMapper = new ObjectMapper();
        Request readValueToString = objectMapper.readValue(jsonData.get(Constants.requestData).toString(), Request.class);
        return readValueToString;
    }


    //this method is used to send response back to end user

    public ResponseEntity<Response> convertStringToResponseObject(Response response, String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        // check response code and set http status accordingly
        if (code.equals(Constants.successCode)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().equals(Constants.success)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }else if (response.getMessage().equals(Constants.accountCreatedSucessfully)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().equals(Constants.recordExist)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        } else if (response.getMessage().equals(Constants.accountAlreadyExist)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (code.equals(Constants.recordNotFoundCode)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        } else if (response.getMessage().equals(Constants.invalidToken)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.UNAUTHORIZED);
        }
        else if(response.getMessage().equals(Constants.ACCOUNT_PARKED_FOR_APPROVAL)){
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }

    //this method is used to convert json to string
    public String convertObjecttoJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //this method is used to park logs to kafka
//    @Async
//    public String logs(String endPoint, String logLevel, String className, String methodName, String packageDetails, Request request, String message, Response resp) throws HttpClientErrorException {
//        RequestKafka requestKafka = setLogsRequestFromPostApis(endPoint, logLevel, className, methodName, packageDetails, request, message, resp);
//        String url = env.getProperty(Constants.logsurl).toString();
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        Map<String, String> headerMap = new HashMap<String, String>();
//        headerMap.put(Constants.accept, Constants.applicationJson);
//        Map<String, Object> postParam = new HashMap<String, Object>();
//        headers.setAll(headerMap);
//        postParam.put(Constants.securityVariable, requestKafka.getSecurity());
//        postParam.put(Constants.payloadVariable, requestKafka.getPayload());
//        postParam.put(Constants.indexNameVariable, Constants.configuration);
//        return getResponseFromPostAPI(headerMap, postParam, url);
//    }

//    //Logs Request From Post APIS
//    public RequestKafka setLogsRequestFromPostApis(String endPoint, String logLevel, String className, String methodName, String packageDetails, Request request, String message, Response response) {
//        RequestKafka requestKafka = new RequestKafka();
//        PayloadKafka payloadKafka = new PayloadKafka();
//        String serviceName = env.getProperty(Constants.serviceNameVar);
//        payloadKafka.setDateTime(new Date().toString());
//        payloadKafka.setEndpoint(endPoint);
//        payloadKafka.setClassName(className);
//        payloadKafka.setMethodName(methodName);
//        payloadKafka.setLoggingLevel(logLevel);
//        if (response.getResponseCode() != null && response.getResponseCode() != "") {
//            payloadKafka.setPayloadService(Constants.kafkaDataVar + convertObjecttoJson(response));
//        } else {
//            payloadKafka.setPayloadService(Constants.kafkaDataVar + convertObjecttoJson(request));
//        }
//        payloadKafka.setMessage(message);
//        payloadKafka.setServiceName(serviceName);
//        payloadKafka.setPackageName(packageDetails);
//        payloadKafka.setLoggerID(Constants.kafkaPidVar + System.getProperty(Constants.kafkaPidVar));
//        if (response.getResponseCode() != null && response.getResponseCode() != "") {
//            requestKafka.setSecurity(new Security());
//        } else {
//            requestKafka.setSecurity(request.getSecurity());
//        }
//        requestKafka.setPayload(payloadKafka);
//        return requestKafka;
//    }

    //Method to check Logged User
    public TokenData getLoggedUserDataFromHeaderToken(String token) {
        if (token != null && !token.equals(Constants.empty)) {
            JWTSecurity jwtSecurity = new JWTSecurity();
            String jwt = token.substring(Constants.seven);
            HashMap<String, String> data = jwtSecurity.parseJWT(jwt);
            if (data != null && data.get(Constants.expired).equalsIgnoreCase(Constants.equalsIgnoreCase)) {
                TokenData loggedUserDetail = new TokenData();
                loggedUserDetail.setUserId(Long.parseLong(data.get(Constants.userId)));
                loggedUserDetail.setLoginId(Long.parseLong(data.get(Constants.loginId)));
                loggedUserDetail.setUserType(data.get(Constants.userTypeId));
                return loggedUserDetail;
            } else {
                return Constants.TokenUnhandleException;
            }
        } else {
            return Constants.TokenUnhandleException;
        }
    }

    public String checkMcApplicability(Request jsonRequest, String authorization, String tableName, String formName, String requestTypeSave, String moduleId) {

        String url = env.getProperty(Constants.workflowurlKey).toString() + Constants.mcApplicableEndPoint;
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Constants.contentType, Constants.applicationJson);
        headerMap.put(Constants.accept, Constants.applicationJson);
        headerMap.put(Constants.AUTHORIZATION, authorization);
        Map<String, String> postParam = new HashMap<String, String>();
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put(Constants.tableNameKey, tableName);
        requestParams.put(Constants.formNameKey, formName);
        requestParams.put(Constants.requestTypeKey, requestTypeSave);
        requestParams.put(Constants.moduleIdKey, moduleId);
        jsonRequest.setPayLoad(requestParams);
        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
        return getResponseFromPostAPI(headerMap, postParam, url);
    }

    public String mcRequest(Request jsonRequest, String authorization, String formName, BigDecimal makerId, String makerComments, String ftFlag, String tableName, String requestTypeSave, String updateType, String updateJson, long refTableId, String moduleId) {
        String urlmcRequest = env.getProperty(Constants.workflowurlKey).toString() + Constants.mcRequestEndPoint;
        Map<String, String> postParam = new HashMap<String, String>();
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Constants.contentType, Constants.applicationJson);
        headerMap.put(Constants.accept, Constants.applicationJson);
        headerMap.put(Constants.AUTHORIZATION, authorization);
        Map<String, String> requestParamsMcRequest = new HashMap<String, String>();
        requestParamsMcRequest.put(Constants.formNameKey, formName);
        requestParamsMcRequest.put(Constants.makerIdKey, String.valueOf(makerId));
        requestParamsMcRequest.put(Constants.makerCommentsKey, makerComments);
        requestParamsMcRequest.put(Constants.ftFlagKey, ftFlag);
        requestParamsMcRequest.put(Constants.tableNameKey, tableName);
        requestParamsMcRequest.put(Constants.requestTypeKey, requestTypeSave);
        requestParamsMcRequest.put(Constants.updateTypeKey, updateType);
        requestParamsMcRequest.put(Constants.updateJsonKey, updateJson);
        requestParamsMcRequest.put(Constants.refTableIdKey, String.valueOf(refTableId));
        requestParamsMcRequest.put(Constants.moduleIdKey, moduleId);
        jsonRequest.setPayLoad(requestParamsMcRequest);
        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
        return getResponseFromPostAPI(headerMap, postParam, urlmcRequest);
    }

    public String mcAction(Request jsonRequest, String authorization, McActionRequest mcActionRequest, BigDecimal userId) {
        String urlmcRequest = env.getProperty(Constants.workflowurlKey).toString() + Constants.mcActionEndPoint;
        Map<String, String> postParam = new HashMap<String, String>();
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Constants.contentType, Constants.applicationJson);
        headerMap.put(Constants.accept, Constants.applicationJson);
        headerMap.put(Constants.AUTHORIZATION, authorization);
        Map<String, String> requestParamsMcAction = new HashMap<String, String>();
        requestParamsMcAction.put(Constants.mcRequestIdKey, mcActionRequest.getMcRequestId());
        requestParamsMcAction.put(Constants.mcPeindingRequestIdKey, mcActionRequest.getMcPeindingRequestId());
        requestParamsMcAction.put(Constants.checkerIdKey, String.valueOf(userId));
        requestParamsMcAction.put(Constants.checkerCommentsKey, mcActionRequest.getCheckerComments());
        requestParamsMcAction.put(Constants.actionKey, mcActionRequest.getAction());
        requestParamsMcAction.put(Constants.updatedIndexKey, String.valueOf(mcActionRequest.getUpdatedIndex()));
        jsonRequest.setPayLoad(requestParamsMcAction);
        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
        return getResponseFromPostAPI(headerMap, postParam, urlmcRequest);
    }

    public Map<String, String> createHeaderMapBackOffice(String token) {
        // Create a new HashMap object to store the headers
        Map<String, String> headerMap = new HashMap<>();

        // Set the "content-type" header to "application/json"
        headerMap.put("content-type", "application/json");

        // Set the "accept" header to "application/json"
        headerMap.put("accept", "application/json");
        //Set the "Authorization" Token
        headerMap.put("Authorization", token);

        // Return the populated headerMap object
        return headerMap;
    }

    public Map<String, Object> createPostParamBackOffice(Object data) {
        // Create a new HashMap object to store the post parameters
        Map<String, Object> postParam = new HashMap<>();

        // Convert the data object to JSON and store it in the "data" parameter
        postParam.put("data", convertObjecttoJson(data));

        // Return the populated postParam object
        return postParam;
    }

    public String convertObjectToString(Object object) {
        // Create an instance of ObjectMapper, which is a part of the Jackson library used for JSON serialization and deserialization
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // or handle the exception as desired
        }
    }

    public String generatePinSalt() {

//        logs(Constants.generateRrnNumber, Constants.logInfo,  this.getClass().getSimpleName(), Constants.generateRrnNumber, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo,null);

        int length = Integer.valueOf(4);
        Random random = new SecureRandom();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }

        //       logs(Constants.generateRrnNumber, Constants.logInfo,  this.getClass().getSimpleName(), Constants.generateRrnNumber, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo,null);

        return new String(digits);
    }

    public String sha256Encryption(String value) throws NoSuchAlgorithmException {
        MessageDigest md = null;

        md = MessageDigest.getInstance(Constants.sha256);
        byte[] hash = md.digest(value.getBytes(StandardCharsets.UTF_8));

        return hash.toString();
    }

    public String generateRandomNumberForAccountNumber() {

        int length = Integer.valueOf(9);
        Random random = new SecureRandom();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    public String convertCharactersToAscii(String x) {

        byte[] bytes = x.getBytes(StandardCharsets.US_ASCII);
        List<Integer> result = new ArrayList<>();   // convert bytes to ascii
        for (byte aByte : bytes) {
            int ascii = (int) aByte;                // byte -> int
            result.add(ascii);
        }
        String finalResult = result.toString().replace("[", "");
        finalResult = finalResult.replace("]", "");
        finalResult = finalResult.replace(",", "");
        finalResult = finalResult.replace(" ", "");
        return finalResult;

    }

    public String genrateCheckDigit(String x) {
        String result;
        long sum = 0;
        for (int i = 0; i < x.length(); i++) {
            sum = sum + Long.valueOf(x.charAt(i));
        }
        sum = sum % 97;
        sum = 98 - sum;
        if (sum < 10) {
            result = "0" + sum;
        } else {
            result = String.valueOf(sum);
        }
        return result;
    }

    public String generateIban(String accountNumber) {
        String bankCode = convertCharactersToAscii("JSBL");
        String countryCode = convertCharactersToAscii(convertCharactersToAscii("PK"));
        String checkDigit = genrateCheckDigit(bankCode + accountNumber + countryCode + "00");
        String iban = "PK" + checkDigit + "JSBL" + accountNumber;
        return iban;

    }

    public String getCurrentMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length >= 3) {
            return stackTrace[2].getMethodName();
        } else {
            return Constants.UNKNOWN_METHOD;
        }
    }

    public String generateTransactionCode(String channel, String terminal) {
        LocalDate currentDate = LocalDate.now();
        return String.valueOf(currentDate.getMonthValue()) + currentDate.getDayOfMonth() + channel + terminal + generatePinSalt();
    }

    public void setResponse(Response response, String type, Object s) {
        MerchantQrService merchantQrService=new MerchantQrServiceImpl();
        TblResponseMessage tblResponseMessage;
        tblResponseMessage = merchantQrService.findByResponseMessageDescr(type);
        response.setPayLoad(s);
        response.setResponseCode(tblResponseMessage.getResponseMessageCode());
        response.setMessage(tblResponseMessage.getResponseMessageDescr());
    }
}


