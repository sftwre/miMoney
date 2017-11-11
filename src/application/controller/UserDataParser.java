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

	public UserDataParser(User user) 
	{
		super(user);
		bufferInput = null;
	}
	
	
	/**
	 * Used to retrieve the account information of the current
	 * user
	 * @return a User with their username, password, and phone number
	 */
	public User readUserData()
	{
		
		try {
		
			//set the user profile to the account information file
			userProfile += String.format("%s", this.user.getUsername() + ".txt");
			
			bufferInput = new BufferedReader(new FileReader(userProfile));
			
			String  line  = new String(bufferInput.readLine());
			
			while(line != null && !line.equals(""))
			{
				String properties[]=  line.split(":");
				
				this.user.setPassword(properties[1]);
				this.user.setPhone(properties[2]);
				
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
