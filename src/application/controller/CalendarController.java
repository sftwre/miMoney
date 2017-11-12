package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Jonathan Remote
 *
 */

public class CalendarController {

	@FXML
	private Button addExpenseButton;
    
	public CalendarController() {
		
	}

    public void start(Stage primaryStage) {
        //TODO: On click button, pop up DataList

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

}
