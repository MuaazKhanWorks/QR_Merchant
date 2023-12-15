package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_mc_config_detail database table.
 * 
 */
@Entity
@Table(name="tbl_mc_config_detail")
@NamedQuery(name="TblMcConfigDetail.findAll", query="SELECT t FROM TblMcConfigDetail t")
public class TblMcConfigDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MC_CONFIG_DETAIL_ID")
	private int mcConfigDetailId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private int createuser;


	@Column(name="IS_ACTIVE")
	private String isActive;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;


	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;


	@Column(name="SEQ")
	private int seq;


	@Column(name="UPDATEINDEX")
	private int updateindex;


	//bi-directional many-to-one association to TblMcConfig
	@ManyToOne
	@JoinColumn(name="MC_CONFIG_ID")
	private TblMcConfig tblMcConfig;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	//bi-directional many-to-one association to TblMcPendingRequest
	@OneToMany(mappedBy="tblMcConfigDetail")
	private List<TblMcPendingRequest> tblMcPendingRequests;

	//bi-directional many-to-one association to TblMcRequestAction
	@OneToMany(mappedBy="tblMcConfigDetail")
	private List<TblMcRequestAction> tblMcRequestActions;

	public TblMcConfigDetail() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getMcConfigDetailId() {
		return mcConfigDetailId;
	}

	public void setMcConfigDetailId(int mcConfigDetailId) {
		this.mcConfigDetailId = mcConfigDetailId;
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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(int updateindex) {
		this.updateindex = updateindex;
	}

	public TblMcConfig getTblMcConfig() {
		return tblMcConfig;
	}

	public void setTblMcConfig(TblMcConfig tblMcConfig) {
		this.tblMcConfig = tblMcConfig;
	}

	public TblUser getTblUser() {
		return tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public List<TblMcPendingRequest> getTblMcPendingRequests() {
		return this.tblMcPendingRequests;
	}

	public void setTblMcPendingRequests(List<TblMcPendingRequest> tblMcPendingRequests) {
		this.tblMcPendingRequests = tblMcPendingRequests;
	}

	public TblMcPendingRequest addTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		getTblMcPendingRequests().add(tblMcPendingRequest);
		tblMcPendingRequest.setTblMcConfigDetail(this);

		return tblMcPendingRequest;
	}

	public TblMcPendingRequest removeTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		getTblMcPendingRequests().remove(tblMcPendingRequest);
		tblMcPendingRequest.setTblMcConfigDetail(null);

		return tblMcPendingRequest;
	}

	public List<TblMcRequestAction> getTblMcRequestActions() {
		return this.tblMcRequestActions;
	}

	public void setTblMcRequestActions(List<TblMcRequestAction> tblMcRequestActions) {
		this.tblMcRequestActions = tblMcRequestActions;
	}

	public TblMcRequestAction addTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().add(tblMcRequestAction);
		tblMcRequestAction.setTblMcConfigDetail(this);

		return tblMcRequestAction;
	}

	public TblMcRequestAction removeTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().remove(tblMcRequestAction);
		tblMcRequestAction.setTblMcConfigDetail(null);

		return tblMcRequestAction;
	}

}