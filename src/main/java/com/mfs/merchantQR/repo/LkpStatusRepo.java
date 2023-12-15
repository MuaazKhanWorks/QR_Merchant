package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.LkpStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpStatusRepo extends JpaRepository<LkpStatus,Long> {

    LkpStatus findBystatusCode(String statusPending);

}
