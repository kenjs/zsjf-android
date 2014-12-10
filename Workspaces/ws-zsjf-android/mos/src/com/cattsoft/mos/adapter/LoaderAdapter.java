package com.cattsoft.mos.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.cache.ImageLoader;
import com.cattsoft.mos.pub.Constant;







public class LoaderAdapter extends BaseAdapter{

	private static final String TAG = "LoaderAdapter";
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	
	private ImageLoader mImageLoader;
	private int mCount;
	private Context mContext;
	private List alist;
	
	
	public LoaderAdapter(int count, Context context, List list) {
		this.mCount = count;
		this.mContext = context;
		alist = list;
		mImageLoader = new ImageLoader(context);
	}
	
	public ImageLoader getImageLoader(){
		return mImageLoader;
	}

	@Override
	public int getCount() {
		return mCount;
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

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.jiu_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.mTitletView = (TextView) convertView
					.findViewById(R.id.zixun_list_item_title);
			viewHolder.mDescView=(TextView) convertView
					.findViewById(R.id.zixun_list_item_desc);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.zixun_list_item_img);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Map amap=(Map)alist.get(position);
		String url = (String)amap.get("zixun_list_item_img");
		
		viewHolder.mImageView.setImageResource(R.drawable.ic_launcher);
		
		if (!mBusy) {
			mImageLoader.DisplayImage(Constant.ServerURL+url, viewHolder.mImageView, false);
			viewHolder.mTitletView.setText((String)amap.get("zixun_list_item_title"));
			viewHolder.mDescView.setText((String)amap.get("zixun_list_item_desc"));
		} else {
//			mImageLoader.DisplayImage(Constant.ServerURL+url, viewHolder.mImageView, false);		
//			viewHolder.mTitletView.setText("--" + position + "--FLING");
//			viewHolder.mDescView.setText("--" + position + "--FLING");
			mImageLoader.DisplayImage(Constant.ServerURL+url, viewHolder.mImageView, false);
			viewHolder.mTitletView.setText((String)amap.get("zixun_list_item_title"));
			viewHolder.mDescView.setText((String)amap.get("zixun_list_item_desc"));
		}
		return convertView;
	}

	static class ViewHolder {
		TextView mTitletView;
		TextView mDescView;
		ImageView mImageView;
		
	}
}
