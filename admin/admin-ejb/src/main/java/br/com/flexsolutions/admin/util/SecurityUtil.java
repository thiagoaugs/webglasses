package br.com.flexsolutions.admin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe com metodos utils para a implementacao do login
 * 
 * @author Thiago Augusto
 */
public class SecurityUtil {
	
	public static String hash(String string){
		try {
			//Create MessageDigest and encoding for input String
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(string.getBytes("UTF-8"));
			
			//Hash the Input String
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < hash.length; i++) {
	          sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
