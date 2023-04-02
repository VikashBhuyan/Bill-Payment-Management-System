package com.masai.utility;
import java.io.*;
import java.util.*;

import com.masai.entities.Billing;
import com.masai.entities.Consumer;
import com.masai.entities.Transaction;
public class FileExist {
//	This class is for deserialaiztion
//	This is for the map of objcets for consumer details....it has Username as key and Consumer Class as value
	public static Map<String, Consumer> consumerFile() {
		
		Map<String, Consumer> cFile = null;

		File f = new File("Consumer.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				
				cFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(cFile);
				return cFile;

			} else {
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				cFile = (Map<String, Consumer>) ois.readObject();

				return cFile;

			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}
		return cFile;

	}
	
	public static Map<String, Billing> billFile() {
	    Map<String, Billing> cFile = null;
	    File f = new File("Billing.ser");
	    boolean flag = false;
	    try {
	        if (!f.exists()) {
	            f.createNewFile();
	            flag = true;
	        }

	        if (flag) {
	            cFile = new LinkedHashMap<>();
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
	            oos.writeObject(cFile);
	        } else {
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	            cFile = (Map<String, Billing>) ois.readObject();
	            if (cFile == null) {
	                cFile = new LinkedHashMap<>();
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return cFile;
	}
	
	public static List<Transaction> transactionFile() {

		List<Transaction> tFile = new ArrayList<>();

		File f = new File("Transactions.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				tFile =  new ArrayList<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(tFile);

				return tFile;

			} else {

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				tFile = (List<Transaction>) ois.readObject();
				return tFile;

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return tFile;

	}
}
