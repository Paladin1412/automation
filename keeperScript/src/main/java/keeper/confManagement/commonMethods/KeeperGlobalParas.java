package keeper.confManagement.commonMethods;


import com.ziroom.utils.CommonFunction;
import com.ziroom.utils.PropertiesUtil;
import keeper.appScript.Keeper_Renew;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KeeperGlobalParas {

	//管家用户名密码
	public static String serviceUserName= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "serviceUserName");
	public static String servicePwd= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "servicePwd");

	//收房管家用户名密码20186677
	public static String rentHouseUserAccount= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "rentHouseUserAccount");
	public static String rentHousePwd= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "rentHousePwd");

	// 登陆后uid
	// 管家登陆后uid
	public static String login_uid;
	public static String appid = new Keeper_Renew().getAppid();
	public static String customerName;
	public static String customerPhone;
	public static String uid;
	public static String toKeeperTypeName;

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
	public static String address = "";//地址
	public static String resblockName="格兰晴天";
	public static String districtName="通州";
	public static String resblockId="1111027374633";
	public static String districtId="23008625";  //对于管家
    public static String resblock="格兰";  //输入关键字搜索小区列表
    public static String building_no=""; //楼栋
    public static String unit="";//楼栋单元
    public static String floor=""; //楼层
    public static String room_no=""; //房间号
    public static String boType="1";
    public static String firstSourceId="2"; //定值后期计算
    public static String entryPersonName="李腾飞"; //录入管家
    public static String villageId="1111027374633";
    public static String secondSource="10";
    public static String ownerTel="0106125356";  //座机电话
    public static String ownerKeeperCode="20186677"; //所属管家号
    public static String ownerName="贝贝"+ CommonFunction.getTimeStampOf10();//唯一的名字
    public static String operatorName="李腾飞";
    public static String keeperName="韩坡";
    public static String keeperPhone="16986754356";
    public static String isTopFloor="0";  //是否顶层
    public static String ownerPhone="15071116364";
    public static String pageSize="20";//我的商机展示个数
    public static String pageNum="1";
    public static String trackState="0";
    public static String houseId="";
    public static String busOppId="";
    public static String trackResult="4";
    public static String remark="跟踪完成，合作愉快";//标签文本内容
    public static String trackPrice="6800";//和业主谈的价格（任意值）
    public static String housePicList= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,"housePicList");
	public static String toilet="2";//实勘时洗手间配置（输入框）
	public static String state="6"; //实勘时房源状态：待租中
	public static String changeToNum="3";//实勘时房屋间数到几
	public static String changeFromNum="2";//实勘时房屋间数从几
	public static String area="90";//实勘时房屋总大小
	//实勘时房间配置
	public static String roomList= PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,"roomList");
	public static String decorateType="1";
	//核销商机
	public static String cancelRemark="Automation Test";//标签文本内容
	public static String cancelReason="1";//标签文本内容
	//收房模块数据
	public static JSONObject houseinfo;
	public static JSONObject ehrJson;
	public static JSONObject viewJsonFZ;
	public static JSONObject viewJsonZZ;
	public static JSONObject modePrice;
	public static String orderID;//提审单号
	public static String standardId;

}
