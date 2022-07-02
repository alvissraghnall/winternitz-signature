import { createHash } from "crypto";

class SigGen {
  message: string;
  constructor(msg: string){
    this.message = msg;
  }
  
  private hashMessage(): string {
    const msgHash = createHash("sha256").update(this.message).digest("hex");
    return msgHash;
  }
  
  retHM(): string {
    return this.hashMessage()
  }
  
  hex2bin(data: string): string { 
    return data.split('').map(i =>
    parseInt(i, 16).toString(2).padStart(4, '0')).join('');
  }
  
  /*private */static destructureBin(bin: string): string[] {
    let binArray: string[] = Array(32);
    for(let i = 0; i < bin.length; ++i){
      let curr;
      if(i % 8 === 0){
        binArray[i / 8] = bin.slice(i, i + 8);
      }
    }
    return binArray;
  }
  
  public signature(binArray: string[]): string[] {
    const hash = (data: string) => createHash("sha256").update(data).digest("hex");
    const sig = binArray.map(binVal => {
      const hashTimes = 256 - parseInt(binVal, 2);
      //console.log(hashTimes);
      for(let i = 0; i < hashTimes; i++){
        //console.log(i, binVal);
        binVal = hash(binVal);
      }
      return binVal;
    });
    return sig;
  }
}

const sig = new SigGen("God.");
console.log(sig.retHM(), sig.signature(sig.destructureBin(sig.hex2bin(sig.retHM()))));
export default SigGen;