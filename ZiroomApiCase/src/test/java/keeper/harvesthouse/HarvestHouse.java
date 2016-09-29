package keeper.harvesthouse;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;

import config.PropertyConstants;
import keeper.appScript.Keeper_SourceTreasure;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import net.sf.json.JSONObject;


public class HarvestHouse {
    private static final Logger logHH = Logger.getLogger(HarvestHouse.class);
    Keeper_SourceTreasure hh = new Keeper_SourceTreasure();

    @BeforeClass
    public void init() {

    }

    @Test(description = "获取城区列表(添加商机)")
    public void getDistrictList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000075'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = hh.s_getDistrictList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getDistrictList返回值:" + responseJson);
        } else {
            logHH.debug("用例getDistrictList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取小区名字(添加商机)")
    public void getVillageList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000077'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getVillageList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getVillageList返回值:" + responseJson);
        } else {
            logHH.debug("用例getVillageList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取楼栋号(添加商机)")
    public void getBuildNumList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000076'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getBuildNumList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getBuildNumList返回值:" + responseJson);
        } else {
            logHH.debug("用例getBuildNumList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取楼栋单元号(添加商机)")
    public void getUnitList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000078'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getUnitList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getUnitList返回值:" + responseJson);
        } else {
            logHH.debug("用例getUnitList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取楼层列表(添加商机)")
    public void getFloorList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000079'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getFloorList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getFloorList返回值:" + responseJson);
        } else {
            logHH.debug("用例getFloorList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "获取房屋列表(添加商机)")
    public void getRoomNumList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000080'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getRoomNumList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getRoomNumList返回值:" + responseJson);
        } else {
            logHH.debug("用例getRoomNumList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "商机来源(添加商机)")
    public void queryBOFirstSourceList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000081'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryBOFirstSourceList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryBOFirstSourceList返回值:" + responseJson);
        } else {
            logHH.debug("用例queryBOFirstSourceList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "二级商机来源(添加商机)")
    public void queryBOSecondSourceList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000082'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryBOSecondSourceList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryBOSecondSourceList返回值:" + responseJson);
        } else {
            logHH.debug("用例queryBOSecondSourceList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "二级商机来源(添加商机)")
    public void getKeeperByAndSourceType() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000083'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getKeeperByAndSourceType(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getKeeperByAndSourceType返回值:" + responseJson);
        } else {
            logHH.debug("用例getKeeperByAndSourceType返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交一(添加商机)")
    public void appSearchBusOppByStandardInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000084'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_appSearchBusOppByStandardInfo(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例appSearchBusOppByStandardInfo返回值:" + responseJson);
        } else {
            logHH.debug("用例appSearchBusOppByStandardInfo返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交二(添加商机)")
    public void createBusOpp() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000085'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_createBusOpp(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例appSearchBusOppByStandardInfo返回值:" + responseJson);
        } else {
            logHH.debug("用例appSearchBusOppByStandardInfo返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交三(添加商机)")
    public void getClewTotalNumByStatus() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000086'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getClewTotalNumByStatus(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getClewTotalNumByStatus返回值:" + responseJson);
        } else {
            logHH.debug("用例getClewTotalNumByStatus返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "提交四(添加商机)")
    public void queryBOTotalList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000087'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryBOTotalList(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryBOTotalList返回值:" + responseJson);
        } else {
            logHH.debug("用例queryBOTotalList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "我的商机")
    public void queryBOListByMyEntry() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000089'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryBOListByMyEntry(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryBOTotalList返回值:" + responseJson);
        } else {
            logHH.debug("用例queryBOTotalList返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "待跟进")
    public void queryBOListByKeeper() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000091'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryBOListByKeeper(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryBOListByKeeper返回值:" + responseJson);
        } else {
            logHH.debug("用例queryBOListByKeeper返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "点击跟进商机")
    public void queryHouseInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000096'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryHouseInfo(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryHouseInfo返回值:" + responseJson);
        } else {
            logHH.debug("用例queryHouseInfo返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "新增跟进")
    public void getConfigInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000100'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getConfigInfo(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getConfigInfo返回值:" + responseJson);
        } else {
            logHH.debug("用例getConfigInfo返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "保存跟进")
    public void addTrackByKeeper() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000102'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_addTrackByKeeper(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例addTrackByKeeper返回值:" + responseJson);
        } else {
            logHH.debug("用例addTrackByKeeper返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    ///--------------------------------------------------
    @Test(description = "首次跟进")
    public void queryBOListByKeeper1() {
        KeeperGlobalParas.trackState = "1";
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000091'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryBOListByKeeper(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryBOListByKeeper1返回值:" + responseJson);
        } else {
            logHH.debug("用例queryBOListByKeeper1返回值----------->>>>>>>>>>>为空");
        }
        KeeperGlobalParas.trackState = "0";
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "点击首次跟进")
    public void queryHouseInfo1() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000096'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_queryHouseInfo(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryHouseInfo1返回值:" + responseJson);
        } else {
            logHH.debug("用例queryHouseInfo1返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "点击首次跟进实勘记录")
    public void getSurveyInfoByHouseId() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000106'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getSurveyInfoByHouseId(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例queryHouseInfo1返回值:" + responseJson);
        } else {
            logHH.debug("用例queryHouseInfo1返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "添加/查看实勘照片")
    public void getPicListByHouseId() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000106'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_getPicListByHouseId(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例getPicListByHouseId返回值:" + responseJson);
        } else {
            logHH.debug("用例getPicListByHouseId返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "实勘上传照片")
    public void submitHousePic() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000109'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_submitHousePic(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例submitHousePic返回值:" + responseJson);
        } else {
            logHH.debug("用例submitHousePic返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "保存实勘信息")
    public void saveSurvey() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000112'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_saveSurvey(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例s_saveSurvey返回值:" + responseJson);
        } else {
            logHH.debug("用例s_saveSurvey返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "提交实勘信息")
    public void commitSurvey() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000113'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_commitSurvey(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例s_commitSurvey返回值:" + responseJson);
        } else {
            logHH.debug("用例s_commitSurvey返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "核销商机")
    public void cancelBo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000168'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        JSONObject responseJson = hh.s_cancelBo(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logHH.info("用例s_commitSurvey返回值:" + responseJson);
        } else {
            logHH.debug("用例s_commitSurvey返回值----------->>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }
}
