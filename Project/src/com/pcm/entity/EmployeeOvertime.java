package com.pcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "EmployeeOvertime")
@SequenceGenerator(name = "employee_seq", initialValue = 1, allocationSize = 1000)
public class EmployeeOvertime {
	
	@Id
	@Column(name = "ID")
	private int employeeOvertimeID;
	
	@ManyToOne
	@JoinColumn(name = "Employee_Id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "Overtime_ID")
	private OverTime overtime;
	
	@Column
	private double numberOfHours;

	public double getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(double numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	
	
	
	
	
	

}
