package com.cattsoft.mos.view;

import android.content.Context;
import android.text.Html;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cattsoft.mos.R;

/**
 * 自定义组件，用于在显示详细信息
 * @author xieyunchao
 * CreateTime Dec 11, 2012 5:51:39 PM
 */
public class SelectLabelText extends LinearLayout {
	
	private String label=""; //标签,如姓名：，年龄
	private String value="";//属性值
	private String hasStar="";
	
	TextView labelText=null;
	EditText valueText=null;
	
	private int imageResource=0;
	
	public SelectLabelText(Context context){
		this(context,null);
	}
	

	public SelectLabelText(Context context, AttributeSet attrs) {
		super(context, attrs);
		int labelResouceId = -1;
		int valueResouceId=-1;
		int hasStarResourceId=-1;
		labelText=new TextView(context); 
		valueText=new EditText(context);
		
		
		labelResouceId = attrs.getAttributeResourceValue(null, "label", 0);
		if (labelResouceId > 0) {
            label = context.getResources().getText(labelResouceId).toString();
        } else {
            label = "";
        }
		hasStarResourceId= attrs.getAttributeResourceValue(null, "hasStar", 0);
		if(hasStarResourceId>0) {
			label="<font color='red'>*</font>"+label;
		}
		labelText.setText(Html.fromHtml(label));
		labelText.setTextSize(16.0f);
		labelText.setTextColor(0xFF000000);
		valueText.setBackgroundResource(0);
		valueText.setCursorVisible(false);      //设置输入框中的光标不可见  
		valueText.setFocusable(false);           //无焦点  
		valueText.setFocusableInTouchMode(false);
		  
		
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
		
		imageResource = attrs.getAttributeResourceValue(null, "bgImage", 0);
		System.out.println("imageResourceimageResource="+imageResource);
		if(imageResource==0) {
			imageResource=R.drawable.select_input_bg2;
		}
			
		this.setBackgroundResource(imageResource);
		this.setGravity(Gravity.CENTER_VERTICAL);
		
	}
	
	
	public void setLabel(String alabel) {
		labelText.setText(alabel);
	}
	
	public void setValue(String aValue) {
		valueText.setText(aValue);
	}


	public EditText getValueText() {
		return valueText;
	}


	public void setValueText(EditText valueText) {
		this.valueText = valueText;
	}


	public int getImageResource() {
		return imageResource;
	}


	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}


	public String getHasStar() {
		return hasStar;
	}


	public void setHasStar(String hasStar) {
		this.hasStar = hasStar;
	}
	
}
