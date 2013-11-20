/**
 * 
 */
package com.shopping.oauth.po;

/**
 * @author zhangxuhua
 */
public class HttpsParams
{
	// 密钥库
	private String sslKeyStorePath;

	private String sslKeyStorePassword;

	// 密钥库类型
	private String sslKeyStoreType;

	// 信任库
	private String sslTrustStore;

	private String sslTrustStorePassword;

	// 信任库类型
	private String sslTrustStoreType;

	private String schema_name;

	private String schema_port;

	/**
	 * @return the sslKeyStorePath
	 */
	public String getSslKeyStorePath()
	{
		return sslKeyStorePath;
	}

	/**
	 * @param sslKeyStorePath
	 *            the sslKeyStorePath to set
	 */
	public void setSslKeyStorePath(String sslKeyStorePath)
	{
		this.sslKeyStorePath = sslKeyStorePath;
	}

	/**
	 * @return the sslKeyStorePassword
	 */
	public String getSslKeyStorePassword()
	{
		return sslKeyStorePassword;
	}

	/**
	 * @param sslKeyStorePassword
	 *            the sslKeyStorePassword to set
	 */
	public void setSslKeyStorePassword(String sslKeyStorePassword)
	{
		this.sslKeyStorePassword = sslKeyStorePassword;
	}

	/**
	 * @return the sslKeyStoreType
	 */
	public String getSslKeyStoreType()
	{
		return sslKeyStoreType;
	}

	/**
	 * @param sslKeyStoreType
	 *            the sslKeyStoreType to set
	 */
	public void setSslKeyStoreType(String sslKeyStoreType)
	{
		this.sslKeyStoreType = sslKeyStoreType;
	}

	/**
	 * @return the sslTrustStore
	 */
	public String getSslTrustStore()
	{
		return sslTrustStore;
	}

	/**
	 * @param sslTrustStore
	 *            the sslTrustStore to set
	 */
	public void setSslTrustStore(String sslTrustStore)
	{
		this.sslTrustStore = sslTrustStore;
	}

	/**
	 * @return the sslTrustStorePassword
	 */
	public String getSslTrustStorePassword()
	{
		return sslTrustStorePassword;
	}

	/**
	 * @param sslTrustStorePassword
	 *            the sslTrustStorePassword to set
	 */
	public void setSslTrustStorePassword(String sslTrustStorePassword)
	{
		this.sslTrustStorePassword = sslTrustStorePassword;
	}

	/**
	 * @return the sslTrustStoreType
	 */
	public String getSslTrustStoreType()
	{
		return sslTrustStoreType;
	}

	/**
	 * @param sslTrustStoreType
	 *            the sslTrustStoreType to set
	 */
	public void setSslTrustStoreType(String sslTrustStoreType)
	{
		this.sslTrustStoreType = sslTrustStoreType;
	}

	/**
	 * @return the schema_name
	 */
	public String getSchema_name()
	{
		return schema_name;
	}

	/**
	 * @param schema_name
	 *            the schema_name to set
	 */
	public void setSchema_name(String schema_name)
	{
		this.schema_name = schema_name;
	}

	/**
	 * @return the schema_port
	 */
	public String getSchema_port()
	{
		return schema_port;
	}

	/**
	 * @param schema_port
	 *            the schema_port to set
	 */
	public void setSchema_port(String schema_port)
	{
		this.schema_port = schema_port;
	}

}
