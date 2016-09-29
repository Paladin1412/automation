package apprent.joinrent;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ziroom.utils.CommonFunction;
import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;

import config.PropertyConstants;
import net.sf.json.JSONObject;
import ziroom.appScript.CommonApiParas;
import ziroom.appScript.S_FindHouse;
import ziroom.appScript.S_Me;
import ziroom.appScript.S_NewSign;
import ziroom.confManagement.commonMethods.*;

public class NewSign {

	private static final Logger logJR = Logger.getLogger(NewSign.class);
	CommonApiParas cap=new CommonApiParas();
	S_FindHouse sfh = new S_FindHouse();
	S_NewSign sns=new S_NewSign();
	S_Me sme = new S_Me();
	 
	HttpRequest hRequest=new HttpRequest();
	
	@BeforeClass(description = "此类run之前运行")
	public void init_config() {
		
		// 1是管家app,2是自如客
		RentAppGlobalParas.appType = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "appType");
		// 请求类型：1是app请求；2是服务器请求
		RentAppGlobalParas.source = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "source");
		// 1是白条，0是非白条
		RentAppGlobalParas.isBlank = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "isBlank");
		// 合租house_type
		RentAppGlobalParas.detailHouseType = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
				"jr_house_type");
		// 押一付一
		RentAppGlobalParas.payment = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "payment");
		// 是否续约1是 0非
		RentAppGlobalParas.isRenew = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath, "isRenew");
		RentAppGlobalParas.house_tag5 = PropertiesUtil.getPropValAsString(PropertyConstants.paramsConfigPath,
				"jr_house_tag5");// 服务费
		RentAppGlobalParas.couponCostValue="0";
		RentAppGlobalParas.couponValue="0";
		RentAppGlobalParas.money="0";
	}

	@Test(description = "点击合租1<get_bizcirclelist_new>")
	public void jointRent1() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000016'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		
		JSONObject responseJson = sns.s_wholeRent1(requestUrl);

		
		String actual = "";

		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例joinRent1返回值:" + responseJson);
		} else {
			logJR.debug("用例joinRent1返回值------>>>>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

	@Test(description = "点击合租2<searchHouse>")
	public void jointRent2() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000021'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = sns.s_wholeRent2(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.info("用例jointRent2返回值:" + responseJson);
		} else {
			logJR.debug("用例jointRent2返回值------->>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
	}

	@Test(description = "进入单个房源请求<detailShow>")
	public void houseDetail() {
		// 获取api后缀sql
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000002'";
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String httpUrl = domainName + SuffixUrl;

		// 返回内容格式化为json
		JSONObject responseJson = sfh.s_houseDetail(httpUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例houseDetail返回值为" + responseJson);
		} else {
			logJR.debug("用例houseDetail请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");

	}

	@Test(description = "单签请求<请求用户信息>")
	public void oneSign() {
		String InputSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "LoginInputSql")
				+ "  caseID='TC000003'";
		String Input = ProviderDataSource.getDataString(InputSql);
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String _p = JSONObject.fromObject(Input).getString("_p");
		String user_info_select = JSONObject.fromObject(Input).getString("_a");
		String uid = RentAppGlobalParas.login_uid;
		String sign = CommonFunction.getAppSign(uid, CommonFunction.getTimeStampOf10());
		String timestamp = CommonFunction.getTimeStampOf10();
		String SuffixUrl = "/index.php?_p=" + _p + "&_a=" + user_info_select + "&uid=" + uid + "&sign=" + sign
				+ "&timestamp=" + timestamp + "";
		String httpUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_oneSign(httpUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例oneSign返回值为" + responseJson);
		} else {
			logJR.debug("用例oneSign请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");

	}

	@Test(description = "确定个人信息1")
	public void okinfo1() {
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000004'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String httpUrl = domainName + SuffixUrl;
		// 返回内容格式化为json
		JSONObject responseJson = sns.s_okinfo1(httpUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例okinfo1返回值为" + responseJson);
		} else {
			logJR.debug("用例okinfo1请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

	@Test(description = "确定个人信息2")
	public void okinfo2() {
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000005'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String httpUrl = domainName + SuffixUrl;
		// 返回内容格式化为json
		JSONObject responseJson = sns.s_okinfo2(httpUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例okinfo2返回值为" + responseJson);
		} else {
			logJR.debug("用例okinfo2请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

	@Test(description = "确定个人信息3<资质信息>")
	public void okinfo3() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		JSONObject responseJson =sns.s_okinfo3(domainName);
		logJR.info("okinfo3返回值：" + responseJson);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例okinfo3返回值为" + responseJson);
		} else {
			logJR.debug("用例okinfo3请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

	@Test(description = "我承诺1")
	public void promiseMyself1() {
		// 获取资质信息
		// JSONObject certificateValue = me.s_get_certificate();
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000007'";
		String SuffixUrl1 = ProviderDataSource.getDataString(SuffixUrlSql);
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String httpUrl1 = domainName + SuffixUrl1;
		// 请求接口返回值
		JSONObject responseJson = sns.s_promiseMyself1(httpUrl1);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例promiseMyself1返回值为" + responseJson);
		} else {
			logJR.debug("用例promiseMyself1请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");

		// http://s.ziroom.com/crm/common/createAppId?appType=2&imei=352248061569009
	}

	@Test(description = "我承诺2")
	public void promiseMyself2() {
		String domainName = PropertyConstants.CRM_DOMIN;
		RentAppGlobalParas.appid = cap.getAppid();
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000008'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String httpUrl = domainName + SuffixUrl;
		// 请求接口返回值
		JSONObject responseJson = sns.s_promiseMyself2(httpUrl);
		// 返回内容格式化为json
		String actual = "";
		if (responseJson != null) {

			actual = responseJson.getString("error_code");
			logJR.debug("用例promiseMyself2返回值:" + responseJson);
		} else {
			logJR.debug("用例promiseMyself2返回值---------->>>>>>>>为空");
		}
		Assert.assertEquals(actual, "0");
	}

	@Test(description = "确认付款方式1")
	public void okPayType1() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000009'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		// 请求接口返回值
		JSONObject responseJson = sns.s_okPayType1(requestUrl);

		String actual = "";
		if (responseJson != null) {

			actual = responseJson.getString("error_code");
			logJR.info("用例okPayType1返回值:" + responseJson);
		} else {
			logJR.info("用例okPayType1返回值---------->>>>>>>>为空");
		}
		Assert.assertEquals(actual, "0");

	}

	@Test(description = "确认付款方式2")
	public void okPayType2() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000010'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		// 请求接口返回值
		JSONObject responseJson = sns.s_okPayType2(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例okPayType2返回值为" + responseJson);
		} else {
			logJR.debug("用例okPayType2请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

	@Test(description = "我同意上述合同条款1")
	public void agreeContractTerms1() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000011'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		// 请求接口返回值
		JSONObject responseJson = sns.s_agreeContractTerms1(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例agreeContractTerms1返回值为" + responseJson);
		} else {
			logJR.debug("用例agreeContractTerms1请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");

	}

	@Test(description = "我同意上述合同条款2")
	public void agreeContractTerms2() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000012'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = sns.s_agreeContractTerms2(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例agreeContractTerms2返回值为" + responseJson);
		} else {
			logJR.debug("用例agreeContractTerms2请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

	@Test(description = "确认合同")
	public void okContract() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000013'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		// 请求接口返回值
		JSONObject responseJson = sns.s_okContract(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例okContract返回值为" + responseJson);
		} else {
			logJR.debug("用例okContract请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");

	}

	@Test(description = "我确认以上信息1")
	public void okAboveInfo1() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000014'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = sns.s_okAboveInfo1(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例okAboveInfo1返回值为" + responseJson);
		} else {
			logJR.debug("用例okAboveInfo1请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");

	}

	@Test(description = "我确认以上信息2")
	public void okAboveInfo2() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000015'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = sns.s_okAboveInfo2(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例okAboveInfo2返回值为" + responseJson);
		} else {
			logJR.debug("用例okAboveInfo2请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

	@Test(description = "验证码确定")
	public void verificationOk() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000017'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_verificationOk(requestUrl);

		 
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("error_code");
			logJR.debug("用例verificationOk返回值为" + responseJson);
		} else {
			logJR.debug("用例verificationOk请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "0");
	}

	@Test(description = "验证码后获取支付信息")
	public void getPayInfo() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000058'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_getPayInfo(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例getPayInfo返回值为" + responseJson);
		} else {
			logJR.debug("用例getPayInfo请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}
	
	@Test(description = "自如支付")
	public void ziroom_fistPayMent() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000059'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_fistPayMent(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logJR.debug("用例getPayInfo返回值为" + responseJson);
		} else {
			logJR.debug("用例getPayInfo请求返回值------>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

}
