package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class AutoMaintenance extends Expense implements VariableExpense 
{

	public AutoMaintenance(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}

	public AutoMaintenance()
	{
		super();
	}
	
	@Override
	public String getClassName() 
	{
		
		return ("AutoMaintenance");
	}

}
