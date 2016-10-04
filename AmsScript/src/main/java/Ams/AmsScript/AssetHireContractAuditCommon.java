package Ams.AmsScript;

import Ams.confManagement.commonMethods.AmsGlobalParas;
import Ams.confManagement.commonMethods.FunctionCommon;
import com.ziroom.httpclient.HttpClientUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类说明 : 在资产中审核收房合同 作者: wujing 版本号 : V1.0
 */

public class AssetHireContractAuditCommon {
    String cityCode = null;
    String territoryId = null;
    String hireContractId = null;
    String productVersion = null;

    HttpClientUtils hcu = new HttpClientUtils();
    FunctionCommon fc = new FunctionCommon();
    Logger logger = Logger.getLogger(AssetHireContractAuditCommon.class);

    // ========================初始化从参数=======================================

    /**
     * @param viewJson ： viewJson
     * @description: 初始化从界面中得到的数据
     * @author wujing
     **/
    public void initViewJson(JSONObject viewJson) {
        productVersion = viewJson.getString("productVesion");
        cityCode = viewJson.getString("citycode");
    }

    // =========================初始化结束==============================================

    /**
     * @description: 登陆资产
     * @author wujing
     **/
    public String loginAsset(String httpUrl) {

        logger.info(httpUrl);
        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("j_username", "admin");
        map.put("j_password", "111111");
        JSONObject jsobj = JSONObject.fromObject(map);

        // 请求接口返回值
        Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
        Document returnValue = Jsoup.parse(response.get("returnValue"));
        Element ele = returnValue.getElementById("selectCity");
        logger.info(jsobj.toString());
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
        String status = returnStatusCode.body().text();
        Reporter.log("returnStatusCode : " + status);

        if (ele.attr("value").equals("确认")) {
            logger.info("loginAsset返回值" + returnValue);
        } else {
            logger.info("loginAsset返回值------->>>>>>为空");
        }
        return response.toString();

    }

    /**
     * @description: 选择城市
     * @author wujing
     **/
    public String selectCity(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.selectCityAsset;
//		String httpUrl = domainName + suffixUrl;
        logger.info(httpUrl);

        // 城市对应
        if (cityCode.equals("110000")) {
            territoryId = "11";
        } else if (cityCode.equals("310000")) {
            territoryId = "50";
        } else {
            territoryId = "24";
        }

        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("territoryId", territoryId);
        map.put("username", "admin");
        JSONObject jsobj = JSONObject.fromObject(map);

        // 请求接口返回值
        Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
        String returnValue = response.get("returnValue");
        logger.info(jsobj.toString());
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
        String status = returnStatusCode.body().text();
        Reporter.log("returnStatusCode : " + status);

        if (returnValue.contains("/AMS")) {
            logger.info("loginAsset返回值" + returnValue);
        } else {
            logger.info("loginAsset返回值------->>>>>>为空");
        }
        return response.toString();

    }

    /**
     * @description: AMS首页
     * @author wujing
     **/
    public String indexAMS(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.indexAMS;
//		String httpUrl = domainName + suffixUrl;

        logger.info(httpUrl);
        // 请求接口返回值
        Map<String, String> response = hcu.httpPostRequest(httpUrl);
        Document doc = Jsoup.parse(response.get("returnValue"));
        String title = doc.title();
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
        String status = returnStatusCode.body().text();
        Reporter.log("returnStatusCode : " + status);

        if (title.contains("资产管理系统")) {
            logger.info("indexAMS返回值" + response);
        } else {
            logger.info("indexAMS返回值------->>>>>>为空");
        }
        return response.toString();
    }

    /**
     * @description: AMS首页
     * @author wujing
     **/
    public String getResources(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.getResources;
//		String httpUrl = domainName + suffixUrl;
        logger.info(httpUrl);

        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("parentIds",
                "23,28,36,43,45,50,54,58,65,73,80,83,89,2,5,11,17,883,926,983,2000,953,60000220,562,571,502,582,620,690,746,863,871,933,672,790,968");
        JSONObject jsobj = JSONObject.fromObject(map);

        // 请求接口返回值
        Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
        Document doc = Jsoup.parse(response.get("returnValue"));
        String title = doc.title();
        logger.info(jsobj.toString());
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
        String status = returnStatusCode.body().text();
        Reporter.log("returnStatusCode : " + status);


        if (title.contains("资产管理系统")) {
            logger.info("getResources返回值" + response);
        } else {
            logger.info("getResources返回值------->>>>>>为空");
        }
        return response.toString();
    }

