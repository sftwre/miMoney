package application.controller;


import javafx.event.ActionEvent;
import java.io.IOException;
import application.Main;
import application.model.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private TextField salaryTextField; 		// Text Field for the User to view/edit their salary
    
    @FXML
    private TextField employmentTextField;	// Text Field for the User to view/edit their job title

    @FXML
    private Button editIncomeButton; 		// Button to edit the User's income in the Income panel on the 
    
    
    private Button saveIncomeButton;		//Button to save edit's made to the Users Income

    @FXML
    private Button newGoalButton;			// Button to create a financial goal
    
    @FXML
    private ListView<String> currentGoalsList;// ListView for displaying the current goals of the User
    
    
    @FXML
    private PieChart spendingChart;			// Chart for displaying the Users spending
    
    @FXML
    private GridPane incomePaneGrid;		// Grid to add the save button for editing the Users Income
    
    private FinancialDataParser financialData;	//FileParser for retrieving the Financial information of the User

    
 ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(	// data for the pieChart
			
			new PieChart.Data("Housing", 700),
			new PieChart.Data("AutoInsurance", 150),
			new PieChart.Data("AutoPayment",200), 
			new PieChart.Data("Food", 36.75 ),
			new PieChart.Data("Gas", 68.36));

 ObservableList<String> goalsListData = FXCollections.observableArrayList("Car : $15,000",	//data for the Goals pane
		 "House : $120,000", "Savings :  $7000");
 
/**
 * Used to open the user's profile for reading and retrieving the Spenfing data of the user
 * to display on the PieChart.
 */
    
public void loadSpendingData()
{
	//TODO
	
	
}

public void loadGoalsData()
{
	//TODO
	currentGoalsList.setItems(goalsListData);
}

/**
 * Called by the FXMLLoader to initialize the controller. 
 * Initialize the Income, Expenses, and Goals data of the user
 * and display it
 */
public void initialize()
{
	//update the pieChart to display the expenses of the user
	spendingChart.setData(pieChartData);
	
	//populate the Income pane with the income of the user
	financialData = new FinancialDataParser(new User("testUser77"));
	
	ArrayList<Income> userIncome = financialData.readIncome(FinanceType.INCOME);
	
	//TODO format the user income
	employmentTextField.setText(userIncome.get(0).getTitle());
	
	salaryTextField.setText(String.valueOf(userIncome.get(0).getPay()));
	
	//populate the Goals pane with the current Goals
	loadGoalsData();
	
	
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
	
	//add the save button to center of the GridPane
	saveIncomeButton = new Button("Save");
	incomePaneGrid.add(saveIncomeButton, 0, 2);
	saveIncomeButton.setAlignment(Pos.CENTER);
	saveIncomeButton.setPrefWidth(80);
	
	//disable the editIncomeButton
	editIncomeButton.setDisable(true);
	
	/**
	 * Used to write the modified Income data to the Users' profile.
	 * Once the data is successfully written, the saveIncomeButton is removed.
	 */
	
	saveIncomeButton.setOnAction(new EventHandler<ActionEvent>() {
		
        @Override
        public void handle(ActionEvent event) {

        	//TODO write FileWriter to be able to write the data to the Users profile
        	
        	//TODO validate input
        	
        	//set focusTraversable and Editable properties of salaryTextField to false
        	salaryTextField.setFocusTraversable(false);
        	salaryTextField.setEditable(false);
        		
        	employmentTextField.setFocusTraversable(false);
        	employmentTextField.setEditable(false);
        	
        	incomePaneGrid.getChildren().remove(saveIncomeButton);
        	
        	// re-enable the editIncomeButton
        	editIncomeButton.setDisable(false);
        }  
	});	
}

/**
 * Used to create a new stage stage for displaying the FXML file
 * with a list of Goals to create maintained in an accordion layout.
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

/** Obtain the User's goals and display them in the Goals TitledPane.
 * 	If the user does not contain any goals, display a label indicating that
 * 	they currently have not created a financial goal
*/
public void getGoals()
{
	
	// TODO load the user's goals and add them to the list
}

}