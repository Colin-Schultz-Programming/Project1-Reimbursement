package com.reimbursement.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;


import org.apache.log4j.Logger;

import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.User;
import com.reimbursement.repo.ReimbursementDao;
import com.reimbursement.repo.UserDao;

public class UtilityFunctions {
	public static int confirmINT(Scanner sc) {
		while(!sc.hasNextInt()) { //prevents improper input
			System.out.println("Please input an integer.");
			sc.next();
		}
		return sc.nextInt();
	}
	//md5 Salt hash
	public static String encryptPassword(String password) {
		String returnPass = null;
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            returnPass = sb.toString();
            
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return returnPass;
		
	}
	
	public static boolean verifyLogin(String username, String password, Logger logger) {
		UserDao checker = new UserDao(logger);
		User check = checker.findByUserName(username);
		
		if(check.getUsername().equals(username) && check.getPassword().equals(UtilityFunctions.encryptPassword(password))) {
			return true;
		}
		return false;
	}
	
	public static void registerUser(User user, Logger logger) {
		UserDao creator= new UserDao(logger);
		creator.create(user);
	}
	
	public static List<Reimbursement> retrieveReimbursements(User user, Logger logger){
		ReimbursementDao retrieve  = new ReimbursementDao(logger);
		return retrieve.findAllByUser(user.getUserID());
	}
	
	
	
}
