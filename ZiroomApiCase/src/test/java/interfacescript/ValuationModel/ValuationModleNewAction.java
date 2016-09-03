package interfacescript.ValuationModel;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.appScript.Keeper_ValuationModelCommon;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONArray;
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
public class ValuationModleNewAction {

    private static final Logger logV = Logger.getLogger(ValuationModleNewAction.class);

    Keeper_Me me = new Keeper_Me();
    JSONObject result = new JSONObject();
    Keeper_ValuationModelCommon vmc = new Keeper_ValuationModelCommon();

    JSONObject viewJson;
    JSONObject ehrJson;
    JSONObject houseInfo;
    JSONObject gethouseInfo;
    JSONObject commitHouseInfo;
    JSONObject houseinfo;
    String afterRoom ;
    String assessCode;
    String standardId;
    String standardIdJson;
    JSONObject commitAssessInfo;
    JSONObject getHirePrice;
    JSONObject commitConfigInfo;
    JSONObject commitRoomInfo;
    JSONObject getRentPrice;




    @BeforeClass
    public void init_config() {
        String ViewJson = "{\"usermobile\":\"13810327187\",\"diningRoom\":\"0\",\"afterRoomNum\":\"3\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"1\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"account\":\"20092810\",\"productVesion\":\"1003\",\"district\":\"通州\",\"resblock\":\"格兰晴天\"}";
        viewJson = JSONObject.fromObject(ViewJson);

        String EhrJson = "{\"status\":\"success\",\"error_message\":\"获取管家信息成功\",\"data\":{\"phone\":\"13552169604\",\"centerCode\":\"Z10001\",\"cityCode\":11,\"groupCode\":\"Z30208\",\"emplid\":\"20092810\",\"center\":\"业务拓展中心\",\"email\":\"maj53@ziroom.com\",\"name\":\"马洁\",\"jobCode\":\"100076\",\"dept\":\"学院大区\",\"deptCode\":\"Z20076\",\"group\":\"国展业务组\",\"descr\":\"直收管家\",\"setId\":\"HL011\",\"treePath\":\"Z00001,Z00002,Z10001,Z20076,Z30208\"}}";
        ehrJson = JSONObject.fromObject(EhrJson);

        String houseJson = " {\"data\":{\"unit\":\"4单元\",\"districtName\":\"通州\",\"villageId\":\"1111027374633\",\"districtId\":\"23008625\",\"floor\":\"2\",\"status\":\"success\",\"address\":\"通州格兰晴天14号楼4单元2421\",\"seccondSource\":\"70\",\"roomNum\":\"421\",\"buildNum\":\"14号楼\",\"firstSource\":\"5\",\"villageName\":\"格兰晴天\"},\"error_message\":\"通州格兰晴天14号楼4单元2421房源宝录制成功\",\"status\":\"success\"}";
        houseInfo = JSONObject.fromObject(houseJson);

        vmc.getUserInfo(ehrJson);
        vmc.initViewJson(viewJson);

        houseinfo = houseInfo.getJSONObject("data");

    }

    @Test(description = "管家登录")
    public void testKeeperLogin(){
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


    @Test(description = "M站登录")
    public void testMLogin() {
        boolean mlogin = vmc.mLogin();
        if (mlogin == false) {
            result.put("status", "false");
            result.put("error_message", "登陆失败");
        }
    }

    @Test(description = "得到房源信息")
    public void testGetHouseInfo() {
        gethouseInfo = vmc.houseInfo(houseinfo);
        if (gethouseInfo.get("status").equals("failure")) {
            result.putAll(gethouseInfo);
        }
    }

    @Test(description = "提交房源信息")
    public void testCommitHouseInfo() {
    commitHouseInfo = vmc.commitHouseInfo(houseinfo, gethouseInfo);
		if (commitHouseInfo.get("status").equals("failure")) {
        result.putAll(commitHouseInfo);
        }
    }

    @Test(description = "提交房间信息")
    public void testCommitRoomInfo() {
        standardIdJson = JSONArray.fromObject(gethouseInfo.getString("data")).getString(0);
        standardId = JSONObject.fromObject(standardIdJson).getString("standard_id");

        // 得到房间信息并提交
        afterRoom = vmc.afterRoom;
        for (int i = 0; i < Integer.parseInt(afterRoom); i++) {
            System.out.println(commitHouseInfo+"~~~~~~");//
            getRentPrice = vmc.getRentPrice(commitHouseInfo);
            if (getRentPrice.get("status").equals("failure")) {
                result.putAll(getRentPrice);
            }
            System.out.println("=============================");//
            commitRoomInfo = vmc.commitRoomInfo(getRentPrice, Integer.toString(i + 1));
            if (commitRoomInfo.get("status").equals("failure")) {
                result.putAll(commitRoomInfo);
            }
        }
    }

    @Test(description = "提交公共区域配置")
    public void testCommitConfigInfo() {
        commitConfigInfo = vmc.commitConfigInfo();
        if (commitConfigInfo.get("status").equals("failure")) {
            result.putAll(commitConfigInfo);
        }
    }

    @Test(description = "得到出房价")
    public void testGetHirePrice() {
        getHirePrice = vmc.getHirePrice();
        if (getHirePrice.get("status").equals("failure")) {
            result.putAll(getHirePrice);
        }
    }

    @Test(description = "提交信息")
    public void testCommitAssessInfo() {
        commitAssessInfo = vmc.commitAssessInfo();
        if (commitAssessInfo.get("status").equals("failure")) {
            result.putAll(commitAssessInfo);
        }
        else {
            //更新数据库状态为声和通过
            assessCode = vmc.assessCode;
            vmc.updateAuditStatus();
        }
    }

    @Test(description = "上报评估")
    public void testAssessView() {
        boolean assessView = vmc.assessView();
        if (assessView == false) {
            result.put("status", "false");
            result.put("error_message", "上报失败");
        }
    }

    @Test(description = "保存")
    public void testAdmitAssess() {
        JSONObject admitAssess = vmc.admitAssess();
        if (admitAssess.get("status").equals("failure")) {
            result.putAll(admitAssess);
        }

        result.put("status", "success");
        result.put("error_message", "计价模型创建成功:"+ assessCode + "  房屋编号为:" + standardId);
        Map<String,String> map = new HashMap<String,String>();
        map.put("assessCode", assessCode);
        map.put("cityCode", vmc.cityCode);
        map.put("standardID", standardId);

        JSONObject jsonb = JSONObject.fromObject(map);
        result.put("data", jsonb.toString());
    }



//    整体流程
    @Test(description = "分租计价模型")
    public void testDemo(){
        System.out.println(viewJson);
        System.out.println(ehrJson);
        System.out.println(houseInfo);
        JSONObject modePriceFlow = null;

        modePriceFlow = ValuationModelFlow.modePriceFlow(ehrJson, houseInfo.getJSONObject("data"),
                viewJson);
        System.out.println(modePriceFlow.toString());

    }
}