    /**
     * @description: 录收房合同成交报告单
     * @author wujing
     **/
    public String hiredealReport(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.hiredealReport;
//		String httpUrl = domainName + suffixUrl;
        logger.info(httpUrl);

        String sql = "select brz.* from hlasset.bz_hire_contract brz where brz.hire_contract_code='" + AmsGlobalParas.contractCode
                + "'";
        Map<String, String> result = fc.getAllDataFromOracleData(sql).get(0);
        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("hireReport.housecode", result.get("HOUSE_SURCE_CODE"));
        map.put("hireReport.hireConcode", AmsGlobalParas.contractCode);
        map.put("hireReport.executAddress", result.get("RATING_ADDRESS"));
        // 房源所在业务区域
        map.put("hireReport.inRoomArea", "产品运营部(A18222)");
        map.put("hireReport.rentPrice", result.get("RECEPTION_PRICE"));
        map.put("hireReport.startRentData", result.get("AGENT_START_DATE"));
        map.put("hireReport.hireEndData", result.get("AGENT_END_DATE"));
        // 租约对应业绩系数
        map.put("hireReport.payCoeft", "1.1");
        // 支付方式对应业绩系数
        map.put("hireReport.outpayCoeft", "1.0");
        // 收房经济人
        map.put("hireReport.shoufangpzjjr", "null(null)");
        // 所属业务大区/小区
        map.put("hireReport.contractComments", "集团客户组(A17139)");
        // 门店/组别
        map.put("hireReport.hourseEenterAgent", "null(null)");
        // 店面经理
        map.put("hireReport.auditUser", "null(null)");
        map.put("hireReport.shouFangPeople",
                result.get("ZIRU_COMMISSIONER_NAME") + "(" + result.get("ZIRU_COMMISSIONER_CODE") + ")");
        map.put("hireReport.shikanPeople", result.get("ZIRU_CHARGE_NAME") + "(" + result.get("ZIRU_CHARGE_CODE") + ")");
        map.put("hireReport.keyEenterAgent",
                result.get("ZIRU_MAJORDOMO_NAME") + "(" + result.get("ZIRU_MAJORDOMO_CODE") + ")");
        // 获取房间
        String roomSql = "select room.room_code,room.Room_Name,room.sell_price from bz_room room where room.hire_contract_code='"
                + AmsGlobalParas.contractCode + "' and room.is_public = 0 order by ID";
        List<Map<String, String>> roomNum = fc.getAllDataFromOracleData(roomSql);

        for (int i = 0; i < roomNum.size(); i++) {
            Map<String, String> room = roomNum.get(i);
            map.put("hireReport.roomcode" + i, room.get("ROOM_NAME") + "(" + room.get("ROOM_code") + ")");
            map.put("hireReport.shouFCost" + i, "0.0");
            map.put("hireReport.shopCost" + i, room.get("SELL_PRICE"));
        }

        // 得到hireContractId
        String contractSql = "select ID from hlasset.bz_hire_contract brz where brz.hire_contract_code='" + AmsGlobalParas.contractCode + "'";
        Map<String, String> contract = fc.getAllDataFromOracleData(contractSql).get(0);
        hireContractId = contract.get("ID");
        map.put("hireReport.hireContractId", hireContractId);
        JSONObject jsobj = JSONObject.fromObject(map);

        // 请求接口返回值
        Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
        String returnValue = response.get("returnValue");
        logger.info(jsobj.toString());
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
        String status = returnStatusCode.body().text();
        Reporter.log("returnStatusCode : " + status);

        if (returnValue.contains("操作成功！")) {
            logger.info("hiredealReport返回值" + returnValue);
        } else {
            logger.info("hiredealReport返回值------->>>>>>为空");
        }
        return response.toString();

    }

    /**
     * @description: 搜索合同
     * @author wujing
     **/
    public String listContractAudit(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.listContractAudit;
//		String httpUrl = domainName + suffixUrl;

        logger.info(httpUrl);
        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("filter_and_hireContractCode_LIKE_S", AmsGlobalParas.contractCode);
        map.put("_pageNum", "1");
        map.put("_pageSize", "12");
        JSONObject jsobj = JSONObject.fromObject(map);

        // 请求接口返回值
        Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
        String returnValue = response.get("returnValue");
        logger.info(jsobj.toString());
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
        String status = returnStatusCode.body().text();
        Reporter.log("returnStatusCode : " + status);

        if (returnValue.contains(AmsGlobalParas.contractCode)) {
            logger.info("listContractAudit返回值" + returnValue);
        } else {
            logger.info("listContractAudit返回值------->>>>>>为空");
        }
        return response.toString();

    }

