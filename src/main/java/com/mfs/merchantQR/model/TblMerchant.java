package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_merchant database table.
 * 
 */
@Entity
@Table(name="tbl_merchant")
@NamedQuery(name="TblMerchant.findAll", query="SELECT t FROM TblMerchant t")
public class TblMerchant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MERCHANT_ID")
	private int merchantId;

	@Column(name="CITY")
	private String city;

	@Column(name="CNIC")
	private String cnic;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;

	@Column(name="CREATEUSER")
	private Integer createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="DOWNLOAD_STATUS")
	private String downlaodStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;

	@Column(name="LASTUPDATEUSER")
	private Integer lastupdateuser;

	@Column(name="MERCHANT_MSISDN")
	private String merchantMsisdn;

	@Column(name="MERCHANT_NAME")
	private String merchantName;

	@Column(name="QR_CODE")
	private String qrCode;

	@Column(name="TILL_ID")
	private String tillId;

	@Column(name="UPDATEINDEX")
	private Integer updateindex;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	public TblMerchant() {
	}


	public String getDownlaodStatus() {
		return downlaodStatus;
	}

	public void setDownlaodStatus(String downlaodStatus) {
		this.downlaodStatus = downlaodStatus;
	}

	public int getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCnic() {
		return this.cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public int getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(Integer createuser) {
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

	public int getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(Integer lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getMerchantMsisdn() {
		return this.merchantMsisdn;
	}

	public void setMerchantMsisdn(String merchantMsisdn) {
		this.merchantMsisdn = merchantMsisdn;
	}

	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getQrCode() {
		return this.qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getTillId() {
		return this.tillId;
	}

	public void setTillId(String tillId) {
		this.tillId = tillId;
	}

	public int getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(Integer updateindex) {
		this.updateindex = updateindex;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

}