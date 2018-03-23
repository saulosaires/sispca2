package arquitetura.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
	
	private  Cryptography() {
		 throw new IllegalStateException("Utility class");
	}
	
	//Função para criar hash da senha informada  
    public static String md5(String senha){  
        String sen = "";  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("MD5"); 
            
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
            sen = hash.toString(16);              
            return sen; 
            
        } catch (NoSuchAlgorithmException e) {  
          SispcaLogger.logError(e.getMessage());
        }  
        
        return "";
    }

}
