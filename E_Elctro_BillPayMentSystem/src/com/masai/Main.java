package com.masai;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.utility.Admin;
import com.masai.utility.FileExist;
import com.masai.utility.UserNamePass;
import com.masai.entities.Consumer;
import com.masai.entities.Transaction;
import com.masai.exceptions.BillingException;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;
import com.masai.services.ConsumerService;
import com.masai.services.ConsumerServiceImp;
import com.masai.services.TransactionService;
import com.masai.services.TransactionServiceImp;

import java.io.*;
import java.time.LocalDate;

import com.masai.entities.Billing;
public class Main {

	public static void adminFunctionality(Scanner sc, Map<String, Consumer> consumer, Map<String, Billing> bill) throws InvalidDetailsException
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
					return;
//					break;
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
		System.out.println("unit consumed : "+ oneCus.getBill().getUnitsConsumed());
	}
	
	public static void getAllCustomerDetails(ConsumerService cus,Map<String,Consumer> consumer) throws ProductException{
		List<Consumer> li = cus.viewAllCustomers(consumer);
		for(Consumer x : li)
		{
			System.out.println(x);
		}
	}
	
	public static void createBillForConsumer(Map<String, Consumer> consumer, Map<String, Billing> bill, Scanner sc) {
		System.out.println("Enter Consumer's UserName");
	    String username = sc.next();
	    Consumer currConsumer = consumer.get(username);
	    if (currConsumer != null) {
	        System.out.println("Enter Number of Units Consumed: ");
	        int unitsConsumed = sc.nextInt();
	        System.out.println("Enter Additional Price: ");
	        double additionalPrice = sc.nextDouble();
	        System.out.println("Enter fixed Charge: ");
	        double fixCharge = sc.nextDouble();
	        System.out.println("Enter the start Date");
	        LocalDate startDate = LocalDate.now();
	        LocalDate endDate = LocalDate.now(); 
	        boolean isPaid = false;
	        Billing billforConsumer = new Billing(fixCharge, unitsConsumed, additionalPrice, startDate, endDate, isPaid);
	        System.out.println(billforConsumer);
	        bill.put(username, billforConsumer);    
	        currConsumer.setBill(billforConsumer);
	        System.out.println("Bill created successfully for consumer with Phone Number: " + username);
	    } else {
	        System.out.println("Consumer not found for Username: " + username);
	    }
	}
	
	public static void consumerFunctionality(Scanner sc, Map<String,Consumer> consumer, Map<String, Billing> bill, List<Transaction> tran) throws InvalidDetailsException, BillingException{
		ConsumerService cus = new ConsumerServiceImp();
		TransactionService trnsactionService = new TransactionServiceImp();
		System.out.println("please enter the following details to login");
		System.out.println("please enter the username provided by system");
		String username = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		consumerLogin(username ,pass, consumer, cus);
		
		try {
			int choice = 0;
			do {
				System.out.println("");
				System.out.println("Press 1 to add money to a wallet");
				System.out.println("Press 2 view wallet balance");
				System.out.println("Press 3 view my details");
				System.out.println("Press 4 view my transactions");
				System.out.println("Press 5 to Pay Your Bill");
				System.out.println("Press 6 to logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String moneyAdded = customerAddMoneyToWallet(sc, username, consumer, cus);
					System.out.println(moneyAdded);
					break;
				case 2:
					double walletBalance = customerViewWalletBalance(username, consumer, cus);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 3:
					customerViewMyDetails(username, consumer, cus);
					break;
				case 4:
					customerViewCustomerTransactions(username, tran, trnsactionService);
					break;
				case 5:
					payYourBill(sc,username,bill,tran,consumer);
					break;
				case 6:
					System.out.println("you have successsfully logout");
					return;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void payYourBill(Scanner sc,String username, Map<String, Billing> bill, List<Transaction> tran,Map<String,Consumer> consumer) {
		Billing b = bill.get(username);
		if(b != null)
		{
			
			double billAmount = b.getFixedCharge() + (b.getUnitsConsumed()*10)+ b.getAdjustment();
			System.out.println("Pay Bill : Press 1 \n Back to Menu : 0");
			int n = sc.nextInt();
			if(n==1)
			{
				Consumer cus1 = consumer.get(username);
				if(billAmount > cus1.getBalance())
				{
					System.out.println("Insufficient Balance");
					return;
				}
				else
				{
					b.setPaid(true);
					bill.put(username, b);
					cus1.setBalance(cus1.getBalance()-billAmount);
					
					consumer.put(username, cus1);
					System.out.println("Billed Paid for date starting from "+b.getStartDate().toString()+" and end date : "+ b.getEndDate().toString());
					System.out.println("The Balance : "+cus1.getBalance());
				}
			}
			else
			{
				return;
			}
		}
		else
		{
			System.out.println("Admin has not assigned the bill yet");
			return;
		}
	}
	
	public static void customerViewCustomerTransactions(String username, List<Transaction > tran, TransactionService trnsactionService) throws TransactionException {
		List<Transaction> myTransactions = trnsactionService.viewCustomerTransactions(username, tran);

		for (Transaction tr : myTransactions) {
			System.out.println(tr);
		}
	}
	
	public static void customerViewMyDetails(String username, Map<String,Consumer> consumer, ConsumerService cus)
	{
		Consumer cus1 = cus.viewCustomerDetails(username, consumer);
		System.out.println("name is : " + cus1.getFirstName()+" "+cus1.getLastName());
		System.out.println("address : " + cus1.getAddress());
		System.out.println("email : " + cus1.getEmail());
		System.out.println("wallet balance : " + cus1.getBalance());
	}
	
	public static double customerViewWalletBalance(String username, Map<String,Consumer> consumer, ConsumerService cus) {
		double walletBalance = cus.viewWalletBalance(username, consumer);
		return walletBalance;
	}
	
	public static String customerAddMoneyToWallet(Scanner sc,String username , Map<String,Consumer> consumer, ConsumerService cus ) {
		System.out.println("please enter the amount");
		double money = sc.nextDouble();
		boolean added = cus.addMoneyToWallet(money, username, consumer);

		return "Amount: " + money + " successfully added to your wallet";
	}
	
	public static void consumerLogin(String username,String pass, Map<String, Consumer> customers, ConsumerService cusService)
			throws InvalidDetailsException {
		cusService.login(username, pass,customers);
		System.out.println("Customer has successfully login");

	}
	
	public static void consumerSignUp(Scanner sc, Map<String,Consumer> consumer) throws DuplicateDataException
	{
		System.out.println("please enter the following details to Signup");
		System.out.println("please enter the firstName");
		String firstName = sc.next();
		System.out.println("Enter the lastName");
		String lastName = sc.next();
		System.out.println("enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Eneter phone no");
		String phoneNo = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		String username  = 	UserNamePass.getUserName();
		String password = UserNamePass.getPassWord();
		Consumer cus = new Consumer(firstName, lastName, address, email,phoneNo,balance,username,password);

		ConsumerService cusService = new ConsumerServiceImp();
		cusService.signUp(cus, consumer);
		System.out.println("customer has Succefully sign up");
		System.out.println("System Generated Username : "+username+ " Password : "+password);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Map<String, Consumer> consumer = FileExist.consumerFile();
//		System.out.println(consumer);
		Map<String, Billing> bill = FileExist.billFile();
//		System.out.println(bill);
		List<Transaction> tran = FileExist.transactionFile();
//		System.out.println(tran);
		
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
					consumerFunctionality(sc,consumer,bill,tran);
					break;

				case 3:
					consumerSignUp(sc, consumer);
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
				
				ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("Billing.ser"));
				boos.writeObject(bill);
				
				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
				toos.writeObject(tran);
			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
	}

}
