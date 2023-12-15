package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_mc_config database table.
 * 
 */
@Entity
@Table(name="tbl_mc_config")
@NamedQuery(name="TblMcConfig.findAll", query="SELECT t FROM TblMcConfig t")
public class TblMcConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MC_CONFIG_ID")
	private int mcConfigId;

	@Column(name="CONFIG_NAME")
	private String configName;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private int createuser;


	@Column(name="EDIT_DETAIL_URL")
	private String editDetailUrl;


	@Column(name="FORM_NAME")
	private String formName;


	@Column(name="IS_ACTIVE")
	private String isActive;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;



	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;


	@Column(name="REQUEST_TYPE")
	private String requestType;



	@Column(name="TABLE_NAME")
	private String tableName;


	@Column(name="UPDATEINDEX")
	private int updateindex;


	@Column(name="VIEW_DETAIL_URL")
	private String viewDetailUrl;


	//bi-directional many-to-one association to TblMcConfigDetail
	@OneToMany(mappedBy="tblMcConfig")
	private List<TblMcConfigDetail> tblMcConfigDetails;

	//bi-directional many-to-one association to TblMcRequest
	@OneToMany(mappedBy="tblMcConfig")
	private List<TblMcRequest> tblMcRequests;

	public TblMcConfig() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getMcConfigId() {
		return mcConfigId;
	}

	public void setMcConfigId(int mcConfigId) {
		this.mcConfigId = mcConfigId;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
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

	public String getEditDetailUrl() {
		return editDetailUrl;
	}

	public void setEditDetailUrl(String editDetailUrl) {
		this.editDetailUrl = editDetailUrl;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
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

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(int updateindex) {
		this.updateindex = updateindex;
	}

	public String getViewDetailUrl() {
		return viewDetailUrl;
	}

	public void setViewDetailUrl(String viewDetailUrl) {
		this.viewDetailUrl = viewDetailUrl;
	}

	public List<TblMcConfigDetail> getTblMcConfigDetails() {
		return this.tblMcConfigDetails;
	}

	public void setTblMcConfigDetails(List<TblMcConfigDetail> tblMcConfigDetails) {
		this.tblMcConfigDetails = tblMcConfigDetails;
	}

	public TblMcConfigDetail addTblMcConfigDetail(TblMcConfigDetail tblMcConfigDetail) {
		getTblMcConfigDetails().add(tblMcConfigDetail);
		tblMcConfigDetail.setTblMcConfig(this);

		return tblMcConfigDetail;
	}

	public TblMcConfigDetail removeTblMcConfigDetail(TblMcConfigDetail tblMcConfigDetail) {
		getTblMcConfigDetails().remove(tblMcConfigDetail);
		tblMcConfigDetail.setTblMcConfig(null);

		return tblMcConfigDetail;
	}

	public List<TblMcRequest> getTblMcRequests() {
		return this.tblMcRequests;
	}

	public void setTblMcRequests(List<TblMcRequest> tblMcRequests) {
		this.tblMcRequests = tblMcRequests;
	}

	public TblMcRequest addTblMcRequest(TblMcRequest tblMcRequest) {
		getTblMcRequests().add(tblMcRequest);
		tblMcRequest.setTblMcConfig(this);

		return tblMcRequest;
	}

	public TblMcRequest removeTblMcRequest(TblMcRequest tblMcRequest) {
		getTblMcRequests().remove(tblMcRequest);
		tblMcRequest.setTblMcConfig(null);

		return tblMcRequest;
	}

}