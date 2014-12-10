package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.util.JsonUtil;
import com.cattsoft.mos.util.StringUtil;

public class ZiXunDetailActivity extends BasicActivity {

	private TextView tvTitle = null;
	private TextView tvPublicTime = null;
	private TextView tvDetailInfo = null;

	private String responseResult = "";
	private String title;
	private String publicTime;
	private String detailInfo;
	private String source;
	
	private List textList=null;;

	Map intentMap = null;
	Map currentTextFontMap=null;
	SharedPreferences sharedPreferences=null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zixun_detail);

		super.setTitleBar("资讯详情", View.VISIBLE, View.VISIBLE, View.INVISIBLE,
				false);
		super.setTitleRightButtonImg(R.drawable.title_bar_font_setting_normal);
		initData();
		initView();
		registerListener();
		super.showProcessDialog(true);
		toLoadDetailInfo();
		ActivityUtil.getInstance().addActivity(this);
	}

	@Override
	protected void initView() {
		tvTitle = (TextView) findViewById(R.id.title);
		tvPublicTime = (TextView) findViewById(R.id.public_time);
		tvDetailInfo = (TextView) findViewById(R.id.detail_info);
		tvDetailInfo.setTextSize(StringUtil.isBlank(this.getCacheProcess().getCacheValueInSharedPreferences(this, "articleTextFontSize"))?19:Integer.parseInt(this.getCacheProcess().getCacheValueInSharedPreferences(this, "articleTextFontSize")));
	}

	private void initData() {
		currentTextFontMap=new HashMap();
		String fontSize=this.getCacheProcess().getCacheValueInSharedPreferences(this, "articleTextFontSize"); 
		if(StringUtil.isBlank(fontSize)) {
			currentTextFontMap.put("value","19");
		}else {
			currentTextFontMap.put("value",fontSize);
		}
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		intentMap = (Map) b.getSerializable("intentMap");
	}

	@Override
	protected void registerListener() {

	}

	private Handler handlermain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				loadDetailInfo();
			}
			case 2: {
			}
			}
			if (mProgressDialog != null)
				mProgressDialog.dismiss();// 当接到消息时，关闭进度条
		}
	};

	private void loadDetailInfo() {
		title = JsonUtil.getResponseContentJSON(responseResult).getString(
				"title");
		publicTime = JsonUtil.getResponseContentJSON(responseResult).getString(
				"publicTime");
		source = JsonUtil.getResponseContentJSON(responseResult).getString(
				"source");
		detailInfo = JsonUtil.getResponseContentJSON(responseResult).getString(
				"detailInfo");

		tvTitle.setText(title);
		tvPublicTime.setText(source + "  " + publicTime);
		tvDetailInfo.setText(Html.fromHtml(detailInfo));
	}

	private void toLoadDetailInfo() {
		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
					@Override
					public void run() {
						try {
							JSONObject requestJsonObject = new JSONObject();
							requestJsonObject.put("articleId",
									intentMap.get("articleId"));
							System.out.println("articleIdarticleIdarticleId="
									+ intentMap.get("articleId"));

							// 获取响应的结果信息
							String url = Constant.zixunDetail;
							responseResult = getPostHttpContent("", url,
									requestJsonObject.toString());
							if ("1".equals(JsonUtil.getResponseHead(
									responseResult).getString("flag"))) {
								ZiXunDetailActivity.this.sendExceptionMsg(StringUtil.getAppException4MOS((JsonUtil
										.getResponseHead(responseResult))
										.getString("desc")));
								return;
							}

							Message m = new Message();
							m.what = 1;
							handlermain.sendMessage(m);

						} catch (JSONException e) {
							e.printStackTrace();
							String err = StringUtil
									.getAppException4MOS("解析JSON出错！");
							ZiXunDetailActivity.this.sendExceptionMsg(err);
							return;
						}

					}
				});
		mThread.start();

	}
	
	
	private List initTextFontList() {
	 List alist=new ArrayList();
	 Map m1=new HashMap();
	 m1.put("value", "23");
	 m1.put("name", "特大号字");
	 
	 Map m2=new HashMap();
	 m2.put("value", "21");
	 m2.put("name", "大号字");
	 
	 Map m3=new HashMap();
	 m3.put("value", "19");
	 m3.put("name", "中等字号");
	 
	 Map m4=new HashMap();
	 m4.put("value", "17");
	 m4.put("name", "小号字");
	 
	 alist.add(m1);
	 alist.add(m2);
	 alist.add(m3);
	 alist.add(m4);
	 return alist;
	}
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				textList=initTextFontList();
				Dialog dialog = new SelectDialog(ZiXunDetailActivity.this, R.style.selectDialog,"正文字体",textList,currentTextFontMap,new SelectDialog.OnSelectItemgListener() {
					
					@Override
					public void refreshActivity(Map m) {
						tvDetailInfo.setTextSize(Integer.parseInt((String)m.get("value")));
						currentTextFontMap=m;
						Editor editor = sharedPreferences.edit();
						editor.putString("articleTextFontSize", (String)m.get("value"));   
						editor.commit();
					}
				});
				 dialog.show();
			}
		});
	}

}
