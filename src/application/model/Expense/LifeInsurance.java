package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class LifeInsurance extends Expense implements FixedExpense 
{

	public LifeInsurance(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public LifeInsurance()
	{
		super();
	}
	
	@Override
	public String getClassName() 
	{
		
		return ("LifeInsurance");
	}

}
