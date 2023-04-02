package com.masai.services;

import java.util.ArrayList;
import java.util.List;

import com.masai.entities.Transaction;
import com.masai.exceptions.TransactionException;

public class TransactionServiceImp implements TransactionService {

	@Override
	public List<Transaction> viewCustomerTransactions(String username, List<Transaction> transactions)
			throws TransactionException {
		List<Transaction> myTransactions = new ArrayList<>();

		boolean flag = false;
		for (Transaction tr : transactions) {
			if (tr.getUsername().equals(username)) {

				myTransactions.add(tr);

				flag = true;
			}
		}
		if (!flag) {
			throw new TransactionException("you hav not done any transaction yet");
		}

		return myTransactions;
	}

}
