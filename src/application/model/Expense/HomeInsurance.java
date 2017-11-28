package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class HomeInsurance extends Expense implements FixedExpense 
{

	public HomeInsurance(double amount, Date date, String item) 
	{
		super(amount, date, item);
		// TODO Auto-generated constructor stub
	}
	
	public HomeInsurance()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("HomeInsurance");
	}

}
