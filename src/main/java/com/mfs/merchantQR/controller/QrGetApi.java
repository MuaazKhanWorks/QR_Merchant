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


//    @RequestMapping(value = Constants.getSegmentById, method = RequestMethod.GET)
//    public ResponseEntity<Response> getSegmentById(@PathVariable String segmentId, HttpServletRequest request) throws JsonProcessingException {
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        Response response = new Response();
//        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
//        logs(Constants.getKycAttributeById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo, response);
//        List<Error> validations = FieldsValidator.getValidatorId(Long.valueOf(segmentId));
//        TblResponseMessage tblResponseMessage;
//        if (validations.size() <= 0) {
//            LkpSegment lkpSegment = merchantQrService.getSegmentById(Long.valueOf(segmentId));
//            if(lkpSegment.getUploadAgreement()!=null) {
//                lkpSegment.getUploadAgreement();
//                File file = new File(lkpSegment.getUploadAgreement());
//
//                try {
//                    byte[] fileContent = Files.readAllBytes(file.toPath());
//                    String base64Content = Base64.getEncoder().encodeToString(fileContent);
//                    lkpSegment.setBase64content(base64Content);
//
//                } catch (IOException e) {
//                    // Handle the exception appropriately
//                    e.printStackTrace();
//
//                }
//            }
//            if (lkpSegment != null) {
//                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
//                response.setPayLoad(lkpSegment);
//                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
//                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
//                logs(Constants.getKycAttributeById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
//            } else {
//                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotFound);
//                response.setPayLoad(lkpSegment);
//                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
//                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
//                logs(Constants.getKycAttributeById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
//            }
//        } else {
//            response.setResponseCode(Constants.fieldValidationCode);
//            response.setErrors(validations);
//            response.setMessage(Constants.validationFailed);
//        }
//        return convertStringToResponseObject(response, response.getResponseCode());
//    }
//
//    @RequestMapping(value = Constants.getSegmentsUpdateCheckerById, method = RequestMethod.GET)
//    public ResponseEntity<Response> getSegmentsUpdateCheckerById(@PathVariable String mcRequestId, HttpServletRequest request) throws JsonProcessingException {
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName();
//        Response response = new Response();
//        String moduleId = env.getProperty(Constants.moduleIdKey).toString();
//        logs(Constants.getSegmentsUpdateCheckerById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo, response);
//        List<Error> validations = FieldsValidator.getSegmentId(Long.valueOf(mcRequestId));
//        if (validations.size() <= 0) {
//            TblMcRequest tblMcRequest = merchantQrService.getSegmentsUpdateCheckerById(Long.valueOf(mcRequestId));
//            String jsonString=null;
//            LkpSegment lkpSegment =new LkpSegment();
//            SegmentRequest segmentRequest = new SegmentRequest();
//            ObjectMapper objectMapper = new ObjectMapper();
//            segmentRequest=objectMapper.readValue(tblMcRequest.getUpdateJson(), SegmentRequest.class);
//            ModelMapper modelMapper = new ModelMapper();
//            modelMapper.map(segmentRequest, lkpSegment);
//
////             lkpSegment=merchantQrService.getSegmentById(segmentRequest.getSegmentId());
//            if(segmentRequest.getBusinessTypeId()!=null) {
//                LkpBusinessType lkpBusinessType = merchantQrService.getLkpBusinessTypeById(segmentRequest.getBusinessTypeId().longValue());
//                lkpSegment.setLkpBusinessType(lkpBusinessType);
//            }
//            if(segmentRequest.getRegionId()!=null) {
//                LkpRegion lkpRegion = merchantQrService.getLkpRegionByid(segmentRequest.getRegionId().longValue());
//                lkpSegment.setLkpRegion(lkpRegion);
//            }
//            if (segmentRequest.getSalesRoleDetailId()!=null) {
//
//                TblSalesRoleDetail tblSalesRoleDetail = merchantQrService.getTblSalesRoleDetailById(segmentRequest.getSalesRoleDetailId().longValue());
//                lkpSegment.setTblSalesRoleDetail(tblSalesRoleDetail);
//            }
//            TblResponseMessage tblResponseMessage;
//            LkpSegment lkpSegment1 = merchantQrService.getSegmentById(lkpSegment.getSegmentId());
//            if(lkpSegment1.getUploadAgreement()==null || lkpSegment1.getUploadAgreement()==""){
//                lkpSegment.setBase64content(segmentRequest.getUploadAgreement());
//                lkpSegment.setUploadAgreement("/opt/wildfly/standalone/documents/" + segmentRequest.getSegmentId()+".pdf");
//            }
//
//            if(lkpSegment1.getUploadAgreement()!=null) {
//                lkpSegment1.getUploadAgreement();
//                File file = new File(lkpSegment1.getUploadAgreement());
//                try {
//                    byte[] fileContent = Files.readAllBytes(file.toPath());
//                    String base64Content = Base64.getEncoder().encodeToString(fileContent);
//                    lkpSegment.setBase64content(base64Content);
//                    lkpSegment.setUploadAgreement(lkpSegment1.getUploadAgreement());
//
//                } catch (IOException e) {
//                    // Handle the exception appropriately
//                    e.printStackTrace();
//
//                }
//            }
//            if (lkpSegment != null) {
//                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.success);
//                response.setPayLoad(lkpSegment);
//                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
//                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
//                logs(Constants.getSegmentsUpdateCheckerById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
//            } else {
//                tblResponseMessage = merchantQrService.findByResponseMessageDescr(Constants.recordNotFound);
//                response.setPayLoad(lkpSegment);
//                response.setResponseCode(moduleId+tblResponseMessage.getResponseMessageCode());
//                response.setMessage(tblResponseMessage!=null?tblResponseMessage.getResponseMessageDescr():Constants.generalProcessingError);
//                logs(Constants.getSegmentsUpdateCheckerById, Constants.logInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), new Request(), Constants.endingMethod, response);
//            }
//        } else {
//            response.setResponseCode(Constants.fieldValidationCode);
//            response.setErrors(validations);
//            response.setMessage(Constants.validationFailed);
//        }
//        return convertStringToResponseObject(response, response.getResponseCode());
//    }


}