/**
 * 
 */
package com.shopping.server.auth.util;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.shopping.oauth.po.HttpsParams;
import com.shopping.oauth.po.OAuthParams;

/**
 * 在配置文件修改之后，才会去重新加载，否则是读取已经在内存中的数据
 * 
 * @author zhangxuhua
 */
public class ParamsUtil
{
	private static Logger logger = Logger.getLogger(ParamsUtil.class);

	private OAuthParams oAuthParams;

	private HttpsParams httpsParams;

	private OAuthParams oAuthParamsHttp;

	private OAuthParams oAuthParamsHttps;

	/**
	 * Initialization on Demand Holder，使用内部类，延迟加载，且线程安全
	 * 
	 * @author zhangxuhua
	 */
	private static class ParamsUtilHolder
	{
		public final static ParamsUtil instance = new ParamsUtil();
	}

	public static ParamsUtil getInstance()
	{
		return ParamsUtilHolder.instance;
	}

	/**
	 * 在配置文件被修改后，该函数会被调用
	 * 
	 * @param pros
	 */
	public void setPropertiesParams(Properties pros)
	{
		setOAuthParams(getOAuthParamsHandler(pros));
		setOAuthParamsHttp(getOAuthParamsHttpHandle(pros));
		setOAuthParamsHttps(getOAuthParamsHttpsHandle(pros));
		setHttpsParams(getHttpsParamsHandle(pros));
	}

	/**
	 * 单例，线程安全的oAuthParams
	 * 
	 * @author zhangxuhua
	 */
	private static class AuthParamsHolder
	{
		public final static OAuthParams oAuthParams = new OAuthParams();
	}

	private synchronized OAuthParams getOAuthParamsHandler(Properties pros)
	{
		oAuthParams = AuthParamsHolder.oAuthParams;
		oAuthParams.setClientId(pros.getProperty("clientId", "Baiku"));
		oAuthParams.setClientSecret(pros.getProperty("clientSecret", "Baiku"));
		oAuthParams.setScope(pros.getProperty("scope"));
		int expiresIn = 0;
		try
		{
			expiresIn = Integer.valueOf(pros.getProperty("expiresIn"));
		}
		catch (Exception e)
		{
			logger.error(
					"expiresIn is not a integer,set 100000 as the default value.",
					e);
			expiresIn = 100000;
		}
		oAuthParams.setExpiresIn(expiresIn);

		return oAuthParams;
	}

	/**
	 * 单例，线程安全的oAuthParamsHttp
	 * 
	 * @author zhangxuhua
	 */
	private static class AuthParamsHttpHolder
	{
		public final static OAuthParams oAuthParamsHttp = new OAuthParams();
	}

	private synchronized OAuthParams getOAuthParamsHttpHandle(Properties pros)
	{
		oAuthParamsHttp = AuthParamsHttpHolder.oAuthParamsHttp;
		oAuthParamsHttp.setRedirectUri(pros.getProperty("redirectURI_http"));
		oAuthParamsHttp.setResourceUrl(pros.getProperty("resourceUrl_http"));
		oAuthParamsHttp
				.setTokenEndpoint(pros.getProperty("tokenLocation_http"));
		oAuthParamsHttp.setRefreshTokenEndpoint(pros
				.getProperty("refreshToken_http"));
		return oAuthParamsHttp;
	}

	/**
	 * 单例，线程安全的oAuthParamsHttps
	 * 
	 * @author zhangxuhua
	 */
	private static class AuthParamsHttpsHolder
	{
		public final static OAuthParams oAuthParamsHttps = new OAuthParams();
	}

	private synchronized OAuthParams getOAuthParamsHttpsHandle(Properties pros)
	{
		oAuthParamsHttps = AuthParamsHttpsHolder.oAuthParamsHttps;
		oAuthParamsHttps.setRedirectUri(pros.getProperty("redirectURI_https"));
		oAuthParamsHttps.setResourceUrl(pros.getProperty("resourceUrl_https"));
		oAuthParamsHttps.setTokenEndpoint(pros
				.getProperty("tokenLocation_https"));
		oAuthParamsHttps.setRefreshTokenEndpoint(pros
				.getProperty("refreshToken_https"));

		return oAuthParamsHttps;
	}

