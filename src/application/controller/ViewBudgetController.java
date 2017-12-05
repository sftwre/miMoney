package application.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private GridPane budgetGridPane;

    @FXML
    private TextField titleTextField;
    
    @FXML
    private Label invalidTextField;
    
    @FXML
    private ComboBox<String> selectBudgetComboBox;

    @FXML
    private Button saveButton;
    
    private Budget currentBudget;						// The user selected Budget
    
    private String selectedBudget;						// Used to retrieve the Budget file of the selected budget
    
    private Path budgetDir;								// path to the Budget directory of the User
    
    private BufferedWriter budgetFile;					// file to save the Budget
    
    private FinancialDataParser financialData;			// Financial Data Parser for the current user
    
    private ExpenseTracker monthlyExpenses;				// Monthly Expenses for tracking the progress of the Budget
    
    private ArrayList<TextField> budgetItemTextFields;	// ArrayList of Budget items in the selected Budget
    
    private Alert errorMessages;						// Alert dialog to give error messages to the user
    
    /**
     * Used to initialize the controls in the controller
     */
    public void initialize()
    {
    	// set the path  to the Users Budget directory
       	budgetDir = Paths.get(Main.session.currentUser.getPathToProfile() + "Goals" + File.separator + "Budget");
    	
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
    public void budgetSelected(ActionEvent event) 
    {
    	budgetItemTextFields =  new ArrayList<TextField>();
    	
    	// remove all previous nodes from the Grid Pane
    	budgetGridPane.getChildren().removeAll(budgetGridPane.getChildren());
    	
    	// Retrieve the selected Budget
    	selectedBudget = (String)(((ComboBox)(event.getSource()))).getValue();
    	
    	// parse the selected budget
    	currentBudget = financialData.readBudgetFile(selectedBudget);
    	
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
    		
    		TextField amountSpentTextField = new TextField(NumberFormat.getCurrencyInstance().format(amountSpent));
    		
    		// set a tool tip on the TextField
    		amountSpentTextField.setTooltip(new Tooltip("The amount spent for the month"));
    		
    		// progress bar of how well the user is following their Budget
    		ProgressBar amountSpentBar = new ProgressBar();
    		amountSpentBar.setProgress(amountSpent/amountAllocated);
    		
    		// Budget amount for the current Expense Category
    		TextField amountAllocatedTextField  = new TextField(NumberFormat.getCurrencyInstance().format(amountAllocated));
    		
    		// set a tool tip on the TextField
    		amountAllocatedTextField.setTooltip(new Tooltip("The amount budgeted for the month"));
    		
    		budgetItemTextFields.add(amountAllocatedTextField);
    		
    		
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

    /**
     * Used to edit the Budget items of the Budget selected by the user
     * @param event
     */
    @FXML
   public void editBudget(ActionEvent event) 
    {
    	titleTextField.setEditable(true);
    	
    	for(TextField t : budgetItemTextFields)
    	{
    		t.setEditable(true);
    	}
    	
    	// bring the Save Button forward
    	moveNodeInStackPane(buttonStackPane);
    	
    }

    /**
     * Used to update the current Budget and save it to the appropriate file
     */
    @FXML
    public void saveBudget(ActionEvent event) 
    {
    	boolean validBudget = true;		// boolean to determine if all the items of a Budget are valid
    	
    	/* 
    	 * Validate all the data edited by the user.
    	 * If any of the items are invalid, display
    	 * an error message and return.
    	 */
    	
    	if(!isTextFieldValid(titleTextField, false))
    	{
    		titleTextField.setStyle("-fx-border-color: red;");
    	
    		validBudget = false;
    	}
    	
    	else
    		titleTextField.setStyle(null);
    	
    	for(TextField t : budgetItemTextFields)
    	{
    		if(!isTextFieldValid(t, true))
    		{
    			validBudget = false;
    			
    			t.setStyle("-fx-border-color: red;");
    		}
    	}
    	
    	 // All Budget items are valid and the currentBudget should be updated
    	 
    	if(validBudget)
    	{
    		// reset the title of the TextField
    		currentBudget.setTitle(titleTextField.getText());
    		
    		// hide the error label
    		invalidTextField.setVisible(false);
    		
    		int index = 0;
    		
    		for(Expense e : currentBudget.getItems())
    		{
    			TextField currentTextField = budgetItemTextFields.get(index);
    			
    			// remove the error styling
    			currentTextField.setStyle(null);
    			
    			// remove all text formatting from the amount
    			String updatedBudgetAmount= currentTextField.getText().trim().replaceAll("[\\$,]","");
    			
    			// set the Budget amount for the Expense
    			e.setAmmount(Double.parseDouble(updatedBudgetAmount));
    			
    			index++;
    		}
    		
    		
    		try {
    			
    			File oldBudgetFile = new File(budgetDir.toFile() + File.separator + selectedBudget);
    			
    			// Budget file to write to, whether renamed or not
    			File budgetFile = oldBudgetFile;
    			
    			 // rename the Budget file if the User renamed the Budget
   			 	if( ! selectedBudget.equals(currentBudget.getTitle()))
   			 	{
   			 		
   			 		File newBudgetFile = new File(budgetDir.toFile() + File.separator + currentBudget.getTitle());
   			 		
   			 		boolean renameSuccess = oldBudgetFile.renameTo(newBudgetFile);
   			 		
   			 		if(! renameSuccess)
   			 		{
	   			 		errorMessages = new Alert(AlertType.WARNING);
	   					errorMessages.setHeaderText("A Budget with the title '" + currentBudget.getTitle() + "' already exists");
	   					errorMessages.setContentText("Rename the Budget");
	   					errorMessages.showAndWait();
	   					
	   					return;
   			 		}
   			 		
   			 		budgetFile = newBudgetFile;
   			 		
   			 	}
   			 	
   			 	// write to the budget file
    			 BufferedWriter bufferedBudgetFile = new BufferedWriter( new FileWriter(budgetFile));
    			 
    			 bufferedBudgetFile.write(this.currentBudget.toString());
    			 
    			 bufferedBudgetFile.close();
    			 
    		} catch(IOException e){
    			
    			errorMessages = new Alert(AlertType.ERROR);
				errorMessages.setTitle("Data Access Error");
				errorMessages.setHeaderText("An Error occured while saving the Budget");
				errorMessages.setContentText("Check ");
				errorMessages.showAndWait();
    		} 
    		
    		
    		// move the node in the StackPane
    		moveNodeInStackPane(buttonStackPane);
    		
    	}
    	
    	// the Budget has an invalid item, display the error message and return
    	else
    	{
    		invalidTextField.setVisible(true);
    		return;
    	}
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
     * Used to validate the amount for a Budget category.
     * A valid amount must contain digits and may contain
     * decimal points, dollar signs, or commas.
     * @param amount to validate
     * @param monetary true if the text field contains a monetary value to be validated
     * @return true if the value in amount is valid, false otherwise
     */
    private boolean isTextFieldValid(TextField amount, boolean monetary)
    {
    	String value = amount.getText().trim();
    	
    	if((value.equals("") || value.matches(".*[^\\d,\\.\\$].*")) && monetary)
    		return false;	
    	
    	else if(value.equals(""))
    		return false;
    	
    	else
    		return true;
	}
    
    
    /**
     * Used to move nodes in a StackPane forward and backward
     */
    private void moveNodeInStackPane(StackPane stackPane)
    {
    	ObservableList<Node> nodes = stackPane.getChildren();
    	
    	if(nodes.size() > 1 )
    	{
    		Node top = nodes.get(nodes.size() - 1);
    		
    		top.toBack();
    	}
    }
}
