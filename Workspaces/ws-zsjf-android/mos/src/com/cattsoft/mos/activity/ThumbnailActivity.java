package com.cattsoft.mos.activity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.connect.Communication;

public class ThumbnailActivity extends BasicActivity implements OnTouchListener,OnGestureListener{
	private static ImageView imageView = null;
	private static final int FLING_MIN_DISTANCE = 120;//移动最小距离
	private static final int FLING_MIN_VELOCITY = 200;//移动最大速度
	private int position;
	private int index;
	private TextView titleText;
	private int titleIndex;
	private String deletePhotourl = "tm/MosSurveyAction.do?method=deleteMOSPicture4MOS";
	private com.alibaba.fastjson.JSONObject deleteJson = new com.alibaba.fastjson.JSONObject();
	private PopupWindow menu;  //长按listview弹出的“设置默认工区”菜单
	private LayoutInflater inflater;  
    private View layout; //菜单布局
    private TextView descText;
//	private Handler mHandler = new Handler() {
//
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 1:
//				menu.showAsDropDown(imageView, 40, -100);
//				break;
////			case 2:
////				menu.dismiss();
////				break;
//			}
//		};
//	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.thumbnail);				
		initView();
		//实例化PopupWindow创建菜单
//		initMenu();
//	    timer = new Timer();   
//        timer.schedule(new initPopupWindow(), 100);
//		InitPopWindow initPopWindow = new InitPopWindow();
		
		registerListener();
//		initPopWindow.start();
	}
//	private class initPopupWindow extends TimerTask{   
//        @Override  
//        public void run() {   
//               
//            Message message = new Message();   
//            message.what = 1;   
//            mHandler.sendMessage(message);   
//               
//        }          
//    }   
//	private class InitPopWindow extends Thread{
//	   public void run() {   
//	      
//	   Message message = new Message();   
//	   message.what = 1;   
//	   mHandler.sendMessage(message);   	      
//	   }          
//	}
//    private void initMenu(){  
//		  
//        //获取LayoutInflater实例  
//        inflater  = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);  
//        //获取弹出菜单的布局  
//        layout = inflater.inflate(R.layout.image_touch,null);       
//        //设置popupWindow的布局  
//        menu = new PopupWindow(layout, imageView.getLayoutParams().width,WindowManager.LayoutParams.WRAP_CONTENT); 
//        descText = (TextView)layout.findViewById(R.id.desc_text);
//        //菜单的监听事件。。。。
//        descText.setText((String)TakePhotoMain.soAttachList.get(index).get("desc"));
//    }
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		position=bundle.getInt("ID");
		index = position;
		super.setTitleBar(index+1+"/"+TakePhotoMain.soAttachList.size(), View.VISIBLE,View.INVISIBLE,View.GONE, false);
		//super.setTitleRightButtonImg(R.drawable.titlebar_img_btn_photo_delete_bg);
		titleText = super.getTitleTextView();
		descText = (TextView)findViewById(R.id.desc_text);
		descText.getBackground().setAlpha(100);//设置textview背景半透明，这里的数字是0-255，用来改变透明度的
		descText.setText((String)TakePhotoMain.soAttachList.get(index).get("desc"));
		this.imageView=(ImageView) this.findViewById(R.id.display);		
		this.imageView.setImageBitmap((Bitmap) TakePhotoMain.soAttachList.get(index).get("image"));
		this.imageView.setClickable(true);
//		image = (ImageView)findViewById(R.id.display);
//		//pathsrcs-->存放图片路径的数组       ImagePositions-->选择的第几张图片
//		//设置Touch监听
//		image.setOnTouchListener(this);
//		//允许长按
//		image.setLongClickable(true);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		imageView.setOnTouchListener(this);
		imageView.setLongClickable(true);
		super.getTitleRightButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(TakePhotoMain.soAttachList.size()==0) {
					titleText.setText("0/0");
					imageView.setVisibility(View.GONE);
					descText.setVisibility(View.GONE);
				}else {
					String[] titleId = titleText.getText().toString().split("/");
					titleIndex = Integer.parseInt(titleId[0]);	
					deleteJson.put("soAttachId", TakePhotoMain.soAttachList.get(titleIndex-1).get("soAttachId"));
					mProgressDialog = new Dialog(ThumbnailActivity.this,
							R.style.process_dialog);//创建自定义进度条
					mProgressDialog.setContentView(R.layout.progress_dialog);//自定义进度条的内容
					mProgressDialog.show();//显示进度条
					DeleteThread deleteThread = new DeleteThread();
					deleteThread.start();
					
			
				}
				}
					

		});
		super.getTitleLeftButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();		
