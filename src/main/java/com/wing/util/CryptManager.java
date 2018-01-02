package com.wing.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptManager {
	
	public static String encrypt(String password){
        MessageDigest digest = null;
        try {
            if(password!=null){
                digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(password.getBytes("UTF-8"));
                StringBuilder builder = new StringBuilder();
                for(byte b:bytes){
                    builder.append(String.format("%02x",0xFF & b));
                }
                return builder.toString();
            }else{
                System.out.println("senha e nula");
                return null;
            }

        } catch (NoSuchAlgorithmException e) {
            return password;
        } catch (UnsupportedEncodingException e) {
            return password;
        }
    }

	
}
