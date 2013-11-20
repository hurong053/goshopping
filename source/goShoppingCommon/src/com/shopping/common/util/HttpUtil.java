package com.shopping.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

/**
 * <P>
 * Copyright (C), 2006-2011, Channelsoft Tech. Co., Ltd.
 * <P>
 * FileName: HttpUtil.java
 * 
 * @author llk
 *         <P>
 *         CreateTime: 2011-10-19
 *         <P>
 *         Description: Java发送HTTP的POST请求
 *         <P>
 *         Version: 1.0
 *         <P>
 *         <P>
 *         Function List: // 主要函数及其功能
 *         <P>
 *         1. -------
 *         <P>
 *         2. -------
 *         <P>
 *         History: // 历史修改记录
 *         <P>
 *         <author> <time> <version > <desc>
 */
public class HttpUtil
{

	private static Logger logger = Logger.getLogger(HttpUtil.class);

	public static String httpPost(String url, List<NameValuePair> nvps)
	{
		String returnStr = "";
		logger.info("Post,http请求地址：" + url);
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		setConnectionTimeOut(httpClient);
		try
		{
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
		// 20秒未连接则超时
		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
	}

	/*
	 * 通过url传递参数的方式是get，这里是混合模式
	 */
	public static String http(String url)
	{
		URL u = null;
		HttpURLConnection con = null;
		InputStreamReader in = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		StringBuffer buffer = new StringBuffer();

		try
		{
			// 尝试发送请求
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(20000);// 20秒未连接则超时
			con.setReadTimeout(20000);// 20秒未响应则超时
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");

			// 读取返回内容
			in = new InputStreamReader(con.getInputStream(), "UTF-8");
			br = new BufferedReader(in);
			String temp;
			while ((temp = br.readLine()) != null)
			{
				buffer.append(temp);
				buffer.append("\n");
			}
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
					osw.flush();
					osw.close();
				}
				if (br != null)
				{
					br.close();
				}
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			if (con != null)
			{
				con.disconnect();
			}
		}

		return buffer.toString();
	}

	/**
	 * 判断网络图片是否存在
	 * 
	 * @author: llk
	 * @CreateTime: 2012-1-18 下午04:50:41
	 * @param webUrl
	 * @return
	 * @Return: boolean
	 */
	public static boolean isWebPicExist(String webUrl)
	{
		boolean flag = false;
		try
		{
			URL url = new URL(webUrl);
			// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
			URLConnection uc = url.openConnection();
			// 打开的连接读取的输入流。
			uc.getInputStream();
			flag = true;

		}
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}
}
