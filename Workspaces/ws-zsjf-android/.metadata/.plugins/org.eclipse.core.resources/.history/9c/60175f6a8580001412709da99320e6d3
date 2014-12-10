package com.cattsoft.mos.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.service.BackgroundUploadService;
import com.cattsoft.mos.util.DateUtil;
import com.cattsoft.mos.util.JsonUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.vo.mvo.Task;

public class RecorderSaveActivity extends BasicActivity {
	
	private Button btnSave=null;
	private TextView tvTimeLong=null;
	private TextView tvFielSize=null;
	private TextView txtFileName=null;
	private String fileSize="";
	private String timeLong="";
	private String fileType="";//标识是音频文件还是视频文件
	private String customeName="";
	private String remarked="";
	private String fileName="";
	
	private EditText etCustomName;
	private EditText edRemark;
	
	private String response;
	private  HashMap valueMap;
	private BackgroundUploadService.BackgroundUploadBinder binder;
	String recorderInfos=null;
	List fileList=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recorder_save);
		super.setTitleBar("保存文件",View.VISIBLE,View.GONE,View.INVISIBLE,false);
		//this.setTitleRightButtonImg(R.drawable.ok_in_title);
		initData();
		
		initView();
		
		connectService();
		registerListener();
	}


	private void connectService() {
		ServiceConnection connection = new ServiceConnection() {
			public void onServiceDisconnected(ComponentName name) {
			}
			public void onServiceConnected(ComponentName name, IBinder service) {
				binder = (BackgroundUploadService.BackgroundUploadBinder)service;
			}
		};
		Intent myIntent = new Intent(this,BackgroundUploadService.class);
        bindService(myIntent,connection,Context.BIND_AUTO_CREATE);
	}

	
	
	private void initData() {
		try {
			recorderInfos = new CacheProcess().getRecorderList(this);
			if(StringUtil.isBlank(recorderInfos))recorderInfos="[]";
		} catch (org.json.JSONException e) {
			e.printStackTrace();
		} 
		fileList=com.alibaba.fastjson.JSON.parseArray(recorderInfos, java.util.HashMap.class);
		
	}
	
	@Override
	protected void initView() {
		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		valueMap=(HashMap)b.getSerializable("valueMap");
		
		timeLong=(String)valueMap.get("timeLong");
		fileSize=(String)valueMap.get("fileSize");
		fileType=(String)valueMap.get("fileType");
		customeName=(String)valueMap.get("customName");
		remarked=(String)valueMap.get("remarked");
		fileName=(String)valueMap.get("fileName");
		
		
		tvTimeLong=((com.cattsoft.mos.view.EditLabelText)findViewById(R.id.recorder_file_time_long)).getValueText();
		tvFielSize=((com.cattsoft.mos.view.EditLabelText)findViewById(R.id.recorder_file_size)).getValueText();
		txtFileName=((com.cattsoft.mos.view.EditLabelText)findViewById(R.id.recorder_filename)).getValueText();
		
		edRemark=((com.cattsoft.mos.view.EditLabelText)findViewById(R.id.recorder_remark)).getValueText();
		etCustomName=((com.cattsoft.mos.view.EditLabelText)findViewById(R.id.recorder_custom_name)).getValueText();
		
		tvTimeLong.setText(timeLong);
		tvFielSize.setText((int)(Integer.parseInt(fileSize)/1000)+  "K");
		btnSave=(Button)findViewById(R.id.btn_save);
		etCustomName.setText(customeName);
		edRemark.setText(remarked);
		txtFileName.setText(fileName);
		
	}

	private void  renameRecordFile(){
		String path="";
		String fullPath="";
		String extendName="";
		if("A".equals(fileType)) {
			extendName=".wav";
			path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+RecorderTrackActivity.AUDIO_RECORDER_FOLDER;
			fullPath=path+"/temp"+extendName;
		}else {
			extendName=".3gp";
			path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+MCamera.fileSavePath;
			fullPath=path+"/temp"+extendName;
		}
			
		File f=new File( fullPath);
		if(f.exists()){
			f.renameTo(new File( path+"/"+txtFileName.getText()+extendName));
		}
	}
	
	private Map createRecordMap(String fileType,String optFlag) {
		Map m=new HashMap();
		m.put("fileSize", fileSize);
		m.put("fileName", txtFileName.getText());
		m.put("timeLong", timeLong);
		m.put("customName", etCustomName.getText().toString());
		m.put("remarked", edRemark.getText().toString());
		m.put("createTime", DateUtil.getCurrentTime());
		m.put("uploaded", "未上传");//未上传
		if("A".equals(optFlag)) {//如果是添加
			m.put("fileId", UUID.randomUUID().toString());
		}else {//如果是更新，则根据fileId找出HashMap并且重新赋值
			String fileId=(String)valueMap.get("fileId");
			for(int i=0;i<fileList.size();i++) {
				Map aMap=(HashMap)fileList.get(i);
				String afileId=(String)aMap.get("fileId");
				if(afileId.equals(fileId)) {
					fileList.remove(aMap);
				}
			}
			
			m.put("fileId", fileId);
		}
		
		if("A".equals(fileType)) {
			m.put("fileType", "音频");
		}else {
			m.put("fileType", "视频");
		}
		return m;
	}
	
	private void saveInfoInLocal(){
		
		try {
			if(StringUtil.isBlank(recorderInfos)){//如果没有记录则保存，如果有的话，先取出来追加记录再保存
				
				Map m=createRecordMap(fileType,"A");
				
				List alist=new ArrayList();
				alist.add(m);
				
				String jsonstr=com.alibaba.fastjson.JSONObject.toJSONString(alist);
				com.alibaba.fastjson.JSONArray jarr=com.alibaba.fastjson.JSONArray.parseArray(jsonstr);
				new CacheProcess().updateRecorderList(this, jarr);
				 
				 
			}else{
				String updateFlag=(String)valueMap.get("updateFlag");//是更新还是保存,如果是从列表界面传过来updateFlag有值，就是更新
				Map m=null;
				if(StringUtil.isBlank(updateFlag)) {
					 m=createRecordMap(fileType,"A");
				}else {
					 m=createRecordMap(fileType,"U");
				}
				
				
				fileList.add(m);
				
				String jsonstr=com.alibaba.fastjson.JSONObject.toJSONString(fileList);
				com.alibaba.fastjson.JSONArray jarr=com.alibaba.fastjson.JSONArray.parseArray(jsonstr);
					new CacheProcess().updateRecorderList(this, jarr);
				
			}
			doAfterSaveRecorder();
		}catch(Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "保存失败！",
					Toast.LENGTH_LONG).show();
			
		}
		
	}
	
	
	private Handler handlermain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				doAfterSaveRecorder();
			}
			case 3:{

			}
			}
		}
	};
	
	private void doAfterSaveRecorder() {
//		Toast.makeText(getApplicationContext(), "保存成功！",
//				Toast.LENGTH_LONG).show();
			
			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage("已保存本地。是否上传文件？");
			builder.setTitle("提示");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Map amap=createRecordMap(fileType,"A");
					binder.setContent(com.alibaba.fastjson.JSONObject.toJSONString(amap));
					binder.setContext(RecorderSaveActivity.this);
					dialog.dismiss();
					Task task = new Task();
					task.setName(txtFileName.getText().toString());
					String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+RecorderTrackActivity.AUDIO_RECORDER_FOLDER+"/"+txtFileName.getText()+".wav";
					task.setPath(path);
					task.setId((int) (System.currentTimeMillis()/1000));
					binder.startTask(task);
//					tasksList.add(task);
//					downloadAdapter.notifyDataSetChanged();
				}
			});

			builder.setNegativeButton("否",
					new android.content.DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							finish();
						}
					});

			builder.create().show();
			btnSave.setEnabled(false);
		
	}
	
	private void saveOnServer() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
			@Override
			public void run() {
				String response=""; 
				try {
					Map map=createRecordMap(fileType,"U");
					String requestJsonStr=com.alibaba.fastjson.JSONObject.toJSONString(map);
					//获取响应的结果信息
					String url=Constant.saveRecord;
					response = getPostHttpContent("",url, requestJsonStr);
					if("1".equals(JsonUtil.getResponseHead(response).getString("flag"))){
						RecorderSaveActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS((JsonUtil.getResponseHead(response)).getString("desc")));
						return ;
					}
					
					Message m=new Message();
					m.what=1;
					handlermain.sendMessage(m);
					
				} catch (JSONException e) {
					e.printStackTrace();
					String err = StringUtil.getAppException4MOS("解析JSON出错！");
					RecorderSaveActivity.this.sendExceptionMsg(err);
					return;
				}
			
			}
		});
mThread.start();
	}
	
	private  void saveRecorder(){
		if(validate()==false) return;
		renameRecordFile();
		saveInfoInLocal();
//		saveOnServer();
	}
	
	private boolean validate() {
		String fileName=txtFileName.getText().toString();
		if(StringUtil.isBlank(fileName)) {
			Toast.makeText(getApplicationContext(), "文件名称不能为空!",
					Toast.LENGTH_LONG).show();
			return false;
		}
		//校验格式
//		String[] split=fileName.split("-");
//		if(split.length!=4) {
//			Toast.makeText(getApplicationContext(), "文件名称格式错误!",
//					Toast.LENGTH_LONG).show();
//			return false;
//		}
		
		return true;
	}
	
	@Override
	protected void registerListener() {
		btnSave.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveRecorder();
			}
		});

	}

}
