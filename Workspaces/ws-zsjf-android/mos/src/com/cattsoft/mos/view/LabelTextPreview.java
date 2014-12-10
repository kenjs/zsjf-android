package com.cattsoft.mos.view;

import android.content.Context;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定义组件，用于在显示详细信息
 * @author xieyunchao
 * CreateTime Dec 11, 2012 5:51:39 PM
 */
public class LabelTextPreview extends LinearLayout {
	
	private String label=""; //标签,如姓名：，年龄
	private String value="";//属性值
	
	TextView labelText=null;
	TextView valueText=null;
	
	public LabelTextPreview(Context context){
		this(context,null);
	}
	

	public LabelTextPreview(Context context, AttributeSet attrs) {
		super(context, attrs);
		int labelResouceId = -1;
		int valueResouceId=-1;
		labelText=new TextView(context); 
		valueText=new TextView(context);
		
		labelResouceId = attrs.getAttributeResourceValue(null, "label", 0);
		if (labelResouceId > 0) {
            label = context.getResources().getText(labelResouceId).toString();
        } else {
            label = "";
        }
		labelText.setText(label);
		labelText.setTextSize(16.0f);
		labelText.setTextColor(0xFF000000);
		  
		
		valueResouceId = attrs.getAttributeResourceValue(null, "value", 0);
		if (valueResouceId > 0) {
            value = context.getResources().getText(valueResouceId).toString();
        } else {
            value = "";
        }
		valueText.setText(value);
		valueText.setTextColor(0xFF666666);
		valueText.setAutoLinkMask(Linkify.WEB_URLS);
		
		
		addView(labelText);
		addView(valueText);
		
		this.setGravity(LinearLayout.HORIZONTAL);
		this.setGravity(Gravity.CENTER_VERTICAL);
		
	}
	
	
	public void setLabel(String alabel) {
		labelText.setText(alabel);
	}
	
	public void setValue(String aValue) {
		valueText.setText(aValue);
	}


	public TextView getLabelText() {
		return labelText;
	}


	public void setLabelText(TextView labelText) {
		this.labelText = labelText;
	}


	public TextView getValueText() {
		return valueText;
	}


	public void setValueText(TextView valueText) {
		this.valueText = valueText;
	}
	


 

	
}
