package com.cattsoft.mos.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dataprocess.CacheProcess;

/**
 * 日通报
 * @author xieyunchao
 *
 */
public class DayReportActivity extends BasicActivity{
 
	private List plist=null;
	
	public List mathAdapterData(String pid) {
		List plist=new ArrayList();
		String cacheInfo=new CacheProcess().getCacheValueInSharedPreferences(this, "cacheInfo");
		com.alibaba.fastjson.JSONObject j=com.alibaba.fastjson.JSONObject.parseObject(cacheInfo);
		
		com.alibaba.fastjson.JSONObject userCache=j.getJSONObject("suveJsonObject");
		com.alibaba.fastjson.JSONArray funcNodes=userCache.getJSONArray("sysUserFuncTree");
		if(funcNodes!=null && funcNodes.size()>0) {
			for(int i=0;i<funcNodes.size();i++) {
				com.alibaba.fastjson.JSONObject funcTree=funcNodes.getJSONObject(i);
				String funcTreeName=funcTree.getString("nodeTreeName");
				String treeId=funcTree.getString("parentNodeTreeId");
				if(pid.equals(treeId)) {
				com.alibaba.fastjson.JSONArray jarr=funcTree.getJSONArray("userFuncNodeList");
				if(jarr!=null && jarr.size()>0) {
					plist.add(funcTree);
				}
				}
			}
		}
		return plist;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.day_report_activity);
		super.setTitleBar("日通报",View.VISIBLE,View.INVISIBLE,View.INVISIBLE,false);
		initView(); 
		
//		plist.add("魏");
//		plist.add("蜀");
//		plist.add("吴");
//		
		registerListener();
		final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
			//设置组视图的图片
			int[] logos = new int[] { R.drawable.wei, R.drawable.shu,R.drawable.wu, R.drawable.wei, R.drawable.shu,R.drawable.wu};
			//设置组视图的显示文字
		//	private String[] generalsTypes = new String[] { "魏", "蜀", "吴" };
			//子视图显示文字
//			private String[][] generals = new String[][] {
//					{ "夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修" },
//					{ "马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云" },
//					{ "吕蒙", "陆逊", "孙权", "周瑜", "孙尚香" }
//
//			};
			//子视图图片
			public int[][] generallogos = new int[][] {
					{ R.drawable.xiahoudun, R.drawable.zhenji,
							R.drawable.xuchu, R.drawable.guojia,
							R.drawable.simayi, R.drawable.yangxiu },
					{ R.drawable.machao, R.drawable.zhangfei,
							R.drawable.liubei, R.drawable.zhugeliang,
							R.drawable.huangyueying, R.drawable.zhaoyun },
					{ R.drawable.lvmeng, R.drawable.luxun, R.drawable.sunquan,
							R.drawable.zhouyu, R.drawable.sunshangxiang } };
			
			//自己定义一个获得文字信息的方法
			TextView getTextView() {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, 64);
				TextView textView = new TextView(
						DayReportActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				textView.setTextColor(Color.BLACK);
				return textView;
			}

			
			//重写ExpandableListAdapter中的各个方法
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return plist.size();
			}

			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				//return generalsTypes[groupPosition];
				return ((com.alibaba.fastjson.JSONObject)plist.get(groupPosition)).getString("nodeTreeName");
			}

			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				//return generals[groupPosition].length;
				return ((com.alibaba.fastjson.JSONObject)plist.get(groupPosition)).getJSONArray("userFuncNodeList").size();
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				//return generals[groupPosition][childPosition];
				return (((com.alibaba.fastjson.JSONObject)plist.get(groupPosition)).getJSONArray("userFuncNodeList").getJSONObject(childPosition)).getString("funcNodeName");
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout ll = new LinearLayout(
						DayReportActivity.this);
				ll.setOrientation(0);
				ImageView logo = new ImageView(DayReportActivity.this);
				//logo.setImageResource(logos[groupPosition]);
				logo.setPadding(50, 0, 0, 0);
				ll.addView(logo);
				TextView textView = getTextView();
				textView.setTextColor(Color.BLACK);
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);

				return ll;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout ll = new LinearLayout(
						DayReportActivity.this);
				ll.setOrientation(0);
				ImageView generallogo = new ImageView(
						DayReportActivity.this);
//				generallogo
//						.setImageResource(generallogos[groupPosition][childPosition]);
				ll.addView(generallogo);
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition, childPosition)
						.toString());
				ll.addView(textView);
				return ll;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list);
		expandableListView.setAdapter(adapter);
		
		
		//设置item点击的监听器
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				String reportName=(String)adapter.getChild(groupPosition, childPosition);
				if(reportName.equals("2G业务日报")) {
					startActivity(new Intent(DayReportActivity.this,
							G2ywrb.class));
				}else if(reportName.equals("宽带业务日报")){
					startActivity(new Intent(DayReportActivity.this,
							Kdywrb.class));
				}else if(reportName.equals("2G3G融合业务日报")) {
					startActivity(new Intent(DayReportActivity.this,
							G2G3rhrb.class));
				}else if(reportName.equals("行业部业务发展日报")) {
					startActivity(new Intent(DayReportActivity.this,
							Hybywfzrb.class));
				}else if(reportName.equals("行业部业务发展日报")) {
					startActivity(new Intent(DayReportActivity.this,
							Hybywfzrb.class));
				}else if(reportName.equals("区级、市级业务发展日报")) {
					startActivity(new Intent(DayReportActivity.this,
							Qsywfzrb.class));
				}else if(reportName.equals("3G业务日报")) {
						startActivity(new Intent(DayReportActivity.this,
								G3ywrb.class));
				}else if(reportName.equals("客户经理揽装日报")) {
					startActivity(new Intent(DayReportActivity.this,
							Khjllzrb.class));
				}else if(reportName.equals("各网点业务发展日报")) {
					startActivity(new Intent(DayReportActivity.this,
							Wdywfzrb.class));
				}else if(reportName.equals("重点业务发展日报 ")) {
					Intent intent = new Intent(DayReportActivity.this,
							Zdywrb4khq.class);
					intent.putExtra("whickReportFlag", "");
					startActivity(intent);
				}else if(reportName.equals("网格重点业务日报")) {
					startActivity(new Intent(DayReportActivity.this,
							WgZdywrb.class));
				}else if(reportName.equals("渠道网格重点业务日报")) {
					startActivity(new Intent(DayReportActivity.this,
							Qdwgzdywrb.class));
				}
				return false;
			}
		});
	}
	
	
	@Override
	protected void initView() {
		plist=	mathAdapterData("2");
		
	}
	
	

	
	

	@Override
	protected void registerListener() {
		
	}

}
