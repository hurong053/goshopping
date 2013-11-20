/**
 * 
 */
package com.shopping.server.auth.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.common.util.HttpUtil;
import com.shopping.oauth.po.UserInfoRespPo;
import com.shopping.oauth.util.HttpsUtil;
import com.shopping.server.auth.biz.AuthOperation;
import com.shopping.server.auth.dao.AuthClientDao;
import com.shopping.server.auth.util.AuthClientUtil;
import com.shopping.server.auth.util.ParamsUtil;

/**
 * auth资源服务器的相关操作
 * 
 * @author zhangxuhua
 */
public class AuthOperationImpl implements AuthOperation
{

	private static final long serialVersionUID = 1L;

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private AuthClientDao authClientDao;

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
	 * 获得资源
	 * 
	 * @param accessToken
	 * @return
	 */
	private JSONObject getUserInfo(String accessToken)
	{
		String resultStr = "";
		String clientId = ParamsUtil.getInstance().getOAuthParams()
				.getClientId();
		String clientSecret = ParamsUtil.getInstance().getOAuthParams()
				.getClientSecret();
		// String paramStr = "";
		// paramStr = "?accessToken=" + accessToken + "&appId=" + clientId
		// + "&appSecret=" + clientSecret;
		// String url = AuthClientUtil.getOAuthParams().getResourceUrl();
		// url = url + paramStr;
		// // resultStr = HttpUtil.http(url);
		// // 基于https的get方式提交
		// resultStr = HttpsUtil.httpsGet(AuthClientUtil.getHttpsParams(), url);
		// 基于https的post方式提交
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("accessToken", accessToken));
		list.add(new BasicNameValuePair("appId", clientId));
		list.add(new BasicNameValuePair("appSecret", clientSecret));
		String urlHttps = ParamsUtil.getInstance().getOAuthParamsHttps()
				.getResourceUrl();
		String urlHttp = ParamsUtil.getInstance().getOAuthParamsHttp()
				.getResourceUrl();
		String url = "";
		// 如果https配置不为空，则选择https协议；若为空则选择http协议
		if (StringUtils.isNotBlank(urlHttps))
		{
			url = urlHttps;
			logger.info("使用https调用：" + urlHttps);
			resultStr = HttpsUtil.httpsPost(ParamsUtil.getInstance()
					.getHttpsParams(), url, list);
		}
		else
		{
			url = urlHttp;
			logger.info("使用http调用：" + urlHttp);
			resultStr = HttpUtil.httpPost(url, list);
		}

		JSONObject jsonObject = JSON.parseObject(resultStr);
		return jsonObject;
	}

	/**
	 * 根据refreshToken获得换取新的accessToken
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public boolean refreshToken(String accessToken)
	{
		return true;
	}

	@Override
	public String getUserIdFromResource(String accessToken)
	{
		logger.info("accessToken:" + accessToken);
		String userId = "";
		// 获得资源
		if (AuthClientUtil.getInstance().isAccessTokenEmpty(accessToken))
		{
			return userId;
		}
		else
		{
			JSONObject jsonObject = getUserInfo(accessToken);
			if (null != jsonObject)
			{
				String userInfoStr = jsonObject.getString("userInfo");
				UserInfoRespPo userInfo = JSON.parseObject(userInfoStr,
						UserInfoRespPo.class);
				if (userInfo != null)
				{
					userId = userInfo.getUid();
					authClientDao.saveAccessToken(userId, accessToken);
				}
			}
			return userId;
		}

	}

}
