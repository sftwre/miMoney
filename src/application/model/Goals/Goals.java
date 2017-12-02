package application.model.Goals;
import java.io.IOException;
import java.util.*;

/**
 * @author Manuel Deaguinaga
 * 
 */

public class Goals {
	
	private String type;
	private String projectName;
	private double totalCost;
	private double interestRate;
	private double downPayment;
	private int year; 
	private int time;
	private double taxes;
	private double other;
	private double monthly;
	
	
	//CarGoal
	public Goals (String type, String projectName, String totalCost,
			String interestRate, String downPayment, String year, String time) {
		
		try{
			this.projectName = projectName;
			this.type = type;
			this.totalCost = Double.parseDouble(totalCost);
			this.interestRate = Double.parseDouble(interestRate);
			this.downPayment = Double.parseDouble(downPayment);
			this.year = Integer.parseInt(year); 
			this.time = Integer.parseInt(time);
		}
		catch(NumberFormatException ex){
			System.out.println("Not a number");
		}
		
		//operations
		
		this.totalCost = ((this.interestRate) * this.totalCost) + this.totalCost - this.downPayment;
		this.monthly =   (this.totalCost)/(this.time * 12);	
	}
	
	//HomeGoal
	public Goals (String type, String projectName, String totalCost,
			String interestRate, String downPayment, String time, String taxas, String other) {
		this.projectName = projectName;
		this.type = type;
		this.totalCost = Double.parseDouble(totalCost);
		this.interestRate = Double.parseDouble(interestRate);
		this.downPayment = Double.parseDouble(downPayment);
		this.time = Integer.parseInt(time);
		this.taxes = Double.parseDouble(taxas);
		this.other = Double.parseDouble(other);
		
		//Operations
		
		this.totalCost = ((this.interestRate) * this.totalCost) + this.totalCost - this.downPayment + (this.taxes * this.time);
		this.monthly =   (this.totalCost)/(this.time * 12);
		//operations
	}
	
	//LoanGoal
	public Goals (String type, String projectName, String totalCost,
			String interestRate, String downPayment, String time) {
		this.projectName = projectName;
		this.type = type;
		this.totalCost = Double.parseDouble(totalCost);
		this.interestRate = Double.parseDouble(interestRate);
		this.downPayment = Double.parseDouble(downPayment); 
		this.time = Integer.parseInt(time);
		
		// Operations
		
		this.totalCost = ((this.interestRate) * this.totalCost) + this.totalCost - this.downPayment;
		this.monthly =   (this.totalCost)/(this.time);
		
	}
	//VacationGoal
	public Goals (String type, String projectName, String totalCost, String time) {
		this.type = type;
		this.projectName = projectName;
		this.totalCost = Double.parseDouble(totalCost);
		this.time = Integer.parseInt(time); 	
		
		//Operations
		this.monthly = (this.totalCost)/(this.time);
	}

	
	/**
	 * @return the String value of what was just calculated or what the user is entering
	 */
	
	public void TotalCost (){
		double totalCost = 0;	
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	
	public double other(){
		return other;
	}
	
	public void other (double other){
		this.other = other;
	}
	
	public double monthly() {
		return monthly;
	}
	
	public void monthly (double monthly){
		this.monthly = monthly;
	}
	
	public String toString(){
		
		this.projectName = this.projectName.replaceAll("\\s+", "");
		
		return String.format("%s:%s:%.2f:%.2f:%d"
				, this.type
				, this.projectName
				, this.totalCost
				, this.monthly
				, this.time);
	}
}
