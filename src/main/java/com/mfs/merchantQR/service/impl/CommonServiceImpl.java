package com.mfs.merchantQR.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.repo.*;
import com.mfs.merchantQR.service.*;
import com.mfs.merchantQR.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private TblResponseMessageRepo tblResponseMessageRepo;

    @Autowired
    private TblUserRepo tblUserRepo;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private TblMcRequestRepo tblMcRequestRepo;

    @Autowired
    private LkpStatusRepo lkpStatusRepo;

    @Value("${logsurl}")
    private String url;

    @Value("${servicename}")
    private String serviceName;


    @Override
    public TblResponseMessage getResponseMessageByResponseDescr(String descr) {
        return tblResponseMessageRepo.findByResponseMessageDescr(descr);
    }

    @Override
    public TblUser findUserById(Integer userId) {
        return tblUserRepo.findById(userId).orElse(null);
    }

//    @Override
//    public String findUserNameById(Long userId) {
//        return tblUserRepo.findUserNameById(userId);
//    }

    @Override
    public Response makerChecker(String tableName, String formName, String requestType, String updateType, String refId, String updateJson, BigDecimal userId) throws JsonProcessingException {
        Response response = new Response();
        ObjectMapper objectMapper = new ObjectMapper();
        ProcedureResponse checkMcApplicability = workflowService.checkMcApplicability(tableName, formName, requestType);
        if (checkMcApplicability != null) {
            ProcedureResponse procedureResponse = objectMapper.readValue(convertObjecttoJson(checkMcApplicability), ProcedureResponse.class);
            if (procedureResponse != null && procedureResponse.getMcApplicability() > 0) {
                McResponse mcResponse = workflowService.parkRequestToChecker(formName, "", tableName, requestType, updateType, refId, updateJson, userId);
                if (mcResponse != null) {
                    if (mcResponse.getStatus() == 1) {
                        setResponse(response, Constants.SUCCESS, mcResponse, mcResponse.getStatusDecsr());
                    } else {
                        setResponse(response, Constants.RECORD_ALREADY_EXISTS, mcResponse, mcResponse.getStatusDecsr());
                    }
                } else {
                    setResponse(response, Constants.RECORD_NOT_FOUND, null);
                }
            } else {
                response.setResponseCode(Constants.MAKER_CHECKER_NOT_APPLICABLE_CODE);
            }
        } else {
            setResponse(response, Constants.RECORD_NOT_FOUND, null);
        }
        return response;
    }

    @Override
    public String checkMcApplicabilityInsert(String tableName, String formName, String requestType) throws JsonProcessingException {
        String result = Constants.EMPTY;
        ObjectMapper objectMapper = new ObjectMapper();
        ProcedureResponse checkMcApplicability = workflowService.checkMcApplicability(tableName, formName, requestType);
        if (checkMcApplicability != null) {
            ProcedureResponse procedureResponse = objectMapper.readValue(convertObjecttoJson(checkMcApplicability), ProcedureResponse.class);
            if (procedureResponse == null || procedureResponse.getMcApplicability() <= 0) {
                result = Constants.MAKER_CHECKER_NOT_APPLICABLE_CODE;
            }
        } else {
            result = Constants.RECORD_NOT_FOUND;
        }
        return result;
    }

    @Override
    public Response parkRequestInsert(String tableName, String formName, String requestType, String updateType, String refId, String updateJson, BigDecimal userId) {
        Response response = new Response();
        McResponse mcResponse = workflowService.parkRequestToChecker(formName, "", tableName, requestType, updateType, refId, updateJson, userId);
        if (mcResponse != null) {
            if (mcResponse.getStatus() == 1) {
                setResponse(response, Constants.SUCCESS, mcResponse, mcResponse.getStatusDecsr());
            } else {
                setResponse(response, Constants.RECORD_ALREADY_EXISTS, mcResponse, mcResponse.getStatusDecsr());
            }
        } else {
            setResponse(response, Constants.RECORD_NOT_UPDATED, null);
        }
        return response;
    }

    @Override
    public Response mcAction(McActionRequest mcActionRequest) {
        Response response = new Response();
        McActionResponse mcActionResponse = workflowService.mcAction(mcActionRequest);
        if (mcActionResponse != null) {
            if (mcActionRequest.getAction().equals("2") && mcActionResponse.getRequestStatus().equals("A") && mcActionResponse.getStatus() == 1) {
                response.setPayLoad(Constants.ACTION_TAKEN);
                response.setMessage(mcActionResponse.getStatusDecsr());
            } else {
                setResponse(response, Constants.SUCCESS, mcActionResponse, mcActionResponse.getStatusDecsr());
            }
        } else {
            setResponse(response, Constants.RECORD_NOT_UPDATED, null);
        }
        return response;
    }

    //this method is used to convert json to string
    public String convertObjecttoJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void setResponse(Response response, String type, Object s) {
        TblResponseMessage tblResponseMessage;
        tblResponseMessage = getResponseMessageByResponseDescr(type);
        response.setPayLoad(s);
        response.setResponseCode(tblResponseMessage.getResponseMessageCode());
        response.setMessage(tblResponseMessage.getResponseMessageDescr());
    }

    @Override
    public void setResponse(Response response, String type, Object s, String message) {
        TblResponseMessage tblResponseMessage;
        tblResponseMessage = getResponseMessageByResponseDescr(type);
        response.setPayLoad(s);
        response.setResponseCode(tblResponseMessage.getResponseMessageCode());
        response.setMessage(message != null ? message : tblResponseMessage.getResponseMessageDescr());
    }

    @Override
    public TblMcRequest getMcRequestCheckerById(int mcRequestId) {
        return tblMcRequestRepo.findByMcRequestId(mcRequestId);
    }

    @Override
    @Async
    public String logs(String endPoint, String logLevel, String className, String methodName, String packageDetails, Request request, String message, Response resp) throws HttpClientErrorException {
        RequestKafka requestKafka = setLogsRequestFromPostApis(endPoint, logLevel, className, methodName, packageDetails, request, message, resp);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Constants.accept, Constants.applicationJson);
        Map<String, Object> postParam = new HashMap<>();
        headers.setAll(headerMap);
        postParam.put(Constants.securityVariable, requestKafka.getSecurity());
        postParam.put(Constants.payloadVariable, requestKafka.getPayload());
        postParam.put(Constants.indexNameVariable, Constants.indexName);
        return getResponseFromPostAPI(headerMap, postParam, url);
    }

    private RequestKafka setLogsRequestFromPostApis(String endPoint, String logLevel, String className, String methodName, String packageDetails, Request request, String message, Response response) {
        RequestKafka requestKafka = new RequestKafka();
        PayloadKafka payloadKafka = new PayloadKafka();

        payloadKafka.setDateTime(new Date().toString());
        payloadKafka.setEndpoint(endPoint);
        payloadKafka.setClassName(className);
        payloadKafka.setMethodName(methodName);
        payloadKafka.setLoggingLevel(logLevel);
        if (response.getResponseCode() != null && response.getResponseCode().equals(Constants.EMPTY)) {
            payloadKafka.setPayloadService(Constants.kafkaDataVar + convertObjecttoJson(response));
        } else {
            payloadKafka.setPayloadService(Constants.kafkaDataVar + convertObjecttoJson(request));
        }
        payloadKafka.setMessage(message);
        payloadKafka.setServiceName(serviceName);
        payloadKafka.setPackageName(packageDetails);
        payloadKafka.setLoggerID(Constants.KAFKA_PID_VAR + System.getProperty(Constants.kafkaPidVar));
        if (response.getResponseCode() != null && !response.getResponseCode().equals(Constants.EMPTY)) {
            requestKafka.setSecurity(new Security());
        } else {
            requestKafka.setSecurity(request.getSecurity());
        }
        requestKafka.setPayload(payloadKafka);
        return requestKafka;
    }

    public <K, V> String getResponseFromPostAPI(Map<String, String> headerMap, Map<K, V> postParam, String url) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.setAll(headerMap);

        HttpEntity<Map<K, V>> request = new HttpEntity<>(postParam, headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ResponseEntity<String> response;

        try {
            response = restTemplate.postForEntity(url, request, String.class);
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx) here
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response.getBody();
    }

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(60000);
        clientHttpRequestFactory.setReadTimeout(60000);
        return clientHttpRequestFactory;
    }

    @Override
    public LkpStatus getStatusByCode(String code) {
        return lkpStatusRepo.findBystatusCode(code);
    }

}
