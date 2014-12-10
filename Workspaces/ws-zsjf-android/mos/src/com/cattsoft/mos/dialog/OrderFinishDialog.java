package com.cattsoft.mos.dialog;

import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cattsoft.mos.R;

public class OrderFinishDialog extends Dialog{
	Context context;
	private OnSelectItemgListener selectItemListener;
	LinearLayout ll=null;
	int index;
	String title;
	List data=null;
	Map m=null;
	private Button btnBuyNow;
	
	//定义回调事件，用于dialog的点击事件
    public interface OnSelectItemgListener{
            public void refreshActivity(Map map);
    }
    
	 
	public OrderFinishDialog(Context context) {
        super(context);
        this.context = context;
       
	}
	 
	public OrderFinishDialog(Context context, int theme,String title,List data,Map m,OnSelectItemgListener selectItemListener) {
		super(context, theme);
		this.context = context;
		this.selectItemListener=selectItemListener;
		this.title=title;
		this.data=data;
		this.m=m;
	}
	
 
	
	 private View.OnClickListener clickListener = new View.OnClickListener() {
         
         @Override
         public void onClick(View v) {
        	 Button si=(Button)v; 
        	 selectItemListener.refreshActivity(null);
        	 OrderFinishDialog.this.dismiss();
         }
 };
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_order_finish);
        btnBuyNow=(Button)findViewById(R.id.btn_buy_now);
        
        //btnBuyNow.setOnClickListener(clickListener);
        
	}

}
