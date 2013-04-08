package com.pcm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name = "TuitionFee")
@SequenceGenerator(name = "tuition_seq", initialValue = 1, allocationSize = 1000)
public class TuitionFee {
	@Id
	@Column (name = "ID")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "tuition_seq")
	private int id;
	
	 	
	 	@Column (name = "TuitionFee")
		private BigDecimal tuitionFee;
	 		 	
		@Column(name = "YEAROFSTUDENT")
		private int yearsOfService;
	 	
	 	@ManyToOne
	 	@JoinColumn(name = "Student_ID")
	 	private Student student;
	 	
	 	
	 	
	 	
	 	

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public BigDecimal getTuitionFee() {
			return tuitionFee;
		}

		public void setTuitionFee(BigDecimal tuitionFee) {
			this.tuitionFee = tuitionFee;
		}

		public int getYearsOfService() {
			return yearsOfService;
		}

		public void setYearsOfService(int yearsOfService) {
			this.yearsOfService = yearsOfService;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}
	 	
	 	
	 	
	


}


