package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class Medical extends Expense implements VariableExpense {

	public Medical(double amount, Date date, String item) {
		super(amount, date, item);
		
	}

	@Override
	public String getClassName() {
		
		return ("Medical");
	}

}
