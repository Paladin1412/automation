package keeper.ValuationModel;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.appScript.Keeper_ValuationModelCommon;
import keeper.confManagement.commonMethods.EHR.EHRUserInfoCommon;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import keeper.confManagement.config.ValuationModelPropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujing on 16/9/1.
 */
public class ValuationModleZZ {

    private static final Logger logV = Logger.getLogger(ValuationModleZZ.class);

    Keeper_Me me = new Keeper_Me();
    JSONObject result = new JSONObject();
    Keeper_ValuationModelCommon vmc = new Keeper_ValuationModelCommon();
    EHRUserInfoCommon ehr = new EHRUserInfoCommon();

    JSONObject getEntireHirePrice;
    JSONObject getStandardId;
    JSONObject savePriceMode;
    JSONObject decorateInfo;
    JSONObject saveConfig;
    JSONObject saveConfigInfo;
    JSONObject getEntireConfigItems;

    @BeforeClass
    public void init_config() {
        KeeperGlobalParas.ehrJson = ehr.ehrUserInfoFlow(KeeperGlobalParas.rentHouseUserAccount);

        vmc.getUserInfo(KeeperGlobalParas.ehrJson);
        vmc.initViewJson(KeeperGlobalParas.viewJsonZZ);

//        String ViewJson = "{\"usermobile\":\"13810327187\",\"diningRoom\":\"0\",\"afterRoomNum\":\"2\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"3\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"account\":\"20092810\",\"productVesion\":\"1003\",\"district\":\"通州\",\"resblock\":\"格兰晴天\"}";
//        viewJson = JSONObject.fromObject(ViewJson);
//
//        String EhrJson = "{\"status\":\"success\",\"error_message\":\"获取管家信息成功\",\"data\":{\"phone\":\"13552169604\",\"centerCode\":\"Z10001\",\"cityCode\":11,\"groupCode\":\"Z30208\",\"emplid\":\"20092810\",\"center\":\"业务拓展中心\",\"email\":\"maj53@ziroom.com\",\"name\":\"马洁\",\"jobCode\":\"100076\",\"dept\":\"学院大区\",\"deptCode\":\"Z20076\",\"group\":\"国展业务组\",\"descr\":\"直收管家\",\"setId\":\"HL011\",\"treePath\":\"Z00001,Z00002,Z10001,Z20076,Z30208\"}}";
//        ehrJson = JSONObject.fromObject(EhrJson);
//
//        String houseJson = " {\"data\":{\"unit\":\"3单元\",\"districtName\":\"通州\",\"villageId\":\"1111027374633\",\"districtId\":\"23008625\",\"floor\":\"1\",\"status\":\"success\",\"address\":\"通州格兰晴天7号楼3单元1312\",\"seccondSource\":\"21\",\"roomNum\":\"312\",\"buildNum\":\"7号楼\",\"firstSource\":\"4\",\"villageName\":\"格兰晴天\"},\"error_message\":\"通州格兰晴天7号楼3单元1312房源宝录制成功\",\"status\":\"success\"}";
//
//        vmc.getUserInfo(ehrJson);
//        vmc.initViewJson(viewJson);
//
//        houseinfo = houseInfo.getJSONObject("data");

    }

