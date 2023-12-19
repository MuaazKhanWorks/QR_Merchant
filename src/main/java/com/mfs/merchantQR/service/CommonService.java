package com.mfs.merchantQR.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.model.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

public interface CommonService {

    TblResponseMessage getResponseMessageByResponseDescr(String descr);

    TblUser findUserById(Integer userId);

//    String findUserNameById(Long userId);

    //    for update case
    Response makerChecker(String tableName, String formName, String requestType, String updateType, String refId, String updateJson, BigDecimal userId) throws JsonProcessingException;

    String checkMcApplicabilityInsert(String tableName, String formName, String requestType) throws JsonProcessingException;

    //    for parking request in case of insert
    Response parkRequestInsert(String tableName, String formName, String requestType, String updateType, String refId, String updateJson, BigDecimal userId);

    //    for performing action in case of insert/update
    Response mcAction(McActionRequest mcActionRequest);

    //    for getting single MC request object
    TblMcRequest getMcRequestCheckerById(int mcRequestId);

    String logs(String endPoint, String logLevel, String className, String methodName, String packageDetails, Request request, String message, Response resp) throws HttpClientErrorException;

    LkpStatus getStatusByCode(String code);

    void setResponse(Response response, String type, Object s, String message);
}
