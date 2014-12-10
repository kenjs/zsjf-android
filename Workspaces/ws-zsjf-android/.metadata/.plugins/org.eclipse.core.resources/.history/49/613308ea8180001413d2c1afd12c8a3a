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
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 *缺陷 2
 * @author xieyunchao
 *
 */
public class DefecAdd2tActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
	
	private String defect_plan_finish_date=null;
	
	
	
	private EditText defect_level_txt;
	private EditText defect_zerenren1_txt;
	private EditText defect_zerenren2_txt;
	private EditText defect_zerenren3_txt;
	private EditText defect_zerenjiemian_txt;
	private EditText defect_jianduren1_txt;
	private EditText defect_jianduren2_txt;
	private EditText defect_jianduren3_txt;
	private TextView defect_plan_finish_date_txt;
	
	
	private SelectLabelText slt_zerenren1;
	private SelectLabelText slt_zerenren2;
	private SelectLabelText slt_zerenren3;
	
	private SelectLabelText slt_controlPerson1;//监督人
	private SelectLabelText slt_controlPerson2;
	private SelectLabelText slt_controlPerson3;
	
	
	Map problemClassMap=null;
	Map problemGradeMap=null;
	
	
	private SelectLabelText sel_defect_plan_finish_date;
	
	HashMap valueMap=null;
	Bundle bundle=null;
	
	private Map defect_zerenren1_map;
	private Map defect_zerenren2_map;
	private Map defect_zerenren3_map;
	
	private Map defect_jianduren1_map;
	private Map defect_jianduren2_map;
	private Map defect_jianduren3_map;
	
	private List defect_zerenren1_list;
	private List defect_zerenren2_list;
	private List defect_zerenren3_list;
	
	private List defect_jianduren1_list;
	private List defect_jianduren2_list;
	private List defect_jianduren3_list;
	
	
	
	int my_Year;
	int my_Month;
	int my_Day;
	StringBuffer date = new StringBuffer();
	DatePickerDialog datePickerDialog;
	Calendar my_Calendar;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.defect_add2);

		// 设置标题栏
		super.setTitleBar("缺陷录入",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	private void initData() {
		my_Calendar = Calendar.getInstance(Locale.CHINA);
		my_Year = my_Calendar.get(Calendar.YEAR);
		my_Month = my_Calendar.get(Calendar.MONTH);
		my_Day = my_Calendar.get(Calendar.DAY_OF_MONTH);
		
		defect_zerenren1_map=new HashMap();
		defect_zerenren2_map=new HashMap();
		defect_zerenren3_map=new HashMap();
		
		defect_jianduren1_map=new HashMap();
		defect_jianduren2_map=new HashMap();
		defect_jianduren3_map=new HashMap();
		
		
		String defmap=this.getCacheProcess().getCacheValueInSharedPreferences(this, "defmap"); 
		
		
		if(!StringUtil.isBlank(defmap)) {
			com.alibaba.fastjson.JSONObject cacheInfoObj=com.alibaba.fastjson.JSONObject.parseObject(defmap);
			com.alibaba.fastjson.JSONObject cacheJson=cacheInfoObj.getJSONObject("cacheInfo"); 
			 
			String dutyPerson1Liststr=cacheJson.getString("dutyPerson1");
			defect_zerenren1_list=com.alibaba.fastjson.JSON.parseArray(dutyPerson1Liststr, HashMap.class);
			
			String dutyPerson2Liststr=cacheJson.getString("dutyPerson2");
			defect_zerenren2_list=com.alibaba.fastjson.JSON.parseArray(dutyPerson2Liststr, HashMap.class);
			
			String dutyPerson3Liststr=cacheJson.getString("dutyPerson3");
			defect_zerenren3_list=com.alibaba.fastjson.JSON.parseArray(dutyPerson3Liststr, HashMap.class);
			
			String controlPerson1Liststr=cacheJson.getString("controlPerson1");
			defect_jianduren1_list=com.alibaba.fastjson.JSON.parseArray(controlPerson1Liststr, HashMap.class);
			
			String controlPerson2Liststr=cacheJson.getString("controlPerson2");
			defect_jianduren2_list=com.alibaba.fastjson.JSON.parseArray(controlPerson2Liststr, HashMap.class);
			
			String controlPerson3Liststr=cacheJson.getString("controlPerson3");
			defect_jianduren3_list=com.alibaba.fastjson.JSON.parseArray(controlPerson3Liststr, HashMap.class);
		}
		
		
		
		
		Intent intent = getIntent();
		bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
		
		problemClassMap=(HashMap)valueMap.get("problemClassMap");
		problemGradeMap=new HashMap();
		if("01".equals((String)problemClassMap.get("value"))) {
			problemGradeMap.put("value", "01");
			problemGradeMap.put("name", "一级");
		}else if("02".equals((String)problemClassMap.get("value"))) {
			problemGradeMap.put("value", "02");
			problemGradeMap.put("name", "二级");
		}else if("03".equals((String)problemClassMap.get("value"))) {
			problemGradeMap.put("value", "03");
			problemGradeMap.put("name", "三级");
		}
//		
		
	}
	
	
	
	@Override
	protected void initView() {
		nextBtn=(Button)findViewById(R.id.btn_next);
		resetBtn=(Button)findViewById(R.id.btn_reset);
		
		slt_zerenren1=(SelectLabelText)findViewById(R.id.defect_zerenren1);
		slt_zerenren2=(SelectLabelText)findViewById(R.id.defect_zerenren2);
		slt_zerenren3=(SelectLabelText)findViewById(R.id.defect_zerenren3);
		
		slt_controlPerson1=(SelectLabelText)findViewById(R.id.defect_jianduren1);
		slt_controlPerson2=(SelectLabelText)findViewById(R.id.defect_jianduren2);
		slt_controlPerson3=(SelectLabelText)findViewById(R.id.defect_jianduren3);
		
		sel_defect_plan_finish_date=(SelectLabelText)findViewById(R.id.defect_plan_finish_date);
		
		
		defect_level_txt=(EditText)((EditLabelText)findViewById(R.id.defect_level)).getValueText();
		defect_level_txt.setText((String)problemGradeMap.get("name"));
		defect_level_txt.setFocusable(false);
		defect_zerenren1_txt=(EditText)((SelectLabelText)findViewById(R.id.defect_zerenren1)).getValueText();
		defect_zerenren2_txt=(EditText)((SelectLabelText)findViewById(R.id.defect_zerenren2)).getValueText();
		defect_zerenren3_txt=(EditText)((SelectLabelText)findViewById(R.id.defect_zerenren3)).getValueText();
		defect_zerenjiemian_txt=(EditText)((EditLabelText)findViewById(R.id.defect_zerenjiemian)).getValueText();
		defect_zerenjiemian_txt.setSingleLine(true);
		defect_zerenjiemian_txt.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(250)
        });
		defect_jianduren1_txt=(EditText)((SelectLabelText)findViewById(R.id.defect_jianduren1)).getValueText();
		defect_jianduren2_txt=(EditText)((SelectLabelText)findViewById(R.id.defect_jianduren2)).getValueText();
		defect_jianduren3_txt=(EditText)((SelectLabelText)findViewById(R.id.defect_jianduren3)).getValueText();
		defect_plan_finish_date_txt=(TextView)((SelectLabelText)findViewById(R.id.defect_plan_finish_date)).getValueText();
		
	}

	@Override
	protected void registerListener() {
		nextBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				forward();
			}
		});
		
		resetBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetPage();
			}
		});
		
		sel_defect_plan_finish_date.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				datePickerDialog=new DatePickerDialog(DefecAdd2tActivity.this,
						myDateSetListener, my_Year, my_Month, my_Day);
						datePickerDialog.show();
			}
		});
		
		slt_zerenren1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DefecAdd2tActivity.this, R.style.selectDialog,"责任人1",defect_zerenren1_list,defect_zerenren1_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_zerenren1_txt.setText((String)m.get("name"));
						defect_zerenren1_map=m;
					}
				});
				 dialog.show();}
		});
		
		
		slt_zerenren2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DefecAdd2tActivity.this, R.style.selectDialog,"责任人2",defect_zerenren2_list,defect_zerenren2_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_zerenren2_txt.setText((String)m.get("name"));
						defect_zerenren2_map=m;
					}
				});
				 dialog.show();}
		});
		
		slt_zerenren3.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DefecAdd2tActivity.this, R.style.selectDialog,"责任人3",defect_zerenren3_list,defect_zerenren3_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_zerenren3_txt.setText((String)m.get("name"));
						defect_zerenren3_map=m;
					}
				});
				 dialog.show();}
		});
		
		slt_controlPerson1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DefecAdd2tActivity.this, R.style.selectDialog,"监督人1",defect_jianduren1_list,defect_jianduren1_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_jianduren1_txt.setText((String)m.get("name"));
						defect_jianduren1_map=m;
					}
				});
				 dialog.show();}
		});
		
		slt_controlPerson2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DefecAdd2tActivity.this, R.style.selectDialog,"监督人2",defect_jianduren2_list,defect_jianduren2_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_jianduren2_txt.setText((String)m.get("name"));
						defect_jianduren2_map=m;
					}
				});
				 dialog.show();}
		});
		
		slt_controlPerson3.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DefecAdd2tActivity.this, R.style.selectDialog,"监督人3",defect_jianduren3_list,defect_jianduren3_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_jianduren3_txt.setText((String)m.get("name"));
						defect_jianduren3_map=m;
					}
				});
				 dialog.show();}
		});
		
		
		
	}
	
	private void forward() {
//		if(validate()==false)return;
		
		String val_defect_level=defect_level_txt.getText().toString();
		String val_defect_zerenren1=defect_zerenren1_txt.getText().toString();
		String val_defect_zerenren2=defect_zerenren2_txt.getText().toString();
		String val_defect_zerenren3=defect_zerenren3_txt.getText().toString();
		String val_defect_zerenjiemian=defect_zerenjiemian_txt.getText().toString();
		String val_defect_jianduren1=defect_jianduren1_txt.getText().toString();
		String val_defect_jianduren2=defect_jianduren2_txt.getText().toString();
		String val_defect_jianduren3=defect_jianduren3_txt.getText().toString();
		String val_defect_plan_finish_date=defect_plan_finish_date_txt.getText().toString();
	 
		valueMap.put("problemGrade", val_defect_level);
		valueMap.put("problemGradeMap", problemGradeMap);
		
		valueMap.put("dutyPerson", val_defect_zerenren1);
		valueMap.put("leadDutyPerson", val_defect_zerenren2);
		valueMap.put("implementPerson", val_defect_zerenren3);
		
		valueMap.put("dutyPerson1Map", defect_zerenren1_map);
		valueMap.put("dutyPerson2Map", defect_zerenren2_map);
		valueMap.put("dutyPerson3Map", defect_zerenren3_map);
		
		valueMap.put("dutyInterface", val_defect_zerenjiemian);
		valueMap.put("controlPerson", val_defect_jianduren1);
		valueMap.put("leadControlPerson", val_defect_jianduren2);
		valueMap.put("reCheckPerson", val_defect_jianduren3);
		
		valueMap.put("controlPerson1Map", defect_jianduren1_map);
		valueMap.put("controlPerson2Map", defect_jianduren2_map);
		valueMap.put("controlPerson3Map", defect_jianduren3_map);
		
		
		valueMap.put("planDate1", val_defect_plan_finish_date);
		
		Intent intent = new Intent(DefecAdd2tActivity.this, DefecAdd3tActivity.class);			
		bundle.putSerializable("valueMap", valueMap);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private void resetPage(){
		//defect_level_txt.setText("");
		defect_zerenren1_txt.setText("");
		defect_zerenren2_txt.setText(""); 
		defect_zerenren3_txt.setText(""); 
		defect_zerenjiemian_txt.setText("");
		defect_jianduren1_txt.setText("");
		defect_jianduren2_txt.setText("");
		defect_jianduren3_txt.setText("");
		defect_plan_finish_date_txt.setText("");
		
		defect_zerenren1_map=new HashMap();
		defect_zerenren2_map=new HashMap();
		defect_zerenren3_map=new HashMap();
		
		defect_jianduren1_map=new HashMap();
		defect_jianduren2_map=new HashMap();
		defect_jianduren3_map=new HashMap();
	}
	
	private boolean validate() {
		boolean flag=true;
		if(StringUtil.isBlank(defect_plan_finish_date_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写计划完成日期!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
		if(StringUtil.isBlank(defect_zerenren1_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写责任人1!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
		if(StringUtil.isBlank(defect_zerenren2_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写责任人2!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
		if(StringUtil.isBlank(defect_zerenren3_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写责任人3!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
		if(StringUtil.isBlank(defect_jianduren1_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写监督人1!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
		if(StringUtil.isBlank(defect_jianduren2_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写监督人2!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
		if(StringUtil.isBlank(defect_jianduren3_txt.getText().toString())) {
			Toast.makeText(getApplicationContext(), "请填写监督人3!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
		
		return flag;
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
			defect_plan_finish_date = date.toString();
			defect_plan_finish_date_txt.setText(defect_plan_finish_date);
			date=new StringBuffer("");
		} 
		private String FormatString(int x) {
			String s = Integer.toString(x);
			if (s.length() == 1) {
			s = "0" + s;
			} return s;
			}

}
