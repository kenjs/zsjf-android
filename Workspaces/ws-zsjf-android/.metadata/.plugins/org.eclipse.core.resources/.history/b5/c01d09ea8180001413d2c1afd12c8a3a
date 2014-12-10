package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.JsonUtil;

public class EventDetailActivity extends BasicActivity{
 
	
	HashMap valueMap=null;
	Bundle bundle=null;
	private TextView tvEventType;
	private TextView tvEventTime;
	private TextView tvEventDetail;
	 

	public void onCreate(Bundle savedInstanceState) {
		try {
			this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
			super.onCreate(savedInstanceState);
			setContentView(R.layout.event_detail);

			// 设置标题栏
			super.setTitleBar("事件详情",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
			initData();
			initView();
			registerListener();
			updateEventState();
		}catch(Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		

	}
	
	private void initData() {
		Intent intent=this.getIntent();
		Bundle b=intent.getExtras();
		valueMap=(HashMap) b.getSerializable("valueMap");
		String msgId=(String)valueMap.get("msg_id");
		//遍历列表中的事件，更新状态
		String msgs=new CacheProcess().getEventInfo(this); 
		List tmpList=com.alibaba.fastjson.JSON.parseArray(msgs, HashMap.class);
		List msgList=new ArrayList();
	
		for(int i=0;i<tmpList.size();i++) {
			Map m=(HashMap)tmpList.get(i);
			String tempMsgId=(String)m.get("msg_id");
			if(tempMsgId.equals(msgId)) {
				m.put("msg_state", "已读");
			}
			msgList.add(m);
		}
		
		System.out.println("msg===="+com.alibaba.fastjson.JSONArray.toJSONString(msgList));
		
		new CacheProcess().updateEventInfo(this, com.alibaba.fastjson.JSONArray.toJSONString(msgList));
 	
	}
	
	
	private void updateEventState() {
		if("未读".equals((String)valueMap.get("msg_state"))) {
			new Thread() {
				public void run() {
					String res = getPostHttpContent("", Constant.eventDetail,
							com.alibaba.fastjson.JSONObject
									.toJSONString(valueMap));

					if ("1".equals(JsonUtil.getResponseHead(res).getString(
							"flag"))) {
						System.err.println("更新事件状态时发生了错误");
					}

				}
			}.start();
		}
	}
	
	
	@Override
	protected void initView() {
		tvEventType=(TextView)findViewById(R.id.event_type);
		tvEventTime=(TextView)findViewById(R.id.event_time);
		tvEventDetail=(TextView)findViewById(R.id.event_detail);
		
		tvEventType.setText((String)valueMap.get("msg_type"));
		tvEventTime.setText((String)valueMap.get("create_dt"));
		tvEventDetail.setText((String)valueMap.get("msg_content"));
		
		
	}

	@Override
	protected void registerListener() {
	
	}
	
	 

}
