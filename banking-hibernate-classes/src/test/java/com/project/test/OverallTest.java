package com.project.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.project.compoundkey.AddPayeeCompoundKey;
import com.project.dao.GenericDao;
import com.project.entities.RegistrationDetail;
import com.project.entities.UserAccountDetail;
import com.project.entities.UserAccountType;
import com.project.entities.UserAddPayee;
import com.project.entities.UserGeneralDetail;
import com.project.entities.UserTransactionDetail;
import com.project.enums.AccountType;
import com.project.enums.Title;
import com.project.enums.TransactionType;


public class OverallTest {

	
	@Test
	public void addRegistration() {
		
		RegistrationDetail add = new RegistrationDetail(); 
		add.setTitle(Title.Ms);
		add.setFirstName("Chaitra");
		add.setLastName("C");
		add.setFatherName("Karna");
		add.setMobileNo(1234567891);//range problem
		add.setEmailId("chai@gmail.com");
		add.setAadhaarNo(7777);//range problem
		add.setPanCard("CHAI8763");
		add.setDateOfBirth(LocalDate.of(1998,05,21));
		add.setResidentialAddress("Vimanapura ,Bangalore - 560006");
		add.setPermanent("Vimanapura,Shivajinagar ,Bangalore - 560006");
		add.setOccupation("QAE");
		add.setIncomeSource("Self");
		add.setAnnualIncome(550000);
		add.setGstNumber("GST11111");
		add.setRevenueRegisterNo("AEO123");
		
		
		
		GenericDao dao = new GenericDao();
		dao.save(add);
	}
	
	
	@Test
	public void addAccountDetail() {
		GenericDao dao= new GenericDao();
		RegistrationDetail add = (RegistrationDetail)dao.fetch(RegistrationDetail.class, (long)34);
		
		UserAccountDetail ud = new UserAccountDetail();
		ud.setLoginPassword("chaitra");
		ud.setTransactionPassword("chai111");
		ud.setRegistration(add);
		
		add.setUserAccountDetail(ud);
		dao.save(ud);
	}
	
	
	@Test
	public void addAccountType() {
		GenericDao dao= new GenericDao();
		UserAccountDetail ud = (UserAccountDetail)dao.fetch(UserAccountDetail.class, (long)133);
		UserAccountType accType = new UserAccountType();
		accType.setAccountType(AccountType.Savings);
		accType.setBankBalance(7000);
		accType.setCustomerId(ud);
		
		List<UserAccountType> acc = new ArrayList<UserAccountType>();
		acc.add(accType);
		ud.setAccounts(acc);
		dao.save(accType);
		
	}
	
	
	@Test
	public void addGeneralDetails() {
		GenericDao dao = new GenericDao(); 
		RegistrationDetail add =(RegistrationDetail)dao.fetch(RegistrationDetail.class,(long)34);
		UserAccountDetail ud = (UserAccountDetail)dao.fetch(UserAccountDetail.class, (long)133);
		
		UserGeneralDetail general = new UserGeneralDetail();
		general.setAadhaarNo(add.getAadhaarNo());
		general.setCustomerId(ud);
		System.out.println(ud.getCustomerId());
		general.setDateOfBirth(add.getDateOfBirth());
		general.setGrossIncome(add.getAnnualIncome());
		general.setMailingAddress(add.getResidentialAddress());
		general.setOccupation(add.getOccupation());
		general.setPanCard(add.getPanCard());
		general.setGstNumber(add.getGstNumber());
		general.setRevenueRegisterNo(add.getRevenueRegisterNo());
		
		dao.save(general);
	
	}
	/*@Test
	public void addAccountDetailAndTypeWithGeneral() {
		
		
		GenericDao dao= new GenericDao();
		
		RegistrationDetail add = new RegistrationDetail(); 
		add.setTitle(Title.Mr);
		add.setFirstName("Sharath");
		add.setLastName("Kumar");
		add.setFatherName("Jain");
		add.setMobileNo(1237485748);//range problem
		add.setEmailId("Sharath@gmail.com");
		add.setAadhaarNo(144456789);//range problem
		add.setPanCard("NHOK230133");
		add.setDateOfBirth(LocalDate.of(2000,03,24));
		add.setResidentialAddress("basavanagar");
		add.setPermanent(" basavanagar");
		add.setOccupation("Web Designer");
		add.setIncomeSource("self");
		add.setAnnualIncome(500000);
		
		
		UserAccountDetail ud = new UserAccountDetail();
		
		ud.setLoginPassword("sharu");
		ud.setTransactionPassword("sharrr");
		ud.setRegistration(add);
		
		add.setUserAccountDetail(ud);
		dao.save(ud);
		System.out.println(ud.getCustomerId());
		UserAccountType accType = new UserAccountType();
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
		
		UserGeneralDetail general = new UserGeneralDetail();
		general.setAadhaarNo(add.getAadhaarNo());
		general.setCustomerId(ud);
		System.out.println(ud.getCustomerId());
		general.setDateOfBirth(add.getDateOfBirth());
		general.setGrossIncome(add.getAnnualIncome());
		general.setMailingAddress(add.getResidentialAddress());
		general.setOccupation(add.getOccupation());
		general.setPanCard(add.getPanCard());
		
		
		dao.save(general);
		dao.save(accType);
		dao.save(add);
	}*/
	
	
	//self transaction
	@Test
	public void addTransaction() {
		GenericDao dao = new GenericDao();
		
		UserAccountType accType = (UserAccountType)dao.fetch(UserAccountType.class, (long)1085);
		UserAccountType accType2 = (UserAccountType)dao.fetch(UserAccountType.class, (long)1084);
		
		UserTransactionDetail transaction  = new UserTransactionDetail();
		transaction.setAmount(2200);
		transaction.setModeOfTransaction(TransactionType.IMPS);
		transaction.setMaturityInstruction("Bank Instruction");
		transaction.setTransactionDate(LocalDateTime.now());;
		transaction.setRemarks(" loan amount ");
		transaction.setStatus("Transaction Successful...");
		transaction.setFromAccount(accType);
		transaction.setToAccount(accType2);
	   
		List<UserTransactionDetail> t =new ArrayList<UserTransactionDetail>();
		t.add(transaction);
		
		accType.setFromTransaction(t);
		accType2.setToTransaction(t);
		
		dao.save(transaction);
		
	}
	
	
	@Test
	public void addBeneficiary() {
		GenericDao dao = new GenericDao();
		UserAccountType accType = (UserAccountType)dao.fetch(UserAccountType.class, (long)1085);
		UserAccountType accType2 = (UserAccountType)dao.fetch(UserAccountType.class, (long)1084);
		
		//System.out.println("->"+accType.getAccountNumber());
		
		AddPayeeCompoundKey key = new AddPayeeCompoundKey();
		key.setBeneficiaryAccountNo(accType);
		key.setUserAccountNo(accType2);
		UserAddPayee payee = new UserAddPayee();
		payee.setBeneficiaryName("Chaitra");
		payee.setNickName("chai");
		
		payee.setCompoundKey(key);
		List<UserAddPayee> p =new ArrayList<UserAddPayee>();
		p.add(payee);
		
		accType.setUserKey(p);
		accType2.setBeneficiaryKey(p);
		
		dao.save(payee);
		
	}
	

		
}
