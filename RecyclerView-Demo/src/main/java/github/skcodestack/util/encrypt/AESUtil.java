package github.skcodestack.util.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.annotation.SuppressLint;
import android.util.Base64;
/**
 * 
 * ============================================================
 * 
 * copyright ZENG　HUI (c) 2014
 * 
 * author : HUI
 * 
 * version : 1.0
 * 
 * date created : On November 20, 2014 9:30:34
 * 
 * description : AES tools 
 * 
 * revision history :
 * 
 * ============================================================
 *
 */
public class AESUtil {
	@SuppressLint("NewApi")
	public static String encrypt(String input,String key){

		byte[] crypted = null;
		try{
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, skey);

			crypted = cipher.doFinal(input.getBytes());
		}catch(Exception e){
			System.out.println(e.toString());
		}

		return new String(Base64.encode(crypted, 0));

	}

	@SuppressLint("NewApi")
	public static String decrypt(String encryptData,String mykey) {
		byte[] decrypt = null; 
		SecretKeySpec keySpec = new SecretKeySpec(mykey.getBytes(), "AES"); 
		try{ 
			Key key = keySpec; 
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
			cipher.init(Cipher.DECRYPT_MODE, key); 
			decrypt = cipher.doFinal(Base64.decode(encryptData,0)); 
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
		if(decrypt!=null)
			return new String(decrypt).trim();
		return null; 
	} 


}
