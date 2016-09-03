package interfacescript.renew;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import config.PropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ziroom.appScript.S_Me;
import ziroom.appScript.S_NewSign;
import ziroom.appScript.S_Renew;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;


/**
 * Created by wujing on 16/8/16.
 */
public class Renew {
    private static final Logger logR = Logger.getLogger(Renew.class);

    S_Me me = new S_Me();
    S_Renew re = new S_Renew();
    S_NewSign ne = new S_NewSign();

    @BeforeClass(description = "此类run之前运行")
    public void init_config() {
        //初始化用户的用户名和密码,准备数据
        RentAppGlobalParas.passWord = "qwerty";
        RentAppGlobalParas.loginName = "18684847831";
        //RentAppGlobalParas.loginName = "15600083297";
        //续约流程初始化是否为续约字段为1
        RentAppGlobalParas.isRenew="1";
    }

    @Test(description = "登陆")
    public void testLogin() {
        // 获取输入sql语句
        String LoginInputSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "LoginInputSql") + "  caseID='TC000001'";
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000001'";
        // 获取登陆输入内容
        String LoginInput = ProviderDataSource.getDataString(LoginInputSql);
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.INTERFACES_DOMIN;
        String httpUrl = domainName + SuffixUrl;
        // 获取输入字符串 格式化为json
        JSONObject json = JSONObject.fromObject(LoginInput);
        // 请求参数
        //String passWord = json.getString("password");
        //String loginName = json.getString("login_name");
//        String passWord = "qwerty";
//        String loginName = "15600083297";

        JSONObject responseJson = me.s_login(httpUrl, RentAppGlobalParas.loginName, RentAppGlobalParas.passWord);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例login返回值为" + responseJson);
        } else {
            logR.debug("用例login请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "获取合同列表")
    public void testGetContractList() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000050'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = re.s_getContractList(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testGetContractList返回值为" + responseJson);
        } else {
            logR.debug("用例testGetContractList请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取合同详情")
    public void testGetContractInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000051'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = re.s_getContractInfo(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testGetContractInfo返回值为" + responseJson);
        } else {
            logR.debug("用例testGetContractInfo请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "检查合同是否有续约合同")
    public void testCheckHasRenewContract() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000052'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = re.s_checkHasRenewContract(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testCheckHasRenewContract返回值为" + responseJson);
        } else {
            logR.debug("用例testCheckHasRenewContract请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取续约合同详情")
    public void testGetRenewContractInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000053'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = re.s_getRenewContractInfo(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testGetRenewContractInfo返回值为" + responseJson);
        } else {
            logR.debug("用例testGetRenewContractInfo请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "确认合同1")
    public void testGet_ra_config() {
        String domainName = PropertyConstants.INTERFACES_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000005'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = ne.s_okinfo2(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testGetet_ra_config返回值为" + responseJson);
        } else {
            logR.debug("用例testGetet_ra_config请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "确认合同2")
    public void testClauseSelect() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000010'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = re.s_clauseSelect(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testClauseSelect返回值为" + responseJson);
        } else {
            logR.debug("用例testClauseSelect请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "获取首次支付信息")
    public void testGetFirstPayInfo() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000054'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        // 请求接口返回值
        JSONObject responseJson = re.s_getFirstPayInfo(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testGetFirstPayInfo返回值为" + responseJson);
        } else {
            logR.debug("用例testGetFirstPayInfo请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "确认生成续约Activity")
    public void testGetRenewActivity() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000055'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        // 请求接口返回值
        JSONObject responseJson = re.s_getRenewActivity(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testGetRenewActivity返回值为" + responseJson);
        } else {
            logR.debug("用例testGetRenewActivity请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "短信验证码校验1")
    public void testGet_ra_sign_info() {
        String domainName = PropertyConstants.INTERFACES_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000014'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = re.s_get_ra_sign_info(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testGet_ra_sign_info返回值为" + responseJson);
        } else {
            logR.debug("用例testGet_ra_sign_info请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "短信验证码检验2")
    public void testSet_ra_signature() {
        String domainName = PropertyConstants.INTERFACES_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000015'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;

        // 请求参数
        JSONObject responseJson = re.s_set_ra_signature(requestUrl);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logR.debug("用例testSet_ra_signature返回值为" + responseJson);
        } else {
            logR.debug("用例testSet_ra_signature请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }

    @Test(description = "确认续签合同")
    public void testConfirmRenewContract() {
        String domainName = PropertyConstants.CRM_DOMIN;
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
                "SuffixUrlSql") + "  caseID='TC000056'";
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String requestUrl = domainName + SuffixUrl;
        // 请求接口返回值
        JSONObject responseJson = re.s_confirmRenewContract(requestUrl);
        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("error_code");
            logR.debug("用例testConfirmRenewContract返回值为" + responseJson);
        } else {
            logR.debug("用例testConfirmRenewContract请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "0");
    }

}
