package com.cattsoft.mos.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cattsoft.mos.R;

/**
 * 一行中有多个文本输入框
 * @author xieyunchao
 *
 */
public class MultyEditText extends RelativeLayout{
	
	private String label=""; //标签,如姓名：，年龄
	private String value="";//属性值
	
	private int imageResource=0;
	
	private EditText text1=null;
	private EditText text2=null;
	ImageView  iv=null;
	Context context=null;
	
	public MultyEditText(Context context){
		this(context,null);
		this.context=context;
	}
	

	public MultyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		float scale = context.getResources().getDisplayMetrics().density; 
		RelativeLayout.LayoutParams p=  
	            new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
//		int layheight = (int)(40 * scale + 0.5f); 
//		p.height=layheight;
		this.setLayoutParams(p); 
		
		text1=new EditText(context); 
		text1.setId(10000);
		text1.setText(label);
		text1.setTextSize(16.0f);
		text1.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		//text1.setTextColor(0xFF000000);
		
		int width = (int)(120 * scale + 0.5f); 
		int height = (int)(40 * scale + 0.5f); 
		
		int marginRight=(int)(20 * scale + 0.5f); 
		int marginLeft=(int)(10 * scale + 0.5f); 
		RelativeLayout.LayoutParams llp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		llp.width=width;
		llp.height=height;
		llp.setMargins(marginLeft, 0, marginRight, 0);
		text1.setLayoutParams(llp);
		//text1.setBackgroundColor(0);
		 
		text2=new EditText(context);
		//text2.setBackgroundResource(0);
		RelativeLayout.LayoutParams imagebtn_params = new RelativeLayout.LayoutParams(  
        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
		imagebtn_params.addRule(RelativeLayout.RIGHT_OF,10000);
		imagebtn_params.width=width;
		imagebtn_params.height=height;
		text2.setLayoutParams(imagebtn_params);
		text2.setText(value);
		text2.setTextColor(0xFF666666);
		text2.setAutoLinkMask(Linkify.WEB_URLS);
		text2.setFocusable(true);
		text2.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(50)
        });
		
		iv=new ImageView(context);
		iv.setImageResource(R.drawable.line_delete);
		
		addView(text1);
		addView(text2);
		addView(iv);
		
		android.widget.RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)iv.getLayoutParams();
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.setMargins(0, 0, 20, 0);
		iv.setLayoutParams(params); 
		
		this.setGravity(LinearLayout.HORIZONTAL);

		this.setBackgroundResource(R.drawable.device_parm_line_bg);
		
		this.setGravity(Gravity.CENTER_VERTICAL);
		registerListener();
		
	}
	
	public int getImageResource() {
		return imageResource;
	}


	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}


	public void setLabel(String alabel) {
		text1.setText(alabel);
	}
	
	public void setValue(String aValue) {
		text2.setText(aValue);
	}
	
	public TextView getLabelText() {
		return text1;
	}


	public void setLabelText(EditText labelText) {
		this.text1 = labelText;
	}


	public EditText getValueText() {
		return text2;
	}


	public void setValueText(EditText valueText) {
		this.text2 = valueText;
	}
	
	protected void registerListener() {
		iv.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RelativeLayout rl=(RelativeLayout)v.getParent();
				LinearLayout  ll=(LinearLayout)rl.getParent();
				ll.removeView(rl);
			}
		});
	}

 
	

	

}
