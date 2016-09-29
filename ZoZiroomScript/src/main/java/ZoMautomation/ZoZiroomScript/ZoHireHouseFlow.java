package ZoMautomation.ZoZiroomScript;
import net.sf.json.JSONObject;

/**
 * 
 * 类说明 : ZO录入收房合同流程
 * 作者 : Elaine
 * 版本号 : V1.0
 */

public class ZoHireHouseFlow { 	
	/**
	 * @param  ehrJson：从ehr中获取管家信息接口返回值
	 * @param  viewJson： 界面输入的参数
	 * @param  modePrice: 从计价模型中获取相关信息
	 * @author : Elaine
	 */
	public static JSONObject hireHouseFlow(JSONObject ehrJson,JSONObject modePrice,JSONObject viewJson,String address,JSONObject houseinfo){
		
		JSONObject result=new JSONObject();
		ZoHireHouseCommon common = new ZoHireHouseCommon();
		
		common.getUserInfo(ehrJson);
		common.initViewJson(viewJson);
		
		boolean zoLogin = common.zoLogin();
		if (zoLogin == false) {
			result.put("status", "false");
			result.put("error_message", "登陆失败");
			return result;
		}
		
		JSONObject hireNewSign = common.hireNewSign(modePrice);
		if (hireNewSign.get("status").equals("failure")) {
			result.putAll(hireNewSign);
			return result;
		}		
		
		JSONObject queryCustomerInfo = common.queryCustomerInfo();
		if (queryCustomerInfo.get("status").equals("failure")) {
			result.putAll(queryCustomerInfo);
			return result;
		}	
		
		JSONObject getHouseAssessInfo = common.getHouseAssessInfo();
		if (getHouseAssessInfo.get("status").equals("failure")) {
			result.putAll(getHouseAssessInfo);
			return result;
		}	
		
		JSONObject saveHireNewSignPage = common.saveHireNewSignPage(hireNewSign,queryCustomerInfo, address,getHouseAssessInfo);
		if (saveHireNewSignPage.get("status").equals("failure")) {
			result.putAll(saveHireNewSignPage);
			return result;
		}	
		
		JSONObject findHireProperty = common.findHireProperty(saveHireNewSignPage);
		if (findHireProperty.get("status").equals("failure")) {
			result.putAll(findHireProperty);
			return result;
		}
		
		JSONObject addHireProperty = common.addHireProperty();
		if (addHireProperty.get("status").equals("failure")) {
			result.putAll(addHireProperty);
			return result;
		}
	
		String contractCode = common.getHireContractCode();			
		JSONObject hireNewSignContract = common.hireNewSign();
		if (hireNewSignContract.get("status").equals("failure")) {
			result.putAll(hireNewSignContract);
			return result;
		}
		
		// 修改接口
		boolean toHireNewSignPage = common.toHireNewSignPage();
		if (toHireNewSignPage == false) {
			result.put("status", "false");
			result.put("error_message", "调用修改接口失败");
			return result;
		}
			
		// 打开保存基础信息页面
		boolean toHireBaseInfo = common.toHireBaseInfo();
		if (toHireBaseInfo == false) {
			result.put("status", "false");
			result.put("error_message", "打开保存基础信息页面接口失败");
			return result;
		}
		
		// 保存基础信息保存
		boolean saveBaseInfo = common.saveBaseInfo();
		if (saveBaseInfo == false) {
			result.put("status", "false");
			result.put("error_message", "保存基础信息保存接口失败");
			return result;
		}
		
		// 打开房源信息页面
		boolean toHireHouseInfo = common.toHireHouseInfo();
		if (toHireHouseInfo == false) {
			result.put("status", "false");
			result.put("error_message", "打开房源信息页面接口失败");
			return result;
		}
		
		// 保存房源信息
		JSONObject saveHouseInfo = common.saveHouseInfo(houseinfo);
		if (saveHouseInfo.get("status").equals("failure")) {
			result.putAll(saveHouseInfo);
			return result;
		}
		
		// 保存房间信息
		JSONObject saveRoomInfo = common.saveRoomInfo(houseinfo);
		if (saveRoomInfo.get("status").equals("failure")) {
			result.putAll(saveRoomInfo);
			return result;
		}	
		
		// 保存公共区域信息
		JSONObject savePublicInfo = common.savePublicInfo(houseinfo);
		if (savePublicInfo.get("status").equals("failure")) {
			result.putAll(savePublicInfo);
			return result;
		}
		
		// 跳转
		boolean splitPage = common.splitPage();
		if (splitPage == false) {
			result.put("status", "false");
			result.put("error_message", "跳转到收房人信息失败");
			return result;
		}
		
		// 打开收房人信息
		boolean toHirePeopleInfo = common.toHirePeopleInfo();
		if (toHirePeopleInfo == false) {
			result.put("status", "false");
			result.put("error_message", "打开收房人信息页面接口失败");
			return result;
		}
		
		// 得到管家号码
		JSONObject getUsersList = common.getUsersList();
		if (getUsersList.getJSONArray("rows").size() == 0) {
			result.putAll(getUsersList);
			return result;
		}
		
		// 得到管家的上级号码
		JSONObject getHigherLevel = common.getHigherLevel();
		if (getHigherLevel.get("status").equals("failure")) {
			result.putAll(getHigherLevel);
			return result;
		}
		
		// 从楼盘系统中获取所属管家
		JSONObject getManagetInfo = common.getManagetInfo();
		if (getManagetInfo.get("status").equals("failure")) {
			result.putAll(getManagetInfo);
			return result;
		}
		
		//  从EHR系统中获取所属管家的上级
		JSONObject getEhrDeptInfo = common.getEhrDeptInfo();
		if (getEhrDeptInfo.get("status").equals("failure")) {
			result.putAll(getEhrDeptInfo);
			return result;
		}
		
		// 保存管家信息
		JSONObject saveHirePeopleInfo = common.saveHirePeopleInfo(getUsersList,getHigherLevel,getManagetInfo);
		if (saveHirePeopleInfo.get("status").equals("failure")) {
			result.putAll(saveHirePeopleInfo);
			return result;
		}
		
		// 打开付款计划
		boolean toHirePaymentPlan = common.toHirePaymentPlan();
		if (toHirePaymentPlan == false) {
			result.put("status", "false");
			result.put("error_message", "打开付款计划页面接口失败");
			return result;
		}
		
		// 保存付款计划
		boolean payMentPlan = common.payMentPlan();
		if (payMentPlan == false) {
			result.put("status", "false");
			result.put("error_message", "保存付款计划接口失败");
			return result;
		}

		// 打开补充协议
		JSONObject toHireSupplyAgreement = common.toHireSupplyAgreement();
		if (toHireSupplyAgreement.get("status").equals("failure")) {
			result.put("error_message", "打开补充协议页面接口失败");
			return result;
		}
		
		// 提交审核
		JSONObject compileHire = common.compileHire();
		if (compileHire.get("status").equals("failure")) {
			result.putAll(compileHire);
			return result;
		}
				
    	result.put("status", "success");
    	result.put("error_message", "收房合同已经提交审核:" + contractCode + "  房屋编号为：" + common.standardID);
    	return result;
	}
	
