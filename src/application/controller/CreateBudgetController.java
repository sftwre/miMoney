package application.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import application.Main;
import application.model.Expense.*;
import application.model.Goals.Budget;
import application.model.Date;


/**
 * Used to create, add, and delete Budget items
 * from the Budget goal in the GoalsView.
 * @author Isaac Buitrago
 *
 */
public class CreateBudgetController {

	
	@FXML
	private Text titleText;			// The title of the Budget
	
	@FXML
    private Text gasText;
	
	@FXML
    private Text foodText;
	
	@FXML
    private Text apperalText;
	
    @FXML
    private Button deleteButton;
    
    @FXML
    private TextField titleTextField;

    @FXML
    private TextField foodTextField;

    @FXML
    private TextField gasTextField;
    
    @FXML
    private TextField apperalTextField;

    @FXML
    private GridPane budgetGridPane;

    @FXML
    private CheckBox gasExpense;

    @FXML
    private CheckBox apperalExpense;

    @FXML
    private Button clearButton;

    @FXML
    private Button continueButton;			// Button to create and save the Budget
    
    @FXML
    private Label invalidTextField;			// Label for invalid Text Field values

    @FXML
    private CheckBox foodExpense;

    @FXML
    private ComboBox<String> expensesComboBox;

    @FXML
    private StackPane buttonStackPane;
    
    // Options to display adding a new Expense category
    private ObservableList<String> expenseOptions = FXCollections.observableArrayList();
    
    // Queue of indexes to delete from a GridPane
    private LinkedList<Integer> indexesToDelete = new LinkedList<Integer>();
    
    // index to place new Budget Items in budgetGridPane
    private int rowIndex;	
    
    // Delete and Clear Buttons in Stack Pane
    private ObservableList<Node> stackPaneButtons;
    
    // List of Check Boxes in budgetGridPane
    private ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    
    // Map of Text for Expense category and associated TextField
    private HashMap<Text, TextField> budgetItemsMap = new HashMap<Text, TextField>();
    
    // Budget for the user
    private Budget budget = new Budget();
    
    /**
     * Used to initialize components of the view
     */
    public void initialize()
    {
    	Thread comboBoxThread = new Thread(){
    		
    		@Override
    		public void run()
    		{
    			loadExpenseCategories();
    			expensesComboBox.setItems(expenseOptions);
    		}
    		
    	};
    	
    	comboBoxThread.run();
    	
    	 stackPaneButtons = buttonStackPane.getChildren();
    	 
    	 //add to the list of Check Boxes 
    	 checkBoxes.add(foodExpense);
    	 checkBoxes.add(gasExpense);
    	 checkBoxes.add(apperalExpense);
    	 
    	 // put the default list items into the map
    	 budgetItemsMap.put(foodText, foodTextField);
    	 budgetItemsMap.put(apperalText, apperalTextField);
    	 budgetItemsMap.put(gasText, gasTextField);
    	 
    	// set the rowIndex to last row in budgetGridPane
    	 rowIndex =  budgetGridPane.getRowConstraints().size()-1;
    	 
    }

    
    /**
     * Used to display the Delete Button to delete a budget item from the Budget
     * and return the row index of the Check Box selected.
     * @param event that called this method
     */
    @FXML
   public void budgetItemSelected(ActionEvent event) 
    {
    	
    	Integer index = GridPane.getRowIndex(((CheckBox)event.getSource())); // row index of Check Box that was just clicked
    	
    	boolean budgetItemToEdit = budgetItemToEdit();					 	// flag to determine if a Budget Item is selected for deletion
    	
    	// Get the row index in the GridPane of the selected CheckBox
    	if(((CheckBox)event.getSource()).isSelected())
    		indexesToDelete.add(index);
    	
    	// the CheckBox was unselected and should not be deleted
    	else
    		indexesToDelete.remove(index);
    		
    	// Bring Delete Button forward in the StackPane if the Top Button is the "Clear" Button
    	if(((Button)stackPaneButtons.get(1)).getText().equals("Clear") && budgetItemToEdit)
    		moveButtonInStackPane(this.buttonStackPane);
    	
    	// If there are no Items selected, bring the Clear Button forward
    	else if( ! budgetItemToEdit)
    		moveButtonInStackPane(this.buttonStackPane);

    }
    
