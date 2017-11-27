package application.controller;

import java.util.ArrayList;
import java.util.Arrays;
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


/**
 * Used to create, add, and delete Budget items
 * from the Budget goal in the GoalsView.
 * @author Isaac Buitrago
 *
 */
public class CreateBudgetController {

	@FXML
    private Text gasText;
	
	@FXML
    private Text foodText;
	
	@FXML
    private Text apperalText;
	
    @FXML
    private Button deleteButton;

    @FXML
    private TextField foodTextField;

    @FXML
    private TextField gasTextField;

    @FXML
    private GridPane budgetGridPane;

    @FXML
    private CheckBox gasExpense;

    @FXML
    private CheckBox apperalExpense;

    @FXML
    private Button clearButton;

    @FXML
    private Button continueButton;

    @FXML
    private TextField apperalTextField;

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
    	 
    	 //add the Check Boxes to the Collection of CheckBoxes
    	 checkBoxes.add(foodExpense);
    	 checkBoxes.add(gasExpense);
    	 checkBoxes.add(apperalExpense);
    	 
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
    	
    	try
    	{
    		
    	// place a new Budget Item in empty rows first
    	if(indexesToDelete.isEmpty())
    		rowIndex++;
    	
    	else
    		rowIndex = indexesToDelete.remove();
    	
    	budgetGridPane.addRow(rowIndex, itemCategory, itemAmount, itemSelected);
    	
    	// align the CheckBox and TextField
    	GridPane.setValignment(itemAmount, VPos.CENTER);
    	GridPane.setHalignment(itemSelected, HPos.CENTER);
    	
    	} catch(Exception e)
    	{
    	System.out.println(rowIndex);
    	}
    	
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
    
    @FXML
    public void Continue(ActionEvent event) {

    }
    
    /**
     * Used to move buttons in the buttonStackPane forward and backward
     * 
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
    
}
