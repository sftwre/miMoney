package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Entertainment extends Expense implements VariableExpense 
{

	public Entertainment(double amount, Date date, String item) 
	{
		super(amount, date, item);
	}
	
	public Entertainment()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("Entertainment");
	}

}
