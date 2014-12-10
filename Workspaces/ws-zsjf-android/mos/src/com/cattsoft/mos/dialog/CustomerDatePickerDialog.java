package com.cattsoft.mos.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class CustomerDatePickerDialog extends DatePickerDialog {  
    public CustomerDatePickerDialog(Context context,  
            OnDateSetListener callBack, int year, int monthOfYear,  
            int dayOfMonth) {  
        super(context, callBack, year, monthOfYear, dayOfMonth);  
    }  
  
    @Override  
    public void onDateChanged(DatePicker view, int year, int month, int day) {  
        super.onDateChanged(view, year, month, day);  
       this.setTitle(year + "年" + (month + 1) + "月");  
    }  
}  