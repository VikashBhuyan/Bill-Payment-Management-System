package com.masai.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.masai.entities.Consumer;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;

public class ConsumerServiceImp implements ConsumerService{

	@Override
	public boolean login(String username, String password, Map<String, Consumer> customers)
			throws InvalidDetailsException {
		// TODO Auto-generated method stub
		if (customers.containsKey(username) ) {
			
			if(customers.get(username).getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid Credentials");
			}
			
		} else {
			throw new InvalidDetailsException("you have not sign up yet, please signup");
		}
	}

	@Override
	public void signUp(Consumer cus, Map<String, Consumer> customers) throws DuplicateDataException {
		// TODO Auto-generated method stub
		if (customers.containsKey(cus.getUsername())) {
			throw new DuplicateDataException("Customer already exists , please login");
		} else {

			customers.put(cus.getUsername(), cus);

		}
	}

	@Override
	public boolean addMoneyToWallet(double amount, String username, Map<String, Consumer> customers) {
		// TODO Auto-generated method stub
		Consumer cus = customers.get(username);

		cus.setBalance(cus.getBalance() + amount);

		customers.put(username, cus);

		return true;
	}

	@Override
	public double viewWalletBalance(String username, Map<String, Consumer> customers) {
		// TODO Auto-generated method stub
		Consumer cus = customers.get(username);

		return cus.getBalance();
//		return 0;
	}

	@Override
	public Consumer viewCustomerDetails(String username, Map<String, Consumer> customers) {
		// TODO Auto-generated method stub
		if (customers.containsKey(username)) {

			return customers.get(username);

		}
		return null;
	}

	@Override
	public List<Consumer> viewAllCustomers(Map<String, Consumer> customers) throws ProductException {
		// TODO Auto-generated method stub
		List<Consumer> list = null;

		if (customers != null && customers.size() > 0) {
			Collection<Consumer> coll = customers.values();
			list = new ArrayList<>(coll);
		} else {
			throw new ProductException("Customer list is empty");
		}
		
		return list;
	}

	@Override
	public void deleteCustomer(String username, Map<String, Consumer> consumer) throws ProductException {
		// TODO Auto-generated method stub
		if (consumer != null && consumer.size() > 0) {

			if (consumer.containsKey(username)) {
				consumer.remove(username);
				System.out.println("Consumer deleted successfully");

			} else {
				throw new ProductException("Consumer not found");
			}

		} else {
			throw new ProductException("No Consumer");
		}
	}

}
