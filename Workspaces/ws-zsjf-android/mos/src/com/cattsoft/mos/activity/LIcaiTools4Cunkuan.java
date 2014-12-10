package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.jsq.ZCZQ;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

public class LIcaiTools4Cunkuan extends BasicActivity{
	
	private List cunKuanTypeList=null;
	
	private Map cunkuanMap=null;
	
	private SelectLabelText sltCunkuanType=null;
	private TextView tvCunkuanType=null;
	
	private SelectLabelText sltBizhong;
	private TextView tvBizhong=null;
	private EditLabelText edlcsckje;
	private EditText etcsckje;
	private EditLabelText edlnlv=null;
	private EditText etnlv=null;
	private SelectLabelText sltcscrrq;
	private EditText etcscrrq;
	private SelectLabelText slttqrq;
	private EditText ettqrq;
	private EditLabelText eltsdlxje;
	private EditText etsdlxje;
	private EditLabelText eltlxsje;
	private EditText etlxsje;
	private EditLabelText eltbxhj;
	private EditText etbxhj;
	
	private String flagCscrrq="";
	
	private Button btnJisuan=null;
	
	
	String hqnlv=null;
	
	
	private List moneyTypeList=null;
	private  Map bizhongMap=null;
	
	private DatePickerDialog datePickerDialog=null;
	
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.licai_tools_cunkuan);

		// 设置标题栏
		super.setTitleBar("存款计算器",View.VISIBLE,View.GONE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);
	}

	
	private void initData() {
		super.initCalendar();
		Map m1=new HashMap();
		m1.put("name", "活期");
		m1.put("value", "hq");
		
		Map m2=new HashMap();
		m2.put("name", "整存整取");
		m2.put("value", "zczq");
		
		Map m3=new HashMap();
		m3.put("name", "零存整取");
		m3.put("value", "lczq");
		
		Map m4=new HashMap();
		m4.put("name", "存本取息");
		m4.put("value", "cbqx");
		
		Map m5=new HashMap();
		m5.put("name", "整存零取");
		m5.put("value", "zclq");
		
		Map m6=new HashMap();
		m6.put("name", "定活两便");
		m6.put("value", "dhlb");
		
		Map m7=new HashMap();
		m7.put("name", "通知存款");
		m7.put("value", "tzck");
		
		Map m8=new HashMap();
		m8.put("name", "教育储蓄");
		m8.put("value", "jycx");
		
		cunKuanTypeList=new ArrayList();
		cunKuanTypeList.add(m1);
		cunKuanTypeList.add(m2);
		cunKuanTypeList.add(m3);
		cunKuanTypeList.add(m4);
		cunKuanTypeList.add(m5);
		cunKuanTypeList.add(m6);
		cunKuanTypeList.add(m7);
		cunKuanTypeList.add(m8);
		
		
		cunkuanMap=new HashMap();
		cunkuanMap.put("name", "活期");
		cunkuanMap.put("value", "hq");
		
		//币种
		String moneyTypes=new CacheProcess().getMoneyTypesInSharedPreferences(this, "moneyTypes");
		List tempMoneyTypeList=com.alibaba.fastjson.JSONArray.parseArray(moneyTypes,HashMap.class);
		
		moneyTypeList=new ArrayList();
		for(int i=0;i<tempMoneyTypeList.size();i++) {
			Map m=(HashMap)tempMoneyTypeList.get(i);
			Map newm=new HashMap();
			newm.put("name", (String)m.get("value"));
			newm.put("value", (String)m.get("key"));
			moneyTypeList.add(newm);
		}
		
		
		
	}
	
	
	@Override
	protected void initView() {
		RelativeLayout rl=(RelativeLayout)findViewById(R.id.js_content);
		//View  huoqiView = View.inflate(this, R.layout.licai_tools_4huoqi, null);
		//rl.addView(huoqiView);
		sltCunkuanType=(SelectLabelText)findViewById(R.id.cunkuan_type);
		tvCunkuanType=sltCunkuanType.getValueText();
		
		btnJisuan=(Button)findViewById(R.id.btn_jisuan);
		tvCunkuanType.setText("活期");
		
		drawHuoqi(rl);
	}

	

	private void drawHuoqi(View v) {
		bizhongMap=new HashMap();
		bizhongMap.put("name", "人民币");
		bizhongMap.put("value", "10");
		
		View  huoqiView = View.inflate(this, R.layout.licai_tools_4huoqi, null);
		((ViewGroup) v).addView(huoqiView);
		
		sltBizhong=(SelectLabelText)huoqiView.findViewById(R.id.bizhong);
		tvBizhong=sltBizhong.getValueText();
		tvBizhong.setText("人民币");
		
		edlcsckje=(EditLabelText)huoqiView.findViewById(R.id.csckje);
		etcsckje=edlcsckje.getValueText();
		etcsckje.setInputType(InputType.TYPE_CLASS_NUMBER);
		
//		String cacheInfo=new CacheProcess().getCacheValueInSharedPreferences(this, "cache");
//		com.alibaba.fastjson.JSONObject cacheJson=com.alibaba.fastjson.JSONObject.parseObject(cacheInfo);
//		com.alibaba.fastjson.JSONObject lcjsqjson=cacheJson.getJSONObject("lcjsq");
//		hqnlv=lcjsqjson.getString("hqnlv");
		edlnlv=(EditLabelText)huoqiView.findViewById(R.id.nlv);
		etnlv=edlnlv.getValueText();
		etnlv.setText("0.385 %");
		etnlv.setCursorVisible(false);      //设置输入框中的光标不可见  
		etnlv.setFocusable(false);           //无焦点  
		etnlv.setFocusableInTouchMode(false);
		
		sltcscrrq=(SelectLabelText)huoqiView.findViewById(R.id.cscrrq);
		etcscrrq=sltcscrrq.getValueText();
		etcscrrq.setText(DateUtil.getCurrentDateStr());
		
		slttqrq=(SelectLabelText)huoqiView.findViewById(R.id.tqrq);
		ettqrq=slttqrq.getValueText();
		
		String currentDate=DateUtil.getCurrentDateStr();
		
		ettqrq.setText((Integer.parseInt(currentDate.substring(0, 4))+1)+""+ currentDate.substring(4, 10));
		
		eltsdlxje=(EditLabelText)huoqiView.findViewById(R.id.sdlxje);
		etsdlxje=eltsdlxje.getValueText();
		etsdlxje.setCursorVisible(false);      //设置输入框中的光标不可见  
		etsdlxje.setFocusable(false);           //无焦点  
		etsdlxje.setFocusableInTouchMode(false);
		
		eltlxsje=(EditLabelText)huoqiView.findViewById(R.id.lxsje);
		etlxsje=eltlxsje.getValueText();
		etlxsje.setCursorVisible(false);      //设置输入框中的光标不可见  
		etlxsje.setFocusable(false);           //无焦点  
		etlxsje.setFocusableInTouchMode(false);
		
		eltbxhj=(EditLabelText)huoqiView.findViewById(R.id.bxhj);
		etbxhj=eltbxhj.getValueText();
		
		sltBizhong.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(LIcaiTools4Cunkuan.this, R.style.selectDialog,"币种",moneyTypeList,bizhongMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
						tvBizhong.setText((String)m.get("name"));
						bizhongMap=m;
					}
				});
				 dialog.show();
			}
		});
		
		sltcscrrq.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 datePickerDialog = new DatePickerDialog(
						LIcaiTools4Cunkuan.this, cscrrqListener, thisYear,
						thisMonth, thisDay);
				datePickerDialog.show();
			
			}
		});
		
		slttqrq.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 datePickerDialog = new DatePickerDialog(
						LIcaiTools4Cunkuan.this, tqrqListener, thisYear,
						thisMonth, thisDay);
				datePickerDialog.show();
			
			}
		});
		btnJisuan.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (validate4huoqi()==false)return;
				
				String amount=etcsckje.getText().toString();
				String cscrrq=etcscrrq.getText().toString();
				String tqrq=ettqrq.getText().toString();
				
				
				int iamount=Integer.parseInt(amount);
