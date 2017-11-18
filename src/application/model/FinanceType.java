package application.model;

/**
 * A FinanceType enumerates the Users financial information.
 * Financial data related to the users Income is INCOME.
 * Financial data related to the users Goals is GOALS
 * Financial data related to the users variable Expenses is REXPENSE
 * Financial data related to the users Fixed Expenses is FEXPENSE
 * 
 * @author Isaac Buitrago
 *
 */

public enum FinanceType {
	
	INCOME(1),	// User Income
	GOALS(2),	// User financial goals
	REXPENSE(3),// User variable expenses
	FEXPENSE(4);// User fixed expenses, e.g. Home Payment
	
	private int type;
	
	private FinanceType(int type) {
		
		this.type = type;
	}

}
