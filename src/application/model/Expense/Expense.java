package application.model.Expense;

import application.model.Date;

/**
 * Each Expense object contains the amount that was spent
 * and the date that the expense was made. This is an abstract
 * class for
 * @author Isaac Buitrago
 * @author jguzm
 *
 */

public abstract class Expense {
	
	private double amount;			// The amount of the Expense
	
	private Date transactionDate;	// The Date the Expense was made
	
	private String item;			// description of the item bought
	
	public Expense(double amount, Date date, String item) {
		
		if( amount >= 0 )
			this.amount = amount;
		
		if(item.isEmpty() && item != null)
			this.item = item;
		
		this.transactionDate = date;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getAmmount() 
	{
		return this.amount;
	}
	
	public void setAmmount(double amount)
	{
		if(amount >= 0)
		{
			this.amount = amount;
		}
	}
	
	public Date getDate() 
	{
		return this.transactionDate;
	}
	
	public void setDate(Date date) 
	{
		this.transactionDate = date;
	}	

}
