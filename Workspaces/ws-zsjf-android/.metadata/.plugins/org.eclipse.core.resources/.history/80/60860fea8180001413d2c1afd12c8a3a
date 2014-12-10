package com.cattsoft.mos.dialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.view.SelectItem;

public class SelectDialog  extends Dialog{

	Context context;
	private OnSelectItemgListener selectItemListener;
	LinearLayout ll=null;
	int index;
	String title;
	List data=null;
	Map m=null;
	
	//定义回调事件，用于dialog的点击事件
    public interface OnSelectItemgListener{
            public void refreshActivity(Map map);
    }
    
	 
	public SelectDialog(Context context) {
        super(context);
        this.context = context;
       
	}
	 
	public SelectDialog(Context context, int theme,String title,List data,Map m,OnSelectItemgListener selectItemListener) {
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
        	 SelectItem si=(SelectItem)v;
        	 si.getIv().setImageResource(R.drawable.redio_checked);
        	
        	 selectItemListener.refreshActivity((Map)si.getTag());
        	 SelectDialog.this.dismiss();
         }
 };
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_select);
        initView(context);
        
	}
	


	
	private void  initView(Context context) {
		ll=(LinearLayout)findViewById(R.id.layout_dialog_selct2);
		//ScrollView scv=(ScrollView)findViewById(R.id.select_scroll);
		
		TextView tv=(TextView)findViewById(R.id.dialog_select_title);
		tv.setText(title);
		String key="";
		if(m!=null) {
			key=(String)m.get("value");
			if(key==null)key="";
		}
		if(data!=null && data.size()>0) {
			for(int i=0;i<data.size();i++) {
				Map mm=(HashMap)data.get(i);
				SelectItem si=new SelectItem(context);
				String value=(String)mm.get("value");
				if(key.equals(value)) {
					si.getIv().setImageResource(R.drawable.redio_checked);
				}
				si.setTag(mm);
				si.getTv().setText("   "+(String)mm.get("name"));
				si.setOnClickListener(clickListener);
				ll.addView(si);
				//scv.addView(si);
			}
		}
		
	}

}
