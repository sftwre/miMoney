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
	
	/**
	 * Constructor for an Expense object
	 * @param amount of the Expense made
	 * @param date the Expense was made
	 * @param item that was purchased
	 */
	public Expense(double amount, Date date, String item) 
	{
		
		if( amount >= 0 )
			this.amount = amount;
		
		if(item == null || item.isEmpty() || item.matches("^\\s*$"))
			this.item = null;
		else
			this.item = item;
		
		this.transactionDate = date;
	}
	
	/**
	 * Overloaded constructor for instantiating an empty Expense object 
	 * to be used later.
	 */
	public Expense()
	{
		super();
	}
	
	/**
	 * 
	 * @return The class name of the Expense type
	 */
	
	public abstract String getClassName();
	
	public String getItem() {
		
		if(item == null)
			return "";
		
		else
			return this.item;
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
		if(this.transactionDate == null)
			return new Date(1,1,1970);
		else
			return this.transactionDate;
	}
	
	public void setDate(Date date) 
	{
		this.transactionDate = date;
	}	

	/**
	 * toString() used to output the object information to a file 
	 */
	public String toString()
	{
		return (String.format("%s:%f:%s:%s", this.getClassName(),this.getAmmount(), 
				this.getDate().toString(), this.getItem()));
	}
}
