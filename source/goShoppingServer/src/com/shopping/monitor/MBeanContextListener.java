package com.shopping.monitor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 初始化MBean对象
 */
public class MBeanContextListener implements ServletContextListener
{
	private final Logger logger = LoggerFactory
			.getLogger(MBeanContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * 用于实例化MBean对象,例如调用MBeanFactory.getUploadContactsDataMonitor();
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		try
		{
			MBeanFactory.getBaikuCommonMonitor();
			MBeanFactory.getDBMonitor();
		}
		catch (Exception e)
		{
			logger.error("初始化MBean出现异常", e);
		}

	}
}