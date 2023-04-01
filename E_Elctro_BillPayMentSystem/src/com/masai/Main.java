package com.masai;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.utility.Admin;
import com.masai.utility.FileExist;
import com.masai.entities.Consumer;
import com.masai.exceptions.BillingException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.services.ConsumerService;
import com.masai.services.ConsumerServiceImp;

import java.io.*;
import java.time.LocalDate;

import com.masai.entities.Billing;
public class Main {

	public static void adminFunctionality(Scanner sc, Map<String, Consumer> consumer, List<Billing> bill) throws InvalidDetailsException
	{
		ConsumerService cus = new ConsumerServiceImp();
		adminLogin(sc);
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 Create Billing Cycle for Consumer");
				System.out.println("Press 2 Get Customer Detail");
				System.out.println("Press 3 Get All Consumer");
				System.out.println("Press 4 Delete A Consumer");
				System.out.println("Press 5 Log Out");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					createBillForConsumer(consumer,bill,sc);
					break;
				case 2:
					getCustomerDetail(sc,cus,consumer);
					break;
				case 3:
					getAllCustomerDetails(cus,consumer);
					break;
				case 4:
					deleteACustomer(cus,sc,consumer);
					break;
				case 5:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void deleteACustomer(ConsumerService cus,Scanner sc,Map<String,Consumer> consumer) throws ProductException {
		System.out.println("Enter the username of cus to be deleted");
		String username = sc.next();
		cus.deleteCustomer(username,consumer);
	}
	
	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(Admin.username) && password.equals(Admin.password)) {

			System.out.println("successfully login");
		} else {
			throw new InvalidDetailsException("Invalid Admin Credentials");
		}
	}
	
	public static void getCustomerDetail(Scanner sc,ConsumerService cus, Map<String, Consumer> consumer) {
		System.out.println("Enter Consumer email");
		String username = sc.next();
		Consumer oneCus = cus.viewCustomerDetails(username,consumer);
		System.out.println("Firstname : " + oneCus.getFirstName());
		System.out.println("Lastname : " + oneCus.getLastName());
		System.out.println("address : " + oneCus.getAddress());
		System.out.println("email : " + oneCus.getEmail());
		System.out.println("username : " + oneCus.getUsername());
		System.out.println("password : " + oneCus.getPassword());
		System.out.println("phone : " + oneCus.getPhoneNo());
		System.out.println("wallet balance : " + oneCus.getBalance());
	}
	
	public static void getAllCustomerDetails(ConsumerService cus,Map<String,Consumer> consumer) throws ProductException{
		List<Consumer> li = cus.viewAllCustomers(consumer);
		for(Consumer x : li)
		{
			System.out.println(x);
		}
	}
	
	public static void createBillForConsumer(Map<String, Consumer> consumer, List<Billing> bill, Scanner sc) {
	    System.out.println("Enter Consumer's UserName");
	    String username = sc.next();
	    Consumer currConsumer = consumer.get(username);
	    if (currConsumer != null) {
	        System.out.println("Enter Fixed Price: ");
	        double fixedPrice = sc.nextDouble();
	        System.out.println("Enter Number of Units COnsumed: ");
	        int unitsConsumed = sc.nextInt();
	        System.out.println("Enter Adjustment if Any(Enter 0 is none): ");
	        double adjustment = sc.nextDouble();
	        LocalDate startDate = LocalDate.now();
	        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // set end date to the last day of the month
	        boolean isPaid = false;
	        Billing billforCunsumer = new Billing(fixedPrice, unitsConsumed, adjustment, startDate, endDate, isPaid);
	        bill.add(billforCunsumer);	
	        ((Consumer) consumer).setBill(billforCunsumer);
	        System.out.println("Bill created successfully for consumer with Phone Number: " + username);
	    } else {
	        System.out.println("Consumer not found for Phone Number: " + username);
	    }
	}
	
	public static void consumerFunctionality(Scanner sc, Map<String,Consumer> consumer, List<Billing> bill) throws InvalidDetailsException, BillingException{
		ConsumerService cus = new ConsumerServiceImp();
		
		System.out.println("please enter the following details to login");
		System.out.println("please enter the username provided by system");
		String username = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		consumerLogin(username ,pass, consumer, cus);
	}
	
	public static void consumerLogin(String username,String pass, Map<String, Consumer> customers, ConsumerService cusService)
			throws InvalidDetailsException {
		cusService.login(username, pass,customers);
		System.out.println("Customer has successfully login");

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
//		Deserialazing...If we are doing it for first time then create as well
		Map<String, Consumer> consumer = FileExist.consumerFile();
		List<Billing> bill = FileExist.billFile();
		
		
		System.out.println("Welcome , to E-Elctro Bill Management System");
		try {

			int preference = 0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin login , '2' --> Consumer login , "
				+ "'3' for Customer signup, '0' for exit");
				preference = sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc,consumer,bill);
					break;
				case 2:
					consumerFunctionality(sc,consumer,bill);
					break;

				case 3:
					System.out.println("You have chosen Consumer Signup");
					break;

				case 0:
					System.out.println("successfully existed from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}
				

			}

			while (preference != 0);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		} 
		finally {
			// serialization (finally always executed)
			try {
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Consumer.ser"));
				coos.writeObject(consumer);

				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Billing.ser"));
				toos.writeObject(bill);
			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
	}

}
