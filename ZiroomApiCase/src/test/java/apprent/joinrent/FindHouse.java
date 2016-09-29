package apprent.joinrent;

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
 

public class FindHouse {
	private final static Logger logFH = Logger.getLogger(FindHouse.class);
	HttpRequest hRequest = new HttpRequest();
	S_FindHouse sfh = new S_FindHouse();
	S_NewSign sns = new S_NewSign();

	@BeforeClass
	public void init_config() {
		RentAppGlobalParas.is_reserve = "0";
		RentAppGlobalParas.detailHouseType="0";

	}

	@Test(description = "位置找房请求地铁线")
	public void fh_getSubway() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql")
				+ "  caseID='TC000029'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;

		JSONObject responseJson = sfh.s_getSubway(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logFH.info("用例fh_getSubway返回值:" + responseJson);
		} else {
			logFH.debug("用例fh_getSubway返回值----------->>>>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
	}

	@Test(description = "位置找房请求商业圈（区）")
	public void fh_getBizCircle() {
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql")
				+ "  caseID='TC000016'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sfh.s_getBizCircle(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logFH.info("用例fh_getBizCircle返回值:" + responseJson);
		} else {
			logFH.debug("用例fh_getBizCircle返回值----------->>>>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");

	}

	@Test(description = "按照查询条件找房（位置租金排序更多）")
	public void fh_searchFindByBizcircle() {
		RentAppGlobalParas.subway_station_name = "";
		// 611100330(北工大)18335739(朝阳公园)611100412(北苑)
		RentAppGlobalParas.bizcircle_code = "611100330";
		String domainName = PropertyConstants.INTERFACES_DOMIN;
		String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql")
				+ "  caseID='TC000021'";
		String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
		String requestUrl = domainName + SuffixUrl;
		JSONObject responseJson = sns.s_wholeRent2(requestUrl);

		String actual = "";
		if (responseJson != null) {
			actual = responseJson.getString("status");
			logFH.info("用例fh_searchFindByBizcircle返回值:" + responseJson);
		} else {
			logFH.debug("用例fh_searchFindByBizcircle返回值----------->>>>>>>>>>>为空");
		}

		Assert.assertEquals(actual, "success");
	}
}
