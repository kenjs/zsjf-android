package com.cattsoft.mos.dataprocess;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.cattsoft.mos.util.JsonUtil;
import com.cattsoft.mos.vo.mvo.StaffExtendMVO;
import com.cattsoft.mos.vo.mvo.SysConfigCacheMVO;
import com.cattsoft.mos.vo.mvo.SysUserExtendedMVO;
import com.cattsoft.mos.vo.mvo.WorkAreaMVO;
import com.cattsoft.mos.vo.svo.StaffSVO;
import com.cattsoft.mos.vo.svo.SysUserSVO;

public class CacheProcess{
	
	private WorkAreaMVO loadWorkAreaMVO(JSONObject jsonObject) {

		if (jsonObject != null) {
			WorkAreaMVO workAreaMVO = new WorkAreaMVO();
			workAreaMVO.setWorkAreaId(JsonUtil.getString(jsonObject,"workAreaId"));
			workAreaMVO.setName(JsonUtil.getString(jsonObject,"name"));
			workAreaMVO.setWorkTypeId(JsonUtil.getString(jsonObject,"workTypeId"));
			workAreaMVO.setWorkTypeName(JsonUtil.getString(jsonObject,"workTypeName"));
			workAreaMVO.setWorkMode(JsonUtil.getString(jsonObject,"workMode"));
			workAreaMVO.setLocalNetId(JsonUtil.getString(jsonObject,"localNetId"));
			workAreaMVO.setLocalNetName(JsonUtil.getString(jsonObject,"localNetName"));
			workAreaMVO.setLocalNetIscenter(JsonUtil.getString(jsonObject,"localNetIscenter"));
			workAreaMVO.setAreaId(JsonUtil.getString(jsonObject,"areaId"));
			workAreaMVO.setAreaName(JsonUtil.getString(jsonObject,"areaName"));
			workAreaMVO.setAreaIscenter(JsonUtil.getString(jsonObject,"areaIscenter"));
			workAreaMVO.setServDeptId(JsonUtil.getString(jsonObject,"servDeptId"));
			workAreaMVO.setServDeptName(JsonUtil.getString(jsonObject,"servDeptName"));
			workAreaMVO.setBranchId(JsonUtil.getString(jsonObject,"branchId"));
			workAreaMVO.setBranchName(JsonUtil.getString(jsonObject,"branchName"));
			workAreaMVO.setDispatchLevel(JsonUtil.getString(jsonObject,"dispatchLevel"));
			workAreaMVO.setParentWorkAreaId(JsonUtil.getString(jsonObject,"parentWorkAreaId"));
			workAreaMVO.setStandardCode(JsonUtil.getString(jsonObject,"standardCode"));
			workAreaMVO.setSts(JsonUtil.getString(jsonObject,"sts"));
			workAreaMVO.setStsDate(JsonUtil.getDate(jsonObject,"stsDate"));
			workAreaMVO.setWorkType(JsonUtil.getString(jsonObject,"workType"));
			workAreaMVO.setAdminFlag(JsonUtil.getString(jsonObject,"adminFlag"));
			return workAreaMVO;
		}
		return null;
	}
	
	private SysUserExtendedMVO loadSysUserExtendedMVO(JSONObject jsonObject){
		if(jsonObject!=null){
		SysUserExtendedMVO sysUserExtendedMVO=new SysUserExtendedMVO();
		sysUserExtendedMVO.setSysUserSVO(loadSysUserSVO(JsonUtil.getJSONObject(jsonObject,"sysUserSVO")));
		sysUserExtendedMVO.setStaffExtendMVO(loadStaffExtendMVO(JsonUtil.getJSONObject(jsonObject,"staffExtendMVO")));
		sysUserExtendedMVO.setCurrentWorkAreaVO(loadWorkAreaMVO(JsonUtil.getJSONObject(jsonObject,"currentWorkAreaVO")));
		return sysUserExtendedMVO;
		}
		return null;
	}
	
	private SysUserSVO loadSysUserSVO(JSONObject jsonObject){
		if(jsonObject!=null){
		SysUserSVO sysUserSVO=new SysUserSVO();
		sysUserSVO.setSysUserId(JsonUtil.getString(jsonObject,"sysUserId"));
		sysUserSVO.setPartyRoleTypeId(JsonUtil.getString(jsonObject,"partyRoleTypeId"));
		sysUserSVO.setPartyRoleId(JsonUtil.getString(jsonObject,"partyRoleId"));
		sysUserSVO.setSysUserName(JsonUtil.getString(jsonObject,"sysUserName"));
		sysUserSVO.setPassword(JsonUtil.getString(jsonObject,"password"));
		sysUserSVO.setSetPwdTime(JsonUtil.getTimestamp(jsonObject,"setPwdTime"));
		sysUserSVO.setUpdatePwdTime(JsonUtil.getTimestamp(jsonObject,"updatePwdTime"));
		sysUserSVO.setLastPwd(JsonUtil.getString(jsonObject,"lastPwd"));
		sysUserSVO.setCreateDate(JsonUtil.getTimestamp(jsonObject,"createDate"));
		sysUserSVO.setSts(JsonUtil.getString(jsonObject,"sts"));
		sysUserSVO.setStsDate(JsonUtil.getTimestamp(jsonObject,"stsDate"));
		sysUserSVO.setLocalNetId(JsonUtil.getString(jsonObject,"localNetId"));
		return sysUserSVO;
		}
		return null;
	}
	
