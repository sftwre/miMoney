package application.model;

/**
 * Session controls the global state of the application by 
 * storing the current User of the application.
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
	 * Start the session for the user
	 */
	public void startSession(User user)
	{
		this.currentUser = user;
		this.currentUser.setPassAuthenticated(true);
	}
	
	/**
	 * End the user session
	 */
	
	public void endSession()
	{
		this.currentUser = null;
	}
}
