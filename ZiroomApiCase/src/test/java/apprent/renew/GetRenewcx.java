package apprent.renew;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.appScript.Keeper_Renew;
import keeper.confManagement.commonMethods.RenewFunCommon;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Created by wujing on 16/8/3.
 */
public class GetRenewcx {
    private static final Logger logR = Logger.getLogger(GetRenewcx.class);

    Keeper_Renew re = new Keeper_Renew();
    Keeper_Me me = new Keeper_Me();


    @BeforeClass(description = "此类run之前运行")
    public void init_config() {
       // KeeperGlobalParas.appType = "1";
        KeeperGlobalParas.oldContractCode = "BJZYZCW81410050214-01";
        KeeperGlobalParas.customerName = "刘欣佳";
        KeeperGlobalParas.customerPhone = "18510864726";
        KeeperGlobalParas.uid = "afcd53c1-4d98-6f06-3fa9-275c160e6143";
        //老用户进行信用认证
        //String selectsql = "select * from tb_user_identity where phone = '" + GlobalParameter.customerPhone +"'";
        //RenewFunCommon.selectMysqlData(selectsql);
//        String updatestatussql = "update tb_user_identity set status = 4 where phone = '" + GlobalParameter.customerPhone + "'";
//        RenewFunCommon.updateMysqlData(updatestatussql);
//        String updateidentitystatussql = "update tb_user_status set identity_status = 4 where uid = '" + GlobalParameter.uid + "'";
//        RenewFunCommon.updateMysqlData(updateidentitystatussql);

 }

