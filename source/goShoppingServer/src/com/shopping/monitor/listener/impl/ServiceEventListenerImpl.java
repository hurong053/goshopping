package com.shopping.monitor.listener.impl;

import org.apache.log4j.Logger;

import com.shopping.monitor.MBeanFactory;
import com.shopping.monitor.listener.ServiceEventListener;
import com.shopping.monitor.utils.Constant;
import com.shopping.monitor.utils.Constant.MonitorType;

public class ServiceEventListenerImpl implements ServiceEventListener
{
	private static Logger logger = Logger
			.getLogger(ServiceEventListenerImpl.class);

	/**
	 * 成功调用上传联系人接口次数
	 */
	private int uploadSuccessCount = 0;

	/**
	 * 失败调用上传联系人接口次数
	 */
	private int uploadFailedCount = 0;

	/**
	 * 调用上传联系人接口响应时间
	 */
	private long uploadRespTime = 0;

	private int downloadSuccessCount = 0;

	private int downloadFailedCount = 0;

	private long downloadRespTime = 0;

	private int dbSuccessCount = 0;

	private int dbFailedCount = 0;

	private int dbRespTime = 0;

	public ServiceEventListenerImpl()
	{
		Counter counter = new Counter();
		Thread thread = new Thread(counter);
		thread.start();
	}

	@Override
	public void onUploadContactsDataEnd(boolean isSuccess, long respTime)
	{
		if (isSuccess)
		{
			uploadSuccessCount++;
			uploadRespTime += respTime;
			logger.debug("uploadSuccessCount ++ ");
		}
		else
		{
			uploadFailedCount++;
			uploadRespTime += respTime;
			logger.debug("uploadFailedCount ++ ");
		}
		logger.debug("respTime " + respTime);
		setRespTimeOutWarning("uploadContactsData", respTime);
	}

	@Override
	public void onDownloadContactsDataEnd(boolean isSuccess, long respTime)
	{
		if (isSuccess)
		{
			downloadSuccessCount++;
			downloadRespTime += respTime;
			logger.debug("downloadSuccessCount ++ ");
		}
		else
		{
			downloadFailedCount++;
			downloadRespTime += respTime;
			logger.debug("downloadFailedCount ++ ");
		}
		logger.debug("respTime " + respTime);
		setRespTimeOutWarning("downloadContactsData", respTime);
	}

	@Override
	public void onDBEnd(boolean isSuccess, long respTime)
	{
		if (isSuccess)
		{
			dbSuccessCount++;
			dbRespTime += respTime;
			logger.debug("dbSuccessCount ++ ");
		}
		else
		{
			dbFailedCount++;
			dbRespTime += respTime;
			logger.debug("dbFailedCount ++ ");
		}
		logger.debug("respTime " + respTime);
		setDBTimeOutWarning("contacts data service:", respTime);
	}

	// /* (non-Javadoc)
	// * @see
	// com.baiku.contacts.monitor.listener.ServiceEventListener#onWarning(com.googlecode.jsendnsca.Level,
	// java.lang.String)
	// * <p>
	// * 通过JSendNSCA API向Nagios主动推送告警信息
	// *
	// */
	// @Override
	// public void onWarning(Level level, String msg)
	// {
	// Properties props = MBeanFactory.getMonitorProps();
	// String nagiosHost = props.getProperty("nagiosHost");
	// int port = Integer.parseInt(props.getProperty("port"));
	// String nscaPassword = props.getProperty("nscaPassword");
	// String hostName = props.getProperty("hostName");
	// String serviceName = props.getProperty("serviceName");
	//
	// // dest
	// NagiosSettings settings = new NagiosSettingsBuilder()
	// .withNagiosHost(nagiosHost).withPort(port)
	// .withEncryption(Encryption.XOR).withPassword(nscaPassword)
	// .create();
	//
	// // source
	// MessagePayload payload = new MessagePayloadBuilder()
	// .withHostname(hostName).withLevel(level)
	// .withServiceName(serviceName).withMessage(msg).create();
	//
	// NagiosPassiveCheckSender sender = new NagiosPassiveCheckSender(settings);
	//
	// try
	// {
	// sender.send(payload);
	// }
	// catch (NagiosException e)
	// {
	// logger.error(e);
	// }
	// catch (IOException e)
	// {
	// logger.error(e);
	// }
	// }

