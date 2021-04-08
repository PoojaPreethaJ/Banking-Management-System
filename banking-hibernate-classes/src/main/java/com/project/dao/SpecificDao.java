package com.project.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.entities.UserGeneralDetail;
import com.project.entities.UserTransactionDetail;

//This class contains all the queries(select/fetch) which will be used by ServiceClass class to fetch required details 

public class SpecificDao extends GenericDao{
	//Use to fetch balance based on account number
	public double fetchBalance(long acno) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					 emf = Persistence.createEntityManagerFactory("oracleTest");
					em = emf.createEntityManager();
					
					String jpql = "select typ.bankBalance from UserAccountType typ where typ.accountNumber = :no";
					Query q= em.createQuery(jpql);
					q.setParameter("no",acno);
					Double balance = (Double) q.getSingleResult();
					return balance;
				}
				finally {
					em.close();
					emf.close();
					}

	}

	//based on remarks get all the transaction details
	public List<UserTransactionDetail> fetchSuccessfulTransaction(String remark) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					 emf = Persistence.createEntityManagerFactory("oracleTest");
					em = emf.createEntityManager();
					
					String jpql = "select tr from UserTransactionDetail tr where tr.remarks = :rm";
					Query q= em.createQuery(jpql);
					q.setParameter("rm",remark);
					List<UserTransactionDetail> list = q.getResultList();
					
					return list;
				}
				finally {
					em.close();
					emf.close();
					}

	}
	
	//get all the general details of user based on given aadhaar card
	public List<UserGeneralDetail> fetchUserDetails(long aadhaar) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					emf = Persistence.createEntityManagerFactory("oracleTest");
					em = emf.createEntityManager();
					
					String jpql = "select det from UserGeneralDetail det where det.aadhaarNo = :aadhaar";
					Query q= em.createQuery(jpql);
					q.setParameter("aadhaar",aadhaar);
					List<UserGeneralDetail> list = q.getResultList();
	
					return list;
				}
				finally {
					em.close();
					emf.close();
					}

	}
}
