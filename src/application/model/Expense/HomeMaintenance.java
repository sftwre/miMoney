package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class HomeMaintenance extends Expense implements VariableExpense 
{

	public HomeMaintenance(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public HomeMaintenance()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("HomeMaintenance");
	}

}