//				int icscrrq=Integer.parseInt(cscrrq);
//				int itqrq=Integer.parseInt(tqrq);
				int idays=(int)DateUtil.dateDiff(cscrrq, tqrq);
				
				double sdlxje=iamount*((idays*0.385*0.01)/360);
				etsdlxje.setText(String.format("%.2f", sdlxje)+  "  元" );
				etlxsje.setText("0.00 元");
				
				etbxhj.setText(String.format("%.2f", iamount+sdlxje)+"  元");
				
			}
		});
		
		
	}
	
	private boolean validate4huoqi() {
		String cskrje=etcsckje.getText().toString();
		if(StringUtil.isBlank(cskrje)) {
			Toast.makeText(getApplicationContext(), "请填写初始存款金额！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
		
	}
	
	private OnDateSetListener cscrrqListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			etcscrrq.setText(year + "-" + (monthOfYear + 1) + "-"
					+ dayOfMonth);
		}
	};
	
	private OnDateSetListener tqrqListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			ettqrq.setText(year + "-" + (monthOfYear + 1) + "-"
					+ dayOfMonth);
		}
	};
	
	
	
	/**
	 * 
	 */
	private void reDrawCunKuanUI(Map m) {
		RelativeLayout rl=(RelativeLayout)findViewById(R.id.js_content);
		rl.removeAllViews();
		
		if("hq".equals((String)m.get("value"))) {
			drawHuoqi(rl);
		}else if("zczq".equals((String)m.get("value"))) {
			btnJisuan.setOnClickListener(null);
			ZCZQ zczq=new ZCZQ(this,rl);
			zczq.setMoneyTypeList(moneyTypeList);
			zczq.setBizhongMap(bizhongMap);
			zczq.initUI();
			zczq.regJsListener(btnJisuan,this);
		}
		
	}
	
	@Override
	protected void registerListener() {
		sltCunkuanType.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(LIcaiTools4Cunkuan.this, R.style.selectDialog,"存款类型",cunKuanTypeList,cunkuanMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
						tvCunkuanType.setText((String)m.get("name"));
						cunkuanMap=m;
						reDrawCunKuanUI(cunkuanMap);
					}
				});
				 dialog.show();
			}
		});
	}

}
