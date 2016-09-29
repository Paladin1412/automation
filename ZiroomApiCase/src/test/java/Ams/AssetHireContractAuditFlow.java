package Ams;

import Ams.AmsScript.AssetHireContractAuditCommon;
import Ams.confManagement.commonMethods.AmsGlobalParas;
import Ams.confManagement.commonMethods.FunctionCommon;
import Ams.confManagement.config.PropertyConstants;
import Zo.confManagement.commonMethods.ZoGlobalParas;
import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by wujing on 16/9/20.
 */
public class AssetHireContractAuditFlow {
    private static final Logger logA = Logger.getLogger(AssetHireContractAuditFlow.class);

    AssetHireContractAuditCommon common = new AssetHireContractAuditCommon();
    FunctionCommon fc = new FunctionCommon();

    public String contractCode;

    @BeforeClass
    public void init_config() {

//        String ViewJson = "{\"usermobile\":\"13810327187\",\"diningRoom\":\"0\",\"afterRoomNum\":\"3\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"1\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"account\":\"20092810\",\"productVesion\":\"1008\",\"district\":\"通州\",\"resblock\":\"格兰晴天\"}";
//        viewJson = JSONObject.fromObject(ViewJson);

        contractCode = ZoGlobalParas.contractCode;
        common.initViewJson(ZoGlobalParas.viewJson);
        AmsGlobalParas.contractCode = contractCode;
    }


    @Test(description = "AMS登录")
    public void testLoginAsset() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000157'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.loginAsset(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf("确认");
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testLoginAsset返回值为" + responseJson);
        } else {
            logA.debug("用例testLoginAsset请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "AMS选择城市")
    public void testSelectCity() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000158'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.selectCity(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf("/AMS");
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testSelectCity返回值为" + responseJson);
        } else {
            logA.debug("用例testSelectCity请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "AMS首页")
    public void testIndexAMS() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000159'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.indexAMS(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf("资产管理系统");
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testIndexAMS返回值为" + responseJson);
        } else {
            logA.debug("用例testIndexAMS请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "跳转到首页")
    public void testGetResources() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000160'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.getResources(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf("资产管理系统");
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testGetResources返回值为" + responseJson);
        } else {
            logA.debug("用例testGetResources请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "保存成交报告")
    public void testHiredealReport() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000161'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.hiredealReport(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf("操作成功！");
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testHiredealReport返回值为" + responseJson);
        } else {
            logA.debug("用例testHiredealReport请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "搜索合同")
    public void testListContractAudit() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000162'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.listContractAudit(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf(contractCode);
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testListContractAudit返回值为" + responseJson);
        } else {
            logA.debug("用例testListContractAudit请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "点击审核")
    public void testIndexHireContract() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000163'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.indexHireContract(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf("出租合同录入");
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testIndexHireContract返回值为" + responseJson);
        } else {
            logA.debug("用例testIndexHireContract请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "点击审核通过")
    public void testIsUniqueForUpdateHireContract() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000164'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.isUniqueForUpdateHireContract(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        int index = responseJson.indexOf("true");//
        System.out.print(index);
        if (responseJson != null) {
            logA.debug("用例testIsUniqueForUpdateHireContract返回值为" + responseJson);
        } else {
            logA.debug("用例testIsUniqueForUpdateHireContract请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);
    }

    @Test(description = "审核通过")
    public void testSaveContractAuditInfo() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000165'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.ASSET_DOMAIN;
        String httpUrl = domainName + SuffixUrl;

        String responseJson = common.saveContractAuditInfo(httpUrl);

        Reporter.log("请求地址:" + httpUrl);
        logA.info(responseJson);

        String auditSql = "Select bz.audit_state from bz_hire_contract bz where bz.hire_contract_code='" + AmsGlobalParas.contractCode + "'";
        Map<String, String> auditState = fc.getAllDataFromOracleData(auditSql).get(0);
        if (auditState.get("AUDIT_STATE").equals("2")) {
            logA.info("isUniqueForUpdateHireContract返回值" + responseJson);
        } else {
            logA.info("isUniqueForUpdateHireContract返回值------->>>>>>为空");
        }
//        int index = responseJson.indexOf("2");//
//        System.out.print(index);
//        if (responseJson !=null) {
//            logA.debug("用例testSaveContractAuditInfo返回值为" + responseJson);
//        } else {
//            logA.debug("用例testSaveContractAuditInfo请求返回值------>>>>为空");
//        }
        Assert.assertNotEquals(2, auditState.get("AUDIT_STATE"));

        System.out.println("合同:" + contractCode + "审核通过");
    }

    @Test(description = "AMS清除数据")
    public void testAmsDataClear() {
        common.clearData();
    }

}
