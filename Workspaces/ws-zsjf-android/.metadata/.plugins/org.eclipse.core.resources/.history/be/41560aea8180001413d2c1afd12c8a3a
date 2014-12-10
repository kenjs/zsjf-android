package com.cattsoft.mos.activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cattsoft.mos.R;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.JsonUtil;
import com.cattsoft.mos.util.StringUtil;

public class LicaiDetailActivity extends BasicActivity{
	
	private String detailInfoRes;
	
	String proCode="";
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.licai_chanpin_detail_main);

		// 设置标题栏
		super.setTitleBar("产品详情",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		requestDetailInfo();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	
	private void requestDetailInfo() {
		super.showProcessDialog(true);
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String response=""; 
						try {
							JSONObject requestJsonObject = new JSONObject();
							requestJsonObject.put("proCode", proCode);//头条
							//获取响应的结果信息
							String url=Constant.prodDetailInfo;
							detailInfoRes = getPostHttpContent("",url, requestJsonObject.toString());
							if("1".equals(JsonUtil.getResponseHead(detailInfoRes).getString("flag"))){
								LicaiDetailActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS((JsonUtil.getResponseHead(detailInfoRes)).getString("desc")));
								return ;
							}
							
							
							if (StringUtil.isBlank(detailInfoRes)) {
								response = StringUtil.getAppException4MOS("未获得服务器响应结果！");
								LicaiDetailActivity.this.sendExceptionMsg(response);
								return;
							}
							com.alibaba.fastjson.JSONObject contentJson=JsonUtil.getResponseContentJSON(detailInfoRes);
							
							Message m=new Message();
							m.what=1;
							m.obj=contentJson;
							handlermain.sendMessage(m);
							
						} catch (JSONException e) {
							e.printStackTrace();
							String err = StringUtil.getAppException4MOS("解析JSON出错！");
							LicaiDetailActivity.this.sendExceptionMsg(err);
							return;
						}
					
					}
				});
		mThread.start();
	
	}
	
	
	private Handler handlermain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				drawViewGroups((com.alibaba.fastjson.JSONObject)msg.obj);
			}
			case 2:{
			}
			}
			if(mProgressDialog!=null)mProgressDialog.dismiss();
		}
	};
	
	private void initData() {
		
		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		HashMap hm=(HashMap)b.getSerializable("valueMap");
		proCode=(String)hm.get("pro_code");
		
		
	}
	
	/**
	 * 
	 * @return
	 */
	private void drawViewGroups(com.alibaba.fastjson.JSONObject contentJson) {
		Set keyset=contentJson.keySet();
		Iterator it=keyset.iterator();
		
		LinearLayout v=(LinearLayout)findViewById(R.id.ll_detail_main);
		
		while(it.hasNext()) {
			String key=(String)it.next();
			com.alibaba.fastjson.JSONArray jarr=(com.alibaba.fastjson.JSONArray)contentJson.get(key);
			View group=matchView(jarr,key);
			v.addView(group);
		}
	}
	
	private View matchView(com.alibaba.fastjson.JSONArray jarr,String akey) {
		int lbl_elemts[]= {R.id.label_0,R.id.label_1,R.id.label_2,R.id.label_3,R.id.label_4,R.id.label_5,R.id.label_6,R.id.label_7,
				R.id.label_8,R.id.label_9,R.id.label_10,R.id.label_11,R.id.label_12,R.id.label_13,R.id.label_14,R.id.label_15};
		
		int value_elemts[]= {R.id.value_0,R.id.value_1,R.id.value_2,R.id.value_3,R.id.value_4,R.id.value_5,R.id.value_6,R.id.value_7,
				R.id.value_8,R.id.value_9,R.id.value_10,R.id.value_11,R.id.value_12,R.id.value_13,R.id.value_14,R.id.value_15};
		double as=(double)jarr.size();
		double asize=as/2;
		double ad=Math.ceil(asize);
		int lines=(int)ad;
		View v=null;
		if(lines<=2) {
			v=View.inflate(this, R.layout.licai_chanpin_detail_line2, null);
		}else if(lines==3){
			v=View.inflate(this, R.layout.licai_chanpin_detail_line3, null);
		}else if(lines==8) {
			v=View.inflate(this, R.layout.licai_chanpin_detail_line8, null);
		}
		
		TextView tvProName=(TextView)v.findViewById(R.id.pro_name);
		tvProName.setText(akey);
		int n=0;
		for(int i=0;i<jarr.size();i++) {
			com.alibaba.fastjson.JSONObject obj=(com.alibaba.fastjson.JSONObject)jarr.get(i);
			Iterator it=obj.keySet().iterator();
			while(it.hasNext()) {
				String key=(String)it.next();
				String value=obj.getString(key);
				System.out.println("key======"+key);
				System.out.println("value======"+value);
				TextView tvLabel=(TextView)v.findViewById(lbl_elemts[n]);
				TextView tvValue=(TextView)v.findViewById(value_elemts[n]);
				tvLabel.setText(key);
				tvValue.setText(value);
				System.out.println("nnnnnnnn="+n);
				n++;
			}
			
		}
		
		return v;
		
		
	}
	

	@Override
	protected void initView() {
		
	}

	@Override
	protected void registerListener() {
		
	}

}
