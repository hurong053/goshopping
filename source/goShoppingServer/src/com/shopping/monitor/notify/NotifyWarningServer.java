package com.shopping.monitor.notify;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotifyWarningServer extends Remote
{
	public void notifyWarning(int type, String msg) throws RemoteException;

}