package keeper.ValuationModel;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.appScript.Keeper_ValuationModelCommon;
import keeper.confManagement.commonMethods.EHR.EHRUserInfoCommon;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONArray;
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
public class ValuationModleFZ {

    private static final Logger logV = Logger.getLogger(ValuationModleFZ.class);

    Keeper_Me me = new Keeper_Me();
    JSONObject result = new JSONObject();
    Keeper_ValuationModelCommon vmc = new Keeper_ValuationModelCommon();
    EHRUserInfoCommon ehr = new EHRUserInfoCommon();

    JSONObject gethouseInfo;
    JSONObject commitHouseInfo;
    String afterRoom;
    String assessCode;
    String standardId;
    String standardIdJson;
    JSONObject commitAssessInfo;
    JSONObject getHirePrice;
    JSONObject commitConfigInfo;
    JSONObject commitRoomInfo;
    JSONObject getRentPrice;


    @BeforeClass
    public void init_config() {

        KeeperGlobalParas.ehrJson = ehr.ehrUserInfoFlow(KeeperGlobalParas.rentHouseUserAccount);
//        System.out.println("~~~~" + KeeperGlobalParas.ehrJson);
//        vmc.getUserInfo(KeeperGlobalParas.ehrJson);
//        vmc.initViewJson(KeeperGlobalParas.viewJsonFZ);


    }

