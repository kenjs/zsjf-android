package com.cattsoft.mos.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cattsoft.mos.R;

public class SelectItem extends RelativeLayout{

	private TextView tv=null;
	private ImageView iv=null;
	
	public SelectItem(Context context) {
		super(context);
		this.setBackgroundResource(R.drawable.bg_select_dialog);
		RelativeLayout.LayoutParams laypare=  
	            new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(laypare);
		tv=new TextView(context);
		tv.setText("aaa");
		tv.setTextColor(0xFF000000);
		float scale = context.getResources().getDisplayMetrics().density; 
		int size = (int)(12 * scale + 0.5f); 
		tv.setTextSize(size);
		
		iv=new ImageView(context);
		iv.setImageResource(R.drawable.redio_not_checked);
 
		this.addView(tv);
		this.addView(iv);
		android.widget.RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)iv.getLayoutParams();
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		iv.setLayoutParams(params); 
		
		android.widget.RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams)tv.getLayoutParams();
		param.addRule(RelativeLayout.CENTER_VERTICAL);
		tv.setLayoutParams(param);

		
	}

	public TextView getTv() {
		return tv;
	}

	public void setTv(TextView tv) {
		this.tv = tv;
	}

	public ImageView getIv() {
		return iv;
	}

	public void setIv(ImageView iv) {
		this.iv = iv;
	}
	
	

}
