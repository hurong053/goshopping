package com.shopping.monitor.mbean;

/**
 * 通用监控MBean，对外提供探测接口,MBean的实现类需要与接口处于一个包中
 */
public interface ShoppingCommonMonitorMBean
{
	/**
	 * 探测接口--探测系统中主要模块是否能够正常运行，判断系统服务是否正常
	 * 
	 * @return 返回系统服务运行信息。 成功，返回null;失败,返回具体失败信息
	 */
	public String ping();

	/**
	 * 通知异常情况告警信息，主动向外广播
	 * 
	 * @param serviceName
	 * @param type
	 * @param e
	 */
	public void onWarning(String serviceName, int type, String e);

}
