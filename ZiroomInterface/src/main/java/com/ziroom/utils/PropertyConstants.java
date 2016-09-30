package com.ziroom.utils;

public class PropertyConstants {
	public static String dbFilePath = "/DataBase.properties";

	public static String logFilePath = "/log4j.properties";
	

	/**
	 * 自动化数据库
	 */
	public static String SQL_ADDRESS = PropertiesUtil.getPropValAsString(dbFilePath,"SQL_ADDRESS");
	public static String SQL_PORT = PropertiesUtil.getPropValAsString(dbFilePath,"SQL_PORT");
	public static String SQL_DATABASENAME = PropertiesUtil.getPropValAsString(dbFilePath,"SQL_DATABASENAME");
	public static String SQL_USERNAME = PropertiesUtil.getPropValAsString(dbFilePath,"SQL_USERNAME");
	public static String SQL_PASSWORD = PropertiesUtil.getPropValAsString(dbFilePath,"SQL_PASSWORD");
	public static String SQL_DRIVER = PropertiesUtil.getPropValAsString(dbFilePath,"SQL_DRIVER");
	public static String SQL_JDBC = PropertiesUtil.getPropValAsString(dbFilePath,"SQL_JDBC");
	//public static String TABLENAME_ADDRESSLIST = PropertiesUtil.getPropValAsString("TABLENAME_ADDRESSLIST",dbFilePath);

	/**
	 * 资产数据库
	 */
	public static String ORACLE_ADDRESS = PropertiesUtil.getPropValAsString(dbFilePath,"ORACLE_ADDRESS");
	public static String ORACLE_PORT = PropertiesUtil.getPropValAsString(dbFilePath,"ORACLE_PORT");
	public static String ORACLE_SID = PropertiesUtil.getPropValAsString(dbFilePath,"ORACLE_SID");
	public static String ORACLE_USERNAME = PropertiesUtil.getPropValAsString(dbFilePath,"ORACLE_USERNAME");
	public static String ORACLE_PASSWORD = PropertiesUtil.getPropValAsString(dbFilePath,"ORACLE_PASSWORD");
	public static String ORACLE_DRIVER = PropertiesUtil.getPropValAsString(dbFilePath,"ORACLE_DRIVER");
	public static String ORACLE_JDBC = PropertiesUtil.getPropValAsString(dbFilePath,"ORACLE_JDBC");

}
