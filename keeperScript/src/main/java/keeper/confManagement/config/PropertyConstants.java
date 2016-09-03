package keeper.confManagement.config;

import com.ziroom.utils.PropertiesUtil;

public class PropertyConstants {

	public static final String INTERFACES_DOMIN = PropertiesUtil.getPropValAsString("/DomainName.properties","INTERFACES_DOMIN");
	public static String CRM_DOMIN = PropertiesUtil.getPropValAsString("/DomainName.properties", "CRM_DOMIN");
	public static String CREDIT_DOMIN = PropertiesUtil.getPropValAsString("/DomainName.properties", "CREDIT_DOMIN");
	public static final String SOLR_DOMAIN = PropertiesUtil.getPropValAsString("/DomainName.properties", "SOLR_DOMAIN");
	
	public static final String paramsConfigPath = "/KeeperParamsConfig.properties";
	public static final String inputOutValueSqlPath = "/InputOutValueSql.properties";

	private static String dnFile = "/DomainName.properties";
	private static String dbFile = "/DataBase.properties";
	/**
	 * 各个系统域名
	 */
	public static String MODEPRICE_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"MODEPRICE_DOMAIN");
	public static String CRM_LOGIN_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"CRM_LOGIN_DOMAIN");
	public static String PHP_CRM_DOMAIN = PropertiesUtil.getPropValAsString(dnFile,"PHP_CRM_DOMAIN");

	//信用系统mysql登录
	public static String CREDIT_DATANAME = PropertiesUtil.getPropValAsString(dbFile,"CREDIT_DATANAME") ;
	// mysql 数据库
	public static String MYSQL_ADDRESS = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_ADDRESS");
	public static String MYSQL_ACCOUNT = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_ACCOUNT");
	public static String MYSQL_APPZIROOM = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_APPZIROOM");
	public static String MYSQL_USERNAME = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_USERNAME");
	public static String MYSQL_PASSWORD = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_PASSWORD");
	public static String MYSQL_PORT = PropertiesUtil.getPropValAsString(dbFile,"MYSQL_PORT");
	public static String ZHUN_MYSQL_ADDRESS = PropertiesUtil.getPropValAsString(dbFile,"ZHUN_MYSQL_ADDRESS");


	// oracle 数据库
	public static String ORACLE_ADDRESS = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_ADDRESS");
	public static String ORACLE_SID = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_SID");
	public static String ORACLE_USERNAME = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_USERNAME");
	public static String ORACLE_PASSWORD = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_PASSWORD");
	public static String ORACLE_PORT = PropertiesUtil.getPropValAsString(dbFile,"ORACLE_PORT");
	public static String ZHUN_ORACLE_ADDRESS = PropertiesUtil.getPropValAsString(dnFile,"ZHUN_ORACLE_ADDRESS");






}
