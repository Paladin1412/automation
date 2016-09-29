package keeper.appScript;

import com.ziroom.utils.CommonFunction;
import keeper.confManagement.commonMethods.HttpRequest;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.util.HashMap;

public class Keeper_Me {
	
		private static final Logger logMe = Logger.getLogger(Keeper_Me.class);
		public static String login_uid;
		HttpRequest hRequest = new HttpRequest();

		/***
		 * 管家登陆api
		 * @param requestUrl#请求url
		 * @param loginName#登陆用户名
		 * @param passWord#登陆密码
		 * @return
		 * 
		 */
		public JSONObject s_login(String requestUrl, String loginName, String passWord) {
			String timestamp = CommonFunction.getTimeStampOf10();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("sign", CommonFunction.getAppSign(loginName, timestamp));
			map.put("uid", loginName);
			map.put("timestamp", timestamp);			
			map.put("appType", KeeperGlobalParas.appType);
			map.put("password", passWord);
			map.put("user_account", loginName);
			map.put("login_name", loginName);

			JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
			String responseData = responseJson.getString("data");

			//报告生成日志
			Reporter.log(requestUrl);
			Reporter.log(map.toString());
			Reporter.log(responseJson.toString());
			logMe.info(map.toString());
			logMe.info(requestUrl);

			// 登陆后的返回的uid，赋值为后面使用
			if (responseData.equals("[]")) {
				logMe.info("变量uid值为空");
			} else {
				KeeperGlobalParas.login_uid = JSONObject.fromObject(responseData).getString("uid");
			}
			logMe.debug("变量login_uid:" + KeeperGlobalParas.login_uid);
			logMe.info("s_login返回值：" + responseJson);

			return responseJson;
		}
}
