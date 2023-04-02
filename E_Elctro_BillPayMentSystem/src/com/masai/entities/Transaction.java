package com.masai.entities;

import java.io.Serializable;

public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String username;
	 private String password;
	 private String email;
	 private double unit;
	 private double total;
	 
	 
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getUnit() {
		return unit;
	}


	public void setUnit(double unit) {
		this.unit = unit;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Transaction(String username, String password, String email, double unit, double total) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.unit = unit;
		this.total = total;
	}
	 
}
