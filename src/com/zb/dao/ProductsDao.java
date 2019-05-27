package com.zb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zb.pojo.Product;
import com.zb.utill.Util;



public class ProductsDao {

	ProductImgDao productImgDao = new ProductImgDao();
	
	  public Product get(String pid) {
	        Product bean = new Product();
	  
	        try (Connection c = Util.getConnection(); Statement s = c.createStatement();) {
	  
	            String sql = "select * from products where pid = " + pid;
	   
	            ResultSet rs = s.executeQuery(sql);
	  
	            if (rs.next()) { 
	               bean.setPid(pid);
	               bean.setPinfo(rs.getString("pinfo"));
	               bean.setPlabel(rs.getString("plabel"));
	               bean.setPname(rs.getString("pname"));
	               bean.setPnewprice(rs.getDouble("pnewprice"));
	               bean.setPoldprice(rs.getDouble("poldprice"));
	               bean.setPstarlevel(rs.getInt("pstarlevel"));
	               bean.setPtype(rs.getString("ptype"));
	               bean.setProductImgs(productImgDao.list(bean));
	            }
	  
	        } catch (SQLException e) {
	  
	            e.printStackTrace();
	        }
	        return bean;
	    }

	public int getTotal(String type) {
		int total = 0;
		try (Connection c = Util.getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from products";
			ResultSet rs;
			if (type == null)
				rs = s.executeQuery(sql);
			else
				rs = s.executeQuery("select count(*) from products where ptype='"+type+"'");

			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	public List<Product> list() {
		return list(0, Short.MAX_VALUE, "all");
	}

	public List<Product> list(int start, int count, String type) {

		List<Product> products = new ArrayList<Product>();
		String sql;

		if (type == null)
			sql = "select * from products limit ?,? ";
		else
			sql = "select * from products where ptype=? limit ?,? ";

		try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			if (type == null) {
				ps.setInt(1, start);
				ps.setInt(2, count);
			} else {
				ps.setString(1, type);
				ps.setInt(2, start);
				ps.setInt(3, count);
			}

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setPid(rs.getString(1));
				product.setPtype(rs.getString(2));
				product.setPname(rs.getString(3));
				product.setPoldprice(rs.getDouble(4));
				product.setPnewprice(rs.getDouble(5));
				product.setPstarlevel(rs.getInt(6));
				product.setPlabel(rs.getString(7));
				product.setPinfo(rs.getString(8));

				product.setProductImgs(productImgDao.list(product));
				products.add(product);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return products;
	}

}
