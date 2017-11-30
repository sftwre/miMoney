/**
 * 
 */
package application.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import application.controller.UserDataParser;

/**
 * @author Jonathan
 *
 */
public class SecureOldUser {
	private User currentUser;
	private UserDataParser dataParser;
	
	public Boolean auth(String user) {
		currentUser = new User(user);
		Boolean auth = false;
		
		dataParser = new UserDataParser(currentUser);
		Path path = Paths.get("UserProfiles" + File.separator + currentUser.getUsername());
		
		if(Files.exists(path)) 
			auth = true;
		
		
		return auth;
	}//END auth()

}
