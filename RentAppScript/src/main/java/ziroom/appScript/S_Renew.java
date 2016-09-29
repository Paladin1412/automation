package ziroom.appScript;

import com.ziroom.utils.CommonFunction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import ziroom.confManagement.commonMethods.RentAppGlobalParas;
import ziroom.confManagement.commonMethods.HttpRequest;

import java.util.HashMap;

/**
 * Created by wujing on 16/8/16.
 */
public class S_Renew {
    private static final Logger logR = Logger.getLogger(S_Renew.class);

    HttpRequest hRequest = new HttpRequest();


    /***
     * 获取合同列表
     *
     * @return
     */
    public JSONObject s_getContractList (String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("model", RentAppGlobalParas.model);
        map.put("imei", RentAppGlobalParas.imei);
        map.put("app_version", RentAppGlobalParas.appVersionStr);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("ignoreCity", RentAppGlobalParas.ignoreCity);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("appId",RentAppGlobalParas.appid);
        map.put("sign",CommonFunction.getCrmSign(map));

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
        //返回data字符串,以[]包含,需将data的返回内容转换为JSONArray,获取中括号中的内容
        JSONArray responseData = responseJson.getJSONArray("data");
        JSONObject  innerJson = responseData.getJSONObject(0);//获取数组中位置为0的中括号中的内容

        //logR.info(map.toString());
        //logR.info(responseData);
        //logR.info(innerJson);
        //报告生成日志
        

        RentAppGlobalParas.sysContractId = JSONObject.fromObject(innerJson).getString("sysContractId");
        RentAppGlobalParas.contractCode = JSONObject.fromObject(innerJson).getString("contractCode");

        if (responseJson != null) {
            logR.info("s_getContractList返回值" + responseJson);
        } else {
            logR.info("s_getContractList返回值------->>>>>>为空");
        }
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(innerJson.toString());
        return responseJson;
    }



    /***
     * 获取合同详情
     *
     * @return
     */
    public JSONObject s_getContractInfo (String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("appId",RentAppGlobalParas.appid);//不一定传参准确
        map.put("model", RentAppGlobalParas.model);
        map.put("imei", RentAppGlobalParas.imei);
        map.put("sysContractId",RentAppGlobalParas.sysContractId);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("app_version", RentAppGlobalParas.appVersionStr);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("contractCode", RentAppGlobalParas.contractCode);
        map.put("sign",CommonFunction.getCrmSign(map));

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_getContractInfo返回值" + responseJson);
        } else {
            logR.info("s_getContractInfo返回值------->>>>>>为空");
        }

