package com.cattsoft.mos.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

public class ViewUtil {
	
	public static void disableEditText(TextView tvsdlx) {
		tvsdlx.setCursorVisible(false);      //设置输入框中的光标不可见  
		tvsdlx.setFocusable(false);           //无焦点  
		tvsdlx.setFocusableInTouchMode(false);
	}
	

	public static DatePicker findDatePicker(ViewGroup group) {  
        if (group != null) {  
            for (int i = 0, j = group.getChildCount(); i < j; i++) {  
                View child = group.getChildAt(i);  
                if (child instanceof DatePicker) {  
                    return (DatePicker) child;  
                } else if (child instanceof ViewGroup) {  
                    DatePicker result = findDatePicker((ViewGroup) child);  
                    if (result != null)  
                        return result;  
                }  
            }  
        }  
        return null;  
    }   

}
