package com.alviss.WinternitzSignature;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

class SigGen {
  
  
  private byte[] messageDigest (String msg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    return md.digest(msg.getBytes(StandardCharsets.UTF_8));
  }
  
  private String[] digestToBytes(byte[] digest){
    String[] mainDig = new String[digest.length];
    for(byte i = 0; i < digest.length; i++){
      mainDig[i] = String.format("%8s", Integer.toBinaryString(digest[i] & 0xFF)).replace(' ', '0');
    }
    return mainDig;
  }
  
  String[] pubmainDig() throws NoSuchAlgorithmException {
    return this.digestToBytes(this.messageDigest("God."));
  }
  
  public static void main(String args[]) throws NoSuchAlgorithmException {
    SigGen sig = new SigGen();
    System.out.println(sig.pubmainDig().length);
    System.out.println(Arrays.toString(sig.pubmainDig()));
  }
}