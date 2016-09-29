package Zo.confManagement.config;

import com.ziroom.utils.PropertiesUtil;

public class ZoHireHousePC {

    public static final String loginFile = "/ZoHireHousePropertyConstants.properties";

    public static final String productIdView = "精装";
    public static final String outHouseProduct = PropertiesUtil.getPropValAsString(loginFile, "hireContract.outHouseProduct");
    public static final String ownershipCode = PropertiesUtil.getPropValAsString(loginFile, "hireContract.ownershipCode");
    public static final String propertyType = PropertiesUtil.getPropValAsString(loginFile, "hireContract.propertyType");
    public static final String isPledge = PropertiesUtil.getPropValAsString(loginFile, "hireContract.isPledge");
    public static final String maxLiving = PropertiesUtil.getPropValAsString(loginFile, "hireContract.maxLiving");
    public static final String supplyHeat = PropertiesUtil.getPropValAsString(loginFile, "hireContract.supplyHeat");
    public static final String bedroomAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.bedroomAmount");
    public static final String parlorAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.parlorAmount");
    public static final String cookroomAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.cookroomAmount");
    public static final String toiletAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.toiletAmount");
    public static final String disposeBedroomAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.disposeBedroomAmount");
    public static final String disposeParlorAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.disposeParlorAmount");
    public static final String disposeCookroomAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.disposeCookroomAmount");
    public static final String disposeToiletAmount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.disposeToiletAmount");
    public static final String customerSource = PropertiesUtil.getPropValAsString(loginFile, "owner.customerSource");
    public static final String hasClient = PropertiesUtil.getPropValAsString(loginFile, "hireContract.hasClient");
    public static final String name = PropertiesUtil.getPropValAsString(loginFile, "hirePeople.name");
    public static final String phone = PropertiesUtil.getPropValAsString(loginFile, "hirePeople.phone");
    public static final String accountHolder = PropertiesUtil.getPropValAsString(loginFile, "hireContract.accountHolder");
    public static final String accountBank = PropertiesUtil.getPropValAsString(loginFile, "hireContract.accountBank");
    public static final String zhiBank = PropertiesUtil.getPropValAsString(loginFile, "hireContract.zhiBank");
    public static final String bankAccount = PropertiesUtil.getPropValAsString(loginFile, "hireContract.bankAccount");
    public static final String isOnline = PropertiesUtil.getPropValAsString(loginFile, "hireContract.isOnline");
    public static final String growthRate = PropertiesUtil.getPropValAsString(loginFile, "vacancys[%d].growthRate");
    public static final String isIncrease = PropertiesUtil.getPropValAsString(loginFile, "hireContract.isIncrease");
    public static final String cableTelevisionFee = PropertiesUtil.getPropValAsString(loginFile, "hireContract.cableTelevisionFee");
    public static final String isHasDeposit = PropertiesUtil.getPropValAsString(loginFile, "hireContract.isHasDeposit");
    public static final String chongwu = PropertiesUtil.getPropValAsString(loginFile, "bzHireContractWhole.chongwu");
    public static final String isResign = PropertiesUtil.getPropValAsString(loginFile, "hireContract.isResign");
    public static final String hireStatusCode = PropertiesUtil.getPropValAsString(loginFile, "hireContract.hireStatusCode");
    public static final String rooms = PropertiesUtil.getPropValAsString(loginFile, "rooms");
    public static final String isClick = PropertiesUtil.getPropValAsString(loginFile, "isClick");
}
