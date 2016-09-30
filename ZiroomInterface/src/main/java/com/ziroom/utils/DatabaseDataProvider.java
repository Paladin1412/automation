package com.ziroom.utils;

import org.testng.annotations.DataProvider;

/**
 * 
 * @author jihaibo
 * @Date 2016-4-15
 *
 */

public class DatabaseDataProvider {

	@DataProvider(name = "data")
	public static Object[][] verifData(String[][] strArry) {
		return strArry;
	}

}
