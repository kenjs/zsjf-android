package com.cattsoft.mos.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.cattsoft.mos.dataprocess.CacheProcess;

public class DeviceUtil {
	
	static TelephonyManager tm=null;
	
	public static TelephonyManager getTelephonyManager(Context context ){
		if(tm==null){
			tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		}
		return tm;
	}
	
	
	public static Map getDeviceParameter(Context context ){
		tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		Map m=new HashMap();
		String deviceId=tm.getDeviceId();
		String deviceSoftwareVersion=tm.getDeviceSoftwareVersion();
		int deviceType=tm.getPhoneType();
		
		
		m.put("deviceId", deviceId);
		m.put("deviceSoftwareVersion", deviceSoftwareVersion);
		m.put("deviceType",String.valueOf(deviceType));
		String phoneNo=tm.getLine1Number();
		if(!StringUtil.isBlank(phoneNo)) {
			if(phoneNo.startsWith("+86")) {
				phoneNo=phoneNo.substring(3);
			}
		}else {
			phoneNo=new CacheProcess().getPhoneNo(context);
		}
		m.put("phoneNo", phoneNo);
		m.put("os", "android");
		return m;
	}


}
