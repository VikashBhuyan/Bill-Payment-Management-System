package com.masai.entities;

import java.io.Serializable;

public class Consumer extends User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double balance;
    private String username;
    private String password;
    private Billing bill;

    public Consumer(String firstName, String lastName, String address, String email, String phoneNo, double balance, String username, String password) {
        super(firstName, lastName, address, email, phoneNo);
        this.balance = balance;
        this.username = username;
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public Billing getBill() {
        return bill;
    }

    public void setBill(Billing bill) {
        this.bill = bill;
    }
}
