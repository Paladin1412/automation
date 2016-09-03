package keeper.confManagement.commonMethods;

import com.ziroom.utils.ConnectDatabase;
import keeper.confManagement.config.PropertyConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wujing on 16/8/8.
 */
public class RenewFunCommon {


    /**
     * 系统时间和截止日期的时间差
     */
    public static String dateDiff(String date){

        SimpleDateFormat OSD = new SimpleDateFormat("yyyy-MM-dd");
        String systemTime = OSD.format(new Date()).toString();
        Date oldStopDate = null;
        Date currentDate = null;
        try {
            oldStopDate = OSD.parse(date);
            currentDate = OSD.parse(systemTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long between = (oldStopDate.getTime()-currentDate.getTime())/1000;
        long deadlineday = between/(24*3600);
        KeeperGlobalParas.DeadLine = Long.toString(deadlineday);

        return KeeperGlobalParas.DeadLine;

    }

    /**
     * 计算续约结束时间
     */
    public static String calculateRenewEndDate (){
        //转换日期为data类型
        String data = KeeperGlobalParas.renewStartDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //log.info(date1);

        //对日期进行增加一年,减少一天的操作
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.YEAR,1);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        //System.out.println(calendar.getTime());

        //输出新签结束日期,转换为String类型
        KeeperGlobalParas.renewEndDate = sdf.format(calendar.getTime());
        System.out.println(KeeperGlobalParas.renewEndDate +"endDate");

        return KeeperGlobalParas.renewEndDate;
    }


    /**
      * 尾房,一次付清,计算period,返回period值
      */
    public static String renewWfDataDiff(String startdate, String enddate){

        SimpleDateFormat OSD = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = OSD.parse(startdate);
            endDate = OSD.parse(enddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int month;
        month = getMonth(startDate, endDate);

        //对日期进行增加月份的操作和减一天的操作
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH,month);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        //System.out.println(calendar.getTime());
        //计算天数
        Date addDate = calendar.getTime();
        //System.out.println("###addmonth():=" +addDate);

        long between = (endDate.getTime() - addDate.getTime()) / 1000;
        long remain = between / (24 * 3600);
        //计算period值
        float remainDay = remain / 30.0f ;
        float lastPeriod = month + remainDay;
//        System.out.println("###day():=" +remainDay );
//        System.out.println("###day():=" +lastPeriod );
        KeeperGlobalParas.periodday = String.valueOf(lastPeriod);

        return KeeperGlobalParas.periodday;

    }

    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }



    /**
     * 计算首付租金
     */

    /**
     * 付款方式折扣 6(8.5折),12(7折)
     */
    public static void paymentDiscount(){
        int paymentType = Integer.valueOf(KeeperGlobalParas.paymentType) ;
        float period = Float.valueOf(KeeperGlobalParas.periodday);
        if (paymentType == 6){
            KeeperGlobalParas.paymentDiscount = 0.85f;
            System.out.println("paymentDiscount"+KeeperGlobalParas.paymentDiscount);
        }else if (paymentType == 12){
            KeeperGlobalParas.paymentDiscount = 0.7f;
            System.out.println("paymentDiscount"+KeeperGlobalParas.paymentDiscount);
        }else if (paymentType == 99 && period > 6.0 ){
            KeeperGlobalParas.paymentDiscount = 0.7f;
            System.out.println("paymentDiscount"+KeeperGlobalParas.paymentDiscount);
        }else if (paymentType == 99 && period <= 6.0&&period >3.0 ){
            KeeperGlobalParas.paymentDiscount = 0.85f;
            System.out.println("paymentDiscount"+KeeperGlobalParas.paymentDiscount);
        }else {
            KeeperGlobalParas.paymentDiscount = 1;
            System.out.println("paymentDiscount"+KeeperGlobalParas.paymentDiscount);
        }
    }
    /**
     * 连续居住时长折扣1年一下无折扣，1年9折，2年8折，3年及以上7折；短续无居住时长折扣
     *
     */
    public static void liveTimeDiscount(){
        String a =KeeperGlobalParas.liveTime.substring(0,1);
        String renewtype = "dx";
        String renew = KeeperGlobalParas.renewType;
        float liveTime = Float.valueOf(a);
        if(liveTime ==1.0&&renew.equals(renewtype)==false){
            KeeperGlobalParas.liveTimeDiscount = 0.9f;
        }else if(liveTime== 2.0&&renew.equals(renewtype)==false){
            KeeperGlobalParas.liveTimeDiscount = 0.8f;
        }else if (liveTime >= 3.0&&renew.equals(renewtype)==false){
            KeeperGlobalParas.liveTimeDiscount = 0.7f;
        }else {
            KeeperGlobalParas.liveTimeDiscount = 1;
        }
        System.out.println("liveTimeDiscount"+KeeperGlobalParas.liveTimeDiscount);
    }
    /**
     * 提前续约折扣  1-（原合同到期日期－续约生成支付日期）X0.01
     */
    public static void earlyRenewDiscount(){
        dateDiff(KeeperGlobalParas.oldStopDate);
        float earlyRenew = Float.valueOf(KeeperGlobalParas.DeadLine);
        float discount = 1-  earlyRenew* 0.01f;
//        System.out.println(discount);
        if (discount < 0.7){
            KeeperGlobalParas.earlyRenewDiscount =  0.7f;
        }else {
            KeeperGlobalParas.earlyRenewDiscount = discount;
        }
        System.out.println(KeeperGlobalParas.earlyRenewDiscount);

    }

