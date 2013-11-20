package com.shopping.oauth.po;

/**
 * @author zhangxuhua
 */
public class OAuthParams
{

	private String clientId;

	private String clientSecret;

	private String redirectUri;

	private String authEndpoint;

	private String tokenEndpoint;

	private String refreshTokenEndpoint;

	private String authCode;

	private String accessToken;

	private int expiresIn;

	private String refreshToken;

	private String scope;

	private String resourceUrl;

	private String resource;

	private String application;

	private String errorMessage;

	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	public String getClientSecret()
	{
		return clientSecret;
	}

	public void setClientSecret(String clientSecret)
	{
		this.clientSecret = clientSecret;
	}

	public String getRedirectUri()
	{
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri)
	{
		this.redirectUri = redirectUri;
	}

	public String getAuthEndpoint()
	{
		return authEndpoint;
	}

	public void setAuthEndpoint(String authEndpoint)
	{
		this.authEndpoint = authEndpoint;
	}

	public String getTokenEndpoint()
	{
		return tokenEndpoint;
	}

	public void setTokenEndpoint(String tokenEndpoint)
	{
		this.tokenEndpoint = tokenEndpoint;
	}

	public String getRefreshTokenEndpoint()
	{
		return refreshTokenEndpoint;
	}

	public void setRefreshTokenEndpoint(String refreshTokenEndpoint)
	{
		this.refreshTokenEndpoint = refreshTokenEndpoint;
	}

	public String getAuthCode()
	{
		return authCode;
	}

	public void setAuthCode(String authzCode)
	{
		this.authCode = authzCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public int getExpiresIn()
	{
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn)
	{
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken()
	{
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}

	public String getResourceUrl()
	{
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl)
	{
		this.resourceUrl = resourceUrl;
	}

	public String getResource()
	{
		return resource;
	}

	public void setResource(String resource)
	{
		this.resource = resource;
	}

	public String getScope()
	{
		return scope;
	}

	public void setScope(String scope)
	{
		this.scope = scope;
	}

	public String getApplication()
	{
		return application;
	}

	public void setApplication(String application)
	{
		this.application = application;
	}
}
