package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_RESPONSE_MESSAGE_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_RESPONSE_MESSAGE_TYPE")
@NamedQuery(name="LkpResponseMessageType.findAll", query="SELECT l FROM LkpResponseMessageType l")
public class LkpResponseMessageType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_RESPONSE_MESSAGE_TYPE_RESPONSEMESSAGETYPEID_GENERATOR", sequenceName="LKP_RESPONSE_MESSAGE_TYPE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_RESPONSE_MESSAGE_TYPE_RESPONSEMESSAGETYPEID_GENERATOR")
	@Column(name="RESPONSE_MESSAGE_TYPE_ID")
	private long responseMessageTypeId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="RESPONSE_MESSAGE_TYPE_CODE")
	private String responseMessageTypeCode;

	@Column(name="RESPONSE_MESSAGE_TYPE_DESCR")
	private String responseMessageTypeDescr;

	@Column(name="RESPONSE_MESSAGE_TYPE_NAME")
	private String responseMessageTypeName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblResponseMessage
	@OneToMany(mappedBy="lkpResponseMessageType")...
	private List<TblResponseMessage> tblResponseMessages;

	public LkpResponseMessageType() {
	}

	public long getResponseMessageTypeId() {
		return this.responseMessageTypeId;
	}

	public void setResponseMessageTypeId(long responseMessageTypeId) {
		this.responseMessageTypeId = responseMessageTypeId;
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

	public String getResponseMessageTypeCode() {
		return this.responseMessageTypeCode;
	}

	public void setResponseMessageTypeCode(String responseMessageTypeCode) {
		this.responseMessageTypeCode = responseMessageTypeCode;
	}

	public String getResponseMessageTypeDescr() {
		return this.responseMessageTypeDescr;
	}

	public void setResponseMessageTypeDescr(String responseMessageTypeDescr) {
		this.responseMessageTypeDescr = responseMessageTypeDescr;
	}

	public String getResponseMessageTypeName() {
		return this.responseMessageTypeName;
	}

	public void setResponseMessageTypeName(String responseMessageTypeName) {
		this.responseMessageTypeName = responseMessageTypeName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblResponseMessage> getTblResponseMessages() {
		return this.tblResponseMessages;
	}

	public void setTblResponseMessages(List<TblResponseMessage> tblResponseMessages) {
		this.tblResponseMessages = tblResponseMessages;
	}

	public TblResponseMessage addTblResponseMessage(TblResponseMessage tblResponseMessage) {
		getTblResponseMessages().add(tblResponseMessage);
		tblResponseMessage.setLkpResponseMessageType(this);

		return tblResponseMessage;
	}

	public TblResponseMessage removeTblResponseMessage(TblResponseMessage tblResponseMessage) {
		getTblResponseMessages().remove(tblResponseMessage);
		tblResponseMessage.setLkpResponseMessageType(null);

		return tblResponseMessage;
	}

}