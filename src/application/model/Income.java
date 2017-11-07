package application.model;

public class Income {
	
	private String jobTitle;
	
	private double wage;
	
	
	public Income(String jobTitle, double wage)
	{
		this.jobTitle = jobTitle;
		this.wage = wage;
	}
	
	public void setjobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}
	
	public String getjobTitle()
	{
		return (this.jobTitle);
	}
	
	public void setWage(double wage)
	{
		this.wage = wage;
	}
	
	public double getWage()
	{
		return(this.wage);
	}

}
