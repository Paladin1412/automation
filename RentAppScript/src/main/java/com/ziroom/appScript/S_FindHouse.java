package com.ziroom.appScript;

import java.util.HashMap;

import com.ziroom.confManagement.commonMethods.HttpRequest;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import com.ziroom.utils.CommonFunction;
import net.sf.json.JSONObject;
import com.ziroom.confManagement.commonMethods.RentAppGlobalParas;

public class S_FindHouse {
	private static final Logger logFH = Logger.getLogger(S_FindHouse.class);
	HttpRequest hRequest = new HttpRequest();
	S_Me me = new S_Me();

	/***合租@
	 * [合租]进入单个房源请求api
	 * http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=detailShow
	 * @param requestUrl
	 * @param
	 * @return
	 */
	public JSONObject s_houseDetail(String requestUrl) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10()));
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", CommonFunction.getTimeStampOf10());
		map.put("house_code", RentAppGlobalParas.house_code);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		JSONObject responseJson =  hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		// 返回的参数，提供下一个接口使用
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logFH.info("s_houseDetail中data中数据为空");
			} else {

				String sub_responseData = responseData.substring(1, responseData.length() - 1);
				RentAppGlobalParas.ReturnHouseType = JSONObject.fromObject(sub_responseData).getString("house_type");
				RentAppGlobalParas.house_id=JSONObject.fromObject(sub_responseData).getString("house_id");
			
				logFH.info("s_houseDetail返回值：" + responseJson);
			}

		} else {
			logFH.info("s_houseDetail服务器返回值-------->>>>>>>>>为空");
		}

		
		return responseJson;
	}

	/****
	 * http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=
	 * searchHouse_suggest
	 * 
	 * @param requestUrl
	 * 
	 * @return
	 */
	public JSONObject s_suggestFindHouse(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf10();
		String sign = CommonFunction.getAppSign(RentAppGlobalParas.login_uid, timeStamp);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("max_lng", RentAppGlobalParas.max_lng);
		map.put("sort", RentAppGlobalParas.sort);
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

		JSONObject responseJson =  hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		if (responseJson != null) {
			logFH.info("s_suggestFindHouse返回值" + responseJson);
		} else {
			logFH.info("s_suggestFindHouse返回值------->>>>>>为空");
		}
		
		return responseJson;
	}

	/****
	 * http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=get_buildingList
	 * 
	 * @param requestUrl
	 * 
	 * @return
	 */
	public JSONObject s_commutingFindHouse(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf10();
		String sign = CommonFunction.getAppSign(RentAppGlobalParas.login_uid, timeStamp);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("max_lng", RentAppGlobalParas.max_lng);
		map.put("min_area", RentAppGlobalParas.min_area);
		map.put("min_lat", RentAppGlobalParas.min_lat);
		map.put("timestamp", timeStamp);
		map.put("house_tags[1]", RentAppGlobalParas.house_tags1);
		map.put("max_area", RentAppGlobalParas.max_area);
		map.put("bizcircle_code", RentAppGlobalParas.bizcircle_code);
		map.put("house_type", RentAppGlobalParas.detailHouseType);
		map.put("max_lat", RentAppGlobalParas.max_lat);
		map.put("house_tags[3]", RentAppGlobalParas.house_tag3);
		map.put("huxing", RentAppGlobalParas.huxing);
		map.put("house_tags[5]", RentAppGlobalParas.house_tag5);
		map.put("tran_sport", RentAppGlobalParas.tranSport);
		map.put("min_lng", RentAppGlobalParas.min_lng);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("min_rentfee", RentAppGlobalParas.min_rentfee);
		map.put("subway_station_name", RentAppGlobalParas.subway_station_name);
		map.put("sign", sign);
		map.put("house_tags[2]", RentAppGlobalParas.house_tags2);
		map.put("house_tags[0]", RentAppGlobalParas.house_tags0);
		map.put("max_rentfee", RentAppGlobalParas.max_rentfee);
		map.put("house_tags[4]", RentAppGlobalParas.house_tags4);
		map.put("commute_name", RentAppGlobalParas.commuteName);
		map.put("heating", RentAppGlobalParas.heating);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("is_commute", RentAppGlobalParas.isCommute);
		map.put("longitude_and_latitude", RentAppGlobalParas.longitudeAndLatitude);
		map.put("work_time", RentAppGlobalParas.workTime);
		map.put("house_tags[6]", RentAppGlobalParas.house_tags6);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);
		map.put("model", RentAppGlobalParas.model);

		JSONObject responseJson =  hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		if (responseJson != null) {
			logFH.info("s_commutingFindHouse返回值" + responseJson);
		} else {
			logFH.info("s_commutingFindHouse返回值------->>>>>>为空");
		}
		
		return responseJson;
	}

	/****
	 * 菜单点击找房请求接口 http://s.ziroom.com/crm-reserve/packSign/getPackState
	 * 
	 * @param requestUrl
	 * @return
	 */
	public JSONObject s_findHouse(String requestUrl) {
		String sign = CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10());
		String timeStamp = CommonFunction.getTimeStampOf10();
		String uuid = RentAppGlobalParas.appid + "_" + CommonFunction.getTimeStampOf10();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", sign);
		map.put("timestamp", timeStamp);
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("appId", RentAppGlobalParas.appid);
		map.put("source", RentAppGlobalParas.source);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("uuid", uuid);
		map.put("appType", RentAppGlobalParas.appType);
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		if (responseJson != null) {
			logFH.info("s_findHouse返回值" + responseJson);
		} else {
			logFH.info("s_findHouse返回值------->>>>>>为空");
		}
		
		return responseJson;
	}

	/***
	 * 找房请求第一个接口 http://s.ziroom.com/crm-reserve/packSign/getPackState
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_findhouse1(String requestUrl) {
		String uuid = RentAppGlobalParas.appid + "_" + CommonFunction.getTimeStampOf13();
		HashMap<String, String> map = new HashMap<String, String>();
		// map.put("sign", CommonFunction.getAppSign(GlobalParameter.login_uid,
		// CommonFunction.getTimeStampOf13()));
		map.put("uid", RentAppGlobalParas.login_uid);
		map.put("timestamp", CommonFunction.getTimeStampOf13());
		map.put("appId", RentAppGlobalParas.appid);
		map.put("source", RentAppGlobalParas.source);
		map.put("imei", RentAppGlobalParas.imei);
		map.put("cityCode", RentAppGlobalParas.city_code);
		map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
		map.put("osType", RentAppGlobalParas.osType);
		map.put("uuid", uuid);
		map.put("appType", RentAppGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson =  hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		if (responseJson != null) {
			logFH.info("s_findhouse1返回值" + responseJson);
		} else {
			logFH.info("s_findhouse1返回值------->>>>>>为空");
		}
		
		return responseJson;
	}

	// /***
	// * 点击房源第一个接口
	// * http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=detailShowZZ
	// *
	// * @author jihaibo
	// * @param requestUrl
	// * @return JSONObject
	// */
	// public JSONObject s_wr_houseSource1(String requestUrl) {
	// HashMap<String, String> map = new HashMap<String, String>();
	// map.put("sign", CommonFunction.getAppSign(GlobalParameter.login_uid,
	// CommonFunction.getTimeStampOf10()));
	// map.put("uid", GlobalParameter.login_uid);
	// map.put("timestamp", CommonFunction.getTimeStampOf10());
	// map.put("city_code", GlobalParameter.city_code);
	// map.put("house_code", GlobalParameter.house_id);
	// map.put("os", GlobalParameter.os);
	// map.put("app", GlobalParameter.app);
	// map.put("model", GlobalParameter.model);
	// map.put("network", GlobalParameter.network);
	// map.put("ip", GlobalParameter.ip);
	//
	// JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
	// log.info("s_wr_houseSource1返回值" + responseJson);
	//
	// if (responseJson != null) {
	// String responseData = responseJson.getString("data");
	//
	// if (responseData.equals("[]")) {
	// log.info("s_wr_houseSource1返回data值为空");
	//
	// } else {
	// String sub_responseData = responseData.substring(1, responseData.length()
	// - 1);
	// GlobalParameter.resblock_id =
	// JSONObject.fromObject(sub_responseData).getString("resblock_id");
	// log.debug("resblock_id的值为" + GlobalParameter.resblock_id);
	// log.info("s_wr_houseSource1服务器返回的值" + responseJson);
	// }
	// } else {
	// log.info("s_wr_houseSource1服务器返回的值------->>>>>>>为空");
	// }
	//
	// return responseJson;
	// }

	// /***
	// * 点击房源第二个接口 http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=
	// * get_houseList_recommend_by_xiaoqu
	// *
	// * @author jihaibo
	// * @param requestUrl
	// * @return JSONObject
	// */
	// public JSONObject s_wr_houseSource2(String requestUrl) {
	//
	// HashMap<String, String> map = new HashMap<String, String>();
	// map.put("sign", CommonFunction.getAppSign(GlobalParameter.login_uid,
	// CommonFunction.getTimeStampOf10()));
	// map.put("uid", GlobalParameter.login_uid);
	// map.put("timestamp", CommonFunction.getTimeStampOf10());
	// map.put("city_code", GlobalParameter.city_code);
	// map.put("show_house_code", GlobalParameter.house_id);
	// map.put("building_code", GlobalParameter.resblock_id);
	// map.put("start", GlobalParameter.start);
	// map.put("length", GlobalParameter.length);
	// map.put("os", GlobalParameter.os);
	// map.put("app", GlobalParameter.app);
	// map.put("model", GlobalParameter.model);
	// map.put("network", GlobalParameter.network);
	// map.put("ip", GlobalParameter.ip);
	//
	// JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
	//
	// if (responseJson != null) {
	// log.info("s_wr_houseSource2返回值" + responseJson);
	// } else {
	// log.info("s_wr_houseSource2返回值------->>>>>>为空");
	// }
	//
	// return responseJson;
	// }

	/***@
	 * 合租 找房中位置找房获取地铁
	 * http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=get_subway_new
	 * 
	 * @param requestUrl
	 * @return
	 */
	public JSONObject s_getSubway(String requestUrl) {
		String timestamp = CommonFunction.getTimeStampOf10();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.city_code, timestamp));
		map.put("uid", "0");
		map.put("timestamp", timestamp);
		map.put("os", RentAppGlobalParas.os);
		map.put("app", RentAppGlobalParas.app);
		map.put("model", RentAppGlobalParas.model);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("network", RentAppGlobalParas.network);
		map.put("ip", RentAppGlobalParas.ip);

		JSONObject responseJson =  hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		if (responseJson != null) {
			logFH.info("s_getSubway返回值" + responseJson);
		} else {
			logFH.info("s_getSubway返回值------->>>>>>为空");
		}
		
		return responseJson;
	}

	/***@
	 * 合租 获取商圈（区） http://interfaces.ziroom.com/index.php?_p=api_mobile&_a=
	 * get_bizcircleList_new
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_getBizCircle(String requestUrl) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.city_code, CommonFunction.getTimeStampOf10()));
		map.put("uid", "0");
		map.put("timestamp", CommonFunction.getTimeStampOf10());
		//map.put("house_type", GlobalParameter.house_type);
		map.put("house_code", RentAppGlobalParas.house_code);
		map.put("is_renew", RentAppGlobalParas.isRenew);
		map.put("city_code", RentAppGlobalParas.city_code);
		map.put("house_id", RentAppGlobalParas.house_id);
		JSONObject responseJson =  hRequest.getPostReturnValue(requestUrl, map);
		Reporter.log(map.toString());
		Reporter.log(requestUrl);
		Reporter.log("返回值："+responseJson);
		if (responseJson != null) {
			logFH.info("s_okAboveInfo1返回值" + responseJson);
		} else {
			logFH.info("s_okAboveInfo1返回值------->>>>>>为空");
		}
		
		return responseJson;
	}

	///////////////////////////////////////////////////////////////////////////
	/// 整租api
	////////////////////////////////////////////////////////////////////////

	/***
	 * 整租 获取商圈（区）
	 * http://interfaces.zz.qa.ziroom.com//zz/house/list-promotion.json?
	 * uid=7a54a7b7-b15d-c335-486e-ca3a58482182&face=south&model=HTC+D816t&sort=
	 * &
	 * app_version=4.4.3&network=WIFI&timestamp=1471308826&subway=&style=fresh&
	 * page=1&bizcircle=611100412&clng=&tags=new-resblock&os=android:4.4.2&
	 * keywords=&imei=352248061569009&resblock_id=&bedroom=two-bedroom&
	 * ip=192.168.1.189&size=10&sign=9df71ac6b04ea37c8b6633af7254b4fe&
	 * configs=smart-lock&price=5000,6000&sign_open=1&city_code=110000&clat=
	 * 
	 * @author jihaibo
	 * @param requestUrl
	 * @return JSONObject
	 */
	public JSONObject s_wr_FindHouseOfGeneral(String requestUrl) {

//		String requestParas = "uid=" + GlobalParameter.login_uid + "&face=" + GlobalParameter.face + "&model="
//				+ GlobalParameter.model + "&sort=&app_version=" + GlobalParameter.appVersionStr + "&network="
//				+ GlobalParameter.network + "&timestamp=" + time + "&subway=&style=" + GlobalParameter.style + "&page="
//				+ GlobalParameter.page + "&bizcircle=611100412&clng=&tags=" + GlobalParameter.tags + "&os="
//				+ GlobalParameter.os + "&keywords=&imei=" + GlobalParameter.imei + "&resblock_id=&bedroom="
//				+ GlobalParameter.bedroom + "&ip=" + GlobalParameter.ip + "&size=" + GlobalParameter.size
//				+ "&sign=348c0b30a6aa80867bc475d6b4215591&configs=" + GlobalParameter.configs + "&price="
//				+ GlobalParameter.betweenPrice + "&sign_open=" + GlobalParameter.signOpen + "&city_code="
//				+ GlobalParameter.city_code + "&clat=";
		 //String url=requestUrl+requestParas;
		
		
		
		//System.out.println("请求地址：" + url);
		//JSONObject responseJson = hRequest.getGetReturn(url);
		 

		return null;
	}
}
