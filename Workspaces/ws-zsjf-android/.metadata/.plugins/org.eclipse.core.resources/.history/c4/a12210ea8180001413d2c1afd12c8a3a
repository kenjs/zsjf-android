package com.cattsoft.mos.service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.cattsoft.mos.R;
import com.cattsoft.mos.connect.CustomMultipartEntity;
import com.cattsoft.mos.connect.CustomMultipartEntity.ProgressListener;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.DeviceUtil;
import com.cattsoft.mos.vo.mvo.Task;

public class BackgroundUploadService extends Service {

	/**
	 * 通知管理
	 */
	private NotificationManager mNotificationManager;

	/**
	 * 通信对象
	 */
	private Binder binder = new BackgroundUploadBinder();

	/**
	 * 总进度
	 */
	private final int amountProgress = 100;

	/**
	 * 任务线程池
	 */
	private Executor executor;

	/**
	 * 最大任务数
	 */
	private final int maxTask = 3;

	private String filePath = "sdcard/Movies/test.mp4";
	private long totalSize;


	public class BackgroundUploadBinder extends Binder {
		private String content="";
		private Context context=null;
		
		
		
		public Context getContext() {
			return context;
		}

		public void setContext(Context context) {
			this.context = context;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public void startTask(final Task task) {
//			 android.os.Debug.waitForDebugger();
			if (null == executor) {
				executor = Executors.newFixedThreadPool(maxTask);
			}
			
			final NotificationCompat.Builder builder = initNotification(task);
			mNotificationManager.notify(task.getId(), builder.build());  
			// 使用线程池管理多任务
			executor.execute(new Runnable() {
				@Override
				public void run() {

					String serverResponse = null;

					HttpClient httpClient = new DefaultHttpClient();
					HttpContext httpContext = new BasicHttpContext();
					String filePathUrl=new CacheProcess().getUpdateFileUploadServer(context);
					HttpPost httpPost = new HttpPost(Constant.http+filePathUrl+Constant.uploadMethod);
					try {
						CustomMultipartEntity multipartContent = new CustomMultipartEntity(
								new ProgressListener() {
									int currentProgress = 0;

									
									@Override
									public void transferred(long num) {
										currentProgress = (int) ((num / (float) totalSize) * 100);
										if (task.getProgress() < currentProgress && task.getProgress() < amountProgress) {
											task.setProgress(currentProgress);
											task.setStatus(Constant.download);
											builder.setProgress(100, task.getProgress(), false).setContentInfo(task.getProgress() + "%");
											mNotificationManager.notify(task.getId(), builder.build());  
											if (task.getProgress() == amountProgress) {
												task.setStatus(Constant.downloaded);
												builder.setAutoCancel(true).setOngoing(false).setContentText("上传完成").setProgress(0, 0, false).setContentInfo("").setContentTitle(task.getName());
												mNotificationManager.notify(task.getId(), builder.build());
											}
										}
									}
								});

						// We use FileBody to transfer
						multipartContent.addPart("data", new FileBody(new File(task.getPath())));
						
						multipartContent.addPart("content", new StringBody(content,Charset.forName("UTF-8")));
						Map headMap=DeviceUtil.getDeviceParameter(context);
						multipartContent.addPart("head", new StringBody(com.alibaba.fastjson.JSONObject.toJSONString(headMap),Charset.forName("UTF-8")));
						
						totalSize = multipartContent.getContentLength();

						// Send it
						httpPost.setEntity(multipartContent);
						HttpResponse response = httpClient.execute(httpPost,httpContext);
						serverResponse = EntityUtils.toString(response.getEntity());

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// 该方法必须返回binder
		return binder;
	}

	private NotificationCompat.Builder initNotification(Task task) {
		final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
        .setContentTitle("正在上传 "+task.getName())
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentInfo(task.getProgress() + "%")
        .setOngoing(true)
        .setProgress(100, task.getProgress(), false);
		return builder;
	}
}
