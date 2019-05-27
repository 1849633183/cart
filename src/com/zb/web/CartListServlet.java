package com.zb.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>购物车增加>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		//接收参数
		int number = Integer.parseInt(request.getParameter("number"));
		String product = request.getParameter("product");

		JSONObject jsonObject = JSONObject.fromObject(product);
		JSONArray array = null;
		if (request.getSession().getAttribute("cart") != null)		
			array = JSONArray.fromObject((String) request.getSession().getAttribute("cart"));
		
		
		
		//订单项数据库数据
		OrderItem item=new OrderItem();
		item.setOiid(UUID.randomUUID().toString().replaceAll("-", ""));
		item.setNumber(number);
	
		item.setProduct((Product)JSONObject.toBean(jsonObject, Product.class));
		item.setOrder(new Order((Userinfo)request.getSession().getAttribute("user"),"-1",1,-1));
		item.setUser((Userinfo)request.getSession().getAttribute("user"));
		JSONObject nJsonObject=JSONObject.fromObject(item);
		logger.info(nJsonObject + ">>>>>>>>>>>>>>>>>>>>>>>nJsonObject>>>>>>>>>>>>>>>>>>>>>>>>>>");
					
		
		//判断并存储订单数据
		if (array == null) {
			array = JSONArray.fromObject(nJsonObject);
		} else {
			logger.info( nJsonObject.getString("product")+nJsonObject.getInt("number"));
			
			if (!Util.oderitemnumberadd(array, nJsonObject,item)) 			
		    {
				array.add(nJsonObject);
			 }
		
		}
		logger.info(item.getOiid() + ">>>>>>>>>>>>>>>>>>>>>>>item.getOiid()>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info(array + ">>>>>>>>>>>>>>>>>>>>>>>array>>>>>>>>>>>>>>>>>>>>>>>>>>");
		request.getSession().setAttribute("cart", array.toString());
		
		orderService.addcartitem(item);
		return "%添加成功";
	
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
logger.info(">>>>>>>>>>>>>>>>>>>>>>>购物车删除>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		//接收参数
		
		String oiid = request.getParameter("oiid");
		JSONArray array =JSONArray.fromObject((String) request.getSession().getAttribute("cart"));
		logger.info(oiid+">>>>>>>>>>>>>>>>>>>>>>>oiid>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(int i=0;i<array.size();i++)
		{
			logger.info(((JSONObject) array.get(i)).getString("oiid")+">>>>>>>>>>>>>>>>>>>>>>>JSONObject>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 if(((JSONObject) array.get(i)).getString("oiid").equals(oiid))
				 array.remove(i);
		}
		logger.info(array+">>>>>>>>>>>>>>>>>>>>>>>array>>>>>>>>>>>>>>>>>>>>>>>>>>");
		orderService.deleteCartItem(oiid);
		request.getSession().setAttribute("cart", array.toString());		
		
					
		return  "%删除成功";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
logger.info(">>>>>>>>>>>>>>>>>>>>>>>购物车更新>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		//接收参数
        int number = Integer.parseInt(request.getParameter("number"));
		String oiid = request.getParameter("oiid");
		JSONArray array =JSONArray.fromObject((String) request.getSession().getAttribute("cart"));
		logger.info(oiid+">>>>>>>>>>>>>>>>>>>>>>>oiid>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(int i=0;i<array.size();i++)
		{
			logger.info(((JSONObject) array.get(i)).getString("oiid")+">>>>>>>>>>>>>>>>>>>>>>>JSONObject>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 if(((JSONObject) array.get(i)).getString("oiid").equals(oiid))
			 { ((JSONObject) array.get(i)).put("number",number);
			 orderService.updateCartItem(oiid,number);
			 }
		}
		logger.info(array+">>>>>>>>>>>>>>>>>>>>>>>array>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		request.getSession().setAttribute("cart", array.toString());
		
		
		
		
					
		return  "%修改成功";
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		
		JSONArray jsonArray=JSONArray.fromObject((String)request.getSession().getAttribute("cart"));
		Double sum=0.0;
		List list = new ArrayList<OrderItem>();
        for(Object jstr:jsonArray){
            list.add(jstr);
        	
        	logger.info(JSONObject.fromObject(((JSONObject)jstr).getString("product")).getDouble("pnewprice")+ ">>>>>>>>>>>>>>>>>>>>>>>((JSONObject)jstr).getInt>>>>>>>>>>>>>>>>>>>>>>>>>>");
            sum+=(((JSONObject)jstr).getJSONObject("product")).getDouble("pnewprice")*((JSONObject)jstr).getInt("number");
            }
        logger.info(sum+ ">>>>>>>>>>>>>>>>>>>>>>>((JSONObject)jstr).getInt>>>>>>>>>>>>>>>>>>>>>>>>>>");
		request.setAttribute("cartlist", list);
		request.setAttribute("cartsum", sum);
		
		return "user/cart.jsp";
	}

	public String ajaxlist(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
	/*	try {
			response.getWriter().print((String)request.getSession().getAttribute("cart"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
return "%"+(String)request.getSession().getAttribute("cart");
	}
	
	public String list2session(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		
		
		
		List<OrderItem> items=orderService.listitem(new Order((Userinfo)request.getSession().getAttribute("user"),"-1",1,-1));
		JSONArray jsonArray=JSONArray.fromObject(items);	
		request.getSession().setAttribute("cart",jsonArray.toString());		
		return "%list已存到session";
	}

}
