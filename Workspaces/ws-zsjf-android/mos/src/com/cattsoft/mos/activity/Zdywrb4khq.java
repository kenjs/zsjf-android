package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.adapter.GridViewAdapter;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.vo.mvo.MenuItemData;

//重点业务日报(按客户群)
public class Zdywrb4khq extends BasicActivity {
	private List respdata = new ArrayList();
	private SimpleAdapter simpleAdapter = null;
	private ListView lv = null;
	String response = "";
	private String flag = "1";
	private String whickReportFlag="";

	private Button btn1, btn2,btn3,btn4,btn5,btn6,btn7;

	private String[] key1 = { "zdywQy", "zdyw2grfz", "zdyw2gylj", "zdyw2gsytqlj",
			"zdyw2gzzs" };
	private String[] key2 = { "zdywQy", "zdyw3grfz", "zdyw3gylj", "zdyw3gsytqlj",
			"zdyw3gzzs" };
	private String[] key3 = { "zdywQy", "zdyw4grfz", "zdyw4gylj", "zdyw4gsytqlj",
	"zdyw4gzzs" };
	private String[] key4 = { "zdywQy", "zdyw2g3grfz", "zdyw2g3gylj", "zdyw2g3gsytqlj",
	"zdyw2g3gzzs" };
	private String[] key5 = { "zdywQy", "zdywKdrqz", "zdywKdylj", "zdywKdsytqlj",
	"zdywKdzzs","zdywKdqnljfz","zdywKdjnljfz" };
	private String[] key6 = { "zdywQy", "zdywKdcjrcj", "zdywKdcjdylj", "zdywKdcjsytqlj",
	"zdywKdcjzzs","zdywKdcjqnljs","zdywKdcjjnljs" };
	
	private String[] key7 = { "zdywQy", "zhwjrfz", "zhwjdylj", "zhwjsytq",
			"zhwjzzs"};

	private long mExitTime = 0l;

	private LinearLayout ll1,ll2,ll3;
	private DatePickerDialog datePickerDialog;
	private String dateFlag="0";//sdk bug防止重复执行
	
	com.alibaba.fastjson.JSONObject reqJson =null;
	private String title="";
	private String url="";
	private String firstPage="";
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zdywrb4khq);
		whickReportFlag=getIntent().getExtras().getString("whickReportFlag");
		firstPage=getIntent().getExtras().getString("firstPage");
		int isShowBackButton=View.VISIBLE;
		if(!StringUtil.isBlank(firstPage)) {
			isShowBackButton=View.GONE;
		}
		// 设置标题栏
		if(StringUtil.isBlank(whickReportFlag)) {
			title="重点业务日报";
			url="tm/ZSJFAction.do?method=hsrb4zdywfzrb";
		}else {
			title="重点业务日报(按客户群)";
			url="tm/ZSJFAction.do?method=zdgz4zdywrb";
		}
		super.setTitleBarWrap(title,DateUtil.getYesterdayStr(), isShowBackButton, View.VISIBLE,
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

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(!StringUtil.isBlank(firstPage)) {
			if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
		        if((System.currentTimeMillis()-mExitTime) > 2000){  
		            Toast.makeText(getApplicationContext(), "再按一次退出！", Toast.LENGTH_SHORT).show();                                
		            mExitTime = System.currentTimeMillis();   
		        } else {
		        	ActivityUtil.exit();
		            mosApp.exit();
		            System.exit(0);
					this.finish();
		        }
		        return true;   
		    }
		}
		
	    return super.onKeyDown(keyCode, event);
	    
	     
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
			simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
					R.layout.list_item_line5, key1, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else if(flag.equals("2")) {
			simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
					R.layout.list_item_line5, key2, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else if(flag.equals("3")) {
			simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
					R.layout.list_item_line5, key3, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else if(flag.equals("4")) {
			simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
					R.layout.list_item_line5, key4, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5 });
		}else if(flag.equals("5")) {
			simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
					R.layout.list_item_line7, key5, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5,R.id.tv_data6,R.id.tv_data7 });
		}else if(flag.equals("6")) {
			simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
					R.layout.list_item_line7, key6, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5, R.id.tv_data6,R.id.tv_data7});
		}else if(flag.equals("7")) {
			simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
					R.layout.list_item_line5, key7, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4,
							R.id.tv_data5});
		}
		
		lv.setAdapter(simpleAdapter);
	}

	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						response = getPostHttpContent("", url,
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
				simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
						R.layout.list_item_line5, key1, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				ll3.setVisibility(View.GONE);
				flag="1";
			} else if (btn == btn2) {
				simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
						R.layout.list_item_line5, key2, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				ll3.setVisibility(View.GONE);
				flag="2";
			} else if (btn == btn3) {
				simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
						R.layout.list_item_line5, key3, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				ll3.setVisibility(View.GONE);
				flag="3";
			} else if (btn == btn4) {
				simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
						R.layout.list_item_line5, key4, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				ll3.setVisibility(View.GONE);
				flag="4";
			} else if (btn == btn5) {
				simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
						R.layout.list_item_line5, key5, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5,R.id.tv_data6,R.id.tv_data7 });
				ll1.setVisibility(View.GONE);
				ll2.setVisibility(View.VISIBLE);
				ll3.setVisibility(View.GONE);
				flag="5";
			} else if (btn == btn6) {
				simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
						R.layout.list_item_line5, key6, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 ,R.id.tv_data6,R.id.tv_data7});
				ll1.setVisibility(View.GONE);
				ll2.setVisibility(View.GONE);
				ll3.setVisibility(View.VISIBLE);
				flag="6";
			}  else if (btn == btn7) {
				simpleAdapter = new SimpleAdapter(Zdywrb4khq.this, respdata,
						R.layout.list_item_line5, key7, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4, R.id.tv_data5 });
				ll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				ll3.setVisibility(View.GONE);
				flag="7";
			} 
			switchCurrent(btn);
			lv.setAdapter(simpleAdapter);
		}

	}

	@Override
	protected void initView() {
		ll1 = (LinearLayout) findViewById(R.id.tab_head_ll_1);
		ll2=(LinearLayout)findViewById(R.id.tab_head_ll_2);
		ll3=(LinearLayout)findViewById(R.id.tab_head_ll_3);
		ll1.setVisibility(View.VISIBLE);
		lv = (ListView) findViewById(R.id.alist);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7=(Button) findViewById(R.id.btn7);
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
		btn6.setOnClickListener(listener);
		btn7.setOnClickListener(listener);
	}

	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(Zdywrb4khq.this,
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
