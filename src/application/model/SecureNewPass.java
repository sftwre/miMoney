/**
 * 
 */
package application.model;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * @author Jonathan
 * CLASS PURPOSE: This class is used to create a new
 * salt+hash combo for a user.
 * 
 * TODO: Create SecureNewPass object and link with Login.fxml
 * then let this class do most of the work. Controller must
 * send user and password field input to this class. Use only
 * the secure() method (acts as similarly to the initialize() 
 * seen in other classes within miMoney)
 */
public class SecureNewPass extends SecurePass{
    private final Scanner input = new Scanner(System.in);
    
    private String salt;
    
    private String hash;
    
    private User currentUser;
        
    public void secure(String username, CharSequence pass, String phoneNumber)
    { 
    	//System.out.println("In");
    	currentUser = new User(username);
    	//System.out.printf("Username: %s", currentUser.getUsername());
    	salt = createSalt();
    	//System.out.printf("Salt: %s", salt);
    	hash = super.hashFun(pass.toString(), salt);
    	//System.out.printf("Hash: %s", hash);
      
    	currentUser.setSalt(salt);
    	currentUser.setPassword(hash);
    	currentUser.setPhone(phoneNumber);
    	
    	//TODO: write toString() to username.txt
    }// END secure()
    
    public String createSalt()
    {
      SecureRandom rand = new SecureRandom();
      StringBuilder build = new StringBuilder();
      
      byte salt[] = new byte[32];
      rand.nextBytes(salt);
      
      for(int i = 0; i<salt.length; i++)
        build.append(String.format("%02x", salt[i]));
      
      return build.toString();
    }// END createSalt()

    public String toString() {
    	return (String.format("%s:%s:%s:%s", currentUser.getUsername(),currentUser.getPassword(), 
				currentUser.getSalt(), currentUser.getPhone()));
    }//END toString()

}//END APPLICATION CLASS SecureNewPass
