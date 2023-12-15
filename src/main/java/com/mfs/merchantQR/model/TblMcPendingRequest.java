package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_mc_pending_request database table.
 * 
 */
@Entity
@Table(name="tbl_mc_pending_request")
@NamedQuery(name="TblMcPendingRequest.findAll", query="SELECT t FROM TblMcPendingRequest t")
public class TblMcPendingRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MC_PENDING_REQUEST_ID")
	private int mcPendingRequestId;

	@Column(name="ACTION_TAKEN")
	private String actionTaken;

	@Column(name="COMMENTS")
	private String comments;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private int createuser;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;


	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;


	@Column(name="SEQ")
	private int seq;


	@Column(name="UPDATEINDEX")
	private int updateindex;


	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblMcConfigDetail
	@ManyToOne
	@JoinColumn(name="MC_CONFIG_DETAIL_ID")
	private TblMcConfigDetail tblMcConfigDetail;

	//bi-directional many-to-one association to TblMcRequest
	@ManyToOne
	@JoinColumn(name="MC_REQUEST_ID")
	private TblMcRequest tblMcRequest;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	//bi-directional many-to-one association to TblMcRequestAction
	@OneToMany(mappedBy="tblMcPendingRequest")
	private List<TblMcRequestAction> tblMcRequestActions;

	public TblMcPendingRequest() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getMcPendingRequestId() {
		return mcPendingRequestId;
	}

	public void setMcPendingRequestId(int mcPendingRequestId) {
		this.mcPendingRequestId = mcPendingRequestId;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblMcConfigDetail getTblMcConfigDetail() {
		return this.tblMcConfigDetail;
	}

	public void setTblMcConfigDetail(TblMcConfigDetail tblMcConfigDetail) {
		this.tblMcConfigDetail = tblMcConfigDetail;
	}

	public TblMcRequest getTblMcRequest() {
		return this.tblMcRequest;
	}

	public void setTblMcRequest(TblMcRequest tblMcRequest) {
		this.tblMcRequest = tblMcRequest;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public List<TblMcRequestAction> getTblMcRequestActions() {
		return this.tblMcRequestActions;
	}

	public void setTblMcRequestActions(List<TblMcRequestAction> tblMcRequestActions) {
		this.tblMcRequestActions = tblMcRequestActions;
	}

	public TblMcRequestAction addTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().add(tblMcRequestAction);
		tblMcRequestAction.setTblMcPendingRequest(this);

		return tblMcRequestAction;
	}

	public TblMcRequestAction removeTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().remove(tblMcRequestAction);
		tblMcRequestAction.setTblMcPendingRequest(null);

		return tblMcRequestAction;
	}

}