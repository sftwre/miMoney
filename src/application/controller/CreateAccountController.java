package application.controller;






import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.YearMonth;

import application.Main;
import application.model.MonthFormatter;
import application.model.SecureNewPass;
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
/**
 * Controller handles events in the create account page
 * @author kel
 *
 */

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
    private TextField health;
    
    @FXML
    private TextField autoPay;
    
    @FXML
    private TextField autoInsur;
    
    @FXML
    private Hyperlink tutorialLink;
    
    private SecureNewPass snp;
    
    private YearMonth currentMonth;

    
    /**
	 * Isaac Buitrago commentary
	 * 
	 * Please use An Income object for the Income and Job.
	 * Use a User object for the username, phone and pathToProfile
	 * Create a HomePayment,AutoPayment, and AutoInsurance objects for the
	 * appropriate fields.
	 * 
	 * When it is time to write this data, call the toString of each object
	 */
    @FXML
    void createAcct(ActionEvent event) throws IOException {
    	
    	currentMonth = YearMonth.now();
    	
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
		    			//income.setText(NumberFormat.getCurrencyInstance().format(number));
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
				//String newPass = password.getText().trim();
				/*
				 * Commenting out line above (after we talk). This is slightly dangerous
				 * but mostly because it's a little redundant. Sending
				 * password.getCharacters() to SecureNewPass
				 * cuts out this step.
				 */
				
				
				String phone = phone_Number.getText().trim();
				String newIncome = income.getText().trim();
				String newJob = jobInfo.getText().trim();
				String houseDebt = housing.getText().trim();
				String healthDebt = health.getText().trim();
				String payment = autoPay.getText().trim();
				String insurance = autoInsur.getText().trim();
				String fileName = "FixedExpenses.txt";
				//String incomeFile = "Income.txt";
				File incomeFile = new File("UserProfiles" + File.separator+newUser+File.separator +"Income.txt");
				File userFile = new File("UserProfiles" + File.separator+newUser+File.separator +newUser+".txt");
				File dir = new File("UserProfiles" + File.separator+newUser+File.separator +"AnnualExpenses/2017");
				File dateTrack = new File("UserProfiles" + File.separator +newUser+File.separator +"AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + MonthFormatter.formatMonth(currentMonth.getMonth()));
				if(dateTrack.exists()) {
					System.out.println("directory already exists");
				}
				else {
					boolean success = dateTrack.mkdirs();
					if (success){
				      // creating the directory succeeded
				      System.out.println("directory was created successfully");
				      File expTr = new File(dateTrack+File.separator +"ExpenseTracker.txt");
				      if(expTr.createNewFile()) {
				    	  System.out.println("Expense Tracker file is created!");
				      }
				      else {
				    	  System.out.println("Tracker failed to print");
				      }
				      
				    }
					else{
				      // creating the directory failed
				      System.out.println("failed trying to create the directory");
				    }
				}
				
				if(dir.exists()) {
					System.out.println("directory already exists");
				}
				else {
					boolean success = dir.mkdirs();
					if (success){
				      // creating the directory succeeded
				      System.out.println("directory was created successfully");
				      
				      
				    }
					else{
				      // creating the directory failed
				      System.out.println("failed trying to create the directory");
				    }
				}
				//if()
				if(incomeFile.createNewFile()) {
					System.out.println("Income File is created!");
					FileWriter writer = new FileWriter(incomeFile);
					writer.write(newJob+":"+ Double.parseDouble(newIncome));
					writer.close();
					
				}else {
					System.out.println("File already exists.");
				}
				
				if(userFile.createNewFile()) {
					FileWriter writer = new FileWriter(userFile);
					snp = new SecureNewPass();
					snp.secure(newUser, password.getCharacters(), phone);
					System.out.printf("%s", snp.toString());
					writer.write(snp.toString());
					//writer.write(newUser + ":" + newPass + ":" + phone);
					writer.close();
					System.out.println("User File is created!");
					
				}else {
					System.out.println("File already exists.");
				}
				
				
				
				if(!houseDebt.isEmpty() || !healthDebt.isEmpty() || !payment.isEmpty() || !insurance.isEmpty()) {
					File fixedFile = new File(dir+File.separator +"FixedExpenses.txt");
					if(fixedFile.createNewFile()) {
						System.out.println("Fixed File is created!");
					}
					//fixedFile.mkdirs();
					FileWriter fill = new FileWriter(fixedFile);
					if(!houseDebt.isEmpty()){
						fill.write("Housing:"+Double.parseDouble(houseDebt)+":");
						//fill.close();	
					}
					if(!healthDebt.isEmpty()) {
						fill.write("Health Insurance:"+Double.parseDouble(healthDebt)+":");
						
					}if(!payment.isEmpty()) {
						fill.write("Auto Payment:"+Double.parseDouble(payment)+":");
						
					}if(!insurance.isEmpty()) {
						fill.write("Auto Insurance:"+Double.parseDouble(insurance)+":");
					}
					
					fill.close();
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
