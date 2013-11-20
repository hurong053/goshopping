package com.shopping.monitor.mbean;

/**
 * 数据服务系统负载情况接口,MBean的实现类需要与接口处于一个包中
 */
public interface DBMonitorMBean
{
	/**
	 * 统计被监控接口1分钟内调用总次数
	 * 
	 * @return 1分钟内调用总次数
	 */
	public int getInterfaceCalledCount();

	/**
	 * 统计被监控接口1分钟内调用平均响应时长
	 * 
	 * @return 平均响应时长，单位：毫秒
	 */
	public int getInterfaceRespAvgTime();

}
