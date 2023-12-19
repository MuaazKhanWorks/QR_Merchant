package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblMerchant;
import com.mfs.merchantQR.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TblMerchantRepo extends JpaRepository<TblMerchant,Integer> {

    @Query(value = "SELECT *\n" +
            "FROM qrmerchant.tbl_merchant \n" +
            "WHERE \n" +
            "    MERCHANT_NAME = COALESCE(NULLIF(:merchantName, ''), MERCHANT_NAME)\n" +
            "    AND MERCHANT_MSISDN = COALESCE(NULLIF(:merchantMsdsdn, ''), MERCHANT_MSISDN)\n" +
            "    AND DATE(CREATEDATE) = COALESCE(NULLIF(:date,''), DATE(CREATEDATE)) \n" +
            "    AND CREATEUSER = COALESCE(NULLIF(:createUser,''), DATE(CREATEUSER)) \n" +
            "    AND STATUS_ID = COALESCE(NULLIF(:status, ''), STATUS_ID)", nativeQuery = true)
    List<TblMerchant> getAllMerchantBySearch(String merchantName, String merchantMsdsdn, String date, String createUser, String status);

}
