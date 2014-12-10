package com.cattsoft.mos.activity;

import java.util.ArrayList;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.adapter.PhotoGridAdapter;
import com.cattsoft.mos.dialog.PopDialog;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 *缺陷 3
 * @author xieyunchao
 *
 */
public class DefecAdd3tActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
	
	private List stsList=new ArrayList();
	private List isOpenList=new ArrayList();
	
	private EditLabelText elt_defect_zhegngaidanwei=null;
	private SelectLabelText elt_defect_kaiyeqianhou=null;
	private com.cattsoft.mos.view.EditLabelText sel_defect_state=null;
	private SelectLabelText sel_defect_audit_date=null;
	private EditLabelText elt_defect_remark=null;
	
	
	private TextView defect_zhegngaidanwei_txt=null;
	private TextView defect_state_txt=null;
	private TextView defect_audit_date_txt=null;
	private TextView defect_kaiyeqianhou_txt=null;
	private TextView defect_remark_txt=null;
	
	private String defect_audit_date;
	
	private Map defect_state_map=null;
	private Map defect_kaiyeqianhou_map=null;
	
	
	int my_Year;
	int my_Month;
	int my_Day;
	StringBuffer date = new StringBuffer();
	DatePickerDialog datePickerDialog;
	Calendar my_Calendar;
	
	private HashMap valueMap;
	
	String path="";
	
	private GridView photoGridView=null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.defect_add3);

		// 设置标题栏
		super.setTitleBar("缺陷录入",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		this.setTitleRightButtonImg(R.drawable.date_choose);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);
	}
	
	private void initData() {
		TakePhotoMain.j = 0;
		path = TakePhotoMain.ReadSDPath() + "/DCIM/Camera/";
		TakePhotoMain.soAttachList = new ArrayList();
		
		String defmap=this.getCacheProcess().getCacheValueInSharedPreferences(this, "defmap");
		if(!StringUtil.isBlank(defmap)) {
			com.alibaba.fastjson.JSONObject cacheInfoObj=com.alibaba.fastjson.JSONObject.parseObject(defmap);
			com.alibaba.fastjson.JSONObject cacheJson=cacheInfoObj.getJSONObject("cacheInfo");  
			String stsListStr=cacheJson.getString("spResultlist");
			stsList=com.alibaba.fastjson.JSON.parseArray(stsListStr, HashMap.class);
			
			String isOpenListStr=cacheJson.getString("isOpenlist");
			isOpenList=com.alibaba.fastjson.JSON.parseArray(isOpenListStr, HashMap.class);
		}  
		
		
		my_Calendar = Calendar.getInstance(Locale.CHINA);
		my_Year = my_Calendar.get(Calendar.YEAR);
		my_Month = my_Calendar.get(Calendar.MONTH);
		my_Day = my_Calendar.get(Calendar.DAY_OF_MONTH);
		
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
		
		defect_state_map=new HashMap();
		defect_state_map.put("name", "新入");
		defect_state_map.put("value", "");
	} 
	
	@Override
	protected void initView() {
		photoGridView = (GridView) findViewById(R.id.gridView1);
		nextBtn=(Button)findViewById(R.id.btn_next);
		resetBtn=(Button)findViewById(R.id.btn_reset);
		
		sel_defect_state=(com.cattsoft.mos.view.EditLabelText)findViewById(R.id.defect_state);
		//sel_defect_audit_date=(SelectLabelText)findViewById(R.id.defect_audit_date);
		elt_defect_zhegngaidanwei=(EditLabelText)findViewById(R.id.defect_zhegngaidanwei);
		elt_defect_kaiyeqianhou=(SelectLabelText)findViewById(R.id.defect_kaiyeqianhou);
		elt_defect_remark=(EditLabelText)findViewById(R.id.defect_remark);
		
		defect_zhegngaidanwei_txt=elt_defect_zhegngaidanwei.getValueText();
		defect_zhegngaidanwei_txt.setSingleLine(true);
		defect_zhegngaidanwei_txt.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		defect_state_txt= sel_defect_state.getValueText();
		defect_state_txt.setText("新入");
		defect_state_txt.setCursorVisible(false);      //设置输入框中的光标不可见  
		defect_state_txt.setFocusable(false);           //无焦点  
		defect_state_txt.setFocusableInTouchMode(false);
		
		
		//defect_audit_date_txt=sel_defect_audit_date.getValueText();
		defect_kaiyeqianhou_txt=elt_defect_kaiyeqianhou.getValueText();
		defect_remark_txt=elt_defect_remark.getValueText();
		defect_remark_txt.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(200)
        });
		
		defect_kaiyeqianhou_map=new HashMap();
		defect_kaiyeqianhou_map.put("name", "系统上线前");
		defect_kaiyeqianhou_map.put("value", "01");
		defect_kaiyeqianhou_txt.setText("系统上线前");
		
		
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
//		sel_defect_state.setOnClickListener(new Button.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Dialog dialog = new SelectDialog(DefecAdd3tActivity.this, R.style.selectDialog,"状态",stsList,defect_state_map,new SelectDialog.OnSelectItemgListener() {
//					
//					@Override
//					public void refreshActivity(Map m) {
//						defect_state_txt.setText((String)m.get("name"));
//						defect_state_map=m;
//						if("1".equals((String)defect_state_map.get("value"))) {
//							sel_defect_audit_date.setEnabled(true);
//							
//						}
//					}
//				});
//				 dialog.show();
//			}
//		});
		
