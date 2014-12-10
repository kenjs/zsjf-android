package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.MultyEditText;

/**
 * 设备管理参数添加界面
 * @author xieyunchao
 *
 */
public class DeviceParmActivity extends BasicActivity{
	
	private ImageView iv=null;
	
	private List<HashMap> parmList=null;
	
	private HashMap parmResMap=null;
	
	Intent intent=null;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_parm);
		super.setTitleBar("设备参数",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		this.setTitleRightButtonImg(R.drawable.ok_in_title);
		initView();
		
		registerListener();
		ActivityUtil.getInstance().addActivity(this);
	}

	
	@Override
	protected void initView() {
		
		intent=getIntent();
		Bundle b=intent.getExtras();
		if(b.getSerializable("parmList")!=null) {
			HashMap hm=(HashMap)b.getSerializable("parmList");
			parmList=(ArrayList)hm.get("parmList");
		}
		LinearLayout ll=(LinearLayout)findViewById(R.id.dev_parm_liner);
		if(parmList==null ||parmList.size()==0) {
			
			MultyEditText met1=new MultyEditText(this);
			MultyEditText met2=new MultyEditText(this);
			MultyEditText met3=new MultyEditText(this);
			ll.addView(met1);
			ll.addView(met2);
			ll.addView(met3);
		}else {
			for(HashMap<String,String> m:parmList) {
				MultyEditText met=new MultyEditText(this);
				met.getLabelText().setText((String)m.get("paraName"));
				met.getValueText().setText((String)m.get("paraContent"));
				ll.addView(met);
			}
		}
		
		
		iv=(ImageView)findViewById(R.id.add_line);
	}
	
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				parmList=new ArrayList(); 
				LinearLayout ll=(LinearLayout)findViewById(R.id.dev_parm_liner);
				int cldcount=ll.getChildCount();
				for(int i=0;i<cldcount;i++) {
					View vv=ll.getChildAt(i);
					if(vv instanceof com.cattsoft.mos.view.MultyEditText) {
						MultyEditText met=(com.cattsoft.mos.view.MultyEditText)vv;
						String val1=met.getLabelText().getText().toString();
						String val2=met.getValueText().getText().toString();
						System.out.println("val1val1val1="+val1);
						System.out.println("val2val2val2="+val2);
						if(StringUtil.isBlank(val1) && !StringUtil.isBlank(val2) ) {
							Toast.makeText(getApplicationContext(), "请填写第"+(i+1)+"行参数名称！", Toast.LENGTH_SHORT).show();
							return;
						}
						if(!StringUtil.isBlank(val1) && StringUtil.isBlank(val2) ) {
							Toast.makeText(getApplicationContext(), "请填写第"+(i+1)+"行参数内容！", Toast.LENGTH_SHORT).show();
							return;
						}
						
						if(!StringUtil.isBlank(val1.trim()) &&  !StringUtil.isBlank(val2.trim()) ) {
							HashMap m=new HashMap();
							m.put("paraName", val1);
							m.put("paraContent", val2);
							parmList.add(m);
						}
					}
				}
				parmResMap=new HashMap();
				parmResMap.put("parmList", parmList);
				intent.putExtra("parmList",parmResMap);
		    	setResult(RESULT_OK, intent);
				DeviceParmActivity.this.finish();
			}
		});
	}

	@Override
	protected void registerListener() {
		iv.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MultyEditText met1=new MultyEditText(DeviceParmActivity.this);
				LinearLayout ll=(LinearLayout)findViewById(R.id.dev_parm_liner);
				ll.addView(met1);
				
			}
		});
		
	}

}
