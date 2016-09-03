package keeper.appScript;

import com.ziroom.httpclient.HttpClientUtils;
import com.ziroom.utils.CommonFunction;
import keeper.confManagement.commonMethods.HttpRequest;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static keeper.confManagement.commonMethods.RenewFunCommon.*;

public class Keeper_Renew {
	private static final Logger log = Logger.getLogger(Keeper_Renew.class);
	HttpClientUtils hcu = new HttpClientUtils();
	HttpRequest hRequest = new HttpRequest();
	/***
	 * 获取续约详情列表
	 * 
	 * @return
	 */
	public JSONObject s_getCanRenewContract(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		StackTraceElement ste = (new Throwable()).getStackTrace()[1];
		String classAndMethod = (ste.getClassName() + "->" + ste.getMethodName());
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("isFollow", KeeperGlobalParas.isFollow);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("pageSize", "10");
		map.put("pageNum", "1");
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			// 截取数据中收尾[]
			if (responseData.equals("[]")) {
				log.info("s_getRenewList中data数据为空");
			} else {
				String sub_responseData = responseData.substring(1, responseData.length() - 1);
				
				ArrayList<String> arryData = CommonFunction.stringToList(sub_responseData);
				// 随机获取arraylist索引
				Random random = new Random();
				
				int num = random.nextInt(arryData.size());
				String SingleJson = arryData.get(num);
				KeeperGlobalParas.customerName = JSONObject.fromObject(SingleJson).getString("customerName");
				KeeperGlobalParas.oldContractCode = JSONObject.fromObject(SingleJson).getString("oldContractCode");

				//调试生成日志
//				log.info(classAndMethod+"_customerName值为：" + GlobalParameter.customerName);
//				log.info(classAndMethod+"_oldContractCode值为：" + GlobalParameter.oldContractCode);
//				log.info(SingleJson);

				//报告生成日志
				Reporter.log(map.toString());
				Reporter.log(SingleJson);

				log.info("s_getRenewList选中数据的返回值" + SingleJson);
				
			}
		} else {
			log.info("s_getRenewList服务器返回值------>>>>>为空");
		}
		return responseJson;
	}

	/***
	 * 获取续约详情列表_不随机
	 *
	 * @return
	 */
	public JSONObject s_getCanRenewContractSP(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("isFollow", KeeperGlobalParas.isFollow);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("pageSize", "10");
		map.put("pageNum", "1");
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		if (responseJson != null) {
			String responseData = responseJson.getString("data");
			// 截取数据中收尾[]
			if (responseData.equals("[]")) {
				log.info("s_getRenewList中data数据为空");
			}
			log.info("s_getRenewList返回值" + responseJson);
		} else {
			log.info("s_getRenewList服务器返回值------>>>>>为空");
		}
		return responseJson;
	}


	/***
	 * 查看是否能生成新的合同
	 *
	 * @return
	 */
	public JSONObject s_checkContractCanRenew(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		//
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("userId", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("contractCode", KeeperGlobalParas.oldContractCode);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		//String responseData = responseJson.getString("data");

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		if (responseJson != null) {
			log.info("s_checkContractCanRenew返回值：" + responseJson);
		} else {
			log.info("s_checkContractCanRenew服务器返回值----------->>>>>>>>>>为空");
		}
		return responseJson;
	}


	/***
	 * 获取用户续约详情
	 * 
	 * @return
	 */
	public JSONObject s_getContractInfo(String requestUrl) {
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("oldContractCode", KeeperGlobalParas.oldContractCode);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		String responseData = responseJson.getString("data");

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		//获取data中的oldContract内容
		KeeperGlobalParas.oldContract = JSONObject.fromObject(responseData).getString("oldContract");
		KeeperGlobalParas.renewStartDate = JSONObject.fromObject(KeeperGlobalParas.oldContract).getString("renewStartDate");
		KeeperGlobalParas.liveTime = JSONObject.fromObject(KeeperGlobalParas.oldContract).getString("liveTime");
		KeeperGlobalParas.oldStopDate = JSONObject.fromObject(KeeperGlobalParas.oldContract).getString("oldStopDate");

		//距离截止日期的时间天数 返回GlobalParameter.DeadLine
		dateDiff(KeeperGlobalParas.oldStopDate);

		log.info(KeeperGlobalParas.oldStopDate);
		log.info(KeeperGlobalParas.DeadLine);

		//获取data中的houseInfo中的数据内容
		KeeperGlobalParas.houseInfo = JSONObject.fromObject(responseData).getString("houseInfo");
		String hirestopDate = JSONObject.fromObject(KeeperGlobalParas.houseInfo).getString("hireStopDate");
		KeeperGlobalParas.hireStopDate = hirestopDate.replace(".","-");

		//计算续约结束时间
		calculateRenewEndDate();

		if (responseJson != null) {
			log.info("s_getContractInfo返回值：" + responseJson);
		} else {
			log.info("s_getContractInfo服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;
	}
	/***
	 * 生成续约1--查询续约详情
	 * 
	 * @return
	 */
	public JSONObject s_getRenewType(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		//
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("oldContractCode", KeeperGlobalParas.oldContractCode);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

        //data中选择长续cx
		JSONArray responseData = responseJson.getJSONArray("data");
        KeeperGlobalParas.renewType = responseData.get(0).toString();
        //log.info(GlobalParameter.renewType);

		if (responseJson != null) {
			log.info("s_getRenewType返回值：" + responseJson);
		} else {
			log.info("s_getRenewType服务器返回值----------->>>>>>>>>>为空");
		}
		return responseJson;
	}
	/***
	 * 生成续约2——续约类型
	 * 
	 * @return
	 */
	public JSONObject s_getRenewPaymentList(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		//
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("oldContractCode", KeeperGlobalParas.oldContractCode);
		map.put("signDate", KeeperGlobalParas.renewStartDate);
		map.put("endDate", KeeperGlobalParas.renewEndDate);
		map.put("renewType", "cx");
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

        //data中选择paymentType,get(1)选择为季付
        JSONObject responseData = JSONObject.fromObject(responseJson).getJSONObject("data");
        JSONArray paymentlist = JSONObject.fromObject(responseData).getJSONArray("paymentList");
        KeeperGlobalParas.paymentType = paymentlist.get(1).toString();
        log.info(KeeperGlobalParas.paymentType);

		if (responseJson != null) {
			log.info("s_getRenewPaymentList返回值：" + responseJson);
		} else {
			log.info("s_getRenewPaymentList服务器返回值----------->>>>>>>>>>为空");
		}
		return responseJson;
	}
	/***
	 * 生成续约3——付款方式
	 * 
	 * @return
	 */
	public JSONObject s_getRenewPayInfo(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		//
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("oldContractCode", KeeperGlobalParas.oldContractCode);
		map.put("signDate", KeeperGlobalParas.renewStartDate);
		map.put("endDate", KeeperGlobalParas.renewEndDate);
		map.put("renewType", KeeperGlobalParas.renewType);
		map.put("paymentType", KeeperGlobalParas.paymentType);
		map.put("hireStopDate", KeeperGlobalParas.hireStopDate);

		map.put("isBlank", KeeperGlobalParas.isBlank);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		//log.info(map.get("hireStopDate").toString());
        log.info(map.toString());
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		String responseData = responseJson.getString("data");

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		KeeperGlobalParas.oldIsDeposit = JSONObject.fromObject(responseData).getString("oldIsDeposit");
		//log.info(GlobalParameter.oldIsDeposit);
		KeeperGlobalParas.countMoney = JSONObject.fromObject(responseData).getString("countMoney");
		//log.info(GlobalParameter.countMoney);
		KeeperGlobalParas.deposit = JSONObject.fromObject(responseData).getString("deposit");
		//log.info(GlobalParameter.deposit);
		KeeperGlobalParas.monthPrice = JSONObject.fromObject(responseData).getString("monthPrice");
		//log.info(GlobalParameter.monthPrice);
		KeeperGlobalParas.countPrice = JSONObject.fromObject(responseData).getString("countPrice");
		//log.info(GlobalParameter.countPrice);
        KeeperGlobalParas.commission = JSONObject.fromObject(responseData).getString("commission");
        //log.info(GlobalParameter.commission);
        KeeperGlobalParas.period = JSONObject.fromObject(responseData).getString("period");
		//log.info(GlobalParameter.period);
		//log.info(responseData);

		renewWfDataDiff(KeeperGlobalParas.renewStartDate, KeeperGlobalParas.renewEndDate);
//		log.info(GlobalParameter.periodday);


		calculateCountPrice("6");

		if (responseJson != null) {
			log.info("s_getRenewPayInfo返回值：" + responseJson);
		} else {
			log.info("s_getRenewPayInfo服务器返回值----------->>>>>>>>>>为空");
			}

		return responseJson;
	}
	/***
	 * 生成续约4--生成合同
	 * 
	 * @return
	 */
	public JSONObject s_renewContract(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		//
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("contractCode", KeeperGlobalParas.oldContractCode);
		map.put("signDate", KeeperGlobalParas.renewStartDate);
		map.put("endDate", KeeperGlobalParas.renewEndDate);
		map.put("renewType", KeeperGlobalParas.renewType);
		map.put("paymentType", KeeperGlobalParas.paymentType);
		map.put("hireStopDate", KeeperGlobalParas.hireStopDate);
		map.put("oldIsDeposit", KeeperGlobalParas.oldIsDeposit);
		map.put("creatType", KeeperGlobalParas.creatType);
		map.put("countMoney", KeeperGlobalParas.countMoney);
		map.put("deposit", KeeperGlobalParas.deposit);
		map.put("monthPrice", KeeperGlobalParas.monthPrice);
		map.put("commission", KeeperGlobalParas.commission);
		map.put("isBlank", KeeperGlobalParas.isBlank);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		//log.info(map.get("hireStopDate").toString());
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		String responseData = responseJson.getString("data");

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		KeeperGlobalParas.renewContract = JSONObject.fromObject(responseData).getString("renewContract");
		//log.info(GlobalParameter.renewContract);

		if (responseJson != null) {
			log.info("s_renewContract返回值：" + responseJson);
		} else {
			log.info("s_renewContract服务器返回值----------->>>>>>>>>>为空");
			}

		return responseJson;
	}


	/***
	 * 获取生成续约合同信息
	 *
	 * @return
	 */
	public JSONObject s_getRenewContractInfo(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("renewContractCode", KeeperGlobalParas.renewContract);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		String responseData = responseJson.getString("data");

		log.info(responseJson);
		log.info(map.toString());

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		KeeperGlobalParas.countMoney = JSONObject.fromObject(responseData).getString("countMoney");
		//获取monthPrice的值,省略返回的元/月
		String sub = JSONObject.fromObject(responseData).getString("monthPrice");
		KeeperGlobalParas.monthPrice = sub.substring(0,sub.length()-3);
		//转换格式2016年09月07日为2016-09-07
		KeeperGlobalParas.renewStartDate = JSONObject.fromObject(responseData).getString("startDate");
		String data = KeeperGlobalParas.renewStartDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
		Date date1 = null;
		try {
			date1 = sdf.parse(data);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		String formatDate = null;
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		formatDate = dFormat.format(date1);
		System.out.println(formatDate);


//		GlobalParameter.oldContract = JSONObject.fromObject(responseData).getString("oldContractCode");
//		GlobalParameter.renewStartDate = JSONObject.fromObject(GlobalParameter.oldContract).getString("renewStartDate");
//		GlobalParameter.houseInfo = JSONObject.fromObject(responseData).getString("houseInfo");
//		String hirestopDate = JSONObject.fromObject(GlobalParameter.houseInfo).getString("hireStopDate");
//		GlobalParameter.hireStopDate = hirestopDate.replace(".","-");
//
////		log.info(GlobalParameter.oldContract);
////		log.info(GlobalParameter.renewStartDate);
////		log.info(GlobalParameter.houseInfo);
////		log.info(GlobalParameter.hireStopDate);
//
//
//		//转换日期为data类型
//		String data = GlobalParameter.renewStartDate;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date1 = null;
//		try {
//			date1 = sdf.parse(data);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		//log.info(date1);
//
//		//对日期进行增加一年,减少一天的操作
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date1);
//		calendar.add(Calendar.YEAR,1);
//		calendar.add(Calendar.DAY_OF_MONTH,-1);
//		//System.out.println(calendar.getTime());
//
//		//输出新签结束日期,转换为String类型
//		GlobalParameter.renewEndDate = sdf.format(calendar.getTime());
//		//System.out.println(GlobalParameter.renewEndDate +"endDate");

		if (responseJson != null) {
			log.info("s_getContractInfo返回值：" + responseJson);
		} else {
			log.info("s_getContractInfo服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;
	}



	/***
	 * 重新发起续约
	 *
	 * @return
	 */
	public JSONObject s_renewContractAgain(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		//
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("contractCode", KeeperGlobalParas.renewContract);
		map.put("signDate", KeeperGlobalParas.renewStartDate);
		map.put("endDate", KeeperGlobalParas.renewEndDate);
		map.put("renewType", KeeperGlobalParas.renewType);
		map.put("paymentType", KeeperGlobalParas.paymentType);
		map.put("oldIsDeposit", KeeperGlobalParas.oldIsDeposit);
		map.put("creatType", KeeperGlobalParas.creatType);
		map.put("countMoney", KeeperGlobalParas.countMoney);
		map.put("deposit", KeeperGlobalParas.deposit);
		map.put("monthPrice", KeeperGlobalParas.monthPrice);
		map.put("commission", KeeperGlobalParas.commission);
		map.put("isBlank", KeeperGlobalParas.isBlank);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		//log.info(map.get("hireStopDate").toString());

		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
		String responseData = responseJson.getString("data");


		log.info(map.toString());
		log.info(responseJson.toString());

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		//GlobalParameter.renewContract = JSONObject.fromObject(responseData).getString("renewContract");
		//log.info(GlobalParameter.renewContract);

		if (responseJson != null) {
			log.info("s_renewContract返回值：" + responseJson);
		} else {
			log.info("s_renewContract服务器返回值----------->>>>>>>>>>为空");
		}

		return responseJson;
	}
	/***
	 * 关闭续约
	 * 
	 * @return
	 */
	public JSONObject s_closeRenewContract(String requestUrl){
		String timestamp = CommonFunction.getTimeStampOf13();
		String uuid = KeeperGlobalParas.appid + timestamp;
		HashMap<String, String> map = new HashMap<String, String>();
		//
		map.put("keeperCode", KeeperGlobalParas.test1);
		map.put("cityCode", KeeperGlobalParas.city_code );
		map.put("appId", KeeperGlobalParas.appid);
		map.put("imei", KeeperGlobalParas.imei);
		map.put("osType", KeeperGlobalParas.osType);
		map.put("contractCode", KeeperGlobalParas.renewContract);
		map.put("timestamp", timestamp);
		map.put("versionInt", KeeperGlobalParas.versionInt);
		map.put("source", KeeperGlobalParas.source);
		map.put("uuid", uuid);
		map.put("appType", KeeperGlobalParas.appType);
		map.put("sign", CommonFunction.getCrmSign(map));
		//log.info(map.get("hireStopDate").toString());
		
		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);

		//报告生成日志
		Reporter.log(map.toString());
		Reporter.log(responseJson.toString());

		if (responseJson != null) {
			log.info("s_closeRenewContract返回值：" + responseJson);
		} else {
			log.info("s_closeRenewContract服务器返回值----------->>>>>>>>>>为空");
			}

		return responseJson;
	}

	
	/***
	 * 获取appid
	 * 
	 * @return
	 */
	public String getAppid() {
		String domainName = PropertyConstants.CRM_DOMIN;
		String httpUrlAppid = domainName + "/crm/common/createAppId?appType=1&imei=352248061569009";
		JSONObject responseJson = hRequest.getGetReturn(httpUrlAppid);
		String app_id = "";
		if (responseJson != null) {

			app_id = JSONObject.fromObject(responseJson.getString("data")).getString("appId");

			if (app_id.equals("")) {
				log.info("变量getAppid值为空");
			} else {
				KeeperGlobalParas.appid = app_id;
				log.debug("变量appid:" + KeeperGlobalParas.appid);
			}
		} else {
			log.info("getAppid服务器返回值----------->>>>>>>>>>为空");
		}

		return app_id;
	}


	/***
	 * 获取续约详情列表信息详情
	 *
	 * @return
	 */
//	public JSONObject s_getRenewContracts(String requestUrl){
//		String timestamp = CommonFunction.getTimeStampOf13();
//		StackTraceElement ste = (new Throwable()).getStackTrace()[1];
//		String classAndMethod = (ste.getClassName() + "->" + ste.getMethodName());
//		String uuid = GlobalParameter.appid + timestamp;
//		HashMap<String, String> map = new HashMap<String, String>();
//
//		map.put("keeperCode", GlobalParameter.test1);
//		map.put("isFollow", GlobalParameter.isFollow);
//		map.put("cityCode", GlobalParameter.city_code );
//		map.put("pageSize", "10");
//		map.put("pageNum", "1");
//		map.put("appId", GlobalParameter.appid);
//		map.put("imei", GlobalParameter.imei);
//		map.put("osType", GlobalParameter.osType);
//		map.put("timestamp", timestamp);
//		map.put("versionInt", GlobalParameter.versionInt);
//		map.put("source", GlobalParameter.source);
//		map.put("uuid", uuid);
//		map.put("appType", GlobalParameter.appType);
//		map.put("sign", CommonFunction.getCrmSign(map));
//
//		JSONObject responseJson = hRequest.getPostReturnValue(requestUrl, map);
//		if (responseJson != null) {
//			String responseData = responseJson.getString("data");
//			// 截取数据中收尾[]
//			if (responseData.equals("[]")) {
//				log.info("s_getRenewList中data数据为空");
//			} else {
////				String sub_responseData = responseData.substring(1, responseData.length() - 1);
////
////				ArrayList<String> arryData = CommonFunction.stringToList(sub_responseData);
////				// 随机获取arraylist索引
////				Random random = new Random();
////
////				int num = random.nextInt(arryData.size());
////				String SingleJson = arryData.get(num);
////				GlobalParameter.customerName = JSONObject.fromObject(SingleJson).getString("customerName");
////				log.info(classAndMethod+"_customerName值为：" + GlobalParameter.customerName);
////				GlobalParameter.oldContractCode = JSONObject.fromObject(SingleJson).getString("oldContractCode");
////				log.info(classAndMethod+"_oldContractCode值为：" + GlobalParameter.oldContractCode);
//
//			}
//			log.info("s_getRenewList返回值" + responseJson);
//		} else {
//			log.info("s_getRenewList服务器返回值------>>>>>为空");
//		}
//		return responseJson;
//	}

}
