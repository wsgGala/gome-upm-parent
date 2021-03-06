package com.gome.upm.common.gtrace;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class TokenGenerator {
	//private static Logger logger = LogManager.getLogger(TokenGenerator.class);
	private static Logger logger = LoggerFactory.getLogger(TokenGenerator.class);
	
    public static String generateTreeToken(String originData) {
        return "TREE_ID_" + generate(originData);
    }
    
    public static String generate(String originData) {
        StringBuilder result = new StringBuilder();
        if (originData != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte bytes[] = md.digest(originData.getBytes());
                for (int i = 0; i < bytes.length; i++) {
                    String str = Integer.toHexString(bytes[i] & 0xFF);
                    if (str.length() == 1) {
                        str += "F";
                    }
                    result.append(str);
                }
            } catch (NoSuchAlgorithmException e) {
                logger.error("Cannot found algorithm.", e);
                System.exit(-1);
            }
        }
        return result.toString().toUpperCase();
    }
}
