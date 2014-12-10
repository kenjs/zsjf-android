package com.cattsoft.mos.activity;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.cattsoft.mos.R;

public class AboutUsActivity extends BasicActivity{
  
	private TextView versionText;
	String versionName;
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		versionText = (TextView) findViewById(R.id.version_text);
		try {
			versionName = getApplicationContext().getPackageManager().getPackageInfo("com.cattsoft.mos", 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String versionNameText = "V"+versionName;
		versionText.setText(versionNameText);
		super.setTitleBar("关于", View.VISIBLE,
				View.GONE, View.GONE, false);

	}
	@Override
	protected void initView() {
		
	}
	@Override
	protected void registerListener() {
		
	}
}
