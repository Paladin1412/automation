package keeper.confManagement.TimedTask;

public class Command {
	public static String appToCrm = "/usr/local/php/bin/php -f /home/www/www.ziroom.com/cron/app_contractInfoToCrm.php";
	public static String appRenewToSave = "/usr/local/php/bin/php -f /home/www/www.ziroom.com/cron/app_renewContractSave.php";
	public static String saveContract = "/usr/local/php/bin/php -f /home/www/s.ziroom.com/cron/contract/saveContract.php";
	public static String insertReceipt ="/usr/local/php/bin/php -f /home/www/s.ziroom.com/cron/contract/insertReceipt.php";
	
	public static String zchToweb = "/usr/local/php/bin/php -f /home/www/www.ziroom.com/cron/zch_to_web.php";
	public static String crmToApp = "/usr/local/php/bin/php -f /home/www/www.ziroom.com/cron/app_CRMStateToApp.php";
	public static String payPlan ="/usr/local/php/bin/php -f /home/www/www.ziroom.com/cron/relet_pay_plan_status.php";
	public static String delYcz = "/usr/local/php/bin/php -f /home/www/www.ziroom.com/cron/del_ycz_house.php";
	// 测试环境
	public static String test_ip = "10.16.24.174";
	public static String username = "root";
	public static String test_pwd = "ziroom";
	
//	// 准生产环境
//	public final String ip = "10.16.24.30";
//	public final String pwd = "ziroom1018";
	
	public static String execTaskResult(String command){
		String exec = null;

		exec = SSHHelper.exec(test_ip, username, test_pwd, 22, command);

		return exec;
	}
	
}
