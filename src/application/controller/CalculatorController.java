package calculator.controller;

import calculator.main.CalculatorMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CalculatorController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		// print a message to the console (debugging step, to ensure everything is connected)
		System.out.println("clicked!");
		
		try {
			// change over to a second view
			Parent root = FXMLLoader.load(getClass().getResource("/CalcView.fxml"));   // Load the FXML
			CalculatorMain.stage.setScene(new Scene(root, 500, 575));							   // Add the scene to the stage
			CalculatorMain.stage.show();														   // Show the stage to the user
		}catch(Exception e) {
			e.printStackTrace(); // TODO: app should do something more productive if errors occur...
		}
	}
}