package interfacescript.ValuationModel;

import keeper.appScript.Keeper_ValuationModelCommon;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 类说明 : M站录计价模型 流程 
 * 作者 : Elaine
 * 版本号 : V1.0
 */

public class ValuationModelFlow {
	/**
	 * @param ehrJson ehrJson：从ehr中获取管家信息接口返回值
	 * @param houseinfo houseinfo： 从房源宝中接口中获取房源信息
	 * @param viewJson viewJson: 界面元素
	 * @author : Elaine
	 */
	public static JSONObject modePriceFlow(JSONObject ehrJson,JSONObject houseinfo,JSONObject viewJson)
	{
		JSONObject result = new JSONObject();
		Keeper_ValuationModelCommon vmc = new Keeper_ValuationModelCommon();
		
		vmc.getUserInfo(ehrJson);		
		vmc.initViewJson(viewJson);
		String afterRoom = vmc.afterRoom;		
		
		// M站登陆
		boolean mlogin = vmc.mLogin();
		if (mlogin == false) {
			result.put("status", "false");
			result.put("error_message", "登陆失败");
			return result;
		}

		// 得到房源信息
		JSONObject gethouseInfo = vmc.houseInfo(houseinfo);
		if (gethouseInfo.get("status").equals("failure")) {
			result.putAll(gethouseInfo);
			return result;
		}		
		
		// 提交房源信息
		JSONObject commitHouseInfo = vmc.commitHouseInfo(houseinfo, gethouseInfo);
		if (commitHouseInfo.get("status").equals("failure")) {
			result.putAll(commitHouseInfo);
			return result;
		}
		// 得到房源编号
		String standardIdJson = JSONArray.fromObject(gethouseInfo.getString("data")).getString(0);
		String standardId = JSONObject.fromObject(standardIdJson).getString("standard_id");
		
		// 得到房间信息并提交
		for (int i = 0; i < Integer.parseInt(afterRoom); i++) {
			System.out.println(commitHouseInfo+"~~~~~~");
			JSONObject getRentPrice = vmc.getRentPrice(commitHouseInfo);
			if (getRentPrice.get("status").equals("failure")) {
				result.putAll(getRentPrice);
				return result;
			}
			System.out.println("=============================");
			System.out.println(getRentPrice+"~~~~~~");
			JSONObject commitRoomInfo = vmc.commitRoomInfo(getRentPrice, Integer.toString(i + 1));
			if (commitRoomInfo.get("status").equals("failure")) {
				result.putAll(commitRoomInfo);
				return result;
			}
		}
		
		// 提交公共区域配置
		JSONObject commitConfigInfo = vmc.commitConfigInfo();
		if (commitConfigInfo.get("status").equals("failure")) {
			result.putAll(commitConfigInfo);
			return result;
		}

		// 得到出房价
		JSONObject getHirePrice = vmc.getHirePrice();
		if (getHirePrice.get("status").equals("failure")) {
			result.putAll(getHirePrice);
			return result;
		}

		// 得到评估值
//		JSONObject getAssessResult = vmc.getAssessResult();
//		if (!(getAssessResult.get("returnStatusCode").equals("200"))) {
//			result.putAll(getAssessResult);
//			return result;
//		}
		
		// 提交信息
		JSONObject commitAssessInfo = vmc.commitAssessInfo();
		if (commitAssessInfo.get("status").equals("failure")) {
			result.putAll(commitAssessInfo);
			return result;
		}
		String assessCode = vmc.assessCode;
		
		vmc.updateAuditStatus();

		// 上报评估
		boolean assessView = vmc.assessView();
		if (assessView == false) {
			result.put("status", "false");
			result.put("error_message", "上报失败");
			return result;
		}

		// 保存
		JSONObject admitAssess = vmc.admitAssess();
		if (admitAssess.get("status").equals("failure")) {
			result.putAll(admitAssess);
			return result;
		}
		
		result.put("status", "success");
		result.put("error_message", "计价模型创建成功:"+ assessCode + "  房屋编号为:" + standardId);
		Map<String,String> map = new HashMap<String,String>();
		map.put("assessCode", assessCode);
		map.put("cityCode", vmc.cityCode);
		map.put("standardID", standardId);
		
		JSONObject jsonb = JSONObject.fromObject(map);
		result.put("data", jsonb.toString());
		return result;
	}
	
	
	/**
	 * @param ehrJson ehrJson：从ehr中获取管家信息接口返回值
	 * @param houseinfo houseinfo： 从房源宝中接口中获取房源信息
	 * @param viewJson viewJson: 界面元素
	 * @author : Elaine
	 */
	public static JSONObject modePriceZZFlow(JSONObject ehrJson,JSONObject houseinfo,JSONObject viewJson)
	{
		JSONObject result = new JSONObject();
		Keeper_ValuationModelCommon vmc = new Keeper_ValuationModelCommon();
		
		
		vmc.getUserInfo(ehrJson);		
		vmc.initViewJson(viewJson);		
		
		// 登陆 login.ziroom.com
		boolean login = vmc.login();
		if (login == false) {
			result.put("status", "false");
			result.put("error_message", "登陆login失败");
			return result;
		}
		
		JSONObject getEntireHirePrice = vmc.getEntireHirePrice();
		if (getEntireHirePrice.get("status").equals("failure")) {
			result.putAll(getEntireHirePrice);
			return result;
		}
		
		JSONObject getStandardId = vmc.getStandardId(houseinfo);
		String status = getStandardId.getJSONObject("responseHeader").getString("status");
		if (!status.equals("0")) {
			result.putAll(getStandardId);
			return result;
		}

		JSONObject savePriceMode = vmc.savePriceMode(houseinfo, getEntireHirePrice, getStandardId);
		if (savePriceMode.get("status").equals("failure")) {
			result.putAll(savePriceMode);
			return result;
		}
		
		vmc.getEntireConfigItems();

		JSONObject decorateInfo = vmc.decorateInfo(savePriceMode);
		if (decorateInfo.get("status").equals("failure")) {
			result.putAll(decorateInfo);
			return result;
		}
				
		JSONObject saveConfig = vmc.saveConfig(savePriceMode);
		if (saveConfig.get("status").equals("failure")) {
			result.putAll(saveConfig);
			return result;
		}		
		
		vmc.saveConfig(savePriceMode);
		
		JSONObject saveConfigInfo = vmc.saveConfigInfo();
		if (saveConfigInfo.get("status").equals("failure")) {
			result.putAll(saveConfigInfo);
			return result;
		}
		
		vmc.updateAuditStatus();
		
		boolean admitPriceMode = vmc.admitPriceMode();
		if (admitPriceMode == false) {
			result.put("status", "false");
			result.put("error_message", "上报失败");
			return result;
		}
		
		result.put("status", "success");
		result.put("error_message", vmc.standardId + "计价模型创建成功");
		Map<String,String> map = new HashMap<String,String>();
		String  assessCode = vmc.getAssessCode();
		map.put("assessCode", assessCode);
		map.put("cityCode", vmc.cityCode);
		map.put("standardID", vmc.standardId);
		
		JSONObject jsonb = JSONObject.fromObject(map);
		result.put("data", jsonb.toString());
		return result;
	}
}
