package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;

/**
 * 工程建设信息
 * 
 * @author xieyunchao
 * 
 */
public class Gcjgxxtj extends BasicActivity {

	private ListView lv=null;
	private List respdata = new ArrayList();
	private SimpleAdapter simpleAdapter = null;
	String response = "";
	private String flag = ""; 
	private String[] key = { "gcmc", "jgsjStr", "sqr","sqsjStr"};
	com.alibaba.fastjson.JSONObject reqJson =null;
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gcjgxxtj);

		// 设置标题栏
		super.setTitleBarWrap("工程竣工信息",DateUtil.getYesterdayStr(), View.VISIBLE, View.VISIBLE,
				View.INVISIBLE, false);
		
		this.setTitleRightButtonImg(R.drawable.date_choose);

		initData();
		initView();
		registerListener();
		super.showProcessDialog(true);
		requestData();
		ActivityUtil.getInstance().addActivity(this);

	}
	

	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String subUrl = "tm/ZSJFAction.do?method=showGCJGTJReport";
						response = getPostHttpContent("", subUrl,
								reqJson.toJSONString());
//						if (handleError(response) == true)
//							return;
						Message m = new Message();
						m.what = 1;
						handler.sendMessage(m);
					}
				});
		mThread.start();
	}


	private void initData() {
		reqJson = new com.alibaba.fastjson.JSONObject();
		reqJson.put("startDate", "");
		reqJson.put("endDate", "");
	}

	@Override
	protected void initView() {
		lv=(ListView)findViewById(R.id.alist);
	}
	
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				HashMap m=new HashMap();
				intent.setClass(Gcjgxxtj.this,
						Gcjsfilter.class);
				startActivityForResult(intent, 1);
			}
		});
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle b=data.getExtras();
			Map parmMap=(Map)b.getSerializable("parm");
			String startTime=(String)parmMap.get("startTime");
			String endTime=(String)parmMap.get("endTime");
			String dept=(String)parmMap.get("dept");
			reqJson.put("startDate", startTime);
			reqJson.put("endDate", endTime);
			reqJson.put("sqr", dept);
			showProcessDialog(true);
			requestData();
		}
	}
	

	private void redrawUI() {
//		com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject
//				.parseObject(response);
		respdata = com.alibaba.fastjson.JSON.parseArray(
				response,
				java.util.HashMap.class);
		simpleAdapter = new SimpleAdapter(Gcjgxxtj.this, respdata,
				R.layout.list_item_gcjgxx, key, new int[] { R.id.txt_head,
						R.id.txt_jgsjxqbm, R.id.txt_jgsjxqbm,R.id.txt_sqsjsqsj});
		lv.setAdapter(simpleAdapter);
	}

	

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				redrawUI();
			}
			case 3: {

			}
				if (mProgressDialog != null)
					mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			}
		}
	};

	
	

	@Override
	protected void registerListener() {
		
		
	}

}
