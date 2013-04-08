package com.pcm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.pcm.dao.StudentDAO;
import com.pcm.entity.Student;
import com.pcm.entity.TuitionFee;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})

public class StudentTest {
	@Autowired
	private StudentDAO dao;
	
	@Test
	public void employeeScore() {
		Student student1 = new Student("Information Technology", 90, "Ezekiel", "Calayag");
		Student student2 = new Student("Information Technology", 90, "Boss", "Balita");
	
		dao.persist(student1);
		dao.persist(student2);
		
/*		
		List<Employee> above80 = dao.findByScoreRange(71,100);
		assertEquals(above80.size(),3);*/
	}
	

}




