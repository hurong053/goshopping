/**
 * 
 */
package com.shopping.server.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

/**
 * @author zhangxuhua
 */
public class EncodingHttpServletRequest extends HttpServletRequestWrapper
{
	private static Logger logger = Logger
			.getLogger(EncodingHttpServletRequest.class);

	private final HttpServletRequest request;

	private String encoding;

	private List<String> queryValues;

	public EncodingHttpServletRequest(HttpServletRequest request)
	{
		super(request);
		this.request = request;
	}

	public EncodingHttpServletRequest(HttpServletRequest request,
			String encoding)
	{
		super(request);
		this.request = request;
		this.encoding = encoding;
	}

	public EncodingHttpServletRequest(HttpServletRequest request,
			String encoding, List<String> queryValues)
	{
		super(request);
		this.request = request;
		this.encoding = encoding;
		this.queryValues = queryValues;
	}

	/**
	 * 在该方法被调用的时候执行
	 */
	@Override
	public String getParameter(String name)
	{
		String value = request.getParameter(name);
		if (null != value)
		{
			try
			{
				String decodeStr = URLDecoder.decode(value, encoding);
				// tomcat默认以ISO8859-1处理GET传来的参数。
				// 设置tomcat参数URIEncoding="UTF-8"，使用UTF-8处理GET传来的传输；
				// 设置tomcat参数useBodyEncodingForURI="true"，表示使用request.setCharacterEncoding对URL提交的数据和表单中GET方式提交的数据进行重新编码
				value = new String(decodeStr.getBytes(encoding), encoding);
			}
			catch (UnsupportedEncodingException e)
			{
				logger.error(e);
			}
		}
		return value;
	}
}