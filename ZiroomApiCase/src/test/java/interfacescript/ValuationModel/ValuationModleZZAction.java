package interfacescript.ValuationModel;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.appScript.Keeper_ValuationModelCommon;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujing on 16/9/1.
 */
public class ValuationModleZZAction {

    private static final Logger logV = Logger.getLogger(ValuationModleZZAction.class);

    Keeper_Me me = new Keeper_Me();
    JSONObject result = new JSONObject();
    Keeper_ValuationModelCommon vmc = new Keeper_ValuationModelCommon();

    JSONObject viewJson;
    JSONObject ehrJson;
    JSONObject houseInfo;
    JSONObject houseinfo;
    JSONObject getEntireHirePrice;
    JSONObject getStandardId;
    JSONObject savePriceMode;
    JSONObject decorateInfo;
    JSONObject saveConfig;
    JSONObject saveConfigInfo;

    @BeforeClass
    public void init_config() {
        String ViewJson = "{\"usermobile\":\"13810327187\",\"diningRoom\":\"0\",\"afterRoomNum\":\"2\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"3\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"account\":\"20092810\",\"productVesion\":\"1003\",\"district\":\"通州\",\"resblock\":\"格兰晴天\"}";
        viewJson = JSONObject.fromObject(ViewJson);

        String EhrJson = "{\"status\":\"success\",\"error_message\":\"获取管家信息成功\",\"data\":{\"phone\":\"13552169604\",\"centerCode\":\"Z10001\",\"cityCode\":11,\"groupCode\":\"Z30208\",\"emplid\":\"20092810\",\"center\":\"业务拓展中心\",\"email\":\"maj53@ziroom.com\",\"name\":\"马洁\",\"jobCode\":\"100076\",\"dept\":\"学院大区\",\"deptCode\":\"Z20076\",\"group\":\"国展业务组\",\"descr\":\"直收管家\",\"setId\":\"HL011\",\"treePath\":\"Z00001,Z00002,Z10001,Z20076,Z30208\"}}";
        ehrJson = JSONObject.fromObject(EhrJson);

        String houseJson = " {\"data\":{\"unit\":\"3单元\",\"districtName\":\"通州\",\"villageId\":\"1111027374633\",\"districtId\":\"23008625\",\"floor\":\"1\",\"status\":\"success\",\"address\":\"通州格兰晴天7号楼3单元1312\",\"seccondSource\":\"21\",\"roomNum\":\"312\",\"buildNum\":\"7号楼\",\"firstSource\":\"4\",\"villageName\":\"格兰晴天\"},\"error_message\":\"通州格兰晴天7号楼3单元1312房源宝录制成功\",\"status\":\"success\"}";
        houseInfo = JSONObject.fromObject(houseJson);

        vmc.getUserInfo(ehrJson);
        vmc.initViewJson(viewJson);

        houseinfo = houseInfo.getJSONObject("data");

    }

    @Test(description = "登录")
    public void testLogin(){
        // 获取输入sql语句
        String LoginInputSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "LoginInputSql") + "  caseID='TC000001'";
        // 获取api后缀sql
        String SuffixUrlSql = PropertiesUtil.getPropValAsString(PropertyConstants.inputOutValueSqlPath, "SuffixUrlSql") + "  caseID='TC000001'";
        // 获取登陆输入内容
        String LoginInput = ProviderDataSource.getDataString(LoginInputSql);
        // 获取请求http
        String SuffixUrl = ProviderDataSource.getDataString(SuffixUrlSql);
        String domainName = PropertyConstants.INTERFACES_DOMIN;
        String httpUrl = domainName + SuffixUrl;
        //获取输入字符串 格式化为json
        JSONObject json = JSONObject.fromObject(LoginInput);
        //请求参数
        //String passWord = json.getString("password");
        //String loginName = json.getString("login_name");
        String passWord = "123456";
        String loginName = "20092810";

        JSONObject responseJson = me.s_login(httpUrl,loginName, passWord);

        String actual ="";
        if (responseJson !=null) {
            actual = responseJson.getString("status");
            logV.debug("用例login返回值为" + responseJson);
        } else {
            logV.debug("用例login请求返回值------>>>>为空");
        }
        Assert.assertEquals(actual, "success");
    }


    @Test(description = "登录网站")
    public void testMLogin() {
        boolean mlogin = vmc.login();
        if (mlogin == false) {
            result.put("status", "false");
            result.put("error_message", "登陆失败");
        }
    }
    @Test(description = "获得整租价格")
    public void testGetEntireHirePrice() {
        getEntireHirePrice = vmc.getEntireHirePrice();
        if (getEntireHirePrice.get("status").equals("failure")) {
            result.putAll(getEntireHirePrice);
        }
    }
    @Test(description = "得到standardId")
    public void testGetStandardID() {
        getStandardId = vmc.getStandardId(houseinfo);
        String status = getStandardId.getJSONObject("responseHeader").getString("status");
        if (!status.equals("0")) {
            result.putAll(getStandardId);
        }
    }
    @Test(description = "保存数据")
    public void testSavePriceMode() {
        savePriceMode = vmc.savePriceMode(houseinfo, getEntireHirePrice, getStandardId);
        if (savePriceMode.get("status").equals("failure")) {
            result.putAll(savePriceMode);
        }
    }

    @Test(description = "获取装修配置信息")
    public void testGetEntireConfigItems(){
        vmc.getEntireConfigItems();
    }

    @Test(description = "装修信息")
    public void testDecorateInfo(){
        decorateInfo = vmc.decorateInfo(savePriceMode);
        if (decorateInfo.get("status").equals("failure")) {
            result.putAll(decorateInfo);
        }
    }

    @Test(description = "配置信息")
    public void testSaveConfig(){
        saveConfig = vmc.saveConfig(savePriceMode);
        if (saveConfig.get("status").equals("failure")) {
            result.putAll(saveConfig);
        }
    }

    @Test(description = "保存信息")
    public void testSaveConfig1(){
        vmc.saveConfig(savePriceMode);
    }

    @Test(description = "保存配置信息")
    public void testSaveConfigInfo(){
        saveConfigInfo = vmc.saveConfigInfo();
        if (saveConfigInfo.get("status").equals("failure")) {
            result.putAll(saveConfigInfo);
        }
    }

    @Test(description = "更新审核状态")
    public void testUpdateAuditStatus(){
        vmc.updateAuditStatus();
    }

    @Test(description = "保存价格配置信息")
    public void testAdmitPriceMode(){
        boolean admitPriceMode = vmc.admitPriceMode();
        if (admitPriceMode == false) {
            result.put("status", "false");
            result.put("error_message", "上报失败");
        }
        else {
            result.put("status", "success");
            result.put("error_message", vmc.standardId + "计价模型创建成功");
            Map<String, String> map = new HashMap<String, String>();
            String assessCode = vmc.getAssessCode();
            map.put("assessCode", assessCode);
            map.put("cityCode", vmc.cityCode);
            map.put("standardID", vmc.standardId);

            JSONObject jsonb = JSONObject.fromObject(map);
            result.put("data", jsonb.toString());
        }
    }

//    @Test(description = "整租计价模型")
//    public void testZZDemo(){
//        System.out.println(viewJson);
//        System.out.println(ehrJson);
//        System.out.println(houseInfo);
//        JSONObject modePriceZZFlow = null;
//
//        modePriceZZFlow = ValuationModelFlow.modePriceZZFlow(ehrJson, houseInfo.getJSONObject("data"),
//                viewJson);
//        System.out.println(modePriceZZFlow.toString());
//
//    }
}
