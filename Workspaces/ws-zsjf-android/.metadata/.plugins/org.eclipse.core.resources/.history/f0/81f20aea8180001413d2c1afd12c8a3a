package com.cattsoft.mos.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.EditLabelText;

/**
 *设备
 * @author xieyunchao
 *
 */
public class ModifyPwd extends BasicActivity{
	
	private EditLabelText eltInitPwd,eltNewPwd,eltNewPwdConfirm;
	private Button btnOk;
	private String response;
	private com.alibaba.fastjson.JSONObject reqJson;
	private 	String initPwd,newPwd;
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifypwd);

		// 设置标题栏
		super.setTitleBar("修改密码",View.VISIBLE,View.GONE,View.GONE,false);
		//this.setTitleRightButtonImg(R.drawable.take_photo);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	private void initData() {
		reqJson=new com.alibaba.fastjson.JSONObject();
	}
	
	@Override
	protected void initView() {
		eltInitPwd=(EditLabelText)findViewById(R.id.init_pwd);
		eltNewPwd=(EditLabelText)findViewById(R.id.newPwd);
		eltNewPwdConfirm=(EditLabelText)findViewById(R.id.newPwd_confirm);
		btnOk=(Button)findViewById(R.id.btn_ok);
		
		eltInitPwd.getValueText().setTransformationMethod(PasswordTransformationMethod.getInstance());
		eltNewPwd.getValueText().setTransformationMethod(PasswordTransformationMethod.getInstance());
		eltNewPwdConfirm.getValueText().setTransformationMethod(PasswordTransformationMethod.getInstance());
	}

	@Override
	protected void registerListener() {
		btnOk.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validate()==false)return;
				requestData();
			}
		});
		
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				com.alibaba.fastjson.JSONObject reqj=com.alibaba.fastjson.JSONObject.parseObject(response);
				String flag=reqj.getString("flag");
				if("1".equals(flag)) {
					Toast.makeText(getApplicationContext(), "密码修改成功！", Toast.LENGTH_SHORT).show();
					finish();
				}else {
					Toast.makeText(getApplicationContext(), "密码修改失败，请联系管理员！", Toast.LENGTH_SHORT).show();
				}
			}
			case 3: {

			}
				if (mProgressDialog != null)
					mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			}
		}
	};
	
	
	public void requestData() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String subUrl = "tm/ZSJFAction.do?method=modifyPwd";
						reqJson.put("oldPwd", initPwd);
						reqJson.put("newPwd", newPwd);
						response = getPostHttpContent("", subUrl,
								reqJson.toJSONString());
						if(StringUtil.isExcetionInfo(response)) {
							sendExceptionMsg(response);
							return;
						}
						
						Message m = new Message();
						m.what = 1;
						handler.sendMessage(m);
					}
				});
		mThread.start();
	}


	
	private boolean validate() {
		initPwd=eltInitPwd.getValueText().getText().toString();
		newPwd=eltNewPwd.getValueText().getText().toString();
		String newPwdConfirm=eltNewPwdConfirm.getValueText().getText().toString();
		
		if(StringUtil.isBlank(initPwd)) {
			Toast.makeText(getApplicationContext(), "请填写原密码！", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(StringUtil.isBlank(newPwd)) {
			Toast.makeText(getApplicationContext(), "请填写新密码！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(StringUtil.isBlank(newPwd)) {
			Toast.makeText(getApplicationContext(), "请确认新密码！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!newPwd.equals(newPwdConfirm)) {
			Toast.makeText(getApplicationContext(), "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}


	
		
}
