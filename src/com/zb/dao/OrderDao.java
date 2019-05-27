package com.zb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zb.pojo.Order;
import com.zb.pojo.OrderItem;
import com.zb.utill.Util;

public class OrderDao {
	ProductsDao productsDao = new ProductsDao();
	UserDao userDao = new UserDao();
	
	public Order get(String oid) {
		Order bean = new Order();
	  
	        try (Connection c = Util.getConnection(); Statement s = c.createStatement();) {
	  
	            String sql = "select * from users where oid = " + oid;
	   
	            ResultSet rs = s.executeQuery(sql);
	  
	            if (rs.next()) { 
	             bean.setOid(oid);
	             bean.setSum(rs.getDouble("sum"));
	             bean.setUser(userDao.get(rs.getString("uid")));
	             bean.setStatus(rs.getInt("status"));
	             bean.setItems(listByOrder(bean));
	            }
	  
	        } catch (SQLException e) {
	  
	            e.printStackTrace();
	        }
	        return bean;
	    }
	
	public List<OrderItem> listByOrder(Order order) {
		return listByOrder(0, Short.MAX_VALUE,order);
	}
	
	public List<OrderItem> listByOrder(int start, int count, Order order ) {
		List<OrderItem> items = new ArrayList<OrderItem>();


	
		String	sql = "select * from orderitem where oid =? and uid=? order by oiid asc limit ?,? ";
		

		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, order.getOid());
			ps.setString(2, order.getUser().getId());
				ps.setInt(3, start);
				ps.setInt(4, count);
		

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderItem item = new OrderItem();
				item.setNumber(rs.getInt("number"));
				item.setOiid(rs.getString("oiid"));
				item.setOrder(order);
				item.setProduct(productsDao.get(rs.getString("pid")));
				item.setUser(order.getUser());
				
				items.add(item);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return items;

	}

	public void additem(OrderItem bean) {
		String sql = "insert into orderitem values(?,?,?,?,?)";
		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

		
			ps.setString(1, bean.getOiid());		
			ps.setString(2, bean.getOrder().getOid());
			ps.setString(3, bean.getProduct().getPid());		
			ps.setInt(4, bean.getNumber());
			ps.setString(5, bean.getUser().getId());
			ps.execute();
			System.out.println("add");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void addOrder(Order bean) {
		String sql = "insert into order_ values(?,?,?,?)";
		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, bean.getStatus());
		ps.setString(2, bean.getOid());
		ps.setString(3, bean.getUser().getId());
		ps.setDouble(4, bean.getSum());
			
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean isInCart(OrderItem bean) {
		String sql = "select * from orderitem where oid=? and uid=? and pid=?";
		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, bean.getOrder().getOid());
			ps.setString(2, bean.getOrder().getUser().getId());
			ps.setString(3, bean.getProduct().getPid());
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				System.out.println("结果集不为空!");				
			return true;
			
			} else {
				System.out.println("结果集为空");
				return false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}
	

	
	
	public void updateitem(OrderItem bean){
		String sql = "update orderitem set number=?  where pid= ?";
		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
System.out.println(bean.getNumber()+bean.getOiid());
			ps.setInt(1, bean.getNumber());
			ps.setString(2, bean.getProduct().getPid());
			ps.execute();
			System.out.println("update");
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
	}
	
	public int getCartTotal() {
		int total = 0;
		try (Connection c = Util.getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from orderitem where oid=-1";
			ResultSet rs = s.executeQuery(sql);
		
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	public void deleteCartItemById(String oiid) {
		// TODO Auto-generated method stub
		String sql = "delete from orderitem where oiid=?";
		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, oiid);
		
			ps.execute();
			System.out.println("delete");
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
	}

	public void updateCartItemById(String oiid, int i) {
		// TODO Auto-generated method stub
		String sql = "update orderitem set number=?  where oiid= ?";
		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, i);
			ps.setString(2,oiid);
			ps.execute();
			System.out.println("updateCartItemById");
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
	}

}
