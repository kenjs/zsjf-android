package com.cattsoft.mos.vo.mvo;

import java.sql.Date;
import java.util.Set;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-24 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysUserMVO {
    /**
     * 属性sysUserId 标识 属性
     */
    private String sysUserId;

    /**
     * 属性partyRoleId 持久化 属性
     */
    private String partyRoleId;

    /**
     * 属性partyRoleTypeId 持久化 属性
     */
    private String partyRoleTypeId;

    /**
     * 属性sysUserName 持久化 属性
     */
    private String sysUserName;

    /**
     * 属性password 持久化 属性
     */
    private String password;

    /**
     * 属性setPwdTime 持久化 属性
     */
    private Date setPwdTime;

    /**
     * 属性updatePwdTimenullable 持久化 属性
     */
    private Date updatePwdTime;

    /**
     * 属性lastPwdnullable 持久化 属性
     */
    private String lastPwd;

    /**
     * 属性createDate 持久化 属性
     */
    private Date createDate;

    /**
     * 属性sts 持久化 属性
     */
    private String sts;

    /**
     * 属性stsDate 持久化 属性
     */
    private Date stsDate;

    /** ******party表的一些信息************** */
    private String partyId;

    private String name;

    private String localNetId;

    private String areaId;

    private String branchId;

    private String servDeptId;

    private String partyType;

    private String partySts;

    private Date partyStsDate;

    /**
     * 属性loginLogs 持久化 属性
     */
    private Set loginLogs;

    private String loginFlag;

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getPartyRoleId() {
		return partyRoleId;
	}

	public void setPartyRoleId(String partyRoleId) {
		this.partyRoleId = partyRoleId;
	}

	public String getPartyRoleTypeId() {
		return partyRoleTypeId;
	}

	public void setPartyRoleTypeId(String partyRoleTypeId) {
		this.partyRoleTypeId = partyRoleTypeId;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getSetPwdTime() {
		return setPwdTime;
	}

	public void setSetPwdTime(Date setPwdTime) {
		this.setPwdTime = setPwdTime;
	}

	public Date getUpdatePwdTime() {
		return updatePwdTime;
	}

	public void setUpdatePwdTime(Date updatePwdTime) {
		this.updatePwdTime = updatePwdTime;
	}

	public String getLastPwd() {
		return lastPwd;
	}

	public void setLastPwd(String lastPwd) {
		this.lastPwd = lastPwd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public Date getStsDate() {
		return stsDate;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalNetId() {
		return localNetId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getServDeptId() {
		return servDeptId;
	}

	public void setServDeptId(String servDeptId) {
		this.servDeptId = servDeptId;
	}

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	public String getPartySts() {
		return partySts;
	}

	public void setPartySts(String partySts) {
		this.partySts = partySts;
	}

	public Date getPartyStsDate() {
		return partyStsDate;
	}

	public void setPartyStsDate(Date partyStsDate) {
		this.partyStsDate = partyStsDate;
	}

	public Set getLoginLogs() {
		return loginLogs;
	}

	public void setLoginLogs(Set loginLogs) {
		this.loginLogs = loginLogs;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

 
}
