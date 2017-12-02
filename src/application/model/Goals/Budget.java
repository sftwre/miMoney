package application.model.Goals;

import java.util.ArrayList;

import application.model.Expense.Expense;

/**
 * This object represents a user's Budget.
 * A Budget consists of Expenses and a fixed amount
 * to spend on each type of Expense. No date
 * is required to represent a Budget. 
 * @author Isaac Buitrago
 *
 */

public class Budget 
{

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
	 * Overloaded constructor to create an empty Budget
	 */
	public Budget()
	{
		this.budgetItems = new ArrayList<Expense>();
	}
	
	
	/**
	 * 
	 * @return the Title of the Budget
	 */
	public String getTitle() 
	{
		return title;
	}

	/**
	 * 
	 * @param title of the Budget
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * 
	 * @param expense the user decides to spend a fixed amount on
	 */
	public void addItem(Expense expense) {
		
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
	public ArrayList<Expense> getItems()
	{
		
		return this.budgetItems;
		
	}
	
	/**
	 * Method for storing the Budget in a file
	 */
	public String toString()
	{
		String output = new String();
		
		for(Expense e : budgetItems)
			output += String.format("%s%n", e.toString());
		
		return (String.format("%s%n", this.title) + output);
	}
}
