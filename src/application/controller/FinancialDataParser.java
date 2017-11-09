package application.controller;


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.NoSuchElementException;
import application.model.Expense.*;
import application.model.Goals.Goals;
import application.model.*;


/**
 * A FinancialDataParser can read Income,Expense,and Goals data
 * from the users profile in UserProfiles directory
 * @author Isaac Buitrago
 *
 */
public class FinancialDataParser extends FileParser {
	
	private String line;				// Store a line of text from a file
	private String[] rawObjects;		// raw data used to instantiate an object

	public FinancialDataParser(User user, FinanceType recordType) 
	{
		super(user);
		bufferInput = null;
		this.recordType = recordType;
	}
	
	
	/**
	 * Set up the userProfile to point to a specific file or directory
	 * based on the recordType. This is used to prepare for Buffered
	 * reading from the Users' profile.
	 * 
	 */
	private void prepareBuffRead(FinanceType recordType) 
	{
		switch(recordType) 
		{
		
		case INCOME: 
			
			this.userProfile += "income.txt";
			break;
			
		case GOALS:
			
			this.userProfile += "Goals" + File.pathSeparator;
			break;
	
			
		case REXPENSE:
		
			this.userProfile += "AnnualExpenses" + File.pathSeparator;	
			break;
		
		case FEXPENSE:
		
			this.userProfile += "AnnualExpenses" + File.pathSeparator + "FixedExpenses.txt";
			break;
		}
		
	}
	
	/**
	 * 
	 * @param regex Regular Expression to match in a string of data
	 * @param data the string of data to match formatting
	 * @return true if the data matches the associated regex,
	 * false otherwise
	 */
	private boolean formattedData(String regex, String data) 
	{
		return Pattern.matches(regex, data);
	}
	
	/**
	 * Reads the Income data from income.txt
	 * Preconditions: The recordType is INCOME
	 * @return an ArrayList of Income Objects
	 */
	public ArrayList<Income> getIncomeData()
	{
		
		ArrayList<Income> incomeList = new ArrayList<Income>();	//list Income objects to return
		
		try {
			
			if(recordType != FinanceType.INCOME)
			{
					throw new NoSuchElementException(
							String.format("getUserIncomeData cannot read records of type: %s", recordType));
			}
			
			//prepare the Buffer to read from the file
			this.prepareBuffRead(recordType);
			
			//read from income.txt in UserProfiles
			bufferInput = new BufferedReader(new FileReader(this.userProfile));
			
			line = bufferInput.readLine();
			
			while(line != null) 
			{
				//clear the white space
				line = line.replaceAll("\\s","");
				
				//check that the data in the file is formatted correctly
				if(! formattedData("[a-zA-Z\\s]+:\\d+_?\\d*", line))
					throw new Exception(String.format("Data in %s is not formatted to standards", userProfile));

				//obtain the data for each object by splitting the line on a colon
				rawObjects = line.split(":");
				
				//create a new Income with Job the title and Wage
				Income income = new Income(rawObjects[0], Double.parseDouble(rawObjects[1]));
				
				incomeList.add(income);
				
				bufferInput.readLine();
			}
			
			bufferInput.close();
					
		}catch(NoSuchElementException e) {
			
			System.out.println(e.getMessage());
			
		} catch(FileNotFoundException e) {
				System.out.printf("File: %s does not exists\n", userProfile);
			
		} catch(IOException e){
			
			System.out.printf("Could not open or close the file: %s\n", userProfile);
			
		} catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
		
			return (incomeList);
	}
		
	
	/**
	 * Get all the Goals data for the user
	 */
	//TODO 
	public ArrayList<Goals> getUserGoals() { return null;}
	
	
	/**
	 * Retrieve all the monthly expense for a specified month in a specified year.
	 * Ex: Date object 1/1/2017 and 1/12/2017 both return the Expense objects for the month of
	 * January 2017, so the day used in the Date object is irrelevant.
	 * 
	 * @param date month and year to query expense data from the users AnnualExpenses folder.
	 * .
	 * @return ArrayList of Expense objects
	 */
	public ArrayList<Expense> getMonthlyExpenseData(Date date) 
	{
		
		ArrayList<Expense> expensesList =  new ArrayList<Expense>();
		
		
		try {
			
			if(recordType != FinanceType.REXPENSE || recordType != FinanceType.FEXPENSE)
			{
					throw new NoSuchElementException(
							String.format("getMonthlyExpenses cannot read records of type: %s", recordType));
			}
			
			//prepare the Buffer to read from a file relevant to the recordType
			this.prepareBuffRead(recordType);
			
			//set the userProfile to point to the ExpenseTracker.txt for the specified year and month
			userProfile += String.format("%d%c%s%c%s", date.getYear(), File.pathSeparator, 
							Date.MONTHS_IN_YEAR[date.getMonth()] , File.pathSeparator, "ExpenseTracker.txt");
			
			//read expense data from the specified month/year in the AnnualExpenses folder
			bufferInput = new BufferedReader(new FileReader(this.userProfile));
			
			line = bufferInput.readLine();
			
			while(line != null) 
			{

				//obtain the data for each object by splitting the line on comma
				rawObjects = line.split(",");
				
				
				for(String properties : rawObjects)
				{
					
					//clear out white space
					properties = properties.replaceAll("\\s", "");
					
					//Check the properties of an object are formated correctly
					if(! formattedData("[a-zA-Z]+:\\d+_?\\d*\\.?\\d*:\\d+\\/\\d+\\/\\d+", properties))
						throw new Exception(String.format("Data in %s is not formatted to standards", userProfile));
					
					
					//Instantiate the Expense object based on the first token in properties
					Expense e = createExpenseObject(properties.split(":"));
					
					expensesList.add(e);
				}
				
				//read the next line of rawObjects
				line = bufferInput.readLine();
			}
			
			bufferInput.close();
			
		} catch(NoSuchElementException e) {
			
			System.out.println(e.getMessage());
			
		} catch(FileNotFoundException e) {
				System.out.printf("File: %s does not exists\n", userProfile);
			
		} catch(IOException e){
			
			System.out.printf("Could not open or close the file: %s\n", userProfile);
			
		} catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
		return (expensesList);
	}
	