    /**
     * Used to delete all Budget items that had a CheckBox
     * selected and push the Delete Button back in the StackPane.
     * @param event
     */
    @FXML
    public void deleteGoal(ActionEvent event) 
    {
    	ArrayList<Node> nodesToDelete = new ArrayList<Node>();
    	
    	for(Node child : budgetGridPane.getChildren())
    	{
    		if(indexesToDelete.contains(GridPane.getRowIndex(child)))
    		{
    			nodesToDelete.add(child);
    			
    			if(child instanceof Text)
    				budgetItemsMap.remove(child);
    		}
    			
    	}
    	
    	budgetGridPane.getChildren().removeAll(nodesToDelete);
    	
    	moveButtonInStackPane(this.buttonStackPane);
    }

    
    @FXML
    public void clearTextFields(ActionEvent event) {

    }

    /**
     * Used to add a new Budget Item to the Budget.
     * Each Item contains a Label, TextField, and CheckBox
     * @param event
     */
    @FXML
    public void addExpenseCategories(ActionEvent event) 
    {
    	Text itemCategory;		// Expense Category of the Budget Item
    	TextField itemAmount;	// Amount to allocate for the Expense Category
    	CheckBox itemSelected;	// CheckBox for selecting and deleting a Budget Item
    	
    	itemCategory = new Text((String)((ComboBox)(event.getSource())).getValue());
    	
    	itemAmount = new TextField();
    	
    	itemSelected = new CheckBox("Select");
    	
    	// set the properties of the Text
    	itemCategory.setWrappingWidth(200);
    	itemCategory.setTextAlignment(TextAlignment.CENTER);
    	itemCategory.setFontSmoothingType(FontSmoothingType.GRAY);
    	
    	// register an event for the CheckBox
    	itemSelected.addEventHandler(ActionEvent.ACTION, this::budgetItemSelected);
    	
    	// add Check Box to list of Budget Items Check Boxes
    	checkBoxes.add(itemSelected);
    	
    	// create a mapping between the Text and TextField
    	budgetItemsMap.put(itemCategory, itemAmount);
    		
    	// place a new Budget Item in an empty row first before creating a new row
    	if(indexesToDelete.isEmpty())
    		rowIndex++;
    	
    	else
    		rowIndex = indexesToDelete.remove();
    	
    	budgetGridPane.addRow(rowIndex, itemCategory, itemAmount, itemSelected);
    	
    	
    	
    	// align the CheckBox and TextField
    	GridPane.setValignment(itemAmount, VPos.CENTER);
    	GridPane.setHalignment(itemSelected, HPos.CENTER);
    	
    	
    }

    
    /**
     * Used to load Expense Categories into the expensesComboBox
     * @param event
     */
    
    private void loadExpenseCategories()
    {
    	
    	expenseOptions.addAll("Apperal", "Auto Maintenance", "Home Maintenance", "Medical", 
    						  "Education", "Entertainment", "Food", "Gas","Luxury", "Personal Care", 
    						  "Public Transportation", "Subscriptions", "Savings Goal", "Auto Goal", 
    						  "Vacation Goal", "Miscellaneous");
    }
    
