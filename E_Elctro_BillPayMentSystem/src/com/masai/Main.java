package com.masai;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome , to E-Elctro Bill Management System");
		try {

			int preference = 0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin login , '2' --> Consumer login , "
				+ "'3' for Customer signup, '0' for exit");
				preference = sc.nextInt();
				switch (preference) {
				case 1:
					System.out.println("You have chosen Admin Login");
					break;
				case 2:
					System.out.println("You have chosen Consumer Login");
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
	}

}
