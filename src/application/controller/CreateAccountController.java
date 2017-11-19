package application.controller;






import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CreateAccountController {

	private TextField user_name;
    @FXML
    private Button createAcctButton;
    @FXML
    private TextField phone_number;
    @FXML
    private TextField jobInfo;
    @FXML
    private TextField income;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPass;
    
    private Label userText;
    
    private Label phoneNmTxt;
    
    private Label passwordTxt;
    
   
    private Label confirmPassTxt;
    
    private Label incomeTxt;
    
    private Label jobLabel;
   
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
    	if(jobInfo.getText().trim().isEmpty()) {
    		System.err.println("You must enter your fianancial information!");
    	}

    }
    
    @FXML
    void OnMouseDragOver(MouseEvent event) {

    	//if(event.)
    	Tooltip inc = new Tooltip("Enter monthly income");
    	Tooltip pass = new Tooltip("\nYour password must be\n" + "at least 8 characters in length\n");
    	Tooltip job = new Tooltip("Enter the title of your job");
    	passwordTxt.setTooltip(pass);
    	incomeTxt.setTooltip(inc);
    	jobLabel.setTooltip(job);
//    	Tooltip.install(income, inc);
//    	Tooltip.install(jobInfo, job);
    	
    	//Rectangle rect = new Rectangle(100, 100);
    	//rect.setOnMouseDragOver(value);
    	
    	//income.setTooltip(text);
    }


}
