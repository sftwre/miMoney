package application.controller;
import java.nio.file.*;
import java.io.File;
import java.io.FileWriter;

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
	@FXML
	public void cContinue(ActionEvent event) {
		
		//kind name cost interestRate down
		Goals carGoal = new Goals("CarGoal", this.Cmodel.getText(), Ccost.getText(),
					this.Cinteres.getText(), this.Cdown.getText(), this.Cyear.getText(), this.Ctime.getText());
		//Close window and open goal
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		
			
		String path = "UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator +
				"Goals" +  File.separator + "Auto" + File.separator + this.Cmodel.getText();
		
		//if there is a file print the info to the file 
		
		File file = new File(path);
		if(!file.exists()) { 
		
			try {
            
				FileWriter fw = new FileWriter(file);
				fw.write(carGoal.toString());
				fw.close();
			}
				catch (IOException iox) {
				//do stuff with exception
				iox.printStackTrace();
			}
		}
		
		else if(file.exists()){
			System.out.println("Goal Exist");
			
		}
	}
	
	/**
	 * 
	 * @param event
	 *  It cleans the informations that was entered in 
	 *  the search area and the description
	 */
	@FXML
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
	@FXML
	public void hContinue(ActionEvent event) {
			
			Goals homeGoal = new Goals("HomeGoal", this.hProjectName.getText(), hCost.getText(),
					this.hInteres.getText(), this.hDown.getText(), this.hTime.getText(), 
					this.hTaxas.getText(), this.hOther.getText());
			//Close window and open goal
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			
			// Create file
			String path = "UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator +
					"Goals" +  File.separator + "Home" + File.separator + this.hProjectName.getText();
			
			//if there is a file print the info to the file 	
			File file = new File(path);
			if(!file.exists()) { 
			
				try {
	            
					FileWriter fw = new FileWriter(file);
					fw.write(homeGoal.toString());
					fw.close();
				}
					catch (IOException iox) {
					//do stuff with exception
					iox.printStackTrace();
				}
			}
			
			else if(file.exists()){
				System.out.println("Goal Exist");	
			}
	}
	@FXML
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
	@FXML
	public void lContinue(ActionEvent event) {
			
			Goals lLoanGoal = new Goals("LoanGoal", this.lProjectName.getText(), lCost.getText(),
					this.lInteres.getText(), this.lDown.getText(), this.lTime.getText());
			//Close window and open goal
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			
			String path = "UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator +
					"Goals" +  File.separator + "Loan" + File.separator + this.lProjectName.getText();

			//if there is a file print the info to the file 
			File file = new File(path);
			if(!file.exists()) { 
			
				try {
	            
					FileWriter fw = new FileWriter(file);
					fw.write(lLoanGoal.toString());
					fw.close();
				}
					catch (IOException iox) {
					//do stuff with exception
					iox.printStackTrace();
				}
			}
			
			else if(file.exists()){
				System.out.println("Goal Exist");
				
			}
	}
	@FXML
	/**
	 * Cleans the information that was displayed 
	 * @param event
	 */
	public void lCancel(ActionEvent event){
		this.lProjectName.setText("");
		this.lType.setText("");
		this.lCost.setText("");
		this.lInteres.setText("");
		this.lDown.setText("");
		this.lTime.setText("");
	}
	
	@FXML
	private TextField vProjectName;	// Name of the project
	
	@FXML
	private TextField vTotalCost; //	Total cost 
	
	@FXML
	private TextField vTime;	//	Time to achieve the goal
	
	@FXML
	private Button vContinue;	// Continue button
	
	@FXML
	private Button vCancel;		// Cansel button
	
	@FXML
	public void vContinue(ActionEvent event) {
		Goals vacationsGoal = new Goals("Vacations", this.vProjectName.getText(), this.vTotalCost.getText(), this.vTime.getText());
		
		//Close window and open goal
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		
		String path = "UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator +
				"Goals" +  File.separator + "Vacation" + File.separator + this.vProjectName.getText();
		
		//if there is a file print the info to the file 
		
		File file = new File(path);
		if(!file.exists()) { 
		
			try {
            
				FileWriter fw = new FileWriter(file);
				fw.write(vacationsGoal.toString());
				fw.close();
			}
				catch (IOException iox) {
				//do stuff with exception
				iox.printStackTrace();
			}
		}
		else if(file.exists()){
			System.out.println("Goal Exist");
			
		}
	}
	
	@FXML
	/**
	 * Clean the information displayed
	 * @param event
	 */
	public void vCancel(ActionEvent event){
		this.vProjectName.setText("");
		this.vTotalCost.setText("");
		this.vTime.setText("");
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}