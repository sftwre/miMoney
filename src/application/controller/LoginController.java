/**
 * 
 */
package application.controller;

import application.model.SecureOldPass;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.HyperlinkBuilder;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
	
	private User currentUser;
	private SecureOldPass sop;
	
	public void initialize() {
	currentUser.setUsername(userField.getCharacters().toString());
	sop = new SecureOldPass();
	currentUser.setAuthenticated(sop.secure(currentUser.getUsername(), passField.getCharacters()));
	}

	//TODO: method on loginButton press, it current user authenticated then go to mainview else stay
	
}
