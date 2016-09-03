package interfacescript.renew;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.appScript.Keeper_Renew;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by wujing on 16/8/19.
 */
public class RenewAgain {

    private static final Logger logR = Logger.getLogger(Resign.class);

    Keeper_Renew re = new Keeper_Renew();
    Keeper_Me me = new Keeper_Me();

    @BeforeClass(description = "此类run之前运行")
    public void init_config() {
        KeeperGlobalParas.oldContractCode = "BJZYZCW81409070132-01";
        KeeperGlobalParas.renewContract =   "BJZYZCW81409070132-02";
        KeeperGlobalParas.paymentType = "3";
        KeeperGlobalParas.renewType ="cx";
        KeeperGlobalParas.customerName = "胡乃林";
        KeeperGlobalParas.customerPhone = "15600083297";
        //发起类型为2:重新发起
        KeeperGlobalParas.creatType = "2";

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

    @Test(description="获取合同信息")
    public void getRenewContractInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000057'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_getRenewContractInfo(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例getContractInfo返回值:" + responseJson);
        } else {
            logR.debug("用例getContractInfo返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }

    @Test(description="生成续约3")
    public void getRenewPayInfo() {
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
        Assert.assertEquals(actual, "success");

    }


    @Test(description="重新发起续约")
    public void renewContractAgain() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000037'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = re.s_renewContractAgain(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例renewContractAgain返回值:" + responseJson);
        } else {
            logR.debug("用例renewContractAgain返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }

}
