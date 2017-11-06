package application.model;

import java.util.ArrayList;

import application.model.Expense.Expense;


/**
 * This class is responsible for tracking a user's varaible Expenses
 * on a monthly basis.
 * 
 * @author Isaac Buitrago
 *
 */

public class ExpenseTracker{
	
	/**
	 * Living expenses include Housing, Auto expenses, and General Insurance payments
	 */
	private ArrayList<Expense> livingExpenses;
	
	public ExpenseTracker() {

		livingExpenses = new ArrayList<Expense>();
	}
	
	/**
	 *@return ArrayList of expenses for the month 
	 */
	public Expense getExpense(int index){
		
		return this.livingExpenses.get(index);
	}
	
	/**
	 * 
	 * @param expense made during the month
	 */
	public void addExpense(Expense expense) {
		
		this.livingExpenses.add(expense);
	}
	
	/**
	 * This function removes an expense from the list.
	 * @param expense to be deleted from the list
	 */
	
	public void removeExpense(Expense expense) {
		
		this.livingExpenses.remove(expense);
		
	}
	

}
