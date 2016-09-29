package keeper.confManagement.config;

import com.ziroom.utils.PropertiesUtil;

public class ValuationModelPropertyConstants {
public static final String appFile = "/ValuationModelPropertyConstants.properties";
	
	public static final String login = PropertiesUtil.getPropValAsString(appFile,"login");
	public static final String houseInfo = PropertiesUtil.getPropValAsString(appFile,"houseInfo");
	public static final String type = PropertiesUtil.getPropValAsString(appFile,"type");
	public static final String commithouseInfo = PropertiesUtil.getPropValAsString(appFile,"commithouseInfo");
	public static final String getRentPrice = PropertiesUtil.getPropValAsString(appFile,"getRentPrice");
	public static final String decorateType = PropertiesUtil.getPropValAsString(appFile,"decorate_type");
	public static final String saloonArea = PropertiesUtil.getPropValAsString(appFile,"saloon_area");
	public static final String roomArea = PropertiesUtil.getPropValAsString(appFile,"room_area");
	public static final String orientation = PropertiesUtil.getPropValAsString(appFile,"orientation");
//	public static final String orientation = new String(PropertiesUtil.getPropValAsString(appFile,"orientation").getBytes("gbk"), "utf-8");

	public static final String toliet = PropertiesUtil.getPropValAsString(appFile,"toliet");
	public static final String balcony = PropertiesUtil.getPropValAsString(appFile,"balcony");
	public static final String isShelter = PropertiesUtil.getPropValAsString(appFile,"isShelter");
	
	public static final String roomInfo = PropertiesUtil.getPropValAsString(appFile,"roomInfo");
	public static final String isNewRoom = PropertiesUtil.getPropValAsString(appFile,"is_new_room");
	public static final String yg = PropertiesUtil.getPropValAsString(appFile,"yg");
	public static final String shzh = PropertiesUtil.getPropValAsString(appFile,"shzh");
	public static final String shf = PropertiesUtil.getPropValAsString(appFile,"shf");
	public static final String kt = PropertiesUtil.getPropValAsString(appFile,"kt");
	public static final String mch1_1 = PropertiesUtil.getPropValAsString(appFile,"1_1mch");
	public static final String mchd1_1 = PropertiesUtil.getPropValAsString(appFile,"1_1mchd");
	public static final String mch1_5 = PropertiesUtil.getPropValAsString(appFile,"1_5mch");
	public static final String mchd1_5 = PropertiesUtil.getPropValAsString(appFile,"1_5mchd");
	public static final String mch1_8 = PropertiesUtil.getPropValAsString(appFile,"1_8mch");
	public static final String mchd1_8 = PropertiesUtil.getPropValAsString(appFile,"1_8mchd");
	
	public static final String configInfo = PropertiesUtil.getPropValAsString(appFile,"configInfo");
	public static final String bx = PropertiesUtil.getPropValAsString(appFile,"bx");
	public static final String xyj = PropertiesUtil.getPropValAsString(appFile,"xyj");
	public static final String rshq = PropertiesUtil.getPropValAsString(appFile,"rshq");
	public static final String rshq60 = PropertiesUtil.getPropValAsString(appFile,"rshq");
	public static final String wbl = PropertiesUtil.getPropValAsString(appFile,"wbl");
	public static final String scsjj = PropertiesUtil.getPropValAsString(appFile,"scsjj");
	public static final String yj = PropertiesUtil.getPropValAsString(appFile,"yj");
	public static final String czh = PropertiesUtil.getPropValAsString(appFile,"czh");
	public static final String dzs = PropertiesUtil.getPropValAsString(appFile,"dzs");
	public static final String zj = PropertiesUtil.getPropValAsString(appFile,"zj");
	public static final String gdq = PropertiesUtil.getPropValAsString(appFile,"gdq");
	public static final String shq = PropertiesUtil.getPropValAsString(appFile,"shq");
	public static final String db = PropertiesUtil.getPropValAsString(appFile,"db");
	public static final String zhtchg = PropertiesUtil.getPropValAsString(appFile,"zhtchg");
	public static final String qtpzhchb = PropertiesUtil.getPropValAsString(appFile,"qtpzhchb");
	public static final String qtzhxchb = PropertiesUtil.getPropValAsString(appFile,"qtzhxchb");
	public static final String dlgz = PropertiesUtil.getPropValAsString(appFile,"dlgz");
	public static final String shlgz = PropertiesUtil.getPropValAsString(appFile,"shlgz");
	public static final String wshm = PropertiesUtil.getPropValAsString(appFile,"wshm");
	public static final String chfczh = PropertiesUtil.getPropValAsString(appFile,"chfczh");
	public static final String sgch = PropertiesUtil.getPropValAsString(appFile,"sgch");
	public static final String diaoding = PropertiesUtil.getPropValAsString(appFile,"diaoding");
	
