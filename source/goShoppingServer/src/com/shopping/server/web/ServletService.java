package com.shopping.server.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shopping.server.auth.biz.AuthClientBiz;
import com.shopping.server.auth.util.BeanFactoryUtil;

public class ServletService extends HttpServlet
{
	/**
	 * 通讯录servlet
	 * 
	 * @author zhangxuhua
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ServletService.class);

	private static final long serialVersionUID = -4187892622890961839L;

	AuthClientBiz authClientBiz;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		authClientBiz = (AuthClientBiz) BeanFactoryUtil
				.getBean("authClientBiz");
		String service = request.getParameter("service");// 请求服务类型
		PrintWriter pw = response.getWriter();
		String params = request.getParameter("params");
		String accessToken = request.getParameter("accessToken");
		String userId = "";
		logger.info("用户请求服务类型：" + service);
		logger.info("accessToken:" + accessToken);
		logger.info("accessToken:" + accessToken + ", 传递的参数：" + params);
		if ("queryAllGroup".equals(service))
		{

		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		/*
		* call doGet method to access
		*/
		logger.info("doPost method is called");
		this.doGet(req, resp);
	}

}
