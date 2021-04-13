package com.project.dao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.entities.GeneralDetail;
import com.project.entities.Payee;
import com.project.entities.Transaction;


//This class contains all the queries(select/fetch) which will be used by ServiceClass class to fetch required details 

public class SpecificDao extends GenericDao{
	//Use to fetch balance based on account number
	public double fetchBalance(long acno) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					 emf = Persistence.createEntityManagerFactory("oracleTest");
					em = emf.createEntityManager();
					
					String jpql = "select typ.bankBalance from AccountDetail typ where typ.accountNumber = :no";
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

	//based on account number get all the transaction details
	public List<Transaction> fetchTransactions(long acno) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					 emf = Persistence.createEntityManagerFactory("oracleTest");
					 em = emf.createEntityManager();
					
					String jpql = "select tr from Transaction tr where tr.fromAccount.accountNumber = :acno";
					Query q= em.createQuery(jpql);
					q.setParameter("acno",acno);
					List<Transaction> list = q.getResultList();
					
					return list;
				}
				finally {
					em.close();
					emf.close();
					}

	}
	
	//based on date get all the transaction details
		public List<Transaction> fetchTransactionsByRange(LocalDateTime fromDate,LocalDateTime toDate) {
			EntityManagerFactory emf = null;
			EntityManager em = null;
					try {
						 emf = Persistence.createEntityManagerFactory("oracleTest");
						 em = emf.createEntityManager();
						
						String jpql = "select tr from Transaction tr where tr.transactionDate >=  :start AND tr.transactionDate <= :end ";
						Query q= em.createQuery(jpql);
						q.setParameter("start",fromDate);
						q.setParameter("end",toDate);
						
						List<Transaction> list = q.getResultList();
						
						return list;
					}
					finally {
						em.close();
						emf.close();
						}

		}
	
	//get all the general details of user based on given aadhaar card
	public List<GeneralDetail> fetchUserDetails(long aadhaar) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					emf = Persistence.createEntityManagerFactory("oracleTest");
					em = emf.createEntityManager();
					
					String jpql = "select det from GeneralDetail det where det.aadhaarNo = :aadhaar";
					Query q= em.createQuery(jpql);
					q.setParameter("aadhaar",aadhaar);
					List<GeneralDetail> list = q.getResultList();
	
					return list;
				}
				finally {
					em.close();
					emf.close();
					}
	}
	
	public List<Payee> fetchBeneficiaryDetails(long beneficiaryAccountNo){
	EntityManagerFactory emf = null;
	EntityManager em = null;
	try {
		emf = Persistence.createEntityManagerFactory("oracleTest");
		em = emf.createEntityManager();
		
		String jpql = "select beneficiary_account_no, beneficiary_name, user_account_no, nick_name from tbl_add_payee where beneficiary_account_no = :ban";
		Query q= em.createQuery(jpql);
		 q.setParameter("ban",beneficiaryAccountNo);
		List<Payee> list = q.getResultList();

		return list;
	}
	finally {
		em.close();
		emf.close();
		}
}
}
