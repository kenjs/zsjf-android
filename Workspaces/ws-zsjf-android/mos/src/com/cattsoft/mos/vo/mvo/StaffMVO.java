package com.cattsoft.mos.vo.mvo;

import java.sql.Date;

import com.cattsoft.mos.vo.svo.PartySVO;
import com.cattsoft.mos.vo.svo.StaffSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-26 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StaffMVO{
	
    private PartySVO partySVO = new PartySVO();

    private StaffSVO staffSVO = new StaffSVO();

    private String deptName;

    private String deptType;

    private String deptSts;

    private Date deptStsDate;

    private String certCode;

    private String certTypeId;

    private String contactAddr;

    private String postalCode;

    private Date certExpDate;

    private String contactId;

    private String partyIdentityId;
    
    private String  sysUserId;//默认创建
    
    private String passWord;//默认密码
    
    private String partyRoleType;
    
    private String sysUserName;//登录名

	public PartySVO getPartySVO() {
		return partySVO;
	}

	public void setPartySVO(PartySVO partySVO) {
		this.partySVO = partySVO;
	}

	public StaffSVO getStaffSVO() {
		return staffSVO;
	}

	public void setStaffSVO(StaffSVO staffSVO) {
		this.staffSVO = staffSVO;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getDeptSts() {
		return deptSts;
	}

	public void setDeptSts(String deptSts) {
		this.deptSts = deptSts;
	}

	public Date getDeptStsDate() {
		return deptStsDate;
	}

	public void setDeptStsDate(Date deptStsDate) {
		this.deptStsDate = deptStsDate;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public String getCertTypeId() {
		return certTypeId;
	}

	public void setCertTypeId(String certTypeId) {
		this.certTypeId = certTypeId;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getCertExpDate() {
		return certExpDate;
	}

	public void setCertExpDate(Date certExpDate) {
		this.certExpDate = certExpDate;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getPartyIdentityId() {
		return partyIdentityId;
	}

	public void setPartyIdentityId(String partyIdentityId) {
		this.partyIdentityId = partyIdentityId;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPartyRoleType() {
		return partyRoleType;
	}

	public void setPartyRoleType(String partyRoleType) {
		this.partyRoleType = partyRoleType;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
    
}
