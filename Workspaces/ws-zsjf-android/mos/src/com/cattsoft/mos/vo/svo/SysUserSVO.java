package com.cattsoft.mos.vo.svo;

import java.sql.Timestamp;

public class SysUserSVO {
	
	 private String sysUserId;

	    private String partyRoleTypeId;

	    private String partyRoleId;

	    private String sysUserName;

	    private String password;

	    private Timestamp setPwdTime;

	    private Timestamp updatePwdTime;

	    private String lastPwd;

	    private Timestamp createDate;

	    private String sts;

	    private Timestamp stsDate;

	    private String localNetId;
	    
	    //added by yangkai 增加日志记录 2009-6-9   
	    private ActionLogSVO actionLog=null;

		public String getSysUserId() {
			return sysUserId;
		}

		public void setSysUserId(String sysUserId) {
			this.sysUserId = sysUserId;
		}

		public String getPartyRoleTypeId() {
			return partyRoleTypeId;
		}

		public void setPartyRoleTypeId(String partyRoleTypeId) {
			this.partyRoleTypeId = partyRoleTypeId;
		}

		public String getPartyRoleId() {
			return partyRoleId;
		}

		public void setPartyRoleId(String partyRoleId) {
			this.partyRoleId = partyRoleId;
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

		public Timestamp getSetPwdTime() {
			return setPwdTime;
		}

		public void setSetPwdTime(Timestamp setPwdTime) {
			this.setPwdTime = setPwdTime;
		}

		public Timestamp getUpdatePwdTime() {
			return updatePwdTime;
		}

		public void setUpdatePwdTime(Timestamp updatePwdTime) {
			this.updatePwdTime = updatePwdTime;
		}

		public String getLastPwd() {
			return lastPwd;
		}

		public void setLastPwd(String lastPwd) {
			this.lastPwd = lastPwd;
		}

		public Timestamp getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Timestamp createDate) {
			this.createDate = createDate;
		}

		public String getSts() {
			return sts;
		}

		public void setSts(String sts) {
			this.sts = sts;
		}

		public Timestamp getStsDate() {
			return stsDate;
		}

		public void setStsDate(Timestamp stsDate) {
			this.stsDate = stsDate;
		}

		public String getLocalNetId() {
			return localNetId;
		}

		public void setLocalNetId(String localNetId) {
			this.localNetId = localNetId;
		}

		public ActionLogSVO getActionLog() {
			return actionLog;
		}

		public void setActionLog(ActionLogSVO actionLog) {
			this.actionLog = actionLog;
		}
	    
}