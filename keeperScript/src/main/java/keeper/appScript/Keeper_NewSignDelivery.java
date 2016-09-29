package keeper.appScript;

import com.ziroom.utils.CommonFunction;
import keeper.confManagement.commonMethods.HttpRequest;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;

import java.util.HashMap;
import java.util.Map;

/****
 * 扫码二维码新签物业交割API
 * 
 * @author jihaibo
 *
 */
public class Keeper_NewSignDelivery {
	private static final Logger logNSTD = Logger.getLogger(Keeper_NewSignDelivery.class);
	HttpRequest hRequest = new HttpRequest();
	
	
	////////////////////////////
	/***
	 * 管家扫二维码
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/getConfirmInfo
	 * @return
	 */
	public JSONObject s_keeperScan(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + "_" + timeStamp;
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
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("gjAccount", KeeperGlobalParas.serviceUserName);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logNSTD.info("s_keeperScan返回值" + responseJson);
		} else {
			logNSTD.info("s_keeperScan返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}

	/***
	 * 完成新签确认
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/confirmOuter
	 * @return
	 */
	public JSONObject s_CompleteSignAffirm(String requestUrl) {
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + "_" + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("outerAccount", KeeperGlobalParas.serviceUserName);
		map.put("contractCode", RentAppGlobalParas.contractCode);
		map.put("gjAccount", KeeperGlobalParas.serviceUserName);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logNSTD.info("s_CompleteSignAffirm返回值" + responseJson);
		} else {
			logNSTD.info("s_CompleteSignAffirm返回值------->>>>>>为空");
		}
		Reporter.log(requestUrl);
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}

	/***
	 * 获取配置物品
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/getGoodsList
	 * @return
	 */
	public JSONObject s_getConfigGoods(String requestUrl) {
		Reporter.log(requestUrl);

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
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			KeeperGlobalParas.configGoodsNext_Data = JSONObject.fromObject(responseJson.getString("data"))
					.getString("goods_list");
			
			logNSTD.info("s_getConfigGoods返回值" + responseJson);

		} else {
			logNSTD.info("s_getConfigGoods返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}

	/***
	 * 添加物品配置2-获取房间名字
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/getRooms
	 * @return
	 */
	public JSONObject s_addConfigGoods2(String requestUrl) {
		Reporter.log(requestUrl);

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
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			String subData = responseData.substring(1, responseData.length() - 1);
			if (responseData.equals("[]")) {
				logNSTD.info("s_addConfigGoods2中data数据为空");
			} else {
				KeeperGlobalParas.roomName = JSONObject.fromObject(subData).getString("roomName");
				KeeperGlobalParas.roomId = JSONObject.fromObject(subData).getString("room_id");
				KeeperGlobalParas.roomType = JSONObject.fromObject(subData).getString("type");
			}
			logNSTD.info("s_addConfigGoods2返回值" + responseJson);
		}

		else

		{
			logNSTD.info("s_addConfigGoods2返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;

	}

	/***
	 * 添加物品配置(获取大类物品)
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/getUniversalGoods
	 * @return
	 */
	public JSONObject s_addConfigGoods1(String requestUrl) {
		Reporter.log(requestUrl);

		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("generalOrType", KeeperGlobalParas.generalOrType);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logNSTD.info("s_addConfigGoods返回值" + responseJson);
		} else {
			logNSTD.info("s_addConfigGoods返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}

	/***
	 * 添加物品配置(获取物品)
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/getUniversalGoods
	 * @return
	 */
	public JSONObject s_getUniversalGoods(String requestUrl) {
		Reporter.log(requestUrl);

		KeeperGlobalParas.generalOrType = "false";
		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("generalOrType", KeeperGlobalParas.generalOrType);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("typeId", KeeperGlobalParas.typeId);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		   
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		KeeperGlobalParas.generalOrType = "true";
		if (responseJson != null) {
			logNSTD.info("s_getUniversalGoods返回值" + responseJson);
		} else {
			logNSTD.info("s_getUniversalGoods返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);

		return responseJson;
	}

	

	/***
	 * 物品配置下一步
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/updateGoods
	 * @return
	 */
	public JSONObject s_configGoodsNext(String requestUrl) {
		Reporter.log(requestUrl);

		JSONArray object = JSONArray.fromObject(KeeperGlobalParas.configGoodsNext_Data);
		HashMap<String, String> addGoodsList = new HashMap<String, String>();
		addGoodsList.put('"' + "belong" + '"', KeeperGlobalParas.belong);
		addGoodsList.put('"' + "brand" + '"', KeeperGlobalParas.brand);
		addGoodsList.put('"' + "goodsType" + '"', KeeperGlobalParas.goodsType);
		addGoodsList.put('"' + "model" + '"', KeeperGlobalParas.goodsModel);
		addGoodsList.put('"' + "name" + '"', KeeperGlobalParas.goodsName);
		addGoodsList.put('"' + "roomName" + '"', '"' + KeeperGlobalParas.roomName + '"');
		addGoodsList.put('"' + "room_id" + '"', '"' + KeeperGlobalParas.roomId + '"');
		addGoodsList.put('"' + "subType" + '"', KeeperGlobalParas.subType);
		addGoodsList.put('"' + "sums" + '"', KeeperGlobalParas.sums);
		addGoodsList.put('"' + "type" + '"', '"' + KeeperGlobalParas.roomType + '"');
		addGoodsList.put('"' + "typeId" + '"', KeeperGlobalParas.typeId);
		addGoodsList.put('"' + "use_status" + '"', KeeperGlobalParas.useStatus);
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[");
		for (int i = 0; i < object.size(); i++) {
			if (i == object.size() - 1) {
				sBuffer.append(CommonFunction.jsonFieldSort(object.getJSONObject(i).discard("goodsprice")));
				sBuffer.append("]");
			} else {
				sBuffer.append(CommonFunction.jsonFieldSort(object.getJSONObject(i).discard("goodsprice")));
				sBuffer.append(",");
			}
		}

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
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("goods_list", sBuffer.toString());
		map.put("sign", CommonFunction.getCrmSign(map));
 
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logNSTD.info("s_configGoodsNext返回值" + responseJson);
		} else {
			logNSTD.info("s_configGoodsNext返回值------->>>>>>为空");
		}

		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}

	/***
	 * 获取钥匙凭证
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/getPropertyKeyCards
	 * @return
	 */
	public JSONObject s_getPropertyKeyCards(String requestUrl) {
		Reporter.log(requestUrl);

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
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			KeeperGlobalParas.unitKeyisOver = JSONObject.fromObject(responseData).getString("unitKeyisOver");
			KeeperGlobalParas.doorKeyNums = JSONObject.fromObject(responseData).getString("doorKeyNums");
			KeeperGlobalParas.letterBoxKeyisOver = JSONObject.fromObject(responseData).getString("letterBoxKeyisOver");
			KeeperGlobalParas.letterBoxKeyNums = JSONObject.fromObject(responseData).getString("letterBoxKeyNums");
			KeeperGlobalParas.electricCardNums = JSONObject.fromObject(responseData).getString("electricCardNums");
			KeeperGlobalParas.electricCardisOver = JSONObject.fromObject(responseData).getString("electricCardisOver");
			KeeperGlobalParas.unitKeyNums = JSONObject.fromObject(responseData).getString("unitKeyNums");
			KeeperGlobalParas.guardCardisOver = JSONObject.fromObject(responseData).getString("guardCardisOver");
			KeeperGlobalParas.gasCardisOver = JSONObject.fromObject(responseData).getString("gasCardisOver");
			KeeperGlobalParas.gasCardNums = JSONObject.fromObject(responseData).getString("gasCardNums");
			KeeperGlobalParas.warterCardNums = JSONObject.fromObject(responseData).getString("warterCardNums");
			KeeperGlobalParas.warterCardisOver = JSONObject.fromObject(responseData).getString("warterCardisOver");
			KeeperGlobalParas.doorKeyisOver = JSONObject.fromObject(responseData).getString("doorKeyisOver");
			KeeperGlobalParas.guardCardNums = JSONObject.fromObject(responseData).getString("gasCardNums");
			logNSTD.info("s_getPropertyKeyCards返回值" + responseJson);
		} else {
			logNSTD.info("s_getPropertyKeyCards返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}
	/***
	 * 提交钥匙凭证
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/
	 *            updatePropertyKeyCards
	 * @return
	 */
	public JSONObject s_updatePropertyKeyCards(String requestUrl) {
		Reporter.log(requestUrl);

		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("unitKeyisOver", KeeperGlobalParas.unitKeyisOver);
		map.put("doorKeyNums", KeeperGlobalParas.versionInt);
		map.put("letterBoxKeyisOver", KeeperGlobalParas.letterBoxKeyisOver);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("imei", KeeperGlobalParas.appid);
		map.put("letterBoxKeyNums", KeeperGlobalParas.letterBoxKeyNums);
		map.put("electricCardNums", KeeperGlobalParas.electricCardNums);
		map.put("electricCardisOver", KeeperGlobalParas.electricCardisOver);
		map.put("unitKeyNums", KeeperGlobalParas.unitKeyNums);
		map.put("guardCardisOver", KeeperGlobalParas.guardCardisOver);
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("gasCardNums", KeeperGlobalParas.gasCardNums);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("gasCardisOver", KeeperGlobalParas.gasCardisOver);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("warterCardNums", KeeperGlobalParas.warterCardNums);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("warterCardisOver", KeeperGlobalParas.warterCardisOver);
		map.put("doorKeyisOver", KeeperGlobalParas.doorKeyisOver);
		map.put("guardCardNums", KeeperGlobalParas.guardCardNums);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logNSTD.info("s_updatePropertyKeyCards返回值" + responseJson);
		} else {
			logNSTD.info("s_updatePropertyKeyCards返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}

	/***
	 * 获取生活费
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/getExpenses
	 * @return
	 */
	public JSONObject s_getExpenses(String requestUrl) {
		Reporter.log(requestUrl);

		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.appid);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			if (responseData.equals("[]")) {
				logNSTD.info("s_addConfigGoods2中data数据为空");
			} else {
				String fees_listData = JSONObject.fromObject(responseData).getString("fees_list");
				KeeperGlobalParas.Jsonfeeslist = JSONArray.fromObject(fees_listData);
				
				for (int i = 0; i < KeeperGlobalParas.Jsonfeeslist.size(); i++) {
					String type = JSONObject.fromObject(KeeperGlobalParas.Jsonfeeslist.getString(i)).getString("type");
					if (type.equals("冷水一") || type.equals("中水一")) {
						KeeperGlobalParas.Jsonfeeslist.getJSONObject(i).put("displayNums", KeeperGlobalParas.waterCoolDisplayNums);
						KeeperGlobalParas.Jsonfeeslist.getJSONObject(i).put("displayNums_pic", KeeperGlobalParas.displayNums_pic);	
					} else if (type.equals("电表") ||type.equals("厨房煤气")){ 
						KeeperGlobalParas.Jsonfeeslist.getJSONObject(i).put("balance", KeeperGlobalParas.electricBalance);
						KeeperGlobalParas.Jsonfeeslist.getJSONObject(i).put("balance_pic", KeeperGlobalParas.displayNums_pic);
						KeeperGlobalParas.Jsonfeeslist.getJSONObject(i).put("displayNums", KeeperGlobalParas.electricDisplayNums);
						KeeperGlobalParas.Jsonfeeslist.getJSONObject(i).put("displayNums_pic", KeeperGlobalParas.displayNums_pic);
						
					}
					 
				}
			}
			logNSTD.info("s_getExpenses返回值" + responseJson);
		} else {
			logNSTD.info("s_getExpenses返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
	}
	
	
	/***
	 * 提交生活费（物业交割）
	 * 
	 * @param requestUrl
	 *            http://s.t.ziroom.com/crm-reserve/property/updateExpenses
	 * @return
	 */
	public JSONObject s_updateExpenses(String requestUrl) {
		Reporter.log(requestUrl);

		String timeStamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timeStamp;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appType", KeeperGlobalParas.appType);
		map.put("source", KeeperGlobalParas.source);
		map.put("fees_list",KeeperGlobalParas.Jsonfeeslist.toString());
		map.put("keeperCode", KeeperGlobalParas.serviceUserName);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("imei", KeeperGlobalParas.appid);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("uuid", uuid);
		map.put("timestamp", timeStamp);
		map.put("appId", KeeperGlobalParas.appid);
		map.put("contract_code", RentAppGlobalParas.contractCode);
		map.put("cityCode", KeeperGlobalParas.city_code);
		map.put("sign", CommonFunction.getCrmSign(map));
		for(Map.Entry<String, String> m:map.entrySet())
		{
			System.out.println(m.getKey()+":"+m.getValue());
		}
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			logNSTD.info("s_updateExpenses返回值" + responseJson);
		} else {
			logNSTD.info("s_updateExpenses返回值------->>>>>>为空");
		}
		Reporter.log(map.toString());
		Reporter.log("返回值"+responseJson);
		return responseJson;
		
		
	}
	
}
