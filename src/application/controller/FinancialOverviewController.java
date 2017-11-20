package application.controller;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.text.NumberFormat;

import application.Main;
import application.model.*;
import application.model.Expense.Expense;
import application.model.Goals.Goals;

import java.util.ArrayList;
import java.util.HashMap;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
public class FinancialOverviewController {

    @FXML
    private TextField salaryTextField; 			// Text Field for the User to view/edit their salary
    
    @FXML
    private TextField employmentTextField;		// Text Field for the User to view/edit their job title

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
    private GridPane incomePaneGrid;			// Grid to add the save button for editing the Users Income
    
    @FXML
    private StackPane buttonStackPane;			// Stack Pane that contains the Edit and Save buttons
    
    
    private FinancialDataParser financialData;	// FileParser for retrieving the Financial information of the User
    
    private User user;							// Current user of the application
    
    // Expense data for pie chart
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(); 

    // Goal data for the Current Goals pane
    private ObservableList<String> goalsListData = FXCollections.observableArrayList();			
 



/**
 * Called by the FXMLLoader to initialize the controller. 
 * Loads the Income, Expenses, and Goals data of the user
 * and display it in the appropriate controls.
 */
public void initialize()
{
	//instantiate the current user and FinancaialDataParser
	this.user = new User("testUser77");
	
	this.financialData = new FinancialDataParser(user);
	
	//TODO use concurrency to kick each of these off in a separate thread
	
	//load Expense and Goals into the ObservableList's
	loadSpendingDataForChart();
	
	loadGoalsDataForList();
	
	//populate the pieChart to display the expenses of the user
	spendingChart.setData(pieChartData);
	
	//populate the Current Goals pane with the goals of the user
	currentGoalsListView.setItems(goalsListData);
	
	//populate the Income pane with the income of the user
	financialData = new FinancialDataParser(new User("testUser77"));
	
	ArrayList<Income> userIncome = financialData.readIncome();
	
	//format the user income
	employmentTextField.setText(userIncome.get(0).getTitle());
	
	salaryTextField.setText(NumberFormat.getCurrencyInstance().format(userIncome.get(0).getPay()));
		
}

/**
 * Used to retrieve all of the the User's spending data, add
 * the total spent by category and load it into the ObservableList pieChartData.
 */
public void loadSpendingDataForChart()
{
	
		//Create a Date to control what Expense data is retrieved
		Date january = new Date(1,1,2017);
			
		//Get the variable expenses for January
		ArrayList<Expense> monthlyExpenses = financialData.readExpenses(january, FinanceType.REXPENSE);
			
		//Get the fixed expenses for January and append them to the current list of Expenses
		monthlyExpenses.addAll(financialData.readExpenses(january, FinanceType.FEXPENSE));
		
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
	
	//instantiate the goalsListData
	goalsListData = FXCollections.observableArrayList();
	
	//for each goal display the ProjectName and cost
	for(Goals g: goalsList)
	{
		goalsListData.add(String.format("%s : %s", g.getProjectName(),
				NumberFormat.getCurrencyInstance().format(g.getTotalCost())));
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
	editIncomeButton.setDisable(true);
}

	/**
	 * Used to write the modified Income data to the Users' profile.
	 * Once the data is successfully written, the saveIncomeButton is hidden
	 * and sent to the back of the stack pane
	 */

@FXML
public void saveIncomeChanges(ActionEvent event) 
{
        //TODO write FileWriter to be able to write the data to the Users profile
        	
        //TODO validate input
        	
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
        editIncomeButton.setDisable(false);
}  


/**
 * Used to display a new stage for displaying a list
 * of Goals the user can create.
 * @param event that invoked Handler
 */
@FXML
void createNewGoal(ActionEvent event) 
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
		
		System.out.printf("The resource 'view/resources/CarGoalView.fxml' could not be located");
	}

}

}