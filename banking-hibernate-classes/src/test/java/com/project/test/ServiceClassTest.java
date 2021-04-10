package com.project.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.project.dao.GenericDao;
import com.project.entities.RegistrationDetail;
import com.project.entities.UserGeneralDetail;
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
		add.setFirstName("Rohit");
		add.setLastName("Gupta");
		add.setFatherName("Raju");
		add.setMobileNo(1852048525);//range problem
		add.setEmailId("rohit@gmail.com");
		add.setAadhaarNo(74107103);//range problem
		add.setPanCard("ROH8763");
		add.setDateOfBirth(LocalDate.of(1998,07,14));
		add.setResidentialAddress("Kalina, Mumbai 400098");
		add.setPermanent("Santacruz");
		add.setOccupation("Developer");
		add.setIncomeSource("Self");
		add.setAnnualIncome(400000);
		
		test.register(add);
	}
	
	@Test
	public void approvedByAdmin() {
		
		GenericDao dao = new GenericDao();
		
		RegistrationDetail user = (RegistrationDetail) dao.fetch(RegistrationDetail.class, (long)29);
		
		ServiceClass test = new ServiceClass();
		test.onRequestApprove(user, "neha@123", "neha@123tr", 10000);
	}
	
	@Test
	public void transactionTest() {
		ServiceClass test = new ServiceClass();
		
		test.transaction((long)1026, (long)1027, 1000);
	}
}

