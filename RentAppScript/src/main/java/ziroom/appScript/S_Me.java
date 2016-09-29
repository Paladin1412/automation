package ziroom.appScript;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import com.ziroom.httpclient.HttpClientUtils;
import com.ziroom.utils.CommonFunction;
import net.sf.json.JSONObject;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;
import ziroom.confManagement.commonMethods.HttpRequest;
import ziroom.confManagement.config.PropertyConstants;

public class S_Me {

	private static final Logger logMe = Logger.getLogger(S_Me.class);
	public static String login_uid;
	HttpClientUtils hcu = new HttpClientUtils();
	HttpRequest hRequest = new HttpRequest();

	/***
	 * 登陆api
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 *            #请求url
	 * @param loginName
	 *            #登陆用户名
	 * @param passWord
	 *            #登陆密码
	 * @return
	 * 
	 */
	public JSONObject s_login(String requestUrl, String loginName, String passWord) {
		String initUid = "0";
		String timestamp = CommonFunction.getTimeStampOf10();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", CommonFunction.getAppSign(initUid, timestamp));
		map.put("uid", "0");
		map.put("timestamp", timestamp);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("password", passWord);
		map.put("login_name", loginName);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		String responseData = responseJson.getString("data");

		// 登陆后的返回的uid，赋值为后面使用
		if (responseData.equals("[]")) {
			logMe.info("变量uid值为空");
		} else {
			RentAppGlobalParas.login_uid = JSONObject.fromObject(responseData).getString("uid");
		}
		logMe.debug("变量login_uid:" + RentAppGlobalParas.login_uid);
		logMe.info("s_login返回值：" + responseJson);
		
		return responseJson;
	}

	/***
	 * 获取资质信息
	 * @param requestStr  域名
	 * @return
	 */
	public JSONObject s_get_certificate(String requestStr) {
		// 请求获取资质信息
		String domainName = requestStr;
		String SuffixUrl = "/index.php?_p=api_newsign&_a=select_extend_info&uid=" + RentAppGlobalParas.login_uid + "&sign="
				+ CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10())
				+ "&timestamp=" + CommonFunction.getTimeStampOf10() + "";
		String httpUrl = domainName + SuffixUrl;
		JSONObject responseJson = hRequest.getGetReturn(httpUrl);
		Reporter.log(httpUrl);
		Reporter.log("返回值："+responseJson);
		String responseData = responseJson.getString("data");
		// 获取资质信息字段
		RentAppGlobalParas.work_name = JSONObject.fromObject(responseData).getString("work_name");
		RentAppGlobalParas.work_address = JSONObject.fromObject(responseData).getString("work_address");
		RentAppGlobalParas.certifier_name = JSONObject.fromObject(responseData).getString("certifier_name");
		RentAppGlobalParas.certifier_phone = JSONObject.fromObject(responseData).getString("certifier_phone");
		RentAppGlobalParas.urgency_name = JSONObject.fromObject(responseData).getString("urgency_name");
		RentAppGlobalParas.urgency_phone = JSONObject.fromObject(responseData).getString("urgency_phone");
		RentAppGlobalParas.urgency_relation = JSONObject.fromObject(responseData).getString("urgency_relation");
		logMe.debug("变量work_name:" + RentAppGlobalParas.work_name);
		logMe.debug("变量work_address:" + RentAppGlobalParas.work_address);
		logMe.debug("变量certifier_name:" + RentAppGlobalParas.certifier_name);
		logMe.debug("变量certifier_phone:" + RentAppGlobalParas.certifier_phone);
		logMe.debug("变量urgency_name:" + RentAppGlobalParas.urgency_name);
		logMe.debug("变量urgency_phone:" + RentAppGlobalParas.urgency_phone);
		logMe.debug("变量urgency_relation:" + RentAppGlobalParas.urgency_relation);
		logMe.info("s_get_certificate返回值：" + responseJson);
		
		return responseJson;
	}
}
