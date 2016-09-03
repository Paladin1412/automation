package keeper.appScript;

import keeper.confManagement.commonMethods.HttpRequest;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by wujing on 16/8/31.
 */
public class Keeper_Audit {

    private static final Logger logA = Logger.getLogger(Keeper_Audit.class);
    HttpRequest hRequest = new HttpRequest();

    /***
     * 修改管家信息
     * 登录
     */
    public String s_LoginKeeper() {

        String loginUrl = "http://login.ziroom.com/login.php?user_account=20245865&city=bj";


        String htmlStr = hRequest.getGetStrReturn(loginUrl).get("returnValue");
        String htmlStr1 = hRequest.getGetStrReturn(loginUrl).get("returnStatusCode");
        System.out.println(htmlStr);
        if (htmlStr.equals("")) {
            logA.info("s_LoginKeeper服务器返回值------>>>>>为空");

        } else {
            logA.info("s_LoginKeeper返回值" + htmlStr);
        }
        return htmlStr;
    }

    /**
     *
     * 登录进入修改管家
     */
    public String s_error() {

        String loginUrl = "http://s.ziroom.com/index.php?uri=renew/newsign_list";

        String htmlStr = hRequest.getGetStrReturn(loginUrl).get("returnValue");
        String htmlStr1 = hRequest.getGetStrReturn(loginUrl).get("returnStatusCode");
//        System.out.println(htmlStr);

        Document doc = Jsoup.parse(htmlStr);

        String content = doc.select("#z_ListTable2_BJZYCW81609010002.z_ListTable2 tbody tr td p a").get(0).attr("href");

        System.out.println(content);



        if (htmlStr.equals("")) {
            logA.info("s_error服务器返回值------>>>>>为空");
        } else {
//            logA.info("s_error返回值" + htmlStr);
        }
        return htmlStr;
    }


}
