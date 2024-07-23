package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_RESPONSE_MESSAGE database table.
 * 
 */
@Entity
@Table(name="TBL_RESPONSE_MESSAGE")
@NamedQuery(name="TblResponseMessage.findAll", query="SELECT t FROM TblResponseMessage t")
public class TblResponseMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_RESPONSE_MESSAGE_RESPONSEMESSAGEID_GENERATOR", sequenceName="TBL_RESPONSE_MESSAGE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_RESPONSE_MESSAGE_RESPONSEMESSAGEID_GENERATOR")
	@Column(name="RESPONSE_MESSAGE_ID")
	private long responseMessageId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="RESPONSE_MESSAGE_CODE")
	private String responseMessageCode;

	@Column(name="RESPONSE_MESSAGE_DESCR")
	private String responseMessageDescr;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpResponseMessageType
	@ManyToOne
	@JoinColumn(name="RESPONSE_MESSAGE_TYPE_ID").....
	private LkpResponseMessageType lkpResponseMessageType;

	public TblResponseMessage() {
	}

	public long getResponseMessageId() {
		return this.responseMessageId;
	}

	public void setResponseMessageId(long responseMessageId) {
		this.responseMessageId = responseMessageId;
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

	public String getResponseMessageCode() {
		return this.responseMessageCode;
	}

	public void setResponseMessageCode(String responseMessageCode) {
		this.responseMessageCode = responseMessageCode;
	}

	public String getResponseMessageDescr() {
		return this.responseMessageDescr;
	}

	public void setResponseMessageDescr(String responseMessageDescr) {
		this.responseMessageDescr = responseMessageDescr;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpResponseMessageType getLkpResponseMessageType() {
		return this.lkpResponseMessageType;
	}

	public void setLkpResponseMessageType(LkpResponseMessageType lkpResponseMessageType) {
		this.lkpResponseMessageType = lkpResponseMessageType;
	}

}