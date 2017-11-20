package application.model;

import java.util.HashMap;

/**
 * Session controls the global state of the application and 
 * stores data on the User of the application.
 * @author Isaac Buitrago
 *
 */
public class Session {
	
	public User currentUser;
	
	/**
	 * New Session starts with an unauthenticated User
	 * that must be redirected to Login.fxml
	 */
	public Session()
	{
		//default to an anonymous User
		this.currentUser = new User("");
		this.currentUser.setUserAuthenticated(false);
		this.currentUser.setPassAuthenticated(false);
	}
	
	/**
	 * Load the User data into Collections User
	 */
	public void startSession()
	{
		
		
	}
	
	/**
	 * Write User data from the collections to UserProfiles
	 */
	
	public void endSession()
	{
		
	}
}
