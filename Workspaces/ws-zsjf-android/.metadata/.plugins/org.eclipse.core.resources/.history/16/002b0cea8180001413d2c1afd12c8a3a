package com.cattsoft.mos.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.util.ActivityUtil;
public class SettingActivity extends BasicActivity{
	
	private TextView tvAbout;
	private TextView tvChangeServer;
	private ImageView ivExit;
	private TextView tvUpdateCheck;
	private TextView tv_setting_modi_upload_server;
	AlertDialog.Builder builderFileuploadPath=null;
	Dialog validateDialog =null;
	
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		super.setTitleBar("设置", View.GONE,
				View.GONE, View.GONE, false);
		initView();
		registerListener();
	}
 

	@Override
	protected void initView() {
		tvAbout=(TextView)findViewById(R.id.setting_about);
		tv_setting_modi_upload_server=(TextView)findViewById(R.id.setting_modi_upload_server);
		
	}

	@Override
	protected void registerListener() {
		tvAbout.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SettingActivity.this, AboutUsActivity.class);			
				Bundle bundle = new Bundle();
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		tv_setting_modi_upload_server.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				 //为空则弹出窗口让用户输入
				builderFileuploadPath=new AlertDialog.Builder(SettingActivity.this);
				builderFileuploadPath.setTitle("上传路径") ;
				builderFileuploadPath.setIcon(android.R.drawable.ic_dialog_info)  ;
				final EditText et=new EditText(SettingActivity.this);
				et.setText(new CacheProcess().getUpdateFileUploadServer(SettingActivity.this));
				et.setHint("如192.168.1.105:8080");
				et.setRawInputType(InputType.TYPE_CLASS_NUMBER);
				builderFileuploadPath.setView(et);
				builderFileuploadPath.setCancelable(false); 
				builderFileuploadPath.setPositiveButton("确认", new OnClickListener() {
					   @Override
					   public void onClick(DialogInterface dialog, int which) {
						   new CacheProcess().updateFileUploadServer(SettingActivity.this, et.getText().toString());
					   
					   }
					  });
				
				validateDialog  =builderFileuploadPath.show();
			
			}
		});
		
		
	}
	
	private void finishActivity() {
		List<Activity> actList =ActivityUtil.getInstance().getActivityList();
		 for (Activity acti : actList) {
			 acti.finish();
		 } 
		 finish();
	}

}
