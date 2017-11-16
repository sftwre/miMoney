package application.controller;

/**
 * @author Manuel Deaguinaga
 */

import java.io.IOException;

import application.Main;
import application.model.Goals.Goals;
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
	private TextField CA;	// Name of the project
	
	@FXML
	private TextField CB;	// Cost in shop
	
	@FXML
	private TextField CC;	//
	
	@FXML
	private TextField CD;	//
	
	@FXML
	private TextField CE;	//
	
	@FXML
	private TextField CF;	//
	
	@FXML
	private Button Continue;
	
	@FXML
	private Button Cancel;
	
	/**
	 * @param ActionEvent event
	 * is using to create the function of each 
	 * button and update the information
	 * in the displayed
	 */
	public void cContinue(ActionEvent event) {
			
			Goals carGoal = new Goals("CarGoal", this.CA.getText(), CB.getText(),
					this.CC.getText(), this.CD.getText(), this.CE.getText(), this.CF.getText());
			//Close window and open goal
	}
	
	public void cCancel(ActionEvent event){
		this.CA.setText("");
		this.CB.setText("");
		this.CC.setText("");
		this.CD.setText("");
		this.CE.setText("");
		this.CF.setText("");
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}