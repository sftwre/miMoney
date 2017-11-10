package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import application.model.Goals.Goals;

public class GoalsController implements EventHandler<ActionEvent> {
	
	@FXML
	private Label output;
	private Goals goals;
	
	/**
	 * MainControler constructor
	 */
	public GoalsController() {
		super();
		this.goals = new Goals();
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
		this.goals.information( b.getText());
		this.output.setText( goals.getData() );
	}
	
	
}