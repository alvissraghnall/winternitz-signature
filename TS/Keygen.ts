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

  returnKeys(algorithm: string): {privKey: Uint8Array[], pubKey: Buffer[]} {
    return {
      privKey: this.genPrivate(),
      pubKey: this.genPublic(algorithm)
    }
  }

  private genPublic(algorithm: string): Buffer[] {
    const privKey = this.genPrivate();
    const hash = (alg: string, data: Uint8Array) => createHash(alg).update(data).digest();
    const pubKey: Buffer[] = new Array(32);
    for(let i in privKey){
      pubKey[i] = hash(algorithm, privKey[i]);
    }
    return pubKey;
  }

}

const oop = new Keygen();
console.log(oop.returnKeys("sha256").pubKey[5].toString("hex"));


export default Keygen;