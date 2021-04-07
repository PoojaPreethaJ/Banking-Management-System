package com.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.project.enums.AccountType;

@Entity
@Table(name="tbl_account_type")
public class UserAccountType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "new_seq3")
	@SequenceGenerator(sequenceName = "reference_seq3", initialValue = 1000, allocationSize = 1, name="new_seq3")	
	private long accountNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private UserAccountDetail customerId;
	
	@Column(name="account_type")
	private AccountType accountType;
	
	@Column(name="balance")
	private double bankBalance;
	
	@OneToMany(mappedBy = "fromAccount" ,cascade =CascadeType.ALL)
	private List<UserTransactionDetail> fromTransaction;
	
	@OneToMany(mappedBy = "toAccount" ,cascade = CascadeType.ALL)
	private List<UserTransactionDetail> toTransaction;
	
	@OneToMany(mappedBy = "compoundKey.userAccountNo",cascade =CascadeType.ALL)
	private List<UserAddPayee> userKey;
	
	@OneToMany(mappedBy = "compoundKey.beneficiaryAccountNo",cascade =CascadeType.ALL)
	private List<UserAddPayee> beneficiaryKey;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public UserAccountDetail getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UserAccountDetail customerId) {
		this.customerId = customerId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public List<UserTransactionDetail> getFromTransaction() {
		return fromTransaction;
	}

	public void setFromTransaction(List<UserTransactionDetail> fromTransaction) {
		this.fromTransaction = fromTransaction;
	}

	public List<UserTransactionDetail> getToTransaction() {
		return toTransaction;
	}

	public void setToTransaction(List<UserTransactionDetail> toTransaction) {
		this.toTransaction = toTransaction;
	}

	public List<UserAddPayee> getUserKey() {
		return userKey;
	}

	public void setUserKey(List<UserAddPayee> userKey) {
		this.userKey = userKey;
	}

	public List<UserAddPayee> getBeneficiaryKey() {
		return beneficiaryKey;
	}

	public void setBeneficiaryKey(List<UserAddPayee> beneficiaryKey) {
		this.beneficiaryKey = beneficiaryKey;
	}
	
	
}
