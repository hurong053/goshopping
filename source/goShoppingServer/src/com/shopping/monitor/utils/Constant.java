package com.shopping.monitor.utils;

/**
 * 常量类
 * <p>
 * 枚举监控指标项
 */
public class Constant
{
	public static int WARNING = 0;

	public static int FATAL = 1;

	/**
	 * 监控指标类型
	 */
	public enum MonitorType
	{
		DOWNLOAD_CONTACTS_DATA_COUNT_IN_MIN, DOWNLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN, UPLOAD_CONTACTS_DATA_COUNT_IN_MIN, UPLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN, DB_COUNT_IN_MIN, DB_RESP_AVG_TIME_IN_MIN;

		@Override
		public String toString()
		{
			String rtString = "";
			switch (this)
			{
			case DOWNLOAD_CONTACTS_DATA_COUNT_IN_MIN:
				rtString = "downloadContactsData每分钟次数";
				break;
			case DOWNLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN:
				rtString = "downloadContactsData每分钟平均响应时间";
				break;
			case UPLOAD_CONTACTS_DATA_COUNT_IN_MIN:
				rtString = "uploadContactsData每分钟次数";
				break;
			case UPLOAD_CONTACTS_DATA_RESP_AVG_TIME_IN_MIN:
				rtString = "uploadContactsData每分钟平均响应时间";
				break;
			case DB_COUNT_IN_MIN:
				rtString = "访问数据服务每分钟次数";
				break;
			case DB_RESP_AVG_TIME_IN_MIN:
				rtString = "访问数据服务每分钟平均响应时间";
				break;
			}
			return rtString;
		}
	}

	/**
	 * @param type
	 * @return
	 */
	public static String getWarningValue(int type)
	{
		switch (type)
		{
		case 0:
			return "warning";
		case 1:
			return "fatal";
		default:
			return "fatal";
		}
	}

	/**
	 * 服务允许最大响应时间--致命错误临界点
	 */
	public static long Max_Response_Time_Fatal = 30 * 1000;

	/**
	 * 数据服务 ，rpc响应时间
	 */
	public static long Max_DB_Response_Time_Fatal = 10 * 1000;

}
