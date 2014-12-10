package com.cattsoft.mos.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.vo.mvo.MenuItemData;

public class GridViewAdapter extends BaseAdapter {
	Context mContext;
	LayoutInflater mLayoutInflater;
	MenuItemData mMenuItemData;

	public GridViewAdapter(Context context, MenuItemData menuItemData) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		mMenuItemData = menuItemData;
	}

	public void refreshData(MenuItemData menuItemData) {
		mMenuItemData = menuItemData;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mMenuItemData.getCount();
	}

	@Override
	public Object getItem(int position) {

		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(
					R.layout.work_order_handle_toolbar_item, null);
		}
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.toolbar_item_image);
		if (position == 0) {
			imageView.setBackgroundResource(R.drawable.toolbar_maini);
		} else if (position == 1) {
			imageView.setBackgroundResource(R.drawable.toolbar_day);
		} else if (position == 2) {
			imageView.setBackgroundResource(R.drawable.toolbar_month);
		} else if (position == 3) {
			imageView.setBackgroundResource(R.drawable.toolbar_zdgz);
		}
		return convertView;
	}
}
