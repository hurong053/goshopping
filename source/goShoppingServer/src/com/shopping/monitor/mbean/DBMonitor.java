package com.shopping.monitor.mbean;

import org.apache.log4j.Logger;

import com.shopping.monitor.MBeanFactory;

public class DBMonitor implements DBMonitorMBean
{
	private Logger logger = Logger.getLogger(DBMonitor.class);

	public DBMonitor()
	{
		MBeanFactory.getMBeanAgent().registerMBean(
				"com.baiku.monitor:name=DBMonitor,Type=DBMonitor", this);
		logger.debug("register DBMonitor success");
	}

	@Override
	public int getInterfaceCalledCount()
	{
		try
		{
			int countInMin = MBeanFactory.getMonitoredParams()
					.getDbCountInMin();
			logger.debug("dbCountInMin " + countInMin);
			return countInMin;
		}

		catch (Exception e)
		{
			logger.error("DBMonitor error!", e);
			return 0;
		}
	}

	@Override
	public int getInterfaceRespAvgTime()
	{
		try
		{
			int respAvgTimeInMin = MBeanFactory.getMonitoredParams()
					.getDbRespAvgTimeInMin();
			logger.debug("dbRespAvgTimeInMin" + respAvgTimeInMin);
			return respAvgTimeInMin;
		}

		catch (Exception e)
		{
			logger.error("DBMonitor error!", e);
			return 0;
		}

	}

}
