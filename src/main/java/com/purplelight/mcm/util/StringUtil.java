package com.purplelight.mcm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	private static MessageDigest digest;
	static {
        try {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
	
	public static boolean IsNullOrEmpty(String str){
		return str == null || "".equals(str);
	}
	
	public static String md5StringFor(String s){
		final byte[] hash = digest.digest(s.getBytes());
        final StringBuilder builder = new StringBuilder();
        for (byte b : hash) {
        	String encodeB = Integer.toString(b & 0xFF, 16);
        	if (encodeB.length() == 1){
        		encodeB = "0" + encodeB;
        	}
            builder.append(encodeB);
        }
        return builder.toString();
	}
}
