package com.shopping.monitor.utils;

/**
 * 监控指标类，统一管理被监控参数
 */
public class MonitoredParams
{
	/**
	 * 批量上传联系人接口在1分钟内调用总次数
	 */
	private int uploadCountInMin = 0;

	/**
	 * 批量上传联系人接口在1分钟内调用平均响应时长
	 */
	private int uploadRespAvgTimeInMin = 0;

	private int downloadCountInMin = 0;

	private int downloadRespAvgTimeInMin = 0;

	private int dbCountInMin = 0;

	private int dbRespAvgTimeInMin = 0;

	public int getUploadCountInMin()
	{
		return uploadCountInMin;
	}

	public void setUploadCountInMin(int uploadCountInMin)
	{
		this.uploadCountInMin = uploadCountInMin;
	}

	public int getUploadRespAvgTimeInMin()
	{
		return uploadRespAvgTimeInMin;
	}

	public void setUploadRespAvgTimeInMin(int uploadRespAvgTimeInMin)
	{
		this.uploadRespAvgTimeInMin = uploadRespAvgTimeInMin;
	}

	public int getDownloadCountInMin()
	{
		return downloadCountInMin;
	}

	public void setDownloadCountInMin(int downloadCountInMin)
	{
		this.downloadCountInMin = downloadCountInMin;
	}

	public int getDownloadRespAvgTimeInMin()
	{
		return downloadRespAvgTimeInMin;
	}

	public void setDownloadRespAvgTimeInMin(int downloadRespAvgTimeInMin)
	{
		this.downloadRespAvgTimeInMin = downloadRespAvgTimeInMin;
	}

	public int getDbCountInMin()
	{
		return dbCountInMin;
	}

	public void setDbCountInMin(int dbCountInMin)
	{
		this.dbCountInMin = dbCountInMin;
	}

	public int getDbRespAvgTimeInMin()
	{
		return dbRespAvgTimeInMin;
	}

	public void setDbRespAvgTimeInMin(int dbRespAvgTimeInMin)
	{
		this.dbRespAvgTimeInMin = dbRespAvgTimeInMin;
	}

}
