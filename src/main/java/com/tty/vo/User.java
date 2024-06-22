package com.tty.vo;

public class User {
	private int id;
	private String name;
	private String pwd;
	private int priv;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getPriv() {
		return priv;
	}
	public void setPriv(int priv) {
		this.priv = priv;
	}
	public User(int id, String name, String pwd, int priv) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.priv = priv;
	}
	
}
