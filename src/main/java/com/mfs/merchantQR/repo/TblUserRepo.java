package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TblUserRepo extends JpaRepository<TblUser, Long> {

    TblUser findByName(String name);

    @Query(value = "SELECT *  \n" +
            "FROM tbl_user U \n" +
            "INNER JOIN tbl_user_role R ON U.USER_ID = R.USER_ID  \n" +
            "WHERE U.NAME = NVL(:name,U.NAME)\n" +
            "AND R.ROLE_ID =  NVL(:role,R.ROLE_ID) \n" +
            "AND U.CREATEUSER = NVL(:user,U.CREATEUSER)  \n" +
            "AND U.STATUS_ID = NVL(:status,U.STATUS_ID)", nativeQuery = true)
    List<TblUser> getAllUsersBySearch(String name, String role, String user, String status);

    List<TblUser> findByIsActive(String setYes);

    TblUser findByNameAndPassword(String name, String password);

}
