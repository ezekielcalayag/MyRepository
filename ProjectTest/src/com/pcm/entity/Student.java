package com.pcm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table (name = "STUDENT")
@SequenceGenerator(name = "student_seq", initialValue = 1, allocationSize = 1000)
public class Student{
	
	@Id
	@Column(name = "Student_Id")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "student_seq")	
	private int id;
	
	@Column (name = "Course")
	private String course;
	
	@Column (name = "Score")
	private int score;
	
	@Column (name = "FirstName")
	private String fName;
	
	@Column (name = "LastName")
	private String lName;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "student")
	private List<TuitionFee> tuition = new ArrayList<TuitionFee>();

 
    //boss year payroll
	

	


	public String getCourse() {
		return course;
	}



	public Student(String course, int score, String fName, String lName) {
		this.course = course;
		this.score = score;
		this.fName = fName;
		this.lName = lName;
	}



	public void setCourse(String course) {
		this.course = course;
	}



	public String getfName() {
		return fName;
	}



	public void setfName(String fName) {
		this.fName = fName;
	}



	public String getlName() {
		return lName;
	}



	public void setlName(String lName) {
		this.lName = lName;
	}



	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

}