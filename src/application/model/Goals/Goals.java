package application.model.Goals;
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
	
	public Goals (String type, String projectName, String totalCost,
			String interestRate, String downPayment, String year, String time) {
		this.projectName = projectName;
		this.type = type;
		this.totalCost = Double.parseDouble(totalCost);
		this.interestRate = Double.parseDouble(interestRate);
		this.downPayment = Double.parseDouble(downPayment);
		this.year = Integer.parseInt(year); 
		this.time = Integer.parseInt(time);
	}
	
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
	}
	
	public Goals (String type, String projectName, String totalCost,
			String interestRate, String downPayment, String time) {
		this.projectName = projectName;
		this.type = type;
		this.totalCost = Double.parseDouble(totalCost);
		this.interestRate = Double.parseDouble(interestRate);
		this.downPayment = Double.parseDouble(downPayment); 
		this.time = Integer.parseInt(time);
		
		System.out.println(toString3());
		//Operations
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

	public String toString1(){
		return String.format("%s:%s:%f:%f:%f:%d:%d"
				, this.type
				, this.projectName
				, this.totalCost
				, this.interestRate
				, this.downPayment
				, this.year
				, this.time);
	}
	public String toString2(){
		return String.format("%s:%s:%f:%f:%f:%d:%f:%f"
				, this.type
				, this.projectName
				, this.totalCost
				, this.interestRate
				, this.downPayment
				, this.time
				, this.taxes
				, this.other);
	}
	public String toString3(){
		return String.format("%s:%s:%f:%f:%f:%d"
				, this.type
				, this.projectName
				, this.totalCost
				, this.interestRate
				, this.downPayment
				, this.time);
	}
	

}
