package keeper.confManagement.commonMethods.EHR;

import com.ziroom.httpclient.HttpClientUtils;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujing on 16/9/26.
 */
public class EHRUserInfoCommon {
    HttpClientUtils hcu = new HttpClientUtils();
    Logger logger = Logger.getLogger(EHRUserInfoCommon.class);

    public static JSONObject ehrUserInfoFlow(String account) {
        EHRUserInfoCommon common = new EHRUserInfoCommon();
        JSONObject result = new JSONObject();

        JSONObject getUserDetail = common.getUserDetail(account);
        if (getUserDetail.get("status").equals("failure")) {
            result.putAll(getUserDetail);
            return result;
        }

        JSONObject getUserInfo = common.getUserInfo(getUserDetail);

        result.put("status", "success");
        result.put("error_message", "获取管家信息成功");
        result.put("data", getUserInfo.toString());
        return result;
    }

    /**
     * @description: 从EHR得到管家信息
     * @author Elaine
     **/
    public JSONObject getUserDetail(String account){
        String domainName = PropertyConstants.EHR_DOMAIN;
        String suffixUrl = PropertyConstants.getUserDetail;
        String httpUrl = domainName + suffixUrl;
        logger.info(httpUrl);

        // 输入参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("userCode", "[\""+account+"\"]");
        JSONObject jsobj = JSONObject.fromObject(map);
        System.out.println("Map:" + map.toString());

        // 请求接口返回值
        Map<String, String> response = hcu.httpGetRequest(httpUrl, jsobj);
        JSONObject actual = JSONObject.fromObject(response.get("returnValue"));
        actual.put("url", httpUrl);
        actual.put("inPara", jsobj.toString());
        return actual;
    }

    /**
     * @description: 得到管家具体信息
     * @param json ： getUserDetail返回json
     * @author Elaine
     **/
    public JSONObject getUserInfo(JSONObject json){
        JSONArray array = JSONArray.fromObject(json.getString("data"));
        JSONObject info = array.getJSONObject(0);
        System.out.printf(info.toString());
        return info;
    }
}
