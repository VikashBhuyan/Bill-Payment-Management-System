package com.masai.utility;

public interface UserNamePass {
	public static String getUserName() {
		int x = (int) (Math.random() * 10000);
		return ("E_Electro"+x);
	}
	public static String getPassWord() {
		int x = (int) (Math.random() * 1000);
		return "eE"+x;
	}
}
