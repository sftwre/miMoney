/**
 * 
 */
package application.view;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.controller.PaneNode;
import javafx.scene.text.Text;

/**
 * @author Jonathan
 *
 */
public class CalendarView {
	
	private ArrayList<String> list = new ArrayList<String>(42);
	
	/**
	 * This method fills the current month tiles days
	 * with the corresponding days for the month.
	 */
	public ArrayList<String> fill(YearMonth yearMonth, LocalDate exactDate) {
		while (!exactDate.getDayOfWeek().toString().equals("SUNDAY"))
            exactDate = exactDate.minusDays(1);
		/*
		 * While loop above will loop until we are back at the last Sunday
		 * of last month. Once there we will stop, and exact date will be
		 * the exact date of the last Sunday of last month. This is similar
		 * to the calendar format in Windows 10 (taskbar calendar)
		 */
        	
		for(int i = 0; i<42; i++) {
			list.add(Integer.toString(exactDate.getDayOfMonth()));
			//System.out.printf("%d ", exactDate.getDayOfMonth());

			exactDate = exactDate.plusDays(1);
		}
		
		return list;
	}// END fill()

}