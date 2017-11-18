package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Apperal extends Expense implements VariableExpense {

	public Apperal(double amount, Date date, String item) {
		super(amount, date, item);
		
	}

	@Override
	public String getClassName() {
		
		return ("Apperal");
	}

}
