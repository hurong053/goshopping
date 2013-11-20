/**
 * 
 */
package com.shopping.common.util;

/**
 * Baiku平台统一的异常定义。
 * 
 * @author wuxz
 */
public class GeneralException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 错误代码。
	 */
	public int code;

	/**
	 * 错误原因。
	 */
	public String reason;

	/**
	 * 
	 */
	public GeneralException(int code, String reason)
	{
		this.code = code;
		this.reason = reason;
	}

}
