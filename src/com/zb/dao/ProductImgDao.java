package com.zb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zb.pojo.Product;
import com.zb.pojo.ProductImg;
import com.zb.utill.Util;




public class ProductImgDao {
	
	 public int getTotal() {
	        int total = 0;
	        try (Connection c =Util.getConnection(); Statement s = c.createStatement();) {
	 
	            String sql = "select count(*) from pimg";
	 
	            ResultSet rs = s.executeQuery(sql);
	            while (rs.next()) {
	                total = rs.getInt(1);
	            }
	        } catch (SQLException e) {
	 
	            e.printStackTrace();
	        }
	        return total;
	    }
	 
	 
	 public List<ProductImg> list(Product product) {
	        return list(product, 0, Short.MAX_VALUE);
	    }
	 
	 
	 
	    public List<ProductImg> list(Product product, int start, int count) {
	        List<ProductImg> productImgs = new ArrayList<ProductImg>();
	 
	        String sql = "select * from pimg where pmid =? order by imgid asc limit ?,? ";
	 
	        try (Connection c = Util.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	 
	            ps.setString(1, product.getPid());
	 
	            ps.setInt(2, start);
	            ps.setInt(3, count);
	            
	            
	            
	            ResultSet rs = ps.executeQuery();
	 
	            while (rs.next()) {

	            	ProductImg productImg = new ProductImg();
	   


	                productImg.setProduct(product);
	                productImg.setImgid(rs.getString(1));
	                productImg.setImgurl(rs.getString(2));
	                  
	                productImgs.add(productImg);
	            }
	        } catch (SQLException e) {
	 
	            e.printStackTrace();
	        }
	        return productImgs;
	    }

}
