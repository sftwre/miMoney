package application.model;

/**
 * A GoalType enumerates the Users financial goals.
 * Goal to purchase a home is HOME
 * Goal to purchase an automobile is AUTO
 * Goal to pay off debt LOAN
 * Goal to save up money is SAVINGS
 * 
 * @author Isaac Buitrago
 *
 */
public enum GoalType {
	
	HOME(1), 
	AUTO(2), 
	LOAN(3),
	SAVINGS(4);
	
	int type;
	
	private GoalType(int type) {
		
		this.type = type;
	}

}
