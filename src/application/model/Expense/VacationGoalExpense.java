package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class VacationGoalExpense extends Expense implements VariableExpense 
{

	public VacationGoalExpense(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}

	
	public VacationGoalExpense()
	{
		super();
	}
	
	@Override
	public String getClassName() 
	{
		
		return ("VacationGoalExpense");
	}

}
