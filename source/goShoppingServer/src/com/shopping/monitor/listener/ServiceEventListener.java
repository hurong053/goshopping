package com.shopping.monitor.listener;

/**
 * 通知服务端事件的回调接口
 */
public interface ServiceEventListener
{
	/**
	 * 通知批量上传联系人接口调用情况
	 * 
	 * @param isSuccess
	 *            是否成功
	 * @param respTime
	 *            响应时长，单位：毫秒
	 */
	public void onUploadContactsDataEnd(boolean isSuccess, long respTime);

	/**
	 * 通知批量下载联系人接口调用情况
	 * 
	 * @param isSuccess
	 * @param respTime
	 */
	public void onDownloadContactsDataEnd(boolean isSuccess, long respTime);

	/**
	 * 通知后端数据服务调用情况
	 * 
	 * @param isSuccess
	 * @param respTime
	 */
	public void onDBEnd(boolean isSuccess, long respTime);

}
