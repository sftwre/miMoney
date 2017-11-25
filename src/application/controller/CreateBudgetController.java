package application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


/**
 * Used to create, add, and delete Budget items
 * from the Budget goal in the GoalsView.
 * @author Isaac Buitrago
 *
 */
public class CreateBudgetController {

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
    private CheckBox rentExpense;

    @FXML
    private Button clearButton;

    @FXML
    private Button continueButton;

    @FXML
    private TextField rentTextField;

    @FXML
    private CheckBox foodExpense;

    @FXML
    private ComboBox<String> expensesComboBox;

    @FXML
    private StackPane buttonStackPane;
    
    // Options to display adding a new Expense category
    
    private ObservableList<String> expenseOptions = FXCollections.observableArrayList();	

    
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
    }

    @FXML
    void deleteGoal(ActionEvent event) 
    {

    }

    @FXML
    void clearTextFields(ActionEvent event) {

    }

    @FXML
    void showExpenseCategories(ActionEvent event) {

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
    void Continue(ActionEvent event) {

    }
}
