/**
 * 
 */
package application.model;

import java.time.DayOfWeek;
import java.time.Month;

/**
 * @author Jonathan
 *
 */
public class DateFormatter {
	
	
	/**
	 * formatMonth(Month mo) takes in a Month type. The month type is returned from the
	 * YearMonth method getMonth(). Use this method to format the given month and return
	 * it formatted properly.
	 * 
	 * e.g.
	 * YearMonth currentMonth = YearMonth.now() //this gets the current month and year
	 * System.out.printf("%s", MonthFormatter.formatMonth(currentMonth.getMonth()));
	 * Output: November
	 * 
	 * If you simply print currentMonth.getMonth().toString() you will get the current
	 * month printed in all CAPS.
	 * 
	 * @param mo
	 * 			The month type given
	 * @return
	 * 		Returns a formatted month with the first letter capitalized and the rest lowercase
	 * 				e.g. November vs NOVEMBER (NOVEMBER is printed when using a Month types toString()
	 */
	public static String formatMonth(Month mo){
	 return mo.toString().substring(0, 1).toUpperCase()
		+ mo.toString().substring(1).toLowerCase();
	}//END static formatMonth()
	
	public static String formatDay(DayOfWeek d){
		 return d.toString().substring(0, 1).toUpperCase()
			+ d.toString().substring(1).toLowerCase();
		}//END static formatMonth()

}//END MODEL CLASS MonthFormatter
