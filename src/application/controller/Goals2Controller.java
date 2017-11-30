package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import application.Main;
import application.model.User;
import application.model.Goals.Goals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Manuel Deaguinaga
 */

public class Goals2Controller {
	
	@FXML
	private Text name;	// Name of the project
	
	@FXML
	private Text totalCost;	// Name of the project
	
	@FXML
	private Text monthlyPayment;	// Name of the project
	
	@FXML
	private Text time;	// Name of the project
	
	@FXML
	private Text interestRate;	// Name of the project
	
	@FXML
	private TextField searchText;
	
	@FXML
    private ListView<String> currentGoalsListView;// ListView for displaying the current goals of the User
    
    @FXML 
    private StackPane goalsStackPane;			// Stack Pane that contains a ListView and label
    
    String [] sGoals = {"Auto","Home","Loan","Savings","Vacation"}; // Array with the different types of goals
	
	public void searchButton (ActionEvent event ){
		int i;
		String path;
		this.searchText.getText();
		File file;
		int notExist = 0;
		//for loop that runs to find the file that is being looking for
		for(i = 0; i<=4; i++){
			path = "UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator +
					"Goals" +  File.separator + sGoals[i] + File.separator + this.searchText.getText();
			
			file = new File(path);
			
			
			
			if(file.exists()){
				
				Scanner in = null;
				
				try {
				    in = new Scanner(new File(path));	//in holds the file information
				} 
				catch (FileNotFoundException exception) {	//prints an error for the file 
				    System.err.println("failed to open data.txt");
				    System.exit(1);
				}		
				
			
				// Transfer data by token to name
				while (in.hasNext()) {
				    String name = in.next();
				    String [] parts = name.split(":");
				    
				    System.out.println(name);
				    
				    this.name.setText(parts[1]);
					this.totalCost.setText(parts[2]);
					this.monthlyPayment.setText(parts[3]);
					this.time.setText(parts[4]);
				     
				    System.out.println(name);
				    
				}
				in.close();	//Close file
				notExist = 0;
				break;
			}
			else if (!file.exists()){
				notExist = 1;
			}
		}
		if ( notExist == 1){
			this.name.setText("");
			this.totalCost.setText("");
			this.monthlyPayment.setText("");
			this.time.setText("");
			Error();
		}
	}
	
	
	public void closeButton (ActionEvent event ){
		this.name.setText("");
		this.totalCost.setText("");
		this.monthlyPayment.setText("");
		this.time.setText("");
		this.searchText.setText("");
		
	}
	public void deleteButton (ActionEvent event ){
		this.name.setText("");
		this.totalCost.setText("");
		this.monthlyPayment.setText("");
		this.time.setText("");
		this.interestRate.setText("");
		
	}
	
	@FXML
	void Error(){			
		Stage popUp =  new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("../view/resources/GoalsError.fxml"));
			Scene scene = new Scene(root);
			popUp.setScene(scene);
			popUp.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/GoalsError.fxml' could not be located");
		}
	}
	
	// Above this line is for search and find goal
	
//--------------------------------------------------------------------------------------------------
	
	//Sam, you can start working from here 
	
	


}
