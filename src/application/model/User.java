package application.model;

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
	
	private String phone;			//The phone number of the user
	
	private boolean authenticated;	//Flag to determine if the user is logged in

	
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
	

}
