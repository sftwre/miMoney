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
	
	public Goals (String type, String projectName, String totalCost,
			String interestRate, String downPayment, String year, String time) {
		this.projectName = projectName;
		this.type = type;
		this.totalCost = Double.parseDouble(totalCost);
		this.interestRate = Double.parseDouble(interestRate);
		this.downPayment = Double.parseDouble(downPayment);
		this.year = Integer.parseInt(year); 
		this.time = Integer.parseInt(time);
		
		System.out.println(toString1());
		//Operations
	}
	
	public Goals (String type, String projectName, String data, double totalCost,
			double interestRate, double downPayment, int time, double taxes) {
		this.projectName = projectName;
		this.type = type;
		this.totalCost = totalCost;
		this.interestRate = interestRate;
		this.downPayment = downPayment;
		this.time = time;
		this.taxes = taxes;
		//Operations
	}
	
	/**
	 * @return the String value of what was just calculated or what the user is entering
	 */
	
	public void TotalCost (){
		double totalCost = 0;
		
		
	}
	public String toString1(){
		return String.format("%s:%s:%.2f:%.2f:%.2f:%d:%d"
				, this.type
				, this.projectName
				, this.totalCost
				, this.interestRate
				, this.downPayment
				, this.year
				, this.time);
	}
	public String toString2(){
		return String.format(":");
	}
}
