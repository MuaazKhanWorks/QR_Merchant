package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_MC_CONFIG database table.
 * 
 */
@Entity
@Table(name="TBL_MC_CONFIG")
@NamedQuery(name="TblMcConfig.findAll", query="SELECT t FROM TblMcConfig t")
public class TblMcConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MC_CONFIG_MCCONFIGID_GENERATOR", sequenceName="TBL_MC_CONFIG_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MC_CONFIG_MCCONFIGID_GENERATOR")
	@Column(name="MC_CONFIG_ID")
	private long mcConfigId;

	@Column(name="CONFIG_NAME")
	private String configName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="EDIT_DETAIL_URL")
	private String editDetailUrl;

	@Column(name="FORM_NAME")
	private String formName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="REQUEST_TYPE")
	private String requestType;

	@Column(name="TABLE_NAME")
	private String tableName;

	private BigDecimal updateindex;

	@Column(name="VIEW_DETAIL_URL")
	private String viewDetailUrl;

	//bi-directional many-to-one association to TblMcConfigDetail
	@OneToMany(mappedBy="tblMcConfig")
	private List<TblMcConfigDetail> tblMcConfigDetails;

	public TblMcConfig() {
	}

	public long getMcConfigId() {
		return this.mcConfigId;
	}

	public void setMcConfigId(long mcConfigId) {
		this.mcConfigId = mcConfigId;
	}

	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
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

	public String getEditDetailUrl() {
		return this.editDetailUrl;
	}

	public void setEditDetailUrl(String editDetailUrl) {
		this.editDetailUrl = editDetailUrl;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
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

	public String getRequestType() {
		return this.requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getViewDetailUrl() {
		return this.viewDetailUrl;
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

}