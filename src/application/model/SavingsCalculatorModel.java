package application.model;

/**
 * @author eeo072
 * The purpose of this code is to create a savings calculator.
 * The calculator will tell you when you will be able to buy something based on how much you save per month.
 *
 */
public class SavingsCalculatorModel {
	private double savingsGoal; // Total cost of the item the user saving up to obtain
	private double monthlyDeposit; // Amount deposited per month towards item
	private int time; // Time in months that it will take to be able to afford the item
	private String itemName; // The item that the user wishes to save up for

	public SavingsCalculatorModel() {
		savingsGoal = 0.0;
		monthlyDeposit = 0.0;
		time = 0;
		itemName = "";
	}
	/**
	 * 
	 * @param s The name of the item that the user wishes to purchase
	 */
	public void desiredItem(String s) {
		this.itemName = s;
	}
	/**
	 * 
	 * @return savings goal as a double
	 */
	public double getSavingsGoal () {
		return savingsGoal;
	}
	/**
	 * 
	 * @param savings goal to edit within savings calculator
	 */
	public void setSavingsGoal(double savingsGoal) {
		this.savingsGoal = savingsGoal;
	}
	/**
	 * 
	 * @return monthly deposit as a double
	 */
	public double getMonthlyDeposit () {
		return monthlyDeposit;
	}
	/**
	 * 
	 * @param monthly deposit to edit within savings calculator
	 */
	public void setMonthlyDeposit(double monthlyDeposit) {
		this.monthlyDeposit = monthlyDeposit;
	}
	/**
	 * Calculate time to save up for purchase in months
	 * @param sG the savings goal
	 * @param mD the monthly deposit
	 */
	public void savingsTime (double sG, double mD) {
		this.savingsGoal = sG;
		this.monthlyDeposit = mD;
		double rT = sG/mD;
		this.time = (int)rT;
	}
	/**
	 * return formatted string of savings calculation
	 */
	public String toString() {
		return (String.format("At a rate of %f deposited per month, it will take you %d months to be able to afford your %s", 
				this.monthlyDeposit, this.time, this.itemName));
	}
	
	
}