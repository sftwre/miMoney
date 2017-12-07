package application.controller;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.NoSuchElementException;
import application.model.Expense.*;
import application.model.Goals.*;
import application.model.*;


/**
 * A FinancialDataParser can read Income,Expense,and Goals data
 * from the users profile in UserProfiles directory
 * @author Isaac Buitrago
 *
 */
public class FinancialDataParser extends FileParser 
{
	
	private String line;				// Store a line of text from a file
	private String rawObjects[];		// raw data used to instantiate an object

	
	/**
	 * 
	 * @param user who's Financial data is to be accessed
	 */
	public FinancialDataParser(User user) 
	{
		super(user);
		bufferInput = null;
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
	 * Reads the Income data from Income file
	 * @return Income of the user or null if none is provided
	 */
	public Income readIncome()
	{
		Income userIncome = null;		// Income data of the User
		
		//reset root directory of the current user
		setUserProfile(this.user);
		
		try {
			
			//prepare the Buffer to read from Income.txt
			userProfile += "Income";
			
			//read from income.txt in UserProfiles
			bufferInput = new BufferedReader(new FileReader(this.userProfile));
			
			line = bufferInput.readLine();
			
			if(! line.isEmpty())
			{
				//no need to split the line on commas, each line is a separate Income
				
				//check that the data in the file is formatted correctly
				if(! Pattern.matches("[a-zA-Z\\s]+:\\d+\\.\\d{6}", line))
				{
					throw new Exception(String.format("%s is not formatted to standards in %s.%n"
							+ "Standard: Colons seperating fields, commas seperating objects, 6 decimal digits of precision%n"
							+ "for double values, no decimals for integer values."
							+ "%n", line, userProfile));
				}

				//obtain the data for each object by splitting the line on a colon
				rawObjects = line.split(":");
				
				//create a new Income with Job the title and Wage
				userIncome = new Income(rawObjects[0], Double.parseDouble(rawObjects[1]));
			}
			
			bufferInput.close();
					
		} catch(FileNotFoundException e) {
				System.out.printf("File: %s does not exists\n", userProfile);
			
		} catch(IOException e){
			
			System.out.printf("Could not open or close the file: %s\n", userProfile);
			
		} catch(Exception e){
			
			System.out.println(e.getMessage() + " readIncome()");
			
		}
		
			return (userIncome);
	}
		
	
	/**
	 * Used to parse all of the Goals data, except Budget, of the user.
	 * Open each goal file, parse the data, instantiate the Goal and add the Goal to an ArrayList.
	 * @return an ArrayList of Goals data
	 */
	public ArrayList<Goals> readGoals() 
	{
		
		//list of Goals for the user
		ArrayList<Goals> goalsList = new ArrayList<Goals>();
		
		//reset root directory of the current user
		setUserProfile(this.user);
		
		//prepare the userProfile to read from the Goals directory
		this.userProfile += "Goals";
		
		// Path to skip
		Path budgetFolder =  Paths.get("Budget");
		
		try {
			
		Path path = Paths.get(this.userProfile);
		
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
		
		for(Path p : directoryStream)
		{
			// Iterate through all the files in all directories but Goals and parse the goals data
			if(Files.isDirectory(p) && !p.getFileName().equals(budgetFolder))
			{	
				DirectoryStream<Path> filesStream = Files.newDirectoryStream(p);
				
				for(Path f : filesStream)
				{
					//skip hidden files
					if(Files.isHidden(f))
						continue;
					
					//Create reader for character files
					FileReader file =  new FileReader(f.toFile());
					
					bufferInput = new BufferedReader(file);
					
					line = bufferInput.readLine();
					
					while(line != null && ! line.isEmpty() )
					{
						
						
						//split on commas if they are present
						if(line.matches(".*,"))
						{
							rawObjects = line.split(",");
						}
						
						else
							rawObjects = new String[]{line};
							
						
						//for each set of properties, instantiate a new Goal
						for(String object : rawObjects)
						{
							//check that the data in the file is formatted correctly
							if(!formattedData("[a-zA-Z]+:.+:\\d+\\.\\d+:\\d+\\.\\d+:\\d+", object) )
							{
								throw new Exception(String.format("%s is not formatted to standards in %s.%n"
										+ "Standard: Colons seperating fields, commas seperating objects, 6 decimal digits of precision%n"
										+ "for double values, no decimals for integer values."
										+ "%n", object, f.toFile()));
							}
							
							String properties[] =  object.split(":");
							
							
							Goals financialGoal = new Goals(properties[0], properties[1], properties[2], 
									properties[3], properties[4]);
							
							//add the Goals to the list
							goalsList.add(financialGoal);
						}
						
						line = bufferInput.readLine();
					}
					
					bufferInput.close();
				}
				
				filesStream.close();
			}
			
		}
		
		directoryStream.close();
		
		} catch(FileNotFoundException e){
			
			System.out.printf("File: %s does not exists\n", userProfile);
			
		} catch(IOException e){
			
			System.out.printf("Could not locate the directory : %s\n", userProfile);
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " readGoals()");
		}
		
		return(goalsList);
		
	}
	
