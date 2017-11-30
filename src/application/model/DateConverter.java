/**
 * 
 */
package application.model;

import java.time.LocalDate;

/**
 * @author Jonathan
 *
 */
public class DateConverter {
	
	/**
	 * convertDate(LocalDate date) takes in a local date and converts it to the
	 * Date type used in all of the expense classes (and other places too).
	 * @param date
	 * 				The LocalDate type given
	 * @return
	 * 				The method returns a Date type of the given date
	 */
		
	public static Date convertDate(LocalDate date) {
		return new Date(date.getMonthValue(), date.getDayOfMonth(), date.getYear());
	}// END convertDate

}
