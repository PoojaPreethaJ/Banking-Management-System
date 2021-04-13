package com.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.GenericDao;
import com.project.dao.SpecificDao;
import com.project.entities.Account;
import com.project.entities.AccountDetail;
import com.project.entities.GeneralDetail;
import com.project.entities.Registration;
import com.project.entities.Transaction;
import com.project.enums.AccountType;
import com.project.enums.TransactionType;

//This class is used to add the business logic 
//use get before method name to avoid confusion
public class ServiceClass implements Service{

	

	public void register(Registration userRegistration) {

		GenericDao dao = new GenericDao();
		dao.save(userRegistration);
		
	}

	public void onRequestApprove(Registration userRegistration,String loginPassword, String transactionPassword, double amount) {
		
		GenericDao dao= new GenericDao();
		
		Account ud = new Account();
		ud.setLoginPassword(loginPassword);
		ud.setTransactionPassword(transactionPassword);
		ud.setRegistration(userRegistration);//fk
		
		userRegistration.setAccount(ud);//that side mapping
		//dao.save(ud);//no needed due to cascading
		
		AccountDetail account = new AccountDetail();
		account.setAccountType(AccountType.Savings);
		account.setBankBalance(amount);
		account.setAccount(ud);//fk
		
		List<AccountDetail> accounts = new ArrayList<AccountDetail>();
		accounts.add(account);
		ud.setAccounts(accounts);//that side mapping
		//dao.save(account);//no needed due to cascading
		
		GeneralDetail details = new GeneralDetail();
		details.setAadhaarNo(userRegistration.getAadhaarNo());//pk
		details.setAccount(ud);//fk
		details.setFullName(userRegistration.getFirstName()+" "+userRegistration.getLastName());
		details.setDateOfBirth(userRegistration.getDateOfBirth());
		details.setMailingAddress(userRegistration.getResidentialAddress());
		details.setPanCard(userRegistration.getPanCard());
		details.setOccupation(userRegistration.getOccupation());
		details.setGrossIncome(userRegistration.getAnnualIncome());
		
		ud.setGeneralDetail(details);//that side mapping
		
		dao.save(ud);//due to cascading details will be saved in child tables automatically
	}

	public void transaction(long fromAccount, long toAccount, double amount) {
		
		GenericDao dao = new GenericDao();
		
		AccountDetail acc1 = (AccountDetail) dao.fetch(AccountDetail.class, fromAccount);
		AccountDetail acc2 = (AccountDetail) dao.fetch(AccountDetail.class, toAccount);
		
		if(amount>acc1.getBankBalance())
			System.out.println("Insufficient Balance");
		else {
			acc1.setBankBalance(acc1.getBankBalance()-amount);
			dao.save(acc1);
			
			//Setting transaction for the sender.
			Transaction trx = new Transaction();
			trx.setFromAccount(acc1);
			trx.setToAccount(acc2);
			trx.setAmount(amount);
			trx.setTransactionDate(LocalDateTime.now());
			trx.setMaturityInstruction("xyz");
			trx.setModeOfTransaction(TransactionType.NEFT);
			trx.setRemarks("Gift Balance Debit");
			trx.setStatus("Transaction Successful");
			
			List<Transaction> t = new ArrayList<Transaction>();
			t.add(trx);
			
			acc1.setFromTransactions(t);
			acc2.setToTransactions(t);
			
			dao.save(trx);
			
			//Setting transaction for the receiver.
			
			acc2.setBankBalance(acc2.getBankBalance()+amount);
			dao.save(acc2);
			
			Transaction trx1 = new Transaction();
			trx1.setToAccount(acc2);
			trx1.setFromAccount(acc1);
			trx1.setAmount(amount);
			trx1.setTransactionDate(LocalDateTime.now());
			trx1.setMaturityInstruction("xyz");
			trx1.setModeOfTransaction(TransactionType.NEFT);
			trx1.setRemarks("Gift Balance Credit");
			trx1.setStatus("Transaction Successful");
			
			List<Transaction> t1 = new ArrayList<Transaction>();
			t1.add(trx1);
			
			acc1.setFromTransactions(t1);
			acc2.setToTransactions(t1);
			
			dao.save(trx1);
			
			
		}
	}

	public List<Transaction> getTransactionsOfUser(long accountNo) {
		
		SpecificDao dao = new SpecificDao();
		
		List<Transaction> list = dao.fetchTransactions(accountNo);
		return list;
	}
	
	public List<Transaction> getTransactionsOfUserByRange(LocalDateTime fromDate,LocalDateTime toDate) {
		
		SpecificDao dao = new SpecificDao();
		
		List<Transaction> list = dao.fetchTransactionsByRange(fromDate,toDate);
		return list;
	}
	
	public List<GeneralDetail> getDetailByAadhaar(long aadhaar){
		SpecificDao dao = new SpecificDao();
		return dao.fetchUserDetails(aadhaar);	
	}
	
	public List<AccountDetail> getSuspiciousAccounts(double amount){
		SpecificDao dao = new SpecificDao();
		return dao.fetchAccountsByTransactionAmount(amount);
	}
}
