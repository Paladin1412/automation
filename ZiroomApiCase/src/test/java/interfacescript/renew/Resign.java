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

public class Resign {
	
	private static final Logger logR = Logger.getLogger(Resign.class);

	Keeper_Renew re = new Keeper_Renew();
	Keeper_Me me = new Keeper_Me();

	@BeforeClass(description = "此类run之前运行")
	public void init_config() {
		KeeperGlobalParas.oldContractCode = "BJZYCW81404130107-03";
		KeeperGlobalParas.customerName = "祖力卡尔·铁木尔";
		KeeperGlobalParas.customerPhone = "18684847831";
		KeeperGlobalParas.uid = "5ae1f252-7ef3-dd56-8ecf-7232a5b14743";
		//老用户进行信用认证

		//String selectsql = "select * from tb_user_identity where phone = '" + GlobalParameter.customerPhone +"'";
		//RenewFunCommon.selectMysqlData(selectsql);
//        String updatestatussql = "update tb_user_identity set status = 4 where phone = '" + GlobalParameter.customerPhone + "'";
//        RenewFunCommon.updateMysqlData(updatestatussql);
//        String updateidentitystatussql = "update tb_user_status set identity_status = 4 where uid = '" + GlobalParameter.uid + "'";
//        RenewFunCommon.updateMysqlData(updateidentitystatussql);
}


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
		JSONObject json = JSONObject.fromObject(LoginInput);
		//请求参数
		//String passWord = json.getString("password");
		//String loginName = json.getString("login_name");
		String passWord = "123456";
		String loginName = "20227270";
		
		JSONObject responseJson = me.s_login(httpUrl, loginName, passWord);
		
		String actual ="";
		if (responseJson !=null) {
			actual = responseJson.getString("status");
			logR.debug("用例login返回值为" + responseJson);
		} else {
			logR.debug("用例login请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");		
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
			logR.debug("用例getCanRenewContract返回值:" + responseJson);
		} else {
			logR.debug("用例getCanRenewContract返回值------>>>>>>>>>>>为空");
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
	@Test(description="生成续约4")
	public void renewContract() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000037'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = re.s_renewContract(requestUrl);
		String actual = "";

		if (responseJson != null) {
			actual = responseJson.getString("status");
			logR.debug("用例renewContract返回值:" + responseJson);
		} else {
			logR.debug("用例renewContract返回值------>>>>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");

	}
	@Test(description="关闭续约")
	public void closeRenewContract() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000038'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = re.s_closeRenewContract(requestUrl);
		String actual = "";

		if (responseJson != null) {
			actual = responseJson.getString("status");
			logR.debug("用例closeRenewContract返回值:" + responseJson);
		} else {
			logR.debug("用例closeRenewContract返回值------>>>>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");

	}

}
