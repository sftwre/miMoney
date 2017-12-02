package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Luxury extends Expense implements VariableExpense 
{

	public Luxury(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public Luxury()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("Luxury");
	}

}
