/*
 * @(#)GenerateHtmlUtil.java Feb 3, 2010
 *
 * Copyright (c) 2010 - financegene
 * nanjing
 * china
 * All rights reserved.
 */

package com.cattsoft.mos.activity;



public class GenerateHtmlUtil {
///**
//    public static final String HTML_CONTENT_TYPE = "text/html";
//
//    private static final Pattern newLinePattern = Pattern.compile("(\r\n|\n|\r)");
//    
//    static{
//        trustAllCertificates();
//    }
//
//    private GenerateHtmlUtil() {}
//
////    public static String getHtml(String actionName, Map<String, Object> params) throws IOException {
////
////        String url = getUrl(actionName, null);
////        HttpUtil hu = new HttpUtil();
////        HttpResult hr = hu.sendPostRequest(url, params);
////        return hr.getResponseContent().toString();
////    }
//
//    public static <E extends AbstractFileTableObject>  E constructHtmlFile(String actionName, String fileName, Map<String, Object> params, WmsLogin login, Class<E> clazz) {
//    	E obj = null;
//        try {
//
//            if(params == null) {
//                params = Collections.emptyMap();//new HashMap<String, Object>(0);
//            }
//
//            String url = getUrl(actionName, null);
//            //HttpUtil hu = new HttpUtil();
//            HttpResult hr = sendPostRequest(url, params, login.getSessionNo());
//            
//            obj = clazz.newInstance();
//            obj.setContent(new ByteArrayInputStream(hr.getResponseContent().toString().getBytes(HttpResult.UTF8)));
//            obj.setFile_name(fileName);
//            obj.setContent_type(HTML_CONTENT_TYPE);
//        }
//        catch (Exception e) {
//        	e.printStackTrace();
//            throw new BIRuntimeException(e);
//            //
//        }
//        return obj;
//    }
//    /**
//     * 
//     * Sends an HTTP POST request to url.
//     * Content-Type = application/x-www-form-urlencoded; charset=UTF-8
//     * @param url
//     * @param requestParameters
//     * @return HttpResult
//     * @throws IOException
//     */
//    public static HttpResult sendPostRequest(String url, Map<String, Object> requestParameters, String sessionId) throws IOException {
//    	
//    	/**
//    	 * tomcat下使用的
//    	 
//        if(!StrUtil.isEmpty(sessionId)) {
//            url += ";jsessionid="+sessionId;
//        }*/
//        if(!CollectionUtil.isEmpty(requestParameters)) {
//            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
//            for (Iterator<Entry<String, Object>> i = requestParameters.entrySet().iterator(); i.hasNext();) {
//
//                Entry<String, Object> entry = i.next();
//                Object value = entry.getValue();
//                if(value != null) {
//                    String key = entry.getKey();
//                    if(value instanceof Collection<?>) {
//                        Collection<?> col = (Collection<?>) value;
//                        for(Object o : col) {
//                            qparams.add(new BasicNameValuePair(key, String.valueOf(o)));
//                        }
//                    }
//                    else {
//                        qparams.add(new BasicNameValuePair(key, String.valueOf(entry.getValue())));
//                    }
//                }
//            }
//
//            url += "?"+ URLEncodedUtils.format(qparams, HttpResult.UTF8);
//        }
//        HttpPost post = new HttpPost(url);
//       
//        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset="+HttpResult.UTF8);
//        
//        
//        
//        
//        /**
//         * was下的使用的
//         */       
//        post.addHeader("Cookie", "JSESSIONID=0000"+sessionId+":-1");
//        HttpResult result = sendRequest(post);
//        return result;
//    }
//
//    /**
//     * 
//     * @param login
//     * @param method
//     * @param responseType HttpResult.RESPONSETYPE_STRING or HttpResult.RESPONSETYPE_STREAM
//     * @return HttpResult
//     */
//    private static HttpResult sendRequest(HttpRequestBase request) throws IOException {
//
//        DefaultHttpClient client = new DefaultHttpClient();
//      
//        HttpResult httpResult = new HttpResult();
//        HttpResponse response = client.execute(request);
//        httpResult.setStatus(response.getStatusLine().getStatusCode());
//
//        HttpEntity entity = response.getEntity();
//
//        if(entity != null) {
//            httpResult.setStringFromInputStream(entity.getContent());
//            //entity.consumeContent();
//        }
//        request.abort();
//   
//        return httpResult;
//    }
//
//
//    public static String replaceNewLineWithBr(String str) {
//        
//        if(!StrUtil.isEmpty(str)) {
//            str = newLinePattern.matcher(str).replaceAll("<br>");
//        }
//        return str;
//    }
//
//    public static String getUrl(String actionName, Map<String, Object> params) {
//    	return UrlHelperWMS.buildUrl(formatActionName(actionName), ServletActionContext.getRequest(), ServletActionContext.getResponse(), params, ServletActionContext.getRequest().getScheme(), true, true, true, true);
//    }
//
//    private static String getActionExtenstion() {
//        Container cont = ActionContext.getContext().getContainer();
//        return cont.getInstance(String.class, StrutsConstants.STRUTS_ACTION_EXTENSION);
//    }
//
//    private static String formatActionName(String actionName) {
//        return actionName+"."+getActionExtenstion();
//    }
//    
//    private static void trustAllCertificates() {
//
//        // Create a trust manager that does not validate certificate chains
//        TrustManager[] trustAllCerts = new TrustManager[] {
//                new X509TrustManager() {
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return null;
//                    }
//                    public void checkClientTrusted(
//                            X509Certificate[] certs, String authType) {
//                    }
//                    public void checkServerTrusted(
//                            X509Certificate[] certs, String authType) {
//                    }
//                }
//        };
//
//        // Install the all-trusting trust manager
//        try {
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//            SSLSocketFactory.getSocketFactory().setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        } catch (Exception e) {
//            System.out.println("Trust all certificate failed." + e.getMessage());
//            e.printStackTrace();
//        }
//
//    }
//
//	public static void constructHtmlFile(String saveAssetComUrl) {
//		
//       HttpClient httpClient = new HttpClient();
//       GetMethod method =  new GetMethod(saveAssetComUrl);
//       try {
//		int d = httpClient.executeMethod(method);
//		System.out.println(method.getResponseBodyAsString());;
//	} catch (HttpException e) {
//		
//		e.printStackTrace();
//	} catch (IOException e) {
//		
//		e.printStackTrace();
//	}
//       
//	System.out.println(saveAssetComUrl);
//	}**/

}