	private StaffExtendMVO loadStaffExtendMVO(JSONObject jsonObject){
		if(jsonObject!=null){
		StaffExtendMVO staffExtendMVO=new StaffExtendMVO();
		staffExtendMVO.setStaffSVO(loadStaffSVO(JsonUtil.getJSONObject(jsonObject,"staffSVO")));
		staffExtendMVO.setPartyId(JsonUtil.getString(jsonObject,"partyId"));
		staffExtendMVO.setPartyName(JsonUtil.getString(jsonObject,"partyName"));
		staffExtendMVO.setPartySts(JsonUtil.getString(jsonObject,"partySts"));
		staffExtendMVO.setPartyType(JsonUtil.getString(jsonObject,"partyType"));
		staffExtendMVO.setLocalNetId(JsonUtil.getString(jsonObject,"localNetId"));
		staffExtendMVO.setLocalNetName(JsonUtil.getString(jsonObject,"localNetName"));
		staffExtendMVO.setLocalNetIscenter(JsonUtil.getString(jsonObject,"localNetIscenter"));
		staffExtendMVO.setAreaId(JsonUtil.getString(jsonObject,"areaId"));
		staffExtendMVO.setAreaName(JsonUtil.getString(jsonObject,"areaName"));
		staffExtendMVO.setAreaIscenter(JsonUtil.getString(jsonObject,"areaIscenter"));
		staffExtendMVO.setServDeptId(JsonUtil.getString(jsonObject,"servDeptId"));
		staffExtendMVO.setServDeptName(JsonUtil.getString(jsonObject,"servDeptName"));
		staffExtendMVO.setBranchId(JsonUtil.getString(jsonObject,"branchId"));
		staffExtendMVO.setBranchName(JsonUtil.getString(jsonObject,"branchName"));
		return staffExtendMVO;
		}
		return null;
	}
	
	private StaffSVO loadStaffSVO(JSONObject jsonObject){
		if(jsonObject!=null){
		StaffSVO staffSVO=new StaffSVO();
		staffSVO.setCreateDate(JsonUtil.getTimestamp(jsonObject,"createDate"));
		staffSVO.setDeptId(JsonUtil.getString(jsonObject,"deptId"));
		staffSVO.setPartyId(JsonUtil.getString(jsonObject,"partyId"));
		staffSVO.setPosition(JsonUtil.getString(jsonObject,"position"));
		staffSVO.setStaffId(JsonUtil.getString(jsonObject,"staffId"));
		staffSVO.setStandardCode(JsonUtil.getString(jsonObject,"standardCode"));
		staffSVO.setSts(JsonUtil.getString(jsonObject,"sts"));
		staffSVO.setStsDate(JsonUtil.getTimestamp(jsonObject,"stsDate"));
		staffSVO.setCompanyCode(JsonUtil.getString(jsonObject,"companyCode"));
		staffSVO.setDeptType(JsonUtil.getString(jsonObject,"deptType"));
		staffSVO.setTelNbr(JsonUtil.getString(jsonObject,"telNbr"));
		return staffSVO;
		}
		return null;
	}	

	private SysConfigCacheMVO loadSysConfigCacheMVO(JSONObject jsonObject){
		if(jsonObject!=null){
			SysConfigCacheMVO sysConfigCacheMVO = new SysConfigCacheMVO();
			sysConfigCacheMVO.setSendLocationInterval(JsonUtil.getInt(jsonObject, "sendLocationInterval"));
			return sysConfigCacheMVO;
		}
		return null;
	}
	
