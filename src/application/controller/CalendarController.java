package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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
import application.model.DatalistModel;

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
		
	private ArrayList<String> sList;
	
	private ArrayList<String> sListCopy;
		
	private YearMonth currentMonth;
	
	private LocalDate date;
	
	private int counter;

	private DatalistModel datalist;
	
	public void initialize() {
		monthTile.setPrefSize(420, 380);
		monthTile.setMinSize(420, 380);
		view = new CalendarView();
		currentMonth = YearMonth.now();
		sList = new ArrayList<String>(42);
		sListCopy = new ArrayList<String>(42);
		int k = 1;
		
				date = LocalDate.of(currentMonth.getYear(), currentMonth.getMonthValue(), 1);
				
				//PaneNode specificDay = new PaneNode();
				//specificDay.setPrefSize(75, 70);
				//specificDay.setDate(LocalDate.now());
				
				sList = view.fill(currentMonth, date);
				sListCopy = sList;
				int j = 7;
				
				for(int i = 0; i<7; i++) {
					monthTile.add(new Label(sList.get(i)), i, 0);
						j = 0;
						k = 0;
						while(k<6) {
							monthTile.add(new Label(sListCopy.get(j+i)), i, k);
							//monthTile.setAlignment(CENTER);
							j+=7;
							k++;
						}//END while k<6
				}// END for i<7
				

				/*PaneNode specificDay = new PaneNode();
				specificDay.setPrefSize(75, 70);
				specificDay.setDate(LocalDate.now());
		        LocalDate calendarDate = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonthValue(), 1);*/
				
				//monthTile.add(new Label("myStr"), i, j);

				//list.add(specificDay);		
	}

	@FXML
	public void addAnExpense(ActionEvent event) 
	{
		//datalist = new DatalistModel(date);//DataListController.initialize(currentMonth, date);
		
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/DatalistView.fxml"));
		
		Scene scene = new Scene(root);
		
		popUp.setScene(scene);
		
		popUp.setTitle("Date of " + currentMonth.getMonth().toString().substring(0, 1).toUpperCase()
						+ currentMonth.getMonth().toString().substring(1).toLowerCase() + " "
						+ date.now().getDayOfMonth() + ", "
						+ date.now().getYear());
		
		popUp.show();
		
		} catch(IOException e){
			
			System.out.printf("The resource 'view/resources/DatalistModel.fxml' could not be located");
		}

	}
		/*Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/DatalistModel.fxml"));
		Scene scene = new Scene(root);
		popUp.setScene(scene);
		popUp.show();
		} catch(IOException e){
			System.out.printf("\nThe resource 'view/resources/DatalistModel.fxml' could not be located\n");
		}// END try/catch load FXML
	}*/
	
	/**
	 * Sample code:
	 * 
	 * FinancialDataParser financialData =  new FinancialDataParser(new User("testUser77"));
	 * 
	 * Date  date = new Date(11,26,2017);
	 * 
	 * ArrayList<Expense> expenses = financialData.readExpenses(date, FinanceType.FEXPENSE);
	 */
	
}