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
	private Hyperlink forgotUserLink;
	
	@FXML
	private Hyperlink forgotPassLink;
	
	@FXML
	Button loginButton;
	
	@FXML
	Label incorrectCombo;
	
	private User currentUser = new User("testUser77");	//in the mean time, John
	private SecureOldPass oldPass;
	private SecureOldUser oldUser;
	private ActionEvent e;
	
	public void initialize() {
		oldUser = new SecureOldUser();
		oldPass = new SecureOldPass();

		currentUser.setUserAuthenticated(oldUser.auth(userField.getCharacters().toString()));
		
			if(!currentUser.isUserAuthenticated())
				currentUser.setPassAuthenticated(false);
			else if(currentUser.isUserAuthenticated())
				currentUser.setPassAuthenticated(oldPass.secure(currentUser.getUsername(), passField.getCharacters()));

	loginButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			if(currentUser.isPassAuthenticated())
			{
				goToMainView(new ActionEvent());
			}
			incorrectCombo.setText("Username or password is incorrect.");
		}
	});
		
	}// END initialize()
	
	//TODO: method on loginButton press, it current user authenticated then go to mainview else stay
	@FXML
	public void goToMainView(ActionEvent event) {
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/MainView.fxml"));
		Scene scene = new Scene(root);
		popUp.setScene(scene);
		popUp.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/MainView.fxml' could not be located");
		}// END try/catch load FXML
	}
	
	@FXML
	public void goToSignUpView(ActionEvent event) {
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
		Scene scene = new Scene(root);
		popUp.setScene(scene);
		popUp.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/Tutorial.fxml' could not be located");
		}// END try/catch load FXML
	}//END goToSignUpView()
	
	@FXML
	public void goToForgotUserView(ActionEvent event) {
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/ForgotUsername.fxml"));
		Scene scene = new Scene(root);
		popUp.setScene(scene);
		popUp.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/ForgotUsername.fxml' could not be located");
		}// END try/catch load FXML
	}// END goToForgotUserView()
	
	@FXML
	public void goToForgotPassView(ActionEvent event) {
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
		Scene scene = new Scene(root);
		popUp.setScene(scene);
		popUp.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/ForgotPassword.fxml' could not be located");
		}// END try/catch load FXML
	}
	
}//END CONTROLLER CLASS LoginController