package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Food extends Expense implements VariableExpense {

	public Food(double amount, Date date, String item) {
		super(amount, date, item);
		
	}

}
