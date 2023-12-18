package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_role database table.
 * 
 */
@Entity
@Table(name="tbl_role")
@NamedQuery(name="TblRole.findAll", query="SELECT t FROM TblRole t")
public class TblRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID")
	private int roleId;

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



	@Column(name="ROLE_CODE")
	private String roleCode;



	@Column(name="ROLE_DESCR")
	private String roleDescr;



	@Column(name="UPDATEINDEX")
	private Integer updateindex;



//	//bi-directional many-to-one association to LkpStatus
//	@ManyToOne
//	@JoinColumn(name="STATUS_ID")
//	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblUserRole
	@OneToMany(mappedBy="tblRole")
	private List<TblUserRole> tblUserRoles;

	public TblRole() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDescr() {
		return roleDescr;
	}

	public void setRoleDescr(String roleDescr) {
		this.roleDescr = roleDescr;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(Integer updateindex) {
		this.updateindex = updateindex;
	}

//	public LkpStatus getLkpStatus() {
//		return this.lkpStatus;
//	}
//
//	public void setLkpStatus(LkpStatus lkpStatus) {
//		this.lkpStatus = lkpStatus;
//	}

	public List<TblUserRole> getTblUserRoles() {
		return this.tblUserRoles;
	}

	public void setTblUserRoles(List<TblUserRole> tblUserRoles) {
		this.tblUserRoles = tblUserRoles;
	}

	public TblUserRole addTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().add(tblUserRole);
		tblUserRole.setTblRole(this);

		return tblUserRole;
	}

	public TblUserRole removeTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().remove(tblUserRole);
		tblUserRole.setTblRole(null);

		return tblUserRole;
	}

}