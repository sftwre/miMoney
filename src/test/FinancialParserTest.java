package test;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import application.controller.*;
import application.model.FinanceType;
import application.model.Income;
import application.model.User;

public class FinancialParserTest {

	static User user;
	static FinancialDataParser input;
	UserDataParser userInput;
	
	@BeforeClass
	public static void setUpClass() throws Exception
	{
		user  = new User("testUser77");
		input =  new FinancialDataParser(user);
	}
	
	
	@Test
	public void testReadIncome() 
	{
		ArrayList<Income> incomeList = new ArrayList<Income>();
		
		incomeList =  input.readIncome(FinanceType.INCOME);
		
		for(Income income : incomeList)
			System.out.printf("%s %f\n", income.getTitle(), income.getPay());
		
	}
	
	@Test
	public void testReadExpenses()
	{

	}
	
	@Test
	public void testReadGoals()
	{
		input.readGoals(FinanceType.GOALS);
	}
	
	@Test
	public void testReadUserData()
	{
		userInput = new UserDataParser(user);
		User testUser = userInput.readUserData();
		
		System.out.printf("Username : %s, Password : %s, Phone Number : %s\n",
				testUser.getUsername(), testUser.getPassword(), testUser.getPhone());
		
	}

}
