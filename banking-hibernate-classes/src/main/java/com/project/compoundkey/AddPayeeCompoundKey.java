package com.project.compoundkey;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.entities.UserAccountType;


public class AddPayeeCompoundKey implements Serializable {

	
	@ManyToOne
	@JoinColumn(name = "user_account_no")
	private UserAccountType userAccountNo;
	
	
	@ManyToOne
	@JoinColumn(name = "beneficiary_account_no")
	private UserAccountType beneficiaryAccountNo;

	

	public UserAccountType getUserAccountNo() {
		return userAccountNo;
	}

	public void setUserAccountNo(UserAccountType userAccountNo) {
		this.userAccountNo = userAccountNo;
	}

	public UserAccountType getBeneficiaryAccountNo() {
		return beneficiaryAccountNo;
	}

	public void setBeneficiaryAccountNo(UserAccountType beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}
	
	
	
	
}
