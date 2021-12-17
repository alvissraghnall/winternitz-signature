import secrets
import hashlib
import binascii

def hash256(data):
  for i in range(0, 256):
    data = hashlib.sha256(data).digest()
  return data

def keygen():
  """The function which generates a cryptographically secure assymetric keypair for the winternitz signature: keygen"""
  privKey = [0 for x in range (32)]
  pubKey = [0 for x in range (32)]
  
  for i in range(0, len(privKey)):
    privKey[i] = secrets.token_bytes(32)
    pubKey[i] = hash256(privKey[i])
    
  keypair = { 'privKey': privKey, 'pubKey': pubKey }
  
  #print(pubKey[23], hash256(privKey[23]))
  return keypair
  
keygen()