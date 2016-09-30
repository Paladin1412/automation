package com.ziroom.utils;


import java.sql.*;

/**
 * 
 * @author 程程
 * @Date 2015-11-6
 *
 */
public class ConnectDatabase {

	// Mysql 自动化数据库
	public static String DRIVER = PropertyConstants.SQL_DRIVER;
	public static String JDBC = PropertyConstants.SQL_JDBC;
	public static String ADDRESS = PropertyConstants.SQL_ADDRESS;
	public static String PORT = PropertyConstants.SQL_PORT;
	public static String DATABASENAME = PropertyConstants.SQL_DATABASENAME;
	public static String USER = PropertyConstants.SQL_USERNAME;
	public static String PWD = PropertyConstants.SQL_PASSWORD;

	// 资产数据库
	public static String ASSETS_DRIVER = PropertyConstants.ORACLE_DRIVER;
	public static String ASSETS_JDBC = PropertyConstants.ORACLE_JDBC;
	public static String ASSETS_ADDRESS = PropertyConstants.ORACLE_ADDRESS;
	public static String ASSETS_PORT = PropertyConstants.ORACLE_PORT;
	public static String ASSETS_SID = PropertyConstants.ORACLE_SID;
	public static String ASSETS_USER = PropertyConstants.ORACLE_USERNAME;
	public static String ASSETS_PWD = PropertyConstants.ORACLE_PASSWORD;

	// 地址拼接
	public static String mysqlUrl = JDBC + ADDRESS + ":" + PORT + "/" + DATABASENAME;
	public static String orcacleUrl = ASSETS_JDBC + ":@" + ASSETS_ADDRESS + ":" + ASSETS_PORT + ":" + ASSETS_SID;

	/**
	 * 连接Mysql数据库
	 * 
	 */
	public static Connection getConnect(String driver, String url, String userName, String password) {

		Connection conn = null;

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		try {

			conn = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 关闭数据库
	 * 
	 */
	public static void closeAll(ResultSet rs, Statement stat, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		if (stat != null)
			try {
				stat.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 将String转化成JSon对象(此方法针对入参) eg: contractCode=BJWZ11111111&cityCode=11
	 * 变成:{"contractCode":"BJWZ11111111", "cityCode":"11"}
	 * 
	 * @param
	 *            str : 入参值
	 * 
	 */
	public String stringToJson(String str) {
		String firstStr = str.replace("&", ",").replace("=", ":");

		String[] firStr = firstStr.split(",");
		StringBuffer sb = new StringBuffer();
		String rl = null;
		for (String item : firStr) {
			String[] SecStr = item.split(":");
			for (int i = 0; i < SecStr.length - 1; i++) {
				String rs = "\"" + SecStr[i] + "\":" + "" + "\"" + SecStr[i + 1] + "\"";
				sb.append(rs);
			}

			rl = "{" + sb.toString().replace("\"\"", "\",\"") + "}";
		}

		return rl;
	}

}
