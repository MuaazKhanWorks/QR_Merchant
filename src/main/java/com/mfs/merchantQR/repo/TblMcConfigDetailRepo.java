package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblMcConfigDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblMcConfigDetailRepo extends JpaRepository<TblMcConfigDetail, Long> {
    TblMcConfigDetail findByMcConfigDetailId(long mcConfigDetailId);
}
