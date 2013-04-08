package com.pcm.dao;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.pcm.entity.Student;

public class StudentDAO {
	
	private EntityManager studentM;
	
	private EntityManagerFactory factory;

    static {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.hsqldb.jdbcDriver").newInstance());
        } catch (Exception e) {
            System.out.println("Crazy exception: " + e.getMessage());
            System.exit(-1);
        }
    }

    public void init() {
    	studentM = factory.createEntityManager();
    }
	
    public StudentDAO() {
    	studentM = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}
    
    
	public void persist(Student e) {
		studentM.getTransaction().begin();
		try {
			//em.persist(e.getPayroll());
			studentM.persist(e);
			studentM.flush();
			studentM.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception occured - rolling back");
			studentM.getTransaction().rollback();
		}
		
	}

	public Student find(int id) {
		// TODO Auto-generated method stub
		return studentM.find(Student.class,id);
	}

	public List<Student> findByScoreRange(int start, int end) {
		// TODO Auto-generated method stub
		return studentM.createQuery("SELECT e FROM Employee e WHERE score >= :start AND score <= :stop",Student.class)
                .setParameter("start",start)
                .setParameter("stop",end)
                .getResultList();
	}
	
	public List<Student> searchByDepartment(String substring) {
        //substring = "%"+substring+"%";
        
        return studentM.createQuery("SELECT e FROM Employee e WHERE department LIKE :a",Student.class)
                     .setParameter("a", "%"+substring+"%").
                     getResultList();
        
 }
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public Student findByName(String firstName, String lastName) {
		
		Query query = studentM.createQuery("SELECT e FROM Employee e WHERE firstname = :fName AND lastName = :lName", Student.class);
		query.setParameter("fName", firstName);
		query.setParameter("lName", lastName);
		return (Student) query.getSingleResult();
		
	}
}
