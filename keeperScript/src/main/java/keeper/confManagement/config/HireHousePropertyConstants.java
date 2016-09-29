package keeper.confManagement.config;

import com.ziroom.utils.PropertiesUtil;

public class HireHousePropertyConstants {
public static final String hireFile = "/HireHousePropertyConstants.properties";
	public static final String isTopFloor = PropertiesUtil.getPropValAsString(hireFile,"isTopFloor");

}
