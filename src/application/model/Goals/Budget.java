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

	
	private ArrayList<Expense> budgetItems; // Expenses the Budget consists of
	
	private String title;					// Title of the Budget
	
	/**
	 * Constructor used to create a new Budget
	 */
	public Budget(String title)
	{
		
		this.budgetItems = new ArrayList<Expense>();
		this.title = title;
	}
	
	/**
	 * Constructor used to create a new Budget from a given list of Expenses
	 */
	public Budget(String title, ArrayList<Expense> budgetItems)
	{
		this.budgetItems = budgetItems;
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
		
		this.budgetItems.add(expense);
	}
	
	/**
	 * Used to return the budget items that represent this Budget
	 * @return
	 */
	public ArrayList<Expense> getExpenses()
	{
		
		return this.budgetItems;
		
	}
}