//				intent.setClass(ThumbnailActivity.this,WoDetailActivity.class);
				setResult(RESULT_OK,intent);
			//	timer.cancel();
				finish();
			}
			
		});
	}
	private class DeleteThread extends Thread{
		@Override
		public void run() {
			Message msg=new Message();
			try {
				String isSuccess = Communication.getPostResponse(deletePhotourl, deleteJson.toString());
				if(isSuccess.toString().equals("success")) {
					
					msg.what = 1;
				}else {
					msg.what = 2;
					
				}
				 handler.sendMessage(msg);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.what = 0;
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.what = 2;
				return;
			}			
		}
	}
	// 用于接收线程发送的消息
			private Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case 0: {
						mProgressDialog.dismiss();
						Toast.makeText(getApplicationContext(), "网络连接失败，请检查网络设置！",
								Toast.LENGTH_SHORT).show();
					}break;
					case 1: {
						mProgressDialog.dismiss();// 当接到消息时，关闭进度条
						Toast.makeText(getApplicationContext(), "删除成功",
								Toast.LENGTH_SHORT).show();
						TakePhotoMain.soAttachList.remove(titleIndex-1);
						if(TakePhotoMain.soAttachList.size()==0) {
							titleText.setText("0/0");
							imageView.setVisibility(View.GONE);
							return;
						}else {
							
								if(titleIndex==1) {
									imageView.setImageBitmap((Bitmap) TakePhotoMain.soAttachList.get(0).get("image"));
									titleText.setText(1+"/"+TakePhotoMain.soAttachList.size());
									index = 0;
								}else {
									imageView.setImageBitmap((Bitmap) TakePhotoMain.soAttachList.get(titleIndex-2).get("image"));
									titleText.setText(titleIndex-1+"/"+TakePhotoMain.soAttachList.size());
									index = titleIndex-2;
								}		
						}
					}break;
					case 2: {
						mProgressDialog.dismiss();// 当接到消息时，关闭进度条
						Toast.makeText(getApplicationContext(), "删除失败",
								Toast.LENGTH_SHORT).show();
					}break;
					}
				}
			};
	//构建手势探测器
	GestureDetector mygesture = new GestureDetector(this);
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return mygesture.onTouchEvent(event);
	}
	
	//显示下一张图片
	public void showNextImage(){
//		position=++GridViewTest.ImagePositions;
//		if(position>=TakePhotoMain.soAttachList.size()){
//			Toast.makeText(ThumbnailActivity.this, "已到最后一张图片", Toast.LENGTH_SHORT).show();
//			GridViewTest.ImagePositions=GridViewTest.pathsrcs.size()-1;
//		}else{
//			String path = GridViewTest.pathsrcs.get(position);
//			image.setImageURI(Uri.parse(path));
//		}
//		System.out.println("positoon="+position);
		index++;
		if(index>=TakePhotoMain.soAttachList.size()){
	//		Toast.makeText(ThumbnailActivity.this, "已到最后一张图片", Toast.LENGTH_SHORT).show();
			index--;
		}else {
			titleIndex = index+1;
			titleText.setText(titleIndex+"/"+TakePhotoMain.soAttachList.size());
			imageView.setImageBitmap((Bitmap) TakePhotoMain.soAttachList.get(index).get("image"));
			descText.setText((String)TakePhotoMain.soAttachList.get(index).get("desc"));
		}
		
	}
	//显示上一张图片
	public void showLastImage(){
//		position=--GridViewTest.ImagePositions;
//		if(position<0){
//			Toast.makeText(ThumbnailActivity.this, "已到第一张图片", Toast.LENGTH_SHORT).show();
//			GridViewTest.ImagePositions=0;
//		}else{
//			String path = GridViewTest.pathsrcs.get(position);
//			image.setImageURI(Uri.parse(path));
//		}
		index--;
		if(index<0){
		//	Toast.makeText(ThumbnailActivity.this, "已到第一张图片", Toast.LENGTH_SHORT).show();
			index++;
		}else {
			titleIndex = index+1;
			titleText.setText(titleIndex+"/"+TakePhotoMain.soAttachList.size());
			imageView.setImageBitmap((Bitmap) TakePhotoMain.soAttachList.get(index).get("image"));
			descText.setText((String)TakePhotoMain.soAttachList.get(index).get("desc"));
		}
		
	}
	/*用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 
	 * 多个ACTION_MOVE, 1个ACTION_UP触发*/
	//主要方法
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// e1：第1个ACTION_DOWN MotionEvent 
		// e2：最后一个ACTION_MOVE MotionEvent 
		// velocityX：X轴上的移动速度（像素/秒） 
		// velocityY：Y轴上的移动速度（像素/秒） 
		
		// X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒 
		//向有翻图片
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE    
		             && Math.abs(velocityX) > FLING_MIN_VELOCITY) {    
			showNextImage();
		     } 
		//向左翻图片
		if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE    
		             && Math.abs(velocityX) > FLING_MIN_VELOCITY) {    
			showLastImage();
		}    
		   return false;    
	}
	//////////////////////////////////////////////////////////////下面方法没用，但是这里必须实现
	/* 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发*/
	@Override
	public void onLongPress(MotionEvent e) {}
	
	/* 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发*/
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,float distanceY) {return false;}
	
	/* 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发    
	  注意和onDown()的区别，强调的是没有松开或者拖动的状态    */
	@Override
	public void onShowPress(MotionEvent e) {}
	
	/*用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发*/
	@Override
	public boolean onSingleTapUp(MotionEvent e) {return false;}
	
	@Override
	public boolean onDown(MotionEvent e) {return false;}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
	  	  if(keyCode == KeyEvent.KEYCODE_BACK) {
	  		 
	  		Intent intent = new Intent();		
			intent.setClass(ThumbnailActivity.this,DefecAdd3tActivity.class);
			setResult(RESULT_OK,intent);
//			 Message message = new Message();   
//            message.what = 2;   
//            mHandler.sendMessage(message);
			
			finish();
	  	  }
	  	  super.onKeyDown(keyCode, event);
			  return true;   	
	  }
		
	
}