	/**
	 * Used to parse all of the user's Budgets.
	 * Open each Budget file, parse the data, and instantiate a Budget object.
	 * @return an ArrayList of Budgets
	 */
	public ArrayList<Budget> readBudgets() 
	{
		
		//list of Goals for the user
		ArrayList<Budget> budgetList = new ArrayList<Budget>();
		
		//reset root directory of the current user
		setUserProfile(this.user);
		
		//prepare the userProfile to read from the Budget directory
		this.userProfile += "Goals" + File.separator + "Budget";
		
		try 
		{
			
		Path path = Paths.get(this.userProfile);
		
		DirectoryStream<Path> fileStream = Files.newDirectoryStream(path);
		
		// for all Budgets stored in separate files, open the file and create a Budget
		
		for(Path f : fileStream)
		{
				
			//skip hidden files
			if(Files.isHidden(f))
				continue;
					
				//Create reader for character files
				FileReader file =  new FileReader(f.toFile());
					
				bufferInput = new BufferedReader(file);
					
				line = bufferInput.readLine();
				
					
				/*
				 * First line contains the title of the Budget.
				 * Create a new BUdget with this title
				*/
				Budget budget = new Budget(line);
					
				line = bufferInput.readLine();
					
				// read all the Expense categories within the Budget
					
				while(line != null && ! line.isEmpty())
				{
					
					//check that the current line is formatted correctly
					if(!formattedData("[a-zA-Z]+:\\d+\\.\\d{6}:\\d+\\/\\d+\\/\\d+:.*", line) )
					{
						throw new Exception(String.format("%s is not formatted to standards in %s.%n"
								+ "Standard: Colons seperating fields, commas seperating objects, 6 decimal digits of precision%n"
								+ "for double values, no decimals for integer values."
								+ "%n", line, f.toFile()));
					}
							
					// create the appropriate Expense object from the current line
					Expense budgetItem = createExpenseObject(line);
							
					// add the budgetItem to the budget
					budget.addItem(budgetItem);
							
					line = bufferInput.readLine();
				}
					
				bufferInput.close();
					
				//add Budget to budgetList
				budgetList.add(budget);	
			}
				
			fileStream.close();
				
		} catch(FileNotFoundException e){
			
			System.out.printf("File: %s does not exists\n", userProfile);
			
		} catch(IOException e){
			
			System.out.printf("Could not locate the directory : %s\n", userProfile);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage() + " readGoals()");
		}
		
