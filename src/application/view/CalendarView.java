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
	
	public CalendarView() {

	}// END constructor
	
	/**
	 * This method fills the current month tiles days
	 * with the corresponding days for the month.
	 */
	public String fill(YearMonth yearMonth, int col, int row, LocalDate exactDate) {
		int day = -1;
		day = exactDate.getDayOfMonth();
		
		return Integer.toString(day);
	}// END fill()

}