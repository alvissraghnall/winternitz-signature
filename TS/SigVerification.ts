import { createHash } from "crypto";
import SigGen from "./SigGen";

export default class SigVerification {
  
  private static digestFromMessage(msg: string): string[] {
    const msgHashInBinary = SigVerification.computeHash(msg, true).split('').map(i => parseInt(i, 16).toString(2).padStart(4, '0')).join('');
    const msgHashInBinaryArray = SigGen.destructureBin(msgHashInBinary);
    
    return msgHashInBinaryArray;
  }
  
  private static computeHash(data: string, hex?: boolean) {
    const hash = (data: string) => hex ? createHash("sha256").update(data).digest("hex") : createHash("sha256").update(data).digest();
    return hash;
  }
  
  public static verify (message: string, publicKey: Buffer[], sig: Buffer[]) {
    const msgHash = SigVerification.digestFromMessage(message);
    
    let hashTimes: number;
    let messageByte: string;
    let value: Buffer;
    
    for(let i = 0; i < sig.length; i++) {
      messageByte = msgHash[i];
      hashTimes = parseInt(messageByte, 2);
      for (let j = 0; j < hashTimes; j++) {
        /** i don tire, bro. */
      }
    }
  }
}