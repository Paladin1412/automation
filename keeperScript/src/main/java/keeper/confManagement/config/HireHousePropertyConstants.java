package keeper.confManagement.config;

import com.ziroom.utils.PropertiesUtil;

public class HireHousePropertyConstants {
public static final String hireFile = "/HireHousePropertyConstants.properties";
	
	public static final String q = PropertiesUtil.getPropValAsString(hireFile,"q");
	public static final String rows = PropertiesUtil.getPropValAsString(hireFile,"rows");
	public static final String fl = PropertiesUtil.getPropValAsString(hireFile,"fl");
	public static final String wt = PropertiesUtil.getPropValAsString(hireFile,"wt");
	public static final String indent = PropertiesUtil.getPropValAsString(hireFile,"indent");
	
	public static final String districtId = PropertiesUtil.getPropValAsString(hireFile,"districtId");
	public static final String resblockId = PropertiesUtil.getPropValAsString(hireFile,"resblockId");
	public static final String boType = PropertiesUtil.getPropValAsString(hireFile,"boType");
	public static final String isTopFloor = PropertiesUtil.getPropValAsString(hireFile,"isTopFloor");
	public static final String timeBucket = PropertiesUtil.getPropValAsString(hireFile,"timeBucket");
	public static final String pageNum = PropertiesUtil.getPropValAsString(hireFile,"pageNum");
	public static final String pageSize = PropertiesUtil.getPropValAsString(hireFile,"pageSize");
	public static final String trackState = PropertiesUtil.getPropValAsString(hireFile,"trackState");
    public static final String trackResult = PropertiesUtil.getPropValAsString(hireFile,"trackResult");
    public static final String state = PropertiesUtil.getPropValAsString(hireFile,"state");
    public static final String decorateType = PropertiesUtil.getPropValAsString(hireFile,"decorateType");
	public static final String area = PropertiesUtil.getPropValAsString(hireFile,"area");
    public static final String toilet = PropertiesUtil.getPropValAsString(hireFile,"toilet");
    public static final String ownerName = PropertiesUtil.getPropValAsString(hireFile,"ownerName");
}
