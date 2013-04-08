package com.pcm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (name = "OverTime")
@SequenceGenerator(name = "overtime_seq", initialValue = 1, allocationSize = 1000)
public class OverTime {
	
	
	
	
	
	public OverTime(String overTimeName,String description) {
		super();
		this.overTimeName = overTimeName;
		
	}

	@Id
	@Column(name = "Id")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "overtime_seq")	
	private int id;
	
	@Column(name = "TypeofOverTime")
	private String overTimeName;
	
	@Column(name = "OvertimeDEscription")
	private String overtimeDescription;
		
/*	@ManyToMany
	@JoinColumn(name = "Employee_Id")
	private List<Employee> employee= new ArrayList <Employee>();*/

	public String getOverTimeName() {
		return overTimeName;
	}

	public String getOvertimeDescription() {
		return overtimeDescription;
	}

	public void setOvertimeDescription(String overtimeDescription) {
		this.overtimeDescription = overtimeDescription;
	}

	public void setOverTimeName(String overTimeName) {
		this.overTimeName = overTimeName;
	}
}
