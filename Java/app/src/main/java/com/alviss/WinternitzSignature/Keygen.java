package com.alviss.WinternitzSignature;

import java.util.Arrays;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

class Keygen {
  
  private class KeyType {
    private byte[][] privKey;
    private ArrayList<Byte>[] pubKey;
    
    private KeyType(byte[][] privKey, ArrayList<Byte>[] pubKey){
      this.privKey = privKey;
      this.pubKey = pubKey;
    }
    
    private byte[][] getPrivKey(){
      return privKey;
    }
    
    private ArrayList<Byte>[] getPubKey(){
      return pubKey;
    }
  }

	public static Keygen instance;
	private String algorithm;

	private Keygen(String algorithm) {
		this.algorithm = algorithm;
	}
	
	private byte[][] genPrivKey() throws NoSuchAlgorithmException {
		byte[][] priv = new byte[32][32];
		SecureRandom randBytes = SecureRandom.getInstance("DRBG");

		for (byte[] i : priv) {
			randBytes.nextBytes(i);
		}
		return priv;
	}
	
	private ArrayList<Byte>[] genPubKey() {
		MessageDigest md;
		byte[][] privKey; 
		byte[] currHash;
		ArrayList<Byte>[] result= new ArrayList[32];
		try {
			privKey = this.genPrivKey();
			md = MessageDigest.getInstance(this.algorithm);
			for(byte i = 0; i < privKey.length; i++){
				result[i] = new ArrayList<Byte>();
				currHash = md.digest(privKey[i]);
				for(byte j : currHash){
					result[i].add(j);
				}
				//if(i == 31) System.out.println(Arrays.toString(result));
				//if(i == 31) System.out.println(Arrays.toString(currHash));
				System.out.println(result[i].size());
			}
			//System.out.println(result.length);
			//System.out.println(Arrays.toString(result));
			System.out.println("----------------------++++++++--------------------");
			Byte[] nxt = result[6].toArray(new Byte[0]);
			byte[] axa = new byte[nxt.length];
			for(Byte x = 0; x < nxt.length; ++x){
				axa[x] = nxt[x];
			}
			System.out.println(Arrays.toString(nxt));
			System.out.println(Arrays.toString(axa));
			System.out.println(Arrays.toString(md.digest(privKey[6])));
			return result;
		} catch(NoSuchAlgorithmException excepttt){
			throw new IllegalArgumentException("Please enter a valid algorithm.");
		}
		
	}
	
	HashMap<String, Object> returnKeys() throws NoSuchAlgorithmException {
	  byte[][] privKey = this.genPrivKey();
	  ArrayList<Byte>[] pubKey = this.genPubKey();
	  HashMap<String, Object> keys = new HashMap<>();
	  keys.put("privKey", privKey);
	  keys.put("pubKey", pubKey);
	  return keys;
	}

	public static void main(String[] args)  throws NoSuchAlgorithmException {
		Keygen fr = Keygen.getInstance("SHA-256");
		//System.out.println(Arrays.deepToString(fr.genPrivKey()));
		System.out.println(fr.returnKeys());
	}

	public static Keygen getInstance(String alg){
	    if (instance == null) {
	        instance = new Keygen(alg);
	    }
	    return instance;
    }

} 

