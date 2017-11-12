package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.Main;

/**
 * @author Jonathan Remote
 *
 */

public class CalendarController {

	@FXML
	private Button addExpenseButton;
	
	@FXML
	private GridPane monthTile;
	
	//private ArrayList<> displayedDays = new ArrayList<>();
	
	public void initialize() {
monthTile.setPrefSize(640, 380);
		
		for(int i = 0; i<6; i++)
		{
			for(int j = 0; j<7; j++) {
				PaneNode specificDay = new PaneNode();
				specificDay.setPrefSize(50, 40);
				monthTile.add(specificDay, j, i);
			}
		}// END forLoop adding specificDay's to monthTile
		
	}

	@FXML
	public void addAnExpense(ActionEvent event) {
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/DatalistView.fxml"));
		Scene scene = new Scene(root);
		popUp.setScene(scene);
		popUp.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/DatalistView.fxml' could not be located");
		}// END try/catch load FXML
	}
	
}