    @Test(description = "管家登录")
    public void testKeeperLogin() {
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000001'";
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.INTERFACES_DOMIN;
        String httpUrl = domainName + SuffixUrl;
        //请求参数

        JSONObject responseJson = me.s_login(httpUrl, KeeperGlobalParas.rentHouseUserAccount, KeeperGlobalParas.rentHousePwd);

        Reporter.log("请求地址:" + httpUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logV.debug("用例login返回值为" + responseJson);
        } else {
            logV.debug("用例login请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "M站登录")
    public void testMLogin() {
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000088'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;
//        System.out.println("httpUrl" + httpUrl);

        Reporter.log("请求地址:" + httpUrl);

        String mlogin = vmc.mLogin(httpUrl);

        int index = mlogin.indexOf("引导页 - 计价模型");
        System.out.print(index);
        if (mlogin != null) {
            logV.debug("用例testMLogin返回值为" + mlogin);
        } else {
            logV.debug("用例testMLogin请求返回值------>>>>为空");
        }
        Assert.assertNotEquals(-1, index);


    }

    @Test(description = "得到房源信息")
    public void testGetHouseInfo() {
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000090'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);

        gethouseInfo = vmc.houseInfo(KeeperGlobalParas.houseinfo, httpUrl);

        if (gethouseInfo.get("status").equals("failure")) {
            result.putAll(gethouseInfo);
        } else {
            logV.debug("用例testGetHouseInfo返回值为" + gethouseInfo);
        }
        String actual = gethouseInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交房源信息")
    public void testCommitHouseInfo() {
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000092'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);
        logV.info("请求地址:" + httpUrl);

        commitHouseInfo = vmc.commitHouseInfo(KeeperGlobalParas.houseinfo, gethouseInfo, httpUrl);
        if (commitHouseInfo.get("status").equals("failure")) {
            result.putAll(commitHouseInfo);
        } else {
            logV.debug("用例testCommitHouseInfo返回值为" + commitHouseInfo);
        }

        String actual = commitHouseInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交房间信息")
    public void testCommitRoomInfo() {
        standardIdJson = JSONArray.fromObject(gethouseInfo.getString("data")).getString(0);
        standardId = JSONObject.fromObject(standardIdJson).getString("standard_id");

        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000093'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        String DomainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000094'";
        String suffixUrl = ProviderDataSource.getDataString(SuffixSql);
        String HttpUrl = DomainName + suffixUrl;

        // 得到房间信息并提交
        afterRoom = vmc.afterRoom;
        for (int i = 0; i < Integer.parseInt(afterRoom); i++) {

            Reporter.log("getRentPrince请求地址:" + httpUrl);

            getRentPrice = vmc.getRentPrice(commitHouseInfo, httpUrl);
            if (getRentPrice.get("status").equals("failure")) {
                result.putAll(getRentPrice);
            } else {
                logV.debug("用例testCommitRoomInfo返回值为" + getRentPrice);
            }

            Reporter.log("commitRoomInfo请求地址:" + HttpUrl);

            commitRoomInfo = vmc.commitRoomInfo(getRentPrice, Integer.toString(i + 1), HttpUrl);
            if (commitRoomInfo.get("status").equals("failure")) {
                result.putAll(commitRoomInfo);
            } else {
                logV.debug("用例testCommitRoomInfo返回值为" + commitRoomInfo);
            }
            String actual1 = getRentPrice.getString("status");
            Assert.assertEquals(actual1, "success");
            String actual = commitRoomInfo.getString("status");
            Assert.assertEquals(actual, "success");
        }

    }

    @Test(description = "提交公共区域配置")
    public void testCommitConfigInfo() {
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000095'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);

        commitConfigInfo = vmc.commitConfigInfo(httpUrl);
        if (commitConfigInfo.get("status").equals("failure")) {
            result.putAll(commitConfigInfo);
        } else {
            logV.debug("用例testCommitConfigInfo返回值为" + commitConfigInfo);
        }
        String actual = commitConfigInfo.getString("status");
        Assert.assertEquals(actual, "success");

    }

    @Test(description = "得到出房价")
    public void testGetHirePrice() {
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000097'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log("请求地址:" + httpUrl);
        getHirePrice = vmc.getHirePrice(httpUrl);
        if (getHirePrice.get("status").equals("failure")) {
            result.putAll(getHirePrice);
        } else {
            logV.debug("用例testGetHirePrice返回值为" + getHirePrice);
        }
        String actual = getHirePrice.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交信息")
    public void testCommitAssessInfo() {
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000098'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;
        System.out.println(SuffixUrl);

        Reporter.log("请求地址:" + httpUrl);
        commitAssessInfo = vmc.commitAssessInfo(httpUrl);
        if (commitAssessInfo.get("status").equals("failure")) {
            result.putAll(commitAssessInfo);
        } else {
            //更新数据库状态为声和通过
            assessCode = vmc.assessCode;
            vmc.updateAuditStatus();
        }
        String actual = commitAssessInfo.getString("status");
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "上报评估")
    public void testAssessView() {
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000099'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl + standardId;

        Reporter.log("请求地址:" + httpUrl);
        String assessView = vmc.assessView(httpUrl);

        if (assessView != null) {
            logV.debug("用例testAssessView返回值为" + getHirePrice);
        } else {
            result.put("status", "false");
            result.put("error_message", "上报失败");
        }
    }

    @Test(description = "保存")
    public void testAdmitAssess() {
        String ID = vmc.getAssessID();
        String domainName = PropertyConstants.MODEPRICE_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(config.PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000101'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl + ID;

        Reporter.log("请求地址:" + httpUrl);

        JSONObject admitAssess = vmc.admitAssess(httpUrl);
        if (admitAssess.get("status").equals("failure")) {
            result.putAll(admitAssess);
        }
        String actual = admitAssess.getString("status");
        Assert.assertEquals(actual, "success");

        result.put("status", "success");
        result.put("error_message", "计价模型创建成功:" + assessCode + "  房屋编号为:" + standardId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("assessCode", assessCode);
        map.put("cityCode", vmc.cityCode);
        map.put("standardID", standardId);

        JSONObject jsonb = JSONObject.fromObject(map);
        result.put("data", jsonb.toString());
        System.out.println(result);
        KeeperGlobalParas.modePrice = result;
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

////    整体流程
//    @Test(description = "分租计价模型")
//    public void testDemo(){
//        System.out.println(viewJson);
//        System.out.println(ehrJson);
//        System.out.println(houseInfo);
//        JSONObject modePriceFlow = null;
//
//        modePriceFlow = ValuationModelFlow.modePriceFlow(ehrJson, houseInfo.getJSONObject("data"),
//                viewJson);
//        System.out.println(modePriceFlow.toString());
//
//    }
}
