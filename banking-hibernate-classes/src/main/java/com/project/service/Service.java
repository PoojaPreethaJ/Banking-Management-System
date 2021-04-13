package com.project.service;

import java.util.List;

import com.project.entities.GeneralDetail;
import com.project.entities.Registration;
import com.project.entities.Transaction;

public interface Service {

	public void register(Registration userRegistration);
	public void onRequestApprove(Registration userRegistration, String loginPassword, String transactionPassword, double amount);
	public void transaction(long fromAccount, long toAccount, double amount);
	public List<Transaction> getTransactionsOfUser(long accountNo);
	public List<GeneralDetail> getDetailByAadhaar(long aadhaar);
}
