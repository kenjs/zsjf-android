package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.util.ActivityUtil;

/**
 * 工程建设信息
 * 
 * @author xieyunchao
 * 
 */
public class GcjsMainActivity extends BasicActivity {
	private Button btn1;
	private Button btn2;

	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gcjsmain);

		// 设置标题栏
		super.setTitleBar("工程建设信息", View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
				false);

		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}

	private void initData() {
		
	}

	@Override
	protected void initView() {
		btn1=(Button)findViewById(R.id.btn_gcjg);
		btn2=(Button)findViewById(R.id.btn_gczt);
		btn1.setTag("1");
		btn2.setTag("2");
	}
	
	
	
	
	private class BtnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			String tag=(String)arg0.getTag();
			if(tag.equals("1")) {
				Intent intent = new Intent(GcjsMainActivity.this,
						Gcjgxxtj.class);
				startActivity(intent);
			}else if(tag.equals("2")){
				Intent intent = new Intent(GcjsMainActivity.this,
						Gcztxxtj.class);
				startActivity(intent);
			}
		}
		
	}

	@Override
	protected void registerListener() {
		BtnClickListener listener=new BtnClickListener();
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
	}

}
