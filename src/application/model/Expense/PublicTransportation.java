package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class PublicTransportation extends Expense implements VariableExpense 
{

	public PublicTransportation(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public PublicTransportation()
	{
		super();
	}

	@Override
	public String getClassName() 
	{
		
		return ("PublicTransportation");
	}

}
