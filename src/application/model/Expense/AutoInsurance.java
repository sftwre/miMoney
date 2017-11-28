package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class AutoInsurance extends Expense implements FixedExpense 
{

	public AutoInsurance(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public AutoInsurance()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("AutoInsurance");
	}

}
