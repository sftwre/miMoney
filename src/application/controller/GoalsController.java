package application.controller;

/**
 * @author Manuel Deaguinaga
 */
import java.io.File;

/**
 * @author Manuel Deaguinaga
 */

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import application.Main;
import application.model.Goals.Goals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GoalsController implements EventHandler<ActionEvent> {
	
	private FinancialDataParser financialData;	// FileParser for retrieving the Financial information of the User
	private ObservableList<String> goalsListData = FXCollections.observableArrayList();	
	@FXML
	private TextField Cmodel;	// Name of the project
	
	@FXML
	private TextField Cyear;	// Year of the car
	
	@FXML
	private TextField Ccost;	// Total cost of the Goal
	
	@FXML
	private TextField Ctime;	// Time to achieve the goal
	
	@FXML
	private TextField Cinteres;	// Interest rate of the goal
	
	@FXML
	private TextField Cdown;	// Down payment 
	
	@FXML
	private Button Continue;	// Continue button
	
	@FXML
	private Button Cancel;		// Cleaner button
		
	/**
	 * @param ActionEvent event
	 * is using to create the function of each 
	 * button and update the information
	 * in the displayed
	 */
	public void cContinue(ActionEvent event) {
		Goals carGoal = new Goals("CarGoal", this.Cmodel.getText(), Ccost.getText(),
					this.Cinteres.getText(), this.Cdown.getText(), this.Cyear.getText(), this.Ctime.getText());
		//Close window and open goal
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		
		File file = new File("UserProfiles/testUser77/Goals/Auto/"+this.Cmodel.getText());
		System.out.printf(carGoal.toString1());
	}
	
	/**
	 * 
	 * @param event
	 */
	public void cCancel(ActionEvent event){
		this.Cmodel.setText("");
		this.Ccost.setText("");
		this.Cinteres.setText("");
		this.Cdown.setText("");
		this.Cyear.setText("");
		this.Ctime.setText("");
	}
	
	
	@FXML
	private TextField hProjectName;	// Name of the project
	
	@FXML
	private TextField hCost;	// Year of the car
	
	@FXML
	private TextField hTime;	// Time to achieve the goal
	
	@FXML
	private TextField hInteres;	// Interest rate 
	
	@FXML
	private TextField hDown;	// Down Payment
	
	@FXML
	private TextField hTaxas;	// Taxes per year
	
	@FXML
	private TextField hOther;	// Other payment
	
	@FXML
	private Button hContinue;	// Continue button
	
	@FXML
	private Button hCancel;		// Cleaner button 
	
	/**
	 * @param ActionEvent event
	 * is using to create the function of each 
	 * button and update the information
	 * in the displayed
	 */
	public void hContinue(ActionEvent event) {
			
			Goals homeGoal = new Goals("HomeGoal", this.hProjectName.getText(), hCost.getText(),
					this.hInteres.getText(), this.hDown.getText(), this.hTime.getText(), 
					this.hTaxas.getText(), this.hOther.getText());
			//Close window and open goal
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			
			// Create file
			
			// Print the information
			System.out.printf(homeGoal.toString2());
			
	}
	
	public void hCancel(ActionEvent event){
		//Deletes the data entered
		this.hProjectName.setText("");
		this.hCost.setText("");
		this.hInteres.setText("");
		this.hDown.setText("");
		this.hTime.setText("");
		this.hTaxas.setText("");
		this.hOther.setText("");
	}
	

	@FXML
	private TextField lProjectName;	// Name of the project
	
	@FXML
	private TextField lCost;	//
	
	@FXML
	private TextField lTime;	//
	
	@FXML
	private TextField lInteres;	//
	
	@FXML
	private TextField lDown;	//
	
	@FXML
	private TextField lYear;
	
	@FXML
	private TextField lType;
	
	@FXML
	private Button lContinue;
	
	@FXML
	private Button lCancel;
	
	/**
	 * @param ActionEvent event
	 * is using to create the function of each 
	 * button and update the information
	 * in the displayed
	 */
	public void lContinue(ActionEvent event) {
			
			Goals lLomeGoal = new Goals("LoanGoal", this.lProjectName.getText(), lCost.getText(),
					this.lInteres.getText(), this.lDown.getText(), this.lTime.getText());
			//Close window and open goal
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			
			//Create the file txt
			
			//Print the information
			System.out.printf(lLomeGoal.toString3());
	}
	
	public void lCancel(ActionEvent event){
		this.lProjectName.setText("");
		this.lType.setText("");
		this.lCost.setText("");
		this.lInteres.setText("");
		this.lDown.setText("");
		this.lTime.setText("");
	}
	
	
	//Will show the data of the goals
	public void loadGoalsDataForList()
	{
		//load all the Goals from the Goals directory
		ArrayList<Goals> goalsList = financialData.readGoals();
		
		//instantiate the goalsListData
		goalsListData = FXCollections.observableArrayList();
		
		//for each goal display the ProjectName and cost
		for(Goals g: goalsList)
		{
			goalsListData.add(String.format("%s : %s", g.getProjectName(),
					NumberFormat.getCurrencyInstance().format(g.getTotalCost())));
		}

	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}