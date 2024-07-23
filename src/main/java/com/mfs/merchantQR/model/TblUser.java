package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * The persistent class for the TBL_USER database table.
 * 
 */
@Entity
@Table(name="TBL_USER")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
public class TblUser  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_USER_USERID_GENERATOR", sequenceName="TBL_USER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_USERID_GENERATOR")
	@Column(name="USER_ID")
	private long userId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String email;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	private String name;

	private String password;

	@Column(name="PWD_UPDATE_FLAG")
	private String pwdUpdateFlag;

	private BigDecimal updateindex;

//	//bi-directional many-to-one association to TblMcConfigDetail
//	@OneToMany(mappedBy="tblUser")
//	private List<TblMcConfigDetail> tblMcConfigDetails;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblUserRole
	@OneToMany(mappedBy="tblUser")
	@JsonIgnore
	private List<TblUserRole> tblUserRoles;

	@Transient
	private Map<BigDecimal, List<TblMenu>> menuListMap;

	@Transient
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<BigDecimal, List<TblMenu>> getMenuListMap() {
		return menuListMap;
	}

	public void setMenuListMap(Map<BigDecimal, List<TblMenu>> menuListMap) {
		this.menuListMap = menuListMap;
	}

	public TblUser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwdUpdateFlag() {
		return this.pwdUpdateFlag;
	}

	public void setPwdUpdateFlag(String pwdUpdateFlag) {
		this.pwdUpdateFlag = pwdUpdateFlag;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

//	public List<TblMcConfigDetail> getTblMcConfigDetails() {
//		return this.tblMcConfigDetails;
//	}
//
//	public void setTblMcConfigDetails(List<TblMcConfigDetail> tblMcConfigDetails) {
//		this.tblMcConfigDetails = tblMcConfigDetails;
//	}

//	public TblMcConfigDetail addTblMcConfigDetail(TblMcConfigDetail tblMcConfigDetail) {
//		getTblMcConfigDetails().add(tblMcConfigDetail);
//		tblMcConfigDetail.setTblUser(this);
//
//		return tblMcConfigDetail;
//	}

//	public TblMcConfigDetail removeTblMcConfigDetail(TblMcConfigDetail tblMcConfigDetail) {
//		getTblMcConfigDetails().remove(tblMcConfigDetail);
//		tblMcConfigDetail.setTblUser(null);
//
//		return tblMcConfigDetail;
//	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public List<TblUserRole> getTblUserRoles() {
		return this.tblUserRoles;
	}

	public void setTblUserRoles(List<TblUserRole> tblUserRoles) {
		this.tblUserRoles = tblUserRoles;
	}

	public TblUserRole addTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().add(tblUserRole);
		tblUserRole.setTblUser(this);

		return tblUserRole;
	}

	public TblUserRole removeTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().remove(tblUserRole);
		tblUserRole.setTblUser(null);

		return tblUserRole;
	}

}