	public static final String getHirePrice = PropertiesUtil.getPropValAsString(appFile,"getHirePrice");
	public static final String decorateYear = PropertiesUtil.getPropValAsString(appFile,"decorate_year");
	public static final String leaseYear = PropertiesUtil.getPropValAsString(appFile,"lease_year");
	public static final String ownerPayment = PropertiesUtil.getPropValAsString(appFile,"owner_payment");
	public static final String payment = PropertiesUtil.getPropValAsString(appFile,"payment");
	public static final String fund = PropertiesUtil.getPropValAsString(appFile,"fund");
	
	public static final String assessResult = PropertiesUtil.getPropValAsString(appFile,"assessResult");
	public static final String assessInfo = PropertiesUtil.getPropValAsString(appFile,"assessInfo");
	public static final String op = PropertiesUtil.getPropValAsString(appFile,"op");
	public static final String assessView = PropertiesUtil.getPropValAsString(appFile,"assessView");
	public static final String admitAssess = PropertiesUtil.getPropValAsString(appFile,"admitAssess");

	//===============================================整租======================================================
	// 收房价格
	public static final String uri = PropertiesUtil.getPropValAsString(appFile,"uri");
	public static final String entire_type = PropertiesUtil.getPropValAsString(appFile,"hire_price_type");
	public static final String house_type = PropertiesUtil.getPropValAsString(appFile,"house_type");
	public static final String real_rent_price = PropertiesUtil.getPropValAsString(appFile,"real_rent_price");
	// 保存
	public static final String save_type = PropertiesUtil.getPropValAsString(appFile,"save_type");
	public static final String info_type = PropertiesUtil.getPropValAsString(appFile,"info_type");
	public static final String lease_month = PropertiesUtil.getPropValAsString(appFile,"lease_month");
	// 配置标准
	public static final String config_level = PropertiesUtil.getPropValAsString(appFile,"config_level");
    // 改后户型
	public static final String after_saloon = PropertiesUtil.getPropValAsString(appFile,"after_saloon");
	public static final String after_kitchen = PropertiesUtil.getPropValAsString(appFile,"after_kitchen");
	public static final String after_toliet = PropertiesUtil.getPropValAsString(appFile,"after_toliet");
    // 风格
	public static final String house_style = PropertiesUtil.getPropValAsString(appFile,"house_style");
	// 改前户型
	public static final String pre_house_type = PropertiesUtil.getPropValAsString(appFile,"pre_house_type");
	// 空置期
	public static final String vanancyday = PropertiesUtil.getPropValAsString(appFile,"vanancyday");
    // room参数
	public static final String room_toliet = PropertiesUtil.getPropValAsString(appFile,"room_toliet");
    public static final String room_balcony = PropertiesUtil.getPropValAsString(appFile,"room_balcony");
    public static final String room_is_new = PropertiesUtil.getPropValAsString(appFile,"room_is_new");
    public static final String room_type = PropertiesUtil.getPropValAsString(appFile,"room_type");
    // 得到standardid
    public static final String dict_uri = PropertiesUtil.getPropValAsString(appFile,"dict_uri");
    public static final String dict_op = PropertiesUtil.getPropValAsString(appFile,"dict_op");
    public static final String dict_type = PropertiesUtil.getPropValAsString(appFile,"dict_type");

    public static final String apiUri = PropertiesUtil.getPropValAsString(appFile,"apiUri");
    public static final String config_info_type = PropertiesUtil.getPropValAsString(appFile,"config_info_type");
   
    // 保存config
    public static final String sums = PropertiesUtil.getPropValAsString(appFile,"sums");
    public static final String unit_price = PropertiesUtil.getPropValAsString(appFile,"unit_price");
    public static final String payment_times = PropertiesUtil.getPropValAsString(appFile,"payment_times");
 
}
