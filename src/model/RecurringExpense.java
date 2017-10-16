package model;

import java.util.ArrayList;


/**
 * This class is responsible for storing the living expenses of a user
 * on a monthly basis.
 * @author Isaac Buitrago
 *
 */
public class RecurringExpense {
	
	/**
	 * Living expenses include Housing, Auto expenses, and General Insurance payments
	 */
	private ArrayList<Expense> livingExpenses;
	
	public RecurringExpense() {

		livingExpenses = new ArrayList<Expense>();
	}
	
	/**
	 *@return ArrayList of expenses for the month 
	 */
	public ArrayList<Expense> getExpenses(){
		
		return this.livingExpenses;
	}
	
	/**
	 * 
	 * @param expense made during the month
	 */
	public void addExpense(Expense expense) {
		
		this.livingExpenses.add(expense);
	}
	

}
