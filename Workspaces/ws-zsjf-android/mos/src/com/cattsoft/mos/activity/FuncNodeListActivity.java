package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.widget.PullToRefreshListView;

public class FuncNodeListActivity extends BasicListActivity{


	private SimpleAdapter simpleAdapter;
	private PullToRefreshListView refreshListView; 

	private ArrayList<Map<String, Object>> funcNodeList = new ArrayList<Map<String, Object>>();
	private String[] key = {"func_node_list_item_img", "func_node_list_item_title",
			"func_node_list_item_code"};
	String isCanDefectInput=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.func_node_list);

		// 设置标题栏
		super.setTitleBar("万达酒店工程管理系统",View.GONE,View.GONE,View.GONE,false);

		// 设置公告下拉列表
		refreshListView = (PullToRefreshListView) getListView();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);
 
	}
	
	private void initDataSource(){
		
		String funcList=this.getCacheProcess().getCacheValueInSharedPreferences(this, "funcList"); 
		isCanDefectInput=this.getCacheProcess().getCacheValueInSharedPreferences(this, "defectIsCanInput"); 
		com.alibaba.fastjson.JSONArray funcArr=com.alibaba.fastjson.JSONObject.parseArray(funcList);
		if(funcArr!=null && funcArr.size()>0) {
			for(int i=0;i<funcArr.size();i++) {
				com.alibaba.fastjson.JSONObject j=(com.alibaba.fastjson.JSONObject)funcArr.get(i);
				String funcName=j.getString("name");
				if("缺陷管理".equals(funcName)) {
					Map defectMap=new HashMap();
					defectMap.put(key[0], R.drawable.func_broken);
					defectMap.put(key[1], "缺陷录入");
					defectMap.put(key[2], "QXLR");
					funcNodeList.add(defectMap);
				}
				if("设备台账".equals(funcName)) {
					Map deviceMap=new HashMap();
					deviceMap.put(key[0], R.drawable.func_device);
					deviceMap.put(key[1], "设备录入");
					deviceMap.put(key[2], "SBLR");
					funcNodeList.add(deviceMap);
				}
			}
		}
		
		Map settingMap=new HashMap();
		settingMap.put(key[0], R.drawable.func_tools);
		settingMap.put(key[1], "设置");
		settingMap.put(key[2], "SZ");
		
		
		
		funcNodeList.add(settingMap);
	}
 
	@Override
	protected void initView() {
		initDataSource();
		simpleAdapter=new SimpleAdapter(FuncNodeListActivity.this, funcNodeList,
				R.layout.func_node_list_item, key, new int[] {
				R.id.func_node_list_item_img,
				R.id.func_node_list_item_title});
		refreshListView.setDivider(null);
		refreshListView.setBackgroundColor(R.drawable.background);
		setListAdapter(simpleAdapter);
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (((keyCode == KeyEvent.KEYCODE_BACK) || (keyCode == KeyEvent.KEYCODE_HOME))
				&& event.getRepeatCount() == 0) {
			dialogExit();
		}
		return false;
	}


 
	@Override
	protected void registerListener() {
		refreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				Map m=funcNodeList.get(arg2-1);
				String code=(String)m.get("func_node_list_item_code");
				if("QXLR".equals(code)){
					if("1".equals(isCanDefectInput)) {
						intent.setClass(FuncNodeListActivity.this,
								DefecAdd1tActivity.class);
						startActivity(intent);
					}else {
						Toast.makeText(getApplicationContext(), "当前时间已超过开业时间+180天，不能录入!", Toast.LENGTH_SHORT).show();
					}
				}else if("SBLR".equals(code)){
					intent.setClass(FuncNodeListActivity.this,
							DeviceAdd1tActivity.class);
					startActivity(intent);
				}else{
					intent.setClass(FuncNodeListActivity.this,
							SettingActivity.class);
					startActivity(intent);
				}

			}

		});
	}
	

}
