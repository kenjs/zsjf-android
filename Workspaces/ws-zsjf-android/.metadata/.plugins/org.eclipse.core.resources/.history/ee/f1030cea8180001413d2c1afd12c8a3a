package com.cattsoft.mos.activity;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;

/**
 *报表说明
 * @author xieyunchao
 *
 */
public class ReportDesc extends BasicActivity{
	private String response;
	private TextView tvdesc;
	private com.alibaba.fastjson.JSONObject reqJson;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportdesc);

		// 设置标题栏
		super.setTitleBar("报表说明",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		super.showProcessDialog(true);
		requestData();
		ActivityUtil.getInstance().addActivity(this);
	}
	
	private void initData() {
		 reqJson=new com.alibaba.fastjson.JSONObject();
	}
	
	@Override
	protected void initView() {
		tvdesc=(TextView)findViewById(R.id.tv_desc);
	}

	@Override
	protected void registerListener() {
	}
	
	private void forward() {
		
	}
	
	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String subUrl = "tm/ZSJFAction.do?method=getReportIntrouctionList";
						response = getPostHttpContent("", subUrl,
								reqJson.toJSONString());
						if(StringUtil.isExcetionInfo(response)) {
							sendExceptionMsg(response);
							return;
						}
						Message m = new Message();
						m.what = 1;
						handler.sendMessage(m);
					}
				});
		mThread.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				com.alibaba.fastjson.JSONArray jarr=com.alibaba.fastjson.JSONArray.parseArray(response);
				List alist=com.alibaba.fastjson.JSON.parseArray(jarr.toJSONString(), HashMap.class);
				tvdesc.setText((String)((java.util.HashMap)alist.get(0)).get("introduction"));
			}
			case 3: {

			}
				if (mProgressDialog != null)
					mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			}
		}
	};



}
