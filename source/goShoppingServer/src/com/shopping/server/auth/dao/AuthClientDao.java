/**
 * 
 */
package com.shopping.server.auth.dao;

import org.springframework.dao.DataAccessException;

/**
 * @author zhangxuhua
 */
public interface AuthClientDao
{
	/**
	 * 保存userId和accessToken到redis中
	 * 
	 * @param userId
	 * @param accessToken
	 * @return
	 */
	public boolean saveAccessToken(String userId, String accessToken)
			throws DataAccessException;

	/**
	 * 根据用户传入的accessToken从redis中获取userId
	 * 
	 * @return
	 */
	public String getUserIdByAccessToken(String accessToken)
			throws DataAccessException;

	public String ping() throws Exception;
}
