package com.example;

import java.security.*;

public class Mda5 {
	private String str;
	
	public Mda5(String str){
		this.str=str;
	}
	public Mda5(){
		
	}
	public String returnMda5(){
		byte[] defaultBytes = str.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte[] messageDigest = algorithm.digest();
			StringBuffer hexString = new StringBuffer();
			
			for(int i=0;i<messageDigest.length;i++){
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			String foo = messageDigest.toString();
			System.out.println("sessionid "+str+" md5 version is "+hexString.toString());
			str=hexString+"";
		}catch(NoSuchAlgorithmException nsae){
		            
		}


		
		return str;
	}	
}
