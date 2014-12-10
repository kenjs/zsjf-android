package com.cattsoft.mos.vo.mvo;

public class FilterMVO {
	private String[] filterTitle;// 筛选对话框窗口标题
	private String[][] filterChoice;// 筛选对话框选项
	private String[][] filterChoiceSql;// 筛选对话框选项对应的sql条目
	private boolean[] filterItemSelected;// 是否选中了选项
	private boolean[] filterCanceled;// 是否按了取消按钮
	private String[] requestSqlJsonKey;//请求中放在json中sql的key值
	private int sortSts;// 排序状态
	private boolean[][] defaultFilterStatus;//默认选中状态
	private boolean[][] remeberedFilterStatus;//记忆的选中状态
	public String[] getFilterTitle() {
		return filterTitle;
	}
	public void setFilterTitle(String[] filterTitle) {
		this.filterTitle = filterTitle;
	}
	public String[][] getFilterChoice() {
		return filterChoice;
	}
	public void setFilterChoice(String[][] filterChoice) {
		this.filterChoice = filterChoice;
	}
	public String[][] getFilterChoiceSql() {
		return filterChoiceSql;
	}
	public void setFilterChoiceSql(String[][] filterChoiceSql) {
		this.filterChoiceSql = filterChoiceSql;
	}
	public boolean[] getFilterItemSelected() {
		return filterItemSelected;
	}
	public void setFilterItemSelected(boolean[] filterItemSelected) {
		this.filterItemSelected = filterItemSelected;
	}
	public boolean[] getFilterCanceled() {
		return filterCanceled;
	}
	public void setFilterCanceled(boolean[] filterCanceled) {
		this.filterCanceled = filterCanceled;
	}
	public String[] getRequestSqlJsonKey() {
		return requestSqlJsonKey;
	}
	public void setRequestSqlJsonKey(String[] requestSqlJsonKey) {
		this.requestSqlJsonKey = requestSqlJsonKey;
	}
	public int getSortSts() {
		return sortSts;
	}
	public void setSortSts(int sortSts) {
		this.sortSts = sortSts;
	}
	public boolean[][] getDefaultFilterStatus() {
		return defaultFilterStatus;
	}
	public void setDefaultFilterStatus(boolean[][] defaultFilterStatus) {
		this.defaultFilterStatus = defaultFilterStatus;
	}
	public boolean[][] getRemeberedFilterStatus() {
		return remeberedFilterStatus;
	}
	public void setRemeberedFilterStatus(boolean[][] remeberedFilterStatus) {
		this.remeberedFilterStatus = remeberedFilterStatus;
	}
}
