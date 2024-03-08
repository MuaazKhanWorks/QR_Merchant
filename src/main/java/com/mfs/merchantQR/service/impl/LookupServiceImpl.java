/*Author Name:muhammad.anas
Project Name: zconnect_backoffice
Package Name:com.mfs.zconnect_backoffice.service
Class Name: LookupServiceImpl
Date and Time:11/10/2023 7:46 PM
Version:1.0*/
package com.mfs.merchantQR.service.impl;

import com.mfs.merchantQR.dto.LovResponse;
import com.mfs.merchantQR.model.*;
import com.mfs.merchantQR.repo.*;
import com.mfs.merchantQR.service.LookupService;
import com.mfs.merchantQR.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookupServiceImpl implements LookupService {

    @Autowired
    private TblRoleRepo tblRoleRepo;


    @Autowired
    private TblUserRepo tblUserRepo;


    @Autowired
    private LkpStatusRepo statusRepo;

    @Autowired
    private TblMcConfigRepo tblMcConfigRepo;

    @Autowired
    private LkpStatusRepo lkpStatusRepo;


    @Override
    public List<LovResponse> getUser() {
        List<LovResponse> lovResponses = new ArrayList<>();
        List<TblUser> tblUsers = tblUserRepo.findByIsActive(Constants.SET_YES);
        if (tblUsers != null && !tblUsers.isEmpty()) {
            LovResponse lovResponse = null;
            for (TblUser tblUser : tblUsers) {
                lovResponse = new LovResponse();
                lovResponse.setId(String.valueOf(tblUser.getUserId()));
                lovResponse.setDescr(tblUser.getName());

                lovResponses.add(lovResponse);
            }
        }
        return lovResponses;
    }


    @Override
    public List<LovResponse> getRole() {
        List<LovResponse> lovResponses = new ArrayList<>();
        List<TblRole> tblRoles = tblRoleRepo.findByIsActive(Constants.SET_YES);
        if (tblRoles != null && !tblRoles.isEmpty()) {
            LovResponse lovResponse = null;
            for (TblRole tblRole : tblRoles) {
                lovResponse = new LovResponse();
                lovResponse.setId(String.valueOf(tblRole.getRoleId()));
                lovResponse.setDescr(tblRole.getRoleDescr());

                lovResponses.add(lovResponse);
            }
        }
        return lovResponses;
    }
    public List<LovResponse> getLkpStatus() {
        List<LovResponse> lovResponses = new ArrayList<>();
        List<LkpStatus> tblRoles = lkpStatusRepo.findByIsActive(Constants.SET_YES);
        if (tblRoles != null && !tblRoles.isEmpty()) {
            LovResponse lovResponse = null;
            for (LkpStatus tblRole : tblRoles) {
                lovResponse = new LovResponse();
                lovResponse.setId(String.valueOf(tblRole.getStatusId()));
                lovResponse.setDescr(tblRole.getStatusDescr());

                lovResponses.add(lovResponse);
            }
        }
        return lovResponses;
    }
}