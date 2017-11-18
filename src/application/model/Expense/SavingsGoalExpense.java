package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class SavingsGoalExpense extends Expense implements VariableExpense {

	public SavingsGoalExpense(double amount, Date date, String item) {
		super(amount, date, item);
		
	}

	@Override
	public String getClassName() {
		
		return ("SavingsGoalExpense");
	}

}
