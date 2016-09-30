package com.ziroom.confManagement.commonMethods;

import java.util.Map;
import org.apache.log4j.Logger;
import com.ziroom.httpclient.HttpClientUtils;
import com.ziroom.httpclient.JsonValidator;
import net.sf.json.JSONObject;


public class HttpRequest {
	private final static Logger logCm = Logger.getLogger(HttpRequest.class);
	HttpClientUtils hcu = new HttpClientUtils();

	
	/***
	 * post带参数请求返回json格式数据
	 * 
	 * @param requestUrl
	 * @param mapParameter
	 * @return JSONObject
	 */
	public JSONObject getPostReturnValue(String requestUrl, Map<String, String> mapParameter) {

		// 打印出调用此方法的类名和方法名字
		StackTraceElement ste = (new Throwable()).getStackTrace()[1];
		String classAndMethod = (ste.getClassName() + "->" + ste.getMethodName());
		// 格式化入参为json根式
		JSONObject jsobj = JSONObject.fromObject(mapParameter);
		// 请求接口返回内容
		String returnValue = hcu.httpPostRequest(requestUrl, jsobj).get("returnValue").trim();
		// 请求接口返回状态例如：200
		String returnStatusCode = hcu.httpPostRequest(requestUrl, jsobj).get("returnStatusCode");

		if (returnValue.equals("")) {
			logCm.debug(classAndMethod + "请求，服务器---->>>>>返回值为空");

		} else {
			if (new JsonValidator().validate(returnValue)) {
				JSONObject responseJson = JSONObject.fromObject(returnValue);
				responseJson.put("returnStatusCode", returnStatusCode);
				logCm.debug(classAndMethod + "请求，状态码:" + returnStatusCode + ",服务器返回值：" + returnValue);
				return responseJson;
			} else {
				logCm.debug(classAndMethod + "请求，状态码:" + returnStatusCode + ",服务器返回不是有效json格式，返回值：" + returnValue);
			}

		}
		return null;

	}

	/***
	 * get无参数请求返回JSONObject
	 * 
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject getGetReturn(String requestUrl) {
		// 打印出调用此方法的类名和方法名字
		StackTraceElement ste = (new Throwable()).getStackTrace()[1];
		String classAndMethod = (ste.getClassName() + "->" + ste.getMethodName());
		String returnValue = hcu.httpGetRequest(requestUrl).get("returnValue").trim();
		String returnStatusCode = hcu.httpGetRequest(requestUrl).get("returnStatusCode").trim();
		if (returnValue.equals("")) {
			logCm.info("getGetReturn被调函数----->>>>>返回值为空");

		} else {
			if (new JsonValidator().validate(returnValue)) {
				JSONObject responseJson = JSONObject.fromObject(returnValue);
				responseJson.put("returnStatusCode", returnStatusCode);
				logCm.debug(classAndMethod + "请求，状态码:" + returnStatusCode + ",服务器返回值：" + returnValue);
				return responseJson;
			} else {
				logCm.debug(classAndMethod + "请求，状态码:" + returnStatusCode + ",服务器返回不是有效json格式，返回值：" + returnValue);
			}

		}

		return null;

	}
	
	
//	public String getPostReturnValue1(String requestUrl, Map<String, String> mapParameter) {
//
//		// 打印出调用此方法的类名和方法名字
//		StackTraceElement ste = (new Throwable()).getStackTrace()[1];
//		String classAndMethod = (ste.getClassName() + "->" + ste.getMethodName());
//		// 格式化入参为json根式
//		JSONObject jsobj = JSONObject.fromObject(mapParameter);
//		// 请求接口返回内容
//		String returnValue = hcu.httpPostRequest(requestUrl, jsobj).get("returnValue").trim();
//		// 请求接口返回状态例如：200
//		String returnStatusCode = hcu.httpPostRequest(requestUrl, jsobj).get("returnStatusCode");
//
//		return returnValue;
//
//	}
	
	/***
	 * get带参数请求返回JSONObject
	 * 
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject getGetOfParasReturn(String requestUrl,Map<String, String> mapParameter ) {
		// 打印出调用此方法的类名和方法名字
		StackTraceElement ste = (new Throwable()).getStackTrace()[1];
		String classAndMethod = (ste.getClassName() + "->" + ste.getMethodName());
		JSONObject jsobj = JSONObject.fromObject(mapParameter);
		String returnValue = hcu.httpGetRequest(requestUrl,jsobj).get("returnValue").trim();
		String returnStatusCode = hcu.httpGetRequest(requestUrl,jsobj).get("returnStatusCode").trim();
		if (returnValue.equals("")) {
			logCm.info("getGetOfParasReturn被调函数----->>>>>返回值为空");

		} else {
			if (new JsonValidator().validate(returnValue)) {
				JSONObject responseJson = JSONObject.fromObject(returnValue);
				logCm.debug(classAndMethod + "请求，状态码:" + returnStatusCode + ",服务器返回值：" + returnValue);
				return responseJson;
			} else {
				logCm.debug(classAndMethod + "请求，状态码:" + returnStatusCode + ",服务器返回不是有效json格式，返回值：" + returnValue);
			}

		}

		return null;

	}


}
