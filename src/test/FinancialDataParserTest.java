package test;

import java.util.ArrayList;
import org.junit.Test;
import application.controller.FinancialDataParser;
import application.model.FinanceType;
import application.model.Income;
import application.model.User;

public class FinancialDataParserTest {


	@Test
	public void test() {
		User user = new User("testUser77");

		FinancialDataParser input = new FinancialDataParser(user, FinanceType.INCOME);
		
		ArrayList<Income> incomeList = new ArrayList<Income>();
		
		incomeList =  input.getIncomeData();
		
		for(Income income : incomeList)
		{
			System.out.println(income.getTitle());
		}
	}

}
