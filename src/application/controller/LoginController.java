/**
 * 
 */
package application.controller;

import java.io.IOException;

import application.Main;
import application.model.SecureOldPass;
import application.model.SecureOldUser;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Jonathan
 *
 */
public class LoginController {

	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passField;
	
	@FXML
	private Hyperlink signupLink;
	
	@FXML
	Button loginButton;
	
	@FXML
	Label incorrectCombo;
	
	private User currentUser;
	private SecureOldPass oldPass;
	private SecureOldUser oldUser;
	private ActionEvent e;
	
	public void initialize() {
		oldUser = new SecureOldUser();
		oldPass = new SecureOldPass();

	loginButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			currentUser = new User(userField.getCharacters().toString());
			currentUser.setUserAuthenticated(oldUser.auth(currentUser.getUsername()));
			
			if(!currentUser.isUserAuthenticated())
				currentUser.setPassAuthenticated(false);
			else if(currentUser.isUserAuthenticated()) {
				currentUser.setPassAuthenticated(oldPass.secure(currentUser.getUsername(), passField.getCharacters()));
			}

			if(currentUser.isPassAuthenticated())
			{
				Main.session.startSession(currentUser);
				goToMainView(new ActionEvent());
			}
			incorrectCombo.setText("Username or password is incorrect.");
		}
	});
		
	}// END initialize()
	
	//TODO: method on loginButton press, it current user authenticated then go to mainview else stay
	@FXML
	public void goToMainView(ActionEvent event) {
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/MainView.fxml"));
		Scene scene = new Scene(root);
		Main.stage.setWidth(1000);
		Main.stage.setHeight(730);
		
		Main.setScene(scene);
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/MainView.fxml' could not be located");
		}// END try/catch load FXML
	}// END CONTROLLER CLASS goToMainView()
	
	@FXML
	public void goToSignUpView(ActionEvent event) {
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/CreateAccount2.fxml"));
		Scene scene = new Scene(root);
		Main.setScene(scene);
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/CreateAccount2.fxml' could not be located");
		}// END try/catch load FXML
		
	}//END goToSignUpView()
	
}//END CONTROLLER CLASS LoginController