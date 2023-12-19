package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TblUserRepo extends JpaRepository<TblUser,Integer> {

    TblUser findByName(String name);

    @Query(value = "SELECT * \n" +
            "FROM qrmerchant.tbl_user U\n" +
            "INNER JOIN qrmerchant.tbl_user_role R ON U.USER_ID = R.USER_ID \n" +
            "WHERE U.NAME =  COALESCE(NULLIF(:name,''),U.NAME)\n" +
            "AND R.ROLE_ID =  COALESCE(NULLIF(:role,''),R.ROLE_ID)\n" +
            "AND U.CREATEUSER = COALESCE(NULLIF(:user,''),U.CREATEUSER) \n" +
            "AND DATE(U.CREATEDATE) = COALESCE(NULLIF(:date,''), DATE(U.CREATEDATE)) \n" +
            "AND U.STATUS_ID = COALESCE(NULLIF(:status,''),U.STATUS_ID)", nativeQuery = true)
    List<TblUser> getAllUsersBySearch(String name, String role,String user, String date, String status);

    List<TblUser> findByIsActive(String setYes);
}
