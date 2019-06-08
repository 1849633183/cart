package com.zb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class BackServletFilter implements Filter {

	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String contextPath = request.getServletContext().getContextPath();
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		if (uri.startsWith("/User_")) {
			String servletPath = StringUtils.substringBetween(uri, "_", "_") + "Servlet";
		/*if (servletPath.equals("CartListServlet") && request.getSession().getAttribute("user") == null) {
			if(!StringUtils.isBlank(request.getHeader("x-requested-with"))&&request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("无用户");
				return;}
			else {
				response.sendRedirect("user/login.jsp");
			}
		}else{*/
				String method = StringUtils.substringAfterLast(uri, "_");
				System.out.println(servletPath + "ooooo" + method);
				request.setAttribute("method", method);
				req.getRequestDispatcher("/" + servletPath).forward(request, response);
				return;
		}
		
	System.out.println("BackServletFilter****************************************");

		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
