package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */

public class HomePayment extends Expense implements FixedExpense {

	public HomePayment(double amount, Date date, String item) {
		super(amount, date, item);
		
	}

}
