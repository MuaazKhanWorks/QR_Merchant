package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.MerchanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MerchanAccountRepo extends JpaRepository<MerchanAccount,Long> {

    @Query(value = "select 'GetfromAPI' QR_Images, \n" +
            "                   ma.mobile_no, \n" +
            "                   ma.cnic, \n" +
            "                   ma.business_name, \n" +
            "                   ma.city, \n" +
            "                   ma.business_address, \n" +
            "                   ma.type_of_business, \n" +
            "                   'Download Status', \n" +
            "                   ma.created_on, \n" +
            "                   ma.created_by, \n" +
            "                   rs.name RegistrationStatus \n" +
            "              from merchan_account ma \n" +
            "              join registration_state rs on ma.registration_state_id = rs.registration_state_id \n" +
            "             where ma.mobile_no = nvl(:mobileNo, ma.mobile_no)\n" +
            "               and ma.business_name = nvl(:businessName, ma.business_name)",nativeQuery = true)
//            "               and trunc(ma.created_on) = nvl(to_date(:createdOn,'yyyy-mm-dd'), trunc(ma.created_on)) \n" +
//            "               and ma.created_by = nvl(:createdBy, ma.created_by) \n" +
//            "               and rs.registration_state_id = nvl(:registrationStateId, rs.registration_state_id)", nativeQuery = true)
    List<Object> getAllMerchant( String mobileNo , String businessName);

}
