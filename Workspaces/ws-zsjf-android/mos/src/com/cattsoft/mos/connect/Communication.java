package com.cattsoft.mos.connect;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.cattsoft.mos.dataprocess.MosApp;
import com.cattsoft.mos.exception.SysException;
import com.cattsoft.mos.pub.Constant;
import com.cattsoft.mos.util.EncryptUtil;
import com.cattsoft.mos.util.StringUtil;

/**
 * 用于和服务器端通信
 * @author xieyunchao
 * CreateTime Aug 22, 2012 1:33:37 PM
 */
public class Communication{
	
	 private static HttpClient httpClient;
	 public static HttpPost post;
	 private static String encryptUsable;
	 private static SharedPreferences sharedPreferences;
	 
	 /**
	  * 获取HttpClient静态对象，用于和服务器端通信
	  * @return HttpClient
	  */
	 @Deprecated
	 public static HttpClient getHttpClient() {
		 HttpParams httpParameters = new BasicHttpParams(); 
		 HttpConnectionParams.setConnectionTimeout(httpParameters, 50000);  
		 HttpConnectionParams.setSoTimeout(httpParameters, 180000);   
		 if(httpClient==null) {
			  httpClient=new DefaultHttpClient(httpParameters);
		  }
		  return httpClient;
	 }
	 
	
	/**
	 * @param url 服务器的地址，直接写Action的地址，如tm/WoHandleAction.do?method=query
	 * @param parameter 拼接好的json字符串
	 * @return 服务端json字符串，当服务器端返回AppException或SysException时，得到的字符串是一个html文档
	 * @throws Exception 
	 */
	 @Deprecated
	public static String getResponse(String url,String parameter) throws ClientProtocolException, IOException {
		  
		 String serverUrl=Constant.ServerURL+url;
		String result=null;
		initEncrypt();
		try{
		 if(!StringUtil.isBlank(parameter)) {
			 serverUrl=serverUrl+"&parameter="+parameter;
		 }
		 HttpGet request=new HttpGet(serverUrl);  
		 request.addHeader("Accept","text/json");   
		//获取响应的结果   
		 HttpResponse response =getHttpClient().execute(request);   
		//获取HttpEntity   
		 HttpEntity entity=response.getEntity();   
		// 获取响应的结果信息
		byte[] resultByteType = EntityUtils.toByteArray(entity);
		// 解压返回的字符串
		result = StringUtil.unCompress(resultByteType);
		if("Y".equals(encryptUsable)){
		result = EncryptUtil.decryptThreeDESECB(result); 
		}
		result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
	}  catch (SysException e)  {
		Toast.makeText(MosApp.getInstance().getApplicationContext(), e.getErrMsg() ,Toast.LENGTH_SHORT).show();
	} 
		return result;

	}
	
	 @Deprecated
	public static String getResponse(String url)throws ClientProtocolException, IOException  {
		 String result=null;
		 initEncrypt();
//		 try { 
		 HttpGet request=new HttpGet(url);  
		 request.addHeader("Accept","text/json");
		//获取响应的结果   
		 HttpResponse response =getHttpClient().execute(request);   
		//获取HttpEntity   
		 HttpEntity entity=response.getEntity();   
		// 获取响应的结果信息
//		byte[] resultByteType = EntityUtils.toByteArray(entity);
		// 解压返回的字符串
//		result = StringUtil.unCompress(resultByteType);
		result =EntityUtils.toString(entity,"UTF-8");   
//		if("Y".equals(encryptUsable)){
//		result = EncryptUtil.decryptThreeDESECB(result);
//		}
//		 }catch (SysException e)  {
//			 Toast.makeText(MosApp.getInstance().getApplicationContext(), e.getErrMsg() ,Toast.LENGTH_SHORT).show();
//		}
		return result;
	}
	 
	 @Deprecated
		public static String getResponseEncrypt(String url)throws ClientProtocolException, IOException  {
			 
		
			String serverUrl = Constant.ServerURL + url;
			HttpPost post = new HttpPost(serverUrl);
			post.addHeader("Content-Type", "application/json");
			String result = null;

				// 获取响应的结果
				HttpResponse response = getHttpClient().execute(post);
				// 获取HttpEntity
				HttpEntity respEntity = response.getEntity();
		 
			 result =EntityUtils.toString(respEntity,"UTF-8");   
			 return result;
			
		}
	 
