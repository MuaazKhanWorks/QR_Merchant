package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_user_role database table.
 * 
 */
@Entity
@Table(name="tbl_user_role")
@NamedQuery(name="TblUserRole.findAll", query="SELECT t FROM TblUserRole t")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TblUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ROLE_ID")
	private int userRoleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private Integer createuser;



	@Column(name="IS_ACTIVE")
	private String isActive;



	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;



	@Column(name="LASTUPDATEUSER")
	private Integer lastupdateuser;



//	@Column(name="STATUS_ID")
//	private Integer statusId;

	@Column(name="UPDATEINDEX")
	private Integer updateindex;



	//bi-directional many-to-one association to TblRole

	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private TblRole tblRole;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	@JsonIgnore
	private TblUser tblUser;

	public TblUserRole() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getCreateuser() {
		return createuser;
	}

	public void setCreateuser(Integer createuser) {
		this.createuser = createuser;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public int getLastupdateuser() {
		return lastupdateuser;
	}

	public void setLastupdateuser(Integer lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

//	public int getStatusId() {
//		return statusId;
//	}
//
//	public void setStatusId(Integer statusId) {
//		this.statusId = statusId;
//	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(Integer updateindex) {
		this.updateindex = updateindex;
	}

	public TblRole getTblRole() {
		return this.tblRole;
	}

	public void setTblRole(TblRole tblRole) {
		this.tblRole = tblRole;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

}