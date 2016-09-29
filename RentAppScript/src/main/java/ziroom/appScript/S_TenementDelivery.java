package ziroom.appScript;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import com.ziroom.utils.CommonFunction;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;
import ziroom.confManagement.commonMethods.HttpRequest;

/***
 * 物业交割API
 * 
 * @author jihaibo
 *
 */
public class S_TenementDelivery {
	private static final Logger logTD = Logger.getLogger(S_TenementDelivery.class);
	HttpRequest hRequest = new HttpRequest();

	/***
	 * app端发起 --去做物业交割
	 * 
	 * @param requestUrl
	 *            http://s.ziroom.com/crm-reserve/contractPersonInfo/
	 *            haveContractjointRent
	 * 
	 * @return
	 */
	public JSONObject s_tenementDeliveryAndSteward(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("timestamp", timeStamp);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("source", RentAppGlobalParas.source);
		map.put("haveJointRent", RentAppGlobalParas.haveJointRent);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("uuid", uuid);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("sign", CommonFunction.getCrmSign(map));
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		
		
		if (responseJson != null) {
			logTD.info("s_tenementDeliveryAndSteward返回值" + responseJson);
		} else {
			logTD.info("s_tenementDeliveryAndSteward返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值" + responseJson.toString());
		return responseJson;
	}

	/***
	 * app端 查看物业交割
	 * 
	 * @param requestUrl
	 *            http://s.ziroom.com/crm-reserve/contractInfo/getContractInfo
	 * @return
	 */
	public JSONObject s_checkDelivery(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", RentAppGlobalParas.source);
		map.put("sysContractId", RentAppGlobalParas.sysContractId);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("app", RentAppGlobalParas.app);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("os", RentAppGlobalParas.os);
		map.put("network", RentAppGlobalParas.network);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("model", RentAppGlobalParas.model);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logTD.info("s_checkDelivery返回值" + responseJson);
		} else {
			logTD.info("s_checkDelivery返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值" + responseJson.toString());
		return responseJson;
	}

	/***
	 * 去交割
	 * 
	 * @param requestUrl
	 *            http://s.ziroom.com/crm-reserve/property/propertySelect
	 * @return
	 */
	public JSONObject s_goDelivery(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", RentAppGlobalParas.source);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("app", RentAppGlobalParas.app);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("os", RentAppGlobalParas.os);
		map.put("network", RentAppGlobalParas.network);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("model", RentAppGlobalParas.model);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logTD.info("s_goDelivery返回值" + responseJson);
		} else {
			logTD.info("s_goDelivery返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值" + responseJson.toString());
		return responseJson;
	}

	/***
	 * 确认 物业交割信息
	 * 
	 * @param requestUrl
	 *            http://s.ziroom.com/crm-reserve/property/propertyConfirm
	 * @return
	 */
	public JSONObject s_confirmDelivery(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", RentAppGlobalParas.source);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("app", RentAppGlobalParas.app);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("os", RentAppGlobalParas.os);
		map.put("network", RentAppGlobalParas.network);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("confirm_state", RentAppGlobalParas.confirm_state);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("model", RentAppGlobalParas.model);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logTD.info("s_confirmDelivery返回值" + responseJson);
		} else {
			logTD.info("s_confirmDelivery返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值" + responseJson.toString());
		return responseJson;
	}

	/***
	 * 获取管家
	 * 
	 * @param requestUrl
	 *            http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=
	 *            getRentAgentCode
	 * @return
	 */
	public JSONObject s_getRentAgentCode(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf10();
		String uuid = RentAppGlobalParas.appid + "_" + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("network", RentAppGlobalParas.network);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, timeStamp));
		map.put("imei", RentAppGlobalParas.imei);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", timeStamp);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("app", RentAppGlobalParas.app);
		map.put("os", RentAppGlobalParas.os);
		map.put("model", RentAppGlobalParas.model);
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		String responseData = responseJson.getString("data");
		if (responseJson != null) {
			if (responseData.equals("[]")) {
				logTD.info("s_getRentAgentCode返回data值为空");
			} else {
				RentAppGlobalParas.AgentUser = JSONObject.fromObject(responseData).getString("user_account");
				logTD.debug("user_account的值为" + RentAppGlobalParas.AgentUser);
			}
		}
		logTD.info("s_getRentAgentCode返回值" + responseJson);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值" + responseJson.toString());
		return responseJson;
	}

	/***
	 * 获取评价问题
	 * 
	 * @param requestUrl
	 *            http://es.ziroom.com/rest/evaluate/getEvaluateProblems.do
	 * @return
	 */
	public JSONObject s_getEvaluateProblems(String requestUrl) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("beEvaluatorType", RentAppGlobalParas.beEvaluatorType);
		map.put("channelCode", RentAppGlobalParas.channelCode);
		map.put("beEvaluatorId", RentAppGlobalParas.AgentUser);
		map.put("questionType", RentAppGlobalParas.questionType);
		for(Map.Entry<String, String> m:map.entrySet()) 
		{
			System.out.println(m.getKey()+":"+m.getValue());
		}
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			RentAppGlobalParas.tokenId = responseJson.getString("tokenId");
			logTD.debug("tokenId的值为" + RentAppGlobalParas.tokenId);
			logTD.info("s_getEvaluateProblems返回的值" + responseJson);
		} else {
			logTD.info("s_getEvaluateProblems返回的值------->>>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值" + responseJson.toString());
		return responseJson;
	}

	/***
	 * 提交评价问题
	 * 
	 * @param requestUrl
	 *            http://es.ziroom.com/rest/evaluate/putEvaluateContent.do
	 * @return
	 */
	public JSONObject s_putEvaluateContent(String requestUrl) {
		JSONObject msg = JSONObject.fromObject(RentAppGlobalParas.evaluateMsg);
		msg.put("orderCode", RentAppGlobalParas.contractCode);
		msg.put("requesterId", RentAppGlobalParas.login_uid);
		String newMsg=CommonFunction.jsonFieldSort(msg).replace("\"[", "[");
		String newMsg2=newMsg.toString().replace("]\"", "]");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tokenId", RentAppGlobalParas.tokenId);
		map.put("evaluateMsg",newMsg2);
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		
		if (responseJson != null) {
			logTD.info("s_putEvaluateContent返回的值" + responseJson);
		} else {
			logTD.info("s_putEvaluateContent返回的值------->>>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值" + responseJson.toString());
		return responseJson;
	}
}
