package com.cattsoft.mos.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextViewWithImage extends LinearLayout{
	
	private String label="";
	TextView labelText=null;
	private ImageView iv=null;
	
	
	public TextViewWithImage(Context context){
		this(context,null);
	}
	

	public TextViewWithImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setGravity(LinearLayout.HORIZONTAL);
		
		int labelResouceId = -1;
		int imageResourceId=-1;
		
		labelResouceId = attrs.getAttributeResourceValue(null, "label", 0);
		if (labelResouceId > 0) {
            label = context.getResources().getText(labelResouceId).toString();
        } else {
            label = "";
        }
		labelText=new TextView(context);
		labelText.setText(label);
		labelText.setTextSize(16.0f);
		labelText.setTextColor(0xFF000000);
		this.addView(labelText);
		
		iv=new ImageView(context);
		imageResourceId = attrs.getAttributeResourceValue(null, "image", 0);
		iv.setImageResource(imageResourceId);
		this.addView(iv);
		
	}

	
	
}
