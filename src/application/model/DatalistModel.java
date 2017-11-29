package application.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.model.ExpenseTracker;
import application.model.Expense.*;
import application.model.Expense.Gas;
//import application.model.Goals.*;

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
	
	/*
	 * constructor for generic DataList use case
	 */

	public DatalistModel(LocalDate date) {
		// TODO Auto-generated constructor stub
	}
	
	public void add(String category, double total, LocalDate date, String item) {
		et.addExpense(decideType(category, total, date, item));
	}	
	
	public Expense decideType(String category, double t, LocalDate date, String i) {
		switch(category) {
		case "Apperal":
			d = convertDate(date);
			e = new Apperal(t, d, i);
			break;
		case"Auto Maintenance":
			d = convertDate(date);
			e = new AutoMaintenance(t, d, i);
			break;
		case "Home Maintenance":
			d = convertDate(date);
			e = new HomeMaintenance(t, d, i);
			break;
		case "Medical":
			d = convertDate(date);
			e = new Medical(t, d, i);
			break;
		case "Education":
			d = convertDate(date);
			e = new Education(t, d, i);
			break;
		case "Entertainment":
			d = convertDate(date);
			e = new Entertainment(t, d, i);
			break;
		case "Food":
			d = convertDate(date);
			e = new Food(t, d, i);
			break;
		case "Gas":
			d = convertDate(date);
			e = new Gas(t, d, i);
			break;
		case "Luxury":
			d = convertDate(date);
			e = new Luxury(t, d, i);
			break;
		case "Personal Care":
			d = convertDate(date);
			e = new PersonalCare(t, d, i);
			break;
		case "Public Transportation":
			d = convertDate(date);
			e = new PublicTransportation(t, d, i);
			break;
		case "Subscriptions":
			d = convertDate(date);
			e = new Subscriptions(t, d, i);
			break;
		case "Miscellaneous":
			d = convertDate(date);
			e = new Miscellaneous(t, d, i);
			break;
		}//END switch(category
		
		return e;
	}//END 

	private Date convertDate(LocalDate date) {
		//TODO: get all values from date and put into d
		d = new Date(date.getMonthValue(), date.getDayOfMonth(), date.getYear());
		return d;
	}// END convertDate

}//END MODEL CLASS DatalistModel
