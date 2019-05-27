package com.zb.web;


import java.io.InputStream;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.catalina.connector.Request;
import org.apache.log4j.Logger;

import com.zb.dao.ProductsDao;
import com.zb.service.OrderService;
import com.zb.service.ProductListService;
import com.zb.utill.Page;
import com.zb.utill.test;





public abstract class PageBaseBackServlet extends HttpServlet {

	public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page) ;
	
	static Logger logger = Logger.getLogger(test.class);
	protected ProductListService productListService=new ProductListService();
    protected OrderService orderService=new OrderService();

	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			/*获取分页信息*/
			int start= 0;
			int count = 12;
			String type=null;
			try {
				start = Integer.parseInt(request.getParameter("page.start"));
			} catch (Exception e) {
				
			}
			try {
				count = Integer.parseInt(request.getParameter("page.count"));
			} catch (Exception e) {
			}	
			try {
			/*	type = new String(request.getParameter("page.type").getBytes("iso-8859-1"), "utf-8");*/
				type=request.getParameter("page.type");
				if(type.length()==0)
					type=null;		
			} catch (Exception e) {
				// TODO: handle exception
			}
			logger.info(type+">>>>>>>>>>>>>>>>>>>>>>>type>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
			
			Page page = new Page(start,count,type);
			
			/*借助反射，调用对应的方法*/
			String method = (String) request.getAttribute("method");
			Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
					javax.servlet.http.HttpServletResponse.class,Page.class);
			String redirect=null;
			try {
				redirect = m.invoke(this,request, response,page).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info(redirect+">>>>>>>>>>>>>>>>>>>>>>>json导致空指针>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			
			/*根据方法的返回值，进行相应的客户端跳转，服务端跳转，或者仅仅是输出字符串*/
			
			logger.info(redirect+">>>>>>>>>>>>>>>>>>>>>>>redirect>>>>>>>>>>>>>>>>>>>>>>>>>>");
			if(redirect==null)
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>json>>>>>>>>>>>>>>>>>>>>>>>>>>");
			else if(redirect.startsWith("@"))
				response.sendRedirect(redirect.substring(1));
			else if(redirect.startsWith("%"))
				response.getWriter().print(redirect.substring(1));
			else
				request.getRequestDispatcher(redirect).forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	 
	

}
