package model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

/**
 * 
 * @author Isaac Buitrago
 *
 */
public class Date implements Comparable<Date>
{
	final static private String INVALID_MONTH = "Invalid Month "; // Error String
	
	final static private int INVALID_DAY  = -1; // Error integer 
	 
	private int month; // month in Date
	
	private int day; // day in Date
	
	private int year; // year in Date
	
	private static final int DAYS_PER_MONTH[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // Number of days for each month of the year
	
	private static final String MONTHS_IN_YEAR[] = {INVALID_MONTH, "January", "February","March","April", "May", "June", "July", 
													"August", "September", "October", "November", "December"}; // Months of the year
	/**
	 * Constructor for the date object. Parse the String for each field and validate input 
	 */
	 
	public Date(String date)
	{
		int month = parseMonth(date);
		int day = parseDay(date);
		int year = parseYear(date);
		
		//perform error checking and throw an error if an invalid Date is encountered
		
		//check if the month is Invalid
		if(month == 0)
		{
			throw new IllegalArgumentException("Invalid Date");
		}
		
		//check that the day is within range for the appropriate month
		if(day <= 0 || (day > DAYS_PER_MONTH[month] && !( day == 29 && month ==  2)))
		{
			throw new IllegalArgumentException("Invalid Date");
		}
		
		//if the date is February 29, ensure that it is a leap year
		if((day == 29 && month == 2) && 
				!(day % 400 == 0 || year % 4 == 0 && year % 100 != 0 ))
		{
			throw new IllegalArgumentException("Invalid Date");
		}
		
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	/**
	 * 
	 * @param month string to parse for the month
	 * @return month of the year in integer 
	 */
	private int parseMonth(String month)
	{
		List<String> list = Arrays.asList(MONTHS_IN_YEAR);
		
		for(String M : MONTHS_IN_YEAR)
		{
				
			if(M.regionMatches(true, 0, month, 0, 3))
			{
				return list.indexOf(M);
			}
		}
		
		return(list.indexOf(INVALID_MONTH));		
	}
	
	/**
	 * 
	 * @param day string to parse for the day
	 * @return day of the month in integer
	 */
	private int parseDay(String day)
	{
		//extract the day from the String
		Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(day);
        
        if(m.find())
        {
        	return(Integer.parseInt(m.group()));
        }
        
        return (INVALID_DAY);
	}
	
	/**
	 * 
	 * @param year day string to parse for the year
	 * @return the year
	 */
	private int parseYear(String year)
	{
		String tokens[] = year.split(",");
	
		return( Integer.parseInt(tokens[1].trim()) );
	}

	/**
	 * @param other Date object to compare against invoking Date object
	 * @return Returns -1 if this Date is before the other Date, 
	 * 0 if they are equal, and +1 if this Date is after the other Date. 
	 */
	@Override
	public int compareTo(Date other) 
	{
		
		/*Check for the same Date first and then test
		 * smaller logical cases
		 */
		if(this.month == other.month && this.day == other.day
				&& this.year == other.year)
		{
			return(0);
		}
		
		else if(this.year > other.year)
		{
			return(1);
		}
		
		else if(this.year < other.year)
		{
			return(-1);
		}
		
		else if(this.month > other.month)
		{
			return(1);
		}
		
		else if(this.month < other.month)
		{
			return(-1);
		}
		
		else if(this.day > other.day)
		{
			return(1);
		}
		
		else
			return(-1);
	}

	
	/**
	 * @return formatted String of the object state
	 */
	public String toString()
	{
		return(String.format("Date: %s %d, %d", MONTHS_IN_YEAR[this.month], this.day, this.year));
	}
}
