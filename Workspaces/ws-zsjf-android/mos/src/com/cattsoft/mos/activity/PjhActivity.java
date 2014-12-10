package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.FilterDialog;
import com.cattsoft.mos.dialog.WineDetailDialog;
import com.cattsoft.mos.util.ActivityUtil;

/**
 * 品酒会
 * @author xieyunchao
 *
 */
public class PjhActivity extends BasicActivity{

	 
	 
	private String[] key = {"zixun_list_item_img", "zixun_list_item_title",
	"zixun_list_item_desc","price","address"};
	
	private SimpleAdapter simpleAdapter=null;
	 
	
	LinearLayout ll=null;
	
	private List wineList=null;
	ListView lv=null;
	
	private TextView tvSales=null;
	private TextView tvPrice=null;
	private TextView tvRenQi=null;
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pinjiu_list);
		
		// 设置标题栏
		super.setTitleBar("品酒会",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		this.setTitleRightButtonImg(R.drawable.filter);
		initDataSourceList();
		initData();
		initView();
		registerListener();
		SimpleAdapter adapter = new SimpleAdapter(this,wineList,R.layout.pinjiu_list_item,
				key,
				new int[]{R.id.zixun_list_item_img,R.id.zixun_list_item_title,R.id.zixun_list_item_desc
				,R.id.price,R.id.address });
		
		lv.setAdapter(adapter);
		ActivityUtil.getInstance().addActivity(this);
	}
	
	private void initData() {
		
	}

 
	@Override
	protected void initView() {
		lv=(ListView)findViewById(R.id.wine_list);
	}
	
	
 
	private void initDataSourceList() {
		wineList=new ArrayList();
		Map m1=new HashMap();
		m1.put("zixun_list_item_img", R.drawable.pinjiu_1);
		m1.put("zixun_list_item_title", "[北京]安东尼世家葡萄酒晚宴");
		m1.put("zixun_list_item_desc", "酒会时间：07-10 19:00-21:00");
		m1.put("price", "举办者：安东尼世家 ");
		m1.put("address", "举办地点:北京圆明园路169号");
		
		Map m2=new HashMap();
		m2.put("zixun_list_item_img", R.drawable.pinjiu_2);
		m2.put("zixun_list_item_title", "[北京]沃恩-罗曼尼村品鉴会");
		m2.put("zixun_list_item_desc", "酒会时间：07-11 18:00-21:00");
		m2.put("price", "举办者：红樽坊");
		m2.put("address", "举办地点:北京朝阳区三里屯男345号");
		
		Map m3=new HashMap();
		m3.put("zixun_list_item_img", R.drawable.pinjiu_3);
		m3.put("zixun_list_item_title", "[北京]嘉雅酒园晚宴");
		m3.put("zixun_list_item_desc", "酒会时间：07-12 18:30-22:00");
		m3.put("price", "举办者：嘉雅酒业");
		m3.put("address", "举办地点:益丰外滩5楼");
		
		Map m4=new HashMap();
		m4.put("zixun_list_item_img", R.drawable.pinjiu_4);
		m4.put("zixun_list_item_title", "[北京]王府半岛酒店雷司令体检日");
		m4.put("zixun_list_item_desc", "酒会时间：07-12 18:30-22:00");
		m4.put("price", "举办者：王府井半岛酒店凰厅");
		m4.put("address", "举办地点:王府井半岛酒店凰厅"); 
		
		Map m5=new HashMap();
		m5.put("zixun_list_item_img", R.drawable.pinjiu_1);
		m5.put("zixun_list_item_title", "[北京]安东尼世家葡萄酒晚宴");
		m5.put("zixun_list_item_desc", "酒会时间：07-10 19:00-21:00");
		m5.put("price", "举办者：安东尼世家 ");
		m5.put("address", "举办地点:北京圆明园路169号");
		
		Map m6=new HashMap();
		m6.put("zixun_list_item_img", R.drawable.pinjiu_2);
		m6.put("zixun_list_item_title", "[北京]沃恩-罗曼尼村品鉴会");
		m6.put("zixun_list_item_desc", "酒会时间：07-11 18:00-21:00");
		m6.put("price", "举办者：红樽坊");
		m6.put("address", "举办地点:北京朝阳区三里屯男345号");
		
		wineList.add(m1);
		wineList.add(m2);
		wineList.add(m3);
		wineList.add(m4);
		wineList.add(m5);
		wineList.add(m6);
		
	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(PjhActivity.this, FilterDialog.class);			
				//Intent intent = new Intent(WineListActivity.this, ExampleActivity.class);			
				startActivityForResult(intent, 3);
				
			}
		});
	}
 

	@Override
	protected void registerListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Dialog dialog = new WineDetailDialog(PjhActivity.this, R.style.selectDialog,"责任人1",null,null,new WineDetailDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						startActivity(new Intent(PjhActivity.this,
								OrderDetailActivity.class));
					}
				});
				 dialog.show();
				}

		});
	}
 

}
