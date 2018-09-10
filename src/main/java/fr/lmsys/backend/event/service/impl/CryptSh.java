package fr.lmsys.backend.event.service.impl;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CryptSh {
	private static final Logger logger = LoggerFactory.getLogger(CryptSh.class);
	@Autowired
	byte[] getSalt;
	
//	public static byte[] getSalt() throws NoSuchAlgorithmException
//	{
//	    //Always use a SecureRandom generator
//		logger.info("before SecureRandom");
//	    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//	    logger.info("after SecureRandom");
//	   // SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//	    logger.info("after SecureRandom");
//	    //Create array for salt
//	    byte[] salt = new byte[16];
//	    //Get a random salt
//	    sr.nextBytes(salt);
//	    //return salt
//	    return salt;
//	}
	
	
	public boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        logger.info("after part");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
        logger.info("after hash");
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        logger.info("after spec");
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        logger.info("after skf");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
        logger.info("after generatesecret");
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
	
	public String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        logger.info("in getSalt");
        byte[] salt = getSalt;
        
     //   SecretKey secretKey = new SecretKeySpec(encoded, "AES");
         
        logger.info("in generateStorngPasswordHash");
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        logger.info("in PBEKeySpec");
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        logger.info("after SecretKeyFactory.getInstance");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        logger.info("after generateSecret");
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
	
/*	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String  originalPassword = "password";
        String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
        System.out.println(generatedSecuredPasswordHash);
         
        boolean matched = validatePassword("password", generatedSecuredPasswordHash);
        System.out.println(matched);
         
        matched = validatePassword("password1", generatedSecuredPasswordHash);
        System.out.println(matched);
    }
     
    */
    private byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    
     
    
     
    public static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}
