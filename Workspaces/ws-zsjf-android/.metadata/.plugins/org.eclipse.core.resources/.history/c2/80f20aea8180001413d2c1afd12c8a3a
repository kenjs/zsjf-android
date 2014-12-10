package com.cattsoft.mos.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;

public class MCamera extends Activity {
	private Button mVideoStartBtn;
	private SurfaceView mSurfaceview;
	private MediaRecorder mMediaRecorder;
	private SurfaceHolder mSurfaceHolder;
	private File mRecVedioPath;
	private File mRecAudioFile;
	private TextView timer;
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private boolean bool;
	private int parentId;
	protected Camera camera;
	protected boolean isPreview;
	private Drawable iconStart;
	private Drawable iconStop;
	private Button btn_luxiang_list_normal;
	private boolean isRecording = true; // true表示没有录像，点击开始；false表示正在录像，点击暂停
	
	public static String fileSavePath="/朗宸资讯/视频/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * 全屏显示
		 */
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.map_video);
		iconStart = getResources().getDrawable(
				R.drawable.arc_hf_btn_video_start);
		iconStop = getResources().getDrawable(R.drawable.arc_hf_btn_video_stop);
		btn_luxiang_list_normal=(Button) findViewById(R.id.btn_luxiang_list_normal);

		parentId = getIntent().getIntExtra("parentId", 0);
		timer = (TextView) findViewById(R.id.arc_hf_video_timer);
		mVideoStartBtn = (Button) findViewById(R.id.arc_hf_video_start);
		mSurfaceview = (SurfaceView) this.findViewById(R.id.arc_hf_video_view);

		// 设置计时器不可见
		timer.setVisibility(View.GONE);

		// 设置缓存路径
		mRecVedioPath = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + fileSavePath);
		if (!mRecVedioPath.exists()) {
			mRecVedioPath.mkdirs();
		}

		// 绑定预览视图
		SurfaceHolder holder = mSurfaceview.getHolder();
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (camera != null) {
					if (isPreview) {
						camera.stopPreview();
						isPreview = false;
					}
					camera.release();
					camera = null; // 记得释放
				}
				mSurfaceview = null;
				mSurfaceHolder = null;
				mMediaRecorder = null;
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				try {
					camera = Camera.open();
					Camera.Parameters parameters = camera.getParameters();
					parameters.setPreviewFrameRate(5); // 每秒5帧
					parameters.setPictureFormat(PixelFormat.JPEG);// 设置照片的输出格式
					parameters.set("jpeg-quality", 85);// 照片质量
					camera.setParameters(parameters);
					camera.setPreviewDisplay(holder);
					camera.startPreview();
					isPreview = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				mSurfaceHolder = holder;
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				mSurfaceHolder = holder;
			}
		});
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		btn_luxiang_list_normal.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				HashMap m=new HashMap();
				m.put("pageFlag", "T");
				intent.putExtra("parmList", m);
				intent.setClass(MCamera.this,
						RecorderListActivity.class);
				startActivityForResult(intent, 1);
			}
		}
		);
		
		
		mVideoStartBtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isRecording) {
					/*
					 * 点击开始录像
					 */
					if (isPreview) {
						camera.stopPreview();
						camera.release();
						camera = null;
					}
					second = 0;
					minute = 0;
					hour = 0;
					bool = true;
					if (mMediaRecorder == null)
						mMediaRecorder = new MediaRecorder();
					else
						mMediaRecorder.reset();
					mMediaRecorder.setPreviewDisplay(mSurfaceHolder
							.getSurface());
					mMediaRecorder
							.setVideoSource(MediaRecorder.VideoSource.CAMERA);
					mMediaRecorder
							.setAudioSource(MediaRecorder.AudioSource.MIC);
					mMediaRecorder
							.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mMediaRecorder
							.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
					mMediaRecorder
							.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					mMediaRecorder.setVideoSize(320, 240);
					mMediaRecorder.setVideoFrameRate(15);
						mRecAudioFile = new File(mRecVedioPath,"temp.3gp");
					mMediaRecorder.setOutputFile(mRecAudioFile
							.getAbsolutePath());
					try {
						mMediaRecorder.prepare();
						timer.setVisibility(View.VISIBLE);
						handler.postDelayed(task, 1000);
						mMediaRecorder.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
					showMsg("开始录制");
					mVideoStartBtn.setBackgroundDrawable(iconStop);
					isRecording = !isRecording;
				} else {
					/*
					 * 点击停止
					 */
					try {
						bool = false;
						mMediaRecorder.stop();
						timer.setText(format(hour) + ":" + format(minute) + ":"
								+ format(second));
						mMediaRecorder.release();
						mMediaRecorder = null;
						//videoRename();
						toSave();
					} catch (Exception e) {
						e.printStackTrace();
					}
					isRecording = !isRecording;
					mVideoStartBtn.setBackgroundDrawable(iconStart);
					showMsg("录制完成，已保存");

					try {
						camera = Camera.open();
						Camera.Parameters parameters = camera.getParameters();
						parameters.setPreviewFrameRate(5); // 每秒5帧
						parameters.setPictureFormat(PixelFormat.JPEG);// 设置照片的输出格式
						parameters.set("jpeg-quality", 85);// 照片质量
						camera.setParameters(parameters);
						camera.setPreviewDisplay(mSurfaceHolder);
						camera.startPreview();
						isPreview = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		//Button btnImgStart = (Button) findViewById(R.id.arc_hf_img_start);
//		btnImgStart.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (mMediaRecorder != null) {
//					try {
//						bool = false;
//						mMediaRecorder.stop();
//						timer.setText(format(hour) + ":" + format(minute) + ":"
//								+ format(second));
//						mMediaRecorder.release();
//						mMediaRecorder = null;
//						videoRename();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					isRecording = !isRecording;
//					mVideoStartBtn.setBackgroundDrawable(iconStart);
//					showMsg("录制完成，已保存");
//
//					try {
//						camera = Camera.open();
//						Camera.Parameters parameters = camera.getParameters();
//						parameters.setPreviewFrameRate(5); // 每秒5帧
//						parameters.setPictureFormat(PixelFormat.JPEG);// 设置照片的输出格式
//						parameters.set("jpeg-quality", 85);// 照片质量
//						camera.setParameters(parameters);
//						camera.setPreviewDisplay(mSurfaceHolder);
//						camera.startPreview();
//						isPreview = true;
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//				if (camera != null) {
//					camera.autoFocus(null);
//					camera.takePicture(null, null, new PictureCallback() {
//						@Override
//						public void onPictureTaken(byte[] data, Camera camera) {
//							Bitmap bitmap = BitmapFactory.decodeByteArray(data,
//									0, data.length);
//							Matrix matrix = new Matrix();
//							// 设置缩放
//							matrix.postScale(5f, 4f);
//							bitmap = Bitmap.createBitmap(bitmap, 0, 0,
//									bitmap.getWidth(), bitmap.getHeight(),
//									matrix, true);
//
//							String path = Environment
//									.getExternalStorageDirectory()
//									.getAbsolutePath()
//									+ "/hfdatabase/img/"
//									+ String.valueOf(parentId) + "/";
//							String fileName = new SimpleDateFormat(
//									"yyyyMMddHHmmss").format(new Date())
//									+ ".jpg";
//							File out = new File(path);
//							if (!out.exists()) {
//								out.mkdirs();
//							}
//							out = new File(path, fileName);
//							try {
//								FileOutputStream outStream = new FileOutputStream(
//										out);
//								bitmap.compress(CompressFormat.JPEG, 100,
//										outStream);
//								outStream.close();
//								camera.startPreview();
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//							showMsg("拍照成功");
//						}
//					}); // 拍照
//				}
//			}
//		});
	}

	/*
	 * 消息提示
	 */
	private Toast toast;

	public void showMsg(String arg) {
		if (toast == null) {
			toast = Toast.makeText(this, arg, Toast.LENGTH_SHORT);
		} else {
			toast.cancel();
			toast.setText(arg);
		}
		toast.show();
	}

	
	
	private void toSave() throws IOException {
		FileInputStream  fis= new FileInputStream(mRecAudioFile);
		Intent intent = new Intent();
    	Bundle b=new Bundle();
    	HashMap valueMap=new HashMap();
    	valueMap.put("fileSize", fis.available()+"");
    	valueMap.put("timeLong", timer.getText().toString());
    	valueMap.put("fileType", "V");
    	
    	b.putSerializable("valueMap", valueMap);
		intent.putExtras(b);
    	
    	intent.setClass(MCamera.this,
				RecorderSaveActivity.class);
		startActivity(intent);
	}
	
	
	
	/*
	 * 生成video文件名字
	 */
	protected void videoRename() {
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ fileSavePath
				+ String.valueOf(parentId) + "/";
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()) + ".3gp";
		File out = new File(path);
		if (!out.exists()) {
			out.mkdirs();
		}
		out = new File(path, fileName);
		if (mRecAudioFile.exists())
			mRecAudioFile.renameTo(out);
	}

	/*
	 * 定时器设置，实现计时
	 */
	private Handler handler = new Handler();
	private Runnable task = new Runnable() {
		public void run() {
			if (bool) {
				handler.postDelayed(this, 1000);
				second++;
				if (second >= 60) {
					minute++;
					second = second % 60;
				}
				if (minute >= 60) {
					hour++;
					minute = minute % 60;
				}
				timer.setText(format(hour) + ":" + format(minute) + ":"
						+ format(second));
			}
		}
	};

	/*
	 * 格式化时间
	 */
	public String format(int i) {
		String s = i + "";
		if (s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}

	/*
	 * 覆写返回键监听
	 */
	@Override
	public void onBackPressed() {
		if (mMediaRecorder != null) {
			mMediaRecorder.stop();
			mMediaRecorder.release();
			mMediaRecorder = null;
			//videoRename();
		}
		finish();
	}

	@Override
	protected void onPause() {
		super.onPause();
		onBackPressed();
	}
}