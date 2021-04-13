package com.project.compoundkey;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.entities.AccountDetail;


public class PayeeCompound implements Serializable {

	
	@ManyToOne
	@JoinColumn(name = "user_account_no")
	private AccountDetail userAccount;
	
	
	@ManyToOne
	@JoinColumn(name = "beneficiary_account_no")
	private AccountDetail beneficiaryAccount;

	

	public AccountDetail getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(AccountDetail userAccount) {
		this.userAccount = userAccount;
	}

	public AccountDetail getBeneficiaryAccount() {
		return beneficiaryAccount;
	}

	public void setBeneficiaryAccount(AccountDetail beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}
	
	
	
	
}