    /**
     * @description: 点击审核合同
     * @author wujing
     **/
    public String indexHireContract(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.indexHireContract;
//		String httpUrl = domainName + suffixUrl;

        logger.info(httpUrl);
        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("filter_and_hireContractCode_LIKE_S", AmsGlobalParas.contractCode);
        map.put("_pageNum", "1");
        map.put("_pageSize", "12");
        map.put("isDefault", "0");
        map.put("pageNum", "");
        map.put("pageSize", "");
        map.put("id", hireContractId);
        JSONObject jsobj = JSONObject.fromObject(map);

        // 请求接口返回值
        Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
        Document doc = Jsoup.parse(response.get("returnValue"));
        Element ele = doc.getElementsByClass("navigation-font").get(0);
        logger.info(jsobj.toString());
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        Document returnStatusCode = Jsoup.parse(response.get("returnStatusCode"));
        String status = returnStatusCode.body().text();
        Reporter.log("returnStatusCode : " + status);

        if (ele.text().contains("出租合同录入")) {
            logger.info("indexHireContract返回值" + response);
        } else {
            logger.info("indexHireContract返回值------->>>>>>为空");
        }
        return response.toString();
    }

    /**
     * @description: 审核合同
     * @author wujing
     **/
    public String isUniqueForUpdateHireContract(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.isUniqueForUpdateHireContract;
//		String httpUrl = domainName + suffixUrl;

        logger.info(httpUrl);
        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("hireContractCode", AmsGlobalParas.contractCode);
        map.put("id", hireContractId);
        JSONObject jsobj = JSONObject.fromObject(map);

        // 请求接口返回值
        Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);
        JSONObject returnValue = JSONObject.fromObject(response.get("returnValue"));
        logger.info(jsobj.toString());
        logger.info(response.toString());
        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        Reporter.log(returnValue.toString());


