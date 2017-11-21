package application.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import application.controller.FileParser;
import application.model.User;


/**
 * UserDataParser reads the account information of a user
 * 
 * @author Isaac Buitrago
 *
 */
public class UserDataParser extends FileParser {

	/**
	 * 
	 * @param user who's account information is to be retrieved
	 */
	public UserDataParser(User user) 
	{
		super(user);
	}
	
	
	/**
	 * Used to retrieve the account information of the current
	 * user and store it in the User field of the FileParser
	 * @return a User with a username, password, salt, and phone number
	 */
	public User readUserData()
	{
		
		try {
		
			//set the user profile to the account information file
			this.userProfile += String.format("%s", this.user.getUsername() + ".txt");
			
			bufferInput = new BufferedReader(new FileReader(this.userProfile));
			
			String  line  = bufferInput.readLine();
			
			while(line != null && !line.isEmpty())
			{
				String properties[] = line.split(":");
				
				this.user.setPassword(properties[1]);
				this.user.setSalt(properties[2]);
				this.user.setPhone(properties[3]);
				
				line = bufferInput.readLine();
			}
			
			bufferInput.close();
			
		}catch(FileNotFoundException e) {
			
			System.out.printf("File: %s does not exits", userProfile);
			
		} catch (IOException e) {
			
			System.out.printf("Could not open or close the file: %s\n", userProfile);
			
		} 
		
		return this.user;
		
	}
}
