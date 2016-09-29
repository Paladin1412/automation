package ziroom.appScript;

import ziroom.confManagement.commonMethods.RentAppGlobalParas;
import ziroom.confManagement.config.PropertyConstants;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import ziroom.confManagement.commonMethods.HttpRequest;


public class CommonApiParas {
	private static final Logger logCAP = Logger.getLogger(S_FindHouse.class);
	HttpRequest hRequest = new HttpRequest();

	/***
	 * 获取appid
	 * 
	 * @return
	 */
	public String getAppid() {
		String domainName = PropertyConstants.R_CRM_DOMIN;
		String httpUrlAppid = domainName + "/crm/common/createAppId?appType=2&imei=352248061569009";
		JSONObject responseJson = hRequest.getGetReturn(httpUrlAppid);
		String app_id = "";
		if (responseJson != null) {

			app_id = JSONObject.fromObject(responseJson.getString("data")).getString("appId");

			if (app_id.equals("")) {
				logCAP.info("变量getAppid值为空");
			} else {
				RentAppGlobalParas.appid = app_id;
				logCAP.debug("变量appid:" + RentAppGlobalParas.appid);
			}
		} else {
			logCAP.info("getAppid服务器返回值----------->>>>>>>>>>为空");
		}

		return app_id;
	}
}
