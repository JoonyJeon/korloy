/*****************************************************************************
 * 프로그램명  : AES256Util.java
 * 설     명  : 코드변환처리 .
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.12  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.util;


import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
 




import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
 

 
public class AES256Util {
    //private String iv;
	byte[] iv = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
    private Key keySpec;
 
    public AES256Util(String key, String strSalt) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[32];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if(len > keyBytes.length) {
            len = keyBytes.length;
        }
        
        // convert key ^ strSalt
        byte[] salt = strSalt.getBytes("UTF-8");
        for (int i = 0; i < len; i++) {
        	keyBytes[i] = (byte) (b[i] ^ salt[i%salt.length]);
        }
        
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
 
        this.keySpec = keySpec;
    }
    
    // 암호화
    public String encrypt(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, 
                                                     NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
                                                     IllegalBlockSizeException, BadPaddingException{
    	Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	byte[] iv = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 }; 
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
    	
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
 
        return enStr;
    }
 
    //복호화
    public String decrypt(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, 
                                                     NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
                                                     IllegalBlockSizeException, BadPaddingException {
    	Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding"); //AES/CBC/NoPadding
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv)); 
    	
        byte[] byteStr = Base64.decodeBase64(str.getBytes());
 
        return new String(c.doFinal(byteStr),"UTF-8");
    }
    
    /**
     * 랜덤한 Uuid를 반환한다.
     * 
     * @return 랜덤한 Uuid
     */
	public static String randomUuid()  {
        String uuid = null;
        	
    	String uuid1 = UUID.randomUUID().toString();
    	String uuid2 = UUID.randomUUID().toString();
    	uuid = (uuid1 + uuid2).replaceAll("-", "");
    	if(uuid.length() > 50) {
    		uuid = uuid.substring(0, 50);
    	} else {
    		uuid = (uuid + uuid + uuid + uuid + uuid).substring(0, 50); 
    	}
        	        
        return uuid;
    }
}
