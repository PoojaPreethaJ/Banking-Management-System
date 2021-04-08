package com.project.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.project.entities.UserGeneralDetail;
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
}

