import { createHash } from "crypto";
import SigGen from "./SigGen";

export default class SigVerification {
  
  private static digestFromMessage(msg: string): string[] {
    const msgHashInBinary = createHash("sha256").update(msg).digest("hex").split('').map(i => parseInt(i, 16).toString(2).padStart(4, '0')).join('');
    const msgHashInBinaryArray = SigGen.destructureBin(msgHashInBinary);
    
    return msgHashInBinaryArray;
  }
  
  public static verifySignature (String message, Buffer[] publicKey) {
    
  }
}