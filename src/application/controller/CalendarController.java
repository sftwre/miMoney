package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
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
	
	private Writer bw;		
	
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
	}//END initialize()
	
	private void filePathExists() {
		
		user = Main.session.currentUser;
		//dataParser = new UserDataParser(user);
		Path path = Paths.get("UserProfiles" + File.separator + user.getUsername() + File.separator + "AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()));
		
		if(Files.exists(path))
			return;
		
		String fileName = "FixedExpenses.txt";
		//String incomeFile = "Income.txt";
		File incomeFile = new File("UserProfiles" + File.separator+user.getUsername()+File.separator +"Income.txt");
		File userFile = new File("UserProfiles" + File.separator+user.getUsername()+File.separator +user.getUsername()+".txt");
		File dir = new File("UserProfiles" + File.separator+user.getUsername()+File.separator +"AnnualExpenses" + File.separator + currentMonth.getYear());
		File dateTrack = new File("UserProfiles" + File.separator +user.getUsername()+File.separator +"AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()));

		if(dateTrack.exists()) {
			System.out.println("directory already exists");
		}
		else {
			boolean success = dateTrack.mkdirs();
			if (success){
		      // creating the directory succeeded
		      System.out.println("directory was created successfully");
		      File expTr = new File(dateTrack+File.separator +"ExpenseTracker.txt");
		      
		      try {
		    	  expTr.createNewFile();
		    	  System.out.println("Expense Tracker file is created!");
		      }catch(IOException e) {
		    	  System.out.println("Tracker failed to print");
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
		      // creating the directory failed
		      System.out.println("failed trying to create the directory");
		    }
		}
	}//END filePathExists()

    private void addToAccordion(Expense e) {
		Path path = Paths.get("UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator + "AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()) + File.separator + "ExpenseTracker.txt");
		File et = new File(path.toString());
		String line = "";
		
		//Series of try catch used with FileIO		
		try {
			br = new BufferedReader(new FileReader(path.toFile()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Boolean auth = false;
		try {
			while((line = br.readLine()) != null) {
				System.out.printf("\n" + line + "\n");
				if(line.contains(e.getDate().toString())) {
					System.out.printf("YES\n");
					//bw.append("," + e.toString());
					auth = true;
				}//END if date append
			}//END while read next line
			
			if(auth == false) {
				System.out.printf("\nNew Line!!!\n");
				//bw.append("\n");
				//bw.append(e.toString());
			}//if date not find
			
			} catch (IOException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		}//END try catch read line check line
		
		//END series of try/catch
		
		//TODO: sort through the dates line by line, store the last known date, current date, and next date
		//TODO: if currentDate == givenDate then append the expense to end of line
		//TODO: if currentDate.day() > givenDate.day()
		//lastKnownDate = givenDate; write new line with expense
		return;
	}//END addToFile()
	
	private void addTitlePanes(int n) {
//*		
		//tpList.add(new TitledPane("Monday", new Button("yes")));
		//tpList.add(new TitledPane("Tuesday", new Button("yes")));
		//tpList.add(new TitledPane("Wednesday", new Button("yes")));
		//tpList.add(new TitledPane("Thursday", new Button("yes")));
		//tpList.add(new TitledPane("Friday", new Button("yes")));
		//tpList.add(new TitledPane("Saturday", new Button("yes"))); //*/
		
		for(int j = n; j>0; j--) {
			weekAccordion.getPanes().add(tpList.get(j));
			tpList.get(j).setPrefHeight(50);
		}
	}//END addTitlePanes()

	//TODO: LOOP
	//TODO: if(I can find this days date within monthly expenses
	//TODO: create a Gridpane and add it to gpList, create a new TitledPane add the Gridpane to it, add the TitledPane to tpList
	//TODO: add a new row to gridpane with itemLabel and doubleTotal until no more expenses for this date exist
	//TODO: else (I can not find this days date within monthly expenses)
	//TODO: create new TitledPane for this day and a label that says "no expenses found"
	//TODO: LOOP until all days in this week are done
	
	private int decideDays() {
		int days = 0;
		int i = 0;
		int j = 0;
		duplicateDate = LocalDate.now();
		d = DateConverter.convertDate(duplicateDate);

		monthExpenses = input.readExpenses(d, FinanceType.REXPENSE);
		monthExpenses.addAll(input.readExpenses(d, FinanceType.FEXPENSE));
		i = 0;
		
		for(j = 0; j<=duplicateDate.getDayOfWeek().getValue(); j++) {
			gpList.add(new GridPane());
			System.out.printf("New gridpane created + %d\n", duplicateDate.getDayOfWeek().getValue());
		}
				
		Boolean auth = false;
		String str = "";
		//*
		while (!duplicateDate.getDayOfWeek().toString().equals("SATURDAY")) {
			for(Expense e : monthExpenses)
			{
				if(e.getDate().toString().contains(d.toString())) {
					auth = true;
					if(e.getItem().length() > 18) {
							gpList.get(i).addRow(counter,
									new Label(e.getItem().substring(0, 18) + "\t\t" + DecimalFormat.getCurrencyInstance().format(e.getAmmount())));
							str = e.getItem().substring(18);
							gpList.get(i).addRow(counter + 1, new Label(str));
					}else {
					gpList.get(i).addRow(counter,
							new Label(e.getItem() + "\t\t" + DecimalFormat.getCurrencyInstance().format(e.getAmmount())));
							//new Label(DecimalFormat.getCurrencyInstance().format(e.getAmmount())));
					}//END inner if/else str length
					
					
					System.out.printf("%s + ", d.toString());
					System.out.printf("%s\n", e.toString());
					str = "";
					counter++;
					
				}//END if current expense matches date
			}//END for each expense
			
			if(auth == false) {
				gpList.get(i).addRow(counter, new Label("No expenses on this day!"));
				tpList.add(new TitledPane(DateFormatter.formatDay(duplicateDate.getDayOfWeek()), new Label("No expenses on this day!")));
			}else {
				auth = false;
				tpList.add(new TitledPane(DateFormatter.formatDay(duplicateDate.getDayOfWeek()), gpList.get(i)));
			}
			
			tpList.add(new TitledPane(DateFormatter.formatDay(duplicateDate.getDayOfWeek()), gpList.get(i)));
			counter = 0;
			i++;
            duplicateDate = duplicateDate.minusDays(1);
            d.setDay(d.getDay() - 1);
			days++;
		}//END while rolling back to SUNDAY
//*/		
		return days;	
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