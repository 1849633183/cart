package com.zb.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zb.pojo.Userinfo;
import com.zb.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) //
	 */
	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// response.getWriter().append("Served at:
	// ").append(request.getContextPath());
	// }
	//
	// /**
	// * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// */
	// protected void doPost(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// doGet(request, response);
	// }
	public String Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email+password);
		UserService userService = new UserService();
		Userinfo user = null;
		try {
			user = userService.Login(email, password);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		if (user.getName() != null) {
			String remember = request.getParameter("remember");
			System.out.println(remember);
			if (remember != null) {

				Cookie emailCookie = new Cookie("email", email);
				Cookie passwordCookie = new Cookie("password", password);
				emailCookie.setMaxAge(60 * 10);
				passwordCookie.setMaxAge(60 * 10);
				response.addCookie(emailCookie);
				response.addCookie(passwordCookie);
			}
	
			request.getSession().setAttribute("user", user);
			return "%密码正确";
		} else {
			System.out.println("密码错误");
			 
			return "%密码错误";
			
		}

	}

	public String Register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		UserService userService = new UserService();
		boolean register = userService.register(email, name, password);
		if (register) {
			return "%注册成功";

		} else {

			return "%注册失败";

		}
	}
	public String Logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub		
		request.getSession().invalidate();
		return "user/login.jsp";
	}
}
