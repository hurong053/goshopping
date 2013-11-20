/**
 * 
 */
package com.shopping.server.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxuhua
 */
public class EncodingFilter implements Filter
{
	private String encoding;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy()
	{

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * 支持post和get混合提交；post和get中有相同name的情况
	 * 注意，使用浏览器访问时要注意浏览器的URLEncoding编码格式，需同"encoding"(默认为UTF-8)保持一致
	 * 
	 * 在EncodingHttpServletRequest中，
	 * 默认对下面四种获取方式做统一支持，使用混合提交方式后，单独支持了一种方式，通过别的方式获取的值不会对应改变
	 * request.getParameter() String 单个数据，这里取第一个
	 * request.getParameterMap() Collection 返回所有的键值对
	 * request.getParameterValues() Enumeration 一组数据
	 * request.getParameterNames() Enumeration  一组数据(当servlet中不知道参数的名称时使用)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httprequest = (HttpServletRequest) request;
		request.setCharacterEncoding(encoding);
		// 需要写在强制转化之前，解决post方式的编码问题
		response.setContentType("text/html;charset=" + encoding);
		// 将httpRequest进行包装
		EncodingHttpServletRequest wrapper = new EncodingHttpServletRequest(
				httprequest, encoding);
		chain.doFilter(wrapper, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException
	{
		encoding = fConfig.getInitParameter("encoding");
	}

}
