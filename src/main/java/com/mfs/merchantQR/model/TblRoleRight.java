package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_role_rights database table.
 * 
 */
@Entity
@Table(name="tbl_role_rights")
@NamedQuery(name="TblRoleRight.findAll", query="SELECT t FROM TblRoleRight t")
public class TblRoleRight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_RIGHTS_ID")
	private int roleRightsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;



	@Column(name="CREATEUSER")
	private int createuser;




	@Column(name="DELETE_ALLOWED")
	private String deleteAllowed;


	@Column(name="INSERT_ALLOWED")
	private String insertAllowed;


	@Column(name="IS_ACTIVE")
	private String isActive;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;



	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;





	@Column(name="STATUS_ID")
	private int statusId;


	@Column(name="UPDATE_ALLOWED")
	private String updateAllowed;



	@Column(name="UPDATEINDEX")
	private int updateindex;


	@Column(name="VIEW_ALLOWED")
	private String viewAllowed;



	//bi-directional many-to-one association to TblMenu
	@ManyToOne
	@JoinColumn(name="MENU_ID")
	private TblMenu tblMenu;

	//bi-directional many-to-one association to TblRole
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private TblRole tblRole;

	public TblRoleRight() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getRoleRightsId() {
		return roleRightsId;
	}

	public void setRoleRightsId(int roleRightsId) {
		this.roleRightsId = roleRightsId;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public int getCreateuser() {
		return createuser;
	}

	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}

	public String getDeleteAllowed() {
		return deleteAllowed;
	}

	public void setDeleteAllowed(String deleteAllowed) {
		this.deleteAllowed = deleteAllowed;
	}

	public String getInsertAllowed() {
		return insertAllowed;
	}

	public void setInsertAllowed(String insertAllowed) {
		this.insertAllowed = insertAllowed;
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

	public void setLastupdateuser(int lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getUpdateAllowed() {
		return updateAllowed;
	}

	public void setUpdateAllowed(String updateAllowed) {
		this.updateAllowed = updateAllowed;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(int updateindex) {
		this.updateindex = updateindex;
	}

	public String getViewAllowed() {
		return viewAllowed;
	}

	public void setViewAllowed(String viewAllowed) {
		this.viewAllowed = viewAllowed;
	}

	public TblMenu getTblMenu() {
		return this.tblMenu;
	}

	public void setTblMenu(TblMenu tblMenu) {
		this.tblMenu = tblMenu;
	}

	public TblRole getTblRole() {
		return this.tblRole;
	}

	public void setTblRole(TblRole tblRole) {
		this.tblRole = tblRole;
	}

}