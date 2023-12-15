package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_charging_plan database table.
 * 
 */
@Entity
@Table(name="tbl_charging_plan")
@NamedQuery(name="TblChargingPlan.findAll", query="SELECT t FROM TblChargingPlan t")
public class TblChargingPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_CHARGING_PLAN_CHARGINGPLANID_GENERATOR", sequenceName="TBL_CHARGING_PLAN_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_CHARGING_PLAN_CHARGINGPLANID_GENERATOR")
	@Column(name="CHARGING_PLAN_ID")
	private int chargingPlanId;

	@Column(name="AMOUNT")
	private String amount;

	@Column(name="AMOUNT_AFTER_DISCOUNT")
	private String amountAfterDiscount;

	@Column(name="CHARGES_PER_HIT")
	private String chargesPerHit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;

	@Column(name="CREATEUSER")
	private int createuser;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="DISCOUNT_PERCENTAGE")
	private String discountPercentage;

	@Column(name="DURATION")
	private String duration;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="FREE_HITS")
	private String freeHits;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;

	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;

	@Column(name="PAY_ASYOUGO")
	private String payAsyougo;

	@Column(name="PLAN_NAME")
	private String planName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="STATUS_ID")
	private int statusId;

	@Column(name="SUBSCRIPTION_CHARGES")
	private String subscriptionCharges;

	@Column(name="SUPPORT")
	private String support;

	@Column(name="TOTAL_HITS")
	private String totalHits;

	@Column(name="UPDATEINDEX")
	private String updateindex;

	public TblChargingPlan() {
	}

	public int getChargingPlanId() {
		return this.chargingPlanId;
	}

	public void setChargingPlanId(int chargingPlanId) {
		this.chargingPlanId = chargingPlanId;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountAfterDiscount() {
		return this.amountAfterDiscount;
	}

	public void setAmountAfterDiscount(String amountAfterDiscount) {
		this.amountAfterDiscount = amountAfterDiscount;
	}

	public String getChargesPerHit() {
		return this.chargesPerHit;
	}

	public void setChargesPerHit(String chargesPerHit) {
		this.chargesPerHit = chargesPerHit;
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

	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiscountPercentage() {
		return this.discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFreeHits() {
		return this.freeHits;
	}

	public void setFreeHits(String freeHits) {
		this.freeHits = freeHits;
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

	public void setLastupdateuser(int lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getPayAsyougo() {
		return this.payAsyougo;
	}

	public void setPayAsyougo(String payAsyougo) {
		this.payAsyougo = payAsyougo;
	}

	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getSubscriptionCharges() {
		return this.subscriptionCharges;
	}

	public void setSubscriptionCharges(String subscriptionCharges) {
		this.subscriptionCharges = subscriptionCharges;
	}

	public String getSupport() {
		return this.support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getTotalHits() {
		return this.totalHits;
	}

	public void setTotalHits(String totalHits) {
		this.totalHits = totalHits;
	}

	public String getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(String updateindex) {
		this.updateindex = updateindex;
	}

}