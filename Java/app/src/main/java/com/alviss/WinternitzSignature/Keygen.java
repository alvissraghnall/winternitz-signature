package com.alviss.WinternitzSignature;

import java.util.Arrays;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

class Keygen {

	public static Keygen instance;
	
	private Keygen(){
	  
	}
	
	private byte[][] genPrivKey() throws NoSuchAlgorithmException {
		byte[][] priv = new byte[32][32];
		SecureRandom randBytes = SecureRandom.getInstance("DRBG");

		for (byte[] i : priv) {
			randBytes.nextBytes(i);
		}
		return priv;
	}
	
	private byte[][] genPubKey(byte[][] privKey) throws NoSuchAlgorithmException {
		byte[][] pubKey = new byte[32][32];
		
		for(byte i = 0; i < privKey.length; ++i){
		  pubKey[i] = this.hash256Times(privKey[i]);
		}
		return pubKey;
	}
	
	private hash256Times(byte[] pkb) throws NoSuchAlgorithmException {
	  MessageDigest md = MessageDigest.getInstance("SHA-256");
	  for(int i = 0; i < 256; i++){
	    pkb = md.digest(pkb);
	  }
	  return pkb;
	}
	
	public HashMap<String, byte[][]> returnKeys() throws NoSuchAlgorithmException {
	  byte[][] privKey = this.genPrivKey();
	  byte[][] pubKey = this.genPubKey(privKey);
	  HashMap<String, byte[][]> keys = new HashMap<>();
	  keys.put("privKey", privKey);
	  keys.put("pubKey", pubKey);
	  return keys;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Keygen fr = Keygen.getInstance();
		//System.out.println(Arrays.deepToString(fr.genPrivKey()));
		System.out.println(fr.returnKeys());
	}

	public static Keygen getInstance(){
	  if (instance == null) {
	    instance = new Keygen();
	  }
	  return instance;
  }

} 

