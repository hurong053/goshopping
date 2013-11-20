package com.shopping.common.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author wangxm
 * @version 0.1, 2012-6-26
 * @Company Channelsoft
 */
public class MD5Util
{
	/**
	 * Constructs the MD5 object and sets the string whose MD5 is to be
	 * computed.
	 * 
	 * @param inStr
	 *            the <code>String</code> whose MD5 is to be computed
	 */
	public MD5Util()
	{
	}

	/**
	 * Computes the MD5 fingerprint of a string.
	 * 
	 * @return the MD5 digest of the input <code>String</code>
	 */
	public static String computeOnce(String str)
	{

		MessageDigest md5 = null;
		try
		{
			md5 = MessageDigest.getInstance("MD5");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			return null;
		}
		// convert input String to a char[]
		// convert that char[] to byte[]
		// get the md5 digest as byte[]
		// bit-wise AND that byte[] with 0xff
		// prepend "0" to the output StringBuffer to make sure that we don't end
		// up with
		// something like "e21ff" instead of "e201ff"

		if (null == str)
		{
			str = "";
		}
		char[] charArray = str.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
		{
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++)
		{
			int val = md5Bytes[i] & 0xff;
			if (val < 16)
			{
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	public static String computeThree(String userName, String password,
			String salt)
	{
		String str1 = computeOnce(userName + ":ChannelSoft:" + password);
		System.out.println(str1);
		String str2 = computeOnce("REGISTER:" + salt);
		System.out.println(str2);
		String str3 = computeOnce(str1 + ":1234567890:" + str2);
		System.out.println(str3);

		return str3;
	}

	public static String getMd5(String password)
	{
		String result = "";
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF-8"));
			byte s[] = md.digest();

			result = new String(Base64.encodeBase64(s));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static String getID(String accountId, String password)
	{
		GregorianCalendar now = new GregorianCalendar();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);

		int pos = accountId.indexOf('@');
		String userId = pos == -1 ? accountId : accountId.substring(0, pos);
		while (userId.length() < 7)
		{
			userId += userId;
		}
		userId = userId.substring(0, 7);

		synchronized (sf)
		{
			String md5Seed = sf.format(now.getTime()) + accountId + password;

			return String.format("%s%02d%s%s%02d%02d%s%s",
					(char) ('A' + month), year - 2000,
					(char) (day > 9 ? 'A' + (day - 10) : '0' + day),
					(char) (hour > 9 ? 'A' + (hour - 10) : '0' + hour), minute,
					second, userId, DigestUtils.md5Hex(md5Seed)
							.substring(8, 24));
		}
	}

	static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

	public static void main(String[] args)
	{
		String userName = "waximi123123@163.com";
		String password = "111111";
		String salt = "sip:10.130.24.83:5068";
		System.out.println(computeOnce(userName + ":ChannelSoft:" + password));

		// System.out.println(computeThree(userName,password,salt));
		// System.out.println(getMd5(password));

		// String uid="18888888888";
		// uid=getMd5(uid);
		// String userId="18888888888";
		// String password="{MD5}"+String.format("ou=%s,ou=%s,o=user",
		// uid.substring(0, 4), uid.substring(0, 3));
		// uid=getID(userId,password);
		// System.out.println(uid);
	}

}
