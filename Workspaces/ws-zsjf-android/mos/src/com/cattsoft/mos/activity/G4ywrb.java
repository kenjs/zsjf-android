package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.ActionSheet;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.dialog.ActionSheet.OnActionSheetSelected;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;

//4G业务日报
public class G4ywrb extends BasicActivity implements OnActionSheetSelected, OnCancelListener{
	private List respdata = new ArrayList();
	private SimpleAdapter simpleAdapter = null;
	private ListView lv = null;
	String response = "";
	private String flag = ""; 
	private String[] key1 = { "tcmc", "rfz", "dylj","sytqlj","zzs"};

	private LinearLayout ll1, ll2;
	private DatePickerDialog datePickerDialog;
	private String dateFlag="0";//sdk bug防止重复执行
	private String showwgFlag="any";
	private Map khqMap=new HashMap();
	private Map hylxMap=new HashMap();
	
	private List khqdata=new ArrayList();
	private List hylxdata=new ArrayList();
	
	com.alibaba.fastjson.JSONObject reqJson =null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g4ywrb);

		// 设置标题栏
		super.setTitleBarWrap("4G业务日报",DateUtil.getYesterdayStr(), View.VISIBLE, View.VISIBLE,
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
		reqJson.put("date", DateUtil.getYesterdayStr());
		reqJson.put("showwgFlag", showwgFlag);
		reqJson.put("khq", "全部");
		reqJson.put("hylx", "全部");
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
				showwgFlag="";
			}
		}
	};

	private void redrawUI(String flag) {
		com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject
				.parseObject(response);
		respdata = com.alibaba.fastjson.JSON.parseArray(
				json.getJSONArray("list").toJSONString(),
				java.util.HashMap.class);
		if(khqdata.size()==0) {
			List tempList=com.alibaba.fastjson.JSON.parseArray(
					json.getJSONArray("khqList").toJSONString(),
					java.util.HashMap.class);
			for(int i=0;i<tempList.size();i++) {
				Map m=(java.util.HashMap)tempList.get(i);
				String diqu=(String)m.get("diqu");
				Map newhm=new HashMap();
				newhm.put("name", diqu);
				newhm.put("value", diqu);
				khqdata.add(newhm);
			}
		}
		
		if(hylxdata.size()==0) {
			List tempList=com.alibaba.fastjson.JSON.parseArray(
					json.getJSONArray("hylxlist").toJSONString(),
					java.util.HashMap.class);
			for(int i=0;i<tempList.size();i++) {
				Map m=(java.util.HashMap)tempList.get(i);
				String diqu=(String)m.get("diqu");
				Map newhm=new HashMap();
				newhm.put("name", diqu);
				newhm.put("value", diqu);
				hylxdata.add(newhm);
			}
		}
		
		
		simpleAdapter = new SimpleAdapter(G4ywrb.this, respdata,
				R.layout.list_item_line5, key1, new int[] { R.id.tv_data1,
						R.id.tv_data2, R.id.tv_data3,R.id.tv_data4,R.id.tv_data5});
		lv.setAdapter(simpleAdapter);
	}

	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						reqJson.put("showwgFlag", showwgFlag);
						String subUrl = "tm/ZSJFAction.do?method=g4rb44grb";
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
				ActionSheet.showSheet(G4ywrb.this, G4ywrb.this, G4ywrb.this,"选择客户群","选择合约类型","选择日期");
//				datePickerDialog=new DatePickerDialog(G4ywrb.this,
//						myDateSetListener, DateUtil.getYesterdayYear(), DateUtil.getYesterdayMonth()-1, DateUtil.getYesterdayDay());
//						datePickerDialog.show();
			}
		});
	}
	
	private OnDateSetListener myDateSetListener=new OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year,
		int monthOfYear, int dayOfMonth) {
			if(dateFlag.equals("0")) {
				int amonth=monthOfYear+1;
				String strMonth=amonth>=10?amonth+"":"0"+amonth;
				String strDayofMonth=dayOfMonth>=10?dayOfMonth+"":"0"+dayOfMonth;
				reqJson.put("date", year+"-"+strMonth+"-"+strDayofMonth);
				setWrapText(year+"-"+strMonth+"-"+strDayofMonth);
				showProcessDialog(true);
				requestData();
				dateFlag="1";
			}
			}
		};

	@Override
	public void onCancel(DialogInterface arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(int whichButton) {
		// TODO Auto-generated method stub
		switch (whichButton) {
			case 0:
				Dialog dialog = new SelectDialog(G4ywrb.this, R.style.selectDialog,"客户群",khqdata,khqMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
//						defect_zerenren1_txt.setText((String)m.get("name"));
						khqMap=m;
						reqJson.put("khq", ((String)m.get("name")));
						showProcessDialog(true);
						requestData();
					}
				});
				 dialog.show();
				break;
			case 1:
				Dialog dialog1 = new SelectDialog(G4ywrb.this, R.style.selectDialog,"合约类型",hylxdata,hylxMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
//						defect_zerenren1_txt.setText((String)m.get("name"));
						hylxMap=m;
						reqJson.put("hylx", ((String)m.get("name")));
						showProcessDialog(true);
						requestData();
					}
				});
				 dialog1.show();
				break;
			case 2:
				datePickerDialog=new DatePickerDialog(G4ywrb.this,
						myDateSetListener, DateUtil.getYesterdayYear(), DateUtil.getYesterdayMonth()-1, DateUtil.getYesterdayDay());
						datePickerDialog.show();
				break;

			default:
				break;
		}		
	}
	
}