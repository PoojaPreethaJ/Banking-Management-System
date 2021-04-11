package com.project.service;

import java.util.List;

import com.project.entities.RegistrationDetail;
import com.project.entities.UserGeneralDetail;
import com.project.entities.UserTransactionDetail;

public interface Service {

	public void register(RegistrationDetail userRegistration);
	public void onRequestApprove(RegistrationDetail userRegistration, String loginPassword, String transactionPassword, double amount);
	public void transaction(long fromAccount, long toAccount, double amount);
	public List<UserTransactionDetail> getTransactionsOfUser(long accountNo);
	public List<UserGeneralDetail> getDetailByAadhaar(long aadhaar);
}
