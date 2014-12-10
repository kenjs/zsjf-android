package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cattsoft.mos.R;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.CommonUtil;
import com.cattsoft.mos.util.JsonUtil;
import com.cattsoft.mos.util.StringUtil;

public class ProcDetailActivity extends BasicActivity {

	private int screenW;
	private int perSpacing = 0;// 每个动画图片间距
	private List<View> views; // 存储页面列表
	private ImageView cursor;// 动画图片

	/** 页卡头标 */
	private ArrayList<TextView> pageTitles = new ArrayList<TextView>();
	private ViewPager mPager = null;
	private int currIndex = 0;// 当前页卡编号
	
	private String proCode="";
	private String detailInfoRes;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proc_detail);
		super.setTitleBar("产品详情", View.VISIBLE, View.GONE, View.INVISIBLE,
				false);
		initData();
		requestDetailInfo();
		
		initView();
		
		registerListener();

		ActivityUtil.getInstance().addActivity(this);

		// lt =
		// (LabelText)getLayoutInflater().inflate(R.layout.label_text_layout,
		// null);
	}
	
	
	private void requestDetailInfo() {
		super.showProcessDialog(true);
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						String response=""; 
						try {
							JSONObject requestJsonObject = new JSONObject();
							requestJsonObject.put("proCode", proCode);//头条
							//获取响应的结果信息
							String url=Constant.prodDetailInfo;
							detailInfoRes = getPostHttpContent("",url, requestJsonObject.toString());
							if("1".equals(JsonUtil.getResponseHead(detailInfoRes).getString("flag"))){
								ProcDetailActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS((JsonUtil.getResponseHead(detailInfoRes)).getString("desc")));
								return ;
							}
							
							
							if (StringUtil.isBlank(detailInfoRes)) {
								response = StringUtil.getAppException4MOS("未获得服务器响应结果！");
								ProcDetailActivity.this.sendExceptionMsg(response);
								return;
							}
							com.alibaba.fastjson.JSONObject contentJson=JsonUtil.getResponseContentJSON(detailInfoRes);
							
							Message m=new Message();
							m.what=1;
							m.obj=contentJson;
							handlermain.sendMessage(m);
							
						} catch (JSONException e) {
							e.printStackTrace();
							String err = StringUtil.getAppException4MOS("解析JSON出错！");
							ProcDetailActivity.this.sendExceptionMsg(err);
							return;
						}
					
					}
				});
		mThread.start();
	
	}
	
	private Handler handlermain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				reloadUI();
			}
			case 2:{
			}
			}
			if(mProgressDialog!=null)mProgressDialog.dismiss();
		}
	};

	private void initData() {
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		HashMap hm = (HashMap) b.getSerializable("valueMap");
		proCode = (String) hm.get("pro_code");
	}

	/**
	 * 获取屏幕宽度/4
	 */
	private int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenW = dm.widthPixels;// 获取屏幕宽度
		perSpacing = screenW / 3;

		return perSpacing;
	}

	/**
	 * 设置页卡标题点击事件监听器
	 */
	private void setPageTitleOnClickListener() {
		for (int i = 0; i < pageTitles.size(); i++) {
			pageTitles.get(i).setOnClickListener(new MyOnClickListener(i));
		}
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	private TextView getHeadText(String text) {
		TextView tv = new TextView(this);
		tv.setTextColor(0xFF1C1A19);
		tv.setTextSize(16.0f);
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.setHeight(CommonUtil.dipToPx(this, 45));
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.CENTER;
		tv.setLayoutParams(lp);
		tv.setGravity(Gravity.CENTER);

		tv.setWidth(perSpacing);
		tv.setText(text);
		return tv;
	}

	/**
	 * 初始化页卡标题
	 */
	private void initPageTitles() {
		pageTitles.clear();

		LinearLayout ll = (LinearLayout) findViewById(R.id.viewpage_title_layout);

		TextView tvBaseInfo = getHeadText("基本信息");
		TextView tvBuyInfo = getHeadText("购买信息");
		TextView tvFkInfo = getHeadText("风控信息");

		pageTitles.add(tvBaseInfo);
		pageTitles.add(tvBuyInfo);
		pageTitles.add(tvFkInfo);

		ll.addView(tvBaseInfo);
		ll.addView(tvBuyInfo);
		ll.addView(tvFkInfo);
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
//		mPager.setAdapter(new MyPagerAdapter(views));
		mPager.setCurrentItem(currIndex);
	}

	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {

		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public void notifyDataSetChanged() {
			super.notifyDataSetChanged();
		}


		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * 初始化动画，生成cursor图片
	 */
	private void initCursorImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		getScreenWidth();
		Bitmap bmp = Bitmap
				.createBitmap(perSpacing, 5, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		canvas.drawColor(0xFFA64361);
		cursor.setImageBitmap(bmp);
		setCursorPosition();
	}

	/**
	 * 设置cursor初始位置
	 */
	private void setCursorPosition() {
		Matrix matrix = new Matrix();
		matrix.postTranslate(0, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	private void fillScrollView(com.alibaba.fastjson.JSONObject contentJson,
			String pageName, LinearLayout scrollView) {
		com.alibaba.fastjson.JSONArray arr = contentJson.getJSONArray(pageName);
		for (int i = 0; i < arr.size(); i++) {
			com.alibaba.fastjson.JSONObject jojb = arr.getJSONObject(i);
			Iterator it = jojb.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = jojb.getString(key);
				System.out.println("key======" + key);
				System.out.println("value======" + value);
//				
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0);  // , 1是可选写的
				lp.setMargins(CommonUtil.dipToPx(this, 15), CommonUtil.dipToPx(this, 10), CommonUtil.dipToPx(this, 15), 0); 

				com.cattsoft.mos.view.LabelText lt = (com.cattsoft.mos.view.LabelText) getLayoutInflater()
						.inflate(R.layout.label_text, null);
				lt.setLayoutParams(lp);
				TextView tvLabel = lt.getLabelText();
				TextView tvValue = lt.getValueText();
				tvLabel.setText("     "+key+":   ");
				tvValue.setText(value);

				scrollView.addView(lt);
			}
		}

	}
	
	private void reloadUI() {
		
		com.alibaba.fastjson.JSONObject contentJson=JsonUtil.getResponseContentJSON(detailInfoRes);
		
		LayoutInflater mInflater = getLayoutInflater();

		View vBaseInfo = mInflater.inflate(R.layout.proc_detail_item, null);
		LinearLayout scrollBaseInfo = (LinearLayout) vBaseInfo
				.findViewById(R.id.info_item);
		
		fillScrollView(contentJson, "基本信息", scrollBaseInfo);
		views.add(vBaseInfo);
		
		View vBuyInfo = mInflater.inflate(R.layout.proc_detail_item, null);
		LinearLayout scrollBuyInfo = (LinearLayout) vBuyInfo
				.findViewById(R.id.info_item);
		fillScrollView(contentJson, "购买信息", scrollBuyInfo);
		views.add(vBuyInfo);
		
		View vFkInfo = mInflater.inflate(R.layout.proc_detail_item, null);
		LinearLayout scrollFkInfo = (LinearLayout) vFkInfo
				.findViewById(R.id.info_item);
		fillScrollView(contentJson, "风控信息", scrollFkInfo);
		views.add(vFkInfo);
		
		
		mPager.setAdapter(new MyPagerAdapter(views));
	}

	@Override
	protected void initView() {
		getScreenWidth();
		initPageTitles();
		mPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		
		initViewPager();

		initCursorImageView();
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		Animation animation = null;
		int currentX = 0;

		@Override
		public void onPageSelected(int arg0) {
			if (currIndex <= arg0) {
				animation = new TranslateAnimation(
						perSpacing * (arg0 - 1) < 0 ? 0 : perSpacing
								* (arg0 - 1), perSpacing * arg0, 0, 0);
			} else {
				animation = new TranslateAnimation(perSpacing * (arg0 + 1),
						perSpacing * arg0, 0, 0);
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

	}

	@Override
	protected void registerListener() {
		setPageTitleOnClickListener();
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

}
