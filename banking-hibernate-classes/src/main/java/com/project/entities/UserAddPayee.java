package com.project.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.compoundkey.AddPayeeCompoundKey;

@Entity
@Table(name="tbl_add_payee")
public class UserAddPayee {

	@EmbeddedId
	@Column(name="beneficiary_id")
	private AddPayeeCompoundKey compoundKey;
	
	@Column(name="beneficiary_name")
	private String beneficiaryName;
	
	@Column(name="nick_name")
	private String nickName;

	public AddPayeeCompoundKey getCompoundKey() {
		return compoundKey;
	}

	public void setCompoundKey(AddPayeeCompoundKey compoundKey) {
		this.compoundKey = compoundKey;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	
	
	
	
}
