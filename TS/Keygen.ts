import { randomFillSync, createHash } from "crypto";


class Keygen {

  private genPrivate(): Uint8Array[] {
    const privKey: Uint8Array[] = new Array(32);
    for (let i = 0; i < privKey.length; ++i) {
      privKey[i] = new Uint8Array(32);
      randomFillSync(privKey[i]);
    }
    //console.log(privKey, privKey.reduce((acc, curr) => acc += curr.byteLength, 0));
    return privKey;
  }

  private hash256(data: Uint8Array) {
    const hash = (data: Uint8Array) => createHash("sha256").update(data).digest();
    let currHash: Buffer | Uint8Array = data;
    let i: number = 0;
    while (i < 256) {
      currHash = hash(currHash);
      //console.log(i, data, currHash);
      i++;
    }
    return currHash as Buffer;
  }

  returnKeys(): { privKey: Uint8Array[], pubKey: Buffer[] } {
    return {
      privKey: this.genPrivate(),
      pubKey: this.genPublic()
    }
  }

  private genPublic(): Buffer[] {
    const privKey = this.genPrivate();
    const pubKey: Buffer[] = new Array(32);
    for (let i in privKey) {
      pubKey[i] = this.hash256(privKey[i]);
    }
    return pubKey;
  }

}

const oop = new Keygen();
//console.log(oop.returnKeys("sha256").pubKey[5].toString("hex"));
//console.log(oop.hash256());
//console.log(oop.genPublic());
//console.log(oop.hash256("hello").toString("hex"));

export default Keygen;