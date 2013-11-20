/**
 * 
 */
package com.shopping.server.auth.biz.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.server.auth.biz.AuthClientBiz;
import com.shopping.server.auth.biz.AuthOperation;
import com.shopping.server.auth.dao.AuthClientDao;
import com.shopping.server.auth.util.AuthClientUtil;

/**
 * @author zhangxuhua
 */
public class AuthClientBizImpl implements AuthClientBiz
{
	private static final long serialVersionUID = 1L;

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private AuthClientDao authClientDao;

	@Autowired
	private AuthOperation authOperation;

	/**
	 * @return the authClientDao
	 */
	public AuthClientDao getAuthClientDao()
	{
		return authClientDao;
	}

	/**
	 * @param authClientDao
	 *            the authClientDao to set
	 */
	public void setAuthClientDao(AuthClientDao authClientDao)
	{
		this.authClientDao = authClientDao;
	}

	/**
	 * @return the authOperation
	 */
	public AuthOperation getAuthOperation()
	{
		return authOperation;
	}

	/**
	 * @param authOperation
	 *            the authOperation to set
	 */
	public void setAuthOperation(AuthOperation authOperation)
	{
		this.authOperation = authOperation;
	}

	@Override
	public String getUserId(String accessToken)
	{
		String userId = "";
		if (!AuthClientUtil.getInstance().isAccessTokenEmpty(accessToken))
		{
			userId = authClientDao.getUserIdByAccessToken(accessToken);
			if (AuthClientUtil.getInstance().isUserIdEmpty(userId))
			{
				userId = authOperation.getUserIdFromResource(accessToken);
			}
		}
		logger.info("用户id：" + userId);
		return userId;
	}

}
