package com.cattsoft.mos.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;

public class TestActivity extends BasicActivity{

	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_add1);

		// 设置标题栏
		super.setTitleBar("设备录入",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		super.setTitleRightButtonImg(R.drawable.btn_title_scan);
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		
	}

}
