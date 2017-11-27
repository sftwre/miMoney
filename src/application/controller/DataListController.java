/**
 * 
 */
package application.controller;

/**
 * non-final DataList implementation. Should be a catch-all
 * for anything that needs to be displayed in list format with
 * (String) + (Double) pairs.
 * 
 * e.g. ItemName \t $20.15
 * @author Jonathan Remote
 *
 */
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.IOException;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class DataListController {
    
    @FXML
    private Button addButton;
    
    @FXML
    private TextField t0Field;
    
    @FXML
    private TextField t1Field;
    
    @FXML
    private GridPane itemsGridPane;
    
    @FXML
    private ComboBox<String> expensesComboBox;
    
    private ObservableList<String> expenseOptions = FXCollections.observableArrayList();

    /**
     * 
     * @param title
     * 				title is the name of the DataList window (e.g. Add Expense, Add Recurring Expense)
     * @param message
     * 				message is the prompt text of the TextField at bottom (e.g. add expense... | add recurring expense...)
     */
      
   /* public void initialize() {
    	Thread comboBoxThread = new Thread(){
    		
    		@Override
    		public void run()
    		{
    			System.out.printf("\nin the override\n");
    			loadExpenseCategories();
    			expensesComboBox.setItems(expenseOptions);
    		}
    		
    	};
    	
    	comboBoxThread.run();
    }//END initialize()
*/    
	@FXML
	public void addButtonExpense(ActionEvent event) {
		System.out.println("add pressed");
	}//END addAnExpense()
	
    private void loadExpenseCategories()
    {
    	
    	expenseOptions.addAll("Apperal", "Auto Maintenance", "Home Maintenance", "Medical", 
    						  "Education", "Entertainment", "Food", "Gas","Luxury", "Personal Care", 
    						  "Public Transportation", "Subscriptions", "Savings Goal", "Auto Goal", 
    						  "Vacation Goal", "Miscellaneous");
    }//END loadExpenseCategories()

}//END CONTROLLER CLASS DatalistController