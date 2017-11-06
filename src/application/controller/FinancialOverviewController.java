package application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * 
 * Controller handles events in the Financial overview tab of Main.xml
 * and loads the user's financial information for display
 * @author Isaac Buitrago
 *
 */
public class FinancialOverviewController {

    @FXML
    private TextField salaryTextField; 	// Text Field for the User to view/edit their salary
    
    @FXML
    private TextField employmentTextFeild;	// Text Field for the User to view/edit their job title

    @FXML
    private Button editIncomeButton; 	// Button to edit the User's income in the Income panel on the 

    @FXML
    private Button createGoalButton;	// Button to create a financial goal
    
    @FXML
    private PieChart spendingChart;// Chart for displaying the Users spending

    
 ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(	// data for the pieChart
			
			new PieChart.Data("Housing", 700),
			new PieChart.Data("AutoInsurance", 150),
			new PieChart.Data("AutoPayment",200), 
			new PieChart.Data("Food", 36.75 ),
			new PieChart.Data("Gas", 68.36));
 
/*public FinancialOverviewController()
{
	spendingChart.setData(pieChartData);
	
}
*/
/**
 * Used to open the user's profile for reading and loading data
 * to display on the PieChart
 */
    



public void loadSpendingData()
{
	

 spendingChart.setData(pieChartData);
}

}