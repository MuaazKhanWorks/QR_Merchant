/*
Author Name: romail.ahmed

Project Name: zconnect_backoffice

Package Name: com.mfs.zconnect_backoffice.controller.lookup

Class Name: LookupGetApis

Date and Time:11/7/2023 4:51 PM

Version:1.0
*/
package com.mfs.merchantQR.controller.lookup;

import com.mfs.merchantQR.controller.AbstarctApi;
import com.mfs.merchantQR.dto.LovResponse;
import com.mfs.merchantQR.dto.Response;
import com.mfs.merchantQR.model.TblResponseMessage;
import com.mfs.merchantQR.service.LookupService;
import com.mfs.merchantQR.service.MerchantQrService;
import com.mfs.merchantQR.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = Constants.s1, allowedHeaders = Constants.s1)
@RequestMapping(Constants.requestMapping)
@RestControllerAdvice
public class LookupGetApis extends AbstarctApi {

    @Autowired
    private LookupService lookupService;

    @Autowired
    private MerchantQrService merchantQrService;


    @GetMapping(value = Constants.GET_LOV_DATA)
    public ResponseEntity<Response> getLovData(@PathVariable String key, HttpServletRequest request) {
        Response response = new Response();
        String methodName = getCurrentMethodName();
        try {
//            logs(Constants.GET_LOV_DATA, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo, response);
            List<LovResponse> lovResponses = new ArrayList<>();
            if (key.equals(Constants.TBL_ROLE)) {
                lovResponses = lookupService.getRole();
            }
            if (key.equals(Constants.TBL_USER)) {
                lovResponses = lookupService.getUser();
            }
            if (key.equals(Constants.LKP_STATUS)) {
                lovResponses = lookupService.getLkpStatus();
            }
            if (lovResponses != null && !lovResponses.isEmpty()) {
                TblResponseMessage tblResponseMessage;
                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
                response.setPayLoad(lovResponses);
                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage.getResponseMessageDescr());
            } else {
                TblResponseMessage tblResponseMessage;
                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.RECORD_NOT_FOUND);
                response.setPayLoad(lovResponses);
                response.setResponseCode(tblResponseMessage.getResponseMessageCode());
                response.setMessage(tblResponseMessage.getResponseMessageDescr());
            }
        } catch (Exception e) {
            e.printStackTrace();

            TblResponseMessage tblResponseMessage;
            tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.RECORD_NOT_FOUND);
            response.setPayLoad(null);
            response.setResponseCode(tblResponseMessage.getResponseMessageCode());
            response.setMessage(tblResponseMessage.getResponseMessageDescr());
        }
//        logs(Constants.GET_LOV_DATA, Constants.LOG_INFO, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo, response);
        return convertStringToResponseObject(response, response.getResponseCode());
    }

}
