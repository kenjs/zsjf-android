package com.cattsoft.mos.activity;

import java.util.List;
import java.util.Map;

import android.os.Environment;
/**
 * 拍照功能的公共类
 * @author xueweiwei
 *
 */
public class TakePhotoMain {
	static String sdPath;
	public static int j=0;
	public static List<Map> soAttachList;
	
	public static List<Map> getSoAttachList() {
		return soAttachList;
	}

	public static void setSoAttachList(List<Map> soAttachList) {
		TakePhotoMain.soAttachList = soAttachList;
	}

			//获取SD卡的跟路径
			public static String ReadSDPath(){
				boolean SDExit=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
				String path= Environment.getExternalStorageDirectory().getPath();
				System.out.println("path======="+path);
				if(SDExit){
					System.out.println("加载上了！！！！！！！！！！！！！！！！！！！！！");
					return sdPath=Environment.getExternalStorageDirectory().toString();
				}else{
					System.out.println("没有加载上！！！！！！！！！！！！！！！！！！！！！");
					return "/mnt/sdcard";
				}
			}
}
