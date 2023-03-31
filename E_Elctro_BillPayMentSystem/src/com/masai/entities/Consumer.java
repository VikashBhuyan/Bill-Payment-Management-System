package com.masai.entities;

import java.io.Serializable;

public class Consumer extends User implements Serializable{

	private double balance;
	private String username;
	private String password;
	
	public Consumer(String firstName, String lastName, String address, String email, String phoneNo,double balance,String username,String password) {
		super( firstName,  lastName,  address,  email,  phoneNo);
		this.balance = balance;
		this.username = username;
		this.password = password;
		// TODO Auto-generated constructor stub
	}
	private Consumer()
	{
		super();
	}
	
	
}
