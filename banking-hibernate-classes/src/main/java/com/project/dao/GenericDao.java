package com.project.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GenericDao {
	public void save(Object obj) {//save --> insert or update(saving a new record or saving an existing record)
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					 emf = Persistence.createEntityManagerFactory("oracleTest");
					em = emf.createEntityManager();
					EntityTransaction tx = em.getTransaction();
					tx.begin();
					em.merge(obj);
					
					tx.commit();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			finally {
			em.close();
			emf.close();
			}
		}
	
	public Object newSave(Object e) {//save --> insert or update(saving a new record or saving an existing record)
		EntityManagerFactory emf = null;
		EntityManager em = null;
				try {
					 emf = Persistence.createEntityManagerFactory("oracleTest");
					em = emf.createEntityManager();
					EntityTransaction tx = em.getTransaction();
					tx.begin();
					Object obj = em.merge(e);
					
					tx.commit();
					return obj;
				}
				catch(Exception ex) {
					ex.printStackTrace();
					return null;
				}
			finally {
			em.close();
			emf.close();
			}
		}
	
	public Object fetch(Class clazz , Object pk) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("oracleTest");
			em = emf.createEntityManager();
			Object obj = em.find(clazz,pk);
			return obj;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
		em.close();
		emf.close();
		
		}	
	}
	
	
	

}
