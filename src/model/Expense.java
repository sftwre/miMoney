package model;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public abstract class Expense {
	
	private double amount;			// The amount of the Expense
	
	private Date transactionDate;	// The Date the Expense was made
	
	public Expense(double amount, Date date) {
		
		if(amount >= 0 ) {
			
			this.amount = amount;
		}
		
		this.transactionDate = date;
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
