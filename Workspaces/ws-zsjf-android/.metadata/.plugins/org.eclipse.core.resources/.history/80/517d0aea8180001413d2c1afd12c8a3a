package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.cattsoft.mos.R;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.JsonUtil;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.widget.PullToRefreshListView;

public class LicaiQueryResultActivity extends BasicActivity{
	
	private HashMap valueMap;
	
	private List proList=new ArrayList();
	
	private Button btnLicaiDetail=null;
	
	private PullToRefreshListView refreshListView; 
	private String[] key = {"pro_name", "pro_code","status",
	"exp_return_rate","pro_term_value"};
	
	SimpleAdapter simpleAdapter=null;
	private Button moreBtn=null;
	private int pageNo=1;
	String totalCount="0";
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.licai_chanpin_list);

		// 设置标题栏
		super.setTitleBar("查询结果",View.VISIBLE,View.VISIBLE,View.INVISIBLE,false);
		super.setTitleRightButtonImg(R.drawable.licai_query_orderby_low_normal);
		
		initData();
		initView();
		registerListener();
		super.showProcessDialog(true);
		query();
		ActivityUtil.getInstance().addActivity(this);

	}
	
	private void initData() {
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		valueMap=(HashMap)bundle.getSerializable("valueMap");
		valueMap.put("page", pageNo);
		
		simpleAdapter=new SimpleAdapter(LicaiQueryResultActivity.this,  proList,
				R.layout.licai_chanpin_list_item, key, new int[] {
				R.id.pro_name,
				R.id.pro_code,
				R.id.valid_yn,
				R.id.exp_return_rate,
				R.id.pro_term_value
		});
		
	}

	@Override
	protected void initView() {
		btnLicaiDetail=(Button)findViewById(R.id.licai_detail);
		refreshListView = (PullToRefreshListView) findViewById(R.id.prodList);
		refreshListView.setOnScrollListener(mScrollListener);
		refreshListView.setAdapter(simpleAdapter);
		
		moreBtn = new Button(this);
		moreBtn.setWidth(android.view.ViewGroup.LayoutParams.FILL_PARENT);
		moreBtn.setBackgroundColor(0XFFF6F6F6);
		moreBtn.setTextColor(0XFF444242);
		moreBtn.setText("加 载 中...");
		moreBtn.setTextSize(16);
	}

	@Override
	protected void registerListener() {
		btnLicaiDetail.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				queryDetail();
			}
		});
		
		refreshListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap m=(HashMap)proList.get(arg2);
//				Intent intent = new Intent(LicaiQueryResultActivity.this, LicaiDetailActivity.class);	
				Intent intent = new Intent(LicaiQueryResultActivity.this, ProcDetailActivity.class);	
				Bundle b=new Bundle();
				b.putSerializable("valueMap", m);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
		
	}
	
	private void query() {
		new Thread() {
			public void run() {
					String res = getPostHttpContent("", Constant.licai_query,  com.alibaba.fastjson.JSONObject.toJSONString(valueMap));
					if ("1".equals(JsonUtil.getResponseHead(
							res).getString("flag"))) {
						LicaiQueryResultActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS((JsonUtil
								.getResponseHead(res))
								.getString("desc")));
						Message m = new Message();
						m.what=-1;
						handlermain.sendMessage(m);
						return;
					}
					Message m = new Message();
					m.what = 1;
					m.obj = res;
					handlermain.sendMessage(m);
			}
		}.start();
	}

	
	OnScrollListener mScrollListener = new OnScrollListener() {
		boolean isLastRow = false; 

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			
			if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) { 
				if((pageNo-1)*20<Integer.parseInt(totalCount)) {
					query();
				}
				
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			 if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) { 
				 isLastRow=true;
			 }else{
				 isLastRow=false;
			 }
		
		}
	};
	
	private Handler handlermain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				com.alibaba.fastjson.JSONObject jsoncontent=JsonUtil.getResponseContentJSON((String)msg.obj);
				com.alibaba.fastjson.JSONArray jarrPro=jsoncontent.getJSONArray("proList");
				totalCount=jsoncontent.getString("count");
				List aproList=com.alibaba.fastjson.JSONArray.parseArray(jarrPro.toJSONString(), HashMap.class);
				proList.addAll(aproList);
				
				if(pageNo*20>=Integer.parseInt(totalCount)) {
					moreBtn.setText("已无更多数据");
				}
				simpleAdapter.notifyDataSetChanged();
				refreshListView.removeFooterView(moreBtn);
				refreshListView.addFooterView(moreBtn);
				pageNo++;
			}
			case -1: {
			
			}
			}
			
			if (mProgressDialog != null)
				mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			
		}
	};
	
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String desc=(String)valueMap.get("desc");
				if("true".equals(desc)) {
					valueMap.put("desc", "false");
					LicaiQueryResultActivity.this.setTitleRightButtonImg(R.drawable.licai_query_orderby_up_normal);
				}else {
					valueMap.put("desc", "true");
					LicaiQueryResultActivity.this.setTitleRightButtonImg(R.drawable.licai_query_orderby_low_normal);
				}
				valueMap.put("orderBy", "exp_return_rate");
				LicaiQueryResultActivity.this.showProcessDialog(false);
				query();
				
			}
		
		});
	}
	
	
	private void queryDetail() {
		new Thread() {
			public void run() {
					com.alibaba.fastjson.JSONObject aj=new com.alibaba.fastjson.JSONObject();
					aj.put("proCode", "dongfangcaifu_506973");
					String res = getPostHttpContent("", Constant.licai_query_detail,  aj.toJSONString());
					if ("1".equals(JsonUtil.getResponseHead(
							res).getString("flag"))) {
						LicaiQueryResultActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS((JsonUtil
								.getResponseHead(res))
								.getString("desc")));
						Message m = new Message();
						m.what=-1;
						handlermain.sendMessage(m);
						return;
					}
					Message m = new Message();
					m.what = 2;
					m.obj = res;
					handlermain.sendMessage(m);
			}
		}.start();
	}
	
	
}