    /**
     * Used to create a new Budget from the list of Budget Items
     * provided by the User and save the Budget in the Budget
     * directory of the Goals directory.
     * @param event
     */
    @FXML
    public void saveBudget(ActionEvent event)
    {
    	BufferedWriter bufferedOutput;		// file to write the Budget
    	boolean validBudget = true;			// flag to indicate if all items in the Budget are valid and ready to be stored
    	
    	// no Budget Items available
    	if(budgetItemsMap.isEmpty())
    		return;
    	
    	// validate the title text field with no monetary values
    	if(! isTextFieldValid(titleTextField, false))
    		validBudget = false;
    	
    	for(Text category : budgetItemsMap.keySet())
    	{
    		TextField amount = budgetItemsMap.get(category);

    		// validate the monetary amount within the text field 
    		if( ! isTextFieldValid(amount, true))	
    		{
    			amount.setStyle("-fx-border-color: red;");
    			validBudget = false;
    		}
    		
    		// the TextField is valid
    		else
    		{
    			// remove the red error styling
    			amount.setStyle(null);
    			
    			String value = amount.getText().trim().replaceAll("[,\\$]", "");
    			
    			//create an Expense object based on the Expense category chosen by the User
    			Expense item = createExpenseSubType(category.getText());
    			
    			item.setAmmount(Double.parseDouble(value));
    			
    			// set the Date for the Object to the current month and year
    			YearMonth currentDate = YearMonth.now();
    			
    			Date date = new Date(currentDate.getMonthValue(),1, currentDate.getYear());
    			
    			item.setDate(date);
    			
    			// add the BUdget item to the Budget
    			this.budget.addItem(item);
    		}
    	}
    		
    	// create a file for the Budget and save the Budget if all items are valid
    	if(validBudget)
    	{
    		this.budget.setTitle(titleTextField.getText());
    		
    		invalidTextField.setVisible(false);
    		
    		// create a new file with the same title as the Budget
    		String path = Main.session.currentUser.pathToProfile() + "Goals" + File.separator + "Budget" 
    					+  File.separator + this.budget.getTitle();
    		
    		File filePath =  new File(path);
    		
    		try {
    			
    			boolean fvar =  filePath.createNewFile();
    			
    			if(fvar)
    			{
    				
				BufferedWriter goalFile = new BufferedWriter(new FileWriter(filePath));
				
				goalFile.write(this.budget.toString());
				
				goalFile.flush();
				
				goalFile.close();
				
    			}
    			
    		else
    			invalidTextField.setVisible(true);
    			
    		//TODO Implement an error dialog
			} catch (IOException e) {
				
				
				System.out.printf("Error while accessing %s%n", filePath.getPath());
				
			} catch(SecurityException e)
    		{
				System.out.printf("No permission to write to %s%n", filePath.getPath());
    		}
    		
    	}

    }
    
    
    private boolean isTextFieldValid(TextField titleTextField2) {
		// TODO Auto-generated method stub
		return false;
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
    	String value = amount.getText();
    	
    	if((value.equals("") || value.matches(".*[^\\d,\\.\\$]")) && monetary)
    		return false;	
    	
    	else if(value.equals(""))
    		return false;
    	
    	else
    		return true;
	}


	/**
     * Used to move buttons in the buttonStackPane forward and backward
     */
    private void moveButtonInStackPane(StackPane stackPane)
    {
    	ObservableList<Node> nodes = stackPane.getChildren();
    	
    	if(nodes.size() > 1 )
    	{
    		Node top = nodes.get(nodes.size() - 1);
    		
    		top.toBack();
    	}
    }
    
    /**
     * Used to determine if a Budget Item is selected for deletion
     * @return true if at least one Check Box is selected, false otherwise
     */
    private boolean budgetItemToEdit()
    {
    	boolean itemSelected = false;
    	
    	for(CheckBox box : checkBoxes)
    	{
    		if( box.isSelected())
    			itemSelected = true;
    	}
    	
    	return(itemSelected);
    }
    
    /**
     * Used to
     * @param expenseName
     * @return
     */
    private Expense createExpenseSubType(String expenseName)
    {
    	Expense expense;
    	
    	switch(expenseName)
    	{
    		case "Apperal":
    			return new Apperal();
    			
    		case "Auto Maintenance":
    			return new AutoMaintenance();
    			
    		case "Home Maintenance":
    			return new HomeMaintenance();
    			
    		case "Medical":
    			return new Medical();
    			
    		case "Education":
    			return new Education();
    			
    		case "Entertainment":
    			return new Entertainment();
    			
    		case "Food":
    			return new Food();
    			
    		case "Gas":
    			return new Gas();
    			
    		case "Luxury":
    			return new Luxury();
    			
    		case "Personal Care":
    			return new PersonalCare();
    			
    		case "Public Transportation":
    			return new PublicTransportation();
    			
    		case "Subscriptions":
    			return new Subscriptions();
    			
    		case "Savings Goal":
    			return new SavingsGoalExpense();
    			
    		case "Auto Goal":
    			return new AutoGoalExpense();
    			
    		case "Vacation Goal":
    			return new VacationGoalExpense();
    			
    		case "Miscellaneous":
    			return new Miscellaneous();
  
    	}
    	
    	return null;
    	
    }
    
}
