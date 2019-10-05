package com.assignment.shoppingApp.utils;


import static com.assignment.shoppingApp.constant.AssignmentConstant.CREATE_TOKEN_EXCEPTION;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import org.apache.log4j.Logger;

import com.assignment.shoppingApp.exception.AssignmentException;


public class TokenGeneratorUtil
{

	private static Logger logger = Logger.getLogger(TokenGeneratorUtil.class);

	/**
	 * This will generate token
	 * 
	 * @param email
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateToken(String email) throws AssignmentException
	{
		String encryptedKey = null;
		try
		{
			Date date = new Date();
			email = email.concat(date.toString());
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(email.getBytes(StandardCharsets.UTF_8));
			encryptedKey = Base64.getEncoder().encodeToString(hash);			
		}catch(Exception exception)
		{
			logger.info(exception);
			throw new AssignmentException(ResourceManager.getProperty(CREATE_TOKEN_EXCEPTION));
		}
		
		return encryptedKey;
		
	}
}
