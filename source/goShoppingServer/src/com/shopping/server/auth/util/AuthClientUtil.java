/**
 * 
 */
package com.shopping.server.auth.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 通讯录服务工具类
 * 
 * @author zhangxuhua
 */
public class AuthClientUtil
{
	private static Logger logger = Logger.getLogger(AuthClientUtil.class);

	private Properties properties;

	private static class AuthClientUtilHolder
	{
		private final static AuthClientUtil instance = new AuthClientUtil();
	}

	public static AuthClientUtil getInstance()
	{
		return AuthClientUtilHolder.instance;
	}

	/**
	 * 判断seesion是否为空，若为空返回true，否则返回false
	 * 
	 * @param request
	 * @return
	 */
	public boolean isUserIdEmpty(String userId)
	{
		if (StringUtils.isEmpty(userId))
		{
			return true;
		}
		return false;
	}

	/**
	 * 判断accessToken是否为空，如果为空则返回true，否则返回false
	 * 
	 * @param request
	 * @return
	 */
	public boolean isAccessTokenEmpty(String accessToken)
	{
		if (StringUtils.isEmpty(accessToken))
		{
			return true;
		}
		return false;
	}

	/**
	 * 读取资源文件
	 * 
	 * @return
	 */
	public Properties getProperties()
	{
		if (properties == null)
		{
			synchronized (AuthClientUtil.class)
			{
				properties = new Properties();
				try
				{
					properties.load(this.getClass().getResourceAsStream(
							"/ContactsAuth.properties"));
				}
				catch (IOException e)
				{
					logger.error("load properties failed.", e);
				}
			}

		}
		return properties;
	}

	/**
	 * 在文件发生改变之后动态读取资源文件
	 * 
	 * @return
	 */
	public Properties getDynamicProperties()
	{

		Properties props = null;
		InputStream in = null;
		try
		{
			// 获取绝对路径，解决动态加载properties
			String path = AuthClientUtil.class.getClassLoader().getResource("")
					.getPath();
			path = URLDecoder.decode(path, "utf-8");
			in = new FileInputStream(path + "/ContactsAuth.properties");
			props = new Properties();
			props.load(in);
			in.close();
		}
		catch (Exception e)
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException e1)
			{
				logger.error("close inputstream of loading properties failed.",
						e1);
			}
			logger.error("load properties failed.", e);
		}
		return props;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties pros)
	{
		this.properties = pros;
		ParamsUtil.getInstance().setPropertiesParams(pros);
	}

	/**
	 * 定义用户未在用户中心登录，返回的json数据
	 * 
	 * @param otherStrs
	 *            若有多个参数，则中间用";"隔开
	 * @return
	 */
	public JSONObject getNotLoginJson(String accessToken, String otherStrs)
	{
		JSONObject json = new JSONObject();
		if (isAccessTokenEmpty(accessToken))
		{
			logger.info("用户未登录");
			json.put("status", "-2001");
			json.put("message", "用户未登录");
		}
		else
		{
			logger.info("该accessToken无效");
			json.put("status", "-2002");
			json.put("message", "该accessToken无效");
		}
		if (null != otherStrs && !"".equals(otherStrs))
		{
			String[] arr = otherStrs.split(";");
			for (String str : arr)
			{
				if (!"".equals(str))
				{
					json.put(str, "");
				}
			}
		}
		return json;
	}

}
