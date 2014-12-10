package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.JsonUtil;
import com.cattsoft.mos.util.StringUtil;

public class LoginActivity extends BasicActivity {
	private SharedPreferences sharedPreferences;
	private Button loginBtn;
	private EditText etUserName;
	private EditText etPassword;
	private CheckBox rememberPassword;
	private CheckBox autoLogin;
	private boolean isRememberPassword;
	private boolean isAutoLogin;
	private boolean isFirstRun = true;
	private String userName;
	private String password;
	private boolean isCiphertext = false; 
	private CacheProcess cacheProcess;
	Bitmap bmp=null;
	  Bitmap bitmap=null;
	  com.alibaba.fastjson.JSONObject j=null;
	private TextView tvJierudian=null;
	  

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		cacheProcess = new CacheProcess();
		super.setTitleBar("掌上通报",View.GONE,View.GONE,View.INVISIBLE,false);
		loadLoginInfo();
		if (isAutoLogin && !mosApp.isReLogin()) {
			loginInTread();
		}
		registerListener();
	}

	@Override
	protected void onDestroy() {
		closeProcessDialog();
		super.onDestroy();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (((keyCode == KeyEvent.KEYCODE_BACK) || (keyCode == KeyEvent.KEYCODE_HOME))
				&& event.getRepeatCount() == 0) {
			dialogExit();
		}
		return false;
	}

	/**
	 * 登录验证并处理缓存
	 */
	public void login() {
		String response="";
		mosApp.setReLogin(false);
		JSONObject responseJsonObject = null;
		if(!isCiphertext) {
			userName = etUserName.getText().toString();
			password = etPassword.getText().toString();
		}
		if(StringUtil.isBlank(userName)&&StringUtil.isBlank(password)) {
			LoginActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS("用户名和密码不能为空！"));
			return;
		}else if(StringUtil.isBlank(userName)) {
			LoginActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS("用户名不能为空！"));
			return;
		}else if(StringUtil.isBlank(password)) {
			LoginActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS("密码不能为空！"));
			return;
		}
