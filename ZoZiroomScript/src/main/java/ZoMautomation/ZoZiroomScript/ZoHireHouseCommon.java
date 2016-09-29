package ZoMautomation.ZoZiroomScript;

import ZoMautomation.confManagement.commonMethods.FunctionCommon;
import ZoMautomation.confManagement.config.PropertyConstants;
import ZoMautomation.confManagement.config.ZoHireHousePC;
import com.ziroom.httpclient.HttpClientUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZoHireHouseCommon {
	// 公共参数初始化
	public String cityCode = null;
	public String keeperId = null;
	public String account = null;
	public String standardID = null;
	public String mobile = null;
	public String afterRoom = null;
	public String roomNumber = null;
	public String contractCode = null;
	public String washRoom = null;
	public String houseLayout = null;
	public String is_whole = null;
	public String productVesion = null;
	public String hireContractId = null;
	public String district = null;
	public String productCode = null; 
	public String accountId = null;// 直收管家

	HttpClientUtils hcu = new HttpClientUtils();
	FunctionCommon fc = new FunctionCommon();
	Logger logger = Logger.getLogger(ZoHireHouseCommon.class);

	// ==============================初始化参数================================
	/**
	 * @description: 得到管家具体信息
	 * @param
	 *            json ： getUserDetail返回json
	 * @author Elaine
	 **/
	public void getUserInfo(JSONObject json) {
		JSONObject data = json.getJSONObject("data");
		int index = data.getString("email").lastIndexOf("@");
		keeperId = data.getString("email").substring(0, index);
		account = data.getString("emplid");
	}

	/**
	 * @description: 从界面得到的数据
	 * @param
	 *             ： viewJson返回json
	 * @author Elaine
	 **/
	public void initViewJson(JSONObject viewJson) {
		cityCode = viewJson.getString("citycode");
		mobile = viewJson.getString("usermobile");
		houseLayout = viewJson.getString("houseLayout");
		productVesion = viewJson.getString("productVesion");
		if (productVesion.equals("1009") || productVesion.equals("1007")) {
			is_whole = "1";
			productCode = "1002";
		} else {
			is_whole = "0";
			productCode = "1001";
		}
		if (houseLayout.equals("2")) {
			afterRoom = "2";
			washRoom = viewJson.getString("washRoom");
		} else if (houseLayout.equals("1") || houseLayout.equals("0")) {
			afterRoom = "1";
			washRoom = viewJson.getString("washRoom");
		} else {
			afterRoom = viewJson.getString("afterRoomNum");
			washRoom = ZoHireHousePC.disposeToiletAmount;
		}
		roomNumber = viewJson.getString("roomNum");
		district = viewJson.getString("district");

	}
	// ==============================初始化参数结束================================

	/**
	 * @description: 登陆
	 * @author Elaine
	 **/
	public boolean zoLogin() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.zoLogin;
		String httpUrl = domainName + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", keeperId);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		Element ele = returnValue.getElementById("queryBusOppList");
		boolean flag = false;
		if (!ele.equals(null) || !ele.equals("")) {
			flag = true;
			return flag;
		}

		return flag;
	}

	/**
	 * @description: 获取计价模型
	 * @author Elaine
	 **/
	public JSONObject hireNewSign(JSONObject json) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.hireNewSign;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 得到standID
		JSONObject data = json.getJSONObject("data");
		standardID = data.getString("standardID");
		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("houseCodeId", standardID);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 获取业主信息
	 * @author Elaine
	 **/
	public JSONObject queryCustomerInfo() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.queryCustomerInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mobile", mobile);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		HashMap<String, String> mapJson = new HashMap<String, String>();
		HashMap<String, String> data = new HashMap<String, String>();
		if (actual.toString().contains("无此用户信息")) {
			mapJson.put("error_code", "0");
			mapJson.put("error_message", "成功");
			mapJson.put("status", "success");

			data.put("mobile", mobile);
			data.put("chName", "测试" + FunctionCommon.getRandomNum(50, 1));
			data.put("paperType", "身份证");
			data.put("paperCode", "130281198704090026");
			data.put("email", "bb@ziroom.com");
			data.put("address", "beijing");

			JSONObject para = JSONObject.fromObject(mapJson);
			JSONObject data_da = JSONObject.fromObject(data);
			para.put("data", data_da);
			actual = para;
		}
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 获取计价模型信息
	 * @author Elaine
	 **/
	public JSONObject getHouseAssessInfo() {
		String domainName = PropertyConstants.PHP_CRM_DOMAIN;
		String suffixUrl = "/index.php";
		String httpUrl = domainName + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uri", "pricemode/getHouseAssessInfo");
		map.put("house_code_id", standardID);
		map.put("is_entire", is_whole);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 保存
	 * @JSONObject hireNewSign返回的json串
	 * @JSONObject customerInfo返回的json串
	 * @author Elaine
	 **/
	public JSONObject saveHireNewSignPage(JSONObject hireNewSign, JSONObject customerInfo, String address,
			JSONObject getHouseAssessInfo) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveHireNewSignPage;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		JSONObject customerInfoDt = JSONObject.fromObject(customerInfo.getString("data"));
		JSONObject hireNewSignDt = JSONObject.fromObject(hireNewSign.getString("data"));
		JSONArray getHouseAssessArray = getHouseAssessInfo.getJSONArray("rooms");

		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		// 获取房源类型
		String rentTypeView = null;
		if (productVesion.equals("1003") || productVesion.equals("1008")) {
			rentTypeView = "自如友家";
		} else if (productVesion.equals("1007") || productVesion.equals("1009")) {
			rentTypeView = "自如整租";
		} else {
			rentTypeView = "";
		}
		map.put("hireContract.isWhole", is_whole);
		map.put("rentTypeView", rentTypeView);
		// 房子配置版本
		map.put("hireContract.ziroomVersionId", hireNewSignDt.getString("version_id"));
		// 房子配置版本
		map.put("hireContract.productId", hireNewSignDt.getString("decorate_type"));
		map.put("productIdView", ZoHireHousePC.productIdView);

		// 2000001,经典 ;2000003,闺宿;2000002,原创
		map.put("hireContract.outHouseProduct", ZoHireHousePC.outHouseProduct);
		map.put("bzHireContractWhole.assessCode", hireNewSignDt.getString("assessCode"));
		map.put("bzHireContractWhole.configLevel",
				FunctionCommon.replaceString(hireNewSignDt.getString("configLevel")));
		map.put("bzHireContractWhole.assessConfigTotalPrice",
				FunctionCommon.replaceString(hireNewSignDt.getString("assessConfigTotalPrice")));
		map.put("bzHireContractWhole.paymentTimes",
				FunctionCommon.replaceString(hireNewSignDt.getString("paymentTimes")));
		map.put("hireContract.propertyType", ZoHireHousePC.propertyType);
		map.put("hireContract.ownershipCode", ZoHireHousePC.ownershipCode);
		map.put("hireContract.propertyAddress", address);// 通州格兰晴天5号楼2单元2层221
		map.put("hireContract.ratingAddress", address);
		map.put("hireContract.isPledge", ZoHireHousePC.isPledge);
		map.put("hireContract.maxLiving", ZoHireHousePC.maxLiving);
		// 供暖方式 ：2030001>独立供暖>;2030002>集体供暖>;2030003>中央空调>;2030004>无供暖>
		map.put("hireContract.supplyHeat", ZoHireHousePC.supplyHeat);
		map.put("hireContract.bedroomAmount", roomNumber);
		map.put("hireContract.parlorAmount", ZoHireHousePC.parlorAmount);
		map.put("hireContract.cookroomAmount", ZoHireHousePC.cookroomAmount);
		map.put("hireContract.toiletAmount", ZoHireHousePC.toiletAmount);
		map.put("hireContract.disposeBedroomAmount", afterRoom);
		map.put("hireContract.disposeParlorAmount", ZoHireHousePC.disposeParlorAmount);
		map.put("hireContract.disposeCookroomAmount", ZoHireHousePC.disposeCookroomAmount);
		map.put("hireContract.disposeToiletAmount", washRoom);
		// 客户来源 210003社区开发
		map.put("owner.customerSource", ZoHireHousePC.customerSource);
		map.put("owner.mobile", customerInfoDt.getString("mobile"));
		map.put("owner.chName", customerInfoDt.getString("chName"));
		map.put("owner.email", customerInfoDt.getString("email"));
		map.put("owner.paperType", customerInfoDt.getString("paperType"));
		map.put("owner.peperCode", customerInfoDt.getString("paperCode"));
		map.put("owner.address", customerInfoDt.getString("address"));
		map.put("hireContract.hasClient", ZoHireHousePC.hasClient);
		map.put("hireContract.clientPhone", "");
		map.put("hireContract.clientName", "");
		map.put("hireContract.clientEmail", "");
		map.put("hireContract.clientCardTypeCode", customerInfoDt.getString("paperType"));
		map.put("hireContract.clientCard", "");
		map.put("hireContract.clientAddress", "");
		map.put("hirePeople.name", ZoHireHousePC.name);
		map.put("hirePeople.phone", ZoHireHousePC.phone);
		map.put("hireContract.accountHolder", ZoHireHousePC.accountHolder);
		map.put("hireContract.accountPhone", customerInfoDt.getString("mobile"));
		map.put("hireContract.accountBank", ZoHireHousePC.accountBank);
		map.put("hireContract.zhiBank", ZoHireHousePC.zhiBank);
		map.put("hireContract.bankAccount", ZoHireHousePC.bankAccount);
		map.put("hireContract.bankArea", "北京市");
		map.put("hireContract.isOnline", ZoHireHousePC.isOnline);
		map.put("hireContract.hireContractCode", "");
		map.put("hireContract.hireYear", hireNewSignDt.getString("lease_year"));
		map.put("hireContract.hireMonth", hireNewSignDt.getString("lease_month"));
		map.put("hireContract.paymentCycleCode", hireNewSignDt.getString("payment"));

		// 获取空置期天数
		String vanancyDays = hireNewSignDt.getString("vanancyDays");
		JSONArray array = JSONArray.fromObject(vanancyDays);

		for (int i = 0; i < array.size(); i++) {
			map.put(String.format("vacancys[%d].vacancyDays", i), array.getString(i));
			map.put(String.format("vacancys[%d].yearOrder", i), Integer.toString(i + 1));
			if (i != 0) {
				map.put(String.format("vacancys[%d].growthRate", i), ZoHireHousePC.growthRate);
			}
		}

		map.put("hireContract.isIncrease", ZoHireHousePC.isIncrease);
		map.put("hireContract.cableTelevisionFee", ZoHireHousePC.cableTelevisionFee);
		map.put("hireContract.isHasDeposit", ZoHireHousePC.isHasDeposit);
		map.put("hireContract.deposit", "");
		map.put("hireContract.upkeepCosts", hireNewSignDt.getString("repair_price"));
		map.put("bzHireContractWhole.chongwu", ZoHireHousePC.chongwu);
		map.put("hireContract.handOverDate", time);
		map.put("hireContract.signDate", time);
		map.put("hireContract.firstDate", time);
		map.put("hireContract.monthlyRent", hireNewSignDt.getString("assess_hire_house_price"));
		map.put("hireContract.houseCode", hireNewSignDt.getString("house_code_id"));
		map.put("hireContract.calculatepricemodelId", hireNewSignDt.getString("id"));
		map.put("hireContract.isResign", ZoHireHousePC.isResign);
		map.put("hireContract.houseArea", hireNewSignDt.getString("area"));
		map.put("hireContract.hireStatusCode", ZoHireHousePC.hireStatusCode);
		map.put("payment", "");

		// rooms参数
		for (int i = 0; i < getHouseAssessArray.size(); i++) {
			JSONObject houseInfo = getHouseAssessArray.getJSONObject(i);
		
			map.put("rooms[" + i + "].roomCode", houseInfo.getString("room_no"));
//			map.put("rooms[" + i + "].roomTypeCode", houseInfo.getString("room_type"));
			map.put("rooms[" + i + "].compartmentFace", houseInfo.getString("orientation"));
			map.put("rooms[" + i + "].usageArea", houseInfo.getString("room_area"));
			map.put("rooms[" + i + "].toliet", houseInfo.getString("toliet"));
			map.put("rooms[" + i + "].balcony", houseInfo.getString("balcony"));
			map.put("rooms[" + i + "].isNewRoom", houseInfo.getString("is_new_room"));			
			String salesPrice = houseInfo.getString("real_rent_price").substring(0,
					houseInfo.getString("real_rent_price").length() - 3);
			map.put("rooms[" + i + "].salesPrice", salesPrice);
			map.put("rooms[" + i + "].sellPrice", salesPrice);
		}

		map.put("hireContract.compartmentFace", "");
		map.put("hireContract.receptionPrice", getHouseAssessInfo.getString("assess_hire_house_price"));
		map.put("hireContract.id", "");
		map.put("hireContract.hireContractCode", "");
		map.put("hirePeople.id", "");
		map.put("hireContract.hireWholeId", "");
		map.put("hireContract.isOnline", "1");
		map.put("isClick", ZoHireHousePC.isClick);
		map.put("house.id", "");
		map.put("house.houseCode", "");
		map.put("house.hireContractId", "");
		// zhengzu 
//		map.put("house.preHouseType", getHouseAssessInfo.getString("pre_house_type"));
//		map.put("house.afterHouseType", getHouseAssessInfo.getString("after_house_type"));
		map.put("house.compartmentFace", "南");
		map.put("house.houseAddress", address);

		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		System.out.println("actual:" + actual);
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 保存
	 * @JSONObject hireNewSign返回的json串
	 * @JSONObject customerInfo返回的json串
	 * @author Elaine
	 **/
	public JSONObject saveHireNewSignPage_ZZ(JSONObject hireNewSign, JSONObject customerInfo, String address,
			JSONObject getHouseAssessInfo) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveHireNewSignPage;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		JSONObject customerInfoDt = JSONObject.fromObject(customerInfo.getString("data"));
		JSONObject hireNewSignDt = JSONObject.fromObject(hireNewSign.getString("data"));
		JSONArray getHouseAssessArray = getHouseAssessInfo.getJSONArray("rooms");

		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		// 获取房源类型
		String rentTypeView = null;
		if (productVesion.equals("1003") || productVesion.equals("1008")) {
			rentTypeView = "自如友家";
		} else if (productVesion.equals("1007") || productVesion.equals("1009")) {
			rentTypeView = "自如整租";
		} else {
			rentTypeView = "";
		}
		map.put("hireContract.isWhole", is_whole);
		map.put("rentTypeView", rentTypeView);
		// 房子配置版本
		map.put("hireContract.ziroomVersionId", hireNewSignDt.getString("version_id"));
		// 房子配置版本
		map.put("hireContract.productId", hireNewSignDt.getString("decorate_type"));
		map.put("productIdView", ZoHireHousePC.productIdView);

		// 2000001,经典 ;2000003,闺宿;2000002,原创
		map.put("hireContract.outHouseProduct", ZoHireHousePC.outHouseProduct);
		map.put("bzHireContractWhole.assessCode", hireNewSignDt.getString("assessCode"));
		map.put("bzHireContractWhole.configLevel",
				FunctionCommon.replaceString(hireNewSignDt.getString("configLevel")));
		map.put("bzHireContractWhole.assessConfigTotalPrice",
				FunctionCommon.replaceString(hireNewSignDt.getString("assessConfigTotalPrice")));
		map.put("bzHireContractWhole.paymentTimes",
				FunctionCommon.replaceString(hireNewSignDt.getString("paymentTimes")));
		map.put("hireContract.propertyType", ZoHireHousePC.propertyType);
		map.put("hireContract.ownershipCode", ZoHireHousePC.ownershipCode);
		map.put("hireContract.propertyAddress", address);// 通州格兰晴天5号楼2单元2层221
		map.put("hireContract.ratingAddress", address);
		map.put("hireContract.isPledge", ZoHireHousePC.isPledge);
		map.put("hireContract.maxLiving", ZoHireHousePC.maxLiving);
		// 供暖方式 ：2030001>独立供暖>;2030002>集体供暖>;2030003>中央空调>;2030004>无供暖>
		map.put("hireContract.supplyHeat", ZoHireHousePC.supplyHeat);
		map.put("hireContract.bedroomAmount", roomNumber);
		map.put("hireContract.parlorAmount", ZoHireHousePC.parlorAmount);
		map.put("hireContract.cookroomAmount", ZoHireHousePC.cookroomAmount);
		map.put("hireContract.toiletAmount", ZoHireHousePC.toiletAmount);
		map.put("hireContract.disposeBedroomAmount", afterRoom);
		map.put("hireContract.disposeParlorAmount", ZoHireHousePC.disposeParlorAmount);
		map.put("hireContract.disposeCookroomAmount", ZoHireHousePC.disposeCookroomAmount);
		map.put("hireContract.disposeToiletAmount", washRoom);
		// 客户来源 210003社区开发
		map.put("owner.customerSource", ZoHireHousePC.customerSource);
		map.put("owner.mobile", customerInfoDt.getString("mobile"));
		map.put("owner.chName", customerInfoDt.getString("chName"));
		map.put("owner.email", customerInfoDt.getString("email"));
		map.put("owner.paperType", customerInfoDt.getString("paperType"));
		map.put("owner.peperCode", customerInfoDt.getString("paperCode"));
		map.put("owner.address", customerInfoDt.getString("address"));
		map.put("hireContract.hasClient", ZoHireHousePC.hasClient);
		map.put("hireContract.clientPhone", "");
		map.put("hireContract.clientName", "");
		map.put("hireContract.clientEmail", "");
		map.put("hireContract.clientCardTypeCode", customerInfoDt.getString("paperType"));
		map.put("hireContract.clientCard", "");
		map.put("hireContract.clientAddress", "");
		map.put("hirePeople.name", ZoHireHousePC.name);
		map.put("hirePeople.phone", ZoHireHousePC.phone);
		map.put("hireContract.accountHolder", ZoHireHousePC.accountHolder);
		map.put("hireContract.accountPhone", customerInfoDt.getString("mobile"));
		map.put("hireContract.accountBank", ZoHireHousePC.accountBank);
		map.put("hireContract.zhiBank", ZoHireHousePC.zhiBank);
		map.put("hireContract.bankAccount", ZoHireHousePC.bankAccount);
		map.put("hireContract.bankArea", "北京市");
		map.put("hireContract.isOnline", ZoHireHousePC.isOnline);
		map.put("hireContract.hireContractCode", "");
		map.put("hireContract.hireYear", hireNewSignDt.getString("lease_year"));
		map.put("hireContract.hireMonth", hireNewSignDt.getString("lease_month"));
		map.put("hireContract.paymentCycleCode", hireNewSignDt.getString("payment"));

		// 获取空置期天数
		String vanancyDays = hireNewSignDt.getString("vanancyDays");
		JSONArray array = JSONArray.fromObject(vanancyDays);

		for (int i = 0; i < array.size(); i++) {
			map.put(String.format("vacancys[%d].vacancyDays", i), array.getString(i));
			map.put(String.format("vacancys[%d].yearOrder", i), Integer.toString(i + 1));
			if (i != 0) {
				map.put(String.format("vacancys[%d].growthRate", i), ZoHireHousePC.growthRate);
			}
		}

		map.put("hireContract.isIncrease", ZoHireHousePC.isIncrease);
		map.put("hireContract.cableTelevisionFee", ZoHireHousePC.cableTelevisionFee);
		map.put("hireContract.isHasDeposit", ZoHireHousePC.isHasDeposit);
		map.put("hireContract.deposit", "");
		map.put("hireContract.upkeepCosts", hireNewSignDt.getString("repair_price"));
		map.put("bzHireContractWhole.chongwu", ZoHireHousePC.chongwu);
		map.put("hireContract.handOverDate", time);
		map.put("hireContract.signDate", time);
		map.put("hireContract.firstDate", time);
		map.put("hireContract.monthlyRent", hireNewSignDt.getString("assess_hire_house_price"));
		map.put("hireContract.houseCode", hireNewSignDt.getString("house_code_id"));
		map.put("hireContract.calculatepricemodelId", hireNewSignDt.getString("id"));
		map.put("hireContract.isResign", ZoHireHousePC.isResign);
		map.put("hireContract.houseArea", hireNewSignDt.getString("area"));
		map.put("hireContract.hireStatusCode", ZoHireHousePC.hireStatusCode);
		map.put("payment", "");

		// rooms参数
		for (int i = 0; i < array.size(); i++) {
			JSONObject houseInfo = getHouseAssessArray.getJSONObject(i);	
			String salesPrice = "";
			if(houseInfo.containsKey("real_rent_price")){
				salesPrice = houseInfo.getString("real_rent_price").substring(0,
						houseInfo.getString("real_rent_price").length() - 3);
			}else{
				salesPrice = "";
			}
			map.put("rooms[" + i + "].roomCode", houseInfo.getString("room_no"));
			map.put("rooms[" + i + "].roomTypeCode", houseInfo.getString("room_type"));
			map.put("rooms[" + i + "].compartmentFace", houseInfo.getString("orientation"));
			map.put("rooms[" + i + "].usageArea", houseInfo.getString("room_area"));
			map.put("rooms[" + i + "].toliet", houseInfo.getString("toliet"));
			map.put("rooms[" + i + "].balcony", houseInfo.getString("balcony"));
			map.put("rooms[" + i + "].isNewRoom", houseInfo.getString("is_new_room"));
			map.put("rooms[" + i + "].salesPrice", salesPrice);
			map.put("rooms[" + i + "].sellPrice", salesPrice);
		}

		map.put("hireContract.compartmentFace", "");
		map.put("hireContract.receptionPrice", getHouseAssessInfo.getString("assess_hire_house_price"));
		map.put("hireContract.id", "");
		map.put("hireContract.hireContractCode", "");
		map.put("hirePeople.id", "");
		map.put("hireContract.hireWholeId", "");
		map.put("hireContract.isOnline", "1");
		map.put("isClick", ZoHireHousePC.isClick);
		map.put("house.id", "");
		map.put("house.houseCode", "");
		map.put("house.hireContractId", "");		
		map.put("house.preHouseType", getHouseAssessInfo.getString("pre_house_type"));
		map.put("house.afterHouseType", getHouseAssessInfo.getString("after_house_type"));
		map.put("house.compartmentFace", "南");
		map.put("house.houseAddress", address);

		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		System.out.println("actual:" + actual);
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	
	/**
	 * @description: 物业交割前点击确认
	 * @param :
	 *            JSONObject saveHireNewSignPage
	 * @author Elaine
	 **/
	public JSONObject findHireProperty(JSONObject saveHireNewSignPage) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.findHireProperty;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 得到收房合同id
		hireContractId = saveHireNewSignPage.getString("data");

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contractId", hireContractId);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 添加物业交割
	 * @author Elaine
	 **/
	public JSONObject addHireProperty() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.addHireProperty;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bzHireContract.id", hireContractId);
		map.put("bzHireContract.hireStatusCode", "wqy");
		map.put("bzHireContract.auditState", "-1");
		map.put("bzHireContract.deliveryDate", time);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 修改接口
	 * @author Elaine
	 **/
	public boolean toHireNewSignPage() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.toHireNewSignPage;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractId", hireContractId);
		map.put("hireContractCode", contractCode);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		// JSONObject actual =
		// JSONObject.fromObject(response.get("returnValue"));
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		String ele = returnValue.title();
		boolean flag = false;
		if (ele.equals("新签-合同信息")) {
			flag = true;
			return flag;
		}

		return flag;
	}

	/**
	 * @description: 打开基础信息保存
	 * @author Elaine
	 **/
	public boolean toHireBaseInfo() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.toHireBaseInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractId", hireContractId);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		String ele = returnValue.title();
		boolean flag = false;
		if (ele.equals("新签-基础信息")) {
			flag = true;
			return flag;
		}

		return flag;
	}

	/**
	 * @description: 保存基础信息保存
	 * @author Elaine
	 **/
	public boolean saveBaseInfo() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveBaseInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);

		// 获取结束时间
		String sql = "select brz.agent_end_date from hlasset.bz_hire_contract brz where brz.hire_contract_code='"
				+ contractCode + "'";
		Map<String, String> timeMap = fc.getAllDataFromOracleData(sql).get(0);
		String endtime = timeMap.get("AGENT_END_DATE").substring(0, 10);
		System.out.println(endtime);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("agentStartDate", time);
		map.put("agentEndDate", endtime);
		map.put("supplementStatus", "0");
		map.put("id", hireContractId);
		map.put("isShareProperty", "0");
		map.put("remark", "");
		map.put("customerRemark", "");
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		String ele = response.get("returnStatusCode");
		boolean flag = false;
		if (ele.equals("302")) {
			flag = true;
			return flag;
		}

		return flag;
	}

	/**
	 * @description: 打开房源信息页面
	 * @author Elaine
	 **/
	public boolean toHireHouseInfo() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.toHireHouseInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractId", hireContractId);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		String ele = returnValue.title();
		boolean flag = false;
		if (ele.equals("新签-房源信息")) {
			flag = true;
			return flag;
		}

		return flag;
	}

	/**
	 * @description: 得到房源信息
	 * @author Elaine
	 **/
	public Map<String, String> getHouseInfo() {
		String sql = "select hire.HOUSE_AREA,hire.house_code,hire.house_source_code,hire.property_type,hire.ownership_code,hire.property_address,hire.rating_address,hire.compartment_face,hire.ladder_amount,hire.family_amount,hire.building_frame,hire.lock_type,hire.house_evaluation_traffic,hire.product_id,house.resblock_id,house.resblock_name,house.build_end_year,house.pre_house_type,house.after_house_type, hire.House_Evaluation_Circum from hlasset.bz_hire_contract hire,hlasset.bz_house house where hire.hire_contract_code = house.hire_contract_code and house.hire_contract_code='"
				+ contractCode + "'";
		List<Map<String, String>> list = fc.getAllDataFromOracleData(sql);
		Map<String, String> result = list.get(0);
		return result;
	}

	/**
	 * @description: 保存房源信息
	 * @param houseinfo
	 *            : 是计价模型中的返回值
	 * @author Elaine
	 **/
	public JSONObject saveHouseInfo(JSONObject houseinfo) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveHouseInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 得到houseinfo的data
		JSONObject data = houseinfo.getJSONObject("data");

		// 获取当前时间
		Map<String, String> gethouseInfo = getHouseInfo();
		String city = null;

		if (cityCode.equals("110000")) {
			city = "北京";
		} else if (cityCode.equals("310000")) {
			city = "上海";
		} else {
			city = "深圳";
		}

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		// map.put("menuCount", "2");
		// map.put("menuCompleteCount", "1");
		map.put("bzResBlockInfo.standardId", data.getString("villageId"));
		map.put("bzResBlockInfo.name", gethouseInfo.get("RESBLOCK_NAME"));
		map.put("bzResBlockInfo.alias", "");
		map.put("bzResBlockInfo.buildEndYear", gethouseInfo.get("BUILD_END_YEAR"));
		// map.put("bzResBlockInfo.propertyName", "北京市佳开原物业管理有限公司");
		// map.put("bzResBlockInfo.propertyPhone", "01069551319");
		map.put("resblockInfo.city", city);
		map.put("bzResBlockInfo.districtName", district);
		map.put("bzResBlockInfo.districtId", data.getString("districtId"));
		map.put("contractHouseInfo.id", hireContractId);
		// 整租
