package com.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.GenericDao;
import com.project.entities.RegistrationDetail;
import com.project.entities.UserAccountDetail;
import com.project.entities.UserAccountType;
import com.project.entities.UserGeneralDetail;
import com.project.entities.UserTransactionDetail;
import com.project.enums.AccountType;
import com.project.enums.Title;
import com.project.enums.TransactionType;




public class ServiceClass {

	public void add(){
		GenericDao dao = new GenericDao();
		
		RegistrationDetail add = new RegistrationDetail(); 
		add.setTitle(Title.Mr);
		add.setFirstName("Maru");
		add.setLastName("P");
		add.setFatherName("Paramesh");
		add.setMobileNo(1234567814);//range problem
		add.setEmailId("maru@gmail.com");
		add.setAadhaarNo(142536456);//range problem
		add.setPanCard("ESOP23253");
		add.setDateOfBirth(LocalDate.of(2008,01,23));
		add.setResidentialAddress("basavanagar");
		add.setPermanent("basavanagar");
		add.setOccupation("Manager");
		add.setIncomeSource("self");
		add.setAnnualIncome(600000);

		add=(RegistrationDetail)dao.newSave(add);
	
		UserAccountDetail ud = new UserAccountDetail();	
		ud.setLoginPassword("maru");
		ud.setTransactionPassword("maru234");
		ud.setRegistration(add);
		dao.newSave(ud);
		System.out.println("=>"+ud.getCustomerId());
		
		
		UserGeneralDetail general = new UserGeneralDetail();
		general.setAadhaarNo(add.getAadhaarNo());
		general.setCustomerId(ud);
		general.setDateOfBirth(add.getDateOfBirth());
		general.setGrossIncome(add.getAnnualIncome());
		general.setMailingAddress(add.getResidentialAddress());
		general.setOccupation(add.getOccupation());
		general.setPanCard(add.getPanCard());
		
		general=(UserGeneralDetail)dao.newSave(general);
		
		
		/*UserAccountType accType = new UserAccountType();
		accType.setAccountType(AccountType.Current);
		accType.setBankBalance(2000);
		accType.setCustomerId(ud);
		
		UserAccountType accType2 = new UserAccountType();
		accType2.setAccountType(AccountType.Savings);
		accType2.setBankBalance(5000);
		accType2.setCustomerId(ud);
		
		List<UserAccountType> acc = new ArrayList<UserAccountType>();
		acc.add(accType);
		acc.add(accType2);
		
		ud.setAccounts(acc);
		
		
		dao.newSave(acc);*/
		
		
		
	}
}
