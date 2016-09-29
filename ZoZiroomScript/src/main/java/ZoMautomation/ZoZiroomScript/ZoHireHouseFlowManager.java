package ZoMautomation.ZoZiroomScript;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

/**
 * Created by wujing on 16/9/9.
 */
public class ZoHireHouseFlowManager {
    private static final Logger logV = Logger.getLogger(ZoHireHouseFlowManager.class);

    ZoHireHouseCommon common = new ZoHireHouseCommon();


    JSONObject viewJson;
    JSONObject ehrJson;
    JSONObject houseInfo;
    @BeforeClass
    public void init_config(){

        String ViewJson = "{\"usermobile\":\"13810327187\",\"diningRoom\":\"0\",\"afterRoomNum\":\"3\",\"bedRoom\":\"0\",\"houseLayout\":\"0\",\"citycode\":\"110000\",\"livingRoom\":\"0\",\"point\":\"3\",\"washRoom\":\"0\",\"roomNum\":\"1\",\"houseType\":null,\"bookRoom\":null,\"account\":\"20092810\",\"productVesion\":\"1008\",\"district\":\"通州\",\"resblock\":\"格兰晴天\"}";
        viewJson = JSONObject.fromObject(ViewJson);

        String EhrJson = "{\"center\":\"业务拓展中心\",\"centerCode\":\"Z10001\",\"userCode\":\"20092810\",\"userMobile\":\"13552169604\",\"userName\":\"马洁\",\"userDept\":\"东北大区\",\"groupCode\":\"Z30208\",\"deptCode\":\"Z20081\",\"group\":\"国展业务组\"}";
        ehrJson = JSONObject.fromObject(EhrJson);

        String houseJson = " {\"data\":{\"unit\":\"1单元\",\"districtName\":\"通州\",\"villageId\":\"1111027374633\",\"districtId\":\"23008625\",\"floor\":\"2\",\"status\":\"success\",\"address\":\"通州格兰晴天4号楼1单元2121\",\"seccondSource\":\"14\",\"roomNum\":\"121\",\"buildNum\":\"4号楼\",\"firstSource\":\"6\",\"villageName\":\"格兰晴天\"},\"error_message\":\"通州格兰晴天4号楼1单元2121房源宝录制成功\",\"status\":\"success\"}";
        houseInfo = JSONObject.fromObject(houseJson);
    }

}
