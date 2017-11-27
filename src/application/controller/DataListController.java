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
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
    private Label doubleLabel;
    
    @FXML
    private Label itemLabel;
    
    @FXML
    private ComboBox<String> expensesComboBox;
    
    private ObservableList<String> expenseOptions = FXCollections.observableArrayList();
    private int counter = 0;
    private Locale USA;
    private NumberFormat usFormat;
    private double total;

    /**
     * 
     * @param title
     * 				title is the name of the DataList window (e.g. Add Expense, Add Recurring Expense)
     * @param message
     * 				message is the prompt text of the TextField at bottom (e.g. add expense... | add recurring expense...)
     */
      
    public void initialize() {
    	total = 10.0;
    	doubleLabel.setText(DecimalFormat.getCurrencyInstance().format(total));
    	t1Field.setPromptText(DecimalFormat.getCurrencyInstance().format(total));
    	
    	addButton.setDefaultButton(true);
    	Thread comboBoxThread = new Thread(){
    		
    		public void run()
    		{
    			System.out.printf("\nin the override\n");
    			loadExpenseCategories();
    			expensesComboBox.setItems(expenseOptions);
    		}
    		
    	};
    	
    	comboBoxThread.run();
    }//END initialize()
    
	@FXML
	public void addButtonExpense(ActionEvent event) {
		if(counter == 0)
		{
			itemLabel.setText(t0Field.getCharacters().toString());
			total = Double.parseDouble(t1Field.getCharacters().toString());
			doubleLabel.setText(DecimalFormat.getCurrencyInstance().format(total));
			counter++;
			
			t1Field.clear();
			t0Field.clear();
			t0Field.requestFocus();
			return;
		}
		
		itemsGridPane.add(new Label(t0Field.getCharacters().toString()), 0, counter);
		total = Double.parseDouble(t1Field.getCharacters().toString());
		itemsGridPane.add(new Label(DecimalFormat.getCurrencyInstance().format(total)), 1, counter);
		counter++;
		t1Field.clear();
		t0Field.clear();
		t0Field.requestFocus();
}//END addAnExpense()
	
    private void loadExpenseCategories()
    {
    	
    	expenseOptions.addAll("Apperal", "Auto Maintenance", "Home Maintenance", "Medical", 
    						  "Education", "Entertainment", "Food", "Gas","Luxury", "Personal Care", 
    						  "Public Transportation", "Subscriptions", "Miscellaneous");
    }//END loadExpenseCategories()

}//END CONTROLLER CLASS DatalistController