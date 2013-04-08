package com.pcm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "Department")
@SequenceGenerator(name = "department_seq", initialValue = 1, allocationSize = 1000)
public class Department {
	
	
	@Id
	@Column(name = "ID")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "department_seq")	
	private int id;
	
	
	@Column(name = "DepartmentName")
	private String departmentName;
	
	@OneToMany /*(cascade = CascadeType.ALL, mappedBy = "department")*/
	private List<Employee> employee = new ArrayList<Employee>();
	
	
	
	public Department(String departmentName) {
		this.departmentName = departmentName;
	}
	
	

	public Department(String departmentName, List<Employee> employee) {
		super();
		this.departmentName = departmentName;
		this.employee = employee;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
}
