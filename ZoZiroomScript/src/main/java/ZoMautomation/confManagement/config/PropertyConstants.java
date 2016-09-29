package ZoMautomation.confManagement.config;

import com.ziroom.utils.PropertiesUtil;

public class PropertyConstants {
	private static String dnFile = "/DomainName.properties";
	private static String dbFile = "/DataBase.properties";
	/**
	 * 各个系统域名
	 */
	public static String INTERFACES_DOMIN = PropertiesUtil.getPropValAsString(dnFile,"INTERFACES_DOMIN");
	public static String CRM_DOMIN = PropertiesUtil.getPropValAsString(dnFile,"CRM_DOMIN");
	public static String CRM_IP = PropertiesUtil.getPropValAsString(dnFile,"CRM_IP");
	public static String LOGIN_DOMIN = PropertiesUtil.getPropValAsString(dnFile,"LOGIN_DOMIN");
	public static String LOGIN_ZIROOM_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"LOGIN_ZIROOM_DOMAIN");
	public static String SOLR_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"SOLR_DOMAIN");
	public static String MODEPRICE_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"MODEPRICE_DOMAIN");
	public static String ZO_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"ZO_DOMAIN");
	public static String EHR_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"EHR_DOMAIN");
	public static String CRM_LOGIN_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"CRM_LOGIN_DOMAIN");
	public static String PHP_CRM_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"PHP_CRM_DOMAIN");
	public static String ZRBD_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"ZRBD_DOMAIN");
	public static String HIRE_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"HIRE_DOMAIN");
	public static String CONFIG_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"CONFIG_DOMAIN");
	public static String ZZ_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"ZZ_DOMIAN");

	// mysql 数据库
	public static String MYSQL_ADDRESS = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_ADDRESS");
	public static String MYSQL_ACCOUNT = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_ACCOUNT");
	public static String MYSQL_APPZIROOM = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_APPZIROOM");
	public static String MYSQL_USERNAME = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_USERNAME");
	public static String MYSQL_PASSWORD = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_PASSWORD");
	public static String MYSQL_PORT = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_PORT");
	public static String ZHUN_MYSQL_ADDRESS = PropertiesUtil.getPropValAsString(dbFile,"ZHUN_MYSQL_ADDRESS");

	// oracle 数据库
	public static String ORACLE_ADDRESS = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_ADDRESS");
	public static String ORACLE_SID = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_SID");
	public static String ORACLE_USERNAME = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_USERNAME");
	public static String ORACLE_PASSWORD = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_PASSWORD");
	public static String ORACLE_PORT = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_PORT");
	public static String ZHUN_ORACLE_ADDRESS = PropertiesUtil.getPropValAsString(dnFile,"ZHUN_ORACLE_ADDRESS");

	// 登陆接口地址
	public static final String newlogin = "/login/v2";
	public static final String login = "/index.php?_p=api_mobile&_a=login_normal";
	public static final String housedetail = "/index.php?_p=api_mobile&_a=detailShow";
	public static final String confirmInfoFirst = "/index.php?_p=api_newsign&_a=user_info_update";
	public static final String confirmInfoSec = "/index.php?_p=api_newsign&_a=get_ra_config";
	public static final String confirmExtInfo = "/index.php?_p=api_newsign&_a=select_extend_info";
	public static final String promiseFirst = "/index.php?_p=api_newsign&_a=extend_info";
	public static final String appid = "/crm-reserve/common/createAppId";
	public static final String promiseSec = "/crm-reserve/contractInfo/getContractTenancy";
	public static final String confirmPayFirst = "/crm-reserve/contractInfo/submitContractInfo";
	public static final String confirmPaySec = "/index.php?_p=api_newsign&_a=clause_select";
	public static final String getFirstPayInfo = "/crm-reserve/contractInfo/getFirstPayInfo";
	public static final String getActivity = "/crm-reserve/contractInfo/getActivity";
	public static final String contractSummary = "/crm-reserve/contractInfo/contractSummary";
	public static final String confrimSignInfo = "/index.php?_p=api_newsign&_a=get_ra_sign_info";
	public static final String confrimSignature = "/index.php?_p=api_newsign&_a=set_ra_signature";
	public static final String confirmContract = "/crm-reserve/contractInfo/confirmContract";
	public static final String zzHouse = "/zz/house/list.json";
	// 付款
	public static final String getContractList = "/crm-reserve/contractInfo/getContractList";
	public static final String submitContractInfo ="/crm-reserve/contractInfo/submitContractInfo";
	public static final String getContractInfo = "/crm-reserve/contractInfo/getContractInfo";
	public static final String getRaSignFlag = "/crm-reserve/packContract/getRaSignFlag";
	public static final String getPayInfo = "/crm-reserve/contractInfo/getPayInfo";
	public static final String firstPayment = "/crm-reserve/payment/firstPayment";

	// pc端签约接口
	public static final String pclogin = "/login.php";
	
	// solr查询
	public static final String searchSolr = "/solr/room_info/select";
	
	// 收房合同
	// 获取城区列表
	public static final String getDistrictList="/crm-reserve/house/getDistrictList";
	// 获取楼盘列表
	public static final String getVillageList="/crm-reserve/house/getVillageList";
	// 获取楼栋列表
	public static final String getBuildNumList = "/crm-reserve/house/getBuildNumList";
	// 获取单元列表
	public static final String getUnitList = "/crm-reserve/house/getUnitList";
	// 获取楼层列表
	public static final String getFloorList = "/crm-reserve/house/getFloorList";
	// 获取门牌号列表
	public static final String getRoomNumList = "/crm-reserve/house/getRoomNumList";
	// 获取商机来源一级列表
	public static final String queryBOFirstSourceList = "/crm-reserve/busopp/queryBOFirstSourceList";
	// 获取商机来源二级列表  
	public static final String queryBOSecondSourceList = "/crm-reserve/busopp/queryBOSecondSourceList";
    // 根据楼字典房间Id及来源获取所属管家 
	public static final String getKeeperByAndSourceType = "/crm-reserve/busopp/getKeeperByAndSourceType";
    // app端根据楼盘字典Id查询是否有已录入商机接口
	public static final String appSearchBusOppByStandardInfo = "/crm-reserve/busopp/appSearchBusOppByStandardInfo";
	// 管家提交商机
	public static final String createBusOpp = "/crm-reserve/busopp/createBusOpp";
	// 我的录入查询
	public static final String queryBOListByMyEntry = "/crm-reserve/busopp/queryBOListByMyEntry";
	// 管家查商机列表
	public static final String queryBOListByKeeper = "/crm-reserve/busopp/queryBOListByKeeper";
	// 管家添加待跟进
	public static final String addTrackByKeeper = "/crm-reserve/busopp/addTrackByKeeper";
	// 完成实勘记录
	public static final String commitSurvey = "/crm-reserve/house/commitSurvey";
	
    //===================================ZO系统===================================================
    // 登陆
	public static final String zoLogin = "/common/toSelectCity.action";
	// 获取价钱
	public static final String hireNewSign = "/hireNewSign/getPriceModelDetail.action";
	// 获取业主信息
	public static final String queryCustomerInfo = "/hireNewSign/queryCustomerInfo.action";
	// 保存
	public static final String saveHireNewSignPage = "/hireNewSign/saveHireNewSignPage";
    // 物业交割前点击确认 
	public static final String findHireProperty = "/hireProperty/findHireProperty.action";
	// 添加物业交割
	public static final String addHireProperty = "/hireProperty/addHireProperty.action";
	// 保存基础信息
	public static final String saveBaseInfo = "/hireBaseInfo/saveBaseInfo.action";
	// 修改接口
	public static final String toHireNewSignPage = "/hireNewSignFlow/toHireNewSignPage.action";
    // 打开保存基础信息接口
	public static final String toHireBaseInfo = "/hireNewSignFlow/toHireBaseInfo.action";
    // 打开房源信息
	public static final String toHireHouseInfo = "/hireNewSignFlow/toHireHouseInfo.action";
	// 保存房源信息
	public static final String saveHouseInfo = "/hireHouseInfo/saveHouseInfo.action";
	// 保存房间信息
	public static final String saveRoomInfo = "/hireHouseInfo/saveRoomInfo.action";
	// 打开收房人信息页面
	public static final String toHirePeopleInfo = "/hireNewSignFlow/toHirePeopleInfo.action";
	// 打开收房人信息页面前一步
	public static final String splitPage = "/hireHouseInfo/splitPage.action";
	// 得到用户列表
	public static final String getUsersList = "/hirePeopleInfo/getUsersList.action";
	// 得到管家的上级列表
	public static final String getHigherLevel = "/hirePeopleInfo/getHigherLevel.action";
	// 保存管家信息
	public static final String saveHirePeopleInfo = "/hirePeopleInfo/saveHirePeopleInfo.action";
    // 楼盘获取管家信息
	public static final String getEmpByHouseId = "/building/building!getEmpByHouseId.do";
	// 从ehr中获取管家上级信息
	public static final String getEhrDept = "/ehr/getEhrDept.action";
	// 打开付款计划
	public static final String toHirePaymentPlan ="/hireNewSignFlow/toHirePaymentPlan.action";
	// 保存付款计划
	public static final String payMentPlan ="/payMentPlan/toHireSupplyAgreement.action";
	// 打开补充协议界面
	public static final String toHireSupplyAgreement ="/hireNewSignFlow/toHireSupplyAgreement.action";
    // 上传图片
	public static final String uploadPage = "/hireSupplyAgreement/uploadPage.action";
	// 上传图片完成 
	public static final String uploadImg = "/hireSupplyAgreement/uploadImg.action";
    // 提交审核
	public static final String compileHire = "/hireSupplyAgreement/compileHire.action";
	// 业主确认
	public static final String hireNewSign_contract = "/hireNewSign/confirmContractWx";
	//====================================EHR===================================================
    // 获取管家信息
	public static final String getUserDetail = "/ehr/getUserDetail.action";

	//====================================楼盘系统==================================================
	// 获取管家所管楼栋
	public static final String getBuildingByEmp = "/building/building!getBuildingByEmp.do";
    // 获取合同列表
	public static final String getCanRenewContract = "/crm-reserve/keeperRenew/getCanRenewContract";
    // 获取合同详情页
	public static final String keeperRenew_getContractInfo = "/crm-reserve/keeperRenew/getContractInfo";
    // 查 询支付信息 
	public static final String getRenewPayInfo = "/crm-reserve/keeperRenew/getRenewPayInfo";
    // 生成转签
	public static final String renewContract = "/crm-reserve/keeperRenew/renewContract";

	//===============================配置======================================================
    public static final String configLogin = "/security/security!login.action";
    // 选择城市
    public static final String selectCity= "/security/security!selectCity.action";
    // 查询合同
    public static final String listConfiguration = "/configuration/configurationProgram!listConfiguration.action";
    // 新增配置方案
    public static final String addNewConfig = "/configuration/configurationProgram!indexConfigurationProgram.action";
    // 保存产品方案 
    public static final String generateDisposeProgram = "/configuration/configurationProgram!generateDisposeProgramYj3.action";
    // 保存家电
    public static final String saveDisposeHomeAppliance = "/configuration/programGoods!saveDisposeHomeAppliance.action";
    // 保存保洁
    public static final String saveNewConfigurationClean = "/configuration/configurationClean!saveNewConfigurationClean.action";
    // 保存宽带
    public static final String saveConfigurationBroadband = "/configuration/configurationBroadband!saveConfigurationBroadband.action";
    // 保存维修
    public static final String saveRepaire = "/configuration/configurationProgram!saveRepaire.action";
    // 保存空气质量
    public static final String saveAirtest = "/configuration/configurationProgram!saveAirtest.action";
    // 保存空气质量治理
    public static final String saveAirControl = "/configuration/configurationProgram!saveAirControl.action";
    // 提交配置方案
    public static final String submitYj3Configurtion = "/configuration/configurationProgram!submitYj3Configurtion.action";
    // 新增配置方案前
    public static final String selectProductVersion = "/configuration/configurationProgram!selectProductVersionYj3.action";
    

}