    @Test(description="获取待续约列表")
    public void getCanRenewContractSP() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000032'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_getCanRenewContractSP(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getCanRenewContractSP返回值:" + responseJson);
        } else {
            logR.debug("用例getCanRenewContractSP返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }

    @Test(description="查看合同是否能生成新的")
    public void checkContractCanRenew() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000039'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_checkContractCanRenew(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例checkContractCanRenew返回值:" + responseJson);
        } else {
            logR.debug("用例checkContractCanRenew返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }
    @Test(description="续约详情")
    public void getContractInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000033'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_getContractInfo(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getContractInfo返回值:" + responseJson);
        } else {
            logR.debug("用例getContractInfo返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }
    @Test(description="生成续约1")
    public void getRenewType() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000034'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_getRenewType(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getRenewType返回值:" + responseJson);
        } else {
            logR.debug("用例getRenewType返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }
    @Test(description="生成续约2")
    public void getRenewPaymentList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000035'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_getRenewPaymentList(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getRenewPaymentList返回值:" + responseJson);
        } else {
            logR.debug("用例getRenewPaymentList返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }
    @Test(description="支付方式选择季付")
    public void getRenewPayInfoQuarters() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000036'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_getRenewPayInfo(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getRenewPayInfo返回值:" + responseJson);
        } else {
            logR.debug("用例getRenewPayInfo返回值------>>>>>>>>>>>为空");
        }
        //计算countPrice
        RenewFunCommon.calculateCountPrice(KeeperGlobalParas.period);

        //计算服务费和总金额
        RenewFunCommon.calculateCommission();
        RenewFunCommon.calculateCountMoney();

        Assert.assertEquals(actual, "success");
        Assert.assertEquals(KeeperGlobalParas.countMoney, KeeperGlobalParas.testCountMoney);
        Assert.assertEquals(KeeperGlobalParas.countPrice, KeeperGlobalParas.testCountPrice);
        Assert.assertEquals(KeeperGlobalParas.commission, KeeperGlobalParas.testCommission);

    }
    @Test(description="支付方式选择月付")
    public void getRenewPayInfoMonth() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000036'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        //初始化支付方式为月付
        KeeperGlobalParas.paymentType = "1";

        JSONObject responseJson = re.s_getRenewPayInfo(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getRenewPayInfo返回值:" + responseJson);
        } else {
            logR.debug("用例getRenewPayInfo返回值------>>>>>>>>>>>为空");
        }
        //计算countPrice
        RenewFunCommon.calculateCountPrice(KeeperGlobalParas.period);

        //计算服务费和总金额
        RenewFunCommon.calculateCommission();
        RenewFunCommon.calculateCountMoney();

        Assert.assertEquals(actual, "success");
        Assert.assertEquals(KeeperGlobalParas.countMoney, KeeperGlobalParas.testCountMoney);
        Assert.assertEquals(KeeperGlobalParas.countPrice, KeeperGlobalParas.testCountPrice);
        Assert.assertEquals(KeeperGlobalParas.commission, KeeperGlobalParas.testCommission);


    }
    @Test(description="支付方式选择半年付")
    public void getRenewPayInfoHalfYear() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000036'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        //初始化支付方式为半年付
        KeeperGlobalParas.paymentType = "6";

        JSONObject responseJson = re.s_getRenewPayInfo(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getRenewPayInfo返回值:" + responseJson);
        } else {
            logR.debug("用例getRenewPayInfo返回值------>>>>>>>>>>>为空");
        }
        //计算countPrice
        RenewFunCommon.calculateCountPrice(KeeperGlobalParas.period);

        //计算服务费和总金额
        RenewFunCommon.calculateCommission();
        RenewFunCommon.calculateCountMoney();

        Assert.assertEquals(actual, "success");
        Assert.assertEquals(KeeperGlobalParas.countMoney, KeeperGlobalParas.testCountMoney);
        Assert.assertEquals(KeeperGlobalParas.countPrice, KeeperGlobalParas.testCountPrice);
        Assert.assertEquals(KeeperGlobalParas.commission, KeeperGlobalParas.testCommission);


    }
    @Test(description="支付方式选择年付")
    public void getRenewPayInfoYear() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000036'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        //初始化支付方式为年付
        KeeperGlobalParas.paymentType = "12";

        JSONObject responseJson = re.s_getRenewPayInfo(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getRenewPayInfo返回值:" + responseJson);
        } else {
            logR.debug("用例getRenewPayInfo返回值------>>>>>>>>>>>为空");
        }
        //计算countPrice
        RenewFunCommon.calculateCountPrice(KeeperGlobalParas.period);

        //计算服务费和总金额
        RenewFunCommon.calculateCommission();
        RenewFunCommon.calculateCountMoney();

        Assert.assertEquals(actual, "success");
        Assert.assertEquals(KeeperGlobalParas.countMoney, KeeperGlobalParas.testCountMoney);
        Assert.assertEquals(KeeperGlobalParas.countPrice, KeeperGlobalParas.testCountPrice);
        Assert.assertEquals(KeeperGlobalParas.commission, KeeperGlobalParas.testCommission);

    }
    @Test(description="支付方式选择白条")
    public void getRenewPayInfoWhite() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000036'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        //初始化支付方式为京东白条
        KeeperGlobalParas.isBlank = "1";
        KeeperGlobalParas.paymentType = "99";

        JSONObject responseJson = re.s_getRenewPayInfo(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getRenewPayInfo返回值:" + responseJson);
        } else {
            logR.debug("用例getRenewPayInfo返回值------>>>>>>>>>>>为空");
        }
        //计算countPrice
        RenewFunCommon.calculateCountPrice(KeeperGlobalParas.period);

        //计算服务费和总金额
        RenewFunCommon.calculateCommission();
        RenewFunCommon.calculateCountMoney();

        Assert.assertEquals(actual, "success");
        Assert.assertEquals(KeeperGlobalParas.countMoney, KeeperGlobalParas.testCountMoney);
        Assert.assertEquals(KeeperGlobalParas.countPrice, KeeperGlobalParas.testCountPrice);
        Assert.assertEquals(KeeperGlobalParas.commission, KeeperGlobalParas.testCommission);

    }
//    @Test(description="错误case报告展示")
//    public void getFailedCaseTest() {
//        String domainName = PropertyConstants.CRM_DOMIN;
//        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
//                "SuffixUrlSql") + "  caseID='TC000036'";
//        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
//        String requestUrl = domainName + SuffixUrl;
//
//        //初始化支付方式为京东白条
//        KeeperGlobalParas.isBlank = "1";
//        KeeperGlobalParas.paymentType = "99";
//
//        JSONObject responseJson = re.s_getRenewPayInfo(requestUrl);
//        String actual = "";
//
//        if (responseJson != null) {
//            actual = responseJson.getString("status");
//            logR.debug("用例getRenewPayInfo返回值:" + responseJson);
//        } else {
//            logR.debug("用例getRenewPayInfo返回值------>>>>>>>>>>>为空");
//        }
//        Assert.assertEquals(actual, "success");
//        Assert.assertEquals(KeeperGlobalParas.countMoney, "18207");
//        Assert.assertEquals(KeeperGlobalParas.deposit, "1460");
//        Assert.assertEquals(KeeperGlobalParas.monthPrice, "1460");
//        Assert.assertEquals(KeeperGlobalParas.countPrice, "17520");
//        //服务费计算校验规则待添加
//        // Assert.assertEquals(KeeperGlobalParas.commission, "828");
//        Assert.assertEquals(KeeperGlobalParas.period, "12111");
//    }

}
