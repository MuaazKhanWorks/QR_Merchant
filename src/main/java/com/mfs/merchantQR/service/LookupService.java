package com.mfs.merchantQR.service;

import com.mfs.merchantQR.dto.LovResponse;

import java.util.List;

public interface LookupService {

    List<LovResponse> getUser();
    List<LovResponse> getRole();
    List<LovResponse> getLkpStatus();
}
