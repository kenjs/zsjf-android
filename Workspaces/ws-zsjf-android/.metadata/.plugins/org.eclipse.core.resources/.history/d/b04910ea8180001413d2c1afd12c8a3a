package com.cattsoft.mos.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.cattsoft.mos.R;
import com.cattsoft.mos.activity.EventDetailActivity;
import com.cattsoft.mos.dataprocess.CacheProcess;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.DeviceUtil;
import com.cattsoft.mos.util.StringUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class MessageService extends Service {
	private Channel channel = null;
	private Connection connection = null;
	private QueueingConsumer consumer = null;
//	private static final String EXCHANGE_NAME="testExchange";
//	private static final String RouteKey="testRouteKey";
//	private static final String messageQueenAddr="192.168.1.68";
//	private static final int messageQueenPort=5672;
//	private static String TASK_QUEUE_NAME = "testQueue";
	private MessageThread messageThread;
	NotificationManager manager;
	private String deviceId;//设备id作为队列名
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
    
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Map deviceInfo = DeviceUtil.getDeviceParameter(this);
		deviceId = (String) deviceInfo.get("deviceId");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		messageThread = new MessageThread();
		messageThread.start();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		close();
		super.onDestroy();
	}

	private void init() {
		System.out.println("start init consumer");
		if (consumer == null) {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(Constant.messageQueenAddr);
			factory.setPort(Constant.messageQueenPort);

			try {
				connection = factory.newConnection();
				channel = connection.createChannel();
				channel.exchangeDeclare(Constant.exchangeName, "direct", true);//声明exchange
//				channel.exchangeDeclare("8641400100778701", "direct", true);//声明exchange
				channel.queueDeclare(deviceId, true, false, false, null);//声明queue
				channel.queueBind(deviceId, Constant.exchangeName, Constant.routeKey);
				int prefetchCount = 1;// 表示从服务器上预先取的条
				channel.basicQos(prefetchCount);
				consumer = new QueueingConsumer(channel);
				channel.basicConsume(deviceId, false, consumer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void close() {
		try {
			channel.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void receiveMessage() {
		try {
			init();
			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());

				Log.v("debug"," [x] Received '" + message + "'");
				doWork(message);
				Log.v("debug"," [x] Done");

				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		} catch (Exception e) {
			Log.e("Error","receive message exception!!!", e);
		}
	}


	private void doWork(String message) {
		try {
			System.out.println(message);
			showNotification(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void showNotification(String messageContent) {
//		messageContent=StringUtil.decodeUnicode(messageContent);
		JSONObject messageJsonObj = JSONObject.parseObject(messageContent);
		String messageBody = messageJsonObj.getString("body");
		HashMap messageBodyMap = JSONObject.parseObject(messageBody,HashMap.class);
		String messageType = messageJsonObj.getString("type");
//		String messageSource = messageJsonObj.getString("source");
//		String messageTime = messageJsonObj.getString("time");
		
		String title = (String) messageBodyMap.get("msg_title");
		title= StringUtil.decodeUnicode(title);
		
		String msg_content = (String) messageBodyMap.get("msg_content");
		msg_content=StringUtil.decodeUnicode(msg_content);
		
		String msgId = (String) messageBodyMap.get("msg_id");
		String msgType=(String) messageBodyMap.get("msg_type");
		
		String cacheInfo = new CacheProcess().getCacheValueInSharedPreferences(
				this, "cache");
		com.alibaba.fastjson.JSONObject j = com.alibaba.fastjson.JSONObject
				.parseObject(cacheInfo);
		com.alibaba.fastjson.JSONArray cpzlarr = j.getJSONArray("messageTypes");// 产品种类
		List msgTypeListList = com.alibaba.fastjson.JSONArray.parseArray(
				cpzlarr.toJSONString(), HashMap.class);
		

		for(int i=0;i<msgTypeListList.size();i++) {
			Map m=(HashMap)msgTypeListList.get(i);
			String key=(String)m.get("key");
			if(msgType.equals(key)) {
				msgType=(String)m.get("value");
				break;
			}
		}
		
		
		
		msgType= StringUtil.decodeUnicode(msgType);
		String create_dt=(String) messageBodyMap.get("create_dt");
		
		
		int imsgId = Integer.parseInt(msgId);
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack
		stackBuilder.addParentStack(EventDetailActivity.class);
		// Adds the Intent to the top of the stack
		Intent resultIntent = new Intent(this, EventDetailActivity.class);
//		resultIntent.putExtra("message", messageBody);
		
		HashMap valueMap=new HashMap();
		valueMap.put("msg_title", msgType);
		valueMap.put("msg_content", msg_content);
		valueMap.put("msg_id", msgId);
		valueMap.put("msg_type", msgType);
		valueMap.put("create_dt", create_dt);
		valueMap.put("msg_state", "未读");
		
		resultIntent.putExtra("valueMap", valueMap);
		
		//把接受到的消息存在本地
		String eventStr=new CacheProcess().getEventInfo(this);
		
		if(StringUtil.isBlank(eventStr)) {//如果为空，则直接放入
			com.alibaba.fastjson.JSONArray arr=new com.alibaba.fastjson.JSONArray();
			arr.add(valueMap);
			new CacheProcess().updateEventInfo(this, arr.toJSONString());
		}else {//否则就先取出来，再放进去
			com.alibaba.fastjson.JSONArray jonarr=new com.alibaba.fastjson.JSONArray();
			jonarr.add(valueMap);
			
			com.alibaba.fastjson.JSONArray jarr=com.alibaba.fastjson.JSONArray.parseArray(eventStr);
			jonarr.addAll(jarr);
			
			List alist=new ArrayList();
			if(jonarr.size()>=100) {
				alist=jonarr.subList(0, 99);
			}else {
				alist=jonarr;
			}
			
			
			new CacheProcess().updateEventInfo(this, com.alibaba.fastjson.JSONObject.toJSONString(alist));
		}
		
		
		stackBuilder.addNextIntent(resultIntent);
		// Gets a PendingIntent containing the entire back stack
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_CANCEL_CURRENT);

		Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setTicker(title).setContentInfo("")
				.setContentTitle(title).setContentText(msg_content)
				.setContentIntent(resultPendingIntent).setAutoCancel(true)
				.setDefaults(Notification.DEFAULT_ALL).build();
		manager.notify(imsgId, notification);
	}
	
	class MessageThread extends Thread {

		@Override
		public void run() {
			System.out.println("receiveMessage start to run");
			receiveMessage();
		}
		
	}
}