        return responseJson;
    }


    /***
     * 检查合同是否有生成续约合同
     *
     * @return
     */
    public JSONObject s_checkHasRenewContract (String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("appId",RentAppGlobalParas.appid);//不一定传参准确
        map.put("imei", RentAppGlobalParas.imei);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("oldContractCode", RentAppGlobalParas.contractCode);
        map.put("sign",CommonFunction.getCrmSign(map));

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_checkHasRennewContract返回值" + responseJson);
        } else {
            logR.info("s_checkHasRennewContract返回值------->>>>>>为空");
        }

        return responseJson;
    }


    /***
     * 获取续约合同详情
     *
     * @return
     */
    public JSONObject s_getRenewContractInfo(String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("appId",RentAppGlobalParas.appid);//不一定传参准确
        map.put("imei", RentAppGlobalParas.imei);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("oldContractCode", RentAppGlobalParas.contractCode);
        map.put("sign",CommonFunction.getCrmSign(map));


        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_getRenewContractInfo返回值" + responseJson);
        } else {
            logR.info("s_getRenewContractInfo返回值------->>>>>>为空");
        }

        return responseJson;
    }

    //中间调取接口http://interfaces.ziroom.com/index.php?_p=api_newsign&_a=get_ra_config

    /***
     * 确认合同
     *
     * @return
     */
    public JSONObject s_clauseSelect (String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("appId",RentAppGlobalParas.appid);//不一定传参准确
        map.put("model", RentAppGlobalParas.model);
        map.put("imei", RentAppGlobalParas.imei);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("app_version", RentAppGlobalParas.appVersionStr);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("oldContractCode", RentAppGlobalParas.contractCode);
        map.put("sign",CommonFunction.getCrmSign(map));

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_clauseSelect返回值" + responseJson);
        } else {
            logR.info("s_clauseSelect返回值------->>>>>>为空");
        }

        return responseJson;
    }

    /***
     * 获取首次支付信息
     *
     * @author jihaibo
     * @param requestUrl
     * @return JSONObject
     */
    public JSONObject s_getFirstPayInfo(String requestUrl) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("os",RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("appId",RentAppGlobalParas.appid);//不一定传参准确
        map.put("model", RentAppGlobalParas.model);
        map.put("imei", RentAppGlobalParas.imei);
        map.put("appVersionStr", RentAppGlobalParas.appVersionStr);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("app_version", RentAppGlobalParas.appVersionStr);
        map.put("osType", RentAppGlobalParas.osType);
        map.put("timestamp", CommonFunction.getTimeStampOf13());
        map.put("source", RentAppGlobalParas.source);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("uuid", RentAppGlobalParas.appid + "_" + CommonFunction.getTimeStampOf13());
        map.put("appType", RentAppGlobalParas.appType);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("activities","");
        map.put("oldContractCode", RentAppGlobalParas.contractCode);
        map.put("sign", CommonFunction.getCrmSign(map));
        // 请求接口返回值
        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
        String responseData = responseJson.getString("data");

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());
        logR.info(map.toString());
        logR.info(responseJson.toString());
        logR.info(responseData);

        RentAppGlobalParas.commission = JSONObject.fromObject(responseData).getString("commission");
        RentAppGlobalParas.deposit = JSONObject.fromObject(responseData).getString("deposit");
        RentAppGlobalParas.payment = JSONObject.fromObject(responseData).getString("payment");
        RentAppGlobalParas.housePrice = JSONObject.fromObject(responseData).getString("housePrice");
        RentAppGlobalParas.commission = JSONObject.fromObject(responseData).getString("commission");

        double price = Double.parseDouble(RentAppGlobalParas.housePrice == null ? "0.0" : RentAppGlobalParas.housePrice);
        // 租N个月押金1个月
        int months = Integer.parseInt(RentAppGlobalParas.payment == null ? "0" : RentAppGlobalParas.payment);
        // 服务费
        double commission = Double.parseDouble(RentAppGlobalParas.commission == null ? "0.0" : RentAppGlobalParas.commission);
        double countMoney = (price * months) + commission;
        RentAppGlobalParas.countMoney = "" +countMoney;

        if (responseJson != null) {
            if (responseData.equals("[]")) {
                logR.info("s_agreeContractTerms1返回data值为空");

            } else {
                logR.debug("变量commission" + RentAppGlobalParas.commission);
                logR.debug("变量deposit" + RentAppGlobalParas.deposit);
                logR.debug("变量reserveDeposit" + RentAppGlobalParas.reserveDeposit);
                logR.debug("变量housePrice" + RentAppGlobalParas.housePrice);
                logR.info("s_GetFirstPayInfo返回值" + responseJson);
            }

        } else {
            logR.info("s_GetFirstPayInfo服务器返回值------------>>>>>>>为空");
        }
        return responseJson;

    }

    /***
     * 确认生成续约Activity
     *
     * @return
     */
    public JSONObject s_getRenewActivity (String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("appId",RentAppGlobalParas.appid);//不一定传参准确
        map.put("model", RentAppGlobalParas.model);
        map.put("imei", RentAppGlobalParas.imei);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("app_version", RentAppGlobalParas.appVersionStr);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("oldContractCode", RentAppGlobalParas.contractCode);
        map.put("sign",CommonFunction.getCrmSign(map));

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_getRenewActivity返回值" + responseJson);
        } else {
            logR.info("s_getRenewActivity返回值------->>>>>>为空");
        }

        return responseJson;
    }

    //中间调取接口http://interfaces.ziroom.com/index.php?_p=api_newsign&_a=get_ra_sign_info

    public JSONObject s_get_ra_sign_info (String requestUrl) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10()));
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", CommonFunction.getTimeStampOf10());
        map.put("house_type", RentAppGlobalParas.ReturnHouseType);
        map.put("house_code", RentAppGlobalParas.house_code);
        map.put("is_renew", RentAppGlobalParas.isRenew);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("house_id", RentAppGlobalParas.house_id);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("model", RentAppGlobalParas.model);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("imei",RentAppGlobalParas.imei);
        map.put("app_version",RentAppGlobalParas.appVersionStr);
        map.put("old_contract_code", RentAppGlobalParas.contractCode);


        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
        //String responseData = responseJson.getString("data");

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_get_ra_sign_info返回值" + responseJson);
        } else {
            logR.info("s_get_ra_sign_info返回值------->>>>>>为空");
        }
        return responseJson;
    }

    //http://interfaces.ziroom.com/index.php?_p=api_newsign&_a=set_ra_signature

    public JSONObject s_set_ra_signature(String requestUrl) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sign", CommonFunction.getAppSign(RentAppGlobalParas.login_uid, CommonFunction.getTimeStampOf10()));
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", CommonFunction.getTimeStampOf10());
        map.put("house_type", RentAppGlobalParas.ReturnHouseType);
        map.put("house_code", RentAppGlobalParas.house_code);
        map.put("is_renew", RentAppGlobalParas.isRenew);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("house_id", RentAppGlobalParas.house_id);
        map.put("password", RentAppGlobalParas.passWord);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("model", RentAppGlobalParas.model);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("imei",RentAppGlobalParas.imei);
        map.put("app_version",RentAppGlobalParas.appVersionStr);
        map.put("old_contract_code", RentAppGlobalParas.contractCode);


        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
        //String responseData = responseJson.getString("data");

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_set_ra_signature返回值" + responseJson);
        } else {
            logR.info("s_set_ra_signature返回值------->>>>>>为空");
        }
        return responseJson;
    }


    /***
     * 确认续签合同
     *
     * @return
     */
    public JSONObject s_confirmRenewContract (String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("appId",RentAppGlobalParas.appid);
        map.put("model", RentAppGlobalParas.model);
        map.put("imei", RentAppGlobalParas.imei);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("app_version", RentAppGlobalParas.appVersionStr);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("countMoney",RentAppGlobalParas.countMoney);
        map.put("isRenew",RentAppGlobalParas.isRenew);
        map.put("verificationCode","1234567890");
        //参数activities为空
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("oldContractCode", RentAppGlobalParas.contractCode);
        map.put("sign",CommonFunction.getCrmSign(map));

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
        //String responseData = responseJson.getString("data");
        RentAppGlobalParas.renewContractCode = JSONObject.fromObject(responseJson).getString("data");

        logR.info(map.toString());
        logR.info(responseJson.toString());

        //报告生成日志
        Reporter.log(map.toString());
        Reporter.log(requestUrl);
        Reporter.log(responseJson.toString());

        if (responseJson != null) {
            logR.info("s_confirmRenewContract返回值" + responseJson);
        } else {
            logR.info("s_confirmRenewContract返回值------->>>>>>为空");
        }
        return responseJson;
    }

    /***
     * 获取支付信息
     *
     * @return
     */
    public JSONObject s_getPayInfo (String requestUrl) {
        String timeStamp = CommonFunction.getTimeStampOf13();
        String uuid = RentAppGlobalParas.appid +"_"+ timeStamp;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uid", RentAppGlobalParas.login_uid);
        map.put("timestamp", timeStamp);
        map.put("os", RentAppGlobalParas.os);
        map.put("app", RentAppGlobalParas.app);
        map.put("appId",RentAppGlobalParas.appid);
        map.put("model", RentAppGlobalParas.model);
        map.put("imei", RentAppGlobalParas.imei);
        map.put("appVersionStr",RentAppGlobalParas.appVersionStr);
        map.put("app_version", RentAppGlobalParas.appVersionStr);
        map.put("city_code", RentAppGlobalParas.city_code);
        map.put("network", RentAppGlobalParas.network);
        map.put("ip", RentAppGlobalParas.ip);
        map.put("cityCode", RentAppGlobalParas.city_code);
        map.put("uuid", uuid);
        map.put("osType",RentAppGlobalParas.osType);
        map.put("source", RentAppGlobalParas.source);
        map.put("appType", RentAppGlobalParas.appType);
        map.put("contractCode", RentAppGlobalParas.renewContractCode);
        map.put("sign",CommonFunction.getCrmSign(map));

        JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
        //获取参数
        //RentAppGlobalParas.shouldPrice = JSONObject.fromObject(responseData).getString("shouldPrice")
        //报告生成日志
        if (responseJson != null) {
            logR.info("s_confirmRenewContract返回值" + responseJson);
        } else {
            logR.info("s_confirmRenewContract返回值------->>>>>>为空");
        }
        Reporter.log(responseJson.toString());
        Reporter.log(requestUrl);
        Reporter.log(map.toString());

        return responseJson;
    }

}
