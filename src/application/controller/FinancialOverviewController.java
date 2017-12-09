package application.controller;


import javafx.event.ActionEvent;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;

import application.Main;
import application.model.*;
import application.model.Expense.Expense;
import application.model.Goals.Budget;
import application.model.Goals.Goals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.EventHandler;


/**
 * 
 * Controller handles events in the Financial overview tab of Main.fxml
 * and loads the user's financial information for display
 * @author Isaac Buitrago
 *
 */
public class FinancialOverviewController 
{
	
    @FXML
    private TextField salaryTextField; 			// Text Field for the User to view/edit their salary
    
    @FXML
    private Label invalidSalary;				// Label for Validation
    
    @FXML
    private TextField employmentTextField;		// Text Field for the User to view/edit their job title
    
    @FXML
    private Label invalidEmployment;			// Label for Validation

    @FXML
    private Button editIncomeButton; 			// Button to edit the User's income in the Income panel on the 
    
    @FXML
    private Button saveIncomeButton;			// Button to save the changes made to the Users Income

    @FXML
    private Button newGoalButton;				// Button to create a financial goal
    
    @FXML
    private ListView<String> currentGoalsListView;// ListView for displaying the current goals of the User
    
    @FXML
    private PieChart spendingChart;				// Chart for displaying the Users spending
    
    @FXML
    private Label nullSpendingData;				// Label for indicating that no spending data is available
    
    @FXML
    private GridPane incomePaneGrid;			// Grid to add the save button for editing the Users Income
    
    @FXML
    private StackPane buttonStackPane;			// Stack Pane that contains the Edit and Save buttons
    
    @FXML 
    private StackPane goalsStackPane;			// Stack Pane that contains a ListView and label
    
    @FXML
    private Button addExpensesButton;
    
    @FXML
    private Hyperlink logOutLink;
    
    private FinancialDataParser financialData;	// FileParser for retrieving the Financial information of the User
    
    private Income userIncome;					// Income data of the User
    
    // Expense data for pie chart
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(); 

    // Goal data for the Current Goals pane
    private ObservableList<String> goalsListData = FXCollections.observableArrayList();	
    
    private YearMonth currentMonth;
    
