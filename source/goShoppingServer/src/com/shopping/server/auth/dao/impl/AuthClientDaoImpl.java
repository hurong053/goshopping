/**
 * 
 */
package com.shopping.server.auth.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.shopping.server.auth.dao.AuthClientDao;
import com.shopping.server.auth.datasource.JedisTemplate;
import com.shopping.server.auth.redis.clients.jedis.Transaction;
import com.shopping.server.auth.util.AuthClientUtil;
import com.shopping.server.auth.util.ParamsUtil;

/**
 * @author zhangxuhua
 */
public class AuthClientDaoImpl implements AuthClientDao
{
	private static final long serialVersionUID = 1L;

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	JedisTemplate jedisTemplate;

	/**
	 * @return the jedisTemplate
	 */
	public JedisTemplate getJedisTemplate()
	{
		return jedisTemplate;
	}

	/**
	 * @param jedisTemplate
	 *            the jedisTemplate to set
	 */
	public void setJedisTemplate(JedisTemplate jedisTemplate)
	{
		this.jedisTemplate = jedisTemplate;
	}

	/* (non-Javadoc)
	 * @see com.baiku.contacts.auth.dao.AuthClientDao#saveAccessToken(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean saveAccessToken(String userId, String accessToken)
			throws DataAccessException
	{
		/**
		 * redis 事物
		 */
		if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(userId))
		{
			return false;
		}
		else
		{
			Transaction jedisTransaction = jedisTemplate.multi();
			// 保存accessToken对应的userId
			jedisTransaction.set(accessToken, userId);
			// 设置accessToken保存时间
			jedisTransaction.expire(
					accessToken,
					Integer.valueOf(ParamsUtil.getInstance().getOAuthParams()
							.getExpiresIn()));
			// 执行
			List<Object> result = jedisTransaction.exec();
			return allPass(result);
		}
	}

	/* (non-Javadoc)
	 * @see com.baiku.contacts.auth.dao.AuthClientDao#getUserIdByAccessToken()
	 */
	@Override
	public String getUserIdByAccessToken(String accessToken)
			throws DataAccessException
	{
		if (AuthClientUtil.getInstance().isAccessTokenEmpty(accessToken))
		{
			return "";
		}
		else
		{
			String userId = jedisTemplate.get(accessToken);
			return userId;
		}
	}

	// 所有的操作都成功，返回true
	private boolean allPass(List<Object> result)
	{
		if (result != null && result.size() > 0)
		{
			if (logger.isDebugEnabled())
			{
				for (Object o : result)
				{
					logger.debug("result:" + o);
				}
			}
			for (Object o : result)
			{
				// 依次判断所有的返回结果
				if (!(StringUtils.equals(o.toString(), "0") || StringUtils
						.equals(o.toString(), "1")))
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public String ping() throws Exception
	{
		return jedisTemplate.ping();
	}

}
