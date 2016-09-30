package com.ziroom.confManagement.commonMethods;

import com.ziroom.appScript.CommonApiParas;
import com.ziroom.confManagement.config.PropertyConstants;
import com.ziroom.utils.PropertiesUtil;

public class RentAppGlobalParas {

	// 初始化登录用户名
	public static String loginName="";
	public static String sysContractId="";
	// 新生成的续约合同合同号
	public static String renewContractCode="";
	// 当月需支付金额
	public static String countMoney="";
	// 获取管家用户名
	public static String AgentUser="";
	// 登陆后uid
	public static String login_uid="";
	public static String appid = new CommonApiParas().getAppid();
	// --------------------------------------------------------------------------
	// 搜房源house_type（整租合租自如驿之分）
	public static String detailHouseType="";
	public static String house_code="";
	public static String house_id="";
	// 单个房源类型ReturnHouseType
	public static String ReturnHouseType="";
	/***
	 * 整租搜房house_number
	 */
	public static String house_number=""; 
	// ------------------------------------------------------------------------------
	// 个人信息中姓名（昵称）
	public static String person_name="";
	public static String user_cert1="";
	public static String user_cert2="";
	public static String user_cert3="";
	public static String cert_num="";
	public static String cert_type="";
	public static String user_sex="";
	public static String user_phone="";
	// ----------------------------------------------
	public static String signDate="";
	public static String stopDate="";
	public static String price="";
	public static String priceUnit="";
	public static String tenancyType="";// s_promiseMyself2返回值
	public static String isShort="";
	// ----------------------------------------------
	public static String contractCode=""; // ---合同号
	// ----------------------------------------------
	// 获取资质信息
	public static String work_name="";
	public static String work_address="";
	public static String certifier_name="";
	public static String certifier_phone="";
	public static String urgency_name="";
	public static String urgency_phone="";
	public static String urgency_relation="";
	// -----------------------------------------------------------
	public static String osType = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "osType");
	// 1是管家app,2是自如客
	public static String appType = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "appType");
	public static String city_code = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "city_code");
	// 请求类型：1是app请求；2是服务器请求
	public static String source = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "source");
	public static String imei = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "imei");
	public static String appVersionStr = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"appVersionStr");
	// 1是白条，0是非白条
	public static String isBlank = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "isBlank");

	// -----------------------------------------------------------------------------------------------
	// 公共参数
	public static String os = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "os");
	public static String app = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "app");
	public static String model = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "model");
	public static String network = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "network");
	public static String ip = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "ip");

	// -----------------------------------------------------------------------------------------------
	// 服务费
	public static String commission="";
	// 押金
	public static String deposit="";
	public static String reserveDeposit="";
	public static String housePrice="";
	// 管家参数
	public static String isLogin = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "isLogin");
	// 自如支付
	public static String orderType = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "orderType");
	// 优惠码
	public static String couponCode = "";

	public static String paySource = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "paySource");
	public static String couponCostValue = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"couponCostValue");
	public static Double balance;
	public static String mac = "2c:8a:72:4e:53:9c";
	public static String couponValue = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"couponValue");
	public static String money="";

	// ------------------------------------------------------------------------
	public static String resblock_id="";
	// --------------------------------------------------------------------------
	public static String tranSport = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"tran_sport");
	public static String commuteName = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"commute_name");
	public static String isCommute = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"is_commute");
	public static String longitudeAndLatitude = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"longitude_and_latitude");
	public static String workTime = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "work_time");

	// 押一付一
	public static String payment = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "payment");
	// 是否续约1是 0非
	public static String isRenew = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "isRenew");

	// 无纸化
	public static String passWord = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "passWord");
	// 整租参数
	public static String max_lng = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "max_lng");
	public static String sort = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "sort");
	public static String min_area = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "min_area");
	public static String min_lat = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "min_lat");
	public static String house_tags1 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"house_tags1");
	public static String tags = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "tags");
	public static String max_area = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "max_area");
	public static String bizcircle_code = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"bizcircle_code");
	public static String clng = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "clng");
	public static String max_lat = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "max_lat");
	public static String house_tag3 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"house_tag3");
	public static String length = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "length");
	public static String huxing = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "huxing");
	public static String house_tag5;
	public static String min_lng = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "min_lng");
	public static String keywords = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "keywords");
	public static String min_rentfee = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"min_rentfee");
	public static String subway_station_name = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"subway_station_name");
	public static String house_tags2 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"house_tags2");
	public static String house_tags0 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"house_tags0");
	public static String max_rentfee = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"max_rentfee");
	public static String house_tags4 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"house_tags4");
	public static String start = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "start");
	public static String heating = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "heating");
	public static String house_tags6 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"house_tags6");
	public static String style_codes = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"style_codes");
	public static String style_code = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"style_code");
	public static String version_id = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"version_id");

	public static String ignoreCity = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"ignoreCity");

	public static String is_reserve;
	public static String can_sign;
	public static String clat;
	public static String bedroom = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "bedroom");
	public static String size = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "size");
	public static String configs = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "configs");
	public static String betweenPrice = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "price");
	public static String signOpen = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "signOpen");

	public static String face = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "face");
	public static String page = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "page");
	public static String style = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "style");
	// 新签物业交割
	public static String haveJointRent;
	public static String confirm_state = "Y";
	public static String beEvaluatorType = "salesZO";
	public static String channelCode = "app";
	public static String questionType = "SZOASE2";
	public static String tokenId;
	public static String evaluateMsg = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
			"evaluateMsg");
	// 自如账号支付
	public static String remainMoney = "0"; // 后期需要修改动态计算

}
