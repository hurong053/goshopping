package com.shopping.monitor.notify;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotifyWarningClient extends Remote
{
	/**
	 * @param level
	 *            告警级别
	 * @param msg
	 *            包括了服务名，时间戳，告警原因等信息
	 * @throws RemoteException
	 */
	public void notifyWarning(int type, String msg) throws RemoteException;
}