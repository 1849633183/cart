package com.zb.pojo;

import java.util.List;

public class Order {
	String oid;
	Userinfo user;
	double sum;
	int status;
	List<OrderItem> items; 
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(Userinfo user,String oid,int status,double sum) {
		// TODO Auto-generated constructor stub
		this.user=user;
		this.oid=oid;
		this.status=status;
		this.sum=sum;
	}
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Userinfo getUser() {
		return user;
	}
	public void setUser(Userinfo user) {
		this.user = user;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", user=" + user + ", sum=" + sum + ", status=" + status + ", items=" + items
				+ "]";
	}
	
	

}
