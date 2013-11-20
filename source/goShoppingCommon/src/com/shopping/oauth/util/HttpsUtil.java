/**
 * 
 */
package com.shopping.oauth.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyStore;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import com.shopping.oauth.po.HttpsParams;

/**
 * @author zhangxuhua 提供对post提交方式的支持。如果在url中直接输入了参数信息，则相当于get方式提交
 * @httpsGet方法提供了get提交方式，传递参数是HttpsParams对象(包括了证书的相关信息)和提交的url(url中包括了参数信息)
 * @httpsPost方式提供了post提交方式，传递参数是HttpsParams对象和提交的url(url只是包括请求地址)，和List<NameValuePair>。
 * @ListList<NameValuePair>封装方法举例：
 * @List<NameValuePair> list = new ArrayList<NameValuePair>();
 * @list.add(new BasicNameValuePair("accessToken", accessToken));
 * @list.add(new BasicNameValuePair("appId", clientId));
 * @list.add(new BasicNameValuePair("appSecret", clientSecret));
 */
public class HttpsUtil
{
	private static Logger logger = Logger.getLogger(HttpsUtil.class);

	// 客户端密钥库
	private static String sslKeyStorePath;

	private static String sslKeyStorePassword;

	private static String sslKeyStoreType;

	// 客户端信任的证书
	private static String sslTrustStore;

	private static String sslTrustStorePassword;

	private static String sslTrustStoreType;

	private static String schema_name;

	private static int schema_port;

	/**
	 * 初始化参数
	 * 
	 * @param httpsParams
	 */
	private static void setUp(HttpsParams httpsParams)
	{
		// 这是密钥库
		sslKeyStorePath = httpsParams.getSslKeyStorePath();
		sslKeyStorePassword = httpsParams.getSslKeyStorePassword();
		sslKeyStoreType = httpsParams.getSslKeyStoreType(); // 密钥库类型，有JKS
		// PKCS12等
		// 信任库，这里需要服务端来信任客户端才能调用，因为这个是配置的https双向验证，不但是要客户端信任服务端，服务端也要信任客户端。
		sslTrustStore = httpsParams.getSslTrustStore();
		sslTrustStorePassword = httpsParams.getSslTrustStorePassword();
		sslTrustStoreType = httpsParams.getSslTrustStoreType(); // 密钥库类型，有JKS
		// PKCS12等
		// 传输协议
		schema_name = httpsParams.getSchema_name();
		// 传输端口
		schema_port = Integer.parseInt(httpsParams.getSchema_port());
	}

	private static HostnameVerifier getHostnameVerifier()
	{
		HostnameVerifier hv = new HostnameVerifier()
		{
			@Override
			public boolean verify(String urlHostName, SSLSession session)
			{
				return true;
			}
		};
		return hv;
	}

	/**
	 * 创建SSLContext对象
	 * 
	 * @param httpsParams
	 * @return
	 * @throws Exception
	 */
	private static SSLContext getSSLContext(HttpsParams httpsParams)
			throws Exception
	{
		SSLContext sslContext;
		// create a "default" JSSE X509TrustManager.
		// keystore
		KeyManager[] km;
		KeyStore ks = KeyStore.getInstance(sslKeyStoreType);
		ks.load(new FileInputStream(sslKeyStorePath),
				sslKeyStorePassword.toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509",
				"SunJSSE");
		kmf.init(ks, sslKeyStorePassword.toCharArray());
		km = kmf.getKeyManagers();

		// trust keystore
		TrustManager[] tm;
		KeyStore ts = KeyStore.getInstance(sslTrustStoreType);
		ts.load(new FileInputStream(sslTrustStore),
				sslTrustStorePassword.toCharArray());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509",
				"SunJSSE");
		tmf.init(ts);
		tm = tmf.getTrustManagers();

