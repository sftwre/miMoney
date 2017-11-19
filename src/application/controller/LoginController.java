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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.HyperlinkBuilder;
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
	
	private User currentUser = new User("testUser77");	//in the mean time, John
	private SecureOldPass oldPass;
	private SecureOldUser oldUser;
	
	public void initialize() {
		oldUser = new SecureOldUser();
		currentUser.setUserAuthenticated(oldUser.auth(userField.getCharacters().toString()));
		oldPass = new SecureOldPass();
		currentUser.setPassAuthenticated(oldPass.secure(currentUser.getUsername(), passField.getCharacters()));
		
		//if(currentUser.isPassAuthenticated() && currentUser.isUserAuthenticated())
			//TODO: Login button goes to FinancialOverview.fxml
		
		//TODO: Login button says "Wrong username/password"
	}// END initialize()

	//TODO: method on loginButton press, it current user authenticated then go to mainview else stay
	/*@FXML
	public void addAnExpense(ActionEvent event) {
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/DatalistView.fxml"));
		Scene scene = new Scene(root);
		popUp.setScene(scene);
		popUp.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/DatalistView.fxml' could not be located");
		}// END try/catch load FXML
	}*/
	
}//END CONTROLLER CLASS LoginController
