/*Author Name:ahmad.raza
Project Name: merchantQR
Package Name:com.mfs.merchantQR.service.impl
Class Name: MerchantQrServiceImpl
Date and Time:12/13/2023 11:26 AM
Version:1.0*/
package com.mfs.merchantQR.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.merchantQR.controller.AbstarctApi;
import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.dto.Error;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.repo.*;
import com.mfs.merchantQR.service.CommonService;
import com.mfs.merchantQR.service.MerchantQrService;
import com.mfs.merchantQR.utils.Constants;
import com.mfs.merchantQR.utils.FieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.instrument.classloading.WeavingTransformer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MerchantQrServiceImpl extends AbstarctApi implements MerchantQrService {

    @Autowired
    private TblResponseMessageRepo tblResponseMessageRepo;

    @Autowired
    private TblUserRepo tblUserRepo;

    @Autowired
    private TblUserRoleRepo tblUserRoleRepo;

    @Autowired
    private TblRoleRepo tblRoleRepo;

    @Autowired
    private CommonService commonService;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.access_token}")
    private String accessToken;

    @Autowired
    private RestTemplate restTemplate;



//    @Autowired
//    private TblMcRequestRepo tblMcRequestRepo;

    @Autowired
    private TblMerchantRepo tblMerchantRepo;

    @Autowired
    private MerchanAccountRepo merchanAccountRepo;


    @Override
    public TblResponseMessage findByResponseMessageDescr(String success) {
        return tblResponseMessageRepo.findByResponseMessageDescr(success);
    }

    @Override
    public TblUser saveUser(TblUserRequest tblUserRequest, int userId) throws ParseException {
        TblUser tblUser = new TblUser();
        tblUser.setName(tblUserRequest.getName());
        tblUser.setPassword(tblUserRequest.getPassword());
        if (tblUserRequest.getEmail() != null && !tblUserRequest.getEmail().equalsIgnoreCase("")) {
            tblUser.setEmail(tblUserRequest.getEmail());
        }
        tblUser.setIsActive("Y");
        tblUser.setCreatedate(new Date());
        tblUser.setCreateuser(BigDecimal.valueOf(userId));
        LkpStatus lkpStatus = new LkpStatus();
        lkpStatus.setStatusId(tblUserRequest.getStatusId());
//        tblUser.setStatusId(lkpStatus.getStatusId());
        tblUser.setLastupdateuser(BigDecimal.valueOf(0));
        tblUser.setUpdateindex(BigDecimal.valueOf(0));
        tblUser = tblUserRepo.save(tblUser);
        if (tblUser != null) {
            if (Long.valueOf(tblUserRequest.getRoleId()) > 0) {
                TblUserRole tblUserRole = new TblUserRole();
                TblRole tblRole1 = tblRoleRepo.findById(Long.valueOf(tblUserRequest.getRoleId())).orElse(null);
                tblRole1.setRoleId(tblRole1.getRoleId());
                tblUserRole.setTblUser(tblUser);
                tblUserRole.setTblRole(tblRole1);
                tblUserRole.setIsActive("Y");
                tblUserRole.setCreateuser(tblUser.getCreateuser());
                tblUserRole.setCreatedate(new Date());
                tblUserRole.setLastupdateuser(BigDecimal.valueOf(0));
                tblUserRole.setUpdateindex(BigDecimal.valueOf(0));

                tblUserRoleRepo.save(tblUserRole);
            }

            return tblUser;

        } else {
            return tblUser;
        }

    }


    public Response saveUserRequest(TokenData loggedUserDetail, TblUserRequest tblUserRequest) throws JsonProcessingException, ParseException {
        Response response = new Response();
        response = commonService.makerChecker(Constants.TBL_USER, Constants.CREATE_USER_FORM_NAME, Constants.REQUEST_TYPE_SAVE, Constants.REQUEST_TYPE_SAVE, String.valueOf(1), Constants.EMPTY, BigDecimal.valueOf(1));
        if (response.getResponseCode() != null && response.getResponseCode().equals(Constants.MAKER_CHECKER_NOT_APPLICABLE_CODE)) {
            tblUserRequest.setIsActive(Constants.SET_YES);
            TblUser tblUser = saveUser(tblUserRequest, (int) 1);
            if (tblUser != null) {
                setResponse(response, Constants.SUCCESS, tblUser);
            } else {
                setResponse(response, Constants.RECORD_NOT_SAVED, Constants.EMPTY);
            }
        }
        return response;
    }


    @Override
    public TblUser checkUserExistance(String userName) {
        return tblUserRepo.findByName(userName);
    }

    public void setResponse(Response response, String type, Object s) {
        TblResponseMessage tblResponseMessage;
        tblResponseMessage = commonService.getResponseMessageByResponseDescr(type);
        response.setPayLoad(s);
        response.setResponseCode(tblResponseMessage.getResponseMessageCode());
        response.setMessage(tblResponseMessage.getResponseMessageDescr());
    }


    @Override
    public Response updateUserRequest(TokenData loggedUserDetail, UpdateUserRequest updateUserRequest) throws JsonProcessingException {
        Response response = new Response();
        ObjectMapper objectMapper = new ObjectMapper();
        TblUser tblUser = tblUserRepo.findById(Long.valueOf(updateUserRequest.getUserId())).orElse(null);
        if (tblUser != null) {
            String updateJson = objectMapper.writeValueAsString(updateUserRequest);
            response = commonService.makerChecker(Constants.TBL_USER, Constants.UPDATE_USER_FORM_NAME, Constants.REQUEST_TYPE_UPDATE, Constants.REQUEST_TYPE_UPDATE, String.valueOf(tblUser.getUserId()), updateJson, BigDecimal.valueOf(loggedUserDetail.getUserId()));
            if (response.getResponseCode() != null && response.getResponseCode().equals(Constants.MAKER_CHECKER_NOT_APPLICABLE_CODE)) {
                tblUser = updateUser(updateUserRequest, (int) loggedUserDetail.getUserId());

                if (tblUser != null) {
                    setResponse(response, Constants.SUCCESS, tblUser);
                } else {
                    setResponse(response, Constants.RECORD_NOT_UPDATED, Constants.EMPTY);
                }
            }

        } else {
            setResponse(response, Constants.RECORD_NOT_FOUND, Constants.EMPTY);
        }

        return response;
    }


    private TblUser updateUser(UpdateUserRequest updateUserRequest, int valueOf) {
        TblUser tblUser = tblUserRepo.findById(Long.valueOf(updateUserRequest.getUserId())).orElse(null);
        tblUser.setName(updateUserRequest.getName());
        tblUser.setPassword(updateUserRequest.getPassword());
        if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().equalsIgnoreCase("")) {
            tblUser.setEmail(updateUserRequest.getEmail());
        }
        tblUser.setIsActive("Y");
        tblUser.setCreatedate(new Date());
        tblUser.setCreateuser(BigDecimal.valueOf(valueOf));
        LkpStatus lkpStatus = new LkpStatus();
        lkpStatus.setStatusId(updateUserRequest.getStatusId());
