package Zo;

import Zo.ZoZiroomScript.ZoHireHouseCommon;
import Zo.confManagement.commonMethods.FunctionCommon;
import Zo.confManagement.commonMethods.ZoGlobalParas;
import Zo.confManagement.config.PropertyConstants;
import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.confManagement.commonMethods.EHR.EHRUserInfoCommon;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by wujing on 16/9/9.
 */
public class ZoHireHouseFlowManagerFZ {
    private static final Logger logZ = Logger.getLogger(ZoHireHouseFlowManagerFZ.class);

    ZoHireHouseCommon common = new ZoHireHouseCommon();
    EHRUserInfoCommon ehr = new EHRUserInfoCommon();

    JSONObject result = new JSONObject();

    //    JSONObject houseInfo;
//    JSONObject modePrice;
    JSONObject hireNewSign;
    JSONObject queryCustomerInfo;
    JSONObject getHouseAssessInfo;
    JSONObject saveHireNewSignPage;
    JSONObject findHireProperty;
    JSONObject addHireProperty;
    JSONObject hireNewSignContract;
    JSONObject saveHouseInfo;
    JSONObject saveRoomInfo;
    JSONObject savePublicInfo;
    JSONObject getUsersList;
    JSONObject getHigherLevel;
    JSONObject getManageInfo;
    JSONObject getEhrDeptInfo;
    JSONObject saveHirePeopleInfo;
    JSONObject toHireSupplyAgreement;
    JSONObject compileHire;

    @BeforeClass
    public void init_config() {

        KeeperGlobalParas.ehrJson = ehr.ehrUserInfoFlow(KeeperGlobalParas.rentHouseUserAccount);

        common.getUserInfo(KeeperGlobalParas.ehrJson);
        common.initViewJson(KeeperGlobalParas.viewJsonFZ);
        ZoGlobalParas.address = KeeperGlobalParas.houseinfo.get("address").toString();
        System.out.println(ZoGlobalParas.address);

        ZoGlobalParas.viewJson = KeeperGlobalParas.viewJsonFZ;//用于AMS审核数据

//        String modeJson = "{\"status\":\"success\",\"error_message\":\"计价模型创建成功:P1609142132080  房屋编号为:1115030424407\",\"data\":{\"assessCode\":\"P1609142132080\",\"cityCode\":\"110000\",\"standardID\":\"1115030424407\"}}";
//        modePrice = JSONObject.fromObject(modeJson);
    }

    @Test(description = "ZO登录")
    public void testZoLogin() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000116'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.zoLogin(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(responseJson);

