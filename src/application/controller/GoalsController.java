package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GoalsController implements EventHandler<ActionEvent> {
	
	@FXML
	private Label output;
	
	
	/**
	 * MainControler constructor
	 */
	public GoalsController() {
		super();
	
	}
	
	/**
	 * @param ActionEvent event
	 * is using to create the function of each 
	 * button and update the information
	 * in the displayed
	 */
	@Override
	public void handle(ActionEvent event) {
		Button b = (Button)event.getSource();
		
	
	}
	
	
}