//		if(userName.length()<2) {
//			LoginActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS("用户名长度不足！"));
//			return;
//		}
//		if(password.length()<2) {
//			LoginActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS("密码长度不足！"));
//			return;
//		}
		if(!validate(userName)) {
			LoginActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS("用户名只能为字母、数字或下划线！"));
			return;
		}
		if(!validate(password)) {
			LoginActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS("密码名只能为字母、数字或下划线！"));
			return;
		}
		
		
		
		try {
			JSONObject requestJsonObject = new JSONObject();
			requestJsonObject.put("userName", userName);
			requestJsonObject.put("password", password);
			//requestJsonObject.put("isCiphertext", isCiphertext);
			//获取响应的结果信息
			String url=Constant.loginServerMethod;
			response = getPostHttpContent("",url, requestJsonObject.toString());
			if (StringUtil.isExcetionInfo(response)) {
				response = StringUtil.getAppException4MOS(response);
				LoginActivity.this.sendExceptionMsg(response);
				return;
			}
			
			
			responseJsonObject = new JSONObject(response);
			if (responseJsonObject.has("resultCode") && "0".equals(responseJsonObject.getString("resultCode"))) {
				String errinfo=responseJsonObject.getString("resultInfo");
				String err=StringUtil.getAppException4MOS(errinfo);
				LoginActivity.this.sendExceptionMsg(err);
				return;
			}else {
				sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
				   // userName = mosApp.getSysUserExtendedMVO().getSysUserSVO().getSysUserName();
				    //password = mosApp.getSysUserExtendedMVO().getSysUserSVO().getPassword();
					Editor editor = sharedPreferences.edit();
					editor.putString("loginName", userName);
					
					//真实姓名
					String realName=JsonUtil.getString(responseJsonObject,"userName");
					editor.putString("realUserName", realName);
					editor.putString("cacheInfo", response);
					
					editor.commit();
					
				//cacheProcess.initCacheInSharedPreferences(this, responseJsonObject);

			    // 如果是第一次登录，则改变状态为false
					
				if (isFirstRun) {
					isFirstRun = false;
					if(hasFunction("重点业务发展日报（按客户群）")) {
						Intent intent = new Intent(LoginActivity.this,
								Zdywrb4khq.class);
						intent.putExtra("whickReportFlag", "zdgz");
						intent.putExtra("firstPage", "1");
						startActivity(intent);
					}else {
						startActivity(new Intent(LoginActivity.this, MainActivity.class));
					}
				} else {
					if(hasFunction("重点业务发展日报（按客户群）")) {
						Intent intent = new Intent(LoginActivity.this,
								Zdywrb4khq.class);
						intent.putExtra("whickReportFlag", "zdgz");
						startActivity(intent);
					}else {
						startActivity(new Intent(LoginActivity.this, MainActivity.class));
					}
					
				}
				if (isRememberPassword) {
					saveLoginInfo();
				} else {
					cleanLoginInfo();
				}
//				startService(locationService);
				finish();
			}
			Log.d("【Json】", response);
			
			
			 
		} catch (JSONException e) {
			String err = StringUtil.getAppException4MOS("解析JSON出错！");
			LoginActivity.this.sendExceptionMsg(err);
			return;
		}
	}
	
	private boolean hasFunction(String funcName) {
		List funcList=getFuncNodeListByUser();
		for(int i=0;i<funcList.size();i++) {
			java.util.HashMap hm=(java.util.HashMap)funcList.get(i);
			String afuncName=(String)hm.get("funcNodeName");
			if(funcName.equals(afuncName))return true;
		}
		return false;
	}

	private List getFuncNodeListByUser() {
		List funcNodeList = new ArrayList();
		String cacheInfo = new CacheProcess().getCacheValueInSharedPreferences(
				this, "cacheInfo");
		com.alibaba.fastjson.JSONObject j = com.alibaba.fastjson.JSONObject
				.parseObject(cacheInfo);

		com.alibaba.fastjson.JSONObject userCache = j
				.getJSONObject("suveJsonObject");
		com.alibaba.fastjson.JSONArray funcNodes = userCache
				.getJSONArray("sysUserFuncTree");
		if (funcNodes != null && funcNodes.size() > 0) {
			for (int i = 0; i < funcNodes.size(); i++) {
				com.alibaba.fastjson.JSONObject funcTree = funcNodes
						.getJSONObject(i);
				com.alibaba.fastjson.JSONArray jarr = funcTree
						.getJSONArray("userFuncNodeList");
				List afuncNodeList = com.alibaba.fastjson.JSON.parseArray(
						jarr.toJSONString(), HashMap.class);
				funcNodeList.addAll(afuncNodeList);
			}
		}
		return funcNodeList;
	}
	/**
	 * 20130521 xieyunchao，被cacheProcess.initCacheInSharedPreferences方法覆盖
	 * 保存推送所需要的信息
	private void savePushInfo() {
		sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = sharedPreferences.edit();
		edit.putString("sysUserId", mosApp.getSysUserExtendedMVO().getSysUserSVO().getSysUserId());
		edit.putString("sysPassword", mosApp.getSysUserExtendedMVO().getSysUserSVO().getPassword());
		edit.commit();
	}*/

	/**
	 * 加载用户名密码等登录信息
	 */
	public void loadLoginInfo() {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		userName = sharedPreferences.getString("userName", "");
		password = sharedPreferences.getString("password", "");
		System.out.println("passwordpasswordpassword="+password);
		isRememberPassword = sharedPreferences.getBoolean("isRememberPassword",	false);
		isAutoLogin = sharedPreferences.getBoolean("isAutoLogin", false);
		isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
		etUserName.setText(userName);
		if(!StringUtil.isBlank(password)) {
			isCiphertext = true;
			etPassword.setText("*******");
		}
		rememberPassword.setChecked(isRememberPassword);
		autoLogin.setChecked(isAutoLogin);
	}
	/**
	 * 保存用户名密码等登录信息
	 */
	public void saveLoginInfo() {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	   // userName = mosApp.getSysUserExtendedMVO().getSysUserSVO().getSysUserName();
	    //password = mosApp.getSysUserExtendedMVO().getSysUserSVO().getPassword();
		Editor editor = sharedPreferences.edit();
		editor.putString("userName", userName);
		editor.putString("password", password);
		editor.putBoolean("isRememberPassword", isRememberPassword);
		editor.putBoolean("isAutoLogin", isAutoLogin);
		editor.commit();
	}

	public void cleanLoginInfo() {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
//		editor.putString("userName", "");
//		editor.putString("password", "");
		editor.putBoolean("isRememberPassword", false);
		editor.putBoolean("isAutoLogin", false);
		editor.putBoolean("isFirstRun", isFirstRun);
		editor.commit();
	}

	@Override
	protected void initView() {
		loginBtn = (Button) findViewById(R.id.login_btn_login);
		etUserName = (EditText) findViewById(R.id.login_edit_username);
		etPassword = (EditText) findViewById(R.id.login_edit_password);
		rememberPassword = (CheckBox) findViewById(R.id.login_remember_password);
		autoLogin = (CheckBox) findViewById(R.id.login_auto_login);
//		textBtn=(Button)findViewById(R.id.login_btn_login1);
//		sendBtn=(Button)findViewById(R.id.login_btn_login2);
//		parmBtn=(Button)findViewById(R.id.login_btn_login3);
	}
   
	@Override
	protected void registerListener() {
		loginBtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				loginInTread();
			}
		});
		


		rememberPassword.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							isRememberPassword = true;
						} else {
							isRememberPassword = false;
							autoLogin.setChecked(false);
						}
					}
				});

		autoLogin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					isAutoLogin = true;
					rememberPassword.setChecked(true);
				} else {
					isAutoLogin = false;
				}
			}
		});
		
		etPassword.setOnClickListener(new EditText.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isCiphertext) {
					isCiphertext = false;
					etPassword.setText("");
				}else {
					etPassword.selectAll();
				}
			}
		});
		
		etPassword.setOnTouchListener(new EditText.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etPassword.requestFocus();
				return false;
			}
		});
		
		etUserName.setOnClickListener(new EditText.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isCiphertext) {
					isCiphertext = false;
					etUserName.setText("");
					etPassword.setText("");
				}else {
					etUserName.selectAll();
				}
			}
		});
		
		etUserName.setOnTouchListener(new EditText.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etUserName.requestFocus();
				return false;
			}
		});
		 
	}
	
	private boolean validate(String str) {
		Pattern p = Pattern.compile("^\\w+$");
		Matcher m = p.matcher(str);   
		return m.matches();  
	}

	/**
	 * 在子线程与远端服务器交互，验证登录
	 */
	private void loginInTread() {
		showProcessDialog(false);
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						login();
					}
				});
		mThread.start();
	}
}
