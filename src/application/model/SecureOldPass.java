package application.model;

import java.util.Scanner;

/**
 * @author Jonathan
 * CLASS PURPOSE: This class is used to authenticate
 * an existing user. 
 * TODO: Create SecureOldPass object and link with Login.fxml
 * then let this class do most of the work. Controller must
 * send user and password field input to this class. Use only
 * the secure() method (acts as similarly to the initialize() 
 * seen in other classes within miMoney)
 *
 */

public class SecureOldPass extends SecurePass{
	
    private final Scanner input = new Scanner(System.in);
    
    private String salt;
    
    private String hash;
    
    private String oldHash;
    
    private Boolean auth;
    
    private User currentUser;
	
    /**
     * 
     * @param user
     */
	public void secure(String user)
	{
		currentUser = new User(user);
		salt = currentUser.getSalt();
		hash = super.hashFun(input.nextLine(), salt);
		//TODO: find another way to take password input, above line compiles but will not work in the end
		
		oldHash = currentUser.getPassword();
		auth(hash, oldHash);
	}//END secure()
	
	/**
	 * 
	 * @param newHash
	 * @param oldHash
	 */
	public void auth(String newHash, String oldHash)
	{
		Boolean auth = false;
		
		if(newHash.compareTo(oldHash) == 1)
			auth = true;

		currentUser.setAuthenticated(auth);
	}//END auth()
	
}// END APPLICATION CLASS SecureOldPass
