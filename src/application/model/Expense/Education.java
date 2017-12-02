package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Education extends Expense implements VariableExpense 
{

	public Education(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public Education()
	{
		super();
	}
	
	@Override
	public String getClassName() 
	{
		
		return ("Education");
	}

}
