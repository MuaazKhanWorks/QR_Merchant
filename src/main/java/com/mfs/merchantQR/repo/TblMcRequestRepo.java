package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblMcRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblMcRequestRepo extends JpaRepository<TblMcRequest, Integer> {
    TblMcRequest findByMcRequestId(int limitId);
}
