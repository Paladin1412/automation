package keeper.appScript;

import com.ziroom.httpclient.HttpClientUtils;
import com.ziroom.utils.CommonFunction;
import keeper.confManagement.commonMethods.FunctionCommon;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.HireHousePropertyConstants;
import keeper.confManagement.config.PropertyConstants;
import keeper.confManagement.config.ValuationModelPropertyConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Keeper_ValuationModelCommon {
	/**
	 * 
	 * 类说明 : M站录计价模型 作者 : Elaine 版本号 : V1.0
	 */

	HttpClientUtils hcu = new HttpClientUtils();
	FunctionCommon fc = new FunctionCommon();
	Logger logger = Logger.getLogger(Keeper_ValuationModelCommon.class);

	// 全局变量
	public String assessCode = null;
//	public String standardId = null;
	public String name = null;
	public String agentPart = null;
	public String group = null;
	public String hlUser = null;
	public String real_sh_jg = null;

	// 界面参数
	public String account = null;
	public String cityCode = null;
	public String preRoom = null;
	public String afterRoom = null;
	public String district = null;
	public String resblock = null;
//	public String orderID = null;//提审单号
	public String rentType = null;
	// 版本
	public String version = null;
	public String washRoom = null;
	public String livingRoom = null;
	public String qijushiRoom = null;
	public String diningRoom = null;
	public String houseLayout = null;
	public String bookRoom = null;


	// ==============================初始化参数================================
	/**
	 * @description: 得到管家具体信息
	 * @param json
	 *            json ： getUserDetail返回json
	 * @author Elaine
	 **/
	public void getUserInfo(JSONObject json) {
		System.out.println(json);
		JSONObject data = json.getJSONObject("data");
		name = data.getString("name");
		agentPart = data.getString("descr");
		group = data.getString("group");
		// hlUser = data.getString("hlUser");
	}

	/**
	 * @description: 从界面得到的数据
	 * @param viewJson
	 *            ViewJson ： viewJson返回json
	 * @author Elaine
	 **/
	public void initViewJson(JSONObject viewJson) {
		cityCode = viewJson.getString("citycode");
		afterRoom = viewJson.getString("afterRoomNum");
		account = viewJson.getString("account");
		preRoom = viewJson.getString("roomNum");
		rentType = viewJson.getString("houseType");
		version = viewJson.getString("productVesion");
		district = viewJson.getString("district");
		resblock = viewJson.getString("resblock");
		washRoom = viewJson.getString("washRoom");
		bookRoom = viewJson.getString("bookRoom");
		livingRoom = viewJson.getString("livingRoom");
		qijushiRoom = viewJson.getString("bedRoom");
		diningRoom = viewJson.getString("diningRoom");
		houseLayout = viewJson.getString("houseLayout");
	}


	// ==============================初始化参数结束================================

	/**
	 * @description: 登陆M站
	 * @author Elaine
	 **/
	public String mLogin(String httpUrl) {

		// 生成sign值
		String time = CommonFunction.getTimeStampOf10();
		String sign = CommonFunction.getAppSign(account, time);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("r", ValuationModelPropertyConstants.login);
		map.put("sign", sign);
		map.put("agent_part", agentPart);
		map.put("city_code", cityCode);
		map.put("user_account", account);
		map.put("agent_name", name);
		map.put("timestamp", time);
		map.put("blonger_group",group);
		JSONObject jsobj = JSONObject.fromObject(map);
//		System.out.println("Map:" + map.toString());
		// 请求接口返回值
		Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
//		System.out.println("Map:" + response.toString());

		Document returnValue = Jsoup.parse(response.get("returnValue"));
		String title = returnValue.title();
		String responseJson = response.toString();

		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(responseJson.toString());
		Reporter.log(map.toString());
		Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
		String status = returnStatusCode.body().text();
		Reporter.log("returnStatusCode : " + status);

		if (title.equals("引导页 - 计价模型")){
			logger.info("s_getRenewContractInfo返回值" + returnValue);
		} else {
			logger.info("s_getRenewContractInfo返回值------->>>>>>为空");
		}

		return responseJson;

	}

	/**
	 * @description: 得到standard_id信息
	 * @author Elaine
	 **/
	public JSONObject houseInfo(JSONObject json,String httpUrl) {

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", ValuationModelPropertyConstants.type);
		map.put("resblock_id", json.getString("villageId"));
		map.put("building_no", json.getString("buildNum"));
		map.put("unit", json.getString("unit"));
		map.put("district_id", json.getString("districtId"));
		map.put("floor", json.getString("floor"));
		map.put("room_no", json.getString("roomNum"));
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);

		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());

		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());

		return actual;
	}

	/**
	 * @description: 提交房子信息
	 * @param json
	 *            : 房源信息json串
	 * @param json
	 *            : houseinfo返回json串
	 * @author Elaine
	 **/
	public JSONObject commitHouseInfo(JSONObject json, JSONObject gethouseInfo,String httpUrl) {
		// standard_id值
		String standardIdJson = JSONArray.fromObject(gethouseInfo.getString("data")).getString(0);
		KeeperGlobalParas.standardId = JSONObject.fromObject(standardIdJson).getString("standard_id");
		System.out.println(KeeperGlobalParas.standardId);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("assess_code", "");
		map.put("city_code", cityCode);
		map.put("district_id", json.getString("districtId"));
		map.put("district", json.getString("districtName"));
		map.put("resblock", json.getString("villageName"));
		map.put("resblock_id", json.getString("villageId"));
		map.put("building_no", json.getString("buildNum"));
		map.put("unit", json.getString("unit"));
		map.put("floor", json.getString("floor"));
		map.put("room_no", json.getString("roomNum"));
		map.put("house_code_id", KeeperGlobalParas.standardId);
		map.put("is_first_floor", HireHousePropertyConstants.isTopFloor);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
//		logger.info(actual);

		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());

		return actual;
	}

	/**
	 * @description: 得到房子出房评估价格
	 * @param commitHouseInfo
	 *            : commitHouseInfo返回json串
	 * @author Elaine
	 **/
	public JSONObject getRentPrice(JSONObject commitHouseInfo,String httpUrl) {

		// assess_code值
		assessCode = JSONObject.fromObject(commitHouseInfo.getString("data")).getString("assess_code");
//		System.out.println(assessCode);
		rentType = "FZ";
		logger.info("评估单号:" + assessCode);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("assess_code", assessCode);
		map.put("pre_room", preRoom);
		map.put("after_room", afterRoom);
		// 装修程度
		map.put("decorate_type", ValuationModelPropertyConstants.decorateType);
		// 客厅面积
		map.put("saloon_area", ValuationModelPropertyConstants.saloonArea);
		// 房间面积
		map.put("room_area", ValuationModelPropertyConstants.roomArea);
		// 朝向
		map.put("orientation", "南");
		map.put("toliet", ValuationModelPropertyConstants.toliet);
		map.put("balcony", ValuationModelPropertyConstants.balcony);
		map.put("is_new_room", ValuationModelPropertyConstants.isNewRoom);
		map.put("is_shelter", ValuationModelPropertyConstants.isShelter);
		map.put("rent_type", rentType);
		map.put("version", version);
		JSONObject jsobj = JSONObject.fromObject(map);
		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
//		logger.info(actual);

		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());

		return actual;
	}

	/**
	 * @description: 提交房间
	 * @param json
	 *            : getRentPrice返回json串
	 * @author Elaine
	 **/
	public JSONObject commitRoomInfo(JSONObject json, String roomNo, String HttpUrl) {

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("assess_code", assessCode);
		map.put("room_no", roomNo);
		map.put("after_room", afterRoom);
		// 装修程度
		map.put("decorate_type", ValuationModelPropertyConstants.decorateType);
		// 客厅面积
		map.put("saloon_area", ValuationModelPropertyConstants.saloonArea);
		// 房间面积
		map.put("room_area", ValuationModelPropertyConstants.roomArea);
		// 朝向
		map.put("orientation", "南");
//		map.put("orientation", ValuationModelPropertyConstants.orientation);
		map.put("toliet", ValuationModelPropertyConstants.toliet);
		map.put("balcony", ValuationModelPropertyConstants.balcony);
		// 是否为优化间， 1是，2否
		map.put("is_new_room", ValuationModelPropertyConstants.isNewRoom);
		map.put("is_shelter", ValuationModelPropertyConstants.isShelter);
		map.put("yg", ValuationModelPropertyConstants.yg);
		map.put("shzh", ValuationModelPropertyConstants.shzh);
		map.put("shf", ValuationModelPropertyConstants.shf);
		map.put("kt", ValuationModelPropertyConstants.kt);
		map.put("1_1mch", ValuationModelPropertyConstants.mch1_1);
		map.put("1_1mchd", ValuationModelPropertyConstants.mchd1_1);
		map.put("1_5mch", ValuationModelPropertyConstants.mch1_5);
		map.put("1_5mchd", ValuationModelPropertyConstants.mchd1_5);
		map.put("1_8mch", ValuationModelPropertyConstants.mch1_8);
		map.put("1_8mchd", ValuationModelPropertyConstants.mchd1_8);
		map.put("stardard_rent_price", JSONObject.fromObject(json.getString("data")).getString("rentPrice"));
		map.put("real_rent_price", JSONObject.fromObject(json.getString("data")).getString("rentPrice"));

		JSONObject jsobj = JSONObject.fromObject(map);
		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(HttpUrl, jsobj);

		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", HttpUrl);
		actual.put("inPara", jsobj.toString());
//		logger.info(actual);

		logger.info(HttpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());

		return actual;
	}

	/**
	 * @description: 提交公共区域配置信息
	 * @author Elaine
	 **/
	public JSONObject commitConfigInfo(String httpUrl) {

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("assess_code", assessCode);
		// 冰箱
		map.put("bx", ValuationModelPropertyConstants.bx);
		// 洗衣机
		map.put("xyj", ValuationModelPropertyConstants.xyj);
		// 热水器
		map.put("rshq", ValuationModelPropertyConstants.rshq);
		// 60热水器
		map.put("60shrshq", ValuationModelPropertyConstants.rshq60);
		// 微波炉
		map.put("wbl", ValuationModelPropertyConstants.wbl);
		// 四层三角架
		map.put("scsjj", ValuationModelPropertyConstants.scsjj);
		// 衣架
		map.put("yj", ValuationModelPropertyConstants.yj);
		// 餐桌
		map.put("czh", ValuationModelPropertyConstants.czh);
		// 电子锁
		map.put("dzs", ValuationModelPropertyConstants.dzs);
		//
		map.put("zj", ValuationModelPropertyConstants.zj);
		// 隔断墙
		map.put("gdq", ValuationModelPropertyConstants.gdq);
		// 刷漆
		map.put("shq", ValuationModelPropertyConstants.shq);
		// 地板
		map.put("db", ValuationModelPropertyConstants.db);
		// 整体橱柜
		map.put("zhtchg", ValuationModelPropertyConstants.zhtchg);
		// 其他配置成本
		map.put("qtpzhchb", ValuationModelPropertyConstants.qtpzhchb);
		// 其他装修成本
		map.put("qtzhxchb", ValuationModelPropertyConstants.qtzhxchb);
		// 电路改造
		map.put("dlgz", ValuationModelPropertyConstants.dlgz);
		// 水路改造
		map.put("shlgz", ValuationModelPropertyConstants.shlgz);
		// 卧室门
		map.put("wshm", ValuationModelPropertyConstants.wshm);
		// 厨房瓷砖
		map.put("chfczh", ValuationModelPropertyConstants.chfczh);
		// 塑钢窗
		map.put("sgch", ValuationModelPropertyConstants.sgch);
		// 吊顶
		map.put("diaoding", ValuationModelPropertyConstants.diaoding);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);

		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());

		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());

		return actual;
	}

	/**
	 * @description: 得到出房价格
	 * @author Elaine
	 **/
	public JSONObject getHirePrice(String httpUrl) {
		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("assess_code", assessCode);
		map.put("pre_room", preRoom);
		map.put("after_room", afterRoom);
		map.put("public_toliet", ValuationModelPropertyConstants.toliet);
		map.put("area", ValuationModelPropertyConstants.roomArea);
		map.put("saloon_area", ValuationModelPropertyConstants.saloonArea);
		map.put("rent_type", rentType);
		map.put("version", version);
		map.put("decorate_type", ValuationModelPropertyConstants.decorateType);
		// 装修年限
		map.put("decorate_year", ValuationModelPropertyConstants.decorateYear);
		// 租期
		map.put("lease_year", ValuationModelPropertyConstants.leaseYear);
		// 租客付款方式
		map.put("owner_payment", ValuationModelPropertyConstants.ownerPayment);
		// 业主付款方式
		map.put("payment", ValuationModelPropertyConstants.payment);
		// 维修基金
		map.put("fund", ValuationModelPropertyConstants.fund);

		JSONObject jsobj = JSONObject.fromObject(map);
		System.out.println(jsobj);
		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		Reporter.log(httpUrl);
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(jsobj.toString());
		logger.info(actual.toString());

		return actual;
	}

	/**
	 * @description: 得到评估结果
	 * @author Elaine
	 **/
	public String getAssessResult() {
		String domainName = PropertyConstants.MODEPRICE_DOMAIN;
		String httpUrl = domainName + "/index.php?" + ValuationModelPropertyConstants.assessResult + assessCode;
		logger.info(httpUrl);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl);
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		Element ele = returnValue.getElementById("cksf");
		String real_sh_jg = ele.text();

		return real_sh_jg;
	}

	/**
	 * @description: 得到评估值
	 * @param json
	 *            json： getAssessResult返回json
	 * @author Elaine
	 **/
	public String getAssessValue(JSONObject json) {
		Document doc = Jsoup.parse(json.getString("returnValue"));
		Element ele = doc.getElementById("cksf");
		real_sh_jg = ele.text();
		return real_sh_jg;
	}

	/**
	 * @description: 提交评估
	 * @author Elaine
	 **/
	public JSONObject commitAssessInfo(String httpUrl) {
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("assess_code", assessCode);
		map.put("real_sh_jg", getAssessResult());
		map.put("op", ValuationModelPropertyConstants.op);
		JSONObject jsobj = JSONObject.fromObject(map);
		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		logger.info(response);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		return actual;
	}

	/**
	 * @description: 上报评估
	 * @author Elaine
	 **/
	public String assessView(String httpUrl) {
		logger.info(httpUrl);

		// 请求接口返回值
		Map<String, String> response = hcu.httpGetRequest(httpUrl);
		Document doc = Jsoup.parse(response.get("returnValue"));
		String title = doc.title();

		String responseJson = response.toString();
		Reporter.log(httpUrl);
		Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
		String status = returnStatusCode.body().text();
		Reporter.log("returnStatusCode : " + status);
		logger.info(httpUrl);
		logger.info(response);

		if (title.equals("评估详细")){
			logger.info("assessView返回值" + doc);
		} else {
			logger.info("assessView返回值------->>>>>>为空");
		}

		return responseJson;

	}

	/**
	 * @description: 得到订单的ID
	 * @author Elaine
	 **/
	public String getAssessID() {
		String sql = "Select id from mode_price.t_price_mode_assess where assess_code='" + assessCode + "'";
//		logger.info(sql);
		List<Map<String, String>> getAllData = fc.getAllDataFromMySqlData(sql, "mode_price");
//		logger.info("getAllData " + getAllData);
		String id = getAllData.get(0).get("id").toString();
		logger.info("Asscess ID" + id);
		KeeperGlobalParas.orderID = id;
		return id;
	}

	/**
	 * @description: 提交上报
	 * @author Elaine
	 **/
	public JSONObject admitAssess(String httpUrl) {

		// 请求接口返回值
		Map<String, String> response = hcu.httpGetRequest(httpUrl);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		Reporter.log(httpUrl);
		Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
		String status = returnStatusCode.body().text();
		Reporter.log("returnStatusCode : " + status);
		logger.info(httpUrl);
		logger.info(actual.toString());
		return actual;
	}
	/**
	 * 撤销计价模型
	 *http://s.ziroom.com/index.php?uri=pricemode/admit_cancel&id=124862&house_code_id=1115030424407
	 **/
	public String admitCancel(String httpUrl){
//		orderID = getAssessID();
		String HttpUrl = httpUrl + "uri=pricemode/admit_cancel&id=" + KeeperGlobalParas.orderID +"&house_code_id=" + KeeperGlobalParas.standardId;
		logger.info(HttpUrl);
		// 请求接口返回值
		Map<String, String> response = hcu.httpGetRequest(HttpUrl);
		logger.info(response);
		Document doc = Jsoup.parse(response.get("returnValue"));
		String title = doc.getElementsByClass("tq_h1title").text();

		String responseJson = response.toString();

		Reporter.log(HttpUrl);
		Reporter.log(responseJson.toString());

		if (title.equals("已撤销")){
			logger.info("admitCancel返回值" + doc);
		} else {
			logger.info("admitCancel返回值------->>>>>>为空");
		}

		return responseJson;

	}
	// ====================整租3.0===================================================
	/**
	 * @description: 登陆login.ziroom.com
	 * @author Elaine
	 **/
	public String login(String httpUrl) {

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_account", account);
		String city = null;
		if (cityCode.equals("110000")) {
			city = "bj";
		} else if (cityCode.equals("310000")) {
			city = "sh";
		} else {
			city = "sz";
		}
		map.put("city", city);
		JSONObject jsobj = JSONObject.fromObject(map);

		logger.info(account);
		Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
		Document doc = Jsoup.parse(response.get("returnValue"));
		String title = doc.getElementsByClass("bg").first().text();

		String responseJson = response.toString();
		Reporter.log(jsobj.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(doc.toString());

		if (title.equals("平台首页")){
			logger.info("login返回值" + doc);
		} else {
			logger.info("login返回值------->>>>>>为空");
		}

		return responseJson;
	}

	/**
	 * @description: 得到整租收房价格
	 * @author Elaine
	 **/
	public JSONObject getEntireHirePrice(String httpUrl) {

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uri", ValuationModelPropertyConstants.uri);
		map.put("type", ValuationModelPropertyConstants.entire_type);
		map.put("house_type", ValuationModelPropertyConstants.house_type);
		map.put("real_rent_price", ValuationModelPropertyConstants.real_rent_price);
		JSONObject jsobj = JSONObject.fromObject(map);

		Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());

		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		return actual;
	}

	/**
	 * @description: 得到standardId
	 * @param hireHouseFlow
	 *            hireHouseFlow : hireHouseFlow返回json串
	 * @author Elaine
	 **/
	public JSONObject getStandardId(JSONObject hireHouseFlow,String httpUrl) {

		// JSONObject data = hireHouseFlow.getJSONObject("data");
		String value = hireHouseFlow.getString("districtId") + "__" + hireHouseFlow.getString("villageId") + "_"
				+ hireHouseFlow.getString("buildNum") + "_" + hireHouseFlow.getString("unit") + "_"
				+ hireHouseFlow.getString("floor") + "_" + hireHouseFlow.getString("roomNum");

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uri", ValuationModelPropertyConstants.dict_uri);
		map.put("op", ValuationModelPropertyConstants.dict_op);
		map.put("type", ValuationModelPropertyConstants.dict_type);
		map.put("val", value);
		JSONObject jsobj = JSONObject.fromObject(map);

		Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());

		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		return actual;
	}

	/**
	 * @description: 保存数据
	 * @param hireHouseFlow
	 *            hireHouseFlow : 房源宝中house信息
	 * @param getEntireHirePrice
	 *            getEntireHirePrice : getEntireHirePrice返回值
	 * @author Elaine
	 **/
	public JSONObject savePriceMode(JSONObject hireHouseFlow, JSONObject getEntireHirePrice, JSONObject getStandardId , String httpUrl) {

		JSONArray doc = getStandardId.getJSONObject("response").getJSONArray("docs");
		KeeperGlobalParas.standardId = doc.getJSONObject(0).getString("standard_id");

		// 请求接口返回
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", ValuationModelPropertyConstants.save_type);
		map.put("info_type", ValuationModelPropertyConstants.info_type);

		Map<String, String> data = new HashMap<String, String>();
		data.put("assess_id", "");
		data.put("estate", resblock);
		// 修改后的房间
		JSONObject modifyRoom = new JSONObject();
		Map<String, String> room = new HashMap<String, String>();
		Map<String, String> room_one = new HashMap<String, String>();

		String is_new_room = null;
		if (houseLayout.equals("2")) {
			is_new_room = "2";
		} else {
			is_new_room = "1";
		}
		// 添加卧室
		int i = 1;
		room.put("room_area", ValuationModelPropertyConstants.roomArea);
		room.put("room_type", "ws");
//		room.put("orientation", ValuationModelPropertyConstants.orientation);
		room.put("orientation", "南");
		room.put("toliet", washRoom);
		room.put("balcony", ValuationModelPropertyConstants.room_balcony);
		room.put("is_new_room", is_new_room);
		modifyRoom.put(Integer.toString(i), JSONObject.fromObject(room));
		i++;

		// 添加书卧
		if (houseLayout.equals("2")) {
			room.put("room_area", ValuationModelPropertyConstants.roomArea);
			room.put("room_type", "sw");
			room.put("orientation", ValuationModelPropertyConstants.orientation);
			room.put("toliet", washRoom);
			room.put("balcony", ValuationModelPropertyConstants.room_balcony);
			room.put("is_new_room", is_new_room);
			modifyRoom.put(Integer.toString(i), JSONObject.fromObject(room));
		}
		// int i = 0;
		// if(houseLayout.equals("2")){
		// for (i = 1; i <= Integer.parseInt(afterRoom); i++) {
		// String room_type = null;
		// if (i == 2) {
		// room_type = "sw";
		// } else {
		// room_type = "ws";
		// }
		//
		// room.put("room_area", ValuationModelPropertyConstants.roomArea);
		// room.put("room_type", room_type);
		// room.put("orientation", ValuationModelPropertyConstants.orientation);
		// room.put("toliet", ValuationModelPropertyConstants.room_toliet);
		// room.put("balcony", ValuationModelPropertyConstants.room_balcony);
		// room.put("is_new_room", ValuationModelPropertyConstants.room_is_new);
		// modifyRoom.put(Integer.toString(i), JSONObject.fromObject(room));
		// }
		// }

		// 起居室
		if (!qijushiRoom.equals("0")) {
			for(int n = 0; n < Integer.parseInt(qijushiRoom); n++){
			room_one.put("room_area", ValuationModelPropertyConstants.saloonArea);
			room_one.put("room_type", "qjs");
			modifyRoom.put(Integer.toString(i), JSONObject.fromObject(room_one));
			i++;
			if (i == 4) {
				i = 5;
			 }
			}			
		}

		// 餐厅
		if (!diningRoom.equals("0")) {
			for(int n = 0; n < Integer.parseInt(diningRoom); n++){
			room_one.put("room_area", ValuationModelPropertyConstants.saloonArea);
			room_one.put("room_type", "ct");
			modifyRoom.put(Integer.toString(i), JSONObject.fromObject(room_one));
			i++;
			if (i == 4) {
				i = 5;
			}
		 }
		}
		// 客厅
		if (!livingRoom.equals("0")) {
			for(int n = 0; n < Integer.parseInt(livingRoom); n++){
			room_one.put("room_area", ValuationModelPropertyConstants.saloonArea);
			room_one.put("room_type", "kt");
			modifyRoom.put(Integer.toString(i), JSONObject.fromObject(room_one));
			i++;
			if (i == 4) {
				i = 5;
			}
			}
		}
		// 卫生间
		if (!washRoom.equals("0")) {
			for(int n = 0; n < Integer.parseInt(livingRoom); n++){

			room_one.put("room_area", ValuationModelPropertyConstants.saloonArea);
			room_one.put("room_type", "wsj");
			modifyRoom.put(Integer.toString(i), JSONObject.fromObject(room_one));
			i++;
			if (i == 4) {
				i = 5;
			 }
			}
		}

		data.put("house_area", ValuationModelPropertyConstants.roomArea);
		data.put("real_rent_price", ValuationModelPropertyConstants.real_rent_price);
		data.put("real_hire_house_price", getEntireHirePrice.getJSONObject("data").getString("price"));
		data.put("standard_id", KeeperGlobalParas.standardId);
		data.put("resblock_id", hireHouseFlow.getString("villageId"));
		data.put("pre_house_type", ValuationModelPropertyConstants.pre_house_type);	

		data.put("after_house_type", houseLayout);
		// 改前风格 优格，清语
		//data.put("house_style", ValuationModelPropertyConstants.house_style);
		data.put("house_style","优格");
		// 装修程度：1 老旧，2 精装,3毛坯
		data.put("decorate_type", ValuationModelPropertyConstants.decorateType);
		// 城市
		String city = null;
		if (cityCode.equals("110000")) {
			city = "北京";
		} else if (cityCode.equals("310000")) {
			city = "上海";
		} else {
			city = "深圳";
		}
		data.put("city", city);
		data.put("district", district);
		data.put("building", hireHouseFlow.getString("buildNum"));
		data.put("unit", hireHouseFlow.getString("unit"));
		data.put("floor", hireHouseFlow.getString("floor"));
		data.put("number", hireHouseFlow.getString("roomNum"));
		data.put("pre_room", preRoom);
		data.put("pre_saloon", ValuationModelPropertyConstants.after_saloon);
		data.put("pre_kitchen", ValuationModelPropertyConstants.after_kitchen);
		data.put("pre_toliet", ValuationModelPropertyConstants.after_toliet);
		data.put("after_room", is_new_room);
		data.put("after_saloon", ValuationModelPropertyConstants.after_saloon);
		data.put("after_kitchen", ValuationModelPropertyConstants.after_toliet);
		data.put("after_toliet", washRoom);
		//data.put("house_orientation", ValuationModelPropertyConstants.orientation);
		data.put("house_orientation", "南");
		// 配置标准
		data.put("config_level", ValuationModelPropertyConstants.config_level);
		data.put("payment", ValuationModelPropertyConstants.payment);
		data.put("lease_year", ValuationModelPropertyConstants.leaseYear);
		data.put("lease_month", ValuationModelPropertyConstants.lease_month);
		data.put("is_first_floor", HireHousePropertyConstants.isTopFloor);
		data.put("fund", ValuationModelPropertyConstants.fund);
		JSONObject jsobj = JSONObject.fromObject(data);
		jsobj.put("rooms", modifyRoom);
		jsobj.put("vanancyday", ValuationModelPropertyConstants.vanancyday);

		JSONObject mapJson = JSONObject.fromObject(map);
		mapJson.put("data", jsobj);

		Map<String, String> response = hcu.httpPostRequest(httpUrl, mapJson);
//		System.out.println(mapJson + "~~~~~~~~~~~");
//		System.out.println(jsobj + "~~~~~~~~~~~");
//		System.out.println(response + "~~~~~~~~~~~");
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		// logger.info(actual);

		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());

		return actual;
	}
	
	/**
	 * @description: 装修信息
	 * @author Elaine
	 **/
	public JSONObject getEntireConfigItems(String httpUrl) {

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uri", "pricemode/api");
		map.put("type", "GetEntireConfigItems");
		map.put("city_code", "110000");
		map.put("style", "优格");
		map.put("config_level", "2");
		JSONObject jsobj = JSONObject.fromObject(map);

		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
//		System.out.println(response + "~~~~~~~~~~~");
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
//		System.out.println(jsobj);
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());

		Reporter.log(httpUrl);
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());

		return actual;
	}

	/**
	 * @description: 装修信息
	 * @param savePriceMode
	 *            savePriceMode : savePriceMode返回json串
	 * @author Elaine
	 **/

	public JSONObject decorateInfo(JSONObject savePriceMode,String httpUrl) {

		assessCode = savePriceMode.getJSONObject("data").getString("assess_id");

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", ValuationModelPropertyConstants.save_type);
		map.put("info_type", "decorate");
		map.put("assess_id", assessCode);
		JSONObject jsobj = JSONObject.fromObject(map);

		// data 值
		HashMap<String, String> data = new HashMap<String, String>();
		JSONObject decorate = new JSONObject();
		JSONObject dec = new JSONObject();

		for (int i = 25; i < 77; i++) {
			if (i < 40) {
				data.put("sums", "1");
			} else {
				data.put("sums", ValuationModelPropertyConstants.sums);
			}
			data.put("item_id", Integer.toString(i));
			decorate.put(Integer.toString(i), JSONObject.fromObject(data));
			decorate.remove("70");
		}

		dec.put("decorate", decorate);
		jsobj.put("data", dec);

		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());
		return actual;
	}

	public JSONObject saveConfig(JSONObject savePriceMode,String httpUrl) {

		assessCode = savePriceMode.getJSONObject("data").getString("assess_id");

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", ValuationModelPropertyConstants.save_type);
		map.put("info_type", ValuationModelPropertyConstants.config_info_type);
		map.put("assess_id", assessCode);
		JSONObject jsobj = JSONObject.fromObject(map);

		// data 值
		HashMap<String, String> data = new HashMap<String, String>();
		HashMap<String, String> data_Three = new HashMap<String, String>();
		JSONObject configs = new JSONObject();
		JSONObject config = new JSONObject();

		for (int i = 84; i < 139; i++) {
			if(i < 90){
				data.put("sums", "1");
			}else{
				data.put("sums", ValuationModelPropertyConstants.sums);
			}
			data.put("item_id", Integer.toString(i));
			configs.put(Integer.toString(i), JSONObject.fromObject(data));
			configs.remove("118");
			configs.remove("119");
			configs.remove("109");
		}

		data_Three.put("unit_price", ValuationModelPropertyConstants.unit_price);
		data_Three.put("sums", "1");
		data_Three.put("item_id", "3");
		configs.put("3", JSONObject.fromObject(data_Three));
		config.put("config", configs);
		jsobj.put("data", config);

		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);

		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		System.out.println(actual);
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());

		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());

		return actual;
	}

	/**
	 * @description: 保存配置信息
	 * @author Elaine
	 **/
	public JSONObject saveConfigInfo(String httpUrl) {

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uri", ValuationModelPropertyConstants.uri);
		map.put("type", ValuationModelPropertyConstants.save_type);
		map.put("info_type", "config_param");
		map.put("assess_id", assessCode);
		map.put("payment_times", ValuationModelPropertyConstants.payment_times);
		JSONObject jsobj = JSONObject.fromObject(map);

		Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());

		Reporter.log(jsobj.toString());
		Reporter.log(actual.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(actual.toString());

		return actual;
	}	

	/**
	 * @description: 更新审核状态
	 * @author Elaine
	 **/
	public void updateAuditStatus() {
		String sql = "update t_price_mode_assess set audit_status=2 where house_code_id='" + KeeperGlobalParas.standardId + "'";
		fc.updateMySqlData(sql, "mode_price");
//		System.out.println("ss");

	}

	/**
	 * @description: 保存配置信息
	 * @author Elaine
	 **/
	public String admitPriceMode(String httpUrl) {

		// 请求接口返回值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uri", "pricemode/admit");
		map.put("id", assessCode);
		map.put("house_code_id", KeeperGlobalParas.standardId);
		JSONObject jsobj = JSONObject.fromObject(map);

		Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
		Document doc = Jsoup.parse(response.get("returnValue"));
		String title = doc.getElementsByClass("tq_h1title").text();

		String responseJson = response.toString();
		KeeperGlobalParas.orderID = assessCode;
		Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));

		Reporter.log(jsobj.toString());
		Reporter.log(returnStatusCode.toString());
		logger.info(httpUrl);
		logger.info(jsobj.toString());
		logger.info(responseJson.toString());

		if (title.equals("提交成功!")) {
			logger.info("assessView返回值" + doc);
		} else {
			logger.info("assessView返回值------->>>>>>为空");
		}

		return responseJson;

	}

	/**
	 * @description: 得到订单的号
	 *            : 评价code
	 * @author Elaine
	 **/
	public String getAssessCode() {
		String sql = "Select assess_code from t_price_mode_assess where house_code_id='" + KeeperGlobalParas.standardId + "'";
		List<Map<String, String>> getAllData = fc.getAllDataFromMySqlData(sql, "mode_price");
		String code = getAllData.get(0).get("assess_code").toString();
		logger.info("Asscess code" + code);
		return code;
	}
}
