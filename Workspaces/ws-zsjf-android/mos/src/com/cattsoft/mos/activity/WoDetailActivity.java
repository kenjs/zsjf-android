package com.cattsoft.mos.activity;


public class WoDetailActivity  {
//	
//	/** 翻转所用变量 */
//	/** 旋转中心x位置 */
//	private int mCenterX;
//	/** 旋转中心y位置 */
//	private int mCenterY;
//	private int screenW;
//	/** 获取布局 */
//	private LayoutInflater mInflater;
//	private int currentTab = 0;// 当前Layout的序号
//
//	/** ViewPager 控件变量声明 */
//	private ViewPager mPager;// 页卡控件
//	private List<View> listViews; // 存储页面列表
//	private View resPage;// 用于存储资源页面view
//	/** 页卡动画 */
//	private ImageView cursor;// 动画图片
//	private int offset = 0;// 动画图片偏移量
//	private int perSpacing = 0;// 每个动画图片间距
//	private int currIndex = 0;// 当前页卡编号
//	private int bmpW;// 动画图片宽度
//	private int[] pageIndex;// 存储旋转时当前页卡所在当前界面的位置
//	/** 页卡头标 */
//	private ArrayList<TextView> pageTitles = new ArrayList<TextView>();
//	private LinearLayout linearLayout;
//
//	/** 异步处理变量声明 */
//	private MSGHandler handler = null;
//
//	/** 底部菜单变量声明 */
//	private SlidingDrawer slidingDrawer;// 底部抽屉控件
//
//	private MenuItemData mToolBarItemData;// 底部Grid控件数据
//	private GridView mToolBarGrid; // 底部菜单栏(第二行)
//	private GridViewAdapter mToolBarAdapter; // 底部菜单栏适配器
//
//	private ImageView handleImageBtn;// 抽屉菜单(第一行)handle切换按钮
//	/** 工单详情底部菜单功能屏蔽 */
//	private String woDetailFunctionVisiable = "";
//	
//	
//	/** 顶部左右小图标 */
//	private ImageView leftMove;
//	private ImageView rightMove;
//	/** 获取页面传值 */
//	private Intent intent;
//	private String workorder;// 获得工单号
//	private String areaId;// 获得包区
//	private String localNetId;// 获得本地网id
//	private String contacInfo;// 联系方式
//	private String woType;// 工单类型
//	private String menuFlag;// 底部菜单隐藏标识
//	private String woTypeName;// 工单类型名称
//	private String soCat;
//	private String prodId;// 产品id
//	private String chgServSpecId;// 专业服务id
//	private String staffId;// 员工id
//	private String stepId;// 环节
//	private String extSoNbr;// 定单号
//	private String actType;// 动作类型
//	private long appTime;// 预约时间
//	private String soNbr;// 订单号
//	private String accNbr;// 业务号。
//	private long bookTime;
//	private String custName;
//	private String situated;
//	private String woWorkAreaId;//当前工单所属工区ID
//	private String woWorkAreaName;//当前工单所属工区
//	/** 弹出菜单 */
//	private PopupWindow switchResInfoBtn; // 资源信息新旧切换按钮
//	private PopupWindow materalFillBackMenu; // 材料回填弹出菜单
//	private PopupWindow outsidelineConstructionMenu; // 外线施工弹出菜单
//	private View outsidelineConstructionLayout;// 外线施工菜单界面
//	private View materalFillBackLayout;// 材料回填弹出菜单布局
//	private String switchFlag = "new";// 切换标识
//	private TextView materalInput;// 材料录入
//	private TextView materalRecover;// 材料回收
//	
//
//	/** 拍照 照相及显示 声明变量 */
//	private GridView thumbnail;// 显示照片的缩略图
//	private String path;
//	private Bitmap myBitmap;
//
//	/** 工单详情后台数据 */
//	private String woDetailData;// 后台获取的jsonString
//	private Map<String, Object> detailInfoMap;// jsonString字符串转化的map
//	private String woDetailWorkData;// 后台获取的jsonString
//	private ArrayList detailInfoWorkList;// jsonString字符串转化的map
//
//	private String woDetailNewResData;// 后台获取的jsonString
//	private ArrayList detailInfoNewResList;// jsonString字符串转化的map
//
//	private String woDetailOldResData;// 后台获取的jsonString
//	private ArrayList detailInfoOldResList;// jsonString字符串转化的map
//
//	private String woDetailOrderData;// 后台获取的jsonString
//	private ArrayList detailInfoOrderList;// jsonString字符串转化的map
//
//	/** 通话记录 */
//	private ArrayList<HashMap<String, Object>> callLogList = new ArrayList<HashMap<String, Object>>();// 存储通话记录信息
//	private SimpleAdapter callLogAdapter;// 通话记录适配器
//	private boolean isCallLogException = false;// 初始化通话记录异常状态
//	private String responseJson;// 通话记录后台数据
//
//	// need update
//	/** 日志 */
//	private ArrayList<HashMap<String, Object>> logList = new ArrayList<HashMap<String, Object>>();// 存储通话记录信息
//	private SimpleAdapter LogAdapter;// 日志适配器
//	private String responseJson1;// 日志的后台数据
//	private Date date;
//	private String handle_date = "";
//	private LinearLayout mainline;// 主布局
//	private LinearLayout workinfoMainline;// 主布局
//	private LinearLayout resinfoMainline;// 主布局
//	private LinearLayout orderinfoMainline;// 主布局
//
//	private View metralView;
//
//	/** 电话监听 */
//	private TelephonyManager manager;// 电话监听
//	private String telNum;
//	private String isSystem = null;// 判断是否是Android系统打来的电话
//	private boolean isReservation = false;// 判断是否已经点击过呼叫按钮
//
//	/** 将定单号传送到后台 */
//	private com.alibaba.fastjson.JSONObject infoJsonObject = new com.alibaba.fastjson.JSONObject();
//	private String isCanReturnurl = "tm/MosSurveyAction.do?method=queryMOSSurvey4MOS";
//	private String isCanReturnJson;
//
//	/** 从工单查询进入出现的弹出框 */
//	private TextView callText;
//	private ImageView outsideConstruction;
//
//	private Handler mHandler = new Handler();
//
//	/** 初始化照片界面 */
//	private boolean isPhotoPageException = false;// 初始化照片异常状态
//	private String initPhotourl = "tm/MosSurveyAction.do?method=queryMOSPicture4MOS";
//	private com.alibaba.fastjson.JSONObject initPhotoJsonObject = new com.alibaba.fastjson.JSONObject();
//	private String photoJson;// 照片后台数据
//	private String descString;// 拍完照填写的照片描述信息
//	private String soAttachId;
//	/** 工单领取处理结果 */
//	private String fetchWoResult;
//	private String whichAction;// 判断是回单之前是否竣工核实还是竣工核实之前是否竣工核实
//
//	private boolean isMaterialReturn = false;// 回单前，是否已经进行了材料回填
//	private boolean isReturn = false;// 回单前，是否已经进行了竣工核实
//
//	private List<MaterialSpecMVO> materialInitList;
//	private List<ScanRowObj> listdata = new ArrayList<ScanRowObj>();
//	private String materialStepId;//可以材料回填的环节id
//	private List funcNodeList;
//	private ImageView ivSucBackOrder,//回单
//						ivFailBackOrder,//退单
//						ivCall,//呼叫
//						ivRetuanBack,//竣工核实
//						ivMaterailBack,//材料回填
//						ivUpdateRemarks,//修改备注
//						ivChangeOrder,//转派
//						ivPreOrder,//预约
//						ivDetailServ,//网格维护
//						barCodeValidate,//设备确认
//						ivResChange,//资源变更
//						ivTakePhoto,//拍照
//						ivResCheck,//资源核查
//						ivServiceTest,//业务测试
//						ivOnScheduleBtn;//履约签到
//	private static String barCode;//终端二维码
//	private View gridView;
//	private LinearLayout gridViewlayout=null;
//	private String provinceName=null;
//	private String isForceReturnVisit=null;//回单之前是否强制进行竣工核实
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// 标题处理
//		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);// 设置标题栏为用户自定义标题栏
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_work_detil_main);// 设置当前显示的布局
//		super.setTitleBar("工单处理", View.VISIBLE, View.INVISIBLE, View.GONE,
//				false);
//		woDetailFunctionVisiable = this.getCacheProcess()
//				.getCacheValueInSharedPreferences(this, "woDetailFunctionVisiable");
//		materialStepId = this.getCacheProcess()
//				.getCacheValueInSharedPreferences(this, "materialStepId");
//		initIntentData();// 初始化数据
//		initTakePhoto();
//		initFuncNode();
//		initView();
//		registerListener();
//		handler = new MSGHandler(this);// 接收线程消息
//		isHideMenu();
//		showProcessDialog(true);
//		new initThread().start();// 线程请求数据
//		
//
//	}
//	/**
//	 * 实现工单详情底部按钮的可控性
//	 */
//	private Boolean initBottomButtonControl(String locationStr) {
//		Boolean isCanControl = false;
//		if(woDetailFunctionVisiable!=null && !woDetailFunctionVisiable.equals("")){
//			String woFunctionVis[] = woDetailFunctionVisiable.split(",");
//			for (int i = 0; i < woFunctionVis.length; i++) {
//				if(woFunctionVis[i].trim().toUpperCase().equals(locationStr)){
//					isCanControl = true;
//				}
//			}
//		}
//		return isCanControl;
//	}
//
//
//	private class initPopupWindow extends TimerTask {
//		@Override
//		public void run() {
//			Message message = new Message();
//			message.what = 1;
//			mHandler.sendMessage(message);
//		}
//	}
//
//	private void initIntentData() {
//		provinceName=this.getCacheProcess()
//				.getCacheValueInSharedPreferences(this, "provinceName");//使用掌上运维的省份
//		isForceReturnVisit=this.getCacheProcess()
//				.getCacheValueInSharedPreferences(this, "isForceReturnVisit");//回单之前是否强制进行竣工核实
//		Intent intent = getIntent();
//		Bundle bundle = intent.getExtras();
//		if (bundle != null) {
//			workorder = bundle.getString("work_order");
//			localNetId = bundle.getString("localNetId");
//			areaId = bundle.getString("areaId");
//			prodId = bundle.getString("prodId");
//			chgServSpecId = bundle.getString("chgServSpecId");
//			appTime = bundle.getLong("applDate");
//			woTypeName = bundle.getString("woTypeName");
//			woType = bundle.getString("woType");
//			soCat = bundle.getString("soCat");
//			stepId = bundle.getString("stepId");
//			extSoNbr = bundle.getString("extSoNbr");
//			menuFlag = bundle.getString("menu_flag");
//			actType = bundle.getString("actType");
//			soNbr = bundle.getString("soNbr");
//			accNbr = bundle.getString("accNbr");
//			contacInfo = bundle.getString("contactInfo");
//			bookTime = bundle.getLong("bookTime");
//			custName = bundle.getString("custName");
//			situated = bundle.getString("situated");
//			woWorkAreaId = bundle.getString("woWorkAreaId");
//			woWorkAreaName = bundle.getString("woWorkAreaName");
//
//			//long 类型的转化为 date类型
//			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			java.util.Date dt = new Date(bookTime);  
//			String sDateTime = sdf.format(dt); 
////			System.out.println(sDateTime);
//			
//			if (bundle.getString("isSystem") != null) {
//				isSystem = bundle.getString("isSystem");
//			}
//			infoJsonObject.put("extSoNbr", extSoNbr);
//			
//			Log.d("===============预约时间为：", bookTime + "");
//		}
//	}
//
//	/**
//	 * 初始化拍照
//	 */
//	private void initTakePhoto() {
//		TakePhotoMain.j = 0;
//		path = TakePhotoMain.ReadSDPath() + "/DCIM/Camera/";
//		TakePhotoMain.soAttachList = new ArrayList();
//	}
//
//	/** ---------------------模拟照相功能-------------- **/
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//
//		/**
//		 * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
//		 * 所以为了区别到底选择了那个方式获取图片要进行判断，
//		 * 这里的requestCode跟startActivityForResult里面第二个参数对应
//		 */
//		if (resultCode == RESULT_OK) {
//			switch (requestCode) {
//			case 1: {
//				try {
//					super.onActivityResult(requestCode, resultCode, data);
//					BitmapFactory.Options options = new BitmapFactory.Options();
//					// 先设置为TRUE不加载到内存中，但可以得到宽和高
//					options.inJustDecodeBounds = true;
//					Bitmap bm = BitmapFactory.decodeFile(path + "/"
//							+ TakePhotoMain.j + ".jpg", options); // 此时返回bm为空
//					options.inJustDecodeBounds = false;
//					// 计算缩放比
//					int be = (int) (options.outHeight / (float) 200);
//					if (be <= 0)
//						be = 1;
//					options.inSampleSize = be;
//					// 这样就不会内存溢出了
//					bm = BitmapFactory.decodeFile(path + "/" + TakePhotoMain.j
//							+ ".jpg", options);
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();
//					bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//					byte[] bitmapBytes = baos.toByteArray();
//					int i = bitmapBytes.length;
//					Intent intent = new Intent();
//					intent.putExtra("BitImageBytes", bitmapBytes);
//					intent.putExtra("woNbr", workorder);
//					intent.putExtra("localNetId", localNetId);
//					intent.putExtra("soNbr", extSoNbr);
//					intent.setClass(WoDetailActivity.this,
//							PreviewActivity.class);
//					startActivityForResult(intent, 2);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//				break;
//			case 2: {
//				byte[] bitmapBytes = data.getByteArrayExtra("BitImageBytes");
//				descString = data.getStringExtra("desc");
//				soAttachId = data.getStringExtra("soAttachId");
//				myBitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0,
//						bitmapBytes.length);
//				if (myBitmap != null) {
//					Map soAttachMap = new HashMap();
//					soAttachMap.put("image", myBitmap);
//					soAttachMap.put("soAttachId", soAttachId);
//					soAttachMap.put("desc", descString);
//					TakePhotoMain.soAttachList.add(soAttachMap);
//					TakePhotoMain.j++;
//				}
//				initViewPager();
//			}
//				break;
//			case 3: {
//				initViewPager();
//
//			}
//				break;
//
//			case 4: {
//				if (data != null) {
//					// 清空listdata
//					listdata.clear();
//
//					try {
//						JSONArray arr = new JSONArray(
//								data.getStringExtra("listdata"));
//						for (int i = 0; i < arr.length(); i++) {
//							ScanRowObj sr = new ScanRowObj();
//							JSONObject temp = (JSONObject) arr.get(i);
//							String proNum = temp.getString("proNum");
//							String scanProName = temp.getString("scanProName");
//							String scanType = temp.getString("scanType");
//
//							sr.setProNum(proNum);
//							sr.setScanProName(scanProName);
//							sr.setScanType(scanType);
//							listdata.add(sr);
//						}
//
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					initPage6(metralView);
//				}
//			}
//				break;
//				
//			case 5: {
//
//				String flagBtn = data.getStringExtra("flagBtn");
//				//拍照
//				if("takePhotos".equals(flagBtn))
//					{
//					if(initBottomButtonControl("PZ")){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						// 解决图片照完之后自动压缩成小图片
//						File dir = new File(path);
//						Intent intent = new Intent(
//								android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//						// Date date = new Date();
//						// long time = date.getTime();
//						File file = new File(dir, TakePhotoMain.j + ".jpg");
//						// File file = new File(dir,time+".jpg");
//						Uri r = Uri.fromFile(file);
//						intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
//						intent.putExtra(MediaStore.EXTRA_OUTPUT, r);
//						// getContentResolver().delete(r, null, null);
//						startActivityForResult(intent, 1);
//					}
//				}
//				//资源变更
//			 if("reschange".equals(flagBtn)){
//				 
//					if (initBottomButtonControl("ZYBG")) {
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					} else {
//
//						if (soCat.equals("2")) {
//							Intent intent = new Intent(WoDetailActivity.this,
//									ResourceMaintenanceResActivity.class);
//							intent.putExtra("acc_nbr", accNbr);
//							startActivity(intent);
//						} else {
//							Toast.makeText(getApplicationContext(),
//									"该环节不需要资源变更！", Toast.LENGTH_SHORT).show();
//						}
//					}
//					
//				}
//			//业务测试
//			if("servicetest".equals(flagBtn)){
//				
//				if(initBottomButtonControl("YWCS")){
//					Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//							Toast.LENGTH_SHORT).show();
//				}else{
//				Intent intent = new Intent(WoDetailActivity.this,
//						AutoTestConnectActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putString("localNetId", localNetId);
//				bundle.putString("accNbr", accNbr);
//				bundle.putString("workorder", workorder);
//				intent.putExtras(bundle);
//				startActivity(intent);
//				}
//			}
//			//网格维护
//             if("detailserv".equals(flagBtn)){
//            	 
//            	 if(initBottomButtonControl("WGWH")){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//					Intent intent = new Intent(WoDetailActivity.this,
//							ServDeptActivity.class);
//					Bundle bundle = new Bundle();
//					bundle.putString("workorder", workorder);
//					intent.putExtras(bundle);
//					// 设置传递的参数,targetActivity
//					// 启动intent的Activity
//					startActivity(intent);
//					}
//				
//			 }	
//           //资源核查
//             if("resCheck".equals(flagBtn)){
//            		// TODO Auto-generated method stub
//					if(initBottomButtonControl("ZYHC")){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//					Intent intent = new Intent(WoDetailActivity.this,DeviceCoordinateActivity.class);
//					Bundle bundle = new Bundle();
//					bundle.putString("accNbr", accNbr);
//					intent.putExtras(bundle);
//					startActivity(intent);
//					}
// 				
// 			 }	
//           //退单
//             if("failback".equals(flagBtn)){
//         		
//					if(initBottomButtonControl("TD")){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//					// 跳转到对应的页面 失败回单
//
//					if ("N".equals(woType) || "P".equals(woType)) {
//
//						Intent intent1 = new Intent();
//						if (Constant.SO_CAT_SPS.equals(soCat)) {
//							intent1.setClass(WoDetailActivity.this,
//									BackOrderFailActivity.class);
//						} else if (Constant.SO_CAT_SGS.equals(soCat)) {
//							intent1.setClass(WoDetailActivity.this,
//									BackSgsOrderFailActivity.class);
//						}
//						intent1.putExtra("woNbr", workorder);
//						intent1.putExtra("soCat", soCat);
//						startActivity(intent1);
//					}
//					else if ("F".equals(woType)) {
//
//						Toast.makeText(getApplicationContext(),
//								"追单不能进行退单操作", Toast.LENGTH_SHORT)
//								.show();
//					}
//					}
//				
//			 }		
//             
//            //预约 
//             if("preorder".equals(flagBtn)){
//            	 if(initBottomButtonControl("YY")){
//  					Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//  							Toast.LENGTH_SHORT).show();
//  				}else{
//         		Intent intent1 = new Intent();
//    			intent1.setClass(WoDetailActivity.this,
//						ReserveTimeActivity.class);
//    			intent1.putExtra("bookTime", bookTime);
//				intent1.putExtra("soNbr", soNbr);
//				intent1.putExtra("localNetId", localNetId);
//				intent1.putExtra("areaId", areaId);
//				intent1.putExtra("extSoNbr", extSoNbr);
//				intent1.putExtra("accNbr", accNbr);
//				intent1.putExtra("workorder", workorder);
//				intent1.putExtra("appTime", appTime);
//				intent1.putExtra("custName", custName);
//				intent1.putExtra("situated", situated);
//				startActivity(intent1);
//			 }		
//             }
//             //转派
//             if("changeorder".equals(flagBtn)){
//            	 if(initBottomButtonControl("ZP")){
//  					Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//  							Toast.LENGTH_SHORT).show();
//  				}else{
//         		Intent intent1 = new Intent();
//    			intent1.setClass(WoDetailActivity.this,
//						ChangeWorkOrderActivity.class);
//    			intent1.putExtra("accNbr", accNbr);
//				intent1.putExtra("custName", custName);
//				intent1.putExtra("localNetId", localNetId);
//				intent1.putExtra("workorder", workorder);
//				intent1.putExtra("soCat", soCat);
//				intent1.putExtra("woWorkAreaId", woWorkAreaId);
//				intent1.putExtra("woWorkAreaName", woWorkAreaName);
//				
//				startActivity(intent1);
//  					}
//			     }		
//             
//		    }
//				break;
//			case 6:{
//				if(data.getStringExtra("smnumber").equals(barCode)) {
//					MediaPlayer mediaPlayer = new MediaPlayer(); 
//					mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//					mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { 
//					   @Override 
//					   public void onCompletion(MediaPlayer player) { 
//					       player.seekTo(0); 
//					   } 
//					});
//					AssetFileDescriptor file = this.getResources().openRawResourceFd( 
//							R.raw.beep); 
//							try { 
//							    mediaPlayer.setDataSource(file.getFileDescriptor(), 
//							    file.getStartOffset(), file.getLength()); 
//							    file.close(); 
//							    mediaPlayer.setVolume(6,6); 
//							    mediaPlayer.prepare(); 
//							} catch (IOException ioe) { 
//							
//							  mediaPlayer = null; 
//							} 
//							if (mediaPlayer != null) { 
//								mediaPlayer.start(); 
//								} 
//
//					Toast.makeText(this, "终端正确！", Toast.LENGTH_LONG).show();
//				}else {
//					MediaPlayer mediaPlayer = new MediaPlayer(); 
//					mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//					mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { 
//					   @Override 
//					   public void onCompletion(MediaPlayer player) { 
//					       player.seekTo(0); 
//					   } 
//					});
//					AssetFileDescriptor file = this.getResources().openRawResourceFd( 
//							R.raw.wrongoo); 
//							try { 
//							    mediaPlayer.setDataSource(file.getFileDescriptor(), 
//							    file.getStartOffset(), file.getLength()); 
//							    file.close(); 
//							    mediaPlayer.setVolume(15,15);
//							    mediaPlayer.prepare(); 
//							   
//							} catch (IOException ioe) { 
//							
//							  mediaPlayer = null; 
//							} 
//							if (mediaPlayer != null) { 
//								mediaPlayer.start();  
//								  
//								} 
//					Toast.makeText(this, "终端错误，请更换终端！", Toast.LENGTH_LONG).show();
//				}
//			}break;
//			}
//		}
//
//	}
//
//	/** ---------------------模拟照相功能-------------- **/
//
//	/**
//	 * 是否隐藏菜单项
//	 */
//	public void isHideMenu() {
//		if ("hide".equals(menuFlag) || "WoFetch".equals(menuFlag)) {
//			mToolBarGrid.setVisibility(View.GONE);
//		/*	slidingDrawer.setVisibility(View.GONE);
//			// 重新设置mPager的高度
//			LayoutParams lp = mPager.getLayoutParams();
//			LayoutParams lp1 = slidingDrawer.getLayoutParams();
//			lp.height = 1500;
//			lp1.height = 800;
//			slidingDrawer.setLayoutParams(lp1);
//			mPager.setLayoutParams(lp);
//			mPager.requestLayout();
//		*/
//			Timer timer = new Timer();
//			timer.schedule(new initPopupWindow(), 100);
//
//		}
//	}
//
//	class MyPhoneStateListener extends PhoneStateListener {
//
//		int i = 0;// 第几次打电话系统会监听几次（具体原因不知道为什么），该变量用来只让页面跳转一次
//
//		@Override
//		public void onCallStateChanged(int state, String incomingNumber) {
//			if (mosApp.getPhoneFlag() == 0)
//				return;
//			if (isSystem != null) {// 4.0版本之后就不用判断是从那个地方打的电话了，因为4.0之后从那打的电话就会回到那个地方去
//
//				switch (state) {
//
//				case TelephonyManager.CALL_STATE_IDLE:
//					if (isReservation && i == 1) {// 如果没有标志位的话，会出现进入该页面就会触发挂断电话事件
//						// 所以在触发打电话事件时，将该标志位更改之后，在这次挂断电话时就会触发挂断事件中我们想要走的代码
//						Intent intent = new Intent(WoDetailActivity.this,
//								ReservationActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putString("woNum", workorder);
//						bundle.putString("work_order", workorder);
//						bundle.putString("localNetId", localNetId);
//						bundle.putString("areaId", areaId);
//						bundle.putLong("appTime", appTime);
//						bundle.putLong("bookTime", bookTime);
//						bundle.putString("soCat", soCat);
//						intent.putExtras(bundle);
//						startActivity(intent);
//						finish();
//						isSystem = null;
//					}
//					break;
//				case TelephonyManager.CALL_STATE_RINGING:
//					break;
//				case TelephonyManager.CALL_STATE_OFFHOOK:
//					isReservation = true;
//					i++;
//					//
//				default:
//					break;
//				}
//
//			} else {
//				super.onCallStateChanged(state, incomingNumber);
//			}
//		}
//	}
//
//	/**
//	 * 头标点击监听
//	 */
//	public class MyOnClickListener implements View.OnClickListener {
//		private int index = 0;
//
//		public MyOnClickListener(int i) {
//			index = i;
//		}
//
//		public void onClick(View v) {
//			mPager.setCurrentItem(index);
//		}
//	};
//
//	/**
//	 * 获取屏幕宽度/4
//	 */
//	private int getScreenWidth() {
//		DisplayMetrics dm = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		screenW = dm.widthPixels;// 获取屏幕宽度
//		perSpacing = screenW / 4;
//
//		return perSpacing;
//	}
//
//	/**
//	 * 初始化动画，生成cursor图片
//	 */
//	private void initImageView() {
//		cursor = (ImageView) findViewById(R.id.cursor);
//		getScreenWidth();
//		Bitmap bmp = Bitmap
//				.createBitmap(perSpacing, 5, Bitmap.Config.ARGB_8888);
//		Canvas canvas = new Canvas(bmp);
//		canvas.drawColor(0xFFBC1E28);
//		cursor.setImageBitmap(bmp);
//		setCursorPosition();
//	}
//
//	/**
//	 * 设置cursor初始位置
//	 */
//	private void setCursorPosition() {
//		Matrix matrix = new Matrix();
//		matrix.postTranslate(0, 0);
//		cursor.setImageMatrix(matrix);// 设置动画初始位置
//	}
//
//	/**
//	 * 初始化ViewPager
//	 */
//	private void initViewPager() {
//		mPager.setAdapter(new MyPagerAdapter(listViews));
//		mPager.setCurrentItem(currIndex);
//	}
//
//	/**
//	 * ViewPager适配器
//	 */
//	public class MyPagerAdapter extends PagerAdapter {
//
//		public List<View> mListViews;
//
//		public MyPagerAdapter(List<View> mListViews) {
//			this.mListViews = mListViews;
//		}
//
//		@Override
//		public void destroyItem(View arg0, int arg1, Object arg2) {
//			((ViewPager) arg0).removeView(mListViews.get(arg1));
//		}
//
//		@Override
//		public void finishUpdate(View arg0) {
//		}
//
//		@Override
//		public int getCount() {
//			return mListViews.size();
//		}
//
//		@Override
//		public void notifyDataSetChanged() {
//			super.notifyDataSetChanged();
//		}
//
//		@Override
//		public Object instantiateItem(View arg0, int arg1) {
//			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
//
//			if (arg1 == 0) {
//				initPage1(arg0);
//			}
//			if (arg1 == 1) {
//				initPage2(arg0);
//			}
//			if (arg1 == 2) {
//				initPage3(arg0);
//			}
//			if (arg1 == 3) {
//				initPage4(arg0);
//			}
//			if (arg1 == 4) {
//				initPage11(arg0);
//			}
//			if (arg1 == 5) {
//				initPage6(arg0);
//			}
//			if (arg1 == 6) {
//				// initPage7(arg0);
//			}
//			if (arg1 == 7) {
//				// initPage7(arg0);
//			}
//			if (arg1 == 8) {
//				// initPage8(arg0);
//			}
//
//			return mListViews.get(arg1);
//		}
//
//		@Override
//		public boolean isViewFromObject(View arg0, Object arg1) {
//			return arg0 == (arg1);
//		}
//
//		@Override
//		public void restoreState(Parcelable arg0, ClassLoader arg1) {
//		}
//
//		@Override
//		public Parcelable saveState() {
//			return null;
//		}
//
//		@Override
//		public void startUpdate(View arg0) {
//		}
//	}
//
//	// 初始化数据
//	private void initPageMap() {
//		// 发起请求从后台获取数据后显示出来
//		String url = "tm/WoHandleAction.do?method=initWoDetail4MOS";
//		// 把条件数据以JSON形式存储好
//		Message msg = new Message();
//		try {
//			JSONObject jsonParmter = new JSONObject();
//			jsonParmter.put("woNbr", workorder);
//			jsonParmter.put("localNetId", localNetId);
//			String parameter = jsonParmter.toString();
//			woDetailWorkData = getPostHttpContent(url, parameter);
//			// 把得到的数据变成map形式，然后再赋值
//			if (StringUtil.isExcetionInfo(woDetailWorkData)) {
//				WoDetailActivity.this.sendExceptionMsg(woDetailWorkData);
//				return;
//			}
//
//			JSONArray arr = new JSONArray(woDetailWorkData);
//
//			detailInfoWorkList = new ArrayList<Map>();
//			detailInfoNewResList = new ArrayList<Map>();
//			detailInfoOrderList = new ArrayList<Map>();
//			detailInfoOldResList = new ArrayList<Map>();
//
//			JSONArray workarr = new JSONArray(arr.get(0).toString());
//			for (int i = 0; i < workarr.length(); i++) {
//				Map map = getMap(workarr.get(i).toString());
//				detailInfoWorkList.add(map);
//
//			}
//
////			JSONArray newResarr = new JSONArray(arr.get(1).toString());
////			for (int i = 0; i < newResarr.length(); i++) {
////				Map map = getMap(newResarr.get(i).toString());
////				detailInfoNewResList.add(map);
////			}
////
////			JSONArray orderarr = new JSONArray(arr.get(2).toString());
////			for (int i = 0; i < orderarr.length(); i++) {
////				Map map = getMap(orderarr.get(i).toString());
////				detailInfoOrderList.add(map);
////			}
////
////			JSONArray oldResarr = new JSONArray(arr.get(3).toString());
////			for (int i = 0; i < oldResarr.length(); i++) {
////				Map map = getMap(oldResarr.get(i).toString());
////				detailInfoOldResList.add(map);
////
////			}
//
//			initCallLog();
//			initPhotoPage();
////			initLogPage();
//			initMaterialData();
//			msg.what = 1;
//			handler.sendMessage(msg);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 初始化材料信息
//	 */
//	private void initMaterialData() {
//
//		String initMaterialTypeDataUrl = "tm/MaterialHandleAction.do?method=initMaterialTypeData4MOSJL";
//
//		com.alibaba.fastjson.JSONObject initMaterialProObject = new com.alibaba.fastjson.JSONObject();
//		initMaterialProObject.put("localNetId", localNetId);
//		initMaterialProObject.put("areaId", areaId);
//		initMaterialProObject.put("chgServSpecId", chgServSpecId);
//		initMaterialProObject.put("prodId", prodId);
//		initMaterialProObject.put("workorder", workorder);
//		initMaterialProObject.put("soNbr", soNbr);
//		String materialTypeJson = getPostHttpContent(initMaterialTypeDataUrl,
//				initMaterialProObject.toString());
//
//		materialInitList = JSON.parseArray(materialTypeJson,
//				MaterialSpecMVO.class);
//
//		if (materialInitList != null && materialInitList.size() > 0) {
//			for (int i = 0; i < materialInitList.size(); i++) {
//				ScanRowObj sr = new ScanRowObj();
//				String proNum = materialInitList.get(i).getUseAmount() + "";
//				if("null".equals(proNum)){
//					proNum = "0";
//				}
//				String scanProName = materialInitList.get(i).getName();
//				String scanType = materialInitList.get(i).getStsWords();
//
//				sr.setProNum(proNum);
//				sr.setScanProName(scanProName);
//				sr.setScanType(scanType);
//				listdata.add(sr);
//			}
//		}
//	}
//
//	/**
//	 * 查询通话记录，并保存数据
//	 * 
//	 * @author maxun
//	 */
//	private void initCallLog() {
//		isCallLogException = false;
//		JSONObject requestJsonObject = new JSONObject();
//
//		JSONArray responseJsonArray = new JSONArray();
//		try {
//			requestJsonObject.put("woNbr", workorder);
//			responseJson = getPostHttpContent(
//					"tm/MosCallLogAction.do?method=queryCallLog4MOS",
//					requestJsonObject.toString());
//			if (StringUtil.isExcetionInfo(responseJson)) {
//				isCallLogException = true;
//				return;
//			}
//
//			if (responseJson != null) {
//				responseJsonArray = new JSONArray(responseJson);
//			}
//
//			if (responseJsonArray != null) {
//				for (int i = 0; i < responseJsonArray.length(); i++) {
//					JSONObject callLogSvoJsonObject = responseJsonArray
//							.getJSONObject(i);
//					HashMap<String, Object> map = new HashMap<String, Object>();
//					map.put("call_log_list_item_text",
//							DateUtil.dateHm2Str(JsonUtil.getUtilDateByLong(
//									callLogSvoJsonObject, "startTime"))
//									+ "\t"
//									+ JsonUtil.getString(callLogSvoJsonObject,
//											"callDuration") + "秒");
//					map.put("call_log_list_item_call_log_id", JsonUtil
//							.getString(callLogSvoJsonObject, "mosCallLogId"));
//					callLogList.add(map);
//				}
//			}
//		} catch (JSONException e) {
//			Log.d("【JSON】", "initCallLog Json存取异常！");
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 初始化照片页面信息
//	 */
//	private void initPhotoPage() {
//		isPhotoPageException = false;
//		initPhotoJsonObject.put("woNbr", workorder);
//		photoJson = getPostHttpContent(initPhotourl,
//				initPhotoJsonObject.toString());
//		if (StringUtil.isExcetionInfo(photoJson)) {
//			isCallLogException = true;
//			return;
//		}
//		if (photoJson.toString().equals("null")) {
//			isPhotoPageException = true;
//			return;
//		}
//		List<SoAttachMVO> woList = JSON
//				.parseArray(photoJson, SoAttachMVO.class);
//		for (int i = 0; i < woList.size(); i++) {
//			if (woList.get(i) != null){
//				byte[] bitmapBytes = woList.get(i).getBytes();
//				myBitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0,
//						bitmapBytes.length);
//				Map soAttachMap = new HashMap();
//				if (myBitmap != null) {
//					soAttachMap.put("image", myBitmap);
//					TakePhotoMain.j++;
//				}
//				soAttachMap.put("soAttachId", woList.get(i).getSoAttachId());
//				soAttachMap.put("desc", woList.get(i).getRemarks());
//				TakePhotoMain.soAttachList.add(soAttachMap);
//			}
//		}
//
//	}
//
//	// need update
//	/**
//	 * 初始化日志页面信息
//	 * 
//	 * @author zhangshaoqing
//	 */
//	private void initLogPage() {
//
//		JSONObject requestJsonObject = new JSONObject();
//		JSONArray responseJsonArray = new JSONArray();
//		try {
//			requestJsonObject.put("woNbr", workorder);
//			responseJson1 = getPostHttpContent(
//					"tm/WoDetailAction.do?method=woHandleInfo4MOS",
//					requestJsonObject.toString());
//			if (responseJson1 != null) {
//				responseJsonArray = new JSONArray(responseJson1);
//			}
//			if (responseJsonArray != null) {
//				for (int i = 0; i < responseJsonArray.length(); i++) {
//					JSONObject LogSvoJsonObject = responseJsonArray
//							.getJSONObject(i);
//					HashMap<String, Object> map = new HashMap<String, Object>();
//					SimpleDateFormat sfd = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");// 格式化成24小时
//					date = new Date(Long.parseLong(LogSvoJsonObject.get(
//							"handleDate").toString()));
//					handle_date = sfd.format(date);// 操作时间
//
//					map.put("list_item_handle_date", handle_date);
//					map.put("list_item_handle_type_name",
//							LogSvoJsonObject.get("handleTypeName").toString());
//					if (LogSvoJsonObject.has("remarks")) {
//						map.put("list_item_reason_info",
//								LogSvoJsonObject.get("remarks").toString());
//					}
//					if (LogSvoJsonObject.has("staffName")) {
//						map.put("list_item_staff_name",
//								LogSvoJsonObject.get("staffName").toString());
//					}
//					if (LogSvoJsonObject.has("failReasonName")) {
//						map.put("list_item_fail_reason_name", LogSvoJsonObject
//								.get("failReasonName").toString());
//					}
//					if (LogSvoJsonObject.has("workareaName")) {
//						map.put("list_item_workarea_name", LogSvoJsonObject
//								.get("workareaName").toString());
//					}
//					if (LogSvoJsonObject.has("wostaffName")) {
//						map.put("list_item_wostaff_name",
//								LogSvoJsonObject.get("wostaffName").toString());
//					}
//					if (LogSvoJsonObject.has("handleRate")) {
//						map.put("list_item_handle_rate",
//								LogSvoJsonObject.get("handleRate").toString());
//					}
//					if (LogSvoJsonObject.has("overTimeName")) {
//						map.put("list_item_over_time_name", LogSvoJsonObject
//								.get("overTimeName").toString());
//					}
//
//					logList.add(map);
//				}
//			}
//		} catch (JSONException e) {
//			Log.d("【JSON】", "initCallLog Json存取异常！");
//			e.printStackTrace();
//		}
//
//	}
//
//	public class MyAdapter extends BaseAdapter {
//		private Context context;
//
//		// 定义构造方法加入Context参数
//		public MyAdapter(Context context) {
//			this.context = context;
//		}
//
//		@Override
//		// 返回值为所有图片的个数
//		public int getCount() {
//
//			return TakePhotoMain.soAttachList.size();
//		}
//
//		public Object getItem(int position) {
//			return null;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return 0;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			// 定义一个ImageView显示图片
//			ImageView imageView;
//			if (convertView == null) {
//				imageView = new ImageView(context);
//				DisplayMetrics dm = new DisplayMetrics();
//				getWindowManager().getDefaultDisplay().getMetrics(dm);
//				screenW = dm.widthPixels;// 获取屏幕宽度
//				int whith = screenW / 3;
//				imageView.setLayoutParams(new GridView.LayoutParams(whith - 5,
//						whith - 5));
//				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//				imageView.setPadding(10, 0, 0, 10);
//			} else {
//				imageView = (ImageView) convertView;
//			}
//			imageView.setImageBitmap((Bitmap) TakePhotoMain.soAttachList.get(
//					position).get("image"));
//			return imageView;
//		}
//	}
//
//	// 初始化第二屏的第一个页面
//	private void initPage11(View funcNavPage1) {
//		this.thumbnail = (GridView) funcNavPage1.findViewById(R.id.gridView1);
//
//		// 把自定义的适配器加载到gridView中
//		this.thumbnail.setAdapter(new MyAdapter(WoDetailActivity.this));
//		// 点击单张图片显示
//		this.thumbnail
//				.setOnItemClickListener(new GridView.OnItemClickListener() {
//
//					@Override
//					public void onItemClick(AdapterView<?> arg0, View arg1,
//							int postion, long arg3) {
//						Intent intent = new Intent();
//						intent.setClass(WoDetailActivity.this,
//								ThumbnailActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putInt("ID", postion);
//						intent.putExtras(bundle);
//						startActivityForResult(intent, 3);
//					}
//				});
//	}
//
//	int workindex = 0;
//
//	// 曾加以行
//	private void addRow(String key, String value, LinearLayout layout) {
//
//		View itemview = View.inflate(this, R.layout.add_row_wodetail, null);
//		// ID自增
//		workindex = workindex + 1;
//		// 给item布局添加ID
//		itemview.setId(workindex);
//		initOneRowValue(itemview, key, value);
//		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.FILL_PARENT,
//				LinearLayout.LayoutParams.WRAP_CONTENT);
//		p.topMargin = 10;
//		layout.addView(itemview, p);
//
//	}
//
//	/**
//	 * @param itemview
//	 *            初始化新增行数据
//	 */
//	private void initOneRowValue(View itemview, String key, String value) {
//		TextView content = (TextView) itemview
//				.findViewById(R.id.detail_basinfo_work_order_content);
//		content.setText(value);
//		content.setFocusableInTouchMode(true);
//		content.setFocusable(true);
//		content.setClickable(true);
//		content.setLongClickable(true);
//		content.setMovementMethod(  ArrowKeyMovementMethod.getInstance());
//		content.setText(content.getText(),BufferType.SPANNABLE );
//		TextView title = (TextView) itemview
//				.findViewById(R.id.detail_basinfo_work_order_title);
//		title.setText(key + " : ");
//	}
//
//	// 初始化基本信息页面，动态加载数据，只有当初始化完后才会显示页面
//	private void initPage1(View funcNavPage1) {
//
//		workinfoMainline = (LinearLayout) funcNavPage1
//				.findViewById(R.id.detail_basinfo_layout);
//
//		if (workinfoMainline.getChildCount() == 0) {// 判断是否是第一次初始化
//			for (int i = 0; i < detailInfoWorkList.size(); i++) {
//				Map map = (Map) detailInfoWorkList.get(i);
//				String value = "";
//				String key = map.keySet().iterator().next().toString();
//				if (!map.get(key).toString().equals("null")) {
//					value = (map.get(key).toString());
//				}
//				addRow(key, value, workinfoMainline);
//			}
//
//		}
//	}
//
//	// 初始化基本信息页面，动态加载数据，只有当初始化完后才会显示页面
//	private void initPage3(View funcNavPage1) {
//
//		orderinfoMainline = (LinearLayout) funcNavPage1
//				.findViewById(R.id.detail_orderinfo_layout);
//		if (orderinfoMainline.getChildCount() == 0) {
//
//			for (int i = 0; i < detailInfoOrderList.size(); i++) {
//				Map map = (Map) detailInfoOrderList.get(i);
//				String value = "";
//				String key = map.keySet().iterator().next().toString();
//				if (!map.get(key).toString().equals("null")) {
//					value = (map.get(key).toString());
//				}
//				addRow(key, value, orderinfoMainline);
//			}
//
//		}
//
//	}
//
//	// 初始化资源信息页面
//	private void initPage2(View funcNavPage1) {
//
//		resinfoMainline = (LinearLayout) funcNavPage1
//				.findViewById(R.id.detail_resinfo_layout);
//		resPage = funcNavPage1;
//		// 初始化资源title
//		initResInfo(resPage, switchFlag);// 最后是先写新的后截取字符
//	}
//
//	private void initPage4(View callLogView) {
//		ListView callLogListView = (ListView) callLogView
//				.findViewById(R.id.call_log_layout);
//		callLogAdapter = new AllTaskListAdapterActivity(this, callLogList,
//				R.layout.call_log_list_item, new String[] {
//						"call_log_list_item_text",
//						"call_log_list_item_call_log_id" }, new int[] {
//						R.id.call_log_list_item_text,
//						R.id.call_log_list_item_call_log_id });
//		callLogListView.setAdapter(callLogAdapter);
//		callLogListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				intent = new Intent(WoDetailActivity.this,
//						CallLogDetailActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putString(
//						"mosCallLogId",
//						(String) callLogList.get(position).get(
//								"call_log_list_item_call_log_id"));
//				intent.putExtras(bundle);
//				startActivity(intent);
//			}
//		});
//	}
//
//	// need update
//	private void initPage6(View pageView) {
//
//		metralView = pageView;
//
//		if (listdata != null) {
//
//			// 去掉0记录
//			for (int index = 0; index < listdata.size(); index++) {
//				if ("0".equals(listdata.get(index).getProNum())) {
//					listdata.remove(index);
//					index--;
//				}
//			}
//
//			if (metralView != null) {
//				mainline = (LinearLayout) metralView
//						.findViewById(R.id.detail_relay_material_page);
//			} else {
//				mainline = (LinearLayout) listViews.get(5).findViewById(
//						R.id.detail_relay_material_page);
//			}
//			mainline.removeAllViews();
//
//			int k = 0;
//			// 直接显示即可
//			for (int index = 0; index < listdata.size(); index++) {
//
//				View itemview = View.inflate(this,
//						R.layout.detail_material_item, null);
//				// ID自增
//				// 给item布局添加ID
//				itemview.setId(k);
//				k++;
//				TextView deviceNameText = (TextView) itemview
//						.findViewById(R.id.material_info_content1);
//				deviceNameText.setText(listdata.get(index).getScanProName()
//						+ ": " + listdata.get(index).getProNum() + "  "
//						+ listdata.get(index).getScanType());
//				mainline.addView(itemview);
//			}
//		}
//
//	}
//
//	/**
//	 * 将json 数组转换为Map 对象
//	 * 
//	 * @param jsonString
//	 * @return
//	 */
//
//	// 只是把一个对象细化，一个对象为json 串里的一个元素
//	public static Map<String, Object> getMap(String jsonString) {
//		JSONObject jsonObject;
//		try {
//			jsonObject = new JSONObject(jsonString);
//			Iterator<String> keyIter = jsonObject.keys();
//			String key;
//			Object value;
//			Map<String, Object> valueMap = new HashMap<String, Object>();
//			while (keyIter.hasNext()) {
//				key = (String) keyIter.next();
//				value = jsonObject.get(key);
//				valueMap.put(key, value);
//				if(key.equals("MAC")) {
//					barCode = (String) value;
//				}
//			}
//			return valueMap;
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 页卡切换监听
//	 */
//	public class MyOnPageChangeListener implements OnPageChangeListener {
//		Animation animation = null;
//
//		@Override
//		public void onPageSelected(int arg0) {
//			// 页卡1 -> 页卡2 偏移量
//			switch (arg0) {
//			case 0:
//				if (currIndex == 1) {
//					// 从2到1
//					moveOnetoLeft(perSpacing);
//					if (switchResInfoBtn.isShowing()) {
//						switchResInfoBtn.dismiss();
//					}
//				} else if (currIndex == 2) {
//					// 从3到1
//					moveTwoToLeft(perSpacing);
//				} else if (currIndex == 3) {
//					// 从4到1
//					animation = new TranslateAnimation(3 * perSpacing, 0, 0, 0);
//				}
//				leftMove.setVisibility(View.GONE);
//				break;
//			case 1:
//				// 从1 到 2 页面
//				if (currIndex == 0) {
//					moveOneToRight(perSpacing);
//				} else if (currIndex == 2) {
//					// 从3到2
//					moveOnetoLeft(perSpacing);
//					rightMove.setVisibility(View.VISIBLE);
//				} else if (currIndex == 3) {
//					// 从4到2
//					moveTwoToLeft(perSpacing);
//				} else if (currIndex == 4) {
//					// 从5到2
//					animation = new TranslateAnimation(3 * perSpacing, 0, 0, 0);
//				}
//
//				if (!switchResInfoBtn.isShowing()) {
//					switchResInfoBtn.showAsDropDown(linearLayout,
//							screenW * 3 / 4, 0);
//				}
//
//				break;
//			case 2:
//				// 从1到3
//				if (currIndex == 0) {
//					moveTwoToRight(perSpacing);
//				} else if (currIndex == 1) {
//					// 从 2 到3
//					moveOneToRight(perSpacing);
//					if (switchResInfoBtn.isShowing()) {
//						switchResInfoBtn.dismiss();
//					}
//				} else if (currIndex == 3) {
//					// 从 4 到3
//					moveOnetoLeft(perSpacing);
//
//				} else if (currIndex == 4) {
//					moveTwoToLeft(perSpacing);
//				} else if (currIndex == 5) {
//					// 从 6 到3
//					animation = new TranslateAnimation(3 * perSpacing, 0, 0, 0);
//				}
//				break;
//			case 3:
//
//				if (currIndex == 0) {
//					// 从1到4
//					animation = new TranslateAnimation(0, 3 * perSpacing, 0, 0);
//				} else if (currIndex == 1) {
//					// 从 2 到4
//					moveTwoToRight(perSpacing);
//					if (switchResInfoBtn.isShowing()) {
//						switchResInfoBtn.dismiss();
//					}
//				} else if (currIndex == 2) {
//					// 从3 到4
//					moveOneToRight(perSpacing);
//
//				} else if (currIndex == 4) {
//					// //从 5到4
//					moveOnetoLeft(perSpacing);
//					// rightMove.setVisibility(View.VISIBLE);
//				} else if (currIndex == 5) {
//					// //从 6到4
//					moveTwoToLeft(perSpacing);
//
//				} else if (currIndex == 6) {
//					// 7到4
//					animation = new TranslateAnimation(3 * perSpacing, 0, 0, 0);
//				}
//				if (isCallLogException) {
//					Toast.makeText(getApplicationContext(),
//							StringUtil.getExceptionDesc(responseJson),
//							Toast.LENGTH_SHORT).show();
//				}
//				break;
//			case 4:
//				if (currIndex == 1) {
//					// 从 2 到5
//					animation = new TranslateAnimation(0, 3 * perSpacing, 0, 0);
//					if (switchResInfoBtn.isShowing()) {
//						switchResInfoBtn.dismiss();
//					}
//				} else if (currIndex == 2) {
//					// //从3到5
//					moveTwoToRight(perSpacing);
//
//				} else if (currIndex == 3) {
//					// 从 4到5
//					moveOneToRight(perSpacing);
//					leftMove.setVisibility(View.VISIBLE);
//
//				} else if (currIndex == 5) {
//					// //从 6 到5
//					moveOnetoLeft(perSpacing);
//				} else if (currIndex == 6) {
//					// 从 7 到5
//					moveTwoToLeft(perSpacing);
//				} else if (currIndex == 7) {
//					// //从 8到5
//					animation = new TranslateAnimation(3 * perSpacing, 0, 0, 0);
//				}
//				break;
//
//			case 5:
//				if (currIndex == 2) {
//					// //从3到6
//					animation = new TranslateAnimation(0, 3 * perSpacing, 0, 0);
//				} else if (currIndex == 3) {
//					// //从4到6
//					moveTwoToRight(perSpacing);
//
//				} else if (currIndex == 4) {
//					// 从 5到6
//					moveOneToRight(perSpacing);
//
//				} else if (currIndex == 6) {
//					// 从 7到6
//					moveOnetoLeft(perSpacing);
//
//				} else if (currIndex == 7) {
//					// 从 8到6
//					moveTwoToLeft(perSpacing);
//				}
//				rightMove.setVisibility(View.GONE);
//				break;
//			case 6:
//				if (currIndex == 3) {
//					// 从 4到7
//					animation = new TranslateAnimation(0, 3 * perSpacing, 0, 0);
//				} else if (currIndex == 4) {
//					// 从 5到7
//					moveTwoToRight(perSpacing);
//				} else if (currIndex == 5) {
//					// 从 6到7
//					moveOneToRight(perSpacing);
//				} else if (currIndex == 7) {
//					// 从 8到7
//					moveOnetoLeft(perSpacing);
//				}
//				break;
//			case 7:
//				if (currIndex == 4) {
//					// 从 5到8
//					animation = new TranslateAnimation(0, 3 * perSpacing, 0, 0);
//				} else if (currIndex == 5) {
//					// 从6到8
//					moveTwoToRight(perSpacing);
//				} else if (currIndex == 6) {
//					// 从 7到8
//					moveOneToRight(perSpacing);
//				}
//				rightMove.setVisibility(View.GONE);
//				break;
//			}
//			setPageTitlesColor(arg0);
//			currIndex = arg0;
//			animation.setFillAfter(true);// True:图片停在动画结束位置
//			animation.setDuration(300);
//			cursor.startAnimation(animation);
//		}
//
//		/**
//		 * @param one
//		 *            向左移动2个单位
//		 */
//		private void moveTwoToLeft(int one) {
//			if (pageTitles.get(currIndex).getLeft() > one * 5 / 2) {
//				animation = new TranslateAnimation(3 * one, one, 0, 0);
//			} else if ((pageTitles.get(currIndex).getLeft() > one * 3 / 2)
//					&& (pageTitles.get(currIndex).getLeft() < one * 5 / 2)) {
//				animation = new TranslateAnimation(2 * one, 0, 0, 0);
//			}
//		}
//
//		/**
//		 * @param one
//		 *            向左移动一个单位
//		 */
//		private void moveOnetoLeft(int one) {
//			if (pageTitles.get(currIndex).getLeft() > one * 5 / 2) {
//				animation = new TranslateAnimation(3 * one, 2 * one, 0, 0);
//			} else if ((pageTitles.get(currIndex).getLeft() > one * 3 / 2)
//					&& (pageTitles.get(currIndex).getLeft() < one * 5 / 2)) {
//				animation = new TranslateAnimation(2 * one, one, 0, 0);
//			} else if ((pageTitles.get(currIndex).getLeft() > one / 2)
//					&& (pageTitles.get(currIndex).getLeft() < one * 3 / 2)) {
//				animation = new TranslateAnimation(one, 0, 0, 0);
//			} else if (pageTitles.get(currIndex).getLeft() < one / 2) {
//				LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) pageTitles
//						.get(0).getLayoutParams();
//				params1.leftMargin = pageTitles.get(0).getLeft() + one;
//				pageTitles.get(0).setLayoutParams(params1);
//				animation = new TranslateAnimation(0, 0, 0, 0);
//			}
//
//		}
//
//		/**
//		 * @param one
//		 *            向右移2个单位
//		 */
//		private void moveTwoToRight(int one) {
//			if (pageTitles.get(currIndex).getLeft() > one * 5 / 2) {
//				LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) pageTitles
//						.get(0).getLayoutParams();
//				params1.leftMargin = pageTitles.get(0).getLeft() - one;
//				pageTitles.get(0).setLayoutParams(params1);
//				animation = new TranslateAnimation(3 * one, 3 * one, 0, 0);
//			} else if ((pageTitles.get(currIndex).getLeft() > one * 3 / 2)
//					&& (pageTitles.get(currIndex).getLeft() < one * 5 / 2)) {
//				animation = new TranslateAnimation(2 * one, 3 * one, 0, 0);
//			} else if ((pageTitles.get(currIndex).getLeft() > one / 2)
//					&& (pageTitles.get(currIndex).getLeft() < one * 3 / 2)) {
//				animation = new TranslateAnimation(one, 3 * one, 0, 0);
//			} else if (pageTitles.get(currIndex).getLeft() < one / 2) {
//				animation = new TranslateAnimation(0, 2 * one, 0, 0);
//			}
//		}
//
//		/**
//		 * @param one
//		 *            向右移动1个单位
//		 */
//		private void moveOneToRight(int one) {
//			if (pageTitles.get(currIndex).getLeft() > one * 5 / 2) {
//				LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) pageTitles
//						.get(0).getLayoutParams();
//				params1.leftMargin = pageTitles.get(0).getLeft() - one;
//				pageTitles.get(0).setLayoutParams(params1);
//				animation = new TranslateAnimation(3 * one, 3 * one, 0, 0);
//			} else if ((pageTitles.get(currIndex).getLeft() > one * 3 / 2)
//					&& (pageTitles.get(currIndex).getLeft() < one * 5 / 2)) {
//				animation = new TranslateAnimation(2 * one, 3 * one, 0, 0);
//			} else if ((pageTitles.get(currIndex).getLeft() > one / 2)
//					&& (pageTitles.get(currIndex).getLeft() < one * 3 / 2)) {
//				animation = new TranslateAnimation(one, 2 * one, 0, 0);
//			} else if (pageTitles.get(currIndex).getLeft() < one / 2) {
//				animation = new TranslateAnimation(0, one, 0, 0);
//			}
//		}
//
//		@Override
//		public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//		}
//
//		@Override
//		public void onPageScrollStateChanged(int arg0) {
//
//		}
//	}
//	
//	/**
//	 * 初始化底部工具条
//	 */
//	private void initGridItem() {
//		
//	}
//	
//	
//	/**
//	 * 获取功能权限列表
//	 */
//	private void initFuncNode() {
//		if(funcNodeList==null)funcNodeList=new ArrayList();
//		String funcListJson =this.getCacheProcess()
//				.getCacheValueInSharedPreferences(this, "funcNodeList");
//		com.alibaba.fastjson.JSONArray funcArray=com.alibaba.fastjson.JSONArray.parseArray(funcListJson);
//		if(funcArray==null||funcArray.size()==0) {
//			Toast.makeText(getApplicationContext(), "所有功能都被屏蔽，请联系维护人员开放功能权限！",
//					Toast.LENGTH_SHORT).show();
//			return ;
//		}
//		for(int i=0;i<funcArray.size();i++) {
//			com.alibaba.fastjson.JSONObject json=(com.alibaba.fastjson.JSONObject)funcArray.get(i);
//			MosFuncNodeSVO funcSVO=JSON.parseObject(json.toJSONString(), MosFuncNodeSVO.class);
//			String funcShortName=funcSVO.getShortName();
//			String type=funcSVO.getType();
//			if("D".equals(type)) {
//				if("HD".equals(funcShortName)) { //回单
//					ivSucBackOrder=new ImageView(this);
//					ivSucBackOrder.setTag(funcSVO);
//					ivSucBackOrder.setImageResource(R.drawable.detail_suc_back_order);
//					funcNodeList.add(ivSucBackOrder);
//				}
//				
//				if("TD".equals(funcShortName)) {//退单
//					ivFailBackOrder=new ImageView(this);
//					ivFailBackOrder.setTag(funcSVO);
//					ivFailBackOrder.setImageResource(R.drawable.detail_fail_back_order);
//					funcNodeList.add(ivFailBackOrder);
//				}
//				if("HJ".equals(funcShortName)) {//呼叫
//					ivCall=new ImageView(this);
//					ivCall.setTag(funcSVO);
//					ivCall.setImageResource(R.drawable.detail_call);
//					funcNodeList.add(ivCall);
//				}
//				if("JGHS".equals(funcShortName)) {//竣工核实
//					ivRetuanBack=new ImageView(this);
//					ivRetuanBack.setTag(funcSVO);
//					ivRetuanBack.setImageResource(R.drawable.detail_call_back);
//					funcNodeList.add(ivRetuanBack);
//				}
//				if("CLHT".equals(funcShortName)) {//材料回填
//					ivMaterailBack=new ImageView(this);
//					ivMaterailBack.setTag(funcSVO);
//					ivMaterailBack.setImageResource(R.drawable.detail_materal_fill_back);
//					funcNodeList.add(ivMaterailBack);
//				}
//				//-------------------wh
//				if("XGBZ".equals(funcShortName)) {//修改备注
//					ivUpdateRemarks=new ImageView(this);
//					ivUpdateRemarks.setTag(funcSVO);
//					ivUpdateRemarks.setImageResource(R.drawable.detail_remarks_order);
//					funcNodeList.add(ivUpdateRemarks);
//				}
//				if("ZP".equals(funcShortName)) {//转派
//					ivChangeOrder=new ImageView(this);
//					ivChangeOrder.setTag(funcSVO);
//					ivChangeOrder.setImageResource(R.drawable.detail_change_order);
//					funcNodeList.add(ivChangeOrder);
//				}
//				if("YY".equals(funcShortName)) {//预约
//					ivPreOrder=new ImageView(this);
//					ivPreOrder.setTag(funcSVO);
//					ivPreOrder.setImageResource(R.drawable.detail_pre_order);
//					funcNodeList.add(ivPreOrder);
//				}
//				if("WGWH".equals(funcShortName)) {//网格维护
//					ivDetailServ=new ImageView(this);
//					ivDetailServ.setTag(funcSVO);
//					ivDetailServ.setImageResource(R.drawable.detail_serv_dept);
//					funcNodeList.add(ivDetailServ);
//				}
//				if("SBQR".equals(funcShortName)) {//设备确认
//					barCodeValidate=new ImageView(this);
//					barCodeValidate.setTag(funcSVO);
//					barCodeValidate.setImageResource(R.drawable.bar_code_validate);
//					funcNodeList.add(barCodeValidate);
//				}
//				if("ZYBG".equals(funcShortName)) {//资源变更
//					ivResChange=new ImageView(this);
//					ivResChange.setTag(funcSVO);
//					ivResChange.setImageResource(R.drawable.detail_fault_check);
//					funcNodeList.add(ivResChange);
//				}
//				if("PZ".equals(funcShortName)) {//拍照
//					ivTakePhoto=new ImageView(this);
//					ivTakePhoto.setTag(funcSVO);
//					ivTakePhoto.setImageResource(R.drawable.detail_take_photo);
//					funcNodeList.add(ivTakePhoto);
//				}
//				if("ZYHC".equals(funcShortName)) {//资源核查
//					ivResCheck=new ImageView(this);
//					ivResCheck.setTag(funcSVO);
//					ivResCheck.setImageResource(R.drawable.detail_resourse_check);
//					funcNodeList.add(ivResCheck);
//				}
//				if("YWCS".equals(funcShortName)) {//业务测试
//					ivServiceTest=new ImageView(this);
//					ivServiceTest.setTag(funcSVO);
//					ivServiceTest.setImageResource(R.drawable.detail_service_test);
//					funcNodeList.add(ivServiceTest);
//				}
//				if("LYQD".equals(funcShortName)) {//履约签到
//					ivOnScheduleBtn=new ImageView(this);
//					ivOnScheduleBtn.setTag(funcSVO);
//					ivOnScheduleBtn.setImageResource(R.drawable.on_schedule);
//					funcNodeList.add(ivOnScheduleBtn);
//				}
//			}
//		} 
//		
//	}
//	
// 
//
//	// GridViewAdapter 适配器
//	public class GridViewAdapter extends BaseAdapter {
//
//		Context mContext;
//		LayoutInflater mLayoutInflater;
//		MenuItemData mMenuItemData;
//
//		public GridViewAdapter(Context context, MenuItemData menuItemData) {
//			mContext = context;
////			mLayoutInflater = LayoutInflater.from(context);
////			mMenuItemData = menuItemData;
//		}
//
//		@Override
//		public int getCount() {
//			int count;
//			if ("P".equals(woType)) {
//				count = 1;
//			} else {
//				if(funcNodeList.size()==0) {
//					count=0;
//				}else {
//					count = funcNodeList.size()>5?5:funcNodeList.size();
//				}
//			}
//			return count;
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return position;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			ImageView v=null;
//			if ("P".equals(woType)) {
//				v=new ImageView(WoDetailActivity.this);
//				MosFuncNodeSVO svo=new MosFuncNodeSVO();
//				svo.setShortName("HD");
//				v.setTag(svo);
//				v.setBackgroundDrawable(getResources().getDrawable(
//						R.drawable.detail_info_fill_back));
//			} else {
//				if(funcNodeList.size()>0) {
//					v=(ImageView)funcNodeList.get(position);
//				}else {
//					v=new ImageView(WoDetailActivity.this);
//				}
//				
//			}
//			return v;
//		}
//	}
//
//	// 封装date数据类型
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
//
//	private class initThread extends Thread {
//		public void run() {
//			initPageMap();
//		}
//	}
//
//	// 用于接收线程发送的消息
//	static class MSGHandler extends Handler {
//		WeakReference<WoDetailActivity> mActivity;
//
//		MSGHandler(WoDetailActivity activity) {
//			mActivity = new WeakReference<WoDetailActivity>(activity);
//		}
//
//		@Override
//		public void handleMessage(Message msg) {
//			WoDetailActivity activity = mActivity.get();
//			switch (msg.what) {
//			case 1: {
//				activity.initViewPager();
//				activity.initSwitchResInfoMenu();
//			}
//				break;
//			case 6: {// 调完后台的领单功能之后，用于显示toast和显示后刷新
//				Toast toast;
//
//				toast = Toast.makeText(activity.getApplicationContext(),
//						activity.fetchWoResult, Toast.LENGTH_SHORT);
//				toast.show();
//				if (activity.fetchWoResult.equals("成功领单!")) {
//					Intent intent = new Intent();
//					// intent.putExtra("isFinishedFetchWo", true);
//					activity.setResult(RESULT_OK, intent);
//					activity.finish();
//				}
//			}
//				break;
//			}
//			activity.closeProcessDialog();
//		}
//	};
//
//	private void initSwitchResInfoMenu() {
//		// 设置popupWindow的布局
//		mInflater = getLayoutInflater();
//		materalFillBackLayout = mInflater.inflate(
//				R.layout.wo_detail_popmenu_item, null);
//		// 设置popupWindow的布局
//		switchResInfoBtn = new PopupWindow(materalFillBackLayout,
//				WindowManager.LayoutParams.WRAP_CONTENT,
//				WindowManager.LayoutParams.WRAP_CONTENT);
//		final LinearLayout replayOrder = (LinearLayout) materalFillBackLayout
//				.findViewById(R.id.scan_detail_pop_click_layout);
//		replayOrder.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// 重新赋值
//				if ("new".equals(switchFlag)) {
//					replayOrder
//							.setBackgroundResource(R.drawable.detail_res_change_img_down);
//					switchFlag = "old";
//				} else {
//					switchFlag = "new";
//					replayOrder
//							.setBackgroundResource(R.drawable.detail_pop_item_bg_up);
//				}
//				initPage2(resPage);
//
//			}
//		});
//
//	}
//
//	// 初始化资源信息
//	private void initResInfo(View funcNavPage, String switchflag) {
//
//		if ("new".equals(switchflag)) {
//
//			if (resinfoMainline.getChildCount() > 0) {
//				resinfoMainline.removeAllViews();
//			}
//
//			for (int i = 0; i < detailInfoNewResList.size(); i++) {
//				Map map = (Map) detailInfoNewResList.get(i);
//				String value = "";
//				String key = map.keySet().iterator().next().toString();
//				if (!map.get(key).toString().equals("null")) {
//					value = (map.get(key).toString());
//				}
//				addRow(key, value, resinfoMainline);
//			}
//
//		} else if ("old".equals(switchflag)) {
//
//			if (resinfoMainline.getChildCount() > 0) {
//				resinfoMainline.removeAllViews();
//			}
//
//			for (int i = 0; i < detailInfoOldResList.size(); i++) {
//				Map map = (Map) detailInfoOldResList.get(i);
//				String value = "";
//				String key = map.keySet().iterator().next().toString();
//				if (!map.get(key).toString().equals("null")) {
//					value = (map.get(key).toString());
//				}
//				addRow(key, value, resinfoMainline);
//			}
//
//		}
//	}
//
//	// 判断按键 菜单的显示与隐藏
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			if (materalFillBackMenu.isShowing()) {
//				materalFillBackMenu.dismiss();
//			} else {
//				super.onKeyDown(keyCode, event);
//			}
//		}
//		return true;
//	}
//	
//	//初始化抽屉效果
//	private void initSlidingDrawer() {
//		slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingdrawer);
//		LayoutParams layp= slidingDrawer.getLayoutParams();
//		
//		LinearLayout ll=(LinearLayout)slidingDrawer.findViewById(R.id.content); 
//		if(funcNodeList.size()<=5) {//如果小于等于5，则使抽屉不可见
//			slidingDrawer.setVisibility(View.GONE);
//		}else {//放入抽屉里
//			List funcSubList=funcNodeList.subList(5, funcNodeList.size());
//			int subListSize=funcSubList.size();
//			float scale = this.getResources().getDisplayMetrics().density; 
//			int pxHeight=0;
//			if(subListSize<=5) {
//				pxHeight=80;//dip值
//				
//			}else {
//				pxHeight=110;//dip
//			}
//			//转成分辨率
//			int hight= (int)(pxHeight * scale + 0.5f);
//			layp.height=hight;
//			slidingDrawer.setLayoutParams(layp);
//			
//			LayoutParams lparam = new LinearLayout.LayoutParams(
//	                  LayoutParams.WRAP_CONTENT,
//	                  LayoutParams.WRAP_CONTENT, 1.0f);
//			List cloneList=new ArrayList();
//			cloneList.addAll(funcSubList);
//			
//			if(subListSize%5!=0) {//让按钮个数是5的整数倍，方便处理
//				int n=5-subListSize%5;
//				for(int i=0;i<n;i++) {
//					ImageView v=new ImageView(this);
//					v.setImageResource(R.drawable.wo_detail_grid_no_color);
//					v.setLayoutParams(lparam);
//					cloneList.add(v);
//				}
//			}
//			
//			LinearLayout lil=null;
//			for(int i=0;i<cloneList.size();i++) {
//				if((i+1)%5==1) { //每5个放一行
//					lil=new LinearLayout(this);
//					ll.addView(lil);
//				}
//				ImageView iv=(ImageView)cloneList.get(i);
//				iv.setLayoutParams(lparam);
//				lil.addView(iv);
//			}
////			
////			//使n个图片平分空间
////			LayoutParams param = new LinearLayout.LayoutParams(
////                    LayoutParams.WRAP_CONTENT,
////                    LayoutParams.WRAP_CONTENT, 1.0f);
////			LinearLayout ll1=new LinearLayout(this);
////			LinearLayout ll2=new LinearLayout(this);
////			LayoutParams param1 = new LinearLayout.LayoutParams(
////                    LayoutParams.FILL_PARENT,
////                    LayoutParams.WRAP_CONTENT);
////			ll1.setLayoutParams(param1);
////			ll2.setLayoutParams(param1);
////			ll.addView(ll1);
////			ll.addView(ll2);
////			
////			for(int i=0;i<funcSubList.size();i++) {
////				ImageView iv=(ImageView)funcSubList.get(i);
////				iv.setLayoutParams(param);
////				ll1.addView(iv);
//////				
////				ImageView iv1=new ImageView(this);
////				iv1.setLayoutParams(param);
////				iv1.setImageResource(R.drawable.detail_suc_back_order);
////				ll2.addView(iv1);
////			}
//		}
//	}
//
//	// need update
//	@Override
//	protected void initView() {
//		// 获取手机屏幕的宽度
//		getScreenWidth();
//		// 重新设置页卡textview的宽度
//		initPageTextWide(perSpacing);
//		initSlidingDrawer();
//		linearLayout = (LinearLayout) findViewById(R.id.detail_title_layout);// 所有页卡
//		mToolBarGrid = (GridView) findViewById(R.id.GridView_toolbar);
//		if(funcNodeList.size()==0) {
//			mToolBarGrid.setVisibility(View.GONE);
//		}
//		
//		handleImageBtn = (ImageView) findViewById(R.id.scanhandle);// 把手
//		callText = (TextView) findViewById(R.id.detail_title_layout_title4);// 通话记录的页卡
//		leftMove = (ImageView) findViewById(R.id.left_more);
//		leftMove.setVisibility(View.GONE);
//		rightMove = (ImageView) findViewById(R.id.right_more);
//
//		initPageTitles();
//
//		if (menuFlag == null) {
//			// 初始化底部第一行按鈕
//			if ("P".equals(woType)) {
//				mToolBarGrid.setNumColumns(1);
//			}
//
//			mToolBarAdapter = new GridViewAdapter(this, mToolBarItemData);
//			mToolBarGrid.setAdapter(mToolBarAdapter);
//		} else if (menuFlag.equals("WoFetch")) {
//			setTitleRightButtonImg(R.drawable.titlebar_img_btn_fetchwo);
//			setTitleRightButtonVisibility(View.VISIBLE);
//		}
//
//		mPager = (ViewPager) findViewById(R.id.vPager);
//		listViews = new ArrayList<View>();
//		LayoutInflater mInflater = getLayoutInflater();
//		listViews.add(mInflater.inflate(R.layout.activity_work_order_basicinfo,
//				null)); // 加载静态xml布局文件
//		listViews.add(mInflater.inflate(R.layout.activity_work_order_resinfo,
//				null));
//		listViews.add(mInflater.inflate(R.layout.activity_order_info, null));
//		listViews.add(mInflater.inflate(R.layout.activity_tel_record, null));
//		listViews.add(mInflater.inflate(R.layout.activity_photos_info, null)); // 加载静态xml布局文件
//		listViews.add(mInflater.inflate(R.layout.activity_material_info, null));
//		// listViews.add(mInflater.inflate(R.layout.activity_log_info, null));
//		// listViews.add(mInflater.inflate(R.layout.activity_other_info, null));
//
//		initImageView();
//
//		// 设置popupWindow的布局
//		materalFillBackLayout = mInflater.inflate(
//				R.layout.wo_detail_popmenu_top_item, null);// 录入和回收弹出框
//		outsidelineConstructionLayout = mInflater.inflate(
//				R.layout.wo_details_outside_pop, null);
//		// 设置popupWindow的布局
//		materalFillBackMenu = new PopupWindow(materalFillBackLayout,
//				WindowManager.LayoutParams.WRAP_CONTENT,
//				WindowManager.LayoutParams.WRAP_CONTENT);
//		outsidelineConstructionMenu = new PopupWindow(
//				outsidelineConstructionLayout,
//				WindowManager.LayoutParams.WRAP_CONTENT,
//				WindowManager.LayoutParams.WRAP_CONTENT);
//		materalFillBackMenu.setFocusable(true);
//		materalFillBackMenu.setOutsideTouchable(true);
//		materalFillBackMenu.setBackgroundDrawable(new BitmapDrawable());
//
//		// 菜单的监听事件。。。。
//		materalInput = (TextView) materalFillBackLayout
//				.findViewById(R.id.materal_input_btn);
//		materalRecover = (TextView) materalFillBackLayout
//				.findViewById(R.id.materal_back_btn);
//		outsideConstruction = (ImageView) outsidelineConstructionLayout
//				.findViewById(R.id.outside_menu);
//
//	}
//	
//	//注册工具条底部项的监听事件
//	private void registerImageViewListener(){
//		
//		if(ivSucBackOrder!=null) { //回单
//			ivSucBackOrder.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//
//					if("V".equals(((MosFuncNodeSVO)ivSucBackOrder.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if (!"P".equals(woType)) {// 正常回单
//							// 环节，装机公司（装）
//							if (StringUtil.isBlank(materialStepId) || (!StringUtil.isBlank(materialStepId) && materialStepId.indexOf(stepId) != -1))  {
//								// 判断是否已经回填
//								whichAction = "backOrder";
//								new materialReturnThread().start();
//								// if(!isMaterialReturn || !isReturn){
//								// return;
//								// }
//							} else {
//								if (!(soCat.endsWith(Constant.SO_CAT_SPS) && actType
//										.endsWith("拆"))) {
//									whichAction = "backOrder";
//									new returnThread().start();
//									// if(!isReturn){
//									// return;
//									// }
//								} else {
//									Intent intent0 = new Intent();
//									if (Constant.SO_CAT_SPS.equals(soCat)) {
//										intent0.setClass(WoDetailActivity.this,
//												BackOrderSuccessActivity.class);
//									} else if (Constant.SO_CAT_SGS
//											.equals(soCat)) {
//										intent0.setClass(
//												WoDetailActivity.this,
//												BackSgsOrderSuccessActivity.class);
//										intent0.putExtra("prodId", prodId);
//									}
//									intent0.putExtra("woNbr", workorder);
//									intent0.putExtra("soCat", soCat);
//									intent0.putExtra("soNbr", soNbr);
//									startActivity(intent0);
//								}
//							}
//			
//						} else if ("P".equals(woType)) {// 外勘单
//							Intent intent0 = new Intent();
//							intent0.setClass(WoDetailActivity.this,
//									InfoBackWorkOrderActivity.class);
//							intent0.putExtra("woNbr", workorder);
//							startActivity(intent0);
//						}
//					}
//				}
//			});
//		}
//		
//		//-------------------wanghu
//		
//		if(ivUpdateRemarks!=null) {//修改备注
//			ivUpdateRemarks.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivUpdateRemarks.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{						
//							Intent intent1 = new Intent();				
//							intent1.setClass(WoDetailActivity.this,
//										UpdateRemarksActivity.class);
//							intent1.putExtra("woNbr", workorder);
//							intent1.putExtra("soCat", soCat);
//							startActivity(intent1);
//					}
//				}
//			});
//			
//		}
//		
//		
//		if(ivFailBackOrder!=null) {//退单
//			ivFailBackOrder.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivFailBackOrder.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						//如果是障碍单 且为山西省份 ，不允许退单 2013-12-24 fus
//						if(Constant.SO_CAT_SGS
//								.equals(soCat) && Constant.MOS_PROVINCE_SHANXI.equals(provinceName)) {
//							Toast.makeText(getApplicationContext(), "障碍单无法进行退单操作！",
//									Toast.LENGTH_SHORT).show();
//							return;
//						}
//						
//						if ("N".equals(woType) || "P".equals(woType)) {
//							Intent intent1 = new Intent();
//							if (Constant.SO_CAT_SPS.equals(soCat)) {
//								intent1.setClass(WoDetailActivity.this,
//										BackOrderFailActivity.class);
//							} else if (Constant.SO_CAT_SGS.equals(soCat)) {
//								intent1.setClass(WoDetailActivity.this,
//										BackSgsOrderFailActivity.class);
//							}
//							intent1.putExtra("woNbr", workorder);
//							intent1.putExtra("soCat", soCat);
//							startActivity(intent1);
//						}
//					}
//				}
//			});
//			
//		}
//		if(ivCall!=null) {//呼叫
//			ivCall.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivCall.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						manager = (TelephonyManager) WoDetailActivity.this
//								.getSystemService(TELEPHONY_SERVICE);
//						manager.listen(new MyPhoneStateListener(),
//								PhoneStateListener.LISTEN_CALL_STATE);
//						mosApp.setPhoneFlag(1);
//						telNum = contacInfo;
//						if(!(contacInfo==null||("").equals(contacInfo))) {
//							Pattern p = null; // 正则表达式 Matcher m = null; //操作的字符串
//							Matcher m = null; // 操作的字符串
//							List<String> contacInfoList = new ArrayList<String>();
//							String tempStr = new String();
//							p = Pattern.compile("((\\+?86)?1\\d{10})|((\\(?(0[12]\\d)\\)?|\\(?(0[3-9]\\d{2})\\)?)?-?[2-9]\\d{6,7})");
//							m = p.matcher(contacInfo);
//							while(m.find()) {
//								tempStr = m.group();
//								tempStr = tempStr.replaceAll("[-\\(\\)]", "");
//								contacInfoList.add(tempStr);
//							}
//							if (contacInfoList.size()==0) {
//								Toast.makeText(getApplicationContext(),
//										"无效的联系方式！", Toast.LENGTH_SHORT)
//										.show();
//								return;
//							} else {
//								
//								Intent intent = new Intent(Intent.ACTION_DIAL, Uri
//										.parse("tel:" + contacInfoList.get(0).toString()));
//								// 开始呼叫
//								startActivity(intent);
//								isReservation = true;
//							}
//
//						}else {
//							Toast.makeText(getApplicationContext(),
//									"联系方式为空", Toast.LENGTH_SHORT)
//									.show();
//						}
//					}
//				}
//			});
//		}
//		if(ivRetuanBack!=null) {//竣工核实
//			ivRetuanBack.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivRetuanBack.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						whichAction = "return";
//						new returnThread().start();
//					}
//				}
//			});
//			
//		}
//		if(ivMaterailBack!=null) {//材料回填
//			ivMaterailBack.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivMaterailBack.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						if (StringUtil.isBlank(materialStepId) || (!StringUtil.isBlank(materialStepId) && materialStepId.indexOf(stepId) != -1)) {
//							Intent intent1 = new Intent();
//							Bundle bundle1 = new Bundle();
//							bundle1.putString("localNetId", localNetId);
//							bundle1.putString("areaId", areaId);
//							bundle1.putString("prodId", prodId);
//							bundle1.putString("chgServSpecId", chgServSpecId);
//							bundle1.putString("staffId", staffId);
//							bundle1.putString("workorder", workorder);
//							bundle1.putString("soNbr", soNbr);
//							bundle1.putString("flag", "C");
//							intent1.putExtras(bundle1);
//							// 设置传递的参数,targetActivity
//							CaptureActivity.targetActivity = com.cattsoft.mos.activity.ScanResultActivity.class;
//							intent1.setClass(WoDetailActivity.this,
//									MaterialBackActivity.class);
//							startActivityForResult(intent1, 4);
//							
//						} else {
//							Toast.makeText(getApplicationContext(),
//									"该环节不需要材料回填！", Toast.LENGTH_SHORT).show();
//						}
//					}
//				}
//			});
//			
//		}
//		if(ivChangeOrder!=null) {//转派
//			ivChangeOrder.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivChangeOrder.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						Intent intent1 = new Intent();
//		    			intent1.setClass(WoDetailActivity.this,
//								ChangeWorkOrderActivity.class);
//		    			intent1.putExtra("accNbr", accNbr);
//						intent1.putExtra("custName", custName);
//						intent1.putExtra("localNetId", localNetId);
//						intent1.putExtra("workorder", workorder);
//						intent1.putExtra("soCat", soCat);
//						intent1.putExtra("woWorkAreaId", woWorkAreaId);
//						intent1.putExtra("woWorkAreaName", woWorkAreaName);
//						
//						startActivity(intent1);
//					}
//				}
//			});
//		}
//		if(ivPreOrder!=null) {//预约
//			ivPreOrder.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivPreOrder.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						Intent intent1 = new Intent();
//						intent1.setClass(WoDetailActivity.this,
//								ReserveTimeActivity.class);
//						intent1.putExtra("bookTime", bookTime);
//						intent1.putExtra("soNbr", soNbr);
//						intent1.putExtra("localNetId", localNetId);
//						intent1.putExtra("areaId", areaId);
//						intent1.putExtra("extSoNbr", extSoNbr);
//						intent1.putExtra("accNbr", accNbr);
//						intent1.putExtra("workorder", workorder);
//						intent1.putExtra("appTime", appTime);
//						intent1.putExtra("custName", custName);
//						intent1.putExtra("situated", situated);
//						startActivity(intent1);
//					 }
//				}
//			});
//		}
//		
//		if(ivDetailServ!=null) {//网格维护
//			ivDetailServ.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivDetailServ.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						Intent intent = new Intent(WoDetailActivity.this,
//								ServDeptActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putString("workorder", workorder);
//						intent.putExtras(bundle);
//						// 设置传递的参数,targetActivity
//						// 启动intent的Activity
//						startActivity(intent);
//						}
//				}
//			});
//		}
//		if(barCodeValidate!=null) {//验证终端
//			barCodeValidate.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivDetailServ.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						Intent intent = new Intent(WoDetailActivity.this,
//								CaptureActivity.class);
//						intent.putExtra("isFirstRun", false);
//						startActivityForResult(intent, 6);
//						}
//				}
//			});
//		}
//		
//		if(ivResChange!=null) {//资源变更
//			ivResChange.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivResChange.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					} else {
//						if(limitUse()==false)return;
//						if (soCat.equals("2")) {
//							Intent intent = new Intent(WoDetailActivity.this,
//									ResourceMaintenanceResActivity.class);
//							intent.putExtra("acc_nbr", accNbr);
//							startActivity(intent);
//						} else {
//							Toast.makeText(getApplicationContext(),
//									"该环节不需要资源变更！", Toast.LENGTH_SHORT).show();
//						}
//					}
//				}
//			});
//			
//		}
//		if(ivTakePhoto!=null) {//拍照
//			ivTakePhoto.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivTakePhoto.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						// 解决图片照完之后自动压缩成小图片
//						File dir = new File(path);
//						Intent intent = new Intent(
//								android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//						// Date date = new Date();
//						// long time = date.getTime();
//						File file = new File(dir, TakePhotoMain.j + ".jpg");
//						// File file = new File(dir,time+".jpg");
//						Uri r = Uri.fromFile(file);
//						intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
//						intent.putExtra(MediaStore.EXTRA_OUTPUT, r);
//						// getContentResolver().delete(r, null, null);
//						startActivityForResult(intent, 1);
//					}
//				}
//			});
//		}
//		if(ivResCheck!=null) {//资源核查
//			ivResCheck.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivResCheck.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						Intent intent = new Intent(WoDetailActivity.this,DeviceCoordinateActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putString("accNbr", accNbr);
//						intent.putExtras(bundle);
//						startActivity(intent);
//					}
//				}
//			});
//		}
//		if(ivServiceTest!=null) {//业务测试
//			ivServiceTest.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivServiceTest.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						Intent intent = new Intent(WoDetailActivity.this,
//								AutoTestConnectActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putString("localNetId", localNetId);
//						bundle.putString("accNbr", accNbr);
//						bundle.putString("workorder", workorder);
//						intent.putExtras(bundle);
//						startActivity(intent);
//					}
//				}
//			});
//		}
//		if(ivOnScheduleBtn!=null) {//履约签到
//			ivOnScheduleBtn.setOnClickListener(new Button.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if("V".equals(((MosFuncNodeSVO)ivOnScheduleBtn.getTag()).getFunccLevel())){
//						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//								Toast.LENGTH_SHORT).show();
//					}else{
//						if(limitUse()==false)return;
//						Thread mThread = new Thread(new Runnable() {// 启动新的线程，
//							@Override
//							public void run() {
//									onSchedule();
//							}
//						});
//						mThread.start();
//					}
//				}
//			});
//		}
//		
//	}
//	
//
//	@Override
//	protected void registerListener() {
//		registerImageViewListener();
//		slidingDrawer.setOnDrawerScrollListener(new OnDrawerScrollListener() {
//
//			@Override
//			public void onScrollStarted() {
//			}
//
//			@Override
//			public void onScrollEnded() {
//				handleImageBtn
//						.setBackgroundResource(R.drawable.detail_image_btn_toorbar_down);
//			}
//		});
//		/**监听抽屉关闭事件*/
//		slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
//
//			@Override
//			public void onDrawerClosed() {
//				handleImageBtn
//						.setBackgroundResource(R.drawable.detail_image_btn_toorbar);
//			}
//		});
//		
//		/** 监听页卡头标点击事件 */
//		setPageTitleOnClickListener();
//		
//		
//		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
//		if (menuFlag == null) {
//			
////			mToolBarGrid.setOnItemClickListener(new OnItemClickListener() {
////				// 底部菜单的单击事件的触发
////				@Override
////				public void onItemClick(AdapterView<?> parent, View view,
////						int position, long id) {
////					ImageView iv=(ImageView)view;
////					MosFuncNodeSVO funcSVO=(MosFuncNodeSVO)iv.getTag();
////					System.out.println("funcSVO.getShortName()="+funcSVO.getShortName()); 
////				//	WoDetailActivity.this.registerGridItemListener(view);
////					
//////					switch (position) {
//////					case 0:
//////						if(initBottomButtonControl("HD")){
//////							Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//////									Toast.LENGTH_SHORT).show();
//////						}else{
//////						if ("N".equals(woType)) {// 第一个按钮为正常回单
//////
//////							// 环节，装机公司（装）
//////							if (StringUtil.isBlank(materialStepId) || (!StringUtil.isBlank(materialStepId) && materialStepId.indexOf(stepId) != -1))  {
//////								// 判断是否已经回填
//////								whichAction = "backOrder";
//////								new materialReturnThread().start();
//////								// if(!isMaterialReturn || !isReturn){
//////								// return;
//////								// }
//////							} else {
//////								if (!(soCat.endsWith(Constant.SO_CAT_SPS) && actType
//////										.endsWith("拆"))) {
//////									whichAction = "backOrder";
//////									new returnThread().start();
//////									// if(!isReturn){
//////									// return;
//////									// }
//////								} else {
//////									Intent intent0 = new Intent();
//////									if (Constant.SO_CAT_SPS.equals(soCat)) {
//////										intent0.setClass(WoDetailActivity.this,
//////												BackOrderSuccessActivity.class);
//////									} else if (Constant.SO_CAT_SGS
//////											.equals(soCat)) {
//////										intent0.setClass(
//////												WoDetailActivity.this,
//////												BackSgsOrderSuccessActivity.class);
//////									}
//////									intent0.putExtra("woNbr", workorder);
//////									intent0.putExtra("soCat", soCat);
//////									startActivity(intent0);
//////								}
//////							}
//////
//////						} else if ("P".equals(woType)) {// 外勘单
//////							Intent intent0 = new Intent();
//////							intent0.setClass(WoDetailActivity.this,
//////									InfoBackWorkOrderActivity.class);
//////							intent0.putExtra("woNbr", workorder);
//////							startActivity(intent0);
//////						}
//////						}
//////						break;
//////					case 1:
//////						if(initBottomButtonControl("HJ")){
//////							Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//////									Toast.LENGTH_SHORT).show();
//////						}else{
//////						// 跳转到对应的页面
//////						// add by xyc 201027 修改缓存中的值，使工单列表里的监听无效
//////						manager = (TelephonyManager) WoDetailActivity.this
//////								.getSystemService(TELEPHONY_SERVICE);
//////						manager.listen(new MyPhoneStateListener(),
//////								PhoneStateListener.LISTEN_CALL_STATE);
//////						mosApp.setPhoneFlag(1);
//////						telNum = contacInfo;
//////						if (!(contacInfo == null || ("").equals(contacInfo))) {
//////							Pattern p = null; // 正则表达式 Matcher m = null;
//////												// //操作的字符串
//////							Matcher m = null; // 操作的字符串
//////							List<String> contacInfoList = new ArrayList<String>();
//////							String tempStr = new String();
//////							p = Pattern
//////									.compile("((\\+?86)?1\\d{10})|((\\(?(0[12]\\d)\\)?|\\(?(0[3-9]\\d{2})\\)?)?-?[2-9]\\d{6,7})");
//////							m = p.matcher(contacInfo);
//////							while (m.find()) {
//////								tempStr = m.group();
//////								tempStr = tempStr.replaceAll("[-\\(\\)]", "");
//////								contacInfoList.add(tempStr);
//////							}
//////							if (contacInfoList.size() == 0) {
//////								Toast.makeText(getApplicationContext(),
//////										"无效的联系方式！", Toast.LENGTH_SHORT).show();
//////								return;
//////							} else {
//////
//////								Intent intent = new Intent(Intent.ACTION_DIAL,
//////										Uri.parse("tel:"
//////												+ contacInfoList.get(0)
//////														.toString()));
//////								// 开始呼叫
//////								startActivity(intent);
//////								isReservation = true;
//////							}
//////
//////						} else {
//////							Toast.makeText(getApplicationContext(), "联系方式为空",
//////									Toast.LENGTH_SHORT).show();
//////						}
//////						}
//////						break;
//////					case 2:
//////
//////						
//////						if(initBottomButtonControl("JGHS")){
//////							Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//////									Toast.LENGTH_SHORT).show();
//////						}else{	
//////							// 回访功能，竣工核实
//////							whichAction = "return";
//////							new returnThread().start();}
//////						break;
//////						
//////					case 3:
//////						if(initBottomButtonControl("CLHT")){
//////						Toast.makeText(getApplicationContext(), "该功能暂未开放！",
//////								Toast.LENGTH_SHORT).show();
//////					}else{
//////					// 材料回填
//////					if (StringUtil.isBlank(materialStepId) || (!StringUtil.isBlank(materialStepId) && materialStepId.indexOf(stepId) != -1)) {
//////
//////						Intent intent1 = new Intent();
//////						Bundle bundle1 = new Bundle();
//////						bundle1.putString("localNetId", localNetId);
//////						bundle1.putString("areaId", areaId);
//////						bundle1.putString("prodId", prodId);
//////						bundle1.putString("chgServSpecId", chgServSpecId);
//////						bundle1.putString("staffId", staffId);
//////						bundle1.putString("workorder", workorder);
//////						bundle1.putString("flag", "C");
//////						intent1.putExtras(bundle1);
//////						// 设置传递的参数,targetActivity
//////						CaptureActivity.targetActivity = com.cattsoft.mos.activity.ScanResultActivity.class;
//////						intent1.setClass(WoDetailActivity.this,
//////								MaterialBackActivity.class);
//////						startActivityForResult(intent1, 4);
//////						
//////					} else {
//////						Toast.makeText(getApplicationContext(),
//////								"该环节不需要材料回填！", Toast.LENGTH_SHORT).show();
//////					}
//////					}
//////					break;
//////
//////					case 4:
//////                        //更多   
//////						Intent intent = new Intent(WoDetailActivity.this, MoreFunctionActivity.class);
//////						startActivityForResult(intent, 5);
//////					}
////				}
////			});
//
//
//		} else if (menuFlag.equals("WoFetch")) {
//			rightButtonOnClick();
//			slidingDrawer.setVisibility(View.GONE);
//			
//		} else {
//			outsideConstruction.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent(WoDetailActivity.this,
//							FaultCheckActivity.class);
//					startActivity(intent);
//				}
//
//			});
//		}
//
//	}
//
//	private class returnThread extends Thread {
//		public void run() {
//			Message msg = new Message();
//			// try {
//			if(Constant.SO_CAT_SGS
//					.equals(soCat) && "HEBEI".equals(provinceName)&&whichAction.equals("backOrder")) {// 如果是河北障碍单的话，不用校验材料回填
//				msg.what = 4;
//				returnHandler.sendMessage(msg);
//				return;
//			}
//			if("N".equals(isForceReturnVisit)&&whichAction.equals("backOrder")) {// 回单之前是否强制校验竣工核实
//				msg.what = 4;
//				returnHandler.sendMessage(msg);
//				return;
//			}
//			isCanReturnJson = getPostHttpContent(isCanReturnurl,
//					infoJsonObject.toString());
//			if (whichAction.equals("return")
//					&& isCanReturnJson.toString().equals("Y")) {
//				msg.what = 1;
//				returnHandler.sendMessage(msg);
//			} else if (whichAction.equals("return")
//					&& isCanReturnJson.toString().equals("N")) {
//				msg.what = 2;
//				returnHandler.sendMessage(msg);
//			} else if (whichAction.equals("backOrder")
//					&& isCanReturnJson.toString().equals("N")) {
//				isReturn = false;
//				msg.what = 3;
//				returnHandler.sendMessage(msg);
//			} else if (whichAction.equals("backOrder")
//					&& isCanReturnJson.toString().equals("Y")) {
//				isReturn = true;
//				msg.what = 4;
//				returnHandler.sendMessage(msg);
//			}
//		}
//	}
//	
//	//限制使用
//		private boolean limitUse() {
//			boolean flag=true;
//			if(Constant.SO_CAT_SGS
//					.equals(soCat) && "HEBEI".equals(provinceName)) {
//				Toast.makeText(getApplicationContext(), "障碍单只能进行回单操作！",
//						Toast.LENGTH_SHORT).show();
//				flag=false;
//			}
//			
//			return flag;
//		}
//
//	// 判断是否已经材料回填
//	private class materialReturnThread extends Thread {
//		public void run() {
//			Message msg = new Message();
//			// isCanReturnJson = Communication.getPostResponse(isCanReturnurl,
//			// infoJsonObject.toString());
//			if(Constant.SO_CAT_SGS
//					.equals(soCat) && "HEBEI".equals(provinceName)) {// 如果是河北障碍单的话，不用校验材料回填
//				msg.what = 2;
//				materialReturnHandler.sendMessage(msg);
//				return;
//			}
//			
//			com.alibaba.fastjson.JSONObject infoJsonObject1 = new com.alibaba.fastjson.JSONObject();
//			String isCanReturnUrl = "tm/MaterialHandleAction.do?method=queryWoCount4MOS";
//			infoJsonObject1.put("workorder", workorder);
//			String count = null;
//			count = getPostHttpContent(isCanReturnUrl,
//					infoJsonObject1.toString());
//
//			if ("0".equals(count) || Integer.parseInt(count) == 0) {
//				isMaterialReturn = false;
//				msg.what = 1;
//				materialReturnHandler.sendMessage(msg);
//			} else {
//				// 判断是否要审核
//				if (!(soCat.endsWith(Constant.SO_CAT_SPS) && actType
//						.endsWith("拆"))) {
//					whichAction = "backOrder";
//					if(!"N".equals(isForceReturnVisit)) {//回单之前是否必须进行竣工核实操作
//						isCanReturnJson = getPostHttpContent(isCanReturnurl,
//								infoJsonObject.toString());
//						if (whichAction.equals("backOrder")
//								&& isCanReturnJson.toString().equals("N")) {
//							isReturn = false;
//							msg.what = 3;
//							materialReturnHandler.sendMessage(msg);
//						} else if (whichAction.equals("backOrder")
//								&& isCanReturnJson.toString().equals("Y")) {
//							isReturn = true;
//							msg.what = 2;
//							materialReturnHandler.sendMessage(msg);
//						}
//					}else {
//						isReturn = true;
//						msg.what = 2;
//						materialReturnHandler.sendMessage(msg);
//					}
//					
//				} else {
//					isMaterialReturn = true;
//					msg.what = 2;
//					materialReturnHandler.sendMessage(msg);
//				}
//
//			}
//
//		}
//	}
//
//	private Handler materialReturnHandler = new Handler() {
//
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 0:
//
//				Toast.makeText(getApplicationContext(), "网络连接失败，请检查网络设置！",
//						Toast.LENGTH_SHORT).show();
//				break;
//			case 1:
//
//				Toast.makeText(getApplicationContext(), "请先进行材料回填！",
//						Toast.LENGTH_SHORT).show();
//				break;
//			case 2:
//				Intent intent0 = new Intent();
//				if (Constant.SO_CAT_SPS.equals(soCat)) {
//					intent0.setClass(WoDetailActivity.this,
//							BackOrderSuccessActivity.class);
//				} else if (Constant.SO_CAT_SGS.equals(soCat)) {
//					intent0.setClass(WoDetailActivity.this,
//							BackSgsOrderSuccessActivity.class);
//					intent0.putExtra("prodId", prodId);
//				}
//				intent0.putExtra("woNbr", workorder);
//				intent0.putExtra("soCat", soCat);
//				startActivity(intent0);
//				break;
//			case 3:
//				Toast.makeText(getApplicationContext(), "请先进行竣工核实！",
//						Toast.LENGTH_SHORT).show();
//				break;
//			}
//		};
//	};
//
//	private Handler returnHandler = new Handler() {
//
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 0:
//
//				Toast.makeText(getApplicationContext(), "网络连接失败，请检查网络设置！",
//						Toast.LENGTH_SHORT).show();
//				break;
//			case 1:
//
//				Toast.makeText(getApplicationContext(), "已经进行了竣工核实",
//						Toast.LENGTH_SHORT).show();
//				break;
//			case 2:
//				Intent intent = new Intent();
//				intent.setClass(WoDetailActivity.this,
//						ReturnVisitActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putString("extSoNbr", extSoNbr);
//				bundle.putString("work_order", workorder);
//				bundle.putString("localNetId", localNetId);
//				bundle.putString("areaId", areaId);
//				bundle.putString("soNbr", soNbr);
//				if (!(contacInfo == null || ("").equals(contacInfo))) {
//					Pattern p = null; // 正则表达式 Matcher m = null; //操作的字符串
//					Matcher m = null; // 操作的字符串
//					List<String> contacInfoList = new ArrayList<String>();
//					String tempStr = new String();
//					p = Pattern.compile("((\\+?86)?1\\d{10})");
//					m = p.matcher(contacInfo);
//					while (m.find()) {
//						tempStr = m.group();
//						tempStr = tempStr.replaceAll("[-\\(\\)]", "");
//						contacInfoList.add(tempStr);
//					}
//
//					if (contacInfoList.size() == 0) {
//						bundle.putString("contacInfo", "");
//
//					} else {
//
//						bundle.putString("contacInfo", contacInfoList.get(0)
//								.toString());
//					}
//				} else {
//					bundle.putString("contacInfo", "");
//				}
//
//				intent.putExtras(bundle);
//				startActivity(intent);
//				break;
//			case 3:
//				Toast.makeText(getApplicationContext(), "请先进行竣工核实！",
//						Toast.LENGTH_SHORT).show();
//				break;
//			case 4:
//				Intent intent0 = new Intent();
//				if (Constant.SO_CAT_SPS.equals(soCat)) {
//					intent0.setClass(WoDetailActivity.this,
//							BackOrderSuccessActivity.class);
//				} else if (Constant.SO_CAT_SGS.equals(soCat)) {
//					intent0.setClass(WoDetailActivity.this,
//							BackSgsOrderSuccessActivity.class);
//					intent0.putExtra("prodId", prodId);
//				}
//				intent0.putExtra("woNbr", workorder);
//				intent0.putExtra("soCat", soCat);
//				startActivity(intent0);
//				break;
//			case 5:
//				Toast.makeText(getApplicationContext(), "履约签到成功！",Toast.LENGTH_SHORT).show();	
//				break;
//			case 6:
//				Toast.makeText(getApplicationContext(), "已进行过履约签到！",Toast.LENGTH_SHORT).show();	
//				break;
//			case 7:
//				Toast.makeText(getApplicationContext(), "履约签到失败！",Toast.LENGTH_SHORT).show();	
//				break;
//			}
//		};
//	};
//
//	/**
//	 * 初始化页卡标题
//	 */
//	private void initPageTitles() {
//		pageTitles.clear();
//		pageTitles
//				.add((TextView) findViewById(R.id.detail_title_layout_title1));
//		pageTitles
//				.add((TextView) findViewById(R.id.detail_title_layout_title2));
//		pageTitles
//				.add((TextView) findViewById(R.id.detail_title_layout_title3));
//		pageTitles
//				.add((TextView) findViewById(R.id.detail_title_layout_title4));
//		pageTitles
//				.add((TextView) findViewById(R.id.detail_title_layout_title5));
//		pageTitles
//				.add((TextView) findViewById(R.id.detail_title_layout_title6));
//		// pageTitles.add((TextView)findViewById(R.id.detail_title_layout_title7));
//		// pageTitles.add((TextView)findViewById(R.id.detail_title_layout_title8));
//	}
//
//	/**
//	 * 设置页卡标题文字颜色
//	 * 
//	 * @param titleIndex
//	 *            页卡序号
//	 */
//	private void setPageTitlesColor(int titleIndex) {
//		for (int i = 0; i < pageTitles.size(); i++) {
//			if (i == titleIndex) {
//				pageTitles.get(i).setTextColor(0xffca202b);
//			} else {
//				pageTitles.get(i).setTextColor(Color.BLACK);
//			}
//		}
//	}
//
//	/**
//	 * 设置页卡宽度
//	 */
//	private void initPageTextWide(int perSpacing) {
//
//		TextView woInfoText = (TextView) findViewById(R.id.detail_title_layout_title1);
//		woInfoText.setWidth(perSpacing);
//		TextView resInfoText = (TextView) findViewById(R.id.detail_title_layout_title2);
//		resInfoText.setWidth(perSpacing);
//		TextView orderInfoText = (TextView) findViewById(R.id.detail_title_layout_title3);
//		orderInfoText.setWidth(perSpacing);
//		TextView telInfoText = (TextView) findViewById(R.id.detail_title_layout_title4);
//		telInfoText.setWidth(perSpacing);
//		TextView phoInfoText = (TextView) findViewById(R.id.detail_title_layout_title5);
//		phoInfoText.setWidth(perSpacing);
//		TextView metInfoText = (TextView) findViewById(R.id.detail_title_layout_title6);
//		metInfoText.setWidth(perSpacing);
//		// TextView logInfoText =
//		// (TextView)findViewById(R.id.detail_title_layout_title7);
//		// logInfoText.setWidth(perSpacing);
//		// TextView moreInfoText =
//		// (TextView)findViewById(R.id.detail_title_layout_title8);
//		// moreInfoText.setWidth(perSpacing);
//	}
//
//	/**
//	 * 设置页卡标题点击事件监听器
//	 */
//	private void setPageTitleOnClickListener() {
//		for (int i = 0; i < pageTitles.size(); i++) {
//			pageTitles.get(i).setOnClickListener(new MyOnClickListener(i));
//		}
//	}
//
//	/**
//	 * 右侧按钮点击事件监听
//	 */
//	@Override
//	public void rightButtonOnClick() {
//		titleRightButton.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				fetchWoTread();
//			}
//		});
//	}
//
//	/**
//	 * 线程完成后台领单功能
//	 */
//	private void fetchWoTread() {
//		Thread mThread = new Thread(new Runnable() {// 启动新的线程，
//					@Override
//					public void run() {
//						fetchWo(workorder + " ; ");
//						Message msg = new Message();
//						msg.what = 6;
//						handler.sendMessage(msg);
//					}
//				});
//		mThread.start();
//	}
//	
//	
//	//履约时间回传后台 fus 2014-02-14
//	private void onSchedule() {
//			Message msg=new Message();
////			Date now = new Date();
////			long nowTime = now.getTime();
//			String onScheduleUrl = "tm/WoHandleAction.do?method=onSchedule4MOS";
//			com.alibaba.fastjson.JSONObject scheduleJsonObject = new com.alibaba.fastjson.JSONObject();
//			scheduleJsonObject.put("onScheduleTime", DateUtil.dateTime2Str(new Date()));//将当前时间作为履约时间传送
//			scheduleJsonObject.put("woStaffId", this.getCacheProcess().getCacheValueInSharedPreferences(this, "staffId"));
//			scheduleJsonObject.put("soNbr", soNbr);
//			scheduleJsonObject.put("woNbr", workorder);
//			String	result = null;
//			result = getPostHttpContent(onScheduleUrl, scheduleJsonObject.toString());	
//		    //0 表示履约签到成功 1表示已履约 2 表示履约失败
//		    if("0".equals(result)){
//		    	msg.what = 5;
//		    }else if("1".equals(result)){
//		    	msg.what = 6;
//		    }else if("2".equals(result)){
//		    	msg.what = 7;
//		    }
//		    returnHandler.sendMessage(msg);
//	}
//	
//
//	/**
//	 * 调用后台的领单功能
//	 * 
//	 * @throws JSONException
//	 * @throws IOException
//	 */
//	private void fetchWo(String woNbrAry) {
//
//		String requestURL = "tm/WoHandleAction.do?method=fetchWo4MOS";
//
//		try {
//			JSONObject fetchWoRequestJsonObject = new JSONObject();
//			fetchWoRequestJsonObject.put("woNbrAry", woNbrAry);
//			fetchWoRequestJsonObject.put("woStaffId", this.getCacheProcess()
//					.getCacheValueInSharedPreferences(this, "staffId"));
//			String fetchWoResponse = getPostHttpContent(requestURL,
//					fetchWoRequestJsonObject.toString());
//			if (!StringUtil.isBlank(fetchWoResponse)) {
//				JSONObject fetchWoResponseJsonObject = new JSONObject(
//						fetchWoResponse);
//				fetchWoResult = JsonUtil.getString(fetchWoResponseJsonObject,
//						"failMsg");
//			} else {
//				fetchWoResult = "服务端无响应！";
//			}
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * @author wanghaoc 定义一行类型
//	 */
//	public class ScanRowObj {
//
//		private String scanProName;
//		private String proNum;
//		private String scanType;
//
//		public String getProNum() {
//			return proNum;
//		}
//
//		public void setProNum(String proNum) {
//			this.proNum = proNum;
//		}
//
//		public String getScanProName() {
//			return scanProName;
//		}
//
//		public void setScanProName(String scanProName) {
//			this.scanProName = scanProName;
//		}
//
//		public String getScanType() {
//			return scanType;
//		}
//
//		public void setScanType(String scanType) {
//			this.scanType = scanType;
//		}
//	}
}