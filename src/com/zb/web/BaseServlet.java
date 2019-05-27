package com.zb.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zb.utill.Page;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 @Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		/*根据方法的返回值，进行相应的客户端跳转，服务端跳转，或者仅仅是输出字符串*/
		
		try {
			// TODO Auto-generated method stub
			/*借助反射，调用对应的方法*/
			String method = (String) request.getAttribute("method");
			Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
					javax.servlet.http.HttpServletResponse.class);
			String redirect = m.invoke(this, request, response).toString();
			response.setCharacterEncoding("utf-8");
			if (redirect.startsWith("@"))
				response.sendRedirect(redirect.substring(1));
			else if (redirect.startsWith("%"))
				response.getWriter().print(redirect.substring(1));
			else
				request.getRequestDispatcher(redirect).forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}	 
	
	
	
}

}
