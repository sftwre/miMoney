/**
 * 
 */
package application.model;

import java.time.Month;

/**
 * @author Jonathan
 *
 */
public class MonthFormatter {
	
	public static String formatMonth(Month mo){
	 return mo.toString().substring(0, 1).toUpperCase()
		+ mo.toString().substring(1).toLowerCase();
	}//END static formatMonth()

}//END MODEL CLASS MonthFormatter
