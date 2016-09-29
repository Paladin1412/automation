package keeper.appScript;

import com.ziroom.utils.CommonFunction;
import keeper.confManagement.commonMethods.HttpRequest;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Keeper_SourceTreasure {
	private static final Logger logST = Logger.getLogger(Keeper_NewSignDelivery.class);

	HttpRequest hRequest = new HttpRequest();
	// 添加商机----------------------------------------

	// 获取城区
	/**
	 * @description: 获取城区列表
	 * @author jihaibo /crm-reserve/house/getVillageList
	 **/
	public JSONObject s_getDistrictList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_getDistrictList返回值" + responseJson);
		} else {
			logST.info("s_getDistrictList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 获取小区列表
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/house/getVillageList
	 **/
	public JSONObject s_getVillageList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("resblock", KeeperGlobalParas.resblock);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_getVillageList返回值" + responseJson);
		} else {
			logST.info("s_getVillageList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 获取楼栋
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/house/getBuildNumList
	 **/
	public JSONObject s_getBuildNumList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("resblockId", KeeperGlobalParas.resblockId);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_getBuildNumList的responseData返回值为[]" + responseJson);
			} else {

				KeeperGlobalParas.building_no = JSONArray.fromObject(responseData).getJSONObject(0).getString("building_no");
//				KeeperGlobalParas.building_no = "";
				logST.info("s_getBuildNumList返回值" + responseJson);
			}
		} else {
			logST.info("s_getBuildNumList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 获取楼栋第几单元
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/house/getUnitList
	 **/
	public JSONObject s_getUnitList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("resblockId", KeeperGlobalParas.resblockId);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("buildingNo", KeeperGlobalParas.building_no);
		map.put("sign", CommonFunction.getCrmSign(map));

//		for (Map.Entry<String, String> m : map.entrySet()) {
//			System.out.println(m.getKey() + ":" + m.getValue());
//		}
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_getUnitList的responseData返回值为[]" + responseJson);
			} else {
				KeeperGlobalParas.unit = JSONArray.fromObject(responseData).getJSONObject(0).getString("unit");
				logST.info("s_getUnitList返回值" + responseJson);
				System.out.println("单元：" + KeeperGlobalParas.unit);
			}
		} else {
			logST.info("s_getUnitList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 获取楼层
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/house/getFloorList
	 **/
	public JSONObject s_getFloorList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("resblockId", KeeperGlobalParas.resblockId);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("buildingNo", KeeperGlobalParas.building_no);
		map.put("unit", KeeperGlobalParas.unit);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_getFloorList的responseData返回值为[]" + responseJson);
			} else {
				KeeperGlobalParas.floor = JSONArray.fromObject(responseData).getJSONObject(0).getString("floor");
				logST.info("s_getFloorList返回值" + responseJson);
				System.out.println("楼层：" + KeeperGlobalParas.floor);
			}
		} else {
			logST.info("s_getFloorList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 获取房屋
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/house/getRoomNumList
	 **/
	public JSONObject s_getRoomNumList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("resblockId", KeeperGlobalParas.resblockId);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("buildingNo", KeeperGlobalParas.building_no);
		map.put("unit", KeeperGlobalParas.unit);
		map.put("floor", KeeperGlobalParas.floor);
		map.put("sign", CommonFunction.getCrmSign(map));
		/*
		 * for (Map.Entry<String, String> m : map.entrySet()) {
		 * System.out.println(m.getKey() + ":" + m.getValue()); }
		 */
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_getRoomNumList的responseData返回值为[]" + responseJson);
			} else {
				KeeperGlobalParas.room_no = JSONArray.fromObject(responseData).getJSONObject(1).getString("room_no");
				logST.info("s_getRoomNumList返回值" + responseJson);

			}
		} else {
			logST.info("s_getRoomNumList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 商机来源
	 * @author jihaibo
	 *         http://s.t.ziroom.com/crm-reserve/busopp/queryBOFirstSourceList
	 **/
	public JSONObject s_queryBOFirstSourceList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("boType", KeeperGlobalParas.boType);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_queryBOFirstSourceList的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_queryBOFirstSourceList返回值" + responseJson);

			}
		} else {
			logST.info("s_queryBOFirstSourceList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 二级商机来源
	 * @author jihaibo
	 *         http://s.t.ziroom.com/crm-reserve/busopp/queryBOSecondSourceList
	 **/
	public JSONObject s_queryBOSecondSourceList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("firstSourceId", KeeperGlobalParas.firstSourceId);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_queryBOSecondSourceList的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_queryBOSecondSourceList返回值" + responseJson);

			}
		} else {
			logST.info("s_queryBOSecondSourceList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 确定商机（添加商机）
	 * @author jihaibo
	 *         http://s.t.ziroom.com/crm-reserve/busopp/getKeeperByAndSourceType
	 **/
	public JSONObject s_getKeeperByAndSourceType(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("entryPersonName", KeeperGlobalParas.entryPersonName);
		map.put("source", KeeperGlobalParas.source);
		map.put("entryPersonCode", KeeperGlobalParas.serviceUserName); // 管家号
		map.put("firstSource", KeeperGlobalParas.firstSourceId);
		map.put("buildNum", KeeperGlobalParas.building_no);
		map.put("uuid", uuid);
		map.put("secondSource", KeeperGlobalParas.secondSource);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("roomNum", KeeperGlobalParas.room_no);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("unit", KeeperGlobalParas.unit);
		map.put("floor", KeeperGlobalParas.floor);
		map.put("villageId", KeeperGlobalParas.villageId);
		map.put("sign", CommonFunction.getCrmSign(map));
//		System.out.println(map);
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
//			System.out.println(responseJson);
			KeeperGlobalParas.toKeeperTypeName = JSONObject.fromObject(responseData).getString("toKeeperTypeName");
			if (responseData.equals("[]")) {
				logST.info("s_getKeeperByAndSourceType的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_getKeeperByAndSourceType返回值" + responseJson);
			}
		} else {
			logST.info("s_getKeeperByAndSourceType返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 提交一（添加商机）
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/busopp/appSearchBusOppByStandardInfo
	 **/
	public JSONObject s_appSearchBusOppByStandardInfo(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		// map.put("entryPersonName", KeeperGlobalParas.entryPersonName);
		map.put("source", KeeperGlobalParas.source);
		map.put("buildNum", KeeperGlobalParas.building_no);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("roomNum", KeeperGlobalParas.room_no);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("unit", KeeperGlobalParas.unit);
		map.put("floor", KeeperGlobalParas.floor);
		map.put("villageId", KeeperGlobalParas.villageId);
		map.put("sign", CommonFunction.getCrmSign(map));
//		logST.info(map);
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
//		logST.info(responseJson);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_appSearchBusOppByStandardInfo的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_appSearchBusOppByStandardInfo返回值" + responseJson);
			}
		} else {
			logST.info("s_appSearchBusOppByStandardInfo返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 提交二（添加商机）
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/busopp/createBusOpp
	 **/
	public JSONObject s_createBusOpp(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		// 通州格兰晴天7号楼4单元6层461
		String address = KeeperGlobalParas.districtName + KeeperGlobalParas.resblockName + KeeperGlobalParas.building_no
				+ KeeperGlobalParas.unit + KeeperGlobalParas.floor + KeeperGlobalParas.room_no;
		KeeperGlobalParas.address = address;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("firstSource", KeeperGlobalParas.firstSourceId);
		map.put("buildNum", KeeperGlobalParas.building_no);
		map.put("uuid", uuid);
		map.put("districtName", KeeperGlobalParas.districtName);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("ownerTel", KeeperGlobalParas.ownerTel);
		map.put("districtId", KeeperGlobalParas.districtId);
		map.put("ownerKeeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("ownerName", KeeperGlobalParas.ownerName);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("unit", KeeperGlobalParas.unit);
		map.put("villageId", KeeperGlobalParas.villageId);
		map.put("operatorName", KeeperGlobalParas.operatorName);
		map.put("address", address);
		map.put("entryPersonName", KeeperGlobalParas.entryPersonName);
		map.put("entryPersonCode", KeeperGlobalParas.serviceUserName);
		map.put("secondSource", KeeperGlobalParas.secondSource);
		map.put("keeperOwnerUid", KeeperGlobalParas.login_uid);
		map.put("roomNum", KeeperGlobalParas.room_no);
		map.put("keeperName", KeeperGlobalParas.keeperName);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("keeperPhone", KeeperGlobalParas.keeperPhone);
		map.put("entryPersonPhone", KeeperGlobalParas.keeperPhone);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("isTopFloor", KeeperGlobalParas.isTopFloor);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("ownerPhone", KeeperGlobalParas.ownerPhone);
		map.put("villageName", KeeperGlobalParas.resblockName);
		map.put("floor", KeeperGlobalParas.floor);
		map.put("operatorCode", KeeperGlobalParas.serviceUserName);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
//		logST.info(map);
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
//		logST.info(responseJson);
		if (responseJson != null) {
				logST.info("s_createBusOpp的responseData返回值为[]" + responseJson);
		} else {
			logST.info("s_createBusOpp返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 提交三（添加商机）
	 * @author jihaibo
	 *         http://s.t.ziroom.com/crm-reserve/clew/getClewTotalNumByStatus
	 **/
	public JSONObject s_getClewTotalNumByStatus(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_getClewTotalNumByStatus的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_appSearchBusOppByStandardInfo返回值" + responseJson);
			}
		} else {
			logST.info("s_getClewTotalNumByStatus返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 提交四（添加商机）
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/busopp/queryBOTotalList
	 **/
	public JSONObject s_queryBOTotalList(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_queryBOTotalList的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_queryBOTotalList返回值" + responseJson);
			}
		} else {
			logST.info("s_queryBOTotalList返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/****
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 管家跟进
	 */

	/**
	 * @description: 我的商机列表
	 * @author jihaibo
	 *         http://s.t.ziroom.com/crm-reserve/busopp/queryBOListByMyEntry
	 **/
	public JSONObject s_queryBOListByMyEntry(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("pageSize", KeeperGlobalParas.pageSize);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("keeperId", KeeperGlobalParas.serviceUserName);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("pageNum", KeeperGlobalParas.pageNum);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_queryBOListByMyEntry的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_queryBOListByMyEntry返回值" + responseJson);
			}
		} else {
			logST.info("s_queryBOListByMyEntry返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 待跟进列表(待跟进：0，首次跟进：1)
	 * @author jihaibo
	 *         http://s.t.ziroom.com/crm-reserve/busopp/queryBOListByKeeper
	 **/
	public JSONObject s_queryBOListByKeeper(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keeperId", KeeperGlobalParas.ownerKeeperCode);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("trackState", KeeperGlobalParas.trackState);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));

		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			System.out.println("生活：" + responseData);
			if (responseData.equals("[]")) {

				logST.info("s_queryBOListByKeeper的responseData返回值为[]" + responseJson);
			} else {
				JSONArray array = JSONArray.fromObject(responseData);
				String ownerName = array.getJSONObject(0).getString("ownerName");// 天天
				KeeperGlobalParas.houseId = array.getJSONObject(0).getString("houseId");
				KeeperGlobalParas.busOppId = array.getJSONObject(0).getString("busOppId");
				logST.info("s_queryBOListByKeeper返回值" + responseJson);
			}
		} else {
			logST.info("s_queryBOListByKeeper返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 点击跟进商机
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/house/queryHouseInfo
	 **/
	public JSONObject s_queryHouseInfo(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("houseId", KeeperGlobalParas.houseId);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_queryHouseInfo的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_queryHouseInfo返回值" + responseJson);
			}
		} else {
			logST.info("s_queryHouseInfo返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 新增跟进
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/common/getConfigInfo
	 **/
	public JSONObject s_getConfigInfo(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logST.info("s_getConfigInfo的responseData返回值为[]" + responseJson);
			} else {
				logST.info("s_getConfigInfo返回值" + responseJson);
			}
		} else {
			logST.info("s_getConfigInfo返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 保存跟踪
	 * @author jihaibo http://s.t.ziroom.com/crm-reserve/busopp/addTrackByKeeper
	 **/
	public JSONObject s_addTrackByKeeper(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowDate=dateFormat.format(new Date()); //2016-09-06 15:11:18
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("trackTime", nowDate);
		map.put("trackResult", KeeperGlobalParas.trackResult);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("remark", KeeperGlobalParas.remark);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("busOppId",KeeperGlobalParas.busOppId);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("timestamp", timeStamp);
		map.put("price", KeeperGlobalParas.trackPrice);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperId", KeeperGlobalParas.ownerKeeperCode);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("uuid", uuid);
		map.put("keeperName", KeeperGlobalParas.keeperName);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
//		for(Map.Entry<String, String> m:map.entrySet())
//		{
//			System.out.println(m.getKey()+":"+m.getValue());
//		}
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_addTrackByKeeper的返回值为" + responseJson);
		} else {
			logST.info("s_addTrackByKeeper返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}
	
	/**
	 * @description: 点击实勘记录
	 * @author jihaibo   http://s.t.ziroom.com/crm-reserve/house/getSurveyInfoByHouseId
	 **/
	public JSONObject s_getSurveyInfoByHouseId(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("houseId", KeeperGlobalParas.houseId);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_getSurveyInfoByHouseId的返回值为" + responseJson);
		} else {
			logST.info("s_getSurveyInfoByHouseId返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);

		return responseJson;
	}
	
	
	/**
	 * @description: 添加/查看实勘照片
	 * @author jihaibo  http://s.t.ziroom.com/crm-reserve/house/getPicListByHouseId
	 **/
	public JSONObject s_getPicListByHouseId(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("houseId", KeeperGlobalParas.houseId);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_getPicListByHouseId的返回值为" + responseJson);
		} else {
			logST.info("s_getPicListByHouseId返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		return responseJson;
	}
	/**
	 * @description: 实勘上传照片
	 * @author jihaibo  http://s.t.ziroom.com/crm-reserve/house/submitHousePic
	 **/
	public JSONObject s_submitHousePic(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("housePicList",KeeperGlobalParas.housePicList);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("keeperName", KeeperGlobalParas.keeperName);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("keeperId", KeeperGlobalParas.ownerKeeperCode);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("houseId", KeeperGlobalParas.houseId);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_submitHousePic的返回值为" + responseJson);
		} else {
			logST.info("s_submitHousePic返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}
	
	
	/**
	 * @description: 保存实勘信息
	 * @author jihaibo  http://s.t.ziroom.com/crm-reserve/house/saveSurvey
	 **/
	public JSONObject s_saveSurvey(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		// 通州格兰晴天7号楼4单元6层461
				String address = KeeperGlobalParas.districtName + KeeperGlobalParas.resblockName + KeeperGlobalParas.building_no
						+ KeeperGlobalParas.unit + KeeperGlobalParas.floor + KeeperGlobalParas.room_no;
				
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("address", address);
		map.put("source", KeeperGlobalParas.source);
		map.put("toilet", KeeperGlobalParas.toilet);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("state",KeeperGlobalParas.state);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("keeperName", KeeperGlobalParas.keeperName);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("decorateType",KeeperGlobalParas.decorateType);
		map.put("keeperId", KeeperGlobalParas.ownerKeeperCode);
		map.put("changeToNum",KeeperGlobalParas.changeToNum);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("area", KeeperGlobalParas.area);
		map.put("roomList",KeeperGlobalParas.roomList);
		map.put("changeFromNum",KeeperGlobalParas.changeFromNum);
		map.put("houseId", KeeperGlobalParas.houseId);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_saveSurvey的返回值为" + responseJson);
		} else {
			logST.info("s_saveSurvey返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}
	
	/**
	 * @description: 提交实勘信息
	 * @author jihaibo  http://s.t.ziroom.com/crm-reserve/house/commitSurvey
	 **/
	public JSONObject s_commitSurvey(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		// 通州格兰晴天7号楼4单元6层461
				String address = KeeperGlobalParas.districtName + KeeperGlobalParas.resblockName + KeeperGlobalParas.building_no
						+ KeeperGlobalParas.unit + KeeperGlobalParas.floor + KeeperGlobalParas.room_no;
				
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("address", address);
		map.put("source", KeeperGlobalParas.source);
		map.put("toilet", KeeperGlobalParas.toilet);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("state", KeeperGlobalParas.state);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("keeperName", KeeperGlobalParas.keeperName);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("keeperPhone", KeeperGlobalParas.keeperPhone);
		map.put("decorateType", KeeperGlobalParas.decorateType);	
		map.put("changeToNum",KeeperGlobalParas.changeToNum );
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("area", KeeperGlobalParas.area);
		map.put("roomList",KeeperGlobalParas.roomList);
		map.put("changeFromNum",KeeperGlobalParas.changeFromNum);
		map.put("houseId", KeeperGlobalParas.houseId);
		map.put("sign", CommonFunction.getCrmSign(map));
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_commitSurvey的返回值为" + responseJson);
		} else {
			logST.info("s_commitSurvey返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		getHouseInfoData();
		getViewJsonFZData();
		getViewJsonZZData();
		return responseJson;
		}

	/**
	 * @description: 核銷商機
	 * @author wujing http://s.t.ziroom.com/crm-reserve/busopp/cancelBo
	 **/
	public JSONObject s_cancelBo(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("busOppId",KeeperGlobalParas.busOppId);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("timestamp", timeStamp);
//		map.put("price", KeeperGlobalParas.trackPrice);
		map.put("source", KeeperGlobalParas.source);
		map.put("cancelRemark",KeeperGlobalParas.cancelRemark);//备注
		map.put("cancelReason",KeeperGlobalParas.cancelReason);//核销原因
		map.put("keeperId", KeeperGlobalParas.ownerKeeperCode);
		map.put("keeperCode", KeeperGlobalParas.ownerKeeperCode);
		map.put("uuid", uuid);
		map.put("houseId",KeeperGlobalParas.houseId);
		map.put("keeperName", KeeperGlobalParas.keeperName);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
//		for(Map.Entry<String, String> m:map.entrySet())
//		{
//			System.out.println(m.getKey()+":"+m.getValue());
//		}
		// 请求接口返回值
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logST.info("s_cancelBo的返回值为" + responseJson);
		} else {
			logST.info("s_cancelBo返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值" + responseJson);
		logST.info(requestUrl);
		logST.info(map.toString());
		logST.info("返回值" + responseJson);
		return responseJson;
	}

	/**
	 * @description: 传出HouseInfo数据
	 * @author wujing
	 **/
	public JSONObject getHouseInfoData() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("unit", KeeperGlobalParas.unit);
		map.put("villageId", KeeperGlobalParas.villageId);
		map.put("districtName", KeeperGlobalParas.districtName);
		map.put("districtId",KeeperGlobalParas.districtId);
		map.put("floor", KeeperGlobalParas.floor);
		map.put("address", KeeperGlobalParas.address);
		map.put("secondSource", KeeperGlobalParas.secondSource);
//		map.put("price", KeeperGlobalParas.trackPrice);
		map.put("roomNum", KeeperGlobalParas.room_no);
		map.put("buildNum",KeeperGlobalParas.building_no);
		map.put("firstSource",KeeperGlobalParas.firstSourceId);
		map.put("villageName", KeeperGlobalParas.resblockName);

		KeeperGlobalParas.houseinfo = JSONObject.fromObject(map);
		System.out.println(KeeperGlobalParas.houseinfo);
		logST.info("houseinfo:" + KeeperGlobalParas.houseinfo);

		return KeeperGlobalParas.houseinfo;
	}
	/**
	 * @description: 传出viewInfo数据
	 * @author wujing
	 **/
	public JSONObject getViewJsonFZData() {
		String ViewJson = "{\"diningRoom\":\"0\",\"afterRoomNum\":\"3\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"1\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"productVesion\":\"1008\"}";
		KeeperGlobalParas.viewJsonFZ = JSONObject.fromObject(ViewJson);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("usermobile", KeeperGlobalParas.ownerPhone);
		map.put("account", KeeperGlobalParas.rentHouseUserAccount);
		map.put("district", KeeperGlobalParas.districtName);
		map.put("resblock", KeeperGlobalParas.resblockName);
		JSONObject b  = JSONObject.fromObject(map);
		KeeperGlobalParas.viewJsonFZ.putAll(b);

		System.out.println(KeeperGlobalParas.viewJsonFZ);
		logST.info("viewJsonFZ:" + KeeperGlobalParas.viewJsonFZ);

		return KeeperGlobalParas.viewJsonFZ;
	}
	/**
	 * @description: 传出viewInfo整租数据
	 * @author wujing
	 **/
	public JSONObject getViewJsonZZData() {
//		String ViewJson = "{\"diningRoom\":\"0\",\"afterRoomNum\":\"2\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"3\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"productVesion\":\"1003\"}";
		String ViewJson = "{\"diningRoom\":\"1\",\"afterRoomNum\":\"装修后房间数\",\"bedRoom\":\"1\",\"houseLayout\":\"2\",\"citycode\":\"110000\",\"livingRoom\":\"1\",\"point\":\"2\",\"washRoom\":\"2\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"productVesion\":\"1009\",}";
		KeeperGlobalParas.viewJsonZZ = JSONObject.fromObject(ViewJson);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("usermobile", KeeperGlobalParas.ownerPhone);
		map.put("account", KeeperGlobalParas.rentHouseUserAccount);
		map.put("district", KeeperGlobalParas.districtName);
		map.put("resblock", KeeperGlobalParas.resblockName);
		JSONObject b  = JSONObject.fromObject(map);
		KeeperGlobalParas.viewJsonZZ.putAll(b);

		System.out.println(KeeperGlobalParas.viewJsonZZ);
		logST.info("viewJsonZZ:" + KeeperGlobalParas.viewJsonZZ);

		return KeeperGlobalParas.viewJsonZZ;
	}

}

