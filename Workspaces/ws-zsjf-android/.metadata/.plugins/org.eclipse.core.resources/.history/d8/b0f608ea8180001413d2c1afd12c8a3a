package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

import com.cattsoft.mos.R;
import com.cattsoft.mos.adapter.SimpleItemAdapter;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.widget.PullToRefreshListView;

public class EventActivity extends BasicListActivity{

	private SimpleAdapter simpleAdapter;
	private PullToRefreshListView refreshListView; 

	private String[] key = {"msg_type", "create_dt",
			"msg_content","msg_state","msg_id"}; //state为已读或未读
	private List eventList=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.func_node_list);

		// 设置标题栏
		super.setTitleBar("事件列表",View.GONE,View.GONE,View.GONE,false);

		// 设置公告下拉列表
		refreshListView = (PullToRefreshListView) getListView();
		initView();
		registerListener();
 
	}
	
	private void initDataSource(){
		if(eventList==null)eventList=new ArrayList();
		String eventListStr=new CacheProcess().getEventInfo(this);//json数组格式
		
//		for(int i=0;i<5;i++) {
//			Map m=new HashMap();
//			m.put("msg_type", "普通事件");
//			m.put("create_dt", "2014-12-12 12:12:12");
//			m.put("msg_id", i+"");
//			m.put("msg_content", "中新网4月17日电 中国经济今年第一季度数据日前公布，引起关注。境外华文媒体分析称");
//			if(i==3) {
//				m.put("msg_state", "已读");
//			}else {
//				m.put("msg_state", "未读");
//			}
//			
//			eventList.add(m);
//		}
		
		if(!StringUtil.isBlank(eventListStr)) {
			eventList=com.alibaba.fastjson.JSON.parseArray(eventListStr, HashMap.class);
		}
		
		
	}
 
	@Override
	protected void initView() {
		initDataSource();
		
		simpleAdapter=new SimpleItemAdapter(EventActivity.this, eventList,
				R.layout.event_item, key, new int[] {
				R.id.event_type,
				R.id.event_time,
				R.id.event_detail,
				R.id.event_state,
				R.id.event_id
		});
		refreshListView.setBackgroundColor(R.drawable.background);
		setListAdapter(simpleAdapter);
	}
	

 
	@Override
	protected void registerListener() {
		refreshListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				HashMap m=(HashMap)eventList.get(arg2-1);
				
				Intent intent = new Intent();
				Bundle b=new Bundle();
				b.putSerializable("valueMap", m);
				intent.putExtras(b);
				intent.setClass(EventActivity.this,
						EventDetailActivity.class);
				startActivity(intent);

			}

		});
	}

}
