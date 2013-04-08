package com.pcm.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.pcm.interfaces.Iperson;


@MappedSuperclass
public abstract class Person implements Iperson{
	
	public String firstName;
	public String lastName;
	public int age;

	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;

	}
	
	public String getFirstName() {
		
		return this.firstName;
	}

	public String getLastName() {
		
		return this.lastName;
	}
	public int getAge() {
		
		return this.age ;
	}



	
}

