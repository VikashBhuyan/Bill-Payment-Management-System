package com.masai.services;
import java.util.*;
import com.masai.entities.Consumer;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
public interface ConsumerService {
	public boolean login(String username,String password, Map<String, Consumer> customers) throws InvalidDetailsException;
	public void signUp(Consumer cus, Map<String, Consumer> customers) throws DuplicateDataException;
	public boolean addMoneyToWallet(double amount, String username, Map<String, Consumer> customers);

	public double viewWalletBalance(String username, Map<String, Consumer> customers);
	public void deleteCustomer(String username, Map<String, Consumer> customers) throws ProductException;
	public Consumer viewCustomerDetails(String usernmae, Map<String, Consumer> customers);

	public List<Consumer> viewAllCustomers(Map<String, Consumer> customers) throws ProductException;
}
