package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

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
    
	public CalendarController() {
		monthTile.setPrefSize(640, 380);
		
		for(int i = 0; i<6; i++)
		{
			for(int j = 0; j<7; j++) {
				PaneNode specificDay = new PaneNode();
				specificDay.setPrefSize(50, 40);
				monthTile.add(specificDay, j, i);
			}
		}// END forLoop adding specificDay's to monthTile
		
	}// END constructor

}
