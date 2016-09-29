package keeper.confManagement.commonMethods;

import com.ziroom.utils.CommonFunction;
import com.ziroom.utils.ConnectDatabase;
import keeper.confManagement.config.PropertyConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionCommon extends CommonFunction {

	/**
	 * 从数据库中获取相应值
	 *
	 *            sql : 查询语句
	 * @Elaine
	 */
	private static List<Map<String, String>> getAllData(String sql, String url, String driver, String username,
			String pw) {
		Connection conn = ConnectDatabase.getConnect(driver, url, username, pw);
		Statement stmt = null;
		ResultSet result = null;
		Map<String, String> map;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

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
	 * 从mysql数据库中获取相应值
	 *
	 *            sql : 查询语句
	 * @Elaine
	 */
	public List<Map<String, String>> getAllDataFromMySqlData(String sql, String dataName) {
		List<Map<String, String>> list = getAllDataFromMySqlDatabase(sql, dataName, true);
		return list;
	}

	/**
	 * 从mysql数据库中获取相应值
	 *
	 *            sql : 查询语句
	 * @Elaine
	 */
	public List<Map<String, String>> getAllDataFromMySqlDatabase(String sql, String dataName, boolean flag) {
		String mysqlUrl = null;
		if(flag == true){
		  mysqlUrl = ConnectDatabase.JDBC + PropertyConstants.MYSQL_ADDRESS + ":" + PropertyConstants.MYSQL_PORT
				+ "/" + dataName + "?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";
		}else{
			 mysqlUrl = ConnectDatabase.JDBC + PropertyConstants.ZHUN_MYSQL_ADDRESS + ":" + PropertyConstants.MYSQL_PORT
						+ "/" + dataName + "?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";
		}
		List<Map<String, String>> list = getAllData(sql, mysqlUrl, ConnectDatabase.DRIVER,
				PropertyConstants.MYSQL_USERNAME, PropertyConstants.MYSQL_PASSWORD);
		return list;
	}

	/**
	 * 从oracle数据库中获取相应值
	 *
	 *            sql : 查询语句
	 * @Elaine
	 */
	public List<Map<String, String>> getAllDataFromOracleDatabase(String sql, boolean flag) {
		String orcacleUrl = null;
		if(flag == true){
			orcacleUrl = ConnectDatabase.ASSETS_JDBC + ":@" + PropertyConstants.ORACLE_ADDRESS + ":" + PropertyConstants.ORACLE_PORT + ":" + PropertyConstants.ORACLE_SID;
		}else{
			orcacleUrl = ConnectDatabase.ASSETS_JDBC + ":@" + PropertyConstants.ZHUN_MYSQL_ADDRESS + ":" + PropertyConstants.ORACLE_PORT + ":" + PropertyConstants.ORACLE_SID;
		}

		List<Map<String, String>> list = getAllData(sql, orcacleUrl, ConnectDatabase.ASSETS_DRIVER,
				PropertyConstants.ORACLE_USERNAME, PropertyConstants.ORACLE_PASSWORD);
		return list;
	}


	/**
	 * 更新数据库
	 *
	 * @param
	 *            sql : 查询语句
	 * @Elaine
	 */
	private void updateData(String driver, String url, String username, String pw, String sql) {
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

	/**
	 * 更新mysql数据库中数据
	 *
	 *            sql : 查询语句
	 * @Elaine
	 */
	public void updateMySqlDatabase(String sql, String dataName, boolean flag) {
		String mysqlUrl = "";
		if(flag == true){
			mysqlUrl = ConnectDatabase.JDBC + PropertyConstants.MYSQL_ADDRESS + ":" + PropertyConstants.MYSQL_PORT + "/" + dataName;

		}else{
			mysqlUrl = ConnectDatabase.JDBC + PropertyConstants.ZHUN_MYSQL_ADDRESS + ":" + PropertyConstants.MYSQL_PORT + "/" + dataName;
		}
		System.out.println("地址"+mysqlUrl);
		updateData(ConnectDatabase.DRIVER, mysqlUrl, PropertyConstants.MYSQL_USERNAME, PropertyConstants.MYSQL_PASSWORD,
				sql);
	}

	/**
	 * 更新mysql数据库中数据
	 *
	 *            sql : 查询语句
	 * @Elaine
	 */
	public void updateMySqlData(String sql, String dataName) {
		updateMySqlDatabase(sql,dataName, true);
	}


}