//        tblUser.setStatusId(lkpStatus.getStatusId());
        tblUser.setLastupdatedate(new Date());
        tblUser.setLastupdateuser(BigDecimal.valueOf(valueOf));
        tblUser.setUpdateindex(tblUser.getUpdateindex() != null ? new BigDecimal(tblUser.getUpdateindex().longValue() + 1) : new BigDecimal(1));
        tblUser = tblUserRepo.saveAndFlush(tblUser);
        if (tblUser != null && tblUser.getUserId() > 0) {
            if (Long.valueOf(updateUserRequest.getRoleId()) > 0) {
                for (TblUserRole tblUserRole1 : tblUser.getTblUserRoles()) {
                    TblUserRole tblUserRole = tblUserRoleRepo.findById(Long.valueOf(tblUserRole1.getUserRoleId())).orElse(null);
                    TblRole tblRole1 = tblRoleRepo.findById(Long.valueOf(updateUserRequest.getRoleId())).orElse(null);
                    tblRole1.setRoleId(tblRole1.getRoleId());
                    tblUserRole.setTblUser(tblUser);
                    tblUserRole.setTblRole(tblRole1);
                    tblUserRole.setIsActive("Y");
                    tblUserRole.setCreateuser(tblUser.getCreateuser());
                    tblUserRole.setCreatedate(new Date());
                    tblUserRole.setLastupdateuser(BigDecimal.valueOf(valueOf));
                    tblUserRole.setUpdateindex(tblUserRole.getUpdateindex() != null ? new BigDecimal(tblUserRole.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                    tblUserRoleRepo.save(tblUserRole);
                }
            }
//            }
            return tblUser;

        } else {
            return tblUser;
        }
    }

    @Override
    public List<TblUser> getAllUsersBySearch(GetAllUsersRequest getAllUsersRequest) {
        List<TblUser> getAllUsers = tblUserRepo.getAllUsersBySearch(getAllUsersRequest.getName(), getAllUsersRequest.getRole(), getAllUsersRequest.getUser(), getAllUsersRequest.getStatus());
        return getAllUsers;
    }

    @Override
    public List<TblMerchant> getAllMerchantBySearch(GetAllMerchantFromMicrobank getAllMerchantRequest) {
//        List<TblMerchant> tblMerchants = tblMerchantRepo.getAllMerchantBySearch(getAllMerchantRequest.getMerchantName(), getAllMerchantRequest.getMerchantAccountNo(), getAllMerchantRequest.getMerchantCreationDate(), getAllMerchantRequest.getCreatedBy(), getAllMerchantRequest.getStatus());
        return null;
    }

    @Override
    public TblUser getUserById(int userId) {
        return tblUserRepo.findById(Long.valueOf(userId)).orElse(null);
    }

//    @Override
//    public TblMcRequest getUserUpdateCheckerById(int mcRequestId) {
//        return tblMcRequestRepo.findByMcRequestId(mcRequestId);
//    }

    @Override
    public TblUser loginMember(TblUser tblUser) {
        Map<BigDecimal, List<TblMenu>> parentChildMap = tblUser.getTblUserRoles().stream()
                .filter(userRole -> userRole.getIsActive().equals(Constants.SET_YES)) // Filter active user roles
                .flatMap(userRole -> userRole.getTblRole().getTblRoleRights().stream()
                        .filter(roleRight -> roleRight.getIsActive().equals(Constants.SET_YES)) // Only active TblRoleRight
                        .map(TblRoleRight::getTblMenu)
                )
                .filter(menu -> menu.getParentMenu().longValue() != 0) // Filter out menus without a parent
                .filter(menu -> menu.getIsActive().equals(Constants.SET_YES)) // Filter active menus
                .collect(Collectors.groupingBy(
                        TblMenu::getParentMenu,
                        Collectors.toList()
                ));

        tblUser.setMenuListMap(parentChildMap);
        return tblUser;
    }

    @Override
    public TblMerchant updateDownloadStatus(TokenData loggedUserDetail, UpdateDownloadStatusRequest updateDownloadStatusRequest) {
        TblMerchant tblMerchant = tblMerchantRepo.findById(Long.valueOf(updateDownloadStatusRequest.getMerchantId())).orElse(null);

        tblMerchant.setDownlaodStatus(updateDownloadStatusRequest.getDownloadStatus());
        tblMerchant.setCreatedate(new Date());
        tblMerchant.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblMerchant.setUpdateindex(tblMerchant.getUpdateindex() != null ? new BigDecimal(tblMerchant.getUpdateindex().longValue() + 1) : new BigDecimal(1));
        tblMerchant.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblMerchant.setLastupdatedate(new Date());
        return tblMerchant = tblMerchantRepo.save(tblMerchant);
    }

    @Override
    public List<MerchantAccountDTO> getAllMerchant(GetAllMerchantFromMicrobank getAllMerchantFromMicrobank) {
        List<Object> objects = merchanAccountRepo.getAllMerchant(getAllMerchantFromMicrobank.getMobileNo(),getAllMerchantFromMicrobank.getBusinessName());
        List<MerchantAccountDTO> merchantAccountDTOS = new ArrayList<>();
        for (Object resultArray : objects) {
            Object[] row = (Object[]) resultArray;
            MerchantAccountDTO merchantAccountDTO = new MerchantAccountDTO();
            merchantAccountDTO.setQrImages((String) (row[0]));
            merchantAccountDTO.setMobileNo((String) (row[1]));
            merchantAccountDTO.setCnic((String) (row[2]));
            merchantAccountDTO.setBusinessName((String) (row[3]));
            merchantAccountDTO.setCity((String) (row[4]));
            merchantAccountDTO.setBusinessAddress((String) (row[5]));
            merchantAccountDTO.setTypeOfBusiness((String) (row[6]));
            merchantAccountDTO.setDownloadStatus((String) (row[7]));
            merchantAccountDTO.setCreatedOn((Date) (row[8]));
            merchantAccountDTO.setCreatedBy((BigDecimal) (row[9]));
            merchantAccountDTO.setRegistrationStatus((String) (row[10]));
            merchantAccountDTOS.add(merchantAccountDTO);
        }
        return merchantAccountDTOS;
    }

    @Override
    public StaticQrResponse getStaticQR(String mobileNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access_token", accessToken);

        String requestBody = "{" + mobileNumber +"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(mobileNumber, headers);

        ResponseEntity<StaticQrResponse> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                StaticQrResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            // Handle error scenarios
            return null;
        }
    }



}