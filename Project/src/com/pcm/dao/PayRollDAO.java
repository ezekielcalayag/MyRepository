package com.pcm.dao;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pcm.entity.PayRoll;

public class PayRollDAO {
	
	private EntityManager pr;
	
	
	private EntityManagerFactory factory;

    static {
        try {
        	
            DriverManager.registerDriver((Driver) Class.forName("org.hsqldb.jdbcDriver").newInstance());
        } catch (Exception e) {
            System.out.println("Crazy exception: " + e.getMessage());
            System.exit(-1);
        }
    }

    public PayRollDAO() {
		// TODO Auto-generated constructor stub
    	pr = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}
    
    public void init() {
    	pr = factory.createEntityManager();
    }
	
	public void persist(PayRoll p) {
		pr.getTransaction().begin();
		try {
			pr.persist(p);
			pr.flush();
			pr.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception occured - rolling back");
			pr.getTransaction().rollback();
		}
	}

	public List<PayRoll> findByScoreRange(int amount) {
		// TODO Auto-generated method stub
		return pr.createQuery("SELECT e FROM payroll e WHERE salary >= :amount",PayRoll.class)
                .setParameter("amount",amount)
                .getResultList();
	}	
	
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

}