    private LocalDate date;
 


/**
 * Called by the FXMLLoader to initialize the controller. 
 * Loads the Income, Expenses, and Goals data of the user
 * and displays it in the appropriate controls.
 */
public void initialize()
{
	this.financialData = new FinancialDataParser(Main.session.currentUser);
	
	currentMonth = YearMonth.now();
	date = LocalDate.now();
	
	loadSpendingDataForChart();
	
	//If there is no spending data, set the label indicating that no spending data is available to visible
	if(pieChartData.isEmpty())
		nullSpendingData.setVisible(true);
	
	// spending data is available, populate the pieChart to display the expenses of the user
	else 
		spendingChart.setData(pieChartData);
	
	
	// Load all the non Budget Goals into the ListView on the Current Goals panel
	loadGoalsDataForList();
	
	// Load all the Budgets into the ListView on the Current Goals panel
	loadBudgetDataForList();
	
	
	/*
	 * If the User currently does not contain any goals, display a label  
	 * with a proper message.
	*/
	if(goalsListData.isEmpty())
	{
		ObservableList<Node> nodes = goalsStackPane.getChildren();
		

    	if(nodes.size() > 1 )
    	{
    		Node top = nodes.get(nodes.size() - 1);
    		
    		top.toBack();
    	}
		
	}
	
	else 
		//populate the Current Goals pane with the goals of the user
		currentGoalsListView.setItems(goalsListData);
			
	userIncome = financialData.readIncome();
		
	//format the user income
	employmentTextField.setText(userIncome.getTitle());
		
	salaryTextField.setText(NumberFormat.getCurrencyInstance().format(userIncome.getPay()));
		

		
}

/**
 * Used to retrieve all of the the User's spending data for the current month.
 * After all Expense data is loaded,  duplicate Expense categories are merged.
 * The Final operation places all Expenses for the Month in the pieChartData
 * collection.
 */
public void loadSpendingDataForChart()
{
		// retrieve financial data relevant to the current month and year
		YearMonth currentDate = YearMonth.now();
	
		//Create a Date to control what Expense data is retrieved
		Date currentMonth = new Date(currentDate.getMonthValue(), 1, currentDate.getYear());
			
		//Get the variable expenses for January
		ArrayList<Expense> monthlyExpenses = financialData.readExpenses(currentMonth, FinanceType.REXPENSE);
			
		//Get the fixed expenses for January and append them to the current list of Expenses
		monthlyExpenses.addAll(financialData.readExpenses(currentMonth, FinanceType.FEXPENSE));
		
		// if the user has no monthly expenses, leave the pieChartData empty
		if(monthlyExpenses.isEmpty())
			return;
		
		//Combine duplicate Expense objects to obtain the total Expenses per category
		HashMap<String, Double> totalExpenses = totalExpensesByCategory(monthlyExpenses);
		
		//add the expenses to the PieChart
		for(String e : totalExpenses.keySet()) 
		{
				pieChartData.add(new PieChart.Data(e , totalExpenses.get(e)));
		}		
}

/**
 * Given an ArrayList of Expense objects, the className of the
 * Expense is hashed into a map and the amount spent in the Expense
 * category is added to the existing amount for the category, if any.
 * 
 * @param expenseList of Expenses that may contain duplicates
 * @return HashMap mapping an Expense category to the total amount spent for that
 * category in the ArrayList
 */
public HashMap<String, Double> totalExpensesByCategory(ArrayList<Expense> expenseList)
{
	
	 HashMap<String, Double> totalExpenses =  new HashMap<String , Double>();
	 
	 for(Expense e : expenseList)
	 {
		 if( totalExpenses.containsKey(e.getClassName()))
		 {
			 totalExpenses.put(e.getClassName(), totalExpenses.get(e.getClassName()) + e.getAmmount());
		 }
		 
		 else
			 totalExpenses.put(e.getClassName(), e.getAmmount());
	 }
	 
	 return (totalExpenses);
}

/**
 * Used to load all of the Users Goals and load them into the
 * ObservableList goalsListData. 
 */
public void loadGoalsDataForList()
{
	//load all the Goals from the Goals directory
	ArrayList<Goals> goalsList = financialData.readGoals();
	
	//for each goal display the ProjectName and cost
	for(Goals g: goalsList)
	{
		goalsListData.add(String.format("%s : %s", g.getProjectName(),
				NumberFormat.getCurrencyInstance().format(g.getTotalCost())));
	}

}


/**
 * Used to load all of the users Budgets into the goalsListData 
 */
public void loadBudgetDataForList()
{
	//load all the Goals from the Goals directory
	ArrayList<Budget> budgetList = financialData.readBudgets();
		
	//for each Budget, display the title of the Budget 
	for(Budget b: budgetList)
	{
		goalsListData.add(String.format("%s", b.getTitle()));
	}
}

/**
 * Used to set focusTraversable and Editable properties of the
 * text fields in the Income panel to true. This also
 * adds a save button to the panel for saving edits made
 * by the user.
 */
@FXML
public void editIncome(ActionEvent event) 
{
	//set focusTraversable and Editable properties of salaryTextField to true
	salaryTextField.setFocusTraversable(true);
	salaryTextField.setEditable(true);
	
	employmentTextField.setFocusTraversable(true);
	employmentTextField.setEditable(true);
	
	//bring the saveIncomeButton forward in the StackPane
	ObservableList<Node> buttons = this.buttonStackPane.getChildren();
	
	if(buttons.size() > 1 )
	{
		Node top = buttons.get(buttons.size() - 1);
		
		top.toBack();
	}
	
	//make the save button visible
	saveIncomeButton.setVisible(true);
	
	//disable the editIncomeButton
	//editIncomeButton.setDisable(true);
}

