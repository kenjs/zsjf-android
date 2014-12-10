package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 *设备
 * @author xieyunchao
 *
 */
public class DeviceAdd3tActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;

	
	EditLabelText elt_device_zhongliang=null;
	SelectLabelText slt_device_canshu=null;
	EditLabelText elt_device_zhidaoshang=null;
	EditLabelText elt_device_address=null;
	EditLabelText elt_device_lianxi_ren=null;
	EditLabelText elt_device_lianxi_fangshi=null;
	EditLabelText elt_device_shigongdanwei=null;
	SelectLabelText slt_device_anzhuang_riqi=null;
	EditLabelText elt_device_address2=null;
	EditLabelText elt_device_lianxiren2=null;
	
	
	EditText edt_device_zhongliang=null;
	EditText edt_device_canshu=null;
	EditText edt_device_zhidaoshang=null;
	EditText edt_device_address=null;
	EditText edt_device_lianxi_ren=null;
	EditText edt_device_lianxi_fangshi=null;
	EditText edt_device_shigongdanwei=null;
	EditText edt_device_anzhuang_riqi=null;
	EditText edt_device_address2=null;
	EditText edt_device_lianxiren2=null;

	DatePickerDialog datePickerDialog;
	
	List parmList=null;
	
	
	private HashMap valueMap=null;
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_add3);

		// 设置标题栏
		super.setTitleBar("设备录入",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	private void initData() {
		parmList=new ArrayList();
		super.initCalendar();
	}
	
	@Override
	protected void initView() {
		nextBtn=(Button)findViewById(R.id.btn_next);
		resetBtn=(Button)findViewById(R.id.btn_reset);
		
		elt_device_zhongliang=(EditLabelText)findViewById(R.id.device_zhongliang);
		
		slt_device_canshu=(SelectLabelText)findViewById(R.id.device_canshu);
		elt_device_zhidaoshang=(EditLabelText)findViewById(R.id.device_zhidaoshang);
		elt_device_address=(EditLabelText)findViewById(R.id.device_address);
		elt_device_lianxi_ren=(EditLabelText)findViewById(R.id.device_lianxi_ren);
		elt_device_lianxi_fangshi=(EditLabelText)findViewById(R.id.device_lianxi_fangshi);
		elt_device_shigongdanwei=(EditLabelText)findViewById(R.id.device_shigongdanwei);
		slt_device_anzhuang_riqi=(SelectLabelText)findViewById(R.id.device_anzhuang_riqi);
		elt_device_address2=(EditLabelText)findViewById(R.id.device_address2);
		elt_device_lianxiren2=(EditLabelText)findViewById(R.id.device_lianxiren2);
		
		edt_device_zhongliang=elt_device_zhongliang.getValueText();
		edt_device_zhongliang.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		edt_device_zhongliang.setSingleLine(true);
		edt_device_zhongliang.setSingleLine(true);
		edt_device_zhongliang.setRawInputType(InputType.TYPE_CLASS_NUMBER);
		edt_device_canshu=slt_device_canshu.getValueText();
		edt_device_zhidaoshang=elt_device_zhidaoshang.getValueText();
		edt_device_zhidaoshang.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		edt_device_zhidaoshang.setSingleLine(true);
		edt_device_address=elt_device_address.getValueText();
		edt_device_address.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		edt_device_address.setSingleLine(true);
		edt_device_lianxi_ren=elt_device_lianxi_ren.getValueText();
		edt_device_lianxi_ren.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(18)
        });
		edt_device_lianxi_ren.setSingleLine(true);
		edt_device_lianxi_fangshi=elt_device_lianxi_fangshi.getValueText();
		edt_device_lianxi_fangshi.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(20)
        });
		edt_device_lianxi_fangshi.setSingleLine(true);
		edt_device_shigongdanwei=elt_device_shigongdanwei.getValueText();
		edt_device_shigongdanwei.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		edt_device_shigongdanwei.setSingleLine(true);
		edt_device_anzhuang_riqi=slt_device_anzhuang_riqi.getValueText();
		edt_device_address2=elt_device_address2.getValueText();
		edt_device_address2.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		edt_device_address2.setSingleLine(true);
		edt_device_lianxiren2=elt_device_lianxiren2.getValueText();
		edt_device_lianxiren2.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(18)
        });
		edt_device_lianxiren2.setSingleLine(true);
	}

	@Override
	protected void registerListener() {
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
		
		slt_device_anzhuang_riqi.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(DeviceAdd3tActivity.this,
						myDateSetListener, thisYear, thisMonth, thisDay);
						datePickerDialog.show();
			}
		});
		
		slt_device_canshu.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				HashMap m=new HashMap();
				m.put("parmList", parmList);
				intent.putExtra("parmList", m);
				intent.setClass(DeviceAdd3tActivity.this,
						DeviceParmActivity.class);
				startActivityForResult(intent, 1);
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle b=data.getExtras();
			Map parmMap=(Map)b.getSerializable("parmList");
			if(parmMap!=null) {
				parmList=(List)parmMap.get("parmList");
				if(parmList!=null && parmList.size()>0) {
					edt_device_canshu.setText("已填写");
				}else {
					edt_device_canshu.setText("");
				}
			}
		}
	}
	
	private void  preareIntentData() {
		this.initCalendar();
		
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
		
		valueMap.put("weight", edt_device_zhongliang.getText().toString());
		valueMap.put("canshuList",parmList);
		valueMap.put("manufacturer", edt_device_zhidaoshang.getText().toString());
		valueMap.put("manuAddress",edt_device_address.getText().toString());
		valueMap.put("manuPerson",edt_device_lianxi_ren.getText().toString());
		valueMap.put("manuPhone",edt_device_lianxi_fangshi.getText().toString());
		valueMap.put("constructName",edt_device_shigongdanwei.getText().toString());
		valueMap.put("consDate1",edt_device_anzhuang_riqi.getText().toString());
		valueMap.put("consAddress",edt_device_address2.getText().toString());
		valueMap.put("consPerson",edt_device_lianxiren2.getText().toString());
		
	}
	
	private void forward() {
		preareIntentData();
		Intent intent = new Intent(DeviceAdd3tActivity.this, DeviceAdd4tActivity.class);			
		Bundle bundle = new Bundle();
		bundle.putSerializable("valueMap", valueMap);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private boolean validate() {
		String lxfs1=edt_device_lianxi_fangshi.getText().toString();
		if(!StringUtil.isMobileNO(lxfs1)) {
			Toast.makeText(getApplicationContext(), "联系方式格式错误！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	private void resetPage(){
		edt_device_zhongliang.setText("");
		edt_device_canshu.setText("");
		edt_device_zhidaoshang.setText("");
		edt_device_address.setText("");
		edt_device_lianxi_ren.setText("");
		edt_device_lianxi_fangshi.setText("");
		edt_device_shigongdanwei.setText("");
		edt_device_anzhuang_riqi.setText("");
		edt_device_address2.setText("");
		edt_device_lianxiren2.setText("");
		parmList.clear();
	}
	
	private OnDateSetListener myDateSetListener=new OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year,
		int monthOfYear, int dayOfMonth) {
			edt_device_anzhuang_riqi.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
			}
		};

}
