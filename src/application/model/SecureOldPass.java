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
	public Boolean secure(String user, CharSequence pass)
	{
		currentUser = new User(user);
		salt = currentUser.getSalt();
		hash = super.hashFun(pass.toString(), salt);
		
		oldHash = currentUser.getPassword();
		return auth(hash, oldHash);
	}//END secure()
	
	/**
	 * 
	 * @param newHash
	 * @param oldHash
	 */
	public Boolean auth(String newHash, String oldHash)
	{
		Boolean auth = false;
		
		if(newHash.compareTo(oldHash) == 1)
			auth = true;

		return auth;
	}//END auth()
	
}// END APPLICATION CLASS SecureOldPass