		return(budgetList);	
	}
	
	/**
	 * Used to parse the Budget data from a single Budget file
	 * @param budgetFile to open in the Users profile
	 * @return a Budget parsed from budgetFile
	 */
	public Budget readBudgetFile(String budgetFile)
	{ 
		// Budget to parse
		Budget budget = new Budget();
		
		//reset root directory of the current user
		setUserProfile(this.user);
				
		//prepare the userProfile to read from the Budget directory
		this.userProfile += "Goals" + File.separator + "Budget" + File.separator + budgetFile;
				
		try 
		{
							
			//Create reader for character files
			FileReader file =  new FileReader(this.userProfile);
							
			bufferInput = new BufferedReader(file);
							
			/*
			* First line contains the title of the Budget.
			* Create a new BUdget with this title
			*/
			line = bufferInput.readLine();
			
			budget.setTitle(line);
				
							
			line = bufferInput.readLine();
							
			// read all the Expense categories within the Budget
							
			while(line != null && ! line.isEmpty())
			{
							
				//check that the current line is formatted correctly
				if(!formattedData("[a-zA-Z]+:\\d+\\.\\d{6}:\\d+\\/\\d+\\/\\d+:.*", line) )
				{
					throw new Exception(String.format("%s is not formatted to standards in %s.%n"
							+ "Standard: Colons seperating fields, commas seperating objects, 6 decimal digits of precision%n"
							+ "for double values, no decimals for integer values."
							+ "%n", line, budgetFile));
				}
									
				// create the appropriate Expense object from the current line
				Expense budgetItem = createExpenseObject(line);
									
				// add the budgetItem to the budget
				budget.addItem(budgetItem);
									
				line = bufferInput.readLine();
			}
							
			bufferInput.close();
							
						
		} catch(FileNotFoundException e){
					
			System.out.printf("File: %s does not exists\n", userProfile);
					
		} catch(IOException e){
					
			System.out.printf("Could not locate the directory : %s\n", userProfile);
					
		} catch (Exception e) {
					
			System.out.println(e.getMessage() + " readGoals()");
		}
				
		return(budget);	
		
	}
	
	
	
	/**
	 * Used to retrieve the Expenses of a user based on the recordType.
	 * If FinanceType FEXPENSE is passed, all the fixed expenses of the User
	 * are parsed from FixedExpenses and placed into an ArrayList
	 * 
	 * If FinanceType REXPENSE is passed, all the monthly expenses in SpendingTracker
	 * are placed into an ArrayList. The SpendingTracker file is selected based on the
	 * year and month in the date argument.
	 * Ex: Date object 1/1/2017 and 1/12/2017 both return the Expense data for the month of
	 * January 2017, so the day used in the Date object is irrelevant.
	 * 
	 * @param date month and year to query expense data
	 * @param recordType of Expense data to query. FEXPENSE for fixed expenses that occur monthly 
	 * and REXPENSE for variable expenses that occur during the month.
	 * .
	 * @return ArrayList of Expense objects
	 */
	
	public ArrayList<Expense> readExpenses(Date date, FinanceType recordType)
	{
		
		ArrayList<Expense> expenseList = new ArrayList<Expense>();
		
		//reset URL of UserProfile to root directory for the current User
		setUserProfile(this.user);
		
try {
			
			if( recordType != FinanceType.FEXPENSE && recordType != FinanceType.REXPENSE)
			{
					throw new NoSuchElementException(
							String.format("readExpenses cannot read records of type: %s", recordType));
			}
			
			//if a REXPENSE is desired, set userProfile to the ExpenseTracker.txt expense file for year and month in date
			if(recordType == FinanceType.REXPENSE)
			{
				
				userProfile += String.format("%s%s%d%s%s%s%s", "AnnualExpenses", File.separator,
						date.getYear(), File.separator, Date.MONTHS_IN_YEAR[date.getMonth()] , 
						File.separator,"ExpenseTracker");
			}
			
			//FEXPENSE is desired, set the userProfile to FixedExpenses.txt expense file for year in date
			else
			{
				userProfile += String.format("%s%s%d%s%s", "AnnualExpenses", File.separator, 
								date.getYear(), File.separator, "FixedExpenses");
			}
			
			//read expense data from the expense file
			bufferInput = new BufferedReader(new FileReader(this.userProfile));
			
			line = bufferInput.readLine();
			
			while(line != null && ! line.isEmpty()) 
			{
				
				//obtain the data for each object by splitting the line on comma
				rawObjects = line.split(",");
				
				for(String object : rawObjects)
				{
					
					//Check the properties of an object are formated correctly
					if(! formattedData("[a-zA-Z]+:\\d+\\.\\d{6}:\\d+\\/\\d+\\/\\d+:.*", object ))
					{	
						throw new Exception(String.format("%s is not formatted to standards in %s.%n"
								+ "Standard: Colons seperating fields, commas seperating objects, 6 decimal digits of precision%n"
								+ "for double values, no decimals for integer values."
								+ "%n", line, userProfile));
					}
					
					//Instantiate the Expense object based on the first token in the properties
					Expense e = createExpenseObject(object);
					
					expenseList.add(e);
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
			
			System.out.println(e.getMessage() + " readExpenses()");
		}
		
		return (expenseList);
		
}
	/**
	 * Used to retrieve the Expense data for a specific month
	 * @param date to retrieve the Expense data
	 */
	
	public ArrayList<Expense> readMonthExpenses(Date date)
	{
		
		ArrayList<Expense> expenseList = new ArrayList<Expense>();
		
		//reset URL of UserProfile to root directory for the current User
		setUserProfile(this.user);
		
		try {
			
			
			userProfile += String.format("%s%s%d%s%s%s%s", "AnnualExpenses", File.separator, 
								date.getYear(), File.separator,Date.MONTHS_IN_YEAR[date.getMonth()],
								File.separator, "ExpenseTracker");
			
			
			//read expense data from the expense file
			bufferInput = new BufferedReader(new FileReader(this.userProfile));
			
			line = bufferInput.readLine();
			
			while(line != null && ! line.isEmpty()) 
			{
				// if the line contains commas, separate each Expense object
				if(line.matches(".*,.*"))
				{
					
					rawObjects = line.split(",");
				}
				
				else
					rawObjects[0] = line;
				
				for(String object : rawObjects)
				{
					
					//Check the properties of an object are formated correctly
					if(! formattedData("[a-zA-Z]+:\\d+\\.\\d{6}:\\d+\\/\\d+\\/\\d+:.*", object ))
					{	
						throw new Exception(String.format("%s is not formatted to standards in %s.%n"
								+ "Standard: Colons seperating fields, commas seperating objects, 6 decimal digits of precision%n"
								+ "for double values, no decimals for integer values."
								+ "%n", line, userProfile));
					}
					
					//Instantiate the Expense object based on the first token in the properties
					Expense e = createExpenseObject(object);
					
					expenseList.add(e);
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
			
			System.out.println(e.getMessage() + " readExpenses()");
		}
		
		return (expenseList);
		
	}
	
	
	
	/**
	 * This method instantiates a new Expense object with it's associated properties.
	 * 
	 * @param object string representing the properties of an Expense object. The properties
	 * are string separated by a colon ':', where the first string indicates the type of Expense object
	 * and all other strings indicate the
	 * Expense.
	 * Ex: Food:5.56:12/15/2016:In-N-Out Double-Double
	 * @return Expense object sub-type 
	 * @throws Exception if the Expense object in properties.get(0) is undefined
	 */
	private Expense createExpenseObject(String object) throws Exception {
		
		//properties of an Expense object separated by colons
		ArrayList<String> properties = new ArrayList<String>(Arrays.asList(object.split(":")));
		
		//If the last colon contains no text, add null to indicate that the Expense contains no item 
		if(properties.size() == 3)
		{
			properties.add(null);
		}
				
		//create a date object from the second property to construct an Expense
		String[] mdy = properties.get(2).split("\\/");
		
		Date date = new Date(Integer.parseInt(mdy[0]), Integer.parseInt(mdy[1]) , Integer.parseInt(mdy[2]));
		
		
		if(properties.get(0).equals("Food"))
		{
			
			return new Food(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else if(properties.get(0).equals("Gas")) 
		{
	
			return new Gas(Double.valueOf(properties.get(1)), date, properties.get(3) );
			 
		}
		
		else if(properties.get(0).equals("Apparel")) 
		{
		
			return new Apparel(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("Medical")) 
		{
		
			return new Medical(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("HomeMaintenance")) 
		{
		
			return new HomeMaintenance(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("AutoMaintenance")) 
		{
		
			return new AutoMaintenance(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("PersonalCare")) 
		{
		
			return new PersonalCare(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("Entertainment")) 
		{
		
			return new Entertainment(Double.valueOf(properties.get(1)), date, properties.get(3));
		
		}
		
		else if(properties.get(0).equals("Education")) 
		{
		
			return new Education(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else if(properties.get(0).equals("Subscriptions")) 
		{
		
			return new Subscriptions(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("Luxury")) 
		{
		
			return new Luxury(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("PublicTransportation")) 
		{
		
			return new PublicTransportation(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("Miscellaneous")) 
		{
		
			return new Miscellaneous(Double.valueOf(properties.get(1)), date, properties.get(3) );
		
		}
		
		else if(properties.get(0).equals("HomeInsurance")) 
		{
		
			return new HomeInsurance(Double.valueOf(properties.get(1)), date, properties.get(3));
		
		}
		
		else if(properties.get(0).equals("LifeInsurance")) 
		{
		
			return new LifeInsurance(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else if(properties.get(0).equals("HealthInsurance")) 
		{
		
			return new HealthInsurance(Double.valueOf(properties.get(1)), date, properties.get(3));
		
		}
		
		else if(properties.get(0).equals("AutoInsurance")) 
		{
		
			return new AutoInsurance(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else if(properties.get(0).equals("AutoPayment")) 
		{
		
			return new AutoPayment(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else if(properties.get(0).equals("HomePayment")) 
		{
		
			return new HomePayment(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else if(properties.get(0).equals("Utilities")) 
		{
		
			return new Utilities(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else if(properties.get(0).equals("SavingsGoalExpense")) 
		{
		
			return new SavingsGoalExpense(Double.valueOf(properties.get(1)), date, properties.get(3));
		
		}
		
		else if(properties.get(0).equals("VacationGoalExpense")) 
		{
		
			return new VacationGoalExpense(Double.valueOf(properties.get(1)), date, properties.get(3));
	
		}
		
		else if(properties.get(0).equals("AutoGoalExpense")) 
		{
		
			return new AutoGoalExpense(Double.valueOf(properties.get(1)), date, properties.get(3));
			
		}
		
		else
			throw new Exception(String.format("No such Expense Object %s", properties.get(0)));
		
		
	}	
	
}