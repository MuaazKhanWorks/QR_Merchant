package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MERCHAN_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="MERCHAN_ACCOUNT")
@NamedQuery(name="MerchanAccount.findAll", query="SELECT m FROM MerchanAccount m")
public class MerchanAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MERCHAN_ACCOUNT_MERCHANACCOUNTID_GENERATOR", sequenceName="MERCHAN_ACCOUNT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MERCHAN_ACCOUNT_MERCHANACCOUNTID_GENERATOR")
	@Column(name="MERCHAN_ACCOUNT_ID")
	private long merchanAccountId;

	@Column(name="ACC_UPDATE")
	private BigDecimal accUpdate;

	@Column(name="ACTION_AUTHORIZATION_ID")
	private BigDecimal actionAuthorizationId;

	@Column(name="ACTION_STATUS_ID")
	private String actionStatusId;

	@Column(name="BUSINESS_ADDRESS")
	private String businessAddress;

	@Column(name="BUSINESS_NAME")
	private String businessName;

	@Column(name="CHK_COMMENTS")
	private String chkComments;

	private String city;

	private String cnic;

	private String comments;

	@Column(name="CONSUMER_NAME")
	private String consumerName;

	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="CUSTOMER_ID")
	private BigDecimal customerId;

	@Column(name="EXPECTED_MONTHLY_SALES")
	private String expectedMonthlySales;

	@Column(name="ID_N")
	private BigDecimal idN;

	@Column(name="ID_TYPE")
	private BigDecimal idType;

	@Column(name="IS_DELETE")
	private BigDecimal isDelete;

	private String latitude;

	private String longitude;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="PREVIOUS_ACTION_STATE_ID")
	private BigDecimal previousActionStateId;

	@Column(name="PREVIOUS_REGISTRATION_STATE_ID")
	private BigDecimal previousRegistrationStateId;

	@Column(name="QR_DATA")
	private String qrData;

	@Column(name="REGISTRATION_STATE_ID")
	private BigDecimal registrationStateId;

	private String reserved1;

	private String reserved10;

	private String reserved2;

	private String reserved3;

	private String reserved4;

	private String reserved5;

	private String reserved6;

	private String reserved7;

	private String reserved8;

	private String reserved9;

	@Column(name="TILL_ID")
	private BigDecimal tillId;

	@Column(name="TYPE_OF_BUSINESS")
	private String typeOfBusiness;

	@Column(name="UPDATED_BY")
	private BigDecimal updatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_ON")
	private Date updatedOn;

	public MerchanAccount() {
	}

	public long getMerchanAccountId() {
		return this.merchanAccountId;
	}

	public void setMerchanAccountId(long merchanAccountId) {
		this.merchanAccountId = merchanAccountId;
	}

	public BigDecimal getAccUpdate() {
		return this.accUpdate;
	}

	public void setAccUpdate(BigDecimal accUpdate) {
		this.accUpdate = accUpdate;
	}

	public BigDecimal getActionAuthorizationId() {
		return this.actionAuthorizationId;
	}

	public void setActionAuthorizationId(BigDecimal actionAuthorizationId) {
		this.actionAuthorizationId = actionAuthorizationId;
	}

	public String getActionStatusId() {
		return this.actionStatusId;
	}

	public void setActionStatusId(String actionStatusId) {
		this.actionStatusId = actionStatusId;
	}

	public String getBusinessAddress() {
		return this.businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getChkComments() {
		return this.chkComments;
	}

	public void setChkComments(String chkComments) {
		this.chkComments = chkComments;
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

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getConsumerName() {
		return this.consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public String getExpectedMonthlySales() {
		return this.expectedMonthlySales;
	}

	public void setExpectedMonthlySales(String expectedMonthlySales) {
		this.expectedMonthlySales = expectedMonthlySales;
	}

	public BigDecimal getIdN() {
		return this.idN;
	}

	public void setIdN(BigDecimal idN) {
		this.idN = idN;
	}

	public BigDecimal getIdType() {
		return this.idType;
	}

	public void setIdType(BigDecimal idType) {
		this.idType = idType;
	}

	public BigDecimal getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(BigDecimal isDelete) {
		this.isDelete = isDelete;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public BigDecimal getPreviousActionStateId() {
		return this.previousActionStateId;
	}

	public void setPreviousActionStateId(BigDecimal previousActionStateId) {
		this.previousActionStateId = previousActionStateId;
	}

	public BigDecimal getPreviousRegistrationStateId() {
		return this.previousRegistrationStateId;
	}

	public void setPreviousRegistrationStateId(BigDecimal previousRegistrationStateId) {
		this.previousRegistrationStateId = previousRegistrationStateId;
	}

	public String getQrData() {
		return this.qrData;
	}

	public void setQrData(String qrData) {
		this.qrData = qrData;
	}

	public BigDecimal getRegistrationStateId() {
		return this.registrationStateId;
	}

	public void setRegistrationStateId(BigDecimal registrationStateId) {
		this.registrationStateId = registrationStateId;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved10() {
		return this.reserved10;
	}

	public void setReserved10(String reserved10) {
		this.reserved10 = reserved10;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return this.reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getReserved4() {
		return this.reserved4;
	}

	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}

	public String getReserved5() {
		return this.reserved5;
	}

	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}

	public String getReserved6() {
		return this.reserved6;
	}

	public void setReserved6(String reserved6) {
		this.reserved6 = reserved6;
	}

	public String getReserved7() {
		return this.reserved7;
	}

	public void setReserved7(String reserved7) {
		this.reserved7 = reserved7;
	}

	public String getReserved8() {
		return this.reserved8;
	}

	public void setReserved8(String reserved8) {
		this.reserved8 = reserved8;
	}

	public String getReserved9() {
		return this.reserved9;
	}

	public void setReserved9(String reserved9) {
		this.reserved9 = reserved9;
	}

	public BigDecimal getTillId() {
		return this.tillId;
	}

	public void setTillId(BigDecimal tillId) {
		this.tillId = tillId;
	}

	public String getTypeOfBusiness() {
		return this.typeOfBusiness;
	}

	public void setTypeOfBusiness(String typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
	}

	public BigDecimal getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}