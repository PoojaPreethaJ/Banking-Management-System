package com.project.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.project.dao.GenericDao;
import com.project.entities.RegistrationDetail;
import com.project.entities.UserGeneralDetail;
import com.project.entities.UserTransactionDetail;
import com.project.enums.Title;
import com.project.service.ServiceClass;

//to test the methods under ServiceClass 
public class ServiceClassTest {

	@Test
	public void fetchDetailByAadhaarTest() {
		ServiceClass sc =new ServiceClass();
		List<UserGeneralDetail> detail = new ArrayList<UserGeneralDetail>();
		detail = sc.getDetailByAadhaar(7777);
		for(UserGeneralDetail val:detail) {
			System.out.println(val.getCustomerId().getCustomerId()+" , "+val.getGrossIncome());
		}
	}
	
	@Test
	public void registerUserTest() {
		ServiceClass test = new ServiceClass();
		
		RegistrationDetail add = new RegistrationDetail();
		add.setTitle(Title.Mr);
		add.setFirstName("Pooja");
		add.setLastName("Preetha");
		add.setFatherName("Jothiappan");
		add.setMobileNo(123456789);//range problem
		add.setEmailId("pooja@gmail.com");
		add.setAadhaarNo(55555);//range problem
		add.setPanCard("POOJ2398");
		add.setDateOfBirth(LocalDate.of(1998,02,23));
		add.setResidentialAddress("Basavanagar, Bangalore 560037");
		add.setPermanent("Bangalore");
		add.setOccupation("full stack developer");
		add.setIncomeSource("Self");
		add.setAnnualIncome(500000);
		
		test.register(add);
	}
	
	@Test
	public void approvedByAdmin() {
		
		GenericDao dao = new GenericDao();
		
		RegistrationDetail user = (RegistrationDetail) dao.fetch(RegistrationDetail.class, (long)39);
		
		ServiceClass test = new ServiceClass();
		test.onRequestApprove(user, "pooja@123", "pooja@123tr", 7000);
	}
	
	@Test
	public void transactionTest() {
		ServiceClass test = new ServiceClass();
		
		test.transaction((long)1102, (long)1103, 7800);
	}
	
	
	
	@Test
	public void displayStatement() {
		
		ServiceClass test = new ServiceClass();
		
		List<UserTransactionDetail> list = test.getTransactionsOfUser(1102);
		for(UserTransactionDetail trx : list) {
			System.out.println(trx.getFromAccount().getAccountNumber()+" , "+trx.getToAccount().getAccountNumber()+" , "+trx.getAmount()+" , "+trx.getModeOfTransaction());
		}
	}
	
	@Test
	public void displayStatementByRange() {
		
		ServiceClass test = new ServiceClass();
		
		List<UserTransactionDetail> list = test.getTransactionsOfUserByRange(LocalDateTime.of(LocalDate.of(2021,04,11), LocalTime.of(00,00)),LocalDateTime.of(LocalDate.of(2021,04,11), LocalTime.of(23,59)));
		for(UserTransactionDetail trx : list) {
			System.out.println(trx.getFromAccount().getAccountNumber()+" , "+trx.getToAccount().getAccountNumber()+" , "+trx.getAmount()+" , "+trx.getModeOfTransaction());
		}
	}

}

