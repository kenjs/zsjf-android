package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.ActionSheet;
import com.cattsoft.mos.dialog.ActionSheet.OnActionSheetSelected;
import com.cattsoft.mos.dialog.CustomerDatePickerDialog;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;
import com.cattsoft.mos.util.ViewUtil;

//3g业务月报
public class G3ywyb extends BasicActivity implements OnActionSheetSelected, OnCancelListener ,OnDateSetListener{
	private List respdata = new ArrayList();
	private List areadata=new ArrayList();
	
	private SimpleAdapter simpleAdapter = null;
	private ListView lv = null;
	String response = "";
	private String flag = "1";

	private Button btn1, btn2,btn3;

	private String[] key1 = { "yw", "dyfzBy", "dyfzSy", "dyfzHbzzl" };
	private String[] key2 = { "yw", "dyczsrBy", "dyczsrSy", "dyczsrHbzzl"};
	private String[] key3 = { "yw", "czyhsBy", "czyhsSy","czyhsHbzzl" };
	
	private LinearLayout ll1;
 
	private DatePickerDialog datePickerDialog;
	private String dateFlag="0";//sdk bug防止重复执行
	private String showwgFlag="any";
	private Map areaMap=new HashMap();
	
	com.alibaba.fastjson.JSONObject reqJson =null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g3ywyb);

		// 设置标题栏
		super.setTitleBarWrap("3G业务月报",DateUtil.getLastMonth(new java.util.Date()), View.VISIBLE, View.VISIBLE,
				View.INVISIBLE, false);
		
		this.setTitleRightButtonImg(R.drawable.toolbar_filter);
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
		String lastMonth=DateUtil.getLastMonth(new java.util.Date());
		reqJson.put("date", lastMonth.substring(0, 4)+"-"+lastMonth.substring(4, 6)+"-"+"01");
		reqJson.put("cp", "全部");
		reqJson.put("showwgFlag", showwgFlag);
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
				showwgFlag="";
			}
		}
	};

	private void redrawUI() {
		com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject
				.parseObject(response);
		respdata = com.alibaba.fastjson.JSON.parseArray(
				json.getJSONArray("list").toJSONString(),
				java.util.HashMap.class);

		if(areadata.size()==0) {
			List tempList=com.alibaba.fastjson.JSON.parseArray(
					json.getJSONArray("wglist").toJSONString(),
					java.util.HashMap.class);
			for(int i=0;i<tempList.size();i++) {
				Map m=(java.util.HashMap)tempList.get(i);
				String diqu=(String)m.get("diqu");
				Map newhm=new HashMap();
				newhm.put("name", diqu);
				newhm.put("value", diqu);
				areadata.add(newhm);
			}
		}
		
		if(flag.equals("1")) {
			simpleAdapter = new SimpleAdapter(G3ywyb.this, respdata,
					R.layout.list_item_line7, key1, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4});
		}else if(flag.equals("2")){
			simpleAdapter = new SimpleAdapter(G3ywyb.this, respdata,
					R.layout.list_item_line5, key2, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3, R.id.tv_data4 });
		}else if(flag.equals("3")) {
			simpleAdapter = new SimpleAdapter(G3ywyb.this, respdata,
					R.layout.list_item_line3, key3, new int[] { R.id.tv_data1,
							R.id.tv_data2, R.id.tv_data3 ,R.id.tv_data4});
		}
		
		lv.setAdapter(simpleAdapter);
	}

	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String subUrl = "tm/ZSJFAction.do?method=hsyb43gywyb";
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
				simpleAdapter = new SimpleAdapter(G3ywyb.this, respdata,
						R.layout.list_item_line4, key1, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4});
				flag="1";
			} else if (btn == btn2) {
				simpleAdapter = new SimpleAdapter(G3ywyb.this, respdata,
						R.layout.list_item_line4, key2, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3,
								R.id.tv_data4});
		 
				flag="2";
			} else if (btn == btn3) {
				simpleAdapter = new SimpleAdapter(G3ywyb.this, respdata,
						R.layout.list_item_line3, key3, new int[] {
								R.id.tv_data1, R.id.tv_data2, R.id.tv_data3, R.id.tv_data4});
		 
				flag="3";
			} 
			switchCurrent(btn);
			lv.setAdapter(simpleAdapter);
		}

	}

	@Override
	protected void initView() {
		ll1 = (LinearLayout) findViewById(R.id.tab_head_ll_1);
		ll1.setVisibility(View.VISIBLE);
		lv = (ListView) findViewById(R.id.alist);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		
		btn1.setTextColor(0xFFF38121);
	}

	@Override
	protected void registerListener() {
		BtnClickListener listener = new BtnClickListener();
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
	}

	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ActionSheet.showSheet(G3ywyb.this, G3ywyb.this, G3ywyb.this,"","");
			}
		});
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		
	}

	@Override
	public void onClick(int whichButton) {

		// TODO Auto-generated method stub
		switch (whichButton) {
			case 0:
				Dialog dialog = new SelectDialog(G3ywyb.this, R.style.selectDialog,"选择产品",areadata,areaMap,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
//						defect_zerenren1_txt.setText((String)m.get("name"));
						areaMap=m;
						reqJson.put("showwgFlag", showwgFlag);
						reqJson.put("diqu", ((String)m.get("name")));
						showProcessDialog(true);
						requestData();
					}
				});
				 dialog.show();
				break;
			case 1:
//				datePickerDialog=new DatePickerDialog(Srhbtb.this,
//						myDateSetListener, DateUtil.getYesterdayYear(), DateUtil.getYesterdayMonth()-1, DateUtil.getYesterdayDay());
//						datePickerDialog.show(); 
				String lastMonth=DateUtil.getLastMonth(new java.util.Date());
				CustomerDatePickerDialog mDialog = new CustomerDatePickerDialog(this, this,
				    Integer.parseInt(lastMonth.substring(0, 4)), Integer.parseInt(lastMonth.substring(4, 6))-1,  
				    1);  
				mDialog.setTitle(lastMonth.substring(0, 4)+"年"+lastMonth.substring(4,6)+"月");
				mDialog.show();  
				  
				DatePicker dp = ViewUtil.findDatePicker((ViewGroup) mDialog.getWindow().getDecorView());  
				if (dp != null) {  
				    ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);  
				}   
				break;

			default:
				break;
		}

	
	}

	@Override
	public void onDateSet(DatePicker arg0, int year, int month, int day) {
		if(dateFlag.equals("0")) {
			reqJson.put("date", year+"-"+(month+1)+"-"+day);
			showProcessDialog(true);
			reqJson.put("showwgFlag", showwgFlag);
			requestData();
			setWrapText( year+""+(month+1>10?month+1+"":"0"+(month+1)));
			dateFlag="1";
		}
		
	}
	
	
	
}
