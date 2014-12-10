package com.cattsoft.mos.dataprocess;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.cattsoft.mos.connect.EasySSLSocketFactory;
import com.cattsoft.mos.vo.mvo.SysConfigCacheMVO;
import com.cattsoft.mos.vo.mvo.SysUserExtendedMVO;

/**
 * Mos的缓存类，用于存放全局变量
 * 
 * @author maxun
 * 
 */
public class MosApp extends Application {
	private SysUserExtendedMVO sysUserExtendedMVO = new SysUserExtendedMVO();
	/**
	 * 使用缓存来保存工单处理界面的工单列表 在进入主界面的时候，会查询工单列表，以显示工单处理图标右上方的tips中的待处理工单数量。
	 * 为了减少查询次数，在主界面进行查询时，得到工单列表。 在跳转到工单处理界面时，进行判断，如果缓存为空，则将主界面查询到的工单列表存入缓存，
	 * 否则，从缓存中读取工单列表，并显示。
	 */
	private ArrayList<HashMap<String, Object>> woList = new ArrayList<HashMap<String, Object>>();
	//为工单领取使用
	private ArrayList<HashMap<String, Object>> woList1 = new ArrayList<HashMap<String, Object>>();
	private boolean isThreePage = false;//用于判断在工单领取中按了返回键
	
	/**
	 * 保存工单处理中，查询到的工单总数， 主要用于判断工单列表是否显示完，以决定是否显示更多按钮。 在每次查询时，需要更改此数值
	 */
	private int count;
	//为工单领取使用
	private int count1;

	private int phoneFlag = 0; // 用于区分从哪里进行呼叫操作。目前系统中在工单列表中和工单详情中都有呼叫操作，可以根据此标志位进行区分，从而做不同的处理。0：从详情界面
	/**
	 * 退出状态，用于整个程序的退出
	 */
	private boolean isExit = false;
	
	private boolean isReLogin = false;

	private HttpClient httpClient;

	private Intent locationService = new Intent("com.cattsoft.mos.LOCATION_SERVICE");
	
	private Intent notificationService = new Intent("com.cattsoft.mos.client.NOTIFICATION_SERVICE");
	
	private Intent messageService = new Intent("com.cattsoft.mos.service.MessageService");

	/**百度MapAPI的管理类*/
	/**
	 * 服务端SysConfig缓存的MOS数据
	 */
	private SysConfigCacheMVO sysConfigCache;
	
	private boolean isCancleUpdate = false;//是否取消更新
	
	private static MosApp instance;
	
	
 
	
	public boolean isCancleUpdate() {
		return isCancleUpdate;
	}
	
	public static MosApp getInstance() {
		return instance;
	}


	public void setCancleUpdate(boolean isCancleUpdate) {
		this.isCancleUpdate = isCancleUpdate;
	}

	@Override 
	public void onCreate() {
		super.onCreate(); 
		 instance = this;
		httpClient = createHttpClient();
	//	initBMapManager();
//		startService(locationService);
		startMessageService();
	}

	// 创建HttpClient对象
	private HttpClient createHttpClient() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 500000);
		HttpConnectionParams.setSoTimeout(params, 1800000);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
//		schReg.register(new Scheme("https",
//				SSLSocketFactory.getSocketFactory(), 443));
		schReg.register(new Scheme("https",  
                new EasySSLSocketFactory(), 443));  
		//ClientConnectionManager connManager = new ThreadSafeClientConnManager(params, schReg); 

		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
				params, schReg);
		

		return new DefaultHttpClient(conMgr, params);
	}

	/**
	 * 外部接口获得HttpClient的方法
	 * 
	 * @return
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	// 关闭HttpClent
	public void shutdownHttpClient() {
		if (httpClient != null && httpClient.getConnectionManager() != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public SysUserExtendedMVO getSysUserExtendedMVO() {
		return sysUserExtendedMVO;
	}

	public void setSysUserExtendedMVO(SysUserExtendedMVO sysUserExtendedMVO) {
		this.sysUserExtendedMVO = sysUserExtendedMVO;
	}

	public ArrayList<HashMap<String, Object>> getWoList() {
		return woList;
	}

	public void setWoList(ArrayList<HashMap<String, Object>> woList) {
		this.woList = woList;
	}

	public ArrayList<HashMap<String, Object>> getWoList1() {
		return woList1;
	}

	public void setWoList1(ArrayList<HashMap<String, Object>> woList) {
		this.woList1 = woList;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count) {
		this.count1 = count;
	}
	
	public int getPhoneFlag() {
		return phoneFlag;
	}

	public void setPhoneFlag(int phoneFlag) {
		this.phoneFlag = phoneFlag;
	}

	public boolean isThreePage() {
		return isThreePage;
	}

	public void setThreePage(boolean isThreePage) {
		this.isThreePage = isThreePage;
	}
	
	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}

	public boolean isReLogin() {
		return isReLogin;
	}

	public void setReLogin(boolean isReLogin) {
		this.isReLogin = isReLogin;
	}

	public SysConfigCacheMVO getSysConfigCache() {
		return sysConfigCache;
	}

	public void setSysConfigCache(SysConfigCacheMVO sysConfigCache) {
		this.sysConfigCache = sysConfigCache;
	}

	/**
	 * 清空缓存
	 */
	public void clearAll() {
		this.sysUserExtendedMVO = null;
		this.woList = null;
		this.count = 0;
		this.phoneFlag = 0;
		this.isExit = false;
		this.sysConfigCache = null;
		this.isCancleUpdate = false;
		clearPushInfo();
//		if (mBMapMan != null) {
//			mBMapMan.destroy();
//			mBMapMan = null;
//		}
	}

	/**
	 * 退出程序
	 */
	public void exit() {
		this.isExit = true;
//		stopAllService();
		stopMessageService();
		stopNotificationService();
		clearAll();
		System.gc();
	}

	/**
	 * 停止所有服务
	 */
	public void stopAllService() {
		stopService(locationService);
	}
	
	
	public void stopNotificationService(){
		stopService(notificationService);
	}

	public boolean isMissing() {
		if (this.sysUserExtendedMVO == null) {
			return true;
		}
		return false;
	}
	
	public void stopMessageService() {
		stopService(messageService);
	}
	
	public void startMessageService() {
		startService(messageService);
	}
 
	
	private void clearPushInfo() {
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = sharedPreferences.edit();
		edit.putString("sysUserId", "");
		//edit.putString("password", "");
		edit.commit();
	}
}
