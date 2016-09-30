package com.ziroom.utils;

import com.ziroom.tech.scaffold.boot.util.security.MD5Util;
import net.sf.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CommonFunction {

	/****
	 * md5加密子方法
	 * 
	 * @param bytes
	 * @param separator
	 * @return
	 * @author jihaibo
	 */
	public static String toHexString(byte[] bytes, String separator) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xFF & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex).append(separator);
		}
		return hexString.toString();
	}

	/***
	 * md5加密
	 * 
	 * @param bytes
	 * @return
	 * @author jihaibo
	 */

	public static String toMd5(byte[] bytes) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(bytes);
			return toHexString(algorithm.digest(), "");
		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e);
		}
	}

	/***
	 * 封装算法返回sign值
	 * 
	 * @param
	 * @return string
	 * 
	 * @author jihaibo
	 */
	public static String getAppSign(String uid, String timeStamp) {
		// String key = "7srzT88FcNiRQA3n";
		// String sign = toMd5((initUid + timeStamp() + key).getBytes());
		String key = "7srzT88FcNiRQA3n";
		if (uid == null || uid.length() < 1) {
			uid = "0";
		}
		String sign = toMd5((uid + timeStamp + key).getBytes());
		return sign;
	}
	
	 

	/***
	 * 10位时间戳
	 * 
	 * @return
	 * 
	 * @author jihaibo
	 */
	public static String getTimeStampOf10() {
		// String time = String.valueOf(System.currentTimeMillis());
		// String timestamp = time.substring(0, 11);
		Date date = new Date();
		String timestamp = String.valueOf(date.getTime()).substring(0, 10);
		return timestamp;
	}

	/***
	 * 13位时间戳
	 * 
	 * @author jihaibo
	 * @return
	 */
	public static String getTimeStampOf13() {
		Date date = new Date();
		String timestamp = String.valueOf(date.getTime()).substring(0, 13);
		return timestamp;
	}

	/****
	 * 生成CRM接口需要的MD5校验码
	 * 
	 * @return
	 */
	public static String getCrmSign(Map<String, String> map) {
		Map<String, Object> m = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			Object obj = map.get(key);
			m.put(key, obj);
		}
		String sign = CrmToMD5(m);
		return sign;

	}

	/**
	 * 生成CRM接口需要的MD5校验码
	 *
	 * @param map
	 *            需排序的Map
	 * @return 生成的校验码
	 */
	public static String CrmToMD5(Map<String, Object> map) {
		StringBuffer signCode = new StringBuffer();
		String[] keys = new String[map.size()];
		map.keySet().toArray(keys);

		String temp;
		for (int i = 0; i < keys.length; ++i) {
			for (int j = 0; j < keys.length - i - 1; ++j) {
				if (keys[j].compareTo(keys[j + 1]) > 0) {
					temp = keys[j];
					keys[j] = keys[j + 1];
					keys[j + 1] = temp;
				}
			}
		}

		for (int i = 0; i < map.size(); i++) {
			if (map.get(keys[i]) != null) {
				signCode.append(keys[i] + "=" + map.get(keys[i]));
			}
		}
		// MD5加盐
		signCode.append("7srzT88FcNiRQA3n"); 
		 
		// MD5加密
		String sign = MD5Util.md5Hex(signCode.toString());
		return sign;
	}
	
	/***
	 * 整租sign算法
	 * 请求参数排序并且参数后面加&符合
	 * @param map
	 * @return
	 */
	public static String GetZzSign(Map<String, String> map) {
		StringBuffer signCode = new StringBuffer();
		String[] keys = new String[map.size()];
		map.keySet().toArray(keys);

		String temp;
		for (int i = 0; i < keys.length; ++i) {
			for (int j = 0; j < keys.length - i - 1; ++j) {
				if (keys[j].compareTo(keys[j + 1]) > 0) {
					temp = keys[j];
					keys[j] = keys[j + 1];
					keys[j + 1] = temp;
				}
			}
		}

		for (int i = 0; i < map.size(); i++) {
			if (map.get(keys[i]) != null) {
				if (i==map.size()-1) {
					signCode.append(keys[i] + "=" + map.get(keys[i]));
				}
				else {
					signCode.append(keys[i] + "=" + map.get(keys[i])+"&");
				}
			}
		}
		// MD5加盐
		signCode.append("b29df42e480325d2e8fb6fa17fba037f"); 
		 
		// MD5加密
		String sign = MD5Util.md5Hex(signCode.toString());
		return sign;
	}
	
	/****
	 * 单一json嵌套字段排序（按照abcdefghij.....）
	 * @param jObject
	 * @return
	 */
	public static String jsonFieldSort(JSONObject jObject) {
		StringBuffer strMap = new StringBuffer();
		String[] keys = new String[jObject.size()];
		Iterator<?> iterator = jObject.keys();
		int y = 0;
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = jObject.getString(key);
			keys[y] = '"' + key + '"' + ":" + '"' + value + '"';
			y++;
		}
		String temp;
		for (int i = 0; i < keys.length; ++i) {
			for (int j = 0; j < keys.length - i - 1; ++j) {
				if (keys[j].compareTo(keys[j + 1]) > 0) {
					temp = keys[j];
					keys[j] = keys[j + 1];
					keys[j + 1] = temp;
				}
			}
		}
		strMap.append("{");
		for (int i = 0; i < jObject.size(); i++) {
			if (i == jObject.size() - 1) {

				strMap.append(keys[i].toString() + "}");
			} else {

				strMap.append(keys[i].toString() + ",");
			}
		}
		return strMap.toString();
	}
	
	
	
	 
	
	/***
	 * {"name":1,"user":2},{"name":3,"user":4},{"name":5,"user":5}
	 * 
	 * @param jsonStr
	 * @author jihaibo
	 * @return
	 */
	public static ArrayList<String> stringToList(String jsonStr) {
		ArrayList<String> arrylist = new ArrayList<String>();
		String[] arr = jsonStr.split("}");

		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				arrylist.add(arr[i] + "}");
			} else {
				arrylist.add(arr[i].substring(1, arr[i].length()) + "}");
			}
		}
		return arrylist;
	}
	
	/****
	 * 将map参数转化地址形式
	 * 例如：
	 * @param map
	 * @return  例如：city_code=110000&sign_open=1&network=WIFI&ip=192.168.1.124&
	 */
	public static String spliceUrl(Map<String, String> map)
	{
		StringBuffer sBufferUrl=new StringBuffer();
		for(Map.Entry<String, String> m:map.entrySet())
		{
			sBufferUrl.append(m.getKey()+"="+m.getValue()+"&");
		}
		
		return sBufferUrl.toString();
	}

}
