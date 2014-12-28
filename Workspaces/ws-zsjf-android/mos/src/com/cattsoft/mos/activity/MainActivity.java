package com.cattsoft.mos.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;

public class MainActivity extends BasicActivity{

	private Button home_zdgz=null;
	private Button home_rtb=null;
	private Button home_ytb=null;
	private Button home_gcjs=null;
	private Button home_help=null;
	private long mExitTime = 0l;
	private String noticeResult="";
	
	private UpdateManager manager = new UpdateManager(MainActivity.this);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		super.setTitleBar("掌上通报",View.GONE,View.GONE,View.INVISIBLE,false);
		checkThread.start();
		registerListener();
		requesNotice();
	}
	
	@Override
	protected void initView() {
		home_zdgz=(Button)findViewById(R.id.home_zdgz);
		home_rtb=(Button)findViewById(R.id.home_rtb);
		home_ytb=(Button)findViewById(R.id.home_ytb);
		home_gcjs=(Button)findViewById(R.id.home_jsgcxx);
		home_help=(Button)findViewById(R.id.home_help);
	}
	

	private Thread checkThread = new Thread(new Runnable() {// 启动新的线程，
				@Override
				public void run() {
					Message msg = new Message();
					try {

						// 检查软件更新
						if (manager.isUpdate() && !mosApp.isCancleUpdate()) {
							msg.what = 3;
							handler.sendMessage(msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	
	public void requesNotice() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String subUrl = "tm/ZSJFAction.do?method=showNotice";
						noticeResult = getPostHttpContent("", subUrl,
								"{}");
						if (handleError(noticeResult) == true)
							return;
						Message m = new Message();
						m.what = 8;
						handler.sendMessage(m);
					}
				});
		mThread.start();
	}
	
	// 用于接收线程发送的消息
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
		  
			case 3: {
				showUpdateDialog();
			}
			case 8:{
				showNoticeDialog();
			}
				break;
		
			}
		}
	};
	
	
	/**
	 * 显示通知对话框
	 */
	private void showNoticeDialog() {
		com.alibaba.fastjson.JSONObject j=com.alibaba.fastjson.JSONObject.parseObject(noticeResult);
		String noticeId=j.getString("noticeId");
		if(StringUtil.isBlank(noticeId))return ;
		// 构造对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle(j.getString("title"));
		builder.setMessage(j.getString("content"));
		builder.setNegativeButton("我知道了",
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.setOnKeyListener(keylistener);
		Dialog noticeDialog = builder.create();
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.show();
	}

	
	
	/**
	 * 显示软件更新对话框
	 */
	private void showUpdateDialog() {
		// 构造对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle(R.string.soft_update_title);
		builder.setMessage(manager.mosVersionSVO.getVersionDesc());
		// 更新
		builder.setPositiveButton(R.string.soft_update_updatebtn,
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载对话框
						manager.showDownloadDialog();
					}
				});
		// 稍后更新
		builder.setNegativeButton(R.string.soft_update_later,
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (manager.mosVersionSVO.getIsForce().equals("Y")) {
							CacheProcess cacheProcess = new CacheProcess();
							cacheProcess.clearCache(mosApp);
							finish();
						} else {
							mosApp.setCancleUpdate(true);
							dialog.dismiss();
						}
					}
				});
		builder.setOnKeyListener(keylistener);
		Dialog noticeDialog = builder.create();
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.show();
	}

	OnKeyListener keylistener = new DialogInterface.OnKeyListener(){
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
            {
             return true;
            }
            else
            {
             return false;
            }
        }
    } ;
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-mExitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出！", Toast.LENGTH_SHORT).show();                                
	            mExitTime = System.currentTimeMillis();   
	        } else {
	        	ActivityUtil.exit();
	            mosApp.exit();
	            System.exit(0);
				this.finish();
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	    
	     
	}

	@Override
	protected void registerListener() {
		home_zdgz.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ZdgzMainActivity.class);			
				startActivity(intent);
			}
		});
		home_rtb.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, DayReportActivity.class);			
				startActivity(intent);
			}
		});
		
		home_ytb.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MonthReportActivity.class);			
				startActivity(intent);
			}
		});
		home_gcjs.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, GcjsMainActivity.class);			
				startActivity(intent);
			}
		});
		home_help.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ZsjfHelp.class);			
				startActivity(intent);
			}
		});
	}
	
	
	
	

}
