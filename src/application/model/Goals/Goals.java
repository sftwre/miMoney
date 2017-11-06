package application.model.Goals;

/**
 * @author manueldeaguinaga
 * 
 */

public class Goals {
	
	private String goal;
	private double budget;
	private double rate;
	private int years;
	private double cost;
	
	public Goals(String goal, double budget, double rate, int years, double cost){
		this.goal = goal;
		this.budget = budget;
		this.rate = rate;
		this.years = years;	
		this.cost = cost;
	}
	/**
	 * 
	 * @return monthlyPayments
	 */
	public double MonthlyPayments(){
		double monthlyPayments;
		double totalCost = TotalCost();
		int months = this.years * 12;
		
		monthlyPayments = totalCost/months;
		
		return monthlyPayments;
	}
	
	/**
	 * 
	 * @return totalCost
	 */
	public double TotalCost(){
		double totalCost;
		totalCost = this.cost+(this.cost*(this.rate*0.01));
		
		return totalCost;
	}
	
	/**
	 * 
	 * @return yes if is possible or not if is not
	 */
	public String PosibleOrNot(){
		
		if(this.budget >= MonthlyPayments())
			return "yes";
		else
			return "no";
	}
	
	/**
	 * @return a string with the information that contains the current goal
	 */
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
}
