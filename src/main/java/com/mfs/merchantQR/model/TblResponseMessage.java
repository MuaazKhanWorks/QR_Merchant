package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_response_message database table.
 * 
 */
@Entity
@Table(name="tbl_response_message")
@NamedQuery(name="TblResponseMessage.findAll", query="SELECT t FROM TblResponseMessage t")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TblResponseMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RESPONSE_MESSAGE_ID")
	private int responseMessageId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private Integer createuser;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;



	@Column(name="LASTUPDATEUSER")
	private Integer lastupdateuser;



	@Column(name="RESPONSE_MESSAGE_CODE")
	private String responseMessageCode;



	@Column(name="RESPONSE_MESSAGE_DESCR")
	private String responseMessageDescr;



	@Column(name="UPDATEINDEX")
	private Integer updateindex;



	//bi-directional many-to-one association to LkpResponseMessageType
	@ManyToOne
	@JoinColumn(name="RESPONSE_MESSAGE_TYPE_ID")
	private LkpResponseMessageType lkpResponseMessageType;

	public TblResponseMessage() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getResponseMessageId() {
		return responseMessageId;
	}



	public void setResponseMessageId(int responseMessageId) {
		this.responseMessageId = responseMessageId;
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

	public void setCreateuser(Integer createuser) {
		this.createuser = createuser;
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

	public void setLastupdateuser(Integer lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getResponseMessageCode() {
		return responseMessageCode;
	}

	public void setResponseMessageCode(String responseMessageCode) {
		this.responseMessageCode = responseMessageCode;
	}

	public String getResponseMessageDescr() {
		return responseMessageDescr;
	}

	public void setResponseMessageDescr(String responseMessageDescr) {
		this.responseMessageDescr = responseMessageDescr;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(Integer updateindex) {
		this.updateindex = updateindex;
	}

	public LkpResponseMessageType getLkpResponseMessageType() {
		return this.lkpResponseMessageType;
	}

	public void setLkpResponseMessageType(LkpResponseMessageType lkpResponseMessageType) {
		this.lkpResponseMessageType = lkpResponseMessageType;
	}

}