        if (returnValue.get("isUnique").equals("true")) {
            logger.info("isUniqueForUpdateHireContract返回值" + returnValue);
        } else {
            logger.info("isUniqueForUpdateHireContract返回值------->>>>>>为空");
        }
        return response.toString();
    }

    /**
     * @description: 保存合同
     * @author wujing
     **/
    public String saveContractAuditInfo(String httpUrl) {
//		String domainName = PropertyConstants.ASSET_DOMAIN;
//		String suffixUrl = PropertyConstants.saveContractAuditInfo;
//		String httpUrl = domainName + suffixUrl;
        logger.info(httpUrl);

        // 得到合同全部信息
        String sql = "select brz.*, house.resblock_id,house.name from hlasset.bz_hire_contract brz,hlasset.bz_house house where brz.hire_contract_code= house.hire_contract_code and  brz.hire_contract_code='" + AmsGlobalParas.contractCode + "'";
        Map<String, String> contractInfo = fc.getAllDataFromOracleData(sql).get(0);
//		System.out.println(contractInfo);
        // 得到业主信息
        String cusSql = "Select * from bz_customer_zrnet zrnet where zrnet.mobile='" + contractInfo.get("ACCOUNT_PHONE") + "' and zrnet.customer_type='owner'";
        Map<String, String> cusInfo = fc.getAllDataFromOracleData(cusSql).get(0);

        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("territoryCode", contractInfo.get("TERRITORY_CODE"));
        map.put("hireContract.id", contractInfo.get("ID"));
        map.put("hireContract.ownerId", contractInfo.get("OWNER_ID"));
        map.put("hireContract.ownerSnapshotId", contractInfo.get("OWNER_SNAPSHOT_ID"));
        map.put("hireContract.isOnlySave", contractInfo.get("IS_ONLY_SAVE"));
        map.put("hireContract.auditState", contractInfo.get("AUDIT_STATE"));
        map.put("isAudit", "audit");
//		map.put("user", "org.hpin.system.userManager.entity.User@56f7e3e3");
        map.put("isPass", "0");
        map.put("isSpecial", "");
        map.put("hmResblock.standardId", contractInfo.get("RESBLOCK_ID"));
        map.put("hireContract.houseCode", contractInfo.get("HOUSE_CODE"));
        map.put("isDisabled", "1");
        map.put("isUpdate", "false");
        map.put("hireContract.businessUID", contractInfo.get("BUSINESS_UID"));
        map.put("houseName", contractInfo.get("NAME"));
        map.put("productId", "精装");
        map.put("hireContract.productId", FunctionCommon.replaceString(contractInfo.get("PRODUCT_ID")));
        map.put("hireContract.outHouseProduct", contractInfo.get("OUT_HOUSE_PRODUCT"));
        map.put("hireContract.isPrint", FunctionCommon.replaceString(contractInfo.get("IS_PRINT")));
        map.put("hireContract.printVersion", FunctionCommon.replaceString(contractInfo.get("PRINT_VERSION")));
        map.put("customerCode", cusInfo.get("CUSTOMER_CODE"));
        map.put("nationality", "");
        map.put("emergency.id", FunctionCommon.replaceString(contractInfo.get("EMERGENCY_ID")));
        map.put("sex", "");
        map.put("mobile", contractInfo.get("ACCOUNT_PHONE"));
        map.put("paperType", contractInfo.get("CLIENT_CARD_TYPE_CODE"));
        map.put("peperCode", cusInfo.get("PEPER_CODE"));
        map.put("address", cusInfo.get("ADDRESS"));
        map.put("customerSource", cusInfo.get("CUSTOMER_SOURCE"));
        map.put("peperCode", cusInfo.get("PEPER_CODE"));
        map.put("hireContract.accountBank", contractInfo.get("ACCOUNT_BANK"));
        map.put("hireContract.zhiBank", contractInfo.get("ZHI_BANK"));
        map.put("hireContract.accountHolder", contractInfo.get("ACCOUNT_HOLDER"));
        map.put("hireContract.bankAccount", contractInfo.get("BANK_ACCOUNT"));
        map.put("hireContract.accountPhone", contractInfo.get("ACCOUNT_PHONE"));
        map.put("confirmBankAccount", contractInfo.get("BANK_ACCOUNT"));
        map.put("hireContract.hasClient", contractInfo.get("HAS_CLIENT"));
        map.put("hireContract.clientName", FunctionCommon.replaceString(contractInfo.get("CLIENT_NAME")));
        map.put("hireContract.clientPhone", FunctionCommon.replaceString(contractInfo.get("CLIENT_PHONE")));
        map.put("hireContract.clientSex", FunctionCommon.replaceString(contractInfo.get("CLIENT_SEX")));
        map.put("hireContract.clientCardTypeCode", FunctionCommon.replaceString(contractInfo.get("CLIENT_CARD_TYPE_CODE")));
        map.put("hireContract.clientCard", FunctionCommon.replaceString(contractInfo.get("CLIENT_CARD")));
        map.put("hireContract.clientAddress", FunctionCommon.replaceString(contractInfo.get("CLIENT_ADDRESS")));
        map.put("hireContract.clientEmail", FunctionCommon.replaceString(contractInfo.get("CLIENT_EMAIL")));

        JSONObject jsobj = JSONObject.fromObject(map);
        System.out.println(jsobj);

        // 请求接口返回值
        Map<String, String> response = hcu.httpPostRequest(httpUrl, jsobj);

        String auditSql = "Select bz.audit_state from bz_hire_contract bz where bz.hire_contract_code='" + AmsGlobalParas.contractCode + "'";
        Map<String, String> auditState = fc.getAllDataFromOracleData(auditSql).get(0);

        //生成测试报告
        Reporter.log(httpUrl);
        Reporter.log(jsobj.toString());
        logger.info(httpUrl);
        logger.info(jsobj.toString());
        logger.info(response.toString());

        if (auditState.get("AUDIT_STATE").equals("2")) {
            logger.info("isUniqueForUpdateHireContract返回值" + response);
        } else {
            logger.info("isUniqueForUpdateHireContract返回值------->>>>>>为空");
        }
        return response.toString();

    }

    /**
     * @description: 清除数据
     * @author wujing
     **/

    public void clearData() {
        System.out.println(AmsGlobalParas.contractCode);
        String updateSql = "update bz_hire_contract bz set bz.audit_state=0 where bz.hire_contract_code='" + AmsGlobalParas.contractCode + "'";
        fc.getAllDataFromOracleData(updateSql);
        String auditSql = "Select bz.audit_state from bz_hire_contract bz where bz.hire_contract_code='" + AmsGlobalParas.contractCode + "'";
        Map<String, String> auditState = fc.getAllDataFromOracleData(auditSql).get(0);
        if (auditState.get("AUDIT_STATE").equals("0")) {
            logger.info("clearData返回值" + auditState);
        } else {
            logger.info("clearData返回值------->>>>>>为空");
        }

    }
}
