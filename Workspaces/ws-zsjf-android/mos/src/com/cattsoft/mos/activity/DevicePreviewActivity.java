package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 *设备
 * @author xieyunchao
 *
 */
public class DevicePreviewActivity extends BasicActivity{
	
	private Button nextBtn;
	private Button resetBtn;
 
	
	private HashMap valueMap=null;
	

	
	private GridView photoGridView=null;
	
	String path;
 
	DatePickerDialog datePickerDialog; 
	
	GridView gv=null;
	
	String response="";
	List plist=null;
	
	Map deviceUseDeptMap=null;
	Map quantityUnitMap=null;
	Map dutyTeamMap=null;
	Map equipTypeMap=null;
	
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_preview);

		// 设置标题栏
		super.setTitleBar("设备录入",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		this.setTitleRightButtonImg(R.drawable.ok_in_title);
		initData();
		initView();
		registerListener();

	}
	
	private void initData() {
		TakePhotoMain.j = 0;
		path = TakePhotoMain.ReadSDPath() + "/DCIM/Camera/";
		plist=TakePhotoMain.soAttachList;
		List alist=new ArrayList(); 
		if(plist!=null && plist.size()>0) {
			for(int i=0;i<plist.size();i++) {
				Map m=(HashMap)plist.get(i);
				Bitmap myBitmap=(Bitmap)m.get("image");
				byte[] bitmapBytes =ImageUtil.compressImage(myBitmap, DevicePreviewActivity.this); 
				alist.add(bitmapBytes);
			}
		}
		
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
		valueMap.put("imageList", alist);
		
		
		deviceUseDeptMap=(HashMap)valueMap.get("useDepartmentMap");
		quantityUnitMap=(HashMap)valueMap.get("quantityUnitMap");
		dutyTeamMap=(HashMap)valueMap.get("dutyTeamMap");
		equipTypeMap=(HashMap)valueMap.get("equipTypeMap");
		
	}
	
	@Override
	protected void initView() {
		for(int i=0;i<TakePhotoMain.soAttachList.size();i++) {
			for(Map  m:TakePhotoMain.soAttachList) {
				Bitmap myBitmap=(Bitmap)m.get("image");
				byte[] bitmapBytes =ImageUtil.compressImage(myBitmap, DevicePreviewActivity.this);  
				System.out.println("xxxxxxxx"+bitmapBytes.length);
			} 
		}
		gv=(GridView)this.findViewById(R.id.gridView1);
		
		gv.setAdapter(new PhotoGridAdapter(DevicePreviewActivity.this));
		
		String device_bianhao =(String)valueMap.get("number");
		String device_yzzcdm  =(String)valueMap.get("code");
		String device_name  =(String)valueMap.get("chName");
		String device_english_name  =(String)valueMap.get("enName");
		String device_use_dept  =(String)valueMap.get("useDepartment");
		String device_install_address  =(String)valueMap.get("installAddress");
		String device_mode  =(String)valueMap.get("model");
		String device_count  =(String)valueMap.get("quantity");
		String device_unit  =(String)valueMap.get("quantityUnit");
		String device_belongs  =(String)valueMap.get("belong");
		String device_prod_date  =(String)valueMap.get("productDate1");
		String device_fzbz  =(String)valueMap.get("dutyTeam");
		String device_zenrenren  =(String)valueMap.get("dutyPerson");
		String device_dengji_date  =(String)valueMap.get("registerDate1");
		String device_lingyongren  =(String)valueMap.get("recipientsPerson");
		String device_lingyong_date  =(String)valueMap.get("recipientsDate1");
		String device_baoguanren  =(String)valueMap.get("keeper");
		String device_type  =(String)valueMap.get("equipType");
		String device_gonglv  =(String)valueMap.get("power");
		String device_dianya  =(String)valueMap.get("voltage");
		String device_guige  =(String)valueMap.get("specification");
		
		Object canshuObj=valueMap.get("canshuList");
		List canshuList=null;
		String canshuRes="未填";
		if(canshuObj!=null) {
			canshuList=(List)valueMap.get("canshuList");
			if(canshuList!=null && canshuList.size()>0) {
				canshuRes="已填写";
			}
		} 
		String device_zhongliang  =(String)valueMap.get("weight");
		String device_zhidaoshang  =(String)valueMap.get("manufacturer");
		String device_address  =(String)valueMap.get("manuAddress");
		String device_lianxi_ren  =(String)valueMap.get("manuPerson");
		String device_lianxi_fangshi  =(String)valueMap.get("manuPhone");
		String device_shigongdanwei  =(String)valueMap.get("constructName");
		String device_anzhuang_riqi  =(String)valueMap.get("consDate1");
		String device_address2  =(String)valueMap.get("consAddress");
		String device_lianxiren2  =(String)valueMap.get("consPerson");
		String device_lianfangshi2  =(String)valueMap.get("consPhone");
		String device_desc  =(String)valueMap.get("description");
		String device_remark  =(String)valueMap.get("remark");
		
		LabelTextPreview ltp_bianhao=(LabelTextPreview)findViewById(R.id.device_bianhao);
		LabelTextPreview ltp_device_yzzcdm=(LabelTextPreview)findViewById(R.id.device_yzzcdm);
		LabelTextPreview ltp_device_name=(LabelTextPreview)findViewById(R.id.device_name);
		LabelTextPreview ltp_device_english_name=(LabelTextPreview)findViewById(R.id.device_english_name);
		LabelTextPreview ltp_device_use_dept=(LabelTextPreview)findViewById(R.id.device_use_dept);
		LabelTextPreview ltp_device_install_address=(LabelTextPreview)findViewById(R.id.device_install_address);
		LabelTextPreview ltp_device_mode=(LabelTextPreview)findViewById(R.id.device_mode);
		LabelTextPreview ltp_device_count=(LabelTextPreview)findViewById(R.id.device_count);
		LabelTextPreview ltp_device_unit=(LabelTextPreview)findViewById(R.id.device_unit);
		LabelTextPreview ltp_device_belongs=(LabelTextPreview)findViewById(R.id.device_belongs);
		LabelTextPreview ltp_device_prod_date=(LabelTextPreview)findViewById(R.id.device_prod_date);
		LabelTextPreview ltp_device_fzbz=(LabelTextPreview)findViewById(R.id.device_fzbz);
		LabelTextPreview ltp_device_zenrenren=(LabelTextPreview)findViewById(R.id.device_zenrenren);
		LabelTextPreview ltp_device_dengji_date=(LabelTextPreview)findViewById(R.id.device_dengji_date);
		LabelTextPreview ltp_device_lingyongren=(LabelTextPreview)findViewById(R.id.device_lingyongren);
		LabelTextPreview ltp_device_lingyong_date=(LabelTextPreview)findViewById(R.id.device_lingyong_date);
		LabelTextPreview ltp_device_baoguanren=(LabelTextPreview)findViewById(R.id.device_baoguanren);
		LabelTextPreview ltp_device_type=(LabelTextPreview)findViewById(R.id.device_type);
		LabelTextPreview ltp_device_gonglv=(LabelTextPreview)findViewById(R.id.device_gonglv);
		LabelTextPreview ltp_device_dianya=(LabelTextPreview)findViewById(R.id.device_dianya);
		LabelTextPreview ltp_device_guige=(LabelTextPreview)findViewById(R.id.device_guige);
		LabelTextPreview ltp_device_canshu=(LabelTextPreview)findViewById(R.id.device_canshu);
		LabelTextPreview ltp_device_zhongliang=(LabelTextPreview)findViewById(R.id.device_zhongliang);
		LabelTextPreview ltp_device_zhidaoshang=(LabelTextPreview)findViewById(R.id.device_zhidaoshang);
		LabelTextPreview ltp_device_address=(LabelTextPreview)findViewById(R.id.device_address);
		LabelTextPreview ltp_device_lianxi_ren=(LabelTextPreview)findViewById(R.id.device_lianxi_ren);
		LabelTextPreview ltp_device_lianxi_fangshi=(LabelTextPreview)findViewById(R.id.device_lianxi_fangshi);
		LabelTextPreview ltp_device_shigongdanwei=(LabelTextPreview)findViewById(R.id.device_shigongdanwei);
		LabelTextPreview ltp_device_anzhuang_riqi=(LabelTextPreview)findViewById(R.id.device_anzhuang_riqi);
		LabelTextPreview ltp_device_address2=(LabelTextPreview)findViewById(R.id.device_address2);
		LabelTextPreview ltp_device_lianxiren2=(LabelTextPreview)findViewById(R.id.device_lianxiren2);
		LabelTextPreview ltp_device_lianfangshi2=(LabelTextPreview)findViewById(R.id.device_lianfangshi2);
		LabelTextPreview ltp_device_desc=(LabelTextPreview)findViewById(R.id.device_desc); 
		LabelTextPreview ltp_device_remark=(LabelTextPreview)findViewById(R.id.device_remark); 
		
		 ltp_bianhao.getValueText().setText(device_bianhao);
		 ltp_device_yzzcdm.getValueText().setText(device_yzzcdm);
		 ltp_device_name.getValueText().setText(device_name);
		 ltp_device_english_name.getValueText().setText(device_english_name);
		 ltp_device_use_dept.getValueText().setText(device_use_dept);
		 ltp_device_install_address.getValueText().setText(device_install_address);
		 ltp_device_mode.getValueText().setText(device_mode);
		 ltp_device_count.getValueText().setText(device_count);
		 ltp_device_unit.getValueText().setText(device_unit);
		 ltp_device_belongs.getValueText().setText(device_belongs);
		 ltp_device_prod_date.getValueText().setText(device_prod_date);
		 ltp_device_fzbz.getValueText().setText(device_fzbz);
		 ltp_device_zenrenren.getValueText().setText(device_zenrenren);
		 ltp_device_dengji_date.getValueText().setText(device_dengji_date);
		 ltp_device_lingyongren.getValueText().setText(device_lingyongren);
		 ltp_device_lingyong_date.getValueText().setText(device_lingyong_date);
		 ltp_device_baoguanren.getValueText().setText(device_baoguanren);
		 ltp_device_type.getValueText().setText(device_type);
		 ltp_device_gonglv.getValueText().setText(device_gonglv);
		 ltp_device_dianya.getValueText().setText(device_dianya);
		 ltp_device_guige.getValueText().setText(device_guige);
		 ltp_device_canshu.getValueText().setText(canshuRes);
		 ltp_device_zhongliang.getValueText().setText(device_zhongliang);
		 ltp_device_zhidaoshang.getValueText().setText(device_zhidaoshang);
		 ltp_device_address.getValueText().setText(device_address);
		 ltp_device_lianxi_ren.getValueText().setText(device_lianxi_ren);
		 ltp_device_lianxi_fangshi.getValueText().setText(device_lianxi_fangshi);
		 ltp_device_shigongdanwei.getValueText().setText(device_shigongdanwei);
		 ltp_device_anzhuang_riqi.getValueText().setText(device_anzhuang_riqi);
		 ltp_device_address2.getValueText().setText(device_address2);
		 ltp_device_lianxiren2.getValueText().setText(device_lianxiren2);
		 ltp_device_lianfangshi2.getValueText().setText(device_lianfangshi2);
		 ltp_device_desc.getValueText().setText(device_desc);
		 ltp_device_remark.getValueText().setText(device_remark);
		 
		 
		
	}

	@Override
	protected void registerListener() {
		
	}
	
	private void forward() {
	
	}
	
	private boolean validate() {
		return false;
	}
	
	private void initPhotoGrid() {
		photoGridView = (GridView) findViewById(R.id.gridView1);
		photoGridView.setVisibility(View.VISIBLE);
		System.out.println("uuuuuuuuuu");

		this.photoGridView.setAdapter(new PhotoGridAdapter(DevicePreviewActivity.this));
		// 点击单张图片显示
		this.photoGridView
				.setOnItemClickListener(new GridView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int postion, long arg3) {
						Intent intent = new Intent();
						intent.setClass(DevicePreviewActivity.this,
								ThumbnailActivity.class);
						Bundle bundle = new Bundle();
						bundle.putInt("ID", postion);
						intent.putExtras(bundle);
						startActivityForResult(intent, 3);
					}
				});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case 3: {
					this.initPhotoGrid();
				}
				break;
			}
		}
		
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
	
	private void resetPage(){
	
	}
	
	public void doAfterSave(Message msg) { 
		
		Bundle b=msg.getData();
		String content=b.getString("content");
		if(StringUtil.isBlank(content)) {
			Toast.makeText(DevicePreviewActivity.this, "服务器端没有响应！", Toast.LENGTH_SHORT).show();
		}else {
			com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(response);
			String flag=json.getString("flag");
			String desc=json.getString("desc");
			if("1".equals(flag)) {
				Toast.makeText(DevicePreviewActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
				finishActivity();
			}else {
				Toast.makeText(DevicePreviewActivity.this, desc,Toast.LENGTH_SHORT).show();
			}
		}
	
	}
	
	private void finishActivity() {
		List<Activity> actList =ActivityUtil.getInstance().getActivityList();
		 for (Activity acti : actList) {  
			 if(acti instanceof com.cattsoft.mos.activity.DeviceAdd1tActivity ||
					 acti instanceof com.cattsoft.mos.activity.DeviceAdd2tActivity||
					 acti instanceof com.cattsoft.mos.activity.DeviceAdd3tActivity||
					 acti instanceof com.cattsoft.mos.activity.DeviceAdd4tActivity
					 ) {
				 acti.finish();  
			 }
	           
	        } 
		 DevicePreviewActivity.this.finish();
	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showProcessDialog(false);
				Thread mThread = new Thread(new Runnable() {// 启动新的线程，
							@Override
							public void run() {
								valueMap.put("useDepartment", deviceUseDeptMap.get("value")==null?"":deviceUseDeptMap.get("value"));
								valueMap.put("quantityUnit", quantityUnitMap.get("value")==null?"":quantityUnitMap.get("value"));
								valueMap.put("dutyTeam", dutyTeamMap.get("value")==null?"":dutyTeamMap.get("value"));
								valueMap.put("equipType", equipTypeMap.get("value")==null?"":equipTypeMap.get("value"));
								
								valueMap.remove("useDepartmentMap");
								valueMap.remove("quantityUnitMap");
								valueMap.remove("dutyTeamMap");
								valueMap.remove("equipTypeMap");
								
								String parm=com.alibaba.fastjson.JSONObject.toJSONString(valueMap);
								
								String method=Constant.saveDeviceMethod;
								response = getPostHttpContent("",method, parm);
								//response = getPostHttpContent("androidDef/saveAndroidDef.action", parm);
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
		
}
