package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_STATUS database table.
 * 
 */
@Entity
@Table(name="LKP_STATUS")
@NamedQuery(name="LkpStatus.findAll", query="SELECT l FROM LkpStatus l")
public class LkpStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_STATUS_STATUSID_GENERATOR", sequenceName="LKP_STATUS_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_STATUS_STATUSID_GENERATOR")
	@Column(name="STATUS_ID")
	private long statusId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="STATUS_CODE")
	private String statusCode;

	@Column(name="STATUS_DESCR")
	private String statusDescr;

	@Column(name="STATUS_NAME")
	private String statusName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblMerchant
	@OneToMany(mappedBy="lkpStatus")
	@JsonIgnore
	private List<TblMerchant> tblMerchants;

	//bi-directional many-to-one association to TblRoleRight
	@OneToMany(mappedBy="lkpStatus")
	@JsonIgnore
	private List<TblRoleRight> tblRoleRights;

	//bi-directional many-to-one association to TblUser
	@OneToMany(mappedBy="lkpStatus")
	@JsonIgnore
	private List<TblUser> tblUsers;

	public LkpStatus() {
	}

	public long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
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

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescr() {
		return this.statusDescr;
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
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

	public List<TblRoleRight> getTblRoleRights() {
		return this.tblRoleRights;
	}

	public void setTblRoleRights(List<TblRoleRight> tblRoleRights) {
		this.tblRoleRights = tblRoleRights;
	}

	public TblRoleRight addTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().add(tblRoleRight);
		tblRoleRight.setLkpStatus(this);

		return tblRoleRight;
	}

	public TblRoleRight removeTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().remove(tblRoleRight);
		tblRoleRight.setLkpStatus(null);

		return tblRoleRight;
	}

	public List<TblUser> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}

	public TblUser addTblUser(TblUser tblUser) {
		getTblUsers().add(tblUser);
		tblUser.setLkpStatus(this);

		return tblUser;
	}

	public TblUser removeTblUser(TblUser tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setLkpStatus(null);

		return tblUser;
	}

}