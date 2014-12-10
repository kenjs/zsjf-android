package com.cattsoft.mos.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 * 理财产品查询条件界面
 * 
 * @author xieyunchao
 * 
 */
public class LicaiQueryActivity extends BasicActivity {

	private EditLabelText elt_chanpin_name;
	private SelectLabelText slt_publish_area;
	private SelectLabelText slt_publish_org;
	private SelectLabelText slt_publish_date;
	private SelectLabelText slt_shouyilv;
	private SelectLabelText slt_licai_type;
	private SelectLabelText slt_sale_status;

	private EditText et_chanpin_name;
	private TextView tv_publish_area;
	private TextView tv_publish_org;
	private TextView tv_publish_date;
	private TextView tv_shouyilv;
	private TextView tv_licai_type;
	private TextView tv_sale_status;

	private Button btnSure;

	private Map licaiOrgMap = null;
	private List licaiOrgList = null;
	private Map souyilvMap = null;
	private List souyilvList = null;
	private Map chanpinzlMap = null;
	private List chanpinzlList = null;
	private Map saleStatusMap=null;
	private List saleStatusList=null;
	private Map licaiAreaMap=null;
	private List licaiAreaList=null;
	String provinceName="";
	

	DatePickerDialog datePickerDialog;
	
	private HashMap valueMap=null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_licai_query);

		// 设置标题栏
		super.setTitleBar("产品查询", View.VISIBLE, View.GONE, View.GONE, false);
		initData();
		initView();
		registerListener();

	}

	private void initData() {
		super.initCalendar();
		licaiOrgMap = new HashMap();
		licaiOrgMap.put("name", "");
		licaiOrgMap.put("value", "");
		
		String cacheInfo = new CacheProcess().getCacheValueInSharedPreferences(
				this, "cache");
		com.alibaba.fastjson.JSONObject j = com.alibaba.fastjson.JSONObject
				.parseObject(cacheInfo);
		
		String publishOrgs=new CacheProcess().getPublishOrgCacheValueInSharedPreferences(this,"publishOrgs");
		
		Map orgMap =new HashMap();
		orgMap.put("value", "");
		orgMap.put("name", "全部");
		licaiOrgList=new ArrayList();
		licaiOrgList.add(orgMap);
		List alicaiOrgList = com.alibaba.fastjson.JSONArray.parseArray(publishOrgs, HashMap.class);
		licaiOrgList.addAll(alicaiOrgList);
		
		Map cpzlMap=new HashMap();
		cpzlMap.put("name", "全部");
		cpzlMap.put("value", "");
		chanpinzlList=new ArrayList();
		chanpinzlList.add(cpzlMap);
		
		com.alibaba.fastjson.JSONArray cpzlarr = j.getJSONArray("pfcatList");// 产品种类
		List achanpinzlList = com.alibaba.fastjson.JSONArray.parseArray(
				cpzlarr.toJSONString(), HashMap.class);
		chanpinzlList.addAll(achanpinzlList);
		
		String cities=this.getCitiesFromFile();
		List alicaiAreaList=com.alibaba.fastjson.JSONArray.parseArray(cities, HashMap.class);
		licaiAreaList=new ArrayList();
		Map am=new HashMap();
		am.put("name", "全部");
		am.put("value", "");
		licaiAreaList.add(am);
		licaiAreaList.addAll(alicaiAreaList);
		
		licaiAreaMap=new HashMap();
		

		// 收益率
		souyilvMap = new HashMap();
		souyilvMap.put("name", "");
		souyilvMap.put("value", "");
		
		souyilvList = new ArrayList();
		
		Map lv0 = new HashMap();
		lv0.put("name", "全部");
		lv0.put("value", "");
		
		Map lv1 = new HashMap();
		lv1.put("name", "3%以下");
		lv1.put("value", "0.00:0.03");

		Map lv2 = new HashMap();
		lv2.put("name", "3%-6%");
		lv2.put("value", "0.03:0.06");

		Map lv3 = new HashMap();
		lv3.put("name", "6%-10%");
		lv3.put("value", "0.06:0.10");

		Map lv4 = new HashMap();
		lv4.put("name", "10%以上");
		lv4.put("value", "0.10:1.00");
		souyilvList.add(lv0);
		souyilvList.add(lv1);
		souyilvList.add(lv2);
		souyilvList.add(lv3);
		souyilvList.add(lv4);

		// 产品种类
		chanpinzlMap = new HashMap();
		chanpinzlMap.put("name", "");
		chanpinzlMap.put("value", "");
		
		saleStatusMap=new HashMap();
		saleStatusMap.put("name", "");
		saleStatusMap.put("value", "");
		
		Map s0=new HashMap();
		s0.put("name", "全部");
		s0.put("value", "");
		
		Map s1=new HashMap();
		s1.put("name", "在售");
		s1.put("value", "1");
		Map s2=new HashMap();
		s2.put("name", "预售");
		s2.put("value", "2");
		Map s3=new HashMap();
		s3.put("name", "售罄");
		s3.put("value", "3");
		Map s4=new HashMap();
		s4.put("name", "不限");
		s4.put("value", "4");
		Map s5=new HashMap();
		s5.put("name", "结束");
		s5.put("value", "5");
		saleStatusList=new ArrayList();
		saleStatusList.add(s0);
		saleStatusList.add(s1);
		saleStatusList.add(s2);
		saleStatusList.add(s3);
		saleStatusList.add(s4);
		saleStatusList.add(s5);
		
		
		
		try {
			provinceName=new CacheProcess().getLocationInfo(this);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		
		valueMap=new HashMap();

	}

	@Override
	protected void initView() {
		elt_chanpin_name = (EditLabelText) findViewById(R.id.chanpin_name);
		slt_publish_area = (SelectLabelText) findViewById(R.id.publish_area);
		slt_publish_org = (SelectLabelText) findViewById(R.id.publish_org);
		slt_publish_date = (SelectLabelText) findViewById(R.id.publish_date);
		slt_shouyilv = (SelectLabelText) findViewById(R.id.shouyilv);
		slt_licai_type = (SelectLabelText) findViewById(R.id.licai_type);
		slt_sale_status=(SelectLabelText) findViewById(R.id.sale_status);

		et_chanpin_name = elt_chanpin_name.getValueText();
		tv_publish_area = slt_publish_area.getValueText();
		
		
		if(!StringUtil.isBlank(provinceName)) {
			provinceName=provinceName.replaceAll("省", "").replaceAll("市", "");
			tv_publish_area.setText(provinceName);
			
			//根据获取到的省份去json中找到id
			for(int i=0;i<licaiAreaList.size();i++) {
				Map m=(HashMap)licaiAreaList.get(i);
				String areaName=(String)m.get("name");
				if(areaName.equals(provinceName)) {
					String areaId=(String)m.get("value");
					licaiAreaMap.put("value", areaId);
				}
				
			}
			licaiAreaList.size();
			licaiAreaMap.put("name", provinceName);
			
		}else {
			licaiAreaMap.put("name", "全部");
			licaiAreaMap.put("value", "");
		}
		
		
		tv_publish_org = slt_publish_org.getValueText();
		tv_publish_org.setText("全部");
		tv_publish_date = slt_publish_date.getValueText();
		tv_shouyilv = slt_shouyilv.getValueText();
		tv_shouyilv.setText("全部");
		tv_licai_type = slt_licai_type.getValueText();
		tv_licai_type.setText("全部");
		tv_sale_status=slt_sale_status.getValueText();
		tv_sale_status.setText("全部");

		btnSure = (Button) findViewById(R.id.btn_sure);

	}
	
	
	
	
	
	/**
	 * 发行机构
	 */
	private Button.OnClickListener fxjglistener= new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Dialog dialog = new SelectDialog(LicaiQueryActivity.this,
					R.style.selectDialog, "发行机构", licaiOrgList,
					licaiOrgMap, new SelectDialog.OnSelectItemgListener() {
						@Override
						public void refreshActivity(Map m) {
							tv_publish_org.setText((String) m.get("name"));
							licaiOrgMap = m;
						}
					});
			dialog.show();
		}
	};
	
	
	/**
	 * 发行机构
	 */
	private Button.OnClickListener fxqylistener= new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Dialog dialog = new SelectDialog(LicaiQueryActivity.this,
					R.style.selectDialog, "发行区域", licaiAreaList,
					licaiAreaMap, new SelectDialog.OnSelectItemgListener() {
						@Override
						public void refreshActivity(Map m) {
							tv_publish_area.setText((String) m.get("name"));
							licaiAreaMap = m;
						}
					});
			dialog.show();
		}
	};
	
	
	
	/**
	 * 收益率
	 */
	private  Button.OnClickListener syllistener=new  Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Dialog dialog = new SelectDialog(LicaiQueryActivity.this,
					R.style.selectDialog, "收益率", souyilvList, souyilvMap,
					new SelectDialog.OnSelectItemgListener() {
						@Override
						public void refreshActivity(Map m) {
							tv_shouyilv.setText((String) m.get("name"));
							souyilvMap = m;
						}
					});
			dialog.show();
		}
	};
	
	/**
	 * 理财类别
	 */
	private Button.OnClickListener cpzllistener=new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Dialog dialog = new SelectDialog(LicaiQueryActivity.this,
					R.style.selectDialog, "产品种类", chanpinzlList,
					chanpinzlMap, new SelectDialog.OnSelectItemgListener() {
						@Override
						public void refreshActivity(Map m) {
							tv_licai_type.setText((String) m.get("name"));
							chanpinzlMap = m;
						}
					});
			dialog.show();
		}
	};
	
	/**
	 * 发行日期
	 */
	Button.OnClickListener fxrqlistener=new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			datePickerDialog = new DatePickerDialog(
					LicaiQueryActivity.this, myDateSetListener, thisYear,
					thisMonth, thisDay);
			datePickerDialog.show();
		}
	};
	
	/**
	 * 销售状态
	 */
	Button.OnClickListener xsztlistener=new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Dialog dialog = new SelectDialog(LicaiQueryActivity.this,
					R.style.selectDialog, "销售状态", saleStatusList,
					saleStatusMap, new SelectDialog.OnSelectItemgListener() {
						@Override
						public void refreshActivity(Map m) {
							tv_sale_status.setText((String) m.get("name"));
							saleStatusMap = m;
						}
					});
			dialog.show();
		}
	};

	private String getCitiesFromFile() {
//		String readlPath=getApplicationContext().getFilesDir().getAbsolutePath();
		InputStream in = null;
		
			in = LicaiQueryActivity.class.getResourceAsStream("cities.txt");
	 
		String res = "";
		try {
			// 一次读一个字节 
 
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String msg = null;
			while ((msg = br.readLine()) != null) {
				res = res + msg;

			}
		} catch (IOException e) { 
			e.printStackTrace();
			return "";
		}
		return res;

	}
	
	
	
	
	
	@Override
	protected void registerListener() {
		slt_publish_org.setOnClickListener(fxjglistener);
		tv_publish_org.setOnClickListener(fxjglistener);

		slt_shouyilv.setOnClickListener(syllistener);
		tv_shouyilv.setOnClickListener(syllistener);

		slt_licai_type.setOnClickListener(cpzllistener);
		tv_licai_type.setOnClickListener(cpzllistener);
		
		

		slt_publish_date.setOnClickListener(fxrqlistener );
		tv_publish_date.setOnClickListener(fxrqlistener);
		
		slt_sale_status.setOnClickListener(xsztlistener);
		tv_sale_status.setOnClickListener(xsztlistener);
		
		slt_publish_area.setOnClickListener(fxqylistener);
		tv_publish_area.setOnClickListener(fxqylistener);
		

		btnSure.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				getIntentParm();
				Intent intent = new Intent(LicaiQueryActivity.this, LicaiQueryResultActivity.class);			
				Bundle bundle = new Bundle();
				bundle.putSerializable("valueMap", valueMap);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});

	}

	private OnDateSetListener myDateSetListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			tv_publish_date.setText(year + "-" + (monthOfYear + 1) + "-"
					+ dayOfMonth);
		}
	};
	
	private void getIntentParm() {
		valueMap.put("pro_name", et_chanpin_name.getText().toString());
		valueMap.put("issuer_code", (String)licaiOrgMap.get("value"));
		
		//收益率
		String shouyilvbetween=(String)souyilvMap.get("value");
		
		if(!StringUtil.isBlank(shouyilvbetween)) {
			String shouyilvs[]=shouyilvbetween.split(":");
			String shouyilvMin=shouyilvs[0];
			String shouyilvMax=shouyilvs[1];
			valueMap.put("min_exp_return_rate", shouyilvMin);
			valueMap.put("max_exp_return_rate", shouyilvMax);
		}else {
			valueMap.put("min_exp_return_rate", "");
			valueMap.put("max_exp_return_rate", "");
		}
		
		valueMap.put("issue_date", tv_publish_date.getText().toString());
		valueMap.put("pfcat_code", (String)chanpinzlMap.get("value"));
		
		valueMap.put("area", tv_publish_area.getText().toString());
		valueMap.put("status", (String)saleStatusMap.get("value"));
		valueMap.put("orderBy", "exp_return_rate");
		valueMap.put("desc", "true");
		valueMap.put("page", 1);
		
		
	}


}
