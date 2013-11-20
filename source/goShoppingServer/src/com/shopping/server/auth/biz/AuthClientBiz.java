/**
 * 
 */
package com.shopping.server.auth.biz;


/**
 * @author zhangxuhua
 */
public interface AuthClientBiz
{
	/**
	 * 根据具体情况，获得userId
	 * 
	 * @return
	 */
	public String getUserId(String accessToken);
}
