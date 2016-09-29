
package apprent.wholerent;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;

import config.PropertyConstants;
import net.sf.json.JSONObject;
import ziroom.appScript.CommonApiParas;
import ziroom.appScript.S_FindHouse;
import ziroom.appScript.S_Me;
import ziroom.appScript.S_NewSign;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;
 


public class NewSign {

	private static final Logger logWR = Logger.getLogger(NewSign.class);
	CommonApiParas cap =new CommonApiParas();
	S_FindHouse sfh = new S_FindHouse();
	S_NewSign sns =new S_NewSign();
	S_Me me = new S_Me();
	
	@BeforeClass
	public void init_config() {

		 
	}
	
	@Test(description = "搜索房源大列表")
	public void ZSearchHouse() {
		String domainName = PropertyConstants.INTERFACES_ZZ_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000072'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_ZSearchHouse(requestUrl);
		String actual = "";

		if (responseJson != null) {
			actual = responseJson.getString("status");
			logWR.debug("用例wholeRent1返回值:" + responseJson);
		} else {
			logWR.debug("用例wholeRent1返回值------>>>>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}
	
	@Test(description = "进入单个房源")
	public void ZSearchHouseDetail() {
		String domainName = PropertyConstants.INTERFACES_ZZ_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000073'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_ZSearchHouseDetail(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logWR.debug("用例wholeRent1返回值:" + responseJson);
		} else {
			logWR.debug("用例wholeRent1返回值------>>>>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}
	@Test(description = "提升房源")
	public void ZSearchHousePromotion() {
		String domainName = PropertyConstants.INTERFACES_ZZ_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath,
				"SuffixUrlSql") + "  caseID='TC000074'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_ZSearchHousePromotion(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logWR.debug("用例wholeRent1返回值:" + responseJson);
		} else {
			logWR.debug("用例wholeRent1返回值------>>>>>>>>>>>为空");
		}
		Assert.assertEquals(actual, "success");
	}

}
 