	/**
	 * 单例，线程安全的httpsParams
	 * 
	 * @author zhangxuhua
	 */
	private static class HttpsParamsHolder
	{
		public final static HttpsParams httpsParams = new HttpsParams();
	}

	private synchronized HttpsParams getHttpsParamsHandle(Properties pros)
	{
		httpsParams = HttpsParamsHolder.httpsParams;
		httpsParams.setSslKeyStorePath(pros.getProperty("sslKeyStorePath"));
		httpsParams.setSslKeyStorePassword(pros
				.getProperty("sslKeyStorePassword"));
		httpsParams.setSslKeyStoreType(pros.getProperty("sslKeyStoreType"));
		httpsParams.setSslTrustStore(pros.getProperty("sslTrustStore"));
		httpsParams.setSslTrustStorePassword(pros
				.getProperty("sslTrustStorePassword"));
		httpsParams.setSslTrustStoreType(pros.getProperty("sslTrustStoreType"));
		httpsParams.setSchema_name(pros.getProperty("schema_name"));
		httpsParams.setSchema_port(pros.getProperty("schema_port"));

		return httpsParams;
	}

	/**
	 * @param oAuthParams
	 *            the oAuthParams to set
	 */
	public void setOAuthParams(OAuthParams oAuthParams)
	{
		this.oAuthParams = oAuthParams;
	}

	/**
	 * @param httpsParams
	 *            the httpsParams to set
	 */
	public void setHttpsParams(HttpsParams httpsParams)
	{
		this.httpsParams = httpsParams;
	}

	/**
	 * @param oAuthParamsHttp
	 *            the oAuthParamsHttp to set
	 */
	public void setOAuthParamsHttp(OAuthParams oAuthParamsHttp)
	{
		this.oAuthParamsHttp = oAuthParamsHttp;
	}

	/**
	 * @param oAuthParamsHttps
	 *            the oAuthParamsHttps to set
	 */
	public void setOAuthParamsHttps(OAuthParams oAuthParamsHttps)
	{
		this.oAuthParamsHttps = oAuthParamsHttps;
	}

	/**
	 * 读取资源文件中的属性，并实例化为OAuthParams
	 * 
	 * @return
	 */
	public OAuthParams getOAuthParams()
	{
		if (oAuthParams == null)
		{
			Properties pros = AuthClientUtil.getInstance().getProperties();
			oAuthParams = getOAuthParamsHandler(pros);
		}
		return oAuthParams;
	}

	/**
	 * 读取资源文件中的属性，并实例化为OAuthParams，https相关的
	 * 
	 * @return
	 */
	public OAuthParams getOAuthParamsHttps()
	{
		if (oAuthParamsHttps == null)
		{
			Properties pros = AuthClientUtil.getInstance().getProperties();
			oAuthParamsHttps = getOAuthParamsHttpsHandle(pros);
		}
		return oAuthParamsHttps;
	}

	/**
	 * 读取资源文件中的属性，并实例化为OAuthParams，http相关的的
	 * 
	 * @return
	 */
	public OAuthParams getOAuthParamsHttp()
	{
		if (oAuthParamsHttp == null)
		{
			Properties pros = AuthClientUtil.getInstance().getProperties();
			oAuthParamsHttp = getOAuthParamsHttpHandle(pros);
		}
		return oAuthParamsHttp;
	}

	public HttpsParams getHttpsParams()
	{
		if (httpsParams == null)
		{
			Properties pros = AuthClientUtil.getInstance().getProperties();
			httpsParams = getHttpsParamsHandle(pros);
		}
		return httpsParams;
	}
}
