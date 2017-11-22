package application.controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	
	public void searchButton (ActionEvent event ){
		
		String Goal = "Ford F-150";
		if(Goal.compareTo(this.searchText.getText())==0){
			this.name.setText("Ford F-150");
			this.totalCost.setText("25050.00");
			this.monthlyPayment.setText("521.875");
			this.time.setText("4 Years");
			this.interestRate.setText("0.8%");
		}
		else{
			this.name.setText("");
			this.totalCost.setText("");
			this.monthlyPayment.setText("");
			this.time.setText("");
			this.interestRate.setText("");
			Error();
			this.searchText.setText("");
		}
	}
	public void closeButton (ActionEvent event ){
		this.name.setText("");
		this.totalCost.setText("");
		this.monthlyPayment.setText("");
		this.time.setText("");
		this.interestRate.setText("");
		
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
}
