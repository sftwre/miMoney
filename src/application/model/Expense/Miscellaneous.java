package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Miscellaneous extends Expense implements VariableExpense 
{

	public Miscellaneous(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}

	public Miscellaneous()
	{
		super();
	}
	
	@Override
	public String getClassName() 
	{
		
		return ("Miscellaneous");
	}

}
