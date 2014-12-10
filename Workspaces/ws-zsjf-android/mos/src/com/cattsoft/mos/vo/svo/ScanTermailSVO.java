package com.cattsoft.mos.vo.svo;
/**
 * 终端领取设备信息
 * @author xueweiwei
 *
 */
public class ScanTermailSVO {
	
    private String macAddress;//设备条形码
    private String StaffId;//用户id
    private String localNetId;//本地网
    private String areaId;//服务区
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getStaffId() {
		return StaffId;
	}
	public void setStaffId(String staffId) {
		StaffId = staffId;
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
    
    
}
