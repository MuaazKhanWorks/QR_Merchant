package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_MERCHANT database table.
 * 
 */
@Entity
@Table(name="TBL_MERCHANT")
@NamedQuery(name="TblMerchant.findAll", query="SELECT t FROM TblMerchant t")
public class TblMerchant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MERCHANT_MERCHANTID_GENERATOR", sequenceName="TBL_MERCHANT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MERCHANT_MERCHANTID_GENERATOR")
	@Column(name="MERCHANT_ID")
	private long merchantId;

	private String city;

	private String cnic;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DOWNLOAD_STATUS")
	private String downloadStatus;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MERCHANT_MSISDN")
	private String merchantMsisdn;

	@Column(name="MERCHANT_NAME")
	private String merchantName;

	@Column(name="QR_CODE")
	private String qrCode;

	@Column(name="TILL_ID")
	private String tillId;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	@Transient
	private String downlaodStatus;

	public String getDownlaodStatus() {
		return downlaodStatus;
	}

	public void setDownlaodStatus(String downlaodStatus) {
		this.downlaodStatus = downlaodStatus;
	}

	public TblMerchant() {
	}

	public long getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(long merchantId) {
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

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getDownloadStatus() {
		return this.downloadStatus;
	}

	public void setDownloadStatus(String downloadStatus) {
		this.downloadStatus = downloadStatus;
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

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

}