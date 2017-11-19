package application.model;

import java.util.ArrayList;

import application.model.Expense.Expense;
import application.model.Goals.Goals;

/**
 * 
 * Every User has a username, password, and phone number
 * 
 * @author Isaac Buitrago
 *
 */

public class User {
	
	
	private String username;		//The username of the user
	
	private String password;		//The password of the user
	
	private String salt;

	private String phone;			//The phone number of the user

	private boolean passAuthenticated;	//Flag to determine if the user is logged in
	
	private boolean userAuthenticated;
	
	/**
	 * Constructor
	 * @param username of the User 
	 */
	
	public User(String username) 
	{
		this.username = username;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	/**
	 * Use regex to standardize the storing of phone numbers
	 * @param phone
	 */
	public void setPhone(String phone) {
		
		//TODO
		this.phone = phone;
	}

	public boolean isPassAuthenticated() {
		return passAuthenticated;
	}

	public void setPassAuthenticated(boolean passAuthenticated) {
		this.passAuthenticated = passAuthenticated;
	}

	public boolean isUserAuthenticated() {
		return userAuthenticated;
	}

	public void setUserAuthenticated(boolean userAuthenticated) {
		this.userAuthenticated = userAuthenticated;
	}

}