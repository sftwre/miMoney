package application.controller;






import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private PasswordField password;
    @FXML
    private PasswordField confirmPass;
    
   
    
    private Label phoneNmTxt;
    
    private Label passwordTxt;
    
   
    private Label confirmPassTxt;
    
    private Label incomeTxt;
    
    private Label jobLabel;
    
    
    private Label username_error;
    
    
    private Label password_error;

   
    private Label income_error;

    
    private Label job_error;
    
  
    private Label password2_error;

  
    private Label phone_error;
   
    Text fianancialTxt = new Text("Fianancial Information:");
    

    
    void createAcct(ActionEvent event) {
    	if(user_name.getText().trim().isEmpty() || password.getText().trim().isEmpty() || confirmPass.getText().trim().isEmpty() || phone_number.getText().trim().isEmpty() || income.getText().trim().isEmpty() || jobInfo.getText().trim().isEmpty()) {
    		{ 
    			user_name.setStyle("-fx-border-color: red;");
    			username_error.setText("username is not entered");
    			username_error.setTextFill(Color.RED);
    		}
    		
    		{
    			password.setStyle("-fx-border-color: red;");
    			password_error.setText("password is not entered");
    			password_error.setTextFill(Color.RED);
    		}
    		
    		{
    			confirmPass.setStyle("-fx-border-color: red;");
    			password2_error.setText("please confirm password");
    			password2_error.setTextFill(Color.RED);
    		}
    		
    		{
    			phone_number.setStyle("-fx-border-color: red;");
    			phone_error.setText("phone number is not entered");
    			phone_error.setTextFill(Color.RED);
    		}
    	}
    	
    	
    	
    
    	else {
    		
    		goToTutorial(new ActionEvent());
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
    
    public void goToTutorial(ActionEvent event) {
    	Stage popUp =  new Stage();
		
		//popUp.initModality(Modality.APPLICATION_MODAL);
		//popUp.initOwner(Main.stage);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/resources/Tutorial.fxml"));
    		Scene scene = new Scene(root);
    		popUp.setScene(scene);
    		popUp.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
    }


}
