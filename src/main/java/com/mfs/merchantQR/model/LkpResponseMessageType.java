package com.mfs.merchantQR.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lkp_response_message_type database table.
 * 
 */
@Entity
@Table(name="lkp_response_message_type")
@NamedQuery(name="LkpResponseMessageType.findAll", query="SELECT l FROM LkpResponseMessageType l")
public class LkpResponseMessageType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RESPONSE_MESSAGE_TYPE_ID")
	private int responseMessageTypeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private int createuser;


	@Column(name="IS_ACTIVE")
	private String isActive;


	@Temporal(TemporalType.DATE)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;



	@Column(name="LASTUPDATEUSER")
	private Integer lastupdateuser;



	@Column(name="RESPONSE_MESSAGE_TYPE_CODE")
	private String responseMessageTypeCode;


	@Column(name="RESPONSE_MESSAGE_TYPE_DESCR")
	private String responseMessageTypeDescr;



	@Column(name="RESPONSE_MESSAGE_TYPE_NAME")
	private String responseMessageTypeName;



	@Column(name="UPDATEINDEX")
	private Integer updateindex;


	//bi-directional many-to-one association to TblResponseMessage
	@OneToMany(mappedBy="lkpResponseMessageType")
	private List<TblResponseMessage> tblResponseMessages;

	public LkpResponseMessageType() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getResponseMessageTypeId() {
		return responseMessageTypeId;
	}

	public void setResponseMessageTypeId(int responseMessageTypeId) {
		this.responseMessageTypeId = responseMessageTypeId;
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

//	public void setLastupdateuser(int lastupdateuser) {
//		this.lastupdateuser = lastupdateuser;
//	}

	public void setLastupdateuser(Integer lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getResponseMessageTypeCode() {
		return responseMessageTypeCode;
	}

	public void setResponseMessageTypeCode(String responseMessageTypeCode) {
		this.responseMessageTypeCode = responseMessageTypeCode;
	}

	public String getResponseMessageTypeDescr() {
		return responseMessageTypeDescr;
	}

	public void setResponseMessageTypeDescr(String responseMessageTypeDescr) {
		this.responseMessageTypeDescr = responseMessageTypeDescr;
	}



	public String getResponseMessageTypeName() {
		return responseMessageTypeName;
	}

	public void setResponseMessageTypeName(String responseMessageTypeName) {
		this.responseMessageTypeName = responseMessageTypeName;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(Integer updateindex) {
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