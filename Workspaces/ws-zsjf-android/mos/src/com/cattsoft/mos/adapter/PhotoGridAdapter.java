package com.cattsoft.mos.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.cattsoft.mos.activity.TakePhotoMain;

public class PhotoGridAdapter extends BaseAdapter  {

	private Activity context;

	// 定义构造方法加入Context参数
	public PhotoGridAdapter(Activity context) {
		this.context = context;
	}

	@Override
	// 返回值为所有图片的个数
	public int getCount() {

		return TakePhotoMain.soAttachList.size();
	}

	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 定义一个ImageView显示图片
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			DisplayMetrics dm = new DisplayMetrics();
			context.getWindowManager().getDefaultDisplay().getMetrics(dm);
			int screenW = dm.widthPixels;// 获取屏幕宽度
			int whith = screenW / 3;
			imageView.setLayoutParams(new GridView.LayoutParams(whith - 5,
					whith - 5));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(10, 0, 0, 10);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageBitmap((Bitmap) TakePhotoMain.soAttachList.get(
				position).get("image"));
		return imageView;
	}

}
