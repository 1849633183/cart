package com.zb.pojo;

import java.util.List;

public class Userinfo {
	private String id;
	private String name;
	private String password;
	private String email;
	private int Ustatus;
	 private String statusStr;
	  private List<Role> roles;
	
	public Userinfo() {
		// TODO Auto-generated constructor stub
	}
	
	public Userinfo(String id,String name,String email) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.email=email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return Ustatus;
	}

	public void setStatus(int Ustatus) {
		Ustatus = Ustatus;
	}
	  public String getStatusStr() {
	        //状态0 未开启 1 开启
	        if (Ustatus == 0) {
	            statusStr = "未开启";
	        } else if (Ustatus == 1) {
	            statusStr = "开启";
	        }
	        return statusStr;
	    }

	    public void setStatusStr(String statusStr) {
	        this.statusStr = statusStr;
	    }

	    public List<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(List<Role> roles) {
	        this.roles = roles;
	    }

		@Override
		public String toString() {
			return "Userinfo [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", Ustatus="
					+ Ustatus + ", statusStr=" + getStatusStr() + ", roles=" + roles + "]";
		}

	

}
