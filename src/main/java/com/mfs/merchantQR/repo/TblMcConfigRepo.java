package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblMcConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblMcConfigRepo extends JpaRepository<TblMcConfig, Long> {

    TblMcConfig findByMcConfigId(long mcConfigId);

    TblMcConfig findByTableName(String tableName);
}
