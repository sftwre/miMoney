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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;

import application.Main;
import application.model.DatalistModel;
import application.model.Date;
import application.model.DateConverter;
import application.model.DateFormatter;
import application.model.FinanceType;
import application.model.Expense.Expense;
import application.model.Expense.VariableExpense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class DataListControllerCopy {
    
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
	Label incorrectCombo;
    
    @FXML
    private ComboBox<String> expensesComboBox;
    
    private ObservableList<String> expenseOptions = FXCollections.observableArrayList();
    
    private int counter = 0;
    
    private double total;
    
    private YearMonth currentMonth;
    
    private SimpleDateFormat sdf;
    
    private LocalDate date;
    
	private Date d;

	static FinancialDataParser input;
	
    private DatalistModel dm;

	//File fixedFile = new File(dir+File.separator +"FixedExpenses.txt");

    /**
     * 
     * @param date 
     * @param currentMonth 
     * @param title
     * 				title is the name of the DataList window (e.g. Add Expense, Add Recurring Expense)
     * @param message
     * 				message is the prompt text of the TextField at bottom (e.g. add expense... | add recurring expense...)
     */
    
    public void initialize() {//YearMonth currentMonth, LocalDate date) implements  Initializable{
    	dm = new DatalistModel();
		currentMonth = YearMonth.now();
		date = LocalDate.of(currentMonth.getYear(), currentMonth.getMonthValue(), LocalDate.now().getDayOfMonth());
		sdf = new SimpleDateFormat("MM/dd/yyyy");
		input = new FinancialDataParser(Main.session.currentUser);
		    	
		total = 10.0;
    	t1Field.setPromptText(DecimalFormat.getCurrencyInstance().format(total));
    	doubleLabel.setText(DecimalFormat.getCurrencyInstance().format(total));
		readExpenses();
    	
    	addButton.setDefaultButton(true);
    	Thread comboBoxThread = new Thread(){
    		
    		public void run()
    		{
    			loadExpenseCategories();
    			expensesComboBox.setItems(expenseOptions);
    		}
    		
    	};//END anonymous class Thread
    	
    	comboBoxThread.run();
    }//END initialize()

	@FXML
	public void addButtonExpense(ActionEvent event) {
		incorrectCombo.setVisible(!validate());
		if(!validate())
			return;
				
		addFreshExpense();
		
		//itemsGridPane.setPrefHeight(67);
		//itemsGridPane.setMinHeight(67);
}//END addAnExpense()
	
    public boolean validate() {
    	boolean auth = false;
		if(t0Field.getCharacters().toString().compareTo("") == 0) {
			incorrectCombo.setText("Must enter item name");
			return auth;
		}else if(t0Field.getCharacters().toString().length() > 45) {
			incorrectCombo.setText("Item length is over 45 characters");
			return auth;
		}else if(t1Field.getCharacters().toString().compareTo("") == 0) {
			incorrectCombo.setText("Must enter an item total");
			return auth;
		}else if(expensesComboBox.getSelectionModel().getSelectedItem() == null) {
			incorrectCombo.setText("Must select expense category");
			return auth;
		}//END if/else validation
		
    	return true;
    }//END validate()
	
	
    
	public void addFreshExpense(){
		if(counter == 0)
		{
			itemLabel.setText(t0Field.getCharacters().toString());
			total = Double.parseDouble(t1Field.getCharacters().toString().replaceAll("[^\\d.]", ""));
			doubleLabel.setText(DecimalFormat.getCurrencyInstance().format(total));
			counter++;
			
			dm.add(expensesComboBox.getSelectionModel().getSelectedItem(), total, date, t0Field.getCharacters().toString());
			t1Field.clear();
			t0Field.clear();
			t0Field.requestFocus();
			return;
		}//END counter is 0
		total = Double.parseDouble(t1Field.getCharacters().toString().replaceAll("[^\\d.]", ""));
		
		itemsGridPane.addRow(counter, 
				new Label(t0Field.getCharacters().toString()), 
				new Label(DecimalFormat.getCurrencyInstance().format(total)));
		dm.add(expensesComboBox.getSelectionModel().getSelectedItem(), total, date, t0Field.getCharacters().toString());
		//itemsGridPane.add(new Label(t0Field.getCharacters().toString()), 0, counter);
		//itemsGridPane.add(new Label(DecimalFormat.getCurrencyInstance().format(total)), 1, counter);
		counter++;
		t1Field.clear();
		t0Field.clear();
		t0Field.requestFocus();
	}//END addExpense()

	public void addOldExpense(String category, double oldTotal, LocalDate date, String item) {
		if(counter == 0)
		{
			itemLabel.setText(item);
			oldTotal = Double.parseDouble((Double.toString(oldTotal).format("%.2f", oldTotal)));
			//doubleLabel.setText("NO");
			doubleLabel.setText(DecimalFormat.getCurrencyInstance().format(oldTotal));
			counter++;
			
			//dm.add(category, oldTotal, date, item);
			t0Field.requestFocus();
			return;
		}
		itemsGridPane.addRow(counter, 
				new Label(item), 
				new Label(DecimalFormat.getCurrencyInstance().format(oldTotal)));
				counter++;
				t0Field.requestFocus();
		
	}//END addOldExpense()
    
    public void readExpenses()
	{
		//Create a Date to control what Expense data is retrieved
		d = DateConverter.convertDate(date);

		//Get the variable expenses for February
		ArrayList<Expense> monthExpenses = input.readExpenses(d, FinanceType.REXPENSE);
		//Get the fixed expenses for February an append them to the current list of Expenses
		monthExpenses.addAll(input.readExpenses(d, FinanceType.FEXPENSE));
		
		//Separate the Variable Expense's from the Fixed Expenses
		for(Expense e : monthExpenses)
		{
			if(e.getDate().toString().compareTo(d.toString()) == 0)
			{
				addOldExpense(e.getClassName(), e.getAmmount(), LocalDate.of(e.getDate().getYear(), e.getDate().getMonth(), e.getDate().getDay()), e.getItem());
			}
		}//END for each expense
		
		System.out.println();
	}//END readExpenses()
    
    private void loadExpenseCategories()
    {
    	
    	expenseOptions.addAll("Apparel", "Auto Maintenance", "Home Maintenance", "Medical", 
    						  "Education", "Entertainment", "Food", "Gas","Luxury", "Personal Care", 
    						  "Public Transportation", "Subscriptions", "Miscellaneous");
    }//END loadExpenseCategories()
    
    public void setDate(LocalDate date) {
    	this.date = date;
    }//END setDate()

}//END CONTROLLER CLASS DatalistController