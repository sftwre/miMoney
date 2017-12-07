package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
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
	private TextField searchText;
    
    int fileType;
    
    String [] sGoals = {"Auto","Home","Loan","Savings","Vacation"}; // Array with the different types of goals
	
    /**
     * Search for the goal that was being entered 
     * and display the information if it is founded
     * other wise will display an error message 
     * @param event
     */
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
				    notExist = 1;
				    //System.exit(1);
				}		
				
				if (in == null){
			    	notExist = 1;
			    	break;
			    }
				
				// Transfer data by token to name
				while (in.hasNext()) {
				    String name = in.next();
				    
				    System.out.println(name);
				    
				    String parts [] = name.split(":");
				    
				    
				    this.name.setText(parts[1]);
					this.totalCost.setText(parts[2]);
					this.monthlyPayment.setText(parts[3]);
					this.time.setText(parts[4]);
				    
				}
				in.close();	//Close file
				this.fileType = i;
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
		notExist = 0;
	}
	
	/**
	 * Clears the information and the
	 * name of the project entered
	 * @param event
	 */
	public void closeButton (ActionEvent event ){
		this.name.setText("");
		this.totalCost.setText("");
		this.monthlyPayment.setText("");
		this.time.setText("");
		this.searchText.setText("");
		
	}
	/**
	 * Deleted the current goal displayed in
	 * the goal section and the data
	 * @param event
	 */
	public void deleteButton (ActionEvent event ){
		
		String path;
		File file;
		
		path = "UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator +
				"Goals" +  File.separator + sGoals[this.fileType] + File.separator + this.searchText.getText();
		
		file = new File(path);
		
		try {
			Files.deleteIfExists(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.name.setText("");
		this.totalCost.setText("");
		this.monthlyPayment.setText("");
		this.time.setText("");
		this.searchText.setText("");
		this.searchText.getText();
			

	}
	
	@FXML
	/**
	 * Error that will be showed if the information 
	 * entered is wrong or does not exist
	 */
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
}
