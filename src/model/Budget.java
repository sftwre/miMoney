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
	
	
	public String getTitle() {
		
		return this.title;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
}
