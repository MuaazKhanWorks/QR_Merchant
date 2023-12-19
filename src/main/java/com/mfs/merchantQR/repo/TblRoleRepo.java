package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblRoleRepo extends JpaRepository<TblRole,Integer> {
    List<TblRole> findByIsActive(String setYes);
}
