package keeper.confManagement.commonMethods;

import com.ziroom.utils.PropertiesUtil;
import keeper.appScript.Keeper_Renew;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KeeperGlobalParas {

	//管家用户名密码
	public static String serviceUserName= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "serviceUserName");
	public static String servicePwd= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "servicePwd");

	//收房管家用户名密码
	public static String rentHouseUserAccount;
	public static String rentHousePwd;

	// 登陆后uid
	// 管家登陆后uid
	public static String login_uid;
	public static String appid = new Keeper_Renew().getAppid();
	public static String customerName;
	public static String customerPhone;
	public static String uid;

	public static String oldContractCode;
	public static String oldContract;
	public static String renewStartDate;
	public static String renewEndDate;
	public static String houseInfo;
	public static String hireStopDate;
	public static String oldIsDeposit;
	//总金额
	public static String countMoney;
	public static String testCountMoney;

	public static String deposit;
	public static String monthPrice;
	//首付金额
	public static String countPrice;
	public static String testCountPrice;
	// 所有总金额
	public static String testTotalCountPrice;

	public static String renewContract;
	public static String renewType;
	//付款方式
	public static String paymentType;
	public static float paymentDiscount;
	//服务费
	public static String commission;
	public static String testCommission;
	//付款方式选择、剩余时间
	public static String period;
	public static String periodday;
	//老合同截止日期
	public static String oldStopDate;
	//续约距离截止日期
	public static String DeadLine;
	//提前续约折扣
	public static float earlyRenewDiscount;
	//连续居住时长
	public static String liveTime;
	public static float liveTimeDiscount;

	// 1是管家app,2是自如客
	public static String appType = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "appType");
	// 请求类型：1是app请求；2是服务器请求
	public static String source = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "source");
	// 1是白条，0是非白条
	public static String isBlank = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "isBlank");

	public static String city_code = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
					"city_code");
	public static String imei = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "imei");

	public static String isFollow = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "isFollow");
	public static String test1 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "test1");
	public static String versionInt = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "versionInt");
	// 公共参数
	public static String os = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "os");
	public static String app = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "app");
	public static String model = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "model");
	public static String network = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "network");
	public static String ip = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "ip");
	public static String osType = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "osType");

	//新签物业交割参数 
	public static String generalOrType= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "generalOrType");
	public static String configGoodsNext_Data;
	public static String roomName;
	public static String roomId;
	public static String roomType;
	public static String sums= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "sums");
	public static String typeId= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "typeId");
	public static String subType= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "subType");
	public static String goodsName= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "goodsName");
	public static String goodsType= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "goodsType");
	public static String belong= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "belong");
	public static String brand= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "brand");
	public static String goodsModel= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "goodsModel");
	public static String useStatus= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "useStatus");
	public static String displayNums_pic= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "displayNums_pic");
	public static String unitKeyisOver;
	public static String doorKeyNums;
	public static String letterBoxKeyisOver;
	public static String letterBoxKeyNums;
	public static String electricCardNums;
	public static String electricCardisOver;
	public static String unitKeyNums;
	public static String guardCardisOver;
	public static String gasCardisOver;
	public static String gasCardNums;
	public static String warterCardNums;
	public static String warterCardisOver;
	public static String doorKeyisOver;
	public static String guardCardNums;
	public static JSONObject waterCool;
	public static JSONObject electric;
	public static JSONObject gas;
	public static JSONObject middleWaterCool;
	public static JSONArray Jsonfeeslist;
	public static String gasDisplayNums= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "gasDisplayNums");
	public static String waterCoolDisplayNums= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "waterCoolDisplayNums");
	public static String electricDisplayNums= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "electricDisplayNums");
	public static String electricBalance= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "electricBalance");
	

	//发起类型:1:发起,2:重新发起
	public static String creatType= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,"creatType");

	// 房源宝
	public static String resblockName= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,"resblockName");
	public static String districtName= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,"districtName");
    public static JSONObject getResblockIds;

}
