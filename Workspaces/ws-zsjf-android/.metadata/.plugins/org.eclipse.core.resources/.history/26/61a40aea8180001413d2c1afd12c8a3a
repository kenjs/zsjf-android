package com.cattsoft.mos.activity;

import java.util.HashMap;
import java.util.List;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.widget.PullToRefreshListView;

/**
 * 理财计算器
 * @author xieyunchao
 *
 */
public class LicaiToolsActivity extends BasicActivity{
	
	private HashMap valueMap;
	
	private List proList=null;
	
	private Button btnLicaiDetail=null;
	
	private PullToRefreshListView refreshListView; 
	private String[] key = {"pro_name", "pro_code","valid_yn",
	"exp_return_rate","pro_term_value"};
	
	SimpleAdapter simpleAdapter=null;
	
	private Button btnCunKuan;
	
	private List moneyTypeList;
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_licai_tools);

		// 设置标题栏
		super.setTitleBar("理财工具",View.VISIBLE,View.GONE,View.INVISIBLE,false);
		initData();
		initView();
		registerListener();
		requestMoneyType();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	private void initData() {
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		
	}

	@Override
	protected void initView() {
		btnLicaiDetail=(Button)findViewById(R.id.licai_detail);
		refreshListView = (PullToRefreshListView) findViewById(R.id.prodList);
		btnCunKuan=(Button)findViewById(R.id.btn_cunkuan);
	}

	@Override
	protected void registerListener() {
		btnCunKuan.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LicaiToolsActivity.this, LIcaiTools4Cunkuan.class);	
				startActivity(intent);
			}
		});
		
	}
	
	private Handler handlermain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			
			case -1: {
				Toast.makeText(getApplicationContext(), "初始化缓存失败，请联系系统维护人员！", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(LicaiToolsActivity.this,
						HomeMainActivity.class));
			}
			}
			
		}
	};
	
	
	private void fetchMoneyTypes(String str){
		str=str.replace("(", "").replace(")", "");
		com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(str);
		com.alibaba.fastjson.JSONArray jarr=json.getJSONArray("Currency");
		try {
			new CacheProcess().initMoneyType(LicaiToolsActivity.this, jarr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 获取币种
	 */
	public void requestMoneyType() {
		//获取币种的缓存，如果为空，则重新去取
		final String moneyTypes=new CacheProcess().getMoneyTypesInSharedPreferences(LicaiToolsActivity.this,"moneyTypes");
		if(!StringUtil.isBlank(moneyTypes)) {
			moneyTypeList = com.alibaba.fastjson.JSONArray.parseArray(moneyTypes, HashMap.class);
		}
		
		//如果为空则去服务器端取
		if(moneyTypeList==null ||moneyTypeList.size()==0) {
			new Thread() {
				public void run() {
					String res = getPostHttpContent(Constant.moneyType, "",
							new com.alibaba.fastjson.JSONObject()
									.toJSONString());
						System.out.print("moneyTypesmoneyTypes="+res);
						//获取json中的数组
						fetchMoneyTypes(res);
					 
				}
			}.start();
		}
	}
 
	
	
}
