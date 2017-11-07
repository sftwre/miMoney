/**
 * 
 * @author Raul Cecena
 *
 */

package model;

import java.util.*;

public class Income {

	private String title;
	private double pay;

	public void userPay(String t, double p) {

	
		this.title = t;
		this.pay = p;

	}// userProfile

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
			this.title = title;
	}

	public double getPay() {
		return this.pay;
	}

	// user incomes stores how much they makes and job title.
	//

}// End public Income
