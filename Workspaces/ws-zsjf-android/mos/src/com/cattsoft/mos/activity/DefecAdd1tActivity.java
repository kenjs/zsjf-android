package com.cattsoft.mos.activity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 *缺陷 
 * @author xieyunchao
 *
 */
public class DefecAdd1tActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
	private String defect_no;//编号
	private String defect_dept;//提交部门
	private String defect_submit_person;//提交人
	private String defect_time;//录入时间
	private String defect_address;//位置
	private String defect_desc;//描述
	private String defect_type;//问题分类
	private String defect_fenbu;//分布
	private String defect_fenxiang;//分项
	

	private EditText defect_no_txt;
	private EditText defect_dept_txt;//提交部门
	private SelectLabelText sel_defect_submit_dept;
	private EditText defect_submit_person_txt;//提交人
	private TextView defect_time_txt;//录入时间
	private EditText defect_address_txt;//位置
	private EditText defect_desc_txt;//描述
	private TextView defect_type_txt;//问题分类
	private TextView defect_fenbu_txt;//分布
	private TextView defect_fenxiang_txt;//
	
	SelectLabelText sel_defect_input_time=null;
	SelectLabelText sel_defect_type=null;
	SelectLabelText sel_defect_fenbu=null;
	SelectLabelText sel_defect_fenxiang=null;
	
	private HashMap valueMap=null;
	
	private Map defect_type_map=null;
	private Map defect_fenbu_map=null;
	private Map defect_fenxiang_map=null;
	private Map defect_defect_dept_map=null;
	
	List defect_type_List =null;
	List defect_fenbu_List=null;
	List defect_fenxiang_List=null;
	List defect_tijiaobumen_List=null;
	String response="";
	String submitPerson="";
	
	int my_Year;
	int my_Month;
	int my_Day;
	StringBuffer date = new StringBuffer();
	DatePickerDialog datePickerDialog;
	Calendar my_Calendar;
	
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.defect_add1);

		// 设置标题栏
		super.setTitleBar("缺陷录入",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);
		super.showProcessDialog(true);
		new DefaultValueThread().start();
		

	}
	
	private void initData() {
		
		defect_type_map=new HashMap();
		defect_fenbu_map=new HashMap();
		defect_fenxiang_map=new HashMap();
		defect_defect_dept_map=new HashMap();
		
		
		my_Calendar = Calendar.getInstance(Locale.CHINA);
		my_Year = my_Calendar.get(Calendar.YEAR);
		my_Month = my_Calendar.get(Calendar.MONTH);
		my_Day = my_Calendar.get(Calendar.DAY_OF_MONTH);
		
		String defmap=this.getCacheProcess().getCacheValueInSharedPreferences(this, "defmap");
		System.out.println("defmapdefmapdefmap="+defmap);
		submitPerson=this.getCacheProcess().getCacheValueInSharedPreferences(this, "submitPersonName");
		
		
		if(!StringUtil.isBlank(defmap)) {
			com.alibaba.fastjson.JSONObject cacheInfoObj=com.alibaba.fastjson.JSONObject.parseObject(defmap);
			com.alibaba.fastjson.JSONObject cacheJson=cacheInfoObj.getJSONObject("cacheInfo"); 
			 
			String problemClassListStr=cacheJson.getString("problemClasslist");
			defect_type_List=com.alibaba.fastjson.JSON.parseArray(problemClassListStr, HashMap.class);
			
			
			String subsectionListStr=cacheJson.getString("subsectionlist");
			defect_fenbu_List=com.alibaba.fastjson.JSON.parseArray(subsectionListStr, HashMap.class);
			
			String subitemListStr=cacheJson.getString("subitemlist");
			defect_fenxiang_List=com.alibaba.fastjson.JSON.parseArray(subitemListStr, HashMap.class);
			
			String submitDeptStr=cacheJson.getString("tijiaobumenlist");
			defect_tijiaobumen_List=com.alibaba.fastjson.JSON.parseArray(submitDeptStr, HashMap.class);
		}
		
 
	}
	
	@Override
	protected void initView() {
		
		nextBtn=(Button)findViewById(R.id.btn_next);
		resetBtn=(Button)findViewById(R.id.btn_reset);
		
		
		sel_defect_submit_dept=(SelectLabelText)findViewById(R.id.defect_dept);
		sel_defect_input_time=(SelectLabelText)findViewById(R.id.defect_time);
		sel_defect_type=(SelectLabelText)findViewById(R.id.defect_type);
		sel_defect_fenbu=(SelectLabelText)findViewById(R.id.defect_fenbu);
		sel_defect_fenxiang=(SelectLabelText)findViewById(R.id.defect_fenxiang);
		
		defect_no_txt=(EditText)((EditLabelText)findViewById(R.id.defect_no)).getValueText();
		defect_no_txt.setCursorVisible(false);      //设置输入框中的光标不可见  
		defect_no_txt.setFocusable(false);           //无焦点  
		defect_no_txt.setFocusableInTouchMode(false);
		defect_dept_txt=(EditText)((SelectLabelText)findViewById(R.id.defect_dept)).getValueText();
		defect_submit_person_txt=(EditText)((EditLabelText)findViewById(R.id.defect_submit_person)).getValueText();
		defect_submit_person_txt.setText(submitPerson);
		defect_submit_person_txt.setFocusable(false);
		
		
		defect_time_txt=(TextView)((SelectLabelText)findViewById(R.id.defect_time)).getValueText();
		defect_time_txt.setText(DateUtil.date2Str(new java.util.Date()));
		defect_time_txt.setFocusable(false);
		defect_address_txt=(EditText)((EditLabelText)findViewById(R.id.defect_address)).getValueText();
		defect_address_txt.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		defect_address_txt.setSingleLine(true);
		
		defect_desc_txt=(EditText)((EditLabelText)findViewById(R.id.defect_desc)).getValueText();
		defect_desc_txt.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(200)
        });
		defect_desc_txt.setSingleLine(true);
		defect_type_txt=(TextView)((SelectLabelText)findViewById(R.id.defect_type)).getValueText();
		defect_fenbu_txt=(TextView)((SelectLabelText)findViewById(R.id.defect_fenbu)).getValueText();
		defect_fenxiang_txt=(TextView)((SelectLabelText)findViewById(R.id.defect_fenxiang)).getValueText();
		
	}

	@Override
	protected void registerListener() {
		
		sel_defect_submit_dept.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 Dialog dialog = new SelectDialog(DefecAdd1tActivity.this, R.style.selectDialog,"提交部门",defect_tijiaobumen_List,defect_defect_dept_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_dept_txt.setText((String)m.get("name"));
						defect_defect_dept_map=m;
						}
				});
				 dialog.show();
			}
		});
		
		
		sel_defect_type.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 Dialog dialog = new SelectDialog(DefecAdd1tActivity.this, R.style.selectDialog,"问题分类",defect_type_List,defect_type_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_type_txt.setText((String)m.get("name"));
						defect_type_map=m;
						}
				});
				 dialog.show();
			}
		});
		
		sel_defect_fenbu.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 Dialog dialog = new SelectDialog(DefecAdd1tActivity.this, R.style.selectDialog,"分部",defect_fenbu_List,defect_fenbu_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_fenbu_txt.setText((String)m.get("name"));
						defect_fenbu_map=m;
					}
				});
				 dialog.show();
			}
		});
		
		sel_defect_fenxiang.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 Dialog dialog = new SelectDialog(DefecAdd1tActivity.this, R.style.selectDialog,"分项",defect_type_List,defect_fenxiang_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_fenxiang_txt.setText((String)m.get("name"));
						defect_fenxiang_map=m;
					}
				});
				 dialog.show();
			}
		});
	
		
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
		
