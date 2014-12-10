package com.cattsoft.mos.activity;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.dataprocess.MosApp;
import com.cattsoft.mos.exception.SysException;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.EncryptUtil;
import com.cattsoft.mos.util.StringUtil;

public abstract class BasicListActivity extends ListActivity {
	private String encryptUsable;
	private SharedPreferences sharedPreferences;
	private ImageButton titleLeftButton;// 标题栏左边按钮，默认图标为返回，背景为透明
	private TextView titleTextView;// 标题栏居中标题
	private ImageView titleDownArrow;// 标题右侧小箭头，默认不可见
	private RelativeLayout titleMiddleButton;// 中部可点击区域，包括标题以及小箭头，默认不可点击
	private ImageButton titleRightButton;// 标题栏右边按钮，默认图标为刷新，背景为透明
	public MosApp mosApp;//缓存
	public Dialog mProgressDialog;//进度条
	public boolean isCancel=false;//判断是按了返回键
	public String exceptionDesc=null;
	public  CacheProcess cache;
	
	public ImageButton getTitleLeftButton() {
		return titleLeftButton;
	}

	public TextView getTitleTextView() {
		return titleTextView;
	}

	public ImageView getTitleDownArrow() {
		return titleDownArrow;
	}

	public RelativeLayout getTitleMiddleButton() {
		return titleMiddleButton;
	}

	public ImageButton getTitleRightButton() {
		return titleRightButton;
	}
	
