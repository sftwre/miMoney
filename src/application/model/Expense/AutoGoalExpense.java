package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class AutoGoalExpense extends Expense implements VariableExpense 
{

	public AutoGoalExpense(double amount, Date date, String item) 
	{
		super(amount, date, item);
		
	}
	
	public AutoGoalExpense()
	{
		super();
	}
	@Override
	public String getClassName() {
		
		return ("AutoGoalExpense");
	}

}
