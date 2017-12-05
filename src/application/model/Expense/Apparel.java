package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Apparel extends Expense implements VariableExpense 
{

	public Apparel(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public Apparel()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("Apparel");
	}

}