	/**
	 * 退出提示框	
	 */
		public void dialogExit() {
			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage("确定要退出吗?");
			builder.setTitle("提示");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					mosApp.exit();
					finish();
				}
			});

			builder.setNegativeButton("取消",
					new android.content.DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});

			builder.create().show();
		}

	/**
	 * 设置标题栏
	 * 
	 * @param titleText
	 *            标题文字
	 */
	public void setTitleBar(String titleText) {
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);// 设置标题栏布局文件为title_model
		titleLeftButton = (ImageButton) findViewById(R.id.titlebar_img_btn_left);
		titleTextView = (TextView) findViewById(R.id.titlebar_text);
		titleDownArrow = (ImageView) findViewById(R.id.titlebar_down_arrow);
		titleMiddleButton = (RelativeLayout) findViewById(R.id.titlebar_title);
		titleRightButton = (ImageButton) findViewById(R.id.titlebar_img_btn_right);
		setTitleText(titleText);
		leftButtonOnClick();
		middleButtonOnClick();
		rightButtonOnClick();
	}

	/**
	 * 设置标题栏
	 * 
	 * @param titleText
	 *            标题文字
	 * @param leftButtonVisibility
	 *            左侧按钮可见状态
	 * @param rightButtonVisibility
	 *            右侧按钮可见状态
	 * @param downArrowVisibility
	 *            小箭头可见状态
	 * @param middleButtonClickable
	 *            中部标题区域可点击状态
	 */
	public void setTitleBar(String titleText, int leftButtonVisibility,
			int rightButtonVisibility, int downArrowVisibility,
			boolean middleButtonClickable) {
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);// 设置标题栏布局文件为title_model
		titleLeftButton = (ImageButton) findViewById(R.id.titlebar_img_btn_left);
		titleTextView = (TextView) findViewById(R.id.titlebar_text);
		titleDownArrow = (ImageView) findViewById(R.id.titlebar_down_arrow);
		titleMiddleButton = (RelativeLayout) findViewById(R.id.titlebar_title);
		titleRightButton = (ImageButton) findViewById(R.id.titlebar_img_btn_right);
		setTitleText(titleText);
		setTitleLeftButtonVisibility(leftButtonVisibility);
		setTitleRightButtonVisibility(rightButtonVisibility);
		setTitleDownArrowVisibility(downArrowVisibility);
		leftButtonOnClick();
		middleButtonOnClick();
		rightButtonOnClick();
		setTitleMiddleButtonClickable(middleButtonClickable);
	}
	
	public String getHttpContent(Activity activity, String url, String parameter) {
		MosApp app = (MosApp) activity.getApplication();
		HttpClient client = app.getHttpClient();

		String serverUrl = Constant.ServerURL + url;
		if (!StringUtil.isBlank(parameter)) {
			serverUrl = serverUrl + "&parameter=" + parameter;
		}
		HttpGet get = new HttpGet(serverUrl);
		get.addHeader("Accept", "text/json");
		// 获取响应的结果
		HttpResponse response = null;
		String result = null;
		try {
			response = client.execute(get);
			// 获取HttpEntity
			HttpEntity entity = response.getEntity();
			// 获取响应的结果信息
			byte[] resultByteType = EntityUtils.toByteArray(entity);
			// 解压返回的字符串
			result = StringUtil.unCompress(resultByteType);
			if("Y".equals(encryptUsable)){
			result = EncryptUtil.decryptThreeDESECB(result);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (IOException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (ParseException e) {
			result=StringUtil.getAppException4MOS("返回结果解析错误！");
		}catch (SysException e)  {
			result=StringUtil.getAppException4MOS("解压或加密出现异常！");
		}

		return result;
	}
	
	/**
	 * 以post方式提交数据
	 * @param url 服务器的地址，直接写Action的地址，如tm/WoHandleAction.do?method=query
	 * @param parameter 拼接好的json字符串
	 * @return 服务端json字符串，当服务器端返回AppException或SysException时，得到的字符串是一个html文档
	 * @throws Exception 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getPostHttpContent(String url,String parameter)  {
		MosApp  app= (MosApp)this.getApplication();   
		HttpClient client = app.getHttpClient();   
		
		String serverUrl = Constant.ServerURL + url;
		HttpPost post = new HttpPost(serverUrl);
		post.addHeader("Content-Type", "application/json");
		String result =null;
		String parameterEncrypted = null;
		try {
			if("Y".equals(encryptUsable)){
			//数据加密（请求数据不进行压缩）
			parameterEncrypted = EncryptUtil.encryptThreeDESECB(parameter);
			}else{
				parameterEncrypted = parameter;
			}
			StringEntity resEntity = new StringEntity(parameterEncrypted, "UTF-8");
			post.setEntity(resEntity);
			// 获取响应的结果
			HttpResponse response = client.execute(post);
			// 获取HttpEntity
			HttpEntity respEntity = response.getEntity();
			// 获取响应的结果信息
			byte[] resultCompressed = EntityUtils.toByteArray(respEntity);
			// 解压返回的字符串
			result = StringUtil.unCompress(resultCompressed);
			if("Y".equals(encryptUsable)){
			result = EncryptUtil.decryptThreeDESECB(result);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (IOException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		}catch (SysException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("解密过程出错！");
		}
		return result;
	}
	
	
	public void sendExceptionMsg(String res){
		exceptionDesc=StringUtil.getExceptionDesc(res);
		Message errMsg=new Message();
		errMsg.what=-1;
		baseHandler.sendMessage(errMsg);
	}
	
	public Handler baseHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case -1: {//处理异常信息
				Toast.makeText(getApplicationContext(), exceptionDesc,
						Toast.LENGTH_SHORT).show();
				dealWithException();
				closeProcessDialog();
			}
			//关闭进度条
			case -2:{
				
			}
				break;
			}
		}
	};
	
	/**
	 * 关闭对话框
	 */
	protected void closeProcessDialog() {
		if(mProgressDialog!=null) {
			mProgressDialog.dismiss();
		}
	}
	
	/**
	 * 当出现异常消息时，同时执行的方法
	 */
	protected void dealWithException() {
		
	}

	/**
	 * 设置左边按钮是否可见
	 * 
	 * @param visibility
	 *            可见状态
	 */
	public void setTitleLeftButtonVisibility(int visibility) {
		titleLeftButton.setVisibility(visibility);
	}

	/**
	 * 设置右边按钮是否可见
	 * 
	 * @param visibility
	 *            可见状态
	 */
	public void setTitleRightButtonVisibility(int visibility) {
		titleRightButton.setVisibility(visibility);
	}

	/**
	 * 设置小箭头是否可见
	 * 
	 * @param visibility
	 *            可见状态
	 */
	public void setTitleDownArrowVisibility(int visibility) {
		titleDownArrow.setVisibility(visibility);
	}

	/**
	 * 设置左侧按钮图标
	 * 
	 * @param leftButtonImg
	 *            图标资源id
	 */
	public void setTitleLeftButtonImg(int leftButtonImg) {
		titleLeftButton.setImageResource(leftButtonImg);
	}

	/**
	 * 设置右侧按钮图标
	 * 
	 * @param rightButtonImg
	 *            图标资源id
	 */
	public void setTitleRightButtonImg(int rightButtonImg) {
		titleRightButton.setImageResource(rightButtonImg);
	}

	/**
	 * 设置标题文本
	 * 
	 * @param titleText
	 *            标题文本
	 */
	public void setTitleText(String titleText) {
		titleTextView.setText(titleText);
	}

	/**
	 * 设置中部区域可点击状态
	 * 
	 * @param clickable
	 *            可点击状态
	 */
	public void setTitleMiddleButtonClickable(boolean clickable) {
		titleMiddleButton.setClickable(clickable);
	}

	/**
	 * 左侧按钮点击事件监听 默认返回主界面
	 */
	public void leftButtonOnClick() {
		titleLeftButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	/**
	 * 初始化组件。用于在Activity装载时初始化相关组件和变量，以减少oncreate方法的庞大和冗余。
	 */
	protected abstract void initView();
	
	/**
	 * 用于注册组件的监听事件。
	 */
	protected abstract void registerListener();
	

	/**
	 * 中部标题区域点击事件监听 默认切换工区
	 */
	public void middleButtonOnClick() {
		titleMiddleButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.cattsoft.mos.CHANGE_WORK_AREA");// CHANGE_WORK_AREA为自定义action，在Manifest中配置
				startActivity(intent);
			}
		});
	}

	/**
	 * 右侧按钮点击事件监听
	 */
	public void rightButtonOnClick() {
		titleRightButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		encryptUsable = sharedPreferences.getString("encryptUsable", "");
		mosApp=(MosApp) getApplication();
	}

	/**
	 * 重写onRestart()方法，当isExit值为true时退出整个程序
	 */
	@Override
	public void onRestart() {
		if (mosApp.isExit()) {
			super.onRestart();
			finish();
		} else {
			super.onRestart();
		}
	}
	
	/**
	 * 重写onResume()方法，获得application
	 */
	@Override
	public void onResume() {
		super.onResume();
//		mosApp=(MosApp) getApplication();
//		if(mosApp.isMissing()) {
//			ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//			ComponentName componentName = activityManager.getRunningTasks(1).get(0).topActivity;
//			String currentActivity=componentName.getClassName().substring(26);
//			if(!currentActivity.equals("LoginActivity")) {
//				Intent intent = new Intent("com.cattsoft.mos.LOGIN");// LOGIN为自定义action，在Manifest中配置
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 清除栈中FuncNavActivity之上的所有Activity。FuncNavActivity也同样会finish(),并重新创建
//				startActivity(intent);
//				Toast.makeText(getApplicationContext(), "登录超时，请重新登录！", Toast.LENGTH_SHORT).show();
//			}
//		}
	}

	public void showProcessDialog(final boolean finishActivity) {
		mProgressDialog = new Dialog(this,
				R.style.process_dialog);//创建自定义进度条
		mProgressDialog.setContentView(R.layout.progress_dialog);//自定义进度条的内容
		mProgressDialog.setCancelable(true);
		mProgressDialog
				.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {
					public void onCancel(DialogInterface dialog) {
						isCancel=true;
						if(finishActivity)finish();
					}
				});
		mProgressDialog.show();//显示进度条
	}
	
	@Override
	public void onBackPressed(){
		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		ComponentName componentName = activityManager.getRunningTasks(1).get(0).topActivity;
		String preActivity=componentName.getClassName().substring(26);
		if(preActivity.equals("AllTaskListActivity")||preActivity.equals("WoQueryActivity")||preActivity.equals("ProcessQueryActivity")||preActivity.equals("StatisticalReportActivity")||preActivity.equals("KnowledgeBaseMainActivity")||preActivity.equals("BulletinListActivity")||preActivity.equals("SetUpActivity")) {
			Intent intent = new Intent("com.cattsoft.mos.HOME");// HOME为自定义action，在Manifest中配置
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 清除栈中FuncNavActivity之上的所有Activity。FuncNavActivity也同样会finish(),并重新创建
			startActivity(intent);
		}else {
			super.onBackPressed();
		}
	}
	
	public  CacheProcess getCacheProcess() {
		if(cache==null) {
			cache=new  CacheProcess();
		}
		return cache;
	}
}