	/**
	 * This method instantiates a new Expense object with it's associated properties.
	 * 
	 * @param properties of the Expense Object to instantiate. The first element of properties
	 * is reserved for the type of Expense object to instantiate, each other element is a property of the
	 * Expense object.
	 * Ex: properties[] = {"Food", "12.56", "12/15/2016"}
	 * @return Expense object 
	 * @throws Exception if the Expense object in properties[0] is undefined
	 */
	private Expense createExpenseObject(String[] properties) throws Exception {
		
		//create a date object to construct an Expense
		String[] mdy = properties[2].split("\\/"); 
		
		Date date = new Date(Integer.parseInt(mdy[0]), Integer.parseInt(mdy[1]) , Integer.parseInt(mdy[2]));
		
		if(properties[0].equals("Food"))
		{
			
		/*
			return new Food(Double.valueOf(properties[1]), date );
		*/
		}
		
		else if(properties[0].equals("Gas")) 
		{
		/*
			return new Gas(Double.valueOf(properties[1]), date );
		*/
		}
		
		else if(properties[0].equals("Apparel")) 
		{
		/*
			return new Apparel(Double.valueOf(properties[1]), date );
		*/
		}
		
		else if(properties[0].equals("Medical")) 
		{
		/*
			return new Medical(Double.valueOf(properties[1]), date );
		*/
		}
		
		else if(properties[0].equals("HomeMaintenance")) 
		{
		/*
			return new HomeMaintenance(Double.valueOf(properties[1]), date );
		*/
		}
		
		else if(properties[0].equals("AutoMaintenance")) 
		{
		/*
			return new AutoMaintenance(Double.valueOf(properties[1]), date );
		*/
		}
		
		else if(properties[0].equals("PersonalCare")) 
		{
		/*
			return new PersonalCare(Double.valueOf(properties[1]), date );
		*/
		}
		
		else if(properties[0].equals("Entertainment")) 
		{
		/*
			return new Entertainment(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("Luxury")) 
		{
		/*
			return new Luxury(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("PublicTransportation")) 
		{
		/*
			return new PublicTransportation(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("Miscellaneous")) 
		{
		/*
			return new Miscellaneous(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("HomeInsurance")) 
		{
		/*
			return new HomeInsurance(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("LifeInsurance")) 
		{
		/*
			return new LifeInsurance(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("HealthInsurance")) 
		{
		/*
			return new HealthInsurance(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("CarInsurance")) 
		{
		/*
			return new CarInsurance(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("CarPayment")) 
		{
		/*
			return new CarPayment(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("HomePayment")) 
		{
		/*
			return new HomePayment(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("SavingsGoalExpense")) 
		{
		/*
			return new SavingsGoalExpense(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("VacationGoalExpense")) 
		{
		/*
			return new VacationGoalExpense(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else if(properties[0].equals("AutoGoalExpense")) 
		{
		/*
			return new AutoGoalExpense(Double.valueOf(properties[1]), date );
		*/	
		}
		
		else
			throw new Exception(String.format("No such Expense Object %s", properties[0]));
		
		return null;
	}

	
	
}
	
