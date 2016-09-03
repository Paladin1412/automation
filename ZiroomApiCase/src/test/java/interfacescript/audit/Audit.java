package interfacescript.audit;

import keeper.appScript.Keeper_Audit;
import keeper.confManagement.TimedTask.Command;
import keeper.confManagement.commonMethods.KeeperGlobalParas;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

/**
 * Created by wujing on 16/8/31.
 */
public class Audit {

    private static final Logger logA = Logger.getLogger(Audit.class);

    Keeper_Audit au = new Keeper_Audit();

    @BeforeClass
    public void init_config(){
        KeeperGlobalParas.oldContract = "BJZYCW81609010002";
    }


    @Test(description = "跑定时Job1")
    public void testRunTimedJob(){
        Command.execTaskResult(Command.appToCrm);
    }

//    testModifyInfo
    @Test(description = "登录")
    public void testLogin(){

        String responseJson = au.s_LoginKeeper();

        if (responseJson !=null) {
            logA.debug("用例testlogin返回值为" + responseJson);
        } else {
            logA.debug("用例testlogin请求返回值------>>>>为空");
        }
    }
    @Test(description = "登录后页面管家信息")
    public void testError(){

        String responseJson = au.s_error();

        if (responseJson !=null) {
            logA.debug("用例testError返回值为" + responseJson);
        } else {
            logA.debug("用例testError请求返回值------>>>>为空");
        }
    }
}
