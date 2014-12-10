package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;

/**
 * 行业部业务发展日报
 * 
 * @author xieyunchao
 * 
 */
public class Hybywfzrb extends BasicActivity {
	private List respdata = new ArrayList();
	private SimpleAdapter simpleAdapter = null;
	private ListView lv = null;
	String response = "";
	private String flag = "1";

	private Button btn1, btn2, btn3, btn4, btn5;

	private String[] key1 = { "hybPq", "rh2gRfz", "rh2gYlj", "rh2gSytqlj",
			"rh2gZzs" };
	private String[] key2 = { "hybPq", "rh3gRfz", "rh3gYlj", "rh3gSytqlj",
			"rh3gZzs" };

	private String[] key3 = { "hybPq", "rh4gRfz", "rh4gYlj", "rh4gSytqlj",
			"rh4gZzs" };

	private String[] key4 = { "hybPq", "wyRfz", "wyYlj", "wyYljc" };

	private String[] key5 = { "hybPq", "gxRfz", "gxYlj", "gxYljc" };

	private LinearLayout ll1, ll2;
	private DatePickerDialog datePickerDialog;
	private String dateFlag="0";//sdk bug防止重复执行
	
	com.alibaba.fastjson.JSONObject reqJson =null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hybywfzrb);

		// 设置标题栏
		super.setTitleBarWrap("行业部业务发展日报",DateUtil.getYesterdayStr(), View.VISIBLE, View.VISIBLE,
				View.INVISIBLE, false);
		
		this.setTitleRightButtonImg(R.drawable.date_choose);
		initData();
		initView();
		registerListener();
		initToolbarMenu();
		super.showProcessDialog(true);
		requestData();
		ActivityUtil.getInstance().addActivity(this);
	}

	private void initData() {
		initCalendar();
		reqJson = new com.alibaba.fastjson.JSONObject();
		reqJson.put("date", DateUtil.getYesterdayStr());
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
				dateFlag="0";
			}
		}
	};

	private void redrawUI() {
		com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject
				.parseObject(response);
		respdata = com.alibaba.fastjson.JSON.parseArray(
				json.getJSONArray("list").toJSONString(),
				java.util.HashMap.class);
		if(flag.equals("1")) {
			simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
					R.layout.list_item_line5, key1, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else if(flag.equals("2")) {
			simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
					R.layout.list_item_line5, key2, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else if(flag.equals("3")) {
			simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
					R.layout.list_item_line5, key3, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else if(flag.equals("4")) {
			simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
					R.layout.list_item_line4, key4, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else {
			simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
					R.layout.list_item_line5, key5, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}
		
		lv.setAdapter(simpleAdapter);
	}

	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String subUrl = "tm/ZSJFAction.do?method=jtrb4hybywfzrb";
						response = getPostHttpContent("", subUrl,
								reqJson.toJSONString());
						if (handleError(response) == true)
							return;
						Message m = new Message();
						m.what = 1;
						handler.sendMessage(m);
					}
				});
		mThread.start();
	}

	private class BtnClickListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			Button btn = (Button) arg0;
			if (btn == btn1) {
				simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
						R.layout.list_item_line5, key1, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				flag="1";
			} else if (btn == btn2) {
				simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
						R.layout.list_item_line5, key2, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				flag="2";
			} else if (btn == btn3) {
				simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
						R.layout.list_item_line5, key3, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				flag="3";
			} else if (btn == btn4) {
				simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
						R.layout.list_item_line4, key4, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4});
				ll2.setVisibility(View.VISIBLE);
				ll1.setVisibility(View.GONE);
				flag="4";
			} else {
				simpleAdapter = new SimpleAdapter(Hybywfzrb.this, respdata,
						R.layout.list_item_line4, key5, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4});
				ll2.setVisibility(View.VISIBLE);
				ll1.setVisibility(View.GONE);
				flag="5";
			}
			switchCurrent(btn);
			lv.setAdapter(simpleAdapter);
		}

	}

	@Override
	protected void initView() {
		ll1 = (LinearLayout) findViewById(R.id.tab_head_ll_1);
		ll2 = (LinearLayout) findViewById(R.id.tab_head_ll_2);
		ll1.setVisibility(View.VISIBLE);
		ll2.setVisibility(View.GONE);
		lv = (ListView) findViewById(R.id.alist);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn1.setTextColor(0xFFF38121);
	}

	@Override
	protected void registerListener() {
		BtnClickListener listener = new BtnClickListener();
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		btn4.setOnClickListener(listener);
		btn5.setOnClickListener(listener);
	}

	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(Hybywfzrb.this,
						myDateSetListener, DateUtil.getYesterdayYear(), DateUtil.getYesterdayMonth()-1, DateUtil.getYesterdayDay());
						datePickerDialog.show();
			}
		});
	}
	
	private OnDateSetListener myDateSetListener=new OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year,
		int monthOfYear, int dayOfMonth) {
			if(dateFlag.equals("0")) {
				reqJson.put("date", year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
				showProcessDialog(true);
				requestData();
				dateFlag="1";
			}
			
			}
		};
	
}
