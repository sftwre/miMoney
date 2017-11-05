package application.model;

/**
 * A FinanceType enumerates the Users financial information.
 * Financial data related to the users Income is INCOME.
 * Financial data related to the users Goals is GOALS
 * Financial data relate to the users variable Expenses is REXPENSE
 * Financial data relate to the users Fixed Expenses is FEXPENSE
 * 
 * @author Isaac Buitrago
 *
 */

public enum FinanceType {
	
	INCOME(1),	// Read or Write to Income.txt
	GOALS(2),	// Read or Write to Goals sub-directory
	REXPENSE(3),// Read or Write to RecurringExpenses.txt
	FEXPENSE(4);// Read or Write to FixedExpenses.txt
	
	private int type;
	
	private FinanceType(int type) {
		
		this.type = type;
	}

}
