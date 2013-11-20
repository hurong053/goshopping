/**
 * 
 */
package com.shopping.oauth.po;

import java.io.Serializable;

/**
 * @author zhangxuhua
 */
public class ResponsePo implements Serializable
{
	private static final long serialVersionUID = 3757350045678574758L;

	protected String status;

	protected String message;

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

}
