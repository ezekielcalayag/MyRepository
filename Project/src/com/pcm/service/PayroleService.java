package com.pcm.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcm.dao.EmployeeDAO;
import com.pcm.entity.Employee;
import com.pcm.entity.President;
import com.pcm.interfaces.IGoldenParachute;
import com.pcm.utility.Util;


public class PayroleService{
	
	final int NUMBER_OF_MONTHS = 12;
	final float PENSION_PERCENTAGE = .25F;

	private static PayroleService instance;
	
	Employee employee;

	@Autowired
	private EmployeeDAO employeeDAO;

	public String getPension(Employee employee){
		BigDecimal totalPension = new BigDecimal(0) ;
		employee.fire();
		float PensionYearlyPercentage = 1.1f;
		BigDecimal Salary = new BigDecimal(employee.getSalary());
		if(employee.getPayroll().getYearsOfService() >= 2){
			for(int year = 2; employee.getPayroll().getYearsOfService() >= year; year++){
				
				Salary = Salary.multiply(BigDecimal.valueOf(PensionYearlyPercentage));
				
				totalPension = ((Salary.multiply(BigDecimal.valueOf(NUMBER_OF_MONTHS))).multiply(BigDecimal.valueOf(PENSION_PERCENTAGE)));
				totalPension = Util.roundOff(totalPension);

			}
		}else{
			
			totalPension = ((Salary.multiply(BigDecimal.valueOf(NUMBER_OF_MONTHS))).multiply(BigDecimal.valueOf(PENSION_PERCENTAGE)));
			totalPension = (Util.roundOff(totalPension));
		}
		
		if(employee instanceof IGoldenParachute){
			President pres = (President) employee;
			pres.getGoldenParachuteBonus();
		}
		
		
		return totalPension.toString();
	}
	
	public static PayroleService getInstance() {
		if(instance==null){
			instance = new PayroleService();
		}
		return instance;
	}

	public Employee findByName(String firstName, String lastName) {
		
		Employee emp = employeeDAO.findByName(firstName, lastName);
			
		return emp;
		
		
	}
	
	
}
