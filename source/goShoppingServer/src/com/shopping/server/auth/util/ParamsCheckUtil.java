package com.shopping.server.auth.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

/**
 * @author zhangxuhua
 */
public class ParamsCheckUtil
{
	private static Logger logger = Logger.getLogger(ParamsCheckUtil.class);

	/**
	 * 校验是否符合json数组格式
	 * 
	 * @param jsonStr
	 * @return 数组，失败返回null
	 */
	private static List<String> checkArray(String jsonStr)
	{
		try
		{
			if (jsonStr == null || jsonStr.isEmpty())
			{
				return null;
			}
			List<String> list = new ArrayList<String>();
			list = JSON.parseArray(jsonStr, String.class);
			return list;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 服务类型
	 */
	public enum ServiceType
	{
		LOGIN, LOGOUT, QUERYUSERINFO
	}

	/**
	 * 邮箱校验
	 * 
	 * @param email
	 * @return true,校验成功；false,失败
	 */
	public static boolean emailFormat(String email)
	{
		return isEmail(email);
	}

	/**
	 * 固定电话、手机校验
	 * 
	 * @param phone
	 * @return true,校验成功；false,失败
	 */
	public static boolean phoneFormat(String phone)
	{
		if (isPhone(phone) || isMobileNO(phone))
		{
			return true;
		}
		return false;

	}

	public static boolean isEmail(String email)
	{
		if (!StringUtils.isEmpty(email))
		{
			/*			Pattern p = Pattern
								.compile("^[a-zA-Z0-9][\\w\\.]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
			
			Pattern p = Pattern
					.compile("^[a-z0-9][a-z0-9._-]*@[a-z0-9_-]+(\\.[a-z0-9_-]+)+$");
			*/
			Pattern p = Pattern
					.compile("^[a-z0-9][a-z0-9_-]*@[a-z0-9_-]+(\\.[a-z0-9_-]+)+$");
			Matcher m = p.matcher(email);
			return m.matches();
		}
		return false;
	}

	public static boolean isPhone(String phone)
	{
		if (!StringUtils.isEmpty(phone))
		{
			Pattern p = Pattern.compile("^((\\+86)|(86)|(0086))?((("
					+ "(010|021|022|023|024|025|026|027|028|029)?\\d{8})" + "|"
					+ "((0[3-9][1-9]{2})\\d{7,8})))$");
			Matcher m = p.matcher(phone);
			return m.matches();
		}
		return false;
	}

	public static boolean isMobileNO(String mobiles)
	{
		if (!StringUtils.isEmpty(mobiles))
		{
			Pattern p = Pattern
					.compile("^((\\+86)|(86)|(0086))?(1[3-8]\\d{9})$");

			Matcher m = p.matcher(mobiles);
			return m.matches();
		}
		return false;

	}

	/**
	 * 从右截取字符串
	 * 
	 * @author: llk
	 * @CreateTime: 2011-10-21
	 * @param source
	 * @param num
	 *            截取几位
	 * @return
	 * @Return: String
	 */
	public static String rightSubstring(String source, int num)
	{
		String snew = StringUtils.trimToEmpty(source);
		if (snew == null)
		{
			return "";
		}
		else if (snew.length() > num)
		{
			return snew.substring(snew.length() - num, snew.length());
		}
		else
		{
			return snew;
		}
	}

	public static void main(String[] args)
	{
		// System.out.println("email");
		// System.out.println(isEmail("123@123.com"));
		// System.out.println(isEmail("aaa_123@123.tv.com"));
		// System.out.println(isEmail("11@123.com"));
		// System.out.println(isEmail("_zhaosj@channelsoft.com"));
		System.out.println("phone");
		System.out.println(isPhone("008601012345678"));
		System.out.println(isPhone("12345678"));
		// System.out.println(isPhone("010-12341234"));
		// System.out.println(isPhone("010-1234-1234"));
		System.out.println("false:");
		System.out.println(isPhone("2345678"));
		System.out.println(isPhone("-01012341234"));
		System.out.println(isPhone("010-12341234-"));
		System.out.println(isPhone("0-10-12341234"));
		// System.out.println("moblie");
		// System.out.println(isMobileNO("13221112111"));
		// System.out.println(isMobileNO("8613211121112"));
		// System.out.println(isMobileNO("+8613211121112"));
		// System.out.println("false:");
		// System.out.println(isMobileNO("013211121112"));
		// System.out.println(isMobileNO("10211121112"));
		// System.out.println(isMobileNO("01102111211121"));
		// System.out.println(isMobileNO("132211121111"));

	}
}