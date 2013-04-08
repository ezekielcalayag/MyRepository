package com.pcm.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.pcm.exceptions.PresidentException;
import com.pcm.exceptions.SalaryInLimitException;
import com.pcm.exceptions.SubordinateInLimitException;
import com.pcm.interfaces.IGoldenParachute;

@Entity
public class President extends Employee implements IGoldenParachute{
	
	final BigDecimal PERCENTBONUS = BigDecimal.valueOf(0.50);	
	
	public President(String firstName, String lastName, int age, String salary) throws PresidentException {
		super(firstName, lastName, age, salary);
		
		super.getPayroll().setPercentageBonus(getPercentBonus());
		setSalary(salary);

	}

	public BigDecimal getPercentBonus(){
		return this.PERCENTBONUS;
	}
	
	
	public void addSubordinate(Employee employee) throws PresidentException{
		
		BigDecimal subordinatesSalary = BigDecimal.valueOf(0);
		
		for(Employee e: this.subordinates){
			subordinatesSalary = subordinatesSalary.add( new BigDecimal(e.getPayroll().getSalaryWithBonusAnnual()));
		}
		
		
		if(this.subordinates.size() >10){
			throw new PresidentException("subordinate ex");
		}

		this.subordinates.add(employee);
	}
	
	public Set<Employee> getSubordinates(){
		
		return subordinates;
		
	}

	public boolean hasSubordinate(Employee e2) {
		// TODO Auto-generated method stub
		return false;
	}


	public void fire() {
		super.fire();
		applyGoldenParachuteBonus();
		
		
	}

	private void applyGoldenParachuteBonus() {
		
		setGoldenParachuteBonus("10000");
		
		
	}
	
	public void addSubordinate(Employee...subordinates) throws SubordinateInLimitException, SalaryInLimitException{
		for(Employee s : subordinates){
			checkMaximumSubordinate();
			this.subordinates.add(s);
			
			if(this.subordinates.size() > 5){
				checkSalaryLimit();
			}
		}
	}

	private void checkMaximumSubordinate() throws SubordinateInLimitException {
		if(this.subordinates.size() == 10)
		{
			throw new SubordinateInLimitException();
		}
	}
	private void checkSalaryLimit() throws SalaryInLimitException{
		if(subordinatesTotalSalary().compareTo(getAnnualSalary()) == -1)
		{
				throw new SalaryInLimitException();
		}
		
	}
	public void checkAge() throws PresidentException{
		
		if(getAge() < 10){
		throw new PresidentException("To young to be the president!");
		}
		
	}
	
	public BigDecimal subordinatesTotalSalary(){
		
		BigDecimal totalSalary = new BigDecimal(0);
		
		for(Employee subordinate: this.subordinates){
			
			totalSalary = totalSalary.add(subordinate.getAnnualSalary());
		}
		
		return totalSalary;
	}

/*	@Override
	public PayRoll getPayroll() {
		
		return super.getPayroll();
	}
*/
	
	
		
}
