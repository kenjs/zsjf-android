package com.cattsoft.mos.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.StringUtil;

public class WelcomeActivity extends BasicActivity {

	public static final String LOGIN = "com.cattsoft.mos.LOGIN";

	private String isFirstRun = "YES";
	private SharedPreferences sharedPreferences;
	
	private String location="";//是否已经初始化位置信息，初始化一次后就放到文件中，以后在文件中读取，以减少响应时间
	
	
	
	Map hm=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);
//		loadGuideInfo();
//		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//		initData();

		Start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void initData() {
		hm = new HashMap();
		CacheProcess cp = new CacheProcess();
		String publishOrgs = cp.getPublishOrgCacheValueInSharedPreferences(
				this, "publishOrgs");
		if (!StringUtil.isBlank(publishOrgs)) {
			com.alibaba.fastjson.JSONArray jarr = com.alibaba.fastjson.JSONArray
					.parseArray(publishOrgs);
			hm.put("proIssuerCount", jarr.size());
		} else {
			hm.put("proIssuerCount", 0);
		}
		
		String fileUploadUrl=new CacheProcess().getUpdateFileUploadServer(this);
		if(StringUtil.isBlank(fileUploadUrl)) {
			new CacheProcess().updateFileUploadServer(this, Constant.file_upload_URL);
		}
		
		try {
			location=new CacheProcess().getLocationInfo(this);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(StringUtil.isBlank(location)) {
			initLocation();
		}
		
		
	}

 

	public void Start() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1500);
					Message m=new Message();
					m.what=1;
					handlermain.sendMessage(m);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	
	private Handler handlermain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				startActivity(new Intent(WelcomeActivity.this,
						LoginActivity.class));
				finish();
			}
			}
			
		}
	};
	
	
	private void initLocation() {
		LocationClient mLocationClient = new LocationClient(this);
		MyLocationListener mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		
		
		try {
			LocationClientOption option = new LocationClientOption();
			option.setLocationMode(LocationMode.Battery_Saving);
			option.setCoorType("gcj02");
			option.setScanSpan(0);
			option.setNeedDeviceDirect(false);
			option.setIsNeedAddress(true);
			mLocationClient.setLocOption(option);
			
			mLocationClient.start();
			mLocationClient.requestLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			StringBuffer sb = new StringBuffer(256);
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				//运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
			Log.i("BaiduLocationApiDem", sb.toString());
			try {
				new CacheProcess().updateLocation(WelcomeActivity.this, location.getProvince());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			
		}
	}
	
	

	
	private void loadGuideInfo() {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		isFirstRun = sharedPreferences.getString("isFirstLogin", "");
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void registerListener() {

	}
}
