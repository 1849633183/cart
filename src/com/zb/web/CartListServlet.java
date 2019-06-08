package com.zb.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.zb.dao.UserDao;
import com.zb.pojo.Order;
import com.zb.pojo.OrderItem;
import com.zb.pojo.Product;
import com.zb.pojo.Userinfo;
import com.zb.utill.Page;
import com.zb.utill.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@WebServlet("/CartListServlet")
public class CartListServlet extends PageBaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub

		// 接收参数
		int number = Integer.parseInt(request.getParameter("number"));
		String product = request.getParameter("product");

		JSONObject jsonObject = JSONObject.fromObject(product);
		JSONArray array = null;
		Userinfo userinfo = null;
		if (request.getSession().getAttribute("cart") != null)
			array = JSONArray.fromObject((String) request.getSession().getAttribute("cart"));
		if (request.getSession().getAttribute("userinfo") != null)
			userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
		else
			userinfo = getUserInfo();

		// 订单项数据库数据
		OrderItem item = new OrderItem();
		item.setOiid(UUID.randomUUID().toString().replaceAll("-", ""));
		item.setNumber(number);
		item.setProduct((Product) JSONObject.toBean(jsonObject, Product.class));
		item.setOrder(new Order(userinfo, "-1", 1, -1));
		item.setUser(userinfo);
		JSONObject nJsonObject = JSONObject.fromObject(item);

		// 判断并存储订单数据
		if (array == null) {
			array = JSONArray.fromObject(nJsonObject);
		} else {

			if (!Util.oderitemnumberadd(array, nJsonObject, item)) {
				array.add(nJsonObject);
			}

		}
		request.getSession().setAttribute("cart", array.toString());

		orderService.addcartitem(item);
		return "%添加成功";

	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub

		// 接收参数

		String oiid = request.getParameter("oiid");
		JSONArray array = JSONArray.fromObject((String) request.getSession().getAttribute("cart"));
		for (int i = 0; i < array.size(); i++) {
			if (((JSONObject) array.get(i)).getString("oiid").equals(oiid))
				array.remove(i);
		}
		orderService.deleteCartItem(oiid);
		request.getSession().setAttribute("cart", array.toString());

		return "%删除成功";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub

		// 接收参数
		int number = Integer.parseInt(request.getParameter("number"));
		String oiid = request.getParameter("oiid");
		JSONArray array = JSONArray.fromObject((String) request.getSession().getAttribute("cart"));
		for (int i = 0; i < array.size(); i++) {
			if (((JSONObject) array.get(i)).getString("oiid").equals(oiid)) {
				((JSONObject) array.get(i)).put("number", number);
				orderService.updateCartItem(oiid, number);
			}
		}

		request.getSession().setAttribute("cart", array.toString());
		return "%修改成功";
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = null;
		if (request.getSession().getAttribute("cart") != null)
			jsonArray = JSONArray.fromObject((String) request.getSession().getAttribute("cart"));
		else
			return "user/cart.jsp";
		Double sum = 0.0;
		List list = new ArrayList<OrderItem>();
		for (Object jstr : jsonArray) {
			list.add(jstr);
			sum += (((JSONObject) jstr).getJSONObject("product")).getDouble("pnewprice")
					* ((JSONObject) jstr).getInt("number");
		}

		request.setAttribute("cartlist", list);
		request.setAttribute("cartsum", sum);

		return "user/cart.jsp";
	}

	public String ajaxlist(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		/*
		 * try {
		 * response.getWriter().print((String)request.getSession().getAttribute(
		 * "cart")); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		return "%" + (String) request.getSession().getAttribute("cart");
	}

	public String list2session(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		Userinfo userinfo = null;
		if (request.getSession().getAttribute("userinfo") != null)
			userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
		else
			userinfo = getUserInfo();

		List<OrderItem> items = orderService.listitem(new Order(userinfo, "-1", 1, -1));
		JSONArray jsonArray = JSONArray.fromObject(items);
		request.getSession().setAttribute("cart", jsonArray.toString());
		return "%list已存到session";
	}

	private Userinfo getUserInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String Email = ((UserDetails) principal).getUsername();
		Userinfo userinfo = new UserDao().findUserByEmail(Email);
		return userinfo;

	}

}
