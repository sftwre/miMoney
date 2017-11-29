package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import application.controller.*;
import application.model.Date;
import application.model.FinanceType;
import application.model.Income;
import application.model.User;
import application.model.Expense.*;
import application.model.Goals.Goals;

public class FileIOTest {

	static User user;
	static FinancialDataParser input;
	UserDataParser userData;
	
	@BeforeClass
	public static void setUpClass() throws Exception
	{
		user  = new User("testUser77");
		input =  new FinancialDataParser(user);
	}
	
	
	@Test
	public void testReadIncome() 
	{
		
		Income income =  input.readIncome();
		
		System.out.printf("%nIncome data%n");
		
		System.out.printf("%s %f\n", income.getTitle(), income.getPay());

	}
	 
	
	/**
	 * Create an ArrayList of Expenses and read in the Variable Expenses 
	 * of the user for the month of February.
	 * 
	 */
	
	@Test
	public void testReadExpenses()
	{
		//Create a Date to control what Expense data is retrieved
		Date febraury =  new Date(2,2,2017);
		
		//Get the variable expenses for February
		ArrayList<Expense> febExpenses = input.readExpenses(febraury, FinanceType.REXPENSE);
		
		//Get the fixed expenses for February an append them to the current list of Expenses
		febExpenses.addAll(input.readExpenses(febraury, FinanceType.FEXPENSE));
		
		//Separate the Variable Expense's from the Fixed Expenses
		System.out.printf("%nExpense's in Febraury classified by type%n");
		
		for(Expense e : febExpenses)
		{
			if(e instanceof VariableExpense)
			{
				System.out.printf("VariableExpense: %f %s %s%n", e.getAmmount(), e.getItem(), e.getDate().toString());	
			}
			
			else
			{
				System.out.printf("FixedExpense: %f %s %s%n", e.getAmmount(), e.getItem(), e.getDate().toString());
			}
		}
		
		System.out.println();
		
		//Display all the Expenses for the month of February
		System.out.printf("%nDisplaying All Expenses for Febraury%n");
		
		for(Expense e : febExpenses)
		{
				System.out.printf("%f %s %s%n", e.getAmmount(), e.getItem(), e.getDate().toString());
		}
	}
	
	
	@Test
	public void testReadGoals()
	{
		ArrayList<Goals> goalsList = new ArrayList<Goals>();
		
		//get the Goals for the user by creating an ArrayList of Goals
		goalsList = input.readGoals();
		
		//iterate through the goals and display the data
		System.out.printf("%nGoals data for testUser77%n");
		
		for(Goals g : goalsList)
		{
			System.out.printf("%s %s %f %f %f %d %d%n%n", g.getType(), g.getProjectName(),
					g.getTotalCost(), g.getInterestRate(), g.getDownPayment(), g.getYear(), g.getTime());
		}
		

	}
	
	
	/**
	 * Read the Users account information from their account file. 
	 */
	@Test
	public void testReadUserData()
	{
		userData = new UserDataParser(user);
		
		/*
		 * Read the user's account information from their profile
		 * and return a User object with a username, password, salt, phone number.
		 */
		User authenticatedUser = userData.readUserData();
		
		System.out.printf("%nUser account information%n");
		
		System.out.printf("%s %s %s %s\n", authenticatedUser.getUsername(), 
				authenticatedUser.getPassword(), authenticatedUser.getSalt(),
				authenticatedUser.getPhone());
	}
	
	

}
