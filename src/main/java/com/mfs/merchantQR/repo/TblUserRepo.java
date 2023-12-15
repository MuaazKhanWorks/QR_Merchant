package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblUserRepo extends JpaRepository<TblUser,Integer> {

    TblUser findByName(String name);

}
