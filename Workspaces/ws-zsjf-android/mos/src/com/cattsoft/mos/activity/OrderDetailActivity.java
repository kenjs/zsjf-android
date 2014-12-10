package com.cattsoft.mos.activity;

import java.util.Map;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.OrderFinishDialog;
import com.cattsoft.mos.util.ActivityUtil;

public class OrderDetailActivity extends BasicActivity{
	
	private Button btnOrderSure=null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_detail);

		// 设置标题栏
		super.setTitleBar("订单详情",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this); 

	}
	
	@Override
	protected void initView() {
		btnOrderSure=(Button)findViewById(R.id.btn_sure_order);
	}

	@Override
	protected void registerListener() {
		btnOrderSure.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new OrderFinishDialog(OrderDetailActivity.this, R.style.selectDialog,"责任人1",null,null,new OrderFinishDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						
					}
				});
				 dialog.show();
			}
		});
		
		
		
	}

}
