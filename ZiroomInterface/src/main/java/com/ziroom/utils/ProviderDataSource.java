package com.ziroom.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProviderDataSource {

	/***
	 * 获取查询table的行或者列
	 * mysql
	 * @param sql
	 * @return
	 */
	public static int getColOrRow(String sql) {
		int value = 0;
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.DRIVER, com.ziroom.utils.ConnectDatabase.mysqlUrl,
				com.ziroom.utils.ConnectDatabase.USER, com.ziroom.utils.ConnectDatabase.PWD);
		PreparedStatement pre = null;
		ResultSet result = null;
		try {
			pre = conn.prepareStatement(sql);
			result = pre.executeQuery();

			while (result.next()) {

				value = Integer.parseInt(result.getString(1));

				result.close();
				conn.close();

				return value;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return value;

	}

	/***
	 * 获取数据库中某一条数据某个字段数据
	 * mysql
	 * @param sql
	 * @return 返回一个字符串
	 */
	public static String getDataString(String sql) {
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.DRIVER, com.ziroom.utils.ConnectDatabase.mysqlUrl,
				com.ziroom.utils.ConnectDatabase.USER, com.ziroom.utils.ConnectDatabase.PWD);
		PreparedStatement pre = null;
		ResultSet result = null;
		try {
			pre = conn.prepareStatement(sql);
			result = pre.executeQuery();
			while (result.next()) {

				return result.getString(1);
			}
			result.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return "";
	}

	/***
	 *  获取数据库中某一条数据某个字段数据
	 *  oracle
	 * @param sql
	 * @return
	 */
	public static String getDataByOracle(String OracleUrl,String user,String pwd,String sql)
	{
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.ASSETS_DRIVER, OracleUrl,
				user, pwd);
		PreparedStatement pre = null;
		ResultSet result = null;
		try {
			pre = conn.prepareStatement(sql);
			result = pre.executeQuery();
			while (result.next()) {

				return result.getString(1);
			}
			result.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return "";

	}

	public static boolean updateDataByOracle(String OracleUrl,String user,String pwd,String sql)
	{
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.ASSETS_DRIVER, OracleUrl,
				user, pwd);
		PreparedStatement pre = null;
		try {
			pre = conn.prepareStatement(sql);
			pre.executeUpdate(sql);

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return true;
	}

	/***
	 *
	 * 读取数据库表格到二维数组中
	 *
	 * @param selectSql
	 *            查询数据库的表SQL语句
	 * @param rowSql
	 *            查询表中的行数为数组创建行
	 * @param colSql
	 *            查询表中的列数 为数组创建列
	 * @author jihaibo
	 * @return
	 */
	public static String[][] getDataTable(String selectSql, String rowSql, String colSql) {
		int row = getColOrRow(rowSql);
		int col = getColOrRow(colSql);

		String[][] strArry = new String[row][col];
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.DRIVER, com.ziroom.utils.ConnectDatabase.mysqlUrl,
				com.ziroom.utils.ConnectDatabase.USER, com.ziroom.utils.ConnectDatabase.PWD);

		PreparedStatement pre = null;
		ResultSet result = null;
		int arrRow = 0;
		try {
			pre = conn.prepareStatement(selectSql);
			result = pre.executeQuery();

			while (result.next()) {

				for (int i = 0; i < col; i++) {

					strArry[arrRow][i] = result.getString(i + 1);

				}
				arrRow++;

			}
			result.close();
			conn.close();
			return strArry;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return strArry;
	}

	/**
	 * 获取资产数据中某一列的数据
	 *
	 * @param
	 *            column : 资产表中的列值
	 * @author Elaine
	 *
	 */
	public List<String> getDataFromOracle(String sql, String column) {
		List<String> dataList = new ArrayList<String>();
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.ASSETS_DRIVER, com.ziroom.utils.ConnectDatabase.orcacleUrl,
				com.ziroom.utils.ConnectDatabase.ASSETS_USER, com.ziroom.utils.ConnectDatabase.ASSETS_PWD);
		Statement stmt = null;
		ResultSet result = null;

		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				String rs = result.getString(column);
				dataList.add(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		com.ziroom.utils.ConnectDatabase.closeAll(result, stmt, conn);

		return dataList;
	}

	/**
	 * 获取资产数据中的数据
	 *
	 * @param
	 *            sql : 查询语句
	 * @Elaine
	 */
	public static Map<String, String> getAllDataFromOracle(String sql) {
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.ASSETS_DRIVER, com.ziroom.utils.ConnectDatabase.orcacleUrl,
				com.ziroom.utils.ConnectDatabase.ASSETS_USER, com.ziroom.utils.ConnectDatabase.ASSETS_PWD);
		Statement stmt = null;
		ResultSet result = null;
		Map<String, String> map = new HashMap<String, String>();

		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			while (result.next()) {
				ResultSetMetaData rsmd = result.getMetaData();
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					String columnName = rsmd.getColumnName(i);
					map.put(columnName, result.getString(columnName));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		com.ziroom.utils.ConnectDatabase.closeAll(result, stmt, conn);
		return map;
	}

	/**
	 * 获取资产数据中的数据
	 *
	 * @param
	 *            sql : 查询语句
	 * @Elaine
	 */
	public static List<Map<String, String>> getAllDataFromOracleData(String sql) {
		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.ASSETS_DRIVER, com.ziroom.utils.ConnectDatabase.orcacleUrl,
				com.ziroom.utils.ConnectDatabase.ASSETS_USER, com.ziroom.utils.ConnectDatabase.ASSETS_PWD);
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

		com.ziroom.utils.ConnectDatabase.closeAll(result, stmt, conn);
		return list;
	}

	/***
	 * 读取数据库查看表数据结构类型
	 *
	 * @param dbName
	 * @param tableName
	 * @author jihaibo
	 * @return 返回一个hashmap
	 */
	private HashMap<String, String> getDbFiledType(String dbName, String tableName) {

		HashMap<String, String> hMap = new HashMap<String, String>();

		String sql = "SELECT  COLUMN_NAME ,DATA_TYPE   FROM information_schema.COLUMNS  where  TABLE_SCHEMA = '"
				+ dbName + "'  and TABLE_NAME = '" + tableName + "'";

		Connection conn = com.ziroom.utils.ConnectDatabase.getConnect(com.ziroom.utils.ConnectDatabase.DRIVER, com.ziroom.utils.ConnectDatabase.mysqlUrl,
				com.ziroom.utils.ConnectDatabase.USER, ConnectDatabase.PWD);
		PreparedStatement pre = null;
		ResultSet result = null;
		try {
			pre = conn.prepareStatement(sql);
			result = pre.executeQuery();
			while (result.next()) {
				hMap.put(result.getString(1), result.getString(2));
			}
			
			result.close();
			
			conn.close();	
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return hMap;
	}
	
}
