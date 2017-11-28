package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class Subscriptions extends Expense implements VariableExpense 
{

	public Subscriptions(double amount, Date date, String item) 
	{
		
		super(amount, date, item);
		
	}
	
	public Subscriptions()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("Subscriptions");
	}

}
