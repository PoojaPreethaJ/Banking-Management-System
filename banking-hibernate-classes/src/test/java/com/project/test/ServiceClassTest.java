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
		add.setTitle(Title.Ms);
		add.setFirstName("Neha");
		add.setLastName("Singh");
		add.setFatherName("Manoj");
		add.setMobileNo(1234568525);//range problem
		add.setEmailId("neha@gmail.com");
		add.setAadhaarNo(8277103);//range problem
		add.setPanCard("NEH8763");
		add.setDateOfBirth(LocalDate.of(1998,10,9));
		add.setResidentialAddress("Ambernath, Mumbai 400098");
		add.setPermanent("UP");
		add.setOccupation("Medical");
		add.setIncomeSource("Self");
		add.setAnnualIncome(400000);
		
		test.register(add);
	}
	
	@Test
	public void approvedByAdmin() {
		
		GenericDao dao = new GenericDao();
		
		RegistrationDetail user = (RegistrationDetail) dao.fetch(RegistrationDetail.class, (long)1003);
		
		ServiceClass test = new ServiceClass();
		test.onRequestApprove(user, "neha@123", "neha@123tr", 10000);
	}
	
	@Test
	public void transactionTest() {
		ServiceClass test = new ServiceClass();
		
		test.transaction((long)1000, (long)1003, 1000);
	}
}

