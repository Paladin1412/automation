package interfacescript.ValuationModel;

import com.ziroom.utils.PropertiesUtil;
import com.ziroom.utils.ProviderDataSource;
import keeper.appScript.Keeper_Me;
import keeper.confManagement.config.PropertyConstants;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by wujing on 16/9/1.
 */
public class ValuationModleAction {

    private static final Logger logV = Logger.getLogger(ValuationModleAction.class);

    Keeper_Me me = new Keeper_Me();

    JSONObject viewJson;
    JSONObject ehrJson;
    JSONObject houseInfo;
    @BeforeClass
    public void init_config() {
        String ViewJson = "{\"usermobile\":\"13810327187\",\"diningRoom\":\"0\",\"afterRoomNum\":\"3\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"1\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"account\":\"20092810\",\"productVesion\":\"1003\",\"district\":\"通州\",\"resblock\":\"格兰晴天\"}";
        viewJson = JSONObject.fromObject(ViewJson);

        String EhrJson = "{\"status\":\"success\",\"error_message\":\"获取管家信息成功\",\"data\":{\"phone\":\"13552169604\",\"centerCode\":\"Z10001\",\"cityCode\":11,\"groupCode\":\"Z30208\",\"emplid\":\"20092810\",\"center\":\"业务拓展中心\",\"email\":\"maj53@ziroom.com\",\"name\":\"马洁\",\"jobCode\":\"100076\",\"dept\":\"学院大区\",\"deptCode\":\"Z20076\",\"group\":\"国展业务组\",\"descr\":\"直收管家\",\"setId\":\"HL011\",\"treePath\":\"Z00001,Z00002,Z10001,Z20076,Z30208\"}}";
        ehrJson = JSONObject.fromObject(EhrJson);

        String houseinfo = " {\"data\":{\"unit\":\"4单元\",\"districtName\":\"通州\",\"villageId\":\"1111027374633\",\"districtId\":\"23008625\",\"floor\":\"2\",\"status\":\"success\",\"address\":\"通州格兰晴天14号楼4单元2421\",\"seccondSource\":\"70\",\"roomNum\":\"421\",\"buildNum\":\"14号楼\",\"firstSource\":\"5\",\"villageName\":\"格兰晴天\"},\"error_message\":\"通州格兰晴天14号楼4单元2421房源宝录制成功\",\"status\":\"success\"}";
        houseInfo = JSONObject.fromObject(houseinfo);

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
