package keeper.init;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


public class Keeper_LoginS {
    private static final Logger logL = Logger.getLogger(Keeper_LoginS.class);

    Keeper_Me me = new Keeper_Me();

    @Test(description = "登陆")
    public void login() {
        // 获取输入sql语句
        String LoginInputSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "LoginInputSql") + "  caseID='TC000001'";
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000001'";
        // 获取登陆输入内容
        String LoginInput = ProviderDataSource.getDataString(LoginInputSql);
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.INTERFACES_DOMIN;
        String httpUrl = domainName + SuffixUrl;

        String passWord = "123456";
        String loginName = KeeperGlobalParas.rentHouseUserAccount;

        JSONObject responseJson = me.s_login(httpUrl, loginName, passWord);

        String actual = "";
        if (responseJson != null) {
            actual = responseJson.getString("status");
            logL.debug("用例login返回值为" + responseJson);
        } else {
            logL.debug("用例login请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
//		String responseData = responseJson.getString("data");
//		String stewardType = JSONObject.fromObject(responseData).getString("stewardType");
//		Assert.assertEquals(stewardType, "直收管家");
    }
}
