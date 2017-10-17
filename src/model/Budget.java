package model;

import java.util.ArrayList;

/**
 * This object represents a user's Budget.
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
	
	//add an expense to the budget
	public void addExpense(Expense expense) {
		
		this.expenses.add(expense);
	}
	
	public void editBudget(Budget budget) {
		
		//copy the contents of the incoming to the current budget
		
		
	}
	
	public void saveBudget(Budget budget) {
		
		
	}
	
	public boolean delete(Budget budget) {
		
		return true;
	}
	
	public String getTitle() {
		
		return this.title;
	}
}
