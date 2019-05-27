/*package com.zb.web;

import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zb.bean.OrderItem;
import com.zb.bean.Product;
import com.zb.bean.User;
import com.zb.utill.Page;
import com.zb.utill.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/CartListServlet")
public class CartListServlet extends PageBaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>购物车增加>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		//接收参数
		int number = Integer.parseInt(request.getParameter("number"));
		String product = request.getParameter("product");

		JSONObject jsonObject = JSONObject.fromObject(product);
		logger.debug("iiiiiiiiiiiiiiiiiiiiiiii");
		String numbername = jsonObject.getString("pid") + "number";

		JSONObject numberjson=new JSONObject();
		JSONArray array = null;
		if (request.getSession().getAttribute("cart") != null)
			{numberjson = JSONObject.fromObject((String) request.getSession().getAttribute("number"));
			array = JSONArray.fromObject((String) request.getSession().getAttribute("cart"));}
		
		
		
		//订单项数据库数据
		OrderItem item=new OrderItem();
		item.setOiid(UUID.randomUUID().toString().replaceAll("-", ""));
		item.setNumber(number);
		item.setState(1);
		item.setProduct((Product)JSONObject.toBean(jsonObject, Product.class));
		item.setUser((User)request.getSession().getAttribute("user"));
		JSONObject nJsonObject=JSONObject.fromObject(item);
		logger.info(nJsonObject + ">>>>>>>>>>>>>>>>>>>>>>>nJsonObject>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info((request.getSession().getAttribute("cart") != null)
				+ ">>>>>>>>>>>>>>>>>>>>>>>request.getSession().getAttribute!=null>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		
		
		//判断并存储订单数据
		if (array == null) {
			array = JSONArray.fromObject(jsonObject);
			request.getSession().setAttribute("cart", array.toString());
			request.getSession().setAttribute(numbername, number);

		} else {
			logger.info(Util.isExistInJSONArray(array, jsonObject));
			if (Util.isExistInJSONArray(array, jsonObject)) {
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>number加一>>>>>>>>>>>>>>>>>>>>>>>>>>");
				logger.info(numbername + ">>>>>>>>>>>>>>>>>>>>>>>numbername>>>>>>>>>>>>>>>>>>>>>>>>>>");
				logger.info((int) request.getSession().getAttribute(numbername) + number
						+ ">>>>>>>>>>>>>>>>>>>>>>>request.getSession().getAttribute(numbername)>>>>>>>>>>>>>>>>>>>>>>>>>>");
				int addnumber = (int) request.getSession().getAttribute(numbername) + number;
				request.getSession().setAttribute(numbername, addnumber);
			} else {
				array.add(jsonObject);
				logger.info(array + ">>>>>>>>>>>>>>>>>>>>>>>array>>>>>>>>>>>>>>>>>>>>>>>>>>");
				request.getSession().setAttribute("cart", array.toString());
				request.getSession().setAttribute(numbername, number);
			}
		}
		logger.info(numberjson + ">>>>>>>>>>>>>>>>>>>>>>>numberjson>>>>>>>>>>>>>>>>>>>>>>>>>>");
		if(numberjson==null||numberjson.get(numbername)==null)
			numberjson.put(numbername, number);
		else
			numberjson.put(numbername, (int)numberjson.get(numbername)+number);

		request.getSession().setAttribute("number", numberjson.toString());
		logger.info(numberjson + ">>>>>>>>>>>>>>>>>>>>>>>numberjson>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info(number + ">>>>>>>>>>>>>>>>>>>>>>>number>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info(product + ">>>>>>>>>>>>>>>>>>>>>>>product>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		orderService.addcartitem(item);
		return "%添加成功";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	public void ajaxlist(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub

	}

}
*/