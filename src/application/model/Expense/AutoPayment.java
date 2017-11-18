package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class AutoPayment extends Expense implements FixedExpense {

	public AutoPayment(double amount, Date date, String item) {
		super(amount, date, item);
		
	}

	@Override
	public String getClassName() {
		
		return ("AutoPayment");
	}

}
