package com.project.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.project.dao.SpecificDao;
import com.project.entities.UserAddPayee;
import com.project.entities.UserGeneralDetail;
import com.project.entities.UserTransactionDetail;

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
		List<UserTransactionDetail> list =dao.fetchTransactions(101);
		assertNotNull(list);
		for(UserTransactionDetail val:list) {
			System.out.println(val.getModeOfTransaction()+" , "+val.getFromAccount().getAccountNumber()+" , "+val.getToAccount().getAccountNumber()+" , "+val.getStatus());
		}
	}
	
	@Test
	public void fetchDetailTest() {
		SpecificDao dao = new SpecificDao();
		List<UserGeneralDetail> list =dao.fetchUserDetails(7777);
		assertNotNull(list);
		for(UserGeneralDetail val:list) {
			System.out.println(val.getGrossIncome()+" , "+val.getOccupation()+" , "+val.getCustomerId().getCustomerId());
		}
	}
	
	@Test
	public void fetchBeneficiaryDetailTest() {
		SpecificDao dao = new SpecificDao();
		List<UserAddPayee> list =dao.fetchBeneficiaryDetails(1000);
		assertNotNull(list);
		for(UserAddPayee val:list) {
			System.out.println(val.getCompoundKey().getBeneficiaryAccountNo()+" , "+val.getBeneficiaryName()+" , "+val.getNickName());
		}
	}
}
