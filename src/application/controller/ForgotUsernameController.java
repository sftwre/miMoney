/**
 * 
 */
package application.controller;

import java.io.IOException;

import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Jonathan
 *
 */
public class ForgotUsernameController {
	
	@FXML
	Button backButton;
	
	@FXML
	Button submitButton;
	
	@FXML
	TextField phoneTextField;
	
	@FXML
	Label yourUserIsLabel;
	
	public void initialize() {
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
					goToLoginView(new ActionEvent());
				}
		});
		
	}// END initialize

	@FXML
	public void goToLoginView(ActionEvent event) {
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Login.fxml"));
		Scene scene = new Scene(root);
		Main.setScene(scene);
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/Login.fxml' could not be located");
		}// END try/catch load FXML
	}// END goToLoginView()
	
}// END CONTROLLER CLASS ForgotUsernameController
