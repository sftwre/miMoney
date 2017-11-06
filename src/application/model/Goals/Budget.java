package application.model.Goals;

import java.util.ArrayList;

import application.model.Expense.Expense;

/**
 * This object represents a user's Budget.
 * A Budget consists of Expenses and fixed amount the
 * user desires to spend on each type of Expense. No date
 * is required to represent a Budget.
 * 
 * @author Isaac Buitrago
 *
 */

public class Budget {

	
	private ArrayList<Expense> expenses; //fixed expenses for the user
	
	private String title; //The name of the budget
	
	public Budget(String title) {
		
		this.expenses = new ArrayList<Expense>();
		this.title = title;
		
	}
	
	/**
	 * 
	 * @param expense the user decides to spend a fixed amount on
	 */
	public void addExpense(Expense expense) {
		
		try{
			if(expense == null)
				throw new NullPointerException();
		} catch(NullPointerException e){
			
			System.out.println("Expense object must be instantiated");
		}
		
		this.expenses.add(expense);
	}
	
	
	/**
	 * 
	 * @return the title of the Budget
	 */
	public String getTitle() {
		
		return this.title;
	}
	
	/**
	 * Reset the title of the Budget
	 * @param title of the Budget
	 */
	
	public void setTitle(String title) {
		
		this.title = title;
	}
}
