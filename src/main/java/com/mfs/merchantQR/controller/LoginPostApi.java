package com.mfs.merchantQR.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.repo.TblUserRepo;
import com.mfs.merchantQR.service.MerchantQrService;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private TblUserRepo tblUserRepo;

    Logger LOG = LoggerFactory.getLogger(LoginPostApi.class);


    @PostMapping(value = Constants.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> loginMember(@RequestBody String data, HttpServletRequest request) throws Exception {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        Request jsonRequest = convertStringToRequestObject(data);
        Response response = new Response();
        AESencryption aes256Encryption = new AESencryption();
        LOG.info("\n\n\nINSIDE \n CLASS == MicrobankZmilesLovController \n METHOD == lovTier(); ");
//        logs(Constants.LOGIN, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.callingMethodInfo, response);
        LoginRequest loginRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), LoginRequest.class);
        List<Error> validator = FieldsValidator.getLoginValidator(loginRequest);
        if (validator.size() <= 0) {
            TblUser tblUser = tblUserRepo.findByNameAndPassword(loginRequest.getUserName(), loginRequest.getUserPassword());
            if (tblUser != null) {
                TblUser user = merchantQrService.loginMember(tblUser);
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
                    TblResponseMessage tblResponseMessage;
                    tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.RECORD_NOT_FOUND);
                    response.setPayLoad(user);
                    response.setResponseCode(tblResponseMessage.getResponseMessageCode());
                    response.setMessage(tblResponseMessage.getResponseMessageDescr());
//                    setResponse(response, Constants.RECORD_NOT_FOUND, "");
                }
            } else {
                TblResponseMessage tblResponseMessage = new TblResponseMessage();
                tblResponseMessage.setResponseMessageDescr("User Not Exist");
                tblResponseMessage.setResponseMessageCode("1111");
                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage.getResponseMessageDescr());
            }
        } else {
            TblResponseMessage tblResponseMessage;
            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.validationFailed);
            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
            response.setMessage(tblResponseMessage.getResponseMessageDescr());

        }
        LOG.info("\n\n\nINSIDE \n CLASS == MicrobankZmilesLovController \n METHOD == lovTier(); ");
//        logs(Constants.LOGIN, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }


}