//		sel_defect_audit_date.setOnClickListener(new Button.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(!"".equals(defect_state_map.get("value"))) {
//					datePickerDialog=new DatePickerDialog(DefecAdd3tActivity.this,
//							myDateSetListener, my_Year, my_Month, my_Day);
//							datePickerDialog.show();
//				}
//				
//			}
//		});
//		
		elt_defect_kaiyeqianhou.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(DefecAdd3tActivity.this, R.style.selectDialog,"开业前后标识",isOpenList,defect_kaiyeqianhou_map,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						defect_kaiyeqianhou_txt.setText((String)m.get("name"));
						defect_kaiyeqianhou_map=m;
					}
				});
				 dialog.show();}
		});
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case 3: {
					this.initPhotoGrid();
				}
				break;
			}
		}
		
	}
	
	private void initPhotoGrid() {
		photoGridView = (GridView) findViewById(R.id.gridView1);
		photoGridView.setVisibility(View.VISIBLE);
		System.out.println("uuuuuuuuuu");

		this.photoGridView.setAdapter(new PhotoGridAdapter(DefecAdd3tActivity.this));
		// 点击单张图片显示
		this.photoGridView
				.setOnItemClickListener(new GridView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int postion, long arg3) {
						Intent intent = new Intent();
						intent.setClass(DefecAdd3tActivity.this,
								ThumbnailActivity.class);
						Bundle bundle = new Bundle();
						bundle.putInt("ID", postion);
						intent.putExtras(bundle);
						startActivityForResult(intent, 3);
					}
				});
	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(TakePhotoMain.soAttachList.size()>=3) {
					Toast.makeText(getApplicationContext(), "照片数量最多为3张!", Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent(DefecAdd3tActivity.this, PopDialog.class);			
				
				startActivityForResult(intent, 3);
				
			}
		});
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
			defect_audit_date = date.toString();
			//defect_audit_date_txt.setText(defect_audit_date);
			date=new StringBuffer("");
		} 
		private String FormatString(int x) {
			String s = Integer.toString(x);
			if (s.length() == 1) {
			s = "0" + s;
			} return s;
			}
	
	private void forward() {
//		if(validate()==false)return;
		String val_defect_zhegngaidanwei=defect_zhegngaidanwei_txt.getText().toString();
		String val_defect_state=defect_state_txt.getText().toString();
		//String val_defect_audit_date=defect_audit_date_txt.getText().toString();
		String val_defect_kaiyeqianhou=defect_kaiyeqianhou_txt.getText().toString();
		String val_defect_remark=defect_remark_txt.getText().toString();
	 
		valueMap.put("modifyUnit", val_defect_zhegngaidanwei);
		valueMap.put("isOpen", val_defect_kaiyeqianhou);
		valueMap.put("isOpenMap", defect_kaiyeqianhou_map);
		
		valueMap.put("spResult", val_defect_state);
		valueMap.put("spResultMap", defect_state_map);
		valueMap.put("spDate1", "");
		valueMap.put("remark", val_defect_remark);
		//valueMap.put("spDate1", val_defect_audit_date);
		//valueMap.put("photoList", TakePhotoMain.soAttachList);
		
		Intent intent = new Intent(DefecAdd3tActivity.this, DefecAddPreViewActivity.class);			
		Bundle bundle = new Bundle();
		bundle.putSerializable("valueMap", valueMap);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private boolean validate() {
		boolean flag=true;
		if(TakePhotoMain.soAttachList.size()<3) {
			Toast.makeText(getApplicationContext(), "照片数量不足!", Toast.LENGTH_SHORT).show();
			flag=false;
			return flag;
		}
//		if(!StringUtil.isBlank((String)defect_state_map.get("value"))) {//状态
//			if(StringUtil.isBlank(defect_audit_date_txt.getText().toString())) {
//				Toast.makeText(getApplicationContext(), "请填写审批时间!", Toast.LENGTH_SHORT).show();
//				flag=false;
//				return flag;
//			}
//		}
//		
		return flag;
	}
	
	private void resetPage(){
		defect_zhegngaidanwei_txt.setText("");
		//defect_state_txt.setText("");
		//defect_audit_date_txt.setText("");
		defect_kaiyeqianhou_txt.setText("系统上线前");
		defect_kaiyeqianhou_map.put("value", "01");
		defect_remark_txt.setText("");
		photoGridView.setVisibility(View.GONE);
		TakePhotoMain.soAttachList.clear();
	
	}
	
	

}
