package config;

import com.ziroom.utils.PropertiesUtil;

public class PropertyConstants {

	public static final String INTERFACES_DOMIN = PropertiesUtil.getPropValAsString("/DomainName.properties", "INTERFACES_DOMIN");
	public static String CRM_DOMIN = PropertiesUtil.getPropValAsString("/DomainName.properties", "CRM_DOMIN");
	public static String INTERFACES_ZZ_DOMIN= PropertiesUtil.getPropValAsString("/DomainName.properties", "INTERFACES_ZZ_DOMIN");
	public static String ES_DOMIN= PropertiesUtil.getPropValAsString("/DomainName.properties", "ES_DOMIN");
	public static String CREDIT_DOMIN= PropertiesUtil.getPropValAsString("/DomainName.properties", "CREDIT_DOMIN");
	public static String CONFIG_DOMAIN= PropertiesUtil.getPropValAsString("/DomainName.properties", "CONFIG_DOMAIN");
	
	// 输入输出值的properties文件路径
	public static final String inputOutValueSqlPath = "/InputOutValueSql.properties";
	// 参数配置文件路径paramsconfig
	public static final String paramsConfigPath = "/ParamsConfig.properties";
}
