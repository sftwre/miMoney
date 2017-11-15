package application.model;


/**
 * A Date has a month, day, and year.
 * @author Isaac Buitrago
 *
 */
public class Date implements Comparable<Date>
{
	final static private String INVALID_MONTH = "Invalid Month "; // Used to indicate that 0 is an invalid month
	 
	private int month; // month in Date
	
	private int day; // day in Date
	
	private int year; // year in Date

	// Number of days for each month of the year
	public static final int DAYS_PER_MONTH[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
	
	// Months of the year as a String
	public static final String MONTHS_IN_YEAR[] = {INVALID_MONTH, "January", "February","March","April", "May", "June", "July", 
	
			"August", "September", "October", "November", "December"}; 
	
	
	/**
	 * Constructor for the date object in North American mm,dd,yyyy format
	 */
	 
	public Date(int month, int day, int year)
	{
		
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
	 * @return month as an integer
	 */
	public int getMonth() 
	{
		return month;
	}

	/**
	 * 
	 * @param month of the year to edit in the current month
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}

	/**
	 * 
	 * @return day of the month as an int
	 */
	public int getDay() 
	{
		return day;
	}

	/**
	 * 
	 * @param day of the month to edit in the current Date
	 */
	public void setDay(int day) 
	{
		this.day = day;
	}

	/**
	 * 
	 * @return the year as an int
	 */
	public int getYear() 
	{
		return year;
	}

	/**
	 * 
	 * @param year to edit in the current Date
	 */
	public void setYear(int year)
	{
		this.year = year;
	}
	
	/**
	 * Compare two date objects
	 * @param other Date to compare to this Date
	 * @return 0 if the Date objects are identical, 1 if this Date is more recent than other
	 * , and -1 if this Date is older than other Date
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
	 * @return formatted String of the Date in mm/dd/yyyy format 
	 */
	public String toString()
	{
		return(String.format("%d/%d/%d", this.month, this.day, this.year));
	}
}
