package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblResponseMessageRepo extends JpaRepository<TblResponseMessage,Integer> {

    TblResponseMessage findByResponseMessageDescr(String responseMessageDesc);

}
