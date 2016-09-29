package ziroom.appScript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import com.ziroom.utils.CommonFunction;
import net.sf.json.JSONObject;
import ziroom.confManagement.commonMethods.HttpRequest;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;

public class S_NewSign {
	private static final Logger logNS = Logger.getLogger(S_NewSign.class);
	HttpRequest hRequest = new HttpRequest();

	/***@
	 * 合租  找房
	 * http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=
	 * get_bizcircleList_new
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_wholeRent1(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf10();
		String sign = CommonFunction.getAppSign(RentAppGlobalParas.city_code, timeStamp);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", timeStamp);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("sign", sign);
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			logNS.info("s_wholeRent1返回值" + responseJson);
		} else {
			logNS.info("s_wholeRent1返回值------->>>>>>为空");
		}

		return responseJson;
	}

	/***@
	 * 合租 合租搜房
	 * url：http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=searchHouse
	 *
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_wholeRent2(String requestUrl) {
		StackTraceElement ste = (new Throwable()).getStackTrace()[1];
		String classAndMethod = (ste.getClassName() + "->" + ste.getMethodName());
		String timeStamp = CommonFunction.getTimeStampOf10();
		String sign = CommonFunction.getAppSign(RentAppGlobalParas.login_uid, timeStamp);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("is_reserve", RentAppGlobalParas.is_reserve);
		map.put("max_lng", RentAppGlobalParas.max_lng);
		map.put("sort", RentAppGlobalParas.sort);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("can_sign", RentAppGlobalParas.can_sign);
		map.put("min_area", RentAppGlobalParas.min_area);
		map.put("min_lat", RentAppGlobalParas.min_lat);
		map.put("timestamp", timeStamp);
		map.put("house_tags[1]", RentAppGlobalParas.house_tags1);
		map.put("max_area", RentAppGlobalParas.max_area);
		map.put("bizcircle_code", RentAppGlobalParas.bizcircle_code);
		map.put("house_type", RentAppGlobalParas.detailHouseType);
		map.put("max_lat", RentAppGlobalParas.max_lat);
		map.put("house_tags[3]", RentAppGlobalParas.house_tag3);
		map.put("length", RentAppGlobalParas.length);
		map.put("huxing", RentAppGlobalParas.huxing);
		map.put("house_tags[5]", RentAppGlobalParas.house_tag5);
		map.put("min_lng", RentAppGlobalParas.min_lng);
		map.put("keywords", RentAppGlobalParas.keywords);
		map.put("style_codes", RentAppGlobalParas.style_codes);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("style_code", RentAppGlobalParas.style_code);
		map.put("version_id", RentAppGlobalParas.version_id);
		map.put("min_rentfee", RentAppGlobalParas.min_rentfee);
		map.put("subway_station_name", RentAppGlobalParas.subway_station_name);
		map.put("sign", sign);
		map.put("house_tags[2]", RentAppGlobalParas.house_tags2);
		map.put("house_tags[0]", RentAppGlobalParas.house_tags0);
		map.put("max_rentfee", RentAppGlobalParas.max_rentfee);
		map.put("house_tags[4]", RentAppGlobalParas.house_tags4);
		map.put("start", RentAppGlobalParas.start);
		map.put("heating", RentAppGlobalParas.heating);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("house_tags[6]", RentAppGlobalParas.house_tags6);
		map.put("network", RentAppGlobalParas.network);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("model", RentAppGlobalParas.model);

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			// 截取数据中收尾[]
			if (responseData.equals("[]")) {
				logNS.info("s_wholeRent2中data数据为空");
			} else {
				String sub_responseData = responseData.substring(1, responseData.length() - 1);

				ArrayList<String> arryData = CommonFunction.stringToList(sub_responseData);
				// 随机获取arraylist索引
				Random random = new Random();
				int num = random.nextInt(arryData.size());
				String SingleJson = arryData.get(num);
				// 定值整租house_code
				// GlobalParameter.house_code="36954";
				// 动态获取整租house_code
				//RentAppGlobalParas.house_id = JSONObject.fromObject(SingleJson).getString("house_id");
				RentAppGlobalParas.house_code = JSONObject.fromObject(SingleJson).getString("house_code");
				//RentAppGlobalParas.detailHouseType = JSONObject.fromObject(SingleJson).getString("house_type");
				// GlobalParameter.house_type=JSONObject.fromObject(SingleJson).getString("house_type");
				// GlobalParameter.housePrice=JSONObject.fromObject(SingleJson).getString("house_price");
				logNS.debug(classAndMethod + "house_code值为：" + RentAppGlobalParas.house_code);
				logNS.debug(classAndMethod + "house_id值为：" + RentAppGlobalParas.house_id);

			}

			logNS.info("s_wholeRent2返回值" + responseJson);
		} else {
			logNS.info("s_wholeRent2服务器返回值------>>>>>为空");
		}

		return responseJson;
	}

	
	/***@
	 * 合租 确定个人信息1
	 * http://interfaces.ziroom.com/index.php?_p=api_newsign&_a=user_info_update
	 * 
	 * @author jihaibo
	 * @return JSONObject
	 */
	public JSONObject s_okinfo1(String requestUrl) {
		String timestamp = CommonFunction.getTimeStampOf10();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("cert_type", RentAppGlobalParas.cert_type);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, timestamp));
		map.put("timestamp", timestamp);
		map.put("user_cert2", RentAppGlobalParas.user_cert2);
		map.put("user_cert3", RentAppGlobalParas.user_cert3);
		map.put("user_name", RentAppGlobalParas.person_name);
		map.put("user_cert1", RentAppGlobalParas.user_cert1);
		map.put("cert_num", RentAppGlobalParas.cert_num);
		map.put("house_type", RentAppGlobalParas.ReturnHouseType);
		map.put("user_phone", RentAppGlobalParas.user_phone);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("user_sex", RentAppGlobalParas.user_sex);

		// 返回内容格式化为json
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			logNS.info("s_okinfo1返回值：" + responseJson);
		} else {
			logNS.info("s_okinfo1服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;
	}

	/***@
	 * 合租 确定个人信息2
	 * http://interfaces.ziroom.com/index.php?_p=api_newsign&_a=get_ra_config
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_okinfo2(String requestUrl) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10()));
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", CommonFunction.getTimeStampOf10());
		map.put("city_code", RentAppGlobalParas.city_code);
		// 返回内容格式化为json
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			logNS.info("s_okinfo2返回值：" + responseJson);
		} else {
			logNS.info("s_okinfo2服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;

	}

	/***@
	 * 合租 确定个人信息3<请求资质信息>
	 * 
	 * @param requestUrl
	 * @return
	 */
	public JSONObject s_okinfo3(String requestUrl) {

		String SuffixUrl = "/index.php?_p=api_newsign&_a=select_extend_info&uid=" + RentAppGlobalParas.login_uid + "&"
				+ "sign=" + CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10())
				+ "&timestamp=" + CommonFunction.getTimeStampOf10() + "";
		String httpUrl = requestUrl + SuffixUrl;
		JSONObject responseJson = hRequest.getGetReturn(httpUrl);
		Reporter.log(httpUrl);
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			logNS.info("s_okinfo3返回值：" + responseJson);
		} else {
			logNS.info("s_okinfo3服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;
	}

	/***@
	 * 合租 我承诺1
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * 
	 * @return
	 */
	public JSONObject s_promiseMyself1(String requestUrl) {
		RentAppGlobalParas.certifier_phone = "16788889098";
		RentAppGlobalParas.work_address = "朝阳区三里屯soho";
		RentAppGlobalParas.urgency_phone = "12909998765";
		RentAppGlobalParas.certifier_name = "杨小白";
		RentAppGlobalParas.urgency_relation = "同学";
		RentAppGlobalParas.work_name = "清华大学海淀区";
		RentAppGlobalParas.urgency_name = "黄小灵";
		// 获取资质信息
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("is_newsign", RentAppGlobalParas.isRenew);
		map.put("certifier_phone", RentAppGlobalParas.certifier_phone);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10()));
		map.put("timestamp", CommonFunction.getTimeStampOf10());
		map.put("work_address", RentAppGlobalParas.work_address);
		map.put("house_type", RentAppGlobalParas.ReturnHouseType);
		map.put("urgency_phone", RentAppGlobalParas.urgency_phone);
		map.put("certifier_name", RentAppGlobalParas.certifier_name);
		map.put("house_code", RentAppGlobalParas.house_code);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("urgency_relation", RentAppGlobalParas.urgency_relation);
		map.put("work_name", RentAppGlobalParas.work_name);
		map.put("house_id", RentAppGlobalParas.house_id);
		map.put("urgency_name", RentAppGlobalParas.urgency_name);

		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			logNS.info("s_promiseMyself1返回值：" + responseJson);
		} else {
			logNS.info("s_promiseMyself1服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;

		// http://s.ziroom.com/crm/common/createAppId?appType=2&imei=352248061569009
	}

	/***@
	 * 合租 我承诺2
	 * 
	 * @param requestUrl
	 * @param
	 * @return JSONObject
	 */

	public JSONObject s_promiseMyself2(String requestUrl) {
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + timestamp;
		// System.out.println("显示的值"+GlobalParameter.detailHouseType);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("timestamp", timestamp);
		map.put("source", RentAppGlobalParas.source);
		map.put("houseType", RentAppGlobalParas.ReturnHouseType);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("houseCode", RentAppGlobalParas.house_code);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("houseId", RentAppGlobalParas.house_id);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("network", RentAppGlobalParas.network);
		String sign = CommonFunction.getCrmSign(map);
		map.put("sign", sign);

		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);

		String responseData = "";
		if (responseJson != null) {

			responseData = responseJson.getString("data");
			String sub_responseData = responseData.substring(1, responseData.length() - 1);
			if (responseData.equals("[]")) {
				logNS.info("s_promiseMyself2返回data值为空");
			} else {
				RentAppGlobalParas.signDate = JSONObject.fromObject(sub_responseData).getString("signDate");
				RentAppGlobalParas.stopDate = JSONObject.fromObject(sub_responseData).getString("stopDate");
				RentAppGlobalParas.price = JSONObject.fromObject(sub_responseData).getString("price");
				RentAppGlobalParas.priceUnit = JSONObject.fromObject(sub_responseData).getString("priceUnit");
				RentAppGlobalParas.tenancyType = JSONObject.fromObject(sub_responseData).getString("tenancyType");
				RentAppGlobalParas.isShort = JSONObject.fromObject(sub_responseData).getString("isShort");
			}
			logNS.debug("变量signDate：" + RentAppGlobalParas.signDate);
			logNS.debug("变量stopDate：" + RentAppGlobalParas.stopDate);
			logNS.debug("变量price：" + RentAppGlobalParas.price);
			logNS.debug("变量priceUnit：" + RentAppGlobalParas.priceUnit);
			logNS.debug("变量tenancyType：" + RentAppGlobalParas.tenancyType);
			logNS.debug("变量isShort：" + RentAppGlobalParas.isShort);
			logNS.info("s_promiseMyself2返回值：" + responseJson);

		} else {
			logNS.info("s_promiseMyself2返回值----->>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;
	}

	/***@
	 * 合租 确认付款方式1
	 * http://s.ziroom.com/crm-reserve/contractInfo/submitContractInfo
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @param mapParameter
	 * 
	 * @return JSONObject
	 */
	public JSONObject s_okPayType1(String requestUrl) {

		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("stopDate", RentAppGlobalParas.stopDate);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("tenancyType", RentAppGlobalParas.tenancyType);
		map.put("payment", RentAppGlobalParas.payment);
		map.put("isShort", RentAppGlobalParas.isShort);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("priceUnit", RentAppGlobalParas.priceUnit);
		map.put("timestamp", time);
		map.put("price", RentAppGlobalParas.price);
		map.put("signDate", RentAppGlobalParas.signDate);
		map.put("source", RentAppGlobalParas.source);
		map.put("houseType", RentAppGlobalParas.ReturnHouseType);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("isBlank", RentAppGlobalParas.isBlank);// 定值
		map.put("houseCode", RentAppGlobalParas.house_code);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("houseId", RentAppGlobalParas.house_id);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("model", RentAppGlobalParas.model);
		map.put("network", RentAppGlobalParas.network);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);

		String sign = CommonFunction.getCrmSign(map);
		map.put("sign", sign);
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);

		// 请求接口返回值
		if (responseJson != null) {
			RentAppGlobalParas.contractCode = responseJson.getString("data");
			logNS.debug("变量contractCode：" + RentAppGlobalParas.contractCode);
			logNS.info("s_okPayType1返回值" + responseJson);

		} else {
			logNS.info("s_okPayType1返回值------->>>>>为空");

		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;

	}

	/***@
	 * 合租 确认付款方式2 http://s.ziroom.com/crm-reserve/contractInfo/clauseSelect
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_okPayType2(String requestUrl) {
		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("timestamp", time);
		map.put("source", RentAppGlobalParas.source);
		map.put("house_type", RentAppGlobalParas.ReturnHouseType);
		map.put("house_code", RentAppGlobalParas.house_code);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("house_id", RentAppGlobalParas.house_id);
		map.put("appType", RentAppGlobalParas.appType);
		String sign = CommonFunction.getCrmSign(map);
		map.put("sign", sign);
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			logNS.info("s_okPayType2返回值：" + responseJson);
		} else {
			logNS.info("s_okPayType2服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;
	}

	/***@
	 * 合租 我同意上述合同条款1
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_agreeContractTerms1(String requestUrl) {

		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("isRenew", RentAppGlobalParas.isRenew);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("timestamp", time);
		map.put("source", RentAppGlobalParas.source);
		map.put("houseType", RentAppGlobalParas.ReturnHouseType);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("houseCode", RentAppGlobalParas.house_code);
		map.put("houseId", RentAppGlobalParas.house_id);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("sign", CommonFunction.getCrmSign(map));

		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);

		if (responseJson != null) {
			String responseData = responseJson.getString("data");

			if (responseData.equals("[]")) {
				logNS.info("s_agreeContractTerms1返回data值为空");

			} else {
				RentAppGlobalParas.commission = JSONObject.fromObject(responseData).getString("commission");
				RentAppGlobalParas.deposit = JSONObject.fromObject(responseData).getString("deposit");
				RentAppGlobalParas.reserveDeposit = JSONObject.fromObject(responseData).getString("reserveDeposit");
				RentAppGlobalParas.housePrice = JSONObject.fromObject(responseData).getString("housePrice");

			}

			logNS.debug("变量commission" + RentAppGlobalParas.commission);
			logNS.debug("变量deposit" + RentAppGlobalParas.deposit);
			logNS.debug("变量reserveDeposit" + RentAppGlobalParas.reserveDeposit);
			logNS.debug("变量housePrice" + RentAppGlobalParas.housePrice);
			logNS.info("s_agreeContractTerms1返回值" + responseJson);

		} else {
			logNS.info("服务器返回值------------>>>>>>>为空");

		}
		Reporter.log(responseJson.toString());
		return responseJson;

	}

	/***@
	 * 合租 我同意上述合同条款2 http://s.ziroom.com/crm-reserve/contractInfo/getActivity
	 * 
	 * @param requestUrl
	 * @return
	 */
	public JSONObject s_agreeContractTerms2(String requestUrl) {
		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("isRenew", RentAppGlobalParas.isRenew);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("timestamp", time);
		map.put("source", RentAppGlobalParas.source);
		map.put("houseType", RentAppGlobalParas.ReturnHouseType);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("houseCode", RentAppGlobalParas.house_code);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("houseId", RentAppGlobalParas.house_id);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);

		if (responseJson != null) {
			logNS.info("s_agreeContractTerms2返回值" + responseJson);

		} else {
			logNS.info("s_agreeContractTerms2返回值------->>>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;
	}

	/***@
	 * 合租 确认合同 http://s.ziroom.com/crm-reserve/contractInfo/contractSummary
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_okContract(String requestUrl) {
		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("timestamp", time);
		map.put("source", RentAppGlobalParas.source);
		map.put("houseType", RentAppGlobalParas.ReturnHouseType);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("houseCode", RentAppGlobalParas.house_code);
		map.put("houseId", RentAppGlobalParas.house_id);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

		if (responseJson != null) {
			logNS.info("s_okContract返回值" + responseJson);
			Reporter.log("返回值：" + responseJson);
		} else {
			logNS.info("s_okContract返回值------->>>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + reportlog);
		return responseJson;

	}

	/***@
	 * 合租 我确认以上信息1
	 * http://interfaces.ziroom.com/index.php?_p=api_newsign&_a=get_ra_sign_info
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_okAboveInfo1(String requestUrl) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10()));
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", CommonFunction.getTimeStampOf10());
		map.put("house_type", RentAppGlobalParas.ReturnHouseType);
		map.put("house_code", RentAppGlobalParas.house_code);
		map.put("is_renew", RentAppGlobalParas.isRenew);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("house_id", RentAppGlobalParas.house_id);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

		Reporter.log(map.toString());

		if (responseJson != null) {
			logNS.info("s_okAboveInfo1返回值" + responseJson);
		} else {
			logNS.info("s_okAboveInfo1返回值------->>>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;
	}

	/***@
	 * 合租 我确认以上信息2
	 * http://interfaces.ziroom.com/index.php?_p=api_newsign&_a=set_ra_signature
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_okAboveInfo2(String requestUrl) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10()));
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", CommonFunction.getTimeStampOf10());
		map.put("house_type", RentAppGlobalParas.ReturnHouseType);
		map.put("house_code", RentAppGlobalParas.house_code);
		map.put("is_renew", RentAppGlobalParas.isRenew);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("house_id", RentAppGlobalParas.house_id);
		map.put("password", RentAppGlobalParas.passWord);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

		Reporter.log(map.toString());

		if (responseJson != null) {
			logNS.info("s_okAboveInfo2返回值" + responseJson);
		} else {
			logNS.info("s_okAboveInfo2返回值------->>>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;
	}

	/***@
	 * 合租 验证码确认
	 * 
	 * @param request
	 * @return
	 */
	public JSONObject s_verificationOk(String request) {
		// String httpUrl =
		// "http://s.ziroom.com/crm-reserve/contractInfo/confirmContract";

		// 房子单价

		double price = Double
				.parseDouble(RentAppGlobalParas.housePrice == null ? "0.0" : RentAppGlobalParas.housePrice);
		// 租N个月押金1个月
		int months = Integer.parseInt(RentAppGlobalParas.payment == null ? "0" : RentAppGlobalParas.payment) + 1;
		// 服务费
		double commission = Double
				.parseDouble(RentAppGlobalParas.commission == null ? "0.0" : RentAppGlobalParas.commission);
		if (commission>5000) {
			commission=5000;
		}
		double reserveDeposit = Double
				.parseDouble(RentAppGlobalParas.reserveDeposit == null ? "0" : RentAppGlobalParas.reserveDeposit);
		double countMoney = (price * months) + commission - reserveDeposit;

		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("model", RentAppGlobalParas.model);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("isRenew", RentAppGlobalParas.isRenew);
		map.put("network", RentAppGlobalParas.network);
		map.put("timestamp", time);
		map.put("verificationCode", "1234567890");
		map.put("activities", "");
		map.put("houseCode", RentAppGlobalParas.house_code);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("countMoney", String.valueOf(countMoney));
		map.put("imei", RentAppGlobalParas.imei);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("source", RentAppGlobalParas.source);
		map.put("houseType", RentAppGlobalParas.ReturnHouseType);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("houseId", RentAppGlobalParas.house_id);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("sign", CommonFunction.getCrmSign(map));
		for(Map.Entry<String, String> m:map.entrySet()) 
		{
			System.out.println(m.getKey()+":"+m.getValue());
		}
		JSONObject responseJson = hRequest.getPostReturnValue(request, map);

		Reporter.log(map.toString());

		if (responseJson != null) {
			logNS.info("s_verificationOk返回值" + responseJson);
		} else {
			logNS.info("s_verificationOk返回值------->>>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;

	}

	/***@
	 * 合租 确认验证码后支付信息 http://s.ziroom.com/crm-reserve/contractInfo/getPayInfo
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_getPayInfo(String requestUrl) {
		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("timestamp", time);
		map.put("source", RentAppGlobalParas.source);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("uuid", uuid);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		if (responseJson != null) {
			String responseData = responseJson.getString("data");

			if (responseData.equals("[]")) {
				logNS.info("s_fistPayMent返回data值为空");

			} else {
				String countMoney = JSONObject.fromObject(responseData).getString("shouldPrice");
				RentAppGlobalParas.balance = Double.valueOf(countMoney) * 100;

			}
			logNS.info("s_getPayInfo返回值" + responseJson);
		} else {
			logNS.info("s_getPayInfo返回值------->>>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;
	}

	/***@
	 * 合租 自如钱包支付 http://s.ziroom.com/crm-reserve//payment/firstPayment
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_fistPayMent(String requestUrl) {
		String time = CommonFunction.getTimeStampOf13();
		String uuid = RentAppGlobalParas.appid + "_" + time;
		String balanceStr = RentAppGlobalParas.balance.toString();
		String balance = balanceStr.substring(0, balanceStr.lastIndexOf("."));

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("couponCostValue", RentAppGlobalParas.couponCostValue);
		map.put("orderType", RentAppGlobalParas.orderType);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("couponCode", RentAppGlobalParas.couponCode);
		map.put("mac", RentAppGlobalParas.mac);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("couponValue", RentAppGlobalParas.couponValue);
		map.put("timestamp", time);
		map.put("paySource", RentAppGlobalParas.paySource);
		map.put("balance", balance);
		map.put("source", RentAppGlobalParas.source);
		map.put("money", RentAppGlobalParas.remainMoney);
		map.put("uuid", uuid);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		if (responseJson != null) {

			logNS.info("s_fistPayMent返回值" + responseJson);
		} else {
			logNS.info("s_fistPayMent返回值------->>>>>>为空");
		}
		String reportlog = responseJson.toString();
		Reporter.log("返回值：" + reportlog);
		return responseJson;
	}

	
	/***@
	 * 合租单签请求<请求用户信息>
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return #JSONObject
	 */
	public JSONObject s_oneSign(String requestUrl) {
		JSONObject responseJson = hRequest.getGetReturn(requestUrl);
		Reporter.log(requestUrl);
		Reporter.log("返回值：" + responseJson);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logNS.info("变量person_name,user_cert2,user_cert3,user_cert1,cert_num,cert_type,user_sex,user_phone值为空");
			} else {

				RentAppGlobalParas.person_name = JSONObject.fromObject(responseData).getString("user_name");
				RentAppGlobalParas.user_cert2 = JSONObject.fromObject(responseData).getString("user_cert2");
				RentAppGlobalParas.user_cert3 = JSONObject.fromObject(responseData).getString("user_cert3");
				RentAppGlobalParas.user_cert1 = JSONObject.fromObject(responseData).getString("user_cert1");
				RentAppGlobalParas.cert_num = JSONObject.fromObject(responseData).getString("cert_num");
				RentAppGlobalParas.cert_type = JSONObject.fromObject(responseData).getString("cert_type");
				RentAppGlobalParas.user_sex = JSONObject.fromObject(responseData).getString("user_sex");
				RentAppGlobalParas.user_phone = JSONObject.fromObject(responseData).getString("user_phone");
				logNS.debug("变量person_name：" + RentAppGlobalParas.person_name);
				logNS.debug("变量user_cert2：" + RentAppGlobalParas.user_cert2);
				logNS.debug("变量user_cert3：" + RentAppGlobalParas.user_cert3);
				logNS.debug("变量user_cert1：" + RentAppGlobalParas.user_cert1);
				logNS.debug("变量cert_num：" + RentAppGlobalParas.cert_num);
				logNS.debug("变量cert_type：" + RentAppGlobalParas.cert_type);
				logNS.debug("变量user_sex：" + RentAppGlobalParas.user_sex);
				logNS.debug("变量user_phone：" + RentAppGlobalParas.user_phone);
				logNS.info("s_oneSign返回值：" + responseJson);
			}
		} else {
			logNS.info("s_oneSign服务器返回值-------------->>>>>>>>>>为空");
		}

		return responseJson;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 整租

	/****
	 * 整租搜房(大列表)
	 * http://interfaces.zz.qa.ziroom.com//zz/house/list.json?
	 * @param requestUrl
	 * @return
	 */
	public JSONObject s_ZSearchHouse(String requestUrl) {
		String time=CommonFunction.getTimeStampOf10();
		Map<String, String> map=new HashMap<String, String>();
		map.put("face", RentAppGlobalParas.face);
		map.put("size", RentAppGlobalParas.size);
		map.put("style", RentAppGlobalParas.style);
		map.put("timestamp", time);
		map.put("os", RentAppGlobalParas.os);
		map.put("network", RentAppGlobalParas.network);
		map.put("sign_open", RentAppGlobalParas.signOpen);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("configs", RentAppGlobalParas.configs);
		map.put("price", RentAppGlobalParas.price);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("bedroom", RentAppGlobalParas.bedroom);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("tags", RentAppGlobalParas.tags);
		map.put("sort", RentAppGlobalParas.sort);
		map.put("page", RentAppGlobalParas.page);
		map.put("model", RentAppGlobalParas.model);
		map.put("sign",CommonFunction.GetZzSign(map));
		
		String url=requestUrl+CommonFunction.spliceUrl(map);
		JSONObject responseJson = hRequest.getGetReturn(url);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			// 截取数据中收尾[]
			if (responseData.equals("[]")) {
				logNS.info("s_ZSearchHouse中data数据为空");
			} else {
				String sub_responseData = responseData.substring(1, responseData.length() - 1);
				ArrayList<String> arryData = CommonFunction.stringToList(sub_responseData);
				Random random = new Random();
				int num = random.nextInt(arryData.size());
				String SingleJson = arryData.get(num);
				RentAppGlobalParas.house_number = JSONObject.fromObject(SingleJson).getString("house_number");
				
			}
			logNS.info("s_ZSearchHouse返回值"+responseJson);
		}
		else {
			logNS.info("s_ZSearchHouse返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值："+responseJson);
		return responseJson;
	}
	
	/****
	 * 整租搜房(请求单个房源)
	 * http://interfaces.zz.qa.ziroom.com/zz/house/detail.json?
	 * @param requestUrl
	 * @return
	 */
	public JSONObject s_ZSearchHouseDetail(String requestUrl) {
		String time=CommonFunction.getTimeStampOf10();
		Map<String, String> map=new HashMap<String, String>();
		map.put("network", RentAppGlobalParas.network);
		map.put("sign_open", RentAppGlobalParas.signOpen);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("house_number", RentAppGlobalParas.house_number);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", time);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("os", RentAppGlobalParas.os);
		map.put("model", RentAppGlobalParas.model);
		map.put("sign",CommonFunction.GetZzSign(map));
		String url=requestUrl+CommonFunction.spliceUrl(map);
		JSONObject responseJson = hRequest.getGetReturn(url);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			RentAppGlobalParas.house_id = JSONObject.fromObject(responseData).getString("house_id");
			RentAppGlobalParas.house_code = JSONObject.fromObject(responseData).getString("house_code");
			RentAppGlobalParas.ReturnHouseType=JSONObject.fromObject(responseData).getString("house_type");
			RentAppGlobalParas.housePrice=JSONObject.fromObject(responseData).getString("house_price");	
			logNS.info("s_ZSearchHouseDetail返回值"+responseJson);
		}
		else {
			logNS.info("s_ZSearchHouseDetail返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值："+responseJson);
		return responseJson;
	}
	/****
	 * 整租搜房(推销此房源)
	 * http://interfaces.zz.qa.ziroom.com//zz/house/promotion.json?
	 * @param requestUrl
	 * @return
	 */
	public JSONObject s_ZSearchHousePromotion(String requestUrl) {
		String time=CommonFunction.getTimeStampOf10();
		Map<String, String> map=new HashMap<String, String>();
		map.put("size", RentAppGlobalParas.size);
		map.put("house_number", RentAppGlobalParas.house_number);
		map.put("timestamp", time);
		map.put("os", RentAppGlobalParas.os);
		map.put("network", RentAppGlobalParas.network);
		map.put("sign_open", RentAppGlobalParas.signOpen);
		map.put("app_version", RentAppGlobalParas.appVersionStr);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("page", RentAppGlobalParas.page);
		map.put("model", RentAppGlobalParas.model);
		map.put("sign",CommonFunction.GetZzSign(map));
		String url=requestUrl+CommonFunction.spliceUrl(map);
		JSONObject responseJson = hRequest.getGetReturn(url);
		if (responseJson != null) {
			logNS.info("s_ZSearchHousePromotion返回值" + responseJson);
		} else {
			logNS.info("s_ZSearchHousePromotion返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值："+responseJson);
		return responseJson;
	}
	
}