	/**
	 * Used to write the modified Income data to the Users' profile.
	 * Once the data is successfully written, the saveIncomeButton is hidden
	 * and sent to the back of the stack pane
	 */

@FXML
public void saveIncomeChanges(ActionEvent event) 
{
	
	String employment = employmentTextField.getText().trim(); 	// temporary variable for employment text
	
	String salary = salaryTextField.getText().trim();			// temporary variable for salary text
      

        //validate User Input
		
	if(! employment.matches("[a-zA-Z\\s]+") || ! salary.matches("\\$*[\\d,]+\\.?\\d*") )
	{
		if(employment.equals("") || employment.matches(".*[^a-zA-Z\\s]+.*"))
		{
			invalidTextAlarm(employmentTextField, invalidEmployment, "* letters and spaces only");
		}
        	

		if(salary.equals("") || salary.matches(".*[^\\d,\\.\\$]"))
		{
			invalidTextAlarm(salaryTextField, invalidSalary, "* monetary values only");
					
		}
	}
		
	else 
	{
			
			// remove error marks from labels
			employmentTextField.setStyle(null);
			salaryTextField.setStyle(null);
			invalidEmployment.setText("");
			invalidSalary.setText("");
			
			// over write the data in the User's Income.txt
			try {
			
			//sanitize data
			salary = salary.replaceAll("[,\\$]", "");
			
			//store the data in the Users profile
			writeIncomeData(employment,salary);
				
			} catch(IOException e){
				
			}
		
		//format the text in the salary text field
		
		salaryTextField.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(salary)));
		
        //set focusTraversable and Editable properties of salaryTextField to false
        salaryTextField.setFocusTraversable(false);
        salaryTextField.setEditable(false);
        		
        employmentTextField.setFocusTraversable(false);
        employmentTextField.setEditable(false);
        
        //bring the Edit button forward in the StackPane
    	ObservableList<Node> buttons = this.buttonStackPane.getChildren();
    	
    	if(buttons.size() > 1 )
    	{
    		Node top = buttons.get(buttons.size() - 1);
    		
    		top.toBack();
    	}
        
        // re-enable the editIncomeButton
        //editIncomeButton.setDisable(false);
        
	}

}  

/**
 * Used to display a new stage for displaying a list
 * of Goals the user can create.
 * @param event that invoked Handler
 */
@FXML
public void createNewGoal(ActionEvent event) 
{
	
	Stage popUp =  new Stage();
	
	popUp.initModality(Modality.APPLICATION_MODAL);
	
	popUp.initOwner(Main.stage);
	
	try
	{
		
	Parent root = FXMLLoader.load(getClass().getResource("../view/resources/GoalsView.fxml"));
	
	Scene scene = new Scene(root);
	
	popUp.setScene(scene);
	
	popUp.show();
	
	} catch(IOException e){
		
		e.printStackTrace();
		
		System.out.printf("The resource 'view/resources/GoalsView.fxml' could not be located");
	}

}

@FXML
public void addAnExpense(ActionEvent event) 
{
	Stage popUp =  new Stage();
	
	popUp.initModality(Modality.APPLICATION_MODAL);
	
	popUp.initOwner(Main.stage);
	
	try
	{
	Parent root = FXMLLoader.load(getClass().getResource("../view/resources/DatalistViewCopy.fxml"));
	
	Scene scene = new Scene(root);
	
	popUp.setScene(scene);
	
	popUp.setTitle("Date of " + DateFormatter.formatMonth(currentMonth.getMonth()) + " "
					+ date.now().getDayOfMonth() + ", "
					+ date.now().getYear());
	
	popUp.show();
	
	} catch(IOException e){
		
		System.out.printf("The resource 'view/resources/DatalistViewCopy.fxml' could not be located");
	}

}//END addAnExpense()


/**
 * Used to highlight a text field and display an error message
 * @param textField to highlight red indicating that something went wrong
 */
private void invalidTextAlarm(TextField textField, Label label, String text)
{
	textField.setStyle("-fx-border-color: red;");
	label.setText(text);
}

/**
 * 
 * @param employment description of the User
 * @param salary of the User
 * @throws IOException if the File cannot be accessed
 */
private void writeIncomeData(String employment, String salary) throws IOException
{
	
	//reset the userIncome object and write it to the file
	userIncome.userPay(employment, Double.parseDouble(salary));
	
	financialData.setUserProfile(Main.session.currentUser);
	
	String incomeFile = financialData.getUserProfile();
	
	BufferedWriter bufferedOutput = new BufferedWriter(new FileWriter(incomeFile + "Income"));
	
	bufferedOutput.write(userIncome.toString());
	
	bufferedOutput.close();
}

@FXML
public void logOut(ActionEvent event) {
	try {
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Login.fxml"));
		Scene scene = new Scene(root);
		Main.stage.setWidth(600);
		Main.stage.setHeight(500);
		Main.setScene(scene);
	}catch(IOException e) {
		e.printStackTrace();
	}
}

}