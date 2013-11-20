package com.shopping.monitor.notify.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.shopping.monitor.MBeanFactory;
import com.shopping.monitor.notify.NotifyWarningClient;
import com.shopping.monitor.notify.NotifyWarningServer;

public class NotifyWarningClientImpl extends UnicastRemoteObject implements
		NotifyWarningClient
{
	/**
	 * ps：服务器和客户端的接口、接口包名、接口名称、接口内容都必须一样。甚至两者之间传输的实体的包名也必须一致。
	 */
	private static final long serialVersionUID = 1L;

	private final Logger logger = Logger
			.getLogger(NotifyWarningClientImpl.class);

	private NotifyWarningServer server;

	public NotifyWarningClientImpl() throws RemoteException
	{
		try
		{
			Properties props = MBeanFactory.getMonitorProps();
			String rmiUrl = props.getProperty("rmiUrl");
			server = (NotifyWarningServer) Naming.lookup(rmiUrl);
		}
		catch (MalformedURLException e)
		{
			logger.error(e);
		}
		catch (NotBoundException e)
		{
			logger.error(e);
		}
	}

	@Override
	public void notifyWarning(int type, String msg) throws RemoteException
	{
		logger.info("level=" + type + "\tmsg=" + msg);
		// 调用server的notifyWarning方法
		server.notifyWarning(type, msg);
	}
}