package com.project.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.project.dao.SpecificDao;
import com.project.entities.GeneralDetail;
import com.project.entities.Payee;
import com.project.entities.Transaction;

/*
 * To test all the methods under specificDao
 */
public class QueryDao {

	@Test
	public void fetchBalanceUsingAccountNumber() {
		SpecificDao dao = new SpecificDao();
		double bal = dao.fetchBalance(1085);
		assertNotNull(bal);
		System.out.println(bal);
	}
	
	@Test
	public void fetchSuccessfulTransactionTest() {
		SpecificDao dao = new SpecificDao();
		List<Transaction> list =dao.fetchTransactions(101);
		assertNotNull(list);
		for(Transaction val:list) {
			System.out.println(val.getModeOfTransaction()+" , "+val.getFromAccount().getAccountNumber()+" , "+val.getToAccount().getAccountNumber()+" , "+val.getStatus());
		}
	}
	
	@Test
	public void fetchDetailTest() {
		SpecificDao dao = new SpecificDao();
		List<GeneralDetail> list =dao.fetchUserDetails(7777);
		assertNotNull(list);
		for(GeneralDetail val:list) {
			System.out.println(val.getGrossIncome()+" , "+val.getOccupation()+" , "+val.getAccount().getCustomerId());
		}
	}
	
	@Test
	public void fetchBeneficiaryDetailTest() {
		SpecificDao dao = new SpecificDao();
		List<Payee> list =dao.fetchBeneficiaryDetails(1000);
		assertNotNull(list);
		for(Payee val:list) {
			System.out.println(val.getCompoundKey().getBeneficiaryAccount()+" , "+val.getBeneficiaryName()+" , "+val.getNickName());
		}
	}
}
