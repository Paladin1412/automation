package keeper.appScript;

import keeper.confManagement.commonMethods.HttpRequest;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by wujing on 16/8/31.
 */
public class Keeper_Audit {

    private static final Logger logA = Logger.getLogger(Keeper_Audit.class);
    HttpRequest hRequest = new HttpRequest();

    String contractDetailUrl;

    /***
     * 修改管家信息
     * 登录
     */
    public Map s_LoginKeeper(String httpUrl) {
        Reporter.log(httpUrl);
        Map<String, String> response = hRequest.getGetStrReturn(httpUrl);

        Reporter.log(response.toString());

        String htmlStr = response.get("returnValue");
        if (htmlStr.equals("")) {
            logA.info("s_LoginKeeper服务器返回值------>>>>>为空");

        } else {
            logA.info("s_LoginKeeper返回值" + response);
        }
        return response;
    }

    /**
     *
     * 获取新签管理列表
     */
    public Map s_newSignList(String httpUrl) {
        Reporter.log(httpUrl);

        Map<String, String> response = hRequest.getGetStrReturn(httpUrl);

        String htmlStr = response.get("returnValue");

        Document doc = Jsoup.parse(htmlStr);

        contractDetailUrl = doc.select("#z_ListTable2_BJZYCW81609010002.z_ListTable2 tbody tr td p a").get(0).attr("href");

        if (htmlStr.equals("")) {
            logA.info("s_newSignList服务器返回值------>>>>>为空");
        } else {
            logA.info("s_newSignList返回值" + response);
        }
        return response;
    }
    /**
     *
     * 获取新签_合同详情
     */
    public Map s_newSignDetail() {

        String domainName = PropertyConstants.PHP_CRM_DOMAIN;
        String httpUrl = domainName + "/" + contractDetailUrl;
        Reporter.log(httpUrl);

        Map<String, String> response = hRequest.getGetStrReturn(httpUrl);

        String htmlStr = response.get("returnValue");

        if (htmlStr.equals("")) {
            logA.info("s_newSignDetail服务器返回值------>>>>>为空");
        } else {
//            logA.info("s_newSignList返回值" + htmlStr);
        }
        return response;
    }

    /**
     *
     * 修改管家信息
     */
    public JSONObject s_getGuanjiaInfo(String httpUrl) {
        Reporter.log(httpUrl);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("contract_code", KeeperGlobalParas.oldContract);
        map.put("user_code", KeeperGlobalParas.serviceUserName);
        map.put("type", "edit_dianjingli");
        map.put("edit_type", "suoshu_guanjia");

        JSONObject responseJson = hRequest.getPostReturnValue(httpUrl, map);


        if (responseJson == null) {
            logA.info("s_getGuanjiaInfo服务器返回值------>>>>>为空");
        } else {
            logA.info("s_newSignList返回值" + responseJson);
        }
        return responseJson;
    }


}
