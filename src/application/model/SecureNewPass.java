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
    
    public void secure(String user, CharSequence pass)
    { 
    	currentUser = new User(user);
    	salt = createSalt();
    	hash = super.hashFun(input.nextLine(), salt);
    	//TODO: find another way to take password input, above line compiles but will not work in the end

      
    	currentUser.setSalt(salt);
    	currentUser.setPassword(hash);
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


}//END APPLICATION CLASS SecureNewPass