    @Test(description = "登录网站")
    public void testLogin() {

        String domainName = PropertyConstants.CRM_LOGIN_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000103'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);

        String login = vmc.login(httpUrl);

        int index = login.indexOf("平台首页");
        System.out.print(index);
        if (login != null) {
            logV.debug("用例testMLogin返回值为" + login);
        } else {
            logV.debug("用例testMLogin请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "获得整租价格")
    public void testGetEntireHirePrice() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000104'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);
        getEntireHirePrice = vmc.getEntireHirePrice(httpUrl);

        String actual = "";
        if (getEntireHirePrice != null) {
            actual = getEntireHirePrice.getString("status");
            logV.debug("用例testGetEntireHirePrice返回值:" + getEntireHirePrice);
        } else {
            logV.debug("用例testGetEntireHirePrice返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "得到standardId")
    public void testGetStandardID() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000104'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);

        getStandardId = vmc.getStandardId(KeeperGlobalParas.houseinfo, httpUrl);

        String status = getStandardId.getJSONObject("responseHeader").getString("status");
        Assert.assertEquals(status, "0");
        if (status.equals("0")) {
            logV.debug("用例testGetStandardID返回值:" + getStandardId);
        } else {
            result.putAll(getStandardId);

        }
    }

    @Test(description = "保存数据")
    public void testSavePriceMode() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000105'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl + ValuationModelPropertyConstants.apiUri;

        Reporter.log("请求地址:" + httpUrl);
        savePriceMode = vmc.savePriceMode(KeeperGlobalParas.houseinfo, getEntireHirePrice, getStandardId, httpUrl);

        String actual = "";
        if (savePriceMode != null) {
            actual = savePriceMode.getString("status");
            logV.debug("用例testGetEntireHirePrice返回值:" + savePriceMode);
        } else {
            logV.debug("用例testGetEntireHirePrice返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取装修配置信息")
    public void testGetEntireConfigItems() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000105'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);
        getEntireConfigItems = vmc.getEntireConfigItems(httpUrl);

        String actual = "";
        if (getEntireConfigItems != null) {
            actual = getEntireConfigItems.getString("status");
            logV.debug("用例testGetEntireHirePrice返回值:" + getEntireConfigItems);
        } else {
            logV.debug("用例testGetEntireHirePrice返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");


    }

    @Test(description = "装修信息")
    public void testDecorateInfo() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000105'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl + ValuationModelPropertyConstants.apiUri;

        Reporter.log("请求地址:" + httpUrl);

        decorateInfo = vmc.decorateInfo(savePriceMode, httpUrl);

        String actual = "";
        if (decorateInfo != null) {
            actual = decorateInfo.getString("status");
            logV.debug("用例testGetEntireHirePrice返回值:" + decorateInfo);
        } else {
            logV.debug("用例testGetEntireHirePrice返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "配置信息")
    public void testSaveConfig() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000105'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl + ValuationModelPropertyConstants.apiUri;

        Reporter.log("请求地址:" + httpUrl);
        saveConfig = vmc.saveConfig(savePriceMode, httpUrl);

        String actual = "";
        if (saveConfig != null) {
            actual = saveConfig.getString("status");
            logV.debug("用例testGetEntireHirePrice返回值:" + saveConfig);
        } else {
            logV.debug("用例testGetEntireHirePrice返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

//    @Test(description = "保存信息")
//    public void testSaveConfig1(){
//        vmc.saveConfig(savePriceMode);
//    }

    @Test(description = "保存配置信息")
    public void testSaveConfigInfo() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000104'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);
        saveConfigInfo = vmc.saveConfigInfo(httpUrl);

        String actual = "";
        if (saveConfigInfo != null) {
            actual = saveConfigInfo.getString("status");
            logV.debug("用例testGetEntireHirePrice返回值:" + saveConfigInfo);
        } else {
            logV.debug("用例testGetEntireHirePrice返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "更新审核状态")
    public void testUpdateAuditStatus() {
        vmc.updateAuditStatus();
//        vmc.getAssessID();//整租获取提审单号用于删除计价模型接口使用
    }

    @Test(description = "保存价格配置信息")
    public void testAdmitPriceMode() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000104'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);
        String admitPriceMode = vmc.admitPriceMode(httpUrl);

        int index = admitPriceMode.indexOf("提交成功!");
        System.out.print(index);
        if (admitPriceMode != null) {
            logV.debug("用例testAdmitPriceMode返回值为" + admitPriceMode);
        } else {
            logV.debug("用例testAdmitPriceMode请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);


        result.put("status", "success");
        result.put("error_message", KeeperGlobalParas.standardId + "计价模型创建成功");
        Map<String, String> map = new HashMap<String, String>();
        String assessCode = vmc.getAssessCode();
        map.put("assessCode", assessCode);
        map.put("cityCode", vmc.cityCode);
        map.put("standardID", KeeperGlobalParas.standardId);

        JSONObject jsonb = JSONObject.fromObject(map);
        result.put("data", jsonb.toString());
        KeeperGlobalParas.modePrice = result;

        logV.info(result);
        Reporter.log(result.toString());
//        vmc.getAssessID();//获取提审单号
        KeeperGlobalParas.modePrice = result;

    }


    @Test(description = "撤销计价模型")
    public void testAdmitCancel() {
        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000105'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);
        String admitCancel = vmc.admitCancel(httpUrl);

        int index = admitCancel.indexOf("已撤销");
        System.out.print(index);
        if (admitCancel != null) {
            logV.debug("用例testAdmitCancel返回值为" + admitCancel);
        } else {
            logV.debug("用例testAdmitCancel请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);

    }

//    @Test(description = "整租计价模型")
//    public void testZZDemo(){
//        System.out.println(viewJson);
//        System.out.println(ehrJson);
//        System.out.println(houseInfo);
//        JSONObject modePriceZZFlow = null;
//
//        modePriceZZFlow = ValuationModelFlow.modePriceZZFlow(ehrJson, houseInfo.getJSONObject("data"),
//                viewJson);
//        System.out.println(modePriceZZFlow.toString());
//
//    }
}