		sslContext = SSLContext.getInstance("SSL");
		sslContext.init(km, tm, new java.security.SecureRandom());
		return sslContext;

	}

	private static SSLSocketFactory getSSLSocketFactory(HttpsParams httpsParams)
	{
		SSLSocketFactory ssf = null;
		try
		{
			SSLContext sslContext = getSSLContext(httpsParams);
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			ssf = sslContext.getSocketFactory();
			return ssf;
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return ssf;
	}

	/**
	 * Get方式提交
	 * 
	 * @param httpsParams
	 * @param httpsUrlConnectionUrl
	 * @return
	 */
	public static String httpsGet(HttpsParams httpsParams,
			String httpsUrlConnectionUrl)
	{
		setUp(httpsParams);
		String returnStr = "";

		URL url;
		HttpsURLConnection connection = null;
		BufferedReader reader = null;
		OutputStreamWriter osw = null;
		logger.info("Get,https请求地址：" + httpsUrlConnectionUrl);
		try
		{
			url = new URL(httpsUrlConnectionUrl);
			// 对于主机名的验证，因为配置服务器端证书的时候，是需要填写用户名的，一般用户名来说是本地ip地址，或者本地配置的域名
			HttpsURLConnection
					.setDefaultHostnameVerifier(getHostnameVerifier());
			// 编写HttpsURLConnection 的请求对象，这里需要注意HttpsURLConnection
			// 比我们平时用的HttpURLConnection对了一个s，因为https是也是遵循http协议的，并且是采用ssl这个安全套接字来传输信息的，但是也有可能遭到黑客的攻击

			connection = (HttpsURLConnection) url.openConnection();
			SSLSocketFactory ssf = getSSLSocketFactory(httpsParams);
			if (ssf == null)
			{
				logger.error("SSLSocketFactory 加载失败");
				return null;
			}
			setHttpsURLConnectionParams(ssf, connection, "GET");
			osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			osw.flush();
			osw.close();

			logger.info("Get,https请求" + httpsUrlConnectionUrl + "成功");
			// 接收请求的返回值
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			StringBuffer stb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null)
			{
				stb.append(line);
			}
			if (connection.getResponseCode() == HttpStatus.SC_OK)
			{
				returnStr = stb.toString();
			}
			else
			{
				throw new Exception("http proxy:"
						+ connection.getResponseCode());
			}
			reader.close();
			connection.disconnect();
			logger.info("Get,https请求返回成功，返回结果为:" + returnStr);
			return returnStr;
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		finally
		{
			try
			{
				if (osw != null)
				{
					osw.close();
				}
				if (reader != null)
				{
					reader.close();
				}
			}
			catch (IOException e)
			{
				logger.error(e);
			}
			if (connection != null)
			{
				connection.disconnect();
			}
		}
		return returnStr;
	}

	/**
	 * Post方式提交， (不会报hostname in certificate didn't
	 * match错，即证书的hostname跟访问的hostname可以不同)
	 * 
	 * @param httpsParams
	 * @param httpsUrlConnectionUrl
	 * @param nvps
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String httpsPost(HttpsParams httpsParams,
			String httpsUrlConnectionUrl, List<NameValuePair> nvps)
	{
		setUp(httpsParams);
		String returnStr = "";
		logger.info("Post,https请求地址：" + httpsUrlConnectionUrl);
		HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

		HttpClient httpClient = new DefaultHttpClient();
		setConnectionTimeOut(httpClient);
		try
		{
			SSLContext sslContext = getSSLContext(httpsParams);
			org.apache.http.conn.ssl.SSLSocketFactory socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(
					sslContext);
			socketFactory
					.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
			// Set verifier
			HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

			Scheme sch = new Scheme(schema_name, schema_port, socketFactory);
			logger.info("Post,https请求" + httpsUrlConnectionUrl + "成功");
			// 接收请求的返回值
			httpClient.getConnectionManager().getSchemeRegistry().register(sch);
			HttpPost httpPost = new HttpPost(httpsUrlConnectionUrl);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(
						httpResponse.getEntity().getContent()));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null)
				{
					sb.append(line);
				}
				br.close();
				returnStr = sb.toString();
				httpClient.getConnectionManager().shutdown();
			}
			else
			{
				httpClient.getConnectionManager().shutdown();
				throw new Exception("http proxy:"
						+ httpResponse.getStatusLine().getStatusCode());
			}
		}
		catch (Exception e)
		{
			httpClient.getConnectionManager().shutdown();
			logger.error(e);
		}
		finally
		{
			httpClient.getConnectionManager().shutdown();
		}
		return returnStr;
	}

	/**
	 * 设置网络超时
	 * 
	 * @param httpClient
	 */
	private static void setConnectionTimeOut(HttpClient httpClient)
	{
		HttpParams httpParams = httpClient.getParams();
		// 设置网络超时参数
		HttpConnectionParams.setConnectionTimeout(httpParams, 30 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 30 * 1000);
	}

	private static void setHttpsURLConnectionParams(SSLSocketFactory ssf,
			HttpsURLConnection connection, String method)
			throws ProtocolException
	{
		connection.setSSLSocketFactory(ssf);
		connection.setRequestProperty("Content-Type", "text/xml");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		// 设置请求方式为post,这里也可以用get，也可用get,post混合模式
		connection.setRequestMethod(method);
		connection.setUseCaches(false);
		connection.setReadTimeout(30000);
	}

}
