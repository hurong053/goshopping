package com.shopping.server.auth.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shopping.server.auth.datasource.JedisTemplate;

public class BeanFactoryUtil implements ServletContextListener
{

	private static final Log logger = LogFactory.getLog(BeanFactoryUtil.class);

	private static boolean isTestCode = false;

	private static ApplicationContext context;

	/**
	 * 测试类使用bean工厂是直接Set
	 */
	private static ConfigurableListableBeanFactory factory;

	/**
	 * 直接初始化BeanFactory
	 * 
	 * @param context
	 */
	public static void setBeanFactory(ConfigurableListableBeanFactory context)
	{
		isTestCode = true;
		BeanFactoryUtil.factory = context;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{

	}

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		logger.info("初始化BeanFactory....");
		context = WebApplicationContextUtils.getWebApplicationContext(event
				.getServletContext());
		logger.info("初始化BeanFactory....OK.");
	}

	/**
	 * 获取Spring中的Bean
	 * 
	 * @param beanName
	 *            Bean的名称
	 * @return 如果成功则反回Bean对象，如果失败则抛出异常.
	 */
	public static Object getBean(String beanName) throws BeansException
	{
		if (context == null && factory == null)
		{
			logger.warn("ApplicationContext 没有初始化！您无法获得业务处理对象，系统无法正常运行");
			return null;
		}
		if (isTestCode)
		{
			return factory.getBean(beanName);
		}
		return context.getBean(beanName);
	}

	public static JedisTemplate getJedisTemplate()
	{
		if (context == null && factory == null)
		{
			logger.warn("ApplicationContext 没有初始化！您无法获得业务处理对象，系统无法正常运行");
			return null;
		}
		if (isTestCode)
		{
			return (JedisTemplate) factory.getBean("jedisTemplate");
		}
		return (JedisTemplate) context.getBean("jedisTemplate");
	}
}
