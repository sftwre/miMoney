package application.controller;

import java.io.BufferedWriter;
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
//import application.model.Goals.Goals;
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

	private FileWriter fw;
    
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
    	
			if(user_name.getText().trim().isEmpty() || password.getText().trim().isEmpty() || phone_Number.getText().trim().isEmpty()
					|| income.getText().trim().isEmpty() || jobInfo.getText().trim().isEmpty() || 
					jobInfo.getText().trim().matches(".*[^a-zA-Z\\s]+.*") || income.getText().trim().matches(".*[^\\d,\\.\\$]+.*")) {
				
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
		    		
		    		if(income.getText().trim().isEmpty() || 
		    				income.getText().trim().matches(".*[^\\d,\\.\\$]+.*")){
		    			  income.setStyle("-fx-border-color: red;");
		    			  income_error.setText("monetary values only");
		    			  income_error.setTextFill(Color.BLACK);
		    		}
		    		else {
		    			income.setStyle("-fx-border-color: black;");
		    			income_error.setText("");
		    		}
		    			    		
		    		if(jobInfo.getText().trim().isEmpty() || 
		    				jobInfo.getText().trim().matches(".*[^a-zA-Z\\s]+.*")) {
		    			
		    			jobInfo.setStyle("-fx-border-color: red;");
		    			job_error.setText("letters and spaces only");
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
				
				File incomeFile = new File(newUser.getPathToProfile() +"Income");

				File userFile = new File(newUser.getPathToProfile()+ newUser.getUsername());
				
				File dir = new File(newUser.getPathToProfile() +"AnnualExpenses" + File.separator + currentMonth.getYear());
				
				File dateTrack = new File(newUser.getPathToProfile() + "AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()));
				
				File goalTrack = new File(newUser.getPathToProfile() + "Goals");

				
				
				if(dateTrack.exists()) {
					
					JOptionPane.showMessageDialog(null, "directory already exists");
					
				}
				else {
					boolean success = dateTrack.mkdirs();
					if (success){
				      // creating the directory succeeded
				      
				      File expTr = new File(dateTrack+File.separator +"ExpenseTracker");
				      
				      if(expTr.createNewFile())
				      {
						FileWriter fw = new FileWriter(expTr);

					      try(BufferedWriter bw = new BufferedWriter(fw)) {

								bw.write("Gas:0.000000:1/1/2017:This is a temporary fix");
							} catch (IOException e) {
								
								JOptionPane.showMessageDialog(null, "\nException in try catch DatalistController addtoFile create new file writer\n");
							}
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
				    		 autogoal.mkdir();
				    		  
				    	  }
				    	  if(!budgetgoal.exists()) {
				    		 budgetgoal.mkdir();
				    		  
				    	  }
				    	  if(!homegoal.exists()) {
				    		 homegoal.mkdir();
				    		 
				    	  }
				    	  if(!loangoal.exists()) {
				    		  loangoal.mkdir();
				    	  }
				    	  
				    	  if(!vacation.exists()) {
				    		  vacation.mkdir();
				    	  
				    	  }
				    }
				      
				  }
					else{
				      // creating the directory failed
				      Alert a = new Alert(AlertType.ERROR);
				      a.setTitle("Alert");
				      a.setHeaderText("failed trying to create the directory");
				      a.setResizable(true);
				      a.getDialogPane().setPrefSize(480, 320);
				      a.show();
				    }
				}
				if(incomeFile.createNewFile()) {
					FileWriter writer = new FileWriter(incomeFile);
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
				
				if(!healthDebt.isEmpty() || !houseDebt.isEmpty() || !payment.isEmpty() || !insurance.isEmpty()) {
					File fixedFile = new File(dir+File.separator +"FixedExpenses");
					if(fixedFile.createNewFile()) {
						//System.out.println("Fixed File is created!");
					}
					//fixedFile.mkdirs();
					FileWriter fill = new FileWriter(fixedFile);
					if(!houseDebt.isEmpty()){
						home = new HomePayment(Double.parseDouble(houseDebt), null, null);
						fill.write(home.toString() + ",");
					}
					if(!healthDebt.isEmpty()) {
						healthy = new HealthInsurance(Double.parseDouble(healthDebt), null, null);
						fill.write(healthy.toString() + ",");
						
					}if(!payment.isEmpty()) {
						auto = new AutoPayment(Double.parseDouble(payment), null, null);
						fill.write(auto.toString() + ",");
						
					}if(!insurance.isEmpty()) {
						insur =  new AutoInsurance(Double.parseDouble(insurance), null, null);
						fill.write(insur.toString() + ",");
					}
					
					fill.close();
				}
				
				//Stage popUp =  new Stage();
				
				
				try {
					
					// authenticate the User
		    		Main.session.startSession(newUser);
		    		
					Parent root = FXMLLoader.load(getClass().getResource("../view/resources/Tutorial.fxml"));
		    		Scene scene = new Scene(root);
		    		
		    		Main.stage.setScene(scene);
		    		Main.stage.show();
				}catch(IOException e) {
					e.printStackTrace();
					Alert a = new Alert(AlertType.ERROR);
				    a.setTitle("Alert");
				    a.setHeaderText("failed to access the tutorial");
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