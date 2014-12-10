package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.adapter.PhotoGridAdapter;
import com.cattsoft.mos.dialog.PopDialog;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;

/**
 *设备
 * @author xieyunchao
 *
 */
public class DeviceAdd4tActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
	 
	private EditLabelText elt_device_lianfangshi2;
	private EditLabelText elt_device_desc;
	private EditLabelText elt_device_remark;
	
	private EditText edt_device_lianfangshi2;
	private EditText edt_device_desc;
	private EditText edt_device_remark;
	
	private HashMap valueMap=null;
	

	
	private GridView photoGridView=null;
	
	String path;
 
	DatePickerDialog datePickerDialog; 
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_add4);

		// 设置标题栏
		super.setTitleBar("设备录入",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
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
		
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
	}
	
	@Override
	protected void initView() {
		photoGridView = (GridView) findViewById(R.id.gridView1);
		nextBtn=(Button)findViewById(R.id.btn_next);
		resetBtn=(Button)findViewById(R.id.btn_reset);
		
		elt_device_lianfangshi2=(EditLabelText)findViewById(R.id.device_lianfangshi2);
		elt_device_desc=(EditLabelText)findViewById(R.id.device_desc);
		elt_device_remark=(EditLabelText)findViewById(R.id.device_remark);
		
		edt_device_lianfangshi2=elt_device_lianfangshi2.getValueText();
		edt_device_lianfangshi2.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(15)
        });
		edt_device_lianfangshi2.setSingleLine(true);
		edt_device_desc=elt_device_desc.getValueText();
		edt_device_desc.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(200)
        });
		edt_device_remark=elt_device_remark.getValueText();
		edt_device_remark.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(200)
        });
		//edt_device_remark.setSingleLine(true);
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
	}
	
	private void forward() {
		String lianxifangshi2=edt_device_lianfangshi2.getText().toString();
		String desc=edt_device_desc.getText().toString();
		String remark=edt_device_remark.getText().toString();
		
		valueMap.put("consPhone", lianxifangshi2);
		valueMap.put("description", desc);
		valueMap.put("remark", remark);
		
		Intent intent = new Intent(DeviceAdd4tActivity.this, DevicePreviewActivity.class);			
		Bundle bundle = new Bundle();
		bundle.putSerializable("valueMap", valueMap);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private boolean validate() {
		String lxfs2=edt_device_lianfangshi2.getText().toString();
		if(!StringUtil.isMobileNO(lxfs2)) {
			Toast.makeText(getApplicationContext(), "联系方式格式错误！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(TakePhotoMain.soAttachList.size()<3) {
			Toast.makeText(getApplicationContext(), "照片数量不足!", Toast.LENGTH_SHORT).show();
			return false; 
		}
		return true;
	}
	
	private void initPhotoGrid() {
		
		photoGridView.setVisibility(View.VISIBLE);
		System.out.println("uuuuuuuuuu");

		this.photoGridView.setAdapter(new PhotoGridAdapter(DeviceAdd4tActivity.this));
		// 点击单张图片显示
		this.photoGridView
				.setOnItemClickListener(new GridView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int postion, long arg3) {
						Intent intent = new Intent();
						intent.setClass(DeviceAdd4tActivity.this,
								ThumbnailActivity.class);
						Bundle bundle = new Bundle();
						bundle.putInt("ID", postion);
						intent.putExtras(bundle);
						startActivityForResult(intent, 3);
					}
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
	
	private void resetPage(){
		edt_device_lianfangshi2.setText("");
		edt_device_desc.setText("");
		edt_device_remark.setText("");
		photoGridView.setVisibility(View.GONE);
		TakePhotoMain.soAttachList.clear();
	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(TakePhotoMain.soAttachList.size()>=3) {
					Toast.makeText(getApplicationContext(), "照片数量最多为3张!", Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent(DeviceAdd4tActivity.this, PopDialog.class);			
				
				startActivityForResult(intent, 3);
				
			}
		});
	}
		
}
