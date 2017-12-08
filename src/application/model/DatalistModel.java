package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.Main;
import application.controller.FinancialDataParser;
import application.model.ExpenseTracker;
//import application.model.Goals.*;
import application.model.Expense.*;

/**
 * A DataList will handle all of the data
 * for a DataList which can be used for expenses,
 * FixedExpenses, Goals, Budgets(?)
 * @author Jonathan Remote
 *
 */
public class DatalistModel {
	
	private LocalDate date;
	
	private ExpenseTracker et;
	
	private Expense e;

	private Date d;
	
    private YearMonth currentMonth;
	
	static FinancialDataParser input;
	
	private BufferedReader br;
	
	private FileWriter fw;
	
	private Writer bw;		
	/*
	 * constructor for generic DataList use case
	 */	
	
	public void add(String category, double total, LocalDate date, String item) {		
		et.addExpense(decideType(category, total, date, item));
	}//END add()
	
	public DatalistModel() {
		et = new ExpenseTracker();
		currentMonth = YearMonth.now();
		input = new FinancialDataParser(Main.session.currentUser);
		date = LocalDate.now();
	}//END constructor

	public Expense decideType(String category, double t, LocalDate date, String i) {
		switch(category) {
		case "Apparel":
			d = DateConverter.convertDate(date);
			e = new Apparel(t, d, i);
			addToFile(e);
			break;
		case"Auto Maintenance":
			d = DateConverter.convertDate(date);
			e = new AutoMaintenance(t, d, i);
			addToFile(e);
			break;
		case "Home Maintenance":
			d = DateConverter.convertDate(date);
			e = new HomeMaintenance(t, d, i);
			addToFile(e);
			break;
		case "Medical":
			d = DateConverter.convertDate(date);
			e = new Medical(t, d, i);
			addToFile(e);
			break;
		case "Education":
			d = DateConverter.convertDate(date);
			e = new Education(t, d, i);
			addToFile(e);
			break;
		case "Entertainment":
			d = DateConverter.convertDate(date);
			e = new Entertainment(t, d, i);
			addToFile(e);
			break;
		case "Food":
			d = DateConverter.convertDate(date);
			e = new Food(t, d, i);
			addToFile(e);
			break;
		case "Gas":
			d = DateConverter.convertDate(date);
			e = new Gas(t, d, i);
			addToFile(e);
			break;
		case "Luxury":
			d = DateConverter.convertDate(date);
			e = new Luxury(t, d, i);
			addToFile(e);
			break;
		case "Personal Care":
			d = DateConverter.convertDate(date);
			e = new PersonalCare(t, d, i);
			addToFile(e);
			break;
		case "Public Transportation":
			d = DateConverter.convertDate(date);
			e = new PublicTransportation(t, d, i);
			addToFile(e);
			break;
		case "Subscriptions":
			d = DateConverter.convertDate(date);
			e = new Subscriptions(t, d, i);
			addToFile(e);
			break;
		case "Miscellaneous":
			d = DateConverter.convertDate(date);
			e = new Miscellaneous(t, d, i);
			addToFile(e);
			break;
		}//END switch(category
		
		return e;
	}//END decideType()
	
    private void addToFile(Expense e) {
		Path path = Paths.get("UserProfiles" + File.separator + Main.session.currentUser.getUsername() + File.separator + "AnnualExpenses" + File.separator + currentMonth.getYear() + File.separator + DateFormatter.formatMonth(currentMonth.getMonth()) + File.separator + "ExpenseTracker");
		File et = new File(path.toString());
		String line = "";
		
		//Series of try catch used with FileIO
		try {
			fw = new FileWriter(et, true);
			bw = new BufferedWriter(fw);
		} catch (IOException err) {
			System.out.printf("\nException in try catch DatalistController addtoFile create new file writer\n");
		}
		
		try {
			br = new BufferedReader(new FileReader(path.toFile()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Boolean auth = false;
		try {
			while((line = br.readLine()) != null) {
				if(line.contains(e.getDate().toString())) {
					bw.append("," + e.toString());
					auth = true;
				}//END if date append
			}//END while read next line
			
			if(auth == false) {
				bw.append("\n");
				bw.append(e.toString());
			}//if date not find
			
			} catch (IOException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		}//END try catch read line check line
		try {
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//END series of try/catch
		
		//TODO: sort through the dates line by line, store the last known date, current date, and next date
		//TODO: if currentDate == givenDate then append the expense to end of line
		//TODO: if currentDate.day() > givenDate.day()
		//lastKnownDate = givenDate; write new line with expense
		return;
	}//END addToFile()

}//END MODEL CLASS DatalistModel
