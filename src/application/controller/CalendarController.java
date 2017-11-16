package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.Main;
import application.view.CalendarView;

/**
 * @author Jonathan Remote
 *
 */

public class CalendarController {

	@FXML
	private Button addExpenseButton;
	
	@FXML
	private GridPane monthTile;
	
	private CalendarView view;
	
	private ArrayList<PaneNode> list;

	private YearMonth currentYearMonth;

	
	public void initialize() {
		monthTile.setPrefSize(420, 380);
		monthTile.setMinSize(420, 380);
		view = new CalendarView();
		list = new ArrayList<PaneNode>(42);
		
		for(int i = 0; i<7; i++)
		{
			//TODO: Set day of the week here
			for(int j = 0; j<6; j++) {
				/*PaneNode specificDay = new PaneNode();
				specificDay.setPrefSize(75, 70);
				specificDay.setDate(LocalDate.now());
		        LocalDate calendarDate = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonthValue(), 1);*/
				monthTile.add(new Label("myStr"), i, j);

				//list.add(specificDay);
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