//		map.put("contractHouseInfo.preHouseType", gethouseInfo.get("PRE_HOUSE_TYPE"));
//		map.put("contractHouseInfo.afterHouseType", gethouseInfo.get("AFTER_HOUSE_TYPE"));
		map.put("contractHouseInfo.houseCode", gethouseInfo.get("HOUSE_CODE"));
		map.put("contractHouseInfo.houseSourceCode", gethouseInfo.get("HOUSE_SOURCE_CODE"));
		map.put("contractHouseInfo.propertyType", gethouseInfo.get("PROPERTY_ADDRESS"));
		map.put("contractHouseInfo.ownershipCode", gethouseInfo.get("OWNERSHIP_CODE"));
		map.put("contractHouseInfo.propertyAddress", gethouseInfo.get("PROPERTY_ADDRESS"));
		map.put("contractHouseInfo.ratingAddress", gethouseInfo.get("RATING_ADDRESS"));
		map.put("contractHouseInfo.compartmentFace", "南");
		// 电梯情况 梯 -户
		map.put("contractHouseInfo.ladderAmount", "0");
		map.put("contractHouseInfo.familyAmount", "0");
		// 户型结构，平面304400000001
		map.put("contractHouseInfo.buildingFrame", "304400000001");// gethouseInfo.get("BUILDING_FRAME"));
		// 门锁
		map.put("contractHouseInfo.houseArea", gethouseInfo.get("HOUSE_AREA"));
		map.put("contractHouseInfo.lockType", "普通门锁");// gethouseInfo.get("LOCK_TYPE"));
		map.put("contractHouseInfo.houseEvaluationIntroduction", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		map.put("contractHouseInfo.houseEvaluationTraffic", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		map.put("contractHouseInfo.houseEvaluationCircum", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		map.put("productCode", productCode);
		map.put("contractHouseInfo.houseEvaluationCell", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		// 整租
//		map.put("contractHouseInfo.houseEvaluationPrice", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 保存房源信息
	 * @param houseinfo
	 *            : 是计价模型中的返回值
	 * @author Elaine
	 **/
	public JSONObject saveHouseInfo_ZZ(JSONObject houseinfo) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveHouseInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 得到houseinfo的data
		JSONObject data = houseinfo.getJSONObject("data");

		// 获取当前时间
		Map<String, String> gethouseInfo = getHouseInfo();
		String city = null;

		if (cityCode.equals("110000")) {
			city = "北京";
		} else if (cityCode.equals("310000")) {
			city = "上海";
		} else {
			city = "深圳";
		}

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		// map.put("menuCount", "2");
		// map.put("menuCompleteCount", "1");
		map.put("bzResBlockInfo.standardId", data.getString("villageId"));
		map.put("bzResBlockInfo.name", gethouseInfo.get("RESBLOCK_NAME"));
		map.put("bzResBlockInfo.alias", "");
		map.put("bzResBlockInfo.buildEndYear", gethouseInfo.get("BUILD_END_YEAR"));
		// map.put("bzResBlockInfo.propertyName", "北京市佳开原物业管理有限公司");
		// map.put("bzResBlockInfo.propertyPhone", "01069551319");
		map.put("resblockInfo.city", city);
		map.put("bzResBlockInfo.districtName", district);
		map.put("bzResBlockInfo.districtId", data.getString("districtId"));
		map.put("contractHouseInfo.id", hireContractId);
		// 整租
		map.put("contractHouseInfo.preHouseType", gethouseInfo.get("PRE_HOUSE_TYPE"));
		map.put("contractHouseInfo.afterHouseType", gethouseInfo.get("AFTER_HOUSE_TYPE"));
		map.put("contractHouseInfo.houseCode", gethouseInfo.get("HOUSE_CODE"));
		map.put("contractHouseInfo.houseSourceCode", gethouseInfo.get("HOUSE_SOURCE_CODE"));
		map.put("contractHouseInfo.propertyType", gethouseInfo.get("PROPERTY_ADDRESS"));
		map.put("contractHouseInfo.ownershipCode", gethouseInfo.get("OWNERSHIP_CODE"));
		map.put("contractHouseInfo.propertyAddress", gethouseInfo.get("PROPERTY_ADDRESS"));
		map.put("contractHouseInfo.ratingAddress", gethouseInfo.get("RATING_ADDRESS"));
		map.put("contractHouseInfo.compartmentFace", "南");
		// 电梯情况 梯 -户
		map.put("contractHouseInfo.ladderAmount", "0");
		map.put("contractHouseInfo.familyAmount", "0");
		// 户型结构，平面304400000001
		map.put("contractHouseInfo.buildingFrame", "304400000001");// gethouseInfo.get("BUILDING_FRAME"));
		// 门锁
		map.put("contractHouseInfo.houseArea", gethouseInfo.get("HOUSE_AREA"));
		map.put("contractHouseInfo.lockType", "普通门锁");// gethouseInfo.get("LOCK_TYPE"));
		map.put("contractHouseInfo.houseEvaluationIntroduction", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		map.put("contractHouseInfo.houseEvaluationTraffic", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		map.put("contractHouseInfo.houseEvaluationCircum", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		map.put("productCode", productCode);
		map.put("contractHouseInfo.houseEvaluationCell", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		// 整租
//		map.put("contractHouseInfo.houseEvaluationPrice", "本小区交通便捷，首先紧邻地铁八通线梨园站步行10分钟");
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	
	/**
	 * @description: 保存房间信息
	 * @author Elaine
	 **/
	public JSONObject saveRoomInfo(JSONObject houseinfo) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveRoomInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 得到房间数目
		String sql = "Select room.* from bz_room room where room.hire_contract_code='" + contractCode
				+ "'and room.is_public=0 order by id";
		List<Map<String, String>> roomNum = fc.getAllDataFromOracleData(sql);

		JSONObject actual = null;
		JSONObject jsobj = null;
		// 输入参数
		HashMap<String, String> map = null;

		for (int i = 0; i < roomNum.size(); i++) {
			map = new HashMap<String, String>();
			Map<String, String> num = roomNum.get(i);
			map.put("id", num.get("ID"));
			map.put("hireContractId", hireContractId);
			map.put("houseId", "");
			map.put("houseCode", num.get("HOUSE_CODE"));
			map.put("isBalcony", num.get("IS_BALCONY"));
			map.put("isBathRoom", "0");
			map.put("productCode", productCode);
			// map.put("menuCount 2", hireContractId);
			// map.put("menuCompleteCount 2",
			// gethouseInfo.get("PRE_HOUSE_TYPE"));
			map.put("roomCode", num.get("ROOM_CODE"));
			map.put("roomName", num.get("ROOM_NAME"));
			map.put("compartmentFace", num.get("COMPARATMENT_FACE"));
			map.put("usageArea", num.get("USAGE_AREA"));
			map.put("sellPrice", num.get("SELL_PRICE"));
			map.put("salesPrice", num.get("SALES_PRICE"));
			map.put("isBathRoomValue", num.get("IS_BALCONY"));
			map.put("isBalconyValue", num.get("IS_BALCONY"));
			map.put("roomStyleCode", num.get("ROOM_STYLE_CODE"));
			// 分租
			map.put("bathRoomArea", "10");
			map.put("balconyType", num.get("BALCONY_TYPE"));
			map.put("balconyArea", "10");
			map.put("roomEvaluationTitle", "测试的房源");
			map.put("roomEvaluationPrice", "测试的房源");	
			map.put("roomEvaluationConfig", "");
			map.put("roomEvaluationHtype", "");
			map.put("roomEvaluationNeighbour", "");
			map.put("roomEvaluationTruth", "");
			
			jsobj = JSONObject.fromObject(map);
			Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
			actual = JSONObject.fromObject(response.get("returnValue"));			
		}

		// 请求接口返回值		
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	
	/**
	 * @description: 保存公共区域信息
	 * @author Elaine
	 **/
	public JSONObject savePublicInfo(JSONObject houseinfo) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveRoomInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 得到房间数目
		String sql = "Select room.* from bz_room room where room.hire_contract_code='" + contractCode
				+ "'and room.is_public=1 order by id";
		List<Map<String, String>> roomNum = fc.getAllDataFromOracleData(sql);

		JSONObject actual = null;
		JSONObject jsobj = null;
		// 输入参数
		HashMap<String, String> map = null;

		for (int i = 0; i < roomNum.size(); i++) {
			map = new HashMap<String, String>();
			Map<String, String> num = roomNum.get(i);
			if(num.get("ROOM_NAME").contains("客厅") || num.get("ROOM_NAME").contains("起居室") || num.get("ROOM_NAME").contains("餐厅")){
				map.put("id", num.get("ID"));
				map.put("hireContractId", hireContractId);
				map.put("houseId", "");
				map.put("houseCode", num.get("HOUSE_CODE"));
				map.put("roomName", num.get("ROOM_NAME"));
				map.put("roomTypeCode", num.get("ROOM_TYPE_CODE"));
				map.put("usageArea", "10");
				map.put("isBalcony", num.get("IS_BALCONY"));
				map.put("balconyArea", "10");
			}else if(num.get("ROOM_NAME").contains("厨房") || num.get("ROOM_NAME").contains("卫生间")){
				map.put("id", num.get("ID"));
				map.put("hireContractId", hireContractId);
				map.put("houseId", "");
				map.put("houseCode", num.get("HOUSE_CODE"));
				map.put("roomName", num.get("ROOM_NAME"));
				map.put("roomTypeCode", num.get("ROOM_TYPE_CODE"));
				// 开放式38000300 封闭式38000301
				map.put("roomStyleCode", "38000301");
			}
			
			jsobj = JSONObject.fromObject(map);
			Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
			actual = JSONObject.fromObject(response.get("returnValue"));			
		}

		// 请求接口返回值		
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	
	/**
	 * @description: 打开收房人信息页面前一步
	 * @author Elaine
	 **/
	public boolean splitPage() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.splitPage;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 得到房间数目
		String sql = "Select room.* from bz_room room where room.hire_contract_code='" + contractCode + "' order by id";
		List<Map<String, String>> roomNum = fc.getAllDataFromOracleData(sql);
		Map<String,String> num = roomNum.get(roomNum.size() -1);

		// 输入参数
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("hireContractId", hireContractId);
		map.put("nextRoomId", num.get("ID"));
		map.put("nextRoomTypeCode", num.get("ROOM_TYPE_CODE"));
		map.put("isFinally", "1");
		map.put("isComplete", "1");
			
		JSONObject jsobj = JSONObject.fromObject(map);
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		String ele = response.get("returnStatusCode");
		boolean flag = false;
		if (ele.equals("302")) {
			flag = true;
			return flag;
		}

		return flag;
	
	}
	
	/**
	 * @description: 打开收房人信息页面
	 * @author Elaine
	 **/
	public boolean toHirePeopleInfo() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.toHirePeopleInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);
		
		String sql = "Select room.* from bz_room room where room.hire_contract_code='" + contractCode + "' order by id";
		List<Map<String, String>> roomNum = fc.getAllDataFromOracleData(sql);
		Map<String,String> num = roomNum.get(roomNum.size() -1);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractId", hireContractId);
		map.put("roomId", num.get("ID"));
		map.put("flowStep", Integer.toString(roomNum.size()));
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		String ele = returnValue.title();
		boolean flag = false;
		if (ele.equals("新签-收房人信息")) {
			flag = true;
			return flag;
		}

		return flag;
	}

	
	/**
	 * @description: 得到管家系统号码
	 * @author Elaine
	 **/
	public JSONObject getUsersList() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.getUsersList;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userCode", account);
		map.put("page", "1");
		map.put("rows", "50");
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	
	/**
	 * @description: 得到管家的上级号码
	 * @author Elaine
	 **/
	public JSONObject getHigherLevel() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.getHigherLevel;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userCode", account);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	
	/**
	 * @description: 从楼盘系统中获取所属管家
	 * @author Elaine
	 **/
	public JSONObject getManagetInfo(){
		String domainName = PropertyConstants.ZRBD_DOMAIN;
		String suffixUrl = PropertyConstants.getEmpByHouseId;
		String httpUrl = domainName + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("houseId", standardID);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		JSONArray managementInfo = actual.getJSONArray("data");
		for(int i = 0; i < managementInfo.size(); i++){
			JSONObject manager = managementInfo.getJSONObject(0);
			String empStyle = manager.getString("empStyle");		
			
			if(empStyle.equals("直收管家")){
				accountId = manager.getString("empCode");
			}
		}

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	
	/**
	 * @description: 从EHR系统中获取所属管家的上级
	 * @author Elaine
	 **/
	public JSONObject getEhrDeptInfo(){
		String domainName = PropertyConstants.EHR_DOMAIN;
		String suffixUrl = PropertyConstants.getEhrDept;
		String httpUrl = domainName + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userCode", accountId);
		map.put("level", "3");
		map.put("flag", "1");
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	/**
	 * @description: 保存管家系统号码
	 * @author Elaine
	 **/
	public JSONObject saveHirePeopleInfo(JSONObject getUsersList,JSONObject getHigherLevel,JSONObject getManagetInfo) {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.saveHirePeopleInfo;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl); 
		
		// 管家信息
		JSONObject accountInfo = getUsersList.getJSONArray("rows").getJSONObject(0);
		// 管家上级信息
		JSONObject accountHigherInfo = getHigherLevel.getJSONObject("data").getJSONObject("usersLevel");
//	    // 得到录入人
//		String sql = "select brz.luruname,brz.lurucode,brz.lurutel from hlasset.bz_hire_contract brz where brz.hire_contract_code='"+contractCode+"'";
//		Map<String, String> info = fc.getAllDataFromOracleData(sql).get(0);
		// 得 到所有管家信息
		JSONArray managementInfo = getManagetInfo.getJSONArray("data");
		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContract.id", hireContractId);
		// 代理方式
		map.put("hireContract.isDirectHar", "zs");
		map.put("hireContract.shopAreaName", "集团客户组");
		// 集团客户组 -- A17139
		map.put("hireContract.shopAreaCode", "A17139");
		map.put("hireContract.ziruCommissionerCode", account);
		map.put("hireContract.ziruCommissionerName", accountInfo.getString("userName"));
		map.put("hireContract.ziruCommissionerPhone", accountInfo.getString("userMobile"));
		map.put("hireContract.ziruChargeCode", accountHigherInfo.getJSONObject("zhuguan").getString("userId"));
		map.put("hireContract.ziruChargeName", accountHigherInfo.getJSONObject("zhuguan").getString("userName"));
		map.put("hireContract.ziruChargePhone", accountHigherInfo.getJSONObject("zhuguan").getString("userPhone"));
		map.put("hireContract.ziruMajordomoCode", accountHigherInfo.getJSONObject("zongjian").getString("userId"));
		map.put("hireContract.ziruMajordomoName", accountHigherInfo.getJSONObject("zongjian").getString("userName"));
		map.put("hireContract.ziruMajordomoPhone", accountHigherInfo.getJSONObject("zongjian").getString("userPhone"));
		map.put("hireContract.luruCode", account);
		map.put("hireContract.luruName", accountInfo.getString("userName"));
		map.put("hireContract.assistantCode", account);
		map.put("hireContract.assistantName", accountInfo.getString("userName"));
		map.put("hireContract.assistantPhone", accountInfo.getString("userMobile"));		
		map.put("hireContract.lastModifyName", accountInfo.getString("userName"));
		map.put("hireContract.lastModifyPhone", accountInfo.getString("userMobile"));
		map.put("hireContract.areaName", "产品运营部");
		map.put("hireContract.areaCode", "A18222");
		for(int i = 0; i < managementInfo.size(); i++){
			JSONObject manager = managementInfo.getJSONObject(i);
			String empType = manager.getString("empType");
		
			
			if(empType.equals("60030006")){
				map.put("hireContract.zhishouEmpCode", manager.getString("empCode"));
				map.put("hireContract.zhishouEmpName", manager.getString("empName"));
				map.put("hireContract.hireCommissionerCode", manager.getString("empCode"));
				map.put("hireContract.hireCommissionerName", manager.getString("empName"));
				map.put("hireContract.hireCommissionerPhone", manager.getString("phoneMobile"));	
				// 得到管家上级
				JSONObject ehrInfo = getEhrDeptInfo();
			    JSONArray ehr = ehrInfo.getJSONArray("data");
			    for(int n = 0; n < ehr.size(); n++){
			    	JSONObject ehrSingle = ehr.getJSONObject(n);
			    	String job = ehrSingle.getString("job");
			    	if(job.contains("主管")){
			    		map.put("hireContract.chargeCode", ehrSingle.getString("userCode"));
						map.put("hireContract.chargeName", ehrSingle.getString("userName"));			    	
			    	}else if(job.contains("总监")){
			    		map.put("hireContract.majordomoCode", ehrSingle.getString("userCode"));
						map.put("hireContract.majordomoName", ehrSingle.getString("userName"));					
			    	}else{
			    		
			    	}
				
			    }
			}else if(empType.equals("60030005")){
				map.put("hireContract.hireServiceCode", manager.getString("empCode"));
				map.put("hireContract.hireServiceName", manager.getString("empName"));
			}else if(empType.equals("60030003")){
				map.put("hireContract.houselurucode", manager.getString("empCode"));
				map.put("hireContract.houseluruname", manager.getString("empName"));
			}else if(empType.equals("60030004")){
				map.put("hireContract.surconfigCode",  manager.getString("empCode"));
				map.put("hireContract.surconfigName", manager.getString("empName"));
			}
				
		}
		
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
	
	/**
	 * @description: 保存付款计划
	 * @author Elaine
	 **/
	public boolean payMentPlan() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.payMentPlan;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractId", hireContractId);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		String ele = response.get("returnStatusCode");
		boolean flag = false;
		if (ele.equals("302")) {
			flag = true;
			return flag;
		}

		return flag;
	}
	
	/**
	 * @description: 打开付款计划页面
	 * @author Elaine
	 **/
	public boolean toHirePaymentPlan() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.toHirePaymentPlan;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractId", hireContractId);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		String ele = returnValue.title();
		boolean flag = false;
		if (ele.equals("新签-付款计划")) {
			flag = true;
			return flag;
		}

		return flag;
	}
	
	/**
	 * @description: 打开补充协议
	 * @author Elaine
	 **/
	public JSONObject toHireSupplyAgreement() {
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.toHireSupplyAgreement;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractId", hireContractId);
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		Document returnValue = Jsoup.parse(response.get("returnValue"));
		String ele = returnValue.title();
		boolean flag = false;
		if (ele.equals("新签-补充协议")) {
			flag = true;
		}
		
		JSONObject result = new JSONObject();
		if(flag == true){
			result.put("status", "success");
			result.put("html", response.get("returnValue"));
			return result;
		}

		result.put("status", "false");
		return result;
	}
	
	/**
	 * @description: 提交审核
	 * @author Elaine
	 **/
	public JSONObject compileHire() {
		// 补充协议页面
		String domainName = PropertyConstants.ZO_DOMAIN;
		String suffixUrl = PropertyConstants.compileHire;
		String httpUrl = domainName + "/" + cityCode + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contractId", hireContractId);		
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));

		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}

	/**
	 * @description: 处理房源未实勘，商机未终结的状况
	 * @param json
	 *            : saveHireNewSignPage返回值
	 * @author Elaine
	 **/
	public JSONObject doDiffProblem(JSONObject json, JSONObject hireNewSign, JSONObject customerInfo, String address,
			JSONObject getHouseAssessInfo) {
		String result = json.getString("error_message");
		JSONObject saveHireNewSignPage = null;
		String sql = null;
		if (result.contains("非终结态")) {
			sql = "update bus_opp set is_final_state =0 where standard_id ='" + standardID + "'";
			fc.updateMySqlData(sql, "crm");
			saveHireNewSignPage = saveHireNewSignPage(hireNewSign, customerInfo, address, getHouseAssessInfo);
			return saveHireNewSignPage;
		} else if (result.contains("实勘")) {
			sql = "update bus_opp set track_state =2 where  standard_id ='" + standardID + "'";
			fc.updateMySqlData(sql, "crm");
			saveHireNewSignPage = saveHireNewSignPage(hireNewSign, customerInfo, address, getHouseAssessInfo);
			return saveHireNewSignPage;
		} else {
			return json;
		}
	}

	/**
	 * @description: 得到收房合同号
	 * @param
	 *            : saveHireNewSignPage返回值
	 * @author Elaine
	 **/
	public String getHireContractCode() {
		String sql = "select brz.Hire_Contract_Code from hlasset.bz_hire_contract brz where brz.house_code='"
				+ standardID + "'";
		List<Map<String, String>> list = fc.getAllDataFromOracleData(sql);
		contractCode = list.get(0).get("HIRE_CONTRACT_CODE").toString();
		logger.info("收房合同创建成功：" + contractCode);
		System.out.println(contractCode);

		return contractCode;
	}

	/**
	 * @description: 改变收房合同审核状态
	 * @author Elaine
	 **/
	public void changeHireContractStatus() {
		String sql = "update bz_hire_contract hire set hire.hire_status_code='yqy' where hire.hire_contract_code='"
				+ contractCode + "'";
		fc.updateOracleData(sql);
	}

	/**
	 * @description: 得到业主确认
	 * @author Elaine
	 **/
	public JSONObject hireNewSign() {
		String domainName = PropertyConstants.HIRE_DOMAIN;
		String suffixUrl = PropertyConstants.hireNewSign_contract;
		String httpUrl = domainName + suffixUrl;
		logger.info(httpUrl);

		// 输入参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hireContractCode", contractCode);// contractCode
		JSONObject jsobj = JSONObject.fromObject(map);

		// 请求接口返回值
		Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
		JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
		actual.put("url", httpUrl);
		actual.put("inPara", jsobj.toString());
		logger.info(actual);
		return actual;
	}
}
