package com.zb.pojo;

import java.util.List;

public class OrderItem {
	String oiid;
	Order order;
	Product product;
	Userinfo user;
	int number;

	
	public String getOiid() {
		return oiid;
	}
	public void setOiid(String oiid) {
		this.oiid = oiid;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Userinfo getUser() {
		return user;
	}
	public void setUser(Userinfo user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "OrderItem [oiid=" + oiid + ", order=" + order + ", product=" + product + ", user=" + user + ", number="
				+ number + "]";
	}

	
	
	
}
