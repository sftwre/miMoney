package application.controller;






import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.Main;
//import application.MainAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.stage.Modality;
//import javafx.stage.Stage;

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
    private Label passwordTxt;
    
    @FXML
    private Label incomeTxt;
    
    @FXML
    private Label username_error;
    
    @FXML
    private Label password_error;

   
    
    @FXML
    private Label phone_error;
    
    @FXML
    private Label income_error;
    
    @FXML
    private Label job_error;
    
    @FXML
	private Label jobTxt;
    
    @FXML
    private TextField housing;
    
    @FXML
    private TextField gas;
    
    @FXML
    private TextField autoPay;
    
    @FXML
    private TextField autoInsur;
    
    @FXML
    private Hyperlink tutorialLink;

    @FXML
    void createAcct(ActionEvent event) throws IOException {
    	
			if(user_name.getText().trim().isEmpty() || password.getText().trim().isEmpty() || phone_Number.getText().trim().isEmpty() || income.getText().trim().isEmpty() || jobInfo.getText().trim().isEmpty()) {
		    		if(user_name.getText().trim().isEmpty()){ 
		    			user_name.setStyle("-fx-border-color: red;");
		    			username_error.setText("username is not entered");
		    			username_error.setTextFill(Color.BLACK);
		    		}
		    		else {
		    			 user_name.setStyle("-fx-border-color: black;");
		    			 username_error.setText("");
		    		}
		    		
		    		if(password.getText().trim().isEmpty()){
		    			password.setStyle("-fx-border-color: red;");
		    			password_error.setText("password is not entered");
		    			password_error.setTextFill(Color.BLACK);
		    		}
		    		else {
		    			password.setStyle("-fx-border-color: black;");
		    			password_error.setText("");
		    		}
		    		
		    		if(phone_Number.getText().trim().isEmpty()){
		    			phone_Number.setStyle("-fx-border-color: red;");
		    			phone_error.setText("phone number is not entered");
		    			phone_error.setTextFill(Color.BLACK);
		    		}
		    		else {
		    			phone_Number.setStyle("-fx-border-color: black;");
		    			phone_error.setText("");
		    		}
		    		
		    		if(income.getText().trim().isEmpty()){
		    			  income.setStyle("-fx-border-color: red;");
		    			  income_error.setText("income is not entered");
		    			  income_error.setTextFill(Color.BLACK);
		    		}
		    		else {
		    			income.setStyle("-fx-border-color: black;");
		    			income_error.setText("");
		    		}
		    			    		
		    		if(jobInfo.getText().trim().isEmpty()) {
		    			jobInfo.setStyle("-fx-border-color: red;");
		    			job_error.setText("job title is not entered");
		    			job_error.setTextFill(Color.BLACK);
		    		}
		    		else {
		    			jobInfo.setStyle("-fx-border-color: black;");
		    			job_error.setText("");
		    		}
		    	}
		else {
				//this.income = Double.parseDouble(income);
				
				String newUser = user_name.getText().trim();
				String newIncome = income.getText().trim();
				String newJob = jobInfo.getText().trim();
				String houseDebt = housing.getText().trim();
				String gasDebt = gas.getText().trim();
				String payment = autoPay.getText().trim();
				String insurance = autoInsur.getText().trim();
				String fileName = "FixedExpenses.txt";
				String incomeFile = "Income.txt";
				File fixedFile = new File("UserProfiles/"+newUser+"/"+incomeFile);
				File dir = new File("UserProfiles/"+newUser);
				
				if(dir.exists()) {
					System.out.println("directory already exists");
				}
				else {
					boolean success = dir.mkdir();
					if (success){
				      // creating the directory succeeded
				      System.out.println("directory was created successfully");
				      
				    }
					else{
				      // creating the directory failed
				      System.out.println("failed trying to create the directory");
				    }
				}
				if(fixedFile.createNewFile()) {
					System.out.println("File is created!");
					FileWriter writer = new FileWriter(fixedFile);
					writer.write(newJob+":"+ Double.parseDouble(newIncome));
					writer.close();
					
				}else {
					System.out.println("File already exists.");
				}
				
				if(!houseDebt.isEmpty() || !gasDebt.isEmpty() || !payment.isEmpty() || !insurance.isEmpty()) {
					if(!houseDebt.isEmpty()){
						//File fixedFile = new File("UserProfiles/"+newUser+"/"+fileName);
						//FileWriter writer = new FileWriter();
						
					}
					
				}
				
				
				System.out.println(newIncome);
				System.out.println(newJob);
				//Stage popUp =  new Stage();
				
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
		    		Scene scene = new Scene(root);
		    		Main.stage.setScene(scene);
		    		Main.stage.show();
				}catch(IOException e) {
					e.printStackTrace();
					System.out.println("MainView page can't be found");
				}
						
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
    
    @FXML
    public void goToTutorial(ActionEvent event) {
    	//Stage popUp =  new Stage();
		
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
    		Scene scene = new Scene(root);
    		Main.setScene(scene);
    		//popUp.setScene(scene);
    		//popUp.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
    }


}
