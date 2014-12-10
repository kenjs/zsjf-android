package com.cattsoft.mos.vo.svo;



import java.util.Date;

public class ActionLogSVO {

	private String actionDomain = null;
	private String actionId = null;
	private String actionModule = null;
	private String actionText = null;
	private Date actionTime = null;
	private String actionType = null;
	private String loginId = null;
	private int flagActionDomain = 0;
	private int flagActionId = 0;
	private int flagActionModule = 0;
	private int flagActionText = 0;
	private int flagActionTime = 0;
	private int flagActionType = 0;
	private int flagLoginId = 0;

	public void clearFlagActionDomain(){
		flagActionDomain = 0 ;
	}
	public void clearFlagActionId(){
		flagActionId = 0 ;
	}
	public void clearFlagActionModule(){
		flagActionModule = 0 ;
	}
	public void clearFlagActionText(){
		flagActionText = 0 ;
	}
	public void clearFlagActionTime(){
		flagActionTime = 0 ;
	}
	public void clearFlagActionType(){
		flagActionType = 0 ;
	}
	public void clearFlagLoginId(){
		flagLoginId = 0 ;
	}
	public void clearAll(){ flagActionDomain = 0;
	flagActionId = 0;
	flagActionModule = 0;
	flagActionText = 0;
	flagActionTime = 0;
	flagActionType = 0;
	flagLoginId = 0;

	}
	public String getActionDomain() {
		return actionDomain;
	}
	public void setActionDomain(String actionDomain) {
		this.actionDomain = actionDomain;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getActionModule() {
		return actionModule;
	}
	public void setActionModule(String actionModule) {
		this.actionModule = actionModule;
	}
	public String getActionText() {
		return actionText;
	}
	public void setActionText(String actionText) {
		this.actionText = actionText;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public int getFlagActionDomain() {
		return flagActionDomain;
	}
	public void setFlagActionDomain(int flagActionDomain) {
		this.flagActionDomain = flagActionDomain;
	}
	public int getFlagActionId() {
		return flagActionId;
	}
	public void setFlagActionId(int flagActionId) {
		this.flagActionId = flagActionId;
	}
	public int getFlagActionModule() {
		return flagActionModule;
	}
	public void setFlagActionModule(int flagActionModule) {
		this.flagActionModule = flagActionModule;
	}
	public int getFlagActionText() {
		return flagActionText;
	}
	public void setFlagActionText(int flagActionText) {
		this.flagActionText = flagActionText;
	}
	public int getFlagActionTime() {
		return flagActionTime;
	}
	public void setFlagActionTime(int flagActionTime) {
		this.flagActionTime = flagActionTime;
	}
	public int getFlagActionType() {
		return flagActionType;
	}
	public void setFlagActionType(int flagActionType) {
		this.flagActionType = flagActionType;
	}
	public int getFlagLoginId() {
		return flagLoginId;
	}
	public void setFlagLoginId(int flagLoginId) {
		this.flagLoginId = flagLoginId;
	}
	
}