        int index = responseJson.indexOf("queryBusOppList");
        System.out.print(index);
        if (responseJson != null) {
            logZ.debug("用例testZoLogin返回值为" + responseJson);
        } else {
            logZ.debug("用例testZoLogin请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "ZO首页")
    public void testZoIndex() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000117'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.zoIndex(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(responseJson);

        int index = responseJson.indexOf("ZO-CRM 首页");
        System.out.print(index);
        if (responseJson != null) {
            logZ.debug("用例testZoIndex返回值为" + responseJson);
        } else {
            logZ.debug("用例testZoIndex请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "Zo添加合同")
    public void testToHireNewSignPage() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000118'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String responseJson = common.toHireNewSignPage(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(responseJson);

        int index = responseJson.indexOf("新签-合同信息");
        System.out.print(index);
        if (responseJson != null) {
            logZ.debug("用例testToHireNewSignPage返回值为" + responseJson);
        } else {
            logZ.debug("用例testToHireNewSignPage请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "获取计价模型")
    public void testHireNewSign() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000119'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        hireNewSign = common.hireNewSign(KeeperGlobalParas.modePrice, httpUrl);

        if (hireNewSign.get("status").equals("failure")) {
            result.putAll(hireNewSign);
        } else {
            logZ.debug("用例testHireNewSign返回值为" + hireNewSign);
        }
        String actual = hireNewSign.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取业主信息")
    public void testQueryCustomerInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000120'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        queryCustomerInfo = common.queryCustomerInfo(httpUrl);

        if (queryCustomerInfo.get("status").equals("failure")) {
            result.putAll(queryCustomerInfo);
        } else {
            logZ.debug("用例testQueryCustomerInfo返回值为" + queryCustomerInfo);
        }
        String actual = queryCustomerInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取计价模型信息")
    public void testGetHouseAssessInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000121'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        getHouseAssessInfo = common.getHouseAssessInfo(httpUrl);

        if (getHouseAssessInfo.get("status").equals("failure")) {
            result.putAll(getHouseAssessInfo);
        } else {
            logZ.debug("用例testGetHouseAssessInfo返回值为" + getHouseAssessInfo);
        }
        String actual = getHouseAssessInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "保存")
    public void testSaveHireNewSignPage() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000122'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        saveHireNewSignPage = common.saveHireNewSignPage(hireNewSign, queryCustomerInfo, ZoGlobalParas.address, getHouseAssessInfo, httpUrl);

        if (saveHireNewSignPage.get("status").equals("failure")) {
            result.putAll(saveHireNewSignPage);
        } else {
            logZ.debug("用例testSaveHireNewSignPage返回值为" + saveHireNewSignPage);
        }
        String actual = saveHireNewSignPage.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "物业交割前点击确认")
    public void testFindHireProperty() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000123'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        findHireProperty = common.findHireProperty(saveHireNewSignPage, httpUrl);

        if (findHireProperty.get("status").equals("failure")) {
            result.putAll(findHireProperty);
        } else {
            logZ.debug("用例testFindHireProperty返回值为" + findHireProperty);
        }
        String actual = findHireProperty.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "添加物业交割")
    public void testAddHreProperty() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000124'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        addHireProperty = common.addHireProperty(httpUrl);

        if (addHireProperty.get("status").equals("failure")) {
            result.putAll(addHireProperty);
        } else {
            logZ.debug("用例testAddHreProperty返回值为" + addHireProperty);
        }
        String actual = addHireProperty.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "得到收房合同号")
    public void testGetHireContractCode() {

        ZoGlobalParas.contractCode = common.getHireContractCode();
        hireNewSignContract = common.hireNewSign();

        if (hireNewSignContract.get("status").equals("failure")) {
            result.putAll(hireNewSignContract);
        } else {
            logZ.debug("用例testGetHireContractCode返回值为" + hireNewSignContract);
        }
        String actual = hireNewSignContract.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "修改接口")
    public void testToHireNewSignPageFlow() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000125'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String responseJson = common.toHireNewSignPageFlow(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(responseJson);

        int index = responseJson.indexOf("新签-合同信息");
        System.out.print(index);
        if (responseJson != null) {
            logZ.debug("用例testToHireNewSignPageFlow返回值为" + responseJson);
        } else {
            logZ.debug("用例testToHireNewSignPageFlow请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "打开基础信息保存")
    public void testToHireBaseInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000126'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String responseJson = common.toHireBaseInfo(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(responseJson);

        int index = responseJson.indexOf("新签-基础信息");
        System.out.print(index);
        if (responseJson != null) {
            logZ.debug("用例testToHireBaseInfo返回值为" + responseJson);
        } else {
            logZ.debug("用例testToHireBaseInfo请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "保存基础信息保存")
    public void testSaveBaseInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000127'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String responseJson = common.saveBaseInfo(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(responseJson);

        int index = responseJson.indexOf("302");
        System.out.print(index);
        if (responseJson != null) {
            logZ.debug("用例testSaveBaseInfo返回值为" + responseJson);
        } else {
            logZ.debug("用例testSaveBaseInfo请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "打开房源信息页面")
    public void testToHireHouseInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000128'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String responseJson = common.toHireHouseInfo(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(responseJson);

        int index = responseJson.indexOf("新签-房源信息");
        System.out.print(index);
        if (responseJson != null) {
            logZ.debug("用例testToHireHouseInfo返回值为" + responseJson);
        } else {
            logZ.debug("用例testToHireHouseInfo请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "保存房源信息")
    public void testSaveHouseInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000129'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        saveHouseInfo = common.saveHouseInfo(KeeperGlobalParas.houseinfo, httpUrl);

        if (saveHouseInfo.get("status").equals("failure")) {
            result.putAll(saveHouseInfo);
        } else {
            logZ.debug("用例testSaveHouseInfo返回值为" + saveHouseInfo);
        }
        String actual = saveHouseInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "保存房间信息")
    public void testSaveRoomInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000130'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        saveRoomInfo = common.saveRoomInfo(KeeperGlobalParas.houseinfo, httpUrl);

        if (saveRoomInfo.get("status").equals("failure")) {
            result.putAll(saveRoomInfo);
        } else {
            logZ.debug("用例testSaveHouseInfo返回值为" + saveRoomInfo);
        }
        String actual = saveRoomInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "保存公共区域信息")
    public void testSavePublicInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000130'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        savePublicInfo = common.savePublicInfo(KeeperGlobalParas.houseinfo, httpUrl);

        if (savePublicInfo.get("status").equals("failure")) {
            result.putAll(savePublicInfo);
        } else {
            logZ.debug("用例testSaveHouseInfo返回值为" + savePublicInfo);
        }
        String actual = savePublicInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "跳转")
    public void testSplitPage() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000133'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String splitPage = common.splitPage(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(splitPage);

        int index = splitPage.indexOf("302");
        System.out.print(index);
        if (splitPage != null) {
            logZ.debug("用例testSplitPage返回值为" + splitPage);
        } else {
            logZ.debug("用例testSplitPage请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);

    }

    @Test(description = "打开收房人信息")
    public void testToHirePeopleInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000134'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String toHirePeopleInfo = common.toHirePeopleInfo(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(toHirePeopleInfo);

        int index = toHirePeopleInfo.indexOf("新签-收房人信息");
        System.out.print(index);
        if (toHirePeopleInfo != null) {
            logZ.debug("用例testToHirePeopleInfo返回值为" + toHirePeopleInfo);
        } else {
            logZ.debug("用例testToHirePeopleInfo请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);

    }

    @Test(description = "得到管家号码")
    public void testGetUsersList() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000135'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        getUsersList = common.getUsersList(httpUrl);

        if (getUsersList.getJSONArray("rows").size() == 0) {
            result.putAll(getUsersList);
        } else {
            logZ.debug("用例testGetUsersList返回值为" + getUsersList);
        }
        int index = getUsersList.toString().indexOf("center");
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "得到管家的上级号码")
    public void testGetHigherLevel() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000137'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        getHigherLevel = common.getHigherLevel(httpUrl);

        if (getHigherLevel.get("status").equals("failure")) {
            result.putAll(getHigherLevel);
        } else {
            logZ.debug("用例testGetHigherLevel返回值为" + getHigherLevel);
        }
        String actual = getHigherLevel.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "从楼盘系统中获取所属管家")
    public void testGetManageInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000138'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZRBD_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        getManageInfo = common.getManageInfo(httpUrl);

        if (getManageInfo.get("status").equals("failure")) {
            result.putAll(getManageInfo);
        } else {
            logZ.debug("用例testGetManageInfo返回值为" + getManageInfo);
        }
        String actual = getManageInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "从EHR系统中获取所属管家的上级")
    public void testGetEhrDeptInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000139'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.EHR_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        getEhrDeptInfo = common.getEhrDeptInfo(httpUrl);

        if (getEhrDeptInfo.get("status").equals("failure")) {
            result.putAll(getEhrDeptInfo);
        } else {
            logZ.debug("用例testGetEhrDeptInfo返回值为" + getEhrDeptInfo);
        }
        String actual = getEhrDeptInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "保存管家信息")
    public void testSaveHirePeopleInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000140'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        saveHirePeopleInfo = common.saveHirePeopleInfo(getUsersList, getHigherLevel, getManageInfo, httpUrl);

        if (saveHirePeopleInfo.get("status").equals("failure")) {
            result.putAll(saveHirePeopleInfo);
        } else {
            logZ.debug("用例testGetEhrDeptInfo返回值为" + saveHirePeopleInfo);
        }
        String actual = saveHirePeopleInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "打开付款计划")
    public void testToHirePaymentPlan() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000141'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String toHirePaymentPlan = common.toHirePaymentPlan(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(toHirePaymentPlan);

        int index = toHirePaymentPlan.indexOf("新签-付款计划");
        System.out.print(index);
        if (toHirePaymentPlan != null) {
            logZ.debug("用例testToHirePaymentPlan返回值为" + toHirePaymentPlan);
        } else {
            logZ.debug("用例testToHirePaymentPlan请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "保存付款计划")
    public void testPaymentPlan() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000142'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        String payMentPlan = common.payMentPlan(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logZ.info(payMentPlan);

        int index = payMentPlan.indexOf("302");
        System.out.print(index);
        if (payMentPlan != null) {
            logZ.debug("用例testPaymentPlan返回值为" + payMentPlan);
        } else {
            logZ.debug("用例testPaymentPlan请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "打开补充协议")
    public void testToHireSupplyAgreement() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000143'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        toHireSupplyAgreement = common.toHireSupplyAgreement(httpUrl);

        if (toHireSupplyAgreement.get("status").equals("failure")) {
            result.putAll(toHireSupplyAgreement);
        } else {
            logZ.debug("用例testToHireSupplyAgreement返回值为" + toHireSupplyAgreement);
        }
        String actual = toHireSupplyAgreement.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交审核")
    public void testCompileHire() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000144'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ZO_DOMAIN;
        String httpUrl = domainName + "/" + ZoGlobalParas.cityCode + SuffixUrl;

        compileHire = common.compileHire(httpUrl);

        if (compileHire.get("status").equals("failure")) {
            result.putAll(compileHire);
        } else {
            logZ.debug("用例testCompileHire返回值为" + compileHire);
        }
        String actual = compileHire.getString("status");
        Assert.assertEquals(actual, "success");

        System.out.println("收房合同已经提交审核:" + ZoGlobalParas.contractCode + "  房屋编号为：" + ZoGlobalParas.standardID);
    }


    @Test(description = "清空ZO系统数据")
    public void testZoDataClear() {
        System.out.println(ZoGlobalParas.contractCode);
//删除此条数据方便反复调用
        String deleteSql1 = "delete from BZ_HOUSE where HIRE_CONTRACT_CODE='" + ZoGlobalParas.contractCode + "'";
        FunctionCommon.updateOracleData(deleteSql1);
        String deleteSql2 = "delete from bz_room room where room.hire_contract_code='" + ZoGlobalParas.contractCode + "'";
        FunctionCommon.updateOracleData(deleteSql2);
        String deleteSql3 = "delete from hlasset.bz_hire_contract brz where brz.hire_contract_code='" + ZoGlobalParas.contractCode + "'";
        FunctionCommon.updateOracleData(deleteSql3);

    }
}
