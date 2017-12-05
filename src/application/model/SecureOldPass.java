package application.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import application.controller.UserDataParser;

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
    
	private UserDataParser dataParser;
	
    /**
     * 
     * @param user
     */
	public Boolean secure(String user, CharSequence pass)
	{ 
		currentUser = new User(user);
		dataParser = new UserDataParser(currentUser);
		currentUser = dataParser.readUserData();
		
		//System.out.printf("\nSalt is %s\n", currentUser.getSalt());
		
		salt = currentUser.getSalt();
		//System.out.printf("Password taken in is %s\n", pass.toString());
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
		Boolean auth = true;
		//System.out.printf("\nHash given out %s", newHash);
		//System.out.printf("\nOld hash       %s\n", oldHash);
		//int cmp = 100;
		//cmp = newHash.compareTo(oldHash);
		auth = newHash.equals(oldHash);
		if(auth == true) {
			auth = true;
			//System.out.println("password authenticated in OldPass");
			return auth;
		}
		System.out.printf("%s", auth.toString());

		System.out.println("password not auth in OldPass");
		return auth;
	}//END auth()
	
}// END APPLICATION CLASS SecureOldPass
