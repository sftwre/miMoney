package application.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import application.Main;
import application.model.Date;
import application.model.ExpenseTracker;
import application.model.Expense.Expense;
import application.model.Goals.Budget;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * Used to display the progress of a Users Budget on the
 * Budget tab.
 * 
 * @author Isaac Buitrago
 *
 */
public class ViewBudgetController
{

	@FXML
    private StackPane buttonStackPane;
	
	@FXML
    private Button editButton;

    @FXML
    private VBox vBox;

    @FXML
    private GridPane budgetGridPane;

    @FXML
    private TextField titleTextField;

    @FXML
    private Label invalidTextField;

    @FXML
    private Label successLabel;

    @FXML
    private ComboBox<String> expensesComboBox;
    
    @FXML
    private ComboBox<String> selectBudgetComboBox;

    @FXML
    private Button saveButton;
    
    private String selectedBudget;					// Used to retrieve the Budget file of the selected budget
    
    private Path budgetDir;							// path to the Budget directory of the User
    
    private FinancialDataParser financialData;		// Financial Data Parser for the current user
    
    private ExpenseTracker monthlyExpenses;			// Monthly Expenses for tracking the progress of the Budget
    

    
    /**
     * Used to initialize the controls in the controller
     */
    public void initialize()
    {
    	// set the path  to the Users Budget directory
       	budgetDir = Paths.get(Main.session.currentUser.getPathToProfile() + "Goals" + File.separator + "Budget");
       	
    	// set the options of Expenses for the user
    	expensesComboBox.setItems(loadExpenseCategories());
    	
    	// set the options of existing Budgets to select for the user
    	selectBudgetComboBox.setItems(loadBugets());
    	
    	// create a new FinancialDataParser for parsing the Budget
       	financialData =  new FinancialDataParser(Main.session.currentUser);
       	
    	// Track the progress of Variable Expenses for the current month
    	monthlyExpenses = new ExpenseTracker(new Date(YearMonth.now().getMonthValue(), 
    			Calendar.DAY_OF_MONTH, YearMonth.now().getYear()));
    	
    	// Retrieve all the Variable Expenses for the current month
    	monthlyExpenses.addExpenses(financialData.readMonthExpenses(monthlyExpenses.getDate()));
    }
    

	/**
     * Used to display a Budget from the users available Budgets
     * @param event
     */
    @FXML
    void budgetSelected(ActionEvent event) 
    {
    	
    	// remove all previous nodes from the gridpane
    	budgetGridPane.getChildren().removeAll(budgetGridPane.getChildren());
    	
    	// Retrieve the selected Budget
    	selectedBudget = (String)(((ComboBox)(event.getSource()))).getValue();
    	
    	// parse the selected budget
    	Budget currentBudget = financialData.readBudgetFile(selectedBudget);
    	
    	// current row of the GridPane
    	int currentRow = 0;
    	
    	// set the title of the Budget
    	titleTextField.setText(currentBudget.getTitle());
    	
    	/**
    	 * For each Budget item, create the appropriate controls and
    	 * add them to the budgetGridPane.
    	 */
    	for(Expense e : currentBudget.getItems())
    	{
    		// determine how much was spent per Expense category for the current month
    		double amountSpent = monthlyExpenses.totalSpentInCategory(e);
    		
    		// amount allocated for the Budget item
    		double amountAllocated = e.getAmmount();
    		
    		Label expenseCategoryLabel = new Label(e.getClassName());
    		
    		TextField amountSpentTextField =  new TextField(NumberFormat.getCurrencyInstance().format(amountSpent));
    		
    		// progress bar of how well the user is following their Budget
    		ProgressBar amountSpentBar =  new ProgressBar();
    		amountSpentBar.setProgress(amountSpent / amountAllocated);
    		
    		// Budget amount for the current Expense Category
    		TextField amountAllocatedTextField  = new TextField(NumberFormat.getCurrencyInstance().format(amountAllocated));
    		
    		/*
    		 * Set the properties of the controls
    		 */
    		amountAllocatedTextField.setEditable(false);
    		amountSpentTextField.setEditable(false);
    		
    		// add the controls to the budgetGridPane
    		budgetGridPane.addRow(currentRow, expenseCategoryLabel, amountSpentTextField, 
    				amountSpentBar, amountAllocatedTextField);
    		
    		// set the Column constraints for the controls
    		budgetGridPane.setHalignment(expenseCategoryLabel, HPos.RIGHT);
    		budgetGridPane.setMargin(expenseCategoryLabel, new Insets(0, 8, 0, 0));
    		budgetGridPane.setMargin(amountSpentTextField, new Insets(0,8,0,8));
    		budgetGridPane.setMargin(amountAllocatedTextField, new Insets(0,8,0,8));
    		
    		currentRow++;
    	}
    	
    }

    @FXML
    void editBudget(ActionEvent event) {

    }

    @FXML
    void addExpenseCategories(ActionEvent event) {

    }

    @FXML
    void saveBudget(ActionEvent event) {

    }
    
    /**
     * Used to load the option of Expense categories for the user into the expensesComboBox
     * @return ObservableList of Expenses in String format
     */
    private ObservableList<String> loadExpenseCategories()
    {
    	
    	ObservableList<String> expenseOptions =  FXCollections.observableArrayList();
    	
    	expenseOptions.addAll("Apperal", "Auto Maintenance", "Home Maintenance", "Medical", 
    						  "Education", "Entertainment", "Food", "Gas","Luxury", "Personal Care", 
    						  "Public Transportation", "Subscriptions", "Savings Goal", "Auto Goal", 
    						  "Vacation Goal", "Miscellaneous");
    	
    	return (expenseOptions);
    }
    
    /**
     * Used to load the option of existing budgets for the user into the selectBudgetComboBox
     * @return ObservableList of Budget titles in String format
     */
    private ObservableList<String> loadBugets() 
    {
    	ObservableList<String> budgetOptions =  FXCollections.observableArrayList();
    	
		// traverse the Budget directory and insert the title of Budgets in the budgetOptions list
    	
    	try
    	{
    	DirectoryStream<Path> fileStream = Files.newDirectoryStream(budgetDir);
    	
    	for(Path f : fileStream)
    	{
    		budgetOptions.add("" + f.getFileName() + "");
    	}
    	
    	fileStream.close();
    	
    	} catch(IOException e){
    		
    		System.out.println("An error occured while accessing your information");
    	}
    	
    	
		return(budgetOptions);
	}


    /**
     * Used to retrieve Expense categories from the users monthly Expenses
     * that match those in the current Budget.
     * 
     * @param budget to compare Expense categories
     */
    
    private ArrayList<Expense> relativeExpenseCategories(Budget budget)
    {
    	ArrayList<Expense> relativeExpenseCategories =  new ArrayList<Expense>();
    	
    	ArrayList<String> budgetItems = new ArrayList<String>();
    	
    	
    	for(Expense e : budget.getItems())
    		budgetItems.add(e.getClassName());
    	
    	for(Expense e : this.monthlyExpenses.getExpenses())
    	{
    		if(budgetItems.contains(e))
    			relativeExpenseCategories.add(e);
    	}
    	
    	return(relativeExpenseCategories);
    }
}
