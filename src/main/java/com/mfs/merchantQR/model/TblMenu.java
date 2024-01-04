package com.mfs.merchantQR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_menu database table.
 * 
 */
@Entity
@Table(name="tbl_menu")
@NamedQuery(name="TblMenu.findAll", query="SELECT t FROM TblMenu t")
public class TblMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MENU_ID")
	private int menuId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDATE")
	private Date createdate;


	@Column(name="CREATEUSER")
	private int createuser;


	@Column(name="ICON")
	private String icon;



	@Column(name="IS_ACTIVE")
	private String isActive;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTUPDATEDATE")
	private Date lastupdatedate;


	@Column(name="LASTUPDATEUSER")
	private int lastupdateuser;



	@Column(name="MENU_CODE")
	private String menuCode;


	@Column(name="MENU_DESCR")
	private String menuDescr;


	@Column(name="MENU_PATH")
	private String menuPath;



	@Column(name="MENU_TYPE")
	private String menuType;



	@Column(name="PARENT_MENU")
	private int parentMenu;



	@Column(name="SORT_SEQ")
	private String sortSeq;



	@Column(name="UPDATEINDEX")
	private int updateindex;


	//bi-directional many-to-one association to TblRoleRight
	@OneToMany(mappedBy="tblMenu")
	@JsonIgnore
	private List<TblRoleRight> tblRoleRights;

	@Transient
	private String deleteAllowed;

	@Transient
	private String insertAllowed;


	@Transient
	private String updateAllowed;

	@Transient
	private String viewAllowed;




	public TblMenu() {
	}

	public String getDeleteAllowed() {
		return deleteAllowed;
	}

	public void setDeleteAllowed(String deleteAllowed) {
		this.deleteAllowed = deleteAllowed;
	}

	public String getInsertAllowed() {
		return insertAllowed;
	}

	public void setInsertAllowed(String insertAllowed) {
		this.insertAllowed = insertAllowed;
	}

	public String getUpdateAllowed() {
		return updateAllowed;
	}

	public void setUpdateAllowed(String updateAllowed) {
		this.updateAllowed = updateAllowed;
	}

	public String getViewAllowed() {
		return viewAllowed;
	}

	public void setViewAllowed(String viewAllowed) {
		this.viewAllowed = viewAllowed;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public void setLastupdateuser(int lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuDescr() {
		return menuDescr;
	}

	public void setMenuDescr(String menuDescr) {
		this.menuDescr = menuDescr;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public int getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(int parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(String sortSeq) {
		this.sortSeq = sortSeq;
	}

	public int getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(int updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblRoleRight> getTblRoleRights() {
		return this.tblRoleRights;
	}

	public void setTblRoleRights(List<TblRoleRight> tblRoleRights) {
		this.tblRoleRights = tblRoleRights;
	}

	public TblRoleRight addTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().add(tblRoleRight);
		tblRoleRight.setTblMenu(this);

		return tblRoleRight;
	}

	public TblRoleRight removeTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().remove(tblRoleRight);
		tblRoleRight.setTblMenu(null);

		return tblRoleRight;
	}

}