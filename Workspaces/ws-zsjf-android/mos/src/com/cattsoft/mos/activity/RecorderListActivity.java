package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.DateUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.widget.PullToRefreshListView;

public class RecorderListActivity extends BasicListActivity{
	
	private List fileList = new ArrayList();
	private PullToRefreshListView refreshListView; 
	private String[] key = {"fileName", "customName","fileSize","timeLong","remarked","createTime","fileType","uploaded"};
	
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recorder_list);

		// 设置标题栏
		super.setTitleBar("文件列表",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	private void initData() {
		
		
	}

	@Override
	protected void initView() {
		refreshListView = (PullToRefreshListView) getListView();
		
		String fileInfos="";
		try {
			fileInfos = new CacheProcess().getRecorderList(this);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(!StringUtil.isBlank(fileInfos)) {
			fileList=com.alibaba.fastjson.JSON.parseArray(fileInfos, java.util.HashMap.class);
			filterFileList();
		}
		SimpleAdapter simpleAdapter=new SimpleAdapter(RecorderListActivity.this, fileList,
				R.layout.reocrd_list_item, key, new int[] {
				R.id.fileName,
				R.id.customName,
				R.id.fileSize,
				R.id.timeLong,
				R.id.remarked,
				R.id.createTime,
				R.id.fileType,
				R.id.uploaded
		});
		setListAdapter(simpleAdapter);
	}
	
	/**
	 * 过滤文件列表，文件已经上传并且过期的不显示
	 */
	private void filterFileList() {
		if(fileList!=null && fileList.size()>0) {
			for(int i=0;i<fileList.size();i++) {
				Map hm=(HashMap)fileList.get(i);
				String adate=(String)hm.get("createTime");
				String nowDate=DateUtil.getCurrentDateStr();
				String uploaded=(String)hm.get("uploaded");
				double dateDiff=DateUtil.dateDiff(adate, nowDate);
				if(dateDiff>7.0d && "已上传".equals(uploaded)) {
					fileList.remove(hm);
				}
			}
			String jsonstr=com.alibaba.fastjson.JSONObject.toJSONString(fileList);
			com.alibaba.fastjson.JSONArray jarr=com.alibaba.fastjson.JSONArray.parseArray(jsonstr);
				try {
					new CacheProcess().updateRecorderList(this, jarr);
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "列表显示时出现JSON异常，请系统维护人员！",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
		}
	}

	@Override
	protected void registerListener() {
		refreshListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap m=(HashMap)fileList.get(arg2-1);
				Intent intent = new Intent(RecorderListActivity.this, RecorderSaveActivity.class);	
				Bundle b=new Bundle();
				m.put("updateFlag", "true");//是修改还是保存
				b.putSerializable("valueMap", m);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}

}
