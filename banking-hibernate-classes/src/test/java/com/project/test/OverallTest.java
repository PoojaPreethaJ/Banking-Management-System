package com.project.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.project.compoundkey.PayeeCompound;
import com.project.dao.GenericDao;
import com.project.entities.Account;
import com.project.entities.AccountDetail;
import com.project.entities.Payee;
import com.project.entities.Registration;
import com.project.enums.AccountType;
import com.project.enums.Title;
import com.project.enums.TransactionType;

/*Test carried out to check the working of hibernate mapping ie GenericDao class methods 
 *  basically contains the details to be inserted into database
 */

public class OverallTest {

	
	@Test
	public void addRegistration() {
		
		Registration add = new Registration(); 
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
		Registration add = (Registration)dao.fetch(Registration.class, (long)34);
		
		Account ud = new Account();
		ud.setLoginPassword("chaitra");
		ud.setTransactionPassword("chai111");
		ud.setRegistration(add);
		
		add.setAccount(ud);
		dao.save(ud);
	}
	
	
	@Test
	public void addAccountType() {
		GenericDao dao= new GenericDao();
		Account ud = (Account)dao.fetch(Account.class, (long)133);
		AccountDetail accType = new AccountDetail();
		accType.setAccountType(AccountType.Savings);
		accType.setBankBalance(7000);
		accType.setAccount(ud);
		
		List<AccountDetail> acc = new ArrayList<AccountDetail>();
		acc.add(accType);
		ud.setAccounts(acc);
		dao.save(accType);
		
	}
	
	
/*	@Test
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
	
	}*/
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
	/*@Test
	public void addTransaction() {
		GenericDao dao = new GenericDao();

		UserAccountType accType = (UserAccountType)dao.fetch(UserAccountType.class, (long)1002);
		UserAccountType accType2 = (UserAccountType)dao.fetch(UserAccountType.class, (long)1003);

		//if(accType.getBankBalance()>1000) {
			
			//accType.setBankBalance(accType.getBankBalance()-1000);
			//accType2.setBankBalance(accType2.getBankBalance()+1000);
			
			UserTransactionDetail transaction  = new UserTransactionDetail();
			transaction.setAmount(1000);
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
			
			//dao.save(accType);
			//dao.save(accType2);
			
			dao.save(transaction);
		//}
		//else 
			//System.out.println("Insufficient Bank Balance");
		
	}
	*/
	
	@Test
	public void addBeneficiary() {
		GenericDao dao = new GenericDao();
		AccountDetail accType = (AccountDetail)dao.fetch(AccountDetail.class, (long)1085);
		AccountDetail accType2 = (AccountDetail)dao.fetch(AccountDetail.class, (long)1084);
		
		//System.out.println("->"+accType.getAccountNumber());
		
		PayeeCompound key = new PayeeCompound();
		key.setBeneficiaryAccount(accType);
		key.setUserAccount(accType2);
		Payee payee = new Payee();
		payee.setBeneficiaryName("Chaitra");
		payee.setNickName("chai");
		
		payee.setCompoundKey(key);
		List<Payee> p =new ArrayList<Payee>();
		p.add(payee);
		
		accType.setUserKey(p);
		accType2.setBeneficiaryKey(p);
		
		dao.save(payee);
		
	}
	

		
}
