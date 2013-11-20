package com.shopping.monitor;

import java.util.ArrayList;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取MBean服务
 */
public class MBeanAgent
{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static MBeanServer server;

	private ArrayList<MBeanServer> mbservers;

	/**
	 * <p>
	 * 获取Tomcat的JMXConnectorServer<blockquote>
	 */
	public MBeanAgent()
	{
		init();
	}

	private void init()
	{
		logger.debug("初始化MBean服务,启动JMXConnectorServer");
		ArrayList<MBeanServer> mbservers = MBeanServerFactory
				.findMBeanServer(null);
		this.mbservers = mbservers;
		logger.debug("初始化MBean代理 success");
	}

	private MBeanServer getServer()
	{
		logger.debug("getMBeanServer");
		MBeanServer mbserver = null;

		if (mbservers.size() > 0)
		{
			mbserver = mbservers.get(0);
		}

		if (mbserver != null)
		{
			logger.debug("Found our MBean server");
		}
		else
		{
			logger.info("createMBeanServer");
			mbserver = MBeanServerFactory.createMBeanServer();
			mbservers.add(mbserver);
		}
		return mbserver;
	}

	/**
	 * 注册MBean对象
	 * 
	 * @param name
	 *            MBean对象名
	 * @param obj
	 *            MBean对象
	 */
	public void registerMBean(String name, Object obj)
	{
		try
		{
			ObjectName objName;
			objName = new ObjectName(name);
			MBeanServer mbserver = getServer();
			mbserver.registerMBean(obj, objName);
			logger.debug(name + " success");
		}
		catch (Exception e)
		{
			logger.error("when register MBean " + name, e);
		}

	}

}
