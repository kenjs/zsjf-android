package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.util.ActivityUtil;

/**
 * 重点关注
 * 
 * @author xieyunchao
 * 
 */
public class ZdgzMainActivity extends BasicActivity {

	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zdgz_main);

		// 设置标题栏
		super.setTitleBar("重点关注", View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
				false);

		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}

	private void initData() {
		
	}

	@Override
	protected void initView() {
		LinearLayout layout=(LinearLayout)findViewById(R.id.layout_items);
		List funNodeList=getRealFuncNodeList();
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);  // , 1是可选写的
		lp.setMargins(20, 20, 20, 0); 
		
		if(funNodeList!=null && funNodeList.size()>0) {
			for(int i=0;i<funNodeList.size();i++) {
				java.util.HashMap m=(java.util.HashMap)funNodeList.get(i);
				Button b=new Button(this);
				b.setText((String)m.get("funcNodeName"));
				b.setBackgroundResource(R.drawable.bg_item_zdgz);
				b.setLayoutParams(lp);
				b.setTag((String)m.get("funcNodeName"));
				b.setOnClickListener( new BtnClickListener());
				layout.addView(b);
			}
		}
	}
	
	
	private List getRealFuncNodeList() {
		List realList=new ArrayList();
		List alist=getFuncNodeListByUser();
		if(alist!=null  && alist.size()>0) {
			for(int i=0;i<alist.size();i++) {
				java.util.HashMap m=(java.util.HashMap)alist.get(i);
				String funcNodeName=(String)m.get("funcNodeName");
				if("重点业务发展日报（按客户群）".equals(funcNodeName)) {
					realList.add(m);
				}else if("重点业务发展日报 ".equals(funcNodeName)){
					realList.add(m);
				}else if("网格重点业务日报".equals(funcNodeName)) {
					realList.add(m);
				}else if("渠道网格重点业务日报".equals(funcNodeName)) {
					realList.add(m);
				}else if("行业部业务发展日报".equals(funcNodeName)) {
					realList.add(m);
				}else if("区级、市级业务发展日报".equals(funcNodeName)) {
					realList.add(m);
				}
			}
			
		}
		return realList;
	}

	private List getFuncNodeListByUser() {
		List funcNodeList = new ArrayList();
		String cacheInfo = new CacheProcess().getCacheValueInSharedPreferences(
				this, "cacheInfo");
		com.alibaba.fastjson.JSONObject j = com.alibaba.fastjson.JSONObject
				.parseObject(cacheInfo);

		com.alibaba.fastjson.JSONObject userCache = j
				.getJSONObject("suveJsonObject");
		com.alibaba.fastjson.JSONArray funcNodes = userCache
				.getJSONArray("sysUserFuncTree");
		if (funcNodes != null && funcNodes.size() > 0) {
			for (int i = 0; i < funcNodes.size(); i++) {
				com.alibaba.fastjson.JSONObject funcTree = funcNodes
						.getJSONObject(i);
				com.alibaba.fastjson.JSONArray jarr = funcTree
						.getJSONArray("userFuncNodeList");
				List afuncNodeList = com.alibaba.fastjson.JSON.parseArray(
						jarr.toJSONString(), HashMap.class);
				funcNodeList.addAll(afuncNodeList);
			}
		}
		return funcNodeList;
	}
	
	
	private class BtnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			String tag=(String)arg0.getTag();
			if(tag.equals("重点业务发展日报（按客户群）")) {
				Intent intent = new Intent(ZdgzMainActivity.this,
						Zdywrb4khq.class);
				intent.putExtra("whickReportFlag", "zdgz");
				startActivity(intent);
			}else if(tag.equals("重点业务发展日报 ")){
				Intent intent = new Intent(ZdgzMainActivity.this,
						Zdywrb4khq.class);
				intent.putExtra("whickReportFlag", "");
				startActivity(intent);
			}else if(tag.equals("网格重点业务日报")) {
				startActivity(new Intent(ZdgzMainActivity.this,
						WgZdywrb.class));
			}else if(tag.equals("渠道网格重点业务日报")) {
				startActivity(new Intent(ZdgzMainActivity.this,
						Qdwgzdywrb.class));
			}else if(tag.equals("行业部业务发展日报")) {
				startActivity(new Intent(ZdgzMainActivity.this,
						Hybywfzrb.class));
			}else if(tag.equals("区级、市级业务发展日报")) {
				startActivity(new Intent(ZdgzMainActivity.this,
						Qsywfzrb.class));
			}
		}
		
	}

	@Override
	protected void registerListener() {

	}

}
