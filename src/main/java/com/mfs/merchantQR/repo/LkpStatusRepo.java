package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.LkpStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LkpStatusRepo extends JpaRepository<LkpStatus,Long> {

    LkpStatus findBystatusCode(String statusPending);

    List<LkpStatus> findByIsActive(String setYes);

}
