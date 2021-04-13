package com.project.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.project.dao.GenericDao;
import com.project.entities.GeneralDetail;
import com.project.entities.Registration;
import com.project.entities.Transaction;
import com.project.enums.Title;
import com.project.service.ServiceClass;

//to test the methods under ServiceClass 
public class ServiceClassTest {

	@Test
	public void fetchDetailByAadhaarTest() {
		ServiceClass sc =new ServiceClass();
		List<GeneralDetail> detail = new ArrayList<GeneralDetail>();
		detail = sc.getDetailByAadhaar(7777);
		for(GeneralDetail val:detail) {
			System.out.println(val.getAccount().getCustomerId()+" , "+val.getGrossIncome());
		}
	}
	
	@Test
	public void registerUserTest() {
		ServiceClass test = new ServiceClass();
		
		Registration add = new Registration();
		add.setTitle(Title.Mr);
		add.setFirstName("Rohit");
		add.setLastName("Gupta");
		add.setFatherName("Raju");
		add.setMobileNo(123546789);//range problem
		add.setEmailId("rohit@gmail.com");
		add.setAadhaarNo(55415);//range problem
		add.setPanCard("ROHIT1425");
		add.setDateOfBirth(LocalDate.of(2001,10,9));
		add.setResidentialAddress("Kalina, Santacruz, Mumbai 400098");
		add.setPermanent("Mumbai");
		add.setOccupation("Uber Driver Manager");
		add.setIncomeSource("Self");
		add.setAnnualIncome(350000);
		
		test.register(add);
	}
	
	@Test
	public void approvedByAdmin() {
		
		GenericDao dao = new GenericDao();
		
		Registration user = (Registration) dao.fetch(Registration.class, (long)42);
		
		ServiceClass test = new ServiceClass();
		test.onRequestApprove(user, "rahul@123", "rahul@123tr", 7000);
	}
	
	@Test
	public void transactionTest() {
		ServiceClass test = new ServiceClass();
		
		test.transaction((long)1041, (long)1040, 800);
	}
	
	
	
	@Test
	public void displayStatement() {
		
		ServiceClass test = new ServiceClass();
		
		List<Transaction> list = test.getTransactionsOfUser(1041);
		for(Transaction trx : list) {
			System.out.println(trx.getFromAccount().getAccountNumber()+" , "+trx.getToAccount().getAccountNumber()+" , "+trx.getAmount()+" , "+trx.getModeOfTransaction());
		}
	}
	
	@Test
	public void displayStatementByRange() {
		
		ServiceClass test = new ServiceClass();
		
		List<Transaction> list = test.getTransactionsOfUserByRange(LocalDateTime.of(LocalDate.of(2021,04,11), LocalTime.of(00,00)),LocalDateTime.of(LocalDate.of(2021,04,14), LocalTime.of(23,59)));
		for(Transaction trx : list) {
			System.out.println(trx.getFromAccount().getAccountNumber()+" , "+trx.getToAccount().getAccountNumber()+" , "+trx.getAmount()+" , "+trx.getModeOfTransaction());
		}
	}

}

