package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class HealthInsurance extends Expense implements FixedExpense 
{

	public HealthInsurance(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}

	public HealthInsurance()
	{
		super();
	}
	
	@Override
	public String getClassName() 
	{
		
		return ("HealthInsurance");
	}

}
