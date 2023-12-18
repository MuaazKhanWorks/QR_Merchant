package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_mc_request database table.
 * 
 */
@Entity
@Table(name="tbl_mc_request")
@NamedQuery(name="TblMcRequest.findAll", query="SELECT t FROM TblMcRequest t")
public class TblMcRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MC_REQUEST_ID")
	private int mcRequestId;

	@Column(name="ACTION_ID")
	private int actionId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private int createuser;


	@Column(name="FORM_NAME")
	private String formName;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;


	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;



	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MAKE_DATE")
	private Date makeDate;



	@Column(name="MAKER_COMMENTS")
	private String makerComments;


	@Column(name="REF_TABLE_ID")
	private int refTableId;


	@Column(name="REQUEST_TYPE")
	private String requestType;



	@Column(name="TABLE_NAME")
	private String tableName;


	@Lob
	@Column(name="UPDATE_JSON")
	private String updateJson;



	@Column(name="UPDATE_TYPE")
	private String updateType;



	@Column(name="UPDATEINDEX")
	private int updateindex;


	//bi-directional many-to-one association to TblMcPendingRequest
	@OneToMany(mappedBy="tblMcRequest")
	private List<TblMcPendingRequest> tblMcPendingRequests;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblMcConfig
	@ManyToOne
	@JoinColumn(name="MC_CONFIG_ID")
	private TblMcConfig tblMcConfig;

	//bi-directional many-to-one association to TblUser
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="MAKER_ID")
	private TblUser tblUser;

	//bi-directional many-to-one association to TblMcRequestAction
	@OneToMany(mappedBy="tblMcRequest")
	private List<TblMcRequestAction> tblMcRequestActions;

	public TblMcRequest() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getMcRequestId() {
		return mcRequestId;
	}

	public void setMcRequestId(int mcRequestId) {
		this.mcRequestId = mcRequestId;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
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

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
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

	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getMakerComments() {
		return makerComments;
	}

	public void setMakerComments(String makerComments) {
		this.makerComments = makerComments;
	}

	public int getRefTableId() {
		return refTableId;
	}

	public void setRefTableId(int refTableId) {
		this.refTableId = refTableId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUpdateJson() {
		return updateJson;
	}

	public void setUpdateJson(String updateJson) {
		this.updateJson = updateJson;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(int updateindex) {
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
		tblMcPendingRequest.setTblMcRequest(this);

		return tblMcPendingRequest;
	}

	public TblMcPendingRequest removeTblMcPendingRequest(TblMcPendingRequest tblMcPendingRequest) {
		getTblMcPendingRequests().remove(tblMcPendingRequest);
		tblMcPendingRequest.setTblMcRequest(null);

		return tblMcPendingRequest;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblMcConfig getTblMcConfig() {
		return this.tblMcConfig;
	}

	public void setTblMcConfig(TblMcConfig tblMcConfig) {
		this.tblMcConfig = tblMcConfig;
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
		tblMcRequestAction.setTblMcRequest(this);

		return tblMcRequestAction;
	}

	public TblMcRequestAction removeTblMcRequestAction(TblMcRequestAction tblMcRequestAction) {
		getTblMcRequestActions().remove(tblMcRequestAction);
		tblMcRequestAction.setTblMcRequest(null);

		return tblMcRequestAction;
	}

}