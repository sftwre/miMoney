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
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
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
    private Rectangle successRectangle;		// Rectangle for displaying a success message with an animation

    @FXML		
    private Label successLabel;				// Label for displaying a success message with an animation
    
    @FXML
    private StackPane budgetStackPane; 		// Stack Pane that contains the budgetGridPane and the success message
    
    @FXML
    private StackPane buttonStackPane;		// Stack Pane that contains the Clear and delete Buttons
    

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
    
    // Map of Text for Expense category and its associated TextField on the budgetGridPane
    private HashMap<Text, TextField> budgetItemsMap = new HashMap<Text, TextField>();
    
    // Budget for the user
    private Budget budget;
    
    /**
     * Used to initialize components of the view and the animations used on those components
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
    	 
    	 // add the default list item check boxes into the List
    	 checkBoxes.add(foodExpense);
    	 checkBoxes.add(gasExpense);
    	 checkBoxes.add(apperalExpense);
    	 
    	 // put the default list items into the map
    	 budgetItemsMap.put(foodText, foodTextField);
    	 budgetItemsMap.put(apperalText, apperalTextField);
    	 budgetItemsMap.put(gasText, gasTextField);
    	 
    	// set the rowIndex to last row in budgetGridPane
    	 rowIndex =  budgetGridPane.getRowConstraints().size()-1;
    	 
    	// instantiate the Budget
    	 budget = new Budget();
    	 
    	// set the row constraints to avoid a NullPointerException
    	GridPane.setRowIndex(gasText, 0);
     	GridPane.setRowIndex(gasTextField, 0);
     	GridPane.setRowIndex(gasExpense, 0);
    	 
    	 
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
    		moveNodeInStackPane(this.buttonStackPane);
    	
    	// If there are no Items selected, bring the Clear Button forward
    	else if( ! budgetItemToEdit)
    		moveNodeInStackPane(this.buttonStackPane);

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
    	
    	moveNodeInStackPane(this.buttonStackPane);
    }

    /**
     * Used to clear all text fields of a Budget
     * @param event
     */
    @FXML
    public void clearTextFields(ActionEvent event) 
    {
    	titleTextField.setText("");
    	
    	for(Text t : budgetItemsMap.keySet())
    		budgetItemsMap.get(t).setText("");

    }

    /**
     * Used to add a new Budget Item to the budgetGridPane.
     * Each Item contains a Label, TextField, and CheckBox
     * @param event
     */
    @FXML
    public void addExpenseCategories(ActionEvent event) 
    {
    	
    	int row;				// row on budgetGridPane to add controls 
    	Text itemCategory;		// Expense Category of the Budget Item
    	TextField itemAmount;	// Amount to allocate for the Expense Category
    	CheckBox itemSelected;	// CheckBox for selecting and deleting a Budget Item
    	
    	itemCategory = new Text((String)((ComboBox)(event.getSource())).getValue());
    	
    	itemAmount = new TextField();
    	
    	itemSelected = new CheckBox("Select");
    	
    	// set the properties of the Text
    	itemCategory.setWrappingWidth(180);
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
    	{
    		rowIndex++;
    		row = rowIndex;
    	}
    	
    	else
    		row = indexesToDelete.remove();
    	
    	budgetGridPane.addRow(row, itemCategory, itemAmount, itemSelected);
    	
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
     * sub-directory of the Goals directory.
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
    	
    	// validate the Title TextField, it must have a value that cannot be monetary
    	if(! isTextFieldValid(titleTextField, false))
    	{
    		validBudget = false;
    		titleTextField.setStyle("-fx-border-color: red;");
    	}
    	
    	
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
    			
    			// set the Date for the Object to the current month, day, year
    			YearMonth currentDate = YearMonth.now();
    			
    			Calendar cal = Calendar.getInstance();
    			
    			Date date = new Date(currentDate.getMonthValue(), cal.get(Calendar.DAY_OF_MONTH) ,currentDate.getYear());
    			
    			item.setDate(date);
    			
    			this.budget.addItem(item);
    		}
    	}
    		
    	// create a file for the Budget, save the Budget if all items are valid, and display a success message
    	if(validBudget)
    	{
    		boolean newFile;		// boolean to determine if the newly created Budget file already exists
    		
    		this.budget.setTitle(titleTextField.getText());
    		
    		invalidTextField.setVisible(false);
    		
    		// create a new file with the same title as the Budget
    		String path = Main.session.currentUser.getPathToProfile() + "Goals" + File.separator + "Budget" 
    					+  File.separator + this.budget.getTitle();
    		
    		File filePath =  new File(path);
    		
    		try {
    			
    			newFile = filePath.createNewFile();
    			
    			// the User has created a new Budget, write it to the file
    			if(newFile)
    			{
    				
				bufferedOutput = new BufferedWriter(new FileWriter(filePath));
				
				bufferedOutput.write(this.budget.toString());
				
				bufferedOutput.flush();
				
				bufferedOutput.close();
				
				// display the rectangle and label for the success message
				moveNodeInStackPane(budgetStackPane);
				
				// set the rectangle and label to visible
				successRectangle.setVisible(true);
				successLabel.setVisible(true);
				
				//apply a fading animation to the Rectangle and Label
				fadeSuccessMessage(successRectangle, successLabel);
				
    			}
    			
    			// the user has created a Budget that already exists
    			else
    			{
    				Alert alert = new Alert(AlertType.WARNING);
    				alert.setTitle("");
    				alert.setHeaderText("A Budget with the title '" + titleTextField.getText() + "' already exists");
    				alert.setContentText("Rename the Budget");
    				alert.showAndWait();
    			}

			} catch (IOException e) {
				
				System.out.printf("Error while accessing %s%n", filePath.getPath());
				
			} catch(SecurityException e){
				System.out.printf("No permission to write to %s%n", filePath.getPath());
    		}
    		
    		
    	}
    	
    	else
			invalidTextField.setVisible(true);
    	
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
     * Used to fade the Rectangle and Label used to display a success message
     * for creating a Budget.
     * 
     * @param rectangle	to set the FadeTransition
     * @param label to set the the FadeTransition
     */
    private void fadeSuccessMessage(Rectangle rectangle, Label label)
    {
    	double durration = 4000.00;
    	
    	// create the Fade Transitions
    	FadeTransition rectangleFade = new FadeTransition(Duration.millis(durration), rectangle);

    	FadeTransition labelFade = new FadeTransition(Duration.millis(durration + .1* durration), label);
    	
    	// set the from and to values for the opacity fading
    	rectangleFade.setToValue(0.0);
    	
    	labelFade.setToValue(0.0);
    	
    	// play the animations
    	
    	rectangleFade.play();
    	
    	labelFade.play();
    		
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
    			return new Apparel();
    			
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
