package com.shopping.monitor;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.shopping.monitor.listener.ServiceEventListener;
import com.shopping.monitor.listener.impl.ServiceEventListenerImpl;
import com.shopping.monitor.mbean.DBMonitor;
import com.shopping.monitor.mbean.DBMonitorMBean;
import com.shopping.monitor.mbean.ShoppingCommonMonitor;
import com.shopping.monitor.mbean.ShoppingCommonMonitorMBean;
import com.shopping.monitor.notify.NotifyWarningClient;
import com.shopping.monitor.notify.impl.NotifyWarningClientImpl;
import com.shopping.monitor.utils.MonitoredParams;

/**
 * MBean工厂类
 */
public class MBeanFactory
{
	/*	
	 * 监控代理类
	 *
	*/
	private static final Logger logger = Logger.getLogger(MBeanFactory.class);

	private static class MBeanAgentHolder
	{
		public final static MBeanAgent mbAgent = new MBeanAgent();
	}

	private static class MonitoredParamsHolder
	{
		public final static MonitoredParams mopa = new MonitoredParams();
	}

	private static class ServiceEventListenerHolder
	{
		public final static ServiceEventListener sel = new ServiceEventListenerImpl();
	}

	private static class BaikuCommonMonitorHolder
	{
		public final static ShoppingCommonMonitorMBean bcmh = new ShoppingCommonMonitor();
	}

	private static class DBMonitorMonitorHolder
	{
		public final static DBMonitorMBean db = new DBMonitor();
	}

	private static class MonitorPropertiesHolder
	{
		public static Properties getMonitorProps()
		{
			Properties properties = new Properties();
			try
			{
				properties.load(MonitorPropertiesHolder.class
						.getResourceAsStream("/Monitor.properties"));
			}
			catch (IOException e)
			{
				logger.error("load properties failed.", e);
			}
			return properties;
		}
	}

	private static class NotifyWarningClientHolder
	{

		public static NotifyWarningClient getWarningClient()
		{
			NotifyWarningClient client = null;
			try
			{
				client = new NotifyWarningClientImpl();
			}
			catch (RemoteException e)
			{
				logger.error(e);
			}
			return client;
		}
	}

	public static NotifyWarningClient getNotifyWarningClient()
	{
		return NotifyWarningClientHolder.getWarningClient();
	}

	public static MBeanAgent getMBeanAgent()
	{
		return MBeanAgentHolder.mbAgent;
	}

	public static MonitoredParams getMonitoredParams()
	{
		return MonitoredParamsHolder.mopa;
	}

	public static ServiceEventListener getServiceEventListener()
	{
		return ServiceEventListenerHolder.sel;
	}

	public static ShoppingCommonMonitorMBean getBaikuCommonMonitor()
	{
		return BaikuCommonMonitorHolder.bcmh;
	}

	public static DBMonitorMBean getDBMonitor()
	{
		return DBMonitorMonitorHolder.db;
	}

	public static Properties getMonitorProps()
	{
		return MonitorPropertiesHolder.getMonitorProps();
	}
}
