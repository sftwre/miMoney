package application.model;

/**
 * Every user account has a user, salt, pass(hash)
 * associated with it. Their pass input is combined
 * with the accounts unique salt and then hashed.
 * This salt and hash is stored with the user
 * account and is used for authentication purposes.
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