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
//区市业务发展日报
public class Qsywfzrb extends BasicActivity {
	private List respdata = new ArrayList();
	private SimpleAdapter simpleAdapter = null;
	private ListView lv = null;
	String response = "";
	private String flag = ""; 
	private String[] key1 = { "cpMc", "qgs", "fgs"};

	private LinearLayout ll1, ll2;
	private DatePickerDialog datePickerDialog;
	private String dateFlag="0";//sdk bug防止重复执行
	
	com.alibaba.fastjson.JSONObject reqJson =null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qsywfzrb);

		// 设置标题栏
		super.setTitleBarWrap("区市业务发展日报",DateUtil.getYesterdayStr(), View.VISIBLE, View.VISIBLE,
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
				redrawUI(flag);
			}
			case 3: {

			}
				if (mProgressDialog != null)
					mProgressDialog.dismiss();// 当接到消息时，关闭进度条
				dateFlag="0";
			}
		}
	};

	private void redrawUI(String flag) {
		com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject
				.parseObject(response);
		respdata = com.alibaba.fastjson.JSON.parseArray(
				json.getJSONArray("list").toJSONString(),
				java.util.HashMap.class);
		simpleAdapter = new SimpleAdapter(Qsywfzrb.this, respdata,
				R.layout.list_item_line3, key1, new int[] { R.id.tv_data1,
						R.id.tv_data2, R.id.tv_data3});
		lv.setAdapter(simpleAdapter);
	}

	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String subUrl = "tm/ZSJFAction.do?method=jtrb4qsjywfzrb";
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


	@Override
	protected void initView() {
		lv = (ListView) findViewById(R.id.alist);
	}

	@Override
	protected void registerListener() {
		

	}

	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(Qsywfzrb.this,
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
				setWrapText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
				showProcessDialog(true);
				requestData();
				dateFlag="1";
			}
			
			}
		};
	
}