package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.Main;
import application.view.CalendarView;
import application.model.DatalistModel;
import application.model.Date;
import application.model.DateConverter;
import application.model.FinanceType;
import application.model.User;
import application.model.DateFormatter;
import application.model.Expense.Expense;
import application.model.DateFormatter;

/**
 * @author Jonathan Remote
 *
 */

public class CalendarController {

	@FXML
	private Button addExpenseButton;
	
	@FXML
	private GridPane monthTile;
	
	@FXML
	private Label monthOfLabel;
	
	@FXML
	Accordion weekAccordion;
	
	@FXML
	TitledPane sundayPane;
		
	private CalendarView view;
	
	private ArrayList<GridPane> gpList;
	
	private ArrayList<TitledPane> tpList;
	
	private ArrayList<Expense> monthExpenses; 
		
	private ArrayList<String> sList;
	
	private ArrayList<String> sListCopy;
		
	private YearMonth currentMonth;
	
	private LocalDate date;
	
	private LocalDate duplicateDate;
	
	private Date d;
	
	private User user;
	
	private int counter;

	private DatalistModel datalist;
	
	static FinancialDataParser input;
	private UserDataParser dataParser;
	
	private BufferedReader br;
	
	private FileWriter fw;
	
	public void initialize() {
		monthTile.setPrefSize(420, 380);
		monthTile.setMinSize(420, 380);
		view = new CalendarView();
		currentMonth = YearMonth.now();

		filePathExists();		
		
		input = new FinancialDataParser(Main.session.currentUser);
		gpList = new ArrayList<GridPane>();
		tpList = new ArrayList<TitledPane>();
		sList = new ArrayList<String>(42);
		sListCopy = new ArrayList<String>(42);
		monthOfLabel.setText("Month of " + DateFormatter.formatMonth(currentMonth.getMonth()));
		
		addTitlePanes(decideDays());
		
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
	}//END initialize()
	
	private void filePathExists() {
		
		user = Main.session.currentUser;
		//dataParser = new UserDataParser(user);
		Path path = Paths.get("UserProfiles" + File.separator + user.getUsername() + File.separator + "AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()));
		
		if(Files.exists(path))
			return;
		
		String fileName = "FixedExpenses";
		File incomeFile = new File("UserProfiles" + File.separator+user.getUsername()+File.separator +"Income");
		File userFile = new File("UserProfiles" + File.separator+user.getUsername()+File.separator +user.getUsername());
		File dir = new File("UserProfiles" + File.separator+user.getUsername()+File.separator +"AnnualExpenses" + File.separator + currentMonth.getYear());
		File dateTrack = new File("UserProfiles" + File.separator +user.getUsername()+File.separator +"AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()));

		if(dateTrack.exists()) {
			System.out.println("directory already exists");
		}
		else {
			boolean success = dateTrack.mkdirs();
			if (success){
		      // creating the directory succeeded
		      File expTr = new File(dateTrack+File.separator +"ExpenseTracker");
		      
		      try {
		    	  expTr.createNewFile();
		    	  System.out.println("Expense Tracker file is created!");
		      }catch(IOException e) {
		    	  System.out.println("Tracker failed to print");
		      }
		      
				try {
					fw = new FileWriter(expTr);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			      try(BufferedWriter bw = new BufferedWriter(fw)) {

						bw.write("Gas:0.000000:1/1/2017:This is a temporary fix");
					} catch (IOException e) {
						System.out.printf("\nException in try catch DatalistController addtoFile create new file writer\n");
					}
		      
		    }//END if success
			else{
		      // creating the directory failed
		      System.out.println("failed trying to create the directory");
		    }
		}//END if dateTrack.exists()
		
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
		      System.out.println("failed trying to create the directory");
		    }
		}
	}//END filePathExists()
	
	private void addTitlePanes(int n) {
		for(int j = n; j>=0; j--) 
			weekAccordion.getPanes().add(tpList.get(j));
	}//END addTitlePanes()


	private int decideDays() {
		int i = 0;
		int j = 0;
		duplicateDate = LocalDate.now();
		d = DateConverter.convertDate(duplicateDate);

		monthExpenses = input.readExpenses(d, FinanceType.REXPENSE);
		
		for(j = 0; j<=duplicateDate.getDayOfWeek().getValue(); j++) {
			gpList.add(new GridPane());
		}
				
		Boolean auth = false;

		while (!duplicateDate.getDayOfWeek().toString().equals("SATURDAY")) {
			for(Expense e : monthExpenses)
			{

				if(e.getDate().toString().contains(d.toString())) {
					auth = true;
					if(e.getItem().length() > 18) {
							gpList.get(i).addRow(counter,
									new Label(e.getItem().substring(0, 18) + "\t\t" + DecimalFormat.getCurrencyInstance().format(e.getAmmount())));
							gpList.get(i).addRow(counter + 1, new Label(e.getItem().substring(18)));
							
					}else {
					gpList.get(i).addRow(counter, new Label(e.getItem() + "\t\t" + DecimalFormat.getCurrencyInstance().format(e.getAmmount())));
					}//END inner if/else str length					
				}//END if current expense matches date
				counter++;
			}//END for each expense
			
			if(auth == false) {
				gpList.get(i).addRow(counter, new Label("No expenses on this day!"));
				tpList.add(new TitledPane(DateFormatter.formatDay(duplicateDate.getDayOfWeek()), new Label("No expenses on this day!")));
			}else {
				auth = false;
				tpList.add(new TitledPane(DateFormatter.formatDay(duplicateDate.getDayOfWeek()), gpList.get(i)));
			}
			counter = 0;
			i++;
            duplicateDate = duplicateDate.minusDays(1);
            d.setDay(d.getDay() - 1);
		}//END while rolling back to SUNDAY
		return i-1;	
	}//END decideDays()

	@FXML
	public void addAnExpense(ActionEvent event) 
	{
		Stage popUp =  new Stage();
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		
		popUp.initOwner(Main.stage);
		
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/DatalistView.fxml"));
		
		Scene scene = new Scene(root);
		
		popUp.setScene(scene);
		
		popUp.setTitle("Date of " + DateFormatter.formatMonth(currentMonth.getMonth()) + " "
						+ date.now().getDayOfMonth() + ", "
						+ date.now().getYear());
		
		popUp.show();
		
		} catch(IOException e){
			
			System.out.printf("The resource 'view/resources/DatalistView.fxml' could not be located");
		}

	}//END addAnExpense()
	
}