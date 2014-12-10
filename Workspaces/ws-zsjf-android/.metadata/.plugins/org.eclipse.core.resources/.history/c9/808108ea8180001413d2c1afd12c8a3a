package com.cattsoft.mos.activity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 *设备
 * @author xieyunchao
 *
 */
public class DeviceAdd1tActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
	
	private EditLabelText elt_device_bianhao;
	private EditLabelText elt_device_yzzcdm;
	private EditLabelText elt_device_name;
	private EditLabelText elt_device_english_name;
	private SelectLabelText slt_device_use_dept;
	private EditLabelText elt_device_install_address;
	private EditLabelText elt_device_mode;
	private EditLabelText elt_device_count;
	private SelectLabelText slt_device_unit;
	private SelectLabelText slt_device_belongs;
	private SelectLabelText elt_device_prod_date;
	
	private TextView txt_device_bianhao;
	private TextView txt_device_yzzcdm;
	private TextView txt_device_name;
	private TextView txt_device_english_name;
	private TextView txt_device_use_dept;
	private TextView txt_device_install_address;
	private TextView txt_device_mode;
	private TextView txt_device_count;
	private TextView txt_device_unit;
	private TextView txt_device_belongs;
	private TextView txt_device_prod_date;
	
	private Map deviceUseDeptMap=null;
	private Map deviceUnitMap=null;
	private Map deviceBelongsMap=null;
	
	private HashMap valueMap=null;
	
	private List useDepartmentList=null;
	private List deviceUnitStrList=null;
	private List belongList=null;
	
	String response="";
 
  
	StringBuffer date = new StringBuffer();
	DatePickerDialog datePickerDialog;
	Calendar my_Calendar;
	
	@Override
	protected void initView() {
		
		elt_device_bianhao=(EditLabelText)findViewById(R.id.device_bianhao);
		
		elt_device_yzzcdm=(EditLabelText)findViewById(R.id.device_yzzcdm);
		elt_device_name=(EditLabelText)findViewById(R.id.device_name);
		elt_device_english_name=(EditLabelText)findViewById(R.id.device_english_name);
		slt_device_use_dept=(SelectLabelText)findViewById(R.id.device_use_dept);
		elt_device_install_address=(EditLabelText)findViewById(R.id.device_install_address);
		elt_device_mode=(EditLabelText)findViewById(R.id.device_mode);
		elt_device_count=(EditLabelText)findViewById(R.id.device_count);
		slt_device_unit=(SelectLabelText)findViewById(R.id.device_unit);
		slt_device_belongs=(SelectLabelText)findViewById(R.id.device_belongs);
		elt_device_prod_date=(SelectLabelText)findViewById(R.id.device_prod_date);
		
		txt_device_bianhao=elt_device_bianhao.getValueText();
		txt_device_bianhao.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		txt_device_bianhao.setSingleLine(true);
		txt_device_yzzcdm=elt_device_yzzcdm.getValueText();
		txt_device_yzzcdm.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		txt_device_yzzcdm.setSingleLine(true);
		txt_device_name=elt_device_name.getValueText();
		txt_device_name.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		txt_device_name.setSingleLine(true);
		txt_device_english_name=elt_device_english_name.getValueText();
		txt_device_english_name.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		txt_device_english_name.setSingleLine(true);
		txt_device_use_dept=slt_device_use_dept.getValueText();
		txt_device_use_dept.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		txt_device_use_dept.setSingleLine(true);
		
		
		
		txt_device_install_address=elt_device_install_address.getValueText();
		txt_device_install_address.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		txt_device_install_address.setSingleLine(true);
		
		txt_device_mode=elt_device_mode.getValueText();
		txt_device_mode.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		txt_device_mode.setSingleLine(true);
		
		txt_device_count=elt_device_count.getValueText();
		txt_device_count.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		txt_device_count.setSingleLine(true);
		txt_device_count.setRawInputType(InputType.TYPE_CLASS_NUMBER);
		
		
		txt_device_unit=slt_device_unit.getValueText();
		txt_device_belongs=slt_device_belongs.getValueText();
		txt_device_prod_date=elt_device_prod_date.getValueText();
		
		
		
		
		nextBtn=(Button)findViewById(R.id.btn_next);
		resetBtn=(Button)findViewById(R.id.btn_reset);
	}
	
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_add1);

		// 设置标题栏
		super.setTitleBar("设备录入",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		super.setTitleRightButtonImg(R.drawable.btn_title_scan);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);  
	    if(resultCode == RESULT_OK){
	    	//Toast.makeText(DeviceAdd1tActivity.this, "dddd", Toast.LENGTH_SHORT).show();
	    	String scanRes=data.getStringExtra("smnumber");
	    	//Toast.makeText(DeviceAdd1tActivity.this, scanRes, Toast.LENGTH_SHORT).show();
	    	txt_device_bianhao.setText(scanRes);
	    }
	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(DeviceAdd1tActivity.this,
						CaptureActivity.class);
				intent.putExtra("isFirstRun", false);
				startActivityForResult(intent,0);
			}
		});
	}
	
	
	private void initData() {
		super.initCalendar();
		
		deviceUseDeptMap=new HashMap();
		deviceUnitMap=new HashMap();
		deviceBelongsMap=new HashMap();
		
		String defmap=this.getCacheProcess().getCacheValueInSharedPreferences(this, "eqpmap");
		if(!StringUtil.isBlank(defmap)) {
			com.alibaba.fastjson.JSONObject cacheInfoObj=com.alibaba.fastjson.JSONObject.parseObject(defmap);
			com.alibaba.fastjson.JSONObject cacheJson=cacheInfoObj.getJSONObject("cacheInfo"); 
			String useDepartmentStr=cacheJson.getString("useDepartmentlist");
			useDepartmentList=com.alibaba.fastjson.JSON.parseArray(useDepartmentStr, HashMap.class);
			
			String deviceUnitStr=cacheJson.getString("quantityUnitlist");
			deviceUnitStrList=com.alibaba.fastjson.JSON.parseArray(deviceUnitStr, HashMap.class);
			
			String belongListStr=cacheJson.getString("belonglist");
			belongList=com.alibaba.fastjson.JSON.parseArray(belongListStr, HashMap.class);
		}
		
		
	}
	
	

	@Override
	protected void registerListener() {
		nextBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
//				boolean validate=validate();
//				if(validate==false)return;
				forward();
			}
		});
		resetBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetPage();
			}
		});
		slt_device_use_dept.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DeviceAdd1tActivity.this, R.style.selectDialog,"使用部门",useDepartmentList,deviceUseDeptMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
						txt_device_use_dept.setText((String)m.get("name"));
						deviceUseDeptMap=m;
					}
				});
				 dialog.show();
			}
		});
		slt_device_unit.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DeviceAdd1tActivity.this, R.style.selectDialog,"单位",deviceUnitStrList,deviceUnitMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
						txt_device_unit.setText((String)m.get("name"));
						deviceUnitMap=m;
					}
				});
				 dialog.show();
			}
		});
		
		slt_device_belongs.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DeviceAdd1tActivity.this, R.style.selectDialog,"设备归属",belongList,deviceBelongsMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
						txt_device_belongs.setText((String)m.get("name"));
						deviceBelongsMap=m;
					}
				});
				 dialog.show();
			}
		});
		
		elt_device_prod_date.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(DeviceAdd1tActivity.this,
						myDateSetListener, thisYear, thisMonth, thisDay);
						datePickerDialog.show();
			}
		});
		
	}
	
	
	private void validateDevicetNo() {
		showProcessDialog(false);
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
			@Override
			public void run() {
				getIntentParm();
				String parm=com.alibaba.fastjson.JSONObject.toJSONString(valueMap);
				String methodName=Constant.news_type_head_line;
				response = getPostHttpContent("",methodName, parm);
				//response = getPostHttpContent("androidDef/saveAndroidDef.action", parm);
				Message m=new Message();
				m.what=1;
				Bundle b=new Bundle();
				b.putString("content", response);
				m.setData(b);
				handler.sendMessage(m);
			}
		});
		mThread.start();
		
	
	}
	
	private void forward() {
		//if(validate()==false)return;
		
		validateDevicetNo();
		
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				Bundle b=msg.getData();
				String content=b.getString("content");
				if(StringUtil.isBlank(content)) {
					Toast.makeText(DeviceAdd1tActivity.this, "服务器端没有响应！", Toast.LENGTH_SHORT).show();
				}else {
					com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(response);
					String flag=json.getString("flag");
					String desc=json.getString("desc");
					if("1".equals(flag)) {
						//Toast.makeText(DevicePreviewActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
						//如果成功进行下一步校验
						boolean bo=validate();
						if(bo==true) {
							//getIntentParm();
							Intent intent = new Intent(DeviceAdd1tActivity.this, DeviceAdd2tActivity.class);			
							Bundle bundle = new Bundle();
							bundle.putSerializable("valueMap", valueMap);
							intent.putExtras(bundle);
							startActivity(intent);
						}
					}else {
						Toast.makeText(DeviceAdd1tActivity.this, "设备编号已存在！", Toast.LENGTH_SHORT).show();
					}
				}
			}
			case 3:{

			}
			if (mProgressDialog != null)
				mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			}
		}
	};
	
	
	
	
	
	private boolean validate(String str) {
		Pattern p = Pattern.compile("^\\w+$");
		Matcher m = p.matcher(str);   
		return m.matches();  
	}
	
	private boolean validate() {
		boolean flag=true;
		if(StringUtil.isBlank( txt_device_bianhao.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写编号!", Toast.LENGTH_SHORT).show();
			return false;
		}
		 String bh=txt_device_bianhao.getText().toString().replaceAll("-", "");
		 if(!validate(bh)) {
			 Toast.makeText(getApplicationContext(), "编号格式不正确!", Toast.LENGTH_SHORT).show();
				return false;
		 }
		
		if(StringUtil.isBlank(txt_device_name.getText().toString() )) {
			Toast.makeText(getApplicationContext(), "请填写设备名称!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(StringUtil.isBlank(txt_device_use_dept.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写使用部门!", Toast.LENGTH_SHORT).show();
			return false;
		}
		return flag;
		
	}
	
	private void getIntentParm() {
		valueMap=new HashMap();
		valueMap.put("number", txt_device_bianhao.getText().toString());
		valueMap.put("code",txt_device_yzzcdm.getText().toString());
		valueMap.put("chName",txt_device_name.getText().toString());
		valueMap.put("enName",txt_device_english_name.getText().toString());
		valueMap.put("useDepartment",txt_device_use_dept.getText().toString()); 
		valueMap.put("useDepartmentMap",deviceUseDeptMap);
		valueMap.put("installAddress",txt_device_install_address.getText().toString());
		valueMap.put("model",txt_device_mode.getText().toString());
		valueMap.put("quantity",txt_device_count.getText().toString());
		valueMap.put("quantityUnitMap", deviceUnitMap);
		valueMap.put("quantityUnit", txt_device_unit.getText().toString());
		valueMap.put("belong",deviceBelongsMap.get("value")==null?"":(String)deviceBelongsMap.get("value"));
		valueMap.put("productDate1",txt_device_prod_date.getText().toString());
	}
	
	private void resetPage(){
		//Toast.makeText(DeviceAdd1tActivity.this, "cccccc", Toast.LENGTH_SHORT).show();
		txt_device_bianhao.setText("");
		txt_device_yzzcdm.setText("");
		txt_device_name.setText("");
		txt_device_english_name.setText("");
		txt_device_use_dept.setText("");
		txt_device_install_address.setText("");
		txt_device_mode.setText("");
		txt_device_count.setText("");
		txt_device_unit.setText("");
		txt_device_belongs.setText("");
		txt_device_prod_date.setText("");
	}
	
	private OnDateSetListener myDateSetListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			txt_device_prod_date.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
		}
	};
		
		 
	

}
