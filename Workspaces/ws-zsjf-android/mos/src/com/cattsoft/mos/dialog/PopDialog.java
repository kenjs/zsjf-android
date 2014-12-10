package com.cattsoft.mos.dialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.activity.BasicActivity;
import com.cattsoft.mos.activity.TakePhotoMain;
import com.cattsoft.mos.util.ImageUtil;


public class PopDialog  extends BasicActivity{
	
	private ImageView img_take_now;
	private ImageView img_local_choose;
	
	Intent intent=null;
	Bitmap bmp=null;
	String path="";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)   {
	    super.onCreate(savedInstanceState);
	    LayoutParams params = getWindow().getAttributes();  
	    int dipx=90;
	    int dipy=-185;
	    float scale = this.getResources().getDisplayMetrics().density; 
	    int pxx=(int)(dipx * scale + 0.5f);
	    int pxy=(int)(dipy * scale + 0.5f);
		//params.x = 270;
		//params.y = -280;
	    params.x = pxx;
	  	params.y = pxy;
        getWindow().setAttributes(params); 
	    setContentView(R.layout.dg_photograph);
	    initView();
	    registerListener();
	}
	
	protected void initView() {
		
		path = TakePhotoMain.ReadSDPath() + "/DCIM/Camera/";
		
		img_take_now=(ImageView)findViewById(R.id.take_image_now);
		img_local_choose=(ImageView)findViewById(R.id.take_image_local);
		
		intent=getIntent();
		
		
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);  
	    if(resultCode == RESULT_OK){
	    	if(requestCode==1) {//本地
	    		Uri uri = data.getData();   
//	    		  String[] pojo = { MediaStore.Images.Media.DATA };
//	    		    Cursor cursor = managedQuery(uri, pojo, null, null, null);
//	    		    if (cursor != null) {
//	    		         int colunm_index = cursor
//	    		           .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//	    		         cursor.moveToFirst();
//	    		         String path = cursor.getString(colunm_index);
//	    		         //Toast.makeText(PopDialog.this, path, Toast.LENGTH_SHORT).show(); 
//	    		    }else {
//	    		    	Toast.makeText(PopDialog.this, "图片格式不正确！", Toast.LENGTH_SHORT).show();
//	    		    	return;
//	    		    }
	    		
	    		
	    		//Toast.makeText(PopDialog.this, "图片处理成功！", Toast.LENGTH_SHORT).show(); 
		    	ContentResolver cr = this.getContentResolver();  
		    	Bitmap bmp=null;
				try {
					bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
					Bitmap bmp1= ImageUtil.compressBitMapBySize(bmp,this);
			    	Map soAttachMap = new HashMap(); 
					soAttachMap.put("image", bmp1); 
					
					soAttachMap.put("desc", "");
					TakePhotoMain.soAttachList.add(soAttachMap);
					TakePhotoMain.j++;
					intent.putExtra("flag", "local");
			    	setResult(RESULT_OK, intent);
			    	PopDialog.this.finish(); 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
//		    	
//		        try {  
//		        	BitmapFactory.Options options = new BitmapFactory.Options();
//		            if(bmp != null)  bmp.recycle();  
//		            //BitmapFactory.decodeStream(is, outPadding, options)
//		            bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));  
//		        } catch (FileNotFoundException e) {  
//		            e.printStackTrace();  
//		        } 
//		    	
		    	//Toast.makeText(PopDialog.this, "图片处理成功！", Toast.LENGTH_SHORT).show(); 
	    		
	    		//Map imageCache = new HashMap<String, SoftReference<Bitmap>>();
//		    	Bitmap bmp1= ImageUtil.compressBitMapBySize(bmp,this);
//	    		
//	    		
//		    	Map soAttachMap = new HashMap();
//				Bitmap bbm=compressImage(bmp1);
//				soAttachMap.put("image", bbm); 
//				
//				soAttachMap.put("desc", "");
//				TakePhotoMain.soAttachList.add(soAttachMap);
//				TakePhotoMain.j++;
//				intent.putExtra("flag", "local");
//		    	setResult(RESULT_OK, intent);
//		    	PopDialog.this.finish(); 
	    	}else if(requestCode==2) {//现场拍摄
	    		BitmapFactory.Options options = new BitmapFactory.Options();
				// 先设置为TRUE不加载到内存中，但可以得到宽和高
				options.inJustDecodeBounds = true;
				Bitmap btm = BitmapFactory.decodeFile(path + "/"
						+ TakePhotoMain.j + ".jpg", options); // 此时返回bm为空
				options.inJustDecodeBounds = false;
				// 计算缩放比
				int be = (int) (options.outHeight / (float) 600);
				if (be <= 0)
					be = 1;
				options.inSampleSize = be;
				// 这样就不会内存溢出了
				btm = BitmapFactory.decodeFile(path + "/" + TakePhotoMain.j
						+ ".jpg", options);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				//bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				//byte[] bitmapBytes = baos.toByteArray(); 
				Intent intent = new Intent();
				Map soAttachMap = new HashMap(); 
				soAttachMap.put("desc", "");
				soAttachMap.put("image",btm);
//				intent.putExtra("image", bmp);  
				TakePhotoMain.soAttachList.add(soAttachMap);
				TakePhotoMain.j++;
				intent.putExtra("flag", "now");
				setResult(RESULT_OK, intent);
				PopDialog.this.finish(); 
	    	}
	    	
	    } 
	         
	    }  
	
	public Bitmap compressImage(Bitmap image) {  
		  
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();  
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos1);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
        int options = 100;   
        while ( baos1.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩         
        	baos1 = new ByteArrayOutputStream(); //重置baos即清空baos  
            image.compress(Bitmap.CompressFormat.JPEG, options, baos1);//这里压缩options%，把压缩后的数据存放到baos中  
            options -= 10;//每次都减少10  
        }  
        
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos1.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片  
        return bitmap;  
    }  
	
	protected void registerListener() {
		img_local_choose.setOnClickListener(new EditText.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent();    
//	            /* 开启Pictures画面Type设定为image */    
//	            intent.setType("image/*");    
//	            /* 使用Intent.ACTION_GET_CONTENT这个Action */    
//				//Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//	            
//	            intent.setAction(Intent.ACTION_GET_CONTENT);     
	            /* 取得相片后返回本画面 */    
				
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_PICK);
				intent.setDataAndType(Uri.parse("content://media/external/images/media"), "image/*");
	            startActivityForResult(intent, 1);  
			}
		});
		
		img_take_now.setOnClickListener(new EditText.OnClickListener() {
			@Override
			public void onClick(View v) {
				File dir = new File(path);
				Intent intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				File file = new File(dir, TakePhotoMain.j + ".jpg");
				Uri r = Uri.fromFile(file);
				intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, r);
				startActivityForResult(intent, 2);
			}
		});
		
		
	}
 
}
