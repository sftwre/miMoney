package application.model;

/**
 * Every user's password is hashed and stored
 * with a known salt (randomized).
 * @author jguzm
 **/

import java.security.Security;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

import java.lang.StringBuilder;
import java.util.Arrays;
import java.util.Scanner;

public class SecurePass
{
    private final Scanner input = new Scanner(System.in);
	
  public void secure()
  { 
    String salt = createSalt();
    
    System.out.printf("Enter password: %n");
    String hash = hashFun(input.nextLine(), salt);
    
    System.out.printf("this is the salt: %s%n", salt);
    System.out.printf("this is the hash: %s\n", hash);
    
    //TODO: take hash and store with salt and username as key
  }// END method()
  
  public static String createSalt()
  {
    SecureRandom rand = new SecureRandom();
    StringBuilder build = new StringBuilder();
    
    byte salt[] = new byte[32];
    rand.nextBytes(salt);
    
    for(int i = 0; i<salt.length; i++)
      build.append(String.format("%02x", salt[i]));
    
    return build.toString();
  }// END createSalt()
  
  public static String hashFun(String pass, String salt)
  {
    StringBuilder build = new StringBuilder();
    MessageDigest dig = null;
    String combo = pass + salt;
    
    byte[] arr1 = combo.getBytes();
    
    try{
      dig = MessageDigest.getInstance("SHA-256");
    }catch(NoSuchAlgorithmException e){
      System.out.println("\nsorry\n"); 
    }// END try/catch
    
    byte[] arr2 = dig.digest(arr1);
    
    for(int i = 0; i<arr2.length; i++)
      build.append(String.format("%02x", arr2[i]));
    //System.out.println(Arrays.toString(passArr));
    //System.out.println(build1 + "YAY");
    
    return build.toString();
  }// END hashFun()
  
}// END APPLICATION CLASS SecurePass