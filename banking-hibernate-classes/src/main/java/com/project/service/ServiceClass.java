package com.project.service;

import java.util.List;

import com.project.dao.SpecificDao;
import com.project.entities.UserGeneralDetail;

//This class is used to add the business logic 
//use get before method name to avoid confusion
public class ServiceClass implements Service{

	public List<UserGeneralDetail> getDetailByAadhaar(long aadhaar){
		SpecificDao dao = new SpecificDao();
		return dao.fetchUserDetails(aadhaar);	
	}
}
