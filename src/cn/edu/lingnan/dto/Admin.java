package cn.edu.lingnan.dto;

import java.util.Date;

public class Admin {
	private String aid; //用户编号
	private String username; //用户账户
	private String password;//用户密码
	
	
	
	
	public Admin() {
		super();
	}
	
	public Admin(String aid, String username, String password) {
		super();
		this.aid = aid;
		this.username = username;
		this.password = password;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
