package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_mc_request_action database table.
 * 
 */
@Entity
@Table(name="tbl_mc_request_action")
@NamedQuery(name="TblMcRequestAction.findAll", query="SELECT t FROM TblMcRequestAction t")
public class TblMcRequestAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MC_REQUEST_ACTION_ID")
	private int mcRequestActionId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_DATE")
	private Date checkDate;


	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;


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

	//bi-directional many-to-one association to TblMcPendingRequest
	@ManyToOne
	@JoinColumn(name="MC_PENDING_REQUEST_ID")
	private TblMcPendingRequest tblMcPendingRequest;

	//bi-directional many-to-one association to TblMcRequest
	@ManyToOne
	@JoinColumn(name="MC_REQUEST_ID")
	private TblMcRequest tblMcRequest;

	//bi-directional many-to-one association to TblUser

	@ManyToOne
	@JoinColumn(name="CHECKER_ID")
	private TblUser tblUser;

	public TblMcRequestAction() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getMcRequestActionId() {
		return mcRequestActionId;
	}

	public void setMcRequestActionId(int mcRequestActionId) {
		this.mcRequestActionId = mcRequestActionId;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckerComments() {
		return checkerComments;
	}

	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
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

	public TblMcPendingRequest getTblMcPendingRequest() {
		return this.tblMcPendingRequest;
	}

	public void setTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		this.tblMcPendingRequest = tblMcPendingRequest;
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

}