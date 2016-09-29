package keeper.init;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


public class Keeper_Login {
	private static final Logger logL = Logger.getLogger(Keeper_Login.class);
	
	Keeper_Me me = new Keeper_Me();

	@Test(description ="登陆")
	public void login(){
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
		//获取输入字符串 格式化为json
		//JSONObject json = JSONObject.fromObject(LoginInput);
		//请求参数
		//String passWord = json.getString("password");
		//String loginName = json.getString("login_name");
		String passWord = "123456";
		String loginName = "20227270";
		
		JSONObject responseJson = me.s_login(httpUrl, loginName, passWord);
		
		String actual ="";
		if (responseJson !=null) {
			actual = responseJson.getString("status");
			logL.debug("用例login返回值为" + responseJson);
		} else {
			logL.debug("用例login请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
		String responseData = responseJson.getString("data");
		String stewardType = JSONObject.fromObject(responseData).getString("stewardType");
		Assert.assertEquals(stewardType, "服务管家");
	}
}
