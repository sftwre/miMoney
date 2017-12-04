package application.model;

import java.util.ArrayList;
import java.util.List;

import application.model.Expense.Expense;


/**
 * This class is responsible for tracking a user's variable Expenses
 * on a monthly basis.
 * 
 * @author Isaac Buitrago
 *
 */

public class ExpenseTracker
{
	
	/**
	 * Living expenses include variable Expenses 
	 * that cannot be accounted for by the user.
	 */
	private ArrayList<Expense> livingExpenses;
	
	/**
	 * Date for which to track Expenses 
	 */
	private Date date;
	
	
	/**
	 * Constructor that does not take a date
	 */
	public ExpenseTracker() 
	{
		livingExpenses = new ArrayList<Expense>();	
	}
	
	
	/**
	 * Constructor that takes a Date
	 * @param date
	 */
	public ExpenseTracker(Date date) 
	{

		livingExpenses = new ArrayList<Expense>();
		this.date = date;
	}
	

	/**
	 *@return ArrayList of expenses for the month 
	 */
	public Expense getExpense(int index)
	{
		
		return this.livingExpenses.get(index);
	}
	
	/**
	 * Used to return all Expenses currently being tracked
	 * @return 
	 */
	public ArrayList<Expense> getExpenses()
	{
		return(this.livingExpenses);
	}
	
	public Date getDate() 
	{
		return date;
	}

	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	/**
	 * 
	 * @param expense made during the month
	 */
	public void addExpense(Expense expense) 
	{
		
		this.livingExpenses.add(expense);
	}
	
	/**
	 * Used to add a List of Expense items to the tracker
	 */
	public void addExpenses(List<Expense> expenses)
	{
		this.livingExpenses.addAll(expenses);
	}
	
	/**
	 * This function removes an expense from the list.
	 * @param expense to be deleted from the list
	 */
	
	public void removeExpense(Expense expense) 
	{
		
		this.livingExpenses.remove(expense);
		
	}
	
	/**
	 * Used to return the total amount spent for a specific Expense category
	 * in the current month.
	 * @return the total amount spent in an Expense category
	 */
	public double totalSpentInCategory(Expense expenseCategory)
	{
		double amountSpent = 0;
		
		String category = expenseCategory.getClassName();
		
		for(Expense e : livingExpenses)
		{
			if(e.getClassName().equals(category))
				amountSpent += e.getAmmount();		
		}
		
		return amountSpent;
	}
	

}
