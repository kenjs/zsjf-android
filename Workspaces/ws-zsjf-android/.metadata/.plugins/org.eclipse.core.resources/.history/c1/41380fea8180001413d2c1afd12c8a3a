package com.cattsoft.mos.dialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cattsoft.mos.R;
import com.cattsoft.mos.activity.BasicActivity;


public class FilterDialog  extends BasicActivity{
	 
	
	Intent intent=null;
	Bitmap bmp=null;
	String path="";
	private RelativeLayout layoutCounties=null;
	private  RelativeLayout layoutCountryItems=null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)   {
	    super.onCreate(savedInstanceState);
//	    LayoutParams params = getWindow().getAttributes();  
//	    int dipx=50;
//	    int dipy=-125;
//	    int dipx=0;
//	    int dipy=0;
//	    float scale = this.getResources().getDisplayMetrics().density; 
//	    int pxx=(int)(dipx * scale + 0.5f);
//	    int pxy=(int)(dipy * scale + 0.5f); 
//	    params.x = pxx;
//	  	params.y = pxy;
//        getWindow().setAttributes(params); 
	    setContentView(R.layout.wine_filter);
	    getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	    initView();
	    registerListener();
	}
	
	protected void initView() {
		layoutCounties=(RelativeLayout)findViewById(R.id.country_head);
		layoutCountryItems=(RelativeLayout)findViewById(R.id.country_items);
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	}  
	
	public Bitmap compressImage(Bitmap image) {
		return null;
	}  
	
	protected void registerListener() {
		layoutCounties.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(View.VISIBLE==layoutCountryItems.getVisibility()) {
					layoutCountryItems.setVisibility(View.GONE);
				}else {
					layoutCountryItems.setVisibility(View.VISIBLE);
				}
			}
		}); 
	
	}
 
}
