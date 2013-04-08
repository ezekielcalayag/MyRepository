package com.pcm.dao;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.pcm.entity.Department;
import com.pcm.entity.Employee;

public class EmployeeDAO {
	
	private EntityManager em;
	
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
    	em = factory.createEntityManager();
    }
	
    public EmployeeDAO() {
    	em = Persistence.createEntityManagerFactory("pu").createEntityManager();
	}
    
    
	public void persist(Employee e) {
		em.getTransaction().begin();
		try {
			//em.persist(e.getPayroll());
			/*em.persist(e.getDepartment());*/
			em.persist(e);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception occured - rolling back");
			em.getTransaction().rollback();
		}
		
	}

	public Employee find(int id) {
		// TODO Auto-generated method stub
		return em.find(Employee.class,id);
	}

	public List<Employee> findByScoreRange(int start, int end) {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT e FROM Employee e WHERE score >= :start AND score <= :stop",Employee.class)
                .setParameter("start",start)
                .setParameter("stop",end)
                .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> searchByDepartment(String substring) {
        substring = "%"+substring+"%";
        
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.department.departmentName LIKE :a",Employee.class);
                     query.setParameter("a", substring);
        return query.getResultList();
	}
	
	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public Employee findByName(String firstName, String lastName) {
		
		Query query = em.createQuery("SELECT e FROM Employee e WHERE firstname = :fName AND lastName = :lName", Employee.class);
		query.setParameter("fName", firstName);
		query.setParameter("lName", lastName);
		return (Employee) query.getSingleResult();
		
	}
	public List<Department> findDeptName(String deptname) {
		// TODO Auto-generated method stub
		deptname = "%" + deptname + "%";
		return em.createQuery("SELECT e FROM Department e WHERE e.department.departmentName = :name",Department.class)
                .setParameter("name",deptname)
                .getResultList();
	}	
}