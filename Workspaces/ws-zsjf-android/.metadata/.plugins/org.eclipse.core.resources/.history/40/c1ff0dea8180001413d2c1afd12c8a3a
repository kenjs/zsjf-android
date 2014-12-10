package com.cattsoft.mos.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cattsoft.mos.R;

public class SimpleItemAdapter extends SimpleAdapter {

	

	
	private List  data=new ArrayList();
	//填充数据的资源文件   
	private int resource;  
	 private String[] from;   
	private Context context;  

	public SimpleItemAdapter(Context context, List data, int resource,
			String[] from, int[] to) {
		super(context, data, resource, from, to);
		this.context = context;
		this.data = data;
		this.resource = resource;
		this.from = from;
	} 
	
	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	} 

	@Override
	public Object getItem(int position) {
		return position;
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Map dataMap=(HashMap)(data.get(position));
		String eventState=(String)dataMap.get("msg_state");
		 
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(resource, null);
				holder = new ViewHolder();
				holder.eventType = (TextView) convertView
						.findViewById(R.id.event_type);
				holder.eventTime = (TextView) convertView
						.findViewById(R.id.event_time);
				holder.eventDetail = (TextView) convertView
						.findViewById(R.id.event_detail);
				holder.eventState = (TextView) convertView
						.findViewById(R.id.event_state);
				convertView.setTag(holder);
				if("已读".equals(eventState)) {
					holder.eventType.setTextColor(0xFF636363);
					holder.eventTime.setTextColor(0xFF636363);
					holder.eventDetail.setTextColor(0xFF636363);
				}
				
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			
			holder.eventType.setText((String)dataMap.get(from[0]));
			holder.eventTime.setText((String)dataMap.get(from[1]));
			holder.eventDetail.setText((String)dataMap.get(from[2]));
			holder.eventState.setText((String)dataMap.get(from[3]));
			return convertView;
		 

	}
	
	 private class ViewHolder{
	        TextView eventType;
	        TextView eventTime;
	        TextView eventDetail;
	        TextView eventState;
	    }

}
