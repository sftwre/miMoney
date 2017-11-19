package application.controller;
import java.io.BufferedReader;
import java.io.File;
import application.model.FinanceType;
import application.model.User;


/**
 * 
 * A FileParser contains the common objects and methods
 * necessary to parse data from any file within the UserProfiles directory.
 * @author Isaac Buitrago
 *
 */

public class FileParser {
	
	protected User user;							//The user who's data will be retrieved
													
	protected String userProfile; 					//Location of the User's profile in UserProfiles directory
	
	protected BufferedReader bufferInput;			//Buffer for faster reading of data
	
	protected FinanceType recordType;				//Type of Financial record to be retrieved
	
public FileParser(User user) 
{		
		this.user = user;
		setUserProfile(this.user);
}

/**
 * Set the record type to read from the User's profile
 * @param recordType
 */

public void setRecordType(FinanceType recordType) {
	this.recordType = recordType;
}

/**
 * Get the type of records being queried from the User's profile
 * @return
 */
public FinanceType getRecordType() {
	return recordType;
}


public User getUser() {
	return user;
}

/**
 * Set the user's profile
 * @param user data to work with
 */
public void setUser(User user) {
	this.user = user;
}

/**
 * 
 * @return The user whose data is being worked with
 */
public String getUserProfile() {
	return userProfile;
}

/**
 * 
 * @param userProfile whose data is being worked with
 */
public void setUserProfile(User user) 
{
	this.userProfile = "UserProfiles" + File.separator + user.getUsername() + File.separator;
}


}
