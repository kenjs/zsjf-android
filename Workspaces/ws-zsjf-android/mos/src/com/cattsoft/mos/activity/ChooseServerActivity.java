package com.cattsoft.mos.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.StringUtil;

public class ChooseServerActivity extends BasicActivity {
	private SharedPreferences sharedPreferences;
  
	private EditText edt_serverName;
	private Button okButton;
	
	private CacheProcess cacheProcess; 
	String response="";
	
	private String fromFlag=""; //是第一次使用进入该界面还是在设置中修改
	  

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
		initView(); 
  
		registerListener();
	}

 
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				doAfterSave(msg);
			}
			if (mProgressDialog != null)
				mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			}
		}
	};
 

	/**
	 * 登录验证并处理缓存
	 */
	public void submit() {

	}

 
	@Override
	protected void registerListener() {
		okButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(validate()==false)return;
				showProcessDialog(false);
				Thread mThread = new Thread(new Runnable() {// 启动新的线程，
							@Override
							public void run() {
								String methodName=Constant.chooseServerMethod;
								response = getPostHttpContent(edt_serverName.getText().toString(),methodName, "dd");
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

		});
	}

	public void doAfterSave(Message msg) {
		Bundle b=msg.getData();
		String content=b.getString("content");
		if(StringUtil.isBlank(content)) {
			Toast.makeText(ChooseServerActivity.this, "服务器端没有响应！", Toast.LENGTH_SHORT).show();
		}else {
			
			if("1".equals(response)) {
				//Toast.makeText(ChooseServerActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
				sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
					Editor editor = sharedPreferences.edit();
					editor.putString("isFirstLogin", "NO");
					editor.putString("serverURL", edt_serverName.getText().toString());
					editor.commit();
				if(StringUtil.isBlank(fromFlag)) {
					//startActivity(new Intent(ChooseServerActivity.this, HomeMainActivity.class));
					finish();
				}else {
					//Toast.makeText(ChooseServerActivity.this, "修改成功！",Toast.LENGTH_SHORT).show();
					finish();
				}
				
			}else {
				//startActivity(new Intent(ChooseServerActivity.this, HomeMainActivity.class));
				//Toast.makeText(ChooseServerActivity.this, "接入点异常，请确认您输入的接入点是否正确！",Toast.LENGTH_SHORT).show();
			}
		}
	
	}
	

	@Override
	protected void initView() {
		super.setTitleBar("选择接入点", View.GONE,
				View.GONE, View.GONE, false);
		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		if(b!=null) {
			fromFlag=b.getString("fromFlag");
		}
		edt_serverName=(EditText)findViewById(R.id.edt_choose_server);
		edt_serverName.setText("https://");
		CharSequence text = edt_serverName.getText();
		if (text instanceof Spannable) {
			     Spannable spanText = (Spannable)text;
			     Selection.setSelection(spanText, text.length());
			 }
		okButton=(Button)findViewById(R.id.btn_choose_server_ok);
	}
	
	private boolean validate() {
		if(StringUtil.isBlank(edt_serverName.getText().toString().trim())) {
			Toast.makeText(ChooseServerActivity.this, "请输入接入点地址！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
}
