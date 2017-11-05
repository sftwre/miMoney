package application.model.Expense;

import application.model.Date;

/**
 * Fixed Expenses occur periodically at a specific date.
 * The user's bills are a Fixed Expense since they occur
 * on a certain date each month and do not change as frequent as
 * living expenses, like gas and food.
 * 
 * @author
 *
 */
public class FixedExpenses extends Expense {
	
	
public FixedExpenses(double amount, Date date) 
{
	super(amount, date);
}


}


