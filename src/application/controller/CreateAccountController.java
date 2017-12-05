package application.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import javax.swing.JOptionPane;
import application.Main;
import application.model.Income;
import application.model.DateFormatter;
import application.model.SecureNewPass;
import application.model.User;
import application.model.Expense.AutoInsurance;
import application.model.Expense.AutoPayment;
import application.model.Expense.HealthInsurance;
import application.model.Expense.HomePayment;
import application.model.Goals.Goals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
    
    private Income money;
    
    private AutoInsurance insur;
    
    private HealthInsurance healthy;
    
    private AutoPayment auto;
    
    private HomePayment home;

    private YearMonth currentMonth;

    private User newUser;	// the user account of the application
    
    private Goals goal;

    
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
				
			/**
			 *  Create the user object and fill in it's fields
			 */
				
				newUser =  new User(user_name.getText().trim());
				/*
				 * Commenting out line above (after we talk). This is slightly dangerous
				 * but mostly because it's a little redundant. Sending
				 * password.getCharacters() to SecureNewPass
				 * cuts out this step.
				 */
				
				newUser.setPhone(phone_Number.getText().trim());
				
				String newIncome = income.getText().trim();
				String newJob = jobInfo.getText().trim();
				money = new Income(newJob, Double.parseDouble(newIncome));
				money.userPay(newJob, Double.parseDouble(newIncome));
				
				/**
				 * Extract the data from the text fields
				 */
				String houseDebt = housing.getText().trim();
				String insurance = autoInsur.getText().trim();
				String healthDebt = health.getText().trim();
				String payment = autoPay.getText().trim();
				String newPhone = phone_Number.getText().trim();
				String fd = "Auto";
				String fd2 = "PlayStation 4";
				goal = new Goals(fd, fd2, "600", "2015");
				

				
				File incomeFile = new File(newUser.getPathToProfile() +"Income.txt");
				
				File userFile = new File(newUser.getPathToProfile()+ newUser.getUsername() + ".txt");
				
				File dir = new File(newUser.getPathToProfile() +"AnnualExpenses" + File.separator + currentMonth.getYear());
				
				File dateTrack = new File(newUser.getPathToProfile() + "AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()));
				
				File goalTrack = new File(newUser.getPathToProfile() + "Goals");

				
				if(dateTrack.exists()) {
					
					// TODO kelly, please use the Alert class to alert the user that the account already exists
					// Alert can also be used to give error messages, do this if you have time
					
					JOptionPane.showMessageDialog(null, "directory already exists");
					//alert("");
				}
				
				//TODO please delete all System.out.println() statements, they should not be in the final version
				
				else {
					boolean success = dateTrack.mkdirs();
					if (success){
				      // creating the directory succeeded
				      
				      File expTr = new File(dateTrack+File.separator +"ExpenseTracker.txt");
				      if(expTr.createNewFile()) {
				    	  //Tracker file is created
				      }
				      else {
				    	  
				    	  JOptionPane.showMessageDialog(null, "Tracker failed to print");
				    	  Alert a = new Alert(AlertType.ERROR);
					      a.setTitle("Alert");
					      a.setHeaderText("Tracker failed to print");
					      a.setResizable(true);
					      a.getDialogPane().setPrefSize(480, 320);
					      a.show();
				    	  
				      }
				      
				    }
					else{
				      // creating the directory failed
				      //System.err.println("failed trying to create the directory");
				      JOptionPane.showMessageDialog(null, "failed trying to create the directory");
				      Alert a = new Alert(AlertType.ERROR);
				      a.setTitle("Alert");
				      a.setHeaderText("failed trying to create the directory");
				      a.setResizable(true);
				      a.getDialogPane().setPrefSize(480, 320);
				      a.show();
				    }
				}
				
				if(dir.exists()) {
					//JOptionPane.showMessageDialog(null, "directory already exists");
				}
				else {
					boolean success = dir.mkdirs();
					if (success){
				      // creating the directory succeeded
				      
				      
				      
				    }
					else{
				      // creating the directory failed
				      
				      Alert a = new Alert(AlertType.ERROR);
				      a.setTitle("Alert");
				      a.setHeaderText("failed trying to create the directory");
				      a.setResizable(true);
				      a.getDialogPane().setPrefSize(480, 320);
				      a.show();
				      
				      //JOptionPane.ERROR()
				    }
				}
				if(goalTrack.exists()) {
					
				}
				else {
					boolean success = goalTrack.mkdirs();
					if (success){
				      // creating the directory succeeded
				      File autogoal = new File(goalTrack + File.separator + "Auto");
				      File budgetgoal = new File(goalTrack + File.separator + "Budget");
				      File homegoal = new File(goalTrack + File.separator + "Home");
				      File loangoal = new File(goalTrack + File.separator + "Loan");
				      File vacation = new File(goalTrack + File.separator + "Vacation");
				      
				      if(!autogoal.exists() || !budgetgoal.exists() || !homegoal.exists() || !loangoal.exists() || !vacation.exists()) {
				    	  if(!autogoal.exists()) {
				    		  boolean success1 = autogoal.mkdir();
				    		  if (success1){
							      // creating the directory succeeded
							      
							      File autoFile = new File(autogoal + File.separator + "AutoGoal");
							      if(autoFile.createNewFile()) {
							    	  //Tracker file is created
							      }
				    		  }
				    	  }
				    	  if(!budgetgoal.exists()) {
				    		  boolean success2 = budgetgoal.mkdir();
				    		  if (success2){
							      // creating the directory succeeded
							      
							      File autoFile = new File(budgetgoal + File.separator +" Winter Budget");
							      if(autoFile.createNewFile()) {
							    	  //Tracker file is created
							      }
				    		  }
				    	  }
				    	  if(!homegoal.exists()) {
				    		  boolean success3 = homegoal.mkdir();
				    		  if (success3){
							      // creating the directory succeeded
							      
							      File autoFile = new File(homegoal + File.separator +"home payment goals");
							      if(autoFile.createNewFile()) {
							    	  //Tracker file is created
							      }
				    		  }
				    	  }
				    	  if(!loangoal.exists()) {
				    		  boolean success4 = loangoal.mkdir();
				    		  if (success4){
							      // creating the directory succeeded
							      
							      File autoFile = new File(loangoal + File.separator +"loans");
							      if(autoFile.createNewFile()) {
							    	  //Tracker file is created
							      }
				    		  }
				    	  
				    	  }
				    	  if(!vacation.exists()) {
				    		  boolean success5 = vacation.mkdir();
				    		  if (success5){
							      // creating the directory succeeded
							      
							      File autoFile = new File(vacation + File.separator +"vacation");
							      if(autoFile.createNewFile()) {
							    	  //Tracker file is created
							      }
				    		  }
				    	  
				    	  }
				    }
				      
				  }
					else{
				      // creating the directory failed
				      //System.err.println("failed trying to create the directory");
				      JOptionPane.showMessageDialog(null, "failed trying to create the directory");
				      Alert a = new Alert(AlertType.ERROR);
				      a.setTitle("Alert");
				      a.setHeaderText("failed trying to create the directory");
				      a.setResizable(true);
				      a.getDialogPane().setPrefSize(480, 320);
				      a.show();
				    }
				}
				//if()
				if(incomeFile.createNewFile()) {
					FileWriter writer = new FileWriter(incomeFile);
					
					// using toString() of Income and Expense objects does all the work for you
					writer.write(money.toString());
					writer.close();
					
				}else {
					JOptionPane.showMessageDialog(null, "File already exists.");
				}
				
				if(userFile.createNewFile()) {
					FileWriter writer = new FileWriter(userFile);
					snp = new SecureNewPass();
					snp.secure(newUser.getUsername(), password.getCharacters(), newPhone);
					writer.write(snp.toString());
					writer.close();
					//User file is created
					
				}else {
					JOptionPane.showMessageDialog(null, "File already exists.");
				}
				
				
				/**
				 * It is fine to access the text fields directly here, since at this point the data 
				 * in the Monthly Expenses has been extracted into objects.
				 */
				
				// TODO, kelly notice line 350. I called the toString() of the HomePayment class to take care of this task.
				// please update all calls to write with the appropriate toString() of the object
				
				if(!healthDebt.isEmpty() || !houseDebt.isEmpty() || !payment.isEmpty() || !insurance.isEmpty()) {
					File fixedFile = new File(dir+File.separator +"FixedExpenses.txt");
					if(fixedFile.createNewFile()) {
						//System.out.println("Fixed File is created!");
					}
					//fixedFile.mkdirs();
					FileWriter fill = new FileWriter(fixedFile);
					if(!houseDebt.isEmpty()){
						home = new HomePayment(Double.parseDouble(houseDebt), null, null);
						fill.write(home.toString());
					}
					if(!healthDebt.isEmpty()) {
						healthy = new HealthInsurance(Double.parseDouble(healthDebt), null, null);
						fill.write(healthy.toString());
						
					}if(!payment.isEmpty()) {
						auto = new AutoPayment(Double.parseDouble(payment), null, null);
						fill.write(auto.toString());
						
					}if(!insurance.isEmpty()) {
						insur =  new AutoInsurance(Double.parseDouble(insurance), null, null);
						fill.write(insur.toString());
					}
					
					fill.close();
				}
				
				//Stage popUp =  new Stage();
				
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
		    		Scene scene = new Scene(root);
		    		Main.stage.setScene(scene);
		    		Main.stage.show();
				}catch(IOException e) {
					e.printStackTrace();
					Alert a = new Alert(AlertType.ERROR);
				    a.setTitle("Alert");
				    a.setHeaderText("failed to access to tutorial");
				    a.setResizable(true);
				    a.getDialogPane().setPrefSize(480, 320);
				    a.show();
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
		}catch(IOException e) {
			e.printStackTrace();
		}
    }
    


}