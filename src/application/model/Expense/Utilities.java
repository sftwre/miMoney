package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Utilities extends Expense implements FixedExpense 
{

	public Utilities(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public Utilities()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("Utilities");
	}

}
