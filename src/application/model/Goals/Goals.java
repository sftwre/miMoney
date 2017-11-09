package application.model.Goals;

/**
 * @author manueldeaguinaga
 * 
 */

public class Goals {
	
	
	private String projectName;
	private String data;
	private double totalCost;
	private double interestRate;
	private double downPayment;
	private int year; 
	private int time;
	private boolean nextOrCancel;
	
	public Goals () {
		projectName = "";
		totalCost = 0.00;
		interestRate = 0.00;
		downPayment = 0.00;
		year = 0;
		time = 0;
		nextOrCancel = true;
		
	}
	
	/**
	 * @return the String value of what was just calculated or what the user is entering
	 */
	public String getData() {
		return data;
	}
	
	public void information ( String txt ){
		System.out.printf(txt + "\n");
		if(txt.equals("Continue")){
			
			
		}
	}

/*
	
	public Goals(String goal, double budget, double rate, int years, double cost){
		this.goal = goal;
		this.budget = budget;
		this.rate = rate;
		this.years = years;	
		this.cost = cost;
	}
	
	public double MonthlyPayments(){
		double monthlyPayments;
		double totalCost = TotalCost();
		int months = this.years * 12;
		
		monthlyPayments = totalCost/months;
		
		return monthlyPayments;
	}
	
	
	public double TotalCost(){
		double totalCost;
		totalCost = this.cost+(this.cost*(this.rate*0.01));
		
		return totalCost;
	}
	
	
	public String PosibleOrNot(){
		
		if(this.budget >= MonthlyPayments())
			return "yes";
		else
			return "no";
	}
	
	
	public String toString(){
		return String.format("goal name: %s, cost: %d, "
				+ "years: %d interest rate: %d payments "
				+ "per month: %d posible or not: %s "
				, this.goal
				, this.cost
				, this.years
				, this.rate
				, MonthlyPayments()
				, PosibleOrNot());
	}
	*/
}
