package ziroom.confManagement.config;

import com.ziroom.utils.PropertiesUtil;

public class PropertyConstants {

	
	public static String R_CRM_DOMIN = PropertiesUtil.getPropValAsString("/R_DomainName.properties", "CRM_DOMIN");
	
	/***
	public static final String INTERFACES_DOMIN = PropertiesUtil.getPropValAsString("/R_DomainName.properties", "INTERFACES_DOMIN");
	public static String INTERFACES_ZZ_DOMIN=PropertiesUtil.getPropValAsString("/R_DomainName.properties", "INTERFACES_ZZ_DOMIN");
	public static String ES_DOMIN=PropertiesUtil.getPropValAsString("/R_DomainName.properties", "ES_DOMIN");
	// 输入输出值的properties文件路径
	public static final String inputOutValueSqlPath = "/R_InputOutValueSql.properties";
	* 
	*/
	// 参数配置文件路径paramsconfig
	public static final String paramsConfigPath = "/R_ParamsConfig.properties";

	
}
