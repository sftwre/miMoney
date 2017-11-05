package application.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import application.model.FinanceType;
import application.model.User;


/**
 * 
 * A FileRW can read and write data to a User's profile
 * @author Isaac Buitrago
 *
 */

public class FileParser {
	
	protected User user;							//The user who's data will be retrieved
													//use relative paths Path or String  doesnt matter
	protected String userProfile; 					//Location of the User's profile in UserProfiles directory
	
	protected BufferedReader bufferInput;			//Buffer for faster reading of data
	
	protected FinanceType recordType;				//Type of Financial record to be retrieved
	
public FileParser(User user) 
{		
		this.user = user;
		this.userProfile = "UserProfiles" + 
		File.pathSeparator + user.getUsername();
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
public void setUserProfile(User user) {
	this.userProfile = "UserProfiles" + File.pathSeparator + user.getUsername();
}


/**
 * Reset the path that the user will
 * @param userProfile the path to the User's profile in the UserProfiles directory
 */
public void setUserProfile(String userProfile) {
	this.userProfile = userProfile;
}

}
