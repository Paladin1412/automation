package keeper.appScript;

import keeper.confManagement.commonMethods.HttpRequest;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.commonMethods.RenewFunCommon;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.util.HashMap;

/**
 * Created by wujing on 16/8/26.
 */
public class Keeper_CreditCertification {

    private static final Logger logC = Logger.getLogger(Keeper_CreditCertification.class);
    HttpRequest hRequest = new HttpRequest();

    /**
     * 更新用户信用认证状态为已完成认证
     */
    public void updateUserInfo() {

        //老用户进行信用认证
        String updateidentitystatussql = "update tb_user_status set identity_status = 4  where uid IN (select uid from tb_user_identity where phone = '" + KeeperGlobalParas.customerPhone +"')";
        RenewFunCommon.updateCreditMysqlData(updateidentitystatussql);
        String updatestatussql = "update tb_user_identity set status = 4 where phone = '" + KeeperGlobalParas.customerPhone + "'";
        RenewFunCommon.updateCreditMysqlData(updatestatussql);

    }

    /**
     * 更新自如信用分大于70
     */
    public void updateCreditScores() {

        String updateidentitystatussql = "update tb_user_module_score set module_score = 15 where uid = '" + KeeperGlobalParas.uid + "'";
        RenewFunCommon.updateCreditMysqlData(updateidentitystatussql);
    }


    /**
     * 用户授权自如信用接口
     *
     */
    public JSONObject s_userAuthorization(String requestUrl){

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("uid", KeeperGlobalParas.uid);
        map.put("phone", KeeperGlobalParas.customerPhone);

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

        //报告生成日志
        Reporter.log(requestUrl);
        Reporter.log(map.toString());
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            String responseData = responseJson.getString("data");
            // 截取数据中收尾[]
            if (responseData.equals("[]")) {
                logC.info("s_userAuthorization中data数据为空");
            }
            logC.info("s_userAuthorization返回值" + responseJson);
        } else {
            logC.info("s_userAuthorization服务器返回值------>>>>>为空");
        }
        return responseJson;
    }

    /**
     * 测试环境,调用算分接口
     *
     */
    public JSONObject s_callCountInterface(String requestUrl){
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("uid", KeeperGlobalParas.uid);

        //更新用户信用认证状态为已完成认证
        updateUserInfo();

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

        //初始化信用总分,使其大于70分
        updateCreditScores();

        //报告生成日志
        Reporter.log(requestUrl);
        Reporter.log(map.toString());
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            String responseData = responseJson.getString("data");
            // 截取数据中收尾[]
            if (responseData.equals("[]")) {
                logC.info("s_userAuthorization中data数据为空");
            }
            logC.info("s_userAuthorization返回值" + responseJson);
        } else {
            logC.info("s_userAuthorization服务器返回值------>>>>>为空");
        }
        return responseJson;

    }

}
