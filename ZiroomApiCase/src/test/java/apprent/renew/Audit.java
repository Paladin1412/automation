package apprent.renew;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Audit;
import keeper.confManagement.TimedTask.Command;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.Map;

/**
 * Created by wujing on 16/8/31.
 */
public class Audit {

    private static final Logger logA = Logger.getLogger(Audit.class);

    Keeper_Audit au = new Keeper_Audit();

    @BeforeClass
    public void init_config(){
        KeeperGlobalParas.oldContract = "BJZYCW81609070014";
    }


    @Test(description = "跑定时Job1")
    public void testRunTimedJob(){
        Command.execTaskResult(Command.appToCrm);
    }

//    testModifyInfo
    @Test(description = "登录")
    public void testLogin(){
        String domainName = PropertyConstants.CRM_LOGIN_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000108'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl +"user_account="+ KeeperGlobalParas.serviceUserName + "&city=bj";

        Reporter.log(httpUrl);

        Map responseJson = au.s_LoginKeeper(httpUrl);

        String htmlStr1 = responseJson.get("returnStatusCode").toString();
//        System.out.println(htmlStr1);

        if (responseJson !=null) {
            logA.debug("用例testlogin返回值为" + responseJson);
        } else {
            logA.debug("用例testlogin请求返回值------>>>>为空");
        }
        Assert.assertEquals(htmlStr1,"200");
    }
    @Test(description = "获取新签管理列表")
    public void testNewSignList(){

        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000110'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log(httpUrl);
        Map responseJson = au.s_newSignList(httpUrl);

        String htmlStr1 = responseJson.get("returnStatusCode").toString();

        if (responseJson !=null) {
            logA.debug("用例testError返回值为" + responseJson);
        } else {
            logA.debug("用例testError请求返回值------>>>>为空");
        }
        Assert.assertEquals(htmlStr1,"200");
    }
    @Test(description = "获取新签_合同详情")
    public void testNewSignDetail(){

        Map responseJson = au.s_newSignDetail();

        String htmlStr1 = responseJson.get("returnStatusCode").toString();

        if (responseJson !=null) {
            logA.debug("用例testError返回值为" + responseJson);
        } else {
            logA.debug("用例testError请求返回值------>>>>为空");
        }
        Assert.assertEquals(htmlStr1,"200");
    }

    @Test(description = "修改管家信息")
    public void testGetGuanjiaInfo(){

        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000111'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String httpUrl = domainName + SuffixUrl;

        Reporter.log(httpUrl);
        JSONObject responseJson = au.s_getGuanjiaInfo(httpUrl);

        String htmlStr = responseJson.get("user_account").toString();

        if (responseJson !=null) {
            logA.debug("用例testGetGuanjiaInfo返回值为" + responseJson);
        } else {
            logA.debug("用例testGetGuanjiaInfo请求返回值------>>>>为空");
        }
        Assert.assertEquals(htmlStr,KeeperGlobalParas.serviceUserName);
    }

    @Test(description = "跑定时Job2")
    public void testRunTimedJob2(){

        Command.execTaskResult(Command.appRenewToSave);
        Command.execTaskResult(Command.saveContract);

    }
}
