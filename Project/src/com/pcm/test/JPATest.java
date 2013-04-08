package com.pcm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcm.dao.DepartmentDAO;
import com.pcm.dao.EmployeeDAO;
import com.pcm.entity.Department;
import com.pcm.entity.Employee;
import com.pcm.entity.Person;
import com.pcm.exceptions.PresidentException;
import com.pcm.entity.President;
import com.pcm.factory.PersonFactory;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})

public class JPATest {
	@Autowired
	private EmployeeDAO dao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private PersonFactory pf;
	
	@Test
	public void employeeScore() {
		Employee e = new Employee("ss","Calayag", 22, "5");
		Employee e1 = new Employee("ss","Calayag", 22, "5");
		Employee e2 = new Employee("ss","Calayag", 22, "5");
		Employee e3= new Employee("ss","Calayag", 22, "5");
		
		//set department
		e.setDepartment("IT");
		e1.setDepartment("IT");
		e2.setDepartment("IT");
		e3.setDepartment("Sales");
		//
		
		e.setScore(100);
		e1.setScore(90);
		e2.setScore(90);
		e3.setScore(70);   
			
		dao.persist(e);
		dao.persist(e1);
		dao.persist(e2);
		dao.persist(e3);
		
		List<Employee> above80 = dao.findByScoreRange(71,100);
		assertEquals(above80.size(),3);
	}
	
	@Test
	public void test() throws PresidentException {

		Employee e = new Employee("ss","Calayag", 22, "5");
		e.setDepartment("IT");

		dao.persist(e);
		
		int id = e.getId();
		
		Employee retrieved = dao.find(id);
		assertNotNull(retrieved);
		assertEquals(retrieved.getDepartment().getDepartmentName(),"IT");
		
		
		Employee e2 = new Employee("ss","Calayag", 22, "5");
		e2.setDepartment("SALES");
		dao.persist(e2);
		
		retrieved = dao.find(e2.getId());
		//System.out.println("asfdsa" + retrieved.getId());
		assertNotNull(retrieved);
		assertEquals(retrieved.getDepartment().getDepartmentName(),"SALES");
	}
	
	@Test
	public void employeeDepartment() {
		Employee e = new Employee("ss","Calayag", 22, "5");
		Employee e1 = new Employee("ss","Calayag", 22, "5");
		Employee e2 = new Employee("ss","Calayag", 22, "5");
		Employee e3= new Employee("ss","Calayag", 22, "5");
		
		e.setDepartment("INFORMATION TECHNOLOGY");
		e1.setDepartment("ACCOUNTING");
		e2.setDepartment("MARKETING");
		e3.setDepartment("SALES");
		
			
		dao.persist(e);
		dao.persist(e1);
		dao.persist(e2);
		dao.persist(e3);
		
		List<Employee> dept = dao.searchByDepartment("IN");
		assertEquals(dept.size(),3);
	}
	@Test
	public void employeePersistence() throws PresidentException {
		
		
		Employee e = new Employee("Cathy","Belen",21,"10000.00");
		e.setDepartment("IT");
		dao.persist(e);
		
		int id = e.getId();
		
		Employee retrieved = dao.find(id);
		assertNotNull(retrieved);
		assertEquals(retrieved.getDepartment().getDepartmentName(),"IT");
		
		
		Employee e2 = new Employee("Kelvin","Te",21,"10000.00");
		e2.setDepartment("SALES");
		dao.persist(e2);
		
		retrieved = dao.find(e2.getId());
		assertNotNull(retrieved);
		assertEquals(retrieved.getDepartment().getDepartmentName(),"SALES");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void factoryTest() throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		President p = (President) pf.createInstance("Ezekiel", "Calayag", 22, "25000.00", President.class);
         
         assertEquals(p.getFirstName(), "Ezekiel");
         assertEquals(p.getLastName(), "Calayag");
         assertEquals(p.getAge(), 22);
         assertEquals(p.getPayroll().getSalary().toString(), "25000.00");
		
		
        Employee emp = (Employee) pf.createInstance("Ezekiel", "Calayag", 22, "250000", Employee.class);
         
        assertNotNull(emp);
        assertEquals(emp.getFirstName(), "Ezekiel");
        assertEquals(emp.getLastName(), "Calayag");
        assertEquals(emp.getAge(), 22);
        assertEquals(emp.getPayroll().getSalary().toString(), "250000");
      
        President pres = (President) pf.createInstance("Ezekiel", "Calayag", 22, "250000", President.class);
	
        assertNotNull(pres);
        assertEquals(pres.getFirstName(), "Ezekiel");
        assertEquals(pres.getLastName(), "Calayag");
        assertEquals(pres.getAge(), 22);
        assertEquals(pres.getPayroll().getSalary().toString(), "250000");
	}
	@Test
	public void DepartmentTest(){
		
		List <Employee> emplist = new ArrayList<Employee>();
		
		Employee e = new Employee("Ezekiel", "Calayag", 22, "250000");
		e.setDepartment("IT");
		Employee e1 = new Employee("Ezekiel", "Calayag", 22, "250000");
		e1.setDepartment("IT");
		Employee e2 = new Employee("Ezekiel", "Calayag", 22, "250000");
		e2.setDepartment("Sales");
		
		emplist.add(e);
		emplist.add(e1);
		emplist.add(e2);
	
		dao.persist(e);
		dao.persist(e1);
		dao.persist(e2);
		
		List <Employee> deptlist = dao.searchByDepartment("IT");
		
		assertNotNull(deptlist);	
/*		assertEquals(deptlist.size(), 2);*/
	}

}