	class Counter implements Runnable
	{

		@Override
		public void run()
		{
			try
			{
				while (true)
				{
					// uploadSuccessCount
					int uploadSC = 0;
					int uploadFC = 0;
					long uploadRT = 0;
					// downloadSuccessCount
					int downloadSC = 0;
					int downloadFC = 0;
					long downloadRT = 0;
					// dbSuccessCount
					int dbSC = 0;
					int dbFC = 0;
					long dbRT = 0;
					long usingTime = 0;
					for (int i = 0; i < 60; i++)
					{
						initParams();
						long startTime = System.currentTimeMillis();
						long endTime = 0;
						if (usingTime < 59 * 1000)
						{
							Thread.sleep(1000);
							endTime = System.currentTimeMillis();
							usingTime += endTime - startTime;
						}
						else
						{
							long sleepTime = 60 * 1000 - usingTime;
							if (sleepTime > 0)
							{
								Thread.sleep(sleepTime);
								endTime = System.currentTimeMillis();
								usingTime += endTime - startTime;
								logger.info("end usingTime:" + usingTime);
							}
							else
							{
								logger.info("goto setParames...");
								// break;
							}
						}
						uploadSC += uploadSuccessCount;
						uploadFC += uploadFailedCount;
						uploadRT += uploadRespTime;
						downloadSC += downloadSuccessCount;
						downloadFC += downloadFailedCount;
						downloadRT += downloadRespTime;
						dbSC += dbSuccessCount;
						dbFC += dbFailedCount;
						dbRT += dbRespTime;
					}
					setMonitoredParams(
							MonitorType.UPLOAD_CONTACTS_DATA_COUNT_IN_MIN,
							uploadSC, uploadFC);
					setMonitoredParams(
							MonitorType.UPLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN,
							uploadSC, uploadFC, uploadRT);
					setMonitoredParams(
							MonitorType.DOWNLOAD_CONTACTS_DATA_COUNT_IN_MIN,
							downloadSC, downloadFC);
					setMonitoredParams(
							MonitorType.DOWNLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN,
							downloadSC, downloadFC, downloadRT);
					setMonitoredParams(MonitorType.DB_COUNT_IN_MIN, dbSC, dbFC);
					setMonitoredParams(MonitorType.DB_RESP_AVG_TIME_IN_MIN,
							dbSC, dbFC, dbRT);
				}
			}
			catch (Exception e)
			{
				logger.error(e);
			}

		}

		private void initParams()
		{
			uploadSuccessCount = 0;
			uploadFailedCount = 0;
			uploadRespTime = 0;
			downloadSuccessCount = 0;
			downloadFailedCount = 0;
			downloadRespTime = 0;
			dbSuccessCount = 0;
			dbFailedCount = 0;
			dbRespTime = 0;
		}

		/**
		 * 设置监控参数
		 * 
		 * @param monitorType
		 *            参数类型
		 * @param CountInMin
		 *            每分钟调用次数
		 * @param toatalRespAvgTime
		 *            每分钟操作响应时间
		 */
		private void setMonitoredParams(MonitorType monitorType,
				int successCountInMin, int failedCountInMin, long totalRespTime)

