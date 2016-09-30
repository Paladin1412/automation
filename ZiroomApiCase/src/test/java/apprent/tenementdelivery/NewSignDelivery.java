package apprent.tenementdelivery;

import keeper.appScript.Keeper_NewSignDelivery;
 

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;

import config.PropertyConstants;
import net.sf.json.JSONObject;
import com.ziroom.appScript.S_TenementDelivery;
import com.ziroom.confManagement.commonMethods.RentAppGlobalParas;

/****
 * 扫码二维码新签物业交割用例
 * @author jihaibo
 *
 */
public class NewSignDelivery {
	private static final Logger logNSTD = Logger.getLogger(NewSignDelivery.class);
	Keeper_NewSignDelivery nss=new Keeper_NewSignDelivery();
	S_TenementDelivery td=new S_TenementDelivery();
	@BeforeClass
	public void init()
	{
		RentAppGlobalParas.haveJointRent="2";
		RentAppGlobalParas.sysContractId="0";
	}
	
	@Test(description = "和管家物业交割")
	public void ns_TenementDeliveryAndSteward() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql =PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000040'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = td.s_tenementDeliveryAndSteward(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例ns_TenementDeliveryAndSteward返回值:" + responseJson);
		} else {
			logNSTD.debug("用例ns_TenementDeliveryAndSteward返回值----------->>>>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
	}
	
	
	@Test(description="扫描新签物业交割二维码")
	public void keeperScanDelivery() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000041'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson =nss.s_keeperScan(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例s_keeperScanDelivery返回值:" + responseJson);
		} else {
			logNSTD.debug("用例s_keeperScanDelivery返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}

	@Test(description="完成新签确认")
	public void CompleteSignAffirm() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000042'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson =nss.s_CompleteSignAffirm(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例CompleteSignAffirm返回值:" + responseJson);
		} else {
			logNSTD.debug("用例CompleteSignAffirm返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="获取配置物品")
	public void getConfigGoods() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000043'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson =nss.s_getConfigGoods(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例getConfigGoods返回值:" + responseJson);
		} else {
			logNSTD.debug("用例getConfigGoods返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="添加物品配置1")
	public void addConfigGoods1() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000044'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson =nss.s_addConfigGoods1(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例addConfigGoods1返回值:" + responseJson);
		} else {
			logNSTD.debug("用例addConfigGoods1返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="添加物品配置2")
	public void addConfigGoods2() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000045'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson =nss.s_addConfigGoods2(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例addConfigGoods2返回值:" + responseJson);
		} else {
			logNSTD.debug("用例addConfigGoods2返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="添加物品配置中获取物品")
	public void getUniversalGoods() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000046'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		
		JSONObject responseJson =nss.s_getUniversalGoods(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例getUniversalGoods返回值:" + responseJson);
		} else {
			logNSTD.debug("用例getUniversalGoods返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="物品配置下一步")
	public void configGoodsNext() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000047'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson =nss.s_configGoodsNext(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例configGoodsNext返回值:" + responseJson);
		} else {
			logNSTD.debug("用例configGoodsNext返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="获取钥匙凭证")
	public void getPropertyKeyCards() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000060'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =nss.s_getPropertyKeyCards(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例getPropertyKeyCards返回值:" + responseJson);
		} else {
			logNSTD.debug("用例getPropertyKeyCards返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="提交钥匙凭证")
	public void updatePropertyKeyCards() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000061'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =nss.s_updatePropertyKeyCards(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例updatePropertyKeyCards返回值:" + responseJson);
		} else {
			logNSTD.debug("用例updatePropertyKeyCards返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
		
	}
	
	@Test(description="获取生活费用")
	public void getExpenses() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000062'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =nss.s_getExpenses(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例getExpenses返回值:" + responseJson);
		} else {
			logNSTD.debug("用例getExpenses返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");	
	}
	
	@Test(description="提交生活费用")
	public void updateExpenses() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000063'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =nss.s_updateExpenses(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例updateExpenses返回值:" + responseJson);
		} else {
			logNSTD.debug("用例updateExpenses返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");	
	}
	////////////////////////////////////////////
	/////管家物业交割完成，自如客确认物业交割并对管家做出评价
	/////////////////////////////////////////////
	@Test(description="查看物业交割")
	public void checkDelivery() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000066'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =td.s_checkDelivery(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例checkDelivery返回值:" + responseJson);
		} else {
			logNSTD.debug("用例checkDelivery返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");	
	}
	
	@Test(description="去交割")
	public void goDelivery() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000067'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =td.s_goDelivery(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例goDelivery返回值:" + responseJson);
		} else {
			logNSTD.debug("用例goDelivery返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");	
	}
	
	@Test(description="确认物业交割")
	public void confirmDelivery() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000068'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =td.s_confirmDelivery(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例confirmDelivery返回值:" + responseJson);
		} else {
			logNSTD.debug("用例confirmDelivery返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");	
	}
	
	@Test(description="获取管家")
	public void getRentAgentCode() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000069'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =td.s_getRentAgentCode(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例getRentAgentCode返回值:" + responseJson);
		} else {
			logNSTD.debug("用例getRentAgentCode返回值---------->>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");	
	}
	
	
	@Test(description="获取页面评价信息")
	public void getEvaluateProblems() {
		String domainName = PropertyConstants.ES_DOMIN;
				
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000070'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =td.s_getEvaluateProblems(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例getEvaluateProblems返回值:" + responseJson);
		} else {
			logNSTD.debug("用例getEvaluateProblems返回值---------->>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");	
	}
	
	@Test(description="提交评价")
	public void putEvaluateContent() {
		String domainName = PropertyConstants.ES_DOMIN;
				
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000071'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson =td.s_putEvaluateContent(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logNSTD.info("用例getEvaluateProblems返回值:" + responseJson);
		} else {
			logNSTD.debug("用例getEvaluateProblems返回值---------->>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");	
	}
}
