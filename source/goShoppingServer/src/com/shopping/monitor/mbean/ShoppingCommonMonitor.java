package com.shopping.monitor.mbean;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.apache.log4j.Logger;

import com.shopping.monitor.MBeanFactory;
import com.shopping.monitor.utils.Constant;
import com.shopping.server.auth.dao.AuthClientDao;
import com.shopping.server.auth.util.BeanFactoryUtil;

public class ShoppingCommonMonitor extends NotificationBroadcasterSupport
		implements ShoppingCommonMonitorMBean
{
	private static Logger logger = Logger.getLogger(ShoppingCommonMonitor.class);

	private AuthClientDao authClientDao;

	private int seq = 0;

	public ShoppingCommonMonitor()
	{
		MBeanFactory
				.getMBeanAgent()
				.registerMBean(
						"com.baiku.monitor:name=BaikuCommonMonitor,Type=BaikuCommonMonitor",
						this);
		logger.debug("register BaikuCommonMonitor success");
	}

	/* (non-Javadoc)
	 * @see
	 com.baiku.contacts.monitor.listener.ServiceEventListener#onWarning(com.googlecode.jsendnsca.Level,
	 java.lang.String)
	 * <p>
	 * 通知异常情况告警信息，主动向外广播
	 * 告警信息展示格式为
	 * 级别; 告警时间; 告警ip; 服务类型; 其他信息
	 *
	 */
	@Override
	public void onWarning(String serviceName, int type, String e)
	{
		// SimpleDateFormat sdf = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		// Date date = new Date();
		// String time = sdf.format(date);
		InetAddress addr;
		String ip = "";
		try
		{
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
		}
		catch (UnknownHostException e1)
		{
			logger.error(e1);
		}
		String msg = ip + "; " + serviceName + "; " + e;

		// 创建一个信息包
		Notification notice = new Notification(
		// 给这个Notification起个名称
				Constant.getWarningValue(type),
				// 由谁发出的Notification
				this,
				// 一系列通知中的序列号,可以设任意数值
				++seq,
				// 发出时间
				System.currentTimeMillis(),
				// 发出的消息文本
				msg);
		// 发出去
		sendNotification(notice);
	}

	@Override
	public String ping()
	{
		String pingRedis = pingRedis();
		return pingRedis;
	}

	private String pingCouchbase()
	{
		logger.debug("ping couchbase...");
		// ping redis
		authClientDao = (AuthClientDao) BeanFactoryUtil
				.getBean("authClientDaoByCouchbase");
		String ping;
		try
		{
			ping = authClientDao.ping();
			return ping;
		}
		catch (Exception e)
		{
			logger.error("ping couchbase error", e);
			return "couchbase error:" + e.getMessage();
		}
	}

	private String pingRedis()
	{
		logger.debug("ping redis...");
		// ping redis
		authClientDao = (AuthClientDao) BeanFactoryUtil
				.getBean("authClientDao");
		String ping;
		try
		{
			ping = authClientDao.ping();
			if (!ping.equals("PONG"))
			{
				logger.error("ping redis error");
				return "redis error:" + ping;
			}
			logger.info("ping redis normal");
			return "redis normal";
		}
		catch (Exception e)
		{
			logger.error("ping redis error", e);
			return "redis error:" + e.getMessage();
		}
	}

}
