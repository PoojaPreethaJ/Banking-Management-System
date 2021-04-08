package com.project.service;

import java.util.List;

import com.project.entities.UserGeneralDetail;

public interface Service {

	public List<UserGeneralDetail> getDetailByAadhaar(long aadhaar);
}
