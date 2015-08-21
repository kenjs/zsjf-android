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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.FilterDialog;
import com.cattsoft.mos.dialog.WineDetailDialog;
import com.cattsoft.mos.util.ActivityUtil;

/**
 * 
 * @author xieyunchao
 * 
 */
public class WineListActivity extends BasicActivity {
 
	 
	private String[] key = {"zixun_list_item_img", "zixun_list_item_title","juli",
	"zixun_list_item_desc","tip1","tip2","price"};
	
	private SimpleAdapter simpleAdapter=null;
	 
	
	LinearLayout ll=null;
	
	private List wineList=null;
	ListView lv=null;
	
	private TextView tvSales=null;
	private TextView tvPrice=null;
	private TextView tvRenQi=null;
	private RelativeLayout tabLayout=null;
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiu_list);
		
		// 设置标题栏
		super.setTitleBar("葡萄酒",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		this.setTitleRightButtonImg(R.drawable.filter);
		initDataSourceList();
		initData();
		initView();
		registerListener();
		SimpleAdapter adapter = new SimpleAdapter(this,wineList,R.layout.jiu_list_item,
				key,
				new int[]{R.id.zixun_list_item_img,R.id.zixun_list_item_title,R.id.juli,R.id.zixun_list_item_desc,
				R.id.img_tip1,R.id.img_tip2,R.id.price });
		
		lv.setAdapter(adapter);
		ActivityUtil.getInstance().addActivity(this);
	}
	
	private void initData() {
		
	}

 
	@Override
	protected void initView() {
		tabLayout=(RelativeLayout)findViewById(R.id.all_task_list_item_img0);
		lv=(ListView)findViewById(R.id.wine_list);
		tvSales=(TextView)findViewById(R.id.tv_sales);
		tvSales.setTag("sales");
		tvPrice=(TextView)findViewById(R.id.tv_price);
		tvPrice.setTag("price");
		tvRenQi=(TextView)findViewById(R.id.tv_renqi);
		tvRenQi.setTag("renqi");
	}
	
	private class TvClickListener implements Button.OnClickListener {
		@Override
		public void onClick(View v) {
			TextView tv=(TextView)v;
			tv.setTextColor(0xFF0761B7);
			if(tv.getTag().equals("sales")) {
				tabLayout.setBackgroundResource(R.drawable.hot_select);
				tvPrice.setTextColor(0xFF000000);
				tvRenQi.setTextColor(0xFF000000);
			}else if(tv.getTag().equals("price")) {
				tabLayout.setBackgroundResource(R.drawable.price_select);
				tvSales.setTextColor(0xFF000000);
				tvRenQi.setTextColor(0xFF000000);
			}else {
				tabLayout.setBackgroundResource(R.drawable.renqi_select);
				tvSales.setTextColor(0xFF000000);
				tvPrice.setTextColor(0xFF000000);
			}
			
		}
	}
	
	
	
	
	
 
	private void initDataSourceList() {
		wineList=new ArrayList();
		Map m1=new HashMap();
		m1.put("zixun_list_item_img", R.drawable.jiu1);
		m1.put("zixun_list_item_title", "法国原装进口葡萄酒瑞纳伯爵干红700ml");
		m1.put("juli", "200m  ");
		m1.put("zixun_list_item_desc", "赠酒刀一把，酒起一个");
		m1.put("tip1", R.drawable.tip1);
		m1.put("tip2", R.drawable.tip2);
		m1.put("price", "58元  ");
		
		Map m2=new HashMap();
		m2.put("zixun_list_item_img", R.drawable.jiu2);
		m2.put("zixun_list_item_title", "西班牙原瓶进口莫斯卡托起泡酒+香槟750ml");
		m2.put("juli", "250m  ");
		m2.put("zixun_list_item_desc", "爱之湾+蓝贵人组合装送2香槟杯");
		m2.put("tip1", R.drawable.tip2);
		m2.put("tip2", R.drawable.tip3);
		m2.put("price", "96元  ");
		
		Map m3=new HashMap();
		m3.put("zixun_list_item_img", R.drawable.jiu3);
		m3.put("zixun_list_item_title", "法国原装进口麦洛尊贵至享红酒750ml");
		m3.put("juli", "500m  ");
		m3.put("zixun_list_item_desc", "2012年国际葡萄酒大赛挑战获奖酒");
		m3.put("tip1", R.drawable.tip3);
		m3.put("tip2", R.drawable.tip4);
		m3.put("price", "128元  ");
		
		Map m4=new HashMap();
		m4.put("zixun_list_item_img", R.drawable.jiu4);
		m4.put("zixun_list_item_title", "解百纳10年珍藏酿造金色典雅600ml");
		m4.put("juli", "800m  ");
		m4.put("zixun_list_item_desc", "限购2瓶");
		m4.put("tip1", R.drawable.tip3);
		m4.put("tip2", R.drawable.tip4);
		m4.put("price", "221元  ");
		//
		Map m5=new HashMap();
		m5.put("zixun_list_item_img", R.drawable.jiu1);
		m5.put("zixun_list_item_title", "法国原装进口葡萄酒瑞纳伯爵干红700ml");
		m5.put("juli", "200m  ");
		m5.put("zixun_list_item_desc", "赠酒刀一把，酒起一个");
		m5.put("tip1", R.drawable.tip1);
		m5.put("tip2", R.drawable.tip2);
		m5.put("price", "58元  ");
		
		Map m6=new HashMap();
		m6.put("zixun_list_item_img", R.drawable.jiu2);
		m6.put("zixun_list_item_title", "西班牙原瓶进口莫斯卡托起泡酒+香槟750ml");
		m6.put("juli", "250m  ");
		m6.put("zixun_list_item_desc", "爱之湾+蓝贵人组合装送2香槟杯");
		m6.put("tip1", R.drawable.tip2);
		m6.put("tip2", R.drawable.tip3);
		m6.put("price", "96元  ");
		
		Map m7=new HashMap();
		m7.put("zixun_list_item_img", R.drawable.jiu3);
		m7.put("zixun_list_item_title", "法国原装进口麦洛尊贵至享红酒750ml");
		m7.put("juli", "500m  ");
		m7.put("zixun_list_item_desc", "2012年国际葡萄酒大赛挑战获奖酒");
		m7.put("tip1", R.drawable.tip3);
		m7.put("tip2", R.drawable.tip4);
		m7.put("price", "128元  ");
		
		Map m8=new HashMap();
		m8.put("zixun_list_item_img", R.drawable.jiu4);
		m8.put("zixun_list_item_title", "解百纳10年珍藏酿造金色典雅600ml");
		m8.put("juli", "800m  ");
		m8.put("zixun_list_item_desc", "限购2瓶");
		m8.put("tip1", R.drawable.tip3);
		m8.put("tip2", R.drawable.tip4);
		m8.put("price", "221元  ");
		
		wineList.add(m1);
		wineList.add(m2);
		wineList.add(m3);
		wineList.add(m4);
		wineList.add(m5);
		wineList.add(m6);
		wineList.add(m7);
		wineList.add(m8);
	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(WineListActivity.this, FilterDialog.class);			
				//Intent intent = new Intent(WineListActivity.this, ExampleActivity.class);			
				startActivityForResult(intent, 3);
				
			}
		});
	}
 

	@Override
	protected void registerListener() {
		tvSales.setOnClickListener(new TvClickListener());
		tvPrice.setOnClickListener(new TvClickListener());
		tvRenQi.setOnClickListener(new TvClickListener());
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Dialog dialog = new WineDetailDialog(WineListActivity.this, R.style.selectDialog,"责任人1",null,null,new WineDetailDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						//startActivity(new Intent(WineListActivity.this,
							//	OrderDetailActivity.class));
					}
				});
				 dialog.show();
				}

		});
	}
 
}
