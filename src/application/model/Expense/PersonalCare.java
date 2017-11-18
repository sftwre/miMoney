package application.model.Expense;

import application.model.Date;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class PersonalCare extends Expense implements VariableExpense {

	public PersonalCare(double amount, Date date, String item) {
		super(amount, date, item);
		
	}

	@Override
	public String getClassName() {
		
		return ("PersonalCare");
	}

}
