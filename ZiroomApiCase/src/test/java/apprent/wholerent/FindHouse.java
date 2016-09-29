package apprent.wholerent;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;

import config.PropertyConstants;
import net.sf.json.JSONObject;
import ziroom.appScript.S_FindHouse;
import ziroom.appScript.S_NewSign;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;
import ziroom.confManagement.commonMethods.HttpRequest;
 

/***
 * 整租搜房
 * 
 * @author homelink
 *
 */
public class FindHouse {
	private final static Logger logFH = Logger.getLogger(FindHouse.class);
	S_FindHouse sfh = new S_FindHouse();
	S_NewSign sns = new S_NewSign();
	

	HttpRequest hRequest = new HttpRequest();

	@BeforeClass
	public void init_config() {
		RentAppGlobalParas.sort = "";
		RentAppGlobalParas.bizcircle_code = "611100412";

	}

	@Test(description = "多条件搜房")
	public void fh_MoreFindHouse() {
		String domainName = PropertyConstants.INTERFACES_ZZ_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql")
				+ "  caseID='TC000048'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sfh.s_wr_FindHouseOfGeneral(requestUrl);
		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logFH.info("用例fh_MoreFindHouse返回值:" + responseJson);
		} else {
			logFH.debug("用例fh_MoreFindHouse返回值----------->>>>>>>>>>>为空");
			}

		Assert.assertEquals(actual, "success");
	}

}
