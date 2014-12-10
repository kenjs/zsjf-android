package com.cattsoft.mos.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义组件，在图片的右上角有一个text，显示数量等信息
 * @author xieyunchao
 * CreateTime Dec 11, 2012 5:51:39 PM
 */
public class ImageWithTips extends RelativeLayout {
	
	private String tips="..."; 
	
	private ImageView iv=null;
	private TextView tvTips=null;
	final int imageId=10000000;
	
	public ImageWithTips(Context context) {
		super(context);
		
		LinearLayout.LayoutParams layoutpare=  
	            new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(layoutpare);
		
		initView(context);
	}
	
	
	public void initView(Context context) {
		iv=new ImageView(context);
		iv.setId(imageId);
		
		tvTips=new TextView(context);
		//tvTips.setBackgroundResource(R.drawable.func_nav_tips_bg);
		tvTips.setTextColor(0xFFFFFFFF);
		tvTips.setGravity(Gravity.CENTER);
		tvTips.setText(tips);
		RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
		llp.setMargins(0, 10, 15, 0);
		llp.addRule(RelativeLayout.ALIGN_TOP, imageId);
		llp.addRule(RelativeLayout.ALIGN_RIGHT,imageId);
		tvTips.setLayoutParams(llp);
		
		this.addView(iv);
		this.addView(tvTips);
		
	}
	
	public void setImageResource(int resource){
		iv.setImageResource(resource);
	}
	
	public TextView getTextView() {
		return tvTips;
	}
	

 

	
}
