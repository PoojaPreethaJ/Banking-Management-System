package com.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.GenericDao;
import com.project.dao.SpecificDao;
import com.project.entities.RegistrationDetail;
import com.project.entities.UserAccountDetail;
import com.project.entities.UserAccountType;
import com.project.entities.UserGeneralDetail;
import com.project.entities.UserTransactionDetail;
import com.project.enums.AccountType;
import com.project.enums.TransactionType;

//This class is used to add the business logic 
//use get before method name to avoid confusion
public class ServiceClass implements Service{

	

	public void register(RegistrationDetail userRegistration) {

		GenericDao dao = new GenericDao();
		dao.save(userRegistration);
		
	}

	public void onRequestApprove(RegistrationDetail userRegistration,String loginPassword, String transactionPassword, double amount) {
		
		GenericDao dao= new GenericDao();
		
		UserAccountDetail ud = new UserAccountDetail();
		ud.setLoginPassword(loginPassword);
		ud.setTransactionPassword(transactionPassword);
		ud.setRegistration(userRegistration);
		
		userRegistration.setUserAccountDetail(ud);
		dao.save(ud);
		
		UserAccountType account = new UserAccountType();
		account.setAccountType(AccountType.Savings);
		account.setBankBalance(amount);
		account.setCustomerId(ud);
		dao.save(account);
		
		UserGeneralDetail details = new UserGeneralDetail();
		details.setAadhaarNo(userRegistration.getAadhaarNo());
		details.setCustomerId(ud);
		details.setDateOfBirth(userRegistration.getDateOfBirth());
		details.setMailingAddress(userRegistration.getResidentialAddress());
		details.setPanCard(userRegistration.getPanCard());
		details.setOccupation(userRegistration.getOccupation());
		details.setGrossIncome(userRegistration.getAnnualIncome());
		
		dao.save(details);
	}

	public void transaction(long fromAccount, long toAccount, double amount) {
		
		GenericDao dao = new GenericDao();
		
		UserAccountType acc1 = (UserAccountType) dao.fetch(UserAccountType.class, fromAccount);
		UserAccountType acc2 = (UserAccountType) dao.fetch(UserAccountType.class, toAccount);
		
		if(amount>acc1.getBankBalance())
			System.out.println("Insufficient Balance");
		else {
			acc1.setBankBalance(acc1.getBankBalance()-amount);
			dao.save(acc1);
			
			//Setting transaction for the sender.
			UserTransactionDetail trx = new UserTransactionDetail();
			trx.setFromAccount(acc1);
			trx.setToAccount(acc2);
			trx.setAmount(amount);
			trx.setTransactionDate(LocalDateTime.now());
			trx.setMaturityInstruction("xyz");
			trx.setModeOfTransaction(TransactionType.NEFT);
			trx.setRemarks("Gift Balance");
			trx.setStatus("Transaction Successful");
			
			List<UserTransactionDetail> t = new ArrayList<UserTransactionDetail>();
			t.add(trx);
			
			acc1.setFromTransaction(t);
			acc2.setToTransaction(t);
			
			dao.save(trx);
			
			//Setting transaction for the receiver.
			
			acc2.setBankBalance(acc2.getBankBalance()+amount);
			dao.save(acc2);
			
			UserTransactionDetail trx1 = new UserTransactionDetail();
			trx1.setFromAccount(acc2);
			trx1.setToAccount(acc1);
			trx1.setAmount(amount);
			trx1.setTransactionDate(LocalDateTime.now());
			trx1.setMaturityInstruction("xyz");
			trx1.setModeOfTransaction(TransactionType.NEFT);
			trx1.setRemarks("Gift Balance");
			trx1.setStatus("Transaction Successful");
			
			List<UserTransactionDetail> t1 = new ArrayList<UserTransactionDetail>();
			t1.add(trx1);
			
			acc2.setFromTransaction(t1);
			acc1.setToTransaction(t1);
			
			dao.save(trx1);
			
			
		}
	}

	public List<UserTransactionDetail> getTransactionsOfUser(int accountNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<UserGeneralDetail> getDetailByAadhaar(long aadhaar){
		SpecificDao dao = new SpecificDao();
		return dao.fetchUserDetails(aadhaar);	
	}
}
