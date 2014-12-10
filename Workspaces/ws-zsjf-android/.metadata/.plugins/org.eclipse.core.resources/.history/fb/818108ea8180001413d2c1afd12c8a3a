package com.cattsoft.mos.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 *设备
 * @author xieyunchao
 *
 */
public class DeviceAdd2tActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
	 
	
	private SelectLabelText slt_device_fzbz;
	private EditLabelText elt_device_zerenren;
	private SelectLabelText slt_device_dengji_date;
	private EditLabelText elt_device_lingyongren;
	private SelectLabelText slt_device_lingyong_date;
	private EditLabelText elt_device_baoguanren;
	private SelectLabelText slt_device_type;
	
	private EditLabelText elt_device_gonglv;
	private EditLabelText elt_device_dianya;
	private EditLabelText elt_device_guige;
	
	private EditText edt_device_fzbz;
	private EditText edt_device_zerenren;
	private EditText edt_device_dengji_date;
	private EditText edt_device_lingyongren;
	private EditText edt_device_lingyong_date;
	private EditText edt_device_baoguanren;
	private EditText edt_device_type;
	private EditText edt_device_gonglv;
	private EditText edt_device_dianya;
	private EditText edt_device_guige; 
	
	
	private HashMap valueMap=null;
	
	List device_fzbz_List=null;
	List device_sblx_List=null;
	
	private Map device_fzbz_map=null;
	private Map device_type_map=null;
	
 
	StringBuffer date = new StringBuffer();
	DatePickerDialog datePickerDialog;
 
	
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_add2);

		// 设置标题栏
		super.setTitleBar("设备录入",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	private void initData() {
		this.initCalendar();
		
		device_fzbz_map=new HashMap();
		device_type_map=new HashMap();
		
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
		
		String defmap=this.getCacheProcess().getCacheValueInSharedPreferences(this, "eqpmap");
		if(!StringUtil.isBlank(defmap)) {
			com.alibaba.fastjson.JSONObject cacheInfoObj=com.alibaba.fastjson.JSONObject.parseObject(defmap);
			com.alibaba.fastjson.JSONObject cacheJson=cacheInfoObj.getJSONObject("cacheInfo"); 
			String dutyTeamListStr=cacheJson.getString("dutyTeamlist");
			device_fzbz_List=com.alibaba.fastjson.JSON.parseArray(dutyTeamListStr, HashMap.class);
			
			String equipTypeListStr=cacheJson.getString("equipTypelist");
			device_sblx_List=com.alibaba.fastjson.JSON.parseArray(equipTypeListStr, HashMap.class);
		}
		
	}
	
	@Override
	protected void initView() {
		nextBtn=(Button)findViewById(R.id.btn_next);
		resetBtn=(Button)findViewById(R.id.btn_reset);
		
		slt_device_fzbz=(SelectLabelText)findViewById(R.id.device_fzbz);
		elt_device_zerenren=(EditLabelText)findViewById(R.id.device_zenrenren);
		
		slt_device_dengji_date=(SelectLabelText)findViewById(R.id.device_dengji_date);
		elt_device_lingyongren=(EditLabelText)findViewById(R.id.device_lingyongren);
		slt_device_lingyong_date=(SelectLabelText)findViewById(R.id.device_lingyong_date);
		elt_device_baoguanren=(EditLabelText)findViewById(R.id.device_baoguanren);
		slt_device_type=(SelectLabelText)findViewById(R.id.device_type);
		elt_device_gonglv=(EditLabelText)findViewById(R.id.device_gonglv);
		
		elt_device_dianya=(EditLabelText)findViewById(R.id.device_dianya);
		elt_device_guige=(EditLabelText)findViewById(R.id.device_guige);
		
		edt_device_fzbz=slt_device_fzbz.getValueText();
		edt_device_zerenren=elt_device_zerenren.getValueText();
		edt_device_zerenren.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(18)
        });
		edt_device_dengji_date=slt_device_dengji_date.getValueText();
		edt_device_lingyongren=elt_device_lingyongren.getValueText();
		edt_device_lingyongren.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(18)
        });
		edt_device_lingyongren.setSingleLine(true);
		edt_device_lingyong_date=slt_device_lingyong_date.getValueText();
		edt_device_baoguanren=elt_device_baoguanren.getValueText();
		edt_device_baoguanren.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(18)
        });
		edt_device_baoguanren.setSingleLine(true);
		edt_device_type=slt_device_type.getValueText();
		
		edt_device_gonglv=elt_device_gonglv.getValueText();
		edt_device_gonglv.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		edt_device_gonglv.setSingleLine(true);
		edt_device_dianya=elt_device_dianya.getValueText();
		edt_device_dianya.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		edt_device_dianya.setSingleLine(true);
		edt_device_guige=elt_device_guige.getValueText(); 
		edt_device_guige.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		edt_device_guige.setSingleLine(true);
		edt_device_gonglv.setRawInputType(InputType.TYPE_CLASS_NUMBER);
		edt_device_dianya.setRawInputType(InputType.TYPE_CLASS_NUMBER);
		
	}

	@Override
	protected void registerListener() {
		
		slt_device_fzbz.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 Dialog dialog = new SelectDialog(DeviceAdd2tActivity.this, R.style.selectDialog,"负责班组",device_fzbz_List,device_fzbz_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						edt_device_fzbz.setText((String)m.get("name"));
						device_fzbz_map=m;
					}
				});
				 dialog.show();
			
			}
		});
		
		slt_device_type.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 Dialog dialog = new SelectDialog(DeviceAdd2tActivity.this, R.style.selectDialog,"设备类型",device_sblx_List,device_type_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						edt_device_type.setText((String)m.get("name"));
						device_type_map=m;
					}
				});
				 dialog.show();
			
			}
		});
		
		slt_device_dengji_date.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(DeviceAdd2tActivity.this,
						edt_device_dengji_date_Listener, thisYear, thisMonth, thisDay);
						datePickerDialog.show();
			}
		});
		
		slt_device_lingyong_date.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(DeviceAdd2tActivity.this,
						slt_device_lingyong_date_listener, thisYear, thisMonth, thisDay);
						datePickerDialog.show();
			}
		});
		
		
		nextBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean validate=validate();
				if(validate==false)return;
				forward();
			}
		});
		resetBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetPage();
			}
		});
	}
	
	private void forward() {
		//if(validate()==false)return;
		preareIntentData();
		Intent intent = new Intent(DeviceAdd2tActivity.this, DeviceAdd3tActivity.class);			
		Bundle bundle = new Bundle();
		bundle.putSerializable("valueMap", valueMap);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private void  preareIntentData() {
		valueMap.put("dutyTeamMap",device_fzbz_map);
		valueMap.put("dutyTeam",edt_device_fzbz.getText().toString());
		valueMap.put("dutyPerson",edt_device_zerenren.getText().toString());
		valueMap.put("registerDate1", edt_device_dengji_date.getText().toString());
		valueMap.put("recipientsPerson", edt_device_lingyongren.getText().toString());
		valueMap.put("recipientsDate1",edt_device_lingyong_date.getText().toString());
		valueMap.put("keeper", edt_device_baoguanren.getText().toString());
		valueMap.put("equipTypeMap",device_type_map);
		valueMap.put("equipType",edt_device_type.getText().toString());
		valueMap.put("power",edt_device_gonglv.getText().toString());
		valueMap.put("voltage",edt_device_dianya.getText().toString());
		valueMap.put("specification",edt_device_guige.getText().toString());
	}
	
	private boolean validate() {
		boolean flag=true;
		if(StringUtil.isBlank(edt_device_dengji_date.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写登记日期!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(StringUtil.isBlank(edt_device_type.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写设备类型!", Toast.LENGTH_SHORT).show();
			return false;
		}
		return flag;
	}
	
	private void resetPage(){
		edt_device_fzbz.setText("");
		edt_device_zerenren.setText("");
		edt_device_dengji_date.setText("");
		edt_device_lingyongren.setText("");
		edt_device_lingyong_date.setText("");
		edt_device_baoguanren.setText("");
		edt_device_type.setText("");
		edt_device_gonglv.setText("");
		edt_device_dianya.setText("");
		edt_device_guige.setText("");
	}
	
	private OnDateSetListener edt_device_dengji_date_Listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			edt_device_dengji_date.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

		}
	};
	
	private OnDateSetListener slt_device_lingyong_date_listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			edt_device_lingyong_date.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

		}
	};
	
	
		
		

}