	public void initCache(MosApp mosApp,JSONObject jsonObject) {
		mosApp.setSysUserExtendedMVO(loadSysUserExtendedMVO(JsonUtil.getJSONObject(jsonObject, "suveJsonObject")));
		mosApp.setSysConfigCache(loadSysConfigCacheMVO(JsonUtil.getJSONObject(jsonObject, "sysConfigCache")));
	}


	
	public void clearCache(MosApp mosApp) {
		mosApp.clearAll();
	}
	
	
	/**
	 * add by xyc 20130521 缓存在mosApp中的数据可能由于Android操作系统的某种机制被回收，经常造成系统获取缓存对象为空，因此在获得服务器端传来的数据后，放到本地文件中
	 * @param context
	 * @param resJson 
	 * @throws JSONException 
	 */
	public void initCacheInSharedPreferences(Context context,com.alibaba.fastjson.JSONObject jsonObject) throws JSONException{
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = sharedPreferences.edit();
		
		String cacheStr=jsonObject.toString();
		edit.putString("cache", cacheStr);
		edit.commit();
	}
	
	
	/**
	 * 发行机构
	 * @param context
	 * @param jsonObject
	 * @throws JSONException
	 */
	public void initPublishOrg(Context context,com.alibaba.fastjson.JSONArray jsonArr) throws JSONException{
		SharedPreferences sharedPreferences=context.getSharedPreferences("publishOrgs", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit();
		String orgstr=jsonArr.toJSONString();
		edit.putString("publishOrgs", orgstr);
		edit.commit();
	}
	
	
	/**
	 * 获取上传文件列表
	 * @param context
	 * @return
	 * @throws JSONException
	 */
	public String  getRecorderList(Context context) throws JSONException{
		SharedPreferences sharedPreferences = context.getSharedPreferences("recorderInfos", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString("recorderInfos", "");
		return val;
	}
	
	/**
	 * 更新上传文件列表
	 * @param context
	 * @param jsonArr
	 * @throws JSONException
	 */
	public void updateRecorderList(Context context,com.alibaba.fastjson.JSONArray jsonArr) throws JSONException{
		SharedPreferences sharedPreferences=context.getSharedPreferences("recorderInfos", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit(); 
		edit.putString("recorderInfos", jsonArr.toJSONString());
		edit.commit();
	}
	
	
	/**
	 * 更新位置信息
	 * @param context
	 * @param jsonArr
	 * @throws JSONException
	 */
	public void updateLocation(Context context,String proName) throws JSONException{
		SharedPreferences sharedPreferences=context.getSharedPreferences("location", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit(); 
		edit.putString("location", proName);
		edit.commit();
	}
	
	public String  getLocationInfo(Context context) throws JSONException{
		SharedPreferences sharedPreferences = context.getSharedPreferences("location", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString("location", "");
		return val;
	}
	
	/**
	 * 校验信息
	 * @param context
	 * @param jsonArr
	 * @throws JSONException
	 */
	public void updateValidateInfo(Context context,String proName){
		SharedPreferences sharedPreferences=context.getSharedPreferences("validateInfo", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit(); 
		edit.putString("validateInfo", proName);
		edit.commit();
	}
	
	public String  getValidateInfo(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("validateInfo", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString("validateInfo", "");
		return val;
	}
	
	
	/**
	 * 校验信息
	 * @param context
	 * @param jsonArr
	 * @throws JSONException
	 */
	public void updateFileUploadServer(Context context,String fileUploadPath){
		SharedPreferences sharedPreferences=context.getSharedPreferences("fileUploadPath", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit(); 
		edit.putString("fileUploadPath", fileUploadPath);
		edit.commit();
	}
	
	public String  getUpdateFileUploadServer(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("fileUploadPath", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString("fileUploadPath", "");
		return val;
	}
	
	
	/**
	 * 事件信息
	 * @param context
	 * @param jsonArr
	 * @throws JSONException
	 */
	public void updateEventInfo(Context context,String eventInfo){
		SharedPreferences sharedPreferences=context.getSharedPreferences("eventInfo", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit(); 
		edit.putString("eventInfo", eventInfo);
		edit.commit();
	}
	
	public String  getEventInfo(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("eventInfo", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString("eventInfo", "");
		return val;
	}
	
	
	/**
	 * 电话号码
	 * @param context
	 * @param jsonArr
	 * @throws JSONException
	 */
	public void updatePhoneNo(Context context,String fileUploadPath){
		SharedPreferences sharedPreferences=context.getSharedPreferences("phoneNo", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit(); 
		edit.putString("phoneNo", fileUploadPath);
		edit.commit();
	}
	
	public String  getPhoneNo(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("phoneNo", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString("phoneNo", "");
		return val;
	}
	
	
	
	
	/**
	 * 币种
	 * @param context
	 * @param jsonObject
	 * @throws JSONException
	 */
	public void initMoneyType(Context context,com.alibaba.fastjson.JSONArray jsonArr) throws JSONException{
		SharedPreferences sharedPreferences=context.getSharedPreferences("moneyTypes", context.MODE_WORLD_WRITEABLE);
		Editor edit = sharedPreferences.edit(); 
		edit.putString("moneyTypes", jsonArr.toJSONString());
		edit.commit();
	}
	
	public String getPublishOrgCacheValueInSharedPreferences(Context context,String key){
		SharedPreferences sharedPreferences = context.getSharedPreferences("publishOrgs", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString(key, "");
		return val;
	}
	
	
	
	public String getMoneyTypesInSharedPreferences(Context context,String key){
		SharedPreferences sharedPreferences = context.getSharedPreferences("moneyTypes", context.MODE_WORLD_WRITEABLE);
		String val=sharedPreferences.getString(key, "");
		return val;
	}
	
	
	
	/**
	 * 根据键获取缓存在SharedPreference的值
	 * @param context
	 * @param key
	 */
	public String getCacheValueInSharedPreferences(Context context,String key){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		String val=sharedPreferences.getString(key, "");
		return val;
	}
}
