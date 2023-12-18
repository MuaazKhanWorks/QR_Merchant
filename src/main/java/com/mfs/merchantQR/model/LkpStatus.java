package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lkp_status database table.
 * 
 */
@Entity
@Table(name="lkp_status")
@NamedQuery(name="LkpStatus.findAll", query="SELECT l FROM LkpStatus l")
public class LkpStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATUS_ID")
	private int statusId;

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


	@Column(name="STATUS_CODE")
	private String statusCode;


	@Column(name="STATUS_DESCR")
	private String statusDescr;


	@Column(name="STATUS_NAME")
	private String statusName;


	@Column(name="UPDATEINDEX")
	private Integer updateindex;


	//bi-directional many-to-one association to TblMcPendingRequest
	@OneToMany(mappedBy="lkpStatus")
	private List<TblMcPendingRequest> tblMcPendingRequests;

	//bi-directional many-to-one association to TblMcRequest
	@OneToMany(mappedBy="lkpStatus")
	private List<TblMcRequest> tblMcRequests;

	//bi-directional many-to-one association to TblMcRequestAction
	@OneToMany(mappedBy="lkpStatus")
	private List<TblMcRequestAction> tblMcRequestActions;

	//bi-directional many-to-one association to TblMerchant
	@OneToMany(mappedBy="lkpStatus")
	private List<TblMerchant> tblMerchants;

//	//bi-directional many-to-one association to TblRole
//	@OneToMany(mappedBy="lkpStatus")
//	private List<TblRole> tblRoles;

	public LkpStatus() {
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescr() {
		return statusDescr;
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(Integer updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblMcPendingRequest> getTblMcPendingRequests() {
		return this.tblMcPendingRequests;
	}

	public void setTblMcPendingRequests(List<TblMcPendingRequest> tblMcPendingRequests) {
		this.tblMcPendingRequests = tblMcPendingRequests;
	}

	public TblMcPendingRequest addTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		getTblMcPendingRequests().add(tblMcPendingRequest);
		tblMcPendingRequest.setLkpStatus(this);

		return tblMcPendingRequest;
	}

	public TblMcPendingRequest removeTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		getTblMcPendingRequests().remove(tblMcPendingRequest);
		tblMcPendingRequest.setLkpStatus(null);

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
		tblMcRequest.setLkpStatus(this);

		return tblMcRequest;
	}

	public TblMcRequest removeTblMcRequest(TblMcRequest tblMcRequest) {
		getTblMcRequests().remove(tblMcRequest);
		tblMcRequest.setLkpStatus(null);

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
		tblMcRequestAction.setLkpStatus(this);

		return tblMcRequestAction;
	}

	public TblMcRequestAction removeTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().remove(tblMcRequestAction);
		tblMcRequestAction.setLkpStatus(null);

		return tblMcRequestAction;
	}

	public List<TblMerchant> getTblMerchants() {
		return this.tblMerchants;
	}

	public void setTblMerchants(List<TblMerchant> tblMerchants) {
		this.tblMerchants = tblMerchants;
	}

	public TblMerchant addTblMerchant(TblMerchant tblMerchant) {
		getTblMerchants().add(tblMerchant);
		tblMerchant.setLkpStatus(this);

		return tblMerchant;
	}

	public TblMerchant removeTblMerchant(TblMerchant tblMerchant) {
		getTblMerchants().remove(tblMerchant);
		tblMerchant.setLkpStatus(null);

		return tblMerchant;
	}

//	public List<TblRole> getTblRoles() {
//		return this.tblRoles;
//	}
//
//	public void setTblRoles(List<TblRole> tblRoles) {
//		this.tblRoles = tblRoles;
//	}

//	public TblRole addTblRole(TblRole tblRole) {
//		getTblRoles().add(tblRole);
//		tblRole.setLkpStatus(this);
//
//		return tblRole;
//	}
//
//	public TblRole removeTblRole(TblRole tblRole) {
//		getTblRoles().remove(tblRole);
//		tblRole.setLkpStatus(null);
//
//		return tblRole;
//	}

}