	/**
	 * @param  ehrJson：从ehr中获取管家信息接口返回值
	 * @param  viewJson： 界面输入的参数
	 * @param  modePrice: 从计价模型中获取相关信息
	 * @author : Elaine
	 */
	public static JSONObject hireZZHouseFlow(JSONObject ehrJson,JSONObject modePrice,JSONObject viewJson,String address,JSONObject houseinfo){
		
		JSONObject result=new JSONObject();
		ZoHireHouseCommon common = new ZoHireHouseCommon();
		
		common.getUserInfo(ehrJson);
		common.initViewJson(viewJson);
		
		boolean zoLogin = common.zoLogin();
		if (zoLogin == false) {
			result.put("status", "false");
			result.put("error_message", "登陆失败");
			return result;
		}
		
		JSONObject hireNewSign = common.hireNewSign(modePrice);
		if (hireNewSign.get("status").equals("failure")) {
			result.putAll(hireNewSign);
			return result;
		}		
		
		JSONObject queryCustomerInfo = common.queryCustomerInfo();
		if (queryCustomerInfo.get("status").equals("failure")) {
			result.putAll(queryCustomerInfo);
			return result;
		}	
		
		JSONObject getHouseAssessInfo = common.getHouseAssessInfo();
		if (getHouseAssessInfo.get("status").equals("failure")) {
			result.putAll(getHouseAssessInfo);
			return result;
		}	
		
		JSONObject saveHireNewSignPage = common.saveHireNewSignPage_ZZ(hireNewSign,queryCustomerInfo, address,getHouseAssessInfo);
		if (saveHireNewSignPage.get("status").equals("failure")) {
			result.putAll(saveHireNewSignPage);
			return result;
		}	
		
		JSONObject findHireProperty = common.findHireProperty(saveHireNewSignPage);
		if (findHireProperty.get("status").equals("failure")) {
			result.putAll(findHireProperty);
			return result;
		}
		
		JSONObject addHireProperty = common.addHireProperty();
		if (addHireProperty.get("status").equals("failure")) {
			result.putAll(addHireProperty);
			return result;
		}
	
		String contractCode = common.getHireContractCode();			
		JSONObject hireNewSignContract = common.hireNewSign();
		if (hireNewSignContract.get("status").equals("failure")) {
			result.putAll(hireNewSignContract);
			return result;
		}
		
		// 修改接口
		boolean toHireNewSignPage = common.toHireNewSignPage();
		if (toHireNewSignPage == false) {
			result.put("status", "false");
			result.put("error_message", "调用修改接口失败");
			return result;
		}
			
		// 打开保存基础信息页面
		boolean toHireBaseInfo = common.toHireBaseInfo();
		if (toHireBaseInfo == false) {
			result.put("status", "false");
			result.put("error_message", "打开保存基础信息页面接口失败");
			return result;
		}
		
		// 保存基础信息保存
		boolean saveBaseInfo = common.saveBaseInfo();
		if (saveBaseInfo == false) {
			result.put("status", "false");
			result.put("error_message", "保存基础信息保存接口失败");
			return result;
		}
		
		// 打开房源信息页面
		boolean toHireHouseInfo = common.toHireHouseInfo();
		if (toHireHouseInfo == false) {
			result.put("status", "false");
			result.put("error_message", "打开房源信息页面接口失败");
			return result;
		}
		
		// 保存房源信息
		JSONObject saveHouseInfo = common.saveHouseInfo_ZZ(houseinfo);
		if (saveHouseInfo.get("status").equals("failure")) {
			result.putAll(saveHouseInfo);
			return result;
		}
		
		// 保存房间信息
		JSONObject saveRoomInfo = common.saveRoomInfo(houseinfo);
		if (saveRoomInfo.get("status").equals("failure")) {
			result.putAll(saveRoomInfo);
			return result;
		}	
		
		// 保存公共区域信息
		JSONObject savePublicInfo = common.savePublicInfo(houseinfo);
		if (savePublicInfo.get("status").equals("failure")) {
			result.putAll(savePublicInfo);
			return result;
		}
		
		// 跳转
		boolean splitPage = common.splitPage();
		if (splitPage == false) {
			result.put("status", "false");
			result.put("error_message", "跳转到收房人信息失败");
			return result;
		}
		
		// 打开收房人信息
		boolean toHirePeopleInfo = common.toHirePeopleInfo();
		if (toHirePeopleInfo == false) {
			result.put("status", "false");
			result.put("error_message", "打开收房人信息页面接口失败");
			return result;
		}
		
		// 得到管家号码
		JSONObject getUsersList = common.getUsersList();
		if (getUsersList.getJSONArray("rows").size() == 0) {
			result.putAll(getUsersList);
			return result;
		}
		
		// 得到管家的上级号码
		JSONObject getHigherLevel = common.getHigherLevel();
		if (getHigherLevel.get("status").equals("failure")) {
			result.putAll(getHigherLevel);
			return result;
		}
		
		// 从楼盘系统中获取所属管家
		JSONObject getManagetInfo = common.getManagetInfo();
		if (getManagetInfo.get("status").equals("failure")) {
			result.putAll(getManagetInfo);
			return result;
		}
		
		//  从EHR系统中获取所属管家的上级
		JSONObject getEhrDeptInfo = common.getEhrDeptInfo();
		if (getEhrDeptInfo.get("status").equals("failure")) {
			result.putAll(getEhrDeptInfo);
			return result;
		}
		
		// 保存管家信息
		JSONObject saveHirePeopleInfo = common.saveHirePeopleInfo(getUsersList,getHigherLevel,getManagetInfo);
		if (saveHirePeopleInfo.get("status").equals("failure")) {
			result.putAll(saveHirePeopleInfo);
			return result;
		}
		
		// 打开付款计划
		boolean toHirePaymentPlan = common.toHirePaymentPlan();
		if (toHirePaymentPlan == false) {
			result.put("status", "false");
			result.put("error_message", "打开付款计划页面接口失败");
			return result;
		}
		
		// 保存付款计划
		boolean payMentPlan = common.payMentPlan();
		if (payMentPlan == false) {
			result.put("status", "false");
			result.put("error_message", "保存付款计划接口失败");
			return result;
		}

		// 打开补充协议
		JSONObject toHireSupplyAgreement = common.toHireSupplyAgreement();
		if (toHireSupplyAgreement.get("status").equals("failure")) {
			result.put("error_message", "打开补充协议页面接口失败");
			return result;
		}
		
		// 提交审核
		JSONObject compileHire = common.compileHire();
		if (compileHire.get("status").equals("failure")) {
			result.putAll(compileHire);
			return result;
		}
				
    	result.put("status", "success");
    	result.put("error_message", "收房合同已经提交审核:" + contractCode + "  房屋编号为：" + common.standardID);
    	return result;
	}
}
