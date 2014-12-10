package com.cattsoft.mos.activity;

import android.app.Activity;


public class AllTaskListActivity extends Activity{
//	
//	
//	
//	private AllTaskListAdapterActivity simpleAdapter;
//	private PullToRefreshListView refreshListView;
//	private int pageNo;
//	private Button moreBtn;
//	private JSONObject requestJsonObject=new JSONObject();
//	private String reponseJson;
//	private Intent intent;
//	private int message;
//	private boolean hasFooterView;
//	private FilterMVO filter = new FilterMVO();
//	private boolean isRefresh;
//	private static final int BIZ_FILTER = 0;
//	private static final int ACT_FILTER = 1;
//	private static final int TIMEOUT_FILTER = 2;
//	/** 异步处理变量声明 */
//	private MSGHandler handler = null;
//
//	private String[] key = {
//			"all_task_list_item_img",
//			"all_task_warn_img", 
//			"all_task_list_item_wo_num", 
//			"all_task_list_item_acc_num", 
//			"all_task_list_item_busi_name",
//			"all_task_list_item_cust_name",
//			"all_task_list_item_phone", 
//			"all_task_list_item_time", 
//			"all_task_list_item_cust_addr", 
//			"detail", 
//			"all_task_list_item_localnet_id", 
//			"all_task_list_item_area_id",
//			"all_task_list_item_appl_date", 
//			"all_task_list_item_wo_type", 
//			"all_task_list_item_wo_type_name",
//			"all_task_list_item_so_cat", 
//			"all_task_list_item_ext_so_nbr",
//			"all_task_list_item_act_type_name",
//			"all_task_list_item_so_nbr",
//			"all_task_list_item_prod_id", 
//			"all_task_list_item_chg_serv_spec_id", 
//			"all_task_list_item_step_id",
//			"all_task_list_item_contactInfo",
//			"all_task_list_item_work_area_id",
//			"all_task_list_item_work_area_name"
//			};
//
//	// 底部菜单变量声明
//	private MenuItemData workOrderHandleToolBarItemData;
//	private GridView workOrderHandleToolBarGrid; // 底部菜单栏
//	private GridViewAdapter workOrderHandleToolBarAdapter; // 底部菜单栏适配器
//		
//	private PopupWindow menu,menu1;  //长按listview弹出的“设置默认工区”菜单
//	private LayoutInflater inflater;  
//	private View layout,layout1; //菜单布局
//	private String woNum,localNetId,workAreaId,contacInfo,woType,soCat,stepId;
//    private Long appDate;
//    private TelephonyManager manager;//电话监听
//    private boolean isReservation = false;//判断是否已经点击过呼叫按钮
//    private String isSystem = "false";
//      //将定单号传送到后台
//    private com.alibaba.fastjson.JSONObject infoJsonObject = new com.alibaba.fastjson.JSONObject();
//    private String isCanReturnurl="tm/MosSurveyAction.do?method=queryMOSSurvey4MOS";
//    private String soNbr,actType,extSoNbr;//订单号
//    private String isCanReturnJson;	
//    private String whichAction;//判断是回单之前是否竣工核实还是竣工核实之前是否竣工核实
//	
//	private boolean isMaterialReturn = false;//回单前，是否已经进行了材料回填
//	private boolean isReturn = false;//回单前，是否已经进行了竣工核实
//	private String materialStepId;//可以材料回填的环节id
//	private String provinceName=null;
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_all_task_list);
//		
//		super.setTitleBar(getString(R.string.title_work_order_handle),View.VISIBLE,View.VISIBLE,View.GONE,false);
//		materialStepId = this.getCacheProcess()
//				.getCacheValueInSharedPreferences(this, "materialStepId");
//		provinceName=this.getCacheProcess()
//				.getCacheValueInSharedPreferences(this, "provinceName");//使用掌上运维的省份
//		initView();
//		registerListener();
//		handler = new MSGHandler(this);
//		
//		rightButtonOnClick();
//		
//        initToolbarMenu();
// 
//        //实例化PopupWindow创建菜单  
//        initMenu(); 
//        initWoList();
//	}
//
//	private void initWoList() {
//		try {
//			requestJsonObject.put("sysUserId", this.getCacheProcess().getCacheValueInSharedPreferences(this, "sysUserId"));
//	        requestJsonObject.put("workAreaId", this.getCacheProcess().getCacheValueInSharedPreferences(this, "workAreaId"));
//	        requestJsonObject.put("workMode", this.getCacheProcess().getCacheValueInSharedPreferences(this, "workMode"));
//	        requestJsonObject.put("localNetId", this.getCacheProcess().getCacheValueInSharedPreferences(this, "localNetId"));
//	        requestJsonObject.put("areaId", this.getCacheProcess().getCacheValueInSharedPreferences(this, "areaId"));
//	        requestJsonObject.put("staffId", this.getCacheProcess().getCacheValueInSharedPreferences(this, "staffId"));
//	        resetPageNo();
//	        Intent intent = this.getIntent();
//			Bundle bundle = intent.getExtras();
//			/**
//			 * 此处判断是否刷新，如果从非主界面进入，带有请求刷新的标识，则刷新列表。
//			 * 如果从主界面进入，且列表中无数据，则接收主界面的数据.
//			 * 如果从非主界面进入，不带有请求刷新标识，则从缓存中加载列表。
//			 */
//			if (bundle != null && bundle.getBoolean("refreshFlag")) {
//				hasFooterView = false;//这是从别的页面进入，此时footerView尚未加载
//				refresh();
//			}else {
////				System.out.println("MosApp--"+mosApp);
////				System.out.println("wolist--"+mosApp.getWoList());
//				if (bundle != null) {
//					reponseJson = bundle.getString("reponseJsonObject");
//					JSONObject reponseJsonObject=new JSONObject(reponseJson);
//					isRefresh = true;
//			        getData(reponseJsonObject);
//					if(mosApp.getCount()==0) {
//						Toast.makeText(getApplicationContext(), "没有需要处理的工单！",Toast.LENGTH_SHORT).show();
//						return;
//					}
//		        }
//				refreshView();
//			}
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		}
//	}
//	
//	class MyPhoneStateListener extends PhoneStateListener {
//
//		int i=0;//第几次打电话系统会监听几次（具体原因不知道为什么），该变量用来只让页面跳转一次
//		@Override
//		public void onCallStateChanged(int state, String incomingNumber) {
//			if(mosApp.getPhoneFlag()==1)return;
//			if(isSystem!=null) {//4.0版本之后就不用判断是从那个地方打的电话了，因为4.0之后从那打的电话就会回到那个地方去
//			
//			switch (state) {
//			case TelephonyManager.CALL_STATE_IDLE:
//				if(isReservation&&i==1) {//如果没有标志位的话，会出现进入该页面就会触发挂断电话事件					           
//				                   //所以在触发打电话事件时，将该标志位更改之后，在这次挂断电话时就会触发挂断事件中我们想要走的代码				
//					if(!"SICHUAN".equals(provinceName)) {
//						Intent intent= new Intent(AllTaskListActivity.this,ReservationActivity.class);
//						Bundle bundle =  new Bundle();
//						bundle.putString("woNum", woNum);
//						bundle.putString("work_order", woNum);
//						bundle.putString("localNetId", localNetId);
//						bundle.putString("areaId", workAreaId);
//						bundle.putLong("appTime", appDate);
//						bundle.putString("soCat", soCat);
//						intent.putExtras(bundle);
//						startActivity(intent);
//					intent.putExtras(bundle);
//					startActivity(intent);
//					finish();
//					isSystem = null;
//					}
//					
//				}
//				break;
//			case TelephonyManager.CALL_STATE_RINGING:
//				break;
//			case TelephonyManager.CALL_STATE_OFFHOOK:
//				isReservation = true;
//				i++;
//			default:
//				break;
//			}
//		}else {
//			super.onCallStateChanged(state, incomingNumber);
//		}
//	}
//	}
//	
//	/**
//	 * 标题栏右侧按钮点击事件监听
//	 */
//	@Override
//	public void rightButtonOnClick() {
//		super.getTitleRightButton().setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				refresh();
//			}
//		});
//	}
//	
//	/**
//	 *  用于接收线程发送的消息
//	 */
//	static class MSGHandler extends Handler {
//		WeakReference<AllTaskListActivity> mActivity;
//		MSGHandler(AllTaskListActivity activity){
//			mActivity = new WeakReference<AllTaskListActivity>(activity);
//		}
//		@Override
//		public void handleMessage(Message msg) {
//			AllTaskListActivity activity = mActivity.get();
//			switch (msg.what) {
//			case 3: {//用于接收刷新的消息
//				activity.refreshView();
//			}break;
//			
//			case 4: {//用于接收更多按钮的线程消息
//				activity.refreshView();
//				activity.refreshListView.setSelection((activity.pageNo - 1) * 20 - 4);
//			}break;
//			
//			case 5: {//用于接收下拉刷新的消息
//				activity.refreshView();
//				((PullToRefreshListView) activity.getListView()).onRefreshComplete();
//			}break;
//			}
//			activity.closeProcessDialog();// 当接到消息时，关闭进度条
//		}
//	};
//
//	@Override
//	protected void dealWithException() {
//		if (hasFooterView) {
//			refreshListView.removeFooterView(moreBtn);
//			hasFooterView = false;
//		}
//		setAdapter(mosApp.getWoList());
//	}
//	
//	private void initToolbarMenu() {
//		String[] work_order_handle_toolbar_name_array = getResources().getStringArray(
//				R.array.work_order_handle_toolbar_name_array);
//		LevelListDrawable levelListDrawable = (LevelListDrawable) getResources()
//				.getDrawable(R.drawable.work_order_handle_toolbar_image_list);
//		workOrderHandleToolBarItemData = new MenuItemData(levelListDrawable,
//				work_order_handle_toolbar_name_array, work_order_handle_toolbar_name_array.length);
//
//		workOrderHandleToolBarGrid = (GridView) findViewById(R.id.GridView_work_order_handle_toolbar);
//		workOrderHandleToolBarAdapter = new GridViewAdapter(this, workOrderHandleToolBarItemData);
//		workOrderHandleToolBarGrid.setAdapter(workOrderHandleToolBarAdapter);
//		workOrderHandleToolBarGrid.setOnItemClickListener(new OnItemClickListener(){
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				switch (position) {
//
//				case 0:
//					// 业务筛选
//					showDialog(0);
//					break;
//				case 1:
//					// 动作筛选
//					showDialog(1);
//					break;
//				case 2:
//					// 超时筛选
//					showDialog(2);
//					break;
//				case 3:{
//					// 派单时间
//			        try {
//			        	resetPageNo();
//						if(filter.getSortSts()==0) {//点击时状态为0，即在默认状态下点击，为升序
//								requestJsonObject.put("culmName", Constant.SORT_ASGN_DATE_ASC);
//								filter.setSortSts(1);
//								
//								isRefresh = true;
//								showProcessDialog(false);
//								requestDataInTread(requestJsonObject, 3);
//						        workOrderHandleToolBarAdapter.notifyDataSetChanged();
//						}else if(filter.getSortSts()==1) {//点击时状态为1，即在升序状态下点击，为降序
//								requestJsonObject.put("culmName", Constant.SORT_ASGN_DATE_DESC);
//								filter.setSortSts(2);
//								
//								isRefresh = true;
//						        showProcessDialog(false);
//						        requestDataInTread(requestJsonObject, 3);
//						        workOrderHandleToolBarAdapter.notifyDataSetChanged();
//						}else if(filter.getSortSts()==2){//点击时状态为2，即在降序状态下点击，为默认
//								requestJsonObject.put("culmName", Constant.SORT_WO_NBR);
//								filter.setSortSts(0);
//								
//								isRefresh = true;
//						        showProcessDialog(false);
//						        requestDataInTread(requestJsonObject, 3);
//						        workOrderHandleToolBarAdapter.notifyDataSetChanged();
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				}break;
//				}
//			}
//		});
//	}
//	
//	/**
//	 * 创建对话框
//	 */
//	protected Dialog onCreateDialog(int id) {
//		return buildFilterDialog(id);
//	}
//
//	/**
//	 * 建立筛选对话框，使用此方法是为以后扩展对话框做好准备
//	 * 
//	 * @param id
//	 *            对话框编号
//	 * @return 对话框
//	 */
//	private Dialog buildFilterDialog(final int id) {
//		Dialog dialog;
//		if(filter.getFilterCanceled()[id]) {
//			filter.getDefaultFilterStatus()[id] = filter.getRemeberedFilterStatus()[id].clone();
//			filter.getFilterCanceled()[id] = false;
//		}else {
//			filter.getDefaultFilterStatus()[id] = new boolean[filter.getFilterChoice()[id].length];
//			filter.getRemeberedFilterStatus()[id] = filter.getDefaultFilterStatus()[id].clone();
//		}
//
//		Builder builder = new AlertDialog.Builder(this);
//		builder.setInverseBackgroundForced(true);   		
//		builder.setTitle(filter.getFilterTitle()[id]);
//		builder.setMultiChoiceItems(filter.getFilterChoice()[id], filter.getDefaultFilterStatus()[id],new OnMultiChoiceClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which,
//					boolean isChecked) {
//				filter.getDefaultFilterStatus()[id][which] = isChecked;
//			}
//		});
//		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				clearOtherDialog(id);
//
//				filter.getRemeberedFilterStatus()[id] = filter.getDefaultFilterStatus()[id].clone();
//				filter.getFilterItemSelected()[id] = false;
//				String filterSql=null;
//				StringBuilder selectedResult = new StringBuilder();
//				for(int i=0;i<filter.getDefaultFilterStatus()[id].length;i++) {
//					if(filter.getDefaultFilterStatus()[id][i]) {
//						selectedResult.append(filter.getFilterChoiceSql()[id][i]);
//						selectedResult.append(",");
//						filter.getFilterItemSelected()[id]=true;
//					}
//				}
//				if(!filter.getFilterItemSelected()[id]) {//没有选择任何选项
//					if(requestJsonObject.has(filter.getRequestSqlJsonKey()[id])) {//移除请求中的actTypeList
//						requestJsonObject.remove(filter.getRequestSqlJsonKey()[id]);
//					}
//				}
//				filterSql=selectedResult.toString();
//
//				try {
//					resetPageNo();
//					requestJsonObject.put(filter.getRequestSqlJsonKey()[id], filterSql);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				isRefresh = true;
//			    showProcessDialog(false);
//			    requestDataInTread(requestJsonObject, 3);
//
//				workOrderHandleToolBarAdapter.notifyDataSetChanged();
//			}
//		});                
//		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				filter.getFilterCanceled()[id] = true;
//				dialog.dismiss();
//				removeDialog(id);
//			}
//		});
//		dialog = builder.create();
//		return dialog;
//	}
//	
//	/**
//	 * 当点击当前筛选对话框确定时，清除其它筛选对话框
//	 * 
//	 * @param currentDialog
//	 *            当前对话框序号
//	 */
//	private void clearOtherDialog(int currentDialog) {
//		for(int i=0;i<filter.getFilterTitle().length;i++) {
//			if(i!=currentDialog) {
//				removeDialog(i);
//				filter.getFilterItemSelected()[i]=false;
//				filter.getFilterCanceled()[i]=false;
//				if(requestJsonObject.has(filter.getRequestSqlJsonKey()[i])) {//移除请求中的businessId
//					requestJsonObject.remove(filter.getRequestSqlJsonKey()[i]);
//				}
//			}
//		}
//	}
//	
//	private class GetDataTask extends AsyncTask<Void, Void, ArrayList<HashMap<String, Object>>> {
//
//		@Override
//		protected ArrayList<HashMap<String, Object>> doInBackground(
//				Void... params) {
//			// Simulates a background job.
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				;
//			}
//			// return mStrings;
//			return mosApp.getWoList();
//		}
//
//		@Override
//		protected void onPostExecute(ArrayList<HashMap<String, Object>> result) {
//			// mListItems.addFirst("Added after refresh...");
//
//			// Call onRefreshComplete when the list has been refreshed.
//
//			pullRefresh();
//			super.onPostExecute(result);
//		}
//	}
//
//	
//	/**
//	 * 动态添加删除工单列表下的更多按钮
//	 */
//	private void more() {
//		if(hasFooterView) {
//			refreshListView.removeFooterView(moreBtn);
//			hasFooterView = false;
//		}
//		buildMoreBtn();
//		if (mosApp.getWoList().size() < mosApp.getCount()) {
//			refreshListView.addFooterView(moreBtn);
//			hasFooterView = true;
//		}else if(mosApp.getWoList().size() != 0 && mosApp.getWoList().size() == mosApp.getCount()) {
//			moreBtn.setText("已经是最后一条……");
//			moreBtn.setClickable(false);
//			refreshListView.addFooterView(moreBtn);
//			hasFooterView = true;
//		}
//	}
//
//	/**
//	 * 创建更多按钮
//	 */
//	private void buildMoreBtn() {
//		moreBtn = new Button(this);
//		moreBtn.setWidth(android.view.ViewGroup.LayoutParams.FILL_PARENT);
//		moreBtn.setBackgroundColor(0XFFF6F6F6);
//		moreBtn.setTextColor(0XFF444242);
//		moreBtn.setText("更  多");
//		moreBtn.setTextSize(16);
//		moreBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				moreBtn.setText("正在加载……");
//				moreBtn.setClickable(false);
//				pageNo++;
//				try {
//					requestJsonObject.put("pageNo", pageNo);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				requestDataInTread(requestJsonObject,4);
//			}
//		});
//	}
//
//
//	/**
//	 * 线程请求数据
//	 * 
//	 * @param requestData
//	 *            请求内容
//	 * @param messages
//	 *            线程消息msg.what
//	 */
//	private void requestDataInTread(final JSONObject requestData,
//			final int messages) {
//		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
//					@Override
//					public void run() {
//						Message msg = new Message();
//						getData(getResponseJsonObject(requestData, messages));
//						msg.what = message;
//						handler.sendMessage(msg);
//					}
//				});
//		mThread.start();
//	}
//	
//	/**
//	 * 刷新工单列表，非按刷新时间取值，每次刷新重新请求整个列表
//	 * 正常刷新
//	 */
//	private void refresh(){
//		isRefresh=true;
//		resetPageNo();
//		showProcessDialog(false);
//		requestDataInTread(requestJsonObject, 3);
//	}
//	/**
//	 * 刷新工单列表，非按刷新时间取值，每次刷新重新请求整个列表
//	 * 下拉刷新
//	 */
//	private void pullRefresh() {
//		isRefresh=true;
//		resetPageNo();
//		requestDataInTread(requestJsonObject, 5);
//	}
//
//	/**
//	 * 重置分页页码
//	 */
//	private void resetPageNo() {
//		pageNo=1;
//		try {
//			requestJsonObject.put("pageNo", pageNo);
//		} catch (JSONException e) {
//			Log.d("【AllTaskListActivity】", "resetPageNo方法JSON异常");
//			e.printStackTrace();
//		}
//	}
//
//
//	/**
//	 * 查询工单列表，获得服务器返回的结果
//	 * 
//	 * @param requestJsonObject
//	 *            需要传递的请求参数
//	 * @param msg
//	 *            线程中使用时，正常状态时传递的message.what
//	 * @return 返回值的JSONObject对象
//	 * @author maxun
//	 */
//	private JSONObject getResponseJsonObject(JSONObject requestJsonObject,int msg){
//		reponseJson = getPostHttpContent("tm/WoHandleAction.do?method=initWo4MOS", requestJsonObject.toString());
//		if(StringUtil.isExcetionInfo(reponseJson)) {
//			mosApp.setWoList(new ArrayList<HashMap<String, Object>>());
//			mosApp.setCount(0);
////			refreshView();
//			AllTaskListActivity.this.sendExceptionMsg(reponseJson);
//			pageNo--;
//			return null;
//		}
//		if(reponseJson!=null) {
//			JSONObject reponseJsonObject;
//			try {
//				reponseJsonObject = new JSONObject(reponseJson);
//				message=msg;
//				return reponseJsonObject;
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//		}
//		pageNo--;
//		return null;
//	}
//	
//	/**
//	 *  获得工单列表数据
//	 * @param reponseJsonObject
//	 * 		服务器返回值的JSONObject
//	 * @author maxun
//	 */
//	private void getData(JSONObject reponseJsonObject) {
//		if(reponseJsonObject!=null) {
//			JSONArray reponseJsonArray=JsonUtil.getJSONArray(reponseJsonObject, "viewList");
//			if(reponseJsonArray!=null) {
//				for(int i=0;i<reponseJsonArray.length();i++){
//					JSONObject woMVOJsonObject;
//					try {
//						woMVOJsonObject = reponseJsonArray.getJSONObject(i);
//						HashMap<String, Object> map = new HashMap<String, Object>();
//						if (("P").equals(JsonUtil.getTrimString(woMVOJsonObject,"woType"))) {
//							map.put(key[0], R.drawable.all_task_list_item_kimg);
//						}else if (("拆").equals(JsonUtil.getString(woMVOJsonObject,"actTypeName"))) {
//							map.put(key[0], R.drawable.all_task_list_item_cimg);
//							map.put(key[17], JsonUtil.getString(woMVOJsonObject,"actTypeName"));
//						}else if(("修").equals(JsonUtil.getString(woMVOJsonObject,"actTypeName"))) {
//							map.put(key[0], R.drawable.all_task_list_item_ximg);
//							map.put(key[17], JsonUtil.getString(woMVOJsonObject,"actTypeName"));
//						}else if(("装").equals(JsonUtil.getString(woMVOJsonObject,"actTypeName"))) {
//							map.put(key[0], R.drawable.all_task_list_item_zimg);
//							map.put(key[17], JsonUtil.getString(woMVOJsonObject,"actTypeName"));
//						}
//						if (("A").equals(JsonUtil.getString(woMVOJsonObject,"alarmSts"))) {
//							map.put(key[1], R.drawable.all_task_warn_hongimg);
//						}else if(("P").equals(JsonUtil.getString(woMVOJsonObject,"alarmSts"))) {
//							map.put(key[1], R.drawable.all_task_warn_huaimg);
//						}else {
//							map.put(key[1], R.drawable.blank);
//						}
//						map.put(key[2], JsonUtil.getTrimString(woMVOJsonObject,"woNbr"));
//						map.put(key[3], JsonUtil.getTrimString(woMVOJsonObject,"accNbr"));
//						if(JsonUtil.getTrimString(woMVOJsonObject,"businessName")!=null&&
//						   JsonUtil.getTrimString(woMVOJsonObject,"businessName").length()>9){
//							map.put(key[4], JsonUtil.getTrimString(woMVOJsonObject,"businessName").substring(0, 8)+"…");
//						}else{
//							map.put(key[4], JsonUtil.getTrimString(woMVOJsonObject,"businessName"));
//						}
//						//首先判断是否为空且长度是否合适，其次还需判断是否为空，2013/12/20
//						if(JsonUtil.getTrimString(woMVOJsonObject,"custName")!=null&&
//								   JsonUtil.getTrimString(woMVOJsonObject,"custName").length()<5) {
//							map.put(key[5], JsonUtil.getTrimString(woMVOJsonObject,"custName"));
//						}else if(JsonUtil.getTrimString(woMVOJsonObject,"custName")!=null){
//							map.put(key[5], JsonUtil.getTrimString(woMVOJsonObject,"custName").substring(0, 4)+"…");
//						}else{
//							map.put(key[5], "");
//						}
//						if(JsonUtil.getTrimString(woMVOJsonObject,"contactInfo")!=null&&
//								   JsonUtil.getTrimString(woMVOJsonObject,"contactInfo").length()<12) {
//							map.put(key[6], JsonUtil.getTrimString(woMVOJsonObject,"contactInfo"));
//						}else if(JsonUtil.getTrimString(woMVOJsonObject,"contactInfo")!=null){
//							map.put(key[6], JsonUtil.getTrimString(woMVOJsonObject,"contactInfo").substring(0, 11));
//						}else {
//							map.put(key[6], "");
//						}
//						
//						if(JsonUtil.getDateByLong(woMVOJsonObject,"bookTime")!=null){
//							map.put(key[7], JsonUtil.getLong(woMVOJsonObject,"bookTime"));
//						}
//						
//						map.put(key[8], JsonUtil.getTrimString(woMVOJsonObject,"situated"));
//						map.put(key[9], R.drawable.detail);
//						map.put(key[10], JsonUtil.getTrimString(woMVOJsonObject,"localNetId"));
//						map.put(key[11], JsonUtil.getTrimString(woMVOJsonObject,"areaId"));
//						map.put(key[12], JsonUtil.getLong(woMVOJsonObject,"applDate"));
//						map.put(key[13], JsonUtil.getTrimString(woMVOJsonObject,"woType"));
//						map.put(key[14], JsonUtil.getTrimString(woMVOJsonObject,"woTypeName"));
//						map.put(key[15], JsonUtil.getTrimString(woMVOJsonObject,"soCat"));
//						map.put(key[16], JsonUtil.getTrimString(woMVOJsonObject,"extSoNbr"));
//						map.put(key[18], JsonUtil.getTrimString(woMVOJsonObject,"soNbr"));
//						map.put(key[19], JsonUtil.getTrimString(woMVOJsonObject,"prodId"));
//						map.put(key[20], JsonUtil.getTrimString(woMVOJsonObject,"chgServSpecId"));
//						map.put(key[21], JsonUtil.getTrimString(woMVOJsonObject,"stepId"));
//						map.put(key[22], JsonUtil.getString(woMVOJsonObject, "contactInfo"));
//						map.put(key[23], JsonUtil.getTrimString(woMVOJsonObject, "workAreaId"));
//						map.put(key[24], JsonUtil.getTrimString(woMVOJsonObject, "workAreaName"));
//						if(mosApp.getWoList()==null) {
//							mosApp.setWoList(new ArrayList<HashMap<String, Object>>());
//						}
//						if(isRefresh) {
//							mosApp.setWoList(new ArrayList<HashMap<String, Object>>());
//							isRefresh=false;
//						}
//						mosApp.getWoList().add(map);
//					} catch (JSONException e) {
//						Log.d("【AllTaskListActivity】", "getData解析json异常");
//						e.printStackTrace();
//					}
//				}
//			}
//			mosApp.setCount(JsonUtil.getInt(reponseJsonObject, "count"));
//		}
//	}
//
//	// date数据类型
//	public class MenuItemData {
//		private LevelListDrawable mLevelListDrawable;
//		private String mTitleID[];
//		private int mCount;
//
//		public MenuItemData(LevelListDrawable levelListDrawable,
//				String titleID[], int count) {
//			refreshData(levelListDrawable, titleID, count);
//		}
//
//		public void refreshData(LevelListDrawable levelListDrawable,
//				String titleID[], int count) {
//			mLevelListDrawable = levelListDrawable;
//			mTitleID = titleID;
//			mCount = count;
//		}
//
//		public String getTitle(int index) {
//			return mTitleID[index];
//		}
//
//		public Drawable getDrawable(int index) {
//			mLevelListDrawable.setLevel(index);
//			Drawable drawable = mLevelListDrawable.getCurrent();
//			return drawable;
//		}
//
//		public int getCount() {
//			return mCount;
//		}
//	}
//	// GridViewAdapter 适配器
//	public class GridViewAdapter extends BaseAdapter {
//
//		Context mContext;
//		LayoutInflater mLayoutInflater;
//		MenuItemData mMenuItemData;
//
//		public GridViewAdapter(Context context, MenuItemData menuItemData) {
//			mContext = context;
//			mLayoutInflater = LayoutInflater.from(context);
//			mMenuItemData = menuItemData;
//		}
//
//		public void refreshData(MenuItemData menuItemData) {
//			mMenuItemData = menuItemData;
//			notifyDataSetChanged();
//		}
//
//		@Override
//		public int getCount() {
//			return mMenuItemData.getCount();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			
//			return position;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@SuppressLint("ResourceAsColor")
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			if (convertView == null) {
//				convertView = mLayoutInflater.inflate(
//						R.layout.work_order_handle_toolbar_item, null);
//			}
//			ImageView imageView = (ImageView) convertView.findViewById(R.id.toolbar_item_image);
//			if(position==0) {
//				if(filter.getFilterItemSelected()[BIZ_FILTER]) {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_busi_pressed));
//				}else {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_busi));
//				}
//			}else if(position==1) {
//				if(filter.getFilterItemSelected()[ACT_FILTER]) {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_action_pressed));
//				}else {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_action));
//				}				
//			}else if(position==2) {
//				if(filter.getFilterItemSelected()[TIMEOUT_FILTER]) {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_timeout_pressed));
//				}else {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_timeout));
//				}
//			}else if(position==3) {
//				if(filter.getSortSts()==0) {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_sendorder_normal));
//				}else if(filter.getSortSts()==1) {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_sendorder_asc));
//				}else if(filter.getSortSts()==2) {
//					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.wo_toolbar_sendorder_desc));
//				}
//			}
//			return convertView;
//		}
//	}
//
//	/**
//	 * 设置list的适配器
//	 * @param woList
//	 * @author maxun
//	 */
//	private void setAdapter(List<HashMap<String, Object>> woList) {
//		simpleAdapter = new AllTaskListAdapterActivity(this, woList, R.layout.list_item,
//				key, new int[] { R.id.all_task_list_item_img,R.id.all_task_warn_img,R.id.all_task_list_item_wo_num,R.id.all_task_list_item_acc_num,
//						R.id.all_task_list_item_busi_name,R.id.all_task_list_item_cust_name,R.id.all_task_list_item_phone,
//						R.id.all_task_list_item_time,R.id.all_task_list_item_cust_addr,R.id.detail,R.id.all_task_list_item_localnet_id,R.id.all_task_list_item_area_id,R.id.all_task_list_item_appl_date,R.id.all_task_list_item_wo_type,R.id.all_task_list_item_wo_type_name,R.id.all_task_list_item_so_cat,R.id.all_task_list_item_ext_so_nbr,R.id.all_task_list_item_act_type_name,R.id.all_task_list_item_so_nbr,R.id.all_task_list_item_prod_id,R.id.all_task_list_item_chg_serv_spec_id,R.id.all_task_list_item_step_id,R.id.all_task_list_item_work_area_id,R.id.all_task_list_item_work_area_name});
//		setListAdapter(simpleAdapter);
//	}
//	 private void initMenu(){  
//		  
//         //获取LayoutInflater实例  
//         inflater  = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);  
//         //获取弹出菜单的布局  
//         layout = inflater.inflate(R.layout.wo_handle_long_click,null);
//         
//         //设置popupWindow的布局  
//         layout = inflater.inflate(R.layout.wo_handle_long_click,null); 
//         layout1 = inflater.inflate(R.layout.wo_handle_long_click1,null);
//         TextView replayOrderTextView = (TextView) layout.findViewById(R.id.reply_order_text); 
//         TextView returnOrderTextView = (TextView) layout.findViewById(R.id.return_order_text);
//         TextView returnVisitTextView = (TextView) layout.findViewById(R.id.return_visit_text);
//         TextView callTextView = (TextView) layout.findViewById(R.id.call_text);
//         //设置popupWindow的布局  
//     	String funcListJson =this.getCacheProcess()
//     			.getCacheValueInSharedPreferences(this, "funcNodeList");
//     	com.alibaba.fastjson.JSONArray funcArray=com.alibaba.fastjson.JSONArray.parseArray(funcListJson);
//     	if(funcArray!=null && funcArray.size()>0) {
//     		for(int i=0;i<funcArray.size();i++) {
//    			com.alibaba.fastjson.JSONObject json=(com.alibaba.fastjson.JSONObject)funcArray.get(i);
//    			MosFuncNodeSVO funcSVO=JSON.parseObject(json.toJSONString(), MosFuncNodeSVO.class);
//    			String type=funcSVO.getType();
//    			String funcShortName=funcSVO.getShortName();
//    			if("D".equals(type)) {
//    				if("HD".equals(funcShortName)) {
//    					replayOrderTextView.setVisibility(View.VISIBLE);
//    				}else if("TD".equals(funcShortName)) {
//    					returnOrderTextView.setVisibility(View.VISIBLE);
//    				}else if("JGHS".equals(funcShortName)) {
//    					returnVisitTextView.setVisibility(View.VISIBLE);
//    				}else if("HJ".equals(funcShortName)) {
//    					callTextView.setVisibility(View.VISIBLE);
//    				}
//    			}
//    		}
//     	}
//		
//         menu = new PopupWindow(layout, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT); 
//         menu1 = new PopupWindow(layout1, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT); 
//         //菜单的监听事件。。。。
//        
//         TextView infoBackFillTextView = (TextView) layout1.findViewById(R.id.info_back_fill_text);
//         replayOrderTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				 if("N".equals(woType)){//第一个按钮为正常回单
//						
//						//环节，装机公司（装）
//						if(StringUtil.isBlank(materialStepId) || (!StringUtil.isBlank(materialStepId) && materialStepId.indexOf(stepId) != -1)){
//					   	  //判断是否已经回填
//						  whichAction = "backOrder";
//						  new materialReturnThread().start();
//						}else{
//							if (!(soCat.endsWith(Constant.SO_CAT_SPS) && actType.endsWith("拆"))) {
//								whichAction = "backOrder";
//								new returnThread().start();
//							}else{
//								Intent intent0 = new Intent();
//								if (Constant.SO_CAT_SPS.equals(soCat)) {
//									intent0.setClass(AllTaskListActivity.this,
//											BackOrderSuccessActivity.class);
//								} else if (Constant.SO_CAT_SGS.equals(soCat)) {
//									intent0.setClass(AllTaskListActivity.this,
//											BackSgsOrderSuccessActivity.class);
//								}
//								intent0.putExtra("woNbr", woNum);
//								intent0.putExtra("soCat", soCat);
//								startActivity(intent0);
//							}
//							
////							if(!(soCat.endsWith(Constant.SO_CAT_SPS)&&actType.endsWith("拆"))) {
////								new backOrderIsCanReturn().start();
////							}
//						}
//				 }else if ("P".equals(woType)){
//					 Intent intent0 = new Intent();
//					 intent0.setClass(AllTaskListActivity.this,
//							 InfoBackWorkOrderActivity.class);
//					 intent0.putExtra("woNbr", woNum);
//					 startActivity(intent0);
//				 }
//				 if(menu.isShowing()) {
//		    			menu.dismiss();
//		    		}
//			}
//         });
//         returnOrderTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//					Intent intent1 = new Intent();
//					intent1.setClass(AllTaskListActivity.this,
//							BackOrderFailActivity.class);
//					intent1.putExtra("woNbr", woNum);
//					startActivity(intent1);
//					if(menu.isShowing()) {
//		    			menu.dismiss();
//		    		}
//				
//			}
//        	 
//         });
//         returnVisitTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//				new isCanReturnJsonThread().start();
//				
//				
//			}
//         });
//         callTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				manager = (TelephonyManager) AllTaskListActivity.this.getSystemService(TELEPHONY_SERVICE);
//				manager.listen(new MyPhoneStateListener(),
//						PhoneStateListener.LISTEN_CALL_STATE);
//				mosApp.setPhoneFlag(0);//
//				String telNum = contacInfo;
//				Pattern p = null; // 正则表达式 Matcher m = null; //操作的字符串
//				Matcher m = null; // 操作的字符串
//				List<String> contacInfoList = new ArrayList<String>();
//				String tempStr = new String();
//				p = Pattern.compile("((\\+?86)?1\\d{10})|((\\(?(0[12]\\d)\\)?|\\(?(0[3-9]\\d{2})\\)?)?-?[2-9]\\d{6,7})");
//				m = p.matcher(contacInfo);
//				while(m.find()) {
//					tempStr = m.group();
//					tempStr = tempStr.replaceAll("[-\\(\\)]", "");
//					contacInfoList.add(tempStr);
//				}
//				if (contacInfoList.size()==0) {
//					Toast.makeText(getApplicationContext(),
//							"无效的联系方式！", Toast.LENGTH_SHORT)
//							.show();
//					if(menu.isShowing()) {
//		    			menu.dismiss();
//		    		}
//					return;
//				} else {
//					
//					Intent intent = new Intent(Intent.ACTION_DIAL, Uri
//							.parse("tel:" + contacInfoList.get(0).toString()));
//					// 开始呼叫
//					startActivity(intent);
//					isReservation = true;
//					if(menu.isShowing()) {
//		    			menu.dismiss();
//		    		}
//				}
//			}
//        	 
//         });
//         infoBackFillTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				 Intent intent0 = new Intent();
//				 intent0.setClass(AllTaskListActivity.this,
//						 InfoBackWorkOrderActivity.class);
//				 intent0.putExtra("woNbr", woNum);
//				 startActivity(intent0);
//				 if(menu1.isShowing()) {
//		    			menu1.dismiss();
//		    		}
//			}
//        	 
//         });
//	 }
//   
//	//判断按键 菜单的显示与隐藏  
//	    public boolean onKeyDown(int keyCode, KeyEvent event) {
//	    	  if(keyCode == KeyEvent.KEYCODE_BACK) {
//	    		if(menu.isShowing()) {
//	    			menu.dismiss();
//	    		}else if(menu1.isShowing()){
//	    			menu1.dismiss();
//	    			
//	    		}else {
//	    			super.onKeyDown(keyCode, event);
//	    		}
//	    	  }
//			  return true;   	
//	    }
//	    public boolean onTouchEvent(MotionEvent event) {
//	    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
//	    		menu.dismiss(); 
//	    	}
//			return true;
//	    	
//	    }
//
//	    private void initFilter() throws JSONException {
//	    	CacheProcess cocheProcess = new CacheProcess();
//	    	String business = cocheProcess.getCacheValueInSharedPreferences(this, "business");
//	    	if(!StringUtil.isBlank(business)){
//		    	JSONArray businessArray = new JSONArray(business);
//		    	String businessName[] = new String[businessArray.length()];
//		    	String businessId[] = new String[businessArray.length()];
//		    	for(int i=0;i<businessArray.length();i++){				
//					JSONObject businessJson = businessArray.getJSONObject(i);
//					businessName[i] = JsonUtil.getString(businessJson, "Name");
//					businessId[i] = JsonUtil.getString(businessJson, "Id");
//				}
//
//		    	filter.setFilterChoice(new String[][] {	businessName , { "装", "拆", "修", "外勘" },	{ "超时预警", "超时告警" } });
//		    	filter.setFilterChoiceSql(new String[][] {businessId, {"A", "R", "M", "P"}, {"P", "A"}});
//		    	filter.setRequestSqlJsonKey(new String[] {"businessId","actTypeList","alarmSts"});
//	    	}else{
//		    	filter.setFilterChoice(new String[][] {	{ },{"装", "拆", "修", "外勘"},	{"超时预警", "超时告警"} });
//		    	filter.setFilterChoiceSql(new String[][] {{ }, {"A", "R", "M", "P"}, {"P", "A"}});
//		    	filter.setRequestSqlJsonKey(new String[] {"businessId","actTypeList","alarmSts"});
//	    	}
//	    	filter.setFilterTitle(new String[] {"业务筛选", "动作筛选", "超时筛选"});
//	    	filter.setFilterItemSelected(new boolean[filter.getFilterTitle().length]);
//	    	filter.setFilterCanceled(new boolean[filter.getFilterTitle().length]);
//	    	filter.setDefaultFilterStatus(new boolean[filter.getFilterTitle().length][]);
//	    	filter.setRemeberedFilterStatus(new boolean[filter.getFilterTitle().length][]);
//	    }
//	    
//		@Override
//		protected void initView() {
//			try {
//				initFilter();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				String err = StringUtil.getAppException4MOS("解析JSON出错！");
//				AllTaskListActivity.this.sendExceptionMsg(err);
//			}
//			refreshListView = (PullToRefreshListView) getListView();
//		}
//
//		@Override
//		protected void registerListener() {
//			refreshListView.setOnItemClickListener(new OnItemClickListener() {
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//						long arg3) {
//					if(menu.isShowing()) {
//				    	menu.dismiss();
//				    }
//					if(menu1.isShowing()) {
//				    	menu1.dismiss();
//				    }
//					refreshListView.onRefreshComplete();//结束下拉刷新
//					intent=new Intent(AllTaskListActivity.this,WoDetailActivity.class);
//					Bundle bundle = new Bundle();
//					bundle.putString("work_order", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_wo_num"));
//					bundle.putString("localNetId", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_localnet_id"));
//					bundle.putString("areaId", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_area_id"));
//					bundle.putLong("applDate",  (Long) mosApp.getWoList().get(arg2-1).get("all_task_list_item_appl_date"));
//					bundle.putString("isSystem", "false");
//					bundle.putString("woType", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_wo_type"));
//					bundle.putString("woTypeName", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_wo_type_name"));
//					bundle.putString("soCat", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_so_cat"));
//					bundle.putString("extSoNbr", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_ext_so_nbr"));
//					bundle.putString("actType", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_act_type_name"));
//					bundle.putString("soNbr", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_so_nbr"));
//					bundle.putString("prodId", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_prod_id"));
//					bundle.putString("chgServSpecId", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_chg_serv_spec_id"));
//					bundle.putString("stepId", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_step_id"));
//					bundle.putString("accNbr", (String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_acc_num"));
//					bundle.putString("contactInfo",(String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_contactInfo"));
//					bundle.putString("custName",(String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_cust_name"));
//					bundle.putString("situated",(String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_cust_addr"));
//					bundle.putString("woWorkAreaId",(String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_work_area_id"));
//					bundle.putString("woWorkAreaName",(String) mosApp.getWoList().get(arg2-1).get("all_task_list_item_work_area_name"));
//
//					if((Long) mosApp.getWoList().get(arg2-1).get("all_task_list_item_time")!=null){
//						bundle.putLong("bookTime",(Long) mosApp.getWoList().get(arg2-1).get("all_task_list_item_time"));
//					}
//
//					intent.putExtras(bundle);
//					startActivity(intent);
//				}
//			});
//			
//	        // Set a listener to be invoked when the list should be refreshed.
//	        refreshListView.setOnRefreshListener(new OnRefreshListener() {
//	            @Override
//	            public void onRefresh() {
//	                // Do work to refresh the list here.
//	            	if(menu.isShowing()) {
//				    	menu.dismiss();
//				    }
//					if(menu1.isShowing()) {
//				    	menu1.dismiss();
//				    }
//	                new GetDataTask().execute();
//	            }
//	        });
//	        
//			 refreshListView.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
//
//					@Override
//					public boolean onItemLongClick(AdapterView<?> arg0, View view,
//							int position, long id) {
//						if(menu.isShowing()) {
//					    	menu.dismiss();
//					    }
//						if(menu1.isShowing()) {
//					    	menu1.dismiss();
//					    }
//						localNetId = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_localnet_id");
//						woNum = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_wo_num");
//						workAreaId = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_area_id");
//						appDate = (Long) mosApp.getWoList().get(position-1).get("all_task_list_item_appl_date");
//						contacInfo = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_phone");
//						woType = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_wo_type");
//						extSoNbr = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_ext_so_nbr");
//						soNbr = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_so_nbr");
//						soCat = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_so_cat");
//						actType = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_act_type_name");
//						stepId = (String) mosApp.getWoList().get(position-1).get("all_task_list_item_step_id");
//						infoJsonObject.put("extSoNbr", extSoNbr);
//						if(woType.equals("N")) {
//							menu.showAsDropDown(view.findViewById(R.id.all_task_list_item_cust_name), 10,-120);
//						}else {
//							menu1.showAsDropDown(view.findViewById(R.id.all_task_list_item_cust_name), 220,-120);
//						}
//						return true;
//					}
//		        });
//		}
//
//		private void refreshView() {
//			setAdapter(mosApp.getWoList());
//			more();
//		}
////		private class backOrderIsCanReturn extends Thread {
////			public void run() {
////				Message msg=new Message();
////				isCanReturnJson = getPostHttpContent(isCanReturnurl, infoJsonObject.toString());
////				if(isCanReturnJson.toString().equals("Y")) {
////					msg.what = 0;
////					isCanReturnJsonHandler.sendMessage(msg);
////					
////					return;
////				}
////				
////		}
////	 }
//		 //查看是否进行了竣工核实
//		 private class isCanReturnJsonThread extends Thread {
//				public void run() {
//					Message msg=new Message();
//					if(Constant.SO_CAT_SGS
//							.equals(soCat) && "SICHUAN".equals(provinceName)) {// 如果是四川障碍单的话，不用校验竣工核实
//						msg.what = 5;
//						returnHandler.sendMessage(msg);
//						return;
//					}
//					isCanReturnJson = getPostHttpContent(isCanReturnurl, infoJsonObject.toString());
//					if(isCanReturnJson.toString().equals("Y")) {
//						msg.what = 0;
//						isCanReturnJsonHandler.sendMessage(msg);
//						
//						return;
//					}else if(isCanReturnJson.toString().equals("N")) {
//						msg.what = 1;
//						isCanReturnJsonHandler.sendMessage(msg);
//						return;
//					}else {
//						msg.what = 2;
//						isCanReturnJsonHandler.sendMessage(msg);
//					}
//					
//			}
//		 }
//		 private Handler isCanReturnJsonHandler = new Handler() {
//
//				public void handleMessage(Message msg) {
//					switch (msg.what) {
//					case 0:
//						menu.dismiss();
//						Toast.makeText(getApplicationContext(), "已经进行了竣工核实",Toast.LENGTH_SHORT).show();					
//						break;
//					case 1:
//						Intent intent = new Intent();
//						intent.setClass(AllTaskListActivity.this, ReturnVisitActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putString("soNbr", soNbr);
//						bundle.putString("extSoNbr", extSoNbr);
//						bundle.putString("work_order", woNum);
//					    bundle.putString("localNetId",localNetId);
//						bundle.putString("areaId",workAreaId);
//						Pattern p = null; //正则表达式 Matcher m = null; //操作的字符串
//						Matcher m = null; //操作的字符串
//						p = Pattern.compile("[^0-9]*");
//						m = p.matcher(contacInfo);
//						List<String> contacInfoList = new ArrayList<String>();
//						String tempStr = new String();
//						p = Pattern.compile("((\\+?86)?1\\d{10})");
//						m = p.matcher(contacInfo);
//						while(m.find()) {
//							tempStr = m.group();
//							tempStr = tempStr.replaceAll("[-\\(\\)]", "");
//							contacInfoList.add(tempStr);
//						}
//						
//						if (contacInfoList.size()==0) {
//							bundle.putString("contacInfo", "");
//							
//						} else {
//												
//								bundle.putString("contacInfo", contacInfoList.get(0).toString());
//						}
//						intent.putExtras(bundle);
//						startActivity(intent);
//						if(menu.isShowing()) {
//			    			menu.dismiss();
//			    		}
//						break;
//					case 2:
//						menu.dismiss();
//						Toast.makeText(getApplicationContext(), "检查是否可以竣工核实失败",Toast.LENGTH_SHORT).show();	
//						break;
//					}
//				}
//			};
//			
//			
//			private class returnThread extends Thread {
//				public void run() {
//					Message msg=new Message();
////					try {
//					if(Constant.SO_CAT_SGS
//							.equals(soCat) && "HEBEI".equals(provinceName)) {// 如果是河北障碍单的话，不用校验竣工核实
//						msg.what = 4;
//						returnHandler.sendMessage(msg);
//						return;
//					}
//					if(Constant.SO_CAT_SGS
//							.equals(soCat) && "SICHUAN".equals(provinceName)) {// 如果是四川障碍单的话，不用校验竣工核实
//						msg.what = 4;
//						returnHandler.sendMessage(msg);
//						return;
//					}
//						isCanReturnJson = getPostHttpContent(isCanReturnurl, infoJsonObject.toString());
//						if(whichAction.equals("return")&&isCanReturnJson.toString().equals("Y")) {
//							msg.what=1;
//							returnHandler.sendMessage(msg);
//						}else if(whichAction.equals("return")&&isCanReturnJson.toString().equals("N")){
//							msg.what = 2;
//							returnHandler.sendMessage(msg);
//						}else if(whichAction.equals("backOrder")&&isCanReturnJson.toString().equals("N")) {
//							isReturn = false;
//							msg.what = 3;
//							returnHandler.sendMessage(msg);
//						}else if(whichAction.equals("backOrder")&&isCanReturnJson.toString().equals("Y")) {
//							isReturn = true;
//							msg.what = 4;
//							returnHandler.sendMessage(msg);
//						}
////					} catch (ClientProtocolException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////						msg.what=0;
////						returnHandler.sendMessage(msg);
////					} catch (IOException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////						msg.what=0;
////						returnHandler.sendMessage(msg);
////					} 
//					
//				}
//			}
//			
//			
//			 //判断是否已经材料回填	
//			private class materialReturnThread extends Thread {
//				public void run() {
//					Message msg=new Message();
////						isCanReturnJson = Communication.getPostResponse(isCanReturnurl, infoJsonObject.toString());
//						com.alibaba.fastjson.JSONObject infoJsonObject1 = new com.alibaba.fastjson.JSONObject();
//						String isCanReturnUrl = "tm/MaterialHandleAction.do?method=queryWoCount4MOS";
//						infoJsonObject1.put("workorder", woNum);
//						String	count = null;
//					    count = getPostHttpContent(isCanReturnUrl, infoJsonObject1.toString());							
//						
//						if("0".equals(count) || Integer.parseInt(count)==0) {
//							isMaterialReturn = false;
//							msg.what=1;
//							materialReturnHandler.sendMessage(msg);
//						}else {
//							//判断是否要审核
//							if (!(soCat.endsWith(Constant.SO_CAT_SPS) && actType.endsWith("拆"))) {
//								whichAction = "backOrder";
//								isCanReturnJson = getPostHttpContent(isCanReturnurl, infoJsonObject.toString());
//								if(whichAction.equals("backOrder")&&isCanReturnJson.toString().equals("N")) {
//									isReturn = false;
//									msg.what = 3;
//									materialReturnHandler.sendMessage(msg);
//								}else if(whichAction.equals("backOrder")&&isCanReturnJson.toString().equals("Y")) {
//									isReturn = true;
//									msg.what = 2;
//									materialReturnHandler.sendMessage(msg);
//								}
//							}else{
//								isMaterialReturn = true;
//								msg.what = 2;
//								materialReturnHandler.sendMessage(msg);
//							}
//							
//						}
//					
//				}
//			}
//			
//			
//			
//			private Handler materialReturnHandler = new Handler() {
//
//				public void handleMessage(Message msg) {
//					switch (msg.what) {
//					case 0:
//						if(menu.isShowing()) {
//			    			menu.dismiss();
//			    		}
//						Toast.makeText(getApplicationContext(), "网络连接失败，请检查网络设置！",Toast.LENGTH_SHORT).show();
//						break;
//					case 1:
//						if(menu.isShowing()) {
//			    			menu.dismiss();
//			    		}
//						Toast.makeText(getApplicationContext(), "请先进行材料回填！",
//								Toast.LENGTH_SHORT).show();
//						break;
//					case 2:
//						Intent intent0 = new Intent();
//						if (Constant.SO_CAT_SPS.equals(soCat)) {
//							intent0.setClass(AllTaskListActivity.this,
//									BackOrderSuccessActivity.class);
//						} else if (Constant.SO_CAT_SGS.equals(soCat)) {
//							intent0.setClass(AllTaskListActivity.this,
//									BackSgsOrderSuccessActivity.class);
//						}
//						intent0.putExtra("woNbr", woNum);
//						intent0.putExtra("soCat", soCat);
//						startActivity(intent0);
//						break;				
//					case 3:
//						if(menu.isShowing()) {
//			    			menu.dismiss();
//			    		}
//						Toast.makeText(getApplicationContext(), "请先进行竣工核实！",Toast.LENGTH_SHORT).show();	
//						break;
//				}
//			};
//		};
//			
//			private Handler returnHandler = new Handler() {
//
//				public void handleMessage(Message msg) {
//					switch (msg.what) {
//					case 0:
//						if(menu.isShowing()) {
//			    			menu.dismiss();
//			    		}
//						Toast.makeText(getApplicationContext(), "网络连接失败，请检查网络设置！",Toast.LENGTH_SHORT).show();
//						break;
//					case 1:
//						if(menu.isShowing()) {
//			    			menu.dismiss();
//			    		}
//						Toast.makeText(getApplicationContext(), "已经进行了竣工核实",
//								Toast.LENGTH_SHORT).show();
//						break;
//					case 2:
////						Intent intent = new Intent();
////						intent.setClass(AllTaskListActivity.this,
////								ReturnVisitActivity.class);
////						Bundle bundle = new Bundle();
////						bundle.putString("extSoNbr", extSoNbr);
////						bundle.putString("work_order", woNum);
////						bundle.putString("localNetId", localNetId);
////						bundle.putString("areaId", areaId);
////						bundle.putString("soNbr", soNbr);
////						Pattern p = null; // 正则表达式 Matcher m = null; //操作的字符串
////						Matcher m = null; // 操作的字符串
////						p = Pattern.compile("[^0-9]*");
////						m = p.matcher(contacInfo);
////						if (m.matches()) {
////							bundle.putString("contacInfo", "");
////						} else {
////							Pattern pattern = Pattern.compile("[/ ,]");
////							String nums[] = pattern.split(contacInfo);
////							if (nums[0].length() < 6) {
////								bundle.putString("contacInfo", "");
////							} else {
////								bundle.putString("contacInfo", nums[0]);
////							}
////						}
////						intent.putExtras(bundle);
////						startActivity(intent);
//						break;
//					case 3:
//						Toast.makeText(getApplicationContext(), "请先进行竣工核实！",Toast.LENGTH_SHORT).show();	
//						break;
//					case 4:
//						Intent intent0 = new Intent();
//						if (Constant.SO_CAT_SPS.equals(soCat)) {
//							intent0.setClass(AllTaskListActivity.this,
//									BackOrderSuccessActivity.class);
//						} else if (Constant.SO_CAT_SGS.equals(soCat)) {
//							intent0.setClass(AllTaskListActivity.this,
//									BackSgsOrderSuccessActivity.class);
//						}
//						intent0.putExtra("woNbr", woNum);
//						intent0.putExtra("soCat", soCat);
//						startActivity(intent0);
//						break;
//					case 5:
//						Toast.makeText(getApplicationContext(), "该功能暂未开放",Toast.LENGTH_SHORT).show();	
//						break;
//					}
//				};
//			};
			
}
