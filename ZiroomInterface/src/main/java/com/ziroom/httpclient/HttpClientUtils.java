package com.ziroom.httpclient;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author 程程
 * @Date 2016-1-26 ##修改类型
 */

public class HttpClientUtils {

	// private static final Logger logger =
	// Logger.getLogger(HttpClientUtils.class);

	private static PoolingHttpClientConnectionManager cm;
	static CookieStore cookieStore = null;
	private static String UTF_8 = "UTF-8";

	private static void init() {
		if (cm == null) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(50);
			cm.setDefaultMaxPerRoute(5);
		}
	}

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient getHttpClient() {
		init();
		// return HttpClients.custom().setConnectionManager(cm).build();
		return HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setDefaultCookieStore(cookieStore).build();
	}

	/**
	 * 处理Http请求
	 * 
	 * @param request
	 * @return
	 */
	private HashMap<String, String> getResult(HttpRequestBase request) {
		HttpClientContext context = HttpClientContext.create();
		HashMap<String, String> map = new HashMap<String, String>();
		CloseableHttpClient httpClient = getHttpClient();
		try {
			CloseableHttpResponse response = httpClient.execute(request, context);
			cookieStore = context.getCookieStore();
			/*
			 * List<Cookie> cookies = cookieStore.getCookies(); for (Cookie
			 * cookie : cookies) { System.out.println("key:" + cookie.getName()
			 * + "  value:" + cookie.getValue()); }
			 */
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				map.put("returnValue", EntityUtils.toString(entity));
				map.put("returnStatusCode", String.valueOf(response.getStatusLine().getStatusCode()));
				response.close();
				return map;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}

	/***
	 * 
	 * @param request
	 * @return private HashMap<String, String> getResult(HttpRequestBase
	 *         request) { HashMap<String, String> map = new HashMap<String,
	 *         String>(); CloseableHttpClient httpClient = getHttpClient();
	 * 
	 *         try { CloseableHttpResponse response =
	 *         httpClient.execute(request); HttpEntity entity =
	 *         response.getEntity();
	 * 
	 *         if (entity != null) { map.put("returnValue",
	 *         EntityUtils.toString(entity)); map.put("returnStatusCode",
	 *         String.valueOf(response.getStatusLine().getStatusCode()));
	 *         response.close(); return map; } } catch (ClientProtocolException
	 *         e) { e.printStackTrace(); } catch (IOException e) {
	 *         e.printStackTrace(); } finally {
	 * 
	 *         }
	 * 
	 *         return null; }
	 */

	/**
	 * 无参数的http Get请求
	 * 
	 * @return
	 */
	public HashMap<String, String> httpGetRequest(String url) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}

	/**
	 * 无参数的http Get请求加header
	 * 
	 * @return
	 */
	public HashMap<String, String> httpGetRequestWithHeaders(String url, Map<String, String> map) {
		HttpGet httpGet = new HttpGet(url);
		for (String key : map.keySet()) {
			httpGet.setHeader(key, map.get(key));
		}
		return getResult(httpGet);
	}

	/**
	 * 带参数 http Get请求
	 * 
	 * @param JSONObject
	 *            paraValue : 接口参数
	 * @return
	 */
	public HashMap<String, String> httpGetRequest(String url, JSONObject paraValue) {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		HttpGet httpGet = null;

		ArrayList<NameValuePair> pairs = covertParams2NVPS(paraValue);
		ub.setParameters(pairs);
		try {
			httpGet = new HttpGet(ub.build());

		} catch (Exception e) {

		}

		return getResult(httpGet);

	}

	/***
	 * 带参数http get请求加header
	 * 
	 * @param url
	 * @param paraValue
	 *            接口参数
	 * @return
	 */
	public HashMap<String, String> httpGetRequestWithHeaders(String url, JSONObject paraValue,
			Map<String, String> map) {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		HttpGet httpGet = null;

		ArrayList<NameValuePair> pairs = covertParams2NVPS(paraValue);
		ub.setParameters(pairs);
		try {
			httpGet = new HttpGet(ub.build());
			for (String key : map.keySet()) {
				httpGet.setHeader(key, map.get(key));
			}

		} catch (Exception e) {

		}

		return getResult(httpGet);

	}

	/***
	 * 无参的http post请求
	 * 
	 * @param url
	 * @return HashMap
	 */
	public HashMap<String, String> httpPostRequest(String url) {
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}

	/***
	 * 无参的http post请求带header
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public HashMap<String, String> httpPostRequestWithHeader(String url, Map<String, String> map) {
		HttpPost httpPost = new HttpPost(url);
		for (String key : map.keySet()) {
			httpPost.setHeader(key, map.get(key));
		}

		return getResult(httpPost);
	}

	/*
	 * http post请求
	 * 
	 * @param JSONObject paraValue ：接口参数
	 * 
	 * @return HashMap
	 */
	public HashMap<String, String> httpPostRequest(String url, JSONObject paraValue) {
		HttpPost httpPost = new HttpPost(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(paraValue);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
		} catch (Exception e) {
			System.out.println("错误的信息" + e.getMessage());
		}

		return getResult(httpPost);
	}

	/****
	 * http post请求带header
	 * 
	 * @param url
	 * @param paraValue
	 *            请求参数
	 * @param map
	 * @return
	 */
	public HashMap<String, String> httpPostRequestWithHeaders(String url, JSONObject paraValue,
			Map<String, String> map) {
		HttpPost httpPost = new HttpPost(url);
		for (String key : map.keySet()) {
			httpPost.setHeader(key, map.get(key));
		}
		ArrayList<NameValuePair> pairs = covertParams2NVPS(paraValue);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

		} catch (Exception e) {
			System.out.println("错误的信息" + e.getMessage());
		}

		return getResult(httpPost);
	}

	/**
	 * 将参数转换成ArrayList<NameValyePair>
	 * 
	 * @param JSONObject
	 *            paraValue ： 接口参数
	 * @return
	 */
	private ArrayList<NameValuePair> covertParams2NVPS(JSONObject paraValue) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		Iterator<?> pv = paraValue.keys();
		while (pv.hasNext()) {
			String key = pv.next().toString();
			pairs.add(new BasicNameValuePair(key, paraValue.getString(key)));
		}

		return pairs;
	}

}
