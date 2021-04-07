package com.project.app;

import java.time.LocalDate;
import java.util.List;

import com.project.dao.GenericDao;
import com.project.entities.RegistrationDetail;
import com.project.entities.UserAccountDetail;
import com.project.enums.Title;

//This is app

public class App {

	public static void main(String[] args) {
		
		//Scenario 1:
		//add a person
		/*RegistrationDetail add = new RegistrationDetail(); 
		add.setTitle(Title.Ms);
		add.setFirstName("Pooja");
		add.setLastName("Preetha");
		add.setFatherName("Jyothiappan");
		add.setMobileNo(1234569874);//range problem
		add.setEmailId("poojajothiappan23@gmail.com");
		add.setAadhaarNo(123456789);//range problem
		add.setPanCard("EOEP230133");
		add.setDateOfBirth(LocalDate.of(1998,02,23));
		add.setResidentialAddress("kalappa layout basavanagar");
		add.setPermanent("kalappa layout basavanagar");
		add.setOccupation("developer");
		add.setIncomeSource("me");
		add.setAnnualIncome(350000);
		
		
		
		GenericDao dao = new GenericDao();
		dao.save(add);
		
		
		//then add address for that person
		GenericDao dao = new GenericDao();
		RegistrationDetail rd = (RegistrationDetail) dao.fetch(RegistrationDetail.class,7);
		UserAccountDetail ud = new UserAccountDetail();
		
		ud.setLoginPassword("pooja");
		ud.setTransactionPassword("pooja123");
		ud.setRegistration(rd);
	
		
		
		dao.save(ud);*/
		
		//====Scenario 2======
		//add person along with address together
		GenericDao dao= new GenericDao();
		
		RegistrationDetail add = new RegistrationDetail(); 
		add.setTitle(Title.Ms);
		add.setFirstName("Priya");
		add.setLastName("Preetha");
		add.setFatherName("Jyothiappan");
		add.setMobileNo(1234569874);//range problem
		add.setEmailId("priya@gmail.com");
		add.setAadhaarNo(123456789);//range problem
		add.setPanCard("EOEP230133");
		add.setDateOfBirth(LocalDate.of(1996,02,23));
		add.setResidentialAddress("basavanagar");
		add.setPermanent(" basavanagar");
		add.setOccupation("Sales Manager");
		add.setIncomeSource("me");
		add.setAnnualIncome(380000);
		
		
		UserAccountDetail ud = new UserAccountDetail();
		
		ud.setLoginPassword("priya");
		ud.setTransactionPassword("pooja123");
		ud.setRegistration(add);
		
		add.setUserAccountDetail(ud);
		
		dao.save(add);
		
		

	
	}
}