//		sel_defect_input_time.setOnClickListener(new Button.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				datePickerDialog=new DatePickerDialog(DefecAdd1tActivity.this,
//						myDateSetListener, my_Year, my_Month, my_Day);
//						datePickerDialog.show();
//			}
//		});
//		
		 
		
	}
	
	private void forward() {
		valueMap=new HashMap();
		String val_defect_no=defect_no_txt.getText().toString();
		String val_defect_dept=defect_dept_txt.getText().toString();
		String val_defect_submit_person=defect_submit_person_txt.getText().toString();
		String val_defect_time=defect_time_txt.getText().toString();
		String val_defect_address=defect_address_txt.getText().toString();
		String val_defect_desc=defect_desc_txt.getText().toString();
		String val_defect_type=defect_type_txt.getText().toString();
		String val_defect_fenbu=defect_fenbu_txt.getText().toString();
		String val_defect_fenxiang=defect_fenxiang_txt.getText().toString();
		
		valueMap.put("number", val_defect_no);
		valueMap.put("putDepartment", val_defect_dept);
		valueMap.put("putPerson", val_defect_submit_person);
		valueMap.put("enterDate1", val_defect_time);
		valueMap.put("address", val_defect_address);
		valueMap.put("problemDesc", val_defect_desc);
		valueMap.put("problemClass", val_defect_type);
		valueMap.put("subsection", val_defect_fenbu);
		valueMap.put("subitem", val_defect_fenxiang);
		
		valueMap.put("putDepartmentMap", defect_defect_dept_map);
		valueMap.put("problemClassMap", defect_type_map);
		valueMap.put("subsectionMap", defect_fenbu_map);
		valueMap.put("subitemMap", defect_fenxiang_map);
		
		
		Intent intent = new Intent(DefecAdd1tActivity.this, DefecAdd2tActivity.class);			
		Bundle bundle = new Bundle();
		bundle.putSerializable("valueMap", valueMap);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private boolean validate() {
		boolean flag=true;
		if(StringUtil.isBlank( defect_dept_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填提交部门!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(StringUtil.isBlank( defect_type_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写问题分类!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(StringUtil.isBlank( defect_fenbu_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写分部!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(StringUtil.isBlank( defect_fenxiang_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写分项!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		return flag;
	}
	
	private void doAfterGetCacheValue(Message msg){
		Bundle b=msg.getData();
		String content=b.getString("content");
		if(StringUtil.isBlank(content)) {
			Toast.makeText(DefecAdd1tActivity.this, "服务器端没有响应！", Toast.LENGTH_SHORT).show();
		}else {
			com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(response);
			String flag=json.getString("flag");
			String defectNo=json.getString("number");
			if("1".equals(flag)) {
				defect_no_txt.setText(defectNo);
			}else {
				Toast.makeText(DefecAdd1tActivity.this, "获取缺陷编号失败！",Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				doAfterGetCacheValue(msg);
			}
			case 3:{

			}
			if (mProgressDialog != null)
				mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			}
		}
	};
	
	
	private class DefaultValueThread extends Thread{
		public void run() {
			String methodName=Constant.getDefectNoMethod;
			response = getPostHttpContent("",methodName, "dd");
			Message m=new Message();
			m.what=1;
			Bundle b=new Bundle();
			b.putString("content", response);
			m.setData(b);
			handler.sendMessage(m);
		}
	}
	
	private void resetPage(){
		//defect_no_txt.setText("");
		defect_dept_txt.setText("");
		//defect_submit_person_txt.setText("");
		//defect_time_txt.setText("");
		defect_address_txt.setText("");
		defect_desc_txt.setText("");
		defect_type_txt.setText("");
		defect_fenbu_txt.setText("");
		defect_fenxiang_txt.setText("");
		
	}
	
	private OnDateSetListener myDateSetListener=new OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year,
		int monthOfYear, int dayOfMonth) {
		my_Year=year;
		my_Month=monthOfYear;
		my_Day=dayOfMonth;
		/*显示设置后的日期*/
		loadDate();
		}
		};
		
		private void loadDate() {
			date.append(my_Year).append("-")
			.append(FormatString(my_Month + 1))
			.append("-").append(FormatString(my_Day));
			defect_time = date.toString();
			defect_time_txt.setText(defect_time);
			date=new StringBuffer("");
		} 
		private String FormatString(int x) {
			String s = Integer.toString(x);
			if (s.length() == 1) {
			s = "0" + s;
			} return s;
			}

}
