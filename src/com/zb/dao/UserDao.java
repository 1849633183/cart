package com.zb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.zb.pojo.Order;
import com.zb.pojo.Userinfo;
import com.zb.utill.Util;

public class UserDao {
	Util util = new Util();
	
	public Userinfo findUserByEmail(String email) {
		Userinfo userinfo=null;
		try {
			PreparedStatement preparedStatement = util.getConnection()
					.prepareStatement("select * from users where email=?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userinfo=new Userinfo(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("email"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;

		}
		return userinfo;
	}

	public boolean checkUser(String email) {
		try {
			PreparedStatement preparedStatement = util.getConnection()
					.prepareStatement("select * from users where email=?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("结果集不为空!");
				return true;
			} else {
				System.out.println("结果集为空");
				return false;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;

		}
	}

	public boolean register(String email, String name, String password) {
		try {
			PreparedStatement preparedStatement = util.getConnection()
					.prepareStatement("insert into users values(?,?,?,?)");
			preparedStatement.setString(1,UUID.randomUUID().toString().replaceAll("-", ""));
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, email);
			preparedStatement.executeUpdate();
			preparedStatement = util.getConnection()
					.prepareStatement("select * from users where email=?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				new OrderDao().addOrder(new Order(new Userinfo(resultSet.getString("id"),name,email),"-1",1,0));
				return true;
			} else {
				return false;
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;

		}

	}

	public Userinfo login(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = util.getConnection()
				.prepareStatement("select id,name from users where email=? and password=?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		Userinfo user = new Userinfo();
		while (resultSet.next()) {
			System.out.println("id:" + resultSet.getString("id") + "   " + "name:" + resultSet.getString("name"));
			user.setId(resultSet.getString("id"));
			user.setName(resultSet.getString("name"));
			user.setEmail(email);
			user.setPassword("******");

		}
		return user;
	}
	
	 public Userinfo get(String uid) {
		 Userinfo bean = new Userinfo();
	  
	        try (Connection c = Util.getConnection(); Statement s = c.createStatement();) {
	  
	            String sql = "select * from users where uid = " + uid;
	   
	            ResultSet rs = s.executeQuery(sql);
	  
	            if (rs.next()) { 
	             bean.setEmail(rs.getString("email"));
	             bean.setId(uid);
	             bean.setName(rs.getString("name"));
	            }
	  
	        } catch (SQLException e) {
	  
	            e.printStackTrace();
	        }
	        return bean;
	    }

}
