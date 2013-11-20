/**
 * 
 */
package com.shopping.server.web.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.shopping.server.auth.util.AuthClientUtil;

/**
 * 启动监听线程，如果配置文件内容发生改变，则替换之前的文件
 * 
 * @author zhangxuhua
 */
public class PropertiesWatcher implements Runnable
{

	private static Logger logger = Logger.getLogger(PropertiesWatcher.class);

	private long modifyTime;

	private File file;

	private boolean quit = false;

	private String propsName = "ContactsAuth.properties";

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */

	/**
	 * 初始化的时候，加载propsName配置文件
	 */
	public PropertiesWatcher()
	{
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		try
		{
			path = URLDecoder.decode(path, "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error(e);
		}
		file = new File(path + "/" + propsName);
		setFile(file);
	}

	@Override
	public void run()
	{
		while (!quit)
		{
			synchronized (this)
			{
				if (file == null)
				{
					waitForFileReady();
				}
				if (modifyTime != file.lastModified())
				{
					logger.info("properties has been changed.");
					fileReload();
					modifyTime = file.lastModified();
				}
			}
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				logger.error(e);
			}
		}
	}

	/**
	 * 如果文件被set进去了，则调用通知方法
	 * 
	 * @param file
	 */
	private synchronized void setFile(File file)
	{
		this.file = file;
		if (file != null)
		{
			modifyTime = file.lastModified();
			notify();
		}
	}

	/**
	 * 如果文件是空的，则一直等待
	 */
	private void waitForFileReady()
	{
		try
		{
			wait();
		}
		catch (InterruptedException e)
		{
			logger.error("waitting for file ready failed.", e);
		}
	}

	/**
	 * 重新加载配置文件
	 */
	private void fileReload()
	{
		try
		{
			AuthClientUtil.getInstance();
			Properties props = AuthClientUtil.getInstance()
					.getDynamicProperties();
			AuthClientUtil.getInstance().setProperties(props);
			logger.info("reload properties succeed.");
		}
		catch (Exception e)
		{
			logger.error("load properties failed.", e);
		}
	}
}
