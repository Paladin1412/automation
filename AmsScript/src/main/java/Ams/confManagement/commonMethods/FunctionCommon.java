package Ams.confManagement.commonMethods;

import Ams.confManagement.config.PropertyConstants;
import com.ziroom.utils.CommonFunction;
import com.ziroom.utils.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionCommon extends CommonFunction {

    /**
     * 从数据库中获取相应值
     *
     * @param sql : 查询语句
     * @wujing
     */
    private static List<Map<String, String>> getAllData(String sql, String url, String driver, String username,
                                                        String pw) {
        Connection conn = ConnectDatabase.getConnect(driver, url, username, pw);
        Statement stmt = null;
        ResultSet result = null;
        Map<String, String> map;
        List<Map<String, String>> list = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            while (result.next()) {
                map = new HashMap<String, String>();
                ResultSetMetaData rsmd = result.getMetaData();
                int count = rsmd.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    map.put(columnName, result.getString(columnName));
                }
                list.add(map);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        ConnectDatabase.closeAll(result, stmt, conn);
        return list;
    }


    /**
     * 从oracle数据库中获取相应值
     *
     * @param sql : 查询语句
     * @wujing
     */
    public List<Map<String, String>> getAllDataFromOracleDatabase(String sql, boolean flag) {
        String orcacleUrl = null;
        if (flag == true) {
            orcacleUrl = ConnectDatabase.ASSETS_JDBC + ":@" + PropertyConstants.ORACLE_ADDRESS + ":" + PropertyConstants.ORACLE_PORT + ":" + PropertyConstants.ORACLE_SID;
        } else {
            orcacleUrl = ConnectDatabase.ASSETS_JDBC + ":@" + PropertyConstants.ZHUN_MYSQL_ADDRESS + ":" + PropertyConstants.ORACLE_PORT + ":" + PropertyConstants.ORACLE_SID;
        }

        List<Map<String, String>> list = getAllData(sql, orcacleUrl, ConnectDatabase.ASSETS_DRIVER,
                PropertyConstants.ORACLE_USERNAME, PropertyConstants.ORACLE_PASSWORD);
        return list;
    }


    /**
     * 从oracle数据库中获取相应值
     *
     * @param sql : 查询语句
     * @wujing
     */
    public List<Map<String, String>> getAllDataFromOracleData(String sql) {
        List<Map<String, String>> list = getAllDataFromOracleDatabase(sql, true);
        return list;
    }


    /**
     * 替换字符串
     *
     * @wujing
     */
//	public static String replaceString(String repString){
//		System.out.println(repString);
//		if(repString.equals("null")){
//			return "";
//		}
//		return repString;
//	}
    public static String replaceString(String repString) {
        if (repString == null) {
            return "";
        }

        return repString;
    }
}
