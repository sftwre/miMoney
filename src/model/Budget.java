package model;

import java.util.ArrayList;

public class Budget {

	
	private ArrayList<Expense> expenses;
	
	private String title;
	
	public Budget(String title) {
		
		this.expenses = new ArrayList<Expense>();
		this.title = title;
		
	}
	
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