		{
			int countInMin = successCountInMin + failedCountInMin;
			logger.debug(monitorType.toString() + " countInMin:" + countInMin
					+ " totalRespTime:" + totalRespTime);
			int respAvgTimeInMin = 0;
			setFailedCountsOutOfThreshold(monitorType, successCountInMin,
					failedCountInMin);
			switch (monitorType)
			{
			case UPLOAD_CONTACTS_DATA_COUNT_IN_MIN:
				MBeanFactory.getMonitoredParams().setUploadCountInMin(
						countInMin);
				break;
			case UPLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN:
				if (countInMin == 0)
				{
					MBeanFactory.getMonitoredParams()
							.setUploadRespAvgTimeInMin(respAvgTimeInMin);
				}
				else
				{
					respAvgTimeInMin = (int) (totalRespTime / countInMin);
					MBeanFactory.getMonitoredParams()
							.setUploadRespAvgTimeInMin(respAvgTimeInMin);
				}
				break;
			case DOWNLOAD_CONTACTS_DATA_COUNT_IN_MIN:
				MBeanFactory.getMonitoredParams().setDownloadCountInMin(
						countInMin);
				break;
			case DOWNLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN:
				if (countInMin == 0)
				{
					MBeanFactory.getMonitoredParams()
							.setDownloadRespAvgTimeInMin(respAvgTimeInMin);
				}
				else
				{
					respAvgTimeInMin = (int) (totalRespTime / countInMin);
					MBeanFactory.getMonitoredParams()
							.setDownloadRespAvgTimeInMin(respAvgTimeInMin);
				}
				break;
			case DB_COUNT_IN_MIN:
				MBeanFactory.getMonitoredParams().setDbCountInMin(countInMin);
				break;
			case DB_RESP_AVG_TIME_IN_MIN:
				if (countInMin == 0)
				{
					MBeanFactory.getMonitoredParams().setDbRespAvgTimeInMin(
							respAvgTimeInMin);
				}
				else
				{
					respAvgTimeInMin = (int) (totalRespTime / countInMin);
					MBeanFactory.getMonitoredParams().setDbRespAvgTimeInMin(
							respAvgTimeInMin);
				}
			}

		}

		private void setMonitoredParams(MonitorType monitorType,
				int successCountInMin, int failedCountInMin)
		{
			setMonitoredParams(monitorType, successCountInMin,
					failedCountInMin, 0);
		}
	}

	/**
	 * 服务响应超时告警（接口收到请求，再返回）
	 * 
	 * @param monitorType
	 *            服务类型
	 * @param respTime
	 *            响应时长
	 */
	private void setRespTimeOutWarning(String serviceName, long respTime)
	{
		if (respTime > Constant.Max_Response_Time_Fatal)
		{
			MBeanFactory.getBaikuCommonMonitor().onWarning(serviceName,
					Constant.FATAL, " respTime: " + respTime + " is too long");
		}
	}

	/**
	 * 服务响应超时告警（接口服务与后端服务）
	 * 
	 * @param monitorType
	 *            服务类型
	 * @param respTime
	 *            响应时长
	 */
	private void setDBTimeOutWarning(String serviceName, long respTime)
	{
		if (respTime > Constant.Max_DB_Response_Time_Fatal)
		{
			MBeanFactory.getBaikuCommonMonitor().onWarning(serviceName,
					Constant.FATAL, " respTime: " + respTime + " is too long");
		}
	}

	/**
	 * 设置调用失败次数超过阀值告警
	 * 
	 * @param monitorType
	 * @param successCounts
	 * @param failedCounts
	 */
	private void setFailedCountsOutOfThreshold(MonitorType monitorType,
			long successCounts, long failedCounts)
	{
		switch (monitorType)
		{

		case DOWNLOAD_CONTACTS_DATA_COUNT_IN_MIN:
		case DOWNLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN:
		case UPLOAD_CONTACTS_DATA_COUNT_IN_MIN:
		case UPLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN:
		case DB_COUNT_IN_MIN:
		case DB_RESP_AVG_TIME_IN_MIN:
			if (failedCounts > 0)
			{
				float percent = 100 * failedCounts
						/ (successCounts + failedCounts);
				if (percent > 5 && percent < 10)
				{
					MBeanFactory.getBaikuCommonMonitor().onWarning(
							monitorType.toString(),
							Constant.WARNING,
							" failed times:" + failedCounts
									+ " ,failed percent: " + percent);
				}
				if (percent >= 10)
				{
					MBeanFactory.getBaikuCommonMonitor().onWarning(
							monitorType.toString(),
							Constant.FATAL,
							" failed too much, times:" + failedCounts
									+ " ,failed percent:" + percent);
				}
			}
			break;
		default:
			return;
		}
	}
}
