package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Gas extends Expense implements VariableExpense 
{

	public Gas(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}

	public Gas()
	{
		super();
	}
	
	@Override
	public String getClassName() 
	{
		
		return ("Gas");
	}

}