	 @Deprecated
	public static String getScanResponse(String url)  {
		  
		 HttpGet request=new HttpGet(url);  
		String result=null;
		initEncrypt();
		try{
		 request.addHeader("Accept","text/html");
		 //获取响应的结果   
		 HttpResponse response =getHttpClient().execute(request);   
		//获取HttpEntity   
		 HttpEntity entity=response.getEntity();   
		// 获取响应的结果信息
		byte[] resultByteType = EntityUtils.toByteArray(entity);
		// 解压返回的字符串
		result = StringUtil.unCompress(resultByteType);
		if("Y".equals(encryptUsable)){
		result = EncryptUtil.decryptThreeDESECB(result);
		}
	 } catch (ClientProtocolException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (IOException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		}catch (SysException e)  {
			 Toast.makeText(MosApp.getInstance().getApplicationContext(), e.getErrMsg() ,Toast.LENGTH_SHORT).show();
		}
			 return result;

	}
	
	/**
	 * 以post方式提交数据
	 * 
	 * @param url
	 *            服务器的地址，直接写Action的地址，如tm/WoHandleAction.do?method=query
	 * @param parameter
	 *            拼接好的json字符串
	 * @return 服务端json字符串，当服务器端返回AppException或SysException时，得到的字符串是一个html文档
	 * @throws SysException
	 * @throws Exception
	 */
	@Deprecated
	public static String getPostResponse(String url, String parameter)
			throws ClientProtocolException, IOException {
		String serverUrl = Constant.ServerURL + url;
		String result = null;
		String parameterEncrypted = null;
		initEncrypt();
		post = new HttpPost(serverUrl);
		post.addHeader("Content-Type", "application/json");
		try {
			if("Y".equals(encryptUsable)){
			// 数据加密
			parameterEncrypted = EncryptUtil.encryptThreeDESECB(parameter);
			}else{
				parameterEncrypted = parameter;
			}
			StringEntity resEntity = new StringEntity(parameterEncrypted,
					"UTF-8");
				post.setEntity(resEntity);
				// 获取响应的结果
				HttpResponse response = getHttpClient().execute(post);

				// 获取HttpEntity
				HttpEntity respEntity = response.getEntity();
				// 获取响应的结果信息
				byte[] resultByteType = EntityUtils.toByteArray(respEntity);
				// 解压返回的字符串
				result = StringUtil.unCompress(resultByteType);
				if("Y".equals(encryptUsable)){
				result = EncryptUtil.decryptThreeDESECB(result);
				
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (IOException e) {
			e.printStackTrace();
			result = StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (SysException e) {
			result = StringUtil.getAppException4MOS("解压或解密出现异常！");
		}
		return result;
	}

	@Deprecated
	public static HttpPost getHttpPost(String url) {
		String serverUrl = Constant.ServerURL + url;
		post = new HttpPost(serverUrl);
		return post;
	}

	/**
	 * 获取服务器端返回的错误信息描述。当服务器端出现Exception时，客户端得到的结果是一个html 错误界面源码，该方法通过解析html，获得错误描述信息
	 * @param result
	 * @return
	 */
	 @Deprecated
	public static String getExceptionDesc(String result) {
		Document doc=Jsoup.parseBodyFragment(result);
        String text=doc.getElementById("errinfospan").text();
        if(StringUtil.isBlank(text)) {
        	text="服务器端出现异常！";
        }
        return text;
	}
	
	public static  String getPostHttpContent(HttpClient client,String url,String parameter)   {
		String serverUrl = Constant.ServerURL + url;
		HttpPost post = new HttpPost(serverUrl);
		initEncrypt();
		post.addHeader("Content-Type", "application/json");
		String result =null;
		String parameterEncrypted = null;
		try {
			if("Y".equals(encryptUsable)){
			//数据加密
			parameterEncrypted = EncryptUtil.encryptThreeDESECB(parameter);
			}else{
				parameterEncrypted = parameter;
			}
			StringEntity resEntity = new StringEntity(parameterEncrypted, "UTF-8");
			post.setEntity(resEntity);
			// 获取响应的结果
			HttpResponse response = client.execute(post);
			// 获取HttpEntity
			HttpEntity respEntity = response.getEntity();
			// 获取响应的结果信息
			byte[] resultByteType = EntityUtils.toByteArray(respEntity);
			// 解压返回的字符串
			result = StringUtil.unCompress(resultByteType);
			if("Y".equals(encryptUsable)){
			result = EncryptUtil.decryptThreeDESECB(result);
			}
			 return result;
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (IOException e) {
			e.printStackTrace();
			result=StringUtil.getAppException4MOS("网络连接异常，请检查网络设置！");
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=StringUtil.getAppException4MOS(e.getErrMsg());
		} 
		return result;
	}


	private static void initEncrypt() {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MosApp.getInstance().getApplicationContext());
		encryptUsable = sharedPreferences.getString("encryptUsable", "");
		
	}
	
	 
}
