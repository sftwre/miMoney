package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * 
 * @author Sam Dash
 *
 */
public class CalculatorController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		System.out.println("clicked!");
	}
}