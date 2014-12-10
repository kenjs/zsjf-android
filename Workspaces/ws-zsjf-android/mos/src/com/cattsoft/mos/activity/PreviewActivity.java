package com.cattsoft.mos.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cattsoft.mos.R;
import com.cattsoft.mos.util.StringUtil;
/**
 * 拍照之后用户用来预览刚拍完的照片
 * @author xueweiwei
 *
 */
public class PreviewActivity extends BasicActivity{

	ImageView image;
	private Bitmap myBitmap;
    byte[] mContent;
    private JSONObject toOkJsonObject = new JSONObject();
    private String path;
    private Button sendToBtn,cancleBtn;
    private EditText remarkText;
    private String woNbr,localNetId,soNbr,soAttachId;
    byte[] bitmapBytes;
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);// 设置标题栏为用户自定义标题栏
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		super.setTitleBar("图片上传", View.VISIBLE,View.VISIBLE,View.GONE, false);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float screenW = dm.widthPixels;// 获取屏幕宽度
		image = (ImageView)findViewById(R.id.show_image);
		//remarkText.setWidth((int) (screenW*3/5));
		Intent intent=getIntent();		 		 
		bitmapBytes=intent.getByteArrayExtra("BitImageBytes");
		woNbr = intent.getStringExtra("woNbr");
		localNetId = intent.getStringExtra("localNetId");
		soNbr = intent.getStringExtra("soNbr");
		toOkJsonObject.put("woNbr", woNbr);
		toOkJsonObject.put("localNetId", localNetId);
		toOkJsonObject.put("soNbr", soNbr);
		myBitmap=BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
		image.setImageBitmap(myBitmap);
	}
	
	
	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				byte[] bitmapBytes1=baos.toByteArray();
		    	try {
		    		baos.flush();
		    		baos.close();
				} catch (IOException e) {
					Log.d("【IO】","SignatureView的saveToBytes()方法关闭流异常");
					e.printStackTrace();
				}
				toOkJsonObject.put("pictureBytes", bitmapBytes1);
				System.out.println("bitmapBytes1bitmapBytes1="+bitmapBytes1);
//				//toOkJsonObject.put("remark", remarkText.getText());
//				mProgressDialog = new Dialog(PreviewActivity.this,
//						R.style.process_dialog);//创建自定义进度条
//				mProgressDialog.setContentView(R.layout.progress_dialog);//自定义进度条的内容
//				mProgressDialog.show();//显示进度条
				//new sendThread().start();
				Intent intent = new Intent();
				intent.putExtra("BitImageBytes",bitmapBytes);
				//intent.putExtra("desc", remarkText.getText().toString());
				//intent.putExtra("soAttachId", soAttachId);
				setResult(RESULT_OK,intent);
				finish();
			}
		});
	}
	
	
	private class sendThread extends Thread {
		public void run() {
			Message msg=new Message();
				soAttachId = getPostHttpContent("","tm/WoSurveyAction.do?method=sendImage", toOkJsonObject.toString());
				if (StringUtil.isExcetionInfo(soAttachId)) {
					msg.what = 0;
					sendHandler.sendMessage(msg);
				}else if(soAttachId.toString().equals("null")) {
					
					msg.what = 0;
					sendHandler.sendMessage(msg);
				}else {
					msg.what = 1;
					sendHandler.sendMessage(msg);
					
				}
		}
	}
	private Handler sendHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(), "发送失败",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(), "发送成功",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.putExtra("BitImageBytes",bitmapBytes);
				intent.putExtra("desc", remarkText.getText().toString());
				intent.putExtra("soAttachId", soAttachId);
				setResult(RESULT_OK,intent);
				finish();
				break;
			}
		}
	};
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		
	}
}