    /**
     * 计算金额 分为1,3,6,12 和 99 两种情况
     */
    public static void calculateCountPrice (String period) {

        float periodDay = Float.valueOf(period);
        //System.out.println(periodDay);
        if (periodDay == 1.0 ){
            //float desposit = Float.valueOf(GlobalParameter.deposit);
            float monthPrice = Float.valueOf(KeeperGlobalParas.monthPrice);
            float countPrice = monthPrice * periodDay;
            int a =Math.round(countPrice);
            KeeperGlobalParas.testCountPrice = String.valueOf(a);
            //计算总金额
            float per = Float.valueOf(KeeperGlobalParas.periodday);
            float countTotalPrice = monthPrice * per;
            KeeperGlobalParas.testTotalCountPrice = String.valueOf(countTotalPrice);
        }else if (periodDay == 3.0 ||periodDay == 6.0 ||periodDay == 12.0 ){
            float monthPrice = Float.valueOf(KeeperGlobalParas.monthPrice);
            float countPrice = monthPrice * periodDay;
            int a =Math.round(countPrice);
            KeeperGlobalParas.testCountPrice = String.valueOf(a);
            //计算总金额
            float per = Float.valueOf(KeeperGlobalParas.periodday);
            float countTotalPrice = monthPrice * per;
            KeeperGlobalParas.testTotalCountPrice = String.valueOf(countTotalPrice);
        }else {
            float monthPrice = Float.valueOf(KeeperGlobalParas.monthPrice);
            float countPrice = monthPrice * periodDay;
            int a =Math.round(countPrice);
            KeeperGlobalParas.testCountPrice = String.valueOf(a);
            KeeperGlobalParas.testTotalCountPrice =String.valueOf(countPrice);
        }
    }

    /**
     * 计算服务费
     */
    public static void calculateCommission(){
        //计算付款折扣
        paymentDiscount();

        //计算居住时长折扣
        liveTimeDiscount();

        //提前续约折扣
        earlyRenewDiscount();

        float countPrice = Float.valueOf(KeeperGlobalParas.testTotalCountPrice);
        float commission = countPrice * 0.1f * KeeperGlobalParas.paymentDiscount * KeeperGlobalParas.earlyRenewDiscount * KeeperGlobalParas.liveTimeDiscount;
        int tCommission = Math.round(commission);
        KeeperGlobalParas.testCommission = String.valueOf(tCommission);
        System.out.println(KeeperGlobalParas.testCommission);
        System.out.println(KeeperGlobalParas.testTotalCountPrice);

    }
    /**
     * 计算总金额
     */
    public static void calculateCountMoney(){
        int a = Integer.valueOf(KeeperGlobalParas.testCountPrice);
        int b = Integer.valueOf(KeeperGlobalParas.testCommission);
        int count = a + b;
        KeeperGlobalParas.testCountMoney = String.valueOf(count);
    }


    /**
     * 更新mysql数据库中数据
     *
     * @paramString
     *            sql : 查询语句
     * @Elaine
     */
    public static void updateMysqlData(String sql) {
        String mysqlUrl = ConnectDatabase.JDBC + PropertyConstants.MYSQL_ADDRESS + ":" + PropertyConstants.MYSQL_PORT
                + "/" + PropertyConstants.CREDIT_DATANAME;
        updateData(ConnectDatabase.DRIVER, mysqlUrl, PropertyConstants.MYSQL_USERNAME, PropertyConstants.MYSQL_PASSWORD,
                sql);
    }
    /**
     * 查询mysql数据库中数据
     *
     * @paramString
     *            sql : 查询语句
     * @Elaine
     */
    public static void selectMysqlData(String sql) {
        String mysqlUrl = ConnectDatabase.JDBC + PropertyConstants.MYSQL_ADDRESS + ":" + PropertyConstants.MYSQL_PORT
                + "/" + PropertyConstants.CREDIT_DATANAME;
        selectData(ConnectDatabase.DRIVER, mysqlUrl, PropertyConstants.MYSQL_USERNAME, PropertyConstants.MYSQL_PASSWORD,
                sql);
    }
    /**
     * 查询数据库
     *
     * @paramString
     *            sql : 查询语句
     * @Wujing
     */
    private static void selectData(String driver, String url, String username, String pw, String sql) {
        Connection conn = ConnectDatabase.getConnect(driver, url, username, pw);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int col = result.getMetaData().getColumnCount();
            System.out.println("============================");
            while (result.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(result.getString(i) + "\t");
                    if ((i == 2) && (result.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                KeeperGlobalParas.uid = result.getString("uid");
//                System.out.println(KeeperGlobalParas.uid);
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {

            e.printStackTrace();
        }

        ConnectDatabase.closeAll(rs, stmt, conn);
    }
    /**
     * 获取用户的uid
     */
    public static  void  selectUserInfo() {

        //获取KeeperGlobalParas.uid在数据库中的值
        String selectsql = "select * from tb_user_identity where phone = '" + KeeperGlobalParas.customerPhone + "'";
        RenewFunCommon.selectMysqlData(selectsql);
    }
    /**
     * 更新数据库
     *
     * @paramString
     *            sql : 查询语句
     * @Elaine
     */
    private static void updateData(String driver, String url, String username, String pw, String sql) {
        Connection conn = ConnectDatabase.getConnect(driver, url, username, pw);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            if (result > 0) {
                System.out.println("操作成功");
            } else {
                System.out.println("操作失败");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        ConnectDatabase.closeAll(rs, stmt, conn);
    }
}
