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


	 

	@FXML
	private TextField user_name;
    @FXML
    private Button createAcctButton;
    @FXML
    private TextField phone_Number;
    @FXML
    private TextField jobInfo;
    @FXML
    private TextField income;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPass;
    
   

    
   
    @FXML
    private Label passwordTxt;
    
   
    
    
    @FXML
    private Label incomeTxt;
    
    @FXML
    private Label username_error;
    
    @FXML
    private Label password_error;

    @FXML
    private Label password2_error;
    
    @FXML
    private Label phone_error;
    
    @FXML
    private Label income_error;
    
    @FXML
    private Label job_error;
    
    @FXML
	private Label jobTxt;

    @FXML
    void createAcct(ActionEvent event) {
    	
				if(user_name.getText().trim().isEmpty() || password.getText().trim().isEmpty() || confirmPass.getText().trim().isEmpty() || phone_Number.getText().trim().isEmpty() || income.getText().trim().isEmpty() || jobInfo.getText().trim().isEmpty()) {
		    		if(user_name.getText().trim().isEmpty()){ 
		    			user_name.setStyle("-fx-border-color: red;");
		    			username_error.setText("username is not entered");
		    			username_error.setTextFill(Color.RED);
		    		}
		    		else {
		    			 user_name.setStyle("-fx-border-color: black;");
		    			 username_error.setText("");
		    		}
		    		
		    		if(password.getText().trim().isEmpty()){
		    			password.setStyle("-fx-border-color: red;");
		    			password_error.setText("password is not entered");
		    			password_error.setTextFill(Color.RED);
		    		}
		    		else {
		    			password.setStyle("-fx-border-color: black;");
		    			password_error.setText("");
		    		}
		    		
		    		if(confirmPass.getText().trim().isEmpty()){
		    			confirmPass.setStyle("-fx-border-color: red;");
		    			password2_error.setText("please confirm password");
		    			password2_error.setTextFill(Color.RED);
		    		}
		    		else {
		    			confirmPass.setStyle("-fx-border-color: black;");
		    			password2_error.setText("");
		    		}
		    		
		    		if(phone_Number.getText().trim().isEmpty()){
		    			phone_Number.setStyle("-fx-border-color: red;");
		    			phone_error.setText("phone number is not entered");
		    			phone_error.setTextFill(Color.RED);
		    		}
		    		else {
		    			phone_Number.setStyle("-fx-border-color: black;");
		    			phone_error.setText("");
		    		}
		    		
		    		if(income.getText().trim().isEmpty()){
		    			  income.setStyle("-fx-border-color: red;");
		    			  income_error.setText("income is not entered");
		    			  income_error.setTextFill(Color.RED);
		    		}
		    		else {
		    			income.setStyle("-fx-border-color: black;");
		    			income_error.setText("");
		    		}
		    			    		
		    		if(jobInfo.getText().trim().isEmpty()) {
		    			jobInfo.setStyle("-fx-border-color: red;");
		    			job_error.setText("job title is not entered");
		    			job_error.setTextFill(Color.RED);
		    		}
		    		else {
		    			jobInfo.setStyle("-fx-border-color: black;");
		    			job_error.setText("");
		    		}
		    	}
			else {
				System.out.println(income.getText());
				System.out.println(jobInfo.getText());
				//Stage popUp =  new Stage();
				
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
		    		Scene scene = new Scene(root);
		    		Main.stage.setScene(scene);
		    		Main.stage.show();
		    		//Main.setScene(scene);
		    		//popUp.setScene(scene);
		    		//popUp.show();
				}catch(IOException e) {
					e.printStackTrace();
					System.out.println("Tutorial page can't be found");
				}
						//goToTutorial(new ActionEvent());
					//}
				//});
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
    	jobTxt.setTooltip(job);
//    	Tooltip.install(income, inc);
//    	Tooltip.install(jobInfo, job);
    	
    	//Rectangle rect = new Rectangle(100, 100);
    	//rect.setOnMouseDragOver(value);
    	
    	//income.setTooltip(text);
    }
    
    /*public void goToTutorial(ActionEvent event) {
    	Stage popUp =  new Stage();
		
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
    		Scene scene = new Scene(root);
    		Main.setScene(scene);
    		//popUp.setScene(scene);
    		//popUp.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
    }*/


}
