package com.alviss.WinternitzSignature;

import java.util.Arrays;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;

class Keygen {

	public static Keygen instance;
	private String algorithm;

	private Keygen(String algorithm) {
		this.algorithm = algorithm;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		byte[][] priv = new byte[32][32];
		SecureRandom randBytes = SecureRandom.getInstance("DRBG");

		for (byte[] i : priv) {
			randBytes.nextBytes(i);
		}
		System.out.println(Arrays.deepToString(priv));
	}

	public static Keygen getInstance(String alg){
	    if (instance == null) {
	        instance = new Keygen(alg);
	    }
	    return instance;
    }

} 

