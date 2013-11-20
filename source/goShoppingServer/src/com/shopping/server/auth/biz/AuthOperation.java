/**
 * 
 */
package com.shopping.server.auth.biz;

/**
 * 针对服务器的相关操作
 * 
 * @author zhangxuhua
 */
public interface AuthOperation
{

	/**
	 * 根据refreshToken获得换取新的accessToken
	 * 
	 * @param request
	 * @return
	 */
	public boolean refreshToken(String accessToken);

	/**
	 * 根据accessToken，从资源服务器中得到联系人服务用户对应的用户Id
	 * 
	 * @param accessToken
	 * @return
	 */
	public String getUserIdFromResource(String accessToken);

}
