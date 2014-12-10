package com.cattsoft.mos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;

/**
 *掌上精分帮助
 * @author xieyunchao
 *
 */
public class ZsjfHelp extends BasicActivity{
	
	private TextView tvabout,tvModifyPwd,tvReportDesc,tvLoginOut;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zsjfhelp);

		// 设置标题栏
		super.setTitleBar("帮助",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);
		

	}
	
	private void initData() {
	}
	
	@Override
	protected void initView() {
		tvabout=(TextView)findViewById(R.id.tv_about);
		tvModifyPwd=(TextView)findViewById(R.id.tv_modify_pwd);
		tvReportDesc=(TextView)findViewById(R.id.tv_report_desc);
		tvLoginOut=(TextView)findViewById(R.id.tv_login_out);
	}

	@Override
	protected void registerListener() {
		tvabout.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ZsjfHelp.this, AboutUsActivity.class);			
				startActivity(intent);
			}
		});
		
		tvModifyPwd.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ZsjfHelp.this, ModifyPwd.class);			
				startActivity(intent);
			}
		});
		tvReportDesc.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ZsjfHelp.this, ReportDesc.class);			
				startActivity(intent);
			}
		});
		
		tvLoginOut.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				mosApp.setReLogin(true);
				Intent intent = new Intent(ZsjfHelp.this, LoginActivity.class);			
				startActivity(intent);
			}
		});
	}
	
	private void forward() {
		
	}
	


}
