package application.controller;






import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreateAccountController {

	private TextField user_name;
    @FXML
    private Button createAcctButton;
    @FXML
    private TextField phone_number;
    @FXML
    private TextField fiananceInfo;
    @FXML
    private TextField income;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPass;
    
    private Text userText;
    
    private Text phoneNmTxt;
    //Text usernameTxt = new Text("Username:");
    
    //Text phoneNmTxt = new Text("Phone Number:");
    
    Text passwordTxt = new Text("Password:");
   
    Text confirmPassTxt = new Text("Confirm Password:");
    
    Text incomeTxt = new Text("Income:");
   
    Text fianancialTxt = new Text("Fianancial Information:");
    

    @FXML
    void createAcct(ActionEvent event) {
    	if(user_name.getText().trim().isEmpty()) {
    		System.err.println("You must enter your username!");
    	}
    	if(password.getText().trim().isEmpty()) {
    		System.err.println("You must enter a password!");
    	}
    	if(confirmPass.getText().trim().isEmpty()) {
    		System.err.println("You must confirm your password!");
    	}
    	if(phone_number.getText().trim().isEmpty()) {
    		System.err.println("You must enter your phone number!");
    	}
    	if(income.getText().trim().isEmpty()) {
    		System.err.println("You must enter your monthly income!");
    	}
    	if(fiananceInfo.getText().trim().isEmpty()) {
    		System.err.println("You must enter your fianancial information!");
    	}

    }

}
