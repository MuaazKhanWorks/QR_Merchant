package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * The persistent class for the tbl_user database table.
 * 
 */
@Entity
@Table(name="tbl_user")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private int createuser;


	@Column(name="EMAIL")
	private String email;


	@Column(name="IS_ACTIVE")
	private String isActive;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;



	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;


	@Column(name="MOBILE_NO")
	private String mobileNo;



	@Column(name="NAME")
	private String name;



	@Column(name="PASSWORD")
	private String password;



	@Column(name="PWD_UPDATE_FLAG")
	private String pwdUpdateFlag;



	@Column(name="STATUS_ID")
	private int statusId;



	@Column(name="UPDATEINDEX")
	private int updateindex;


	//bi-directional many-to-one association to TblMcConfigDetail
	@OneToMany(mappedBy="tblUser")
	private List<TblMcConfigDetail> tblMcConfigDetails;

	//bi-directional many-to-one association to TblMcPendingRequest
	@OneToMany(mappedBy="tblUser")
	private List<TblMcPendingRequest> tblMcPendingRequests;

	//bi-directional many-to-one association to TblMcRequest
	@OneToMany(mappedBy="tblUser")
	private List<TblMcRequest> tblMcRequests;

	//bi-directional many-to-one association to TblMcRequestAction
	@OneToMany(mappedBy="tblUser")
	private List<TblMcRequestAction> tblMcRequestActions;

	//bi-directional many-to-one association to TblUserRole

	@OneToMany(mappedBy="tblUser")
	private List<TblUserRole> tblUserRoles;

	@Transient
	private Map<String, List<TblMenu>> menuListMap;

	@Transient
	private String token;

	public TblUser() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, List<TblMenu>> getMenuListMap() {
		return menuListMap;
	}

	public void setMenuListMap(Map<String, List<TblMenu>> menuListMap) {
		this.menuListMap = menuListMap;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwdUpdateFlag() {
		return pwdUpdateFlag;
	}

	public void setPwdUpdateFlag(String pwdUpdateFlag) {
		this.pwdUpdateFlag = pwdUpdateFlag;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(int updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblMcConfigDetail> getTblMcConfigDetails() {
		return this.tblMcConfigDetails;
	}

	public void setTblMcConfigDetails(List<TblMcConfigDetail> tblMcConfigDetails) {
		this.tblMcConfigDetails = tblMcConfigDetails;
	}

	public TblMcConfigDetail addTblMcConfigDetail(TblMcConfigDetail tblMcConfigDetail) {
		getTblMcConfigDetails().add(tblMcConfigDetail);
		tblMcConfigDetail.setTblUser(this);

		return tblMcConfigDetail;
	}

	public TblMcConfigDetail removeTblMcConfigDetail(TblMcConfigDetail tblMcConfigDetail) {
		getTblMcConfigDetails().remove(tblMcConfigDetail);
		tblMcConfigDetail.setTblUser(null);

		return tblMcConfigDetail;
	}

	public List<TblMcPendingRequest> getTblMcPendingRequests() {
		return this.tblMcPendingRequests;
	}

	public void setTblMcPendingRequests(List<TblMcPendingRequest> tblMcPendingRequests) {
		this.tblMcPendingRequests = tblMcPendingRequests;
	}

	public TblMcPendingRequest addTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		getTblMcPendingRequests().add(tblMcPendingRequest);
		tblMcPendingRequest.setTblUser(this);

		return tblMcPendingRequest;
	}

	public TblMcPendingRequest removeTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		getTblMcPendingRequests().remove(tblMcPendingRequest);
		tblMcPendingRequest.setTblUser(null);

		return tblMcPendingRequest;
	}

	public List<TblMcRequest> getTblMcRequests() {
		return this.tblMcRequests;
	}

	public void setTblMcRequests(List<TblMcRequest> tblMcRequests) {
		this.tblMcRequests = tblMcRequests;
	}

	public TblMcRequest addTblMcRequest(TblMcRequest tblMcRequest) {
		getTblMcRequests().add(tblMcRequest);
		tblMcRequest.setTblUser(this);

		return tblMcRequest;
	}

	public TblMcRequest removeTblMcRequest(TblMcRequest tblMcRequest) {
		getTblMcRequests().remove(tblMcRequest);
		tblMcRequest.setTblUser(null);

		return tblMcRequest;
	}

	public List<TblMcRequestAction> getTblMcRequestActions() {
		return this.tblMcRequestActions;
	}

	public void setTblMcRequestActions(List<TblMcRequestAction> tblMcRequestActions) {
		this.tblMcRequestActions = tblMcRequestActions;
	}

	public TblMcRequestAction addTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().add(tblMcRequestAction);
		tblMcRequestAction.setTblUser(this);

		return tblMcRequestAction;
	}

	public TblMcRequestAction removeTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().remove(tblMcRequestAction);
		tblMcRequestAction.setTblUser(null);

		return tblMcRequestAction;
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