package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.adapter.PhotoGridAdapter;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.ImageUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.view.LabelTextPreview;

/**
 *缺陷 预览
 * @author xieyunchao
 *
 */
@SuppressLint("ParserError")
public class DefecAddPreViewActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
	
	private List stsList=new ArrayList();
	private List isOpenList=new ArrayList();
	
	String putPersonId="";
	private String putPersonLoginName="";
	
	private Map subsectionMap=null;
	private Map problemClassMap=null;
	private Map subitemMap=null;
	private Map defect_state_map=null;
	private Map defect_kaiyeqianhou_map=null;
	private Map defect_defect_dept_map=null;
	private Map defedt_is_open_map=null;
	private Map spResultMap=null;
	private Map problemGradeMap=null;
	private Map defect_zerenren1_map=null;
	private Map defect_zerenren2_map=null;
	private Map defect_zerenren3_map=null;
	private Map defect_jianduren1_map=null;
	private Map defect_jianduren2_map=null;
	private Map defect_jianduren3_map=null;
	
	
	
	String response="";
	
	
	int my_Year;
	int my_Month;
	int my_Day;
	StringBuffer date = new StringBuffer();
	DatePickerDialog datePickerDialog;
	Calendar my_Calendar;
	
	private HashMap valueMap; 
	//byte[] bitmapBytes ;
	
	Bundle bundle=null;
	GridView gv=null;
	List plist=null;
	String realUserName="";

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.defect_preview);

		// 设置标题栏
		super.setTitleBar("缺陷预览",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		this.setTitleRightButtonImg(R.drawable.ok_in_title);
		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);
	}
	
	private void initData() {
		putPersonId=this.getCacheProcess().getCacheValueInSharedPreferences(this, "submitPersonId");
		putPersonLoginName=this.getCacheProcess().getCacheValueInSharedPreferences(this, "loginName");
		realUserName=this.getCacheProcess().getCacheValueInSharedPreferences(this, "realUserName");
		Intent intent = getIntent();
		bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
		
		defect_defect_dept_map=(HashMap)valueMap.get("putDepartmentMap");
		defedt_is_open_map=(HashMap)valueMap.get("isOpenMap");
		problemClassMap=(HashMap)valueMap.get("problemClassMap");
		subsectionMap=(HashMap)valueMap.get("subsectionMap");
		subitemMap=(HashMap)valueMap.get("subitemMap");
		spResultMap=(HashMap)valueMap.get("spResultMap");
		problemGradeMap=(HashMap)valueMap.get("problemGradeMap");
		defect_zerenren1_map=(HashMap)valueMap.get("dutyPerson1Map");
		defect_zerenren2_map=(HashMap)valueMap.get("dutyPerson2Map");
		defect_zerenren3_map=(HashMap)valueMap.get("dutyPerson3Map");
		defect_jianduren1_map=(HashMap)valueMap.get("controlPerson1Map");
		defect_jianduren2_map=(HashMap)valueMap.get("controlPerson2Map");
		defect_jianduren3_map=(HashMap)valueMap.get("controlPerson3Map");
		
		
		
		plist=TakePhotoMain.soAttachList;
		List alist=new ArrayList(); 
		if(plist!=null && plist.size()>0) {
			for(int i=0;i<plist.size();i++) {
				Map m=(HashMap)plist.get(i);
				Bitmap myBitmap=(Bitmap)m.get("image");
				byte[] bitmapBytes =ImageUtil.compressImage(myBitmap, DefecAddPreViewActivity.this); 
				alist.add(bitmapBytes);
			}
		}
		valueMap.put("imageList", alist);
	} 
	
	@Override
	protected void initView() {
		gv=(GridView)this.findViewById(R.id.gridView1);
		
		gv.setAdapter(new PhotoGridAdapter(DefecAddPreViewActivity.this));
		
		String number=(String)valueMap.get("number");
		String putDepartment=(String)valueMap.get("putDepartment");
		String putPerson=(String)valueMap.get("putPerson");
		String putTime=(String)valueMap.get("enterDate1");
		String address=(String)valueMap.get("address");
		String problemDesc=(String)valueMap.get("problemDesc");
		String problemClass=(String)valueMap.get("problemClass");
		String subsection=(String)valueMap.get("subsection");
		String subitem=(String)valueMap.get("subitem");
		String problemGrade=(String)valueMap.get("problemGrade");
		String dutyPerson=(String)valueMap.get("dutyPerson");
		String implementPerson=(String)valueMap.get("implementPerson");//责任人3，对应接口中的执行人
		String leadDutyPerson=(String)valueMap.get("leadDutyPerson");
		String controlPerson=(String)valueMap.get("controlPerson");
		String leadControlPerson=(String)valueMap.get("leadControlPerson");
		String reCheckPerson=(String)valueMap.get("reCheckPerson"); //监督人3，对应接收人
		String dutyInterface=(String)valueMap.get("dutyInterface");
		String planDate=(String)valueMap.get("planDate1");
		String modifyUnit=(String)valueMap.get("modifyUnit");
		String isOpen=(String)valueMap.get("isOpen");
		String spResult=(String)valueMap.get("spResult");
		String spDate=(String)valueMap.get("spDate1");
		String remark=(String)valueMap.get("remark");
		
		((LabelTextPreview)findViewById(R.id.defect_no)).getValueText().setText(number);
		((LabelTextPreview)findViewById(R.id.defect_dept)).getValueText().setText(putDepartment);
		((LabelTextPreview)findViewById(R.id.defect_submit_person)).getValueText().setText(putPerson);
		((LabelTextPreview)findViewById(R.id.defect_time)).getValueText().setText(putTime);
		((LabelTextPreview)findViewById(R.id.defect_address)).getValueText().setText(address);
		((LabelTextPreview)findViewById(R.id.defect_desc)).getValueText().setText(problemDesc);
		((LabelTextPreview)findViewById(R.id.defect_type)).getValueText().setText(problemClass);
		((LabelTextPreview)findViewById(R.id.defect_fenbu)).getValueText().setText(subsection);
		((LabelTextPreview)findViewById(R.id.defect_fenxiang)).getValueText().setText(subitem);
		((LabelTextPreview)findViewById(R.id.defect_level)).getValueText().setText(problemGrade);
		((LabelTextPreview)findViewById(R.id.defect_zerenren1)).getValueText().setText(dutyPerson);
		((LabelTextPreview)findViewById(R.id.defect_zerenren2)).getValueText().setText(leadDutyPerson);
		((LabelTextPreview)findViewById(R.id.defect_zerenren3)).getValueText().setText(implementPerson);
		((LabelTextPreview)findViewById(R.id.defect_jianduren1)).getValueText().setText(controlPerson);
		((LabelTextPreview)findViewById(R.id.defect_jianduren2)).getValueText().setText(leadControlPerson);
		((LabelTextPreview)findViewById(R.id.defect_jianduren3)).getValueText().setText(reCheckPerson);
		((LabelTextPreview)findViewById(R.id.defect_zerenjiemian)).getValueText().setText(dutyInterface);
		((LabelTextPreview)findViewById(R.id.defect_plan_finish_date)).getValueText().setText(planDate);
		((LabelTextPreview)findViewById(R.id.defect_zhegngaidanwei)).getValueText().setText(modifyUnit);
		((LabelTextPreview)findViewById(R.id.defect_kaiyeqianhou)).getValueText().setText(isOpen);
		((LabelTextPreview)findViewById(R.id.defect_state)).getValueText().setText(spResult);
		((LabelTextPreview)findViewById(R.id.defect_remark)).getValueText().setText(remark);
		

	}

	@Override
	protected void registerListener() {
		gv.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int postion, long arg3) {
				Intent intent = new Intent();
				intent.setClass(DefecAddPreViewActivity.this,
						ThumbnailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("ID", postion);
				intent.putExtras(bundle);
				startActivityForResult(intent, 3);
			}
		});
	}
	
	private void forward() {
		
		
		

	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showProcessDialog(false);
				Thread mThread = new Thread(new Runnable() {// 启动新的线程，
							@Override
							public void run() {
								
								
								valueMap.put("putDepartment", defect_defect_dept_map.get("value")==null?"":defect_defect_dept_map.get("value"));
								valueMap.put("isOpen", defedt_is_open_map.get("value")==null?"":defedt_is_open_map.get("value"));
								valueMap.put("problemClass", problemClassMap.get("value")==null?"":problemClassMap.get("value"));
								valueMap.put("subsection", subsectionMap.get("value")==null?"":subsectionMap.get("value"));
								valueMap.put("subitem", subitemMap.get("value")==null?"":subitemMap.get("value"));
								valueMap.put("spResult", spResultMap.get("value")==null?"":spResultMap.get("value"));
								valueMap.put("problemGrade", problemGradeMap.get("value")==null?"":problemGradeMap.get("value"));
								valueMap.put("putPerson", realUserName);//提交人存放id
								valueMap.put("putPersonLoginName", putPersonLoginName);//用户名
								valueMap.put("dutyPerson", defect_zerenren1_map.get("value")==null?"":defect_zerenren1_map.get("value"));
								valueMap.put("leadDutyPerson", defect_zerenren2_map.get("value")==null?"":defect_zerenren2_map.get("value"));
								valueMap.put("implementPerson", defect_zerenren3_map.get("value")==null?"":defect_zerenren3_map.get("value"));
								valueMap.put("controlPerson", defect_jianduren1_map.get("value")==null?"":defect_jianduren1_map.get("value"));
								valueMap.put("leadControlPerson", defect_jianduren2_map.get("value")==null?"":defect_jianduren2_map.get("value"));
								valueMap.put("reCheckPerson", defect_jianduren3_map.get("value")==null?"":defect_jianduren3_map.get("value"));
								
								
								valueMap.remove("isOpenMap");
								valueMap.remove("spResultMap");
								valueMap.remove("putDepartmentMap");
								valueMap.remove("problemClassMap");
								valueMap.remove("subsectionMap");
								valueMap.remove("subitemMap");
								valueMap.remove("problemGradeMap");
								valueMap.remove("dutyPerson1Map");
								valueMap.remove("dutyPerson2Map");
								valueMap.remove("dutyPerson3Map");
								valueMap.remove("controlPerson1Map");
								valueMap.remove("controlPerson2Map");
								valueMap.remove("controlPerson3Map");
								
								
								String parm=com.alibaba.fastjson.JSONObject.toJSONString(valueMap); 
								String method=Constant.saveDefectMethod;
								response = getPostHttpContent("",method, parm);
								Message m=new Message();
								m.what=1;
								Bundle b=new Bundle();
								b.putString("content", response);
								m.setData(b);
								handler.sendMessage(m);
							}
						});
				mThread.start();
			
				
			}
		});
	}
	
 
	
	private boolean validate() {
		boolean flag=true;
		return flag;
	}
	
	private void resetPage(){

	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				doAfterSave(msg);
			}
			case 3:{

			}
			if (mProgressDialog != null)
				mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			}
		}
	};
	
	public void doAfterSave(Message msg) {

		Bundle b=msg.getData();
		String content=b.getString("content");
		if(StringUtil.isBlank(content)) {
			Toast.makeText(DefecAddPreViewActivity.this, "服务器端没有响应！", Toast.LENGTH_SHORT).show();
		}else {
			com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(response);
			String flag=json.getString("flag");
			String desc=json.getString("desc");
			if("1".equals(flag)) {
				Toast.makeText(DefecAddPreViewActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
				finishActivity();
			}else {
				Toast.makeText(DefecAddPreViewActivity.this, desc,Toast.LENGTH_SHORT).show();
			}
		}
	
	}
	
	private void finishActivity() {
		List<Activity> actList =ActivityUtil.getInstance().getActivityList();
		 for (Activity acti : actList) {  
			 if(acti instanceof com.cattsoft.mos.activity.DefecAdd1tActivity ||
					 acti instanceof com.cattsoft.mos.activity.DefecAdd2tActivity||
					 acti instanceof com.cattsoft.mos.activity.DefecAdd3tActivity) {
				 acti.finish();
			 }
	           
	        } 
		 finish();
	}
	

}
