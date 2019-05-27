package com.zb.service;

import java.util.List;


import com.zb.dao.OrderDao;
import com.zb.pojo.Order;
import com.zb.pojo.OrderItem;

public class OrderService {
	OrderDao orderDao=new OrderDao();
	public void addcartitem(OrderItem bean){
		try {
			if(orderDao.isInCart(bean))
				orderDao.updateitem(bean);
			else 
				orderDao.additem(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<OrderItem> listitem(Order order){
		return orderDao.listByOrder(order);
	}
	public void deleteCartItem(String oiid) {
		orderDao.deleteCartItemById(oiid);
		// TODO Auto-generated method stub
		
	}
	public void updateCartItem(String oiid, int i) {
		// TODO Auto-generated method stub
		orderDao.updateCartItemById(oiid,i);
		
	}

}
