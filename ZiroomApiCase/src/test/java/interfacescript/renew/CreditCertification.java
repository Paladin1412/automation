package interfacescript.renew;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_CreditCertification;
import keeper.confManagement.commonMethods.HttpRequest;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.commonMethods.RenewFunCommon;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Created by wujing on 16/8/26.
 */
public class CreditCertification {

    private static final Logger logC = Logger.getLogger(CreditCertification.class);
    Keeper_CreditCertification cr = new Keeper_CreditCertification();
    HttpRequest hRequest = new HttpRequest();

    @BeforeClass(description = "此类run之前运行")
    public void init_config() {
        KeeperGlobalParas.customerPhone = "13126986422";

        //获取用户的uid
        RenewFunCommon.selectUserInfo();
    }

    @Test(description = "用户授权自如信用接口")
    public void testUserAuthorization() {
        String domainName = PropertyConstants.CREDIT_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000064'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = cr.s_userAuthorization(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logC.debug("用例testUserAuthorization返回值:" + responseJson);
        } else {
            logC.debug("用例testUserAuthorization返回值------>>>>>>>>>>>为空");
        }

        //校验返回值
        String errorCode = responseJson.getString("error_code");
        String errorMessage = responseJson.getString("error_message");
        if (errorCode.equals(10020)){
            Assert.assertEquals(errorMessage, "此用户已经成功授权了，不能重复授权");
        }else if (errorCode.equals(10020)){
            Assert.assertEquals(actual, "success");
        }
    }
    @Test(description = "调用算分接口")
    public void testCallCountInterface(){
        String domainName = PropertyConstants.CREDIT_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000065'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        JSONObject responseJson = cr.s_callCountInterface(requestUrl);
        String actual = "";

        if (responseJson != null) {
            actual = responseJson.getString("status");
            logC.debug("用例testUserAuthorization返回值:" + responseJson);
        } else {
            logC.debug("用例testUserAuthorization返回值------>>>>>>>>>>>为空");
        }
        Assert.assertEquals(actual, "success");

    }
}

